package tech.rory.mobilesafe.activity;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.net.ssl.HttpsURLConnection;

import org.json.JSONException;
import org.json.JSONObject;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;

import tech.rory.mobilesafe.R;
import tech.rory.mobilesafe.R.id;
import tech.rory.mobilesafe.R.layout;
import tech.rory.mobilesafe.utils.StreamUtils;

import android.os.Bundle;

import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager.OnActivityResultListener;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AliasActivity;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class SplashActivity extends Activity {

	protected static final int CODE_UPDATE_DIALOG = 0;
	protected static final int CODE_URL_ERROR = 1;
	protected static final int CODE_NET_ERROR = 2;
	protected static final int CODE_JSON_ERROR = 3;
	protected static final int CODE_ENTER_HOME = 4;
	private TextView tvVersion;
	private TextView tvProgressTextView;
	// private String urlAddress = "http://10.120.1.156:9090/update.json";
	private String urlAddress = "http://192.168.31.212:8080/update.json";

	// ---bleow info from server.
	// Version name
	private String mVersionName;
	// Version Code
	private int mVersionCode;
	private String mDesc;
	private String mDownloadURL;
	private int sleepTime = 5000;
	private Handler mHanler = new Handler() {

		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case CODE_UPDATE_DIALOG:
				Log.d("VVV", "aaa");

				showUpdateDailog();

				break;
			case CODE_URL_ERROR:
				Toast.makeText(SplashActivity.this, "URL error", Toast.LENGTH_LONG).show();
				enterHome();

				break;
			case CODE_NET_ERROR:
				Toast.makeText(SplashActivity.this, "Network error", Toast.LENGTH_LONG).show();
				enterHome();
				break;
			case CODE_JSON_ERROR:
				Toast.makeText(SplashActivity.this, "Read data error", Toast.LENGTH_LONG).show();
				enterHome();
				break;
			case CODE_ENTER_HOME:
				enterHome();
				break;
			}
		}

	};
	private SharedPreferences mPreferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		tvVersion = (TextView) findViewById(R.id.tv_version);
		tvVersion.setText("Version:" + getVersionName());
		tvProgressTextView = (TextView) findViewById(R.id.tv_progress);

		mPreferences = getSharedPreferences("config", MODE_PRIVATE);
		boolean autoUpdate = mPreferences.getBoolean("auto_update", true);
		if (autoUpdate) {
			checkVersion();
		} else {
			// �ӳ�5�����һ������Ϣ��ȷ��app������ҳ��
			mHanler.sendEmptyMessageDelayed(CODE_ENTER_HOME, sleepTime);
		}
	}

	private String getVersionName() {
		// ��ȡ��������
		PackageManager packageManager = getPackageManager();
		try {
			// ��ȡ�������Ϣ

			PackageInfo packageInfo = packageManager.getPackageInfo(getPackageName(), 0);

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
	 * 
	 * Get version code.
	 * 
	 * @return
	 */
	private int getVersionCode() {
		// ��ȡ��������
		PackageManager packageManager = getPackageManager();
		try {
			// ��ȡ�������Ϣ
			PackageInfo packageInfo = packageManager.getPackageInfo(getPackageName(), 0);

			// ��ȡ�汾��Ϣ�Ͱ汾����
			int versionCode = packageInfo.versionCode;
			String versionName = packageInfo.versionName;

			// ���ذ汾����
			return versionCode;
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}

	/**
	 * 
	 * �����������İ汾��Ϣ��
	 */
	private void checkVersion() {
		final long startTime = System.currentTimeMillis();
		// �������߳��첽����
		new Thread() {

			private HttpURLConnection openConnection;

			@Override
			public void run() {
				// Get message
				Message message = Message.obtain();
				try {
					// �����������ַ
					URL url = new URL(urlAddress);
					openConnection = (HttpURLConnection) url.openConnection();

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

						InputStream inputStream = openConnection.getInputStream();
						// Read the Stream message.
						String readFormStream = StreamUtils.readFormStream(inputStream);
						Log.i("Network status", readFormStream);
						// System.out.println("Network status" +
						// readFormStream);

						// porcess Json
						JSONObject jsonObject = new JSONObject(readFormStream);

						mVersionName = jsonObject.getString("versionName");
						// Version Code
						mVersionCode = jsonObject.getInt("versionCode");

						// Description
						mDesc = jsonObject.getString("description");
						mDownloadURL = jsonObject.getString("downloadUrl");
						Log.i("Net", mDesc);

						// Check the update available.
						if (mVersionCode > getVersionCode()) {
							/*
							 * ����������ϵĴ���ȱ��ص��£���ô����Ҫ����������
							 */
							message.what = CODE_UPDATE_DIALOG;
							// showUpdateDailog();

						} else {
							message.what = CODE_ENTER_HOME;
							Log.e("NetWork Status", responseCode + "");
						}
					}
				} catch (MalformedURLException e) {
					message.what = CODE_URL_ERROR;

					e.printStackTrace();
				} catch (IOException e) {
					message.what = CODE_NET_ERROR;

					e.printStackTrace();
				} catch (JSONException e) {
					message.what = CODE_JSON_ERROR;

					e.printStackTrace();

				} finally {
					long endTime = System.currentTimeMillis();
					long timeUse = endTime - startTime;

					// wait 2s for show splash page.
					if (timeUse < sleepTime) {
						try {
							Thread.sleep(sleepTime - timeUse);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}

					}
					System.out.println("AAAA" + timeUse);
					mHanler.sendMessage(message);
					if (openConnection != null) {
						openConnection.disconnect();
					}

				}
			}
		}.start();

	}

	/**
	 * Update dialog.
	 */
	protected void showUpdateDailog() {
		// Create builder
		AlertDialog.Builder builder = new AlertDialog.Builder(this);

		builder.setTitle("Update" + mVersionName);

		// Set the message.
		builder.setMessage(mDesc);

		// �������ѡ��
		builder.setPositiveButton("Update NoW", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				Log.i("Update?", "Update");
				download();

			}
		});

		// �����Ժ�ѡ��
		builder.setNegativeButton("Later", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				enterHome();

			}

		});

		// ��ȡ���������д���
		builder.setOnCancelListener(new OnCancelListener() {

			@Override
			public void onCancel(DialogInterface dialog) {
				enterHome();
			}

		});

		builder.show();

	}

	/**
	 * ����������
	 * 
	 */
	private void enterHome() {
		Intent intent = new Intent(this, HomeActivity.class);
		startActivity(intent);
		finish();
	}

	/**
	 * Download
	 * 
	 */
	protected void download() {
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			tvProgressTextView.setVisibility(View.VISIBLE);
			final String target = Environment.getExternalStorageDirectory() + "/update.apk";
			HttpUtils uHttpUtils = new HttpUtils();
			uHttpUtils.download(mDownloadURL, target, new RequestCallBack<File>() {

				@Override
				public void onSuccess(ResponseInfo<File> arg0) {
					Toast.makeText(SplashActivity.this, "Donwloading Success! See:" + target, Toast.LENGTH_LONG).show();
					// Switch to System App install page.

					Intent intent = new Intent(Intent.ACTION_VIEW);

					intent.addCategory(Intent.CATEGORY_DEFAULT);
					intent.setDataAndType(Uri.fromFile(arg0.result), "application/vnd.android.package-archive");
					// startActivity(intent);

					// get the result form activity.by call onActivityResult();
					startActivityForResult(intent, 0);

				}

				@Override
				public void onFailure(HttpException arg0, String arg1) {
					Toast.makeText(SplashActivity.this, "Donwloading Fail!" + mDownloadURL, Toast.LENGTH_LONG).show();
				}

				@Override
				public void onLoading(long total, long current, boolean isUploading) {

					super.onLoading(total, current, isUploading);
					tvProgressTextView.setText("Download Process: " + current * 100 / total + "%");
					Log.i("Download Process", current + "/" + total);
				}

			});
		} else {
			Toast.makeText(SplashActivity.this, "Please insert sd card", Toast.LENGTH_LONG).show();
		}

	}

	// Get the result form activity
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		enterHome();
	}
}
