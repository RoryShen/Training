package tech.rory.mobilesafe.receiver;

import tech.rory.mobilesafe.R;
import tech.rory.mobilesafe.service.DeviceAdminSampleReceiver;
import tech.rory.mobilesafe.service.LocationService;
import android.app.admin.DevicePolicyManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.telephony.SmsMessage;
import android.util.Log;

public class SmsRevicer extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// �Ѷ������ݴ�ŵ�������
		Object[] objects = (Object[]) intent.getExtras().get("pdus");

		// ������������
		for (Object object : objects) {
			// ����ֻ��140���ֽڣ������ų��ȹ���ʱ�����Ϊ������Ϣ���з��͡�
			SmsMessage message = SmsMessage.createFromPdu((byte[]) object);
			String originatingAddress = message.getOriginatingAddress();
			String messageBody = message.getMessageBody();
			Log.i("SMSReciver", "Phone Number:" + originatingAddress
					+ ", Message:" + messageBody);
			if ("#*alarm*#".equals(messageBody)) {
				// ����һ��������
				MediaPlayer player = MediaPlayer.create(context, R.raw.ylzs);
				// ���������Ĵ�С
				player.setVolume(1f, 1f);
				// ����Ϊѭ������
				player.setLooping(true);
				// ����������
				player.start();
				abortBroadcast();// �ж϶��Ŵ��ݣ�ϵͳ���ղ���������
			} else if ("#*location*#".equals(messageBody)) {
				// ����һ������
				context.startService(new Intent(context, LocationService.class));
				SharedPreferences sharedPreferences = context
						.getSharedPreferences("config", context.MODE_PRIVATE);
				String location = sharedPreferences.getString("location",
						"Get Location....");
				// System.out.println("Location:" + location);
				abortBroadcast();// �ж϶��Ŵ��ݣ�ϵͳ���ղ���������
			} else if ("#*lockscreen*#".equals(messageBody)) {
				// �õ�ϵͳ����
				DevicePolicyManager mDevicePolicyManager = (DevicePolicyManager) context
						.getSystemService(Context.DEVICE_POLICY_SERVICE);
				// �õ�һ������
				ComponentName md = new ComponentName(context,
						DeviceAdminSampleReceiver.class);
				if(mDevicePolicyManager.isAdminActive(md)){
					mDevicePolicyManager.lockNow();
					Log.e("lockscreen", "Now screen is locked");
				}else{
					Log.e("lockscreen", "Now screen is lock fail");
					
				}
				
			}

		}
	}

}
