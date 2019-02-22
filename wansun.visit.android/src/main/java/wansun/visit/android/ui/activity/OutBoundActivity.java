package wansun.visit.android.ui.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import wansun.visit.android.R;
import wansun.visit.android.adapter.visitAdapter;
import wansun.visit.android.api.apiManager;
import wansun.visit.android.bean.caseDetailBean;
import wansun.visit.android.net.requestBodyUtils;
import wansun.visit.android.utils.logUtils;
import wansun.visit.android.utils.netUtils;
import wansun.visit.android.utils.unixTime;

/**
 * 案件详情界面
 * Created by User on 2019/1/22.
 */

public class OutBoundActivity extends BaseActivity {
    ImageView iv_visit_back;
    ListView lv_visit;
    visitAdapter adapter;
    List data;
    String caseCode;
    String visitGuid;
    TextView tv_debtor_name,tv_debtor_money,tv_debtor_adress,tv_base_collecta_mount,tv_base_last_arrears,tv_visit_area,tv_visit_goal;
    TextView tv_visit_remark,tv_customer_name,tv_gender,tv_clerk,tv_visitors,tv_collect_status,tv_deadline,tv_entrust_date;
    RelativeLayout rl_phone,rl_Address,rl_urge_recode,rl_visit_record,rl_visit_cord_detail;
    List<caseDetailBean.DataBean.PhoneDetailsBean>  phoneDetails;//案件电话详细
    List<caseDetailBean.DataBean.UrgeRecordBean> urgeRecord;//急催记录
    List<caseDetailBean.DataBean.VisitRecordBean> visitRecord;//外访记录
    List<caseDetailBean.DataBean.CardDetailsBean> cardDetails;//卡的信息
    @Override
    protected int getLayoutId() {
        return R.layout.activity_out_round;
    }

    @Override
    protected void initView() {
        iv_visit_back= (ImageView) findViewById(R.id.iv_visit_back);
        lv_visit= (ListView) findViewById(R.id.lv_visit);
        tv_debtor_name= (TextView) findViewById(R.id.tv_debtor_name);
        tv_debtor_money= (TextView) findViewById(R.id.tv_debtor_money);
        tv_debtor_adress= (TextView) findViewById(R.id.tv_debtor_adress);
        tv_base_collecta_mount= (TextView) findViewById(R.id.tv_base_collecta_mount);
        tv_base_last_arrears= (TextView) findViewById(R.id.tv_base_last_arrears);
        tv_visit_area= (TextView) findViewById(R.id.tv_visit_area);
        tv_visit_goal= (TextView) findViewById(R.id.tv_visit_goal);
        tv_visit_remark= (TextView) findViewById(R.id.tv_visit_remark);
        tv_customer_name= (TextView) findViewById(R.id.tv_customer_name);
        tv_gender= (TextView) findViewById(R.id.tv_gender);
        tv_clerk= (TextView) findViewById(R.id.tv_clerk);
        tv_visitors= (TextView) findViewById(R.id.tv_visitors);
        tv_collect_status= (TextView) findViewById(R.id.tv_collect_status);
        tv_deadline= (TextView) findViewById(R.id.tv_deadline);
        tv_entrust_date= (TextView) findViewById(R.id.tv_entrust_date);
        rl_phone= (RelativeLayout) findViewById(R.id.rl_phone);
        rl_Address= (RelativeLayout) findViewById(R.id.rl_Address);
        rl_urge_recode= (RelativeLayout) findViewById(R.id.rl_urge_recode);
        rl_visit_record= (RelativeLayout) findViewById(R.id.rl_visit_record);
        rl_visit_cord_detail= (RelativeLayout) findViewById(R.id.rl_visit_cord_detail);
        getIntentData();
    }

    private void getIntentData() {
         caseCode = getIntent().getStringExtra("caseCode");
      visitGuid = getIntent().getStringExtra("visitGuid");
        logUtils.d("案件编号"+caseCode+"visitGuid"+visitGuid);
    }

