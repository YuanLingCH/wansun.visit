package wansun.visit.android.ui.activity;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.hardware.Camera;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.iceteck.silicompressorr.VideoCompress;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import wansun.visit.android.R;
import wansun.visit.android.api.apiManager;
import wansun.visit.android.db.fileInfo;
import wansun.visit.android.global.waifangApplication;
import wansun.visit.android.greendao.gen.fileInfoDao;
import wansun.visit.android.utils.NetWorkTesting;
import wansun.visit.android.utils.SharedUtils;
import wansun.visit.android.utils.SystemAppUtils;
import wansun.visit.android.utils.ToastUtil;
import wansun.visit.android.utils.dialogUtils;
import wansun.visit.android.utils.logUtils;
import wansun.visit.android.utils.vedioWindowsUtils;

import static wansun.visit.android.utils.floatWindownUtils.isFloatWindowOpAllowed;

/**
 *
 * 视频录制
 * Created by User on 2019/2/28.
 */

public class VideoRecorderActivity extends BaseActivity {
    private fileInfoDao dao;
    ImageView iv_visit_back;
    TextView tv_visit_tobar;
    ImageView image;
    dialogUtils utils;
    ImageView imag2;
    Button button;
    Button button2;
    Button button3,but_upload;
    TextView first;
    TextView tv_back;
    private long startTime, endTime;
    ProgressBar progressBar;

