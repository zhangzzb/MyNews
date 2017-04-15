package com.zzb.mynew.activity;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;

import com.zzb.mynew.R;
import com.zzb.mynew.api.Constants;
import com.zzb.mynew.common.base.BaseActivity;
import com.zzb.mynew.common.commonutil.ImageLoaderUtils;
import com.zzb.mynew.common.commonutil.SharePrefUtil;
import com.zzb.mynew.fragment.MeFragment;
import com.zzb.mynew.fragment.TopicFragment;
import com.zzb.mynew.fragment.news.NewsFragment;
import com.zzb.mynew.fragment.video.VaFragment;
import com.zzb.mynew.view.MyFragmentTabHost;
import com.zzb.mynew.view.TabIndicatorView;

import butterknife.Bind;

import static com.zzb.mynew.R.color.colorPrimarytopic;

public class MainActivity extends BaseActivity implements TabHost.OnTabChangeListener,Toolbar.OnMenuItemClickListener,NavigationView.OnNavigationItemSelectedListener {
    private static final String TAB_NEW = "news";
    private static final String TAB_TOPIC = "topic";
    private static final String TAB_VA = "va";
    private static final String TAB_ME = "me";
    @Bind(R.id.navigation_view)
    NavigationView mNavigationView;
    @Bind(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @Bind(android.R.id.tabhost)
    MyFragmentTabHost mFragmentTabHost;
    private TabIndicatorView mNewsIndicator;
    private TabIndicatorView mVaIndicator;
    private TabIndicatorView mTopicIndicator;
    private TabIndicatorView mMeIndicator;
    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initData() {
        // 1. 新建新闻TabSpec
        TabHost.TabSpec newTabSpec = mFragmentTabHost.newTabSpec(TAB_NEW);
        mNewsIndicator = new TabIndicatorView(this);
        mNewsIndicator.setTitle("新闻");
        mNewsIndicator.setImageIcon(R.drawable.new_normal, R.drawable.new_press);
        newTabSpec.setIndicator(mNewsIndicator);
        mFragmentTabHost.addTab(newTabSpec, NewsFragment.class, null);
        //2.新建直播TabSpec
        TabHost.TabSpec vaTabSpec = mFragmentTabHost.newTabSpec(TAB_VA);
        mVaIndicator = new TabIndicatorView(this);
        mVaIndicator.setTitle("直播");
        mVaIndicator.setImageIcon(R.drawable.va_normal, R.drawable.va_press);
        vaTabSpec.setIndicator(mVaIndicator);
        mFragmentTabHost.addTab(vaTabSpec, VaFragment.class, null);
        //3.新建话题TabSpec
        TabHost.TabSpec topicTabSpec = mFragmentTabHost.newTabSpec(TAB_TOPIC);
        mTopicIndicator = new TabIndicatorView(this);
        mTopicIndicator.setTitle("动态");
        mTopicIndicator.setImageIcon(R.drawable.tip_normal, R.drawable.tip_press);
        topicTabSpec.setIndicator(mTopicIndicator);
        mFragmentTabHost.addTab(topicTabSpec, TopicFragment.class, null);
        //4.新建我TabSpec
        TabHost.TabSpec meTabSpec = mFragmentTabHost.newTabSpec(TAB_ME);
        mMeIndicator = new TabIndicatorView(this);
        mMeIndicator.setTitle("我");
        mMeIndicator.setImageIcon(R.drawable.me_normal, R.drawable.me_press);
        meTabSpec.setIndicator(mMeIndicator);
        mFragmentTabHost.addTab(meTabSpec, MeFragment.class, null);
        //去除分割线
        mFragmentTabHost.getTabWidget().setDividerDrawable(android.R.color.white);
        //初始化选中
        mFragmentTabHost.setCurrentTabByTag(TAB_NEW);
        mNewsIndicator.setTabSelected(true);
        //设置监听器
        mFragmentTabHost.setOnTabChangedListener(this);
    }
    @Override
    public void initView() {
        //初始化TabHost
        mFragmentTabHost.setup(this, getSupportFragmentManager(), R.id.content_id);
        initToolBar(getString(R.string.app_name),R.drawable.ic_drawer);
        mToolbar.inflateMenu(R.menu.activity_menu);
        //初始化actionbar
        ActionBarDrawerToggle drawerToggle =new ActionBarDrawerToggle(this,mDrawerLayout,mToolbar,R.string.app_name,R.string.app_name);
        mDrawerLayout.setDrawerListener(drawerToggle);
        drawerToggle.syncState();
        mNavigationView.setNavigationItemSelectedListener(this);
        View headerView = mNavigationView.getHeaderView(0);
        ImageView userImageView=(ImageView)headerView.findViewById(R.id.iv_user);
        ImageLoaderUtils.displayRound(this,userImageView,MeFragment.URL_HEADER);
        mToolbar.setOnMenuItemClickListener(this);
    }
    @Override
    public void onTabChanged(String tabId) {
        mNewsIndicator.setTabSelected(false);
        mVaIndicator.setTabSelected(false);
        mMeIndicator.setTabSelected(false);
        mTopicIndicator.setTabSelected(false);
        boolean flag = SharePrefUtil.getBoolean(Constants.AUTO_SKIN_MODE, false);
        switch (tabId) {
            case TAB_NEW:
                mNewsIndicator.setTabSelected(true);
                tabSelected(flag);
                break;
            case TAB_VA:
                mVaIndicator.setTabSelected(true);
                tabSelected(flag);
                break;
            case TAB_TOPIC:
                if(!flag){
                    SetStatusBarColor(ContextCompat.getColor(this,R.color.colorPrimarytopic));
                    mToolbar.setBackgroundColor(getResources().getColor(colorPrimarytopic));
                }
                mTopicIndicator.setTabSelected(true);
                break;
            case TAB_ME:
                tabSelected(flag);
                mMeIndicator.setTabSelected(true);
                break;
        }
    }
    public void tabSelected(boolean flag){
        if(!flag){
            SetStatusBarColor(ContextCompat.getColor(this,R.color.colorPrimaryDark));
            mToolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        }
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.drawer_menu_exit:

                break;
            case R.id.drawer_menu_me:

                break;
            case R.id.drawer_menu_setting:
                SettingActivity.startAction(this);
                break;
            case R.id.drawer_menu_theme:
                break;
            case R.id.drawer_menu_scan:
                break;
            case R.id.drawer_menu_aboutsoft:
                break;
        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    @Override
    public boolean onMenuItemClick(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.search:
                showShortToast(R.string.actionbar_title_search);
                break;
            case R.id.share:
                showShortToast(R.string.share_to);
                break;
        }
        return false;
    }
}
