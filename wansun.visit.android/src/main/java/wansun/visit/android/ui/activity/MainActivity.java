package wansun.visit.android.ui.activity;


import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapView;

import wansun.visit.android.R;

/**
 * 主页就是百度地图界面
 */
public class MainActivity extends BaseActivity {
MapView mMapView;
    private BaiduMap map;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        mMapView= (MapView) findViewById(R.id.map);

    }

    @Override
    protected void initEvent() {
        map = mMapView.getMap();
        //显示卫星图层
        map.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
    }

    @Override
    protected void initData() {

    }


    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时必须调用mMapView. onResume ()
        mMapView.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时必须调用mMapView. onPause ()
        mMapView.onPause();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时必须调用mMapView.onDestroy()
        mMapView.onDestroy();
    }
}
