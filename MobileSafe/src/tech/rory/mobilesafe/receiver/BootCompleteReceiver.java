package tech.rory.mobilesafe.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;

import android.text.TextUtils;

/*
 * �������������Ĺ㲥
 */
public class BootCompleteReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// �������ȡsim��״̬
		SharedPreferences sharedPreferences = context.getSharedPreferences("config", Context.MODE_PRIVATE);
		boolean protect = sharedPreferences.getBoolean("protect", false);

		// ֻ�з�����������������²���Ҫ�����´���
		if (protect) {
			// ��ȡ�󶨵�sim��
			String sim = sharedPreferences.getString("sim", null);
			if (!TextUtils.isEmpty(sim)) {
				// ��ȡ��ǰ��SIM��
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
