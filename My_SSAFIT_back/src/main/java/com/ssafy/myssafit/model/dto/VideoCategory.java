package com.ssafy.myssafit.model.dto;

public class VideoCategory {
	private String keyword;
	private String category;
	
	public VideoCategory() {
	}

	public VideoCategory(String keyword, String category) {
		super();
		this.keyword = keyword;
		this.category = category;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "VideoCategory [keyword=" + keyword + ", category=" + category + "]";
	}
	
	
}
