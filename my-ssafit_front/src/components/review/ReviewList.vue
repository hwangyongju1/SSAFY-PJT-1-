<template>
    <div class="list-box" >
        <div class="top">
            <span class="id">{{ review.reviewWriter }}</span>
            <span class="regDate">{{ review.reviewRegDate }}</span>
        </div>
        <div class="bottom">
            <span>{{ review.reviewContent }}</span>
        </div>
        <div class="btn-box" v-if="userId === review.reviewWriter">
            <button class="modify-btn"
            type="button"
                    data-bs-toggle="modal"
                    data-bs-target="#reviewDetail"
                    data-bs-whatever="@getbootstrap"
                    @click="getReviewDetail(review.reviewIdx)"
                    >수정</button>
            <button class="remove-btn" @click="deleteReview(review.reviewIdx)">삭제</button>
        </div>
    </div>

    <div
        class="modal fade"
        id="reviewDetail"
        tabindex="-1"
        aria-labelledby="exampleModalLabel"
        aria-hidden="true"
    >
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h1 class="modal-title fs-5" id="exampleModalLabel">
                        리뷰 수정
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
                        <div
                            style="
                                margin-top: 10px;
                                margin-bottom: 10px;
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
                                v-model="review.reviewContent"
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
                        @click="updateReview"
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

const store = useReviewStore();

const userId = sessionStorage.getItem("userId");

const props = defineProps({
    review: {
        type: Object,
        required: true,
    },
});

const getReviewDetail = function (reviewIdx) {
    store.getReviewDetail(reviewIdx);
}

const updateReview = function () {
    store.updateReview();
}

const deleteReview = function (reviewIdx) {
    const isTrue = confirm("댓글을 삭제하시겠습니까?");
    if (isTrue) {
        store.deleteReview(reviewIdx);
    }
}

</script>

<style scoped>
.list-box {
    border: 1px solid rgb(0, 0, 0, 0.1);
    padding: 10px;
    margin: 10px;
    border-radius: 10px;
    width: 70%;
}
.list-box .top .id {
    font-weight: 700;
    margin-right: 10px;
    font-size: 1rem;
}
.list-box .top .regDate {
    font-size: 0.8rem;
}

.list-box .bottom {
    margin-bottom: 20px;
}

.list-box .top {
    margin-bottom: 2px;
}

.btn-box {
    margin: 0 auto 0;
    display: flex;
    justify-content: end;
}
.modify-btn {
    background: inherit ;
    border:none;
    box-shadow:none; 
    border-radius:0; 
    padding:0; 
    overflow:visible; 
    cursor:pointer;
    margin-right: 10px;
    border: 1px solid rgb(0, 0, 0, 0.1);
    padding: 5px 20px;
    border-radius: 5px;
}
.remove-btn {
    background: inherit ;
    border:none;
    box-shadow:none; 
    border-radius:0; 
    padding:0; 
    overflow:visible; 
    cursor:pointer;
    margin-right: 10px;
    color: #fff;
    background-color: #5ab2ff;
    padding: 5px 20px;
    border-radius: 5px;
}
</style>
