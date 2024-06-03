package com.ssafy.myssafit.model.service.fcm;

import java.io.IOException;
import java.util.List;

import com.ssafy.myssafit.model.dto.fcm.FcmDto;

public interface FcmService {

	
	/** 
	 * 
	 * @param job : 각 구독자의 직업
	 * @param time : 서비스 신청 시간
	 * @return
	 * 
	 * 구현의 간경성을 위해 초기 설정한 시간(2, 4)과 직업(developer, student)의
	 * 각각에 해당하는 list를 출력하기 위해 함수를 여러개 선언
	 */
	public List<FcmDto> selectDevelopFcmList2(String job, Boolean subscribe);
	
	public List<FcmDto> selectDevelopFcmList4(String job, Boolean subscribe);
	
    public List<FcmDto> selectStudentFcmList2(String job, Boolean subscribe);
    
    public List<FcmDto> selectStudentFcmList4(String job, Boolean subscribe);
    
    public void insertToken(FcmDto fcm);
    
    public FcmDto selectByFcmUserID(String fcmUserId);
    
    public void updateSubscribe(FcmDto fcm);
    
    public void deleteSubscribe(String fcmUserId);
}
