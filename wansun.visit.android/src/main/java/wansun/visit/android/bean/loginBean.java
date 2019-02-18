package wansun.visit.android.bean;

/**
 * Created by User on 2019/2/15.
 */

public class loginBean {

    /**
     * statusID : 1001
     * message : 成功！
     * dataType : application/json
     * data : {"id":1,"userName":"admin","passWord":null,"email":null,"name":null,"phone":null,"tell":null,"userType":null,"userPID":null,"passwordQuestion":null,"passwordAnswer":null,"providerUserKey":null,"createUserId":null,"createTime":null,"updateUserId":null,"updateTime":null,"registrationTime":null,"tokenCode":"4ea00807ceb04294b0262d95798ac716","authorizationCode":null,"del":null,"approved":null}
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
         * id : 1
         * userName : admin
         * passWord : null
         * email : null
         * name : null
         * phone : null
         * tell : null
         * userType : null
         * userPID : null
         * passwordQuestion : null
         * passwordAnswer : null
         * providerUserKey : null
         * createUserId : null
         * createTime : null
         * updateUserId : null
         * updateTime : null
         * registrationTime : null
         * tokenCode : 4ea00807ceb04294b0262d95798ac716
         * authorizationCode : null
         * del : null
         * approved : null
         */

        private int id;
        private String userName;
        private Object passWord;
        private Object email;
        private Object name;
        private Object phone;
        private Object tell;
        private Object userType;
        private Object userPID;
        private Object passwordQuestion;
        private Object passwordAnswer;
        private Object providerUserKey;
        private Object createUserId;
        private Object createTime;
        private Object updateUserId;
        private Object updateTime;
        private Object registrationTime;
        private String tokenCode;
        private Object authorizationCode;
        private Object del;
        private Object approved;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public Object getPassWord() {
            return passWord;
        }

        public void setPassWord(Object passWord) {
            this.passWord = passWord;
        }

        public Object getEmail() {
            return email;
        }

        public void setEmail(Object email) {
            this.email = email;
        }

        public Object getName() {
            return name;
        }

        public void setName(Object name) {
            this.name = name;
        }

        public Object getPhone() {
            return phone;
        }

        public void setPhone(Object phone) {
            this.phone = phone;
        }

        public Object getTell() {
            return tell;
        }

        public void setTell(Object tell) {
            this.tell = tell;
        }

        public Object getUserType() {
            return userType;
        }

        public void setUserType(Object userType) {
            this.userType = userType;
        }

        public Object getUserPID() {
            return userPID;
        }

        public void setUserPID(Object userPID) {
            this.userPID = userPID;
        }

        public Object getPasswordQuestion() {
            return passwordQuestion;
        }

        public void setPasswordQuestion(Object passwordQuestion) {
            this.passwordQuestion = passwordQuestion;
        }

        public Object getPasswordAnswer() {
            return passwordAnswer;
        }

        public void setPasswordAnswer(Object passwordAnswer) {
            this.passwordAnswer = passwordAnswer;
        }

        public Object getProviderUserKey() {
            return providerUserKey;
        }

        public void setProviderUserKey(Object providerUserKey) {
            this.providerUserKey = providerUserKey;
        }

        public Object getCreateUserId() {
            return createUserId;
        }

        public void setCreateUserId(Object createUserId) {
            this.createUserId = createUserId;
        }

        public Object getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Object createTime) {
            this.createTime = createTime;
        }

        public Object getUpdateUserId() {
            return updateUserId;
        }

        public void setUpdateUserId(Object updateUserId) {
            this.updateUserId = updateUserId;
        }

        public Object getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(Object updateTime) {
            this.updateTime = updateTime;
        }

        public Object getRegistrationTime() {
            return registrationTime;
        }

        public void setRegistrationTime(Object registrationTime) {
            this.registrationTime = registrationTime;
        }

        public String getTokenCode() {
            return tokenCode;
        }

        public void setTokenCode(String tokenCode) {
            this.tokenCode = tokenCode;
        }

        public Object getAuthorizationCode() {
            return authorizationCode;
        }

        public void setAuthorizationCode(Object authorizationCode) {
            this.authorizationCode = authorizationCode;
        }

        public Object getDel() {
            return del;
        }

        public void setDel(Object del) {
            this.del = del;
        }

        public Object getApproved() {
            return approved;
        }

        public void setApproved(Object approved) {
            this.approved = approved;
        }
    }
}
