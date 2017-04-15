package com.zzb.mynew.util;

import android.text.TextUtils;

import com.zzb.mynew.okhttp.HttpException;
import com.zzb.mynew.okhttp.HttpRespone;
import com.zzb.mynew.okhttp.IOkHttpFileCallBack;
import com.zzb.mynew.okhttp.IOkHttpStringCallBack;
import com.zzb.mynew.okhttp.OkHttpTool;
import com.zzb.mynew.okhttp.RequestParams;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author 张智斌
 * @time 2017/3/28 18:15
 * @desc Okhttp的使用类 // get请求，post请求，file文件下载
 */

public class OkHttpUtil {
    /*get请求数据*/
    public static void createGetRequest(String url, RequestParams params, final HttpRespone httpRespone) {
        url=getUrlWithQueryString(url,params);
        Request request = new Request.Builder().url(url).build();
        doRequest(request,httpRespone);
    }
    /*post请求*/
    public static void createPostRequest(String url, RequestParams params, final HttpRespone httpRespone) {
        // 1.创建请求的参数列表
        FormBody.Builder formBodyBuilder = new FormBody.Builder();
        for (HashMap.Entry<String, String> entry : params.mParams.entrySet()) {
            formBodyBuilder.add(entry.getKey(), entry.getValue());
        }
        // 2.创建requset对象
        Request request = new Request.Builder().url(url).post(formBodyBuilder.build())
                .build();
        doRequest(request,httpRespone);
    }
    private static String getUrlWithQueryString(String url, RequestParams params) {
        // 1.创建Url地址
        if (params != null && !TextUtils.isEmpty(url)) {
            StringBuilder sb = new StringBuilder().append(url);
            sb.append("?");
            for (HashMap.Entry<String, String> entry : params.mParams.entrySet()) {
                sb.append(entry.getKey() + "=" + entry.getValue() + "&");
            }
            url = sb.substring(0, sb.length() - 1);
        }
        return url;
    }
    /*文件下载*/
    public static void requestDownloadFile(String url,final IOkHttpFileCallBack callBack) {
        Request request = new Request.Builder().url(url).build();
        OkHttpClient okHttpClient = OkHttpTool.getInstance().getOkHttpClient();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callBack.onFailure(e);
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                // 1.一系列的错误网页
                if (!response.isSuccessful()) {
                    callBack.onFailure(new HttpException("系统异常"));
                }
                //获取流数据，子线程下载
                final InputStream result = response.body().byteStream();
                callBack.onSuccess(result);
            }
        });
    }
    //写一个方法供给request 给okHttpclient使用
    private static void doRequest(Request request,final HttpRespone httpRespone){
        OkHttpClient okHttpClient = OkHttpTool.getInstance().getOkHttpClient();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                //让监听器回调接口的方法在主线程中运行
                OkHttpTool.getInstance().getHandler().post(new Runnable() {
                    @Override
                    public void run() {
                        //其他异常
                        httpRespone.onFailure(new HttpException(e.getMessage()));
                    }
                });
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                // 1.一系列的错误网页
                if (!response.isSuccessful()) {
                    OkHttpTool.getInstance().getHandler().post(new Runnable() {
                        @Override
                        public void run() {
                            //其他异常
                            httpRespone.onFailure(new HttpException("系统异常"));
                        }
                    });
                }
                //获取json数据
                final String result = response.body().string();
                //让监听器回调接口的方法在主线程中运行
                OkHttpTool.getInstance().getHandler().post(new Runnable() {
                    @Override
                    public void run() {
                        httpRespone.parse(result);
                    }
                });
            }
        });
    }
    public static void requestGETStringResult(String url,final IOkHttpStringCallBack callBack) {
        final Request request = new Request.Builder().url(url).build();
        OkHttpClient okHttpClient = OkHttpTool.getInstance().getOkHttpClient();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                //让监听器回调接口的方法在主线程中运行
                OkHttpTool.getInstance().getHandler().post(new Runnable() {
                    @Override
                    public void run() {
                        callBack.onFailure(e);
                    }
                });
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //获取json数据
                if(response.isSuccessful()){
                    final String result = response.body().string();
                    //让监听器回调接口的方法在主线程中运行
                    OkHttpTool.getInstance().getHandler().post(new Runnable() {
                        @Override
                        public void run() {
                            callBack.onSuccess(result);
                        }
                    });
                }else{
                    OkHttpTool.getInstance().getHandler().post(new Runnable() {
                        @Override
                        public void run() {
                            callBack.onFailure(new HttpException("网页加载有误"));
                        }
                    });
                }
            }
        });
    }
}
