<script lang="ts" setup>
import { useTodoStore } from '~~/store/useTodo';
import { storeToRefs } from 'pinia'

const store = useTodoStore()
const { getActiveTodosCount, getCompletedTodosAmount, getTodosAmount } = storeToRefs(store)

const showCompletedAction = ref(getCompletedTodosAmount)
const showControls = ref(getTodosAmount)

async function clearCompleted() {
        store.clearCompleted()
}

</script>

<template>
        <section data-testid="controls" v-show="showControls"
                class="grid grid-cols-3 items-center p-2 text-gray-400 border-t-[1px] footer">
                <div class="pl-2">
                        <span>{{ getActiveTodosCount }} items left</span>
                </div>
                <div class="flex justify-between items-center mx-auto space-x-2">
                        <NuxtLink class="py-1 px-2" active-class="border-red-300 border rounded-sm opacity-80" to="all">
                                All</NuxtLink>
                        <NuxtLink class="py-1 px-2" active-class="border-red-300 border rounded-sm opacity-80" to="active">
                                Active</NuxtLink>
                        <NuxtLink class="py-1 px-2" active-class="border-red-300 border rounded-sm opacity-80" to="completed">
                                Completed</NuxtLink>
                </div>
                <div class="pr-2 text-right">
                        <button @click="clearCompleted" v-show="showCompletedAction">Clear completed</button>
                </div>

        </section>
</template>


<style scoped>
.footer {
        box-shadow: 0 1px 1px rgb(0 0 0 / 20%), 0 8px 0 -3px #f6f6f6, 0 9px 1px -3px rgb(0 0 0 / 20%), 0 16px 0 -6px #f6f6f6, 0 17px 2px -6px rgb(0 0 0 / 20%);
}
</style>