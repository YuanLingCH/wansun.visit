package wansun.visit.android.ui.fragment;

import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

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
 * 添加电话号码fragment
 * Created by User on 2019/2/26.
 */

public class AddPhoneFragment extends BaseFragment {
   EditText ed_person,ed_phone_number,ed_unit;
    Spinner sp_person_type,sp_phone_type,sp_unit_type;
    List<String>RelationData;
    Button bt_submit;
    EditText ed_remark;
    String relationText, phoneTypeText,phoneStatusText;
    Integer relationId ,phoneType,phoneStatus;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_add_phone;
    }

    @Override
    protected void initViews() {
        ed_person= (EditText) root.findViewById(R.id.ed_person);
        ed_phone_number= (EditText) root.findViewById(R.id.ed_phone_number);
        ed_unit= (EditText) root.findViewById(R.id.ed_unit);
        sp_person_type= (Spinner) root.findViewById(R.id.sp_person_type);
        sp_phone_type= (Spinner) root.findViewById(R.id.sp_phone_type);
        sp_unit_type= (Spinner) root.findViewById(R.id.sp_unit_type);
        bt_submit= (Button) root.findViewById(R.id.bt_submit);
        ed_remark= (EditText) root.findViewById(R.id.ed_remark);


    }

    @Override
    protected void initEvents() {
        addRelationData();

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
                logUtils.d("点击事件"+position+"relationId"+relationId);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        sp_phone_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                logUtils.d("点击事件"+position);
                // str = (String) sp.getSelectedItem();
          phoneTypeText= (String) sp_phone_type.getSelectedItem();
                logUtils.d("点击事件"+position+"text"+phoneTypeText);
                if (position==0){
                    phoneType=10;
                }else if (position==1){
                    phoneType=20;
                }else if (position==2){
                    phoneType=30;
                }else if (position==3){
                    phoneType=40;
                }else if (position==4){
                    phoneType=50;
                }else if (position==5){
                    phoneType=999;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
            //电话状态
        sp_unit_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                logUtils.d("点击事件"+position);
                phoneStatusText = (String) sp_unit_type.getSelectedItem();
                if (position==0){
                    phoneStatus=0;
                }else if (position==1){
                    phoneStatus=1;
                }else if (position==2){
                    phoneStatus=2;
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
     * 上传数据到服务器
     */
    private void doSubmit() {
        String person = ed_person.getText().toString().trim();
        if (TextUtils.isEmpty(person)){
            ToastUtil.showToast(getActivity(),"请输入联系人");
            return;
        }
        String phone = ed_phone_number.getText().toString().trim();
        if (TextUtils.isEmpty(phone)){
            ToastUtil.showToast(getActivity(),"请输入电话号码");
           return;
        }
        if (!TextUtils.isEmpty(person)&&!TextUtils.isEmpty(phone)){
        String caseCode = SharedUtils.getString("caseCode");
        String account = SharedUtils.getString("account");
            String remark = ed_remark.getText().toString().trim();
            String unit = ed_unit.getText().toString().trim();
            Retrofit retrofit = netUtils.getRetrofit();
        apiManager manager= retrofit.create(apiManager.class);
    RequestBody requestBody = requestBodyUtils.visitCaseAddPhoneToService(caseCode,relationId,person,phone,phoneStatus,
           unit,remark,relationText,phoneType,phoneTypeText,phoneStatusText);
  Call<String> call = manager.visitCaseAddPhone(requestBody);
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
                        ToastUtil.showToast(getActivity(), "添加电话成功");
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
        }

    }

    private void addRelationData() {
        RelationData=new ArrayList<>();
        RelationData.add("夫妻");
        RelationData.add("父母");
        RelationData.add("子女");
        RelationData.add("朋友");
        RelationData.add("同事");
        RelationData.add("本人");
        RelationData.add("亲戚");
        RelationData.add("担保人");
        RelationData.add("老板");
        RelationData.add("邻居");
        RelationData.add("兄弟姐妹");
        RelationData.add("其他");
    }

    @Override
    protected void initData() {

    }
}
