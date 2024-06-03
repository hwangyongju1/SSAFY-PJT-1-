<script setup>
import { RouterLink } from "vue-router";
import TheHeaderNav from "./components/common/TheHeaderNav.vue";
</script>

<template>
    <TheHeaderNav />
    <RouterLink />
</template>

<style>
@font-face {
    font-family: "SUITE-Regular";
    src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2304-2@1.0/SUITE-Regular.woff2")
        format("woff2");
    font-weight: 400;
    font-style: normal;
}

* {
    font-family: "SUITE-Regular";
    /* color: #111; */
}
</style>
