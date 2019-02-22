package wansun.visit.android.utils;

/**
 * 得到*******的字符串
 * Created by User on 2019/2/19.
 */

public class starUtils {
    public static  String getStarString(String text,int start,int end){
        return text.substring(0,start)+"******"+text.substring(end);
    }

}
