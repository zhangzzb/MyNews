package com.zzb.mynew.fragment.news;

import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.zzb.mynew.activity.DaliyDetailActivity;
import com.zzb.mynew.adapter.DaliyAdapter;
import com.zzb.mynew.api.ApiClient;
import com.zzb.mynew.bean.daliy.DaliyBanner;
import com.zzb.mynew.bean.daliy.HeadLine;
import com.zzb.mynew.bean.daliy.HomeBean;
import com.zzb.mynew.common.commonutil.GsonTools;
import com.zzb.mynew.fragment.base.BaseListFragment;
import com.zzb.mynew.okhttp.IOkHttpStringCallBack;
import com.zzb.mynew.util.OkHttpUtil;
import com.zzb.mynew.view.CustomLoadMoreView;
import com.zzb.mynew.view.DaliyTopView;
import com.zzb.mynew.view.EmptyLayout;

import java.util.List;

/**
 * @author 张智斌
 * @time 2017/4/4 23:56
 * @desc ${TODD}
 */

public class EntertainMentFragment extends BaseListFragment {
    private String url= ApiClient.getDalitUrl("0");
    private String mLast_key;
    private DaliyTopView mTopView;
    @Override
    protected void initUIData() {
        mRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter.setLoadMoreView(new CustomLoadMoreView());
        mAdapter.setOnLoadMoreListener(this, mRecycler);
        mAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        mRecycler.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                //返回有几个头
                    DaliyBanner item = (DaliyBanner)adapter.getItem(position);
                    String url = item.getPost().getAppview();
                    DaliyDetailActivity.newIntent(getActivity(), url);
            }
        });
    }
    @Override
    protected BaseQuickAdapter getListAdapter() {
        return new DaliyAdapter();
    }
    @Override
    protected void sendRequestData() {
        //正常的url,是根据不同参数，拼接形成不同的url
        OkHttpUtil.requestGETStringResult(url, new IOkHttpStringCallBack() {
            @Override
            public void onSuccess(String result) {
                mErrorLayout.setErrorType(EmptyLayout.HIDE_LAYOUT);
                if(!TextUtils.isEmpty(result)){
                    try {
                        processData(result);
                    } catch (Exception e) {
                        e.printStackTrace();
                        mErrorLayout.setErrorType(EmptyLayout.NODATA);
                    }
                }
            }
            @Override
            public void onFailure(Exception e) {
                if(!isLoadMore){
                    mErrorLayout.setErrorType(EmptyLayout.NETWORK_ERROR);
                }
                mAdapter.loadMoreFail();
            }
        });
    }
    private void processData(String result) {
        HomeBean homeBean = GsonTools.changeGsonToBean(result, HomeBean.class);
        if (homeBean != null) {
            if (isLoadMore) {
                List<DaliyBanner> feeds = homeBean.getResponse().getFeeds();
                isLoadMore = false;
                mAdapter.loadMoreComplete();
                mAdapter.addData(feeds);
                mLast_key = homeBean.getResponse().getLast_key();
            } else {
                HeadLine headLine = homeBean.getResponse().getHeadline();
                if (!headLine.getList().isEmpty()) {
                     addHeaderTop(headLine);
                }
                mLast_key = homeBean.getResponse().getLast_key();
                mAdapter.setNewData(homeBean.getResponse().getFeeds());
                if (mRefreshSwipe.isRefreshing()) {
                    setSwipeRefreshLoadedState();//刷新完成
                }
            }
        }
    }
    /*添加大公司头条*/
    private void addHeaderTop(HeadLine headLine) {
        if (mTopView == null) {
            mTopView = new DaliyTopView(getContext(), headLine);
            mAdapter.addHeaderView(mTopView);
        } else {
            mTopView.setData(headLine);
        }
    }
    /*加载更多数据*/
    @Override
    public void onLoadMoreRequested() {
        isLoadMore=true;
        //加载更多
        url= ApiClient.getDalitUrl(mLast_key);
        sendRequestData();
    }
    @Override
    public void onRefresh() {
        super.onRefresh();
        url= ApiClient.getDalitUrl("0");
        sendRequestData();
    }
}
