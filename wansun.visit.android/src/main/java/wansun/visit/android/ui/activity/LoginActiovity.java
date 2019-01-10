package wansun.visit.android.ui.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import wansun.visit.android.R;
import wansun.visit.android.global.waifangApplication;
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
        String acount = et_acount.getText().toString().trim();
        String pasword = et_pasw.getText().toString().trim();
        if (TextUtils.isEmpty(acount)){
            ToastUtil.showToast(waifangApplication.getContext(),"请输入账号");
            return;
        }
        if (TextUtils.isEmpty(pasword)){
            ToastUtil.showToast(waifangApplication.getContext(),"请输入密码");
            return;
        }
        if (!TextUtils.isEmpty(acount)&&!TextUtils.isEmpty(pasword)){
            //进入定位界面 
            // TODO: 2019/1/10   以后用真实数据 ，测试阶段用假数据
            if (acount.equals("test")&&pasword.equals("123456")){
                Intent intent=new Intent(LoginActiovity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }else {
                ToastUtil.showToast(waifangApplication.getContext(),"请输入账号:test,密码：1234546  进行测试");
            }
        }
    }

    @Override
    protected void initData() {

    }
}