    String path;//视频录制输出地址
    private String outputDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath();
    JCVideoPlayerStandard videoplayer;
    //视频压缩数据地址
    private String currentOutputVideoPath = "/mnt/sdcard/out.mp4";
    private static final int REQUEST_CODE_FOR_RECORD_VIDEO = 5230;//录制视频请求码
    private static final int REQUEST_TAKE_PHOTO_PERMISSION = 1;
    Double videoLength=0.0;//视频时长
    String destPath;//视频压缩路径
    boolean isCompress=false;// 视频压缩
    boolean isVideo=false;   //视频录制标记
    Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            ToastUtil.showToast(VideoRecorderActivity.this,"图片上传完成");
            int what = msg.what;
            if (what==0){
                utils.cancleDialog();
                ToastUtil.showToast(VideoRecorderActivity.this,"上传视频失败");
            }else if (what==1){
                utils.cancleDialog();
                ToastUtil.showToast(VideoRecorderActivity.this,"上传视频成功");
            }else if (what==2){
                utils.cancleDialog();
                getFileSize(destPath);
                tv_back.setText(getFileSize(destPath));
                ToastUtil.showToast(VideoRecorderActivity.this,"视频压缩成功");
            }else if (what==3){
                utils.cancleDialog();
                ToastUtil.showToast(VideoRecorderActivity.this,"视频压缩失败");
            }else if (what==4){ //视频录制完成
                logUtils.d("视频录制完成url_file: "+url_file);
                first.setText(getFileSize(url_file));
                playVideo( url_file);
                String visitGuid = SharedUtils.getString("visitGuid");
                fileInfo info=new fileInfo(null,url_file,"1",System.currentTimeMillis(),visitGuid);  //1为视频
                dao.insert(info);
            }

        }
    };
    @Override
    protected int getLayoutId() {
        return R.layout.activity_video_record;
    }

    @Override
    protected void initView() {
        iv_visit_back= (ImageView) findViewById(R.id.iv_visit_back);
        tv_visit_tobar= (TextView) findViewById(R.id.tv_visit_tobar);
        tv_visit_tobar.setText("视频录制");

        videoplayer= (JCVideoPlayerStandard) findViewById(R.id.videoplayer);
        button = (Button) findViewById(R.id.button);

        button2 = (Button) findViewById(R.id.button2);

        button3 = (Button) findViewById(R.id.button3);

        first = (TextView) findViewById(R.id.first);
        tv_back= (TextView) findViewById(R.id.tv_back);
        imag2 = (ImageView) findViewById(R.id.imag2);
        but_upload= (Button) findViewById(R.id.but_upload);
        ////////////////////////////视频录制布局文件/////////////////////////////////////////////////////////////////////////
        LayoutInflater inflater=LayoutInflater.from(VideoRecorderActivity.this);
        View view = inflater.inflate(R.layout.activity_video_input,null);
        initialize(view);
    }
    @Override
    protected void onStart() {
        super.onStart();
        dao=waifangApplication.getInstence().getSession().getFileInfoDao();
    }
    @Override
    protected void initEvent() {
        iv_visit_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.in_from_left,R.anim.out_to_right);
            }
        });

        /**
         * 优化视频分辨率 480
         */
        button2.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View view) {
                isVideo=true;  //录制视频

                openFloat();

            }

        });




        imag2.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View view) {

                openView(currentOutputVideoPath);

            }

        });

            //视频压缩
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
           public void onClick(View v) {
                    if (!isVideo){
                        ToastUtil.showToast(VideoRecorderActivity.this,"请录制视频");
                    }else {
                        isVideo=false;
                WindowManager manager = getWindowManager();
                View view = LayoutInflater.from(waifangApplication.getContext()).inflate(R.layout.compress_loading_layout, null);
                utils = new dialogUtils(VideoRecorderActivity.this, manager, view);
                TextView tv= (TextView) view.findViewById(R.id.tv_load);
                tv.setText("视频压缩中...");
                progressBar= (ProgressBar)view. findViewById(R.id.progressBar);
                utils.getDialog();
                isCompress=true;
                String caseCode = SharedUtils.getString("caseCode");
              destPath = outputDir + File.separator + caseCode  + new SimpleDateFormat("yyyyMMdd_HHmmss", getLocale()).format(new Date()) + ".mp4";
                VideoCompress.compressVideoLow(url_file, destPath, new VideoCompress.CompressListener() {
                    @Override
                    public void onStart() {
                        startTime = System.currentTimeMillis();
                        setTime(startTime,"开始时间");

                    }

                    @Override
                    public void onSuccess() {
                        endTime = System.currentTimeMillis();
                        setTime(endTime,"结束时间");
                        logUtils.d("压缩后大小 = "+getFileSize(destPath));
                     //   openFile(new File(destPath));
                        logUtils.d("压缩后路径 "+destPath);
                        mHandler.sendEmptyMessage(2);

                    }

                    @Override
                    public void onFail() {
                        endTime = System.currentTimeMillis();
                        setTime(endTime,"失败时间");
                        mHandler.sendEmptyMessage(3);
                    }

                    @Override
                    public void onProgress(float percent) {
                        Log.i(TAG,String.valueOf(percent) + "%");
                        progressBar.setProgress((int) percent);

                    }
                });
              }
            }
        });

        /**
         * 上传视频到服务器
         */
        but_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doUploadToService();
            }
        });

    }

    private void permission() {
        List<String> permissionLists = new ArrayList<>();
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            permissionLists.add(Manifest.permission.CAMERA);
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            permissionLists.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            permissionLists.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (!permissionLists.isEmpty()) {//说明肯定有拒绝的权限

            ActivityCompat.requestPermissions(this, permissionLists.toArray(new String[permissionLists.size()]), REQUEST_TAKE_PHOTO_PERMISSION);

        } else {
            //  Toast.makeText(this, "权限都授权了",Toast.LENGTH_SHORT).show();
           // myVideoInputActivity.startActivityForResult(VideoRecorderActivity.this, REQUEST_CODE_FOR_RECORD_VIDEO,myVideoInputActivity.Q720);





        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {

            case REQUEST_TAKE_PHOTO_PERMISSION:
                if (grantResults.length > 0) {
                    for (int grantResult : grantResults) {
                        if (grantResult != PackageManager.PERMISSION_GRANTED) {
                            Toast.makeText(this, "某一个权限被拒绝了", Toast.LENGTH_SHORT).show();
                            return;
                        }

                     //   myVideoInputActivity.startActivityForResult(VideoRecorderActivity.this, REQUEST_CODE_FOR_RECORD_VIDEO,myVideoInputActivity.Q720);
                        openFloat();
                    }
                }
                break;

            default:

                break;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    /**
     * 上传视频
     */
    private void doUploadToService() {
        NetWorkTesting net=new NetWorkTesting(VideoRecorderActivity.this);
        if (net.isNetWorkAvailable()) {
            if (!isCompress){
                ToastUtil.showToast(VideoRecorderActivity.this,R.string.video_compress);
            }else {
                //上传图片到服务器
                isCompress=false;
                WindowManager manager = getWindowManager();
                View view = LayoutInflater.from(waifangApplication.getContext()).inflate(R.layout.loading_layout, null);
                utils = new dialogUtils(VideoRecorderActivity.this, manager, view);
                TextView tv= (TextView) view.findViewById(R.id.tv_load);
                tv.setText(R.string.upload_video);
                utils.getDialog();
                String caseCode = SharedUtils.getString("caseCode");
                String visitGuid = SharedUtils.getString("visitGuid");
                final String account = SharedUtils.getString("account");
                logUtils.d("account"+account);
                String id = SharedUtils.getString("id");
                File file = new File(destPath);
                final OkHttpClient okHttpClient = new OkHttpClient();
                    MultipartBody.Builder builder = new MultipartBody.Builder()
                            .setType(MultipartBody.FORM)
                            .addFormDataPart("caseCode",caseCode)
                            .addFormDataPart("visitGuid",visitGuid)
                            .addFormDataPart("uploaderName",account)
                            .addFormDataPart("uploaderId" ,id )
                            .addFormDataPart("uploadFile", file.getName(), RequestBody.create(MediaType.parse("application/octet-stream"), file));

                    RequestBody requestBody = builder.build();
                    final Request request = new Request.Builder()
                            .url(apiManager.videoUploadToService).post(requestBody).build();
                    new Thread(){
                        @Override
                        public void run() {
                            super.run();
                            Call call = okHttpClient.newCall(request);
                            call.enqueue(new Callback() {
                                @Override
                                public void onFailure(Call call, IOException e) {
                                    logUtils.d("视频上传错误"+e.toString());
                                    mHandler.sendEmptyMessage(0);

                                }

                                @Override
                                public void onResponse(Call call, Response response) throws IOException {
                                    ResponseBody body = response.body();

                                    logUtils.d("视频上传"+body.string());
                                        mHandler.sendEmptyMessage(1);


                                }
                            });
                        }
                    }.start();

                }
            }
        }




    private void setTime(Long time,String type){
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date=new Date(time);
        Log.i(TAG,type+" = "+dateFormat.format(date));
    }


    @Override
    protected void initData() {

    }

    @Override
    protected void initLise() {

    }
    private Locale getLocale() {
        Configuration config = getResources().getConfiguration();
        Locale sysLocale = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            sysLocale = getSystemLocale(config);
        } else {
            sysLocale = getSystemLocaleLegacy(config);
        }

        return sysLocale;
    }
    @SuppressWarnings("deprecation")
    public static Locale getSystemLocaleLegacy(Configuration config){
        return config.locale;
    }

    @TargetApi(Build.VERSION_CODES.N)
    public static Locale getSystemLocale(Configuration config){
        return config.getLocales().get(0);
    }




    /**

     * 录制视频回调

     * @param requestCode

     * @param resultCode

     * @param data

     */

    @Override

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_CODE_FOR_RECORD_VIDEO&&resultCode==RESULT_CANCELED){



        }

        if(requestCode==REQUEST_CODE_FOR_RECORD_VIDEO&&resultCode==RESULT_OK){

            String path = data.getStringExtra(myVideoInputActivity.INTENT_EXTRA_VIDEO_PATH);

            Log.e("TAG","地址："+path);
            //根据视频地址获取缩略图
            ToastUtil.showToast(VideoRecorderActivity.this,"视频地址："+path);

        }


        if (requestCode == 11) {
            if (isFloatWindowOpAllowed(this)) {//已经开启
                initLayout();
            } else {
                logUtils .e("开启悬浮窗失败");
                ToastUtil.showToast(this,"请手动开启悬浮窗允许");
            }
        } else if (requestCode == 12) {
            if (Build.VERSION.SDK_INT >= 23) {
                if (!Settings.canDrawOverlays(VideoRecorderActivity.this)) {
                    ToastUtil.showToast(this,"权限授予失败,无法开启悬浮窗");
                } else {
                    initLayout();
                }
            }
        }
    }

    /**
     * 播放视频
     * @param path
     */
    private void playVideo(String path) {
        videoplayer.setVisibility(View.VISIBLE);
        videoplayer.setUp(path, JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, "");
       // videoplayer.thumbImageView.setImage("http://p.qpic.cn/videoyun/0/2449_43b6f696980311e59ed467f22794e792_1/640");

    }


    public void openView(String path){

        if(TextUtils.isEmpty(path)){



            return;

        }

        File file = new File(path);

        SystemAppUtils.openFile(file,this);


    }







    private String getFileSize(String path) {

        File f = new File(path);

        if (!f.exists()) {

            return "0 MB";

        } else {

            long size = f.length();

            return (size / 1024f) / 1024f + "MB";

        }

    }



    @Override
    public void onBackPressed() {

        if (videoplayer.backPress()) {
            return;
        }
        super.onBackPressed();

    }

    @Override
    protected void onPause() {
        super.onPause();
        videoplayer.releaseAllVideos();
    }

    private void openFile(File file) {
        try {
            Intent intent = new Intent();
            //设置intent的Action属性
            intent.setAction(Intent.ACTION_VIEW);
            if (Build.VERSION.SDK_INT>=24){
                intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                Uri contentUri= FileProvider.getUriForFile(this,"com.babyinhand.fileprovider",file);
                String type = getMIMEType(file);
                intent.setDataAndType(contentUri,type);
            }else {
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                //获取文件file的MIME类型
                String type = getMIMEType(file);
                //设置intent的data和Type属性。
                intent.setDataAndType(/*uri*/Uri.fromFile(file), type);
            }
            //跳转
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(VideoRecorderActivity.this, "不能打开视频文件", Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * 根据文件后缀名获得对应的MIME类型。
     *
     * @param file
     */
    private String getMIMEType(File file) {

        String type = "*/*";
        String fName = file.getName();
        //获取后缀名前的分隔符"."在fName中的位置。
        int dotIndex = fName.lastIndexOf(".");
        if (dotIndex < 0) {
            return type;
        }
        /* 获取文件的后缀名*/
        String end = fName.substring(dotIndex, fName.length()).toLowerCase();
        if (end == "") return type;
        //在MIME和文件类型的匹配表中找到对应的MIME类型。
        for (int i = 0; i < MIME_MapTable.length; i++) {
            if (end.equals(MIME_MapTable[i][0]))
                type = MIME_MapTable[i][1];
        }
        return type;
    }

    private final String[][] MIME_MapTable = {
            //{后缀名，MIME类型}
            {".3gp", "video/3gpp"},
            {".apk", "application/vnd.android.package-archive"},
            {".asf", "video/x-ms-asf"},
            {".avi", "video/x-msvideo"},
            {".bin", "application/octet-stream"},
            {".bmp", "image/bmp"},
            {".c", "text/plain"},
            {".class", "application/octet-stream"},
            {".conf", "text/plain"},
            {".cpp", "text/plain"},
            {".doc", "application/msword"},
            {".docx", "application/vnd.openxmlformats-officedocument.wordprocessingml.document"},
            {".xls", "application/vnd.ms-excel"},
            {".xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"},
            {".exe", "application/octet-stream"},
            {".gif", "image/gif"},
            {".gtar", "application/x-gtar"},
            {".gz", "application/x-gzip"},
            {".h", "text/plain"},
            {".htm", "text/html"},
            {".html", "text/html"},
            {".jar", "application/java-archive"},
            {".java", "text/plain"},
            {".jpeg", "image/jpeg"},
            {".jpg", "image/jpeg"},
            {".js", "application/x-javascript"},
            {".log", "text/plain"},
            {".m3u", "audio/x-mpegurl"},
            {".m4a", "audio/mp4a-latm"},
            {".m4b", "audio/mp4a-latm"},
            {".m4p", "audio/mp4a-latm"},
            {".m4u", "video/vnd.mpegurl"},
            {".m4v", "video/x-m4v"},
            {".mov", "video/quicktime"},
            {".mp2", "audio/x-mpeg"},
            {".mp3", "audio/x-mpeg"},
            {".mp4", "video/mp4"},
            {".mpc", "application/vnd.mpohun.certificate"},
            {".mpe", "video/mpeg"},
            {".mpeg", "video/mpeg"},
            {".mpg", "video/mpeg"},
            {".mpg4", "video/mp4"},
            {".mpga", "audio/mpeg"},
            {".msg", "application/vnd.ms-outlook"},
            {".ogg", "audio/ogg"},
            {".pdf", "application/pdf"},
            {".png", "image/png"},
            {".pps", "application/vnd.ms-powerpoint"},
            {".ppt", "application/vnd.ms-powerpoint"},
            {".pptx", "application/vnd.openxmlformats-officedocument.presentationml.presentation"},
            {".prop", "text/plain"},
            {".rc", "text/plain"},
            {".rmvb", "audio/x-pn-realaudio"},
            {".rtf", "application/rtf"},
            {".sh", "text/plain"},
            {".tar", "application/x-tar"},
            {".tgz", "application/x-compressed"},
            {".txt", "text/plain"},
            {".wav", "audio/x-wav"},
            {".wma", "audio/x-ms-wma"},
            {".wmv", "audio/x-ms-wmv"},
            {".wps", "application/vnd.ms-works"},
            {".xml", "text/plain"},
            {".z", "application/x-compress"},
            {".zip", "application/x-zip-compressed"},
            {"", "*/*"}
    };

    /////////////////////////////////////////////////////////////////////视频录制/////////////////////////////////////////////////////////////////////////////////////////
    private CameraPreview mPreview;
    private Camera mCamera;
    private MediaRecorder mediaRecorder;
    private String url_file;
    private static boolean flash = false;
    private static boolean cameraFront = false;
    private long countUp;
    private int quality = CamcorderProfile.QUALITY_480P;

    private static final int FOCUS_AREA_SIZE = 500;

    String TAG="VideoInputActivity";



    public static final String INTENT_EXTRA_VIDEO_PATH = "intent_extra_video_path";//录制的视频路径
    public static final int RESULT_CODE_FOR_RECORD_VIDEO_FAILED = 3;//视频录制出错

    public static int Q480 = CamcorderProfile.QUALITY_480P;
    public static int Q720 = CamcorderProfile.QUALITY_720P;
    public static int Q1080 = CamcorderProfile.QUALITY_1080P;
    public static int Q21600 = CamcorderProfile.QUALITY_2160P;

    //初始化布局文件
    public  void initLayout(){
        // 相机预览
        previewCamera();
        Log.d("TAG","log");
        vedioWindowsUtils utils=new vedioWindowsUtils(VideoRecorderActivity.this,rl,suoxiao,buttondelect);
        utils.showPopupWindow(VideoRecorderActivity.this);
    }

    ImageView button_ChangeCamera;  // 前后摄像头切换
    LinearLayout cameraPreview;    //视频预览界面
    ImageView buttonCapture ;  //录制视频按钮
    ImageView buttonFlash ;   //开启闪关灯
    Chronometer textChrono;  //计时器
    ImageView chronoRecordingImage;  //计时器图片
    ImageView suoxiao,buttondelect;
    RelativeLayout rl;   //整体布局
    //点击对焦
    public void initialize(View view) {
        button_ChangeCamera = (ImageView) view.findViewById(R.id.button_ChangeCamera);
        cameraPreview = (LinearLayout) view. findViewById(R.id.camera_preview);
        buttonCapture = (ImageView) view. findViewById(R.id.button_capture);
        buttonFlash= (ImageView)  view.findViewById(R.id.buttonFlash);
        chronoRecordingImage= (ImageView) view. findViewById(R.id.chronoRecordingImage);
        textChrono= (Chronometer)  view.findViewById(R.id.textChrono);
        buttonFlash.setOnClickListener(flashListener);
        mPreview = new CameraPreview(VideoRecorderActivity.this, mCamera);
        cameraPreview.addView(mPreview);
        buttonCapture.setOnClickListener(captrureListener);
        button_ChangeCamera.setOnClickListener(switchCameraListener);
        suoxiao=(ImageView) view. findViewById(R.id.suoxiao);
        buttondelect= (ImageView) view.findViewById(R.id.buttondelect); //删除窗口
        rl= (RelativeLayout) view. findViewById(R.id.rl);
        cameraPreview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    try {
                        focusOnTouch(event);
                    } catch (Exception e) {
                        //    Log.i(TAG, getString(R.string.fail_when_camera_try_autofocus, e.toString()));
                        //do nothing
                    }
                }
                return false;
            }
        });

    }

    private void focusOnTouch(MotionEvent event) {
        if (mCamera != null) {
            Camera.Parameters parameters = mCamera.getParameters();
            if (parameters.getMaxNumMeteringAreas() > 0) {
                Rect rect = calculateFocusArea(event.getX(), event.getY());
                parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
                List<Camera.Area> meteringAreas = new ArrayList<Camera.Area>();
                meteringAreas.add(new Camera.Area(rect, 800));
                parameters.setFocusAreas(meteringAreas);
                mCamera.setParameters(parameters);
                mCamera.autoFocus(mAutoFocusTakePictureCallback);
            } else {
                mCamera.autoFocus(mAutoFocusTakePictureCallback);
            }
        }
    }

    private Rect calculateFocusArea(float x, float y) {
        int left = clamp(Float.valueOf((x / mPreview.getWidth()) * 2000 - 1000).intValue(), FOCUS_AREA_SIZE);
        int top = clamp(Float.valueOf((y / mPreview.getHeight()) * 2000 - 1000).intValue(), FOCUS_AREA_SIZE);
        return new Rect(left, top, left + FOCUS_AREA_SIZE, top + FOCUS_AREA_SIZE);
    }



    private int clamp(int touchCoordinateInCameraReper, int focusAreaSize) {
        int result;
        if (Math.abs(touchCoordinateInCameraReper) + focusAreaSize / 2 > 1000) {
            if (touchCoordinateInCameraReper > 0) {
                result = 1000 - focusAreaSize / 2;
            } else {
                result = -1000 + focusAreaSize / 2;
            }
        } else {
            result = touchCoordinateInCameraReper - focusAreaSize / 2;
        }
        return result;
    }

    private Camera.AutoFocusCallback mAutoFocusTakePictureCallback = new Camera.AutoFocusCallback() {
        @Override
        public void onAutoFocus(boolean success, Camera camera) {
            if (success) {
                // do something...
                Log.i("tap_to_focus", "success!");
            } else {
                // do something...
                Log.i("tap_to_focus", "fail!");
            }
        }
    };

   public void onResume() {
        super.onResume();
       permission();


    }

    private void previewCamera() {
        if (!hasCamera(getApplicationContext())) {
            //这台设备没有发现摄像头
          Toast.makeText(getApplicationContext(), "没有发现摄像头"
                    , Toast.LENGTH_SHORT).show();
            setResult(RESULT_CANCELED);
            releaseCamera();
            releaseMediaRecorder();
            finish();
        }
        if (mCamera == null) {
            releaseCamera();
            final boolean frontal = cameraFront;

            int cameraId = findFrontFacingCamera();
            if (cameraId < 0) {
                //前置摄像头不存在
                switchCameraListener = new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //  Toast.makeText(VideoInputActivity.this, R.string.dont_have_front_camera, Toast.LENGTH_SHORT).show();
                    }
                };

                //尝试寻找后置摄像头
                cameraId = findBackFacingCamera();
                if (flash) {
                    mPreview.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                    //    buttonFlash.setImageResource(R.mipmap.ic_flash_on_white);
                }
            } else if (!frontal) {
                cameraId = findBackFacingCamera();
                if (flash) {
                    mPreview.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                    //  buttonFlash.setImageResource(R.mipmap.ic_flash_on_white);
                }
            }

            mCamera = Camera.open(cameraId);
            mPreview.refreshCamera(mCamera);


        }
    }

    //计时器
    private void startChronometer() {
        textChrono.setVisibility(View.VISIBLE);
        final long startTime = SystemClock.elapsedRealtime();
        textChrono.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer arg0) {
                countUp = (SystemClock.elapsedRealtime() - startTime) / 1000;
                if (countUp % 2 == 0) {
                    chronoRecordingImage.setVisibility(View.VISIBLE);
                } else {
                    chronoRecordingImage.setVisibility(View.INVISIBLE);
                }

                String asText = String.format("%02d", countUp / 60) + ":" + String.format("%02d", countUp % 60);
                textChrono.setText(asText);
            }
        });
        textChrono.start();
    }

    /**
     * 找前置摄像头,没有则返回-1
     *
     * @return cameraId
     */
    private int findFrontFacingCamera() {
        int cameraId = -1;
        //获取摄像头个数
        int numberOfCameras = Camera.getNumberOfCameras();
        for (int i = 0; i < numberOfCameras; i++) {
            Camera.CameraInfo info = new Camera.CameraInfo();
            Camera.getCameraInfo(i, info);
            if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
                cameraId = i;
                cameraFront = true;
                break;
            }
        }
        return cameraId;
    }

    /**
     * 找后置摄像头,没有则返回-1
     *
     * @return cameraId
     */
    private int findBackFacingCamera() {
        int cameraId = -1;
        //获取摄像头个数
        int numberOfCameras = Camera.getNumberOfCameras();
        for (int i = 0; i < numberOfCameras; i++) {
            Camera.CameraInfo info = new Camera.CameraInfo();
            Camera.getCameraInfo(i, info);
            if (info.facing == Camera.CameraInfo.CAMERA_FACING_BACK) {
                cameraId = i;
                cameraFront = false;
                break;
            }
        }
        return cameraId;
    }



    //检查设备是否有摄像头
    private boolean hasCamera(Context context) {
        if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
            return true;
        } else {
            return false;
        }
    }

    private void releaseCamera() {
        if (mCamera != null) {
            mCamera.release();
            mCamera = null;
        }
    }

    private void releaseMediaRecorder() {
        if (mediaRecorder != null) {
            mediaRecorder.reset();
            mediaRecorder.release();
            mediaRecorder = null;
            mCamera.lock();
        }
    }


    private boolean prepareMediaRecorder() {
        mediaRecorder = new MediaRecorder();
        mCamera.unlock();
        mediaRecorder.setCamera(mCamera);
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.CAMCORDER);
        mediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            if (cameraFront) {
                mediaRecorder.setOrientationHint(270);
            } else {
                mediaRecorder.setOrientationHint(90);
            }
        }

        mediaRecorder.setProfile(CamcorderProfile.get(quality));

        File file1 =  getOutputMediaFile();
        if (file1.exists()) {
            file1.delete();
        }
