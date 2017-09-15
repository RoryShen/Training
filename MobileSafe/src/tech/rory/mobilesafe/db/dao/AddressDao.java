package tech.rory.mobilesafe.db.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import tech.rory.mobilesafe.activity.AddressActivity;
import tech.rory.mobilesafe.utils.ToastUtils;

/**
 * �����ز�ѯ����
 * 
 * @author Rory
 * 
 */
public class AddressDao {

	// ���ݿ�����ڸ�Ŀ¼��(����ֻ�������Ŀ¼�²��ܱ����ʣ�
	private static final String PATH = "/data/data/tech.rory.mobilesafe/files/address.db";

	public static String getAddress(String number) {
		String address = "δ֪����";
		// ��ȡ���ݿ����
		SQLiteDatabase database = SQLiteDatabase.openDatabase(PATH, null, SQLiteDatabase.OPEN_READONLY);
		if (number.matches("^1[3-8]\\d{9}$")) {
			Cursor rawQuery = database.rawQuery(
					"select location from data2 where id=(select outkey from data1 where id=?)",
					new String[] { number.substring(0, 7) });
			if (rawQuery.moveToNext()) {
				address = rawQuery.getString(0);
				//Log.e("tag", address);
			}
			rawQuery.close();
		} else if (number.matches("^\\d+$")) {
			switch (number.length()) {
			case 3:
				address = "�����绰";

				break;
			case 4:
				address = "ģ����";
				break;
			case 5:
				address = "�ͷ��绰";
				break;
			case 7:
			case 8:
				address = "���غ���";
				break;
			default:
				if (number.startsWith("0") && number.length() > 10) {
					Cursor cursor = database.rawQuery("select location from data2 where area =?",
							new String[] { number.substring(1, 4) });
					if (cursor.moveToNext()) {
						address = cursor.getString(0);
					} else {
						cursor.close();
						cursor = database.rawQuery("select location from data2 where area =?",
								new String[] { number.substring(1, 3) });
						if (cursor.moveToNext()) {
							address = cursor.getString(0);
						}
						cursor.close();
					}
				}
				break;
			}
		}
		database.close();
		return address;

	}
}
