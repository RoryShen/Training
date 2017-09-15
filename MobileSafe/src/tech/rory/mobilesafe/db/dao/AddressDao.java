package tech.rory.mobilesafe.db.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * �����ز�ѯ����
 * 
 * @author Rory
 * 
 */
public class AddressDao {
	
	// ���ݿ�����ڸ�Ŀ¼��
	private static final String PATH = "data/data/tech.rory.mobilesafe/file/address.db";

	public static String getAddress(String number) {
		 String address = "δ֪����";
		// ��ȡ���ݿ����
		SQLiteDatabase database = SQLiteDatabase.openDatabase(PATH, null,
				SQLiteDatabase.OPEN_READONLY);

		Cursor rawQuery = database
				.rawQuery(
						"select location form data2 where id=(select outkey form data1 where id=?",
						new String[] { number.substring(0, 7) });
		if (rawQuery.moveToNext()) {
			address = rawQuery.getString(0);
		}
		return address;

	}
}
