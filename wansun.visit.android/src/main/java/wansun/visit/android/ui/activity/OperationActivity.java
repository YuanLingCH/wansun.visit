package wansun.visit.android.ui.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import wansun.visit.android.R;
import wansun.visit.android.adapter.BaseFragmentPagerAdapter;
import wansun.visit.android.ui.fragment.AddAdressFragment;
import wansun.visit.android.ui.fragment.AddLableFragment;
import wansun.visit.android.ui.fragment.AddPeopleFragment;
import wansun.visit.android.ui.fragment.AddPhoneFragment;
import wansun.visit.android.ui.fragment.AddUrgeFragment;
import wansun.visit.android.ui.fragment.AddVisitUrgeFragment;

/**
 *
 * 操作数据 增加 修改
 * Created by User on 2019/2/26.
 */

public class OperationActivity extends BaseActivity {
    ImageView iv_visit_back;
    TextView tv_visit_tobar;
    private TabLayout tabLayout;
    ViewPager vp;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_operation;
    }

    @Override
    protected void initView() {
        iv_visit_back= (ImageView) findViewById(R.id.iv_visit_back);
        tv_visit_tobar= (TextView) findViewById(R.id.tv_visit_tobar);
        tv_visit_tobar.setText("操作");
        tabLayout= (TabLayout) findViewById(R.id.tab);
        vp= (ViewPager) findViewById(R.id.vp);
        tabLayout.addTab(tabLayout.newTab().setText("添加电话催记"));
        tabLayout.addTab(tabLayout.newTab().setText("添加外访催记"));
        tabLayout.addTab(tabLayout.newTab().setText("添加地址"));
        tabLayout.addTab(tabLayout.newTab().setText("添加联系人"));
        tabLayout.addTab(tabLayout.newTab().setText("添加电话"));
        tabLayout.addTab(tabLayout.newTab().setText("标记"));
        tabLayout.setTabMode(TabLayout.GRAVITY_FILL);
        List<Fragment> list = new ArrayList<>();
        list.add(new AddUrgeFragment());
        list.add(new AddVisitUrgeFragment());
        list.add(new AddAdressFragment());
        list.add(new AddPeopleFragment());
        list.add(new AddPhoneFragment());
        list.add(new AddLableFragment());
        // Fragment嵌套Fragment传入FragmentManager用getChildFragmentManager()
        BaseFragmentPagerAdapter pagerAdapter = new BaseFragmentPagerAdapter(
                getSupportFragmentManager(), list);
        vp.setAdapter(pagerAdapter);






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
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //让TabLayout和ViewPager联动
                tabLayout.getTabAt(position).select();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //让viewPager和TabLayout联动
                vp.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

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
