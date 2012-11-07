package com.onedear.util.useragent;


import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class MobileUtil {
	private static String isMobileRegex1 = ".*(android.+mobile|qqbrowser|ucweb|avantgo|bada\\/|blackberry|blazer|compal|elaine|fennec|hiptop|iemobile|ip(hone|od|ad)|iris|kindle|lge |maemo|midp|mmp|opera m(ob|in)i|palm( os)?|phone|p(ixi|re)\\/|plucker|pocket|psp|symbian|treo|up\\.(browser|link)|vodafone|wap|windows (ce|phone)|xda|xiino).*";
	private static String isMobileRegex2 = "1207|6310|6590|3gso|4thp|50[1-6]i|770s|802s|a wa|abac|ac(er|oo|s\\-)|ai(ko|rn)|al(av|ca|co)|amoi|an(ex|ny|yw)|aptu|ar(ch|go)|as(te|us)|attw|au(di|\\-m|r |s )|avan|be(ck|ll|nq)|bi(lb|rd)|bl(ac|az)|br(e|v)w|bumb|bw\\-(n|u)|c55\\/|capi|ccwa|cdm\\-|cell|chtm|cldc|cmd\\-|co(mp|nd)|craw|da(it|ll|ng)|dbte|dc\\-s|devi|dica|dmob|do(c|p)o|ds(12|\\-d)|el(49|ai)|em(l2|ul)|er(ic|k0)|esl8|ez([4-7]0|os|wa|ze)|fetc|fly(\\-|_)|g1 u|g560|gene|gf\\-5|g\\-mo|go(\\.w|od)|gr(ad|un)|haie|hcit|hd\\-(m|p|t)|hei\\-|hi(pt|ta)|hp( i|ip)|hs\\-c|ht(c(\\-| |_|a|g|p|s|t)|tp)|hu(aw|tc)|i\\-(20|go|ma)|i230|iac( |\\-|\\/)|ibro|idea|ig01|ikom|im1k|inno|ipaq|iris|ja(t|v)a|jbro|jemu|jigs|kddi|keji|kgt( |\\/)|klon|kpt |kwc\\-|kyo(c|k)|le(no|xi)|lg( g|\\/(k|l|u)|50|54|e\\-|e\\/|\\-[a-w])|libw|lynx|m1\\-w|m3ga|m50\\/|ma(te|ui|xo)|mc(01|21|ca)|m\\-cr|me(di|rc|ri)|mi(o8|oa|ts)|mmef|mo(01|02|bi|de|do|t(\\-| |o|v)|zz)|mt(50|p1|v )|mwbp|mywa|n10[0-2]|n20[2-3]|n30(0|2)|n50(0|2|5)|n7(0(0|1)|10)|ne((c|m)\\-|on|tf|wf|wg|wt)|nok(6|i)|nzph|o2im|op(ti|wv)|oran|owg1|p800|pan(a|d|t)|pdxg|pg(13|\\-([1-8]|c))|phil|pire|pl(ay|uc)|pn\\-2|po(ck|rt|se)|prox|psio|pt\\-g|qa\\-a|qc(07|12|21|32|60|\\-[2-7]|i\\-)|qtek|r380|r600|raks|rim9|ro(ve|zo)|s55\\/|sa(ge|ma|mm|ms|ny|va)|sc(01|h\\-|oo|p\\-)|sdk\\/|se(c(\\-|0|1)|47|mc|nd|ri)|sgh\\-|shar|sie(\\-|m)|sk\\-0|sl(45|id)|sm(al|ar|b3|it|t5)|so(ft|ny)|sp(01|h\\-|v\\-|v )|sy(01|mb)|t2(18|50)|t6(00|10|18)|ta(gt|lk)|tcl\\-|tdg\\-|tel(i|m)|tim\\-|t\\-mo|to(pl|sh)|ts(70|m\\-|m3|m5)|tx\\-9|up(\\.b|g1|si)|utst|v400|v750|veri|vi(rg|te)|vk(40|5[0-3]|\\-v)|vm40|voda|vulc|vx(52|53|60|61|70|80|81|83|85|98)|w3c(\\-| )|webc|whit|wi(g |nc|nw)|wmlb|wonu|x700|xda(\\-|2|g)|yas\\-|your|zeto|zte\\-";
	
	
	//手机品牌型号
	private static String mobileModleRegex = ".*(nokia[\\S&&[^\\/]]*)|;([\\s\\S&&[^;]]*)\\sbuild|(ipad)|(iphone)|(ipod)|(ios)|(meizu[\\S&&[^;,|]]*)";
	private static Pattern mobileModleRegexPattern = Pattern.compile(mobileModleRegex,Pattern.CASE_INSENSITIVE);
	//手机系统
	private static String mobileOsRegex = "(symbianOS/\\S*)|(series60)|(series40)|(symbian/\\S*)|(android\\s[\\S&&[^;]]*)[;|\\s]|(iphone\\sos\\s\\S*)|(ios\\s[\\S&&[^;]]*)|(cpu\\sos\\s[\\S&&[^;]]*)|(windows\\sphone\\s[\\S&&[^);,]]*)"; 
	private static Pattern mobileOsRegexPattern = Pattern.compile(mobileOsRegex,Pattern.CASE_INSENSITIVE);
	
	
	private static String mobileBrowserUcs = "(ucweb[\\S&&[^/]]*)|(\\sUC\\s)";
	private static Pattern mobileBrowserUcsPattern = Pattern.compile(mobileBrowserUcs,Pattern.CASE_INSENSITIVE);
	
	private static String mobileBrowserSafari = "version/(\\S*).*safari";
	private static Pattern mobileBrowserSafariPattern = Pattern.compile(mobileBrowserSafari,Pattern.CASE_INSENSITIVE);
	
	private static String mobileBrowserQQ = "mqqbrowser/([\\d]*)";
	private static Pattern mobileBrowserQQPattern = Pattern.compile(mobileBrowserQQ,Pattern.CASE_INSENSITIVE);
	
	private static String mobileBrowserChrome = "chrome/(\\S*)";
	private static Pattern mobileBrowserChromePattern = Pattern.compile(mobileBrowserChrome,Pattern.CASE_INSENSITIVE);
	
	private static String mobileBrowserOpera = "opera.*version/(\\S*)";
	private static Pattern mobileBrowserOperaPattern = Pattern.compile(mobileBrowserOpera,Pattern.CASE_INSENSITIVE);
	
	
	
	private static String mobileBrowserIe = "msie\\s([\\S]*)";
	private static Pattern mobileBrowserIePattern = Pattern.compile(mobileBrowserIe,Pattern.CASE_INSENSITIVE);
	
	private static String mobileBrowserNokia = "([\\S&&[^/]]*browser[\\S&&[^/]]*)\\/([\\S&&[^/;]]*)";
	private static Pattern mobileBrowserNokiaPattern = Pattern.compile(mobileBrowserNokia,Pattern.CASE_INSENSITIVE);
		
		
	
	public static boolean isMobile(String userAgent) {
		if(StringUtil.isEmpty(userAgent))
			return false;
		userAgent = userAgent.toLowerCase();
		if(userAgent.matches(isMobileRegex1) || (userAgent.length()>4 && userAgent.substring(0,4).matches(isMobileRegex2)))
			return true ;
		return false ;
	}	
		
	public static MobileVersion getMobileBrowser(String userAgent) {
		userAgent = userAgent.toLowerCase();
		
		Matcher matcher = mobileBrowserUcsPattern.matcher(userAgent);
		while(matcher.find()) {
			for(int i = 1 ; i <= matcher.groupCount() ; i ++) {
				String mobile = matcher.group(i);
				if(mobile != null && mobile.length() >0) {
					MobileVersion version = verifyMobileBrowser(mobile);
					
					return version;
				}
			}
		}
		
		matcher = mobileBrowserQQPattern.matcher(userAgent);
		while(matcher.find()) { 
			String safariVersion = matcher.group(1);
			MobileVersion version = new MobileVersion();
			version.setType("qq");
			version.setVersion("qq "+ safariVersion);
			return version ;
		}
		
		matcher = mobileBrowserChromePattern.matcher(userAgent);
		while(matcher.find()) { 
			String safariVersion = matcher.group(1);
			MobileVersion version = new MobileVersion();
			version.setType("chrome");
			version.setVersion("chrome "+ safariVersion);
			return version ;
		}
		
		matcher = mobileBrowserOperaPattern.matcher(userAgent);
		while(matcher.find()) { 
			String safariVersion = matcher.group(1);
			MobileVersion version = new MobileVersion();
			version.setType("opera");
			version.setVersion("opera "+ safariVersion);
			return version ;
		}
		
		
		matcher = mobileBrowserSafariPattern.matcher(userAgent);
		while(matcher.find()) { 
			String safariVersion = matcher.group(1);
			MobileVersion version = new MobileVersion();
			version.setType("safari");
			version.setVersion("safari "+ safariVersion);
			return version ;
		}
		
		matcher = mobileBrowserIePattern.matcher(userAgent);
		while(matcher.find()) { 
			String ieVersion = matcher.group(1);
			MobileVersion version = new MobileVersion();
			version.setType("ie");
			version.setVersion("ie "+ ieVersion);
			return version ;
		}
		
		matcher = mobileBrowserNokiaPattern.matcher(userAgent);
		while(matcher.find()) { 
			for(int i = 1 ; i <= matcher.groupCount() ; i ++) {
				String nokiaVersion = matcher.group(i);
				if(nokiaVersion == null || nokiaVersion.length() ==0)
					continue;
				MobileVersion version = new MobileVersion();
				version.setType(nokiaVersion);
				if(matcher.groupCount() >i)
					version.setVersion(nokiaVersion+" "+ matcher.group(i+1));
				else 
					version.setVersion(nokiaVersion);
				return version ;
			}
		}
		
		return null ; 
	}
		
	public static MobileVersion getMobileOs(String userAgent)  {
		userAgent = userAgent.toLowerCase();
		Matcher matcher = mobileOsRegexPattern.matcher(userAgent);
		while(matcher.find()) {
			for(int i = 1 ; i <= matcher.groupCount() ; i ++) {
				String mobile = matcher.group(i);
				if(mobile != null && mobile.length() >0) {
					MobileVersion version = verifyMobileOs(mobile);
					return version;
				}
			}
		}
		if (userAgent.indexOf("iphone") > 0) {
			MobileVersion version = verifyMobileOs("ios");
			return version;
		}
		return null ; 
	} 
	
	public static String getMobileModel(String userAgent ) {
		userAgent = userAgent.toLowerCase();
		Matcher matcher = mobileModleRegexPattern.matcher(userAgent);
		String mobile = null ; 
		while(matcher.find()) {
			for(int i = 1 ; i <= matcher.groupCount() ; i ++) {
				mobile = matcher.group(i);
				if(mobile != null && mobile.trim().length() >0) {
					return  verifyMobile(mobile.trim());
				}
			}
		}
		return null ; 
	}
	
	private static MobileVersion verifyMobileBrowser(String browser) {
		if(browser == null)
			return null ; 
		browser = browser.trim();
		String[] strs = new String[2];
		strs[1] = browser ; 
		for(int i = 0 ; i < browser.length() ; i++) {
			char c = browser.charAt(i);
			if(Character.isDigit(c) || c == ' ') {
				strs[0] = browser.substring(0 , i);
				break;
			}
		}
		
		MobileVersion version = new MobileVersion();
		version.setType(strs[0]);
		version.setVersion(browser);
		return version;
	}
	
	private static MobileVersion verifyMobileOs(String mobileOs) {
		if(mobileOs == null)
			return null  ;
		
		mobileOs = mobileOs.replace("_", ".");
		mobileOs = mobileOs.replace("/", " "); //for nokia
		
		if(mobileOs.indexOf("iphone") != -1)
			mobileOs = mobileOs.replace("iphone os","ios");
		if(mobileOs.equals("ios"))
			mobileOs =  "ios";
		if(mobileOs.indexOf("cpu os") != -1)
			mobileOs = mobileOs.replace("cpu os", "ios");
		if(mobileOs.indexOf("symbianos") != -1)
			mobileOs = mobileOs.replace("symbianos", "symbian");
		if(mobileOs.equals("series60") || mobileOs.equals("series40"))
			mobileOs =  "symbian";
		
		String[] strs = mobileOs.split(" ");
		MobileVersion version = new MobileVersion();
		version.setType(strs[0]);
		version.setVersion(mobileOs);
		
		return version;
	}
	
	private static String verifyMobile(String mobile) {
		if( mobile.indexOf("ios") != -1 )
			mobile = "iphone";
		if(mobile.indexOf("browser") != -1)
			mobile = mobile.replace("browser", "");
		return mobile;
	}
	
	public static void main(String[] args) {
		String nokia = "Nokia7610/2.0 (5.0509.0) SymbianOS/7.0s Series60/2.1 Profile/MIDP-2.0 Configuration/CLDC-1.0";
		String nokia2= "Nokia8310/1.0 (05.11) UP.Link/6.5.0.0.06.5.0.0.06.5.0.0.06.5.0.0.0";
		String nokiaN8 = "Mozilla/5.0 (Symbian/3; Series60/5.2 NokiaN8-00/012.002; Profile/MIDP-2.1 Configuration/CLDC-1.1 ) AppleWebKit/533.4 (KHTML, like Gecko) NokiaBrowser/7.3.0 Mobile Safari/533.4 3gpp-gba";
		String nokiax3 = "Mozilla/5.0 (Series40; NokiaX3-02/le6.32; Profile/MIDP-2.1 Configuration/CLDC-1.1) Gecko/20100401 S40OviBrowser/1.0.0.11.8";
		String nikiaN97 = "Mozilla/5.0 (SymbianOS/9.4; Series60/5.0 NokiaN97-1/12.0.024; Profile/MIDP-2.1 Configuration/CLDC-1.1; en-us) AppleWebKit/525 (KHTML, like Gecko) BrowserNG/7.1.12344";
		String nokiaN5700 = "NOKIA5700/UCWEB7.0.2.37/28/999";
		
		
		String HTCMiui = "HTC miui默认浏览器 ：Mozilla/5.0 (Linux; U; Android 2.3.5; zh-cn; HTC HD2 Build/MIUI) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1";
		String otherAndroid = "Mozilla/5.0 (Linux; U; Android 2.2; nl-nl; Desire_A8181 Build/FRF91) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1";
		String otherAndroid2 = "Mozilla/5.0 (Linux; U; Android 1.6; en-us; WOWMobile myTouch 3G Build/unknown) AppleWebKit/528.5+ (KHTML, like Gecko) Version/3.1.2 Mobile Safari/525.20.1";
		String m9 = " Mozilla/5.0 (Linux; U; Android 2.2; zh-cn; meizu_m9 Build/FRF91) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1";
		String m92 = "JUC (Linux; U; 2.2; zh-cn; meizu_m9; 640*960) UCWEB7.9.0.94/139/800";
		String sonyEricsson = "Mozilla/5.0 (Linux; U; Android 2.1-update1; zh-cn; SonyEricssonE15i Build/2.0.1.A.0.47) AppleWebKit/530.17 (KHTML, like Gecko) Version/4.0 Mobile Safari/530.17";
		String sonyEricsson2 = "Mozilla/5.0 (Linux; U; Android 2.1-update1; de-de; E10i Build/2.0.2.A.0.24) AppleWebKit/530.17 (KHTML, like Gecko) Version/4.0 Mobile Safari/530.17";
		String motorola = "Mozilla/5.0 (Linux; U; Android 2.1-update1; en-us; Droid Build/ESE81) AppleWebKit/530.17 (KHTML, like Gecko) Version/4.0 Mobile Safari/530.17";
		String googleNexus = "Mozilla/5.0 (Linux; U; Android 2.2; en-us; Nexus One Build/FRF91) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1";
		String samsung = "Mozilla/5.0 (Linux; U; Android 2.2; en-gb; GT-P1000 Build/FROYO) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1";
		
		
		String iphone = "Mozilla/5.0 (iPhone; U; CPU iPhone OS 4_1 like Mac OS X; zh-cn)     AppleWebKit/532.9 (KHTML, like Gecko) Version/4.0.5 Mobile/8B117 Safari/6531.22.7";
		String iphoneUc = "IUC(U;iOS 4.1;Zh-cn;320*480;)/UCWEB8.0.3.99/41/997";
		String ipad = "mozilla/5.0 (ipad;U;Cpu os 3_2 like mac os X;zh-ch) applewebkit/ 531.21.10(khtml,like gecko) version/4.0.4 mobile/7B367 safari/531.21.10";
		
		String winphone = "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; Windows Phone 6.5)";
		
		
		String qqbrowser = "symbianos/8.0 series60/2.6";
		
		
		
		String userAgent = ipad;
		
		boolean isMobile = isMobile(userAgent);
		System.out.println("是不是手机设备:" + isMobile );
		
		String mobile = getMobileModel(userAgent);
		System.out.println("手机品牌是： " + mobile );
		
		MobileVersion mobileOs = 	getMobileOs(userAgent);
		if(mobileOs != null) {
			System.out.println("手机系统类型是： " +mobileOs.getType() );
			System.out.println("手机系统版本号是： " +mobileOs.getVersion());
		} else {
			System.out.println("手机系统获取错误");
		}
		
		
		MobileVersion mobileBrowser = getMobileBrowser(userAgent);
		if(mobileBrowser != null) {
			System.out.println("手机浏览器类型是： " +mobileBrowser.getType() );
			System.out.println("手机浏览器版本号是： " +mobileBrowser.getVersion());
		} else {
			System.out.println("手机浏览器获取出错");
		}
		
	}
	
}
