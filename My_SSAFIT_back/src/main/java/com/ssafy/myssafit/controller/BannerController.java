package com.ssafy.myssafit.controller;

import java.io.File;
import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ssafy.myssafit.model.dto.Banner;
import com.ssafy.myssafit.model.service.banner.BannerService;

@RestController
@RequestMapping("/banner")
@CrossOrigin("*")
public class BannerController {
	
	private final BannerService bannerService;
	
	@Autowired
	public BannerController(BannerService bannerService) {
		this.bannerService = bannerService;
	}
	
	@PostMapping("/upload")
	public ResponseEntity<?> updateImgFile(@RequestParam("file") MultipartFile file,
			@RequestParam("title") String title,
			MultipartHttpServletRequest request) throws IOException {
	    try {
	        //File 경로 임의 지정
	    	// 파일을 저장할 경로 지정 (src/main/resources/static/img)
	    	String uploadPath = new File("src/main/resources/static/img").getAbsolutePath();

	        File fileUpload = new File(uploadPath,file.getOriginalFilename());

	        //생성될 경로가 없을 경우, 파일을 생성한다.
	        if (!fileUpload.getParentFile().exists()) {
	            fileUpload.getParentFile().mkdirs();
	        }
	        file.transferTo(fileUpload);
	        
	    }
	    catch (Exception e) {
	    	 return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	    String fileUrl = "http://localhost:8080/img/" + file.getOriginalFilename();
	    // chat gpt가 말해준 vue에서 사용할 수 있는 경로입니다. DB에 저장한 경로입니다.
        Banner banner = new Banner();
        banner.setBannerName(title);
        banner.setBannerImgFile(fileUrl);
        bannerService.setBanner(banner);
	    
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/list")
	public ResponseEntity<?> getBannerList() {
		List<Banner> list = bannerService.getBannerList();
		return new ResponseEntity<List<Banner>>(list, HttpStatus.OK);
	}
	
	@PutMapping("/active/{bannerIdx}")
	public ResponseEntity<?> activeBanner(@PathVariable("bannerIdx") String bannerIdx) {
		bannerService.makeBannerSwitchZero();
		Banner banner = bannerService.getBanner(bannerIdx);
		boolean chk = banner.isBannerSwitch();
		if (chk) {
			banner.setBannerSwitch(false);
		}else {
			banner.setBannerSwitch(true);
		}
		bannerService.modifyBanner(banner);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@DeleteMapping("/remove/{bannerIdx}")
	public ResponseEntity<?> removeBanner(@PathVariable("bannerIdx") String bannerIdx) {
		bannerService.removeBanner(bannerIdx);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	@GetMapping("/getBanner")
	public ResponseEntity<?> getActiveBanner() {
		Banner banner = bannerService.getActiveBanner();
		return new ResponseEntity<Banner>(banner, HttpStatus.OK);
	}
}
