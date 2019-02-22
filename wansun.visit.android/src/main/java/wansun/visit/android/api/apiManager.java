package wansun.visit.android.api;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by User on 2019/2/12.
 */

public interface apiManager {
    String baseUrl="http://192.168.166.233:8088";
    //登陆接口
    @FormUrlEncoded
    @POST(baseUrl+"/user/login?")
    Call<String> login(@Field("userName") String username,@Field("passWord") String password);

    //外访列表
 @Headers({"Content-Type: application/json","Accept: application/json"})
 @POST(baseUrl+"/visit/queryVisitItem")
 Call<String> visitListFormeService(@Body RequestBody  body);

    //外访案件详情
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST(baseUrl+"/visit/queryVisitItemDetails")
    Call<String> visitCaseDetailsFormeService(@Body RequestBody  body);
}
