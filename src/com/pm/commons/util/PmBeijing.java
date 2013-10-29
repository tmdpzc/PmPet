package com.pm.commons.util;

import com.pm.commons.api.ApiClient;
import com.pmpet.model.PmInfoExModel;

public class PmBeijing {
	
	public BeijingData mBeijingData = new BeijingData();
	private static PmBeijing mInstance = null;
	
	public static PmBeijing getInstance(){
		if(null == mInstance){
			mInstance = new PmBeijing();
		}
		
		return mInstance;
	}
	
	public void init(final InitCompletedNotify notify){
		new Thread(){
			@Override
			public void run(){
				mInstance.mBeijingData.getData();
				notify.onCompleted();
			}
		}.start();
	}
	
	public interface InitCompletedNotify{
		public void onCompleted();
	}
	
	public class BeijingData{
		public PmInfoExModel mHaiDianInfo = null;
		public PmInfoExModel mChaoYangInfo = null;
		public PmInfoExModel mDongChengInfo = null;
		public PmInfoExModel mXiChengInfo = null;
		public PmInfoExModel mShiJingShanInfo = null;
		public PmInfoExModel mFengTaiInfo = null;
		public PmInfoExModel mTongZhouInfo = null;
		
		public void getData(){
			try {
				mHaiDianInfo = new PmInfoExModel(116.32953f,39.977773f, ApiClient.getPmInfo(116.32953f,39.977773f));
				mChaoYangInfo = new PmInfoExModel(116.458311f,39.968926f, ApiClient.getPmInfo(116.458311f,39.968926f));
				mDongChengInfo = new PmInfoExModel(116.422091f,39.932199f, ApiClient.getPmInfo(116.422091f,39.932199f));
				mXiChengInfo = new PmInfoExModel(116.368049f,39.932199f, ApiClient.getPmInfo(116.368049f,39.932199f));
				mShiJingShanInfo = new PmInfoExModel(116.245017f,39.918919f, ApiClient.getPmInfo(116.245017f,39.918919f));
				mFengTaiInfo = new PmInfoExModel(116.299059f,39.870205f, ApiClient.getPmInfo(116.299059f,39.870205f));
				mTongZhouInfo = new PmInfoExModel(116.656082f,39.922461f, ApiClient.getPmInfo(116.656082f,39.922461f));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
