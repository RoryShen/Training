package tech.rory.mobilesafe.db.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import tech.rory.mobilesafe.activity.AddressActivity;
import tech.rory.mobilesafe.utils.ToastUtils;

/**
 * 归属地查询工具
 * 
 * @author Rory
 * 
 */
public class AddressDao {

	// 数据库必须在该目录下(数据只有在这个目录下才能被访问）
	private static final String PATH = "/data/data/tech.rory.mobilesafe/files/address.db";

	public static String getAddress(String number) {
		String address = "未知号码";
		// 获取数据库对象
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
				address = "报警电话";

				break;
			case 4:
				address = "模拟器";
				break;
			case 5:
				address = "客服电话";
				break;
			case 7:
			case 8:
				address = "本地号码";
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
