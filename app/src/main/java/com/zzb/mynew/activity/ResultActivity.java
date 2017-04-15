package com.zzb.mynew.activity;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ZoomButtonsController;

import com.zzb.mynew.R;
public class ResultActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.result_main);
		WebView webView =(WebView)findViewById(R.id.webview);
		WebSettings settings = webView.getSettings();
		settings.setSupportZoom(true);
		settings.setJavaScriptEnabled(true);
		settings.setDefaultFontSize(15);
		settings.setBuiltInZoomControls(true);
		int sdkInt = Build.VERSION.SDK_INT;
		if(sdkInt>=11){
			settings.setDisplayZoomControls(false);
		}else{
			ZoomButtonsController zbc = new ZoomButtonsController(webView);
			zbc.getZoomControls().setVisibility(View.GONE);
		}
		String url = getIntent().getStringExtra("url");
		webView.loadUrl(url);
		webView.setWebViewClient(getWebView());
	}
	/*处理内部链接*/
	public static WebViewClient getWebView(){
		return new WebViewClient(){
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				if (!url.equals("about:blank")) {
					view.loadUrl(url);
					return true;
				}
				return super.shouldOverrideUrlLoading(view, url);
			}
		};
	}
}
