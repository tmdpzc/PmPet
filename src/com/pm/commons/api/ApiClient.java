package com.pm.commons.api;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;

import android.util.Log;

import com.pm.commons.AppException;
import com.pm.commons.URLs;

/**
 * API客户端接口：用于访问网络数据
 * 
 * @author liux (http://my.oschina.net/liux)
 * @version 1.0
 * @created 2012-3-21
 */
public class ApiClient {

	public static final String UTF_8 = "UTF-8";
	public static final String DESC = "descend";
	public static final String ASC = "ascend";

	private final static int TIMEOUT_CONNECTION = 20000;
	private final static int TIMEOUT_SOCKET = 20000;
	private final static int RETRY_TIME = 3;

	public static interface ApiCallBack{
		public void onSucceed(PmInfo info);
		public void onFailed(Exception e);
	}
	
	private static ExecutorService sExecutor = Executors.newFixedThreadPool(1);

	private static class ApiTask implements Runnable{

		private ApiCallBack mCallBack;
		float longitude;
		float latitude;
		
		public ApiTask(ApiCallBack mCallBack, float longitude, float latitude) {
			super();
			this.mCallBack = mCallBack;
			this.longitude = longitude;
			this.latitude = latitude;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				PmInfo info = getPmInfo(longitude, latitude);
				if (mCallBack != null) {
					mCallBack.onSucceed(info);
				}
			} catch (Exception e) {
				if (mCallBack != null) {
					mCallBack.onFailed(e);
				}
				e.printStackTrace();
			}
		}
		
	} 
	
	public static void requestPmInfo(float longitude, float latitude,ApiCallBack callBack){
		ApiTask task = new ApiTask(callBack, longitude, latitude);
		sExecutor.submit(task);
	}
	
	
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

	public static PmInfo getPmInfo(float longitude,
			float latitude) throws AppException {
		String url = _makeRESTFULApi(longitude, latitude);
		String result = getStringFromUrl(url);
		try {
			return PmInfo.fromJsonStr(result);
		} catch (JSONException e) {
			throw AppException.xml(e);
		}
	}

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

			BufferedReader rd = new BufferedReader(new InputStreamReader(
					response.getEntity().getContent()));
			StringBuffer sb = new StringBuffer();
			sb.append(rd.readLine());
			System.out.println(sb.toString());
			return sb.toString();
		} catch (Exception e) {
			Log.d("[GET REQUEST]", "Network exception", e);
		}
		return "";
	}

}
