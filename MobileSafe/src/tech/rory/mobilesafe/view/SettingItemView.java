package tech.rory.mobilesafe.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import tech.rory.mobilesafe.R;

/**
 * �Զ��岼��
 * 
 * @author Rory
 *
 */
public class SettingItemView extends RelativeLayout {

	private TextView tvTitle;
	private TextView tvDesc;
	private CheckBox cBox;

	public SettingItemView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initView();
	}

	public SettingItemView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public SettingItemView(Context context) {
		super(context);
		initView();
	}

	/*
	 * ��ʼ������
	 */
	private void initView() {
		// ���Զ���õĲ����ļ����ø���ǰ��SettingItemView
		View.inflate(getContext(), R.layout.view_setting_item, this);
		tvTitle = (TextView) findViewById(R.id.tv_title);
		tvDesc = (TextView) findViewById(R.id.tv_desc);
		cBox = (CheckBox) findViewById(R.id.cb_status);

	}

	public void setTitle(String title) {
		tvTitle.setText(title);

	}

	public void setDesc(String desc) {
		tvDesc.setText(desc);

	}

	/**
	 * �жϵ�ǰ��ѡ״̬
	 * 
	 * @return ���ع�ѡ״̬
	 */
	public boolean isChecked() {
		return cBox.isChecked();

	}

	/**
	 * ���ø�ѡ���״̬
	 * @param checked
	 */
	public void setChecked(boolean checked) {
		cBox.setChecked(checked);
	}
}
