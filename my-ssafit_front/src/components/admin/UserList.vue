<template>
    <div class="container">
        <div class="title">회원 리스트</div>
        <table class="table table-hover text-center">
            <thead class="table-light">
                <tr>
                    <th class="align-middle" scope="col">#</th>
                    <th class="align-middle" scope="col">아이디</th>
                    <th class="align-middle" scope="col">이름</th>
                    <th class="align-middle" scope="col">이메일</th>
                    <th class="align-middle" scope="col">직업</th>
                </tr>
            </thead>
            <tbody>
                <tr
                    v-for="(user, index) in currentPageUserList"
                    :key="user.userId"
                >
                    <td class="align-middle" scope="row">{{ index + 1 }}</td>
                    <td class="align-middle" scope="row">{{ user.userId }}</td>
                    <td class="align-middle" scope="row">
                        {{ user.userName }}
                    </td>
                    <td class="align-middle" scope="row">
                        {{ user.userEmail }}
                    </td>
                    <td class="align-middle" scope="row">
                        {{ user.userJob }}
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
</template>

<script setup>
import { useUserStore } from "@/stores/user";
import { computed, onMounted, ref } from "vue";

const store = useUserStore();

onMounted(() => {
    store.getUserList();
});

const perPage = 5;
const currentPage = ref(1);

const pageCount = computed(() => {
    return Math.ceil(store.userList.length / perPage);
});

const clickPage = function (page) {
    currentPage.value = page;
};

const currentPageUserList = computed(() => {
    return store.userList.slice(
        (currentPage.value - 1) * perPage,
        currentPage.value * perPage
    );
});
</script>

<style scoped>
.container .title {
    margin-bottom: 15px;
    font-size: 2rem;
    font-weight: 600;
}
</style>
