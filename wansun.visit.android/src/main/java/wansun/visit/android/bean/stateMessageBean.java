package wansun.visit.android.bean;
/**
 *
 * 服务器返回数据的状态
 * Created by User on 2019/3/5.
 */
public class stateMessageBean {
    /**
     * statusID : 200
     * message : ok
     * dataType : application/json
     * data : 状态已更新为安排外访
     */
    private String statusID;
    private String message;
    private String dataType;
    private String data;

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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
