package wansun.visit.android.bean;

/**
 * Created by User on 2019/3/21.
 */

public class appUpdataBean {

    /**
     * statusID : 200
     * message : ok
     * dataType : application/json
     * data : {"versionCode":2,"versionName":"1.2.0","versionFileUrl":"http://192.168.166.133:8080/files/versionUpdate/wansun.visit.android_1.2.0_2019-03-21_release.apk"}
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
         * versionCode : 2
         * versionName : 1.2.0
         * versionFileUrl : http://192.168.166.133:8080/files/versionUpdate/wansun.visit.android_1.2.0_2019-03-21_release.apk
         */

        private int versionCode;
        private String versionName;
        private String versionFileUrl;

        public int getVersionCode() {
            return versionCode;
        }

        public void setVersionCode(int versionCode) {
            this.versionCode = versionCode;
        }

        public String getVersionName() {
            return versionName;
        }

        public void setVersionName(String versionName) {
            this.versionName = versionName;
        }

        public String getVersionFileUrl() {
            return versionFileUrl;
        }

        public void setVersionFileUrl(String versionFileUrl) {
            this.versionFileUrl = versionFileUrl;
        }
    }
}
