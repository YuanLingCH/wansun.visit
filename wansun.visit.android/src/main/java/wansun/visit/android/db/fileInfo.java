package wansun.visit.android.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 *
 * Created by User on 2019/3/13.
 */
@Entity
public class fileInfo {
    @Id
    private Long id;
    public  String path;
    public  String type;
    public long time;
    public  String batch;
    public String getType() {
        return this.type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getPath() {
        return this.path;
    }
    public void setPath(String path) {
        this.path = path;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public long getTime() {
        return this.time;
    }
    public void setTime(long time) {
        this.time = time;
    }
    public String getBatch() {
        return this.batch;
    }
    public void setBatch(String batch) {
        this.batch = batch;
    }

    @Generated(hash = 1509027235)
    public fileInfo(Long id, String path, String type, long time, String batch) {
        this.id = id;
        this.path = path;
        this.type = type;
        this.time = time;
        this.batch = batch;
    }
    @Generated(hash = 22633021)
    public fileInfo() {
    }

}
