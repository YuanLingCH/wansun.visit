package wansun.visit.android.utils;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.PixelFormat;
import android.os.Environment;
import android.os.SystemClock;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.File;

import soundrecorderutils.RecordingService;
import wansun.visit.android.R;

/**

 * 悬浮窗工具类

 * created by Pumpkin at 17/3/28

 */

public class WindowsUitlity {

    private static String TAG = WindowsUitlity.class.getSimpleName();

    private static WindowManager mWindowManager = null;

    private static WindowManager.LayoutParams params;

    public static Boolean isShown = false;

    private static View mView = null;
    private boolean mStartRecording = true;
    private boolean mPauseRecording = true;

    long timeWhenPaused = 0;

    private com.melnykov.fab.FloatingActionButton mFabRecord;
    private Chronometer mChronometerTime;
    private ImageView mIvClose;


     Activity activity;
    public WindowsUitlity(Activity activity) {
        this.activity=activity;
    }

    /**

     * 显示弹出框

     *

     * @param context

     */

    @SuppressWarnings("WrongConstant")

    public  void showPopupWindow(final Context context, String showtxt) {
        logUtils.d("添加了view1");
        if (isShown) {

            return;

        }
        logUtils.d("添加了view2");
        isShown = true;

        // 获取WindowManager

        mWindowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        logUtils.d("添加了view");
        mView = setUpView(context, showtxt);
        params = new WindowManager.LayoutParams();

        // 类型，系统提示以及它总是出现在应用程序窗口之上。
//WindowManager.LayoutParams.TYPE_SYSTEM_ALERT
        params.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT |

                WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY;



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

        params.width = WindowManager.LayoutParams.WRAP_CONTENT;

        params.height = WindowManager.LayoutParams.WRAP_CONTENT;

        params.gravity = Gravity.TOP;

        mWindowManager.addView(mView, params);

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

            params.flags = canTouchFlags;

        } else {

            params.flags = notTouchFlags;

        }

        mWindowManager.updateViewLayout(mView, params);



    }





    /**

     * 隐藏弹出框

     */

    public static void hidePopupWindow() {

        if (isShown && null != mView) {

            mWindowManager.removeView(mView);

            isShown = false;

        }

    }



    public static void setShowTxt(String txt) {

        try {

          //  TextView showTv = (TextView) mView.findViewById(R.id.tv_showinpop);

          //  showTv.setText(txt);

            mWindowManager.updateViewLayout(mView, params);

        }catch (Exception e){

        //    Log.d(TAG, "setShowTxt: 更新悬浮框错误");

            e.printStackTrace();

            if(e.getMessage().contains("not attached to window manager")){

                mWindowManager.addView(mView, params);

            }

        }

    }





    public static void setShowImg(Bitmap bitmap) {

        try {

        //    ImageView showImg = (ImageView) mView.findViewById(R.id.iv_showinpop);

         //   showImg.setImageBitmap(bitmap);

            mWindowManager.updateViewLayout(mView, params);

        }catch (Exception e){

            Log.d(TAG, "setShowTxt: 更新悬浮框错误");

            e.printStackTrace();

            if(e.getMessage().contains("not attached to window manager")){

                mWindowManager.addView(mView, params);

            }

        }

    }



    static LinearLayout rl_drag_showinpop;



    public  View setUpView(final Context context, String showtxt) {

        View view = LayoutInflater.from(context).inflate(R.layout.fragment_record_audio, null);


        //   TextView showTv = (TextView) view.findViewById(R.id.tv_showinpop);

     //  showTv.setText(showtxt);

        initView(view);

        rl_drag_showinpop = (LinearLayout) view.findViewById(R.id.record_audio_cv);

        rl_drag_showinpop.setVisibility(View.VISIBLE);
        rl_drag_showinpop.setOnTouchListener(new View.OnTouchListener() {

            private float lastX; //上一次位置的X.Y坐标

            private float lastY;

            private float nowX;  //当前移动位置的X.Y坐标

            private float nowY;

            private float tranX; //悬浮窗移动位置的相对值

            private float tranY;



            @Override

            public boolean onTouch(View v, MotionEvent event) {

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

                        mWindowManager.updateViewLayout(mView, params);

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



        return view;

    }

    private void initView(final View view) {
        mChronometerTime = (Chronometer) view.findViewById(R.id.record_audio_chronometer_time);
        mFabRecord = (com.melnykov.fab.FloatingActionButton) view.findViewById(R.id.record_audio_fab_record);
        mIvClose = (ImageView) view.findViewById(R.id.record_audio_iv_close);
        mFabRecord.setMinimumWidth(25);
        mFabRecord.setMinimumHeight(25);
        mFabRecord.setColorNormal(activity.getResources().getColor(R.color.colorPrimary));
        mFabRecord.setColorPressed(activity.getResources().getColor(R.color.colorPrimaryDark));
        mFabRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
//                        != PackageManager.PERMISSION_GRANTED) {
//                    ActivityCompat.requestPermissions(getActivity()
//                            , new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO}, 1);
//                }else {
                onRecord(mStartRecording);
                mStartRecording = !mStartRecording;
//                }

            }
        });
        /**
         * 关掉录音
         */
        mIvClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logUtils.d("关掉录音");
                isShown=false;

            rl_drag_showinpop.setVisibility(View.GONE);
                stopRecord();
                hidePopupWindow();

            }
        });
    }

    private void stopRecord() {
        if (intent!=null){
        String flags = intent.getFlags()+"";
        if (flags.equals("1")){
            activity.stopService(intent);
            mChronometerTime.stop();
            timeWhenPaused = 0;
        }
        }

    }

    Intent intent;
    private void onRecord(boolean start) {

       intent = new Intent(activity, RecordingService.class);
        intent.addFlags(1);  //1 表示有录音

        if (start) {
            // start recording
            mFabRecord.setImageResource(R.drawable.ic_media_stop);
            //mPauseButton.setVisibility(View.VISIBLE);
            Toast.makeText(activity, "开始录音...", Toast.LENGTH_SHORT).show();
            File folder = new File(Environment.getExternalStorageDirectory() + "/SoundRecorder");
            if (!folder.exists()) {
                //folder /SoundRecorder doesn't exist, create the folder
                folder.mkdir();
            }

            //start Chronometer
            mChronometerTime.setBase(SystemClock.elapsedRealtime());
            mChronometerTime.start();

            //start RecordingService
            activity.startService(intent);   //启动服务录音
            //keep screen on while recording
//            getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        } else {
            //stop recording
            mFabRecord.setImageResource(R.drawable.ic_mic_white_36dp);
            //mPauseButton.setVisibility(View.GONE);

            Toast.makeText(activity, "录音结束...", Toast.LENGTH_SHORT).show();
            stopRecord();

            //allow the screen to turn off again once recording is finished
            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        }
    }






}

