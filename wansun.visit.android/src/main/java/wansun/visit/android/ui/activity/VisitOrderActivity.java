package wansun.visit.android.ui.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import wansun.visit.android.R;
import wansun.visit.android.adapter.visitOrderAdapter;
import wansun.visit.android.utils.NetWorkTesting;
import wansun.visit.android.utils.ToastUtil;

/**
 * 外访单界面
 * Created by User on 2019/2/19.
 */

public class VisitOrderActivity extends BaseActivity {
    TextView tv_visit_tobar;
    ImageView iv_visit_back;
    ListView lv_visit_order;
    visitOrderAdapter adapter;
    List data;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_visit_order;
    }

    @Override
    protected void initView() {
        tv_visit_tobar= (TextView) findViewById(R.id.tv_visit_tobar);
        iv_visit_back= (ImageView) findViewById(R.id.iv_visit_back);
        lv_visit_order= (ListView) findViewById(R.id.lv_visit_order);

    }

    @Override
    protected void initEvent() {
        tv_visit_tobar.setText("我的外访单");
        iv_visit_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    protected void initData() {
        NetWorkTesting net=new NetWorkTesting(this);
        if (net.isNetWorkAvailable()){
            data=new ArrayList();
            for (int i = 0; i < 5; i++) {
                data.add(i);
            }
            adapter=new visitOrderAdapter(this,data);
            lv_visit_order.setAdapter(adapter);
        }else {
            ToastUtil.showToast(this,R.string.network_unavailing);
        }

    }

    @Override
    protected void initLise() {

    }
}
