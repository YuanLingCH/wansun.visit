package wansun.visit.android.ui.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import wansun.visit.android.R;

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
