package wansun.visit.android.ui.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import wansun.visit.android.R;
import wansun.visit.android.adapter.caseUrgeRecordAdapter;
import wansun.visit.android.bean.caseDetailBean;

/**
 * Created by User on 2019/2/21.
 */

public class UrgeRecordActivity extends BaseActivity {
    ImageView iv_visit_back;
   TextView tv_visit_tobar ;
    List data;
    ListView lv_urge_record;
    caseUrgeRecordAdapter adapter;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_urge_record;
    }

    @Override
    protected void initView() {
        iv_visit_back= (ImageView) findViewById(R.id.iv_visit_back);
        tv_visit_tobar= (TextView) findViewById(R.id.tv_visit_tobar);
        tv_visit_tobar.setText("急催详情");
        lv_urge_record= (ListView) findViewById(R.id.lv_urge_record);
        getIntentData();
    }

    private void getIntentData() {
        data=new ArrayList();
        List<caseDetailBean.DataBean.UrgeRecordBean> urgeRecord = (List<caseDetailBean.DataBean.UrgeRecordBean>) getIntent().getSerializableExtra("urgeRecord");
        Iterator<caseDetailBean.DataBean.UrgeRecordBean> iterator = urgeRecord.iterator();
        data.clear();
        while (iterator.hasNext()){
            caseDetailBean.DataBean.UrgeRecordBean next = iterator.next();
            data.add(next);
        }
        updataUI();
    }

    private void updataUI() {
        adapter=new caseUrgeRecordAdapter(UrgeRecordActivity.this,data);
        lv_urge_record.setAdapter(adapter);

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
