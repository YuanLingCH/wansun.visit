package wansun.visit.android.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lidong.photopicker.PhotoPickerActivity;
import com.lidong.photopicker.PhotoPreviewActivity;
import com.lidong.photopicker.SelectModel;
import com.lidong.photopicker.intent.PhotoPickerIntent;
import com.lidong.photopicker.intent.PhotoPreviewIntent;

import org.json.JSONArray;

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
import wansun.visit.android.adapter.GridAdapter;
import wansun.visit.android.api.apiManager;
import wansun.visit.android.db.fileInfo;
import wansun.visit.android.global.waifangApplication;
import wansun.visit.android.greendao.gen.fileInfoDao;
import wansun.visit.android.utils.NetWorkTesting;
import wansun.visit.android.utils.SharedUtils;
import wansun.visit.android.utils.ToastUtil;
import wansun.visit.android.utils.dialogUtils;
import wansun.visit.android.utils.logUtils;

/**
 * 图片选择
 * Created by User on 2019/2/28.
 */

public class PictureSelectActivity extends  BaseActivity {
    private static final int REQUEST_TAKE_PHOTO_PERMISSION = 1;
    private fileInfoDao dao;
    private static final int REQUEST_CAMERA_CODE = 10;
    private static final int REQUEST_PREVIEW_CODE = 20;
    private ArrayList<String> imagePaths = new ArrayList<>();

