package com.pmpet.ui.commons;

import android.util.Log;

import com.baidu.mapapi.MKGeneralListener;

public class MyGeneralListener implements MKGeneralListener {

	@Override
	public void onGetNetworkState(int iError) {
		Log.d("MyGeneralListener", "onGetPermissionState error is "+ iError);
	}

	@Override
	public void onGetPermissionState(int iError) {
		Log.d("MyGeneralListener", "onGetPermissionState error is "+ iError);
	}
	
}