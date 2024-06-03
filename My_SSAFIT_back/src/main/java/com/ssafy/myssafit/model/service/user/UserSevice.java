package com.ssafy.myssafit.model.service.user;

import java.util.List;

import com.ssafy.myssafit.model.dto.Review;
import com.ssafy.myssafit.model.dto.User;

public interface UserSevice {
	// 전체 사용자 목록 불러오기
	// 사용처 : (관리자 페이지 유저 관리 페이지)
	public List<User> getUserList();
	
	// 유저 정보 하나 가져오기 
	// 사용처 : (개인정보 수정, 관리자페이지 상세정보)
	public User getUserDetail(String userId);
	
	// 회원가임
	// 사용처 : (회원가입)
	public void signUp(User user);
	
	// 유저 정보 수정
	// 사용처 : (개인정보에서 유저정보 수정)
	public void modifyUserDetail(User user);
	
	// 유저 정보 삭제
	// 사용처 : (회원 탈퇴)
	public void removeUserDetail(String userId);
	
	// 로그인
	// 사용처 : (로그인)
	public User login(String userId, String userPassword);
}
