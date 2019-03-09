package wansun.visit.android.bean;

/**
 * Created by User on 2019/3/7.
 */

public class caseAddPhoneUrgeBean {

    /**
     * statusID : 200
     * message : ok
     * dataType : application/json
     * data : {"caseCode":"APG-20181206-004-000001","phoneUrgeTag":"08adbc3a-3c52-4cf8-a240-5f517896273e","name":"558","phone":"sxzz","relation":70,"urgeTypeName":"电催","callRecords":"ssz","contactSummary":3,"contactSummaryText":"联系到亲属","contactResult":5,"contactResultText":"第三方转告","remark":"cvhjk","isDel":false,"createDate":1551929538814,"creator":"admin","promisePaymentItems":null}
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
         * caseCode : APG-20181206-004-000001
         * phoneUrgeTag : 08adbc3a-3c52-4cf8-a240-5f517896273e
         * name : 558
         * phone : sxzz
         * relation : 70
         * urgeTypeName : 电催
         * callRecords : ssz
         * contactSummary : 3
         * contactSummaryText : 联系到亲属
         * contactResult : 5
         * contactResultText : 第三方转告
         * remark : cvhjk
         * isDel : false
         * createDate : 1551929538814
         * creator : admin
         * promisePaymentItems : null
         */

        private String caseCode;
        private String phoneUrgeTag;
        private String name;
        private String phone;
        private int relation;
        private String urgeTypeName;
        private String callRecords;
        private int contactSummary;
        private String contactSummaryText;
        private int contactResult;
        private String contactResultText;
        private String remark;
        private boolean isDel;
        private long createDate;
        private String creator;
        private Object promisePaymentItems;

        public String getCaseCode() {
            return caseCode;
        }

        public void setCaseCode(String caseCode) {
            this.caseCode = caseCode;
        }

        public String getPhoneUrgeTag() {
            return phoneUrgeTag;
        }

        public void setPhoneUrgeTag(String phoneUrgeTag) {
            this.phoneUrgeTag = phoneUrgeTag;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public int getRelation() {
            return relation;
        }

        public void setRelation(int relation) {
            this.relation = relation;
        }

        public String getUrgeTypeName() {
            return urgeTypeName;
        }

        public void setUrgeTypeName(String urgeTypeName) {
            this.urgeTypeName = urgeTypeName;
        }

        public String getCallRecords() {
            return callRecords;
        }

        public void setCallRecords(String callRecords) {
            this.callRecords = callRecords;
        }

        public int getContactSummary() {
            return contactSummary;
        }

        public void setContactSummary(int contactSummary) {
            this.contactSummary = contactSummary;
        }

        public String getContactSummaryText() {
            return contactSummaryText;
        }

        public void setContactSummaryText(String contactSummaryText) {
            this.contactSummaryText = contactSummaryText;
        }

        public int getContactResult() {
            return contactResult;
        }

        public void setContactResult(int contactResult) {
            this.contactResult = contactResult;
        }

        public String getContactResultText() {
            return contactResultText;
        }

        public void setContactResultText(String contactResultText) {
            this.contactResultText = contactResultText;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public boolean isIsDel() {
            return isDel;
        }

        public void setIsDel(boolean isDel) {
            this.isDel = isDel;
        }

        public long getCreateDate() {
            return createDate;
        }

        public void setCreateDate(long createDate) {
            this.createDate = createDate;
        }

        public String getCreator() {
            return creator;
        }

        public void setCreator(String creator) {
            this.creator = creator;
        }

        public Object getPromisePaymentItems() {
            return promisePaymentItems;
        }

        public void setPromisePaymentItems(Object promisePaymentItems) {
            this.promisePaymentItems = promisePaymentItems;
        }
    }
}
