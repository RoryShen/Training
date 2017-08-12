package tech.rory.mobilesafe.receiver;

import tech.rory.mobilesafe.R;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
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
			//����ֻ��140���ֽڣ������ų��ȹ���ʱ�����Ϊ������Ϣ���з��͡�
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
			}
		}
	}

}
