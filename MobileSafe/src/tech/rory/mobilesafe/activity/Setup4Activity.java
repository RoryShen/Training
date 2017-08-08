package tech.rory.mobilesafe.activity;

import tech.rory.mobilesafe.R;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

public class Setup4Activity extends BaseSetupActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setup4);

	}

	@Override
	public void showNextPage() {
		// ��ת����һҳ��
		startActivity(new Intent(this, LostFindActivity.class));
		finish();
		overridePendingTransition(R.anim.tran_in, R.anim.tran_out);
		sharedPreferences.edit().putBoolean("configed", true).commit();
	}

	@Override
	public void showPreviousPage() {
		// ��ת����һҳ��
		startActivity(new Intent(this, Setup3Activity.class));
		finish();
		overridePendingTransition(R.anim.prevtran_in, R.anim.prevtran_out);

	}
}
