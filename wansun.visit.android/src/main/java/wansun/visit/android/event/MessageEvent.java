package wansun.visit.android.event;

/**
 * Created by User on 2019/3/12.
 */

public class MessageEvent {
   public String  filePath;
    public  String fileType;

    public MessageEvent(String filePath, String fileType) {
        this.filePath = filePath;
        this.fileType = fileType;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }
}
