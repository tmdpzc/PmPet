package com.pm.commons;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class UIHelper {
	public static void startActivityDefault(Context context,Class<? extends Activity> clazz){
		try {
		Intent intent = new Intent(context, clazz);
		context.startActivity(intent);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void sendAppCrashReport(Context context, String crashReport) {
		makeToast(context, crashReport);
	}
	
	public static void makeToast(Context context, String message){
		Toast.makeText(context, message, Toast.LENGTH_LONG).show();
	}
}
