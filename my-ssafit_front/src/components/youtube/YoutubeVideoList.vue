<template>
    <div>
        <ul>
            <YoutubeVideoListItem
                v-for="video in store.videos"
                :key="video.id.videoId"
                :video="video"
            ></YoutubeVideoListItem>
        </ul>
    </div>
</template>

<script setup>
import { useYoutubeStore } from "@/stores/youtube";
import YoutubeVideoListItem from "./YoutubeVideoListItem.vue";

const store = useYoutubeStore();
</script>

<style scoped>
ul {
    list-style-type: none;
    display: grid;
    grid-template-columns: repeat(5, 1fr);
    column-gap: 10px;
    row-gap: 10px;
}
</style>
