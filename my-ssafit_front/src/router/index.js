import { createRouter, createWebHistory } from "vue-router";
import HomeView from "../views/HomeView.vue";
import AdminView from "../views/AdminView.vue";
import UserList from "@/components/admin/UserList.vue";
import VideoList from "@/components/admin/VideoList.vue";
import UserLogin from "@/components/user/UserLogin.vue";
import UserJoin from "@/components/user/UserJoin.vue";
import UserWithdraw from "@/components/user/UserWithdraw.vue";
import UserView from "@/views/UserView.vue";
import VideoCategory from "@/components/admin/VideoCategory.vue";
import VideoDetail from "@/components/video/VideoDetail.vue";
import UserDetail from "@/components/user/UserDetail.vue";
import UserMain from "@/components/user/UserMain.vue";
import MyReview from "@/components/review/MyReview.vue";
import BannerList from "@/components/admin/BannerList.vue";

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        // 메인 페이지
        {
            path: "/",
            name: "home",
            component: HomeView,
        },
        // 관리자 페이지
        {
            path: "/admin",
            name: "admin",
            component: AdminView,
            children: [
                // 회원 리스트 조회
                {
                    path: "",
                    name: "userList",
                    component: UserList,
                },
                // 동영상 리스트 조회
                {
                    path: "video",
                    name: "videoList",
                    component: VideoList,
                },
                // 동영상 분류
                {
                    path: "videoCategory",
                    name: "videoCategory",
                    component: VideoCategory,
                },
                // 배너 리스트
                {
                    path: "bannerList",
                    name: "bannerList",
                    component: BannerList,
                },
            ],
        },
        // 로그인
        {
            path: "/login",
            name: "login",
            component: UserLogin,
        },
        // 회원가입
        {
            path: "/join",
            name: "join",
            component: UserJoin,
        },
        // 회원 탈퇴

        // 개인 페이지
        {
            path: "/myPage",
            name: "myPage",
            component: UserView,
            children: [
                {
                    path: "",
                    name: "userMain",
                    component: UserMain,
                },
                {
                    path: "userWithdraw",
                    name: "userWithdraw",
                    component: UserWithdraw,
                },
                {
                    path: "userDetail",
                    name: "userDetail",
                    component: UserDetail,
                },
                {
                    path: "userReview",
                    name: "userReview",
                    component: MyReview,
                },
            ],
        },
        {
            path: "/videoDetail/:id",
            name: "videoDetail",
            component: VideoDetail,
        },
    ],
});
// Navigation Guard 수정
// router.beforeEach((to, from, next) => {
//     const publicPages = ['/', '/login', '/join']; // 로그인 페이지 경로 추가
//     const authRequired = !publicPages.includes(to.path); // 로그인이 필요한 페이지인지 여부
    
//     const isLoggedIn = !!sessionStorage.getItem('access-token'); // 현재 로그인 상태 확인
  
//     // 로그인이 필요한 페이지에 접근하고 로그인되어 있지 않다면 로그인 페이지로 리다이렉트
//     if (authRequired && !isLoggedIn) {
//       next('/login');
//     } else {
//       next(); // 다음 페이지로 이동
//     }
//   })
export default router;
