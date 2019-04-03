package wansun.visit.android.net;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import wansun.visit.android.utils.logUtils;

/**
 * 提交给服务器的数据body
 * Created by User on 2019/2/19.
 */

public class requestBodyUtils {
    //外访item
    public  static RequestBody visitItemToService(String userName,boolean toDay,String pageNum,String pageSize){
        Map<String ,String> pageMap=new HashMap<>();
        pageMap.put("pageNum",pageNum);
        pageMap.put("pageSize",pageSize);
        Map<String ,Object> map=new HashMap<>();
        int [] data={30,40};
        map.put("inVisitStatus",data);
        map.put("visitors",userName);
        map.put("toDay",toDay);
        map.put("sortStrategy","ASC");
        map.put("page", pageMap);
        Gson gson=new Gson();
        String json = gson.toJson(map);
        logUtils.d("json "+json);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),json);
        return body;
    }
    //外访案件详情
    public static RequestBody visitCaseDetailsFromeService(String caseCode,String visitGuid){
        Map<String ,String> map=new HashMap<>();
        map.put("caseCode",caseCode);
        map.put("visitGuid",visitGuid);
        Gson gson=new Gson();
        String json = gson.toJson(map);
        logUtils.d("传参"+json );
        return RequestBody.create(MediaType.parse("application/json; charset=utf-8"),json);
    }
    // 外访案件详情联系人
    public static  RequestBody visitCaseDetailsContentsFromeService(String caseCode){
        Map<String ,String> map=new HashMap<>();
        map.put("caseCode",caseCode);
        Gson gson=new Gson();
        String json = gson.toJson(map);
        logUtils.d("传参"+json );
        return RequestBody.create(MediaType.parse("application/json; charset=utf-8"),json);
    }
    // 外访案件详情催收记录
    public static  RequestBody visitCaseDetailsUrgeRecordFromeService(String caseCode){

        Map<String ,String> pageMap=new HashMap<>();
        pageMap.put("pageNum","1");
        pageMap.put("pageSize","20");


        Map<String ,Object> map=new HashMap<>();
        map.put("caseCode",caseCode);
        map.put("page", pageMap);
        Gson gson=new Gson();
        String json = gson.toJson(map);
        logUtils.d("传参"+json );
        return RequestBody.create(MediaType.parse("application/json; charset=utf-8"),json);
    }
     //外访案件详情外访记录
     public static  RequestBody visitCaseDetailsRecordFromeService(String caseCode,String VisitGuid){
         Map<String ,String> pageMap=new HashMap<>();
         pageMap.put("pageNum","1");
         pageMap.put("pageSize","20");
         Map<String ,Object> map=new HashMap<>();
         map.put("caseCode",caseCode);
         map.put("VisitGuid",VisitGuid);
         map.put("page", pageMap);
         Gson gson=new Gson();
         String json = gson.toJson(map);
         logUtils.d("传参"+json );
         return RequestBody.create(MediaType.parse("application/json; charset=utf-8"),json);
     }
    //外访案件详情卡信息
    public static  RequestBody visitCaseDetailsCardFromeService(String caseCode){
        Map<String ,String> map=new HashMap<>();
        map.put("caseCode",caseCode);
        Gson gson=new Gson();
        String json = gson.toJson(map);
        logUtils.d("传参"+json );
        return RequestBody.create(MediaType.parse("application/json; charset=utf-8"),json);
    }
    //添加外访单当前为外访状态
    public static  RequestBody visitLabCurrentVisitToService(String caseCode,String visitGuid,String visitors ){
        Map<String ,String> map=new HashMap<>();
        map.put("caseCode",caseCode);
        map.put("visitGuid",visitGuid);
        map.put("visitors",visitors);
        Gson gson=new Gson();
        String json = gson.toJson(map);
        logUtils.d("传参"+json );
        return RequestBody.create(MediaType.parse("application/json; charset=utf-8"),json);
    }
    //添加外访单为完成状态
    public static  RequestBody visitLabCompleteToService(String caseCode,String visitGuid,String visitors ){
        Map<String ,String> map=new HashMap<>();
        map.put("caseCode",caseCode);
        map.put("visitGuid",visitGuid);
        map.put("visitors",visitors);
        Gson gson=new Gson();
        String json = gson.toJson(map);
        logUtils.d("传参"+json );
        return RequestBody.create(MediaType.parse("application/json; charset=utf-8"),json);
    }
    //添加外访单添加联系电话
    public static  RequestBody visitCaseAddPhoneToService(String caseCode,Integer relationId,String name, String phoneNumber,
            Integer phoneStatus,String company,String remark,String relationText,Integer phoneType,String phoneTypeText,String phoneStatusText){
        Map map=new HashMap<>();
        map.put("caseCode",caseCode);  //必须填
        map.put("relationId",relationId);
        map.put("relationText",relationText);
        map.put("name",name);  //必须填写
        map.put("phoneNumber",phoneNumber);  //必须填写
        map.put("status",phoneStatus);
        map.put("company",company);
        map.put("remark",remark);
        map.put("phoneTypeText",phoneTypeText);
        map.put("phoneType",phoneType);
        map.put("phoneStatusText",phoneStatusText);
        map.put("companyGuId","");
        Gson gson=new Gson();
        String json = gson.toJson(map);
        logUtils.d("传参"+json );
        return RequestBody.create(MediaType.parse("application/json; charset=utf-8"),json);
    }
    //案件添加上门外访催记
    public static  RequestBody visitAddVisitUrgeToService(String caseCode,String visitGuid,String operatorName ,Integer operateId,String operateContent){
        Map<String ,Object> map=new HashMap<>();
        map.put("caseCode",caseCode);
        map.put("visitGuid",visitGuid);
        map.put("operatorName",operatorName);
        map.put("operateId",operateId);
        map.put("operateContent",operateContent);
        map.put("operateTypeText","");
        Gson gson=new Gson();
        String json = gson.toJson(map);
        logUtils.d("传参"+json );
        return RequestBody.create(MediaType.parse("application/json; charset=utf-8"),json);
    }
    //添加案件地址信息
    public static  RequestBody visitCaseAddAdressMessageToService(String caseCode,String relationId,String name, Integer addressType,
                   String address,Integer addressStatus,String remark,String relationText,String companyName,String addressTypeText,String addressStateText){
        Map map=new HashMap<>();
        map.put("caseCode",caseCode);  //必须填
        map.put("relationId",relationId);
        map.put("relation",relationText);
        map.put("name",name);  //必须填写
        map.put("addressType",addressType);  //必须填写
        map.put("address1",address);
        map.put("address2","");
        map.put("addressStatus",addressStatus);
        map.put("remark",remark);
        map.put("company",companyName);
        map.put("addressStatusText",addressStateText);
        map.put("addressTypeText",addressTypeText);
        map.put("postcode","");
        Gson gson=new Gson();
        String json = gson.toJson(map);
        logUtils.d("传参"+json );
        return RequestBody.create(MediaType.parse("application/json; charset=utf-8"),json);
    }
    //添加案件联系人
    public static  RequestBody visitCaseAddcontactsToService(String caseCode,Integer relation,String relationText,String name,String cidNo,
                          Integer cidType,String cidTypeText,Integer gender,String genderText,Integer age ){
        Map map=new HashMap<>();
        map.put("caseCode",caseCode);  //必须填
        map.put("relation",relation);
        map.put("relationText",relationText);
        map.put("name",name);  //必须填写
        map.put("cidNo",cidNo);  //必须填写
        map.put("cidType",cidType);
        map.put("cidTypeText",cidTypeText);
        map.put("gender",gender);
        map.put("genderText",genderText);
        map.put("age",age);
        map.put("im","");
        map.put("debtorNo","");
        map.put("socialNo","");
        Gson gson=new Gson();
        String json = gson.toJson(map);
        logUtils.d("传参"+json );
        return RequestBody.create(MediaType.parse("application/json; charset=utf-8"),json);
    }
    //添加案件电话催收
    public static  RequestBody visitCaseAddphoneUrgeToService(String caseCode,Integer relation,String name,String phone,String remark,String creator,
                                                             Integer contactSummary,String callRecords,Integer contactResult,String contactSummaryText,String contactResultText ){
        Map map=new HashMap<>();
        map.put("caseCode",caseCode);  //必须填
        map.put("relation",relation);
        map.put("name",name);  //必须填写
        map.put("phone",phone);  //必须填写
        map.put("callRecords",callRecords);
        map.put("contactSummary",contactSummary);
        map.put("contactResult",contactResult);
        map.put("contactSummaryText",contactSummaryText);
        map.put("contactResultText",contactResultText);
        map.put("remark",remark);
        map.put("creator",creator);
        Gson gson=new Gson();
        String json = gson.toJson(map);
        logUtils.d("传参"+json );
        return RequestBody.create(MediaType.parse("application/json; charset=utf-8"),json);
    }

    //查询文件
    public static  RequestBody queryFileFromeService(String caseCode,String visitGuid,Integer uploaderId){
        Map map=new HashMap<>();
        map.put("caseCode",caseCode);  //必须填
        map.put("linkId",visitGuid);
        map.put("uploaderId",uploaderId);
        Gson gson=new Gson();
        String json = gson.toJson(map);
        logUtils.d("传参"+json );
        return RequestBody.create(MediaType.parse("application/json; charset=utf-8"),json);
    }
    //app升级
    public static  RequestBody appUpdata(String versionCode,String versionName){
        Map map=new HashMap<>();
        map.put("versionCode",versionCode);  //必须填
        map.put("versionName",versionName);
        Gson gson=new Gson();
        String json = gson.toJson(map);
        logUtils.d("传参"+json );
        return RequestBody.create(MediaType.parse("application/json; charset=utf-8"),json);
    }
    //修改外访路径
    public static  RequestBody modifyAddress(String caseCode,String visitGuid,String address){
        Map map=new HashMap<>();
        map.put("caseCode",caseCode);  //必须填
        map.put("visitGuid",visitGuid);
        map.put("address",address);
        map.put("province","");
        map.put("city","");
        map.put("county","");
        Gson gson=new Gson();
        String json = gson.toJson(map);
        logUtils.d("传参"+json );
        return RequestBody.create(MediaType.parse("application/json; charset=utf-8"),json);
    }
    //保存定位信息
    public static  RequestBody saveLocationMessage(Map<String ,Object> map){
        Gson gson=new Gson();
        String json = gson.toJson(map);
        logUtils.d("传参"+json );
        return RequestBody.create(MediaType.parse("application/json; charset=utf-8"),json);
    }
    //上传定位信息
    public static  RequestBody uploadLocationMessage(Map<String ,Object> map){
        Gson gson=new Gson();
        String json = gson.toJson(map);
        logUtils.d("传参"+json );
        return RequestBody.create(MediaType.parse("application/json; charset=utf-8"),json);
    }
    //验证手机串号
    public static  RequestBody checkImie(String IMIE){
        Map<String ,Object> map=new HashMap<>();
        map.put("imeiNo",IMIE);
        Gson gson=new Gson();
        String json = gson.toJson(map);
        logUtils.d("传参"+json );
        return RequestBody.create(MediaType.parse("application/json; charset=utf-8"),json);
    }
    //上传普通的定位信息
    public static  RequestBody uploadCommonLocationMessage(String IMIE,String type,double longitude,double latitude,long positioningTime){
        Map<String ,Object> map=new HashMap<>();
        map.put("deviceNumber",IMIE);
        map.put("type",type);
        map.put("longitude",IMIE);
        map.put("latitude",IMIE);
        map.put("positioningTime",positioningTime);
        Gson gson=new Gson();
        String json = gson.toJson(map);
        logUtils.d("传参"+json );
        return RequestBody.create(MediaType.parse("application/json; charset=utf-8"),json);
    }
}
