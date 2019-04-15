package wansun.visit.android.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.PixelFormat;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * Created by User on 2019/4/10.
 */

public class vedioWindowsUtils {
    public static WindowManager windowManager=null;
    private static WindowManager.LayoutParams params;
    public static  boolean isShow; //是否显示
    Activity activity;
    private static View mView = null;
    Button but;
    RelativeLayout rl;
    ImageView suoxiao;
    ImageView buttondelect;
    //构造方法
    public vedioWindowsUtils(Activity activity, RelativeLayout rl, ImageView suoxiao,ImageView buttondelect) {
        this.activity=activity;
        this.rl=rl;
        this.suoxiao=suoxiao;
        this.buttondelect=buttondelect;
        Log.d("TAG","vedioWindowsUtils");
    }
    /**
     * 显示弹框
     */
    public void  showPopupWindow(Context context){
        if (isShow){
            return;
        }
        isShow=true;
        windowManager= (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        params= new WindowManager.LayoutParams();
        mView = setUpView(context, "");
        params.type=WindowManager.LayoutParams.TYPE_SYSTEM_ALERT | WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY;
        // 设置flag
        int flags = canTouchFlags;
        // | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;

        // 如果设置了WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE，弹出的View收不到Back键的事件

        params.flags = flags;

        // 不设置这个弹出框的透明遮罩显示为黑色
         params.format = PixelFormat.TRANSLUCENT;
        // FLAG_NOT_TOUCH_MODAL不阻塞事件传递到后面的窗口

        // 设置 FLAG_NOT_FOCUSABLE 悬浮窗口较小时，后面的应用图标由不可长按变为可长按

        // 不设置这个flag的话，home页的划屏会有问题

        params.width = WindowManager.LayoutParams.MATCH_PARENT;

        params.height = WindowManager.LayoutParams.MATCH_PARENT;

     //   params.gravity = Gravity.TOP|Gravity.RIGHT;
        params.gravity = Gravity.TOP;
        windowManager.addView(mView, params);
    }
    ;
    private View setUpView(Context context, String s) {
        // View view = LayoutInflater.from(context).inflate(R.layout.fragment_record_audio, null);
        Log.d("TAG","setUpView");

        //   TextView showTv = (TextView) view.findViewById(R.id.tv_showinpop);

        //  showTv.setText(showtxt);

        initView(rl);

        rl.setOnTouchListener(new View.OnTouchListener() {

            private float lastX; //上一次位置的X.Y坐标

            private float lastY;

            private float nowX;  //当前移动位置的X.Y坐标

            private float nowY;

            private float tranX; //悬浮窗移动位置的相对值

            private float tranY;



            @Override

            public boolean onTouch(View v, MotionEvent event) {
                Log.d("TAG","触摸监听拖动控件");
                boolean ret = false;

                switch (event.getAction()) {

                    case MotionEvent.ACTION_DOWN:

                        // 获取按下时的X，Y坐标

                        lastX = event.getRawX();

                        lastY = event.getRawY();

                        ret = true;

                        break;

                    case MotionEvent.ACTION_MOVE:

                        // 获取移动时的X，Y坐标

                        nowX = event.getRawX();

                        nowY = event.getRawY();

                        // 计算XY坐标偏移量

                        tranX = nowX - lastX;

                        tranY = nowY - lastY;

                        params.x += tranX;

                        params.y += tranY;



                        //更新悬浮窗位置

                        windowManager.updateViewLayout(mView, params);

                        //记录当前坐标作为下一次计算的上一次移动的位置坐标

                        lastX = nowX;

                        lastY = nowY;



                        break;

                    case MotionEvent.ACTION_UP:

                        break;

                }

                return ret;

            }

        });



        return rl;

    }
    boolean isChickflag=false;
    private void initView(View view) {
        Log.d("TAG","initView");
        suoxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!isChickflag){
                    Log.d("TAG","点击了缩小按钮");
                    params.width=400;
                    params.height=400;
                    isChickflag=true;
                }else {
                    Log.d("TAG","点击放大");
                    params.width=WindowManager.LayoutParams.MATCH_PARENT;
                    params.height=WindowManager.LayoutParams.MATCH_PARENT;
                    isChickflag=false;
                }

                windowManager.updateViewLayout(mView, params);
            }
        });
        buttondelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hidePopupWindow();
            }
        });

    }


    private static int canTouchFlags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE

            | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL;



    private static int notTouchFlags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE|

            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE;





    /**

     * 设置是否可响应点击事件

     *

     * @param isTouchable

     */

    public static void setTouchable(boolean isTouchable) {

        if (isTouchable) {
            Log.d("TAG","触摸事件");
            params.flags = canTouchFlags;
            params.width=300;
            params.height=300;

        } else {
            Log.d("TAG","触摸事件1");
            params.flags = notTouchFlags;

        }

        windowManager.updateViewLayout(mView, params);
        Log.d("TAG","触摸事件2");


    }





    /**

     * 隐藏弹出框

     */

    public static void hidePopupWindow() {

        if (isShow && null != mView) {

            windowManager.removeView(mView);

            isShow= false;

        }

    }

}
