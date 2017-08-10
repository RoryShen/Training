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
		
		//��ȡ��ǰ�ֻ�����״̬
		boolean protect = sharedPreferences.getBoolean("protect", false);
		if (protect) {
			cbprotect.setText("���������Ѿ�������");
			cbprotect.setChecked(true);
		} else {
			cbprotect.setText("��������û�п�����");
			cbprotect.setChecked(false);
		}

		cbprotect.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					cbprotect.setText("���������Ѿ�������");
					sharedPreferences.edit().putBoolean("protect", true).commit();
				} else {
					sharedPreferences.edit().putBoolean("protect", false).commit();
					cbprotect.setText("��������û�п�����");
				}

			}
		});
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
