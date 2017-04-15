package com.zzb.mynew.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ZoomButtonsController;

import com.zzb.mynew.common.imagepager.BigImagePagerActivity;
import com.zzb.mynew.inter.OnWebViewImageListener;

import java.util.ArrayList;

/**
 * 全局控件帮助类
 */

public class UIHelper {
    public final static String WEB_STYLE =
            "<style>* {font-size:16px;line-height:20px;} p {color:#333;} a {color:#3E62A6;} img {max-width:340px;} "
            + "img.alignleft {float:left;max-width:120px;margin:0 10px 5px 0;border:1px solid #ccc;background:#fff;padding:2px;} "
            + "pre {font-size:9pt;line-height:12pt;font-family:Courier New,Arial;border:1px solid #ddd;border-left:5px solid #6CE26C;background:#f6f6f6;padding:5px;overflow: auto;} "
            + "a.tag {font-size:15px;text-decoration:none;background-color:#cfc;color:#060;border-bottom:1px solid #B1D3EB;border-right:1px solid #B1D3EB;color:#3E6D8E;margin:2px 2px 2px 0;padding:2px 4px;white-space:nowrap;position:relative}</style>";
    public final static String LINE= "<div style=\"border-top:1px dotted #999999;margin:20px 0px\"></div>";
    public final static String WEB_IMAGE= "<script type=\"text/javascript\">function showImagePreview(i){window.mWebViewImageListener.showImagePreview(i);}</script>";
    public  static void initWebView(WebView webView){
        WebSettings settings = webView.getSettings();
        settings.setDefaultFontSize(15);
        settings.setJavaScriptEnabled(true);
        settings.setSupportZoom(false);
        settings.setBuiltInZoomControls(false);
        int sysVersion = Build.VERSION.SDK_INT;
        if (sysVersion >= 11) {
            settings.setDisplayZoomControls(false);
        } else {
            ZoomButtonsController zbc = new ZoomButtonsController(webView);
            zbc.getZoomControls().setVisibility(View.GONE);
        }
        webView.setWebViewClient(getWebViewClient());
    }
    /**
     * 获取webviewClient对象
     *
     * @return
     */
    public static WebViewClient getWebViewClient() {

        return new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
               // showUrlRedirect(view.getContext(), url);
                view.loadUrl(url);
                return true;
            }
        };
    }
    /**
     * 添加网页的点击图片展示支持
     */
    @SuppressLint({ "JavascriptInterface", "SetJavaScriptEnabled" })
    @JavascriptInterface
    public static void addWebImageShow(final Context cxt, WebView wv, final ArrayList<String> bigImageUrl) {
        wv.getSettings().setJavaScriptEnabled(true);
        wv.addJavascriptInterface(new OnWebViewImageListener() {
            @Override
            @JavascriptInterface
            public void showImagePreview(int index) {
                if(bigImageUrl!=null){
                    UIHelper.showImagePreview(cxt,index,bigImageUrl);
                }
            }
        }, "mWebViewImageListener");
    }
    /*显示webview的图片*/
    @JavascriptInterface
    public static void showImagePreview(Context context, int index,
                                        ArrayList<String> imageUrls) {
        BigImagePagerActivity.startImagePagerActivity(context,imageUrls,index);
    }

}
