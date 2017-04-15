package com.zzb.mynew.fragment.base;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zzb.mynew.R;
import com.zzb.mynew.common.base.BaseFragment;
import com.zzb.mynew.common.baseapp.BaseApplication;
import com.zzb.mynew.common.commonutil.NetWorkUtil;
import com.zzb.mynew.view.EmptyLayout;

/**
 * @author 张智斌
 * @time 2017/3/28 21:54
 * @desc ${TODD}
 */

public abstract class BaseListFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener,BaseQuickAdapter.RequestLoadMoreListener{
    protected RecyclerView mRecycler;
    protected SwipeRefreshLayout mRefreshSwipe;
    protected EmptyLayout mErrorLayout;
    protected boolean isErr =true;
    protected boolean isLoadMore = false;
    protected BaseQuickAdapter mAdapter;
    protected int loadMoreCount;
    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_pull_refreshrecyclerview;
    }
    @Override
    protected void initView() {
        mRefreshSwipe= (SwipeRefreshLayout) rootView.findViewById(R.id.refresh_swipe);
        mRecycler=(RecyclerView)rootView.findViewById(R.id.recycler);
        mErrorLayout=(EmptyLayout)rootView.findViewById(R.id.error_layout);
        mRefreshSwipe.setOnRefreshListener(this);
        mRefreshSwipe.setColorSchemeResources(
                R.color.swiperefresh_color1, R.color.swiperefresh_color2,
                R.color.swiperefresh_color3, R.color.swiperefresh_color4);
        mErrorLayout.setOnLayoutClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mState = STATE_REFRESH;
                mErrorLayout.setErrorType(EmptyLayout.NETWORK_LOADING);
                //点击重新加载
                isErr=true;
                sendRequestData();
            }
        });
    }

    @Override
    public void initData() {
        if (mAdapter != null) {
            mRecycler.setAdapter(mAdapter);
        } else {
            mAdapter = getListAdapter();
            mRecycler.setAdapter(mAdapter);
        }
        initUIData();
        sendRequestData();
    }
    protected abstract void initUIData();

    protected abstract BaseQuickAdapter getListAdapter();

    protected void sendRequestData() {}
    /** 设置顶部正在加载的状态 */
    protected void setSwipeRefreshLoadingState() {
        if (mRefreshSwipe != null) {
            mRefreshSwipe.setRefreshing(true);
            // 防止多次重复刷新
            mRefreshSwipe.setEnabled(false);
            mState = STATE_REFRESH;
        }
    }
    /** 设置顶部加载完毕的状态 */
    protected void setSwipeRefreshLoadedState() {
        if (mRefreshSwipe != null) {
            mRefreshSwipe.setRefreshing(false);
            mRefreshSwipe.setEnabled(true);
            mState=STATE_NONE;
        }
    }
    /*下拉刷新事件*/
    @Override
    public void onRefresh() {
        if(mState==STATE_REFRESH){
            return;
        }
        setSwipeRefreshLoadingState();
    }
    protected void finshRefresh(){
        if(!NetWorkUtil.isNetConnected(BaseApplication.getAppContext())){
           showShortToast("当前网络不给力哦");
        }
    }
    @Override
    public void onLoadMoreRequested() {}
}
