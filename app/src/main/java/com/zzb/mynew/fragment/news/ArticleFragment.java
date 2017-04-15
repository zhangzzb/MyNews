package com.zzb.mynew.fragment.news;

import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.zzb.mynew.R;
import com.zzb.mynew.activity.ArticleDetailActivity;
import com.zzb.mynew.adapter.ArticleAdapter;
import com.zzb.mynew.api.ApiClient;
import com.zzb.mynew.bean.article.ArticleBean;
import com.zzb.mynew.common.commonutil.GsonTools;
import com.zzb.mynew.fragment.base.BaseListFragment;
import com.zzb.mynew.okhttp.IOkHttpStringCallBack;
import com.zzb.mynew.util.OkHttpUtil;
import com.zzb.mynew.view.CustomLoadMoreView;
import com.zzb.mynew.view.EmptyLayout;
import com.zzb.mynew.view.ExpandableTextView;

import java.util.List;

/**
 * @author 张智斌
 * @time 2017/4/5 22:19
 * @desc ${TODD}
 */

public class ArticleFragment extends BaseListFragment {
    private String url= ApiClient.getPlainUrl(1);
    @Override
    protected void initUIData() {
        loadMoreCount=1;
        mRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter.setLoadMoreView(new CustomLoadMoreView());
        mAdapter.setOnLoadMoreListener(this, mRecycler);
        mAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        mRecycler.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                ExpandableTextView textView=(ExpandableTextView)view.findViewById(R.id.tv_article_content);
                if(textView.getIsVisibility()==View.GONE){
                    ArticleBean.ItemsBean itemsBean= (ArticleBean.ItemsBean) adapter.getItem(position);
                    ArticleDetailActivity.startHotDetailActivity(getActivity(),itemsBean);
                }
            }
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });
    }

    @Override
    protected BaseQuickAdapter getListAdapter() {
        return new ArticleAdapter();
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
        ArticleBean articleBean = GsonTools.changeGsonToBean(result, ArticleBean.class);
        List<ArticleBean.ItemsBean> items = articleBean.getItems();
        if (isLoadMore) {
            mAdapter.addData(items);
            isLoadMore = false;
            mAdapter.loadMoreComplete();
        } else {
            mAdapter.setNewData(items);
            if (mRefreshSwipe.isRefreshing()) {
                setSwipeRefreshLoadedState();//刷新完成
            }
        }
    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        url= ApiClient.getPlainUrl(1);
        sendRequestData();
    }
    /*加载更多数据*/
    @Override
    public void onLoadMoreRequested() {
        isLoadMore=true;
        loadMoreCount++;
        //加载更多
        url= ApiClient.getPlainUrl(loadMoreCount);
        sendRequestData();
    }
}
