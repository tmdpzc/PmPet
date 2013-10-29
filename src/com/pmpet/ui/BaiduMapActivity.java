package com.pmpet.ui;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.map.MapController;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.OverlayItem;
import com.baidu.mapapi.map.PopupOverlay;
import com.baidu.platform.comapi.basestruct.GeoPoint;
import com.pm.commons.BaseActiviy;
import com.pm.commons.util.PmBeijing;
import com.pm.commons.util.PmBeijing.InitCompletedNotify;
import com.pmpet.R;
import com.pmpet.model.PmInfoExModel;
import com.pmpet.ui.commons.ItemizedOverlayEx;
import com.pmpet.ui.commons.MyGeneralListener;
import com.pmpet.ui.commons.PmPetPopupClickListener;

public class BaiduMapActivity extends BaseActiviy {

	private BMapManager mBMapMan = null;
	private MapView mMapView = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		mBMapMan = new BMapManager(getApplication());
		//intel.keystore
		//mBMapMan.init("2cb22bed0e01ad3a7cb707b17282cad0", new MyGeneralListener());
		//debug.keystore
		mBMapMan.init("9E01887a5fd7206e9cd1cfc0d0eaf681", new MyGeneralListener());
		
		setContentView(R.layout.activity_pm_pet_baidu);
		mMapView = (MapView) findViewById(R.id.bmapsView);
		mMapView.setBuiltInZoomControls(true);

		MapController mMapController = mMapView.getController();
		GeoPoint point = new GeoPoint((int) (39.915 * 1E6),
				(int) (116.404 * 1E6));

		mMapController.setCenter(point);
		mMapController.setZoom(12);
	}
	
	private void setPMBeijing(){
		PmBeijing.getInstance().init(new InitCompletedNotify() {
			
			@Override
			public void onCompleted() {
				if(!BaiduMapActivity.this.isFinishing()) {
					runOnUiThread(new Runnable(){
						@Override
						public void run() {
							onPaintPMToMap();
						}
					});
				}
			}
		});
	}
	
	private void onPaintPMToMap(){
		
		ItemizedOverlayEx itemOverlay = new ItemizedOverlayEx(null, mMapView, mPopupClickListener);

		mMapView.getOverlays().clear();  
		mMapView.getOverlays().add(itemOverlay);  
		
		List<OverlayItem> listItem = new ArrayList<OverlayItem>();
		OverlayItem item = null;
		if((item = generalOverlay(PmBeijing.getInstance().mBeijingData.mHaiDianInfo)) != null){
			listItem.add(item);
		}
		
		if((item = generalOverlay(PmBeijing.getInstance().mBeijingData.mChaoYangInfo)) != null){
			listItem.add(item);
		}
		
		if((item = generalOverlay(PmBeijing.getInstance().mBeijingData.mTongZhouInfo)) != null){
			listItem.add(item);
		}
		
		if((item = generalOverlay(PmBeijing.getInstance().mBeijingData.mDongChengInfo)) != null){
			listItem.add(item);
		}
		
		if((item = generalOverlay(PmBeijing.getInstance().mBeijingData.mXiChengInfo)) != null){
			listItem.add(item);
		}
		
		if((item = generalOverlay(PmBeijing.getInstance().mBeijingData.mFengTaiInfo)) != null){
			listItem.add(item);
		}
		
		if((item = generalOverlay(PmBeijing.getInstance().mBeijingData.mShiJingShanInfo)) != null){
			listItem.add(item);
		}
		
		itemOverlay.addItem(listItem);
		mMapView.refresh();
	}
	
	private OverlayItem generalOverlay(PmInfoExModel model){
		if(model == null) {
			return null;
		}
			
		GeoPoint ptTAM = new GeoPoint((int) (model.mLatitude * 1E6), (int) (model.mLongitude * 1E6));
		OverlayItem item = new OverlayItem(ptTAM, Integer.toString(model.positionAQE), "");
		item.setAnchor(OverlayItem.ALING_CENTER);
		
		Bitmap bitmap = BitmapFactory.decodeStream(
				getResources().openRawResource(R.drawable.baidu_map_activity_pm_num));
		Bitmap canvasBitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth()-1, bitmap.getHeight()-1, false);
		Canvas canvas = new Canvas(canvasBitmap);
		Paint paint = new Paint();
		paint.setTextSize(40);
		paint.setARGB(255, 255, 255, 255);
		
		String num = Integer.toString(model.positionAQE);
		Rect rcText = new Rect();
		paint.getTextBounds(num, 0, 1, rcText);
		
		int left = 20;

		canvas.drawText(num, left, bitmap.getHeight() / 2, paint);
		BitmapDrawable drawable = new BitmapDrawable(canvasBitmap);
		if(!bitmap.isRecycled()){
			bitmap.recycle();
		}
		
		item.setMarker(drawable);
		
		return item;
	}
	
	private void paintPm(PmInfoExModel model) {		
		if(model == null){
			return;
		}
		
		System.out.println("paintPm" + model.positionAQE);
		
		PopupOverlay popup = new PopupOverlay(mMapView, mPopupClickListener);

		Bitmap[] bmps = new Bitmap[1];
		try {
			bmps[0] = BitmapFactory.decodeStream(
					getResources().openRawResource(R.drawable.baidu_map_activity_pm_num));
			Bitmap canvasBitmap = Bitmap.createScaledBitmap(bmps[0], bmps[0].getWidth()-1, bmps[0].getHeight()-1, false);
			Canvas canvas = new Canvas(canvasBitmap);
			Paint paint = new Paint();
			paint.setTextSize(40);
			paint.setARGB(255, 255, 255, 255);
			canvas.drawText(Integer.toString(model.positionAQE), bmps[0].getWidth() / 6, bmps[0].getHeight() / 2, paint);
			bmps[0].recycle();
			bmps[0] = canvasBitmap;
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("showPopup");
		
		GeoPoint ptTAM = new GeoPoint((int) (model.mLatitude * 1E6), (int) (model.mLongitude * 1E6));
		popup.showPopup(bmps[0], ptTAM, 32);
		
	}
	
	PmPetPopupClickListener mPopupClickListener = new PmPetPopupClickListener(PmPetPopupClickListener.CHAOYANG_CLICK){
		@Override
		public void sendWeibo(int type){
			Intent intent = new Intent(Intent.ACTION_SEND);
	         intent.setType("text/*");
	         intent.putExtra(Intent.EXTRA_SUBJECT, BaiduMapActivity.this.getString(R.string.weibo_subject));    
	         intent.putExtra(Intent.EXTRA_TEXT, BaiduMapActivity.this.getString(R.string.weibo_text));
	         intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);   
	         BaiduMapActivity.this.startActivity(Intent.createChooser(intent, BaiduMapActivity.this.getString(R.string.weibo_subject)));
		}
	};
	
	
	@Override
	protected void onDestroy() {
		mMapView.destroy();
		if (mBMapMan != null) {
			mBMapMan.destroy();
			mBMapMan = null;
		}
		super.onDestroy();
	}

	@Override
	protected void onPause() {
		mMapView.onPause();
		if (mBMapMan != null) {
			mBMapMan.stop();
		}
		super.onPause();
	}

	@Override
	protected void onResume() {
		mMapView.onResume();
		if (mBMapMan != null) {
			mBMapMan.start();
			
			setPMBeijing();
		}
		super.onResume();
	}
}
