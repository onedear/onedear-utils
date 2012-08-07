package db;import java.util.Collection;import java.util.Date;import java.util.HashMap;import java.util.Map;import java.util.Set;public class PageTransactionMessage implements Map<String, Object> {		private static final long serialVersionUID = 3080991294399456476L;	public Integer getType() {		return getInteger("type");	}	public void setType(Integer type) {		put("type", type);	}	public String getUrl() {		return getString("url");	}	public void setUrl(String url) {		put("url", url);	}	public String getTitle() {		return getString("title");	}	public void setTitle(String title) {		put("title", title);	}	public String getVisitorid() {		return getString("visitorid");	}	public void setVisitorid(String visitorid) {		put("visitorid", visitorid);	}	public String getWebTag() {		return getString("webTag");	}	public void setWebTag(String webTag) {		put("webTag", webTag);	}	public String getSystem() {		return getString("system");	}	public void setSystem(String system) {		put("system", system);	}	public String getColor() {		return getString("color");	}	public void setColor(String color) {		put("color", color);	}	public String getFlashversion() {		return getString("flashversion");	}	public void setFlashversion(String flashversion) {		put("flashversion", flashversion);	}	public String getPtid() {		return getString("ptid");	}	public void setPtid(String ptid) {		put("ptid", ptid);	}	public String getEtusername() {		return getString("etusername");	}	public void setEtusername(String etusername) {		put("etusername", etusername);	}	public String getPlugin() {		return getString("plugin");	}	public void setPlugin(String plugin) {		put("plugin", plugin);	}	public String getSound() {		return getString("sound");	}	public void setSound(String sound) {		put("sound", sound);	}	public String getCamera() {		return getString("camera");	}	public void setCamera(String camera) {		put("camera", camera);	}	public String getMic() {		return getString("mic");	}	public void setMic(String mic) {		put("mic", mic);	}	public String getScreen() {		return getString("screen");	}	public void setScreen(String screen) {		put("screen", screen);	}	public String getReferrer() {		return getString("referrer");	}	public void setReferrer(String referrer) {		put("referrer", referrer);	}	public Integer getIsNewUser() {		return getInteger("isNewUser");	}	public void setIsNewUser(Integer isNewUser) {		put("isNewUser", isNewUser);	}	public Integer getValidTime() {		return getInteger("validTime");	}	public void setValidTime(Integer validTime) {		put("validTime", validTime);	}	public Integer getDownloadTime() {		return getInteger("downloadTime");	}	public void setDownloadTime(Integer downloadTime) {		put("downloadTime", downloadTime);	}	public Integer getStayTime() {		return getInteger("stayTime");	}	public void setStayTime(Integer stayTime) {		put("stayTime", stayTime);	}	public String getMouseclick() {		return getString("mouseclick");	}	public void setMouseclick(String mouseclick) {		put("mouseclick", mouseclick);	}	public String getMousemove() {		return getString("mousemove");	}	public void setMousemove(String mousemove) {		put("mousemove", mousemove);	}	public String getKeyboard() {		return getString("keyboard");	}	public void setKeyboard(String keyboard) {		put("keyboard", keyboard);	}	public String getKeyword() {		return getString("keyword");	}	public void setKeyword(String keyword) {		put("keyword", keyword);	}	public String getIp() {		return getString("ip");	}	public void setIp(String ip) {		put("ip", ip);	}	public String getUserAgent() {		return getString("userAgent");	}	public void setUserAgent(String userAgent) {		put("userAgent", userAgent);	}	public String getOs() {		return getString("os");	}	public void setOs(String os) {		put("os", os);	}	public String getSystemLanguage() {		return getString("systemLanguage");	}	public void setSystemLanguage(String systemLanguage) {		put("systemLanguage", systemLanguage);	}	public String getBrowser() {		return getString("browser");	}	public void setBrowser(String browser) {		put("browser", browser);	}	public String getBrowserType() {		return getString("browserType");	}	public void setBrowserType(String browserType) {		put("browserType", browserType);	}	public String getBrowserMainVersion() {		return getString("browserMainVersion");	}	public void setBrowserMainVersion(String browserMainVersion) {		put("browserMainVersion", browserMainVersion);	}	public String getCountry() {		return getString("country");	}	public void setCountry(String country) {		put("country", country);	}	public String getCountryCode() {		return getString("countryCode");	}	public void setCountryCode(String countryCode) {		put("countryCode", countryCode);	}	public String getProvince() {		return getString("province");	}	public void setProvince(String province) {		put("province", province);	}	public String getProvinceCode() {		return getString("provinceCode");	}	public void setProvinceCode(String provinceCode) {		put("provinceCode", provinceCode);	}	public String getCity() {		return getString("city");	}	public void setCity(String city) {		put("city", city);	}	public String getSessionid() {		return getString("sessionid");	}	public void setSessionid(String sessionid) {		put("sessionid", sessionid);	}	public Date getVisiteTime() {		return getDate("visiteTime");	}	public void setVisiteTime(Date visiteTime) {		put("visiteTime", visiteTime);	}	public Date getEndTime() {		return getDate("endTime");	}	public void setEndTime(Date endTime) {		put("endTime", endTime);	}	public Integer getReferrerType() {		return getInteger("referrerType");	}	public void setReferrerType(Integer referrerType) {		put("referrerType", referrerType);	}	public String getEngine() {		return getString("engine");	}	public void setEngine(String engine) {		put("engine", engine);	}	public Integer getParentTreeid() {		return getInteger("parentTreeid");	}	public void setParentTreeid(Integer parentTreeid) {		put("parentTreeid", parentTreeid);	}	public Integer getTreeid() {		return getInteger("treeid");	}	public void setTreeid(Integer treeid) {		put("treeid", treeid);	}	public String getSystemType() {		return getString("systemType");	}	public void setSystemType(String systemType) {		put("systemType", systemType);	}	public String getSystemName() {		return getString("systemName");	}	public void setSystemName(String systemName) {		put("systemName", systemName);	}	public String getAppSystem() {		return getString("appSystem");	}	public void setAppSystem(String appSystem) {		put("appSystem", appSystem);	}	public String getDomain() {		return getString("domain");	}	public void setDomain(String domain) {		put("domain", domain);	}	public Integer getPort() {		return getInteger("port");	}	public void setPort(Integer port) {		put("port", port);	}	public String getPageUrl() {		return getString("pageUrl");	}	public void setPageUrl(String pageUrl) {		put("pageUrl", pageUrl);	}	public Long getUrlid() {		return getLong("urlid");	}	public void setUrlid(Long urlid) {		put("urlid", urlid);	}	public Integer getVisiteHour() {		return getInteger("visiteHour");	}	public void setVisiteHour(Integer visiteHour) {		put("visiteHour", visiteHour);	}	public Integer getPdf() {		return getInteger("pdf");	}	public void setPdf(Integer pdf) {		put("pdf", pdf);	}	public Integer getQuicktime() {		return getInteger("quicktime");	}	public void setQuicktime(Integer quicktime) {		put("quicktime", quicktime);	}	public Integer getRealplayer() {		return getInteger("realplayer");	}	public void setRealplayer(Integer realplayer) {		put("realplayer", realplayer);	}	public Integer getWma() {		return getInteger("wma");	}	public void setWma(Integer wma) {		put("wma", wma);	}	public Integer getDirector() {		return getInteger("director");	}	public void setDirector(Integer director) {		put("director", director);	}	public Integer getFlash() {		return getInteger("flash");	}	public void setFlash(Integer flash) {		put("flash", flash);	}	public Integer getGears() {		return getInteger("gears");	}	public void setGears(Integer gears) {		put("gears", gears);	}	public Integer getSilverlight() {		return getInteger("silverlight");	}	public void setSilverlight(Integer silverlight) {		put("silverlight", silverlight);	}	public Integer getJava() {		return getInteger("java");	}	public void setJava(Integer java) {		put("java", java);	}	public String getErrorName() {		return getString("errorName");	}	public void setErrorName(String errorName) {		put("errorName", errorName);	}	public String getErrorContent() {		return getString("errorContent");	}	public void setErrorContent(String errorContent) {		put("errorContent", errorContent);	}	public String getErrorHttpCode() {		return getString("errorHttpCode");	}	public void setErrorHttpCode(String errorHttpCode) {		put("errorHttpCode", errorHttpCode);	}	public String getIsAjax() {		return getString("isAjax");	}	public void setIsAjax(String isAjax) {		put("isAjax", isAjax);	}	public Long getTimeId() {		return getLong("timeId");	}	public void setTimeId(Long timeId) {		put("timeId", timeId);	}	public Long getOsId() {		return getLong("osId");	}	public void setOsId(Long osId) {		put("osId", osId);	}	public Long getBrowserId() {		return getLong("browserId");	}	public void setBrowserId(Long browserId) {		put("browserId", browserId);	}	public Long getFlashVersionId() {		return getLong("flashVersionId");	}	public void setFlashVersionId(Long flashVersionId) {		put("flashVersionId", flashVersionId);	}	public Long getSystemSiteTreeId() {		return getLong("systemSiteTreeId");	}	public void setSystemSiteTreeId(Long systemSiteTreeId) {		put("systemSiteTreeId", systemSiteTreeId);	}	public Long getSystemId() {		return getLong("systemId");	}	public void setSystemId(Long systemId) {		put("systemId", systemId);	}	public Long getErrorDefinitionId() {		return getLong("errorDefinitionId");	}	public void setErrorDefinitionId(Long errorDefinitionId) {		put("errorDefinitionId", errorDefinitionId);	}	public Long getPageUrlId() {		return getLong("pageUrlId");	}	public void setPageUrlId(Long pageUrlId) {		put("pageUrlId", pageUrlId);	}	public Long getMonitorUserId() {		return getLong("monitorUserId");	}	public void setMonitorUserId(Long monitorUserId) {		put("monitorUserId", monitorUserId);	}	public Long getUserAgentId() {		return getLong("userAgentId");	}	public void setUserAgentId(Long userAgentId) {		put("userAgentId", userAgentId);	}	public Integer getWholeTime() {		return getInteger("wholeTime");	}	public void setWholeTime(Integer wholeTime) {		put("wholeTime", wholeTime);	}	public Integer getNetTime() {		return getInteger("netTime");	}	public void setNetTime(Integer netTime) {		put("netTime", netTime);	}	public Integer getActionTime() {		return getInteger("actionTime");	}	public void setActionTime(Integer actionTime) {		put("actionTime", actionTime);	}	public Integer getBodyTime() {		return getInteger("bodyTime");	}	public void setBodyTime(Integer bodyTime) {		put("bodyTime", bodyTime);	}	public Integer getComputeTime() {		return getInteger("computeTime");	}	public void setComputeTime(Integer computeTime) {		put("computeTime", computeTime);	}	public String getLoginName() {		return getString("loginName");	}	public void setLoginName(String loginName) {		put("loginName", loginName);	}	public Integer getIsErrorPage() {		return getInteger("isErrorPage");	}	public void setIsErrorPage(Integer isErrorPage) {		put("isErrorPage", isErrorPage);	}	public Integer getIsLogin() {		return getInteger("isLogin");	}	public void setIsLogin(Integer isLogin) {		put("isLogin", isLogin);	}	public String getErrorPageUrl() {		return getString("errorPageUrl");	}	public void setErrorPageUrl(String errorPageUrl) {		put("errorPageUrl", errorPageUrl);	}	public Integer getEventSeq() {		return getInteger("eventSeq");	}	public void setEventSeq(Integer eventSeq) {		put("eventSeq", eventSeq);	}		//add by onedear 2010-11-29	public String getFlashVersionType(){		return getString("flashVersionType");	}	public void setFlashVersionType(String flashVersionType) {		put("flashVersionType" , flashVersionType);	}	public String getOsType (){		return getString("osType");	}	public void setOsType(String osType ) {		put("osType" , osType);	}		public Integer getErrorCount() {		return getInteger("errorCount");	}	public void setErrorCount(Integer errorCount) {		put("errorCount", errorCount);	}		public Long getErrorPageUrlId() {		return getLong("errorPageUrlId");	}	public void setErrorPageUrlId(Long errorPageUrlId) {		put("errorPageUrlId", errorPageUrlId);	}	public Long getErrorSystemSiteTreeId() {		return getLong("errorSystemSiteTreeId");	}	public void setErrorSystemSiteTreeId(Long errorSystemSiteTreeId) {		put("errorSystemSiteTreeId", errorSystemSiteTreeId);	}		//add by onedear 2011-03-14	public void setRegionId(Long regionId) {		put("regionId" , regionId);	}	public Long getRegionId() {		return getLong("regionId");	}	public void setEntrance(Integer entrance) {		put("entrance" , entrance);	}	public Integer getEntrance() {		return getInteger("entrance");	}		public void setFilterSessionId(String sessionId) {		put("filterSessionId" , sessionId);	}	public String getFilterSessionId() {		return getString("filterSessionId");	}	public void setLastLoginName(String lastLoginName) {		put("lastLoginName" , lastLoginName);	}	public String getLastLoginName() {		return getString("lastLoginName");	}	//add by onedear 2011-04-27	public Long getKeywordId(){		return getLong("keywordId");	}	public void setKeywordId(Long keywordId) {		put("keywordId",keywordId);	}	public Long getSearchEngineId() {		return getLong("searchEngineId");	}	public void setSearchEngineId(Long searchEngineId) {		put("searchEngineId",searchEngineId);	}	public void setSearchSource(String searchSource) {		put("searchSource",searchSource);	}	public String getSearchSource() {		return getString("searchSource");	}	//add by onedear 2011-06-17	public void setPageWidth(Long pageWidth) {		put("pageWidth" , pageWidth);	}	public Long getPageWidth() {		return getLong("pageWidth");	}		public void setPageAlign(String pageAlign) {		put("pageAlign",pageAlign);	}	public String getPageAlign() {		return getString("pageAlign");	}		public void setUrlParams(String urlParams) {		put("urlParams",urlParams);	}	public String getUrlParams() {		return getString("urlParams");	}	public void setRefferUrlParams(String refferUrlParams) {		put("refferUrlParams",refferUrlParams);	}	public String getRefferUrlParams() {		return getString("refferUrlParams");	}	public void setArgumentId(Long argumentId) {		put("argumentId" , argumentId);	}	public Long getArgumentId() {		return getLong("argumentId");	}	public void setReferrerArgumentId(Long referrerArgumentId) {		put("referrerArgumentId" , referrerArgumentId);	}	public Long getReferrerArgumentId() {		return getLong("referrerArgumentId");	}					//////////////////////////////////////////////////	//////////////////                ////////////////	//////////////////////////////////////////////////		private Date getDate(String key) {		return (Date) get(key);	}	private String getString(String key) {		return (String) get(key);	}	private Long getLong(String key) {		return (Long) get(key);	}	private Integer getInteger(String key) {		return (Integer) get(key);	}		//////////////////////////////////////////////////	//////////////////                ////////////////	//////////////////////////////////////////////////		private final HashMap<String, Object> innerMap;		public PageTransactionMessage() {		innerMap = new HashMap<String, Object>();	}		@Override	public void clear() {		innerMap.clear();	}	@Override	public boolean containsKey(Object key) {		return innerMap.containsKey(key);	}	@Override	public boolean containsValue(Object value) {		return innerMap.containsValue(value);	}	@Override	public Set<java.util.Map.Entry<String, Object>> entrySet() {		return innerMap.entrySet();	}	@Override	public Object get(Object key) {		return innerMap.get(key);	}	@Override	public boolean isEmpty() {		return innerMap.isEmpty();	}	@Override	public Set<String> keySet() {		return innerMap.keySet();	}	@Override	public Object put(String key, Object value) {		return innerMap.put(key, value);	}	@Override	public void putAll(Map<? extends String, ? extends Object> m) {		innerMap.putAll(m);	}	@Override	public Object remove(Object key) {		return innerMap.remove(key);	}	@Override	public int size() {		return innerMap.size();	}	@Override	public Collection<Object> values() {		return innerMap.values();	}	    /**     * Returns a shallow copy of this <tt>HashMap</tt> instance: the keys and     * values themselves are not cloned.     *     * @return a shallow copy of this map     */	@Override	protected Object clone() throws CloneNotSupportedException {		return innerMap.clone();	}		@Override	public String toString() {		return innerMap.toString();	}}