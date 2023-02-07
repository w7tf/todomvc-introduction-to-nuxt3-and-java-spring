<script setup lang="ts">

const { data: todos, error, pending } = await useFetch<Todo[]>('http://localhost:8080/api/v1/todos/active')

if (error.value) {
    navigateTo('/error')
}




</script>


<template>

    <TodoItemsWrapper v-if="!pending" v-for="todo in todos" :key="todo.id">
        <TodoItem :id="todo.id" :title="todo.title" :isCompleted="todo.completed" />
    </TodoItemsWrapper>

</template>