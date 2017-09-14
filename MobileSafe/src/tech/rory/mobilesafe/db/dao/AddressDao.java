package tech.rory.mobilesafe.db.dao;

import android.database.sqlite.SQLiteDatabase;

/**
 * 归属地查询工具
 * 
 * @author Rory
 *
 */
public class AddressDao {
	//数据库必须在该目录下
	private static final String PATH = "data/data/tech.rory.mobilesafe/file";

	public static String getAddress(String number) {
		SQLiteDatabase database = SQLiteDatabase.openDatabase("", null, SQLiteDatabase.OPEN_READONLY);
		//获取数据库对象
		return null;

	}

}
