package tech.rory.mobilesafe.activity;

import tech.rory.mobilesafe.R;
import tech.rory.mobilesafe.db.dao.AddressDao;
import tech.rory.mobilesafe.utils.ToastUtils;
import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * 归属地查询页面
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
		
		//给文本设置监听器
		etNumber.addTextChangedListener(new TextWatcher() {
			

			
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				addressQuery();

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				addressQuery();

			}
		});
	}

	public void query(View view) {
		addressQuery();

	}
	private void addressQuery(){
		String number = etNumber.getText().toString().trim();
		if (!TextUtils.isEmpty(number)) {
			String address = AddressDao.getAddress(number);
			tvResult.setText(address);
			// ToastUtils.showToast(this, number);

		}
		
	}
}
