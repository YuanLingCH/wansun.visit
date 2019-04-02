package wansun.visit.android.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.WindowManager;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.Timer;
import java.util.TimerTask;

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
import wansun.visit.android.utils.logUtils;
import wansun.visit.android.utils.netUtils;

/**
 * Created by User on 2019/3/26.
 */

public class WelocmeActivity extends BaseActivity {
    TextView tv_imie,tv_check_state,tv_link_devices;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void initView() {
        tv_imie= (TextView) findViewById(R.id.tv_imie);
        tv_link_devices= (TextView) findViewById(R.id.tv_link_devices);
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {
        TelephonyManager telephonyManager=(TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
        final String imei=telephonyManager.getDeviceId();
        logUtils.d("手机串号"+imei);
        tv_link_devices.setText(R.string.link_devices);
        Retrofit retrofit = netUtils.getRetrofit();
        apiManager manager= retrofit.create(apiManager.class);
        final RequestBody requestBody = requestBodyUtils.checkImie(imei);
        Call<String> call = manager.checkImie(requestBody);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String body = response.body();
                logUtils.d("手机串号下载"+body);
                if (!TextUtils.isEmpty(body)){
                    Gson gson=new Gson();
                    stateMessageBean data = gson.fromJson(body, new TypeToken<stateMessageBean>() {}.getType());
                    String statusID = data.getStatusID();
                    if (statusID.equals(AppConfig.SUCCESS)){
                        Timer timer=new Timer();
                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                Intent intent=new Intent(WelocmeActivity.this,LoginActiovity.class);
                            startActivity(intent);
                            }
                        },1000);

                    }else {
                        tv_check_state.setText(R.string.check_imei_state);
                        tv_imie.setText(imei.toString().trim());
                        tv_imie.setTextSize(18);
                        tv_link_devices.setText("设备连接失败...");
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }

    @Override
    protected void initLise() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,  WindowManager.LayoutParams.FLAG_FULLSCREEN);


    }
}
