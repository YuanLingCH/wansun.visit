package wansun.visit.android.bean;

import java.util.List;

/**
 * Created by User on 2019/3/5.
 */

public class caseCardMessageBean {

    /**
     * statusID : 200
     * message : ok
     * dataType : application/json
     * data : [{"cardNo":"6230580000137375742","caseAmount":5164.23,"backAmount":0,"arrears":5164.23,"overdueDays":"20","overdueInstallment":"","defaultRatings":"","hand":"","account":""}]
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
         * cardNo : 6230580000137375742
         * caseAmount : 5164.23
         * backAmount : 0
         * arrears : 5164.23
         * overdueDays : 20
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
}
