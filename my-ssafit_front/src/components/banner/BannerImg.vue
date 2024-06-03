<template>
    <div><img :src="store.banner.bannerImgFile" alt="" /></div>
</template>

<script setup>
import { useUserStore } from "@/stores/user";
import { onMounted } from "vue";

const store = useUserStore();
onMounted(() => {
    store.getActiveBanner();
});
</script>

<style scoped>
img {
    width: 100%;
}
</style>
