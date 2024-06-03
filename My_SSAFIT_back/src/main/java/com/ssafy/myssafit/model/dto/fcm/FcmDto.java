package com.ssafy.myssafit.model.dto.fcm;

import java.time.LocalDateTime;

/** 
 * @author HwangYJ
 * 
 * vue에서 넘어온 token과 사용자 정보를 나타내는 dto
 * job, time : 사용자의 직업에 따라 선택한 시간에 메세지를 보낼 것이기 때문에 저장
 */
public class FcmDto {
	private String fcmUserId;
	private String fcmtoken;
	private String fcmjob;
	private Boolean fcmtime2;
	private Boolean fcmtime4;
	private LocalDateTime tokenExpirationTime; 
		
	public FcmDto() {}
	
	public FcmDto(String fcmUserId, String fcmtoken, String fcmjob, Boolean fcmtime2, Boolean fcmtime4, LocalDateTime tokenexpirationTime) {
		super();
		this.fcmUserId = fcmUserId;
		this.fcmtoken = fcmtoken;
		this.fcmjob = fcmjob;
		this.fcmtime2 = fcmtime2;
		this.fcmtime4 = fcmtime4;
		this.tokenExpirationTime = tokenexpirationTime;
	}
	
	public LocalDateTime getTokenExpirationTime() {
		return tokenExpirationTime;
	}

	public void setTokenExpirationTime(LocalDateTime tokenExpirationTime) {
		this.tokenExpirationTime = tokenExpirationTime;
	}

	public String getFcmUserId() {
		return fcmUserId;
	}

	public void setFcmUserId(String fcmUserId) {
		this.fcmUserId = fcmUserId;
	}

	public String getFcmtoken() {
		return fcmtoken;
	}

	public void setFcmtoken(String fcmtoken) {
		this.fcmtoken = fcmtoken;
	}

	public String getFcmjob() {
		return fcmjob;
	}

	public void setFcmjob(String fcmjob) {
		this.fcmjob = fcmjob;
	}

	public Boolean getFcmtime2() {
		return fcmtime2;
	}

	public void setFcmtime2(Boolean fcmtime2) {
		this.fcmtime2 = fcmtime2;
	}

	public Boolean getFcmtime4() {
		return fcmtime4;
	}

	public void setFcmtime4(Boolean fcmtime4) {
		this.fcmtime4 = fcmtime4;
	}

	@Override
	public String toString() {
		return "FcmDto [fcmUserId=" + fcmUserId + ", fcmtoken=" + fcmtoken + ", fcmjob=" + fcmjob + ", fcmtime2="
				+ fcmtime2 + ", fcmtime4=" + fcmtime4 + ", tokenExpirationTime="
				+ tokenExpirationTime + "]";
	}

	
}
