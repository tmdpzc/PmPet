package com.pmpet.ui;

import android.os.Bundle;
import android.view.Menu;
import android.webkit.WebView;

import com.pm.commons.BaseActiviy;
import com.pmpet.R;

public class WebInfoActivity extends BaseActiviy {
	
	private WebView mWebView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_web_info);
		_initView();
	}
	
	@Override
	protected void _initView() {
		mWebView = (WebView)findViewById(R.id.wv_info);
		mWebView.setBackgroundColor(0);
		mWebView.setBackgroundResource(R.drawable.bg_activity);
		mWebView.getSettings().setJavaScriptEnabled(false);
		mWebView.loadUrl("file:///android_asset/pm_info.html");
	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.web_info, menu);
		return true;
	}

}
