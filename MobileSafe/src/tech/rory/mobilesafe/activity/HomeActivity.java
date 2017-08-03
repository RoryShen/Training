package tech.rory.mobilesafe.activity;

import tech.rory.mobilesafe.R;

import java.lang.annotation.Annotation;

import org.w3c.dom.Text;

import com.lidroid.xutils.view.annotation.event.OnItemClick;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends Activity {
	private GridView gvHome;
	private String[] mItems = new String[] { "手机防盗", "通讯卫士", "软件管理", "进程管理", "流量统计", "手机杀毒", "缓存清理", "高级工具", "设置中心" };

	private int[] mPics = new int[] { R.drawable.home_safe, R.drawable.home_callmsgsafe, R.drawable.home_apps,
			R.drawable.home_taskmanager, R.drawable.home_netmanager, R.drawable.home_trojan,
			R.drawable.home_sysoptimize, R.drawable.home_tools, R.drawable.home_settings };
	private SharedPreferences sharedPreferences;

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
				case 0:
					// 手机防盗
					showPasswordDialog();
					break;
				case 8:
					// 满足条件时跳转到设置界面
					startActivity(new Intent(HomeActivity.this, SettingActivity.class));
					break;
				}
			}
		});

		sharedPreferences = getSharedPreferences("config", MODE_PRIVATE);
	}

	class HomeAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return mItems.length;
		}

		@Override
		public Object getItem(int position) {
			return mItems[position];
		}

		@Override
		public long getItemId(int position) {
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

	/*
	 * 显示密码弹窗
	 */
	protected void showPasswordDialog() {
		// 先判断当前是否已经设置了密码，如果没有设置过，则弹出设置密码弹窗
		String savedPassword = sharedPreferences.getString("password", null);
		if (!TextUtils.isEmpty(savedPassword)) {
			showPassordInputDialog();
		} else {
			showPasswordSetDialog();
		}

	}

	private void showPassordInputDialog() {
		// 新建一个弹窗
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		final AlertDialog dialog = builder.create();

		// 往视图中塞入一个布局文件，三个参数分别是上下文，要塞入的布局文件，群组集
		View view = View.inflate(this, R.layout.dailog_input_password, null);

		// 把视图内容塞到dialog中（即，让布局文件显示自定义的视图）
		// dialog.setView(view);
		// 把视图内容塞到dialog中（即，让布局文件显示自定义的视图），同时设置边距
		dialog.setView(view, 0, 0, 0, 0);
		// 拿到

		final EditText etPassword = (EditText) view.findViewById(R.id.et_input_password);
		// 为弹框设置事件监听

		// 找到OK按钮
		Button buttonOK = (Button) view.findViewById(R.id.bt_OK);
		Button buttonCancel = (Button) view.findViewById(R.id.bt_Cancel);

		// 设置事件
		buttonOK.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// 拿到用户输入的密码，

				String passworrd = etPassword.getText().toString();
				if (!TextUtils.isEmpty(passworrd)) {
					String savedpassword = sharedPreferences.getString("password", null);
					if (passworrd.equals(savedpassword)) {
						Toast.makeText(HomeActivity.this, "登陆成功！", Toast.LENGTH_SHORT).show();
						dialog.dismiss();
					} else {
						Toast.makeText(HomeActivity.this, "密码错误！", Toast.LENGTH_SHORT).show();
					}
				} else {
					Toast.makeText(HomeActivity.this, "输入框内容不能为空！", Toast.LENGTH_SHORT).show();
				}

			}
		});
		buttonCancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				dialog.dismiss();

			}
		});

		// 显示弹框
		dialog.show();

	}

	private void showPasswordSetDialog() {
		// 新建一个弹窗
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		final AlertDialog dialog = builder.create();

		// 往视图中塞入一个布局文件，三个参数分别是上下文，要塞入的布局文件，群组集
		View view = View.inflate(this, R.layout.dailog_set_password, null);

		// 把视图内容塞到dialog中（即，让布局文件显示自定义的视图）
		// dialog.setView(view);
		// 把视图内容塞到dialog中（即，让布局文件显示自定义的视图），同时设置边距
		dialog.setView(view, 0, 0, 0, 0);
		// 拿到两次的密码框
		final EditText etPassword = (EditText) view.findViewById(R.id.et_password);
		final EditText etPasswordConfirm = (EditText) view.findViewById(R.id.et_password_confirm);
		// 为弹框设置事件监听

		// 找到OK按钮
		Button buttonOK = (Button) view.findViewById(R.id.bt_OK);
		Button buttonCancel = (Button) view.findViewById(R.id.bt_Cancel);

		// 设置事件
		buttonOK.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// 拿到用户输入的密码，
				String password = etPassword.getText().toString();
				String passworrdConfirm = etPasswordConfirm.getText().toString();

				if (!TextUtils.isEmpty(password) && !passworrdConfirm.isEmpty()) {
					if (password.equals(passworrdConfirm)) {
						Toast.makeText(HomeActivity.this, "登陆成功！", Toast.LENGTH_SHORT).show();
						// 把密码放到sharePreferences里，然后提交
						sharedPreferences.edit().putString("password", password).commit();
						dialog.dismiss();
					} else {
						Toast.makeText(HomeActivity.this, "两次密码不一致！！", Toast.LENGTH_SHORT).show();
					}
				} else {
					Toast.makeText(HomeActivity.this, "输入框内容不能为空!", Toast.LENGTH_SHORT).show();
				}
			}
		});
		buttonCancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				dialog.dismiss();

			}
		});

		// 显示弹框
		dialog.show();

	}
}
