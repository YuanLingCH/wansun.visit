package wansun.visit.android.ui.activity;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.iceteck.silicompressorr.VideoCompress;
import com.sh.shvideolibrary.VideoInputActivity;
import com.sh.shvideolibrary.VideoInputDialog;

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

/**
 *
 * 视频录制
 * Created by User on 2019/2/28.
 */

public class VideoRecorderActivity extends BaseActivity implements VideoInputDialog.VideoCall {
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
    static String TAG="MainActivity";
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
            utils.cancleDialog();
            ToastUtil.showToast(VideoRecorderActivity.this,"图片上传完成");
            int what = msg.what;
            if (what==0){
                ToastUtil.showToast(VideoRecorderActivity.this,"上传视频失败");
            }else if (what==1){
                ToastUtil.showToast(VideoRecorderActivity.this,"上传视频成功");
            }else if (what==2){
                getFileSize(destPath);
                tv_back.setText(getFileSize(destPath));
                ToastUtil.showToast(VideoRecorderActivity.this,"视频压缩成功");
            }else if (what==3){
                ToastUtil.showToast(VideoRecorderActivity.this,"视频压缩失败");
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
        button.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View view) {

                //显示视频录制控件

                VideoInputDialog.show(getSupportFragmentManager(),VideoRecorderActivity.this,VideoInputDialog.Q720,VideoRecorderActivity.this);

            }

        });
        /**
         * 优化视频分辨率 480
         */
        button2.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View view) {
                isVideo=true;  //录制视频
                permission();


            }

        });


   /*     image.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View view) {

                openView(path);

            }

        });*/

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
                VideoCompress.compressVideoLow(path, destPath, new VideoCompress.CompressListener() {
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
            VideoInputActivity.startActivityForResult(VideoRecorderActivity.this, REQUEST_CODE_FOR_RECORD_VIDEO,VideoInputActivity.Q480);
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
                        VideoInputActivity.startActivityForResult(VideoRecorderActivity.this, REQUEST_CODE_FOR_RECORD_VIDEO,VideoInputActivity.Q480);
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

     * 小视屏录制回调

     * @param path

     */

    @Override

    public void videoPathCall(String path) {



        Log.e("地址:",path);

        //根据视频地址获取缩略图

        this.path =path;

        Bitmap bitmap = ThumbnailUtils.createVideoThumbnail(path, MediaStore.Video.Thumbnails.MINI_KIND);

        image.setImageBitmap(bitmap);

        first.setText(getFileSize(path));





    }



    /**

     * 录制视频回调

     * @param requestCode

     * @param resultCode

     * @param data

     */

    @Override

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode==REQUEST_CODE_FOR_RECORD_VIDEO&&resultCode==RESULT_CANCELED){



        }

        if(requestCode==REQUEST_CODE_FOR_RECORD_VIDEO&&resultCode==RESULT_OK){

            String path = data.getStringExtra(VideoInputActivity.INTENT_EXTRA_VIDEO_PATH);

            Log.e("TAG","地址："+path);
            //根据视频地址获取缩略图
            ToastUtil.showToast(VideoRecorderActivity.this,"视频地址："+path);
            this.path =path;
            first.setText(getFileSize(path));
            playVideo( path);
            String visitGuid = SharedUtils.getString("visitGuid");
            fileInfo info=new fileInfo(null,path,"1",System.currentTimeMillis(),visitGuid);  //1为视频
            dao.insert(info);
        }

        super.onActivityResult(requestCode, resultCode, data);

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
}