    private GridView gridView;
    private GridAdapter gridAdapter;
    private TextView tv_click;
    private EditText textView;
    ImageView iv_visit_back;
    TextView tv_visit_tobar;
    dialogUtils utils;
    Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            utils.cancleDialog();
            ToastUtil.showToast(PictureSelectActivity.this,"图片上传完成");
            imagePaths.clear();
            imagePaths.add("paizhao");
            gridAdapter.notifyDataSetChanged();

        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        dao=waifangApplication.getInstence().getSession().getFileInfoDao();
        permission();
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_picture_select;
    }

    @Override
    protected void initView() {
        gridView = (GridView) findViewById(R.id.gridView);
        tv_click = (TextView) findViewById(R.id.find_comment_submit);
        textView= (EditText)findViewById(R.id.et_context);
        iv_visit_back= (ImageView) findViewById(R.id.iv_visit_back);
        tv_visit_tobar= (TextView) findViewById(R.id.tv_visit_tobar);
        tv_visit_tobar.setText("图片选择");
        int cols = getResources().getDisplayMetrics().widthPixels / getResources().getDisplayMetrics().densityDpi;
        cols = cols < 3 ? 3 : cols;
        gridView.setNumColumns(cols);
    }

    @Override
    protected void initEvent() {
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                selectPicture(parent, position);
            }
        });
        imagePaths.add("paizhao");
        gridAdapter = new GridAdapter(PictureSelectActivity.this,imagePaths);
        gridView.setAdapter(gridAdapter);
        tv_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        iv_visit_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.in_from_left,R.anim.out_to_right);
            }
        });
        //点击上传图片
        tv_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NetWorkTesting net=new NetWorkTesting(PictureSelectActivity.this);
                if (net.isNetWorkAvailable()) {

                logUtils.d("图片大小"+imagePaths.size());
            if (imagePaths.size()==1){
                ToastUtil.showToast(PictureSelectActivity.this,R.string.upload_picture);
            }else {
            //上传图片到服务器
                WindowManager manager = getWindowManager();
                View view = LayoutInflater.from(waifangApplication.getContext()).inflate(R.layout.loading_layout, null);
             utils = new dialogUtils(PictureSelectActivity.this, manager, view);
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
                for (int i = 0; i < imagePaths.size(); i++) {
                    if (imagePaths.contains("paizhao")){
                        imagePaths.remove("paizhao");
                    }
                    File file = new File(imagePaths.get(i));
                    MultipartBody.Builder builder = new MultipartBody.Builder()
                            .setType(MultipartBody.FORM)
                            .addFormDataPart("caseCode",caseCode)
                            .addFormDataPart("visitGuid",visitGuid)
                            .addFormDataPart("uploaderName",account)
                            .addFormDataPart("uploaderId" ,id )
                            .addFormDataPart("uploadFile", file.getName(), RequestBody.create(MediaType.parse("image/jpeg"), file));

                    RequestBody requestBody = builder.build();
                    final Request request = new Request.Builder()
                            .url(apiManager.upLoadPicturesToService).post(requestBody).build();
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
                                    cont[0]++;
                                    logUtils.d("图片上传"+body.string()+"..."+cont[0]);
                                    if (cont[0]==imagePaths.size()-1||cont[0]==imagePaths.size()){
                                        mHandler.sendEmptyMessage(0);

                                    }
                                }
                            });
                        }
                    }.start();

                }
            }
            }
            }
        });

    }

    private void permission() {
        List<String> permissionLists = new ArrayList<>();

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            permissionLists.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        }
        if (!permissionLists.isEmpty()) {//说明肯定有拒绝的权限

            ActivityCompat.requestPermissions(this, permissionLists.toArray(new String[permissionLists.size()]), REQUEST_TAKE_PHOTO_PERMISSION);

        } else {
            //  Toast.makeText(this, "权限都授权了",Toast.LENGTH_SHORT).show();

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

                    }
                }
                break;

            default:

                break;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


    private void selectPicture(AdapterView<?> parent, int position) {
        String imgs = (String) parent.getItemAtPosition(position);
        if ("paizhao".equals(imgs) ){
            PhotoPickerIntent intent = new PhotoPickerIntent(PictureSelectActivity.this);
            intent.setSelectModel(SelectModel.MULTI);
            intent.setShowCarema(true); // 是否显示拍照
            intent.setMaxTotal(6); // 最多选择照片数量，默认为6
            intent.setSelectedPaths(imagePaths); // 已选中的照片地址， 用于回显选中状态
            startActivityForResult(intent, REQUEST_CAMERA_CODE);
        }else{
            Toast.makeText(PictureSelectActivity.this,"1"+position,Toast.LENGTH_SHORT).show();
            PhotoPreviewIntent intent = new PhotoPreviewIntent(PictureSelectActivity.this);
            intent.setCurrentItem(position);
            intent.setPhotoPaths(imagePaths);
            startActivityForResult(intent, REQUEST_PREVIEW_CODE);
        }
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initLise() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK) {
            switch (requestCode) {
                // 选择照片
                case REQUEST_CAMERA_CODE:
                    ArrayList<String> list = data.getStringArrayListExtra(PhotoPickerActivity.EXTRA_RESULT);
                 //   Log.d(TAG, "数量："+list.size());
                    loadAdpater(list);
                    break;
                // 预览
                case REQUEST_PREVIEW_CODE:
                    ArrayList<String> ListExtra = data.getStringArrayListExtra(PhotoPreviewActivity.EXTRA_RESULT);
                    loadAdpater(ListExtra);
                    break;
            }
        }
    }

    private void loadAdpater(ArrayList<String> paths){
        if (imagePaths!=null&& imagePaths.size()>0){
            imagePaths.clear();
        }
        if (paths.contains("paizhao")){
            paths.remove("paizhao");
        }
        paths.add("paizhao");
        imagePaths.addAll(paths);
        gridAdapter  = new GridAdapter(PictureSelectActivity.this,imagePaths);
        gridView.setAdapter(gridAdapter);
        String visitGuid = SharedUtils.getString("visitGuid");
        for (int i = 0; i < imagePaths.size(); i++) {
            if (imagePaths.contains("paizhao")) {
                imagePaths.remove("paizhao");
            }

            fileInfo info=new fileInfo(null,imagePaths.get(i),"2",System.currentTimeMillis(),visitGuid);  //2为选择图片
            dao.insert(info);

        }
        try{
            JSONArray obj = new JSONArray(imagePaths);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
