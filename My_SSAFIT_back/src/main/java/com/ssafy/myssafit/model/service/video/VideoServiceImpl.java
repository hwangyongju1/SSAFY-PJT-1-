package com.ssafy.myssafit.model.service.video;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.myssafit.model.dao.video.VideoDao;
import com.ssafy.myssafit.model.dto.Video;
import com.ssafy.myssafit.model.dto.VideoCategory;

@Service
public class VideoServiceImpl implements VideoService {
	
	private final VideoDao videoDao;
	
	@Autowired
	public VideoServiceImpl(VideoDao videoDao) {
		this.videoDao = videoDao;
	}

	@Override
	public List<Video> getVideoList() {
		return videoDao.selectAll();
	}

	@Override
	public Video getVideoDetail(String videoId) {
		return videoDao.selectVideoById(videoId);
	}

	@Override
	public void modifyVideoByVideoId(Video video) {
		videoDao.updateVideoById(video);
	}

	@Override
	public void removeVideoByVideoId(String videoId) {
		videoDao.deleteVideoById(videoId);
	}

	@Override
	public List<VideoCategory> getVideoCategory() {
		return videoDao.selectCategoryAll();
	}

	@Override
	public List<VideoCategory> getVidoCategorySort() {
		return videoDao.selectCategoryDistinct();
	}

	@Override
	public void addVideoCategory(VideoCategory videoCategory) {
		 videoDao.insertVideoCategory(videoCategory);
		
	}

	@Override
	public void removeVideoCategoryByKey(String keyword) {
		videoDao.deleteVideoCategoryByKey(keyword);
	}

	@Override
	public void addVideo(Video video) {
		videoDao.insertVideo(video);
	}

	@Override
	public List<Video> getVideoListByCategory(String category) {
		return videoDao.selectVideoListByCategory(category);
	}

}
