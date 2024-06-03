package com.ssafy.myssafit.model.dao.video;

import java.util.List;
import java.util.Map;

import com.ssafy.myssafit.model.dto.Video;
import com.ssafy.myssafit.model.dto.VideoCategory;

public interface VideoDao {
	// 영상 목록 불러오기
	public List<Video> selectAll();
	
	// 영상 아이디를 통해 영상 정보 가져오기
	public Video selectVideoById(String videoId);
	
	// 영상 아이디를 통해 영상 정보 수정하기
	public void updateVideoById(Video video);
	
	// 영상 삭제
	public void deleteVideoById(String videoId);
	
	
	// 영상 카테고리 전체 조회
	public List<VideoCategory> selectCategoryAll();
	
	// 영상 카테고리 중복 제거 조회
	public List<VideoCategory> selectCategoryDistinct();

	// 영상 카테고리 추가
	public void insertVideoCategory(VideoCategory videoCategory);

	// 영상 카테고리 삭제
	public void deleteVideoCategoryByKey(String keyword);

	// 비디오 추가
	public void insertVideo(Video video);

	// 카테고리에 따른 비디오 리스트 받아오기
	public List<Video> selectVideoListByCategory(String category);
}
