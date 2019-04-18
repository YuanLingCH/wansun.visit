package wansun.visit.android.ui.activity;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import wansun.visit.android.R;
import wansun.visit.android.adapter.visitOrderAdapter;
import wansun.visit.android.api.apiManager;
import wansun.visit.android.bean.visitItemBean;
import wansun.visit.android.net.requestBodyUtils;
import wansun.visit.android.utils.NetWorkTesting;
import wansun.visit.android.utils.SharedUtils;
import wansun.visit.android.utils.ToastUtil;
import wansun.visit.android.utils.logUtils;
import wansun.visit.android.utils.netUtils;
import wansun.visit.android.view.EmptyLayout;
import wansun.visit.android.view.loadMoreListView;

/**
 * 历史外访单
 * Created by User on 2019/2/22.
 */

public class VistRecordActivity extends BaseActivity implements loadMoreListView.onLoadMoreListenner  {
   ImageView iv_visit_back;
    TextView tv_visit_tobar;
    List visitData;
    loadMoreListView lv_visit_order;
    EmptyLayout empty_layout;
    visitOrderAdapter adapter;
    // 当前页号
    public  int pageNo=1;
    //每页显示的记录输
    public  int pageSize=10;
    private  boolean isFirst=false;
    TextView total,current_total;
    int currentNumbler=0;
    int counts;// 加载数据的总条数
    SwipeRefreshLayout srf;//下拉刷新控件
  //

    @Override
    protected int getLayoutId() {
        return R.layout.activity_visit_order_record;
    }

    @Override
    protected void initView() {
        tv_visit_tobar= (TextView) findViewById(R.id.tv_visit_tobar);
        tv_visit_tobar.setText("历史外访单");
        iv_visit_back= (ImageView) findViewById(R.id.iv_visit_back_one);
  lv_visit_order= (loadMoreListView) findViewById(R.id.lv_visit_order);
        empty_layout= (EmptyLayout) findViewById(R.id.empty_layout);
        total= (TextView) findViewById(R.id.total);
        current_total= (TextView) findViewById(R.id.current_total);
      srf= (SwipeRefreshLayout) findViewById(R.id.srf);
        isFirst=true;  //第一次加载数据*/

        getIntentData();
    }
    private void getIntentData() {

    }

    private void updataUI() {
        adapter=new visitOrderAdapter(this,visitData,true); //true 为完成
        lv_visit_order.setAdapter(adapter);

      //  adapter.notifyDataSetChanged();
    }

    @Override
    protected void initEvent() {
        visitData=new ArrayList();
        iv_visit_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                logUtils.d("点击返回按钮");
                overridePendingTransition(R.anim.in_from_left,R.anim.out_to_right);
            }
        });



      lv_visit_order.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                visitItemBean.DataBean  o = (visitItemBean.DataBean) visitData.get(position);
                String caseCode = o.getCaseCode();
                String bacthCode = o.getBatchCode();
                String visitGuid = o.getVisitGuid();

                if (!TextUtils.isEmpty(caseCode)&&!TextUtils.isEmpty(visitGuid )){
                    Intent intent =new Intent(VistRecordActivity.this,OutBoundActivity.class);
                    intent.putExtra("caseCode",caseCode);
                    intent.putExtra("visitGuid",visitGuid);
                    startActivity(intent);
                    overridePendingTransition(R.anim.in_from_right,R.anim.out_to_left);
                }else {
                    ToastUtil.showToast(VistRecordActivity.this,"案件号和标识不能为空");
                }

            }
        });

        empty_layout.setOnLayoutClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logUtils.d("点击重新加载数据");
                loadData( pageNo+"");
            }
        });
        //设置回调
        lv_visit_order.setLoadMoreListnner(this);


    srf.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData(pageNo+"");
            }
        });

    }

    @Override
    protected void initData() {
        //加载数据
  loadData(pageNo+"");
    }

    private void loadData(String pageNo) {

        NetWorkTesting net=new NetWorkTesting(this);
        if (net.isNetWorkAvailable()){

        String userName = SharedUtils.getString("account");
        logUtils.d("userName"+userName);
        Retrofit retrofit = netUtils.getRetrofit();
        apiManager manager= retrofit.create(apiManager.class);
        RequestBody requestBody = requestBodyUtils.visitItemToService(userName,false,pageNo,pageSize+"");
        Call<String> call = manager.visitListFormeService(requestBody);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String body = response.body();
                logUtils.d("body"+body);
                if (!TextUtils.isEmpty(body)){
                    Gson gson=new Gson();
                    visitItemBean bean = gson.fromJson(body, new TypeToken<visitItemBean>() {}.getType());
                    String statusID = bean.getStatusID();
                    if (statusID.equals("200")){
                        List<visitItemBean.DataBean> data = bean.getData();
                        visitItemBean.PageBean page = bean.getPage();
                        counts = page.getCounts();
                        total.setText("总条数："+ counts);
                        if (data.size()>0){
                            Iterator<visitItemBean.DataBean> iterator = data.iterator();

                            while (iterator.hasNext()){
                                visitItemBean.DataBean next = iterator.next();
                                if (isFirst){
                                    visitData.add(next);
                                    isFirst=false;
                                    updataUI();
                                }else {
                                    adapter.addItem(next);
                                    adapter.notifyDataSetChanged();
                                    lv_visit_order.loadFinsh();
                                }

                                String name = next.getName();
                                logUtils.d("债务人名字："+name);
                                ++currentNumbler;
                                srf.setRefreshing(false);
                            }

                        }else {
                            ToastUtil.showToast(VistRecordActivity.this,"没有数据...");
                            logUtils.d("没有数据activity");
                            empty_layout.setVisibility(View.VISIBLE);
                            empty_layout.setErrorType(EmptyLayout.NODATA);
                        }
                        srf.setRefreshing(false);
                        current_total.setText("当前条数："+currentNumbler);
                    }
                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                srf.setRefreshing(false);
            }
        });
        }else {
            empty_layout.setErrorType(EmptyLayout.NETWORK_LOADING);
        }
    }

    @Override
    protected void initLise() {

    }


    @Override
    public void loadMore() {
        logUtils.d("上啦加载更多走了"+pageNo);
      int page=  ++pageNo;
        logUtils.d("上啦加载更多走了page"+page);
     if (counts>pageSize*page){ //总条数 要大于已经加载的数据条数
            loadData(page+"");
            logUtils.d("上啦加载更多走了");
        }else {
            lv_visit_order.loadFinsh();
            ToastUtil.showToast(this,"没有更多数据...");
        }


 //  loadData("1");
    }
}
