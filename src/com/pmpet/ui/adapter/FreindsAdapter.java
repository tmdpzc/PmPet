package com.pmpet.ui.adapter;

import java.util.List;

import com.pm.commons.api.PmInfo;
import com.pmpet.R;
import com.pmpet.model.FriendsInfo;
import com.pmpet.ui.FriendsDetailActivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class FreindsAdapter extends BaseAdapter implements OnClickListener{

	private Context context;// 运行上下文
	private List<FriendsInfo> listItems;// 数据集合
	private LayoutInflater listContainer;// 视图容器
	private int itemViewResource;// 自定义项视图源

	static class ListItemView { // 自定义控件集合
		public TextView name;
		public TextView level;
		public TextView date;
		public ImageView icon;
		public ImageButton next;
	}

	/**
	 * 实例化Adapter
	 * 
	 * @param context
	 * @param data
	 * @param resource
	 */
	public FreindsAdapter(Context context, List<FriendsInfo> data, int resource) {
		this.context = context;
		this.listContainer = LayoutInflater.from(context); // 创建视图容器并设置上下文
		this.itemViewResource = resource;
		this.listItems = data;
		
	}

	@Override
	public int getCount() {
		return listItems.size();
	}

	@Override
	public Object getItem(int position) {
		return listItems.get(position);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		System.out.println("Positon:"+ position);
		// 自定义视图
		ListItemView listItemView = null;

		if (convertView == null) {
			// 获取list_item布局文件的视图
			convertView = listContainer.inflate(this.itemViewResource, null);

			listItemView = new ListItemView();
			// 获取控件对象
			listItemView.name = (TextView) convertView.findViewById(R.id.tv_name);
			listItemView.date = (TextView) convertView.findViewById(R.id.tv_date);
			// listItemView.level = (TextView)
			// convertView.findViewById(R.id.tv_date); //没有显示Level
			listItemView.icon = (ImageView) convertView.findViewById(R.id.iv_icon);
			listItemView.icon.setImageResource(R.drawable.ic_mother);
			listItemView.next = (ImageButton) convertView.findViewById(R.id.bt_next);
			listItemView.next.setOnClickListener(this);
			// 设置控件集到convertView
			convertView.setTag(listItemView);
		} else {
			listItemView = (ListItemView) convertView.getTag();
		}

		// 设置文字和图片
		FriendsInfo info = listItems.get(position);

		listItemView.name.setText(info.name);
		// listItemView.level.setText("" + info.petLevel);
		listItemView.date.setText(info.getDateString());
		listItemView.next.setTag(info);// 设置隐藏参数(实体类)

		return convertView;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void onClick(View v) {
		FriendsInfo info = (FriendsInfo) v.getTag();
		FriendsDetailActivity.startWithInfo(context, info);
	}

}
