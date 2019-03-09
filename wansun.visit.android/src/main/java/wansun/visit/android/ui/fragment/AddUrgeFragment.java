package wansun.visit.android.ui.fragment;

import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import wansun.visit.android.R;
import wansun.visit.android.api.apiManager;
import wansun.visit.android.bean.caseAddPhoneUrgeBean;
import wansun.visit.android.global.AppConfig;
import wansun.visit.android.net.requestBodyUtils;
import wansun.visit.android.utils.SharedUtils;
import wansun.visit.android.utils.ToastUtil;
import wansun.visit.android.utils.logUtils;
import wansun.visit.android.utils.netUtils;

/**
 * 添加电话催记Fragment
 * Created by User on 2019/2/26.
 */

public class AddUrgeFragment extends BaseFragment {
    EditText ed_people,et_phone_numbler,ed_content,ed_remark;
    Spinner sp_relation_people,sp_ummary,sp_result;
    Button bt_ugre_submit;
    Integer relationId,contactSummary,contactResult;
    String contactSummaryText,contactResultText;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_add_urge;
    }

    @Override
    protected void initViews() {
        ed_people= (EditText) root.findViewById(R.id.ed_people);
        sp_relation_people= (Spinner) root.findViewById(R.id.sp_relation_people);
        sp_ummary= (Spinner) root.findViewById(R.id.sp_ummary);
        sp_result= (Spinner) root.findViewById(R.id.sp_result);
        et_phone_numbler= (EditText) root.findViewById(R.id.et_phone_numbler);
        ed_content= (EditText) root.findViewById(R.id.ed_content);
        ed_remark= (EditText) root.findViewById(R.id.ed_remark);
        bt_ugre_submit= (Button) root.findViewById(R.id.bt_ugre_submit);

    }

    @Override
    protected void initEvents() {
        sp_relation_people.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position==0){
                    relationId=10;
                }else if (position==1){
                    relationId=20;
                }else if (position==2){
                    relationId=30;
                }else if (position==3){
                    relationId=40;
                }else if (position==4){
                    relationId=50;
                }else if (position==5){
                    relationId=60;
                }else if (position==6){
                    relationId=70;
                }else if (position==7){
                    relationId=80;
                }else if (position==8){
                    relationId=90;
                }else if (position==9){
                    relationId=100;
                }else if (position==10){
                    relationId=110;
                }else if (position==11){
                    relationId=999;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        sp_ummary.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                contactSummaryText=(String) sp_ummary.getSelectedItem();

                    contactSummary=position;

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        sp_result.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                contactResultText= (String) sp_result.getSelectedItem();
                if (position==23){
                    contactResult=999;
                }else {
                    contactResult=position;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        bt_ugre_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doSubmit();
            }
        });

    }

    /**
     * 提交数据到服务器
     */
    private void doSubmit() {
        String name = ed_people.getText().toString().trim();
        if (TextUtils.isEmpty(name)){
            ToastUtil.showToast(getActivity(),"请输入联系人名字");
            return;
        }
        String numbler = et_phone_numbler.getText().toString().trim();
        if (TextUtils.isEmpty(numbler)){
            ToastUtil.showToast(getActivity(),"请输入电话号码");
            return;
        }
        String content = ed_content.getText().toString().trim();
        if (TextUtils.isEmpty(content)){
            ToastUtil.showToast(getActivity(),"请输入催收记录");
        }
        String mark = ed_remark.getText().toString().trim();

        String caseCode = SharedUtils.getString("caseCode");
        String account = SharedUtils.getString("account");

        Retrofit retrofit = netUtils.getRetrofit();
        apiManager manager= retrofit.create(apiManager.class);
        RequestBody requestBody = requestBodyUtils.visitCaseAddphoneUrgeToService(caseCode,relationId,name,numbler,mark,account,contactSummary,content,contactResult,contactSummaryText,contactResultText);
        Call<String> call = manager.visitCaseAddPhoneUrge(requestBody);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String body = response.body();
                logUtils.d("提交电话催收"+body);
                if (!TextUtils.isEmpty(body)){
                    Gson gson=new Gson();
                    caseAddPhoneUrgeBean data = gson.fromJson(body, new TypeToken<caseAddPhoneUrgeBean>() {}.getType());
                    String statusID = data.getStatusID();
                    if (AppConfig.SUCCESS.equals(statusID)){
                        ToastUtil.showToast(getActivity(), "添加电话催记成功");
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
}
