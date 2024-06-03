<template>
    <div class="admin-container">
        <div class="text-center admin-left-container">
            <h4>
                <RouterLink :to="{ name: 'userList' }">관리자메뉴</RouterLink>
            </h4>
            <hr />
            <p><RouterLink :to="{ name: 'userList' }">회원 관리</RouterLink></p>
            <p>
                <RouterLink :to="{ name: 'videoList' }">동영상 관리</RouterLink>
            </p>
            <p>
                <RouterLink :to="{ name: 'videoCategory' }"
                    >키워드 관리</RouterLink
                >
            </p>
            <hr />
            <p><RouterLink :to="{name : 'bannerList'}">배너 관리</RouterLink></p>
            <hr />
        </div>
    </div>
</template>

<script setup></script>

<style scoped>
.admin-container a {
    text-decoration: none;
    color: black;
}
.admin-left-container {
    position: fixed;
    width: 250px;
    height: 100vh;
    border-right: 1px solid rgba(0, 0, 0, 0.5);
}
.admin-left-container h4 {
    padding-top: 50px;
    font-size: 1.3rem;
    font-weight: 600;
}
.admin-left-container hr {
    width: 180px;
    text-align: center;
    margin: 20px auto 20px;
    border-color: #333;
    display: block;
}
.admin-left-container p {
    margin: 20px auto 20px;
    font-size: 1.1rem;
}
</style>
