<template>
    <div class="header-container">
        <div class="title">
            <RouterLink :to="{ name: 'home' }">SSAFIT</RouterLink>
        </div>
        <div class="dropdown">
            <button class="dropbtn">
                <span class="dropdown">
                    <div class="icon-box">
                        <i class="bi bi-list"></i>
                        <i class="bi bi-person-fill"></i>
                    </div>
                </span>
            </button>
            <div class="dropdown-content">
                <div class="content-item" v-show="!store.loginCheck">
                    <RouterLink :to="{ name: 'login' }">로그인</RouterLink>
                </div>
                <div class="content-item" v-show="!store.loginCheck">
                    <RouterLink :to="{ name: 'join' }">회원가입</RouterLink>
                </div>
                <div class="content-item" v-show="store.loginCheck">
                    <RouterLink :to="{ name: 'userMain' }">내 정보</RouterLink>
                </div>
                <!-- 관리자일 때만 보이도록 수정 -->
                <div class="content-item" v-show="store.adminCheck" >
                    <RouterLink :to="{ name: 'userList' }">관리자페이지</RouterLink>
                </div>
                <div class="content-item" v-show="store.loginCheck">
                    <!-- Wrapping the RouterLink inside a div with a click event -->
                    <div @click.prevent="logout">
                        <RouterLink :to="{ name: 'home' }">로그아웃</RouterLink>
                    </div>
                </div>
            </div>

        </div>
    </div>
    <RouterView />
</template>

<script setup>
import { useUserStore } from "@/stores/user";

const store = useUserStore();
const logout = function () {
    store.logout();
};

const userId = sessionStorage.getItem("userId");

</script>

<style scoped>
.header-container {
    margin-top: 10px;
    display: flex;
    justify-content: space-between;
    padding: 0 50px;
}

.header-container .title {
    font-size: 2rem;
    font-weight: 700;
}

.header-container .title a {
    text-decoration: none;
    color: black;
}

.header-container .title a:hover {
    text-decoration: none;
    color: #333;
}

.bi:before {
    vertical-align: 0;
}

.dropdown {
    position: relative;
    display: inline-block;
}

.dropbtn {
    border: 1px solid rgb(0, 0, 0, 0.2);
    border-radius: 25px;
    text-align: center;
    cursor: pointer;
    font-size: 1.5rem;
    margin-bottom: 5px;
    background: #fff;
    min-width: 70px;
}

.dropdown-content {
    display: none;
    position: absolute;
    right: 0px;
    z-index: 1;
    /*다른 요소들보다 앞에 배치*/
    font-weight: 400;
    background-color: #fff;
    min-width: 150px;
    font-weight: 500;
    border: 1px solid rgba(0, 0, 0, 0.2);
    border-radius: 5px;
}

.dropdown-content a {
    display: block;
    text-decoration: none;
    color: rgb(37, 37, 37);
    font-size: 1rem;
    padding: 12px 20px;
}

.dropdown-content a:hover {
    background-color: #ececec;
}

.dropdown-content .content-item {
    border-radius: 5px;
}

.dropdown:hover .dropdown-content {
    display: block;
}

.bi-person-fill {
    color: #555;
}

.bi-list {
    color: #555;
    margin-right: 5px;
    font-size: 20px;
}

i {
    vertical-align: middle;
}
</style>
