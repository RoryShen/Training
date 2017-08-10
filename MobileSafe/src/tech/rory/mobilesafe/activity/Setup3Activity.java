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
		
		//读取保存的安全联系人
		String phone=sharedPreferences.getString("safe_phone", "");
		etphoneEditText.setText(phone);
	}

	@Override
	public void showNextPage() {
		// 获取文本框里所输入的内容，并去除掉空格
		String etText = etphoneEditText.getText().toString().trim();

		// 判断安全联系人是否已经设置了，如果未设置，则设置安全联系人
		if (TextUtils.isEmpty(etText)) {
			ToastUtils.showToast(this, "安全联系人未设置");
			return;
		}
		// 当安全联系人无误时，则把该号码保存起来，备用
		sharedPreferences.edit().putString("safe_phone", etText).commit();
		// 跳转到下一页面
		startActivity(new Intent(this, Setup4Activity.class));
		finish();
		overridePendingTransition(R.anim.tran_in, R.anim.tran_out);
	}

	@Override
	public void showPreviousPage() {

		// 跳转到下一页面
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
		// 只有当结果码为OK时，才对文本框得值进行更改
		if (resultCode == RESULT_OK) {
			String phoneString = data.getStringExtra("phone");
			phoneString.replaceAll("-", "").replaceAll(" ", "");
			etphoneEditText.setText(phoneString);

		}
		super.onActivityResult(requestCode, resultCode, data);
	}
}
