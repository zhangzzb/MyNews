package com.zzb.mynew.activity;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zzb.mynew.R;
import com.zzb.mynew.api.ApiClient;
import com.zzb.mynew.api.Constants;
import com.zzb.mynew.bean.ads.AdsBean;
import com.zzb.mynew.bean.ads.AdsBeans;
import com.zzb.mynew.common.base.BaseActivity;
import com.zzb.mynew.common.commonutil.FileUtil;
import com.zzb.mynew.common.commonutil.GsonTools;
import com.zzb.mynew.common.commonutil.SharePrefUtil;
import com.zzb.mynew.common.security.Md5Security;
import com.zzb.mynew.okhttp.IOkHttpStringCallBack;
import com.zzb.mynew.service.DownloadIntentService;
import com.zzb.mynew.util.OkHttpUtil;
import com.zzb.mynew.util.TDevice;
import com.zzb.mynew.util.TLog;
import com.zzb.mynew.view.CircleTextProgressbar;

import java.io.File;

import butterknife.Bind;

/**
 * @author 张智斌
 * @time 2017/3/27 16:05
 * @desc ${TODD}
 */
public class SplashActivity extends BaseActivity {
    private static final int PROGRESS_MESS = 1;
    @Bind(R.id.splash_iv)
    ImageView mSplashIv;
    @Bind(R.id.skipview)
    CircleTextProgressbar mSkipview;
    @Bind(R.id.tv_version)
    TextView mTvVersion;
    private Handler mHandler = new Handler();

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public void initData() {
        SetTranslanteBar();
        mTvVersion.setText(String.format("版本名称/%s", TDevice.getVersionName()));
        //获取缓存的json数据
        String result = SharePrefUtil.getString(Constants.ADS_JSON_KEY, "");
        //获取有效期，判断是否需要重新请求数据
        long ads_end_time = SharePrefUtil.getLong(Constants.ADS_END_TIME, 0);
        /*某个时间段内,广告肯定是一样的,没必要每次都去后台下载广告网络数据,我们可以缓存来避免*/
        if (TextUtils.isEmpty(result) || System.currentTimeMillis() > ads_end_time) {
            //过个几秒就跳转到主页 第一次安装应用时出现的情况
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    startHome();
                }
            }, 3000);
            requestData();
        } else {
            //已经有缓存数据，显示加载图片
            showPic(result);
        }
    }
    @Override
    public void initView() {
        mSkipview.setOutLineColor(Color.TRANSPARENT);
        mSkipview.setInCircleColor(Color.parseColor("#AAC6C6C6"));
        mSkipview.setProgressColor(getResources().getColor(R.color.colorPrimary));
        mSkipview.setProgressLineWidth(3);
        mSkipview.setProgressType(CircleTextProgressbar.ProgressType.COUNT);
        // 设置倒计时时间毫秒，默认3000毫秒。
        mSkipview.setTimeMillis(2000);
        mSkipview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startHome();
            }
        });
        mSkipview.setCountdownProgressListener(PROGRESS_MESS, new CircleTextProgressbar.OnCountdownProgressListener() {
            @Override
            public void onProgress(int what, int progress) {
                if (progress == 100) {
                    startHome();
                }
                if(mSkipview!=null){
                    mSkipview.setText(progress + "%");
                }
            }
        });
    }
    private void startHome() {
        startActivity(MainActivity.class,true);
    }
    /*异步请求网络*/
    private void requestData() {
        OkHttpUtil.requestGETStringResult(ApiClient.AD_URI, new IOkHttpStringCallBack() {
            @Override
            public void onSuccess(String result) {
                if (!TextUtils.isEmpty(result)) {
                    //获取地址后，通过服务下载数据
                    AdsBeans adsBeans = GsonTools.changeGsonToBean(result, AdsBeans.class);
                    if (adsBeans != null) {
                        DownloadIntentService.startActionAds(SplashActivity.this, adsBeans);
                        //保存请求到的数据，作为缓存数据
                        int next_req = adsBeans.getNext_req();//认为是分钟单位
                        long endTime = System.currentTimeMillis() + next_req * 60 * 1000;
                        SharePrefUtil.saveString(Constants.ADS_JSON_KEY, result);
                        SharePrefUtil.saveLong(Constants.ADS_END_TIME, endTime);
                        SharePrefUtil.saveInt(Constants.ADS_INDEX_KEY, 0);
                    }
                }
            }

            @Override
            public void onFailure(Exception e) {
            }
        });
    }
    /*显示图片数据*/
    private void showPic(String result) {
        //通过下标合理显示广告图片，防止下标溢出
        int index = SharePrefUtil.getInt(Constants.ADS_INDEX_KEY, 0);
        //将保存好的json缓存对象转换为bean对象
        AdsBeans adsBeans = GsonTools.changeGsonToBean(result, AdsBeans.class);
        if (adsBeans != null) {
            //  index = index % adsBeans.getAds().size();//实现无限循环
            String url = adsBeans.getAds().get(index).getRes_url().get(0);
            String imageName = Md5Security.toMD5(url);
            File file = new File(FileUtil.getIconDir(),imageName + ".jpg");
            TLog.log("====="+file);
            if (file.exists() && file.length() > 0) {
                setImageData(index, adsBeans, file);
                mSkipview.setVisibility(View.VISIBLE);
            } else {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startHome();
                        mSkipview.setVisibility(View.GONE);
                    }
                }, 3000);
                TLog.log("图片不存在");
            }
        }
    }
    private void setImageData(int index, AdsBeans adsBeans, File file) {
        Bitmap bitmap = FileUtil.getBitmap(file.getAbsolutePath());
        mSkipview.start();
        mSplashIv.setImageBitmap(bitmap);
        //获取广告跳转的地址
        final AdsBean.Action action_params = adsBeans.getAds().get(index).getAction_params();
        if (action_params != null) {
            //点击图片进入广告界面
            mSplashIv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle =new Bundle();
                    bundle.putSerializable(Constants.ADS_ACTION_PARAMS,action_params);
                    startActivity(AdsDetailActivity.class,bundle);
                    mSkipview.stop();
                    finish();
                }
            });
            index++;
            index = index % adsBeans.getAds().size();//实现无限循环
            SharePrefUtil.saveInt(Constants.ADS_INDEX_KEY, index);
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mSkipview!=null){
            mSkipview.stop();
        }
        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
        }
    }
}
