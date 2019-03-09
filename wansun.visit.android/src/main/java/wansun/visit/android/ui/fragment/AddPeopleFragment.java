package wansun.visit.android.ui.fragment;

import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import wansun.visit.android.R;
import wansun.visit.android.api.apiManager;
import wansun.visit.android.bean.caseAddPeopleBean;
import wansun.visit.android.global.AppConfig;
import wansun.visit.android.net.requestBodyUtils;
import wansun.visit.android.utils.SharedUtils;
import wansun.visit.android.utils.ToastUtil;
import wansun.visit.android.utils.logUtils;
import wansun.visit.android.utils.netUtils;

/**
 * 添加联系人fragment
 * Created by User on 2019/2/26.
 */

public class AddPeopleFragment extends BaseFragment {
    TextView ed_people,ed_card_numbler,et_age,ed_address_remark;
    Spinner sp_add_people,sp_card_type,sp_sex;
    Button bt_submit;
    Integer relationId,cidType,gender;
    String relationText,cidTypeText,genderText;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_add_people;
    }

    @Override
    protected void initViews() {
        ed_people= (TextView) root.findViewById(R.id.ed_people);
        ed_card_numbler= (TextView) root.findViewById(R.id.ed_card_numbler);
        et_age= (TextView) root.findViewById(R.id.et_age);
        ed_address_remark= (TextView) root.findViewById(R.id.ed_address_remark);
        sp_card_type= (Spinner) root.findViewById(R.id.sp_card_type);
        sp_add_people= (Spinner) root.findViewById(R.id.sp_add_people);
        sp_sex= (Spinner) root.findViewById(R.id.sp_sex);
        bt_submit= (Button) root.findViewById(R.id.bt_submit);

    }

    @Override
    protected void initEvents() {
        sp_add_people.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                relationText = (String) sp_add_people.getSelectedItem();
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
        sp_card_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cidTypeText= (String) sp_card_type.getSelectedItem();
                if (position==0){
                    cidType=0;
                }else if (position==1){
                    cidType=1;
                }else if (position==2){
                    cidType=2;
                }else if (position==3){
                    cidType=3;
                }else if (position==4){
                    cidType=4;
                }else if (position==5){
                    cidType=5;
                }else if (position==6){
                    cidType=6;
                }else if (position==7){
                    cidType=7;
                }else if (position==8){
                    cidType=8;
                }else if (position==9){
                    cidType=999;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        sp_sex.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                genderText= (String) sp_sex.getSelectedItem();
                if (position==0){
                    gender=0;
                }else if (position==1){
                    gender=1;
                }else if (position==2){
                    gender=999;
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
     * 提交信息
     */
    private void doSubmit() {
        String name = ed_people.getText().toString().trim();
        if (TextUtils.isEmpty(name)){
            ToastUtil.showToast(getActivity(),"请输入联系人");
            return;
        }
        String cardName = ed_card_numbler.getText().toString().trim();
        if (TextUtils.isEmpty(cardName )){
            ToastUtil.showToast(getActivity(),"请输入卡号");
            return;
        }
        String age = et_age.getText().toString().trim();
        if (TextUtils.isEmpty(age)){
            ToastUtil.showToast(getActivity(),"请输入年龄");
            return;
        }
        //提交信息到服务器
        String caseCode = SharedUtils.getString("caseCode");
        Retrofit retrofit = netUtils.getRetrofit();
        apiManager manager= retrofit.create(apiManager.class);

        RequestBody requestBody = requestBodyUtils.visitCaseAddcontactsToService(caseCode,relationId,relationText,name,cardName,cidType,cidTypeText,gender,genderText,Integer.valueOf(age));
        Call<String> call = manager.visitCaseAddAddcontacts(requestBody);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String body = response.body();
                logUtils.d("提交联系人"+body);
                if (!TextUtils.isEmpty(body)){
                    Gson gson=new Gson();
                    caseAddPeopleBean data = gson.fromJson(body, new TypeToken<caseAddPeopleBean>() {}.getType());
                    String statusID = data.getStatusID();
                    if (AppConfig.SUCCESS.equals(statusID)){
                        ToastUtil.showToast(getActivity(), "添加联系人成功");
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
