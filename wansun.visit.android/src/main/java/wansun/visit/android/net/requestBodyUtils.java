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
    public  static RequestBody visitItemToService(String userName){
        Map<String ,Object> map=new HashMap<>();
        int [] data={30,40};
        map.put("inVisitStatus",data);
        map.put("visitors",userName);
        map.put("sortStrategy","ASC");
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
        Map<String ,String> map=new HashMap<>();
        map.put("caseCode",caseCode);
        Gson gson=new Gson();
        String json = gson.toJson(map);
        logUtils.d("传参"+json );
        return RequestBody.create(MediaType.parse("application/json; charset=utf-8"),json);
    }
     //外访案件详情外访记录
     public static  RequestBody visitCaseDetailsRecordFromeService(String caseCode,String VisitGuid){
         Map<String ,String> map=new HashMap<>();
         map.put("caseCode",caseCode);
         map.put("VisitGuid",VisitGuid);
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
                                                          String address,Integer addressStatus,String remark,String relationText,String companyName){
        Map map=new HashMap<>();
        map.put("caseCode",caseCode);  //必须填
        map.put("relationId",relationId);
        map.put("relation",relationText);
        map.put("name",name);  //必须填写
        map.put("addressType",addressType);  //必须填写
        map.put("address",address);
        map.put("addressStatus",addressStatus);
        map.put("remark",remark);
        map.put("companyName",companyName);
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

}
