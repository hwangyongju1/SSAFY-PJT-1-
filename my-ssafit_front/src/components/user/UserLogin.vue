<template>
    <div class="login">
        <h2>로그인</h2>
        <ul class="first">
            <li>
                <input
                    type="text"
                    name="userId"
                    id="userId"
                    placeholder="아이디"
                    title="아이디입력"
                    v-model.trim="user.userId"
                />
            </li>
            <li>
                <input
                    type="password"
                    name="userPassword"
                    id="userPassword"
                    placeholder="비밀번호"
                    title="비밀번호입력"
                    v-model.trim="user.userPassword"
                />
            </li>
            <li><button @click="userLogin">로그인</button></li>
        </ul>
        <div class="sub">
            <ul class="second">
                <li>
                    <RouterLink :to="{ name: 'join' }">회원가입</RouterLink>
                </li>
            </ul>
        </div>
    </div>
</template>

<script setup>
import {ref} from 'vue';
import {useUserStore} from '@/stores/user'

const store = useUserStore();

const user = ref({})

const userLogin = function () {
    store.userLogin(user.value);
};
</script>

<style scoped>
@import "../../assets/css/reset.css";
@import "../../assets/css/userLogin.css";
</style>
