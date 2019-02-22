package wansun.visit.android.ui.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import wansun.visit.android.R;
import wansun.visit.android.adapter.casePhoneDetailsAdapter;
import wansun.visit.android.bean.caseDetailBean;
import wansun.visit.android.utils.logUtils;

/**
 * 案件电话详情
 * Created by User on 2019/2/20.
 */

public class CasePhoneActivity extends BaseActivity {
    ImageView iv_visit_back;
    TextView tv_visit_tobar;
    List<caseDetailBean.DataBean.PhoneDetailsBean>data;
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
        data=new ArrayList();
        List<caseDetailBean.DataBean.PhoneDetailsBean> casePhoneBean = (List<caseDetailBean.DataBean.PhoneDetailsBean>) getIntent().getSerializableExtra("phoneDetails");
        Iterator<caseDetailBean.DataBean.PhoneDetailsBean> iterator = casePhoneBean.iterator();
        data.clear();
        while (iterator.hasNext()){
            caseDetailBean.DataBean.PhoneDetailsBean next = iterator.next();
            String name = next.getName();
            data.add(next);
            logUtils.d("name"+name);
        }
            updataUI();
    }

    private void updataUI() {
        adapter=new casePhoneDetailsAdapter(this,data);
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
