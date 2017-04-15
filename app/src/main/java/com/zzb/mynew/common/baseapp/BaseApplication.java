package com.zzb.mynew.common.baseapp;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;

import cn.feng.skin.manager.loader.SkinManager;

/**
 * APPLICATION
 */
public class BaseApplication extends Application {
    private static Context sContext;
    @Override
    public void onCreate() {
        super.onCreate();
        initSkinLoader();
        sContext=getApplicationContext();
    }
    public static synchronized BaseApplication getAppContext(){
        return (BaseApplication) sContext;
    }
    public static synchronized Resources getAppResources() {
        return sContext.getResources();
    }
    /**
     * Must call init first
     */
    private void initSkinLoader() {
        SkinManager.getInstance().init(this);
        SkinManager.getInstance().load();
    }

}
