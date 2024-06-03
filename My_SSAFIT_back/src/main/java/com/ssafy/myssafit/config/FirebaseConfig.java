package com.ssafy.myssafit.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.IOException;
import java.io.InputStream;

@Configuration
public class FirebaseConfig {

    private final ResourceLoader resourceLoader;

    public FirebaseConfig(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    
    // firebase key의 위치를 가져와서 serviceAccount에 저장
    // 이를 통해, spring에서 firebase admin sdk를 초기화
    // firebaseApp이 empty가 아닌데 초기화를 진행할 경우 에러가 발생하기 때문에, if문으로 상태 확인
    
    /** 
     * @return
     * @throws IOException
     * @author HwangYJ
     * 
     * @param serviceAccount -> firebase admin json 
     * 	이를 통해, Firebase option에 GoogleCredentials를 저장 -> google api authentication token
     * .gitignore
     * 
     * 	구현 장소
     *  vue vs spring
     *  - vue 장점
     *  	- component에서 직접 Firebase SDK를 통해 token 발급으로 코드가 쉽다
     *  	- client에서 발급되기에 서버에서 관리할 필요가 없음
     *  - vue 단점
     *  	- 보안 취약, client에서 발급되기 때문에 탈취할 수 있음
     *  	- token 관리가 어렵다 ex) 만료 시간 혹은 저장
     *  
     *  - spring 장점
     *  	- 보안 강화 및 토근 관리 용이
     *  	- 구현이 어렵고 복잡하다 -> 개발자의 역량이 중요
     *  	- server에서 api를 통해 firebase에 연결
     *  		- GoogleCredentials를 이용해서 "<https://www.googleapis.com/auth/cloud-platform>"와 통신
     */
    @Bean
    public FirebaseMessaging firebaseMessaging() throws IOException {
        Resource resource = new ClassPathResource("webpushpractice-f2879-firebase-adminsdk-2qlv8-b86684d23c.json");
        InputStream serviceAccount = resource.getInputStream();
        System.out.println(resource);
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();

        if (FirebaseApp.getApps().isEmpty()) {
            FirebaseApp.initializeApp(options);
        }

        return FirebaseMessaging.getInstance();
    }
}

