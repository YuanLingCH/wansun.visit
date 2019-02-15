package wansun.visit.android.ui.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import wansun.visit.android.R;
import wansun.visit.android.global.waifangApplication;
import wansun.visit.android.utils.SharedUtils;
import wansun.visit.android.utils.ToastUtil;

/**
 *
 * 登陆界面
 * Created by User on 2019/1/10.
 */

public class LoginActiovity extends BaseActivity {
    EditText et_acount,et_pasw;
    Button but_login;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        et_acount= (EditText) findViewById(R.id.et_acount);
        et_pasw= (EditText) findViewById(R.id.et_pasw);
        but_login= (Button) findViewById(R.id.but_login);
        String account = SharedUtils.getString("account");
        if (!TextUtils.isEmpty(account)){
            et_acount.setText(account);
            et_pasw.requestFocus(); //光标移动到指定位置
        }
    }

    @Override
    protected void initEvent() {
        but_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    /**
     * 点击登陆按钮
     */
    private void login() {
        final String acount = et_acount.getText().toString().trim();
        String pasword = et_pasw.getText().toString().trim();
        if (TextUtils.isEmpty(acount)) {
            ToastUtil.showToast(waifangApplication.getContext(), R.string.login_acount);
            return;
        }
        if (TextUtils.isEmpty(pasword)) {
            ToastUtil.showToast(waifangApplication.getContext(), R.string.login_pasw);
            return;
        }
        if (!TextUtils.isEmpty(acount) && !TextUtils.isEmpty(pasword)) {
            // TODO: 2019/1/10   以后用真实数据 ，测试阶段用假数据
            Intent intent = new Intent(LoginActiovity.this, MainActivity.class);
            startActivity(intent);
            finish();

 /*           NetWorkTesting net=new NetWorkTesting(LoginActiovity.this);
            if (net.isNetWorkAvailable()){
                final dialogUtils utils=new dialogUtils(LoginActiovity.this);
                utils.getDialog();
                Retrofit retrofit = netUtils.getRetrofit();
                apiManager manager1 = retrofit.create(apiManager.class);
                Call<String> call = manager1.login(acount, pasword);
                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        utils.cancleDialog();
                        String body = response.body();
                        Intent intent = new Intent(LoginActiovity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                        logUtils.d("登陆" + body);
                        SharedUtils.putString("account",acount);
                    }
                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        logUtils.d("登陆onFailure" + t.toString());
                        utils.cancleDialog();
                        ToastUtil.showToast(LoginActiovity.this,R.string.login_faile);

                    }
                });
            }else {
                ToastUtil.showToast(LoginActiovity.this,R.string.network_unavailing);
            }*/

        }
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initLise() {

    }
}
