package com.zzb.mynew.view;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zzb.mynew.R;
import com.zzb.mynew.adapter.BannerAdapter;
import com.zzb.mynew.common.commonutil.DisplayUtil;
import com.zzb.mynew.common.commonutil.ImageLoaderUtils;
import com.zzb.mynew.util.TLog;

import java.util.ArrayList;

/**
 * @author 张智斌
 * @time 2017/4/6 20:50
 * @desc 轮播图自定义控件
 */

public class BannerView extends RelativeLayout implements ViewPager.OnPageChangeListener{
    private TextView mTitleTv;
    private HeaderViewpager mViewPager;
    private LinearLayout mLinearLayout;
    private ArrayList<String> mTitleList;
    private ArrayList<String> mImageUrlList;
    private ArrayList<ImageView> mImageViews;
    private BannerAdapter mBannerAdapter;
    private int mPicSize;
    private final int LOOP_TIME = 2500;//图片的展示时间
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int currentItem = mViewPager.getCurrentItem();
            mViewPager.setCurrentItem(currentItem + 1);
            mHandler.sendEmptyMessageDelayed(1, LOOP_TIME);
        }
    };
    public BannerView(Context context, ArrayList<String> titleList, ArrayList<String> imageUrlList) {
        super(context);
        this.mTitleList = titleList;
        this.mImageUrlList = imageUrlList;
        mPicSize = mImageUrlList.size();
        init();
        if (mPicSize != 1) {
            initUnLimitPic();
            initData(false);
            initPointData();
            startLooper();
        } else {
            initData(false);
        }
    }
    public void updateData(ArrayList<String> titleList, ArrayList<String> imageUrlList) {
        boolean flag=false;
        for (int i = 0; i < imageUrlList.size(); i++) {
            if(imageUrlList.get(i).equals(mImageUrlList.get(i))){
                flag=false;
            }else{
                flag=true;
            }
        }
        if(flag){
            mTitleList.clear();
            mTitleList.addAll(titleList);
            mImageUrlList.clear();
            mImageUrlList.addAll(imageUrlList);
            mPicSize = mImageUrlList.size();
            if(mPicSize>0){
                initUnLimitPic();
                initData(true);
                initPointData();
            }
        }
        startLooper();
    }
    private void startLooper() {
        stopLooper();
        mHandler.sendEmptyMessageDelayed(1, LOOP_TIME);
    }

    public void stopLooper() {
        mHandler.removeCallbacksAndMessages(null);
    }

    protected void initPointData() {
        mLinearLayout.removeAllViews();
        for (int i = 0; i < mPicSize; i++) {
            ImageView imageView = new ImageView(getContext());
            imageView.setImageResource(R.drawable.pressed_point_image);
            if (i != 0) {
                imageView.setImageResource(R.drawable.default_point_image);
            }
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
            layoutParams.leftMargin = DisplayUtil.dip2px(5);
            mLinearLayout.addView(imageView, layoutParams);
        }
        mViewPager.addOnPageChangeListener(this);
    }

    private void init() {
        View view = View.inflate(getContext(), R.layout.bannerview_header_layout, this);
        mTitleTv = (TextView) view.findViewById(R.id.tv_title);
        mLinearLayout = (LinearLayout) view.findViewById(R.id.ll_container);
        mViewPager = (HeaderViewpager) view.findViewById(R.id.viewpager);
        mViewPager.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        stopLooper();
                        break;
                    case MotionEvent.ACTION_UP:
                        startLooper();
                        break;
                    case MotionEvent.ACTION_MOVE:

                        break;
                    case MotionEvent.ACTION_CANCEL:
                        //取消，用户突然中止操作，滑动ListView
                        //当触摸事件只有down,后续的事件没了,就会走到这里
                        startLooper();
                        TLog.log("后续的事件");
                        break;
                }
                return false;
            }
        });
    }

    private void initData(boolean isUpdate) {
        mImageViews =new ArrayList<>();
        if (isUpdate) {
            if (mImageUrlList != null && mImageUrlList.size() > 0) {
                addImageView();
                mBannerAdapter.setData(mImageViews);
            }
        } else {
            if (mImageUrlList != null && mImageUrlList.size() > 0) {
                addImageView();
                mBannerAdapter = new BannerAdapter(mImageViews);
                mViewPager.setAdapter(mBannerAdapter);
            }
        }
        //默认指向第二张(相当于原来的图集的第一张图)
        mViewPager.setCurrentItem(1);
        if (mTitleList != null && mTitleList.size() > 0) {
             mTitleTv.setText(mTitleList.get(0));
        }
    }
    private void addImageView(){
        mImageViews.clear();
        for(int i = 0; i < mImageUrlList.size(); i++) {
            ImageView imageView = new ImageView(getContext());
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            ImageLoaderUtils.display(getContext(),imageView,mImageUrlList.get(i));
            mImageViews.add(imageView);
        }
    }
    private void initUnLimitPic() {
        //往图片集合当中首尾各添加一张图片来准备做无线循环
        ArrayList<String> backup = new ArrayList<>();
        backup.addAll(mImageUrlList);
        mImageUrlList.clear();
        //添加最后一张到最前面来
        mImageUrlList.add(backup.get(mPicSize - 1));
        //添加中间的
        mImageUrlList.addAll(backup);
        //添加第一张图片到末尾
        mImageUrlList.add(backup.get(0));
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        position = getRealPosition(position);
        if(mTitleList.size()>0){
            mTitleTv.setText(mTitleList.get(position));
        }
        changPoint(position);
    }

    private void changPoint(int position) {
        for (int i = 0; i < mLinearLayout.getChildCount(); i++) {
            ImageView imageView = (ImageView) mLinearLayout.getChildAt(i);
            if (i == position) {
                imageView.setImageResource(R.drawable.pressed_point_image);
            } else {
                imageView.setImageResource(R.drawable.default_point_image);
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        //当状态为停下来时,不滚动时,再偷天换日
        if (state == ViewPager.SCROLL_STATE_IDLE) {
            int currentItem = mViewPager.getCurrentItem();
            if (currentItem == 0) {
                //应该就指向倒数第二张图
                //第二个参数为false,没有切换的动画效果
                mViewPager.setCurrentItem(mImageUrlList.size() - 1 - 1, false);
            } else if (currentItem == mImageUrlList.size() - 1) {
                //应该指向第二张图
                mViewPager.setCurrentItem(1, false);
            }
        }
    }

    private int getRealPosition(int position) {
        // 0 1 2 3 4  原来的
        //4 0 1 2 3 4 0  新的
        int realPosition;
        if (position == 0) {
            realPosition = mPicSize - 1;
        } else if (position == mImageUrlList.size() - 1) {
            realPosition = 0;
        } else {
            realPosition = position - 1;
        }
        return realPosition;
    }
}
