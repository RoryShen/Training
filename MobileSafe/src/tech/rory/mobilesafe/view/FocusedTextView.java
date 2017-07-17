package tech.rory.mobilesafe.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

public class FocusedTextView extends TextView {
	// 有style样式时会走此方法
	public FocusedTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	// 有属性时会走此方法
	public FocusedTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	// 用代码new对象时会走此方法
	public FocusedTextView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 跑马灯要运行，需要调用此函数判断是否有焦点，调用此方法，强制返回有焦点
	 */
	@Override
	public boolean isFocused() {
		// TODO Auto-generated method stub
		return true;
	}
}
