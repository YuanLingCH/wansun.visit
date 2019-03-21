package wansun.visit.android.bean;

import java.util.List;

/**
 * Created by User on 2019/3/12.
 */

public class caseFileQueryBean  {
    /**
     * statusID : 200
     * message : ok
     * dataType : application/json
     * data : [{"fileGuid":"8ac1d256-2fe0-4daa-b217-fae3fb8e7224","sourceFileName":"APG-20181206-004-00000120190311_153235.mp4","fileName":"aeaee0c4-8a45-455a-8135-881bcb8d43cc.mp4","filePath":"http://localhost:8080/files/APG/APG-20181206-004-000001/aeaee0c4-8a45-455a-8135-881bcb8d43cc.mp4","fileSize":1878104,"uploaderId":1,"uploadTime":1552289573205,"isDel":false,"linkId":"aaa5","linkType":2,"linkTypeText":"外访单附件"},{"fileGuid":"af61c0be-e888-4a08-9c17-d31fa81dd312","sourceFileName":"APG-20181206-004-00000120190311_153954.mp4","fileName":"091dc694-9b63-48ee-ac8f-3d0a8f84aeae.mp4","filePath":"http://localhost:8080/files/APG/APG-20181206-004-000001/091dc694-9b63-48ee-ac8f-3d0a8f84aeae.mp4","fileSize":16086988,"uploaderId":1,"uploadTime":1552290194076,"isDel":false,"linkId":"aaa5","linkType":2,"linkTypeText":"外访单附件"},{"fileGuid":"934b9c6f-0d88-4183-b6be-d8370d1eb01e","sourceFileName":"APG-20181206-004-00000120190311_162748.mp4","fileName":"3e057d19-9df2-4cce-a4fc-d9d9afa9b9f1.mp4","filePath":"http://localhost:8080/files/APG/APG-20181206-004-000001/3e057d19-9df2-4cce-a4fc-d9d9afa9b9f1.mp4","fileSize":1675645,"uploaderId":1,"uploadTime":1552292891692,"isDel":false,"linkId":"aaa5","linkType":2,"linkTypeText":"外访单附件"},{"fileGuid":"ace27467-f86a-4d2f-b982-99418619854a","sourceFileName":"APG-20181206-004-00000120190311_163904.mp4","fileName":"7e0a2b59-2d32-461b-bc8b-03f414823400.mp4","filePath":"http://localhost:8080/files/APG/APG-20181206-004-000001/7e0a2b59-2d32-461b-bc8b-03f414823400.mp4","fileSize":965476,"uploaderId":1,"uploadTime":1552293554653,"isDel":false,"linkId":"aaa5","linkType":2,"linkTypeText":"外访单附件"},{"fileGuid":"035e35a1-0596-4aac-b0bf-89e695518070","sourceFileName":"APG-20181206-004-00000120190311_163945.mp4","fileName":"37291dc2-9a2f-4d3b-8d34-cbe0e78cca29.mp4","filePath":"http://localhost:8080/files/APG/APG-20181206-004-000001/37291dc2-9a2f-4d3b-8d34-cbe0e78cca29.mp4","fileSize":564687,"uploaderId":1,"uploadTime":1552293594434,"isDel":false,"linkId":"aaa5","linkType":2,"linkTypeText":"外访单附件"},{"fileGuid":"e9193c85-cec2-43f4-93af-651f4848fab1","sourceFileName":"1552305718663.jpg","fileName":"51dbf85c-5da9-4bd2-bae0-781c148bc616.jpg","filePath":"http://localhost:8080/files/APG/APG-20181206-004-000001/51dbf85c-5da9-4bd2-bae0-781c148bc616.jpg","fileSize":2454260,"uploaderId":1,"uploadTime":1552305726987,"isDel":false,"linkId":"aaa5","linkType":2,"linkTypeText":"外访单附件"},{"fileGuid":"d4de6df7-09f0-4ae3-99ed-f4d5dc7c24d6","sourceFileName":"1552351690700.jpg","fileName":"ecdb3077-248b-4744-88a7-15329d417a4e.jpg","filePath":"http://localhost:8080/files/APG/APG-20181206-004-000001/ecdb3077-248b-4744-88a7-15329d417a4e.jpg","fileSize":2277452,"uploaderId":1,"uploadTime":1552351706739,"isDel":false,"linkId":"aaa5","linkType":2,"linkTypeText":"外访单附件"},{"fileGuid":"d8c9e300-8d69-49f5-b91e-f9d5f4a6db74","sourceFileName":"1552371572481.jpg","fileName":"d3131909-679f-4f90-a0fc-d6a982ae2ed3.jpg","filePath":"http://localhost:8080/files/APG/APG-20181206-004-000001/d3131909-679f-4f90-a0fc-d6a982ae2ed3.jpg","fileSize":2345753,"uploaderId":1,"uploadTime":1552371580073,"isDel":false,"linkId":"aaa5","linkType":2,"linkTypeText":"外访单附件"}]
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
         * fileGuid : 8ac1d256-2fe0-4daa-b217-fae3fb8e7224
         * sourceFileName : APG-20181206-004-00000120190311_153235.mp4
         * fileName : aeaee0c4-8a45-455a-8135-881bcb8d43cc.mp4
         * filePath : http://localhost:8080/files/APG/APG-20181206-004-000001/aeaee0c4-8a45-455a-8135-881bcb8d43cc.mp4
         * fileSize : 1878104
         * uploaderId : 1
         * uploadTime : 1552289573205
         * isDel : false
         * linkId : aaa5
         * linkType : 2
         * linkTypeText : 外访单附件
         */

        private String fileGuid;
        private String sourceFileName;
        private String fileName;
        private String filePath;
        private int fileSize;
        private int uploaderId;
        private long uploadTime;
        private boolean isDel;
        private String linkId;
        private int linkType;
        private String linkTypeText;

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

        public String getFilePath() {
            return filePath;
        }

        public void setFilePath(String filePath) {
            this.filePath = filePath;
        }

        public int getFileSize() {
            return fileSize;
        }

        public void setFileSize(int fileSize) {
            this.fileSize = fileSize;
        }

        public int getUploaderId() {
            return uploaderId;
        }

        public void setUploaderId(int uploaderId) {
            this.uploaderId = uploaderId;
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

        public String getLinkId() {
            return linkId;
        }

        public void setLinkId(String linkId) {
            this.linkId = linkId;
        }

        public int getLinkType() {
            return linkType;
        }

        public void setLinkType(int linkType) {
            this.linkType = linkType;
        }

        public String getLinkTypeText() {
            return linkTypeText;
        }

        public void setLinkTypeText(String linkTypeText) {
            this.linkTypeText = linkTypeText;
        }
    }
}
