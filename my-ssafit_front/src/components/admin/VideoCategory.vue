<template>
    <div class="container" style="width: 50%">
        <div>
            <div class="title">키워드 추가</div>
            <div class="form-control" style="margin-bottom: 40px">
                <div
                    style="
                        display: grid;
                        grid-template-columns: 2fr 2fr 1fr;
                        column-gap: 10px;
                        margin-bottom: 5px;
                        text-align: center;
                    "
                >
                    <div style="font-weight: 700">키워드</div>
                    <div style="font-weight: 700">카테고리</div>
                </div>
                <div
                    style="
                        display: grid;
                        grid-template-columns: 2fr 2fr 1fr;
                        column-gap: 10px;
                    "
                >
                    <input
                        type="text"
                        class="form-control"
                        v-model="category.keyword"
                    />
                    <select class="form-control" v-model="category.category">
                        <option
                            v-for="item in store.videoCategorySort"
                            :key="item.category"
                            :value="item.category"
                        >
                            {{ item.category }}
                        </option>
                    </select>
                    <button class="btn btn-secondary" @click="addCategory">
                        추가
                    </button>
                </div>
            </div>
        </div>
        <div class="title">키워드 리스트</div>
        <table class="table table-hover text-center">
            <thead class="table-light">
                <tr>
                    <th class="align-middle">키워드</th>
                    <th class="align-middle">카테고리</th>
                    <th class="align-middle">삭제</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="video in store.videoCategory" :key="video.keyword">
                    <td class="align-middle">{{ video.keyword }}</td>
                    <td class="align-middle">
                        {{ video.category }}
                    </td>
                    <td>
                        <button
                            class="btn btn-danger"
                            @click="deleteCategory(video.keyword)"
                        >
                            삭제
                        </button>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
    <div style="margin-top: 200px"></div>
</template>

<script setup>
import { useVideoStore } from "@/stores/video";
import axios from "axios";
import { onMounted, ref } from "vue";

const store = useVideoStore();

onMounted(() => {
    store.getCategoryList();
    store.getCategoryListSort();
});

const category = ref({
    keyword: "",
    category: "",
});

const addCategory = function () {
    store.addCategory(category.value);
};

const deleteCategory = function (key) {
    const isDelete = confirm("정말 삭제하시겠습니까?");
    if (isDelete) {
        axios.delete(`http://localhost:8080/video/category/${key}`).then(() => {
            location.reload();
        });
    }
};
</script>

<style scoped>
.container .title {
    margin-bottom: 15px;
    font-size: 2rem;
    font-weight: 600;
}
</style>
