package com.zzb.mynew.view;


import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zzb.mynew.R;

/**
 * 自定义组合控件
 */
public class TabIndicatorView extends LinearLayout {
	private ImageView mTabIcon;
	private TextView mTabHint;
	private int mNormalId,mSelectId;
	public TabIndicatorView(Context context) {
		this(context, null);
		// TODO Auto-generated constructor stub
	}
	public TabIndicatorView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		initLayout(context);
	}
	private void initLayout(Context context) {
		// TODO Auto-generated method stub
		View view = View.inflate(context, R.layout.tab_indicator, this);
		mTabIcon=(ImageView)view.findViewById(R.id.tab_indicator_icon);
		mTabHint=(TextView)view.findViewById(R.id.tab_indicator_hint);
	}
	/*设置标题*/
	public void setTitle(String title){
		mTabHint.setText(title);
	}
	/*是否选中*/
	public void setTabSelected(boolean isSelected){
		if(isSelected){
			mTabIcon.setImageResource(mSelectId);
		}else{
			mTabIcon.setImageResource(mNormalId);
		}
	}
	/*设置图标*/
	public void setImageIcon(int normalId,int selectId){
		this.mNormalId=normalId;
		this.mSelectId=selectId;
		mTabIcon.setImageResource(normalId);
	}
}
