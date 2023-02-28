// https://nuxt.com/docs/api/configuration/nuxt-config
export default defineNuxtConfig({
    test: true,
    ssr: true,
    modules: [
        '@nuxtjs/tailwindcss',
        'nuxt-vitest',
        '@pinia/nuxt'
    ],
})
