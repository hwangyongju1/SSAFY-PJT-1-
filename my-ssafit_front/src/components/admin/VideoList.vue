<template>
    <div class="container">
        <div class="title">동영상 리스트</div>
        <table class="table table-hover text-center">
            <thead class="table-light">
                <tr class="" style="font-size: 1.1rem">
                    <th class="align-middle" scope="col">#</th>
                    <th class="align-middle" scope="col">영상 코드</th>
                    <th class="align-middle" scope="col">제목</th>
                    <th class="align-middle" scope="col">카테고리</th>
                    <th class="align-middle" scope="col">수정</th>
                    <th class="align-middle" scope="col">삭제</th>
                </tr>
            </thead>
            <tbody>
                <tr
                    v-for="(video, index) in currentPageVideoList"
                    :key="video.videoId"
                >
                    <td class="align-middle" scope="row">{{ index + 1 }}</td>
                    <td class="align-middle" scope="row">
                        {{ video.videoId }}
                    </td>
                    <td class="align-middle" scope="row">
                        {{ video.videoTitle }}
                    </td>
                    <td class="align-middle" scope="row">
                        {{ video.videoCategory }}
                    </td>
                    <td class="align-middle" scope="row">
                        <button
                            type="button"
                            class="btn btn-primary"
                            data-bs-toggle="modal"
                            data-bs-target="#videoDetail"
                            data-bs-whatever="@getbootstrap"
                            @click="getVideoNum(video.videoId)"
                        >
                            수정
                        </button>
                    </td>
                    <td scope="row">
                        <button
                            class="btn btn-warning"
                            @click="deleteVideo(video.videoId)"
                        >
                            삭제
                        </button>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
    <nav aria-label="Page navigation">
        <ul class="pagination d-flex justify-content-center">
            <li class="page-item">
                <a
                    class="page-link"
                    @click.prevent="currentPage--"
                    :class="{ disabled: currentPage <= 1 }"
                    href="#"
                    >&lt;</a
                >
            </li>
            <li
                class="page-item"
                :class="{ active: currentPage === page }"
                v-for="page in pageCount"
                :key="page"
            >
                <a
                    class="page-link"
                    href="#"
                    @click.prevent="clickPage(page)"
                    >{{ page }}</a
                >
            </li>
            <li class="page-item">
                <a
                    class="page-link"
                    @click.prevent="currentPage++"
                    :class="{ disabled: currentPage >= pageCount }"
                    href="#"
                    >&gt;</a
                >
            </li>
        </ul>
    </nav>

    <div
        class="modal fade"
        id="videoDetail"
        tabindex="-1"
        aria-labelledby="exampleModalLabel"
        aria-hidden="true"
    >
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h1 class="modal-title fs-5" id="exampleModalLabel">
                        비디오 상세
                    </h1>
                    <button
                        type="button"
                        class="btn-close"
                        data-bs-dismiss="modal"
                        aria-label="Close"
                    ></button>
                </div>
                <div class="modal-body">
                    <div class="mb-3">
                        <img
                            :src="videoById.videoImgUrl"
                            style="margin-bottom: 20px"
                        />
                        <table class="table">
                            <thead class="table-light">
                                <th>항목</th>
                                <th>입력값</th>
                            </thead>
                            <tr>
                                <th scope="col">제목</th>
                                <td scope="row">
                                    <input
                                        type="text"
                                        class="form-control"
                                        v-model="videoById.videoTitle"
                                    />
                                </td>
                            </tr>
                            <tr>
                                <th scope="col">카테고리</th>
                                <td scope="row">
                                    <select
                                        class="form-control"
                                        v-model="videoById.videoCategory"
                                    >
                                        <option
                                            v-for="item in store.videoCategorySort"
                                            :key="item.category"
                                            :value="item.category"
                                        >
                                            {{ item.category }}
                                        </option>
                                    </select>
                                </td>
                            </tr>
                        </table>
                    </div>
                </div>
                <div class="modal-footer">
                    <button
                        type="button"
                        class="btn btn-secondary"
                        data-bs-dismiss="modal"
                    >
                        닫기
                    </button>
                    <button
                        type="button"
                        class="btn btn-primary"
                        @click="modifyVideo(videoById.videoId)"
                    >
                        수정
                    </button>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
import router from "@/router";
import { useVideoStore } from "@/stores/video";
import axios from "axios";
import { computed, onMounted, ref } from "vue";

const store = useVideoStore();

onMounted(() => {
    store.getVideoList();
    store.getCategoryListSort();
});
const perPage = 5;
const currentPage = ref(1);

const pageCount = computed(() => {
    return Math.ceil(store.videoList.length / perPage);
});

const clickPage = function (page) {
    currentPage.value = page;
};

const currentPageVideoList = computed(() => {
    return store.videoList.slice(
        (currentPage.value - 1) * perPage,
        currentPage.value * perPage
    );
});

// 비디오 삭제(삭제후 router로 이동시 삭제가 안된 것처럼 보이기 때문에 새로고침)
const deleteVideo = function (videoId) {
    const isDelete = confirm("정말 삭제학시겠습니까?");
    if (isDelete) {
        axios
            .delete(`http://localhost:8080/video/remove/${videoId}`)
            .then(() => {
                location.reload();
            });
    }
};

// 비디오 가져오기
const videoById = ref({});
const getVideoNum = function (videoId) {
    axios.get(`http://localhost:8080/video/${videoId}`).then((response) => {
        videoById.value = response.data;
    });
};

// 비디오 수정
const modifyVideo = function (videoId) {
    axios
        .put(`http://localhost:8080/video/${videoId}`, videoById.value)
        .then(() => {
            location.reload();
        });
};
</script>

<style scoped>
.container .title {
    margin-bottom: 15px;
    font-size: 2rem;
    font-weight: 600;
}
</style>
