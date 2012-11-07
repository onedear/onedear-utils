package com.onedear.util.useragent;

/**
 * <br>=
 * ========================= <br>
 * 
 * @company ：优视科技 <br>
 * @author ：邱志明(onedear) <br>
 * @version ：1.0 <br>
 * @date : 2012-6-7下午06:17:31 <br>=
 *       =========================
 */
public class UserAgentModule {

	private String brand;

	private String os;

	private String osType;

	private boolean mobile;

	private String browser;

	private String browserType;

	private String userAgent;
	
	public UserAgentModule(String userAgent) {
		if (userAgent == null)
			return;
		this.userAgent = userAgent;
		
		this.mobile = MobileUtil.isMobile(userAgent);
		
		if (mobile) {
			String brand = MobileUtil.getMobileModel(userAgent);
			this.brand = brand;
			MobileVersion mobileOs = MobileUtil.getMobileOs(userAgent);
			if (mobileOs != null) {
				this.osType = mobileOs.getType();
				this.os = mobileOs.getVersion();
			}
			MobileVersion mobileBrowser = MobileUtil.getMobileBrowser(userAgent);
			if (mobileBrowser != null) {
				browserType = mobileBrowser.getType();
				browser = mobileBrowser.getVersion();
			}
		} else {
			browserType = HTTPProtocolUtil.getBrowserFromUserAgent(userAgent);
			browser = browserType + " " + HTTPProtocolUtil.getBrowserVersionFromUserAgent(userAgent, browserType); 
			this.os = HTTPProtocolUtil.getOSFromUserAgent(userAgent);
			this.osType = HTTPProtocolUtil.getOsTypeFromOs(os);
		}
	}

	public String getBrand() {
		return (brand == null)?"":brand ;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getOs() {
		return (os != null)?os:"";
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getOsType() {
		return (osType != null)?osType:"";
	}

	public void setOsType(String osType) {
		this.osType = osType;
	}

	public boolean isMobile() {
		return mobile;
	}

	public void setMobile(boolean mobile) {
		this.mobile = mobile;
	}

	public String getBrowser() {
		return (browser != null)?browser:"";
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}

	public String getBrowserType() {
		return (browserType != null)?browserType:"";
	}

	public void setBrowserType(String browserType) {
		this.browserType = browserType;
	}

	@Override
	public String toString() {
		if (mobile) {
			return "目标是手机访问,手机品牌是： " + brand + ",手机系统类型是： " 
			+ osType + ", 手机系统版本号是： " + os + ", 手机浏览器类型是： " 
			+ browserType + ", 手机浏览器版本号是： " + browser + "\r\n" + userAgent;
		} else {
			return "目标是PC访问,系统类型是： " 
			+ osType + ", 系统版本号是： " + os + ", 浏览器类型是： " 
			+ browserType + ", 浏览器版本号是： " + browser + "\r\n" + userAgent;
		}
		
	}
	
}
