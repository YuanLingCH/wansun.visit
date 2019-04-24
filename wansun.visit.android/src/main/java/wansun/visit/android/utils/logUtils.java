package wansun.visit.android.utils;
import android.util.Log;

/**
 *
 * log日志的封装
 * Created by User on 2019/2/12.
 */
public class logUtils {
 public static boolean isShow = false; // false 表示上线模式
    // public static boolean isShow = true;  // true 表示开发模式
          public  static  String tag= "TAG";
        //对应级别为verbose
        public static void v( String msg) {
            if (isShow == true) {
                Log.v(tag, msg);
            }
        }
        //对应级别为debug
        public static void d( String msg) {
            if (isShow == true) {
                Log.d(tag, msg);
            }
        }
        //对应级别为info
        public static void i( String msg) {
            if (isShow == true) {
                Log.i(tag, msg);
            }
        }
        //对应级别为warn
        public static void w( String msg) {
            if (isShow == true) {
                Log.w(tag, msg);
            }
        }
        //对应级别为error
        public static void e( String msg) {
            if (isShow == true) {
                Log.e(tag, msg);
            }
        }
    }




