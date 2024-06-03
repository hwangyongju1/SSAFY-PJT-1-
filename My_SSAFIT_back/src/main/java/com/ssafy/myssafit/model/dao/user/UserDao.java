package com.ssafy.myssafit.model.dao.user;

import java.util.List;

import com.ssafy.myssafit.model.dto.User;

public interface UserDao {
	
	// 전체 사용자 목록 불러오기 
	public List<User> selectAll();
	
	// 유저 아이디를 통해 유저 정보 가져오기
	public User selectUserById(String userId);
	
	// 회원가입
	public void signUp(User user);
	
	// 유저 아이디를 통한 유저 정보 수정
	public void updateUserDetail(User user);
	
	// 유저 정보 삭제(회원 탈퇴)
	public void deleteUser(String userId);
	
	// 로그인
	public User login(String userId, String userPassword);
	
}
