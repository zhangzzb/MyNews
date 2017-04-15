package com.zzb.mynew.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewConfiguration;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.zzb.mynew.R;
import com.zzb.mynew.activity.CirclePublishActivity;
import com.zzb.mynew.adapter.TopicAdapter;
import com.zzb.mynew.api.ApiClient;
import com.zzb.mynew.bean.topic.TopicBean;
import com.zzb.mynew.bean.topic.TopicDetailBean;
import com.zzb.mynew.fragment.base.BaseListFragment;
import com.zzb.mynew.okhttp.HttpException;
import com.zzb.mynew.okhttp.HttpRespone;
import com.zzb.mynew.util.OkHttpUtil;
import com.zzb.mynew.util.TLog;
import com.zzb.mynew.view.CustomLoadMoreView;
import com.zzb.mynew.view.EmptyLayout;
import com.zzb.mynew.view.ZoneHeaderView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * @author 张智斌
 * @time 2017/3/27 19:06
 * @desc ${TODD}
 */

public class TopicFragment extends BaseListFragment {
    @Bind(R.id.fab1)
    FloatingActionButton mFab1;
    @Bind(R.id.fab2)
    FloatingActionButton mFab2;
    @Bind(R.id.fab3)
    FloatingActionButton mFab3;
    @Bind(R.id.fab4)
    FloatingActionButton mFab4;
    @Bind(R.id.menu_red)
    FloatingActionMenu mMenuRed;
    private ZoneHeaderView mZoneHeaderView;
    private String url= ApiClient.getTopicUrl(1);
    private int topicId=1;

    @Override
    protected void initView() {
        super.initView();
        EventBus.getDefault().register(this);
    }
    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_topic;
    }
    @Override
    protected void initUIData() {
        mMenuRed.setClosedOnTouchOutside(true);
        //初始化头部未读消息
        mZoneHeaderView = new ZoneHeaderView(getContext());
        mZoneHeaderView.setData("爱迪生",MeFragment.URL_HEADER);
        mRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter.setLoadMoreView(new CustomLoadMoreView());
        mAdapter.setOnLoadMoreListener(this, mRecycler);
        mAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        mAdapter.addHeaderView(mZoneHeaderView);
        mRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                boolean isSignificantDelta = Math.abs(dy) > ViewConfiguration.getTouchSlop();
                if (isSignificantDelta) {
                    if (dy > 0) {
                        mMenuRed.hideMenuButton(true);
                    } else {
                        mMenuRed.showMenuButton(true);
                    }
                }
            }
        });
    }
    @Override
    protected BaseQuickAdapter getListAdapter() {
        return new TopicAdapter();
    }
    @Override
    public void onLoadMoreRequested() {
        isLoadMore=true;
        topicId++;
        //加载更多
        url= ApiClient.getTopicUrl(topicId);
        sendRequestData();
    }

    @Override
    protected void sendRequestData() {
        //正常的url,是根据不同参数，拼接形成不同的url
        OkHttpUtil.createGetRequest(url, null, new HttpRespone<TopicBean>(TopicBean.class) {
            @Override
            public void onSuccess(TopicBean topicBean) {
                mErrorLayout.setErrorType(EmptyLayout.HIDE_LAYOUT);
                if(topicBean!=null){
                    processData(topicBean);
                }
            }
            @Override
            public void onFailure(HttpException e) {
                if(!isLoadMore){
                    mErrorLayout.setErrorType(EmptyLayout.NETWORK_ERROR);
                }
                finshRefresh();
                mAdapter.loadMoreFail();
            }
        });
    }

    private void processData(TopicBean topicBean) {
        if (isLoadMore) {
            mAdapter.addData(topicBean.getData());
            isLoadMore = false;
            mAdapter.loadMoreComplete();
            if(mAdapter.getData().size()==topicBean.getTotal()){
                mAdapter.loadMoreEnd();
            }
        }else{
            mAdapter.setNewData(topicBean.getData());
            finshRefresh();
        }
    }
    @Override
    public void onRefresh() {
        super.onRefresh();
        url=ApiClient.getTopicUrl(1);
        sendRequestData();
    }
    @OnClick({R.id.menu_red, R.id.fab1, R.id.fab2, R.id.fab3, R.id.fab4})
    public void onClick(View view) {
        CirclePublishActivity.startAction(getActivity());
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onUIUpdataEvent(TopicDetailBean bean) {
        if(bean!=null){
            mAdapter.addData(0,bean);
            TLog.log(bean.toString());
            mRecycler.scrollToPosition(0);
        }
        mMenuRed.hideMenuButton(true);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
