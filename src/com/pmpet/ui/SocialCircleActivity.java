package com.pmpet.ui;

import java.util.List;

import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;

import com.pm.commons.AppContext;
import com.pm.commons.BaseActiviy;
import com.pmpet.R;
import com.pmpet.data.LocalStorage;
import com.pmpet.model.FriendsInfo;
import com.pmpet.model.PmAccount;
import com.pmpet.ui.adapter.FreindsAdapter;

public class SocialCircleActivity extends BaseActiviy {

	private ListView mListView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_social_circle);
		_initView();
	}
	@Override
	protected void _initView() {
		mListView = (ListView) findViewById(R.id.lv_friends);
		PmAccount account = ((AppContext)getApplication()).getAccount();
		List<FriendsInfo> data = LocalStorage.getFriendsInfos(this, account);
		FreindsAdapter adapter = new FreindsAdapter(this,data, R.layout.adapter_friend_info);
		mListView.setAdapter(adapter);
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.social_circle, menu);
		return true;
	}

}
