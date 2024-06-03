package com.ssafy.myssafit.model.service.fcm.scheduled;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.ssafy.myssafit.model.dao.fcm.FcmDao;
import com.ssafy.myssafit.model.dto.fcm.FcmDto;
import com.ssafy.myssafit.model.fcm.fcmMessage;
import com.ssafy.myssafit.model.service.fcm.FcmServiceImpl;

@Service
public class schedule {
	
	private final FcmServiceImpl fcmService;
	private final FcmDao fcmDao;
	
	@Autowired
	public schedule(FcmServiceImpl fcmService, FcmDao fcmDao) {
		this.fcmService = fcmService;
		this.fcmDao = fcmDao;
	}
	
	// 매 시간 list의 token 유효시간을 계산해서, db 관리
	@Scheduled(cron = "59 * * * * ?")
	public void tokenExpirationManage() {
		List<FcmDto> list = fcmDao.selectList();
		LocalDateTime now = LocalDateTime.now();
		System.out.println("list search");
		for(FcmDto fcm : list) {
			System.out.println(fcm.toString());
			if(fcm.getTokenExpirationTime() == null) // -> temporal error 방지
				continue;
			long diffHour = ChronoUnit.HOURS.between(now, fcm.getTokenExpirationTime());
			if(diffHour < 0) {
				// db에 저장된 token만 삭제, user 정보는 유지
				// diffHour가 음수여야 이미 지난 시간
				fcmDao.deletFcmToken(fcm);
			}
		}
	}

	// 메세지 전송 시간 구간 -> scheduled로 자동화
	// 개발자 - 2시

	@Scheduled(cron = "00 45 04 * * ?")
	public void pushDevelopTwoAlarm() throws IOException {
		List<FcmDto> developList = fcmService.selectDevelopFcmList2("개발자", true);

		for (FcmDto fcmDto : developList) {
			System.out.println(fcmDto.toString());
			fcmService.sendWebPushNotification(fcmDto.getFcmtoken(), fcmMessage.DEVELOPER_TWO.getTitle(),
					fcmMessage.DEVELOPER_TWO.getBody());
		}
	}

	@Scheduled(cron = "40 04 03 * * ?")
	public void pushDevelopFourAlarm() throws IOException {
		System.out.println("4시 개발자");
		List<FcmDto> developList = fcmService.selectDevelopFcmList4("개발자", true);

		for (FcmDto fcmDto : developList) {
			System.out.println("4");
			System.out.println(fcmDto.toString());
			fcmService.sendWebPushNotification(fcmDto.getFcmtoken(), fcmMessage.DEVELOPER_FOUR.getTitle(),
					fcmMessage.DEVELOPER_FOUR.getBody());
		}
	}

	@Scheduled(cron = "10 41 02 * * ?")
	public void pushStudentTwoAlarm() throws IOException {
		List<FcmDto> studentList = fcmService.selectStudentFcmList2("학생", true);

		for (FcmDto fcmDto : studentList) {
			fcmService.sendWebPushNotification(fcmDto.getFcmtoken(), fcmMessage.STUDENT_TWO.getTitle(),
					fcmMessage.STUDENT_TWO.getBody());
		}
	}

	@Scheduled(cron = "50 34 02 * * ?")
	public void pushStudentFourAlarm() throws IOException {
		List<FcmDto> studentList = fcmService.selectStudentFcmList4("학생", true);

		for (FcmDto fcmDto : studentList) {
			fcmService.sendWebPushNotification(fcmDto.getFcmtoken(), fcmMessage.STUDENT_FOUR.getTitle(),
					fcmMessage.STUDENT_FOUR.getBody());
		}
	}
}
