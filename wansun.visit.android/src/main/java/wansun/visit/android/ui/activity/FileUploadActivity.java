package wansun.visit.android.ui.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
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
import java.util.ArrayList;
import java.util.Iterator;
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
import wansun.visit.android.adapter.otherFileReAdapter;
import wansun.visit.android.api.apiManager;
import wansun.visit.android.db.fileInfo;
import wansun.visit.android.global.waifangApplication;
import wansun.visit.android.greendao.gen.fileInfoDao;
import wansun.visit.android.service.batchuploadFileService;
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
    private fileInfoDao dao;
   ImageView  iv_visit_back;
    TextView tv_visit_tobar,tv_path,tvAttachmentInfoDesc;
    Button but_file,but_upload,but_upload_batch;
    dialogUtils utils;
    List<String> list;
    RecyclerView lv_other_file;
    List <fileInfo>bottomData;
    ArrayList<String> bottomPath;
    otherFileReAdapter bottomAdapter;
    private BroadcastReceiver receiver = null;
    String caseCode;
    String visitGuid;

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
        lv_other_file= (RecyclerView) findViewById(R.id.lv_other_file);
        but_upload_batch= (Button) findViewById(R.id.but_upload_batch);
        tvAttachmentInfoDesc= (TextView) findViewById(R.id.tvAttachmentInfoDesc);
        caseCode = SharedUtils.getString("caseCode");
        visitGuid = SharedUtils.getString("visitGuid");
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
        /**
         * 批量上传文件
         */
        but_upload_batch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NetWorkTesting net=new NetWorkTesting(FileUploadActivity.this);
                if (net.isNetWorkAvailable()) {
                    Intent intent=new Intent(FileUploadActivity.this, batchuploadFileService.class);
                    intent.putStringArrayListExtra("listpath",  bottomPath);
                    startService(intent);
                    but_upload_batch.setText("文件上传中...");
                    but_upload_batch.setClickable(false);  //上传中不让点击 防止重复加载
                }else {
                    ToastUtil.showToast(FileUploadActivity.this,R.string.network_unavailing);
                }

            }
        });

        lv_other_file.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState==1){
                    but_upload_batch.setVisibility(View.GONE);
                }else if (newState ==0){
                    but_upload_batch.setVisibility(View.VISIBLE);
                }

            }
        });
        receiver=new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                logUtils.d("广播接收器");
                ToastUtil.showToast(FileUploadActivity.this,"文件上传完成");
               // utils.cancleDialog();
                bottomAdapter=new otherFileReAdapter(FileUploadActivity.this,bottomData,true);
                LinearLayoutManager lin=new LinearLayoutManager(FileUploadActivity.this);
                lv_other_file.setLayoutManager(lin);
                lv_other_file.setAdapter(bottomAdapter);
                if (bottomData.size()>0){
                    Iterator<fileInfo> iterator = bottomData.iterator();
                    while (iterator.hasNext()){
                        fileInfo next = iterator.next();
                        Long id = next.getId();
                        dao.deleteByKey(id);
                    }
                    tvAttachmentInfoDesc.setVisibility(View.VISIBLE);
                    bottomAdapter.notifyDataSetChanged();
                }
                but_upload_batch.setVisibility(View.GONE);
            }
        };


        IntentFilter filter = new IntentFilter();
        filter.addAction("com.example.timejob.service");
        registerReceiver(receiver,filter);
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
        }else {
            ToastUtil.showToast(FileUploadActivity.this,R.string.network_unavailing);
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
                    fileInfo info=new fileInfo(null,path,"3",System.currentTimeMillis(),visitGuid  );  //3为选择文件
                    dao.insert(info);

                }

                Toast.makeText(getApplicationContext(), "选中了" + list.size() + "个文件", Toast.LENGTH_SHORT).show();
            }
            tv_path.setText(sb.toString());

        }
    }


    @Override
    protected void initData() {
        dao= waifangApplication.getInstence().getSession().getFileInfoDao();
        loadBottomLvData();
    }

    @Override
    protected void initLise() {

    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }



    /**
     * 加载底部数据
     */
    private void loadBottomLvData() {
        bottomData=new ArrayList();
        bottomPath=new ArrayList<>();
            new Thread(){
                @Override
                public void run() {
                    super.run();
                    List<fileInfo> fileInfos = dao.loadAll();
                    if (fileInfos.size()>0){
                        Iterator<fileInfo> iterator = fileInfos.iterator();
                        bottomData.clear();
                        bottomPath.clear();
                        while (iterator.hasNext()){    //  遍历数据
                            fileInfo next = iterator.next();
                            logUtils.d("测试数据"+next.path+"ID:"+next.getId());
                            String batch = next.getBatch();
                            if (batch.equals(visitGuid)){
                                bottomData.add(next);
                                bottomPath.add(next.getPath());
                            }
                        }
                        logUtils.d("测试数据bottomData.size()"+bottomData.size());
                        if (bottomData.size()>0){
                            tvAttachmentInfoDesc.setVisibility(View.GONE);
                            but_upload_batch.setVisibility(View.VISIBLE);
                        }


                    }

                }
            }.start();

        bottomAdapter=new otherFileReAdapter(FileUploadActivity.this,bottomData,false);
        LinearLayoutManager lin=new LinearLayoutManager(FileUploadActivity.this);
        lv_other_file.setLayoutManager(lin);
        lv_other_file.setAdapter(bottomAdapter);
        bottomAdapter.setItemClickListener(new otherFileReAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(long id,int postion) {
                logUtils.d("测试数据"+id+":"+postion);
                delete(id, postion);
            }
        });


    }

    private void delete(final long id, final int postion) {
        View view = getLayoutInflater().inflate(R.layout.custom_diaglog_layut_exit_app, null);
        final TextView tv = (TextView) view.findViewById(R.id.tv);
        TextView tv_cancle= (TextView) view.findViewById(R.id.add_cancle);
        tv.setText(R.string.confirm_delete);
        tv.setTextSize(16);
        tv.setGravity(Gravity.CENTER);
        TextView tv_submit= (TextView) view.findViewById(R.id.add_submit);
        WindowManager manager=getWindowManager();
        final dialogUtils utils=new dialogUtils(FileUploadActivity.this,manager,view );
        utils.getDialog();
        tv_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                utils.cancleDialog();

            }
        });
        tv_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                utils.cancleDialog();
                dao.deleteByKey(id);
                bottomData.remove(postion);
                bottomPath.remove(postion);
                bottomAdapter.notifyDataSetChanged();
                if (bottomData.size()==0){
                    but_upload_batch.setVisibility(View.GONE);
                }else {
                    but_upload_batch.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (utils!=null){
            utils.cancleDialog();
        }
        unregisterReceiver(receiver);

    }
}
