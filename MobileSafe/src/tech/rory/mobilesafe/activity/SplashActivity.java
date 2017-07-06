package tech.rory.mobilesafe.activity;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.net.ssl.HttpsURLConnection;

import tech.rory.mobilesafe.R;
import tech.rory.mobilesafe.R.id;
import tech.rory.mobilesafe.R.layout;
import tech.rory.mobilesafe.utils.StreamUtils;

import android.os.Bundle;
import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

public class SplashActivity extends Activity {
	private TextView tvVersion;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tvVersion = (TextView) findViewById(R.id.tv_version);
		tvVersion.setText("Version:" + getVersionName());
		checkVersion();
	}

	private String getVersionName() {
		// ��ȡ��������
		PackageManager packageManager = getPackageManager();
		try {
			// ��ȡ�������Ϣ
			PackageInfo packageInfo = packageManager.getPackageInfo(
					getPackageName(), 0);

			// ��ȡ�汾��Ϣ�Ͱ汾����
			int versionCode = packageInfo.versionCode;
			String versionName = packageInfo.versionName;

			// ���ذ汾����
			return versionName;
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * �����������İ汾��Ϣ��
	 */
	private void checkVersion() {
		// �������߳��첽����
		new Thread() {
			@Override
			public void run() {
				try {
					// �����������ַ
					URL url = new URL("http://10.120.1.156:9090/update.json");
					// ����������
					HttpURLConnection openConnection = (HttpURLConnection) url
							.openConnection();
					// �������󷽷�
					openConnection.setRequestMethod("GET");
					// Set max connect time.
					openConnection.setConnectTimeout(5000);
					// Set read time out.
					openConnection.setReadTimeout(5000);
					// Connect server
					openConnection.connect();

					// Get the server resopnse code.
					int responseCode = openConnection.getResponseCode();

					// Check the resopnse code
					if (responseCode == 200) {
						InputStream inputStream = openConnection
								.getInputStream();
						// Read the Stream message.
						String readFormStream = StreamUtils.readFormStream(inputStream);
						Log.e("Network status",readFormStream);
						System.out.println("Network status"+readFormStream);
					}else{
						Log.e("NetWork Status",responseCode+"");
					}
				} catch (MalformedURLException e) {

					e.printStackTrace();
				} catch (IOException e) {

					e.printStackTrace();
				}
			}
		}.start();

	}

}