    @Override
    protected void initEvent() {
        iv_visit_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        rl_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logUtils.d("点击案件电话");
                Intent intent=new Intent(OutBoundActivity.this,CasePhoneActivity.class);
                intent.putExtra("phoneDetails", (Serializable) phoneDetails);
                startActivity(intent);
                overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left); // 由右向左滑入的效果
            }
        });
        rl_Address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //急催记录
        rl_urge_recode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(OutBoundActivity.this,UrgeRecordActivity .class);
                intent.putExtra("urgeRecord", (Serializable) urgeRecord);
                startActivity(intent);
                overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left); // 由右向左滑入的效果
            }
        });
        // 外访记录
        rl_visit_record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(OutBoundActivity.this,CaseVisitRecordAcitvity .class);
                intent.putExtra("visitRecord", (Serializable) visitRecord);
                startActivity(intent);
                overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left); // 由右向左滑入的效果
            }
        });
        // 卡信息
        rl_visit_cord_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(OutBoundActivity.this,CaseCardMessageActivity .class);
                intent.putExtra("cardDetails", (Serializable) cardDetails);
                startActivity(intent);
                overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left); // 由右向左滑入的效果
            }
        });

    }

    /**
     * 数据加载
     */
    @Override
    protected void initData() {
        phoneDetails=new ArrayList<>();
        Retrofit retrofit = netUtils.getRetrofit();
        apiManager manager= retrofit.create(apiManager.class);
        //TODO注意换回数据 caseData
        final RequestBody requestBody = requestBodyUtils.visitCaseDetailsFromeService("APG-20181206-004-000001",visitGuid);

        Call<String> call = manager.visitCaseDetailsFormeService(requestBody);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String body = response.body();
                logUtils.d("下载数据"+body);
                if (!TextUtils.isEmpty(body)){
                    Gson gson=new Gson();
                    caseDetailBean bean = gson.fromJson(body, new TypeToken<caseDetailBean>() {}.getType());
                    caseDetailBean.DataBean data = bean.getData();
                   phoneDetails = data.getPhoneDetails();
                   urgeRecord = data.getUrgeRecord();
                    visitRecord = data.getVisitRecord();
                   cardDetails = data.getCardDetails();
                    updataUi(bean);
                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });

        data=new ArrayList();
        for (int i = 0; i < 5; i++) {
            data.add(i);
        }
        adapter=new visitAdapter(this,data);
        lv_visit.setAdapter(adapter);
    }

    /**
     * 得到数据后更新界面UI
     * @param bean
     */
    private void updataUi(caseDetailBean bean) {
        caseDetailBean.DataBean data = bean.getData();
        tv_debtor_name.setText("欠款人姓名："+data.getDebtorName());
        tv_debtor_money.setText("欠款金额："+data.getCollectionAmount()+"元");
        tv_debtor_adress.setText("欠款人地址："+data.getArea());
        tv_base_collecta_mount.setText("催收金额："+data.getCollectionAmount()+"元");
        tv_base_last_arrears.setText("最新欠款："+data.getLastArrears()+"元");
        tv_visit_area.setText("外访区域："+data.getArea());
        tv_visit_goal.setText("外访目标："+data.getVisitGoal());
        tv_visit_remark.setText("外访备注："+data.getVisitRemark());
        tv_customer_name.setText("客户名称："+data.getCustomerName());
        tv_gender.setText("性别："+data.getGenderText());
        tv_clerk.setText("案件催收员："+data.getClerkName());
        tv_visitors.setText("外访人员："+data.getVisitors());
        tv_collect_status.setText("催收状态："+data.getUrgeStatusText());
        long deadLine = data.getDeadLine()/1000;
        String s = unixTime.stampToTime(deadLine);
        logUtils.d("时间转换"+s);
        tv_deadline.setText("退案日期："+s);
        long entrustDate = data.getEntrustDate()/1000;
        tv_entrust_date.setText("委案日期："+unixTime.stampToTime(entrustDate));
    }

    @Override
    protected void initLise() {

    }
}
