package wansun.visit.android.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by User on 2019/2/20.
 */

public class casePhoneBean implements Serializable{


    /**
     * statusID : 200
     * message : ok
     * dataType : application/json
     * data : [{"name":"蔡亚川","phoneStatusText":"有效电话","status":1,"relation":"其他","phoneTypeText":"家庭","phoneNumber":"--001112","remark":""},{"name":"蔡亚川","phoneStatusText":"有效电话","status":1,"relation":"其他","phoneTypeText":"家庭","phoneNumber":"--00","remark":""},{"name":"蔡亚川","phoneStatusText":"未知电话","status":0,"relation":"其他","phoneTypeText":"家庭","phoneNumber":"--","remark":""},{"name":"蔡亚川","phoneStatusText":"未知电话","status":0,"relation":"其他","phoneTypeText":"手机","phoneNumber":"13459577717","remark":""},{"name":"蔡亚川","phoneStatusText":"未知电话","status":0,"relation":"其他","phoneTypeText":"家庭","phoneNumber":"--1234566","remark":""},{"name":"蔡亚川","phoneStatusText":"未知电话","status":0,"relation":"其他","phoneTypeText":"手机","phoneNumber":"13459577717***","remark":""},{"name":"陈博","phoneStatusText":"有效","status":1,"relation":"其他","phoneTypeText":"家庭","phoneNumber":"--","remark":""},{"name":"陈博","phoneStatusText":"有效","status":1,"relation":"其他","phoneTypeText":"手机","phoneNumber":"18953040606","remark":""},{"name":"陈博","phoneStatusText":"未知电话","status":0,"relation":"其他","phoneTypeText":"家庭","phoneNumber":"-332225555-","remark":""},{"name":"dvsfb","status":0,"relation":"夫妻","phoneTypeText":"手机","phoneNumber":"4656145145"},{"name":"dvsfb454445","phoneStatusText":"有效电话","status":1,"relation":"夫妻","phoneTypeText":"手机","phoneNumber":"465614514544"},{"name":"王明献","phoneStatusText":"有效","status":1,"relation":"本人","phoneTypeText":"手机","phoneNumber":"13655977703","remark":""},{"name":"王明献","phoneStatusText":"未知电话","status":0,"relation":"本人","phoneTypeText":"单位","phoneNumber":"0595-88712782","remark":""},{"name":"王明献","phoneStatusText":"未知","status":0,"relation":"本人","phoneTypeText":"家庭","phoneNumber":"0595-0000112","remark":""},{"name":"王明献","phoneStatusText":"未知电话","status":0,"relation":"本人","phoneTypeText":"手机","phoneNumber":"13655977703###","remark":""},{"name":"王明献","phoneStatusText":"未知电话","status":0,"relation":"本人","phoneTypeText":"家庭","phoneNumber":"0595-0000","remark":""},{"name":"王明献","phoneStatusText":"未知电话","status":0,"relation":"本人","phoneTypeText":"手机","phoneNumber":"13655977703###000111","remark":""},{"name":"王明献","phoneStatusText":"未知电话","status":0,"relation":"本人","phoneTypeText":"单位","phoneNumber":"0595-88712782###00","remark":""}]
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
         * name : 蔡亚川
         * phoneStatusText : 有效电话
         * status : 1
         * relation : 其他
         * phoneTypeText : 家庭
         * phoneNumber : --001112
         * remark :
         */

        private String name;
        private String phoneStatusText;
        private int status;
        private String relation;
        private String phoneTypeText;
        private String phoneNumber;
        private String remark;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhoneStatusText() {
            return phoneStatusText;
        }

        public void setPhoneStatusText(String phoneStatusText) {
            this.phoneStatusText = phoneStatusText;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
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
}
