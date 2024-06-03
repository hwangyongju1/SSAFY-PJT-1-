package com.ssafy.myssafit.model.service.video;

import java.util.List;
import java.util.Map;

import com.ssafy.myssafit.model.dto.Video;
import com.ssafy.myssafit.model.dto.VideoCategory;

public interface VideoService {
	// 영상 리스트 전체 조회
	// 사용처 : ( 관리자 페이지 영상 관리 페이지)
	public List<Video> getVideoList();
	
	// 영상 정보 하나 가져오기
	// 사용처 : (관리자 페이지 영상 수정, 관리자 페이지 카테고리 분류)
	public Video getVideoDetail(String videoId);
	
	// 영상 정보 수정
	// 사용처 : (관리자 페이지 영상 관리 페이지에서 영상 수정, 관리자 페이지 카테고리 분류)
	public void modifyVideoByVideoId(Video video);
	
	// 영상 삭제
	// 사용처 : (관리자 페이지 영상 관리 페이지에서 영상 삭제)
	public void removeVideoByVideoId(String videoId);
	
	// 
	
	// 영상 카테고리 리스트 전체 조회
	public List<VideoCategory> getVideoCategory();
	
	// 영상 카테고리 중복 제거 조회
	public List<VideoCategory> getVidoCategorySort();

	// 영상 카테고리 추가
	public void addVideoCategory(VideoCategory videoCategory);

	// 영상 카테고리 제거
	public void removeVideoCategoryByKey(String keyword);

	// 영상 추가
	public void addVideo(Video video);

	// 카테고리에 따른 영상 정렬해서 보여주기
	public List<Video> getVideoListByCategory(String category);
	
}
