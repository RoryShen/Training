package tech.rory.mobilesafe.activity;

import tech.rory.mobilesafe.R;
import tech.rory.mobilesafe.db.dao.AddressDao;
import tech.rory.mobilesafe.utils.ToastUtils;
import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * πÈ Ùµÿ≤È—Ø“≥√Ê
 * 
 * @author Rory
 * 
 */
public class AddressActivity extends Activity {
	private EditText etNumber;
	private TextView tvResult;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_adress);
		etNumber = (EditText) findViewById(R.id.et_phone);
		tvResult = (TextView) findViewById(R.id.tv_result);
	}

	public void query(View view) {
		String number = etNumber.getText().toString().trim();
		if (!TextUtils.isEmpty(number)) {
			String address = AddressDao.getAddress(number);
			tvResult.setText(address);
			// ToastUtils.showToast(this, number);

		}

	}
}
