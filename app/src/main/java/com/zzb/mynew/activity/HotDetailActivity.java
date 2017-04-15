package com.zzb.mynew.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.zzb.mynew.R;
import com.zzb.mynew.api.ApiClient;
import com.zzb.mynew.bean.hotnews.ArticleImageBean;
import com.zzb.mynew.bean.hotnews.HotDeatilBean;
import com.zzb.mynew.common.base.BaseActivity;
import com.zzb.mynew.common.commonutil.GsonTools;
import com.zzb.mynew.common.commonutil.ImageLoaderUtils;
import com.zzb.mynew.common.commonutil.NetWorkUtil;
import com.zzb.mynew.common.commonutil.TimeUtil;
import com.zzb.mynew.okhttp.IOkHttpStringCallBack;
import com.zzb.mynew.util.OkHttpUtil;
import com.zzb.mynew.util.UIHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

import static com.zzb.mynew.R.id.fab;
import static com.zzb.mynew.util.UIHelper.LINE;
import static com.zzb.mynew.util.UIHelper.WEB_IMAGE;
import static com.zzb.mynew.util.UIHelper.WEB_STYLE;

/**
 * @author 张智斌
 * @time 2017/4/1 20:54
 * @desc 新闻热点详情
 */

public class HotDetailActivity extends BaseActivity{
    private static final String DETAILID = "detailId";
    private static final String IMAGEURL = "imageUrl";
    @Bind(R.id.news_detail_photo_iv)
    ImageView mNewsDetailPhotoIv;
    @Bind(R.id.toolbar_layout)
    CollapsingToolbarLayout mToolbarLayout;
    @Bind(R.id.app_bar)
    AppBarLayout mAppBar;
    @Bind(R.id.news_detail_from_tv)
    TextView mNewsDetailFromTv;
    @Bind(fab)
    FloatingActionButton mFab;
    @Bind(R.id.webview)
    WebView mWebview;
    private String mNewId;
    private String mImageUrl;
    private ArrayList<String> mImgList;
    @Override
    public int getLayoutId() {
        return R.layout.activity_hot_detail;
    }
    @Override
    public void initData() {
        //分享
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLongToast("点击了分享");
            }
        });
        initToolBar("", R.drawable.ic_arrow_back, true);
        ImageLoaderUtils.display(this, mNewsDetailPhotoIv, mImageUrl);
        String detailUrl = ApiClient.getNewsDetailUrl(mNewId);
        requestData(detailUrl);
    }
    public void requestData(final String url) {
        OkHttpUtil.requestGETStringResult(url, new IOkHttpStringCallBack() {
            @Override
            public void onSuccess(String result) {
                processData(result);
            }

            @Override
            public void onFailure(Exception e) {
                showNetErrorTip("数据加载异常");
            }
        });
    }

    private void processData(String result) {
        String json = praseJson(result);
        HotDeatilBean hotDeatilBean = GsonTools.changeGsonToBean(json, HotDeatilBean.class);
        try {
            if (hotDeatilBean != null) {
                String ptime = hotDeatilBean.getPtime();
                mNewsDetailFromTv.setText(TimeUtil.formatDate(ptime) + "  新闻来源:" + hotDeatilBean.getSource());
                setToolBarLayout(hotDeatilBean.getTitle());
                /*加载web数据*/
                StringBuffer buffer = new StringBuffer();
                buffer.append(LINE);//添加线条
                buffer.append(setHtmlCotentSupportImagePreview(hotDeatilBean.getImg(), hotDeatilBean.getBody()));
                buffer.append(WEB_STYLE);
                buffer.append(WEB_IMAGE);
                loadImage();//点击加载大图片
                mWebview.loadDataWithBaseURL(null, buffer.toString(), "text/html", "utf-8", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            showNetErrorTip("数据加载异常");
        }

    }

    private String praseJson(String json) {
        String result = "";
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONObject detailJsonObj = jsonObject.optJSONObject(mNewId);
            result = detailJsonObj.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }

    /*加载图片数据*/
    private void loadImage() {
        if (!mImgList.isEmpty()) {
            UIHelper.addWebImageShow(HotDetailActivity.this, mWebview, mImgList);
        }
    }
    /*设置可以加载图片*/
    public String setHtmlCotentSupportImagePreview(List<ArticleImageBean> articleImageBean, String body) {
        //将body中的<!--img#0-->这样的标签进行替换
        mImgList = new ArrayList<>();
        // 读取用户设置：是否加载文章图片--默认有wifi下始终加载图片
        if (NetWorkUtil.isNetConnected(this)) {
            for (int i = 0; i < articleImageBean.size(); i++) {
                ArticleImageBean imageBean = articleImageBean.get(i);
                //<img src="ic_launcher.png"/>
                /*设置image的点击事件i就是下标*/
                body = body.replace(imageBean.getRef(), "<img  onClick=\"showImagePreview(" + i + ")\" src = \"" + imageBean.getSrc() + "\"/>");
                mImgList.add(imageBean.getSrc());
            }
        } else {
            // 过滤掉 img标签
            body = body.replaceAll("<\\s*img\\s+([^>]*)\\s*>", "");
        }
        return body;
    }

    @Override
    public void initView() {
        SetTranslanteBar();
        mNewId = getIntent().getStringExtra(DETAILID);
        mImageUrl = getIntent().getStringExtra(IMAGEURL);
        UIHelper.initWebView(mWebview);
    }

    public static void startHotDetailActivity(Activity activity, String id, String imageUrl) {
        Intent intent = new Intent(activity, HotDetailActivity.class);
        intent.putExtra(DETAILID, id);
        intent.putExtra(IMAGEURL, imageUrl);
        activity.startActivity(intent);
    }

    private void setToolBarLayout(String newsTitle) {
        mToolbarLayout.setTitle(newsTitle);
        mToolbarLayout.setExpandedTitleColor(ContextCompat.getColor(this, R.color.white));
        mToolbarLayout.setCollapsedTitleTextColor(ContextCompat.getColor(this, R.color.white));
    }
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.staystill, R.anim.activity_out);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        destroy();
    }

    public void destroy() {
        if (mWebview != null) {
            // 如果先调用destroy()方法，则会命中if (isDestroyed()) return;这一行代码，需要先onDetachedFromWindow()，再
            // destory()
            ViewParent parent = mWebview.getParent();
            if (parent != null) {
                ((ViewGroup) parent).removeView(mWebview);
            }
            mWebview.stopLoading();
            // 退出时调用此方法，移除绑定的服务，否则某些特定系统会报错
            mWebview.getSettings().setJavaScriptEnabled(false);
            mWebview.clearHistory();
            mWebview.clearView();
            mWebview.removeAllViews();
            try {
                mWebview.destroy();
            } catch (Throwable ex) {

            }
        }
    }
}
