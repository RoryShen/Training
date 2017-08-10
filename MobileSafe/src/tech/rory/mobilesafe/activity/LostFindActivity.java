package tech.rory.mobilesafe.activity;

import tech.rory.mobilesafe.R;
import android.app.Activity;
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

	@Override
	protected void onCreate(Bundle savedInstancesStateBundle) {

		super.onCreate(savedInstancesStateBundle);

		sharedPreferences = getSharedPreferences("config", MODE_PRIVATE);
		// 判断是否进入过设置向导
		boolean configed = sharedPreferences.getBoolean("configed", false);
		if (configed) {
			// 设置安全手机
			setContentView(R.layout.activity_lost_find);
		
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
}
