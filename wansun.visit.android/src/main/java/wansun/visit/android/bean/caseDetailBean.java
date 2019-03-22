package wansun.visit.android.bean;

import java.io.Serializable;

/**
 * Created by User on 2019/2/20.
 */

public class caseDetailBean implements Serializable{


    /**
     * statusID : 200
     * message : ok
     * dataType : application/json
     * data : {"visitGuid":"aaa5","caseCode":"APG-20181206-004-000001","batchCode":"APG-20181206-004","customerName":"平安新一贷","name":"王明献","genderText":"男性","caseTotalUrgeAmount":5164.23,"caseUrgeStatusText":"在催","address":"福建省泉州市石狮市凤凰城5A-802","visitArea":"山西省 . 长治市 . 屯留县","applyTime":1550133142948,"visitors":"陈锋,administrator,","visitStatusText":"已排程","remark":"jghkhgkghk","visitGoal":"dsgdfsghdfhgffhfdhdgfhd","caseTotalAppointAmount":5164.23,"caseTotalReceiptAmount":-10328.46,"cardAddress":"福建泉州","applicantName":"qzws136","clerkName":"qzws136","latestFollowUpTime":0,"appointBeginDate":1544025600000,"appointEndBeginDate":1545408000000,"cidNo":"**********1217"}
     */

    private String statusID;
    private String message;
    private String dataType;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * visitGuid : aaa5
         * caseCode : APG-20181206-004-000001
         * batchCode : APG-20181206-004
         * customerName : 平安新一贷
         * name : 王明献
         * genderText : 男性
         * caseTotalUrgeAmount : 5164.23
         * caseUrgeStatusText : 在催
         * address : 福建省泉州市石狮市凤凰城5A-802
         * visitArea : 山西省 . 长治市 . 屯留县
         * applyTime : 1550133142948
         * visitors : 陈锋,administrator,
         * visitStatusText : 已排程
         * remark : jghkhgkghk
         * visitGoal : dsgdfsghdfhgffhfdhdgfhd
         * caseTotalAppointAmount : 5164.23
         * caseTotalReceiptAmount : -10328.46
         * cardAddress : 福建泉州
         * applicantName : qzws136
         * clerkName : qzws136
         * latestFollowUpTime : 0
         * appointBeginDate : 1544025600000
         * appointEndBeginDate : 1545408000000
         * cidNo : **********1217
         */

        private String visitGuid;
        private String caseCode;
        private String batchCode;
        private String customerName;
        private String name;
        private String genderText;
        private double caseTotalUrgeAmount;
        private String caseUrgeStatusText;
        private String address;
        private String visitArea;
        private long applyTime;
        private String visitors;
        private String visitStatusText;
        private String remark;
        private String visitGoal;
        private double caseTotalAppointAmount;
        private double caseTotalReceiptAmount;
        private String cardAddress;
        private String applicantName;
        private String clerkName;
        private long latestFollowUpTime;
        private long appointBeginDate;
        private long appointEndBeginDate;
        private String cidNo;

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

        public String getBatchCode() {
            return batchCode;
        }

        public void setBatchCode(String batchCode) {
            this.batchCode = batchCode;
        }

        public String getCustomerName() {
            return customerName;
        }

        public void setCustomerName(String customerName) {
            this.customerName = customerName;
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

        public double getCaseTotalUrgeAmount() {
            return caseTotalUrgeAmount;
        }

        public void setCaseTotalUrgeAmount(double caseTotalUrgeAmount) {
            this.caseTotalUrgeAmount = caseTotalUrgeAmount;
        }

        public String getCaseUrgeStatusText() {
            return caseUrgeStatusText;
        }

        public void setCaseUrgeStatusText(String caseUrgeStatusText) {
            this.caseUrgeStatusText = caseUrgeStatusText;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getVisitArea() {
            return visitArea;
        }

        public void setVisitArea(String visitArea) {
            this.visitArea = visitArea;
        }

        public long getApplyTime() {
            return applyTime;
        }

        public void setApplyTime(long applyTime) {
            this.applyTime = applyTime;
        }

        public String getVisitors() {
            return visitors;
        }

        public void setVisitors(String visitors) {
            this.visitors = visitors;
        }

        public String getVisitStatusText() {
            return visitStatusText;
        }

        public void setVisitStatusText(String visitStatusText) {
            this.visitStatusText = visitStatusText;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getVisitGoal() {
            return visitGoal;
        }

        public void setVisitGoal(String visitGoal) {
            this.visitGoal = visitGoal;
        }

        public double getCaseTotalAppointAmount() {
            return caseTotalAppointAmount;
        }

        public void setCaseTotalAppointAmount(double caseTotalAppointAmount) {
            this.caseTotalAppointAmount = caseTotalAppointAmount;
        }

        public double getCaseTotalReceiptAmount() {
            return caseTotalReceiptAmount;
        }

        public void setCaseTotalReceiptAmount(double caseTotalReceiptAmount) {
            this.caseTotalReceiptAmount = caseTotalReceiptAmount;
        }

        public String getCardAddress() {
            return cardAddress;
        }

        public void setCardAddress(String cardAddress) {
            this.cardAddress = cardAddress;
        }

        public String getApplicantName() {
            return applicantName;
        }

        public void setApplicantName(String applicantName) {
            this.applicantName = applicantName;
        }

        public String getClerkName() {
            return clerkName;
        }

        public void setClerkName(String clerkName) {
            this.clerkName = clerkName;
        }

        public long getLatestFollowUpTime() {
            return latestFollowUpTime;
        }

        public void setLatestFollowUpTime(int latestFollowUpTime) {
            this.latestFollowUpTime = latestFollowUpTime;
        }

        public long getAppointBeginDate() {
            return appointBeginDate;
        }

        public void setAppointBeginDate(long appointBeginDate) {
            this.appointBeginDate = appointBeginDate;
        }

        public long getAppointEndBeginDate() {
            return appointEndBeginDate;
        }

        public void setAppointEndBeginDate(long appointEndBeginDate) {
            this.appointEndBeginDate = appointEndBeginDate;
        }

        public String getCidNo() {
            return cidNo;
        }

        public void setCidNo(String cidNo) {
            this.cidNo = cidNo;
        }
    }
}
