package wansun.visit.android.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import wansun.visit.android.R;
import wansun.visit.android.adapter.visitOrderrAddressAdapter;
import wansun.visit.android.bean.visitItemBean;
import wansun.visit.android.utils.logUtils;

/**
 * 外访单地址
 * Created by User on 2019/2/22.
 */

public class VisitOrderAddressActivity extends BaseActivity {
    ImageView iv_visit_back;
    TextView tv_visit_tobar ,tv_visit_ddebtorName,tv_visit_case_cade,tv_visit_bath_cade;
    List data;
    visitOrderrAddressAdapter adapter;
    ListView lv_visit_address;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_visti_order_address;
    }

    @Override
    protected void initView() {
        iv_visit_back= (ImageView) findViewById(R.id.iv_visit_back);
        tv_visit_tobar= (TextView) findViewById(R.id.tv_visit_tobar);
        tv_visit_tobar.setText("我的外访单");
        lv_visit_address= (ListView) findViewById(R.id.lv_visit_address);
        tv_visit_ddebtorName= (TextView) findViewById(R.id.tv_visit_ddebtorName);
        tv_visit_case_cade= (TextView) findViewById(R.id.tv_visit_case_cade);
        tv_visit_bath_cade= (TextView) findViewById(R.id.tv_visit_bath_cade);
        getIntentData();
    }

    private void getIntentData() {
        data=new ArrayList();
        String caseCode = getIntent().getStringExtra("caseCode");
        String batchCode = getIntent().getStringExtra("batchCode");
        String name = getIntent().getStringExtra("debtorName");
        tv_visit_ddebtorName.setText("债务人："+name);
        tv_visit_case_cade.setText("案件编号"+caseCode);
        tv_visit_bath_cade.setText("批次编号"+batchCode);
        logUtils.d("caseCode"+caseCode);
        logUtils.d("batchCode"+batchCode);
        List<visitItemBean.DataBeanX.DataBean.UrgeVisitItemsBean> urgeVisitItems= (List<visitItemBean.DataBeanX.DataBean.UrgeVisitItemsBean>) getIntent().getSerializableExtra("urgeVisitItems");
        Iterator<visitItemBean.DataBeanX.DataBean.UrgeVisitItemsBean> iterator = urgeVisitItems.iterator();
        data.clear();
        while (iterator.hasNext()){
            visitItemBean.DataBeanX.DataBean.UrgeVisitItemsBean next = iterator.next();
            String address = next.getAddress();
            logUtils.d("外访地址"+address);
            data.add(next);
        }
        updataUI();
    }

    private void updataUI() {
        adapter=new visitOrderrAddressAdapter(VisitOrderAddressActivity.this,data);
        lv_visit_address.setAdapter(adapter);
        lv_visit_address.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    View layout = lv_visit_address.getChildAt(position);
                   TextView tv = (TextView) layout.findViewById(R.id.tv_visit_address);
                String s = tv.getText().toString();
                String[] split = s.split("家庭地址：");
                String address = split[1];
                logUtils.d(address);
                //TODO 传详细地址过去 定位
               Intent intent=new Intent(VisitOrderAddressActivity.this,MainActivity.class);
                intent.putExtra("address",address);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.in_from_right,R.anim.out_to_left);

            }
        });


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
