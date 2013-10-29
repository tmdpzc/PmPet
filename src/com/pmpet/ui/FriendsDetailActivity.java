package com.pmpet.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import com.pm.commons.BaseActiviy;
import com.pmpet.R;
import com.pmpet.model.FriendsInfo;

public class FriendsDetailActivity extends BaseActiviy {

	private FriendsInfo mInfo;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_friends_detail);
		mInfo = (FriendsInfo) getIntent().getSerializableExtra(EXTRA_INFO);
		_initView();
	}

	@Override
	protected void _initView() {
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.friends_detail, menu);
		return true;
	}
	
	public void onClick_Swap(View v){
		System.out.println("我要交换");
	}

	
	public static void startWithInfo(Context context,FriendsInfo info){
		Intent intent = new Intent(context, FriendsDetailActivity.class);
		intent.putExtra(EXTRA_INFO, info);
		context.startActivity(intent);
	}
	
	private static final String EXTRA_INFO = "info";
	
}
