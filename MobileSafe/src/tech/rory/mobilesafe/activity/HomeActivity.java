package tech.rory.mobilesafe.activity;

import tech.rory.mobilesafe.R;

import java.lang.annotation.Annotation;

import com.lidroid.xutils.view.annotation.event.OnItemClick;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class HomeActivity extends Activity {
	private GridView gvHome;
	private String[] mItems = new String[] { "手机防盗", "通讯卫士", "软件管理", "进程管理", "流量统计", "手机杀毒", "缓存清理", "高级工具", "设置中心" };

	private int[] mPics = new int[] { R.drawable.home_safe, R.drawable.home_callmsgsafe, R.drawable.home_apps,
			R.drawable.home_taskmanager, R.drawable.home_netmanager, R.drawable.home_trojan,
			R.drawable.home_sysoptimize, R.drawable.home_tools, R.drawable.home_settings };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		gvHome = (GridView) findViewById(R.id.gv_home);
		gvHome.setAdapter(new HomeAdapter());

		// 响应点击事件
		gvHome.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

				switch (position) {
				case 8:
					
				}
			}
		});
	}

	class HomeAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return mItems.length;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return mItems[position];
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view = View.inflate(HomeActivity.this, R.layout.home_list_item, null);
			ImageView ivItem = (ImageView) view.findViewById(R.id.iv_item);
			TextView tvItem = (TextView) view.findViewById(R.id.tv_item);
			tvItem.setText(mItems[position]);
			ivItem.setImageResource(mPics[position]);
			return view;
		}

	}
}
