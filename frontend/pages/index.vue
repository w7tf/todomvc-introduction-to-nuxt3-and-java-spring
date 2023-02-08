<script setup>

const route = useRoute()

if (route.path === '/') {
    navigateTo('/app/all')
}


</script>