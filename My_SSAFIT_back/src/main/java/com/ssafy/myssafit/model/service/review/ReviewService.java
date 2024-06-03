package com.ssafy.myssafit.model.service.review;

import java.util.List;

import com.ssafy.myssafit.model.dto.Review;

public interface ReviewService {
	
	// 유저정보를 통해 작성한 리뷰 가져오기
	// 사용처 : (개인정보에서 작성한 리뷰 확인)
	public List<Review> getReviewByUserId(String reviewWriter);
	
	// 리뷰 고유번호를 통한 리뷰 가져오기
	// 사용처 : (리뷰 수정을 위해서)
	public Review getReviewDetailByReviewId(String reviewIdx);
	
	// 리뷰 등록
	// 사용처 : (영상 상세페이지 리뷰 등록)
	public void createReview(Review review);
	
	// 리뷰 고유 번호를 통한 선택한 리뷰 수정
	// 사용처 : (리뷰 페이지, 개인페이지에서 리뷰 수정)
	public void modifyReviewByReviewId(Review review);
	
	// 리뷰 고유 번호를 통한 리뷰 삭제
	// 사용처 : (리뷰 페이지, 개인페이지에서 리뷰 삭제)
	public void removeReviewByReviewId(String reviewIdx);
	
	// 비디오 아이디를 통한 리뷰 리스트 조회
	// 사용처 : (영상 리뷰)
	public List<Review> getReviewByVideoId(String reviewVideoId);
}
