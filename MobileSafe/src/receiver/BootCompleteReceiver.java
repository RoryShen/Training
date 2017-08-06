package receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.widget.Toast;

/*
 * 监听开机重启的广播
 */
public class BootCompleteReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// 开机后读取sim卡状态
		SharedPreferences sharedPreferences = context.getSharedPreferences("config", Context.MODE_PRIVATE);
		// 获取绑定的sim卡
		String sim = sharedPreferences.getString("sim", null);
		if (!TextUtils.isEmpty(sim)) {
			// 获取当前的SIM卡
			TelephonyManager tManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
			String currentSIM = tManager.getSimSerialNumber();
			if (sim.equals(currentSIM)) {
				System.out.println("手机安全！");
				Toast.makeText(context, "手机安全！", Toast.LENGTH_SHORT).show();
			} else {
				System.out.println("Sim卡发生变化，发送报警短信");
			}
		}
	}

}
