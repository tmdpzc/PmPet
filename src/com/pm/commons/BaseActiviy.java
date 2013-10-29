package com.pm.commons;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;

public class BaseActiviy extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		AppManager.getAppManager().addActivity(this);
	}
	protected void _initView() {
	}

	@Override
	protected void onStart() {
		super.onStart();
		log("onStart");
	}

	@Override
	protected void onStop() {
		super.onStop();
		log("onStop");
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		AppManager.getAppManager().finishActivity(this);
		log("onDestory");
	}

	private void log(String message) {
		if (DEBUG) {
			Log.v("TrackActiivy", ":" + message + ": " + this.getClass().getSimpleName());
		}
	}

	public void onBackButtonClicked(View v) {
		onBackPressed();
	}

	private static final boolean DEBUG = true;
}
