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
		// 给系统说明，什么是最佳的位置提供者
		Criteria criteria = new Criteria();
		// 设置是否允许付费（如3g网络下定位）
		criteria.setCostAllowed(true);
		// 设置经度状态
		criteria.setAccuracy(Criteria.ACCURACY_FINE);

		String bestLocation = lm.getBestProvider(criteria, true);
		// 参数1：位置提供类型，参数2，最短更新时间，参数3：最短更新距离，参数4：listener
		lm.requestLocationUpdates(bestLocation, 0, 0, listener);

	}

	class MyLocationListener implements LocationListener {
		// 位置发生变化
		@Override
		public void onLocationChanged(Location location) {
			// String latitude = "纬度：" + location.getLatitude();
			// String longitude = "经度" + location.getLongitude();
			// String accuracy = "精确度" + location.getAccuracy();
			// String altiude = "海拔" + location.getAltitude();
			// tvLocation.setText(latitude + "\n" + longitude/ + "\n" + accuracy
			// + "\n" + altiude);
			// 把经纬度保存起来
			sharedPreferences.edit().putString("location",
					"经度" + location.getLatitude() + ";" + "经度" + location.getLongitude()).commit();
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

	// 销毁activity时，关闭gps更新
	@Override
	public void onDestroy() {
		super.onDestroy();
		lm.removeUpdates(listener);
	}
}
