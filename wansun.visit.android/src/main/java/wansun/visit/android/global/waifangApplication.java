package wansun.visit.android.global;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.media.AudioManager;

import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.tts.client.SpeechSynthesizer;
import com.baidu.tts.client.TtsMode;
import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.tencent.bugly.crashreport.CrashReport;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import wansun.visit.android.greendao.gen.DaoMaster;
import wansun.visit.android.greendao.gen.DaoSession;


/**
 * Created by User on 2019/1/8.
 */
public class waifangApplication extends Application {
    static Context mcontext;
    static   SpeechSynthesizer mSpeechSynthesizer;
    private List<Activity> oList;//用于存放所有启动的Activity的集合
    static   waifangApplication app;
    private DaoMaster.DevOpenHelper dbHelper;
    private SQLiteDatabase db;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;
    @Override
    public void onCreate() {
        super.onCreate();
        mcontext=getApplicationContext();
        SDKInitializer.initialize(this);
        //自4.3.0起，百度地图SDK所有接口均支持百度坐标和国测局坐标，用此方法设置您使用的坐标类型.
        //包括BD09LL和GCJ02两种坐标，默认是BD09LL坐标。
        SDKInitializer.setCoordType(CoordType.BD09LL);
        mSpeechSynthesizer = SpeechSynthesizer.getInstance();
        app=this;
        oList = new ArrayList<Activity>();
        initDatabass();
        /*
        * 第三个参数为SDK调试模式开关，调试模式的行为特性如下：
        输出详细的Bugly SDK的Log；
        每一条Crash都会被立即上报；
        自定义日志将会在Logcat中输出。
        建议在测试阶段建议设置成true，发布时设置为false。
        *
        * */
        CrashReport.initCrashReport(getApplicationContext(), "18ca237fe3", true);


    }

    private void initDatabass() {
        //这里之后会修改，关于升级数据库
        dbHelper = new DaoMaster.DevOpenHelper(this, "visit-db", null);
        db = dbHelper.getWritableDatabase();
        mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();
    }


    public DaoSession getSession(){
        return mDaoSession;
    }
    public SQLiteDatabase getDb(){
        return db;
    }
    /**
     * 初始化语音
     * @return
     */
    public static SpeechSynthesizer getmSpeechSynthesizer() {
        new Thread(){
            @Override
            public void run() {
                super.run();
                mSpeechSynthesizer.setContext(mcontext); // this 是Context的之类，如Activity
                mSpeechSynthesizer.setAppId("15391877");/*这里只是为了让Demo运行使用的APPID,请替换成自己的id。*/
                mSpeechSynthesizer.setApiKey("H8hwKCV0LzHK6wXuOp28D05q4oozGAfG","cmLBTFqVFd0IFgdKcpS6AiivnyPhXClY");/*这里只是为了让Demo正常运行使用APIKey,请替换成自己的APIKey*/
                mSpeechSynthesizer.auth(TtsMode.MIX);  // 纯在线
                mSpeechSynthesizer.initTts(TtsMode.MIX); // 初始化离在线混合模式，如果只需要在线合成功能，使用 TtsMode.ONLINE
                mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_MIX_MODE, SpeechSynthesizer.MIX_MODE_DEFAULT);
                mSpeechSynthesizer.setAudioStreamType(AudioManager.MODE_IN_CALL);
            }
        }.start();

        return mSpeechSynthesizer;
    }

    public  static    Context getContext(){
        return mcontext;
    }
    public static waifangApplication getInstence() {
        return app;
    }

    OkHttpClient okHttpClient;
    ClearableCookieJar cookieJar;
    public   OkHttpClient getClient() {
        if (cookieJar==null){
            cookieJar =
                    new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(waifangApplication.getContext()));
        }
        if (okHttpClient==null){
            okHttpClient = new OkHttpClient.Builder()
                    .cookieJar(cookieJar)
                    .build();
        }
        return okHttpClient;
    }

    /**
     * 添加Activity
     */
    public void addActivity_(Activity activity) {
            // 判断当前集合中不存在该Activity
        if (!oList.contains(activity)) {
            oList.add(activity);//把当前Activity添加到集合中
        }
    }

    /**
     * 销毁单个Activity
     */
    public void removeActivity_(Activity activity) {
           //判断当前集合中存在该Activity
        if (oList.contains(activity)) {
            oList.remove(activity);//从集合中移除
            activity.finish();//销毁当前Activity
        }
    }

    /**
     * 销毁所有的Activity
     */
    public void removeALLActivity_() {
        //通过循环，把集合中的所有Activity销毁
        for (Activity activity : oList) {
            activity.finish();
        }
    }
}
