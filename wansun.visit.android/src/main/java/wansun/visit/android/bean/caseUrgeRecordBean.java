package wansun.visit.android.bean;

import java.util.List;

/**
 * Created by User on 2019/3/4.
 */

public class caseUrgeRecordBean {

    /**
     * statusID : 200
     * message : ok
     * dataType : application/json
     * data : [{"promiseAmount":100,"promiseDate":0,"operateTypeDecoration":"电催","operateObjectContent":"13655977703","measure":"无","operateDate":1547609877475,"operator":"qzws136"},{"promiseAmount":100,"promiseDate":0,"operateTypeDecoration":"电催","operateObjectContent":"13655977703","measure":"无","operateDate":1548143562480,"operator":"qzws136"},{"promiseAmount":100,"promiseDate":0,"operateTypeDecoration":"电催","operateObjectContent":"13655977703","measure":"无","operateDate":1548143562480,"operator":"qzws136"},{"promiseAmount":100,"promiseDate":0,"operateTypeDecoration":"电催","operateObjectContent":"5655654","measure":"无","operateDate":1551342704771,"operator":"qzws136"},{"promiseAmount":100,"promiseDate":0,"operateTypeDecoration":"电催","operateObjectContent":"541471745875","measure":"无","operateDate":1551410899403,"operator":"qzws136"},{"promiseAmount":100,"promiseDate":0,"operateTypeDecoration":"电催","operateObjectContent":"541471745875","measure":"无","operateDate":1551410899403,"operator":"qzws136"},{"promiseAmount":100,"promiseDate":1551410963923,"operateTypeDecoration":"电催","operateObjectContent":"541471745875","measure":"无","operateDate":1551410963922,"operator":"qzws136"},{"promiseAmount":100,"promiseDate":1551410963923,"operateTypeDecoration":"电催","operateObjectContent":"541471745875","measure":"无","operateDate":1551410963922,"operator":"qzws136"}]
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
         * promiseAmount : 100
         * promiseDate : 0
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
}
