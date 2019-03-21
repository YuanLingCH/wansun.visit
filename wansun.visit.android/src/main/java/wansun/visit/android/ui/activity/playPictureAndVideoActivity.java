package wansun.visit.android.ui.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;
import wansun.visit.android.R;
import wansun.visit.android.utils.logUtils;

/**
 *
 * 展示图片和播放视频界面
 * Created by User on 2019/3/18.
 */

public class playPictureAndVideoActivity  extends  BaseActivity{
    ImageView iv_visit_back;
    TextView tv_visit_tobar;
    String path;
    JCVideoPlayerStandard videoplayer;
    ImageView iv_play;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_play;
    }

    @Override
    protected void initView() {
        iv_visit_back= (ImageView) findViewById(R.id.iv_visit_back);
        tv_visit_tobar= (TextView) findViewById(R.id.tv_visit_tobar);
        videoplayer= (JCVideoPlayerStandard) findViewById(R.id.videoplayer);
        iv_play= (ImageView) findViewById(R.id.iv_play);
        tv_visit_tobar.setText("显示");
        path = getIntent().getStringExtra("path");
        logUtils.d("传过来的"+path);

    }

    @Override
    protected void initEvent() {
        iv_visit_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.in_from_left,R.anim.out_to_right);
            }
        });

    }

    @Override
    protected void initData() {
        if (!TextUtils.isEmpty(path)){
            if (path.endsWith(".mp4")){
                videoplayer.setVisibility(View.VISIBLE);
                videoplayer.setUp(path, JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, "");
            }else {
                iv_play.setVisibility(View.VISIBLE);
                Bitmap bitmap = BitmapFactory.decodeFile(path);
                iv_play.setImageBitmap(bitmap);
            }

        }

    }

    @Override
    protected void initLise() {

    }


    @Override
    public void onBackPressed() {

        if (videoplayer.backPress()) {
            return;
        }
        super.onBackPressed();

    }

    @Override
    protected void onPause() {
        super.onPause();
        videoplayer.releaseAllVideos();
    }
}
