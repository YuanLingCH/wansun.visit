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
import wansun.visit.android.bean.stateMessageBean;
import wansun.visit.android.global.AppConfig;
import wansun.visit.android.net.requestBodyUtils;
import wansun.visit.android.utils.SharedUtils;
import wansun.visit.android.utils.ToastUtil;
import wansun.visit.android.utils.logUtils;
import wansun.visit.android.utils.netUtils;

/**
 * 添加地址fragment
 * Created by User on 2019/2/26.
 */

public class AddAdressFragment extends BaseFragment{
    EditText ed_person,ed_add_address,ed_unit,ed_remark;
    Spinner sp_person_type,sp_address_type,sp_address_state;
    Button bt_submit;
 Integer relationId,addressType ,addressStatus;
    String relationText,addressTypeText,addressStateText;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_add_adress;
    }

    @Override
    protected void initViews() {
        ed_person= (EditText) root.findViewById(R.id.ed_person);
        ed_add_address= (EditText) root.findViewById(R.id.ed_add_address);
        sp_person_type= (Spinner) root.findViewById(R.id.sp_person_type);
        sp_address_type= (Spinner) root.findViewById(R.id.sp_address_type);
        sp_address_state= (Spinner) root.findViewById(R.id.sp_address_state);
        ed_unit= (EditText) root.findViewById(R.id.ed_unit);
        ed_remark= (EditText) root.findViewById(R.id.ed_remark);
        bt_submit= (Button) root.findViewById(R.id.bt_submit);


    }

    @Override
    protected void initEvents() {
        sp_person_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                relationText = (String) sp_person_type.getSelectedItem();
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

        sp_address_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                addressTypeText = (String) sp_address_type.getSelectedItem();
                if (position==0){
                    addressType =0;
                }else if (position==1){
                    addressType =1;
                }else if (position==2){
                    addressType =2;
                }else if (position==3){
                    addressType =3;
                }else if (position==4){
                    addressType =4;
                }else if (position==5){
                    addressType =5;
                }else if (position==6){
                    addressType =6;
                }else if (position==7){
                    addressType =7;
                }else if (position==8){
                    addressType =8;
                }else if (position==9){
                    addressType =9;
                }else if (position==10){
                    addressType =10;
                }else if (position==11){
                    addressType =999;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        sp_address_state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                addressStateText = (String) sp_address_state.getSelectedItem();
                if (position==0){
                    addressStatus=0;
                }else if (position==1){
                    addressStatus=1;
                }else if (position==2){
                    addressStatus=2;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        bt_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doSubmit();
            }
        });

    }

    /**
     * 提交数据
     */
    private void doSubmit() {
        String name = ed_person.getText().toString().trim();
    if (TextUtils.isEmpty(name)){
        ToastUtil.showToast(getActivity(),"请输入联系人");
        return;
    }
        String address = ed_add_address.getText().toString().trim();
        if (TextUtils.isEmpty(address)){
            ToastUtil.showToast(getActivity(),"请输入地址");
        }
        String unit = ed_unit.getText().toString().trim();
        String remark = ed_remark.getText().toString().trim();
        if (!TextUtils.isEmpty(name)&&!TextUtils.isEmpty(remark)){
            String caseCode = SharedUtils.getString("caseCode");
            Retrofit retrofit = netUtils.getRetrofit();
            apiManager manager= retrofit.create(apiManager.class);

            RequestBody requestBody = requestBodyUtils.visitCaseAddAdressMessageToService(caseCode,relationId+"",name,addressType,address,addressStatus,remark,relationText,unit,addressTypeText,addressStateText);
            Call<String> call = manager.visitCaseAddAddddressMessage(requestBody);
            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    String body = response.body();
                    logUtils.d("提交数据电话号码"+body);
                    if (!TextUtils.isEmpty(body)){
                        Gson gson=new Gson();
                        stateMessageBean data = gson.fromJson(body, new TypeToken<stateMessageBean>() {}.getType());
                        String statusID = data.getStatusID();
                        if (AppConfig.SUCCESS.equals(statusID)){
                            ToastUtil.showToast(getActivity(), "添加地址成功");
                        }else {
                            ToastUtil.showToast(getActivity(), "添加地址失败");
                        }
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {

                }
            });
        }

    }

    @Override
    protected void initData() {

    }
}
