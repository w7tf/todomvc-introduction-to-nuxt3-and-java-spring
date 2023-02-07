<template>

    <section class="fixed top-0 left-0  h-screen w-screen z-10 bg-white">
        <div class="flex flex-col h-full w-full justify-center items-center space-y-4">

            <h1 class="text-center font-bold text-4xl">An error occured while trying to display todos, is the backend
                running?</h1>

            <NuxtLink href="/app"
                class="text-white bg-red-400 hover:bg-blue-800 focus:ring-4 focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 mr-2 mb-2 dark:bg-blue-600 dark:hover:bg-blue-700 focus:outline-none dark:focus:ring-blue-800">
                If you are sure the backend is running, click here to try again
            </NuxtLink>
        </div>
    </section>
</template>