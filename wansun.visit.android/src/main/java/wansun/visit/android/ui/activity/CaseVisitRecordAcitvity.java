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
import wansun.visit.android.adapter.caseVisitRecordAdapter;
import wansun.visit.android.api.apiManager;
import wansun.visit.android.bean.caseVistRecordBean;
import wansun.visit.android.global.AppConfig;
import wansun.visit.android.net.requestBodyUtils;
import wansun.visit.android.utils.logUtils;
import wansun.visit.android.utils.netUtils;

/**
 * Created by User on 2019/2/21.
 */

public class CaseVisitRecordAcitvity extends BaseActivity {
    ImageView iv_visit_back;
    TextView tv_visit_tobar;
    caseVisitRecordAdapter adapter;
    List recordData;
    ListView lv_case_urge_visit_record;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_case_ugre_record;
    }

    @Override
    protected void initView() {
        iv_visit_back= (ImageView) findViewById(R.id.iv_visit_back);
        tv_visit_tobar= (TextView) findViewById(R.id.tv_visit_tobar);
        lv_case_urge_visit_record= (ListView) findViewById(R.id.lv_case_urge_visit_record);
        tv_visit_tobar.setText("外访记录");
    getIntentData();
    }

   private void getIntentData() {
       recordData=new ArrayList();
       String caseCode = getIntent().getStringExtra("caseCode");
       String visitGuid = getIntent().getStringExtra("visitGuid");
       Retrofit retrofit = netUtils.getRetrofit();
       apiManager manager= retrofit.create(apiManager.class);
       final RequestBody requestBody = requestBodyUtils.visitCaseDetailsRecordFromeService(caseCode,visitGuid);
       Call<String> call = manager.visitCaseDetailsRecordFormeService(requestBody);
       recordData.clear();
       call.enqueue(new Callback<String>() {
           @Override
           public void onResponse(Call<String> call, Response<String> response) {
               String body = response.body();
               if (!TextUtils.isEmpty(body)){
                   Gson gson=new Gson();
                   logUtils.d("案件外访下载数据"+body);
                   caseVistRecordBean data = gson.fromJson(body, new TypeToken<caseVistRecordBean>() {}.getType());
                   String statusID = data.getStatusID();
                    if (AppConfig.SUCCESS.equals(statusID)){  //200
                        List<caseVistRecordBean.DataBean> data1 = data.getData();
                        Iterator<caseVistRecordBean.DataBean> iterator = data1.iterator();
                        while (iterator.hasNext()){
                            caseVistRecordBean.DataBean next = iterator.next();
                            recordData.add(next);
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
        adapter=new caseVisitRecordAdapter(CaseVisitRecordAcitvity.this,recordData);
        lv_case_urge_visit_record.setAdapter(adapter);



    }

    @Override
    protected void initEvent() {
        iv_visit_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.in_from_left, R.anim.out_to_right);  //左边进去 右边出来
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
