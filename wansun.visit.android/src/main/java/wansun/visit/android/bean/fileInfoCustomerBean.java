package wansun.visit.android.bean;

/**
 * Created by User on 2019/3/14.
 */

public class fileInfoCustomerBean {
    private Long id;
    public  String path;
    public  String type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
