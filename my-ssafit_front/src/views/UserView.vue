<template>
    <div class="mypage-container">
        <div class="mypage-top">
            <TheUserNav />
        </div>
        <div class="mypage-right">
            <RouterView />
        </div>
    </div>
</template>

<script setup>
import TheUserNav from "@/components/common/TheUserNav.vue";
</script>

<style scoped>
@import "../assets/css/myPage.css";
@import "../assets/css/reset.css";
</style>
