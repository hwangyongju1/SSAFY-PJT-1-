import { ref, computed } from "vue";
import { defineStore } from "pinia";
import axios from "axios";
import router from "@/router";

const REST_USER_API = `http://localhost:8080/video`;

export const useVideoStore = defineStore("video", () => {
    // 비디오 전체 리스트
    const videoList = ref([]);
    const getVideoList = function () {
        axios.get(`${REST_USER_API}/list`).then((response) => {
            videoList.value = response.data;
        });
    };

    // 카테고리에 따른 영상 리스트 가져오기
    const videoListByCategory = ref([]);
    const getVideoListByCategory = function (category) {
        axios.get(`${REST_USER_API}/list/${category}`).then((response) => {
            videoListByCategory.value = response.data;
        });
    }

    // 비디오 카테고리 전체 가져오기
    const videoCategory = ref();
    const getCategoryList = function () {
        axios.get(`${REST_USER_API}/category`).then((response) => {
            videoCategory.value = response.data;
        });
    };

    // 비디오 카테고리 중복 제거
    const videoCategorySort = ref([]);
    const getCategoryListSort = function () {
        axios.get(`${REST_USER_API}/categorySort`).then((response) => {
            videoCategorySort.value = response.data;
        });
    };

    // 카테고리 추가
    const addCategory = function (category) {
        axios({
            url: `${REST_USER_API}/addCategory`,
            method: "POST",
            data: category,
        })
            .then(() => {
                location.reload();
            })
            .catch((error) => {
                console.log(error);
            });
    };

    const video = ref({});
    const clickVideoByDB = function (videoId) {
        axios.get(`${REST_USER_API}/${videoId}`).then ((response) => {
            video.value = response.data;
            // console.log(video.value)
        })
        router.push({path : `videoDetail/${videoId}`});
    }
    
    const clickVideo = function (videoId) {
        axios.get(`${REST_USER_API}/${videoId}`).then ((response) => {
            video.value = response.data;
        })
    }

    return {
        videoList,
        getVideoList,
        videoCategory,
        getCategoryList,
        videoCategorySort,
        getCategoryListSort,
        addCategory,
        videoListByCategory,
        getVideoListByCategory,
        clickVideoByDB,
        video,
        clickVideo,
    };
});
