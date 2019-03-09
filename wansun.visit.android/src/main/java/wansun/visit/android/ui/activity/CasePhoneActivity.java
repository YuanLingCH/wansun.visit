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
import wansun.visit.android.adapter.casePhoneDetailsAdapter;
import wansun.visit.android.api.apiManager;
import wansun.visit.android.bean.casePhoneBean;
import wansun.visit.android.net.requestBodyUtils;
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
    @Override
    protected int getLayoutId() {
        return R.layout.activity_case_phone;
    }

    @Override
    protected void initView() {
        iv_visit_back= (ImageView) findViewById(R.id.iv_visit_back);
        tv_visit_tobar= (TextView) findViewById(R.id.tv_visit_tobar);
        lv_case_phone_details= (ListView) findViewById(R.id.lv_case_phone_details);
        tv_visit_tobar.setText("案件电话详情");
          getIntentData();
    }

   private void getIntentData() {
       caseData=new ArrayList();
       String caseCode = getIntent().getStringExtra("caseCode");
       Retrofit retrofit = netUtils.getRetrofit();
       apiManager manager= retrofit.create(apiManager.class);
       final RequestBody requestBody = requestBodyUtils.visitCaseDetailsContentsFromeService(caseCode);
       Call<String> call = manager.visitCaseDetailsContactsFormeService(requestBody);
       caseData.clear();
       call.enqueue(new Callback<String>() {
           @Override
           public void onResponse(Call<String> call, Response<String> response) {
               String body = response.body();
               logUtils.d("案件电话下载数据"+body);
               if (!TextUtils.isEmpty(body)){
                   Gson gson=new Gson();
                   casePhoneBean data = gson.fromJson(body, new TypeToken<casePhoneBean>() {}.getType());
                   String statusID = data.getStatusID();
                   if (statusID.equals("200")){
                       List<casePhoneBean.DataBean> data1 = data.getData();
                       Iterator<casePhoneBean.DataBean> iterator = data1.iterator();
                       while (iterator.hasNext()){
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
        adapter=new casePhoneDetailsAdapter(this,caseData);
       lv_case_phone_details.setAdapter(adapter);

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
