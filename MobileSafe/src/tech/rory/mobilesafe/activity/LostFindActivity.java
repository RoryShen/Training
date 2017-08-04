package tech.rory.mobilesafe.activity;

import tech.rory.mobilesafe.R;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

public class LostFindActivity extends Activity {
	private SharedPreferences sharedPreferences;

	@Override
	protected void onCreate(Bundle savedInstancesStateBundle) {

		super.onCreate(savedInstancesStateBundle);

		sharedPreferences = getSharedPreferences("config", MODE_PRIVATE);
		// 判断是否进入过设置向导
		boolean configed = sharedPreferences.getBoolean("configed", false);
		if (configed) {
			setContentView(R.layout.activity_lost_find);
		} else {
			startActivity(new Intent(this, Setup1Activity.class));
			finish();
		}

	}
}
