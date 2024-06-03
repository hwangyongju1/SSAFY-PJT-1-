import { ref, computed, onMounted } from "vue";
import { defineStore } from "pinia";
import axios from "axios";
import router from "@/router";
import { useFcmStore } from "@/stores/fcm";


const REST_USER_API = `http://localhost:8080/user`;
const REST_BANNER_API = `http://localhost:8080/banner`;

export const useUserStore = defineStore("user", () => {
    const accessToken = ref(sessionStorage.getItem('access-token')) // 저장된 access token 불러오기
    const refreshToken = ref(sessionStorage.getItem('refresh-token')) // 저장된 refresh token 불러오기
    const userId = sessionStorage.getItem("userId");
    const fcmStore = useFcmStore();

    // login -> jwt token + fcm token
    const fcmInfo = ref({
        fcmUserId: '',
        fcmtoken: '',
        fcmjob: '',
        fcmtime2: false,
        fcmtime4: false,
        tokenExpirationTime: '',
    })
    const userLogin = function (user) {
        console.log("login axios : " + user);
        axios
            .post(`${REST_USER_API}/login`,
                user
            )
            .then((response) => {
                sessionStorage.setItem(
                    "access-token",
                    response.data["access-token"]
                );
                sessionStorage.setItem(
                    "refresh-token",
                    response.data["refresh-token"]
                );
                sessionStorage.setItem(
                    "userId",
                    response.data["ID"]
                );
                const token = response.data["access-token"].split(".");
                console.log(response.data)
                fcmInfo.value = response.data["fcmInfo"];

                if(fcmInfo.value === null){
                    router.push({ name: 'home' });
                }
                if (fcmInfo.value.fcmtoken !== '') {
                    
                    if (fcmInfo.value.fcmUserId === '') {
                        axios.get(`${REST_USER_API}/${response.data["ID"]}`)
                            .then((response) => {
                                fcmInfo.value.fcmUserId = response.data.userIdx;
                                fcmInfo.value.fcmjob = response.data.userJob;
                                fcmStore.notificationPermission(fcmInfo.value);
                            })
                    }
                    else {
                        fcmStore.notificationPermission(fcmInfo.value);
                    }
                }
                // if (fcmInfo.value.fcmUserId === '') {
                //     console.log(fcmInfo.value)
                //     // DB는 현재 따로 구현
                //     // FcmDto의 정보가 server에서 반환 -> 구독을 안했다면, 애초에 값이 없다
                //     // 이때는 구독 버튼을 모두 보여줘야 한다

                //     // 만약 user + fcmUser
                //     // login할 때, user 정보를 받아와서, user 정보의 구독 정보를 확인하고
                //     // 구독을 한 상태이면, token과 구독 시간을 fcm.js로 넘기면 된다
                // }else if(fcmInfo.value.fcmtoken === ''){
                //     console.log("here")
                //     console.log(fcmInfo.value)
                //     // fcmUserId는 있고, token이 없다면 기존 구독 정보에 의거하여 token 재발급
                //     fcmStore.notificationPermission(fcmInfo.value);
                //     console.log(token);
                // } else if(fcmInfo === ''){
                //     // 구독 정보가 없을 때, 로그인을 하면 response.data['fcmInfo']는 null 로 들어온다
                //     fcmStore.notificationPermission(fcmInfo.value);
                // }

                router.push({ name: 'home' });

            });
    };

    // 로그아웃
    const logout = function () {
        sessionStorage.removeItem("access-token");
        sessionStorage.removeItem("refresh-token");
        sessionStorage.removeItem("userId");
        // home으로 redirect -> navigation guard 수행
        alert("로그아웃되었습니다.");
        loginCheck.value = sessionStorage.getItem("userId") !== null
        adminCheck.value = sessionStorage.getItem("userId") !== null
        router.push({ name: 'home' });
    };

    // 유저 리스트 반환
    const userList = ref([]);
    const getUserList = function () {
        axios.get(`${REST_USER_API}/list`).then((response) => {
            userList.value = response.data;
        });
    };

    // 회원가입
    const userJoin = function (user) {
        axios({
            url: `${REST_USER_API}/join`,
            method: "POST",
            data: user,
        })
            .then(() => {
                alert("가입되었습니다!");
                router.push({ name: "login" });
            })
            .catch(() => { });
    };

    // 유저 개인 정보
    // 아이디 넣어야해
    const user = ref({
        userId: "",
        userPassword: "",
        userName: "",
        userEmail: "",
        userJob: "",
    });
    const getUserDetail = function (userId) {
        axios.get(`${REST_USER_API}/user/${userId}`).then((response) => {
            user.value = response.data;
        });
    };

    // 개인 정보 수정
    const userDetailChange = async function () {
        axios.put(`${REST_USER_API}/user`, user.value).then(() => {
            alert("회원 정보가 수정되었습니다.");
            router.push({ name: "userMain" });
        }).catch;
    };

    // 회원 탈퇴
    const userWithdraw = async function () {
        console.log(userId);
        axios.delete(`${REST_USER_API}/user/${userId}`)
        .then(() => {
            // logout();
            sessionStorage.removeItem("access-token");
        sessionStorage.removeItem("refresh-token");
        sessionStorage.removeItem("userId");
            alert("탈퇴 완료되었습니다. 그동안 이용해주셔서 감사합니다!");
            router.replace({ name: "home" });
        });
    };

    // jwt token 관리
    const isAccessTokenExpired = function () {
        if (!accessToken.value) return true; // access token이 없는 경우
        const decodedToken = JSON.parse(atob(accessToken.value.split('.')[1]));
        return decodedToken.exp * 1000 < Date.now(); // 현재 시간이 만료 시간보다 큰 경우 true 반환
    }

    const refreshAccessToken = async function () {
        if (!refreshToken.value) return; // refresh token이 없는 경우

        // 서버로 refresh token을 전송하여 새로운 access token을 발급 받음
        axios.post(`${REST_USER_API}/refresh-token`, {
            refresh_token: refreshToken.value
        })
            .then((res) => {
                // 새로운 access token 저장
                console.log("access-token 재발급 : ", res.data["access-token"])
                sessionStorage.setItem('access-token', res.data["access-token"])
            })
            .catch(() => {
                // refresh token이 유효하지 않은 경우, 로그아웃 처리
                logout();
            })
    }

    const loginCheck = ref(false);
    const adminCheck = ref(false);
    // 페이지 이동마다 token 유효한 지 검사
    router.beforeEach((to, from, next) => {
        loginCheck.value = sessionStorage.getItem("userId") !== null
        adminCheck.value = sessionStorage.getItem("userId") === "admin"
        if (isAccessTokenExpired()) {
            refreshAccessToken(); // access token이 만료되었으면 refresh token을 사용하여 access token을 갱신
        }

        const publicPages = ['/', '/login', '/join']; // 로그인 페이지 경로 추가
        const authRequired = !publicPages.includes(to.path); // 로그인이 필요한 페이지인지 여부
        const isLoggedIn = !!sessionStorage.getItem('access-token'); // 현재 로그인 상태 확인
        if (authRequired && !isLoggedIn)
            next('/login');
        else
            next();
    });

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


    //   const banner = ref({});
    const regBanner = async function (banner) {
        const formData = new FormData();
        formData.append("title", banner.imgTitle);
        formData.append("file", banner.imgFile);

        try {
            const response = await axios({
                method: "post",
                url: `${REST_BANNER_API}/upload`,
                data: formData,
                headers: {
                    "Content-Type": "multipart/form-data",
                },
            });
        }catch (error) {
            console.error("Error uploading banner:", error);
        }
    };
    const banners = ref([]);
    const getBanner = function () {
        axios({
            method: "get",
            url: `${REST_BANNER_API}/list`,
        }).then((response) => {
            banners.value = response.data;
        });
    };

    const activeBanner = function (bannerName) {
        axios({
            method: "put",
            url: `${REST_BANNER_API}/active/${bannerName}`,
        }).then(() => {
            alert("활성화 되었습니다.");
            location.reload();
        });
    }
    const removeBanner = function (bannerName) {
        axios({
            method: "delete",
            url: `${REST_BANNER_API}/remove/${bannerName}`,
        }).then(() => {
            location.reload();
        });
    }

    const banner = ref({});
    const getActiveBanner = function () {
        axios({
            method : "get",
            url: `${REST_BANNER_API}/getBanner`,
        }).then((response) => {
            banner.value = response.data
        })
    }



    return {
        userLogin,
        userList,
        getUserList,
        userJoin,
        logout,
        user,
        getUserDetail,
        userDetailChange,
        userWithdraw,
        loginCheck,
        adminCheck,
        regBanner,
        getBanner,
        banners,
        removeBanner,
        activeBanner,
        banner,
        getActiveBanner,
    };
});
