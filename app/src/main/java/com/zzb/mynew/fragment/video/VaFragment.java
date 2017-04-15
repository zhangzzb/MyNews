package com.zzb.mynew.fragment.video;

import android.graphics.Typeface;

import com.zzb.mynew.R;
import com.zzb.mynew.common.base.BaseFragment;
import com.zzb.mynew.common.base.BaseFragmentAdapter;
import com.zzb.mynew.fragment.base.BaseViewPagerFragment;

/**
 * @author 张智斌
 * @time 2017/3/27 19:05
 * @desc ${TODD}
 */
public class VaFragment extends BaseViewPagerFragment {
    private static final int HOT_FRAGMENT = 0;
    private static final int DISCOVER_FRAGMENT = 1;
    private static final int RECOMMEND_FRAGMENT = 2;
    private static final int QUALITY_FRAGMENT = 3;
    @Override
    protected void onSetupTabAdapter(BaseFragmentAdapter adapter) {
        String[] titleTab = getResources().getStringArray(R.array.quests_viewpage_arrays);
        mTitleList=getList(titleTab);
        for (int i = 0; i < titleTab.length; i++) {
            mFragmentList.add(createFragment(i));
        }
        initTabStrip();
    }
    private void initTabStrip() {
        mTabsAdapter = new BaseFragmentAdapter(this.getChildFragmentManager(), mFragmentList,mTitleList);
        mTabStrip.setTabPaddingLeftRight(8);
        mTabStrip.setTypeface(Typeface.DEFAULT, Typeface.NORMAL);
        mViewPager.setAdapter(mTabsAdapter);
        mTabStrip.setViewPager(mViewPager);
    }
    @Override
    protected void setScreenPageLimit() {
        // 设置ViewPager左右两边保留页面的个数
        mViewPager.setOffscreenPageLimit(2);
    }
    public BaseFragment createFragment(int position) {
        BaseFragment fragment=null;
        switch (position) {
            case HOT_FRAGMENT:
                fragment=new HotFragment();
                break;
            case DISCOVER_FRAGMENT:
                fragment=new DiscoverFragment();
                break;
            case RECOMMEND_FRAGMENT:
                fragment=new RecommendFragment();
                break;
            case QUALITY_FRAGMENT:
                fragment=new QualityFragment();
                break;
        }
        return fragment;
    }
}
