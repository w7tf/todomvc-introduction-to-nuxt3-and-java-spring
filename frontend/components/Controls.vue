<script setup>
const { data: allItems } = await useFetch('http://localhost:8080/api/v1/todos')
const todosLeft = ref(allItems.value.length)
const hideControls = ref(false)

watch(allItems, () => {
        todosLeft.value = allItems.value.filter(todo => !todo.completed).length
        if (allItems.value.length === 0) {
                hideControls.value = true
        } else {
                hideControls.value = false
        }
})

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
        refreshNuxtData()
}

</script>

<template>
        <footer :class="hideControls ? 'hidden' : 'grid grid-cols-3'"
                class="items-center p-2 text-gray-400 border-t-[1px] footer">
                <div class="pl-2">
                        <span>{{ todosLeft }} items left</span>
                </div>
                <div class="flex justify-between items-center mx-auto space-x-2">
                        <NuxtLink class="py-1 px-2" active-class="border-red-300 border rounded-sm opacity-80" to="all">
                                All</NuxtLink>
                        <NuxtLink class="py-1 px-2" active-class="border-red-300 border rounded-sm opacity-80"
                                to="active">Active</NuxtLink>
                        <NuxtLink class="py-1 px-2" active-class="border-red-300 border rounded-sm opacity-80"
                                to="completed">Completed</NuxtLink>
                </div>
                <div class="pr-2 text-right">
                        <button @click="clearCompleted">Clear completed</button>
                </div>

        </footer>
</template>


<style scoped>
.footer {
        box-shadow: 0 1px 1px rgb(0 0 0 / 20%), 0 8px 0 -3px #f6f6f6, 0 9px 1px -3px rgb(0 0 0 / 20%), 0 16px 0 -6px #f6f6f6, 0 17px 2px -6px rgb(0 0 0 / 20%);
}
</style>