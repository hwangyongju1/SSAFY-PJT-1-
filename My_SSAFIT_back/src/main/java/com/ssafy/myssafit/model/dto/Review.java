package com.ssafy.myssafit.model.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Review {
	private int reviewIdx;
	private String reviewWriter;
	private String reviewContent;
	private String reviewRegDate;
	private String reviewVideoId;
	
	public Review() {
	}

	public Review(int reviewIdx, String reviewWriter, String reviewContent, String reviewRegDate,
			String reviewVideoId) {
		super();
		this.reviewIdx = reviewIdx;
		this.reviewWriter = reviewWriter;
		this.reviewContent = reviewContent;
		this.reviewRegDate = reviewRegDate;
		this.reviewVideoId = reviewVideoId;
	}

	public int getReviewIdx() {
		return reviewIdx;
	}

	public void setReviewIdx(int reviewIdx) {
		this.reviewIdx = reviewIdx;
	}

	public String getReviewWriter() {
		return reviewWriter;
	}

	public void setReviewWriter(String reviewWriter) {
		this.reviewWriter = reviewWriter;
	}

	public String getReviewContent() {
		return reviewContent;
	}

	public void setReviewContent(String reviewContent) {
		this.reviewContent = reviewContent;
	}

	public String getReviewRegDate() {
		return reviewRegDate;
	}

	public void setReviewRegDate(String reviewRegDate) {
		this.reviewRegDate = reviewRegDate;
	}

	public String getReviewVideoId() {
		return reviewVideoId;
	}

	public void setReviewVideoId(String reviewVideoId) {
		this.reviewVideoId = reviewVideoId;
	}

	@Override
	public String toString() {
		return "Review [reviewIdx=" + reviewIdx + ", reviewWriter=" + reviewWriter + ", reviewContent=" + reviewContent
				+ ", reviewRegDate=" + reviewRegDate + ", reviewVideoId=" + reviewVideoId + "]";
	}

}
