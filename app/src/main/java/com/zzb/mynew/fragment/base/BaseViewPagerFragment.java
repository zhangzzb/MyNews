package com.zzb.mynew.fragment.base;


import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.zzb.mynew.R;
import com.zzb.mynew.common.base.BaseFragment;
import com.zzb.mynew.common.base.BaseFragmentAdapter;
import com.zzb.mynew.view.PagerSlidingTabStrip;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 张智斌
 * @time 2017/3/27 23:14
 * @desc ${TODD}
 */

public abstract class BaseViewPagerFragment extends BaseFragment {
    protected PagerSlidingTabStrip mTabStrip;
    protected ViewPager mViewPager;
    protected BaseFragmentAdapter mTabsAdapter;
    //选项卡控件及ViewPager数据
    protected List<Fragment> mFragmentList = new ArrayList<>();
    protected List<String> mTitleList;
    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_news;
    }

    @Override
    public void initData() {
        setScreenPageLimit();
        onSetupTabAdapter(mTabsAdapter);
    }
    //设置预加载的页数
    protected void setScreenPageLimit() {

    }
    protected abstract void onSetupTabAdapter(BaseFragmentAdapter adapter);

    @Override
    protected void initView() {
        mTabStrip = (PagerSlidingTabStrip) rootView
                .findViewById(R.id.tab_strip);
        mViewPager = (ViewPager) rootView.findViewById(R.id.vp_pager);
    }
    protected ArrayList<String> getList(String[] data) {
        ArrayList<String> list = new ArrayList<>();
        for (String title : data) {
            list.add(title);
        }
        return list;
    }
}
