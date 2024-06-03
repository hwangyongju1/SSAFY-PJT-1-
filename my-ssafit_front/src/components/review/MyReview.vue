<template>
    <div class="container">
        <div class="title">내가 작성한 리뷰 목록</div>
        <div v-show="currentPageUserList.length == 0" class="review-none">
            현재 작성된 리뷰가 없습니다.
        </div>
        <div v-show ="currentPageUserList.length != 0"
            v-for="(review, index) in currentPageUserList"
            :key="review.reviewIdx"
        >
            <div class="form-control content-box">
                <div>{{ index + 1 }}</div>
                <div>{{ review.reviewContent }}</div>
                <button
                    type="button"
                    class="btn btn-secondary"
                    data-bs-toggle="modal"
                    data-bs-target="#videoDetail"
                    data-bs-whatever="@getbootstrap"
                    @click="
                        getReviewData(review.reviewVideoId, review.reviewIdx)
                    "
                >
                    수정
                </button>
                <button
                    class="btn btn-warning"
                    @click="deleteReview(review.reviewIdx)"
                >
                    삭제
                </button>
            </div>
        </div>
        <nav aria-label="Page navigation" v-show ="currentPageUserList.length != 0">
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
    </div>
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
                        <div>{{ videoStore.video.videoTitle }}</div>
                        <img
                            :src="videoStore.video.videoImgUrl"
                            style="width: 180px; height: 120px"
                        />
                        <div></div>
                        <div
                            style="
                                margin-top: 10px;
                                font-weight: 700;
                                font-size: 1.2rem;
                            "
                        >
                            작성 내용
                        </div>
                        <div>
                            <input
                                type="text"
                                class="form-control"
                                v-model="reviewStore.review.reviewContent"
                            />
                        </div>
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
                        @click="updateReview()"
                    >
                        수정
                    </button>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
import { useReviewStore } from "@/stores/review";
import { useVideoStore } from "@/stores/video";
import { computed, onMounted, ref } from "vue";

const reviewStore = useReviewStore();
const videoStore = useVideoStore();

onMounted(() => {
    reviewStore.getUserReview();
});

// 리뷰 삭제
const deleteReview = function (reviewIdx) {
    const chk = confirm("정말 삭제하시겠습니까?");
    if (chk) {
        reviewStore.deleteReview(reviewIdx);
    }
};

// 리뷰 업데이트
const updateReview = function () {
    reviewStore.updateReview();
    location.reload();
};

// 리뷰 데이터를 받오기(수정시, 비디오에 대한 정보와 리뷰 정보를 가져오는 메서드)
const getReviewData = function (reviewVideoId, reviewIdx) {
    videoStore.clickVideo(reviewVideoId);
    reviewStore.getReviewDetail(reviewIdx);
};

const perPage = 7;
const currentPage = ref(1);

const pageCount = computed(() => {
    return Math.ceil(reviewStore.reviews.length / perPage);
});

const clickPage = function (page) {
    currentPage.value = page;
};

const currentPageUserList = computed(() => {
    return reviewStore.reviews.slice(
        (currentPage.value - 1) * perPage,
        currentPage.value * perPage
    );
});
</script>

<style scoped>
@font-face {
    font-family: "SUITE-Regular";
    src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2304-2@1.0/SUITE-Regular.woff2")
        format("woff2");
    font-weight: 400;
    font-style: normal;
}

* {
    font-family: "SUITE-Regular";
    /* color: #111; */
}
.container {
    width: 70%;
    margin: 0 auto 0;
    justify-content: center;
    margin-bottom: 100px;
}
.title {
    font-weight: 800;
    font-size: 2rem;
    margin-bottom: 20px;
}
.content-box {
    display: grid;
    grid-template-columns: 1fr 6fr 1fr 1fr;
    column-gap: 20px;
    margin-bottom: 5px;
    align-items: center;
}
nav {
    margin-top: 20px;
}
.review-none {
    font-size: 2rem;
    border: 1px solid rgba(0, 0, 0, 0.1);
    border-radius: 5px;
    height: 300px;
    margin: auto;
    align-content: center;
    text-align: center;
}
</style>
