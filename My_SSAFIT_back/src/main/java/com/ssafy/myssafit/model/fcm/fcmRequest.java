package com.ssafy.myssafit.model.fcm;

public class fcmRequest {
	private String title; // job으로 값이 들어옴
	private Boolean timeTwo;
	private Boolean timeFour;
	private String topic;
	private String token;
	private String fcmUserId;

	public fcmRequest() {
		super();
	}
	
	public fcmRequest(String title, Boolean timeTwo, Boolean timeFour, String topic, String token, String fcmUserId) {
		super();
		this.title = title;
		this.timeTwo = timeTwo;
		this.timeFour = timeFour;
		this.topic = topic;
		this.token = token;
		this.fcmUserId = fcmUserId;
	}

	public String getFcmUserId() {
		return fcmUserId;
	}

	public void setFcmUserId(String fcmUserId) {
		this.fcmUserId = fcmUserId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Boolean getTimeTwo() {
		return timeTwo;
	}

	public void setTimeTwo(Boolean timeTwo) {
		this.timeTwo = timeTwo;
	}

	public Boolean getTimeFour() {
		return timeFour;
	}

	public void setTimeFour(Boolean timeFour) {
		this.timeFour = timeFour;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return "fcmRequest [title=" + title + ", timeTwo=" + timeTwo + ", timeFour=" + timeFour
				+ ", topic=" + topic + ", token=" + token + ", fcmUserId=" + fcmUserId + "]";
	}

}