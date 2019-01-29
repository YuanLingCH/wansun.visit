package wansun.visit.android.global;

import android.app.Application;
import android.content.Context;

import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.tts.client.SpeechSynthesizer;
import com.baidu.tts.client.TtsMode;


/**
 * Created by User on 2019/1/8.
 */

public class waifangApplication extends Application {
   static Context mcontext;
  static   SpeechSynthesizer mSpeechSynthesizer;
    @Override
    public void onCreate() {
        super.onCreate();
        mcontext=getApplicationContext();
        SDKInitializer.initialize(this);
        //自4.3.0起，百度地图SDK所有接口均支持百度坐标和国测局坐标，用此方法设置您使用的坐标类型.
        //包括BD09LL和GCJ02两种坐标，默认是BD09LL坐标。
        SDKInitializer.setCoordType(CoordType.BD09LL);

        mSpeechSynthesizer = SpeechSynthesizer.getInstance();
    }

    /**
     * 初始化语音
     * @return
     */
    public static SpeechSynthesizer getmSpeechSynthesizer() {
        mSpeechSynthesizer.setContext(mcontext); // this 是Context的之类，如Activity

        mSpeechSynthesizer.setAppId("15391877");/*这里只是为了让Demo运行使用的APPID,请替换成自己的id。*/
        mSpeechSynthesizer.setApiKey("H8hwKCV0LzHK6wXuOp28D05q4oozGAfG","cmLBTFqVFd0IFgdKcpS6AiivnyPhXClY");/*这里只是为了让Demo正常运行使用APIKey,请替换成自己的APIKey*/
        mSpeechSynthesizer.auth(TtsMode.ONLINE);  // 纯在线
        mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_SPEAKER, "0"); // 设置发声的人声音，在线生效
        mSpeechSynthesizer.initTts(TtsMode.MIX); // 初始化离在线混合模式，如果只需要在线合成功能，使用 TtsMode.ONLINE
        return mSpeechSynthesizer;
    }

    public  static    Context getContext(){
        return mcontext;
    }

}
