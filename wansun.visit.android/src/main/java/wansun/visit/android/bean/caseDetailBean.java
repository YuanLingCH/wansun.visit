package wansun.visit.android.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by User on 2019/2/20.
 */

public class caseDetailBean implements Serializable{

    /**
     * statusID : 200
     * message : ok
     * dataType : application/json
     * data : {"caseCode":"APG-20181206-004-000001","customerName":"平安新一贷","caseType":10,"clerkName":"施雪婷","cidNo":"350581199205121217","debtorName":"王明献","genderText":"男性","urgeStatusText":"在催","collectionAmount":"5164.23","totalBackAmount":"","lastArrears":"","bankInterest":"","entrustDate":1544025600000,"deadLine":1544003892697,"assignDate":1546825607317,"lastFollowDate":0,"area":"福建泉州","hand":"","debtorCompany":null,"overdueInstallments":"","overdueDays":"20","visitObjectName":"张三","relationText":"父亲","visitGoal":"回款","visitRemark":"备注","visitAddress":"广东省深圳市龙岗区坂田街道","visitReason":null,"visitBeginTime":null,"visitors":null,"cardDetails":[{"cardNo":"6230580000137375742","caseAmount":5164.23,"backAmount":0,"arrears":5164.23,"overdueDays":"","overdueInstallment":"","defaultRatings":"","hand":"","account":""}],"phoneDetails":[{"status":0,"phoneStatusText":"未知电话","name":"王明献","relation":"本人","phoneTypeText":"手机","phoneNumber":"13655977703","remark":""},{"status":0,"phoneStatusText":"未知电话","name":"王明献","relation":"本人","phoneTypeText":"单位","phoneNumber":"0595-88712782","remark":""},{"status":0,"phoneStatusText":"未知电话","name":"王明献","relation":"本人","phoneTypeText":"家庭","phoneNumber":"0595-0000","remark":""},{"status":0,"phoneStatusText":"未知电话","name":"蔡亚川","relation":"其他","phoneTypeText":"家庭","phoneNumber":"--","remark":""},{"status":0,"phoneStatusText":"未知电话","name":"蔡亚川","relation":"其他","phoneTypeText":"手机","phoneNumber":"13459577717","remark":""},{"status":0,"phoneStatusText":"未知电话","name":"陈博","relation":"其他","phoneTypeText":"家庭","phoneNumber":"--","remark":""},{"status":0,"phoneStatusText":"未知电话","name":"陈博","relation":"其他","phoneTypeText":"手机","phoneNumber":"18953040606","remark":""}],"visitRecord":[{"operateTypeText":"外访申请","operateContent":"申请外访,外访目标[fdsa],外访备注[fdsaf]","operatorName":"admin","operateTime":1545280722000,"recordGuid":"kkkk"},{"operateTypeText":"审核","operateContent":"操作前：已申请/待审核修改，操作后：已审核/待排程","operatorName":"admin","operateTime":1545285514806,"recordGuid":"4484137d-9c51-4045-a965-a6e160413f2e"},{"operateTypeText":"审核","operateContent":"操作前：已申请/待审核修改，操作后：已审核/待排程","operatorName":"admin","operateTime":1545285539615,"recordGuid":"b22cc583-58cf-4586-b7a5-88ab77d8020e"},{"operateTypeText":"排程","operateContent":"操作前：已审核/待排程，操作后：已排程/待打印","operatorName":"admin","operateTime":1545285557052,"recordGuid":"40a90c48-2d13-4cb1-b79c-0472a36a5028"},{"operateTypeText":"审核","operateContent":"操作前：已申请/待审核修改，操作后：已审核/待排程","operatorName":"admin","operateTime":1545357649789,"recordGuid":"adb460dc-8a56-4086-bdeb-cb86e2434617"},{"operateTypeText":"排程","operateContent":"操作前：已审核/待排程，操作后：已排程/待打印","operatorName":"admin","operateTime":1545357672272,"recordGuid":"1fa165d6-4bd6-48a7-a9fe-2a67a0214162"},{"operateTypeText":"外访登记","operateContent":"dfsadsaf","operatorName":"administrator","operateTime":1545361634599,"recordGuid":"6bebade1-9194-4ffe-a453-36c2b002b033"},{"operateTypeText":"审核","operateContent":"操作前：已申请/待审核修改，操作后：已审核/待排程","operatorName":"admin","operateTime":1545375729026,"recordGuid":"a4d5c355-ad12-4f48-9a0b-a76bf2361dfe"},{"operateTypeText":"排程","operateContent":"操作前：已审核/待排程，操作后：已排程/待打印","operatorName":"admin","operateTime":1545376223707,"recordGuid":"345825ef-02dc-4137-bc2a-aedefe377e69"},{"operateTypeText":"审核","operateContent":"操作前：已申请/待审核修改，操作后：已审核/待排程","operatorName":"admin","operateTime":1545378207833,"recordGuid":"d20ed99e-af2a-43de-899c-2c3602c9ff6f"},{"operateTypeText":"审核","operateContent":"操作前：已申请/待审核修改，操作后：已审核/待排程","operatorName":"admin","operateTime":1545378497381,"recordGuid":"25eacf85-4ef2-4763-98df-85c8a6fe0412"},{"operateTypeText":"排程","operateContent":"操作前：已审核/待排程，操作后：已排程/待打印","operatorName":"admin","operateTime":1545378563201,"recordGuid":"a47af642-ecdc-40bb-99cf-1168ae5d2c7a"},{"operateTypeText":"排程","operateContent":"操作前：已审核/待排程，操作后：已排程/待打印","operatorName":"admin","operateTime":1545709614530,"recordGuid":"b1aca44a-a12c-403f-b22a-7398de521073"},{"operateTypeText":"排程","operateContent":"操作前：已审核/待排程，操作后：已排程/待打印","operatorName":"admin","operateTime":1545709829767,"recordGuid":"8ae8ee60-4159-46ed-ab0a-5b407233d2a1"},{"operateTypeText":"排程","operateContent":"操作前：已审核/待排程，操作后：已排程/待打印","operatorName":"admin","operateTime":1545709891622,"recordGuid":"b26cd447-5aa0-4687-8c72-29e6981f93a6"},{"operateTypeText":"排程","operateContent":"操作前：已审核/待排程，操作后：已排程/待打印","operatorName":"admin","operateTime":1545709992033,"recordGuid":"2ebb2fae-368e-42d5-9687-29a338826b07"},{"operateTypeText":"审核","operateContent":"操作前：已申请/待审核修改，操作后：已审核/待排程","operatorName":"admin","operateTime":1545723531868,"recordGuid":"78ded534-4a5e-4f49-a9e2-97ad4f5b8a87"},{"operateTypeText":"排程","operateContent":"操作前：已审核/待排程，操作后：已排程/待打印","operatorName":"admin","operateTime":1550214856140,"recordGuid":"e67597e6-f5b5-4015-99c8-ef5a409293b5"},{"operateTypeText":"排程","operateContent":"操作前：已审核/待排程，操作后：已排程/待打印","operatorName":"admin","operateTime":1550224868490,"recordGuid":"230a532b-1dfd-4fae-9370-2f388fd48fe3"}],"urgeRecord":[{"promiseAmount":100,"promiseDate":1547596800000,"operateTypeDecoration":"电催","operateObjectContent":"13655977703","measure":"无","operateDate":1547609877475,"operator":"qzws136"},{"promiseAmount":100,"promiseDate":1548460800000,"operateTypeDecoration":"电催","operateObjectContent":"13655977703","measure":"无","operateDate":1548143562480,"operator":"qzws136"},{"promiseAmount":null,"promiseDate":null,"operateTypeDecoration":"电催","operateObjectContent":"1111111","measure":"承诺还款","operateDate":1548397858750,"operator":"admin"}],"addressDetails":[],"debtorSource":[{"fileGuid":"2f84a32b0ce74bf18d66e5d07583e59f","sourceFileName":"new 2.txt","fileName":"2f84a32b0ce74bf18d66e5d07583e59f.txt","contentTypeName":"text/plain","contentLength":2470,"filePath":"D:/UploadFile/DebtorFile/APG-20181206-004-000001/2f84a32b0ce74bf18d66e5d07583e59f.txt","fileSize":"2470","uploaderId":7657,"uploaderName":"施雪婷","uploadTime":1550454028293,"isDel":false},{"fileGuid":"d7fc9c760df84533a3f6a688d5bd6bc5","sourceFileName":"new 2.txt","fileName":"d7fc9c760df84533a3f6a688d5bd6bc5.txt","contentTypeName":"text/plain","contentLength":2470,"filePath":"D:/UploadFile/DebtorFile/APG-20181206-004-000001/d7fc9c760df84533a3f6a688d5bd6bc5.txt","fileSize":"2470","uploaderId":7657,"uploaderName":"施雪婷","uploadTime":1550454036106,"isDel":false},{"fileGuid":"25b8c93c78334b1d8471d4a990e1e6e8","sourceFileName":"new 2.txt","fileName":"25b8c93c78334b1d8471d4a990e1e6e8.txt","contentTypeName":"text/plain","contentLength":2470,"filePath":"D:/UploadFile/DebtorFile/APG-20181206-004-000001/25b8c93c78334b1d8471d4a990e1e6e8.txt","fileSize":"2470","uploaderId":7657,"uploaderName":"施雪婷","uploadTime":1550454280517,"isDel":false},{"fileGuid":"14b7510bd7ff476fb8243a761a9ee24d","sourceFileName":"new 2.txt","fileName":"14b7510bd7ff476fb8243a761a9ee24d.txt","contentTypeName":"text/plain","contentLength":2470,"filePath":"D:/UploadFile/DebtorFile/APG-20181206-004-000001/14b7510bd7ff476fb8243a761a9ee24d.txt","fileSize":"2470","uploaderId":7657,"uploaderName":"施雪婷","uploadTime":1550454291969,"isDel":false}]}
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
         * customerName : 平安新一贷
         * caseType : 10
         * clerkName : 施雪婷
         * cidNo : 350581199205121217
         * debtorName : 王明献
         * genderText : 男性
         * urgeStatusText : 在催
         * collectionAmount : 5164.23
         * totalBackAmount :
         * lastArrears :
         * bankInterest :
         * entrustDate : 1544025600000
         * deadLine : 1544003892697
         * assignDate : 1546825607317
         * lastFollowDate : 0
         * area : 福建泉州
         * hand :
         * debtorCompany : null
         * overdueInstallments :
         * overdueDays : 20
         * visitObjectName : 张三
         * relationText : 父亲
         * visitGoal : 回款
         * visitRemark : 备注
         * visitAddress : 广东省深圳市龙岗区坂田街道
         * visitReason : null
         * visitBeginTime : null
         * visitors : null
         * cardDetails : [{"cardNo":"6230580000137375742","caseAmount":5164.23,"backAmount":0,"arrears":5164.23,"overdueDays":"","overdueInstallment":"","defaultRatings":"","hand":"","account":""}]
         * phoneDetails : [{"status":0,"phoneStatusText":"未知电话","name":"王明献","relation":"本人","phoneTypeText":"手机","phoneNumber":"13655977703","remark":""},{"status":0,"phoneStatusText":"未知电话","name":"王明献","relation":"本人","phoneTypeText":"单位","phoneNumber":"0595-88712782","remark":""},{"status":0,"phoneStatusText":"未知电话","name":"王明献","relation":"本人","phoneTypeText":"家庭","phoneNumber":"0595-0000","remark":""},{"status":0,"phoneStatusText":"未知电话","name":"蔡亚川","relation":"其他","phoneTypeText":"家庭","phoneNumber":"--","remark":""},{"status":0,"phoneStatusText":"未知电话","name":"蔡亚川","relation":"其他","phoneTypeText":"手机","phoneNumber":"13459577717","remark":""},{"status":0,"phoneStatusText":"未知电话","name":"陈博","relation":"其他","phoneTypeText":"家庭","phoneNumber":"--","remark":""},{"status":0,"phoneStatusText":"未知电话","name":"陈博","relation":"其他","phoneTypeText":"手机","phoneNumber":"18953040606","remark":""}]
         * visitRecord : [{"operateTypeText":"外访申请","operateContent":"申请外访,外访目标[fdsa],外访备注[fdsaf]","operatorName":"admin","operateTime":1545280722000,"recordGuid":"kkkk"},{"operateTypeText":"审核","operateContent":"操作前：已申请/待审核修改，操作后：已审核/待排程","operatorName":"admin","operateTime":1545285514806,"recordGuid":"4484137d-9c51-4045-a965-a6e160413f2e"},{"operateTypeText":"审核","operateContent":"操作前：已申请/待审核修改，操作后：已审核/待排程","operatorName":"admin","operateTime":1545285539615,"recordGuid":"b22cc583-58cf-4586-b7a5-88ab77d8020e"},{"operateTypeText":"排程","operateContent":"操作前：已审核/待排程，操作后：已排程/待打印","operatorName":"admin","operateTime":1545285557052,"recordGuid":"40a90c48-2d13-4cb1-b79c-0472a36a5028"},{"operateTypeText":"审核","operateContent":"操作前：已申请/待审核修改，操作后：已审核/待排程","operatorName":"admin","operateTime":1545357649789,"recordGuid":"adb460dc-8a56-4086-bdeb-cb86e2434617"},{"operateTypeText":"排程","operateContent":"操作前：已审核/待排程，操作后：已排程/待打印","operatorName":"admin","operateTime":1545357672272,"recordGuid":"1fa165d6-4bd6-48a7-a9fe-2a67a0214162"},{"operateTypeText":"外访登记","operateContent":"dfsadsaf","operatorName":"administrator","operateTime":1545361634599,"recordGuid":"6bebade1-9194-4ffe-a453-36c2b002b033"},{"operateTypeText":"审核","operateContent":"操作前：已申请/待审核修改，操作后：已审核/待排程","operatorName":"admin","operateTime":1545375729026,"recordGuid":"a4d5c355-ad12-4f48-9a0b-a76bf2361dfe"},{"operateTypeText":"排程","operateContent":"操作前：已审核/待排程，操作后：已排程/待打印","operatorName":"admin","operateTime":1545376223707,"recordGuid":"345825ef-02dc-4137-bc2a-aedefe377e69"},{"operateTypeText":"审核","operateContent":"操作前：已申请/待审核修改，操作后：已审核/待排程","operatorName":"admin","operateTime":1545378207833,"recordGuid":"d20ed99e-af2a-43de-899c-2c3602c9ff6f"},{"operateTypeText":"审核","operateContent":"操作前：已申请/待审核修改，操作后：已审核/待排程","operatorName":"admin","operateTime":1545378497381,"recordGuid":"25eacf85-4ef2-4763-98df-85c8a6fe0412"},{"operateTypeText":"排程","operateContent":"操作前：已审核/待排程，操作后：已排程/待打印","operatorName":"admin","operateTime":1545378563201,"recordGuid":"a47af642-ecdc-40bb-99cf-1168ae5d2c7a"},{"operateTypeText":"排程","operateContent":"操作前：已审核/待排程，操作后：已排程/待打印","operatorName":"admin","operateTime":1545709614530,"recordGuid":"b1aca44a-a12c-403f-b22a-7398de521073"},{"operateTypeText":"排程","operateContent":"操作前：已审核/待排程，操作后：已排程/待打印","operatorName":"admin","operateTime":1545709829767,"recordGuid":"8ae8ee60-4159-46ed-ab0a-5b407233d2a1"},{"operateTypeText":"排程","operateContent":"操作前：已审核/待排程，操作后：已排程/待打印","operatorName":"admin","operateTime":1545709891622,"recordGuid":"b26cd447-5aa0-4687-8c72-29e6981f93a6"},{"operateTypeText":"排程","operateContent":"操作前：已审核/待排程，操作后：已排程/待打印","operatorName":"admin","operateTime":1545709992033,"recordGuid":"2ebb2fae-368e-42d5-9687-29a338826b07"},{"operateTypeText":"审核","operateContent":"操作前：已申请/待审核修改，操作后：已审核/待排程","operatorName":"admin","operateTime":1545723531868,"recordGuid":"78ded534-4a5e-4f49-a9e2-97ad4f5b8a87"},{"operateTypeText":"排程","operateContent":"操作前：已审核/待排程，操作后：已排程/待打印","operatorName":"admin","operateTime":1550214856140,"recordGuid":"e67597e6-f5b5-4015-99c8-ef5a409293b5"},{"operateTypeText":"排程","operateContent":"操作前：已审核/待排程，操作后：已排程/待打印","operatorName":"admin","operateTime":1550224868490,"recordGuid":"230a532b-1dfd-4fae-9370-2f388fd48fe3"}]
         * urgeRecord : [{"promiseAmount":100,"promiseDate":1547596800000,"operateTypeDecoration":"电催","operateObjectContent":"13655977703","measure":"无","operateDate":1547609877475,"operator":"qzws136"},{"promiseAmount":100,"promiseDate":1548460800000,"operateTypeDecoration":"电催","operateObjectContent":"13655977703","measure":"无","operateDate":1548143562480,"operator":"qzws136"},{"promiseAmount":null,"promiseDate":null,"operateTypeDecoration":"电催","operateObjectContent":"1111111","measure":"承诺还款","operateDate":1548397858750,"operator":"admin"}]
         * addressDetails : []
         * debtorSource : [{"fileGuid":"2f84a32b0ce74bf18d66e5d07583e59f","sourceFileName":"new 2.txt","fileName":"2f84a32b0ce74bf18d66e5d07583e59f.txt","contentTypeName":"text/plain","contentLength":2470,"filePath":"D:/UploadFile/DebtorFile/APG-20181206-004-000001/2f84a32b0ce74bf18d66e5d07583e59f.txt","fileSize":"2470","uploaderId":7657,"uploaderName":"施雪婷","uploadTime":1550454028293,"isDel":false},{"fileGuid":"d7fc9c760df84533a3f6a688d5bd6bc5","sourceFileName":"new 2.txt","fileName":"d7fc9c760df84533a3f6a688d5bd6bc5.txt","contentTypeName":"text/plain","contentLength":2470,"filePath":"D:/UploadFile/DebtorFile/APG-20181206-004-000001/d7fc9c760df84533a3f6a688d5bd6bc5.txt","fileSize":"2470","uploaderId":7657,"uploaderName":"施雪婷","uploadTime":1550454036106,"isDel":false},{"fileGuid":"25b8c93c78334b1d8471d4a990e1e6e8","sourceFileName":"new 2.txt","fileName":"25b8c93c78334b1d8471d4a990e1e6e8.txt","contentTypeName":"text/plain","contentLength":2470,"filePath":"D:/UploadFile/DebtorFile/APG-20181206-004-000001/25b8c93c78334b1d8471d4a990e1e6e8.txt","fileSize":"2470","uploaderId":7657,"uploaderName":"施雪婷","uploadTime":1550454280517,"isDel":false},{"fileGuid":"14b7510bd7ff476fb8243a761a9ee24d","sourceFileName":"new 2.txt","fileName":"14b7510bd7ff476fb8243a761a9ee24d.txt","contentTypeName":"text/plain","contentLength":2470,"filePath":"D:/UploadFile/DebtorFile/APG-20181206-004-000001/14b7510bd7ff476fb8243a761a9ee24d.txt","fileSize":"2470","uploaderId":7657,"uploaderName":"施雪婷","uploadTime":1550454291969,"isDel":false}]
         */

