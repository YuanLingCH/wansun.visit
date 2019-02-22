package wansun.visit.android.ui.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import wansun.visit.android.R;
import wansun.visit.android.bean.visitItemBean;
import wansun.visit.android.utils.logUtils;

/**
 * 外访单对应的外访记录
 * Created by User on 2019/2/22.
 */

public class VistRecordActivity extends BaseActivity {
   ImageView iv_visit_back;
    TextView tv_visit_tobar;
    List data;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_visit_order_record;
    }

    @Override
    protected void initView() {
        tv_visit_tobar= (TextView) findViewById(R.id.tv_visit_tobar);
        tv_visit_tobar.setText("外访记录");
        iv_visit_back= (ImageView) findViewById(R.id.iv_visit_back);
        getIntentData();
    }
    private void getIntentData() {
        data=new ArrayList();
        List<visitItemBean.DataBeanX.DataBean> applyData = (List<visitItemBean.DataBeanX.DataBean>) getIntent().getSerializableExtra("visitData");
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
