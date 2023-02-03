<script setup lang="ts">

const { data: todos } = await useFetch<Todo[]>('http://localhost:8080/api/v1/todos/completed')


</script>


<template>

    <TodoItemsWrapper v-for="todo in todos" :key="todo.id">
        <TodoItem :id="todo.id" :title="todo.title" :isCompleted="todo.completed"  />
    </TodoItemsWrapper>
    
</template>