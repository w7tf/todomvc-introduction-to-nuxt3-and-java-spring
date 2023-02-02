<script setup lang="ts">

const props = defineProps<{ title: string, isCompleted: boolean, id: string }>()
const isHovered = ref(false)
const isSetToEdit = ref(false)
const title = ref(props.title)

async function toggleCompleted() {
    isSetToEdit.value = false
    try {
        await $fetch(`http://localhost:8080/api/v1/todos/${props.id}`, {
            headers: {
                'Content-Type': 'application/json',
            },
            method: 'POST',
            body: JSON.stringify({
                "completed": !props.isCompleted
            }),
        })
    } catch (e) {
        console.log('Error:' + e)
    }
    refreshNuxtData()
}

async function deleteTodo() {
    try {
        await $fetch(`http://localhost:8080/api/v1/todos/${props.id}`, {
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

async function updateTodoTitle() {
    try {
        await $fetch(`http://localhost:8080/api/v1/todos/${props.id}`, {
            headers: {
                'Content-Type': 'application/json',
            },
            method: 'POST',
            body: JSON.stringify({
                "title": title.value
            }),
        })
    } catch (e) {
        console.log('Error:' + e)
    }
    isSetToEdit.value = false
    refreshNuxtData()
}

function handleDoubleClick() {
    isSetToEdit.value = true
}

</script>



<template>
    <div @mouseenter="isHovered = true" @mouseleave="isHovered = false"
        class="flex items-center text-xl font-medium text-gray-600 ">
        <div class="flex items-center space-x-4">
            <button :id="props.id" class="cursor-pointer" @click="toggleCompleted()">
                <Circle class="cursor-pointer" v-if="!props.isCompleted" />
                <CircleChecked class="cursor-pointer" v-if="props.isCompleted" />
            </button>
            <button v-show="!isSetToEdit" v-on:dblclick="handleDoubleClick" class="cursor-text">
                <span :class="props.isCompleted ? 'line-through text-gray-300' : ''">
                    {{ props.title }}
                </span>
            </button>
            <form @submit.prevent="updateTodoTitle">
                <input v-show="isSetToEdit" v-model="title"
                    class="focus:outline-none text-xl placeholder:text-gray-200 placeholder:italic placeholder:font-normal w-full min-w-full" />
            </form>
        </div>
        <button v-show="isHovered" class="ml-auto mr-2" @click="deleteTodo()">
            <X class="text-red-300 cursor-pointer " />
        </button>
    </div>
</template>