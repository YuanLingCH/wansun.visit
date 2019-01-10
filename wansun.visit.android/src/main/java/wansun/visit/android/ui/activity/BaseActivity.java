package wansun.visit.android.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by User on 2019/1/8.
 * 基类的封装
 */

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initView();
        initEvent();
        initData();
    }
    protected  abstract  int getLayoutId();   //加载布局文件
    protected  abstract void  initView();
    protected  abstract void  initEvent();
    protected  abstract void  initData();
}
