<script setup lang="ts">
import { useTodoStore } from '~~/store/useTodo';

const store = useTodoStore()

const props = defineProps<{ title: string, isCompleted: boolean, id: string }>()
const isHovered = ref(false)
const isSetToEdit = ref(false)
const title = ref(props.title)

async function toggleCompleted() {
    isSetToEdit.value = false
    store.toggleCompleted(props.id, props.isCompleted)
}

async function deleteTodo() {
    store.deleteTodoById(props.id)
}

async function updateTodoTitle() {
    store.editTodoById(props.id, title.value)
    isSetToEdit.value = false
}

function handleDoubleClick() {
    isSetToEdit.value = true
}

</script>



<template>
    <div @mouseenter="isHovered = true" @mouseleave="isHovered = false"
        class="flex items-center text-xl font-medium text-gray-600 ">
        <div class="flex items-center space-x-4">
            <button aria-label="Toggle todo status" :id="props.id" class="cursor-pointer" @click="toggleCompleted"
                data-test="toggle-state">
                <Circle data-testid="incompleted-icon" class="cursor-pointer" v-if="!props.isCompleted" />
                <CircleChecked data-testid="completed-icon" class="cursor-pointer" v-if="props.isCompleted" />
            </button>
            <button v-show="!isSetToEdit" v-on:dblclick="handleDoubleClick" class="cursor-text" data-testid="todo-button">
                <span :class="props.isCompleted ? 'line-through text-gray-300' : ''" data-testid="todo">
                    {{ props.title }}
                </span>
            </button>
            <form @submit.prevent="updateTodoTitle" data-testid="todo-edit-form">
                <input v-show="isSetToEdit" v-model="title" data-testid="todo-edit-input"
                    class="focus:outline-none text-xl placeholder:text-gray-200 placeholder:italic placeholder:font-normal w-full min-w-full" />
            </form>
        </div>
        <button aria-label="Delete" v-show="isHovered" class="ml-auto mr-2" @click="deleteTodo">
            <X class="text-red-300 cursor-pointer " data-testid="delete-icon" />
        </button>
    </div>
</template>
