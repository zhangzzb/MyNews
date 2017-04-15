package com.zzb.mynew.okhttp;

import java.io.InputStream;

/**
 * Created by poi on 2017/2/7.
 */

public interface IOkHttpFileCallBack {
    void onSuccess(InputStream result);
    void onFailure(Exception e);
}
