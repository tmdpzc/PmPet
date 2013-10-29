package com.pmpet.ui;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;

import com.ant.liao.GifView;
import com.pm.commons.BaseActiviy;
import com.pm.commons.UIHelper;
import com.pm.commons.widget.FrameAnimaView;
import com.pmpet.R;

public class PmPetActivity extends BaseActiviy implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pm_pet);
		initGifView();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.pm_pet, menu);
		return true;
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	private void initGifView() {
		GifView gf1 = (GifView) findViewById(R.id.gif1);
		gf1.setGifImage(R.drawable.animation);
		gf1.setOnClickListener(this);
	}

	private void toggleAnima() {
		FrameAnimaView iv = (FrameAnimaView) findViewById(R.id.iv_pet);
		if (sFlag) {
			iv.startFrameAnimation();
		}else {
			iv.stopFrameAnimation();
		}
		sFlag = !sFlag;
	}
	
	private static boolean sFlag = true;

	@Override
	public void onClick(View v) {
		System.out.println("onClick");
	}

	public void onClick_Anima(View v) {
		toggleAnima();
	}

	public void onClick_LF(View v) {
		System.out.println("onClick_LF");
		UIHelper.startActivityDefault(this, BaiduMapActivity.class);
	}

	public void onClick_PZC(View v) {
		System.out.println("onClick_PZC");
		UIHelper.startActivityDefault(this, PmPetMainActivity.class);
	}

}
