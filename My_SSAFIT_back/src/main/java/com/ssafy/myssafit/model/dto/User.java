package com.ssafy.myssafit.model.dto;

public class User {
	private int userIdx;
	private String userId;
	private String userPassword;
	private String userName;
	private String userEmail;
	private String userJob;

	public User() {
	}

	public User(int userIdx, String userId, String userPassword, String userName, String userNicName,
			String userEmail, String userJob) {
		super();
		this.userIdx = userIdx;
		this.userId = userId;
		this.userPassword = userPassword;
		this.userName = userName;
		this.userEmail = userEmail;
		this.userJob = userJob;
	}

	public int getUserIdx() {
		return userIdx;
	}

	public void setUserIdx(int userIdx) {
		this.userIdx = userIdx;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserJob() {
		return userJob;
	}

	public void setUserJob(String userJob) {
		this.userJob = userJob;
	}

	@Override
	public String toString() {
		return "User [userIdx=" + userIdx + ", userId=" + userId + ", userPassword=" + userPassword + ", userName="
				+ userName + ", userEmail=" + userEmail + ", userJob=" + userJob + "]";
	}

}
