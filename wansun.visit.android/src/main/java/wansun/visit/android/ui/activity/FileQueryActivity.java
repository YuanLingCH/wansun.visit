package wansun.visit.android.ui.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
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
import wansun.visit.android.adapter.caseFileQueryAdapter;
import wansun.visit.android.api.apiManager;
import wansun.visit.android.bean.caseFileQueryBean;
import wansun.visit.android.net.requestBodyUtils;
import wansun.visit.android.utils.SharedUtils;
import wansun.visit.android.utils.logUtils;
import wansun.visit.android.utils.netUtils;

/**
 *
 * 文件查询界面
 * Created by User on 2019/3/12.
 */

public class FileQueryActivity extends BaseActivity {
    ImageView iv_visit_back;
    TextView tv_visit_tobar;
    ListView lv_file;
    caseFileQueryAdapter adapter;
    List fileData;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_file_query;
    }

    @Override
    protected void initView() {
        iv_visit_back= (ImageView) findViewById(R.id.iv_visit_back);
        tv_visit_tobar= (TextView) findViewById(R.id.tv_visit_tobar);
        tv_visit_tobar.setText("文件查询");
        lv_file= (ListView) findViewById(R.id.lv_file);

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

    /**
     * 加载数据
     */
    @Override
    protected void initData() {
        fileData=new ArrayList();
        String caseCode = SharedUtils.getString("caseCode");
        String visitGuid = SharedUtils.getString("visitGuid");
        String id = SharedUtils.getString("id");
        //请求数据
        Retrofit retrofit = netUtils.getRetrofit();
        apiManager manager= retrofit.create(apiManager.class);
        final RequestBody requestBody = requestBodyUtils.queryFileFromeService(caseCode,visitGuid,Integer.valueOf(id));
        Call<String> call = manager.queryFileFromeService(requestBody);
        fileData.clear();
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String body = response.body();
                logUtils.d("文件下载数据"+body);
                if (!TextUtils.isEmpty(body)){
                        Gson gson=new Gson();
                        caseFileQueryBean data = gson.fromJson(body, new TypeToken<caseFileQueryBean>() {}.getType());
                        String statusID = data.getStatusID();
                        if (statusID.equals("200")){
                            if (data.getData()!=null){
                            List<caseFileQueryBean.DataBean> data1 = data.getData();

                            Iterator<caseFileQueryBean.DataBean> iterator = data1.iterator();
                            while (iterator.hasNext()){
                                caseFileQueryBean.DataBean next = iterator.next();
                                fileData.add(next);
                        }
                        updataUI();
                        }
                        }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                logUtils.d("走了onFailure"+t.toString());
            }
        });

    }

    private void updataUI() {
        logUtils.d("走了");
        adapter=new caseFileQueryAdapter(FileQueryActivity.this,fileData);
        lv_file.setAdapter(adapter);
    }

    @Override
    protected void initLise() {

    }
}
