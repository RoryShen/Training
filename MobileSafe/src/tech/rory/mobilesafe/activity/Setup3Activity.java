package tech.rory.mobilesafe.activity;

import tech.rory.mobilesafe.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.view.View;
import android.widget.EditText;

public class Setup3Activity extends BaseSetupActivity {
	private EditText etphoneEditText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setup3);
		etphoneEditText = (EditText) findViewById(R.id.et_phone);
	}

	@Override
	public void showNextPage() {
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
		startActivityForResult(new Intent(this, ContactsActivity.class), 0);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		super.onActivityResult(requestCode, resultCode, data);
		
		String phoneString = data.getStringExtra("phone");
		phoneString.replaceAll("-","").replaceAll(" ", "");
		etphoneEditText.setText(phoneString);
	}
}
