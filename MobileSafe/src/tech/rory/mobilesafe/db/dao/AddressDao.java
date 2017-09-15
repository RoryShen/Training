package tech.rory.mobilesafe.db.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * 归属地查询工具
 * 
 * @author Rory
 * 
 */
public class AddressDao {
	
	// 数据库必须在该目录下
	private static final String PATH = "data/data/tech.rory.mobilesafe/file/address.db";

	public static String getAddress(String number) {
		 String address = "未知号码";
		// 获取数据库对象
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
