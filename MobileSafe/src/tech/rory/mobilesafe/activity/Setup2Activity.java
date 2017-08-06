package tech.rory.mobilesafe.activity;

import tech.rory.mobilesafe.R;
import tech.rory.mobilesafe.view.SettingItemView;

import java.lang.annotation.Annotation;

import com.lidroid.xutils.view.annotation.event.OnChildClick;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class Setup2Activity extends BaseSetupActivity {
	private SettingItemView sivSim;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setup2);
		// 给SIM卡设置事件监听
		sivSim = (SettingItemView) findViewById(R.id.siv_sim);
		String sim = sharedPreferences.getString("sim", null);
		if (!TextUtils.isEmpty(sim)) {
			sivSim.setChecked(true);
		} else {
			sivSim.setChecked(false);
		}
		sivSim.setOnClickListener(new OnClickListener() {

			private TelephonyManager tManager;

			@Override
			public void onClick(View v) {
				if (sivSim.isChecked()) {
					sivSim.setChecked(false);
					// 把储存的SIM卡信息移除
					sharedPreferences.edit().remove("sim").commit();
				} else {
					sivSim.setChecked(true);
					// 拿到系统服务
					tManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
					// 拿到SIM卡序列号
					String sinuber = tManager.getSimSerialNumber();
					// Toast.makeText(Setup2Activity.this,sinuber,
					// Toast.LENGTH_SHORT).show();
					// 把sim卡序列号放到shared preferences里
					sharedPreferences.edit().putString("sim", sinuber).commit();
				}

			}
		});
	}

	@Override
	public void showNextPage() {
		// 跳转到下一页面
		startActivity(new Intent(this, Setup3Activity.class));
		finish();
		overridePendingTransition(R.anim.tran_in, R.anim.tran_out);
	}

	@Override
	public void showPreviousPage() {
		// 跳转到下一页面
		startActivity(new Intent(this, Setup1Activity.class));
		finish();
		overridePendingTransition(R.anim.prevtran_in, R.anim.prevtran_out);

	}

}
