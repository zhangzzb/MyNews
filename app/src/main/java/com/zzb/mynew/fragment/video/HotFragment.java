package com.zzb.mynew.fragment.video;

import android.support.v7.widget.GridLayoutManager;
import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.zzb.mynew.activity.VaActivity;
import com.zzb.mynew.adapter.VaAdapter;
import com.zzb.mynew.api.ApiClient;
import com.zzb.mynew.bean.va.BannerBean;
import com.zzb.mynew.bean.va.HotBean;
import com.zzb.mynew.bean.va.LiveBean;
import com.zzb.mynew.common.baseapp.BaseApplication;
import com.zzb.mynew.common.commonutil.GsonTools;
import com.zzb.mynew.fragment.base.BaseListFragment;
import com.zzb.mynew.okhttp.IOkHttpStringCallBack;
import com.zzb.mynew.util.OkHttpUtil;
import com.zzb.mynew.util.TLog;
import com.zzb.mynew.view.BannerView;
import com.zzb.mynew.view.CustomLoadMoreView;
import com.zzb.mynew.view.EmptyLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 张智斌
 * @time 2017/4/9 23:46
 * @desc ${TODD}
 */

public class HotFragment extends BaseListFragment {
    private BannerView mBannerView;
    private String url = ApiClient.getBroadcastUrl(1);
    private int pager=1;
    @Override
    protected void initUIData() {
        GridLayoutManager manager = new GridLayoutManager(getContext(), 2);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return position == 0 || position == mAdapter.getItemCount() - 1 ? 2 : 1;
            }
        });
        mRecycler.setLayoutManager(manager);
        mAdapter.setLoadMoreView(new CustomLoadMoreView());
        mAdapter.setOnLoadMoreListener(this, mRecycler);
        mAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        mRecycler.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                //点击进入直播
                LiveBean liveBean=(LiveBean)mAdapter.getItem(position);
                VaActivity.startAction(getActivity(),liveBean.getHdl_live_url());
            }
        });
    }

    @Override
    protected BaseQuickAdapter getListAdapter() {
        return new VaAdapter();
    }

    @Override
    protected void sendRequestData() {
        OkHttpUtil.requestGETStringResult(url, new IOkHttpStringCallBack() {
            @Override
            public void onSuccess(String result) {
                mErrorLayout.setErrorType(EmptyLayout.HIDE_LAYOUT);
                if (!TextUtils.isEmpty(result)) {
                    processData(result);
                }
            }
            @Override
            public void onFailure(Exception e) {
                if (!isLoadMore) {
                    mErrorLayout.setErrorType(EmptyLayout.NETWORK_ERROR);
                }
                mAdapter.loadMoreFail();
            }
        });
    }

    private void processData(String result) {
        HotBean videoBean = GsonTools.changeGsonToBean(result, HotBean.class);
        List<LiveBean> lives = videoBean.getLives();
        if (isLoadMore) {
            mAdapter.addData(lives);
            isLoadMore = false;
            mAdapter.loadMoreComplete();
            if (mAdapter.getData().size()>=videoBean.getTotal()) {
                mAdapter.loadMoreEnd();
            }
        } else {
            mAdapter.setNewData(lives);
            if (mAdapter.getData().size()>=videoBean.getTotal()) {
                mAdapter.loadMoreEnd();
            }
            //轮播图数据
            List<BannerBean> bannerBeanList = videoBean.getBanners();
            //准备图片地址集合
            ArrayList<String> bannerImgUrls = new ArrayList<>();
            if (bannerBeanList != null) {
                for (int i = 0; i < bannerBeanList.size(); i++) {
                    BannerBean bannerBean = bannerBeanList.get(i);
                    bannerImgUrls.add(bannerBean.getUrl());
                }
            }
            addHeaderView(bannerImgUrls);
            if (mRefreshSwipe.isRefreshing()) {
                setSwipeRefreshLoadedState();//刷新完成
            }
        }
    }

    private void addHeaderView(ArrayList<String> bannerImgUrls) {
        //如果是刷新,不用每次都去添加
        ArrayList<String> bannerTitles = new ArrayList<>();
        if (mBannerView == null) {
            mBannerView = new BannerView(BaseApplication.getAppContext(), bannerTitles, bannerImgUrls);
            mAdapter.addHeaderView(mBannerView);
        } else {
            //否则就直接调用控件的刷新方法
            mBannerView.updateData(bannerTitles, bannerImgUrls);
        }
    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        url = ApiClient.getBroadcastUrl(1);
        pager=1;
        sendRequestData();
    }

    /*加载更多数据*/
    @Override
    public void onLoadMoreRequested() {
        isLoadMore = true;
        pager++;
        //加载更多
        TLog.log(pager+"=======");
        url = ApiClient.getBroadcastUrl(pager);
        sendRequestData();
    }
}
