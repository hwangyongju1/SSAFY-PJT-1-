package com.ssafy.myssafit.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.myssafit.model.dto.User;
import com.ssafy.myssafit.model.dto.fcm.FcmDto;
import com.ssafy.myssafit.model.service.fcm.FcmService;
import com.ssafy.myssafit.model.service.user.UserSevice;
import com.ssafy.myssafit.util.JwtUtil;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {
	// 응답을 편하게 하기 위해 상수로 지정
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";
	
	private final UserSevice userService;
	private final FcmService fcmService;
	
	@Autowired
	public UserController(UserSevice userService, FcmService fcmService) {
		this.userService = userService;
		this.fcmService = fcmService;
	}
	
    @Autowired
    private JwtUtil jwtUtil;
	
	// 기본 해시 함수인 SHA, MD5등을 이용하는 것은 빠른 처리속도로 인하여 보안에 취약하기 때문에 충분히 사용자의 password를 보호할 수 없다.
	// Spring Security에서 제공하는 BCrypt 해싱 함수
//	@Autowired
//	BCryptPasswordEncoder passwordEncoder;
	
	// 관리자 페이지에서 전체 회원 리스트 불러오기
	@GetMapping("/list")
	public ResponseEntity<?> getUserList() {
		List<User> userList = userService.getUserList();
		return new ResponseEntity<List<User>>(userList, HttpStatus.OK);
	}
	
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody User user){
        System.out.println(user.toString());
        HttpStatus status = null;
        Map<String, Object> result = new HashMap<>();
        
        if(user.getUserId() != null) {
            // 토큰 만들어서 반환
            result.put("message", SUCCESS);
            result.put("access-token", jwtUtil.createAccessToken(user.getUserId()));
            result.put("refresh-token", jwtUtil.createRefreshToken(user.getUserId()));
            result.put("ID", user.getUserId());
            
            // login 시, fcm db에 저장(구독)되어 있는 정보를 반환
            FcmDto fcm = fcmService.selectByFcmUserID(user.getUserId());
            result.put("fcmInfo", fcm);
            status = HttpStatus.ACCEPTED;
        } else {
            result.put("message", FAIL);
            status = HttpStatus.NO_CONTENT;
        }
        System.out.println(result.toString());
        return new ResponseEntity<>(result, status);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<Map<String, Object>> refreshAccessToken(@RequestBody Map<String, String> request) {
        HttpStatus status = null;
        Map<String, Object> result = new HashMap<>();

        String refreshToken = request.get("refresh_token");

        if (refreshToken != null) {
            // refresh token이 유효한지 확인
            try {
            	jwtUtil.validateToken(refreshToken);

                // refresh token이 유효하면 access token을 새로 발급
                String userId = jwtUtil.extractUserId(refreshToken);
                String accessToken = jwtUtil.createAccessToken(userId);

                // 새로 발급한 access token을 클라이언트에게 응답
                result.put("message", SUCCESS);
                result.put("access-token", accessToken);
                status = HttpStatus.ACCEPTED;
            } catch (Exception e) {
                // refresh token이 유효하지 않은 경우
                result.put("message", FAIL);
                status = HttpStatus.UNAUTHORIZED;
            }
        } else {
            // refresh token이 전송되지 않은 경우
            result.put("message", FAIL);
            status = HttpStatus.BAD_REQUEST;
        }

        return new ResponseEntity<>(result, status);
    }
	
	// 회원가입
	@PostMapping("/join")
	public ResponseEntity<?> userJoin(@RequestBody User user) {
		// Bcrypt 해싱함수로 인코딩
//		user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
		userService.signUp(user);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	// 아이디 중복 검사
	@GetMapping("/{userId}")
	public ResponseEntity<?> userIdChk(@PathVariable("userId") String userId) {
		User user = userService.getUserDetail(userId);
		if (user == null) {
			// ok로 해줘야 오류가 발생하지 않음
			// session으로 회원 정보 넘기기
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		}
		else {
			// ok로 해줘야 오류가 발생하지 않음
			return new ResponseEntity<Boolean>(false, HttpStatus.OK);
		}
	}
	
	// 아이디를 통한 정보 검색
	@GetMapping("/user/{userId}")
	public ResponseEntity<?> getUserDetail(@PathVariable("userId") String userId) {
		User user = userService.getUserDetail(userId);
		user.setUserPassword(null);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	// 개인정보 수정
	@PutMapping("/user")
	public ResponseEntity<?> updateUserDetail(@RequestBody User user) {
		userService.modifyUserDetail(user);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	// 회원 탈퇴
	@DeleteMapping("/user/{userId}")
	public ResponseEntity<?> deleteUserDetail(@PathVariable("userId") String userId) {
		userService.removeUserDetail(userId);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
