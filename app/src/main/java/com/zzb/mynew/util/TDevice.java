package com.zzb.mynew.util;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.zzb.mynew.common.baseapp.BaseApplication;
/**
 * @author 张智斌
 * @time 2017/3/27 14:46
 * @desc ${TODD}
 */

public class TDevice {
    private static Boolean _isTablet = null;
    public static String getVersionName() {
        //从包的管理者对象中,获取指定包名的基本信息(版本名称,版本号),传0代表获取基本信息
        PackageManager packageManager = BaseApplication.getAppContext().getPackageManager();
        PackageInfo info;
        try {
            info = packageManager.getPackageInfo(BaseApplication.getAppContext().getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return  "NameNotFoundException";
        }
        return info.versionName;
    }
    public static int getVersionCode() {
        PackageManager packageManager = BaseApplication.getAppContext().getPackageManager();
        PackageInfo info;
        try {
            info = packageManager.getPackageInfo(BaseApplication.getAppContext().getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return  -1;
        }
        return info.versionCode;
    }

    public static String getPackageName() {
        return  BaseApplication.getAppContext().getPackageName();
    }
    public static boolean isTablet() {
        if (_isTablet == null) {
            boolean flag;
            if ((0xf & BaseApplication.getAppContext().getResources()
                    .getConfiguration().screenLayout) >= 3)
                flag = true;
            else
                flag = false;
            _isTablet = Boolean.valueOf(flag);
        }
        return _isTablet.booleanValue();
    }
}
