package com.ssafy.myssafit.controller;

import java.util.ArrayList;
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

import com.ssafy.myssafit.model.dto.Video;
import com.ssafy.myssafit.model.dto.VideoCategory;
import com.ssafy.myssafit.model.service.video.VideoService;
import com.ssafy.myssafit.util.CategorySearch;



@RestController
@RequestMapping("/video")
@CrossOrigin("*")
public class VideoController {
	private final VideoService videoService;
	
	@Autowired
	public VideoController(VideoService videoService) {
		this.videoService = videoService;
	}
	
	// 관리자 페이지에서 전체 영상 리스트 불러오기
	@GetMapping("/list")
	public ResponseEntity<?> getVideoList() {
		List<Video> videoList = videoService.getVideoList();
		return new ResponseEntity<List<Video>>(videoList, HttpStatus.OK);
	}
	
	// 카테고리에 따른 영상 리스트 불러오기
	@GetMapping("/list/{category}")
	public ResponseEntity<?> getVideoListByCategory(@PathVariable("category") String category) {
		List<Video> videoList = videoService.getVideoListByCategory(category);
		return new ResponseEntity<List<Video>>(videoList, HttpStatus.OK);
	}
	
	// 관리자 페이지에서 비디오 삭제
	@DeleteMapping("/remove/{videoId}")
	public ResponseEntity<Void> removeVideo(@PathVariable("videoId") String videoId) {
		videoService.removeVideoByVideoId(videoId);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	// 관리자 페이지에서 비디오 수정을 위한 비디오 정보 가져오기
	@GetMapping("/{videoId}")
	public ResponseEntity<?> getVideoById(@PathVariable("videoId") String videoId) {
		Video video = videoService.getVideoDetail(videoId);
		return new ResponseEntity<Video>(video, HttpStatus.OK);
	}
	
	// 관리자 페이지에서 비디오 수정하기
	@PutMapping("/{videoId}")
	public ResponseEntity<?> modifyVideoById(@PathVariable("videoId") String videoId, @RequestBody Video video) {
		videoService.modifyVideoByVideoId(video);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	// 카테고리 전체 가져오기
	@GetMapping("/category")
	public ResponseEntity<?> getVideoCategory() {
		List<VideoCategory> list = videoService.getVideoCategory();
		
		return new ResponseEntity<List<VideoCategory>>(list, HttpStatus.OK);
	}
	
	// 카테고리 중복 제거하고 가져오기
	@GetMapping("/categorySort")
	public ResponseEntity<?> getVideoCategorySort() {
		List<VideoCategory> list = videoService.getVidoCategorySort();
		return new ResponseEntity<List<VideoCategory>>(list, HttpStatus.OK);
	}
	
	// 카테고리 추가
	@PostMapping("/addCategory")
	public ResponseEntity<?> addVideoCategory(@RequestBody VideoCategory videoCategory) {
		System.out.println(videoCategory);
		videoService.addVideoCategory(videoCategory);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	// 카테고리 삭제
	@DeleteMapping("/category/{keyword}")
	public ResponseEntity<?> removeVideoCategory(@PathVariable("keyword") String keyword) {
		videoService.removeVideoCategoryByKey(keyword);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	// 영상 추가 
	@PostMapping("/addVideo")
	public ResponseEntity<?> addVideo(@RequestBody Video video) {
		// 비디오가 존재하지 않을 때만 추가
		boolean isVideoAdded = videoService.getVideoDetail(video.getVideoId()) == null;
		if(isVideoAdded) {
			CategorySearch category = new CategorySearch();
			
			List<VideoCategory> list = videoService.getVideoCategory();
			
			for (int i = 0; i < list.size(); i++) {
				boolean check = category.KMP(video.getVideoTitle(), list.get(i).getKeyword());
				if (check) {
					video.setVideoCategory(list.get(i).getCategory());
					break;
				}
			}
			videoService.addVideo(video);
		}
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
}
