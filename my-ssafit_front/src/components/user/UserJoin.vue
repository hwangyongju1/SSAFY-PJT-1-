<template>
    <div class="join">
        <h2>회원가입</h2>
        <ul class="first">
            <li>
                아이디
                <span class="id-box">
                    <input
                        type="text"
                        name="userId"
                        id="userId"
                        placeholder="아이디"
                        v-model.trim="user.userId"
                        autofocus
                        :readonly="isIdChkOk"
                    />
                    <button id="midBtn" @click="userIdChk">아이디 확인</button>
                </span>
            </li>
            <li>
                비밀번호
                <input
                    type="password"
                    name="userPassword"
                    id="pwd"
                    placeholder="비밀번호"
                    v-model.trim="user.userPassword"
                />
            </li>
            <li>
                비밀번호 확인
                <input
                    type="password"
                    id="pwdCheck"
                    placeholder="비밀번호 확인"
                    v-model.trim="userPasswordChk"
                />
            </li>
            <li>
                이름
                <input
                    type="text"
                    id="userName"
                    placeholder="이름"
                    v-model.trim="user.userName"
                />
            </li>
            <li>
                이메일
                <input
                    type="email"
                    name="email"
                    id="email"
                    placeholder="이메일"
                    v-model.trim="user.userEmail"
                />
            </li>
            <li>
                직업
                <input
                    type="text"
                    name="userJob"
                    id="userJob"
                    placeholder="직업"
                    v-model.trim="user.userJob"
                />
            </li>
            <li class="input">
                <button @click="userJoin">회원가입</button>
            </li>
        </ul>
    </div>
</template>

<script setup>
import { useUserStore } from "@/stores/user";
import axios from "axios";
import { ref } from "vue";

let regUserId = /^[a-zA-Z0-9]{4,20}$/;
let regUserEmail =
    /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/;
let regUserPassword = /^[a-zA-Z0-9_]{4,20}$/;
let regUserName = /^[ㄱ-ㅎ가-힣]{2,20}$/;
let regUserJob = /^[ㄱ-ㅎ가-힣]{2,20}$/;

const store = useUserStore();

const user = ref({
    userId: "",
    userPassword: "",
    userName: "",
    userEmail: "",
    userJob: "",
});
const userPasswordChk = ref("");

const isIdChkOk = ref();

const userIdChk = function () {
    axios
        .get(`http://localhost:8080/user/${user.value.userId}`)
        .then((response) => {
            isIdChkOk.value = response.data;
            if (isIdChkOk.value) {
                alert("사용 가능한 아이디입니다.");
            } else {
                alert("사용할 수 없는 아이디입니다.");
            }
        });
};

const userJoin = function () {
    if (!regUserId.test(user.value.userId)) {
        alert("아이디는 4-20글자 사이의 영문과 숫자 조합만 가능합니다.");
    } else if (user.value.userPassword != userPasswordChk.value) {
        alert("비밀번호가 일치하지 않습니다.");
        console.log(userPasswordChk.value);
    } else if (!regUserPassword.test(user.value.userPassword)) {
        alert("비밀번호는 4-20글자 사이의 영문과 숫자 조합만 가능합니다.");
    } else if (!regUserName.test(user.value.userName)) {
        alert("이름을 다시 한번 확인해주세요!");
    } else if (!regUserEmail.test(user.value.userEmail)) {
        alert("이메일 양식을 다시 확인해주세요!");
    } else if (!isIdChkOk.value) {
        alert("아이디 확인 버튼을 눌러주세요!");
    } else if(!regUserJob.test(user.value.userJob)) {
        alert("직업 양식을 다시 확인해주세요!")
    }
    else {
        store.userJoin(user.value);
    }
};
</script>

<style scoped>
@import "../../assets/css/reset.css";
@import "../../assets/css/userJoin.css";
</style>
