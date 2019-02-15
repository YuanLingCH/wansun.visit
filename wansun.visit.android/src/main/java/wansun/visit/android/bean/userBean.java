package wansun.visit.android.bean;

import java.io.Serializable;

/**
 * Created by User on 2019/2/13.
 */

public class userBean implements Serializable {
    public String username;
    public  String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
