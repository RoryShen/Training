package tech.rory.mobilesafe.activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import tech.rory.mobilesafe.R;
import tech.rory.mobilesafe.view.SettingItemView;

/**
 * ��������
 * 
 * @author Rory
 *
 */
public class SettingActivity extends Activity {

	private SettingItemView sivUpdate;
	private SharedPreferences mPreferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// �����ֿ���ʾ����
		setContentView(R.layout.activity_setting);
		
		//�õ�SharePreferences.
		mPreferences = getSharedPreferences("config", MODE_PRIVATE);
		
		//�ҵ�siv_update���textview
		sivUpdate = (SettingItemView) findViewById(R.id.siv_update);
		//sivUpdate.setTitle("�Զ���������");
		// �����Զ����µ�Ĭ��ֵ
		boolean autoUpdate = mPreferences.getBoolean("auto_update", true);
		if (autoUpdate) {
			//sivUpdate.setDesc("�Զ������ѿ���");
			sivUpdate.setChecked(true);
		}else{
			//sivUpdate.setDesc("�Զ������ѹر�");
			sivUpdate.setChecked(false);
		}

		sivUpdate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// �жϵ�ǰ��ѡ״̬
				if (sivUpdate.isChecked()) {
					// ����Ϊ����ѡ
					sivUpdate.setChecked(false);
					sivUpdate.setDesc("�Զ������ѹر�");
					
					// ���Զ��������ñ��浽SharePreferences���༭���ύ��
					mPreferences.edit().putBoolean("auto_update", false).commit();
				} else {
					sivUpdate.setChecked(true);
					sivUpdate.setDesc("�Զ������ѿ���");
					mPreferences.edit().putBoolean("auto_update", true).commit();
				}
			}
		});
	}
}
