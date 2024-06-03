<template>
    <div>
        <div class="container">
            <div class="edit-container">
                <h2>회원정보 수정</h2>
                <hr class="right-item-title-under" />
                <div class="item-container">
                    <div class="category-box">
                        <div class="item-input-box">
                            <div class="item-input-text">아이디</div>
                            <div class="value-input-box">
                                <input
                                    type="text"
                                    name="userId"
                                    v-model.trim="store.user.userId"
                                    readonly
                                />
                            </div>
                        </div>
                        <div class="item-input-box">
                            <div class="item-input-text">비밀번호</div>
                            <div class="value-input-box">
                                <input
                                    type="password"
                                    name="userPassword"
                                    placeholder="비밀번호를 입력해주세요"
                                    maxlength="13"
                                    autocomplete="off"
                                    v-model.trim = "store.user.userPassword"
                                    required
                                />
                            </div>
                        </div>
                        <div class="item-input-box">
                            <div class="item-input-text">이름</div>
                            <div class="value-input-box">
                                <input
                                    type="text"
                                    name="name"
                                    placeholder="이름을 입력해주세요"
                                    v-model.trim="store.user.userName"
                                    required
                                />
                            </div>
                        </div>
                        <div class="item-input-box">
                            <div class="item-input-text">이메일</div>
                            <div class="value-input-box">
                                <input
                                    type="text"
                                    name="email"
                                    placeholder="이메일을 입력해주세요"
                                    v-model.trim="store.user.userEmail"
                                    required
                                />
                            </div>
                        </div>
                        <div class="item-input-box">
                            <div class="item-input-text">직업</div>
                            <div class="value-input-box">
                                <input
                                    type="text"
                                    name="text"
                                    placeholder="직업을 입력해주세요"
                                    v-model.trim="store.user.userJob"
                                    required
                                />
                            </div>
                        </div>
                        <div class="item-input-box-btn">
                            <input
                                type="button"
                                value="수정"
                                @click = "userDetailChange"
                            />
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
import { useUserStore } from '@/stores/user';
import { onMounted, ref } from 'vue';

const store = useUserStore();

onMounted (() => {
    // 세션 아이디를 넣어서 아이디를 보냄
    const userId = sessionStorage.getItem("userId");
    store.getUserDetail(userId);
});
const userDetailChange = function () {
    store.userDetailChange()
}

</script>

<style scoped>
@import "../../assets/css/userInfoEdit.css";
@import "../../assets/css/reset.css";
</style>