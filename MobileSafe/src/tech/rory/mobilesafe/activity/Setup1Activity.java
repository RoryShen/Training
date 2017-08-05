package tech.rory.mobilesafe.activity;

import tech.rory.mobilesafe.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Setup1Activity extends BaseSetupActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setup1);
	}

	@Override
	public void showNextPage() {
		// ��ת����һҳ��
		startActivity(new Intent(this, Setup2Activity.class));
		finish();
		overridePendingTransition(R.anim.tran_in, R.anim.tran_out);
	}

	@Override
	public void showPreviousPage() {
		Toast.makeText(this, "�Ѿ��ǵ�һҳ��Ŷ��", Toast.LENGTH_SHORT).show();
	}
}
