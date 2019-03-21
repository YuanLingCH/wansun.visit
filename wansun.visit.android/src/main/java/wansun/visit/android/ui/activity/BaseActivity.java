package wansun.visit.android.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import wansun.visit.android.global.waifangApplication;

/**
 * Created by User on 2019/1/8.
 * 基类的封装
 */

public abstract class BaseActivity extends AppCompatActivity {
    waifangApplication application;
    private BaseActivity oContext;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initLise();
        setContentView(getLayoutId());
        initView();
        initEvent();
        initData();
        if (application == null) {
            // 得到Application对象
            application = (waifangApplication) getApplication();
        }
        oContext = this;// 把当前的上下文对象赋值给BaseActivity
        addActivity();// 调用添加方法

    }
    protected  abstract  int getLayoutId();   //加载布局文件
    protected  abstract void  initView();
    protected  abstract void  initEvent();
    protected  abstract void  initData();
    protected  abstract void  initLise();
    // 添加Activity方法
    public void addActivity() {
        application.addActivity_(oContext);// 调用myApplication的添加Activity方法
    }
    //销毁当个Activity方法
    public void removeActivity() {
        application.removeActivity_(oContext);// 调用myApplication的销毁单个Activity方法
    }
    //销毁所有Activity方法
    public  void removeALLActivity() {
        application.removeALLActivity_();// 调用myApplication的销毁所有Activity方法
    }


}
