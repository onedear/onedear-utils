package userAgent;

import com.onedear.util.useragent.UserAgentModule;


/**
 * <br>==========================
 * <br> @company ：优视科技
 * <br> @author  ：邱志明(onedear)
 * <br> @version ：1.0 
 * <br> @date    : 2012-6-8下午04:39:57
 * <br>==========================
 */
public class UserAgentTest {
	
	public static void main(String[] args) {
		
		String iphone = "Mozilla/5.0 (iPhone; U; CPU iPhone OS 4_1 like Mac OS X; zh-cn)     AppleWebKit/532.9 (KHTML, like Gecko) Version/4.0.5 Mobile/8B117 Safari/6531.22.7";
		String iphoneUc = "IUC(U;iOS 4.1;Zh-cn;320*480;)/UCWEB8.0.3.99/41/997";
		String ipad = "mozilla/5.0 (ipad;U;Cpu os 3_2 like mac os X;zh-ch) applewebkit/ 531.21.10(khtml,like gecko) version/4.0.4 mobile/7B367 safari/531.21.10";
		
		String aUC8_5 = "[Mozilla/5.0 (Linux; U; Android 2.3.7; zh-cn; HTC Desire Build/GRI40) UC AppleWebKit/534.31 (KHTML, like Gecko) Mobile Safari/534.31]";
		String iphoneSafari501 = "[Mozilla/5.0 (iPhone; CPU iPhone OS 5_0_1 like Mac OS X) AppleWebKit/534.46 (KHTML, like Gecko) Version/5.1 Mobile/9A405 Safari/7534.48.3";
		String iphoneUc501 = " [IUC(U;iOS 5.0.1;Zh-cn;320*480;)/UCWEB8.4.1.169/42/997]";
		
		String xpChrome = "[Mozilla/5.0 (Windows NT 5.1) AppleWebKit/536.5 (KHTML, like Gecko) Chrome/19.0.1084.52 Safari/536.5]";
		
		String iphoneQq = "MQQBrowser/33 Mozilla/5.0 (iPhone; CPU iPhone OS 5_0_1 like Mac OS X) AppleWebKit/534.46 (KHTML, like Gecko) Mobile/9A405 Safari/7534.48.3";
		String androidQq = "MQQBrowser/3.4/Adr (Linux; U; 4.0.3; zh-cn; GT-N7000 Build/IML74K.XXLPY;800*1280)";
		String iphoneOpera = "Opera/9.80 (iPhone; Opera Mini/7.2.37083/27.1667; U; zh) Presto/2.8.119 Version/11.10";
		String androidChrome = "Mozilla/5.0 (Linux; Android 4.0.3;  GT-N7000 Build/IML74K) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.133 Mobile Safari/535.19";
		String hw = "Mozilla/5.0 (Linux; U; Android HW11827 2.2.2_OE By Quinn Q2; zh-cn; C8500 Build/HuaweiC8500) UC AppleWebKit/530+ (KHTML, like Gecko) Mobile Safari/530";
		String userAgent = hw;
		
		UserAgentModule uam = new UserAgentModule(userAgent);
		System.out.println(uam);
	}
	
}
