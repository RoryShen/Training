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
				// 拿到当前Item的电话号码
				String phoneString = readcontactsArrayList.get(position).get(
						"phone");
				Intent intent = new Intent();
				intent.putExtra("phone", phoneString);
				setResult(0, intent);// 将数据放在intent中返回给上一个页面
				finish();
			}
		});

	}

	private ArrayList<HashMap<String, String>> readContacts() {
		// 首先，从raw_contacts中读取联系人的id(contact_id)
		// 其次，根据contact_id从data表中查询出相应的电话号码和联系人名称
		// 然后，根据mimetype来区分，哪个是联系人，哪个是电话号码
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		// 指定contast的uri地址
		Uri rawContactsUri = Uri
				.parse("content://com.android.contacts/raw_contacts");
		Uri rawDataUri = Uri.parse("content://com.android.contacts/data");
		// 拿到查询的cursor
		Cursor rawCursor = getContentResolver().query(rawContactsUri,
				new String[] { "contact_id" }, null, null, null);

		// 如果cursor不为空，则读取里面的内容
		if (rawCursor != null) {
			while (rawCursor.moveToNext()) {
				// 读取联系人的ID信息
				String contactIdString = rawCursor.getString(0);

				// 根据ID信息去获取联系人的相关数据
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

			// 读取完成后，关闭cursor
			rawCursor.close();

		}
		return list;

	}
}
