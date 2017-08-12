package tech.rory.mobilesafe.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;
import tech.rory.mobilesafe.R;

/**
 * 自定义布局
 * 
 * @author Rory
 *
 */
public class SettingItemView extends RelativeLayout {

	// 定义Name space
	private static final String NAMESPACE = "http://schemas.android.com/apk/res/tech.rory.mobilesafe";
	private TextView tvTitle;
	private TextView tvDesc;
	private CheckBox cBox;
	private String mTile;
	private String mDescOn;
	private String mDescOff;

	public SettingItemView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initView();

	}

	public SettingItemView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// 根据属性名称获取属性的值
		mTile = attrs.getAttributeValue(NAMESPACE, "title");
		//
		mDescOn = attrs.getAttributeValue(NAMESPACE, "desc_on");
		mDescOff = attrs.getAttributeValue(NAMESPACE, "desc_off");
		initView();
		// 获取attrs属性的数目
		// int attributeCount = attrs.getAttributeCount();

		// // 循环读取属性信息
		// for (int i = 0; i < attributeCount; i++) {
		// String attributeName = attrs.getAttributeName(i);
		//
		// String attributeValue = attrs.getAttributeValue(i);
		// System.out.println(attributeName + "," + attributeValue);
		// }

	}

	public SettingItemView(Context context) {
		super(context);
		initView();
	}

	/*
	 * 初始化布局
	 */
	private void initView() {
		// 将自定义好的布局文件设置给当前的SettingItemView
		View.inflate(getContext(), R.layout.view_setting_item, this);
		tvTitle = (TextView) findViewById(R.id.tv_title);
		tvDesc = (TextView) findViewById(R.id.tv_desc);
		cBox = (CheckBox) findViewById(R.id.cb_status);
		// 设置标题
		setTitle(mTile);

	}

	public void setTitle(String title) {
		tvTitle.setText(title);

	}

	public void setDesc(String desc) {
		tvDesc.setText(desc);

	}

	/**
	 * 判断当前勾选状态
	 * 
	 * @return 返回勾选状态
	 */
	public boolean isChecked() {
		return cBox.isChecked();

	}

	/**
	 * 设置复选框的状态
	 * 
	 * @param checked
	 */
	public void setChecked(boolean checked) {
		cBox.setChecked(checked);
		// 根据选择框状态，更新文本描述
		if (checked) {
			setDesc(mDescOn);
		} else {
			setDesc(mDescOff);
		}
	}
}
