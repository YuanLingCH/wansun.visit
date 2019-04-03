package wansun.visit.android.ui.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import wansun.visit.android.R;
import wansun.visit.android.adapter.visitOrderAdapter;
import wansun.visit.android.bean.visitItemBean;
import wansun.visit.android.utils.ToastUtil;
import wansun.visit.android.view.EmptyLayout;

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
    EmptyLayout empty_layout;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_visit_order;
    }

    @Override
    protected void initView() {
        tv_visit_tobar= (TextView) findViewById(R.id.tv_visit_tobar);
        iv_visit_back= (ImageView) findViewById(R.id.iv_visit_back);
        lv_visit_order= (ListView) findViewById(R.id.lv_visit_order);
        empty_layout= (EmptyLayout) findViewById(R.id.empty_layout);
        getIntentData();
    }

    private void getIntentData() {
        data=new ArrayList();
        List <visitItemBean.DataBean> applyData = (List<visitItemBean.DataBean>) getIntent().getSerializableExtra("visitData");
        if (applyData.size()>0){
            Iterator<visitItemBean.DataBean> iterator = applyData.iterator();
            data.clear();
            while (iterator.hasNext()){
                visitItemBean.DataBean next = iterator.next();
                data.add(next);
            }
            updataUI();
        }else {
            ToastUtil.showToast(VisitOrderActivity.this,"没有数据...");
            empty_layout.setVisibility(View.VISIBLE);
            empty_layout.setErrorType(EmptyLayout.NODATA);
        }

    }

    private void updataUI() {
        adapter=new visitOrderAdapter(this,data,false);
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
               visitItemBean.DataBean  o = (visitItemBean.DataBean) data.get(position);
                String caseCode = o.getCaseCode();
                String bacthCode = o.getBatchCode();
                String visitGuid = o.getVisitGuid();

                if (!TextUtils.isEmpty(caseCode)&&!TextUtils.isEmpty(visitGuid )){
                    Intent intent =new Intent(VisitOrderActivity.this,OutBoundActivity.class);
                    intent.putExtra("caseCode",caseCode);
                    intent.putExtra("visitGuid",visitGuid);
                    startActivity(intent);
                    overridePendingTransition(R.anim.in_from_right,R.anim.out_to_left);
                }else {
                    ToastUtil.showToast(VisitOrderActivity.this,"案件号和标识不能为空");
                }

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
