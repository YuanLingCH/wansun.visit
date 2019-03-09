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
import wansun.visit.android.adapter.caseCardMessageAdapter;
import wansun.visit.android.api.apiManager;
import wansun.visit.android.bean.caseCardMessageBean;
import wansun.visit.android.global.AppConfig;
import wansun.visit.android.net.requestBodyUtils;
import wansun.visit.android.utils.logUtils;
import wansun.visit.android.utils.netUtils;

/**
 * Created by User on 2019/2/21.
 */

public class CaseCardMessageActivity extends BaseActivity {
    ImageView iv_visit_back;
    TextView tv_visit_tobar;
    List cardData;
    caseCardMessageAdapter adapter;
    ListView lv_card_message;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_case_card_message;
    }

    @Override
    protected void initView() {
        iv_visit_back= (ImageView) findViewById(R.id.iv_visit_back);
        tv_visit_tobar= (TextView) findViewById(R.id.tv_visit_tobar);
        tv_visit_tobar.setText("卡信息");
        lv_card_message= (ListView) findViewById(R.id.lv_card_message);
      getIntentData();


    }

    private void getIntentData() {
        cardData=new ArrayList();
        String caseCode = getIntent().getStringExtra("caseCode");
        Retrofit retrofit = netUtils.getRetrofit();
        apiManager manager= retrofit.create(apiManager.class);
        final RequestBody requestBody = requestBodyUtils.visitCaseDetailsCardFromeService(caseCode);
        Call<String> call = manager.visitCaseDetailsCardFormeService(requestBody);
        cardData.clear();
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String body = response.body();
                if (!TextUtils.isEmpty(body)){
                    logUtils.d("案件卡信息下载数据"+body);
                    Gson gson=new Gson();
                    caseCardMessageBean data = gson.fromJson(body, new TypeToken<caseCardMessageBean>() {}.getType());
                    String statusID = data.getStatusID();
                    if (AppConfig.SUCCESS.equals(statusID)){
                        List<caseCardMessageBean.DataBean> data1 = data.getData();
                        Iterator<caseCardMessageBean.DataBean> iterator = data1.iterator();
                        while (iterator.hasNext()){
                            caseCardMessageBean.DataBean next = iterator.next();
                            cardData.add(next);
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
        adapter=new caseCardMessageAdapter(CaseCardMessageActivity.this,cardData);
        lv_card_message.setAdapter(adapter);
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
