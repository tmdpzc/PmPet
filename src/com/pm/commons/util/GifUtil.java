package com.pm.commons.util;

import android.app.Activity;

import com.ant.liao.GifView;

public class GifUtil {
	public static boolean setupGifView(Activity activity, int gifViewId,
			int gifResId) {
		GifView gf = (GifView) activity.findViewById(gifViewId);
		if (gf == null) {
			return false;
		}
		gf.setGifImage(gifResId);
		return true;
	}
}
