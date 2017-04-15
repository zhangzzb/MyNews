package com.zzb.mynew.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zzb.mynew.R;
import com.zzb.mynew.common.commonutil.FormatUtil;
import com.zzb.mynew.common.commonutil.ImageLoaderUtils;
/**
 * @author 张智斌
 * @time 2017/2/18 20:11
 * @desc 动态圈头布局
 */
public class ZoneHeaderView extends LinearLayout{
    private ImageView img_avater;
    private TextView tv_name;
    public ZoneHeaderView(Context context) {
        super(context);
        initView();
    }

    public ZoneHeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public ZoneHeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ZoneHeaderView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }

    private void initView() {
        View view = inflate(getContext(), R.layout.item_zone_header, null);
        img_avater= (ImageView) view.findViewById(R.id.img_avater);
        tv_name= (TextView) view.findViewById(R.id.tv_name);
        addView(view);
    }
    /**
     * 设置基本信息
     */
    public void setData(String name,String avater){
        tv_name.setText(FormatUtil.checkValue(name));
        ImageLoaderUtils.displayRound(getContext(),img_avater,avater);
    }
}
