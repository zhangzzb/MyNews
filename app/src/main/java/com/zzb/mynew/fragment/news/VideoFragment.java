package com.zzb.mynew.fragment.news;

import android.support.v7.widget.GridLayoutManager;
import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.zzb.mynew.activity.VideoDetailActivity;
import com.zzb.mynew.adapter.SmillAdapter;
import com.zzb.mynew.api.ApiClient;
import com.zzb.mynew.bean.video.thing.ItemsBean;
import com.zzb.mynew.bean.video.thing.VideoBean;
import com.zzb.mynew.common.commonutil.GsonTools;
import com.zzb.mynew.fragment.base.BaseListFragment;
import com.zzb.mynew.okhttp.IOkHttpStringCallBack;
import com.zzb.mynew.util.OkHttpUtil;
import com.zzb.mynew.view.EmptyLayout;

import java.util.List;

/**
 * @author 张智斌
 * @time 2017/4/5 14:03
 * @desc ${TODD}
 */

public class VideoFragment extends BaseListFragment {
    private String url=ApiClient.getVideoUrl(1);
    @Override
    protected void initUIData() {
        loadMoreCount=1;
        mRecycler.setLayoutManager(new GridLayoutManager(getContext(),2));
        mAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        mRecycler.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                List<ItemsBean> itemsBeen=(List<ItemsBean>) adapter.getData();
                VideoDetailActivity.startAction(position,getContext(),itemsBeen);
            }
        });
    }
    @Override
    protected BaseQuickAdapter getListAdapter() {
        return new SmillAdapter();
    }
    @Override
    protected void sendRequestData() {
        OkHttpUtil.requestGETStringResult(url, new IOkHttpStringCallBack() {
            @Override
            public void onSuccess(String result) {
                mErrorLayout.setErrorType(EmptyLayout.HIDE_LAYOUT);
                if(!TextUtils.isEmpty(result)){
                    processData(result);
                }
            }
            @Override
            public void onFailure(Exception e) {
                mErrorLayout.setErrorType(EmptyLayout.NETWORK_ERROR);
            }
        });
    }

    private void processData(String result) {
        VideoBean videoBean = GsonTools.changeGsonToBean(result,VideoBean.class);
        List<ItemsBean> items = videoBean.getItems();
        mAdapter.setNewData(items);
        if (mRefreshSwipe.isRefreshing()) {
            setSwipeRefreshLoadedState();//刷新完成
        }
    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        loadMoreCount++;
        //刷新加载更多
        url= ApiClient.getVideoUrl(loadMoreCount);
        sendRequestData();
    }
}
