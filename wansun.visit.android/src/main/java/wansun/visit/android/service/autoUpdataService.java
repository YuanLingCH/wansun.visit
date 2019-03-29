package wansun.visit.android.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.Nullable;

import java.util.Date;

import wansun.visit.android.boardCastReceiver.BootCompletedReceiver;
import wansun.visit.android.utils.logUtils;

/**
 * Created by User on 2019/3/26.
 */

public class autoUpdataService extends Service {


    private int Time = 1000*3;//周期时间
   //  private int anHour =8*60*60*1000;// 这是8小时的毫秒数 为了少消耗流量和电量，8小时自动更新一次
   private int anHour =2000;// 这是8小时的毫秒数 为了少消耗流量和电量，8小时自动更新一次


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {

        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                logUtils.d("定时任务启动"+ new Date().
                        toString());
            }
        }).start();
        AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
     //   int anHour = 60 * 60 * 1000; // 这是一小时的毫秒数
        int anHour =  30*1000; // 这是一小时的毫秒数
        long triggerAtTime = SystemClock.elapsedRealtime() + anHour;
        Intent i = new Intent(this, BootCompletedReceiver.class);
        PendingIntent pi = PendingIntent.getBroadcast(this, 0, i, 0);
        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime, pi);

        return super.onStartCommand(intent, flags, startId);
    }
}
