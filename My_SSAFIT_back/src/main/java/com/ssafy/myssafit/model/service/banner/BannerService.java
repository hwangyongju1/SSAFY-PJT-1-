package com.ssafy.myssafit.model.service.banner;

import java.util.List;

import com.ssafy.myssafit.model.dto.Banner;

public interface BannerService {
	
	public List<Banner> getBannerList();
	
	// 배너 정보 저장
	public void setBanner(Banner banner);
	
	// 배너 수정(switch 변경)
	public void modifyBanner(Banner banner);
	
	// 배너 정보 가져오기(수정 처리를 위함)
	public Banner getBanner(String bannerIdx);
	
	// 배너 삭제
	public void removeBanner(String bannerIdx); 
	
	// 배너 활성화 바꾸기
	public void makeBannerSwitchZero();
	
	// 활성화된 배너 정보 가져오기
	public Banner getActiveBanner();
}
