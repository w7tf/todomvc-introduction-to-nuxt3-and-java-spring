<script setup lang="ts">

const props = defineProps<{ title: string, isCompleted: boolean, id: string, refresh: () => void }>()
const isHovered = ref(false)

async function toggleCompleted() {
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
    props.refresh()
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
    props.refresh()
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
            <label :for="props.id" :class="props.isCompleted ? 'line-through text-gray-300' : ''">
                {{ props.title }}
            </label>
        </div>
        <button v-show="isHovered" class="ml-auto mr-2" @click="deleteTodo()">
            <X class="text-red-300 cursor-pointer " />
        </button>
    </div>
</template>