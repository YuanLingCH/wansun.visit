package wansun.visit.android.ui.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import wansun.visit.android.R;
import wansun.visit.android.adapter.visitAdapter;

/**
 * 外访界面
 * Created by User on 2019/1/22.
 */

public class OutBoundActivity extends BaseActivity {
    ImageView iv_visit_back;
    ListView lv_visit;
    visitAdapter adapter;
    List data;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_out_round;
    }

    @Override
    protected void initView() {
        iv_visit_back= (ImageView) findViewById(R.id.iv_visit_back);
        lv_visit= (ListView) findViewById(R.id.lv_visit);
    }

    @Override
    protected void initEvent() {
        iv_visit_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    /**
     * 数据加载
     */
    @Override
    protected void initData() {
        data=new ArrayList();
        for (int i = 0; i < 5; i++) {
            data.add(i);
        }
        adapter=new visitAdapter(this,data);
        lv_visit.setAdapter(adapter);
    }

    @Override
    protected void initLise() {

    }
}
