package wansun.visit.android.bean;

import java.util.List;

/**
 * Created by User on 2019/3/7.
 */

public class caseAddPeopleBean {

    /**
     * statusID : 200
     * message : ok
     * dataType : application/json
     * data : {"caseCode":"APG-20181206-004-000001","relation":70,"relationText":"亲戚","name":"554","cidNo":"44","cidType":7,"cidTypeText":"党员证","cidOfDebtor":null,"emailItems":null,"gender":999,"genderText":"其他","age":44,"contactGuId":null,"personStatusItem":null,"companyItems":[],"phoneItems":[],"addressItems":[],"socialNo":"","debtorNo":"","iM":""}
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
         * relation : 70
         * relationText : 亲戚
         * name : 554
         * cidNo : 44
         * cidType : 7
         * cidTypeText : 党员证
         * cidOfDebtor : null
         * emailItems : null
         * gender : 999
         * genderText : 其他
         * age : 44
         * contactGuId : null
         * personStatusItem : null
         * companyItems : []
         * phoneItems : []
         * addressItems : []
         * socialNo :
         * debtorNo :
         * iM :
         */

        private String caseCode;
        private int relation;
        private String relationText;
        private String name;
        private String cidNo;
        private int cidType;
        private String cidTypeText;
        private Object cidOfDebtor;
        private Object emailItems;
        private int gender;
        private String genderText;
        private int age;
        private Object contactGuId;
        private Object personStatusItem;
        private String socialNo;
        private String debtorNo;
        private String iM;
        private List<?> companyItems;
        private List<?> phoneItems;
        private List<?> addressItems;

        public String getCaseCode() {
            return caseCode;
        }

        public void setCaseCode(String caseCode) {
            this.caseCode = caseCode;
        }

        public int getRelation() {
            return relation;
        }

        public void setRelation(int relation) {
            this.relation = relation;
        }

        public String getRelationText() {
            return relationText;
        }

        public void setRelationText(String relationText) {
            this.relationText = relationText;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCidNo() {
            return cidNo;
        }

        public void setCidNo(String cidNo) {
            this.cidNo = cidNo;
        }

        public int getCidType() {
            return cidType;
        }

        public void setCidType(int cidType) {
            this.cidType = cidType;
        }

        public String getCidTypeText() {
            return cidTypeText;
        }

        public void setCidTypeText(String cidTypeText) {
            this.cidTypeText = cidTypeText;
        }

        public Object getCidOfDebtor() {
            return cidOfDebtor;
        }

        public void setCidOfDebtor(Object cidOfDebtor) {
            this.cidOfDebtor = cidOfDebtor;
        }

        public Object getEmailItems() {
            return emailItems;
        }

        public void setEmailItems(Object emailItems) {
            this.emailItems = emailItems;
        }

        public int getGender() {
            return gender;
        }

        public void setGender(int gender) {
            this.gender = gender;
        }

        public String getGenderText() {
            return genderText;
        }

        public void setGenderText(String genderText) {
            this.genderText = genderText;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public Object getContactGuId() {
            return contactGuId;
        }

        public void setContactGuId(Object contactGuId) {
            this.contactGuId = contactGuId;
        }

        public Object getPersonStatusItem() {
            return personStatusItem;
        }

        public void setPersonStatusItem(Object personStatusItem) {
            this.personStatusItem = personStatusItem;
        }

        public String getSocialNo() {
            return socialNo;
        }

        public void setSocialNo(String socialNo) {
            this.socialNo = socialNo;
        }

        public String getDebtorNo() {
            return debtorNo;
        }

        public void setDebtorNo(String debtorNo) {
            this.debtorNo = debtorNo;
        }

        public String getIM() {
            return iM;
        }

        public void setIM(String iM) {
            this.iM = iM;
        }

        public List<?> getCompanyItems() {
            return companyItems;
        }

        public void setCompanyItems(List<?> companyItems) {
            this.companyItems = companyItems;
        }

        public List<?> getPhoneItems() {
            return phoneItems;
        }

        public void setPhoneItems(List<?> phoneItems) {
            this.phoneItems = phoneItems;
        }

        public List<?> getAddressItems() {
            return addressItems;
        }

        public void setAddressItems(List<?> addressItems) {
            this.addressItems = addressItems;
        }
    }
}
