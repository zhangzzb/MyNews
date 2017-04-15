package com.zzb.mynew.common.commonutil;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * 解析JSON的工具类
 */

public class GsonTools {
    /**
     * 将bean对象转换为json
     * @param object
     * @return
     */
    public static String createGsonString(Object object){
        Gson gson =new Gson();
        String gsonString = gson.toJson(object);
        return gsonString;
    }
    /**
     *  将json转换为bean类对象
     * @param gsonString
     * @param cls
     * @param <T>
     * @return
     */
    public static <T> T changeGsonToBean(String gsonString,Class<T> cls){
        Gson gson =new Gson();
        T t = gson.fromJson(gsonString, cls);
        return t;
    }

    /**
     * 将json转换为集合
     * @param gsonString
     * @param cls
     * @param <T>
     * @return
     */
    public static <T> List<T> changeGsonToList(String gsonString, Class<T> cls) {
        Gson gson = new Gson();
        List<T> list = gson.fromJson(gsonString, new TypeToken<List<T>>() {
        }.getType());
        return list;
    }
}
