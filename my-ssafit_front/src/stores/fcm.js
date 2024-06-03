import { ref, onMounted } from "vue";
import { defineStore } from "pinia";
import axios from "axios";
import firebase from 'firebase/compat/app';
import 'firebase/compat/messaging';

const REST_USER_API = `http://localhost:8080/user`;
const REST_FCM_API = `http://localhost:8080`;
const firebaseConfig = {
  apiKey: "AIzaSyBnOvhRALSRYXkiBG5q7xIRSFoOmlvOQlQ",
  authDomain: "webpushpractice-f2879.firebaseapp.com",
  databaseURL: "https://webpushpractice-f2879-default-rtdb.asia-southeast1.firebasedatabase.app",
  projectId: "webpushpractice-f2879",
  storageBucket: "webpushpractice-f2879.appspot.com",
  messagingSenderId: "8920987990",
  appId: "1:8920987990:web:fbbcaef1b333d6f0f83ef7",
  measurementId: "G-K00KDY9Z7Z"
};
const notificationPermissionStatus = ref('');


export const useFcmStore = defineStore('fcm', () => {
  firebase.initializeApp(firebaseConfig);
  const messaging = firebase.messaging();
  const firebaseKey = import.meta.env.VITE_FIREBASE_VAPID;

  const notificationPermission = (fcmInfo) => {
  const fcmDto = ref({
    token: '',
    title: "", // job
    timeTwo: '', // boolean
    timeFour: '', // boolean
    fcmUserId: "",
  });
  
  fcmDto.value.title = fcmInfo.fcmjob;
  fcmDto.value.timeTwo = fcmInfo.fcmtime2;
  fcmDto.value.timeFour = fcmInfo.fcmtime4;
  fcmDto.value.fcmUserId = fcmInfo.fcmUserId;
  console.log("init: ", fcmDto.value);

    Notification.requestPermission().then(permission => {
      if (permission === 'granted') {
        notificationPermissionStatus.value = 'Notification permission granted';
        console.log("notification permission granted");
        getToken(fcmDto);
      } else {
        notificationPermissionStatus.value = 'Notification permission denied';
      }
    });
  };

  const getToken = async (fcmDto) => {
    try {
      const token = await messaging.getToken();
      console.log("messaging : ", messaging);
      console.log("Token : ", token);
      console.log("getToken : ", fcmDto.value);
      sendFcmToken(token, fcmDto);
    } catch (error) {
      console.error('Error getting token', error);
    }
  };



  const sendFcmToken = async (token, fcmDto) => {
    console.log("sendFcmToken : ", fcmDto.value);
    fcmDto.value.token = token;
    console.log("final fcm : ", fcmDto.value);
    axios.post(`${REST_FCM_API}/sendNotification`, fcmDto.value

    )
      .then((response) => {
        console.log(response);
        console.log('send token success');
      })
      .catch((error) => {
        console.log('send token error : ', error);
      });
  };

  const subscribeInfo = ref({
    timeTwo: '',
    timeFour: ''
  })
  const getSubscribeInfo = function(fcmUserId) {
    axios.get(`${REST_FCM_API}/${fcmUserId}`)
    .then((response) => {
      console.log(response.data);
      console.log(subscribeInfo.value);
      subscribeInfo.value.timeTwo = response.data.fcmtime2;
      subscribeInfo.value.timeFour = response.data.fcmtime4;
      console.log(subscribeInfo.value);
    })
  }

  onMounted(() => {
    console.log('user on mounted');
    if ('serviceWorker' in navigator) {
      navigator.serviceWorker.register('/public/firebase-messaging-sw.js')
        .then(async (registration) => {
          console.log('ServiceWorker registration -> scope : ', registration.scope);

          const subscription = await registration.pushManager.subscribe({
            userVisibleOnly: true,
            applicationServerKey: firebaseKey
          });
          console.log('subscribe : ', subscription);
        })
        .catch((error) => {
          console.log('Service Worker regist fail : ', error);
        });
    }

    
  });
  messaging.onMessage(payload => {
    console.log('Received push notification (payload): ', payload);
    new Notification(payload.data.title, {
      body: payload.data.body,
      icon: '/@/icon/notificationLogo.png'
    });
  });
  
  return {
    notificationPermission,
    notificationPermissionStatus,
    subscribeInfo,
    getSubscribeInfo
  };
});
