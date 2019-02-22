package wansun.visit.android.bean;

import java.io.Serializable;

/**
 * Created by User on 2019/2/20.
 */

public class casePhoneBean implements Serializable{

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
