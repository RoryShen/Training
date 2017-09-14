package tech.rory.mobilesafe.activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

/*
 * 设置引导页面的基类，由于该界面不需要显示，因此不用再清单文件里注册
 */
public abstract class BaseSetupActivity extends Activity {
	private GestureDetector mDetector;
	public SharedPreferences sharedPreferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		sharedPreferences = getSharedPreferences("config", MODE_PRIVATE);
		// 设置一个事件监听器
		mDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
			@Override
			public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
				// 判断纵向滑动幅度是否过大，过大时，不允许切换界面
				if (Math.abs(e2.getRawY() - e1.getRawY()) > 100) {
					Toast.makeText(BaseSetupActivity.this, "不能这样划哦！", Toast.LENGTH_SHORT).show();
					return true;
				}
				if (Math.abs(velocityX) < 100) {
					Toast.makeText(BaseSetupActivity.this, "滑动太慢咯", Toast.LENGTH_SHORT).show();
				}

				// 向右划，上一页{
				if (e2.getRawX() - e1.getRawX() > 200) {
					showPreviousPage();
					return true;
				}
				// 向左滑，下一页
				if (e1.getRawX() - e2.getRawX() > 200) {
					showNextPage();
					return true;
				}
				return super.onFling(e1, e2, velocityX, velocityY);

			}

		});

	}

	/**
	 * 展示下一页, 子类必须实现
	 */
	public abstract void showNextPage();

	/**
	 * 展示上一页, 子类必须实现
	 */
	public abstract void showPreviousPage();

	// 点击下一页按钮
	public void next(View view) {
		showNextPage();
	}

	// 点击上一页按钮
	public void previous(View view) {
		showPreviousPage();
	}

	// 把所有事件都交给手势处理器处理。
	public boolean onTouchEvent(MotionEvent event) {
		mDetector.onTouchEvent(event);
		return super.onTouchEvent(event);

	}
}
