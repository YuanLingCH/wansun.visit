package wansun.visit.android.bean;

/**
 * Created by User on 2019/3/25.
 */

public class saveLocationMessageBean {

    /**
     * statusID : 200
     * message : ok
     * dataType : application/json
     * data : {"caseCode":"APG-20190316-005-000005","visitGuid":"3fabb754-7245-4403-9262-4d1b9088f2dd","visitorId":"41","positionGuid":"429bb76d-19cd-4b39-a972-9a5a1f5848f5","deviceNumber":"867951032064889","status":0,"vehicle":"","startTime":1553499422343,"endTime":0,"timeOfUse":0,"startAddress":"当前位置：广东省深圳市在展滔科技大厦-C座附近","endAddress":"","longitude":114.043525,"latitude":22.645725,"positioningTime":1553499422343}
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
         * caseCode : APG-20190316-005-000005
         * visitGuid : 3fabb754-7245-4403-9262-4d1b9088f2dd
         * visitorId : 41
         * positionGuid : 429bb76d-19cd-4b39-a972-9a5a1f5848f5
         * deviceNumber : 867951032064889
         * status : 0
         * vehicle :
         * startTime : 1553499422343
         * endTime : 0
         * timeOfUse : 0
         * startAddress : 当前位置：广东省深圳市在展滔科技大厦-C座附近
         * endAddress :
         * longitude : 114.043525
         * latitude : 22.645725
         * positioningTime : 1553499422343
         */

        private String caseCode;
        private String visitGuid;
        private String visitorId;
        private String positionGuid;
        private String deviceNumber;
        private int status;
        private String vehicle;
        private long startTime;
        private int endTime;
        private int timeOfUse;
        private String startAddress;
        private String endAddress;
        private double longitude;
        private double latitude;
        private long positioningTime;

        public String getCaseCode() {
            return caseCode;
        }

        public void setCaseCode(String caseCode) {
            this.caseCode = caseCode;
        }

        public String getVisitGuid() {
            return visitGuid;
        }

        public void setVisitGuid(String visitGuid) {
            this.visitGuid = visitGuid;
        }

        public String getVisitorId() {
            return visitorId;
        }

        public void setVisitorId(String visitorId) {
            this.visitorId = visitorId;
        }

        public String getPositionGuid() {
            return positionGuid;
        }

        public void setPositionGuid(String positionGuid) {
            this.positionGuid = positionGuid;
        }

        public String getDeviceNumber() {
            return deviceNumber;
        }

        public void setDeviceNumber(String deviceNumber) {
            this.deviceNumber = deviceNumber;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getVehicle() {
            return vehicle;
        }

        public void setVehicle(String vehicle) {
            this.vehicle = vehicle;
        }

        public long getStartTime() {
            return startTime;
        }

        public void setStartTime(long startTime) {
            this.startTime = startTime;
        }

        public int getEndTime() {
            return endTime;
        }

        public void setEndTime(int endTime) {
            this.endTime = endTime;
        }

        public int getTimeOfUse() {
            return timeOfUse;
        }

        public void setTimeOfUse(int timeOfUse) {
            this.timeOfUse = timeOfUse;
        }

        public String getStartAddress() {
            return startAddress;
        }

        public void setStartAddress(String startAddress) {
            this.startAddress = startAddress;
        }

        public String getEndAddress() {
            return endAddress;
        }

        public void setEndAddress(String endAddress) {
            this.endAddress = endAddress;
        }

        public double getLongitude() {
            return longitude;
        }

        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }

        public double getLatitude() {
            return latitude;
        }

        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }

        public long getPositioningTime() {
            return positioningTime;
        }

        public void setPositioningTime(long positioningTime) {
            this.positioningTime = positioningTime;
        }
    }
}
