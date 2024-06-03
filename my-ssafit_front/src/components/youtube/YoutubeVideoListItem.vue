<template>
    <li @click="clickVideoByYoutube">
        <div>
            <img :src="video.snippet.thumbnails.medium.url" />
        </div>
        <div class="title">
            {{ video.snippet.title }}
        </div>
    </li>
</template>

<script setup>
import { useYoutubeStore } from "@/stores/youtube";
import { defineProps } from "vue";
const store = useYoutubeStore();

const props = defineProps({
    video: {
        type: Object,
        required: true,
    },
});

const clickVideoByYoutube = function () {
    store.clickVideoByYoutube(props.video);
};
</script>

<style scoped>
li {
    background: #fff;
    border: 1px solid rgba(0, 0, 0, 0.1);
    width: 340px;
    height: 280px;
    padding: 10px;
    border-radius: 10px;
}
li:hover {
    cursor: pointer;
}
li img {
    border-radius: 5px;
}
.title {
    margin-top: 10px;
    padding-top: 10px;
    border-top: 1px solid rgba(0, 0, 0, 0.1);
    font-size: 1.3rem;
    font-weight: bolder;
    overflow: hidden;
    white-space: nowrap;
    text-overflow: ellipsis;
    word-break: break-all;
}
</style>
