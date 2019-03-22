package wansun.visit.android.ui.activity;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import wansun.visit.android.R;
import wansun.visit.android.api.apiManager;
import wansun.visit.android.bean.appUpdataBean;
import wansun.visit.android.utils.NetWorkTesting;
import wansun.visit.android.utils.ToastUtil;
import wansun.visit.android.utils.UIutils;
import wansun.visit.android.utils.logUtils;
import wansun.visit.android.utils.netUtils;
import wansun.visit.android.widget.VersionUpdateTipdialog;

/**
 * Created by User on 2019/2/25.
 */

public class VersionsActivity extends BaseActivity {
    ImageView iv_visit_back;
    TextView tv_visit_tobar,tv_version;
    Button bt_version;
    String versionName;
    String newVersionNum ; //服务器版本
    String versionFileUrl; //服务器的uir APK 路径
    int versionCode;

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            "android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE" };
    @Override
    protected int getLayoutId() {
        return R.layout.activity_version;
    }

    @Override
    protected void initView() {
        iv_visit_back= (ImageView) findViewById(R.id.iv_visit_back);
        tv_visit_tobar= (TextView) findViewById(R.id.tv_visit_tobar);
        bt_version= (Button) findViewById(R.id.bt_version);
        tv_visit_tobar.setText("软件介绍");
        tv_version= (TextView) findViewById(R.id.tv_version);

    }

    @Override
    protected void initEvent() {
        versionCode = UIutils.getVersionCode();
        versionName = UIutils.getVersionName();
        setVersionNema();
        logUtils.d("versionCode"+versionCode+"versionName"+versionName);
        iv_visit_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.in_from_left,R.anim.out_to_right);
            }
        });
        //版本检查 升级用
        bt_version.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NetWorkTesting net=new NetWorkTesting(VersionsActivity.this);
                if (net.isNetWorkAvailable()){
                    verifyStoragePermissions(VersionsActivity.this);
                    getAppUpdate();
                }else {
                    ToastUtil.showToast(VersionsActivity.this,R.string.network_unavailing);
                }
            }
        });
    }
    /**设置版本名称*/
    private void setVersionNema() {
    tv_version.setText("当前版本："+UIutils.getVersionName());
    }
    @Override
    protected void initData() {
        //TODO 自动检查版本号 连接服务器

        Retrofit retrofit = netUtils.getRetrofit();
        apiManager manager= retrofit.create(apiManager.class);

        Call<String> call = manager.appUpdata("2",versionName);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String body = response.body();
                logUtils.d("版本升级"+body );
                if (!TextUtils.isEmpty(body)) {
                    Gson gson = new Gson();
                    appUpdataBean data = gson.fromJson(body, new TypeToken<appUpdataBean>() {}.getType());
                    String statusID = data.getStatusID();
                    if (statusID.equals("200")) {
                        appUpdataBean.DataBean data1 = data.getData();
                        versionFileUrl = data1.getVersionFileUrl();
                        newVersionNum = data1.getVersionName();
                        bt_version.setVisibility(View.VISIBLE);
                        bt_version.setText("发现新版本："+newVersionNum);
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                logUtils.d("版本升级失败"+t.toString() );
            }
        });


    }

    @Override
    protected void initLise() {

    }


    public static void verifyStoragePermissions(Activity activity) {

        try {
            //检测是否有写的权限
            int permission = ActivityCompat.checkSelfPermission(activity,
                    "android.permission.WRITE_EXTERNAL_STORAGE");
            if (permission != PackageManager.PERMISSION_GRANTED) {
                // 没有写的权限，去申请写的权限，会弹出对话框
                ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE,REQUEST_EXTERNAL_STORAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 待完善
     */
    public void getAppUpdate() {
        //  showProgressDialog("","正在检查版本");
        //联网请求数据   服务器版本大于本地版本  就升级
        if (newVersionNum.equals(versionName)){
            ToastUtil.showToast(VersionsActivity.this,"已经是最新版本");
        }else {
        if (!TextUtils.isEmpty(versionFileUrl)){
            new VersionUpdateTipdialog(VersionsActivity.this,newVersionNum,"2018年9月31号","修复了一下bug ,优化了功能",versionFileUrl).show();
        }else {
            logUtils.d("文件路径为null");
            ToastUtil.showToast(VersionsActivity.this,"已经是最新版本");
        }
        }


    }
}
