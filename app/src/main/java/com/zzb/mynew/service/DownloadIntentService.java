package com.zzb.mynew.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.zzb.mynew.bean.ads.AdsBean;
import com.zzb.mynew.bean.ads.AdsBeans;
import com.zzb.mynew.common.commonutil.FileUtil;
import com.zzb.mynew.common.commonutil.ImageUtil;
import com.zzb.mynew.common.security.Md5Security;
import com.zzb.mynew.okhttp.IOkHttpFileCallBack;
import com.zzb.mynew.util.OkHttpUtil;
import com.zzb.mynew.util.TLog;

import java.io.File;
import java.io.InputStream;
import java.util.List;

/**
 * 用于下载网络图片的服务
 */
public class DownloadIntentService extends IntentService {
    //intent的action
    private static final String ACTION_ADS = "com.zzb.mynew.service.action.ADS";
    private static final String ACTION_IMAGE = "com.zzb.mynew.service.action.IMAGE";
    //intent的param key值
    private static final String PARAM_BEAN = "com.zzb.mynew.service.extra.BEAN";
    private static final String FILE_NAME = "com.zzb.mynew.service.extra.IAMGE";
    private static final String FILE_URL="com.zzb.mynew.service.extra.url";
    public DownloadIntentService() {
        super("DownloadIntentService");
    }
    @Override
    protected void onHandleIntent(Intent intent) {
        TLog.log("DownloadIntentService");
        if (intent != null) {
            String action = intent.getAction();
            if(action.equals(ACTION_ADS)){
                AdsBeans adsBeans = (AdsBeans) intent.getSerializableExtra(PARAM_BEAN);
                List<AdsBean> adsBeanList = adsBeans.getAds();
                for (int i = 0; i < adsBeanList.size(); i++) {
                    //使用md5防止出现重复下载的图片
                    String url = adsBeanList.get(i).getRes_url().get(0);
                    String imageName =  Md5Security.toMD5(url);
                    File fileName =new File(FileUtil.getIconDir(),imageName + ".jpg");
                    TLog.log("++++++"+fileName);
                    if (!fileName.exists()) {
                        TLog.log("开始下载广告");
                        startDownloadImage(url,fileName);
                    }
                }
            }else if(action.equals(ACTION_IMAGE)){
                String imageName = intent.getStringExtra(FILE_NAME);
                String url = intent.getStringExtra(FILE_URL);
                File fileName =new File(FileUtil.getIconDir(),imageName + ".jpg");
                if (!fileName.exists()) {
                    TLog.log("开始下载广告");
                    startDownloadImage(url,fileName);
                }
            }
        }
    }
    /*开启一个下载广告图片服务*/
    public static void startActionAds(Context context, AdsBeans adsBeans) {
        Intent intent = new Intent(context, DownloadIntentService.class);
        intent.setAction(ACTION_ADS);
        intent.putExtra(PARAM_BEAN, adsBeans);
        context.startService(intent);
    }
    /*开启一个下载图片的服务*/
    public static void startActionImage(Context context,String fileName,String url) {
        Intent intent = new Intent(context, DownloadIntentService.class);
        intent.setAction(ACTION_IMAGE);
        intent.putExtra(FILE_NAME, fileName);
        intent.putExtra(FILE_URL, url);
        context.startService(intent);
    }
    private void startDownloadImage(String url,final File file) {
        OkHttpUtil.requestDownloadFile(url, new IOkHttpFileCallBack() {
            @Override
            public void onSuccess(InputStream result) {
                //保存图片到SD卡
                Bitmap bitmap = BitmapFactory.decodeStream(result);
                TLog.log("数据得到了"+bitmap);
                ImageUtil.saveImageToSD(DownloadIntentService.this, bitmap, file.getAbsolutePath(),80);
            }
            @Override
            public void onFailure(Exception e) {

            }
        });
    }
}
