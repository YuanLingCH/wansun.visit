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

}
