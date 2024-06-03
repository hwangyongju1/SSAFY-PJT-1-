<template>
    <div class="input-box">
        <input type="text" class="review-input" v-model="reviewContent" />
        <button class="review-input-btn" @click="createReview">입력</button>
    </div>
</template>

<script setup>
import { useReviewStore } from "@/stores/review";
import { ref } from "vue";
import { useRoute } from "vue-router";

const route = useRoute();
const store = useReviewStore();

const reviewContent = ref("");

const createReview = function () {
    store.createReview(reviewContent.value, route.params.id);
};
</script>

<style scoped>
.input-box {
    margin-bottom: 50px;
}
.input-box .review-input {
    border: none;
    width: 70%;
    border-bottom: 1px solid rgba(0, 0, 0, 0.1);
    margin-right: 10px;
}
.input-box .review-input:focus {
    outline: none !important;
    box-shadow: none !important;
}

.input-box .review-input-btn {
    background: inherit;
    border: none;
    box-shadow: none;
    border-radius: 0;
    padding: 0;
    overflow: visible;
    cursor: pointer;
    background-color: #5ab2ff;
    padding: 2px 20px;
    border-radius: 2px;
    color: #fff;
}
</style>
