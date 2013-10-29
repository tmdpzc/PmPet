package com.pm.commons;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;

public class Commons {
	
	public static boolean IsNetworkAvailable(Context context){
		ConnectivityManager conmgr = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if ( conmgr == null )
		{
			return false;
		}

		// 修改解决判断网络时的崩溃
		// mobile 3G Data Network
		NetworkInfo net3g = conmgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		if ( net3g != null){
			State mobile = net3g.getState();// 显示3G网络连接状态
			if (mobile == State.CONNECTED || mobile == State.CONNECTING)
				return true;	
		}

		NetworkInfo netwifi = conmgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		if (netwifi != null){
			State wifi = netwifi.getState(); // wifi
			if (wifi == State.CONNECTED || wifi == State.CONNECTING)
				return true;
		}

		return false;
	}
}
