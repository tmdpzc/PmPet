package com.pmpet.ui.commons;

import android.graphics.drawable.Drawable;

import com.baidu.mapapi.map.ItemizedOverlay;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.OverlayItem;
import com.baidu.platform.comapi.basestruct.GeoPoint;

public class ItemizedOverlayEx extends ItemizedOverlay<OverlayItem> {
	
	PmPetPopupClickListener mPopupClickListener = null;
	
    public ItemizedOverlayEx(Drawable mark,MapView mapView, PmPetPopupClickListener l){  
            super(mark,mapView);  
            mPopupClickListener = l;
    }
    
	protected boolean onTap(int index) {
		System.out.println("item onTap: " + index);
		mPopupClickListener.sendWeibo(index);
		return true;
	}

	public boolean onTap(GeoPoint pt, MapView mapView) {
		super.onTap(pt, mapView);
		return false;
	}
}   