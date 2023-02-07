<script setup>

const route = useRoute()

if (route.path === '/app') {
    navigateTo('/app/all')
}


</script>

<template>

    <section class="flex justify-center">
        <h1 class="text-6xl text-red-200">
            todos
        </h1>
    </section>
    <section class="my-8 lg:w-4/12 mx-auto bg-white">
        <div class="flex flex-col justify-center mx-auto w-full">
            <div class="shadow-lg w-full mx-auto">
                <TodoInput />

               
                    <NuxtPage :transition="false"></NuxtPage>
                

                <TodoControls />
            </div>
        </div>
    </section>

</template>


<style>
body {
    @apply bg-gray-200 px-4 py-2 lg:p-0
}
</style>