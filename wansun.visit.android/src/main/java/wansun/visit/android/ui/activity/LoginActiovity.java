package wansun.visit.android.ui.activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import wansun.visit.android.R;
import wansun.visit.android.api.apiManager;
import wansun.visit.android.bean.loginBean;
import wansun.visit.android.global.AppConfig;
import wansun.visit.android.global.waifangApplication;
import wansun.visit.android.service.autoUpdataService;
import wansun.visit.android.utils.NetWorkTesting;
import wansun.visit.android.utils.RSAUtils;
import wansun.visit.android.utils.SharedUtils;
import wansun.visit.android.utils.ToastUtil;
import wansun.visit.android.utils.dialogUtils;
import wansun.visit.android.utils.logUtils;
import wansun.visit.android.utils.netUtils;
/**
 *
 * 登陆界面
 * Created by User on 2019/1/10.
 */

public class LoginActiovity extends BaseActivity {
    EditText et_acount,et_pasw;
    Button but_login;
    LinearLayout login_ll;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }
    @Override
    protected void initView() {
        et_acount= (EditText) findViewById(R.id.et_acount);
        et_pasw= (EditText) findViewById(R.id.et_pasw);
        but_login= (Button) findViewById(R.id.but_login);
        login_ll= (LinearLayout) findViewById(R.id.login_ll);
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
            NetWorkTesting net = new NetWorkTesting(LoginActiovity.this);
            if (net.isNetWorkAvailable()) {
                WindowManager manager = getWindowManager();
                View view = LayoutInflater.from(waifangApplication.getContext()).inflate(R.layout.loading_layout, null);
                final dialogUtils utils = new dialogUtils(LoginActiovity.this, manager, view);
                utils.getDialog();
                Retrofit retrofit = netUtils.getRetrofit();
                apiManager manager1 = retrofit.create(apiManager.class);
                String  bytes=null;
                try {
               bytes = RSAUtils.encryptByPublicKey(pasword,RSAUtils.encrykey);
                    logUtils.d("加密数据"+bytes.replaceAll("\r\n|\r|\n", ""));
                } catch (Exception e) {
                    e.printStackTrace();
                    logUtils.d("加密数据e"+e.toString());
                }

           Call<String> call = manager1.login(acount,  bytes.replaceAll("\r\n|\r|\n", ""));
                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        utils.cancleDialog();
                        String body = response.body();
                        if (!TextUtils.isEmpty(body)){
                        logUtils.d("登陆" + body);
                        Gson gson=new Gson();
                        loginBean bean = gson.fromJson(body, new TypeToken<loginBean>() {}.getType());
                        String statusID = bean.getStatusID();
                        String message = bean.getMessage();
                        if (statusID.equals(AppConfig.SUCCESS)){// 200成功
                            Intent i = new Intent(LoginActiovity.this, autoUpdataService.class);
                            startService(i);
                            Intent intent = new Intent(LoginActiovity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                            overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left); // 由右向左滑入的效果
                            SharedUtils.putString("account",acount);
                            loginBean.DataBean data = bean.getData();
                            String id = data.getId()+"";
                            SharedUtils.putString("id",id);

                        }else {
                            ToastUtil.showToast(LoginActiovity.this,message);
                        }
                        }
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
            }

            }
        }


    @Override
    protected void initData() {

    }

    @Override
    protected void initLise() {

    }
}
