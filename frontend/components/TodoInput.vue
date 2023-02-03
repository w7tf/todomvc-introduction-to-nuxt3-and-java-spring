<script setup lang="ts">
import ChevronDown from './ChevronDown.vue';

const input = ref<HTMLInputElement | null>(null)

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
    await refreshNuxtData()
}


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
    await refreshNuxtData()
}


</script>

<template>

    <div class="flex border-gray-200  py-4 px-2 w-full min-w-full">
        <div class="flex align-middle space-x-2 w-full min-w-full">
            <button @click="setAllCompleted" class="mt-1 cursor-pointer" >
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