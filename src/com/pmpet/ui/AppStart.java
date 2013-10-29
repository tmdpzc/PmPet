package com.pmpet.ui;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

import com.pm.commons.AppContext;
import com.pm.commons.BaseActiviy;
import com.pm.commons.UIHelper;
import com.pmpet.R;

/**
 * 应用程序启动类：显示欢迎界面并跳转到主界面
 * 
 * @author liux (http://my.oschina.net/liux)
 * @version 1.0
 * @created 2012-3-21
 */
public class AppStart extends BaseActiviy {

	private View mRootView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mRootView = View.inflate(this, R.layout.activity_start, null);
		setContentView(mRootView);

		// 兼容低版本cookie（1.5版本以下，包括1.5.0,1.5.1）
		AppContext appContext = (AppContext) getApplication();

	}

	@Override
	protected void onResume() {
		super.onResume();
		// 渐变展示启动屏
		AlphaAnimation aa = new AlphaAnimation(0.3f, 1.0f);
		aa.setDuration(2000);
		mRootView.startAnimation(aa);
		aa.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationEnd(Animation arg0) {
				redirectTo();
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
			}

			@Override
			public void onAnimationStart(Animation animation) {
			}

		});
	}

	/**
	 * 跳转到...
	 */
	private void redirectTo() {
		System.out.println("Redirect to");
		UIHelper.startActivityDefault(AppStart.this, PmPetMainActivity.class);
		AppStart.this.finish();
	}
}