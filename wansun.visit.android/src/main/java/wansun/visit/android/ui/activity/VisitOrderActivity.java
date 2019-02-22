package wansun.visit.android.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import wansun.visit.android.R;
import wansun.visit.android.adapter.visitOrderAdapter;
import wansun.visit.android.bean.visitItemBean;
import wansun.visit.android.utils.logUtils;

/**
 * 外访单界面
 * Created by User on 2019/2/19.
 */

public class VisitOrderActivity extends BaseActivity {
    TextView tv_visit_tobar;
    ImageView iv_visit_back;
    ListView lv_visit_order;
    visitOrderAdapter adapter;
    List  data;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_visit_order;
    }

    @Override
    protected void initView() {
        tv_visit_tobar= (TextView) findViewById(R.id.tv_visit_tobar);
        iv_visit_back= (ImageView) findViewById(R.id.iv_visit_back);
        lv_visit_order= (ListView) findViewById(R.id.lv_visit_order);
        getIntentData();
    }

    private void getIntentData() {
        data=new ArrayList();
        List <visitItemBean.DataBeanX.DataBean> applyData = (List<visitItemBean.DataBeanX.DataBean>) getIntent().getSerializableExtra("visitData");
        Iterator<visitItemBean.DataBeanX.DataBean> iterator = applyData.iterator();
        data.clear();
        while (iterator.hasNext()){
            visitItemBean.DataBeanX.DataBean next = iterator.next();
            String debtorName = next.getDebtorName();
            data.add(next);
            logUtils.d("外方单债务人名字"+debtorName);
        }
        updataUI();
    }

    private void updataUI() {
        adapter=new visitOrderAdapter(this,data);
        lv_visit_order.setAdapter(adapter);

    }

    @Override
    protected void initEvent() {
        tv_visit_tobar.setText("我的外访单");
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
                visitItemBean.DataBeanX.DataBean  o = (visitItemBean.DataBeanX.DataBean) data.get(position);
                List<visitItemBean.DataBeanX.DataBean.UrgeVisitItemsBean> urgeVisitItems = o.getUrgeVisitItems();
                String caseCode = o.getCaseCode();
                String batchCode = o.getBatchCode();
                String debtorName = o.getDebtorName();
                Intent intent =new Intent(VisitOrderActivity.this,VisitOrderAddressActivity.class);
                intent.putExtra("urgeVisitItems", (Serializable)urgeVisitItems);
                intent.putExtra("caseCode",caseCode);
                intent.putExtra("batchCode",batchCode);
                intent.putExtra("debtorName",debtorName);
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
