package tech.rory.mobilesafe.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CheckBox;
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

	// ����Name space
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
		// �����������ƻ�ȡ���Ե�ֵ
		mTile = attrs.getAttributeValue(NAMESPACE, "title");
		//
		mDescOn = attrs.getAttributeValue(NAMESPACE, "desc_on");
		mDescOff = attrs.getAttributeValue(NAMESPACE, "desc_off");
		initView();
		// ��ȡattrs���Ե���Ŀ
		// int attributeCount = attrs.getAttributeCount();

		// // ѭ����ȡ������Ϣ
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
	 * ��ʼ������
	 */
	private void initView() {
		// ���Զ���õĲ����ļ����ø���ǰ��SettingItemView
		View.inflate(getContext(), R.layout.view_setting_item, this);
		tvTitle = (TextView) findViewById(R.id.tv_title);
		tvDesc = (TextView) findViewById(R.id.tv_desc);
		cBox = (CheckBox) findViewById(R.id.cb_status);
		// ���ñ���
		setTitle(mTile);

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
	 * 
	 * @param checked
	 */
	public void setChecked(boolean checked) {
		cBox.setChecked(checked);
		// ����ѡ���״̬�������ı�����
		if (checked) {
			setDesc(mDescOn);
		} else {
			setDesc(mDescOff);
		}
	}
}
