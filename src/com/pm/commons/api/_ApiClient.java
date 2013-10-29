package com.pm.commons.api;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;

import android.util.Log;

import com.pm.commons.AppContext;
import com.pm.commons.AppException;
import com.pm.commons.URLs;

/**
 * API客户端接口：用于访问网络数据
 * 
 * @author liux (http://my.oschina.net/liux)
 * @version 1.0
 * @created 2012-3-21
 */
public class _ApiClient {

	public static final String UTF_8 = "UTF-8";
	public static final String DESC = "descend";
	public static final String ASC = "ascend";

	private final static int TIMEOUT_CONNECTION = 20000;
	private final static int TIMEOUT_SOCKET = 20000;
	private final static int RETRY_TIME = 3;

	// private static HttpClient getHttpClient() {
	// HttpClient httpClient = new HttpClient();
	// // 设置 HttpClient 接收 Cookie,用与浏览器一样的策略
	// httpClient.getParams().setCookiePolicy(CookiePolicy.BROWSER_COMPATIBILITY);
	// // 设置 默认的超时重试处理策略
	// httpClient.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new
	// DefaultHttpMethodRetryHandler());
	// // 设置 连接超时时间
	// httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(TIMEOUT_CONNECTION);
	// // 设置 读数据超时时间
	// httpClient.getHttpConnectionManager().getParams().setSoTimeout(TIMEOUT_SOCKET);
	// // 设置 字符集
	// httpClient.getParams().setContentCharset(UTF_8);
	// return httpClient;
	// }
	//
	// private static GetMethod getHttpGet(String url, String cookie, String
	// userAgent) {
	// GetMethod httpGet = new GetMethod(url);
	// // 设置 请求超时时间
	// httpGet.getParams().setSoTimeout(TIMEOUT_SOCKET);
	// httpGet.setRequestHeader("Host", URLs.HOST);
	// httpGet.setRequestHeader("Connection","Keep-Alive");
	// httpGet.setRequestHeader("Cookie", cookie);
	// httpGet.setRequestHeader("User-Agent", userAgent);
	// return httpGet;
	// }

	private static String _makeRESTFULApi(float longitude, float latitude) {
		StringBuffer sb = new StringBuffer("http://");
		sb.append(URLs.HOST);
		sb.append("/api/gps_air/longitude/");
		sb.append(longitude);
		sb.append("/latitude/");
		sb.append(latitude);
		sb.append("/type/aqe");
		System.out.println(sb.toString());
		return sb.toString();
	}

	public static PmInfo getPmInfo(AppContext appContext, float longitude,
			float latitude) throws AppException {
		String url = _makeRESTFULApi(longitude, latitude);
		String result = getStringFromUrl(url);
		try {
			return PmInfo.fromJsonStr(result);
		} catch (JSONException e) {
			throw AppException.xml(e);
		}
	}

	// private static String _MakeURL(String p_url, Map<String, Object> params)
	// {
	// StringBuilder url = new StringBuilder(p_url);
	// if(url.indexOf("?")<0)
	// url.append('?');
	//
	// for(String name : params.keySet()){
	// url.append('&');
	// url.append(name);
	// url.append('=');
	// url.append(String.valueOf(params.get(name)));
	// //不做URLEncoder处理
	// //url.append(URLEncoder.encode(String.valueOf(params.get(name)), UTF_8));
	// }
	//
	// return url.toString().replace("?&", "?");
	// }
	//
	// /**
	// * get请求URL
	// * @param url
	// * @throws AppException
	// */
	// private static InputStream http_get(AppContext appContext, String url)
	// throws AppException {
	// return new
	// ByteArrayInputStream(http_get_string(appContext,url).getBytes());
	// }
	//
	public static InputStream getInputStreamFromUrl(String url) {
		InputStream content = null;
		try {
			HttpClient httpclient = new DefaultHttpClient();
			HttpResponse response = httpclient.execute(new HttpGet(url));
			content = response.getEntity().getContent();
		} catch (Exception e) {
			Log.d("[GET REQUEST]", "Network exception", e);
		}
		return content;
	}
	public static String getStringFromUrl(String url) {
		try {
			HttpClient httpclient = new DefaultHttpClient();
			HttpResponse response = httpclient.execute(new HttpGet(url));
			
			BufferedReader rd = new BufferedReader
					  (new InputStreamReader(response.getEntity().getContent()));
			StringBuffer sb = new StringBuffer();
			sb.append(rd.readLine());
			System.out.println(sb.toString());
			return sb.toString();   
		} catch (Exception e) {
			Log.d("[GET REQUEST]", "Network exception", e);
		}
		return "";
	}
	

	//
	// private static String http_get_string(AppContext appContext, String url)
	// throws AppException {
	// //System.out.println("get_url==> "+url);
	// String cookie = getCookie(appContext);
	//
	// HttpClient httpClient = null;
	// GetMethod httpGet = null;
	//
	// String responseBody = "";
	// int time = 0;
	// do{
	// try
	// {
	// httpClient = getHttpClient();
	// httpGet = getHttpGet(url, cookie, getUseragent(appContext));
	// int statusCode = httpClient.executeMethod(httpGet);
	// if (statusCode != HttpStatus.SC_OK) {
	// throw AppException.http(statusCode);
	// }
	// responseBody = httpGet.getResponseBodyAsString();
	// //System.out.println("XMLDATA=====>"+responseBody);
	// break;
	// } catch (HttpException e) {
	// time++;
	// if(time < RETRY_TIME) {
	// try {
	// Thread.sleep(1000);
	// } catch (InterruptedException e1) {}
	// continue;
	// }
	// // 发生致命的异常，可能是协议不对或者返回的内容有问题
	// e.printStackTrace();
	// throw AppException.http(e);
	// } catch (IOException e) {
	// time++;
	// if(time < RETRY_TIME) {
	// try {
	// Thread.sleep(1000);
	// } catch (InterruptedException e1) {}
	// continue;
	// }
	// // 发生网络异常
	// e.printStackTrace();
	// throw AppException.network(e);
	// } finally {
	// // 释放连接
	// httpGet.releaseConnection();
	// httpClient = null;
	// }
	// }while(time < RETRY_TIME);
	//
	// responseBody = responseBody.replaceAll("\\p{Cntrl}", "");
	// return responseBody;
	// }
	//
	// private static String getCookie(AppContext appContext) {
	// return "";
	// }
	//
	// private static String getUseragent(AppContext appContext){
	// return "";
	// }
}
