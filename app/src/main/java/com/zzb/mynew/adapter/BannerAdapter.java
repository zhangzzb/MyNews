package com.zzb.mynew.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * 轮播图适配器
 * Created by poi on 2017/2/8.
 */

public class BannerAdapter extends PagerAdapter {
    private List<ImageView> mBeanList =new ArrayList<>();
    public BannerAdapter(List<ImageView> list){
        this.mBeanList=list;
    }
    @Override
    public int getCount() {
        return mBeanList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = mBeanList.get(position);
        container.addView(imageView);
        return imageView;
    }
    public void setData(List<ImageView> beanList){
        this.mBeanList=beanList;
        notifyDataSetChanged();
    }
}
