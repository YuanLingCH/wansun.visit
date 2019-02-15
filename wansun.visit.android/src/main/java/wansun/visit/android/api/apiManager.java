package wansun.visit.android.api;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by User on 2019/2/12.
 */

public interface apiManager {
    String baseUrl="http://192.168.166.33:8088";
    //登陆接口
    @FormUrlEncoded
    @POST("http://192.168.166.33:8088/user/login?")
    Call<String> login(@Field("username") String username,@Field("password") String password);
}
