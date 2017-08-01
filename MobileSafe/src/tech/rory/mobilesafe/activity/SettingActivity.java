package tech.rory.mobilesafe.activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import tech.rory.mobilesafe.R;
import tech.rory.mobilesafe.view.SettingItemView;

/**
 * 设置中心
 * 
 * @author Rory
 *
 */
public class SettingActivity extends Activity {

	private SettingItemView sivUpdate;
	private SharedPreferences mPreferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 把文字框显示出来
		setContentView(R.layout.activity_setting);
		
		//得到SharePreferences.
		mPreferences = getSharedPreferences("config", MODE_PRIVATE);
		
		//找到siv_update这个textview
		sivUpdate = (SettingItemView) findViewById(R.id.siv_update);
		//sivUpdate.setTitle("自动更新设置");
		// 设置自动更新的默认值
		boolean autoUpdate = mPreferences.getBoolean("auto_update", true);
		if (autoUpdate) {
			//sivUpdate.setDesc("自动更新已开启");
			sivUpdate.setChecked(true);
		}else{
			//sivUpdate.setDesc("自动更新已关闭");
			sivUpdate.setChecked(false);
		}

		sivUpdate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// 判断当前勾选状态
				if (sivUpdate.isChecked()) {
					// 设置为不勾选
					sivUpdate.setChecked(false);
					sivUpdate.setDesc("自动更新已关闭");
					
					// 把自动更新配置保存到SharePreferences（编辑并提交）
					mPreferences.edit().putBoolean("auto_update", false).commit();
				} else {
					sivUpdate.setChecked(true);
					sivUpdate.setDesc("自动更新已开启");
					mPreferences.edit().putBoolean("auto_update", true).commit();
				}
			}
		});
	}
}
