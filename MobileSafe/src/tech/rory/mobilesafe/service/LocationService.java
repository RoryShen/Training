package tech.rory.mobilesafe.service;

import tech.rory.mobilesafe.activity.HomeActivity;
import tech.rory.mobilesafe.utils.ToastUtils;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

public class LocationService extends Service {
	private LocationManager lm;
	private MyLocationListener listener;
	private SharedPreferences sharedPreferences;

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		sharedPreferences = getSharedPreferences("config", MODE_PRIVATE);
		lm = (LocationManager) getSystemService(LOCATION_SERVICE);

		// ��ϵͳ˵����ʲô����ѵ�λ���ṩ��,������ô�apiʱ��һ��Ҫ�����й���GPS��Ȩ�ޣ�����᷵�ؿջ���Ȩ������
		Criteria criteria = new Criteria();
		// �����Ƿ������ѣ���3g�����¶�λ��
		criteria.setCostAllowed(true);
		// ���þ���״̬
		criteria.setAccuracy(Criteria.ACCURACY_FINE);
		// ����һ�����õ���ѵ�λ���ṩ��,

		String bestLocation = lm.getBestProvider(criteria, true);
		/*
		 * if(bestLocation.equals("")){ Log.e("LCS", "BestLocation is empty");
		 * }else{ Log.e("LCS", "BestLocation is not empty"); }
		 */
		// System.out.println(bestLocation);
		// ����1��λ���ṩ���ͣ�����2����̸���ʱ�䣬����3����̸��¾��룬����4��listener
		listener = new MyLocationListener();
		lm.requestLocationUpdates(bestLocation, 60, 0, listener);

	}

	class MyLocationListener implements LocationListener {
		// λ�÷����仯
		@Override
		public void onLocationChanged(Location location) {
			// String latitude = "γ�ȣ�" + location.getLatitude();
			// String longitude = "����" + location.getLongitude();
			// String accuracy = "��ȷ��" + location.getAccuracy();
			// String altiude = "����" + location.getAltitude();
			// tvLocation.setText(latitude + "\n" + longitude/ + "\n" + accuracy
			// + "\n" + altiude);
			// �Ѿ�γ�ȱ�������
			sharedPreferences
					.edit()
					.putString(
							"location",
							"����" + location.getLatitude() + ";" + "����"
									+ location.getLongitude()).commit();
			Log.e("Location",
					location.getLatitude() + ";" + location.getLongitude());

		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onProviderEnabled(String provider) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onProviderDisabled(String provider) {
			// TODO Auto-generated method stub

		}
	}

	// ����activityʱ���ر�gps����
	@Override
	public void onDestroy() {
		super.onDestroy();
		lm.removeUpdates(listener);
	}
}
