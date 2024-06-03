package com.ssafy.myssafit.model.dao.Banner;

import java.util.List;

import com.ssafy.myssafit.model.dto.Banner;

public interface BannerDao {
	public void insertBanner(Banner banner);
	
	public void updateBanner(Banner banner);
	
	public Banner selectBanner(String bannerIdx);
	
	public void deleteBanner(String bannerIdx);
	
	public List<Banner> selectAll();

	public void updateBannerSwitch();

	public Banner getActiveBanner();
}
