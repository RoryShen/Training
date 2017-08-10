package tech.rory.mobilesafe.activity;

import tech.rory.mobilesafe.R;

import java.sql.RowId;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class Setup4Activity extends BaseSetupActivity {
	private CheckBox cbprotect;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_setup4);
		cbprotect = (CheckBox) findViewById(R.id.cb_protect);
		
		//读取当前手机防盗状态
		boolean protect = sharedPreferences.getBoolean("protect", false);
		if (protect) {
			cbprotect.setText("防盗保护已经开启！");
			cbprotect.setChecked(true);
		} else {
			cbprotect.setText("防盗保护没有开启！");
			cbprotect.setChecked(false);
		}

		cbprotect.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					cbprotect.setText("防盗保护已经开启！");
					sharedPreferences.edit().putBoolean("protect", true).commit();
				} else {
					sharedPreferences.edit().putBoolean("protect", false).commit();
					cbprotect.setText("防盗保护没有开启！");
				}

			}
		});
	}

	@Override
	public void showNextPage() {
		// 跳转到下一页面
		startActivity(new Intent(this, LostFindActivity.class));
		finish();
		overridePendingTransition(R.anim.tran_in, R.anim.tran_out);
		sharedPreferences.edit().putBoolean("configed", true).commit();
	}

	@Override
	public void showPreviousPage() {
		// 跳转到下一页面
		startActivity(new Intent(this, Setup3Activity.class));
		finish();
		overridePendingTransition(R.anim.prevtran_in, R.anim.prevtran_out);

	}
}
