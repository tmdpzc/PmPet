package com.pmpet.ui;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

import com.ant.liao.GifView;
import com.pm.commons.BaseActiviy;
import com.pm.commons.Commons;
import com.pm.commons.UIHelper;
import com.pm.commons.api.ApiClient;
import com.pm.commons.api.PmInfo;
import com.pm.commons.util.PmPosition;
import com.pm.commons.widget.FrameAnimaView;
import com.pmpet.R;

public class PmPetMainActivity extends BaseActiviy implements OnClickListener{

	static class FormHolder {
		TextView pmIndex;
		TextView pmTip;
		TextView pmCity;
		TextView detail1;
		TextView detail2;
		TextView detail3;
		TextView detail4;
		TextView detail5;

		public void init(Activity a) {
			pmIndex = (TextView) a.findViewById(R.id.tv_pm_index);
			pmTip = (TextView) a.findViewById(R.id.tv_pm_tip);
			pmCity = (TextView) a.findViewById(R.id.tv_pm_city);

			detail1 = (TextView) a.findViewById(R.id.tv_pm_detail1);
			detail2 = (TextView) a.findViewById(R.id.tv_pm_detail2);
			detail3 = (TextView) a.findViewById(R.id.tv_pm_detail3);
			detail4 = (TextView) a.findViewById(R.id.tv_pm_detail4);
			detail5 = (TextView) a.findViewById(R.id.tv_pm_detail5);
		}

		public void update(PmInfo info) {
			pmIndex.setText("" + info.positionAQE);
			pmCity.setText(info.cityName);
		}
	}

	private FormHolder mHolder = new FormHolder();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pm_pet_main);
		mHolder.init(this);
		//initGifView();
	}
	
	
//	private void initGifView(){
//		GifView gf1 = (GifView)findViewById(R.id.gif_pet);
//		gf1.setGifImage(R.drawable.gif_pet_eat);
//		gf1.setOnClickListener(this);
//	}

	@Override
	protected void onResume() {
		super.onResume();
		updatePmInfo();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.pm_pet_main, menu);
		return true;
	}

	public void onClick_Map(View v) {
		if (Commons.IsNetworkAvailable(PmPetMainActivity.this)) {
			UIHelper.startActivityDefault(this, BaiduMapActivity.class);
		}else{
			Toast.makeText(this, 
					PmPetMainActivity.this.getString(R.string.network_unavaiable_tips), Toast.LENGTH_SHORT).show();
		}
	}

	public void onClick_Social(View v) {
		UIHelper.startActivityDefault(this, SocialCircleActivity.class);
	}
	public void onClick_WebInfo(View v) {
		UIHelper.startActivityDefault(this, WebInfoActivity.class);
	}
	
	public void onClick_Control(View v) {
		FrameAnimaView fv = (FrameAnimaView) findViewById(R.id.iv_pet);
		toggleAnima(fv);
		//UIHelper.startActivityDefault(this, SocialCircleActivity.class);
	}
	
	private void toggleAnima(FrameAnimaView fv) {
		if (sFlag) {
			fv.startFrameAnimation();
		}else {
			fv.stopFrameAnimation();
		}
		sFlag = !sFlag;
	}
	
	private static boolean sFlag = true;

	/**
	 * Gif Clicked
	 */
	@Override
	public void onClick(View v) {
		System.out.println("Gif Clicked");
	}
	
	private void updatePmInfo() {
		PmPosition.Position pos = PmPosition.getMyPosition(this);
		ApiClient.requestPmInfo(pos.mLongitude, pos.mLatitude, 
				new ApiClient.ApiCallBack() {
					
					@Override
					public void onSucceed(PmInfo info) {
						mMsgHandler.sendMessage(
								mMsgHandler.obtainMessage(
										MSG_HANDLER_UPDATE_PM_INFO, info) );
					}
					
					@Override
					public void onFailed(Exception e) {
						mMsgHandler.sendEmptyMessage(MSG_HANDLER_UPDATE_PM_INFO_FAILED);
					}
				});
	}
	
	private static final int MSG_HANDLER_UPDATE_PM_INFO = 1;
	private static final int MSG_HANDLER_UPDATE_PM_INFO_FAILED = 2;

	private Handler mMsgHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case MSG_HANDLER_UPDATE_PM_INFO:
				mHolder.update((PmInfo) msg.obj);
				break;
				
			case MSG_HANDLER_UPDATE_PM_INFO_FAILED:
				Toast.makeText(
						PmPetMainActivity.this, 
						R.string.toast_info_update_pm_info_failed, 
						Toast.LENGTH_SHORT).show();
				break;
			}
		}
	};
}
