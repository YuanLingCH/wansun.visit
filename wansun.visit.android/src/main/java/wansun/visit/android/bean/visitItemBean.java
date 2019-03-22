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
     * data : [{"visitGuid":"aaa5","applyOrgName":"qzws136","caseCode":"APG-20181206-004-000001","batchCode":"APG-20181206-004","customerName":"平安新一贷","name":"王明献","genderText":"男性","caseTotalUrgeAmount":5164.23,"caseUrgeStatusText":"在催","address":"福建省泉州市石狮市凤凰城5A-802","applyTime":1550133142948,"visitors":"陈锋,administrator,","visitStatusText":"已排程","remark":"jghkhgkghk","visitReason":"1","caseTotalRepaymentAmount":-10328.46,"visitOrgName":"","debtorName":""},{"applyOrgName":"qzws136","caseCode":"APG-20181206-004-000002","batchCode":"APG-20181206-004","customerName":"平安新一贷","name":"颜呈万","genderText":"男性","caseTotalUrgeAmount":7413.67,"caseUrgeStatusText":"在催","address":"福建省泉州市晋江市安海镇尚贤中里18号","applyTime":1550137049215,"remark":"idf\u2018的规范化股份将股份东莞是 ","visitReason":"0","caseTotalRepaymentAmount":0,"visitGuid":"","visitOrgName":"","debtorName":"","visitors":"","visitStatusText":""}]
     * page : {"counts":4,"pageNum":1,"pageSize":2}
     */

    private String statusID;
    private String message;
    private String dataType;
    private PageBean page;
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

    public PageBean getPage() {
        return page;
    }

    public void setPage(PageBean page) {
        this.page = page;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class PageBean {
        /**
         * counts : 4
         * pageNum : 1
         * pageSize : 2
         */

        private int counts;
        private int pageNum;
        private int pageSize;

        public int getCounts() {
            return counts;
        }

        public void setCounts(int counts) {
            this.counts = counts;
        }

        public int getPageNum() {
            return pageNum;
        }

        public void setPageNum(int pageNum) {
            this.pageNum = pageNum;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }
    }

    public static class DataBean implements Serializable {
        /**
         * visitGuid : aaa5
         * applyOrgName : qzws136
         * caseCode : APG-20181206-004-000001
         * batchCode : APG-20181206-004
         * customerName : 平安新一贷
         * name : 王明献
         * genderText : 男性
         * caseTotalUrgeAmount : 5164.23
         * caseUrgeStatusText : 在催
         * address : 福建省泉州市石狮市凤凰城5A-802
         * applyTime : 1550133142948
         * visitors : 陈锋,administrator,
         * visitStatusText : 已排程
         * remark : jghkhgkghk
         * visitReason : 1
         * caseTotalRepaymentAmount : -10328.46
         * visitOrgName :
         * debtorName :
         */

        private String visitGuid;
        private String applyOrgName;
        private String caseCode;
        private String batchCode;
        private String customerName;
        private String name;
        private String genderText;
        private double caseTotalUrgeAmount;
        private String caseUrgeStatusText;
        private String address;
        private long applyTime;
        private String visitors;
        private String visitStatusText;
        private String remark;
        private String visitReason;
        private double caseTotalRepaymentAmount;
        private String visitOrgName;
        private String debtorName;
        private long visitBeginTime;
        private long visitEndTime;

        public long getVisitBeginTime() {
            return visitBeginTime;
        }

        public void setVisitBeginTime(long visitBeginTime) {
            this.visitBeginTime = visitBeginTime;
        }

        public long getVisitEndTime() {
            return visitEndTime;
        }

        public void setVisitEndTime(long visitEndTime) {
            this.visitEndTime = visitEndTime;
        }

        public String getVisitGuid() {
            return visitGuid;
        }

        public void setVisitGuid(String visitGuid) {
            this.visitGuid = visitGuid;
        }

        public String getApplyOrgName() {
            return applyOrgName;
        }

        public void setApplyOrgName(String applyOrgName) {
            this.applyOrgName = applyOrgName;
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

        public String getVisitReason() {
            return visitReason;
        }

        public void setVisitReason(String visitReason) {
            this.visitReason = visitReason;
        }

        public double getCaseTotalRepaymentAmount() {
            return caseTotalRepaymentAmount;
        }

        public void setCaseTotalRepaymentAmount(double caseTotalRepaymentAmount) {
            this.caseTotalRepaymentAmount = caseTotalRepaymentAmount;
        }

        public String getVisitOrgName() {
            return visitOrgName;
        }

        public void setVisitOrgName(String visitOrgName) {
            this.visitOrgName = visitOrgName;
        }

        public String getDebtorName() {
            return debtorName;
        }

        public void setDebtorName(String debtorName) {
            this.debtorName = debtorName;
        }
    }
}