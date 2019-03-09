package wansun.visit.android.bean;

import java.util.List;

/**
 * Created by User on 2019/3/5.
 */

public class caseVistRecordBean {

    /**
     * statusID : 200
     * message : ok
     * dataType : application/json
     * data : [{"operateTypeText":"排程","operateContent":"操作前：已审核/待排程，操作后：已排程/待打印","operatorName":"admin","operateTime":1545709992033,"recordGuid":"2ebb2fae-368e-42d5-9687-29a338826b07"},{"operateTypeText":"审核","operateContent":"操作前：已申请/待审核修改，操作后：已审核/待排程","operatorName":"admin","operateTime":1545723531868,"recordGuid":"78ded534-4a5e-4f49-a9e2-97ad4f5b8a87"},{"operateTypeText":"排程","operateContent":"操作前：已审核/待排程，操作后：已排程/待打印","operatorName":"admin","operateTime":1550214856140,"recordGuid":"e67597e6-f5b5-4015-99c8-ef5a409293b5"},{"operateTypeText":"排程","operateContent":"操作前：已审核/待排程，操作后：已排程/待打印","operatorName":"admin","operateTime":1550224868490,"recordGuid":"230a532b-1dfd-4fae-9370-2f388fd48fe3"},{"operateTypeText":"审核","operateContent":"操作前：已申请/待审核修改，操作后：已审核/待排程","operatorName":"admin","operateTime":1551253373117,"recordGuid":"a6dfad7c-3715-41e7-83d8-e0ce4dc64964"},{"operateTypeText":"排程","operateContent":"操作前：已审核/待排程，操作后：已排程/待打印","operatorName":"admin","operateTime":1551253398545,"recordGuid":"14d52060-ede3-4c21-b2fd-5ade871387dd"},{"operateTypeText":"排程","operateContent":"操作前：已审核/待排程，操作后：已排程/待打印","operatorName":"admin","operateTime":1551255074633,"recordGuid":"2dfc2db7-80ee-46aa-9f43-9cb7a267cae2"},{"operateTypeText":"排程","operateContent":"操作前：已审核/待排程，操作后：已排程/待打印","operatorName":"admin","operateTime":1551256126123,"recordGuid":"1143a1fd-99ed-4b96-9b53-5bcc532f2f90"},{"operateTypeText":"排程","operateContent":"操作前：已审核/待排程，操作后：已排程/待打印","operatorName":"admin","operateTime":1551256754610,"recordGuid":"6770e914-794d-4d7c-82f7-9d1dad91f45c"},{"operateTypeText":"外访登记","operateContent":"qqqq","operatorName":"administrator","operateTime":1551325523855,"recordGuid":"0c61a899-2a1f-4b1a-a8b7-014dfefab734"},{"operateTypeText":"审核","operateContent":"操作前：已申请/待审核修改，操作后：已审核/待排程","operatorName":"admin","operateTime":1551253373117,"recordGuid":"2596b239-323f-4f7d-8f62-4e356c1137fb"},{"operateTypeText":"排程","operateContent":"操作前：已审核/待排程，操作后：已排程/待打印","operatorName":"admin","operateTime":1551254781630,"recordGuid":"f02dfb97-dac9-4b28-95a9-15f5dd880de7"},{"operateTypeText":"排程","operateContent":"操作前：已审核/待排程，操作后：已排程/待打印","operatorName":"admin","operateTime":1551255231088,"recordGuid":"929951fb-2c35-4871-a5e4-5fdfae658c33"},{"operateTypeText":"排程","operateContent":"操作前：已审核/待排程，操作后：已排程/待打印","operatorName":"admin","operateTime":1551256153603,"recordGuid":"5d585541-6229-48ff-90ce-b4d45bc033e5"},{"operateTypeText":"排程","operateContent":"操作前：已审核/待排程，操作后：已排程/待打印","operatorName":"admin","operateTime":1551256762250,"recordGuid":"faabb42f-56fe-4895-bd27-a6532ca03c14"},{"operateTypeText":"审核","operateContent":"操作前：已申请/待审核修改，操作后：已审核/待排程","operatorName":"admin","operateTime":1551253373117,"recordGuid":"30599cb0-f0a0-4f56-a5b8-b337fbc41951"},{"operateTypeText":"排程","operateContent":"操作前：已审核/待排程，操作后：已排程/待打印","operatorName":"admin","operateTime":1551254781630,"recordGuid":"942ef40b-146b-4a8c-abc3-81bc72e4dd8e"},{"operateTypeText":"排程","operateContent":"操作前：已审核/待排程，操作后：已排程/待打印","operatorName":"admin","operateTime":1551255280029,"recordGuid":"f82a8ab3-e886-461b-89dc-75497e0b8b55"},{"operateTypeText":"排程","operateContent":"操作前：已审核/待排程，操作后：已排程/待打印","operatorName":"admin","operateTime":1551256612689,"recordGuid":"c25dc838-b3ef-46f1-ae2f-e9e344fb2050"},{"operateTypeText":"排程","operateContent":"操作前：已审核/待排程，操作后：已排程/待打印","operatorName":"admin","operateTime":1551256781257,"recordGuid":"520e562d-7cea-468c-85cb-3c24b3d7bfeb"},{"operateTypeText":"审核","operateContent":"操作前：已申请/待审核修改，操作后：已审核/待排程","operatorName":"admin","operateTime":1551253373117,"recordGuid":"b423ebfc-73d8-49bd-b2ca-95b934ce8ce5"},{"operateTypeText":"排程","operateContent":"操作前：已审核/待排程，操作后：已排程/待打印","operatorName":"admin","operateTime":1551254781630,"recordGuid":"43dbd5bf-45cd-4167-a19c-2b21c53df6ce"},{"operateTypeText":"排程","operateContent":"操作前：已审核/待排程，操作后：已排程/待打印","operatorName":"admin","operateTime":1551255597386,"recordGuid":"5636d0b5-8d43-4db5-b13b-894e54396323"},{"operateTypeText":"排程","operateContent":"操作前：已审核/待排程，操作后：已排程/待打印","operatorName":"admin","operateTime":1551256645627,"recordGuid":"19cd9612-23ec-4262-95a1-9fb425336a9b"},{"operateTypeText":"审核","operateContent":"操作前：已申请/待审核修改，操作后：已审核/待排程","operatorName":"admin","operateTime":1551253373117,"recordGuid":"582e4ccc-c39f-4bfb-a4fd-2894bebdefd6"},{"operateTypeText":"排程","operateContent":"操作前：已审核/待排程，操作后：已排程/待打印","operatorName":"admin","operateTime":1551254781630,"recordGuid":"f034f8d6-9745-46ff-b905-f6f15f515bb3"},{"operateTypeText":"排程","operateContent":"操作前：已审核/待排程，操作后：已排程/待打印","operatorName":"admin","operateTime":1551255678412,"recordGuid":"06d05b2b-87f6-43ad-9d1a-4336cad38c1e"},{"operateTypeText":"排程","operateContent":"操作前：已审核/待排程，操作后：已排程/待打印","operatorName":"admin","operateTime":1551256645627,"recordGuid":"45a66656-7dbd-4d69-bd43-35728e94df0a"},{"operateTypeText":"审核","operateContent":"操作前：已申请/待审核修改，操作后：已审核/待排程","operatorName":"admin","operateTime":1551253373117,"recordGuid":"0daaf648-6a22-4196-ac1b-86dcfcd95321"},{"operateTypeText":"排程","operateContent":"操作前：已审核/待排程，操作后：已排程/待打印","operatorName":"admin","operateTime":1551254781630,"recordGuid":"269b8e64-c463-4c07-b0e0-0649306c000e"},{"operateTypeText":"排程","operateContent":"操作前：已审核/待排程，操作后：已排程/待打印","operatorName":"admin","operateTime":1551255724954,"recordGuid":"18567c2c-e015-4601-98cb-08c9f6b3b55d"},{"operateTypeText":"审核","operateContent":"操作前：已申请/待审核修改，操作后：已审核/待排程","operatorName":"admin","operateTime":1551253373117,"recordGuid":"4b1cf786-17ad-47c2-b84d-5ba381cbe3ae"},{"operateTypeText":"排程","operateContent":"操作前：已审核/待排程，操作后：已排程/待打印","operatorName":"admin","operateTime":1551254781630,"recordGuid":"63774b77-9a5c-4723-b523-dc7c1b50a99f"}]
     */

