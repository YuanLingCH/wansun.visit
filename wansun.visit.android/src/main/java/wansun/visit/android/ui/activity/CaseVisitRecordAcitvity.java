package wansun.visit.android.ui.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import wansun.visit.android.R;
import wansun.visit.android.adapter.caseVisitRecordAdapter;
import wansun.visit.android.bean.caseDetailBean;

/**
 * Created by User on 2019/2/21.
 */

public class CaseVisitRecordAcitvity extends BaseActivity {
    ImageView iv_visit_back;
    TextView tv_visit_tobar;
    caseVisitRecordAdapter adapter;
    List data;
    ListView lv_case_urge_visit_record;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_case_ugre_record;
    }

    @Override
    protected void initView() {
        iv_visit_back= (ImageView) findViewById(R.id.iv_visit_back);
        tv_visit_tobar= (TextView) findViewById(R.id.tv_visit_tobar);
        lv_case_urge_visit_record= (ListView) findViewById(R.id.lv_case_urge_visit_record);
        getIntentData();
    }

    private void getIntentData() {
        data=new ArrayList();
        List<caseDetailBean.DataBean.VisitRecordBean> visitRecord = (List<caseDetailBean.DataBean.VisitRecordBean>) getIntent().getSerializableExtra("visitRecord");
        Iterator<caseDetailBean.DataBean.VisitRecordBean> iterator = visitRecord.iterator();
        data.clear();
        while (iterator.hasNext()){
            caseDetailBean.DataBean.VisitRecordBean next = iterator.next();
            data.add(next);
        }
        updataUI();
    }

    private void updataUI() {
        adapter=new caseVisitRecordAdapter(CaseVisitRecordAcitvity.this,data);
        lv_case_urge_visit_record.setAdapter(adapter);


    }

    @Override
    protected void initEvent() {
        iv_visit_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.in_from_left, R.anim.out_to_right);  //左边进去 右边出来
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
