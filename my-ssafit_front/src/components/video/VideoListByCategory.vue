<template>
    <div class="btn-box">
        <div v-for="category in store.videoCategorySort" :key="category">
            <button
                class="btn"
                @click="getVideoListByCategory(category.category)"
            >
                {{ category.category }}
            </button>
        </div>
    </div>
    <div class="ul-box" v-show="categoryChk == 0">
        <ul v-for="video in store.videoList" :key="video.videoId">
            <li @click="clickVideo(video.videoId)">
                <div>
                    <img :src="video.videoImgUrl" />
                </div>
                <div class="title">
                    {{ video.videoTitle }}
                </div>
            </li>
        </ul>
    </div>
    <div class="ul-box" v-show="categoryChk == 1" >
        <ul v-for="video in store.videoListByCategory" :key="video.videoId">
            <li @click="clickVideo(video.videoId)">
                <div>
                    <img :src="video.videoImgUrl" />
                </div>
                <div class="title">
                    {{ video.videoTitle }}
                </div>
            </li>
        </ul>
    </div>
    <div v-show="categoryChk == 2">
        <div class="content-none-box">🙏🏻 해당 카테고리가 존재하지 않습니다! 🙏🏻<br/> 검색을 통해 카테고리를 추가해보세요!</div>
    </div>
    <div style="margin-bottom: 150px;"></div>
</template>

<script setup>
import { useVideoStore } from "@/stores/video";
import { onMounted, watch, watchEffect } from "vue";

const store = useVideoStore();

onMounted(() => {
    store.getCategoryListSort();
    store.getVideoList();
    categoryChk = 0;
});
let categoryChk;
const getVideoListByCategory = function (category) {
    store.getVideoListByCategory(category);
};
watchEffect(() => {
    if (store.videoListByCategory.length > 0) {
        categoryChk = 1;
    } else if (store.videoListByCategory.length == 0) {
        categoryChk = 2;
    }
});
const clickVideo = function (videoId) {
    return store.clickVideoByDB(videoId);
};
</script>

<style scoped>
.btn-box {
    display: flex;
    justify-content: center;
    margin-top: 100px;
    margin-bottom: 50px;
    /* border-left: 1px solid rgba(0, 0, 0, 0.1);
    border-right: 1px solid rgba(0, 0, 0, 0.1);
    border: 1px solid rgba(0, 0, 0, 0.1); */
    width: fit-content;
    margin: 100px auto 50px;
    padding: 10px;
    border-radius: 50px;
}
.btn {
    margin-right: 5px;
    background: inherit;
    border: none;
    box-shadow: none;
    border-radius: 0;
    padding: 0;
    overflow: visible;
    cursor: pointer;
    padding: 2px 10px;
    border-radius: 15px;
    color: #5ab2ff;
    font-weight: 800;
    border: 1px solid rgba(0, 0, 0, 0.2);
}
.ul-box {
    display: flex;
    flex-wrap: wrap;
    justify-content: center;

}
.ul-box li {
    list-style-type: none;
}
li {
    background: #fff;
    border: 1px solid rgba(0, 0, 0, 0.1);
    width: 340px;
    height: 260px;
    padding: 10px;
    border-radius: 5px;
    margin: 0 auto 0;
    text-align: center;
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
.content-none-box {
    text-align: center;
    font-size: 2rem;
    height: 300px;
    border-top: 1px solid rgba(0, 0, 0, 0.1);
    border-bottom: 1px solid rgba(0, 0, 0, 0.1);
    border-radius: 5px;
    align-content: center;
    width: 70%;
    margin: 0 auto 0;
}
</style>
