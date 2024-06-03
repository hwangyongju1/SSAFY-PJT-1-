package com.ssafy.myssafit.model.service.review;

import java.util.List;

import org.apache.ibatis.javassist.expr.Instanceof;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.myssafit.model.dao.review.ReviewDao;
import com.ssafy.myssafit.model.dto.Review;

@Service
public class ReviewServiceImpl implements ReviewService {
	
	private final ReviewDao reviewDao;
	
	@Autowired
	public ReviewServiceImpl(ReviewDao reviewDao) {
		this.reviewDao = reviewDao;
	}

	// 유저정보를 통해 작성한 리뷰 가져오기
	// 사용처 : (개인정보에서 작성한 리뷰 확인)
	@Override
	public List<Review> getReviewByUserId(String reviewWriter) {
		return reviewDao.selectReviewByUserId(reviewWriter);
	}

	// 리뷰 고유번호를 통한 리뷰 가져오기
	// 사용처 : (리뷰 수정을 위해서)
	@Override
	public Review getReviewDetailByReviewId(String reviewIdx) {
		return reviewDao.selectReviewDetailByReviewId(reviewIdx);
	}

	// 리뷰 등록
	// 사용처 : (영상 상세페이지 리뷰 등록)
	@Override
	public void createReview(Review review) {
		reviewDao.insertReview(review);
	}

	// 리뷰 고유 번호를 통한 선택한 리뷰 수정
	// 사용처 : (리뷰 페이지, 개인페이지에서 리뷰 수정)
	@Override
	public void modifyReviewByReviewId(Review review) {
		reviewDao.updateReviewByReviewId(review);
	}

	// 리뷰 고유 번호를 통한 리뷰 삭제
	// 사용처 : (리뷰 페이지, 개인페이지에서 리뷰 삭제)
	@Override
	public void removeReviewByReviewId(String reviewIdx) {
		reviewDao.deleteReviewByReviewId(reviewIdx);
	}

	// 비디오 아이디를 통한 리뷰 리스트 조회
	// 사용처 : (영상 리뷰)
	@Override
	public List<Review> getReviewByVideoId(String reviewVideoId) {
		System.out.println(reviewVideoId instanceof String);
		return reviewDao.selectReviewByVideoId(reviewVideoId);
	}

}
