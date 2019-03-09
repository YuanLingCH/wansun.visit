package wansun.visit.android.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by User on 2019/2/19.
 */

public class visitItemBean implements Serializable{


    /**
     * statusID : 200
     * message : ok
     * dataType : application/json
     * data : [{"bacthCode":"APG-20181206-004","visitGuid":"aaa","caseCode":"APG-20181206-004-000001","caseUrgeStatusText":"在催","name":"王明献","genderText":"男性","customerName":"平安新一贷","caseTotalUrgeAmount":5164.23,"caseTotalRepaymentAmount":0,"remark":"备注","applyTime":1545280722000,"visitStatusText":"已排程","address":"广东省深圳市龙岗区坂田街道","visitReason":"验证","visitors":"陈锋,administrator,","applyOrgName":"集团","visitOrgName":"集团"},{"bacthCode":"APG-20181206-004","visitGuid":"aaa6","caseCode":"APG-20181206-004-000001","caseUrgeStatusText":"在催","name":"王明献","genderText":"男性","customerName":"平安新一贷","caseTotalUrgeAmount":5164.23,"caseTotalRepaymentAmount":0,"remark":"jghkhgkghk","applyTime":1550133126042,"visitStatusText":"已排程","address":"福建省泉州市石狮九二东路58号","visitReason":"1","visitors":"陈锋,","applyOrgName":"qzws136"},{"bacthCode":"APG-20181206-004","visitGuid":"aaa5","caseCode":"APG-20181206-004-000001","caseUrgeStatusText":"在催","name":"王明献","genderText":"男性","customerName":"平安新一贷","caseTotalUrgeAmount":5164.23,"caseTotalRepaymentAmount":0,"remark":"jghkhgkghk","applyTime":1550133142948,"visitStatusText":"已排程","address":"福建省泉州市石狮市凤凰城5A-802","visitReason":"1","visitors":"陈锋,administrator,","applyOrgName":"qzws136"},{"bacthCode":"APG-20181206-004","visitGuid":"aaa4","caseCode":"APG-20181206-004-000001","caseUrgeStatusText":"在催","name":"王明献","genderText":"男性","customerName":"平安新一贷","caseTotalUrgeAmount":5164.23,"caseTotalRepaymentAmount":0,"remark":"个鸡飞狗叫","applyTime":1550133300316,"visitStatusText":"已排程","address":"第三个第四个","visitReason":"1","visitors":"陈锋,administrator,","applyOrgName":"qzws136"},{"bacthCode":"APG-20181206-004","visitGuid":"aaa3","caseCode":"APG-20181206-004-000001","caseUrgeStatusText":"在催","name":"王明献","genderText":"男性","customerName":"平安新一贷","caseTotalUrgeAmount":5164.23,"caseTotalRepaymentAmount":0,"remark":"个鸡飞狗叫发的蝴蝶蝴蝶","applyTime":1550133323971,"visitStatusText":"已排程","address":"第三个第四个","visitReason":"1","visitors":"陈锋,administrator,","applyOrgName":"qzws136"},{"bacthCode":"APG-20181206-004","visitGuid":"aaa2","caseCode":"APG-20181206-004-000001","caseUrgeStatusText":"在催","name":"王明献","genderText":"男性","customerName":"平安新一贷","caseTotalUrgeAmount":5164.23,"caseTotalRepaymentAmount":0,"remark":"发货的发货的更好的更好","applyTime":1550133823855,"visitStatusText":"已排程","address":"第三个第四个","visitReason":"0","visitors":"林泽智,","applyOrgName":"qzws136"},{"bacthCode":"APG-20181206-004","visitGuid":"aaa1","caseCode":"APG-20181206-004-000001","caseUrgeStatusText":"在催","name":"王明献","genderText":"男性","customerName":"平安新一贷","caseTotalUrgeAmount":5164.23,"caseTotalRepaymentAmount":0,"remark":"hgfjghj","applyTime":1550136864260,"visitStatusText":"已排程","address":"第三个第四个","visitReason":"1","applyOrgName":"qzws136"},{"bacthCode":"APG-20181206-004","caseCode":"APG-20181206-004-000002","caseUrgeStatusText":"在催","name":"颜呈万","genderText":"男性","customerName":"平安新一贷","caseTotalUrgeAmount":7777,"caseTotalRepaymentAmount":0,"remark":"idf\u2018的规范化股份将股份东莞是 ","applyTime":1550137049215,"address":"福建省泉州市晋江市安海镇尚贤中里18号","visitReason":"0","applyOrgName":"qzws136"},{"bacthCode":"APG-20181206-004","caseCode":"APG-20181206-004-000002","caseUrgeStatusText":"在催","name":"颜呈万","genderText":"男性","customerName":"平安新一贷","caseTotalUrgeAmount":7777,"caseTotalRepaymentAmount":0,"remark":"idf\u2018的规范化股份将股份东莞是 ","applyTime":1550137061870,"address":"福建省泉州市晋江市安海镇尚贤中里18号","visitReason":"0","applyOrgName":"qzws136"},{"bacthCode":"APG-20181206-004","caseCode":"APG-20181206-004-000002","caseUrgeStatusText":"在催","name":"颜呈万","genderText":"男性","customerName":"平安新一贷","caseTotalUrgeAmount":7777,"caseTotalRepaymentAmount":0},{"bacthCode":"APG-20181206-004","caseCode":"APG-20181206-004-000003","caseUrgeStatusText":"在催","name":"李振标","genderText":"男性","customerName":"平安新一贷","caseTotalUrgeAmount":7539.73,"caseTotalRepaymentAmount":0,"remark":"结了婚了很久了","applyTime":1550137087103,"address":"福建省泉州市晋江市梅岭工业区梅岭路502号","visitReason":"1","applyOrgName":"qzws136"},{"bacthCode":"APG-20181206-004","caseCode":"APG-20181206-004-000003","caseUrgeStatusText":"在催","name":"李振标","genderText":"男性","customerName":"平安新一贷","caseTotalUrgeAmount":7539.73,"caseTotalRepaymentAmount":0,"remark":"结了婚了很久了","applyTime":1550137093920,"address":"福建省泉州市安溪县湖头镇福寿村","visitReason":"1","applyOrgName":"qzws136"}]
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

    public static class DataBean implements Serializable{
        /**
         * bacthCode : APG-20181206-004
         * visitGuid : aaa
         * caseCode : APG-20181206-004-000001
         * caseUrgeStatusText : 在催
         * name : 王明献
         * genderText : 男性
         * customerName : 平安新一贷
         * caseTotalUrgeAmount : 5164.23
         * caseTotalRepaymentAmount : 0
         * remark : 备注
         * applyTime : 1545280722000
         * visitStatusText : 已排程
         * address : 广东省深圳市龙岗区坂田街道
         * visitReason : 验证
         * visitors : 陈锋,administrator,
         * applyOrgName : 集团
         * visitOrgName : 集团
         */

