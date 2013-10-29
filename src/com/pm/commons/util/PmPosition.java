package com.pm.commons.util;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

public class PmPosition {
	public static class Position {
		public final float mLongitude;
		public final float mLatitude;
		
		public Position(float longitude, float latitude) {
			mLongitude = longitude;
			mLatitude  = latitude;
		}
	}
	
	public static Position getMyPosition(Context ctx) {

		double latitude=0.0;
		double longitude =0.0;

		LocationManager locationManager = (LocationManager)ctx.getSystemService(Context.LOCATION_SERVICE);
		if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
			Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
			if(location != null){
				latitude = location.getLatitude();
				longitude = location.getLongitude();
			}
		}else{
			LocationListener locationListener = new LocationListener() {

				// Provider的状态在可用、暂时不可用和无服务三个状态直接切换时触发此函数
				@Override
				public void onStatusChanged(String provider, int status, Bundle extras) {

				}

				// Provider被enable时触发此函数，比如GPS被打开
				@Override
				public void onProviderEnabled(String provider) {

				}

				// Provider被disable时触发此函数，比如GPS被关闭 
				@Override
				public void onProviderDisabled(String provider) {

				}

				//当坐标改变时触发此函数，如果Provider传进相同的坐标，它就不会被触发 
				@Override
				public void onLocationChanged(Location location) {
					if (location != null) {   
						Log.e("Map", "Location changed : Lat: "  
								+ location.getLatitude() + " Lng: "  
								+ location.getLongitude());   
					}
				}
			};
			locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,1000, 0,locationListener);   
			Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);   
			if(location != null){   
				latitude = location.getLatitude(); //经度   
				longitude = location.getLongitude(); //纬度
			}   
		}
		
//		float[] longitude = new float[] {
//				116.32953f, 116.458311f, 116.422091f, 116.368049f, 116.245017f, 
//				116.299059f, 116.656082f
//		};
//		float[] latitude = new float[] {
//				39.977773f, 39.968926f, 39.932199f, 39.932199f, 39.918919f, 
//				39.870205f, 39.922461f
//		};
		return new Position((float)longitude, (float)latitude);
	}
}
