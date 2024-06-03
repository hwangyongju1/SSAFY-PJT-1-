package com.ssafy.myssafit.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.myssafit.model.dto.User;
import com.ssafy.myssafit.model.dto.fcm.FcmDto;
import com.ssafy.myssafit.model.fcm.fcmRequest;
import com.ssafy.myssafit.model.fcm.fcmResponse;
import com.ssafy.myssafit.model.service.fcm.FcmServiceImpl;
import com.ssafy.myssafit.model.service.user.UserServiceImpl;

@RestController
@CrossOrigin(origins = "http://localhost:8081/") // 컨트롤러에서 설정
public class PushNotificationController {

	/** 
	 * @author : HwangYJ
	 * 
	 * FCM firebase을 이용하여 web push를 구동하는 공간
	 * client에서 firebase sdk를 이용해 발급받은 token을 DB에 저장하여 관리
	 */
	
	// fcm 관련 서비스 기능
	private final FcmServiceImpl firebaseService;
	private final UserServiceImpl userService;
	@Autowired
	public PushNotificationController(FcmServiceImpl firebaseService, UserServiceImpl userService) {
		this.firebaseService = firebaseService;
		this.userService = userService;
	}

	@PostMapping("/sendNotification")
	public ResponseEntity<fcmResponse> sendWebPushNotification(
			@RequestBody fcmRequest request) {
		System.out.println("Received push notification: " + request.toString());
		// input PushNotification을 FcmDto로 변형
		FcmDto fcmDto = new FcmDto();
		fcmDto.setFcmtoken(request.getToken());
		fcmDto.setFcmjob(request.getTitle());
		fcmDto.setFcmtime2(request.getTimeTwo());
		fcmDto.setFcmtime4(request.getTimeFour());
		fcmDto.setFcmUserId(request.getFcmUserId());
		fcmDto.setTokenExpirationTime(LocalDateTime.now().plusHours(1));
		System.out.println("fcm controller");
		System.out.println(fcmDto.toString());
		firebaseService.insertToken(fcmDto);
		fcmResponse res = new fcmResponse();
//		System.out.println(res);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	@GetMapping("/{fcmUserId}")
	public ResponseEntity<?> getFcmUser(@PathVariable("fcmUserId") String fcmUserId){
		System.out.println("fcmUserId -> subscribe info" + " userID : " + fcmUserId);
		FcmDto fcm = firebaseService.selectByFcmUserID(fcmUserId);
		if(fcm != null) {
			System.out.println(fcm.toString());
			return new ResponseEntity<FcmDto>(fcm, HttpStatus.OK);
		}
		else
			return new ResponseEntity<String>("no Subscribe info", HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("/fcmInfo")
	public ResponseEntity<?> updateSubscribeInfo(@RequestBody FcmDto fcm){
		System.out.println(fcm.toString());
		if(firebaseService.selectByFcmUserID(fcm.getFcmUserId()) == null) {
			System.out.println("put 빈 유저 정보");
//			System.out.println(userService.getUserList().toString());
			System.out.println(fcm.getFcmUserId());
			User user = userService.getUserDetail(fcm.getFcmUserId());
			System.out.println(user.toString());
			fcm.setFcmjob(user.getUserJob());
			System.out.println("set job");
			System.out.println(fcm.toString());
			firebaseService.insertToken(fcm);
			return new ResponseEntity<String>("구독 정보가 없어 첫 구독으로 등록", HttpStatus.OK); 
		}
		firebaseService.updateSubscribe(fcm);
		// 변경 확인을 위해 객체로 반환
		FcmDto updateFcm = firebaseService.selectByFcmUserID(fcm.getFcmUserId());
		System.out.println("유저 정보 업데이트");
		System.out.println(updateFcm.toString());
		return new ResponseEntity<FcmDto>(updateFcm, HttpStatus.OK);
	}
	
	@DeleteMapping("/fcmDelete/{fcmUserId}")
	public ResponseEntity<?> deleteSubscribe(@PathVariable("fcmUserId") String fcmUserId){
		System.out.println(fcmUserId);
		firebaseService.deleteSubscribe(fcmUserId);
		return new ResponseEntity<String>("Cancle Subscribe", HttpStatus.OK);
	}
}