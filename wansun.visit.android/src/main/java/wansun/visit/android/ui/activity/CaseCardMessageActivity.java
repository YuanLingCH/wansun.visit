package wansun.visit.android.ui.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import wansun.visit.android.R;
import wansun.visit.android.adapter.caseCardMessageAdapter;
import wansun.visit.android.bean.caseDetailBean;

/**
 * Created by User on 2019/2/21.
 */

public class CaseCardMessageActivity extends BaseActivity {
    ImageView iv_visit_back;
    TextView tv_visit_tobar;
    List data;
    caseCardMessageAdapter adapter;
    ListView lv_card_message;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_case_card_message;
    }

    @Override
    protected void initView() {
        iv_visit_back= (ImageView) findViewById(R.id.iv_visit_back);
        tv_visit_tobar= (TextView) findViewById(R.id.tv_visit_tobar);
        tv_visit_tobar.setText("卡信息");
        lv_card_message= (ListView) findViewById(R.id.lv_card_message);
        getIntentData();


    }

    private void getIntentData() {
        data=new ArrayList();
        List<caseDetailBean.DataBean.CardDetailsBean> cardDetails= (List<caseDetailBean.DataBean.CardDetailsBean>) getIntent().getSerializableExtra("cardDetails");
        Iterator<caseDetailBean.DataBean.CardDetailsBean> iterator = cardDetails.iterator();
        data.clear();
        while (iterator.hasNext()){
            caseDetailBean.DataBean.CardDetailsBean next = iterator.next();
            data.add(next);
        }
        updataUI();
    }

    private void updataUI() {
        adapter=new caseCardMessageAdapter(CaseCardMessageActivity.this,data);
        lv_card_message.setAdapter(adapter);
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
