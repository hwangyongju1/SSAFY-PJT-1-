package com.ssafy.myssafit.model.service.fcm;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.ssafy.myssafit.model.dao.fcm.FcmDao;
import com.ssafy.myssafit.model.dto.fcm.FcmDto;

@Service
public class FcmServiceImpl implements FcmService {

	/**
	 * @author HwangYJ 
	 * spring에서는 메세지의 전송만을 하기 때문에, FirebaseMessaging을 통한 메시지 전송 기능만 구현
	 * 
	 * 구현의 간결함을 위해 직업과 시간별로 함수를 구현 -> 추후 fcm을 이용한다면, 효율적인 방안 모색
	 * 
	 * 스케쥴링을 이용하여 정해진 시간에 함수를 실행
	 * @implNote
	 * firebase의 message를 사용하여 message의 title, body 구성
	 * 또한, selectList함수로 가져온 list를 이용해서 token이 있는 사람들에게 메시지 전송
	 */

	private final FirebaseMessaging firebaseMessaging;
	private final FcmDao fcmDao;
	@Autowired
	public FcmServiceImpl(FirebaseMessaging firebaseMessaging, FcmDao fcmDao) {
		this.firebaseMessaging = firebaseMessaging;
		this.fcmDao = fcmDao;
	}

	public void sendWebPushNotification(String token, String title, String body) throws IOException {
		Message message = Message.builder().putData("title", title).putData("body", body).setToken(token).build();

		System.out.println("make exit");
		System.out.println(message);
		RestTemplate restTemplate = new RestTemplate();

		try {
			firebaseMessaging.getInstance().sendAsync(message);
//			System.out.println("Successfully sent message to token: " + token);
		} catch (Exception e) {
//			System.out.println("Error sending message to token: " + token);
			e.printStackTrace();
		}
	}


	@Override
	public List<FcmDto> selectDevelopFcmList2(String job, Boolean subscribe) {
		return fcmDao.selectDevelopList2(job, subscribe);
	}

	@Override
	public List<FcmDto> selectDevelopFcmList4(String job, Boolean subscribe) {
		return fcmDao.selectDevelopList4(job, subscribe);
	}

	@Override
	public List<FcmDto> selectStudentFcmList2(String job, Boolean subscribe) {
		return fcmDao.selectStudentList2(job, subscribe);
	}

	@Override
	public List<FcmDto> selectStudentFcmList4(String job, Boolean subscribe) {
		return fcmDao.selectStudentList4(job, subscribe);
	}

	@Override
	public void insertToken(FcmDto fcm) {
		// token 발급이 처음인 경우
		FcmDto findFcm = fcmDao.selectByFcmUserId(fcm.getFcmUserId());
		fcm.setTokenExpirationTime(LocalDateTime.now());
		if(findFcm == null)
			fcmDao.insertToken(fcm);
		
		// token 발급을 받았지만, token의 값이 변한 경우
		else if(findFcm.getFcmUserId().equals(fcm.getFcmUserId())){
			fcmDao.updateToken(fcm);
		}
	}
	
	@Override
	public FcmDto selectByFcmUserID(String fcmUserId) {
		return fcmDao.selectByFcmUserId(fcmUserId);
	}
	
	@Override
	public void updateSubscribe(FcmDto fcm) {
		fcmDao.updateSubscribe(fcm);
	}
	
	@Override
	public void deleteSubscribe(String fcmUserId) {
		fcmDao.deleteSubscribe(fcmUserId);
	}
}