//        File file = new File("/mnt/sdcard/videokit");
//        if (!file.exists()) {
//            file.mkdirs();
//        }
//        Date d = new Date();
//        String timestamp = String.valueOf(d.getTime());

//        url_file = "/mnt/sdcard/videokit/in.mp4";

//
//        File file1 = new File(url_file);
//        if (file1.exists()) {
//            file1.delete();
//        }

        mediaRecorder.setOutputFile(file1.toString());
        try {
            mediaRecorder.prepare();
        } catch (IllegalStateException e) {
            e.printStackTrace();
            releaseMediaRecorder();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            releaseMediaRecorder();
            return false;
        }
        return true;

    }

    private void stopChronometer() {
        textChrono.stop();
        chronoRecordingImage.setVisibility(View.INVISIBLE);
        textChrono.setVisibility(View.INVISIBLE);
    }

    boolean recording = false;
    //切换前置后置摄像头
    View.OnClickListener switchCameraListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!recording) {
                int camerasNumber = Camera.getNumberOfCameras();
                if (camerasNumber > 1) {
                    releaseCamera();
                    chooseCamera();
                } else {
                    //只有一个摄像头不允许切换
                   /* Toast.makeText(getApplicationContext(), R.string.only_have_one_camera
                            , Toast.LENGTH_SHORT).show();*/
                }
            }
        }
    };

    View.OnClickListener captrureListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.d("TAG","点击了录制");
            if (recording) {
                //如果正在录制点击这个按钮表示录制完成
                mediaRecorder.stop(); //停止
                stopChronometer();
                buttonCapture.setImageResource(R.mipmap.shipin);
                changeRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
                releaseMediaRecorder();
                //      Toast.makeText(VideoInputActivity.this, R.string.video_captured, Toast.LENGTH_SHORT).show();
                recording = false;
              Intent intent = new Intent();
                intent.putExtra(INTENT_EXTRA_VIDEO_PATH, url_file);
                setResult(RESULT_OK, intent);
                releaseCamera();
                releaseMediaRecorder();
                vedioWindowsUtils.hidePopupWindow();
              //  finish();
                mHandler.sendEmptyMessage(4);
                Log.d("TAG","视频录制停止地址"+url_file);
            } else {
                //准备开始录制视频
                Log.d("TAG","开始录制");
                if (!prepareMediaRecorder()) {
                    //   Toast.makeText(VideoInputActivity.this, getString(R.string.camera_init_fail), Toast.LENGTH_SHORT).show();
                    setResult(RESULT_CODE_FOR_RECORD_VIDEO_FAILED);
                    releaseCamera();
                    releaseMediaRecorder();
                    finish();
                }
                Log.d("TAG","开始录制1");
                //开始录制视频
                runOnUiThread(new Runnable() {
                    public void run() {
                        // If there are stories, add them to the table
                        try {
                            Log.d("TAG","开始录制2");
                            mediaRecorder.start();
                            startChronometer();
                            Log.d("TAG","开始录制3");
                            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                                changeRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                            } else {
                                changeRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                            }
                            buttonCapture.setImageResource(R.mipmap.luzhi);
                        } catch (final Exception ex) {
                            Log.i("---", "Exception in thread");
                            setResult(RESULT_CODE_FOR_RECORD_VIDEO_FAILED);
                            releaseCamera();
                            releaseMediaRecorder();
                            finish();
                        }
                    }
                });
                recording = true;
            }
        }
    };
    private void changeRequestedOrientation(int orientation) {
        setRequestedOrientation(orientation);
    }
    //闪光灯
    View.OnClickListener flashListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!recording && !cameraFront) {
                if (flash) {
                    flash = false;
                    buttonFlash.setImageResource(R.mipmap.shanoff);
                    setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
                } else {
                    flash = true;
                    buttonFlash.setImageResource(R.mipmap.shan);
                    setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                }
            }
        }
    };

    //选择摄像头
    public void chooseCamera() {
        if (cameraFront) {
            //当前是前置摄像头
            int cameraId = findBackFacingCamera();
            if (cameraId >= 0) {
                // open the backFacingCamera
                // set a picture callback
                // refresh the preview
                mCamera = Camera.open(cameraId);
                // mPicture = getPictureCallback();
                mPreview.refreshCamera(mCamera);

            }
        } else {
            //当前为后置摄像头
            int cameraId = findFrontFacingCamera();
            if (cameraId >= 0) {
                // open the backFacingCamera
                // set a picture callback
                // refresh the preview
                mCamera = Camera.open(cameraId);
                if (flash) {
                    flash = false;
                    //  buttonFlash.setImageResource(R.mipmap.ic_flash_off_white);
                    mPreview.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
                }
                // mPicture = getPictureCallback();
                mPreview.refreshCamera(mCamera);

            }
        }
    }

    //闪光灯
    public void setFlashMode(String mode) {
        try {
            if (getPackageManager().hasSystemFeature(
                    PackageManager.FEATURE_CAMERA_FLASH)
                    && mCamera != null
                    && !cameraFront) {

                mPreview.setFlashMode(mode);
                mPreview.refreshCamera(mCamera);

            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "闪光灯开启失败",
                    Toast.LENGTH_SHORT).show();
        }
    }


    /** Create a File for saving an image or video */
    private File getOutputMediaFile(){

//        return  new File(getContext().getExternalCacheDir().getAbsolutePath() + "/" + fileName);
        String appName = getPackageName();
        File dir = new File(Environment.getExternalStorageDirectory() + "/" +appName);
        if (!dir.exists()){
            dir.mkdir();

        }
        File dirone=new File(dir+"/"+"video");
        if (!dirone.exists()){
            dirone.mkdir();
        }
        url_file = dirone+ "/video_" + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + ".mp4";
        Log.i("filePath",url_file);
        return  new File(url_file);
    }


    ////////////////////////////////判断悬浮窗///////////////////////////////////////////////////////////////////
    /**
     * 请求用户给予悬浮窗的权限
     */
    public void requestPermission() {
        if (isFloatWindowOpAllowed(this)) {//已经开启
            initLayout();
        } else {
            openSetting();
        }
    }

    /**
     * 打开权限设置界面
     */
    public void openSetting() {
        //  try {

        startActivityForResult(new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName())), 11);



