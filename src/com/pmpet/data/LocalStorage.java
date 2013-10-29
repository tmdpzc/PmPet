package com.pmpet.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.content.Context;

import com.pmpet.model.FriendsInfo;
import com.pmpet.model.PmAccount;

public class LocalStorage {
	static Random rd = new Random(System.currentTimeMillis());

	/**
	 * 返回虚拟的值
	 * 
	 * @param context
	 * @param account
	 * @return
	 */
	public static List<FriendsInfo> getFriendsInfos(Context context, PmAccount account) {
		if (DUMMY_FRIENDS_INFO == null) {
			DUMMY_FRIENDS_INFO = new ArrayList<FriendsInfo>();
			DUMMY_FRIENDS_INFO.add(new FriendsInfo("YY小怪", randTime(), rd.nextInt(100)));
			DUMMY_FRIENDS_INFO.add(new FriendsInfo("暴走妈", randTime(), rd.nextInt(100)));
			DUMMY_FRIENDS_INFO.add(new FriendsInfo("打老虎", randTime(), rd.nextInt(100)));
			DUMMY_FRIENDS_INFO.add(new FriendsInfo("河马军", randTime(), rd.nextInt(100)));
			DUMMY_FRIENDS_INFO.add(new FriendsInfo("嘻嘻", randTime(), rd.nextInt(100)));
			DUMMY_FRIENDS_INFO.add(new FriendsInfo("今宵酒醒何处", randTime(), rd.nextInt(100)));
			DUMMY_FRIENDS_INFO.add(new FriendsInfo("你妹妹", randTime(), rd.nextInt(100)));
			DUMMY_FRIENDS_INFO.add(new FriendsInfo("天天向上", randTime(), rd.nextInt(100)));
			DUMMY_FRIENDS_INFO.add(new FriendsInfo("大裤衩", randTime(), rd.nextInt(100)));
			DUMMY_FRIENDS_INFO.add(new FriendsInfo("袜子", randTime(), rd.nextInt(100)));
			DUMMY_FRIENDS_INFO.add(new FriendsInfo("心心相印", randTime(), rd.nextInt(100)));
		}
		return DUMMY_FRIENDS_INFO;
	}

	private static long randTime() {
		return System.currentTimeMillis() - rd.nextInt(1000 * 60 * 24 * 30);
	}

	private static List<FriendsInfo> DUMMY_FRIENDS_INFO;
}
