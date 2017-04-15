package com.zzb.mynew.fragment.news;

import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.zzb.mynew.activity.HotDetailActivity;
import com.zzb.mynew.adapter.NewsHotAdapter;
import com.zzb.mynew.api.ApiClient;
import com.zzb.mynew.api.Constants;
import com.zzb.mynew.bean.hotnews.HotNewsBean;
import com.zzb.mynew.common.baseapp.BaseApplication;
import com.zzb.mynew.common.commonutil.GsonTools;
import com.zzb.mynew.common.commonutil.SharePrefUtil;
import com.zzb.mynew.fragment.base.BaseListFragment;
import com.zzb.mynew.okhttp.IOkHttpStringCallBack;
import com.zzb.mynew.util.OkHttpUtil;
import com.zzb.mynew.view.BannerView;
import com.zzb.mynew.view.CustomLoadMoreView;
import com.zzb.mynew.view.EmptyLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 张智斌
 * @time 2017/3/27 23:41
 * @desc 头条新闻，数据来源于网易新闻
 */
public class HotFragment extends BaseListFragment {
    private BannerView mBannerView;
    private String url=ApiClient.getHotUrl(0,20,false);
    @Override
    protected void initUIData() {
        mRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter.setLoadMoreView(new CustomLoadMoreView());
        mAdapter.setOnLoadMoreListener(this, mRecycler);
        mAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        mRecycler.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                HotNewsBean.NewDetailBean item = (HotNewsBean.NewDetailBean)adapter.getItem(position);
                SharePrefUtil.saveString(Constants.HOT_NEW_KEY+item.docid,item.docid);
                HotDetailActivity.startHotDetailActivity(getActivity(),item.docid,item.imgsrc);
                mAdapter.notifyDataSetChanged();
            }
        });
    }
    @Override
    protected BaseQuickAdapter getListAdapter() {
        return new NewsHotAdapter();
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
                        HotNewsBean hotNewsBean = GsonTools.changeGsonToBean(result, HotNewsBean.class);
                        processData(hotNewsBean);
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

    @Override
    public void onRefresh() {
        super.onRefresh();
        url= ApiClient.getHotUrl(0,20,false);
        sendRequestData();
    }
    /*加载更多数据*/
    @Override
    public void onLoadMoreRequested() {
        isLoadMore=true;
        loadMoreCount++;
        //加载更多
        url= ApiClient.getHotUrl(loadMoreCount * 10, 9 + loadMoreCount * 10,true);
        sendRequestData();
    }
    private void processData(HotNewsBean hotNewsBean) {
        List<HotNewsBean.NewDetailBean> detailBeanList = hotNewsBean.T1348647909107;
            if (isLoadMore) {
                mAdapter.addData(detailBeanList);
                isLoadMore = false;
                mAdapter.loadMoreComplete();
                if(mAdapter.getData().size()>60){
                    mAdapter.loadMoreEnd();
                }
            } else {
                List<HotNewsBean.NewDetailBean> mNewDetailBeanList = hotNewsBean.T1348647909107;
                mAdapter.setNewData(detailBeanList);
                //第一条是轮播图数据.将其移除出来
                HotNewsBean.NewDetailBean detailBean = mNewDetailBeanList.remove(0);
                List<HotNewsBean.NewDetailBean.BannerBean> bannerBeanList = detailBean.ads;
                //准备图片地址集合和标题文本数据集合
                ArrayList<String> bannerTitles = new ArrayList<>();
                ArrayList<String> bannerImgUrls = new ArrayList<>();
                if(bannerBeanList!=null) {
                    for (int i = 0; i < bannerBeanList.size(); i++) {
                        HotNewsBean.NewDetailBean.BannerBean bannerBean = bannerBeanList.get(i);
                        bannerTitles.add(bannerBean.title);
                        bannerImgUrls.add(bannerBean.imgsrc);
                    }
                }
                addHeaderView(bannerTitles, bannerImgUrls);
                if (mRefreshSwipe.isRefreshing()) {
                    setSwipeRefreshLoadedState();//刷新完成
                }

        }
    }
    private void addHeaderView(ArrayList<String> titleList, ArrayList<String> imageList) {
        //如果是刷新,不用每次都去添加
        if (mBannerView == null) {
            mBannerView = new BannerView(BaseApplication.getAppContext(), titleList, imageList);
            mAdapter.addHeaderView(mBannerView);
        } else {
            //否则就直接调用控件的刷新方法
            mBannerView.updateData(titleList, imageList);
        }
    }
}
