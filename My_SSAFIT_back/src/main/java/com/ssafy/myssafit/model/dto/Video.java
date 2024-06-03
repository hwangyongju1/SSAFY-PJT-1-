package com.ssafy.myssafit.model.dto;

public class Video {
	private String videoId;
	private String videoTitle;
	private String videoImgUrl;
	private String videoCategory;
	private int videoViewCnt;
	
	public Video() {
	}

	public Video(String videoId, String videoTitle, String videoImgUrl, String videoCategory, int videoViewCnt) {
		super();
		this.videoId = videoId;
		this.videoTitle = videoTitle;
		this.videoImgUrl = videoImgUrl;
		this.videoCategory = videoCategory;
		this.videoViewCnt = 0;
	}

	public String getVideoId() {
		return videoId;
	}

	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}

	public String getVideoTitle() {
		return videoTitle;
	}

	public void setVideoTitle(String videoTitle) {
		this.videoTitle = videoTitle;
	}

	public String getVideoImgUrl() {
		return videoImgUrl;
	}

	public void setVideoImgUrl(String videoImgUrl) {
		this.videoImgUrl = videoImgUrl;
	}

	public String getVideoCategory() {
		return videoCategory;
	}

	public void setVideoCategory(String videoCategory) {
		this.videoCategory = videoCategory;
	}

	public int getVideoViewCnt() {
		return videoViewCnt;
	}

	public void setVideoViewCnt(int videoViewCnt) {
		this.videoViewCnt = videoViewCnt;
	}

	@Override
	public String toString() {
		return "Video [videoId=" + videoId + ", videoTitle=" + videoTitle + ", videoImgUrl=" + videoImgUrl
				+ ", videoCategory=" + videoCategory + ", videoViewCnt=" + videoViewCnt + "]";
	}
}
