package tech.rory.mobilesafe.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;

/*
 * 监听开机重启的广播
 */
public class BootCompleteReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// 开机后读取sim卡状态
		SharedPreferences sharedPreferences = context.getSharedPreferences("config", Context.MODE_PRIVATE);
		boolean protect = sharedPreferences.getBoolean("protect", false);
		
		//只有防盗保护开启的情况下才需要做如下处理
		if (protect) {
			// 获取绑定的sim卡
			String sim = sharedPreferences.getString("sim", null);
			if (!TextUtils.isEmpty(sim)) {
				// 获取当前的SIM卡
				TelephonyManager tManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
				String currentSIM = tManager.getSimSerialNumber();
				if (sim.equals(currentSIM)) {
					// Log.e("SIM342", "手机很安全");
					// Toast.makeText(context, "手机安全！",
					// Toast.LENGTH_SHORT).show();
				} else {
					// Log.e("SIM342", "Sim卡发生变化，发送报警短信");
					// System.out.println("Sim卡发生变化，发送报警短信");
				}
			} else {
				// Log.e("SIM342", "Sim");
			}
		}

	}

}
