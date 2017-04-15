package com.zzb.mynew.okhttp;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 张智斌
 * @time 2017/3/28 17:12
 * @desc ${TODD}
 */

public class RequestParams {
    public ConcurrentHashMap<String, String> mParams=new ConcurrentHashMap<>();
    public void put(String key,String value){
        if (key!=null&&value!=null) {
            mParams.put(key, value);
        }
    }
}
