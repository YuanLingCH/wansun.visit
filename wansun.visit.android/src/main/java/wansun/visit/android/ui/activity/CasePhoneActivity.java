package wansun.visit.android.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import wansun.visit.android.R;
import wansun.visit.android.adapter.casePhoneDetailsAdapter;
import wansun.visit.android.api.apiManager;
import wansun.visit.android.bean.casePhoneBean;
import wansun.visit.android.net.requestBodyUtils;
import wansun.visit.android.utils.ToastUtil;
import wansun.visit.android.utils.dialogUtils;
import wansun.visit.android.utils.logUtils;
import wansun.visit.android.utils.netUtils;

/**
 * 案件电话详情
 * Created by User on 2019/2/20.
 */

public class CasePhoneActivity extends BaseActivity {
    ImageView iv_visit_back;
    TextView tv_visit_tobar;
    List caseData;
    casePhoneDetailsAdapter adapter;
    ListView lv_case_phone_details;
    private static final int REQUEST_TAKE_PHOTO_PERMISSION = 1;
    String phoneNumber;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_case_phone;
    }

    @Override
    protected void initView() {
        iv_visit_back = (ImageView) findViewById(R.id.iv_visit_back);
        tv_visit_tobar = (TextView) findViewById(R.id.tv_visit_tobar);
        lv_case_phone_details = (ListView) findViewById(R.id.lv_case_phone_details);
        tv_visit_tobar.setText("案件电话详情");
        getIntentData();
    }

    private void getIntentData() {
        caseData = new ArrayList();
        String caseCode = getIntent().getStringExtra("caseCode");
        Retrofit retrofit = netUtils.getRetrofit();
        apiManager manager = retrofit.create(apiManager.class);
        final RequestBody requestBody = requestBodyUtils.visitCaseDetailsContentsFromeService(caseCode);
        Call<String> call = manager.visitCaseDetailsContactsFormeService(requestBody);
        caseData.clear();
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String body = response.body();
                logUtils.d("案件电话下载数据" + body);
                if (!TextUtils.isEmpty(body)) {
                    Gson gson = new Gson();
                    casePhoneBean data = gson.fromJson(body, new TypeToken<casePhoneBean>() {
                    }.getType());
                    String statusID = data.getStatusID();
                    if (statusID.equals("200")) {
                        List<casePhoneBean.DataBean> data1 = data.getData();
                        Iterator<casePhoneBean.DataBean> iterator = data1.iterator();
                        while (iterator.hasNext()) {
                            casePhoneBean.DataBean next = iterator.next();
                            caseData.add(next);
                        }
                    }
                    updataUI();
                }


            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });

    }

    private void updataUI() {
        adapter = new casePhoneDetailsAdapter(this, caseData);
        lv_case_phone_details.setAdapter(adapter);

    }

    @Override
    protected void initEvent() {
        iv_visit_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.in_from_left, R.anim.out_to_right);
            }
        });
        /**
         * 跳到拨号界面
         */
        lv_case_phone_details.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                casePhoneBean.DataBean bean = (casePhoneBean.DataBean) caseData.get(position);
                phoneNumber = bean.getPhoneNumber();
                dialPhoneNumbler(phoneNumber);
            }
        });

    }

    private void dialPhoneNumbler(final String phoneNumber) {
        View view = getLayoutInflater().inflate(R.layout.custom_diaglog_layut_exit_app, null);
        final TextView tv = (TextView) view.findViewById(R.id.tv);
        TextView tv_cancle = (TextView) view.findViewById(R.id.add_cancle);
        tv.setText("拨打该号码" + ":" + phoneNumber);
        tv.setTextSize(16);
        tv.setGravity(Gravity.CENTER);
        TextView tv_submit = (TextView) view.findViewById(R.id.add_submit);
        WindowManager manager = getWindowManager();
        final dialogUtils utils = new dialogUtils(CasePhoneActivity.this, manager, view);
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
                if (!TextUtils.isEmpty( phoneNumber)){
                    permission();
                }else {
                    ToastUtil.showToast(CasePhoneActivity.this,"电话号码不正确");
                }




            }
        });
    }

    private void daile() {
        Intent intent = new Intent(Intent.ACTION_CALL);
        Uri data = Uri.parse("tel:" + phoneNumber);
        intent.setData(data);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        startActivity(intent);
    }

    @Override
    protected void initData() {


    }

    @Override
    protected void initLise() {

    }


    private void permission() {
        List<String> permissionLists = new ArrayList<>();
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            permissionLists.add(Manifest.permission.CALL_PHONE);
        }
        if (!permissionLists.isEmpty()) {//说明肯定有拒绝的权限

            ActivityCompat.requestPermissions(this, permissionLists.toArray(new String[permissionLists.size()]), REQUEST_TAKE_PHOTO_PERMISSION);

        } else {
            daile();
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
                        daile();
                    }
                }
                break;

            default:

                break;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
