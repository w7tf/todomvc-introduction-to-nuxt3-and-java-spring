<script setup lang="ts">
import { storeToRefs } from '@pinia/nuxt/dist/runtime/composables';
import { useTodoStore } from '~~/store/useTodo';
import ChevronDown from './ChevronDown.vue';

const input = ref<HTMLInputElement | null>(null)

const store = useTodoStore()



async function setAllCompleted() {
    store.setAllCompleted()
}

async function addTodo() {
    if (input.value) {
        await store.addTodo(input.value.value)
        input.value.value = ''
    }
}



</script>

<template>
    <div class="flex border-gray-200  py-4 px-2 w-full min-w-full">
        <div class="flex align-middle space-x-2 w-full min-w-full">
            <button @click="setAllCompleted" class="mt-1 cursor-pointer" data-testid="completed-button">
                <ChevronDown class="text-gray-200 font-bold" />
            </button>
            <form @submit.prevent="addTodo()" class="w-full" data-testid="todo-form">
                <input ref="input"
                    class="focus:outline-none text-xl placeholder:text-gray-200 placeholder:italic placeholder:font-normal w-full min-w-full"
                    placeholder="What needs to be done?" data-testid="todo-input" />
            </form>
        </div>
    </div>
</template>