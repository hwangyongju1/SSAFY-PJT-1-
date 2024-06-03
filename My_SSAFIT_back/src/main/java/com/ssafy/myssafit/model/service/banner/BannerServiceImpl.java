package com.ssafy.myssafit.model.service.banner;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.myssafit.model.dao.Banner.BannerDao;
import com.ssafy.myssafit.model.dto.Banner;

@Service
public class BannerServiceImpl implements BannerService {

	private final BannerDao bannerDao;
	
	@Autowired
	public BannerServiceImpl(BannerDao bannerDao) {
		this.bannerDao = bannerDao;
	}
	
	@Override
	public void setBanner(Banner banner) {
		bannerDao.insertBanner(banner);
	}

	@Override
	public void modifyBanner(Banner banner) {
		bannerDao.updateBanner(banner);
	}

	@Override
	public Banner getBanner(String bannerIdx) {
		return bannerDao.selectBanner(bannerIdx);
	}

	@Override
	public void removeBanner(String bannerIdx) {
		bannerDao.deleteBanner(bannerIdx);
	}

	@Override
	public List<Banner> getBannerList() {
		return bannerDao.selectAll();
	}

	@Override
	public void makeBannerSwitchZero() {
		bannerDao.updateBannerSwitch();
	}

	@Override
	public Banner getActiveBanner() {
		return bannerDao.getActiveBanner();
	}

}
