<template>
    <div class="search-box">
        <input type="text" class="search-input" v-model="keyword" />
        <button @click="search"><i class="bi bi-search"></i></button>
    </div>
</template>

<script setup>
import { onMounted, ref } from "vue";
import { useYoutubeStore } from "@/stores/youtube";

const store = useYoutubeStore();
const keyword = ref("");

const search = function () {
    if (keyword.value === "") {
        alert("검색어를 입력해주세요");
    } else {
        store.youtubeSearch(keyword.value);
    }
};

onMounted(() => {
    store.youtubeSearch("5분 스트레칭");
});
</script>

<style scoped>
.search-box {
    border: 1px solid rgb(0, 0, 0, 0.2);
    padding: 5px;
    border-radius: 50px;
    width: fit-content;
}
.search-box button {
    background: none;
    border-radius: 50%;
    border: 1px solid rgb(0, 0, 0, 0.3);
    border: none;
    padding: 10px 15px;
    font-size: 18px;
    color: #fff;

    background: #5ab2ff;
}
.search-box button:hover {
    background: #3f88c5;
}
.search-input {
    border: none;
    margin: 0 10px;
    font-size: 1.4rem;
}
.search-input:focus {
    outline: none;
}
</style>
