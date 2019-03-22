package wansun.visit.android.bean;

import java.io.Serializable;

/**
 * 点击百度地图展示的 窗口信息
 * Created by User on 2019/3/20.
 */

public class mapInfoBean implements Serializable{
    public  String debtor;
    public String  visitState;
    public  String customer;
    public  String addressDeail;
    public  String caseCodeNumber;
    public  String batchNumber;
    public String getDebtor() {
        return debtor;
    }

    public void setDebtor(String debtor) {
        this.debtor = debtor;
    }

    public String getVisitState() {
        return visitState;
    }

    public void setVisitState(String visitState) {
        this.visitState = visitState;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getAddressDeail() {
        return addressDeail;
    }

    public void setAddressDeail(String addressDeail) {
        this.addressDeail = addressDeail;
    }

    public String getCaseCodeNumber() {
        return caseCodeNumber;
    }

    public void setCaseCodeNumber(String caseCodeNumber) {
        this.caseCodeNumber = caseCodeNumber;
    }

    public String getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber;
    }

    public mapInfoBean(String debtor, String visitState, String customer, String addressDeail, String caseCodeNumber, String batchNumber) {
        this.debtor = debtor;
        this.visitState = visitState;
        this.customer = customer;
        this.addressDeail = addressDeail;
        this.caseCodeNumber = caseCodeNumber;
        this.batchNumber = batchNumber;
    }
}
