package wansun.visit.android.ui.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.leon.lfilepickerlibrary.LFilePicker;
import com.leon.lfilepickerlibrary.utils.Constant;

import java.io.File;
import java.io.IOException;
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
import wansun.visit.android.utils.NetWorkTesting;
import wansun.visit.android.utils.SharedUtils;
import wansun.visit.android.utils.ToastUtil;
import wansun.visit.android.utils.dialogUtils;
import wansun.visit.android.utils.logUtils;

/**
 * 文件上传
 * Created by User on 2019/3/8.
 */

public class FileUploadActivity extends BaseActivity {
   ImageView  iv_visit_back;
    TextView tv_visit_tobar,tv_path;
    Button but_file,but_upload;
    dialogUtils utils;
    List<String> list;


    Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            utils.cancleDialog();
            ToastUtil.showToast(FileUploadActivity.this,"文件上传成功");
            tv_path.setText("");

        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.activity_file_upload;
    }

    @Override
    protected void initView() {
    iv_visit_back= (ImageView) findViewById(R.id.iv_visit_back);
        tv_visit_tobar= (TextView) findViewById(R.id.tv_visit_tobar);
        but_file= (Button) findViewById(R.id.but_file);
        tv_visit_tobar.setText("文件上传");
        tv_path= (TextView) findViewById(R.id.tv_path);
        but_upload= (Button) findViewById(R.id.but_upload);
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
         * 选择文件
         *
         */
        but_file.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new LFilePicker()
                        .withActivity(FileUploadActivity.this)
                        .withRequestCode(100)
                        .start();
            }
        });
        but_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doUpload();
            }
        });
    }

    /**
     * 上传到服务器
     */
    private void doUpload() {
        NetWorkTesting net=new NetWorkTesting(FileUploadActivity.this);
        if (net.isNetWorkAvailable()) {
            //上传图片到服务器
            WindowManager manager = getWindowManager();
            View view = LayoutInflater.from(waifangApplication.getContext()).inflate(R.layout.loading_layout, null);
            utils = new dialogUtils(FileUploadActivity.this, manager, view);
            TextView tv= (TextView) view.findViewById(R.id.tv_load);
            tv.setText(R.string.upload_pictureing);
            utils.getDialog();
            String caseCode = SharedUtils.getString("caseCode");
            String visitGuid = SharedUtils.getString("visitGuid");
            final String account = SharedUtils.getString("account");
            logUtils.d("account"+account);
            String id = SharedUtils.getString("id");
            final OkHttpClient okHttpClient = new OkHttpClient();

            final int[] cont = {0};
            for (int i = 0; i <list.size(); i++) {

                File file = new File(list.get(i));
                MultipartBody.Builder builder = new MultipartBody.Builder()
                        .setType(MultipartBody.FORM)
                        .addFormDataPart("caseCode",caseCode)
                        .addFormDataPart("visitGuid",visitGuid)
                        .addFormDataPart("uploaderName",account)
                        .addFormDataPart("uploaderId" ,id )
                        .addFormDataPart("uploadFile", file.getName(), RequestBody.create(MediaType.parse("image/jpeg"), file));

                RequestBody requestBody = builder.build();
                final Request request = new Request.Builder()
                        .url(apiManager.FielsUploadToService).post(requestBody).build();
                new Thread(){
                    @Override
                    public void run() {
                        super.run();
                        Call call = okHttpClient.newCall(request);
                        call.enqueue(new Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {
                                logUtils.d("图片上传错误"+e.toString());
                            }

                            @Override
                            public void onResponse(Call call, Response response) throws IOException {
                                ResponseBody body = response.body();
                                logUtils.d("文件上传"+body.string());
                                cont[0]++;
                                if (cont[0]==list.size()){
                                    mHandler.sendEmptyMessage(0);

                                }
                            }
                        });
                    }
                }.start();

            }
        }
    }

    StringBuffer sb=new StringBuffer();
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 100) {
              list = data.getStringArrayListExtra(Constant.RESULT_INFO);
                for (String path:list){
                    logUtils.d("文件的路径"+path);
                    sb.append(path);
                    sb.append("\r\n");
                }

                Toast.makeText(getApplicationContext(), "选中了" + list.size() + "个文件", Toast.LENGTH_SHORT).show();
            }
            tv_path.setText(sb.toString());
        }
    }


    @Override
    protected void initData() {

    }

    @Override
    protected void initLise() {

    }
}
