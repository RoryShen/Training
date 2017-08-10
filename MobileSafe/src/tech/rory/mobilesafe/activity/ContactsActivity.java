package tech.rory.mobilesafe.activity;

import java.util.ArrayList;
import java.util.HashMap;

import tech.rory.mobilesafe.R;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class ContactsActivity extends Activity {
	private ListView lView;
	private ArrayList<HashMap<String, String>> readcontactsArrayList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contacts);
		lView = (ListView) findViewById(R.id.lv_list);
		readcontactsArrayList = readContacts();
		// System.out.println("AAAA" + readcontactsArrayList);
		lView.setAdapter(new SimpleAdapter(this, readcontactsArrayList,
				R.layout.contacts_list_item, new String[] { "name", "phone" },
				new int[] { R.id.tv_name, R.id.tv_phone }));
		lView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// �õ���ǰItem�ĵ绰����
				String phoneString = readcontactsArrayList.get(position).get(
						"phone");
				Intent intent = new Intent();
				intent.putExtra("phone", phoneString);
				setResult(0, intent);// �����ݷ���intent�з��ظ���һ��ҳ��
				finish();
			}
		});

	}

	private ArrayList<HashMap<String, String>> readContacts() {
		// ���ȣ���raw_contacts�ж�ȡ��ϵ�˵�id(contact_id)
		// ��Σ�����contact_id��data���в�ѯ����Ӧ�ĵ绰�������ϵ������
		// Ȼ�󣬸���mimetype�����֣��ĸ�����ϵ�ˣ��ĸ��ǵ绰����
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		// ָ��contast��uri��ַ
		Uri rawContactsUri = Uri
				.parse("content://com.android.contacts/raw_contacts");
		Uri rawDataUri = Uri.parse("content://com.android.contacts/data");
		// �õ���ѯ��cursor
		Cursor rawCursor = getContentResolver().query(rawContactsUri,
				new String[] { "contact_id" }, null, null, null);

		// ���cursor��Ϊ�գ����ȡ���������
		if (rawCursor != null) {
			while (rawCursor.moveToNext()) {
				// ��ȡ��ϵ�˵�ID��Ϣ
				String contactIdString = rawCursor.getString(0);

				// ����ID��Ϣȥ��ȡ��ϵ�˵��������
				Cursor dataCursor = getContentResolver().query(rawDataUri,
						new String[] { "data1", "mimetype" }, "contact_id=?",
						new String[] { contactIdString }, null);
				if (dataCursor != null) {
					HashMap<String, String> map = new HashMap<String, String>();
					while (dataCursor.moveToNext()) {
						String data1 = dataCursor.getString(0);
						String mimetpye = dataCursor.getString(1);

						if ("vnd.android.cursor.item/phone_v2".equals(mimetpye)) {
							map.put("phone", data1);
						} else if ("vnd.android.cursor.item/name"
								.equals(mimetpye)) {
							map.put("name", data1);
						}
					}
					list.add(map);
					dataCursor.close();
				}

			}

			// ��ȡ��ɺ󣬹ر�cursor
			rawCursor.close();

		}
		return list;

	}
}
