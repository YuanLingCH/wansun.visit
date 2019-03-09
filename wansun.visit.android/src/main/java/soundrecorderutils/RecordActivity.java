package soundrecorderutils;

import android.Manifest;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Message;
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
import wansun.visit.android.utils.dialogUtils;
import wansun.visit.android.utils.logUtils;

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
        mBtnRecordAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(RecordActivity.this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
                    //申请权限，REQUEST_TAKE_PHOTO_PERMISSION是自定义的常量
                    ActivityCompat.requestPermissions(RecordActivity.this,
                            new String[]{Manifest.permission.RECORD_AUDIO},
                            REQUEST_TAKE_PHOTO_PERMISSION);
                }else {
                        record();
                }


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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_TAKE_PHOTO_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //申请成功，可以拍照
                record();
            } else {
                Toast.makeText(RecordActivity.this,"你拒绝了权限，该功能不可用\n可在应用设置里授权录音哦",Toast.LENGTH_SHORT).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
