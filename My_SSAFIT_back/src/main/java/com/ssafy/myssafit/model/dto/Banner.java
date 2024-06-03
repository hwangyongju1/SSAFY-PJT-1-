package com.ssafy.myssafit.model.dto;

public class Banner {
	private int bannerIdx;
	private String bannerName;
	private String bannerImgFile;
	private boolean bannerSwitch;
	
	public Banner() {
	}
	
	protected Banner(int bannerIdx, String bannerName, String bannerImgFile, boolean bannerSwitch) {
		super();
		this.bannerIdx = bannerIdx;
		this.bannerName = bannerName;
		this.bannerImgFile = bannerImgFile;
		this.bannerSwitch = bannerSwitch;
	}

	public int getBannerIdx() {
		return bannerIdx;
	}

	public void setBannerIdx(int bannerIdx) {
		this.bannerIdx = bannerIdx;
	}

	public String getBannerName() {
		return bannerName;
	}

	public void setBannerName(String bannerName) {
		this.bannerName = bannerName;
	}

	public String getBannerImgFile() {
		return bannerImgFile;
	}

	public void setBannerImgFile(String bannerImgFile) {
		this.bannerImgFile = bannerImgFile;
	}

	public boolean isBannerSwitch() {
		return bannerSwitch;
	}

	public void setBannerSwitch(boolean bannerSwitch) {
		this.bannerSwitch = bannerSwitch;
	}

	@Override
	public String toString() {
		return "Banner [bannerIdx=" + bannerIdx + ", bannerName=" + bannerName + ", bannerImgFile=" + bannerImgFile
				+ ", bannerSwitch=" + bannerSwitch + "]";
	}
	
}
