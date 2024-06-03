<template>
    <div class="box">
        <div class="banner-box">
            <BannerImg />
        </div>
        <div class="search-box">
            <YoutubeVideoSearch />
        </div>
        <div class="video-list-box">
            <YoutubeVideoList />
        </div>
        <div class="video-category-box">
            <VideoListByCategory />
        </div>
        <div>
            <GptAPI />
        </div>
    </div>
</template>

<script setup>
import BannerImg from "@/components/banner/BannerImg.vue";
import GptAPI from "@/components/gpt/GptAPI.vue";
import VideoListByCategory from "@/components/video/VideoListByCategory.vue";
import YoutubeVideoList from "@/components/youtube/YoutubeVideoList.vue";
import YoutubeVideoSearch from "@/components/youtube/YoutubeVideoSearch.vue";
</script>

<style scoped>
.search-box {
    display: flex;
    justify-content: flex-end;
    margin-right: 20px;
    margin-top: 50px;
    margin-bottom: 10px;
}
.banner-box {
    margin-top: 30px;
}
.video-list-box {
    margin: 0 auto 0;
    justify-content: center;
}
.video-category-box {
    margin-top: 30px;
}
</style>
