package com.zzb.mynew.okhttp;

/**
 * Created by poi on 2017/2/7.
 */

public interface IOkHttpStringCallBack {
    void onSuccess(String result);
    void onFailure(Exception e);
}
