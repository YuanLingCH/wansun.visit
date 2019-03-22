package wansun.visit.android.ui.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
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
import wansun.visit.android.adapter.visitOrderAdapter;
import wansun.visit.android.api.apiManager;
import wansun.visit.android.bean.visitItemBean;
import wansun.visit.android.net.requestBodyUtils;
import wansun.visit.android.utils.SharedUtils;
import wansun.visit.android.utils.ToastUtil;
import wansun.visit.android.utils.logUtils;
import wansun.visit.android.utils.netUtils;

/**
 * 历史外访单
 * Created by User on 2019/2/22.
 */

public class VistRecordActivity extends BaseActivity {
   ImageView iv_visit_back;
    TextView tv_visit_tobar;
    List visitData;
    ListView lv_visit_order;
    visitOrderAdapter adapter;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_visit_order_record;
    }

    @Override
    protected void initView() {
        tv_visit_tobar= (TextView) findViewById(R.id.tv_visit_tobar);
        tv_visit_tobar.setText("历史外访单");
        iv_visit_back= (ImageView) findViewById(R.id.iv_visit_back);
        lv_visit_order= (ListView) findViewById(R.id.lv_visit_order);
        getIntentData();
    }
    private void getIntentData() {

    }

    private void updataUI() {
        adapter=new visitOrderAdapter(this,visitData,true); //true 为完成
        lv_visit_order.setAdapter(adapter);
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

        lv_visit_order.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                visitItemBean.DataBean  o = (visitItemBean.DataBean) visitData.get(position);
                String caseCode = o.getCaseCode();
                String bacthCode = o.getBatchCode();
                String visitGuid = o.getVisitGuid();

                if (!TextUtils.isEmpty(caseCode)&&!TextUtils.isEmpty(visitGuid )){
                    Intent intent =new Intent(VistRecordActivity.this,OutBoundActivity.class);
                    intent.putExtra("caseCode",caseCode);
                    intent.putExtra("visitGuid",visitGuid);
                    startActivity(intent);
                    overridePendingTransition(R.anim.in_from_right,R.anim.out_to_left);
                }else {
                    ToastUtil.showToast(VistRecordActivity.this,"案件号和标识不能为空");
                }

            }
        });



    }

    @Override
    protected void initData() {
        visitData=new ArrayList();
        String userName = SharedUtils.getString("account");
        logUtils.d("userName"+userName);
        Retrofit retrofit = netUtils.getRetrofit();
        apiManager manager= retrofit.create(apiManager.class);
        RequestBody requestBody = requestBodyUtils.visitItemToService(userName,false);
        Call<String> call = manager.visitListFormeService(requestBody);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String body = response.body();
                logUtils.d("body"+body);
                if (!TextUtils.isEmpty(body)){
                    Gson gson=new Gson();
                    visitItemBean bean = gson.fromJson(body, new TypeToken<visitItemBean>() {}.getType());
                    String statusID = bean.getStatusID();
                    if (statusID.equals("200")){
                        List<visitItemBean.DataBean> data = bean.getData();
                        Iterator<visitItemBean.DataBean> iterator = data.iterator();
                        while (iterator.hasNext()){
                            visitItemBean.DataBean next = iterator.next();
                            visitData.add(next);
                            String name = next.getName();
                            logUtils.d("债务人名字："+name);

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

    @Override
    protected void initLise() {

    }
}
