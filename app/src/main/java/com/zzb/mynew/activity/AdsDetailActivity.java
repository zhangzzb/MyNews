package com.zzb.mynew.activity;

import android.content.Intent;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.zzb.mynew.R;
import com.zzb.mynew.api.Constants;
import com.zzb.mynew.bean.ads.AdsBean;
import com.zzb.mynew.common.base.BaseActivity;
import com.zzb.mynew.util.UIHelper;

import butterknife.Bind;

public class AdsDetailActivity extends BaseActivity {
    @Bind(R.id.webview)
    WebView mWebview;

    @Override
    public int getLayoutId() {
        return R.layout.activity_web_view;
    }

    @Override
    public void initData() {
        AdsBean.Action actionPrams = (AdsBean.Action) getIntent().getSerializableExtra(Constants.ADS_ACTION_PARAMS);
        //链接地址
        String link_url = actionPrams.getLink_url();
        mWebview.loadUrl(link_url);
    }
    @Override
    public void initView() {
        UIHelper.initWebView(mWebview);
        initToolBar("广告详情", R.drawable.ic_arrow_back, true);
        WebSettings settings = mWebview.getSettings();
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(true);
    }
    @Override
    public void onBackPressed() {
        if(mWebview.canGoBack()){
            mWebview.goBack();
        }else{
            Intent intent =new Intent(AdsDetailActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
        super.onBackPressed();
    }
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.staystill, R.anim.activity_out);
    }
}
