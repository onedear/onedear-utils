package com.onedear.util.http;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.SingleClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpRequest {

	private static final Logger log = LoggerFactory.getLogger(HttpRequest.class);

	public static String request(URI uri) throws ClientProtocolException, IOException {
		HttpClient httpclient = new DefaultHttpClient();
		HttpGet httpget = new HttpGet(uri);
		setParams(httpclient);
		HttpResponse response = httpclient.execute(httpget);
		return response(uri.toString(), response);
	}
	
	public static List<NameValuePair> getJsonFormparams(Map<String, Object> params) {
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		Iterator<String> ite = params.keySet().iterator();
		while (ite.hasNext()) {
			String next = ite.next();
			formparams.add(new BasicNameValuePair(next, params.get(next).toString()));
		}
		return formparams;
	}

	private static void setParams(HttpClient httpclient) {
		HttpParams params = httpclient.getParams();
		params.setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 5000);
		params.setParameter(CoreConnectionPNames.SO_TIMEOUT, 8000);
	}

	public static String request(String url) throws ClientProtocolException, IOException {
		String ret = null;
		try {
			HttpClient httpclient = new DefaultHttpClient();
			HttpGet httpget = new HttpGet(url);
			setParams(httpclient);
			HttpResponse response = httpclient.execute(httpget);
			ret = response(url, response);
		} catch (Exception e) {
			log.error(e.toString(), e);
		}
		return ret;
	}

	private static String response(String uri, HttpResponse response) throws IOException, UnsupportedEncodingException {
		HttpEntity entity = response.getEntity();
		if (entity != null) {
			StringBuffer contextBuffer = new StringBuffer();
			BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent(), "utf-8"));
			String temp;
			while ((temp = reader.readLine()) != null) {
				contextBuffer.append(temp);
			}
			return contextBuffer.toString();
		}
		return null;
	}

	public static String postRequest(String url, Map<String, String> params) throws ClientProtocolException,
			IOException {
		List<NameValuePair> formparams = getFormparams(params);
		return postRequest(url, formparams);
	}

	public static String postRequest(String url, List<NameValuePair> formparams) throws ClientProtocolException,
			IOException {
		String ret = null;
		try {
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(url);
			setParams(httpclient);
			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, "utf-8");
			httpPost.setEntity(entity);
			HttpResponse response = httpclient.execute(httpPost);
			ret = response(url, response);
		} catch (Exception e) {
			log.error(e.toString(), e);
		}
		return ret;
	}

	public static List<NameValuePair> getFormparams(Map<String, String> params) {
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		Iterator<String> ite = params.keySet().iterator();
		while (ite.hasNext()) {
			String next = ite.next();
			formparams.add(new BasicNameValuePair(next, params.get(next)));
		}
		return formparams;
	}

//	private static HttpClient getSSLHttpClient(){
//		 HostnameVerifier hostnameVerifier = org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER;
//
//	       DefaultHttpClient client = new DefaultHttpClient();
//
//	       SchemeRegistry registry = new SchemeRegistry();
//	       SSLSocketFactory socketFactory = SSLSocketFactory.getSocketFactory();
//	       socketFactory.setHostnameVerifier((X509HostnameVerifier) hostnameVerifier);
//	       registry.register(new Scheme("https", socketFactory, 443));
//	       SingleClientConnManager mgr = new SingleClientConnManager(client.getParams(), registry);
//	       DefaultHttpClient httpClient = new DefaultHttpClient(mgr, client.getParams());
//	       return httpClient;
//	}
	
	public static boolean download(String url, String toPath, String username, String password) 
			throws ClientProtocolException, IOException {
//		HttpClient httpclient = new DefaultHttpClient();
		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(url);
		httpclient.getCredentialsProvider().setCredentials(
				new AuthScope(AuthScope.ANY_HOST, AuthScope.ANY_PORT, AuthScope.ANY_REALM),
                new UsernamePasswordCredentials(username, password));
		HttpResponse response = httpclient.execute(httpGet);
		if ( response.getStatusLine().getStatusCode() == HttpStatus.SC_OK ) {
            // read the content
            BufferedHttpEntity bufferedHttpEntity = new BufferedHttpEntity(response.getEntity());
            
            InputStream is = bufferedHttpEntity.getContent();
            File file = new File(toPath);
            FileOutputStream fos = null;
            BufferedInputStream bis = null;
            try {
            fos = new FileOutputStream(file);
            bis = new BufferedInputStream(is);
            int BUFFER_SIZE = 1024; 
            byte[] buf = new byte[BUFFER_SIZE];   
            int size = 0;
            while ( (size = bis.read(buf)) != -1)     
                fos.write(buf, 0, size);
            fos.flush();
            } finally {
            	try {
	            	fos.close();
	            	bis.close();
            	} catch (Exception e) {}
            }
        } else if (response.getStatusLine().getStatusCode() == HttpStatus.SC_NOT_FOUND){
        	System.err.println("not found : " + url);
        } else {
        	throw new RuntimeException("error response status :" + response.getStatusLine().getStatusCode());
        }
		return false;
	}
	
	public static void main(String[] args) throws UnsupportedEncodingException, IOException {
//		String url = "http://www.baidu.com/img/baidu_sylogo1.gif";
//		String toPath = "C:\\Documents and Settings\\Administrator\\桌面\\ftpdownload\\xxx.gif";
		String url = "http://10.34.132.52:9080/gamehall/gamehall-access_2012120105.log";
		String toPath = "C:\\Documents and Settings\\Administrator\\桌面\\ftpdownload\\xxx.log";
		download(url, toPath, "admin", "ucweb@2012");
		System.out.println("end");
	}
}