        private String caseCode;
        private String customerName;
        private int caseType;
        private String clerkName;
        private String cidNo;
        private String debtorName;
        private String genderText;
        private String urgeStatusText;
        private String collectionAmount;
        private String totalBackAmount;
        private String lastArrears;
        private String bankInterest;
        private long entrustDate;
        private long deadLine;
        private long assignDate;
        private int lastFollowDate;
        private String area;
        private String hand;
        private Object debtorCompany;
        private String overdueInstallments;
        private String overdueDays;
        private String visitObjectName;
        private String relationText;
        private String visitGoal;
        private String visitRemark;
        private String visitAddress;
        private Object visitReason;
        private Object visitBeginTime;
        private Object visitors;
        private List<CardDetailsBean> cardDetails;
        private List<PhoneDetailsBean> phoneDetails;
        private List<VisitRecordBean> visitRecord;
        private List<UrgeRecordBean> urgeRecord;
        private List<?> addressDetails;
        private List<DebtorSourceBean> debtorSource;

        public String getCaseCode() {
            return caseCode;
        }

        public void setCaseCode(String caseCode) {
            this.caseCode = caseCode;
        }

        public String getCustomerName() {
            return customerName;
        }

        public void setCustomerName(String customerName) {
            this.customerName = customerName;
        }