    private String statusID;
    private String message;
    private String dataType;
    private List<DataBean> data;

    public String getStatusID() {
        return statusID;
    }

    public void setStatusID(String statusID) {
        this.statusID = statusID;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * operateTypeText : 排程
         * operateContent : 操作前：已审核/待排程，操作后：已排程/待打印
         * operatorName : admin
         * operateTime : 1545709992033
         * recordGuid : 2ebb2fae-368e-42d5-9687-29a338826b07
         */

        private String operateTypeText;
        private String operateContent;
        private String operatorName;
        private long operateTime;
        private String recordGuid;

        public String getOperateTypeText() {
            return operateTypeText;
        }

        public void setOperateTypeText(String operateTypeText) {
            this.operateTypeText = operateTypeText;
        }

        public String getOperateContent() {
            return operateContent;
        }

        public void setOperateContent(String operateContent) {
            this.operateContent = operateContent;
        }

        public String getOperatorName() {
            return operatorName;
        }

        public void setOperatorName(String operatorName) {
            this.operatorName = operatorName;
        }

        public long getOperateTime() {
            return operateTime;
        }

        public void setOperateTime(long operateTime) {
            this.operateTime = operateTime;
        }

        public String getRecordGuid() {
            return recordGuid;
        }

        public void setRecordGuid(String recordGuid) {
            this.recordGuid = recordGuid;
        }
    }
}