/*            Intent localIntent = new Intent(
                    "miui.intent.action.APP_PERM_EDITOR");
            localIntent.setClassName("com.miui.securitycenter",
                    "com.miui.permcenter.permissions.AppPermissionsEditorActivity");
            localIntent.putExtra("extra_pkgname", getPackageName());
            startActivityForResult(localIntent, 11);
            logUtils .e("启动小米悬浮窗设置界面");
        } catch (ActivityNotFoundException localActivityNotFoundException) {
            Intent intent1 = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            Uri uri = Uri.fromParts("package", getPackageName(), null);
            intent1.setData(uri);
            startActivityForResult(intent1, 11);
            logUtils .e("启动悬浮窗界面");
        }*/
    }



    public void openFloat(){
        //开启悬浮窗前先请求权限
        if ("Xiaomi".equals(Build.MANUFACTURER)) {//小米手机
            logUtils .e("小米手机");
            requestPermission();
        } else if ("Meizu".equals(Build.MANUFACTURER)) {//魅族手机
            logUtils .e("魅族手机");
            requestPermission();
        } else {//其他手机
            logUtils .e("其他手机");
            if (Build.VERSION.SDK_INT >= 23) {
                if (!Settings.canDrawOverlays(this)) {
                    Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
                    startActivityForResult(intent, 12);
                } else {
                    initLayout();
                }
            } else {
                initLayout();
            }
        }
    }

}