        public int getCaseType() {
            return caseType;
        }

        public void setCaseType(int caseType) {
            this.caseType = caseType;
        }

        public String getClerkName() {
            return clerkName;
        }

        public void setClerkName(String clerkName) {
            this.clerkName = clerkName;
        }

        public String getCidNo() {
            return cidNo;
        }

        public void setCidNo(String cidNo) {
            this.cidNo = cidNo;
        }

        public String getDebtorName() {
            return debtorName;
        }

        public void setDebtorName(String debtorName) {
            this.debtorName = debtorName;
        }

        public String getGenderText() {
            return genderText;
        }

        public void setGenderText(String genderText) {
            this.genderText = genderText;
        }

        public String getUrgeStatusText() {
            return urgeStatusText;
        }

        public void setUrgeStatusText(String urgeStatusText) {
            this.urgeStatusText = urgeStatusText;
        }

        public String getCollectionAmount() {
            return collectionAmount;
        }

        public void setCollectionAmount(String collectionAmount) {
            this.collectionAmount = collectionAmount;
        }

        public String getTotalBackAmount() {
            return totalBackAmount;
        }

        public void setTotalBackAmount(String totalBackAmount) {
            this.totalBackAmount = totalBackAmount;
        }

        public String getLastArrears() {
            return lastArrears;
        }

