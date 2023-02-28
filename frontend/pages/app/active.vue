<script setup lang="ts">
import { useTodoStore } from '~~/store/useTodo';
import { storeToRefs } from 'pinia'

const store = useTodoStore()
const { getActiveTodos: todos } = storeToRefs(store)


</script>


<template>
    <TodoItemsWrapper v-for="todo in todos" :key="todo.id">
        <TodoItem :id="todo.id" :title="todo.title" :isCompleted="todo.completed" />
    </TodoItemsWrapper>
</template>