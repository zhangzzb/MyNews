package com.zzb.mynew.activity;

import android.content.Context;
import android.content.Intent;
import android.widget.ListView;

import com.zzb.mynew.R;
import com.zzb.mynew.adapter.ArticleCommentAdapter;
import com.zzb.mynew.api.ApiClient;
import com.zzb.mynew.bean.article.ArticleBean;
import com.zzb.mynew.bean.article.CommentaryBean;
import com.zzb.mynew.common.base.BaseActivity;
import com.zzb.mynew.common.baseapp.BaseApplication;
import com.zzb.mynew.okhttp.HttpException;
import com.zzb.mynew.okhttp.HttpRespone;
import com.zzb.mynew.util.OkHttpUtil;
import com.zzb.mynew.view.ArticleHeader;
import com.zzb.mynew.view.EmptyLayout;

import butterknife.Bind;

/**
 * @author 张智斌
 * @time 2017/4/6 21:40
 * @desc ${TODD}
 */

public class ArticleDetailActivity extends BaseActivity {
    private static final String ARTICLEBEAN = "detailbean";
    @Bind(R.id.lv_list)
    ListView mLvList;
    @Bind(R.id.error_layout)
    EmptyLayout mErrorLayout;
    private ArticleCommentAdapter mAdapter;
    private ArticleBean.ItemsBean mItemsBean;
    private int mId;
    @Override
    public int getLayoutId() {
        return R.layout.activity_article_detail;
    }
    @Override
    public void initData() {
        ArticleHeader header = new ArticleHeader(this);
        if (mItemsBean != null) {
            header.setHeader(mItemsBean);
            mLvList.addHeaderView(header);
        }
        mAdapter = new ArticleCommentAdapter(BaseApplication.getAppContext());
        mLvList.setAdapter(mAdapter);
        sendResquestData();
    }
    private void sendResquestData() {
        String url = ApiClient.getCommentaryUrl(mId);
        OkHttpUtil.createGetRequest(url, null, new HttpRespone<CommentaryBean>(CommentaryBean.class) {
            @Override
            public void onSuccess(CommentaryBean commentaryBean) {
                if(commentaryBean!=null){
                    mAdapter.setData(commentaryBean.getItems());
                    mErrorLayout.setErrorType(EmptyLayout.HIDE_LAYOUT);
                }
            }
            @Override
            public void onFailure(HttpException e) {
                mErrorLayout.setErrorType(EmptyLayout.NETWORK_ERROR);
            }
        });
    }
    @Override
    public void initView() {
        initToolBar("评论详情", R.drawable.ic_arrow_back, true);
        mItemsBean = (ArticleBean.ItemsBean) getIntent().getSerializableExtra(ARTICLEBEAN);
        mId = mItemsBean.getId();
    }
    public static void startHotDetailActivity(Context activity, ArticleBean.ItemsBean itemsBean) {
        Intent intent = new Intent(activity, ArticleDetailActivity.class);
        intent.putExtra(ARTICLEBEAN, itemsBean);
        activity.startActivity(intent);
    }
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.staystill, R.anim.activity_out);
    }
}
