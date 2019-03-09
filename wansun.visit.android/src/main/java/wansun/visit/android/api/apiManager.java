package wansun.visit.android.api;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.Query;

/**
 * Created by User on 2019/2/12.
 */

public interface apiManager {
    String baseUrl="http://192.168.166.133:8080";
    //      String baseUrl="http://192.168.198.133:8080";
    //登陆接口
    @FormUrlEncoded
    @POST(baseUrl+"/user/login?")
    Call<String> login(@Field("userName") String username,@Field("passWord") String password);
    //外访列表
 @Headers({"Content-Type: application/json","Accept: application/json"})
@POST(baseUrl+"/case/findVisit")
 Call<String> visitListFormeService(@Body RequestBody  body);

    //外访案件详情
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST(baseUrl+"/case/visitDetail")
    Call<String> visitCaseDetailsFormeService(@Body RequestBody  body);

    //外访案件详情联系人
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST(baseUrl+"/case/contacts")
    Call<String> visitCaseDetailsContactsFormeService(@Body RequestBody  body);

    //外访案件详情催收记录
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST(baseUrl+"/case/phoneUrgeRecord")
    Call<String> visitCaseDetailsUrgeRecordFormeService(@Body RequestBody  body);

    // 外访案件详情外访记录
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST(baseUrl+"/case/visitRecord")
    Call<String> visitCaseDetailsRecordFormeService(@Body RequestBody  body);
    // 外访案件详情卡信息
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST(baseUrl+"/case/cards")
    Call<String> visitCaseDetailsCardFormeService(@Body RequestBody  body);
    //外方单添加标记visit
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST(baseUrl+"/case/markAsCurrentVisit")
    Call<String> visitLabCurrentVisit(@Body RequestBody  body);
    //外方单添加标记为完成状态
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST(baseUrl+"/case/markAsCompleted")
    Call<String> visitLabCompleterState(@Body RequestBody  body);
    //添加电话
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST(baseUrl+"/case/addDebtorPhone")
    Call<String> visitCaseAddPhone(@Body RequestBody  body);
    //添加上门催记
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST(baseUrl+"/case/visitRecord/addRecord")
    Call<String> visitCaseAddVisitUrge(@Body RequestBody  body);
    //添加案件地址信息
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST(baseUrl+"/case/increaseAddress")
    Call<String> visitCaseAddAddddressMessage(@Body RequestBody  body);
   //添加案件联系人
   @Headers({"Content-Type: application/json","Accept: application/json"})
   @POST(baseUrl+"/case/saveContacts")
   Call<String> visitCaseAddAddcontacts(@Body RequestBody  body);
    //添加案件电话催收
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST(baseUrl+"/case/addUrgePhone")
    Call<String> visitCaseAddPhoneUrge(@Body RequestBody  body);
   //图片上传
     @Multipart
    @POST(baseUrl+"/case/uploadPhoto")
    Call <String>upLoadImage(@Query("caseCode") String caseCode, @Query("visitGuid") String visitGuid,
    @Query("uploaderId") String uploaderId, @Query("uploaderName") String uploaderName, @PartMap Map<String,RequestBody> map);
    //  录音文件上传
    String recordFileUpToService=baseUrl+"/case/uploadRecord";
    //上传附件
    String FielsUploadToService=baseUrl+"/case/uploadAttachment";
        }

