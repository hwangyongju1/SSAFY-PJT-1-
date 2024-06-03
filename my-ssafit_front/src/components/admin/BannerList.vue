<template>
    <div class="container">
        <div class="title">배너 리스트</div>

        <div class="btn-box">
            <button
                data-bs-toggle="modal"
                data-bs-target="#videoDetail"
                data-bs-whatever="@getbootstrap"
            >
                이미지 등록
            </button>
        </div>
        <table class="table table-hover text-center">
            <thead class="table-light">
                <tr>
                    <th class="align-middle" scope="col">#</th>
                    <th class="align-middle" scope="col">이미지 미리보기</th>
                    <th class="align-middle" scope="col">배너명</th>
                    <th class="align-middle" scope="col">사용버튼</th>
                    <th class="align-middle" scope="col">현재상태</th>
                    <th class="align-middle" scope="col">삭제</th>
                </tr>
            </thead>
            <tbody>
                <tr
                    v-for="(banner, index) in currentPageUserList"
                    :key="banner.bannerName"
                >
                    <td class="align-middle" scope="row">{{ index + 1 }}</td>
                    <td class="align-middle" scope="row">
                        <img :src="banner.bannerImgFile" alt="" style="max-width: 200px;">
                    </td>
                    <td class="align-middle" scope="row">
                        {{ banner.bannerName }}
                    </td>
                    <td class="align-middle" scope="row">
                        <button class="btn btn-secondary" @click="activeBanner(banner.bannerIdx)">활성화</button>
                    </td>
                    <td class="align-middle" scope="row">
                        <div v-if="banner.bannerSwitch === true">활성화</div>
                        <div v-if="banner.bannerSwitch !== true">비활성화</div>
                    </td>
                    <td class="align-middle" scope="row">
                        <button class="btn btn-warning" @click="removeBanner(banner.bannerIdx)">삭제</button>
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
                        배너 등록
                    </h1>
                    <button
                        type="button"
                        class="btn-close"
                        data-bs-dismiss="modal"
                        aria-label="Close"
                        @click="modalClose"
                    ></button>
                </div>
                <div class="modal-body">
                    <div class="mb-3">
                        <img
                            :src="imagePreview"
                            v-show="imagePreview !== null"
                            style="
                                margin-bottom: 20px;
                                width: 100%;
                                height: 20%;
                            "
                        />
                        <table class="table">
                            <tr>
                                <th scope="col">배너이름</th>
                                <td scope="row">
                                    <input
                                        type="text"
                                        class="form-control"
                                        v-model="banner.imgTitle"
                                    />
                                </td>
                            </tr>
                            <tr>
                                <th scope="col">파일 등록</th>
                                <td scope="row">
                                    <input
                                        type="file"
                                        class="form-control"
                                        @change="onFileChange"
                                        ref="fileInput"
                                    />
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
                        @click="modalClose"
                    >
                        닫기
                    </button>
                    <button
                        type="button"
                        class="btn btn-primary"
                        @click="regBanner"
                    >
                        등록
                    </button>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
import { useUserStore } from "@/stores/user";
import { computed, onMounted, ref } from "vue";

const store = useUserStore();

onMounted(() => {
    store.getBanner();
});

const imagePreview = ref(null);

const onFileChange = function (e) {
    const file = e.target.files[0];

    if (file && file.type.startsWith("image/")) {
        const reader = new FileReader();
        reader.onload = (e) => {
            imagePreview.value = e.target.result;
        };
        reader.readAsDataURL(file);
        banner.value.imgFile = file; // 파일을 banner의 imgFile 속성에 저장
    } else {
        imagePreview.value = null;
    }
};

const modalClose = function () {
    imagePreview.value = null;
    banner.value.imgFile = {}; // 파일 값을 초기화
    banner.value.imgTitle = ""; // 배너 이름도 초기화
    // 파일 입력 요소 초기화
    const fileInput = document.querySelector('input[type="file"]');
    if (fileInput) {
        fileInput.value = null;
    }
};

//title/ imgFile
const banner = ref({
    imgTitle: "",
    imgFile: {},
});

const regBanner = function () {
    store.regBanner(banner.value); // banner 값을 넘겨서 등록
    location.reload();
};

const perPage = 5;
const currentPage = ref(1);

const pageCount = computed(() => {
    return Math.ceil(store.banners.length / perPage);
});

const clickPage = function (page) {
    currentPage.value = page;
};

const currentPageUserList = computed(() => {
    return store.banners.slice(
        (currentPage.value - 1) * perPage,
        currentPage.value * perPage
    );
});

const removeBanner = function (bannerName) {
    const chk = confirm("삭제하시겠습니까?")
    if(chk) {
        store.removeBanner(bannerName);
    }
}

const activeBanner = function (bannerName) {
    store.activeBanner(bannerName);
}
</script>

<style scoped>
.container .title {
    margin-bottom: 15px;
    font-size: 2rem;
    font-weight: 600;
}
.file-box {
    margin-bottom: 20px;
    text-align: center;
    /* display: grid; */
    /* grid-template-columns: 2fr 2fr 1fr; */
}
.file-input-box {
    width: 30%;
    align-items: center;
    text-align: center;
    margin: 0 auto 10px;
    display: grid;
    grid-template-columns: 1fr 3fr;
}
.sub-title {
    font-size: 1.4rem;
    margin-bottom: 15px;
}
.btn-box {
    text-align: end;
    margin-bottom: 10px;
}
.btn-box button {
    background: inherit;
    border: none;
    box-shadow: none;
    border-radius: 0;
    padding: 0;
    overflow: visible;
    cursor: pointer;
    margin-right: 10px;
    border: 1px solid rgb(0, 0, 0, 0.5);
    padding: 5px 20px;
    border-radius: 5px;
}
</style>
