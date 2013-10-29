package com.pmpet.ui.commons;

import com.baidu.mapapi.map.PopupClickListener;

public class PmPetPopupClickListener implements PopupClickListener {
	
	public static final int UNKNOW_CLICK = 0;
	public static final int HAIDIAN_CLICK = 1;
	public static final int CHAOYANG_CLICK = 2;
	public static final int DONGCHENG_CLICK = 3;
	public static final int XICHENG_CLICK = 4;
	public static final int SHIJINGSHAN_CLICK = 5;
	public static final int FENGTAI_CLICK = 6;
	public static final int TONGZHOU_CLICK = 7;
	
	private int mType = 0;
	
	public PmPetPopupClickListener(int type){
		mType = type;
	}
	
    public void onClickedPopup(int index) {  
         switch(mType){
         case HAIDIAN_CLICK:
        	 break;
         case CHAOYANG_CLICK:
        	 break;
         case DONGCHENG_CLICK:
        	 break;
         case XICHENG_CLICK:
        	 break;
         case SHIJINGSHAN_CLICK:
        	 break;
         case FENGTAI_CLICK:
        	 break;
         case TONGZHOU_CLICK:
        	 break;
         }
       
         sendWeibo(mType);
    }
    
    public void sendWeibo(int type){
    }
}
