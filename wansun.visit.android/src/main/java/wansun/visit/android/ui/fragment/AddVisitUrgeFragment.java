package wansun.visit.android.ui.fragment;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import wansun.visit.android.R;
import wansun.visit.android.api.apiManager;
import wansun.visit.android.bean.caseVistAddVisitUrgeBean;
import wansun.visit.android.global.AppConfig;
import wansun.visit.android.net.requestBodyUtils;
import wansun.visit.android.utils.SharedUtils;
import wansun.visit.android.utils.ToastUtil;
import wansun.visit.android.utils.logUtils;
import wansun.visit.android.utils.netUtils;

/**
 * 添加上门催记——外访
 * Created by User on 2019/3/6.
 */

public class AddVisitUrgeFragment extends BaseFragment {
   EditText et_visit_urge;
    Button bt_visit_submit;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_add_visit_urge;
    }

    @Override
    protected void initViews() {
        et_visit_urge= (EditText) root.findViewById(R.id.et_visit_urge);
        bt_visit_submit= (Button) root.findViewById(R.id.bt_visit_submit);


    }

    /**
     * 提交
     */
    private void doSubmit() {
        final String caseCode = SharedUtils.getString("caseCode");
        final String visitGuid = SharedUtils.getString("visitGuid");
        final   String account = SharedUtils.getString("account");
        final  String id = SharedUtils.getString("id");
        final    String trim = et_visit_urge.getText().toString().trim();
        if (TextUtils.isEmpty(trim)){
            ToastUtil.showToast(getActivity(),"请输入内容");
            return;
        }
                Retrofit retrofit = netUtils.getRetrofit();
                apiManager manager= retrofit.create(apiManager.class);
                Integer it = Integer.valueOf(id);
                RequestBody requestBody = requestBodyUtils.visitAddVisitUrgeToService(caseCode,visitGuid,account,it ,trim);
                Call<String> call = manager.visitCaseAddVisitUrge(requestBody);
                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        String body = response.body();
                        if (!TextUtils.isEmpty(body)){
                            Gson gson=new Gson();
                            caseVistAddVisitUrgeBean data = gson.fromJson(body, new TypeToken<caseVistAddVisitUrgeBean>() {}.getType());
                            String statusID = data.getStatusID();
                            if (AppConfig.SUCCESS.equals(statusID)){
                                ToastUtil.showToast(getActivity(), "添加外访成功");
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }
                });



    }

    @Override
    protected void initEvents() {

        bt_visit_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logUtils.d("点击提交");
                doSubmit();
            }
        });
    }

    @Override
    protected void initData() {

    }
}
