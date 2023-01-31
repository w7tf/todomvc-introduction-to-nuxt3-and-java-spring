<script setup lang="ts">
const props = defineProps<{ itemsLeft: number, refresh: () => void }>()

async function clearCompleted() {
        try {
                await $fetch(`http://localhost:8080/api/v1/todos`, {
                        headers: {
                                'Content-Type': 'application/json',
                        },
                        method: 'DELETE',
                })
        } catch (e) {
                console.log('Error:' + e)
        }
        props.refresh()
}



</script>

<template>
        <section class="grid grid-cols-3 p-2 text-gray-400 border-t-[1px]">
                <div class="pl-2">
                        <span>{{ props.itemsLeft }} items left</span>
                </div>
                <div class="mx-auto space-x-2">

                        <button>All</button>
                        <button>Active</button>
                        <button>Completed</button>

                </div>
                <div class="pr-2 text-right">
                        <button @click="clearCompleted">Clear completed</button>
                </div>

        </section>
</template>