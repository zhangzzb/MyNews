package com.zzb.mynew.fragment.video;

import android.support.v7.widget.GridLayoutManager;
import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.zzb.mynew.activity.VaActivity;
import com.zzb.mynew.adapter.VaAdapter;
import com.zzb.mynew.api.ApiClient;
import com.zzb.mynew.bean.va.HotBean;
import com.zzb.mynew.bean.va.LiveBean;
import com.zzb.mynew.common.commonutil.GsonTools;
import com.zzb.mynew.fragment.base.BaseListFragment;
import com.zzb.mynew.okhttp.IOkHttpStringCallBack;
import com.zzb.mynew.util.OkHttpUtil;
import com.zzb.mynew.view.EmptyLayout;

import java.util.List;

/**
 * @author 张智斌
 * @time 2017/4/11 19:52
 * @desc ${TODD}
 */

public class DiscoverFragment extends BaseListFragment {
    private String url = ApiClient.getDiscoveryUrl(1);
    @Override
    protected void initUIData() {
        GridLayoutManager manager = new GridLayoutManager(getContext(), 2);
        mRecycler.setLayoutManager(manager);
        mAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        mRecycler.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                //点击进入直播
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
                finshRefresh();
                mErrorLayout.setErrorType(EmptyLayout.NETWORK_ERROR);
            }
        });
    }

    private void processData(String result) {
        HotBean videoBean = GsonTools.changeGsonToBean(result, HotBean.class);
        List<LiveBean> lives = videoBean.getLives();
        mAdapter.setNewData(lives);
        finshRefresh();
    }
    @Override
    public void onRefresh() {
        super.onRefresh();
        sendRequestData();
    }
}
