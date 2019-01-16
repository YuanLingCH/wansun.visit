package wansun.visit.android.bean;


import com.baidu.mapapi.model.LatLng;

/**
 * Created by User on 2019/1/11.
 */

public class searchBean {
    String key;
    String city;
    String district;
    LatLng pt;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

public LatLng getPt() {
        return pt;
    }

    public void setPt(LatLng pt) {
        this.pt = pt;
    }
}
