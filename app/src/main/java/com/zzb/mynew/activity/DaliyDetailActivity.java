package com.zzb.mynew.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.zzb.mynew.R;
import com.zzb.mynew.common.base.BaseActivity;
import com.zzb.mynew.common.commonutil.NetWorkUtil;
import com.zzb.mynew.util.UIHelper;
import com.zzb.mynew.view.EmptyLayout;

import butterknife.Bind;

/**
 * @author 张智斌
 * @time 2017/4/5 21:11
 * @desc ${TODD}
 */

public class DaliyDetailActivity extends BaseActivity {
    public static String DALIY_URL = "daliy_url";
    @Bind(R.id.pb_progress)
    ProgressBar mPbProgress;
    @Bind(R.id.url_web)
    WebView mUrlWeb;
    @Bind(R.id.error_layout)
    EmptyLayout mErrorLayout;
    private String mUrl;

    public static Intent newIntent(Context context, String url) {
        Intent intent = new Intent(context, DaliyDetailActivity.class);
        intent.putExtra(DaliyDetailActivity.DALIY_URL, url);
        context.startActivity(intent);
        return intent;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_daliy_detail;
    }

    @Override
    public void initData() {
        initToolBar("", R.drawable.ic_arrow_back, true);
        UIHelper.initWebView(mUrlWeb);
        mUrlWeb.getSettings().setSupportZoom(false);
        mUrlWeb.getSettings().setBuiltInZoomControls(false);
        mUrlWeb.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                mPbProgress.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                mPbProgress.setVisibility(View.GONE);
            }
        });
        mUrlWeb.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                mPbProgress.setProgress(newProgress);
            }
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                initToolBar(title, R.drawable.ic_arrow_back, true);
            }
        });
        if(NetWorkUtil.isNetConnected(getApplicationContext())){
            mUrlWeb.loadUrl(mUrl);
            mErrorLayout.setErrorType(EmptyLayout.HIDE_LAYOUT);
        }else{
            mErrorLayout.setErrorType(EmptyLayout.NETWORK_ERROR);
        }
    }

    @Override
    public void initView() {
        mUrl = getIntent().getStringExtra(DALIY_URL);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.staystill, R.anim.activity_out);
    }
}
