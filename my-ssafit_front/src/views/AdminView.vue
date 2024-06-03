<template>
    <hr />
    <div class="admin-container">
        <TheAdminNav />
        <div class="admin-right-container">
            <RouterView />
        </div>
    </div>
</template>

<script setup>
import TheAdminNav from "@/components/common/TheAdminNav.vue";
</script>

<style scoped>
.admin-container {
    display: grid;
    grid-template-columns: 1fr 9fr;
}
.admin-right-container {
    margin-top: 80px;
}
</style>
