package wansun.visit.android.utils;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

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


    /**
     * //无核心线程,并且最大线程数为int的最大值.
     //超时时间为60s
     //队列为SynchronousQueue同步阻塞队列,队列中没有任何容量.只有在有需求的情况下,队列中才可以试着添加任务.
     * @return
     */
    public static ExecutorService newCacheThreadPool(){
        return  new ThreadPoolExecutor(
                0,Integer.MAX_VALUE,
                60L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>()
        );
    }

}
