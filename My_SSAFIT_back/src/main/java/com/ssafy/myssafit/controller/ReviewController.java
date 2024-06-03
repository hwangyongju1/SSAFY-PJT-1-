package com.ssafy.myssafit.controller;

import java.util.List;

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

import com.ssafy.myssafit.model.dto.Review;
import com.ssafy.myssafit.model.service.review.ReviewService;

@RestController
@RequestMapping("/review")
@CrossOrigin("*")
public class ReviewController {
	
	private final ReviewService reviewService;
	
	@Autowired
	public ReviewController(ReviewService reviewService) {
		this.reviewService = reviewService; 
	}
	
	// 유저정보를 통해 작성한 리뷰 가져오기
	// 사용처 : (개인정보에서 작성한 리뷰 확인)
	@GetMapping("/user/{userId}")
	public ResponseEntity<?> getReviewByUserId(@PathVariable("userId") String userId) {
		List<Review> list = reviewService.getReviewByUserId(userId);
		return new ResponseEntity<List<Review>>(list, HttpStatus.OK);
	}
	
	// 리뷰 고유번호를 통한 리뷰 가져오기
	// 사용처 : (리뷰 수정을 위해서)
	@GetMapping("/{reviewIdx}")
	public ResponseEntity<?> getReviewByReviewId(@PathVariable("reviewIdx") String reviewIdx) {
		Review review = reviewService.getReviewDetailByReviewId(reviewIdx);
		return new ResponseEntity<Review>(review, HttpStatus.OK);
	}
	
	// 리뷰 등록
	// 사용처 : (영상 상세페이지 리뷰 등록)
	@PostMapping("/create")
	public ResponseEntity<?> insertReview(@RequestBody Review review) {
		reviewService.createReview(review);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	// 리뷰 고유 번호를 통한 선택한 리뷰 수정
	// 사용처 : (리뷰 페이지, 개인페이지에서 리뷰 수정)
	@PutMapping("/update")
	public ResponseEntity<?> modifyReview(@RequestBody Review review) {
		System.out.println(review);
		reviewService.modifyReviewByReviewId(review);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	// 리뷰 고유 번호를 통한 리뷰 삭제
	// 사용처 : (리뷰 페이지, 개인페이지에서 리뷰 삭제)
	@DeleteMapping("/{reviewIdx}")
	public ResponseEntity<?> removeReview(@PathVariable("reviewIdx") String reviewIdx) {
		reviewService.removeReviewByReviewId(reviewIdx);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	// 비디오 아이디를 통한 리뷰 리스트 조회
	// 사용처 : (영상 리뷰)
	@GetMapping("/video/{reviewVideoId}")
	public ResponseEntity<?> getReviewForVideo(@PathVariable("reviewVideoId") String reviewVideoId) {
		List<Review> list = reviewService.getReviewByVideoId(reviewVideoId);
		return new ResponseEntity<List<Review>>(list, HttpStatus.OK);
	}
	
	
	
	
}
