<script setup lang="ts">

const props = defineProps<{ setAllCompleted: () => void, refresh: () => void, showToggle: boolean }>()
const input = ref<HTMLInputElement | null>(null)
const setInvisible = computed(() => {
    return !props.showToggle && 'invisible'
})


async function addTodo() {
    if (input.value) {
        try {
            await $fetch(`http://localhost:8080/api/v1/todos`, {
                headers: {
                    'Content-Type': 'application/json',
                },
                method: 'POST',
                body: JSON.stringify({
                    "title": input.value.value,
                    "completed": false
                }),
            })
        } catch (e) {
            console.log('Error:' + e)
        }
        input.value.value = ''
    }
    props.refresh()
}
</script>

<template>

    <div class="flex border-gray-200  py-4 px-2 w-full min-w-full">
        <div class="flex align-middle space-x-2 w-full min-w-full">
            <button @click="setAllCompleted" class="mt-1 cursor-pointer" :class="setInvisible">
                <ChevronDown class="text-gray-200 font-bold" />
            </button>
            <form @submit.prevent="addTodo()" class="w-full">
                <input ref="input"
                    class="focus:outline-none text-xl placeholder:text-gray-200 placeholder:italic placeholder:font-normal w-full min-w-full"
                    placeholder="What needs to be done?" />
            </form>
        </div>
    </div>
</template>