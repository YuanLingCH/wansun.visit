package wansun.visit.android.utils;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import wansun.visit.android.global.waifangApplication;

/**
 * Created by lingyuan on 2018/7/30.
 */

public class UIutils {
    /**
     * 获取版本号
     * */
    public static int getVersionCode() {
        PackageManager pm = waifangApplication.getInstence().getPackageManager();
        try {
            PackageInfo packageInfo = pm.getPackageInfo(waifangApplication.getInstence().getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 获取版本名称
     * @return
     */
    public static String getVersionName(){
        PackageManager pm = waifangApplication.getInstence().getPackageManager();
        try {
            PackageInfo packageInfo = pm.getPackageInfo(waifangApplication.getInstence().getPackageName(), 0);
            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据id获取字符串
     */
    public static String getString(int id) {
        return waifangApplication.getInstence().getResources().getString(id);
    }


}
