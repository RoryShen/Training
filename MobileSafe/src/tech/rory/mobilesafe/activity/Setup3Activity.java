package tech.rory.mobilesafe.activity;

import tech.rory.mobilesafe.R;
import tech.rory.mobilesafe.utils.ToastUtils;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class Setup3Activity extends BaseSetupActivity {
	private EditText etphoneEditText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setup3);
		etphoneEditText = (EditText) findViewById(R.id.et_phone);
		
		//��ȡ����İ�ȫ��ϵ��
		String phone=sharedPreferences.getString("safe_phone", "");
		etphoneEditText.setText(phone);
	}

	@Override
	public void showNextPage() {
		// ��ȡ�ı���������������ݣ���ȥ�����ո�
		String etText = etphoneEditText.getText().toString().trim();

		// �жϰ�ȫ��ϵ���Ƿ��Ѿ������ˣ����δ���ã������ð�ȫ��ϵ��
		if (TextUtils.isEmpty(etText)) {
			ToastUtils.showToast(this, "��ȫ��ϵ��δ����");
			return;
		}
		// ����ȫ��ϵ������ʱ����Ѹú��뱣������������
		sharedPreferences.edit().putString("safe_phone", etText).commit();
		// ��ת����һҳ��
		startActivity(new Intent(this, Setup4Activity.class));
		finish();
		overridePendingTransition(R.anim.tran_in, R.anim.tran_out);
	}

	@Override
	public void showPreviousPage() {

		// ��ת����һҳ��
		startActivity(new Intent(this, Setup2Activity.class));
		finish();
		overridePendingTransition(R.anim.prevtran_in, R.anim.prevtran_out);

	}

	public void SelectContacts(View view) {
		// startActivity(new Intent(this, ContactsActivity.class));
		startActivityForResult(new Intent(this, ContactsActivity.class), 1);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// ֻ�е������ΪOKʱ���Ŷ��ı����ֵ���и���
		if (resultCode == RESULT_OK) {
			String phoneString = data.getStringExtra("phone");
			phoneString.replaceAll("-", "").replaceAll(" ", "");
			etphoneEditText.setText(phoneString);

		}
		super.onActivityResult(requestCode, resultCode, data);
	}
}
