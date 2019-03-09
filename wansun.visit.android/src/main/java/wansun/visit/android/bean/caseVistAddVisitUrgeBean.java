package wansun.visit.android.bean;

/**
 * Created by User on 2019/3/6.
 */

public class caseVistAddVisitUrgeBean {

    /**
     * statusID : 200
     * message : ok
     * dataType : application/json
     * data : {"address":"广东省深圳市龙岗区坂田街道","visitGuid":"aaa","name":"王明献","caseTotalAppointAmount":"caseTotalAppointAmount","cardAddress":"福建泉州","caseTotalUrgeAmount":"caseTotalUrgeAmount","caseTotalReceiptAmount":"caseTotalReceiptAmount","visitArea":"广东省.深圳市.龙岗区","visitGoal":"回款","remark":"备注","applyTime":1545280722000,"applicantName":"administrator","cidNo":"350581199205121217","customerName":"平安新一贷","genderText":"男性","clerkName":"administrator","batchCode":"APG-20181206-004","lastUrgeTime":0,"bisitors":"陈锋,administrator,","bisitStatusText":"安排外访","caseUrgeStatusText":"在催","appointBeginDate":1544025600000,"aappointEndBeginDate":1545408000000}
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
         * address : 广东省深圳市龙岗区坂田街道
         * visitGuid : aaa
         * name : 王明献
         * caseTotalAppointAmount : caseTotalAppointAmount
         * cardAddress : 福建泉州
         * caseTotalUrgeAmount : caseTotalUrgeAmount
         * caseTotalReceiptAmount : caseTotalReceiptAmount
         * visitArea : 广东省.深圳市.龙岗区
         * visitGoal : 回款
         * remark : 备注
         * applyTime : 1545280722000
         * applicantName : administrator
         * cidNo : 350581199205121217
         * customerName : 平安新一贷
         * genderText : 男性
         * clerkName : administrator
         * batchCode : APG-20181206-004
         * lastUrgeTime : 0
         * bisitors : 陈锋,administrator,
         * bisitStatusText : 安排外访
         * caseUrgeStatusText : 在催
         * appointBeginDate : 1544025600000
         * aappointEndBeginDate : 1545408000000
         */

        private String address;
        private String visitGuid;
        private String name;
        private String caseTotalAppointAmount;
        private String cardAddress;
        private String caseTotalUrgeAmount;
        private String caseTotalReceiptAmount;
        private String visitArea;
        private String visitGoal;
        private String remark;
        private long applyTime;
        private String applicantName;
        private String cidNo;
        private String customerName;
        private String genderText;
        private String clerkName;
        private String batchCode;
        private int lastUrgeTime;
        private String bisitors;
        private String bisitStatusText;
        private String caseUrgeStatusText;
        private long appointBeginDate;
        private long aappointEndBeginDate;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getVisitGuid() {
            return visitGuid;
        }

        public void setVisitGuid(String visitGuid) {
            this.visitGuid = visitGuid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCaseTotalAppointAmount() {
            return caseTotalAppointAmount;
        }

        public void setCaseTotalAppointAmount(String caseTotalAppointAmount) {
            this.caseTotalAppointAmount = caseTotalAppointAmount;
        }

        public String getCardAddress() {
            return cardAddress;
        }

        public void setCardAddress(String cardAddress) {
            this.cardAddress = cardAddress;
        }

        public String getCaseTotalUrgeAmount() {
            return caseTotalUrgeAmount;
        }

        public void setCaseTotalUrgeAmount(String caseTotalUrgeAmount) {
            this.caseTotalUrgeAmount = caseTotalUrgeAmount;
        }

        public String getCaseTotalReceiptAmount() {
            return caseTotalReceiptAmount;
        }

        public void setCaseTotalReceiptAmount(String caseTotalReceiptAmount) {
            this.caseTotalReceiptAmount = caseTotalReceiptAmount;
        }

        public String getVisitArea() {
            return visitArea;
        }

        public void setVisitArea(String visitArea) {
            this.visitArea = visitArea;
        }

        public String getVisitGoal() {
            return visitGoal;
        }

        public void setVisitGoal(String visitGoal) {
            this.visitGoal = visitGoal;
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

        public String getApplicantName() {
            return applicantName;
        }

        public void setApplicantName(String applicantName) {
            this.applicantName = applicantName;
        }

        public String getCidNo() {
            return cidNo;
        }

        public void setCidNo(String cidNo) {
            this.cidNo = cidNo;
        }

        public String getCustomerName() {
            return customerName;
        }

        public void setCustomerName(String customerName) {
            this.customerName = customerName;
        }

        public String getGenderText() {
            return genderText;
        }

        public void setGenderText(String genderText) {
            this.genderText = genderText;
        }

        public String getClerkName() {
            return clerkName;
        }

        public void setClerkName(String clerkName) {
            this.clerkName = clerkName;
        }

        public String getBatchCode() {
            return batchCode;
        }

        public void setBatchCode(String batchCode) {
            this.batchCode = batchCode;
        }

        public int getLastUrgeTime() {
            return lastUrgeTime;
        }

        public void setLastUrgeTime(int lastUrgeTime) {
            this.lastUrgeTime = lastUrgeTime;
        }

        public String getBisitors() {
            return bisitors;
        }

        public void setBisitors(String bisitors) {
            this.bisitors = bisitors;
        }

        public String getBisitStatusText() {
            return bisitStatusText;
        }

        public void setBisitStatusText(String bisitStatusText) {
            this.bisitStatusText = bisitStatusText;
        }

        public String getCaseUrgeStatusText() {
            return caseUrgeStatusText;
        }

        public void setCaseUrgeStatusText(String caseUrgeStatusText) {
            this.caseUrgeStatusText = caseUrgeStatusText;
        }

        public long getAppointBeginDate() {
            return appointBeginDate;
        }

        public void setAppointBeginDate(long appointBeginDate) {
            this.appointBeginDate = appointBeginDate;
        }

        public long getAappointEndBeginDate() {
            return aappointEndBeginDate;
        }

        public void setAappointEndBeginDate(long aappointEndBeginDate) {
            this.aappointEndBeginDate = aappointEndBeginDate;
        }
    }
}
