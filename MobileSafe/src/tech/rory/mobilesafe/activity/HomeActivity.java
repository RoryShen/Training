package tech.rory.mobilesafe.activity;

import tech.rory.mobilesafe.R;

import java.lang.annotation.Annotation;

import org.w3c.dom.Text;

import com.lidroid.xutils.view.annotation.event.OnItemClick;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends Activity {
	private GridView gvHome;
	private String[] mItems = new String[] { "�ֻ�����", "ͨѶ��ʿ", "�������", "���̹���", "����ͳ��", "�ֻ�ɱ��", "��������", "�߼�����", "��������" };

	private int[] mPics = new int[] { R.drawable.home_safe, R.drawable.home_callmsgsafe, R.drawable.home_apps,
			R.drawable.home_taskmanager, R.drawable.home_netmanager, R.drawable.home_trojan,
			R.drawable.home_sysoptimize, R.drawable.home_tools, R.drawable.home_settings };
	private SharedPreferences sharedPreferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		gvHome = (GridView) findViewById(R.id.gv_home);
		gvHome.setAdapter(new HomeAdapter());

		// ��Ӧ����¼�
		gvHome.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

				switch (position) {
				case 0:
					// �ֻ�����
					showPasswordDialog();
					break;
				case 8:
					// ��������ʱ��ת�����ý���
					startActivity(new Intent(HomeActivity.this, SettingActivity.class));
					break;
				}
			}
		});

		sharedPreferences = getSharedPreferences("config", MODE_PRIVATE);
	}

	class HomeAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return mItems.length;
		}

		@Override
		public Object getItem(int position) {
			return mItems[position];
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view = View.inflate(HomeActivity.this, R.layout.home_list_item, null);
			ImageView ivItem = (ImageView) view.findViewById(R.id.iv_item);
			TextView tvItem = (TextView) view.findViewById(R.id.tv_item);
			tvItem.setText(mItems[position]);
			ivItem.setImageResource(mPics[position]);
			return view;
		}

	}

	/*
	 * ��ʾ���뵯��
	 */
	protected void showPasswordDialog() {
		// ���жϵ�ǰ�Ƿ��Ѿ����������룬���û�����ù����򵯳��������뵯��
		String savedPassword = sharedPreferences.getString("password", null);
		if (!TextUtils.isEmpty(savedPassword)) {
			showPassordInputDialog();
		} else {
			showPasswordSetDialog();
		}

	}

	private void showPassordInputDialog() {
		// �½�һ������
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		final AlertDialog dialog = builder.create();

		// ����ͼ������һ�������ļ������������ֱ��������ģ�Ҫ����Ĳ����ļ���Ⱥ�鼯
		View view = View.inflate(this, R.layout.dailog_input_password, null);

		// ����ͼ��������dialog�У������ò����ļ���ʾ�Զ������ͼ��
		// dialog.setView(view);
		// ����ͼ��������dialog�У������ò����ļ���ʾ�Զ������ͼ����ͬʱ���ñ߾�
		dialog.setView(view, 0, 0, 0, 0);
		// �õ�

		final EditText etPassword = (EditText) view.findViewById(R.id.et_input_password);
		// Ϊ���������¼�����

		// �ҵ�OK��ť
		Button buttonOK = (Button) view.findViewById(R.id.bt_OK);
		Button buttonCancel = (Button) view.findViewById(R.id.bt_Cancel);

		// �����¼�
		buttonOK.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// �õ��û���������룬

				String passworrd = etPassword.getText().toString();
				if (!TextUtils.isEmpty(passworrd)) {
					String savedpassword = sharedPreferences.getString("password", null);
					if (passworrd.equals(savedpassword)) {
						Toast.makeText(HomeActivity.this, "��½�ɹ���", Toast.LENGTH_SHORT).show();
						dialog.dismiss();
					} else {
						Toast.makeText(HomeActivity.this, "�������", Toast.LENGTH_SHORT).show();
					}
				} else {
					Toast.makeText(HomeActivity.this, "��������ݲ���Ϊ�գ�", Toast.LENGTH_SHORT).show();
				}

			}
		});
		buttonCancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				dialog.dismiss();

			}
		});

		// ��ʾ����
		dialog.show();

	}

	private void showPasswordSetDialog() {
		// �½�һ������
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		final AlertDialog dialog = builder.create();

		// ����ͼ������һ�������ļ������������ֱ��������ģ�Ҫ����Ĳ����ļ���Ⱥ�鼯
		View view = View.inflate(this, R.layout.dailog_set_password, null);

		// ����ͼ��������dialog�У������ò����ļ���ʾ�Զ������ͼ��
		// dialog.setView(view);
		// ����ͼ��������dialog�У������ò����ļ���ʾ�Զ������ͼ����ͬʱ���ñ߾�
		dialog.setView(view, 0, 0, 0, 0);
		// �õ����ε������
		final EditText etPassword = (EditText) view.findViewById(R.id.et_password);
		final EditText etPasswordConfirm = (EditText) view.findViewById(R.id.et_password_confirm);
		// Ϊ���������¼�����

		// �ҵ�OK��ť
		Button buttonOK = (Button) view.findViewById(R.id.bt_OK);
		Button buttonCancel = (Button) view.findViewById(R.id.bt_Cancel);

		// �����¼�
		buttonOK.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// �õ��û���������룬
				String password = etPassword.getText().toString();
				String passworrdConfirm = etPasswordConfirm.getText().toString();

				if (!TextUtils.isEmpty(password) && !passworrdConfirm.isEmpty()) {
					if (password.equals(passworrdConfirm)) {
						Toast.makeText(HomeActivity.this, "��½�ɹ���", Toast.LENGTH_SHORT).show();
						// ������ŵ�sharePreferences�Ȼ���ύ
						sharedPreferences.edit().putString("password", password).commit();
						dialog.dismiss();
					} else {
						Toast.makeText(HomeActivity.this, "�������벻һ�£���", Toast.LENGTH_SHORT).show();
					}
				} else {
					Toast.makeText(HomeActivity.this, "��������ݲ���Ϊ��!", Toast.LENGTH_SHORT).show();
				}
			}
		});
		buttonCancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				dialog.dismiss();

			}
		});

		// ��ʾ����
		dialog.show();

	}
}
