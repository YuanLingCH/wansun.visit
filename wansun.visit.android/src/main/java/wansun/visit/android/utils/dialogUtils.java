package wansun.visit.android.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/**
 * 加载对话框
 * Created by User on 2019/2/13.
 */

public class dialogUtils {
    Context mContext;
    AlertDialog dialog;
    WindowManager manager;
    View view;

    public dialogUtils(Context mContext, WindowManager manager,View view) {
        this.mContext=mContext;
        this.manager=manager;
        this.view=view;
    }

    public  void getDialog(){
        dialog = new AlertDialog.Builder(mContext)
                .setView(view)
                .setCancelable(false)
                .create();
        dialog.setCanceledOnTouchOutside(false);
        Window window = dialog.getWindow();
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
        Display defaultDisplay = manager.getDefaultDisplay();
        android.view.WindowManager.LayoutParams p = dialog.getWindow().getAttributes();  //获取对话框当前的参数值
        p.width = (int) (defaultDisplay.getWidth() * 0.8);
        dialog.getWindow().setAttributes(p);     //设置生效
    }

        public  void cancleDialog(){
            dialog.cancel();

        }


}
