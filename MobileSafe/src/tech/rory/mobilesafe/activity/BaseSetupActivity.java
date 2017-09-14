package tech.rory.mobilesafe.activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

/*
 * ��������ҳ��Ļ��࣬���ڸý��治��Ҫ��ʾ����˲������嵥�ļ���ע��
 */
public abstract class BaseSetupActivity extends Activity {
	private GestureDetector mDetector;
	public SharedPreferences sharedPreferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		sharedPreferences = getSharedPreferences("config", MODE_PRIVATE);
		// ����һ���¼�������
		mDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
			@Override
			public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
				// �ж����򻬶������Ƿ���󣬹���ʱ���������л�����
				if (Math.abs(e2.getRawY() - e1.getRawY()) > 100) {
					Toast.makeText(BaseSetupActivity.this, "����������Ŷ��", Toast.LENGTH_SHORT).show();
					return true;
				}
				if (Math.abs(velocityX) < 100) {
					Toast.makeText(BaseSetupActivity.this, "����̫����", Toast.LENGTH_SHORT).show();
				}

				// ���һ�����һҳ{
				if (e2.getRawX() - e1.getRawX() > 200) {
					showPreviousPage();
					return true;
				}
				// ���󻬣���һҳ
				if (e1.getRawX() - e2.getRawX() > 200) {
					showNextPage();
					return true;
				}
				return super.onFling(e1, e2, velocityX, velocityY);

			}

		});

	}

	/**
	 * չʾ��һҳ, �������ʵ��
	 */
	public abstract void showNextPage();

	/**
	 * չʾ��һҳ, �������ʵ��
	 */
	public abstract void showPreviousPage();

	// �����һҳ��ť
	public void next(View view) {
		showNextPage();
	}

	// �����һҳ��ť
	public void previous(View view) {
		showPreviousPage();
	}

	// �������¼����������ƴ���������
	public boolean onTouchEvent(MotionEvent event) {
		mDetector.onTouchEvent(event);
		return super.onTouchEvent(event);

	}
}
