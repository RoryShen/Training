package receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.widget.Toast;

/*
 * �������������Ĺ㲥
 */
public class BootCompleteReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// �������ȡsim��״̬
		SharedPreferences sharedPreferences = context.getSharedPreferences("config", Context.MODE_PRIVATE);
		// ��ȡ�󶨵�sim��
		String sim = sharedPreferences.getString("sim", null);
		if (!TextUtils.isEmpty(sim)) {
			// ��ȡ��ǰ��SIM��
			TelephonyManager tManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
			String currentSIM = tManager.getSimSerialNumber();
			if (sim.equals(currentSIM)) {
				System.out.println("�ֻ���ȫ��");
				Toast.makeText(context, "�ֻ���ȫ��", Toast.LENGTH_SHORT).show();
			} else {
				System.out.println("Sim�������仯�����ͱ�������");
			}
		}
	}

}
