<template>
    <div class="menu">
        <RouterLink :to="{ name: 'userReview' }">내 리뷰</RouterLink>
    </div>
    <div class="menu">
        <RouterLink :to="{ name: 'userDetail' }">회원정보 수정</RouterLink>
    </div>
    <div class="menu">
        <RouterLink :to="{ name: 'userWithdraw' }">회원 탈퇴</RouterLink>
    </div>
    <hr />
</template>

<script setup></script>

<style scoped>
@import "../../assets/css/myPage.css";
@import "../../assets/css/reset.css";
</style>
