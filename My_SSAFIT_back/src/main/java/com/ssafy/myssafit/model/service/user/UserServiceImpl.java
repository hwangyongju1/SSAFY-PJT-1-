package com.ssafy.myssafit.model.service.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.myssafit.model.dao.user.UserDao;
import com.ssafy.myssafit.model.dto.User;

@Service
public class UserServiceImpl implements UserSevice {
	
	private final UserDao userDao;
	
	@Autowired
	public UserServiceImpl(UserDao userDao) {
		this.userDao = userDao;
	}

//	전체 사용자 목록 불러오기 
	@Override
	public List<User> getUserList() {
		return userDao.selectAll();
	}
	
//	유저 아이디를 통해 유저 정보 가져오기
	@Override
	public User getUserDetail(String userId) {
		return userDao.selectUserById(userId);
	}
	
//	회원가입
	@Override
	public void signUp(User user) {
		userDao.signUp(user);
	}
	
//	유저 아이디를 통한 유저 정보 수정
	@Override
	public void modifyUserDetail(User user) {
		userDao.updateUserDetail(user);
	}

//	유저 정보 삭제(회원 탈퇴)
	@Override
	public void removeUserDetail(String userId) {
		userDao.deleteUser(userId);
	}

//	로그인
	@Override
	public User login(String userId, String userPassword) {
		return userDao.login(userId, userPassword);
	}

}
