package tech.rory.mobilesafe.db.dao;

import android.database.sqlite.SQLiteDatabase;

/**
 * �����ز�ѯ����
 * 
 * @author Rory
 *
 */
public class AddressDao {
	//���ݿ�����ڸ�Ŀ¼��
	private static final String PATH = "data/data/tech.rory.mobilesafe/file";

	public static String getAddress(String number) {
		SQLiteDatabase database = SQLiteDatabase.openDatabase("", null, SQLiteDatabase.OPEN_READONLY);
		//��ȡ���ݿ����
		return null;

	}

}
