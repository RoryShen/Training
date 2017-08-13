package tech.rory.mobilesafe.service;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;

public class LocationService extends Service {
	private LocationManager lm;
	private MyLocationListener listener;
	private SharedPreferences sharedPreferences;

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		sharedPreferences = getSharedPreferences("config", MODE_PRIVATE);
		lm = (LocationManager) getSystemService(LOCATION_SERVICE);
		listener = new MyLocationListener();
		// ��ϵͳ˵����ʲô����ѵ�λ���ṩ��
		Criteria criteria = new Criteria();
		// �����Ƿ������ѣ���3g�����¶�λ��
		criteria.setCostAllowed(true);
		// ���þ���״̬
		criteria.setAccuracy(Criteria.ACCURACY_FINE);

		String bestLocation = lm.getBestProvider(criteria, true);
		// ����1��λ���ṩ���ͣ�����2����̸���ʱ�䣬����3����̸��¾��룬����4��listener
		lm.requestLocationUpdates(bestLocation, 0, 0, listener);

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
			sharedPreferences.edit().putString("location",
					"����" + location.getLatitude() + ";" + "����" + location.getLongitude()).commit();
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
