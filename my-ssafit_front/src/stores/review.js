import { ref, computed } from "vue";
import { defineStore } from "pinia";
import axios from "axios";
import router from "@/router";
import { useVideoStore } from "./video";

const REST_REVIEW_API = `http://localhost:8080/review`;

export const useReviewStore = defineStore("review", () => {
    const userId = sessionStorage.getItem("userId");
    const reviews = ref([]);
    // 해당 비디오에 대한 리뷰 가져오기
    const getVideoReview = function (videoId) {
        axios.get(`${REST_REVIEW_API}/video/${videoId}`).then((response) => {
            reviews.value = response.data;
        });
    };

    const createReview = function (reviewContent, videoId) {
        axios
            .post(`${REST_REVIEW_API}/create`, {
                reviewWriter: userId,
                reviewContent: reviewContent,
                reviewVideoId: videoId,
            })
            .then(() => {
                location.reload();
            });
    };

    const getUserReview = function () {
        axios.get(`${REST_REVIEW_API}/user/${userId}`).then((response) => {
            reviews.value = response.data;
        });
    };

    const deleteReview = function (reviewIdx) {
        axios.delete(`${REST_REVIEW_API}/${reviewIdx}`).then(() => {
            location.reload();
        });
    };

    const review = ref({});
    const getReviewDetail = function (reviewIdx) {
        axios.get(`${REST_REVIEW_API}/${reviewIdx}`).then((response) => {
            review.value = response.data;
        });
    };

    const updateReview = function () {
        axios.put(`${REST_REVIEW_API}/update`, review.value);
    };

    return {
        reviews,
        getVideoReview,
        createReview,
        getUserReview,
        deleteReview,
        review,
        getReviewDetail,
        updateReview,
    };
});