        public void setLastArrears(String lastArrears) {
            this.lastArrears = lastArrears;
        }

        public String getBankInterest() {
            return bankInterest;
        }

        public void setBankInterest(String bankInterest) {
            this.bankInterest = bankInterest;
        }

        public long getEntrustDate() {
            return entrustDate;
        }

        public void setEntrustDate(long entrustDate) {
            this.entrustDate = entrustDate;
        }

        public long getDeadLine() {
            return deadLine;
        }

        public void setDeadLine(long deadLine) {
            this.deadLine = deadLine;
        }

        public long getAssignDate() {
            return assignDate;
        }

        public void setAssignDate(long assignDate) {
            this.assignDate = assignDate;
        }

        public int getLastFollowDate() {
            return lastFollowDate;
        }

        public void setLastFollowDate(int lastFollowDate) {
            this.lastFollowDate = lastFollowDate;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getHand() {
            return hand;
        }

        public void setHand(String hand) {
            this.hand = hand;
        }

        public Object getDebtorCompany() {
            return debtorCompany;
        }

        public void setDebtorCompany(Object debtorCompany) {
            this.debtorCompany = debtorCompany;
        }

        public String getOverdueInstallments() {
            return overdueInstallments;
        }

        public void setOverdueInstallments(String overdueInstallments) {
            this.overdueInstallments = overdueInstallments;
        }

        public String getOverdueDays() {
            return overdueDays;
        }

        public void setOverdueDays(String overdueDays) {
            this.overdueDays = overdueDays;
        }

        public String getVisitObjectName() {
            return visitObjectName;
        }

        public void setVisitObjectName(String visitObjectName) {
            this.visitObjectName = visitObjectName;
        }

        public String getRelationText() {
            return relationText;
        }

        public void setRelationText(String relationText) {
            this.relationText = relationText;
        }

        public String getVisitGoal() {
            return visitGoal;
        }

        public void setVisitGoal(String visitGoal) {
            this.visitGoal = visitGoal;
        }

        public String getVisitRemark() {
            return visitRemark;
        }

        public void setVisitRemark(String visitRemark) {
            this.visitRemark = visitRemark;
        }

        public String getVisitAddress() {
            return visitAddress;
        }

        public void setVisitAddress(String visitAddress) {
            this.visitAddress = visitAddress;
        }

        public Object getVisitReason() {
            return visitReason;
        }

        public void setVisitReason(Object visitReason) {
            this.visitReason = visitReason;
        }

        public Object getVisitBeginTime() {
            return visitBeginTime;
        }

        public void setVisitBeginTime(Object visitBeginTime) {
            this.visitBeginTime = visitBeginTime;
        }

        public Object getVisitors() {
            return visitors;
        }

        public void setVisitors(Object visitors) {
            this.visitors = visitors;
        }

        public List<CardDetailsBean> getCardDetails() {
            return cardDetails;
        }

        public void setCardDetails(List<CardDetailsBean> cardDetails) {
            this.cardDetails = cardDetails;
        }

        public List<PhoneDetailsBean> getPhoneDetails() {
            return phoneDetails;
        }

        public void setPhoneDetails(List<PhoneDetailsBean> phoneDetails) {
            this.phoneDetails = phoneDetails;
        }

        public List<VisitRecordBean> getVisitRecord() {
            return visitRecord;
        }

        public void setVisitRecord(List<VisitRecordBean> visitRecord) {
            this.visitRecord = visitRecord;
        }

        public List<UrgeRecordBean> getUrgeRecord() {
            return urgeRecord;
        }

        public void setUrgeRecord(List<UrgeRecordBean> urgeRecord) {
            this.urgeRecord = urgeRecord;
        }

        public List<?> getAddressDetails() {
            return addressDetails;
        }

        public void setAddressDetails(List<?> addressDetails) {
            this.addressDetails = addressDetails;
        }

        public List<DebtorSourceBean> getDebtorSource() {
            return debtorSource;
        }

        public void setDebtorSource(List<DebtorSourceBean> debtorSource) {
            this.debtorSource = debtorSource;
        }

        public static class CardDetailsBean implements Serializable {
            /**
             * cardNo : 6230580000137375742
             * caseAmount : 5164.23
             * backAmount : 0
             * arrears : 5164.23
             * overdueDays :
             * overdueInstallment :
             * defaultRatings :
             * hand :
             * account :
             */

            private String cardNo;
            private double caseAmount;
            private int backAmount;
            private double arrears;
            private String overdueDays;
            private String overdueInstallment;
            private String defaultRatings;
            private String hand;
            private String account;

            public String getCardNo() {
                return cardNo;
            }

            public void setCardNo(String cardNo) {
                this.cardNo = cardNo;
            }

            public double getCaseAmount() {
                return caseAmount;
            }

            public void setCaseAmount(double caseAmount) {
                this.caseAmount = caseAmount;
            }

            public int getBackAmount() {
                return backAmount;
            }

            public void setBackAmount(int backAmount) {
                this.backAmount = backAmount;
            }

            public double getArrears() {
                return arrears;
            }

            public void setArrears(double arrears) {
                this.arrears = arrears;
            }

            public String getOverdueDays() {
                return overdueDays;
            }

            public void setOverdueDays(String overdueDays) {
                this.overdueDays = overdueDays;
            }

            public String getOverdueInstallment() {
                return overdueInstallment;
            }

            public void setOverdueInstallment(String overdueInstallment) {
                this.overdueInstallment = overdueInstallment;
            }

            public String getDefaultRatings() {
                return defaultRatings;
            }

            public void setDefaultRatings(String defaultRatings) {
                this.defaultRatings = defaultRatings;
            }

            public String getHand() {
                return hand;
            }

            public void setHand(String hand) {
                this.hand = hand;
            }

            public String getAccount() {
                return account;
            }

            public void setAccount(String account) {
                this.account = account;
            }
        }

        public static class PhoneDetailsBean implements Serializable{
            /**
             * status : 0
             * phoneStatusText : 未知电话
             * name : 王明献
             * relation : 本人
             * phoneTypeText : 手机
             * phoneNumber : 13655977703
             * remark :
             */

            private int status;
            private String phoneStatusText;
            private String name;
            private String relation;
            private String phoneTypeText;
            private String phoneNumber;
            private String remark;

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getPhoneStatusText() {
                return phoneStatusText;
            }

            public void setPhoneStatusText(String phoneStatusText) {
                this.phoneStatusText = phoneStatusText;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getRelation() {
                return relation;
            }

            public void setRelation(String relation) {
                this.relation = relation;
            }

            public String getPhoneTypeText() {
                return phoneTypeText;
            }

            public void setPhoneTypeText(String phoneTypeText) {
                this.phoneTypeText = phoneTypeText;
            }

            public String getPhoneNumber() {
                return phoneNumber;
            }

            public void setPhoneNumber(String phoneNumber) {
                this.phoneNumber = phoneNumber;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }
        }

        public static class VisitRecordBean implements Serializable {
            /**
             * operateTypeText : 外访申请
             * operateContent : 申请外访,外访目标[fdsa],外访备注[fdsaf]
             * operatorName : admin
             * operateTime : 1545280722000
             * recordGuid : kkkk
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

        public static class UrgeRecordBean implements Serializable{
            /**
             * promiseAmount : 100
             * promiseDate : 1547596800000
             * operateTypeDecoration : 电催
             * operateObjectContent : 13655977703
             * measure : 无
             * operateDate : 1547609877475
             * operator : qzws136
             */

            private int promiseAmount;
            private long promiseDate;
            private String operateTypeDecoration;
            private String operateObjectContent;
            private String measure;
            private long operateDate;
            private String operator;

            public int getPromiseAmount() {
                return promiseAmount;
            }

            public void setPromiseAmount(int promiseAmount) {
                this.promiseAmount = promiseAmount;
            }

            public long getPromiseDate() {
                return promiseDate;
            }

            public void setPromiseDate(long promiseDate) {
                this.promiseDate = promiseDate;
            }

            public String getOperateTypeDecoration() {
                return operateTypeDecoration;
            }

            public void setOperateTypeDecoration(String operateTypeDecoration) {
                this.operateTypeDecoration = operateTypeDecoration;
            }

            public String getOperateObjectContent() {
                return operateObjectContent;
            }

            public void setOperateObjectContent(String operateObjectContent) {
                this.operateObjectContent = operateObjectContent;
            }

            public String getMeasure() {
                return measure;
            }

            public void setMeasure(String measure) {
                this.measure = measure;
            }

            public long getOperateDate() {
                return operateDate;
            }

            public void setOperateDate(long operateDate) {
                this.operateDate = operateDate;
            }

            public String getOperator() {
                return operator;
            }

            public void setOperator(String operator) {
                this.operator = operator;
            }
        }

        public static class DebtorSourceBean {
            /**
             * fileGuid : 2f84a32b0ce74bf18d66e5d07583e59f
             * sourceFileName : new 2.txt
             * fileName : 2f84a32b0ce74bf18d66e5d07583e59f.txt
             * contentTypeName : text/plain
             * contentLength : 2470
             * filePath : D:/UploadFile/DebtorFile/APG-20181206-004-000001/2f84a32b0ce74bf18d66e5d07583e59f.txt
             * fileSize : 2470
             * uploaderId : 7657
             * uploaderName : 施雪婷
             * uploadTime : 1550454028293
             * isDel : false
             */

            private String fileGuid;
            private String sourceFileName;
            private String fileName;
            private String contentTypeName;
            private int contentLength;
            private String filePath;
            private String fileSize;
            private int uploaderId;
            private String uploaderName;
            private long uploadTime;
            private boolean isDel;

            public String getFileGuid() {
                return fileGuid;
            }

            public void setFileGuid(String fileGuid) {
                this.fileGuid = fileGuid;
            }

            public String getSourceFileName() {
                return sourceFileName;
            }

            public void setSourceFileName(String sourceFileName) {
                this.sourceFileName = sourceFileName;
            }

            public String getFileName() {
                return fileName;
            }

            public void setFileName(String fileName) {
                this.fileName = fileName;
            }

            public String getContentTypeName() {
                return contentTypeName;
            }

            public void setContentTypeName(String contentTypeName) {
                this.contentTypeName = contentTypeName;
            }

            public int getContentLength() {
                return contentLength;
            }

            public void setContentLength(int contentLength) {
                this.contentLength = contentLength;
            }

            public String getFilePath() {
                return filePath;
            }

            public void setFilePath(String filePath) {
                this.filePath = filePath;
            }

            public String getFileSize() {
                return fileSize;
            }

            public void setFileSize(String fileSize) {
                this.fileSize = fileSize;
            }

            public int getUploaderId() {
                return uploaderId;
            }

            public void setUploaderId(int uploaderId) {
                this.uploaderId = uploaderId;
            }

            public String getUploaderName() {
                return uploaderName;
            }

            public void setUploaderName(String uploaderName) {
                this.uploaderName = uploaderName;
            }

            public long getUploadTime() {
                return uploadTime;
            }

            public void setUploadTime(long uploadTime) {
                this.uploadTime = uploadTime;
            }

            public boolean isIsDel() {
                return isDel;
            }

            public void setIsDel(boolean isDel) {
                this.isDel = isDel;
            }
        }
    }
}
