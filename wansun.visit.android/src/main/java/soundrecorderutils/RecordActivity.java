package soundrecorderutils;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
import wansun.visit.android.global.waifangApplication;
import wansun.visit.android.ui.activity.BaseActivity;
import wansun.visit.android.utils.NetWorkTesting;
import wansun.visit.android.utils.SharedUtils;
import wansun.visit.android.utils.ToastUtil;
import wansun.visit.android.utils.WindowsUitlity;
import wansun.visit.android.utils.dialogUtils;
import wansun.visit.android.utils.logUtils;

import static wansun.visit.android.utils.floatWindownUtils.isFloatWindowOpAllowed;

public class RecordActivity extends BaseActivity {

    private static final int REQUEST_TAKE_PHOTO_PERMISSION = 1;
    private Button mBtnRecordAudio;
    private Button mBtnPlayAudio,btn_record_upload;
    ImageView iv_visit_back;
    TextView tv_visit_tobar;
    dialogUtils utils;
    Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            utils.cancleDialog();
            ToastUtil.showToast(RecordActivity.this,"录音文件上传完成");


        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.activity_record;
    }
    protected void initEvent() {
        //进行录音
        mBtnRecordAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              permission();

            }
        });
        mBtnPlayAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecordingItem recordingItem = new RecordingItem();
                SharedPreferences sharePreferences = getSharedPreferences("sp_name_audio", MODE_PRIVATE);
                final String filePath = sharePreferences.getString("audio_path", "");
                long elpased = sharePreferences.getLong("elpased", 0);
                recordingItem.setFilePath(filePath);
                recordingItem.setLength((int) elpased);
                PlaybackDialogFragment fragmentPlay = PlaybackDialogFragment.newInstance(recordingItem);
                fragmentPlay.show(getSupportFragmentManager(), PlaybackDialogFragment.class.getSimpleName());
            }
        });
        //上传录音文件到服务器
        btn_record_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WindowManager manager = getWindowManager();
                View view = LayoutInflater.from(waifangApplication.getContext()).inflate(R.layout.loading_layout, null);
                utils = new dialogUtils(RecordActivity.this, manager, view);
                TextView tv= (TextView) view.findViewById(R.id.tv_load);
                tv.setText(R.string.upload_record);
                utils.getDialog();

                //TODO 上传到服务器
                SharedPreferences sharePreferences = getSharedPreferences("sp_name_audio", MODE_PRIVATE);
                String filePath = sharePreferences.getString("audio_path", "");
                logUtils.d("录音文件地址"+filePath);
                NetWorkTesting net=new NetWorkTesting(RecordActivity.this);
                if (net.isNetWorkAvailable()) {
                    doUpLoad(filePath);
                }

            }
        });
        iv_visit_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.in_from_left,R.anim.out_to_right);
            }
        });
    }


    private void permission() {
        List<String> permissionLists = new ArrayList<>();
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            permissionLists.add(Manifest.permission.RECORD_AUDIO);
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
           // record();
            openFloat();
        }
    }

    private void windownRecord() {
        logUtils.d("点击录音");
        WindowsUitlity uitlity=new WindowsUitlity(RecordActivity.this);
        uitlity.showPopupWindow(RecordActivity.this,"");
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
                    //    record();
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
     * 上传录音文件
     * @param filePath
     */
    private void doUpLoad(String filePath) {
        File file = new File(filePath);
        String caseCode = SharedUtils.getString("caseCode");
        String visitGuid = SharedUtils.getString("visitGuid");
        final String account = SharedUtils.getString("account");
        logUtils.d("account"+account);
        String id = SharedUtils.getString("id");
        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("caseCode",caseCode)
                .addFormDataPart("visitGuid",visitGuid)
                .addFormDataPart("uploaderName",account)
                .addFormDataPart("uploaderId" ,id )
                .addFormDataPart("uploadFile", file.getName(), RequestBody.create(MediaType.parse("image/jpeg"), file));

        RequestBody requestBody = builder.build();
        final OkHttpClient okHttpClient = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(apiManager.recordFileUpToService).post(requestBody).build();
        new Thread(){
            @Override
            public void run() {
                super.run();
                Call call = okHttpClient.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        logUtils.d("上传录音"+e.toString());
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        ResponseBody body = response.body();
                        logUtils.d("上传录音"+body.string());
                        mHandler.sendEmptyMessage(0);
                    }
                });
            }
        }.start();

    }

    private void record() {
        final RecordAudioDialogFragment fragment = RecordAudioDialogFragment.newInstance();
        fragment.show(getSupportFragmentManager(), RecordAudioDialogFragment.class.getSimpleName());
        fragment.setCancelable(false);
        fragment.setOnCancelListener(new RecordAudioDialogFragment.OnAudioCancelListener() {
            @Override
            public void onCancel() {
                //
                fragment.dismiss();
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initLise() {

    }

    protected void initView() {
        mBtnRecordAudio = (Button)findViewById(R.id.main_btn_record_sound);
        mBtnPlayAudio = (Button) findViewById(R.id.main_btn_play_sound);
        btn_record_upload= (Button) findViewById(R.id.btn_record_upload);
        iv_visit_back= (ImageView) findViewById(R.id.iv_visit_back);
        tv_visit_tobar= (TextView) findViewById(R.id.tv_visit_tobar);
        tv_visit_tobar.setText("录音");
    }
        ////////////////////////////////判断悬浮窗///////////////////////////////////////////////////////////////////
    /**
     * 请求用户给予悬浮窗的权限
     */
    public void requestPermission() {
        if (isFloatWindowOpAllowed(this)) {//已经开启
            windownRecord();
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 11) {
            if (isFloatWindowOpAllowed(this)) {//已经开启
                windownRecord();
            } else {
                logUtils .e("开启悬浮窗失败");
                ToastUtil.showToast(this,"请手动开启悬浮窗允许");
            }
        } else if (requestCode == 12) {
            if (Build.VERSION.SDK_INT >= 23) {
                if (!Settings.canDrawOverlays(RecordActivity.this)) {
                    ToastUtil.showToast(this,"权限授予失败,无法开启悬浮窗");
                } else {
                    windownRecord();
                }
            }
        }

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
                    windownRecord();
                }
            } else {
                windownRecord();
            }
        }
    }

}
