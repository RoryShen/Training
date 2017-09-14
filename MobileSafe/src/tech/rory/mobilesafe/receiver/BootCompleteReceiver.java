package tech.rory.mobilesafe.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;

import android.text.TextUtils;

/*
 * 监听开机重启的广播
 */
public class BootCompleteReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// 开机后读取sim卡状态
		SharedPreferences sharedPreferences = context.getSharedPreferences("config", Context.MODE_PRIVATE);
		boolean protect = sharedPreferences.getBoolean("protect", false);

		// 只有防盗保护开启的情况下才需要做如下处理
		if (protect) {
			// 获取绑定的sim卡
			String sim = sharedPreferences.getString("sim", null);
			if (!TextUtils.isEmpty(sim)) {
				// 获取当前的SIM卡
				TelephonyManager tManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
				String currentSIM = tManager.getSimSerialNumber();
				if (sim.equals(currentSIM)) {

				} else {
					String phoneNumber = sharedPreferences.getString("safe_phone", "");
					SmsManager smsManager = SmsManager.getDefault();
					smsManager.sendTextMessage(phoneNumber, null, "SIM Card Changed!", null, null);
					System.out.println("AAA" + "Message Send!");
				}
			}

		}

	}

}
