package tech.rory.mobilesafe.activity;

import tech.rory.mobilesafe.R;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

public class Setup4Activity extends Activity {
	private SharedPreferences sharedPreferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setup4);
		sharedPreferences = getSharedPreferences("config", MODE_PRIVATE);
	}

	// ��ת����һҳ
	public void next(View view) {
		startActivity(new Intent(this, LostFindActivity.class));
		finish();
		// ��������״̬��������ֵΪtrueʱ������չʾ����ҳ��
		sharedPreferences.edit().putBoolean("configed", true).commit();
		overridePendingTransition(R.anim.tran_in, R.anim.tran_out);

	}

	// ��ת����һҳ
	public void previous(View view) {
		startActivity(new Intent(this, Setup3Activity.class));
		finish();
		overridePendingTransition(R.anim.prevtran_in, R.anim.prevtran_out);
	}
}
