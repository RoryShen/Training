package tech.rory.mobilesafe.activity;

import tech.rory.mobilesafe.R;
import tech.rory.mobilesafe.service.DeviceAdminSampleReceiver;

import org.w3c.dom.Text;

import android.app.Activity;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class LostFindActivity extends Activity {
	private SharedPreferences sharedPreferences;
	private TextView safePhone;
	private boolean isProtect;
	private ImageView ivProtect;
	DevicePolicyManager mDevicePolicyManager;
	ComponentName md;
	TextView tadmin;

	@Override
	protected void onCreate(Bundle savedInstancesStateBundle) {

		super.onCreate(savedInstancesStateBundle);
		sharedPreferences = getSharedPreferences("config", MODE_PRIVATE);

		// 判断是否进入过设置向导
		boolean configed = sharedPreferences.getBoolean("configed", false);
		if (configed) {
			// 设置安全手机
			setContentView(R.layout.activity_lost_find);
			tadmin = (TextView) findViewById(R.id.adminstatus);

			// 更新安全号码
			safePhone = (TextView) findViewById(R.id.tv_safephone);
			String phone = sharedPreferences.getString("safe_phone", "");
			safePhone.setText(phone);

			ivProtect = (ImageView) findViewById(R.id.iv_protect);
			isProtect = sharedPreferences.getBoolean("protect", false);
			if (isProtect) {
				ivProtect.setImageResource(R.drawable.lock);
			} else {
				ivProtect.setImageResource(R.drawable.unlock);
			}
			mDevicePolicyManager = (DevicePolicyManager) this.getSystemService(Context.DEVICE_POLICY_SERVICE);
			md = new ComponentName(this, DeviceAdminSampleReceiver.class);
			sharedPreferences = getSharedPreferences("config", MODE_PRIVATE);
			tadmin.setText("未激活设备管理员权限，锁屏和远程擦除数据功能不可用！激活？");
			
		} else {
			startActivity(new Intent(this, Setup1Activity.class));
			finish();
		}

	}

	/*
	 * 重新进入设置页面
	 */
	public void reEnter(View view) {
		startActivity(new Intent(this, Setup1Activity.class));
		finish();
	}

	public void ActicAdmin(View view) {
		if (mDevicePolicyManager.isAdminActive(md)) {
			tadmin.setText("已经打开设备管理员，一切工作正常");
			
		} else {
			tadmin.setText("未激活设备管理员权限，锁屏和远程擦除数据功能不可用！激活？");
			// Launch the activity to have the user enable our admin.
			Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);

			intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, md);
			intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, "您将激活设备管理员权限！");
			startActivityForResult(intent, RESULT_OK);

		}

	}
}
