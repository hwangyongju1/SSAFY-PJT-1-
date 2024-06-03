import { ref, computed } from "vue";
import { defineStore } from "pinia";
import axios from "axios";
import router from "@/router";

export const useYoutubeStore = defineStore("youtube", () => {
    const videos = ref([]);
    const selectedVideo = ref(null);
    let Video = ref({
        videoId : "",
        videoTitle : "",
        videoImgUrl : ""
    });
    const youtubeSearch = function (keyword) {
        const URL = "https://www.googleapis.com/youtube/v3/search";
        const API_KEY = import.meta.env.VITE_YOUTUBE_API_KEY;

        axios({
            url: URL,
            method: "GET",
            params: {
                key: API_KEY,
                part: "snippet",
                maxResults: 5,
                q: keyword,
                type: "video",
            },
        }).then((response) => {
            videos.value = response.data.items;
        });
    };
    const clickVideoByYoutube = function (video) {
        selectedVideo.value = video;
        Video = {
            videoId : video.id.videoId,
            videoTitle : video.snippet.title,
            videoImgUrl : video.snippet.thumbnails.medium.url
        };
        axios({
            url : `http://localhost:8080/video/addVideo`,
            method : "POST",
            data : Video
        })

        router.push({path : `videoDetail/${selectedVideo.value.id.videoId}`});
    };

    
    return { youtubeSearch, videos, selectedVideo, clickVideoByYoutube, Video };
});
