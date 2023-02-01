<script setup>
const { data: todos, refresh } = await useFetch('http://localhost:8080/api/v1/todos')

const isEmpty = computed(() => {
    return todos.value.length === 0
})

const showToggle = computed(() => {
    return !isEmpty.value
})

function dispatchRefresh() {
    refresh()
}

async function setAllCompleted() {
    try {
        await $fetch(`http://localhost:8080/api/v1/todos/toggle-all`, {
            headers: {
                'Content-Type': 'application/json',
            },
            method: 'POST',
            body: JSON.stringify({
            }),
        })
    } catch (e) {
        console.log('Error:' + e)
    }
    refresh()
}

const itemsLeft = computed(() => {
    return todos.value.filter((todo) => !todo.completed).length
})

</script>

<template>

    <section class="flex justify-center">
        <h1 class="text-6xl text-red-200">
            todos
        </h1>
    </section>
    <section class="my-8 lg:w-4/12 mx-auto bg-white">
        <div class="flex flex-col justify-center mx-auto w-full">
            <div class="shadow-lg w-full mx-auto">
                <TodoInput :showToggle="showToggle" :setAllCompleted="setAllCompleted" :refresh="dispatchRefresh" />
                <TodoItemsWrapper v-for="todo in todos">
                    <TodoItem :title="todo.title" :isCompleted="todo.completed" :id="todo.id"
                        :refresh="dispatchRefresh" />
                </TodoItemsWrapper>
                <Controls v-if="!isEmpty" :refresh="dispatchRefresh" :itemsLeft="itemsLeft" />
            </div>
        </div>
    </section>
</template>


<style>
body {
    @apply bg-gray-200 px-4 py-2 lg:p-0
}
</style>