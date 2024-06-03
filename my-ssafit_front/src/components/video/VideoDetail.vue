<template>
    <div class="container" v-show="videoStore.video === null">
        <div class="video-box">
            <iframe
                width="1200"
                height="700"
                :src="videoURL"
                title="YouTube video player"
                frameborder="0"
                allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
                referrerpolicy="strict-origin-when-cross-origin"
                allowfullscreen
            ></iframe>
        </div>
        <div class="review-box">
            <div class="title-box">{{ youtubeStore.Video.videoTitle }}</div>
            <div class="review-cnt-box">
                댓글 {{ reviewStore.reviews.length }}개
            </div>
            <ReviewCreate />
            <ReviewList
                v-for="review in reviewStore.reviews"
                :key="review.reviewIdx"
                :review="review"
            ></ReviewList>
        </div>
    </div>
    <div class="container" v-show="videoStore.video !== null">
        <div class="video-box">
            <iframe
                width="1200"
                height="700"
                :src="videoURL"
                title="YouTube video player"
                frameborder="0"
                allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
                referrerpolicy="strict-origin-when-cross-origin"
                allowfullscreen
            ></iframe>
        </div>
        <div class="review-box">
            <div class="title-box">{{ videoStore.video.videoTitle }}</div>
            <div class="review-cnt-box">
                댓글 {{ reviewStore.reviews.length }}개
            </div>
            <ReviewCreate />
            <ReviewList
                v-for="review in reviewStore.reviews"
                :key="review.reviewIdx"
                :review="review"
            ></ReviewList>
        </div>
    </div>
    <div style="margin-bottom: 500px"></div>
</template>

<script setup>
import { useVideoStore } from "@/stores/video";
import { computed, onMounted, ref } from "vue";
import { useRoute } from "vue-router";
import ReviewCreate from "../review/ReviewCreate.vue";
import ReviewList from "../review/ReviewList.vue";
import { useReviewStore } from "@/stores/review";
import { useYoutubeStore } from "@/stores/youtube";
    
const route = useRoute();
const videoStore = useVideoStore();
const reviewStore = useReviewStore();
const youtubeStore = useYoutubeStore();

onMounted(() => {
    videoStore.clickVideo(route.params.id);
    reviewStore.getVideoReview(route.params.id);
});

const videoURL = computed(() => {
    const videoId = route.params.id;
    return `https://www.youtube.com/embed/${videoId}`;
});
</script>

<style scoped>
.video-box {
    margin: 50px auto 0;
    text-align: center;
}
.review-box {
    width: 90%;
    margin: 0 auto 0;
    justify-content: center;
}
.title-box {
    font-size: 2rem;
    font-weight: 700;
    margin-top: 10px;
    margin-bottom: 40px;
}
.review-cnt-box {
    font-weight: 800;
    font-size: 1.5rem;
    margin-bottom: 20px;
}
</style>
