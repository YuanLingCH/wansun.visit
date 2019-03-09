package wansun.visit.android.ui.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

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
import wansun.visit.android.adapter.caseUrgeRecordAdapter;
import wansun.visit.android.api.apiManager;
import wansun.visit.android.bean.caseUrgeRecordBean;
import wansun.visit.android.net.requestBodyUtils;
import wansun.visit.android.utils.logUtils;
import wansun.visit.android.utils.netUtils;

/**
 * Created by User on 2019/2/21.
 */

public class UrgeRecordActivity extends BaseActivity {
    ImageView iv_visit_back;
   TextView tv_visit_tobar ;
    List urgeData;
    ListView lv_urge_record;
    caseUrgeRecordAdapter adapter;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_urge_record;
    }

    @Override
    protected void initView() {
        iv_visit_back= (ImageView) findViewById(R.id.iv_visit_back);
        tv_visit_tobar= (TextView) findViewById(R.id.tv_visit_tobar);
        tv_visit_tobar.setText("急催详情");
        lv_urge_record= (ListView) findViewById(R.id.lv_urge_record);
     getIntentData();
    }

 private void getIntentData() {
     urgeData=new ArrayList();
     String caseCode = getIntent().getStringExtra("caseCode");
     Retrofit retrofit = netUtils.getRetrofit();
     apiManager manager= retrofit.create(apiManager.class);
     final RequestBody requestBody = requestBodyUtils.visitCaseDetailsUrgeRecordFromeService(caseCode);
     Call<String> call = manager.visitCaseDetailsUrgeRecordFormeService(requestBody);
     urgeData.clear();
     call.enqueue(new Callback<String>() {
         @Override
         public void onResponse(Call<String> call, Response<String> response) {
             String body = response.body();
             if (!TextUtils.isEmpty(body)){
                 Gson gson=new Gson();
                 logUtils.d("急催下载数据"+body);
                 caseUrgeRecordBean data = gson.fromJson(body, new TypeToken<caseUrgeRecordBean>() {}.getType());
               String statusID = data.getStatusID();
                 if (statusID.equals("200")){
                     List<caseUrgeRecordBean.DataBean> data1 = data.getData();
                     Iterator<caseUrgeRecordBean.DataBean> iterator = data1.iterator();
                     while (iterator.hasNext()){
                         caseUrgeRecordBean.DataBean next = iterator.next();
                         urgeData.add(next);

                     }
                     updataUI();
                 }

             }
         }

         @Override
         public void onFailure(Call<String> call, Throwable t) {

         }
     });


    }

    private void updataUI() {
       adapter=new caseUrgeRecordAdapter(UrgeRecordActivity.this,urgeData);
        lv_urge_record.setAdapter(adapter);

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

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initLise() {

    }
}
