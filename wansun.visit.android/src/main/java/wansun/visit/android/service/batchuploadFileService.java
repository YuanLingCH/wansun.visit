package wansun.visit.android.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import wansun.visit.android.api.apiManager;
import wansun.visit.android.utils.SharedUtils;
import wansun.visit.android.utils.logUtils;

/**
 * 多文件批量上传
 * Created by User on 2019/3/18.
 */

public class batchuploadFileService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(final Intent intent, int flags, int startId) {
        logUtils.d("上传服务启动");
        final ArrayList<String> listpath = intent.getStringArrayListExtra("listpath");
        String caseCode = SharedUtils.getString("caseCode");
        String visitGuid = SharedUtils.getString("visitGuid");
        final String account = SharedUtils.getString("account");
        logUtils.d("account"+account);
        String id = SharedUtils.getString("id");
        for (String path:listpath){
            logUtils.d("测试路劲"+path);
        }
        final int[] cont = {0};

        for (int i = 0; i <listpath.size(); i++) {
            final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .readTimeout(60, TimeUnit.SECONDS)
                    .writeTimeout(60,TimeUnit.SECONDS)
                    .build();

            File file = new File(listpath.get(i));
            String myparse=null;
            String filePath=null;
            if (listpath.get(i).endsWith(".mp4")){
                filePath=apiManager.videoUploadToService;
                myparse="application/octet-stream";
            }else if (listpath.get(i).endsWith(".mp3")){
                filePath=apiManager.recordFileUpToService;
                myparse="image/jpeg";
            }else if (listpath.get(i).endsWith(".jpg")||listpath.get(i).endsWith(".png")){
                filePath=apiManager.upLoadPicturesToService;
                myparse="image/jpeg";
            }else {
                filePath=apiManager.FielsUploadToService;
                myparse="image/jpeg";
            }

            MultipartBody.Builder builder = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("caseCode",caseCode)
                    .addFormDataPart("visitGuid",visitGuid)
                    .addFormDataPart("uploaderName",account)
                    .addFormDataPart("uploaderId" ,id )
                    .addFormDataPart("uploadFile", file.getName(), RequestBody.create(MediaType.parse(myparse), file));

            RequestBody requestBody = builder.build();
            final Request request = new Request.Builder()
                    .url(filePath).post(requestBody).build();
                Call call = okHttpClient.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        logUtils.d("图片上传错误"+e.toString());
                        Intent intent1=new Intent();
                        intent1.setAction("com.example.timejob.service");
                        sendBroadcast(intent1);
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        ResponseBody body = response.body();
                        logUtils.d("文件上传"+body.string());
                        cont[0]++;
                        if (cont[0]==listpath.size()){
                            stopSelf();//停止服务
                            logUtils.d("服务停止");
                            // ToastUtil.showToast(batchuploadFileService.this,"文件上传完成");
                            Intent intent1=new Intent();
                            intent1.setAction("com.example.timejob.service");
                            sendBroadcast(intent1);
                        }
                    }
                });

//            new Thread(){
//                @Override
//                public void run() {
//
//
//                }
//            }.start();
/*            UIutils.newCacheThreadPool().execute(new Runnable() {
                @Override
                public void run() {

                }
            });*/

        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopSelf();  //停止服务

    }



}
