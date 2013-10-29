package com.pmpet.model;

import com.pm.commons.api.PmInfo;

public class PmInfoExModel extends PmInfo{
	public float mLongitude = 0;
	public float mLatitude  = 0;
	
	public PmInfoExModel(float longitude, float latitude, PmInfo info){
		super(info);
		mLongitude = longitude;
		mLatitude  = latitude;
	}
}
