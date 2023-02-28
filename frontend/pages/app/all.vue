<script setup lang="ts">
import { storeToRefs } from 'pinia'
import { useTodoStore } from '~~/store/useTodo';

const store = useTodoStore()
const { todos } = storeToRefs(store)



</script>


<template>
    <TodoItemsWrapper v-for="todo in todos" :key="todo.id">
        <TodoItem :id="todo.id" :title="todo.title" :isCompleted="todo.completed" />
    </TodoItemsWrapper>
</template>