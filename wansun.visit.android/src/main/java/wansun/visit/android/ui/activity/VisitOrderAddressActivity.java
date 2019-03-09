package wansun.visit.android.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import wansun.visit.android.R;
import wansun.visit.android.utils.logUtils;

/**
 * 外访单地址
 * Created by User on 2019/2/22.
 */

public class VisitOrderAddressActivity extends BaseActivity {
    ImageView iv_visit_back;
    TextView tv_visit_tobar ,tv_visit_ddebtorName,tv_visit_case_cade,tv_visit_bath_cade;

    ListView lv_visit_address;
    TextView tv_visit_right;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_visti_order_address;
    }

    @Override
    protected void initView() {
        iv_visit_back= (ImageView) findViewById(R.id.iv_visit_back);
        tv_visit_tobar= (TextView) findViewById(R.id.tv_visit_tobar);
        tv_visit_tobar.setText("我的外访单详情");
        lv_visit_address= (ListView) findViewById(R.id.lv_visit_address);
        tv_visit_ddebtorName= (TextView) findViewById(R.id.tv_visit_ddebtorName);
        tv_visit_case_cade= (TextView) findViewById(R.id.tv_visit_case_cade);
        tv_visit_bath_cade= (TextView) findViewById(R.id.tv_visit_bath_cade);
        tv_visit_right= (TextView) findViewById(R.id.tv_visit_right);
        tv_visit_right.setText("操作");
        getIntentData();
    }

    private void getIntentData() {
    
        String caseCode = getIntent().getStringExtra("caseCode");
        String batchCode = getIntent().getStringExtra("batchCode");
        String name = getIntent().getStringExtra("debtorName");
        tv_visit_ddebtorName.setText("债务人："+name);
        tv_visit_case_cade.setText("案件编号"+caseCode);
        tv_visit_bath_cade.setText("批次编号"+batchCode);
        logUtils.d("caseCode"+caseCode);
        logUtils.d("batchCode"+batchCode);


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
        tv_visit_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(VisitOrderAddressActivity.this,OperationActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.in_from_right,R.anim.out_to_left);
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
