package soundrecorderutils;

import android.app.Service;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimerTask;

import wansun.visit.android.db.fileInfo;
import wansun.visit.android.global.waifangApplication;
import wansun.visit.android.greendao.gen.fileInfoDao;
import wansun.visit.android.utils.SharedUtils;
import wansun.visit.android.utils.logUtils;


/**
 * 录音的 Service
 *
 * Created by developerHaoz on 2017/8/12.
 */

public class RecordingService extends Service {
    private fileInfoDao dao;
    private static final String LOG_TAG = "RecordingService";

    private String mFileName = null;
    private String mFilePath = null;

    private MediaRecorder mRecorder = null;

    private long mStartingTimeMillis = 0;
    private long mElapsedMillis = 0;
    private TimerTask mIncrementTimerTask = null;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        dao= waifangApplication.getInstence().getSession().getFileInfoDao();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        startRecording();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        if (mRecorder != null) {
            stopRecording();
        }
        super.onDestroy();
    }

    public void startRecording() {
        setFileNameAndPath();

        mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        mRecorder.setOutputFile(mFilePath);
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
        mRecorder.setAudioChannels(1);
        mRecorder.setAudioSamplingRate(44100);
        mRecorder.setAudioEncodingBitRate(192000);

        try {
            mRecorder.prepare();
            mRecorder.start();
            mStartingTimeMillis = System.currentTimeMillis();
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }
    }

    public void setFileNameAndPath() {
        String appName = getPackageName();
  /*   int count = 0;
        File f;
        String caseCode = SharedUtils.getString("caseCode");
        do {
            count++;
            mFileName = caseCode
                    + "_" + (System.currentTimeMillis()) + ".mp3";
            mFilePath = Environment.getExternalStorageDirectory().getAbsolutePath();
           mFilePath += "/visit/data/" + mFileName;

            f = new File(mFilePath);
        } while (f.exists() && !f.isDirectory());*/

        int count = 0;

        String caseCode = SharedUtils.getString("caseCode");

            count++;
            mFileName = caseCode
                    + "_" + (System.currentTimeMillis()) + ".mp3";
            mFilePath = Environment.getExternalStorageDirectory().getAbsolutePath();
           mFilePath +=  "/"+appName ;
            File dir=new File(mFilePath);
            if (!dir.exists()){
                dir.mkdir();
            }

            File dirone=new File(dir+"/"+"record");
            if (!dirone.exists()){
                dirone.mkdir();
            }
            mFilePath= dirone+"/" +caseCode+"_" +new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + ".mp3";



    }

    public void stopRecording() {
        logUtils.d("录音保存");
        mRecorder.stop();
        mElapsedMillis = (System.currentTimeMillis() - mStartingTimeMillis);
        mRecorder.release();

        getSharedPreferences("sp_name_audio", MODE_PRIVATE)
                .edit()
                .putString("audio_path", mFilePath)
                .putLong("elpased", mElapsedMillis)
                .apply();
        if (mIncrementTimerTask != null) {
            mIncrementTimerTask.cancel();
            mIncrementTimerTask = null;
        }
        String visitGuid = SharedUtils.getString("visitGuid");
        fileInfo info=new fileInfo(null,mFilePath,"4",System.currentTimeMillis(),visitGuid);  //4为录音
        dao.insert(info);
        mRecorder = null;
    }

}
