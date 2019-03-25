package wansun.visit.android.boardCastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import wansun.visit.android.global.waifangApplication;
import wansun.visit.android.utils.ToastUtil;
import wansun.visit.android.utils.logUtils;

/**
 * 开机广播监听
 * Created by User on 2019/3/25.
 */

public class BootCompletedReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED))
        {
            logUtils.d("开机广播监听");
            ToastUtil.showToast(waifangApplication.getContext(),"开机广播监听");
           // Intent newIntent = new Intent();
          //  newIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);  //注意，必须添加这个标记，否则启动会失败
          //  context.startActivity(newIntent);
        }
    }
}
