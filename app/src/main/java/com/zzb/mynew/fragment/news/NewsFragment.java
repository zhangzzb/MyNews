package com.zzb.mynew.fragment.news;

import android.graphics.Typeface;

import com.zzb.mynew.R;
import com.zzb.mynew.common.base.BaseFragment;
import com.zzb.mynew.common.base.BaseFragmentAdapter;
import com.zzb.mynew.fragment.base.BaseViewPagerFragment;

/**
 * @author 张智斌
 * @time 2017/3/27 19:04
 * @desc ${TODD}
 */

public class NewsFragment extends BaseViewPagerFragment {

    private static final int HOT_FRAGMENT = 0;
    private static final int ENTERTAIN_FRAGMENT = 1;
    private static final int ARTICLE_FRAGMENT = 2;
    private static final int VIDEO_FRAGMENT = 3;
    @Override
    protected void onSetupTabAdapter(BaseFragmentAdapter adapter) {
        String[] titleTab = getResources().getStringArray(R.array.news_viewpage_arrays);
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
            case ENTERTAIN_FRAGMENT:
                fragment=new EntertainMentFragment();
                break;
            case ARTICLE_FRAGMENT:
                fragment=new ArticleFragment();
                break;
            case VIDEO_FRAGMENT:
                fragment=new VideoFragment();
                break;
        }
        return fragment;
    }
}
