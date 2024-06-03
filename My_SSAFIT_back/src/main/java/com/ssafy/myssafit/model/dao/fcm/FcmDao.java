package com.ssafy.myssafit.model.dao.fcm;

import java.util.List;

import com.ssafy.myssafit.model.dto.fcm.FcmDto;

public interface FcmDao {

	public List<FcmDto> selectDevelopList2(String fcmjob, Boolean subscribe);
	
	public List<FcmDto> selectDevelopList4(String fcmjob, Boolean subscribe);

	public List<FcmDto> selectStudentList2(String fcmjob, Boolean subscribe);

	public List<FcmDto> selectStudentList4(String fcmjob, Boolean subscribe);

	public void insertToken(FcmDto fcm);

	public FcmDto selectByFcmUserId(String fcmUserId);

	public void updateToken(FcmDto fcm);

	public List<FcmDto> selectList();

	public void deletFcmToken(FcmDto fcm);

	public void updateSubscribe(FcmDto fcm);
	
	public void deleteSubscribe(String fcmUserId);
}
