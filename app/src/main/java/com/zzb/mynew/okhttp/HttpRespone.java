package com.zzb.mynew.okhttp;

import android.text.TextUtils;

import com.zzb.mynew.common.commonutil.GsonTools;

/**
 * @author 张智斌
 * @time 2017/3/28 17:22
 * @desc ${TODD}
 */

public abstract class HttpRespone<T>{
    Class<T> mClass;
    public HttpRespone(Class<T> mClass) {
        this.mClass = mClass;
    }
    public abstract void onSuccess(T t);
    public abstract void onFailure(HttpException e);
    public void parse(String json) {
        //如果需要的是字符串,则直接返回Json
        if (TextUtils.isEmpty(json)) {
            onFailure(new HttpException("json数据为空"));
        }
        if (mClass==String.class){
            onSuccess((T) json);
            return;
        }
        T t = null;
        try {
            t = GsonTools.changeGsonToBean(json,mClass);
        } catch (Exception e) {
            onFailure(new HttpException("解析异常"));
        }
        onSuccess(t);
    }
}
