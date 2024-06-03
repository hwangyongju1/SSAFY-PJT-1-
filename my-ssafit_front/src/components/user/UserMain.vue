<template>
    <div class="head">내 계정</div>
    <div class="btn-box">
        <div v-if="fcmStore.subscribeInfo.timeTwo || fcmStore.subscribeInfo.timeFour" class="sub-btn-box">
            <!-- 둘 중 하나라도 구독 중이면 취소 버튼 보임 -->
            <button @click="unsubscribe">알람 구독 취소</button>
        </div>
        <div v-if="!fcmStore.subscribeInfo.timeTwo" class="sub-btn-box">
            <button @click="subscribeTimeTwo">2시 알람 구독</button>
        </div>
        <div v-if="!fcmStore.subscribeInfo.timeFour" class="sub-btn-box">
            <button @click="subscribeTimeFour">4시 알람 구독</button>
        </div>
        <div v-if="!fcmStore.subscribeInfo.timeTwo || !fcmStore.subscribeInfo.timeFour" class="sub-btn-box">
            <!-- 둘 중 하나라도 구독이 아니면 모두 구독 보임 -->
            <button @click="subscribeAll">2시, 4시 알람 구독</button>
        </div>
    </div>
    <div class="pic">
        <div class="intro">
            <div class="info1">안녕하세요, {{ sessionId }} 회원님!</div>
            <div class="info2">환영합니다.</div>
        </div>
    </div>
    <div>
    </div>
    
</template>

<script setup>
import { watchEffect, ref, onMounted } from 'vue';
// user -> fcmInfo
import { useFcmStore } from '@/stores/fcm';
import axios from 'axios';
import { useRouter } from 'vue-router';

const fcmStore = useFcmStore();
const sessionId = sessionStorage.getItem("userId");
const router = useRouter();
const fcmInfo = ref({
    fcmUserId: '',
    fcmtoken: "",
    fcmjob: '',
    fcmtime2: '',
    fcmtime4: "",
    tokenExpirationTime: ''
});
const token = ref();
const updateSubscribe = function () {
    fcmInfo.value.fcmUserId = sessionId;
    axios.put(`http://localhost:8080/fcmInfo`, fcmInfo.value)
        .then((response) => {
            console.log(response);
            console.log("update success")
            // window.location.reload(true);
            router.go();
        })
        .catch((error) => {
            console.log('update subscribe info err: ', error);
        })
};

const unsubscribe = async () => {
    axios.delete(`http://localhost:8080/fcmDelete/${sessionId}`)
    .then((response) => {
        console.log("response.data");
        router.go();
    })
    .catch((err) => {
        console.log("delete error : ", err);
    })
};

const subscribeTimeTwo = async () => {
    const tokens = await accessToken();
    console.log("click token : ", tokens[0]);
    fcmInfo.value.fcmtoken = tokens[0];
    fcmInfo.value.fcmUserId = sessionId;
    fcmInfo.value.fcmtime2 = true;
    fcmInfo.value.fcmtime4 = false;
    updateSubscribe();
};

const subscribeTimeFour = async () => {
    const tokens = await accessToken();
    fcmInfo.value.fcmtoken = tokens[0];
    fcmInfo.value.fcmUserId = sessionId;
    fcmInfo.value.fcmtime2 = false;
    fcmInfo.value.fcmtime4 = true;
    updateSubscribe();
};

const subscribeAll = async () => {
    const tokens = await accessToken();
    fcmInfo.value.fcmtoken = tokens[0];
    fcmInfo.value.fcmUserId = sessionId;
    fcmInfo.value.fcmtime2 = true;
    fcmInfo.value.fcmtime4 = true;
    updateSubscribe();
};

watchEffect(() => {
    fcmStore.getSubscribeInfo(sessionId);
});

const accessToken = () => {
    return new Promise((resolve, reject) => {
        const request = window.indexedDB.open("firebase-messaging-database");

        request.onsuccess = function (event) {
            const db = event.target.result;
            console.log("Database opened successfully");

            const transaction = db.transaction(["firebase-messaging-store"], "readonly");
            const objectStore = transaction.objectStore("firebase-messaging-store");
            const allRecords = objectStore.getAll();

            allRecords.onsuccess = function () {
                const records = allRecords.result;
                const tokens = records.map(record => record.token);
                console.log("Tokens:", tokens);
                resolve(tokens);
            };

            allRecords.onerror = function () {
                console.error("Error fetching records:", allRecords.error);
                reject(allRecords.error);
            };
        };

        request.onerror = function (event) {
            console.error("Database open failed", event);
            reject(event);
        };
    });
};


</script>

<style scoped>
@import "../../assets/css/myPage.css";
@import "../../assets/css/reset.css";
</style>