        private String bacthCode;
        private String visitGuid;
        private String caseCode;
        private String caseUrgeStatusText;
        private String name;
        private String genderText;
        private String customerName;
        private double caseTotalUrgeAmount;
        private int caseTotalRepaymentAmount;
        private String remark;
        private long applyTime;
        private String visitStatusText;
        private String address;
        private String visitReason;
        private String visitors;
        private String applyOrgName;
        private String visitOrgName;

        public String getBacthCode() {
            return bacthCode;
        }

        public void setBacthCode(String bacthCode) {
            this.bacthCode = bacthCode;
        }

        public String getVisitGuid() {
            return visitGuid;
        }

        public void setVisitGuid(String visitGuid) {
            this.visitGuid = visitGuid;
        }

        public String getCaseCode() {
            return caseCode;
        }

        public void setCaseCode(String caseCode) {
            this.caseCode = caseCode;
        }

        public String getCaseUrgeStatusText() {
            return caseUrgeStatusText;
        }

        public void setCaseUrgeStatusText(String caseUrgeStatusText) {
            this.caseUrgeStatusText = caseUrgeStatusText;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getGenderText() {
            return genderText;
        }

        public void setGenderText(String genderText) {
            this.genderText = genderText;
        }

        public String getCustomerName() {
            return customerName;
        }

        public void setCustomerName(String customerName) {
            this.customerName = customerName;
        }

        public double getCaseTotalUrgeAmount() {
            return caseTotalUrgeAmount;
        }

        public void setCaseTotalUrgeAmount(double caseTotalUrgeAmount) {
            this.caseTotalUrgeAmount = caseTotalUrgeAmount;
        }

        public int getCaseTotalRepaymentAmount() {
            return caseTotalRepaymentAmount;
        }

        public void setCaseTotalRepaymentAmount(int caseTotalRepaymentAmount) {
            this.caseTotalRepaymentAmount = caseTotalRepaymentAmount;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public long getApplyTime() {
            return applyTime;
        }

        public void setApplyTime(long applyTime) {
            this.applyTime = applyTime;
        }

        public String getVisitStatusText() {
            return visitStatusText;
        }

        public void setVisitStatusText(String visitStatusText) {
            this.visitStatusText = visitStatusText;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getVisitReason() {
            return visitReason;
        }

        public void setVisitReason(String visitReason) {
            this.visitReason = visitReason;
        }

        public String getVisitors() {
            return visitors;
        }

        public void setVisitors(String visitors) {
            this.visitors = visitors;
        }

        public String getApplyOrgName() {
            return applyOrgName;
        }

        public void setApplyOrgName(String applyOrgName) {
            this.applyOrgName = applyOrgName;
        }

        public String getVisitOrgName() {
            return visitOrgName;
        }

        public void setVisitOrgName(String visitOrgName) {
            this.visitOrgName = visitOrgName;
        }
    }
}