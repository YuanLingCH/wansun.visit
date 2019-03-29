package wansun.visit.android.boardCastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import wansun.visit.android.service.autoUpdataService;
import wansun.visit.android.utils.logUtils;

/**
 * 开机广播监听
 * Created by User on 2019/3/25.
 */

public class BootCompletedReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        logUtils.d("接收到广播了 "+intent.getAction());
            Intent i = new Intent(context, autoUpdataService.class);
            context.startService(i);


    }
}
