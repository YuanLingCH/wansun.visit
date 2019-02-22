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
     * data : {"pagination":null,"data":[{"caseCode":"APG-20181206-004-000002","lastArrearAmount":"0.0","genderText":"男性","remark":null,"addressTypeText":null,"collectAmount":"7413.67","visitEndTime":null,"caseType":"10","visitObjectName":null,"visitReason":null,"visitBeginTime":null,"visitReturnAmount":null,"visitApplyUser":null,"applyTime":null,"debtorAge":"0","urgeStatusText":"在催","address":null,"visitStatusText":null,"backAmount":null,"batchCode":"APG-20181206-004","visitOrgName":null,"visitCompleteDate":null,"debtorName":"颜呈万","customerName":"平安新一贷","visitGuid":null,"applyOrgName":null,"visitors":null,"relateionText":null,"visitArea":null,"postCode":null,"visitGoal":null,"urgeVisitItems":[{"visitStatusTime":0,"city":" 阳泉市 ","isArchived":false,"county":" 郊　区","clerkId":7657,"remark":"idf\u2018的规范化股份将股份东莞是 ","addressTypeText":"家庭地址","visitEndTime":0,"visitObjectName":null,"relation":60,"visitReason":"0","visitBeginTime":0,"province":"山西省 ","visitOrgId":0,"applicantId":7657,"applyTime":1550137049215,"clerkName":"qzws136","relationText":"本人","visitStatusRemark":null,"applyOrgId":47,"address":"福建省泉州市晋江市安海镇尚贤中里18号","visitStatusText":null,"addressType":4,"visitOrgName":null,"taskGuid":null,"applicantName":"qzws136","isPrint":false,"visitGuid":null,"applyOrgName":"qzws136","visitors":"admin","visitCompleteTime":0,"effectBackDay":0,"visitStatus":30,"visitBackAmount":0,"visitRecordItems":[],"visitArea":"山西省 . 阳泉市 . 郊　区","postCode":null,"visitGoal":"fdf发，一公分 ","isDel":false,"addressGuid":"dddc4102-73a4-4a26-b4b0-53afa7c5815e","visitTimes":0},{"visitStatusTime":0,"city":" 阳泉市 ","isArchived":false,"county":" 郊　区","clerkId":7657,"remark":"idf\u2018的规范化股份将股份东莞是 ","addressTypeText":"公司地址","visitEndTime":0,"visitObjectName":null,"relation":60,"visitReason":"0","visitBeginTime":0,"province":"山西省 ","visitOrgId":0,"applicantId":7657,"applyTime":1550137061870,"clerkName":"qzws136","relationText":"本人","visitStatusRemark":null,"applyOrgId":47,"address":"福建省泉州市晋江市安海镇尚贤中里18号","visitStatusText":null,"addressType":3,"visitOrgName":null,"taskGuid":null,"applicantName":"qzws136","isPrint":false,"visitGuid":null,"applyOrgName":"qzws136","visitors":"admin","visitCompleteTime":0,"effectBackDay":0,"visitStatus":30,"visitBackAmount":0,"visitRecordItems":[],"visitArea":"山西省 . 阳泉市 . 郊　区","postCode":null,"visitGoal":"fdf发，一公分 ","isDel":false,"addressGuid":"29c3a3e5-99d9-492c-9d44-8036ee2ce447","visitTimes":0}],"visitTimes":null},{"caseCode":"APG-20181206-004-000003","lastArrearAmount":"0.0","genderText":"男性","remark":null,"addressTypeText":null,"collectAmount":"7539.73","visitEndTime":null,"caseType":"10","visitObjectName":null,"visitReason":null,"visitBeginTime":null,"visitReturnAmount":null,"visitApplyUser":null,"applyTime":null,"debtorAge":"0","urgeStatusText":"在催","address":null,"visitStatusText":null,"backAmount":null,"batchCode":"APG-20181206-004","visitOrgName":null,"visitCompleteDate":null,"debtorName":"李振标","customerName":"平安新一贷","visitGuid":null,"applyOrgName":null,"visitors":null,"relateionText":null,"visitArea":null,"postCode":null,"visitGoal":null,"urgeVisitItems":[{"visitStatusTime":0,"city":" 白城市 ","isArchived":false,"county":" 洮南市","clerkId":7657,"remark":"结了婚了很久了","addressTypeText":"公司地址","visitEndTime":0,"visitObjectName":null,"relation":60,"visitReason":"1","visitBeginTime":0,"province":"吉林省 ","visitOrgId":0,"applicantId":7657,"applyTime":1550137087103,"clerkName":"qzws136","relationText":"本人","visitStatusRemark":null,"applyOrgId":47,"address":"福建省泉州市晋江市梅岭工业区梅岭路502号","visitStatusText":null,"addressType":3,"visitOrgName":null,"taskGuid":null,"applicantName":"qzws136","isPrint":false,"visitGuid":null,"applyOrgName":"qzws136","visitors":null,"visitCompleteTime":0,"effectBackDay":0,"visitStatus":0,"visitBackAmount":0,"visitRecordItems":[],"visitArea":"吉林省 . 白城市 . 洮南市","postCode":null,"visitGoal":"更何况还健康良好","isDel":false,"addressGuid":"1fd4a072-b25e-4186-a221-d222d5d336f6","visitTimes":0},{"visitStatusTime":0,"city":" 白城市 ","isArchived":false,"county":" 洮南市","clerkId":7657,"remark":"结了婚了很久了","addressTypeText":"家庭地址","visitEndTime":0,"visitObjectName":null,"relation":60,"visitReason":"1","visitBeginTime":0,"province":"吉林省 ","visitOrgId":0,"applicantId":7657,"applyTime":1550137093920,"clerkName":"qzws136","relationText":"本人","visitStatusRemark":null,"applyOrgId":47,"address":"福建省泉州市安溪县湖头镇福寿村","visitStatusText":null,"addressType":4,"visitOrgName":null,"taskGuid":null,"applicantName":"qzws136","isPrint":false,"visitGuid":null,"applyOrgName":"qzws136","visitors":"admin","visitCompleteTime":0,"effectBackDay":0,"visitStatus":30,"visitBackAmount":0,"visitRecordItems":[],"visitArea":"吉林省 . 白城市 . 洮南市","postCode":null,"visitGoal":"更何况还健康良好第三个发送到","isDel":false,"addressGuid":"f01f13a2-4643-48b4-a441-f52b36ba2239","visitTimes":0}],"visitTimes":null}],"sortStrategy":"ASC","sortFieldName":null}
     */

    private String statusID;
    private String message;
    private String dataType;
    private DataBeanX data;

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

    public DataBeanX getData() {
        return data;
    }

    public void setData(DataBeanX data) {
        this.data = data;
    }

    public static class DataBeanX implements Serializable {
        /**
         * pagination : null
         * data : [{"caseCode":"APG-20181206-004-000002","lastArrearAmount":"0.0","genderText":"男性","remark":null,"addressTypeText":null,"collectAmount":"7413.67","visitEndTime":null,"caseType":"10","visitObjectName":null,"visitReason":null,"visitBeginTime":null,"visitReturnAmount":null,"visitApplyUser":null,"applyTime":null,"debtorAge":"0","urgeStatusText":"在催","address":null,"visitStatusText":null,"backAmount":null,"batchCode":"APG-20181206-004","visitOrgName":null,"visitCompleteDate":null,"debtorName":"颜呈万","customerName":"平安新一贷","visitGuid":null,"applyOrgName":null,"visitors":null,"relateionText":null,"visitArea":null,"postCode":null,"visitGoal":null,"urgeVisitItems":[{"visitStatusTime":0,"city":" 阳泉市 ","isArchived":false,"county":" 郊　区","clerkId":7657,"remark":"idf\u2018的规范化股份将股份东莞是 ","addressTypeText":"家庭地址","visitEndTime":0,"visitObjectName":null,"relation":60,"visitReason":"0","visitBeginTime":0,"province":"山西省 ","visitOrgId":0,"applicantId":7657,"applyTime":1550137049215,"clerkName":"qzws136","relationText":"本人","visitStatusRemark":null,"applyOrgId":47,"address":"福建省泉州市晋江市安海镇尚贤中里18号","visitStatusText":null,"addressType":4,"visitOrgName":null,"taskGuid":null,"applicantName":"qzws136","isPrint":false,"visitGuid":null,"applyOrgName":"qzws136","visitors":"admin","visitCompleteTime":0,"effectBackDay":0,"visitStatus":30,"visitBackAmount":0,"visitRecordItems":[],"visitArea":"山西省 . 阳泉市 . 郊　区","postCode":null,"visitGoal":"fdf发，一公分 ","isDel":false,"addressGuid":"dddc4102-73a4-4a26-b4b0-53afa7c5815e","visitTimes":0},{"visitStatusTime":0,"city":" 阳泉市 ","isArchived":false,"county":" 郊　区","clerkId":7657,"remark":"idf\u2018的规范化股份将股份东莞是 ","addressTypeText":"公司地址","visitEndTime":0,"visitObjectName":null,"relation":60,"visitReason":"0","visitBeginTime":0,"province":"山西省 ","visitOrgId":0,"applicantId":7657,"applyTime":1550137061870,"clerkName":"qzws136","relationText":"本人","visitStatusRemark":null,"applyOrgId":47,"address":"福建省泉州市晋江市安海镇尚贤中里18号","visitStatusText":null,"addressType":3,"visitOrgName":null,"taskGuid":null,"applicantName":"qzws136","isPrint":false,"visitGuid":null,"applyOrgName":"qzws136","visitors":"admin","visitCompleteTime":0,"effectBackDay":0,"visitStatus":30,"visitBackAmount":0,"visitRecordItems":[],"visitArea":"山西省 . 阳泉市 . 郊　区","postCode":null,"visitGoal":"fdf发，一公分 ","isDel":false,"addressGuid":"29c3a3e5-99d9-492c-9d44-8036ee2ce447","visitTimes":0}],"visitTimes":null},{"caseCode":"APG-20181206-004-000003","lastArrearAmount":"0.0","genderText":"男性","remark":null,"addressTypeText":null,"collectAmount":"7539.73","visitEndTime":null,"caseType":"10","visitObjectName":null,"visitReason":null,"visitBeginTime":null,"visitReturnAmount":null,"visitApplyUser":null,"applyTime":null,"debtorAge":"0","urgeStatusText":"在催","address":null,"visitStatusText":null,"backAmount":null,"batchCode":"APG-20181206-004","visitOrgName":null,"visitCompleteDate":null,"debtorName":"李振标","customerName":"平安新一贷","visitGuid":null,"applyOrgName":null,"visitors":null,"relateionText":null,"visitArea":null,"postCode":null,"visitGoal":null,"urgeVisitItems":[{"visitStatusTime":0,"city":" 白城市 ","isArchived":false,"county":" 洮南市","clerkId":7657,"remark":"结了婚了很久了","addressTypeText":"公司地址","visitEndTime":0,"visitObjectName":null,"relation":60,"visitReason":"1","visitBeginTime":0,"province":"吉林省 ","visitOrgId":0,"applicantId":7657,"applyTime":1550137087103,"clerkName":"qzws136","relationText":"本人","visitStatusRemark":null,"applyOrgId":47,"address":"福建省泉州市晋江市梅岭工业区梅岭路502号","visitStatusText":null,"addressType":3,"visitOrgName":null,"taskGuid":null,"applicantName":"qzws136","isPrint":false,"visitGuid":null,"applyOrgName":"qzws136","visitors":null,"visitCompleteTime":0,"effectBackDay":0,"visitStatus":0,"visitBackAmount":0,"visitRecordItems":[],"visitArea":"吉林省 . 白城市 . 洮南市","postCode":null,"visitGoal":"更何况还健康良好","isDel":false,"addressGuid":"1fd4a072-b25e-4186-a221-d222d5d336f6","visitTimes":0},{"visitStatusTime":0,"city":" 白城市 ","isArchived":false,"county":" 洮南市","clerkId":7657,"remark":"结了婚了很久了","addressTypeText":"家庭地址","visitEndTime":0,"visitObjectName":null,"relation":60,"visitReason":"1","visitBeginTime":0,"province":"吉林省 ","visitOrgId":0,"applicantId":7657,"applyTime":1550137093920,"clerkName":"qzws136","relationText":"本人","visitStatusRemark":null,"applyOrgId":47,"address":"福建省泉州市安溪县湖头镇福寿村","visitStatusText":null,"addressType":4,"visitOrgName":null,"taskGuid":null,"applicantName":"qzws136","isPrint":false,"visitGuid":null,"applyOrgName":"qzws136","visitors":"admin","visitCompleteTime":0,"effectBackDay":0,"visitStatus":30,"visitBackAmount":0,"visitRecordItems":[],"visitArea":"吉林省 . 白城市 . 洮南市","postCode":null,"visitGoal":"更何况还健康良好第三个发送到","isDel":false,"addressGuid":"f01f13a2-4643-48b4-a441-f52b36ba2239","visitTimes":0}],"visitTimes":null}]
         * sortStrategy : ASC
         * sortFieldName : null
         */

        private Object pagination;
        private String sortStrategy;
        private Object sortFieldName;
        private List<DataBean> data;

        public Object getPagination() {
            return pagination;
        }

        public void setPagination(Object pagination) {
            this.pagination = pagination;
        }

        public String getSortStrategy() {
            return sortStrategy;
        }

        public void setSortStrategy(String sortStrategy) {
            this.sortStrategy = sortStrategy;
        }

        public Object getSortFieldName() {
            return sortFieldName;
        }

        public void setSortFieldName(Object sortFieldName) {
            this.sortFieldName = sortFieldName;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean implements Serializable{
            /**
             * caseCode : APG-20181206-004-000002
             * lastArrearAmount : 0.0
             * genderText : 男性
             * remark : null
             * addressTypeText : null
             * collectAmount : 7413.67
             * visitEndTime : null
             * caseType : 10
             * visitObjectName : null
             * visitReason : null
             * visitBeginTime : null
             * visitReturnAmount : null
             * visitApplyUser : null
             * applyTime : null
             * debtorAge : 0
             * urgeStatusText : 在催
             * address : null
             * visitStatusText : null
             * backAmount : null
             * batchCode : APG-20181206-004
             * visitOrgName : null
             * visitCompleteDate : null
             * debtorName : 颜呈万
             * customerName : 平安新一贷
             * visitGuid : null
             * applyOrgName : null
             * visitors : null
             * relateionText : null
             * visitArea : null
             * postCode : null
             * visitGoal : null
             * urgeVisitItems : [{"visitStatusTime":0,"city":" 阳泉市 ","isArchived":false,"county":" 郊　区","clerkId":7657,"remark":"idf\u2018的规范化股份将股份东莞是 ","addressTypeText":"家庭地址","visitEndTime":0,"visitObjectName":null,"relation":60,"visitReason":"0","visitBeginTime":0,"province":"山西省 ","visitOrgId":0,"applicantId":7657,"applyTime":1550137049215,"clerkName":"qzws136","relationText":"本人","visitStatusRemark":null,"applyOrgId":47,"address":"福建省泉州市晋江市安海镇尚贤中里18号","visitStatusText":null,"addressType":4,"visitOrgName":null,"taskGuid":null,"applicantName":"qzws136","isPrint":false,"visitGuid":null,"applyOrgName":"qzws136","visitors":"admin","visitCompleteTime":0,"effectBackDay":0,"visitStatus":30,"visitBackAmount":0,"visitRecordItems":[],"visitArea":"山西省 . 阳泉市 . 郊　区","postCode":null,"visitGoal":"fdf发，一公分 ","isDel":false,"addressGuid":"dddc4102-73a4-4a26-b4b0-53afa7c5815e","visitTimes":0},{"visitStatusTime":0,"city":" 阳泉市 ","isArchived":false,"county":" 郊　区","clerkId":7657,"remark":"idf\u2018的规范化股份将股份东莞是 ","addressTypeText":"公司地址","visitEndTime":0,"visitObjectName":null,"relation":60,"visitReason":"0","visitBeginTime":0,"province":"山西省 ","visitOrgId":0,"applicantId":7657,"applyTime":1550137061870,"clerkName":"qzws136","relationText":"本人","visitStatusRemark":null,"applyOrgId":47,"address":"福建省泉州市晋江市安海镇尚贤中里18号","visitStatusText":null,"addressType":3,"visitOrgName":null,"taskGuid":null,"applicantName":"qzws136","isPrint":false,"visitGuid":null,"applyOrgName":"qzws136","visitors":"admin","visitCompleteTime":0,"effectBackDay":0,"visitStatus":30,"visitBackAmount":0,"visitRecordItems":[],"visitArea":"山西省 . 阳泉市 . 郊　区","postCode":null,"visitGoal":"fdf发，一公分 ","isDel":false,"addressGuid":"29c3a3e5-99d9-492c-9d44-8036ee2ce447","visitTimes":0}]
             * visitTimes : null
             */

            private String caseCode;
            private String lastArrearAmount;
            private String genderText;
            private Object remark;
            private Object addressTypeText;
            private String collectAmount;
            private Long visitEndTime;
            private String caseType;
            private Object visitObjectName;
            private Object visitReason;
            private Long visitBeginTime;
            private Object visitReturnAmount;
            private Object visitApplyUser;
            private Long applyTime;
            private String debtorAge;
            private String urgeStatusText;
            private Object address;
            private Object visitStatusText;
            private Object backAmount;
            private String batchCode;
            private Object visitOrgName;
            private Object visitCompleteDate;
            private String debtorName;
            private String customerName;
            private Object visitGuid;
            private Object applyOrgName;
            private Object visitors;
            private Object relateionText;
            private Object visitArea;
            private Object postCode;
            private Object visitGoal;
            private Long visitTimes;
            private List<UrgeVisitItemsBean> urgeVisitItems;

            public String getCaseCode() {
                return caseCode;
            }

            public void setCaseCode(String caseCode) {
                this.caseCode = caseCode;
            }

            public String getLastArrearAmount() {
                return lastArrearAmount;
            }

            public void setLastArrearAmount(String lastArrearAmount) {
                this.lastArrearAmount = lastArrearAmount;
            }

            public String getGenderText() {
                return genderText;
            }

            public void setGenderText(String genderText) {
                this.genderText = genderText;
            }

            public Object getRemark() {
                return remark;
            }

            public void setRemark(Object remark) {
                this.remark = remark;
            }

            public Object getAddressTypeText() {
                return addressTypeText;
            }

            public void setAddressTypeText(Object addressTypeText) {
                this.addressTypeText = addressTypeText;
            }

            public String getCollectAmount() {
                return collectAmount;
            }

            public void setCollectAmount(String collectAmount) {
                this.collectAmount = collectAmount;
            }

            public Long getVisitEndTime() {
                return visitEndTime;
            }

            public void setVisitEndTime(Long visitEndTime) {
                this.visitEndTime = visitEndTime;
            }

            public String getCaseType() {
                return caseType;
            }

            public void setCaseType(String caseType) {
                this.caseType = caseType;
            }

            public Object getVisitObjectName() {
                return visitObjectName;
            }

            public void setVisitObjectName(Object visitObjectName) {
                this.visitObjectName = visitObjectName;
            }

            public Object getVisitReason() {
                return visitReason;
            }

            public void setVisitReason(Object visitReason) {
                this.visitReason = visitReason;
            }

            public Long getVisitBeginTime() {
                return visitBeginTime;
            }

            public void setVisitBeginTime(Long visitBeginTime) {
                this.visitBeginTime = visitBeginTime;
            }

            public Object getVisitReturnAmount() {
                return visitReturnAmount;
            }

            public void setVisitReturnAmount(Object visitReturnAmount) {
                this.visitReturnAmount = visitReturnAmount;
            }

            public Object getVisitApplyUser() {
                return visitApplyUser;
            }

            public void setVisitApplyUser(Object visitApplyUser) {
                this.visitApplyUser = visitApplyUser;
            }

            public Long getApplyTime() {
                return applyTime;
            }

            public void setApplyTime(Long applyTime) {
                this.applyTime = applyTime;
            }

            public String getDebtorAge() {
                return debtorAge;
            }

            public void setDebtorAge(String debtorAge) {
                this.debtorAge = debtorAge;
            }

            public String getUrgeStatusText() {
                return urgeStatusText;
            }

            public void setUrgeStatusText(String urgeStatusText) {
                this.urgeStatusText = urgeStatusText;
            }

            public Object getAddress() {
                return address;
            }

            public void setAddress(Object address) {
                this.address = address;
            }

            public Object getVisitStatusText() {
                return visitStatusText;
            }

            public void setVisitStatusText(Object visitStatusText) {
                this.visitStatusText = visitStatusText;
            }

            public Object getBackAmount() {
                return backAmount;
            }

            public void setBackAmount(Object backAmount) {
                this.backAmount = backAmount;
            }

            public String getBatchCode() {
                return batchCode;
            }

            public void setBatchCode(String batchCode) {
                this.batchCode = batchCode;
            }

            public Object getVisitOrgName() {
                return visitOrgName;
            }

            public void setVisitOrgName(Object visitOrgName) {
                this.visitOrgName = visitOrgName;
            }

            public Object getVisitCompleteDate() {
                return visitCompleteDate;
            }

            public void setVisitCompleteDate(Object visitCompleteDate) {
                this.visitCompleteDate = visitCompleteDate;
            }

            public String getDebtorName() {
                return debtorName;
            }

            public void setDebtorName(String debtorName) {
                this.debtorName = debtorName;
            }

            public String getCustomerName() {
                return customerName;
            }

            public void setCustomerName(String customerName) {
                this.customerName = customerName;
            }

            public Object getVisitGuid() {
                return visitGuid;
            }

            public void setVisitGuid(Object visitGuid) {
                this.visitGuid = visitGuid;
            }

            public Object getApplyOrgName() {
                return applyOrgName;
            }

            public void setApplyOrgName(Object applyOrgName) {
                this.applyOrgName = applyOrgName;
            }

            public Object getVisitors() {
                return visitors;
            }

            public void setVisitors(Object visitors) {
                this.visitors = visitors;
            }

            public Object getRelateionText() {
                return relateionText;
            }

            public void setRelateionText(Object relateionText) {
                this.relateionText = relateionText;
            }

            public Object getVisitArea() {
                return visitArea;
            }

            public void setVisitArea(Object visitArea) {
                this.visitArea = visitArea;
            }

            public Object getPostCode() {
                return postCode;
            }

            public void setPostCode(Object postCode) {
                this.postCode = postCode;
            }

            public Object getVisitGoal() {
                return visitGoal;
            }

            public void setVisitGoal(Object visitGoal) {
                this.visitGoal = visitGoal;
            }

            public Long getVisitTimes() {
                return visitTimes;
            }

            public void setVisitTimes(Long visitTimes) {
                this.visitTimes = visitTimes;
            }

            public List<UrgeVisitItemsBean> getUrgeVisitItems() {
                return urgeVisitItems;
            }

            public void setUrgeVisitItems(List<UrgeVisitItemsBean> urgeVisitItems) {
                this.urgeVisitItems = urgeVisitItems;
            }

            public static class UrgeVisitItemsBean implements Serializable {
                /**
                 * visitStatusTime : 0
                 * city :  阳泉市
                 * isArchived : false
                 * county :  郊　区
                 * clerkId : 7657
                 * remark : idf‘的规范化股份将股份东莞是
                 * addressTypeText : 家庭地址
                 * visitEndTime : 0
                 * visitObjectName : null
                 * relation : 60
                 * visitReason : 0
                 * visitBeginTime : 0
                 * province : 山西省
                 * visitOrgId : 0
                 * applicantId : 7657
                 * applyTime : 1550137049215
                 * clerkName : qzws136
                 * relationText : 本人
                 * visitStatusRemark : null
                 * applyOrgId : 47
                 * address : 福建省泉州市晋江市安海镇尚贤中里18号
                 * visitStatusText : null
                 * addressType : 4
                 * visitOrgName : null
                 * taskGuid : null
                 * applicantName : qzws136
                 * isPrint : false
                 * visitGuid : null
                 * applyOrgName : qzws136
                 * visitors : admin
                 * visitCompleteTime : 0
                 * effectBackDay : 0
                 * visitStatus : 30
                 * visitBackAmount : 0
                 * visitRecordItems : []
                 * visitArea : 山西省 . 阳泉市 . 郊　区
                 * postCode : null
                 * visitGoal : fdf发，一公分
                 * isDel : false
                 * addressGuid : dddc4102-73a4-4a26-b4b0-53afa7c5815e
                 * visitTimes : 0
                 */

                private Long visitStatusTime;
                private String city;
                private boolean isArchived;
                private String county;
                private int clerkId;
                private String remark;
                private String addressTypeText;
                private Long visitEndTime;
                private Object visitObjectName;
                private int relation;
                private String visitReason;
                private Long visitBeginTime;
                private String province;
                private int visitOrgId;
                private int applicantId;
                private Long applyTime;
                private String clerkName;
                private String relationText;
                private Object visitStatusRemark;
                private int applyOrgId;
                private String address;
                private Object visitStatusText;
                private int addressType;
                private Object visitOrgName;
                private Object taskGuid;
                private String applicantName;
                private boolean isPrint;
                private Object visitGuid;
                private String applyOrgName;
                private String visitors;
                private Long visitCompleteTime;
                private int effectBackDay;
                private int visitStatus;
                private int visitBackAmount;
                private String visitArea;
                private Object postCode;
                private String visitGoal;
                private boolean isDel;
                private String addressGuid;
                private Long visitTimes;
                private List<?> visitRecordItems;

                public Long getVisitStatusTime() {
                    return visitStatusTime;
                }

                public void setVisitStatusTime(Long visitStatusTime) {
                    this.visitStatusTime = visitStatusTime;
                }

                public String getCity() {
                    return city;
                }

                public void setCity(String city) {
                    this.city = city;
                }

                public boolean isIsArchived() {
                    return isArchived;
                }

                public void setIsArchived(boolean isArchived) {
                    this.isArchived = isArchived;
                }

                public String getCounty() {
                    return county;
                }

                public void setCounty(String county) {
                    this.county = county;
                }

                public int getClerkId() {
                    return clerkId;
                }

                public void setClerkId(int clerkId) {
                    this.clerkId = clerkId;
                }

                public String getRemark() {
                    return remark;
                }

                public void setRemark(String remark) {
                    this.remark = remark;
                }

                public String getAddressTypeText() {
                    return addressTypeText;
                }

                public void setAddressTypeText(String addressTypeText) {
                    this.addressTypeText = addressTypeText;
                }

                public Long getVisitEndTime() {
                    return visitEndTime;
                }

                public void setVisitEndTime(Long visitEndTime) {
                    this.visitEndTime = visitEndTime;
                }

                public Object getVisitObjectName() {
                    return visitObjectName;
                }

                public void setVisitObjectName(Object visitObjectName) {
                    this.visitObjectName = visitObjectName;
                }

                public int getRelation() {
                    return relation;
                }

                public void setRelation(int relation) {
                    this.relation = relation;
                }

                public String getVisitReason() {
                    return visitReason;
                }

                public void setVisitReason(String visitReason) {
                    this.visitReason = visitReason;
                }

                public Long getVisitBeginTime() {
                    return visitBeginTime;
                }

                public void setVisitBeginTime(Long visitBeginTime) {
                    this.visitBeginTime = visitBeginTime;
                }

                public String getProvince() {
                    return province;
                }

                public void setProvince(String province) {
                    this.province = province;
                }

                public int getVisitOrgId() {
                    return visitOrgId;
                }

                public void setVisitOrgId(int visitOrgId) {
                    this.visitOrgId = visitOrgId;
                }

                public int getApplicantId() {
                    return applicantId;
                }

                public void setApplicantId(int applicantId) {
                    this.applicantId = applicantId;
                }

                public long getApplyTime() {
                    return applyTime;
                }

                public void setApplyTime(long applyTime) {
                    this.applyTime = applyTime;
                }

                public String getClerkName() {
                    return clerkName;
                }

                public void setClerkName(String clerkName) {
                    this.clerkName = clerkName;
                }

                public String getRelationText() {
                    return relationText;
                }

                public void setRelationText(String relationText) {
                    this.relationText = relationText;
                }

                public Object getVisitStatusRemark() {
                    return visitStatusRemark;
                }

                public void setVisitStatusRemark(Object visitStatusRemark) {
                    this.visitStatusRemark = visitStatusRemark;
                }

                public int getApplyOrgId() {
                    return applyOrgId;
                }

                public void setApplyOrgId(int applyOrgId) {
                    this.applyOrgId = applyOrgId;
                }

                public String getAddress() {
                    return address;
                }

                public void setAddress(String address) {
                    this.address = address;
                }

                public Object getVisitStatusText() {
                    return visitStatusText;
                }

                public void setVisitStatusText(Object visitStatusText) {
                    this.visitStatusText = visitStatusText;
                }

                public int getAddressType() {
                    return addressType;
                }

                public void setAddressType(int addressType) {
                    this.addressType = addressType;
                }

                public Object getVisitOrgName() {
                    return visitOrgName;
                }

                public void setVisitOrgName(Object visitOrgName) {
                    this.visitOrgName = visitOrgName;
                }

                public Object getTaskGuid() {
                    return taskGuid;
                }

                public void setTaskGuid(Object taskGuid) {
                    this.taskGuid = taskGuid;
                }

                public String getApplicantName() {
                    return applicantName;
                }

                public void setApplicantName(String applicantName) {
                    this.applicantName = applicantName;
                }

                public boolean isIsPrint() {
                    return isPrint;
                }

                public void setIsPrint(boolean isPrint) {
                    this.isPrint = isPrint;
                }

                public Object getVisitGuid() {
                    return visitGuid;
                }

                public void setVisitGuid(Object visitGuid) {
                    this.visitGuid = visitGuid;
                }

                public String getApplyOrgName() {
                    return applyOrgName;
                }

                public void setApplyOrgName(String applyOrgName) {
                    this.applyOrgName = applyOrgName;
                }

                public String getVisitors() {
                    return visitors;
                }

                public void setVisitors(String visitors) {
                    this.visitors = visitors;
                }

                public Long getVisitCompleteTime() {
                    return visitCompleteTime;
                }

                public void setVisitCompleteTime(Long visitCompleteTime) {
                    this.visitCompleteTime = visitCompleteTime;
                }

                public int getEffectBackDay() {
                    return effectBackDay;
                }

                public void setEffectBackDay(int effectBackDay) {
                    this.effectBackDay = effectBackDay;
                }

                public int getVisitStatus() {
                    return visitStatus;
                }

                public void setVisitStatus(int visitStatus) {
                    this.visitStatus = visitStatus;
                }

                public int getVisitBackAmount() {
                    return visitBackAmount;
                }

                public void setVisitBackAmount(int visitBackAmount) {
                    this.visitBackAmount = visitBackAmount;
                }

                public String getVisitArea() {
                    return visitArea;
                }

                public void setVisitArea(String visitArea) {
                    this.visitArea = visitArea;
                }

                public Object getPostCode() {
                    return postCode;
                }

                public void setPostCode(Object postCode) {
                    this.postCode = postCode;
                }

                public String getVisitGoal() {
                    return visitGoal;
                }

                public void setVisitGoal(String visitGoal) {
                    this.visitGoal = visitGoal;
                }

                public boolean isIsDel() {
                    return isDel;
                }

                public void setIsDel(boolean isDel) {
                    this.isDel = isDel;
                }

                public String getAddressGuid() {
                    return addressGuid;
                }

                public void setAddressGuid(String addressGuid) {
                    this.addressGuid = addressGuid;
                }

                public Long getVisitTimes() {
                    return visitTimes;
                }

                public void setVisitTimes(Long visitTimes) {
                    this.visitTimes = visitTimes;
                }

                public List<?> getVisitRecordItems() {
                    return visitRecordItems;
                }

                public void setVisitRecordItems(List<?> visitRecordItems) {
                    this.visitRecordItems = visitRecordItems;
                }
            }
        }
    }
}