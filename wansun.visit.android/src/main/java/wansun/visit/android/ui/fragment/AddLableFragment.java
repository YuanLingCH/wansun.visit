package wansun.visit.android.ui.fragment;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import wansun.visit.android.R;
import wansun.visit.android.api.apiManager;
import wansun.visit.android.bean.stateMessageBean;
import wansun.visit.android.global.AppConfig;
import wansun.visit.android.net.requestBodyUtils;
import wansun.visit.android.utils.SharedUtils;
import wansun.visit.android.utils.ToastUtil;
import wansun.visit.android.utils.logUtils;
import wansun.visit.android.utils.netUtils;

/**
 * 添加标记fragment
 * Created by User on 2019/2/27.
 */

public class AddLableFragment extends BaseFragment {
    Button but_lab_complete,but_lab_visit;
    String caseCode;
    String visitGuid;
    String account;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_lable;
    }

    @Override
    protected void initViews() {
        but_lab_complete= (Button) root.findViewById(R.id.but_lab_complete);
        but_lab_visit= (Button) root.findViewById(R.id.but_lab_visit);
        getIntentData();
    }

    @Override
    protected void initEvents() {
        //标记为完成
        but_lab_complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    doComplete();
            }
        });
        //标记为外访
        but_lab_visit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    doVisit();
            }
        });
    }

    private void doVisit() {
        Retrofit retrofit = netUtils.getRetrofit();
        apiManager manager= retrofit.create(apiManager.class);
        final RequestBody requestBody = requestBodyUtils.visitLabCurrentVisitToService(caseCode,visitGuid,account);
        Call<String> call = manager.visitLabCurrentVisit(requestBody);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String body = response.body();
                logUtils.d("提交数据"+body);
                if (!TextUtils.isEmpty(body)){
                    Gson gson=new Gson();
                    stateMessageBean data = gson.fromJson(body, new TypeToken<stateMessageBean>() {}.getType());
                    String statusID = data.getStatusID();
                    if (AppConfig.SUCCESS.equals(statusID)){
                        ToastUtil.showToast(getActivity(), data.getData());
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }

    private void doComplete() {
        Retrofit retrofit = netUtils.getRetrofit();
        apiManager manager= retrofit.create(apiManager.class);
        final RequestBody requestBody = requestBodyUtils.visitLabCompleteToService(caseCode,visitGuid,account);
        Call<String> call = manager.visitLabCompleterState(requestBody);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String body = response.body();
                logUtils.d("提交数据"+body);
                if (!TextUtils.isEmpty(body)){
                    Gson gson=new Gson();
                    stateMessageBean data = gson.fromJson(body, new TypeToken<stateMessageBean>() {}.getType());
                    String statusID = data.getStatusID();
                    if (AppConfig.SUCCESS.equals(statusID)){
                        ToastUtil.showToast(getActivity(), data.getData());
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }

    @Override
    protected void initData() {

    }

    public void getIntentData() {
    caseCode = SharedUtils.getString("caseCode");
    visitGuid = SharedUtils.getString("visitGuid");
     account = SharedUtils.getString("account");
        logUtils.d("标记Fragmnet"+caseCode+":"+visitGuid+":"+account );

    }
}
