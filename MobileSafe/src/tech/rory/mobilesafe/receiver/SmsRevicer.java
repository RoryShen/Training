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
		// 把短信内容存放到数组里
		Object[] objects = (Object[]) intent.getExtras().get("pdus");

		// 解析短信内容
		for (Object object : objects) {
			//短信只有140个字节，当短信长度过大时，会分为多条信息进行发送。
			SmsMessage message = SmsMessage.createFromPdu((byte[]) object);
			String originatingAddress = message.getOriginatingAddress();
			String messageBody = message.getMessageBody();
			Log.i("SMSReciver", "Phone Number:" + originatingAddress
					+ ", Message:" + messageBody);
			if ("#*alarm*#".equals(messageBody)) {
				// 创建一个播放器
				MediaPlayer player = MediaPlayer.create(context, R.raw.ylzs);
				// 设置音量的大小
				player.setVolume(1f, 1f);
				// 设置为循环播放
				player.setLooping(true);
				// 启动播放器
				player.start();
				abortBroadcast();// 中断短信传递，系统就收不到短信了
			}
		}
	}

}
