package com.zzb.mynew.view;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zzb.mynew.R;
import com.zzb.mynew.activity.DaliyDetailActivity;
import com.zzb.mynew.bean.daliy.HeadLine;
import com.zzb.mynew.bean.daliy.LineList;

import java.util.List;

/**
 * @author 张智斌
 * @time 2017/2/28 19:01
 * @desc ${TODD}
 */

public class DaliyTopView extends LinearLayout {
    private HeadLine mHeadLine;
    private TextView mHeadline1;
    private TextView mHeadline2;
    private TextView mHeadline3;
    public DaliyTopView(Context context, HeadLine headLine) {
        super(context);
        this.mHeadLine=headLine;
        init();
        initData();
    }
    private void initData() {
        List<LineList> list = mHeadLine.getList();
        if(!list.isEmpty()){
            mHeadline1.setText(mHeadLine.getList().get(0).getDescription());
            mHeadline2.setText(mHeadLine.getList().get(1).getDescription());
            mHeadline3.setText(mHeadLine.getList().get(2).getDescription());
        }
    }
    private void init() {
        View view = View.inflate(getContext(), R.layout.item_daily_headline, this);
        mHeadline1 = (TextView) view.findViewById(R.id.tv_headline_1);
        mHeadline2 = (TextView) view.findViewById(R.id.tv_headline_2);
        mHeadline3 = (TextView) view.findViewById(R.id.tv_headline_3);
        view.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                DaliyDetailActivity.newIntent(getContext(),mHeadLine.getPost().getAppview());
            }
        });
    }
    /*更新第二条数据*/
    public void setData(HeadLine data) {
        this.mHeadLine = data;
        initData();
    }
}
