package wansun.visit.android.ui.activity;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.Address;
import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.BDNotifyListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.bikenavi.BikeNavigateHelper;
import com.baidu.mapapi.bikenavi.adapter.IBEngineInitListener;
import com.baidu.mapapi.bikenavi.adapter.IBRoutePlanListener;
import com.baidu.mapapi.bikenavi.model.BikeRoutePlanError;
import com.baidu.mapapi.bikenavi.params.BikeNaviLaunchParam;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.Projection;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.baidu.mapapi.search.sug.OnGetSuggestionResultListener;
import com.baidu.mapapi.search.sug.SuggestionResult;
import com.baidu.mapapi.search.sug.SuggestionSearch;
import com.baidu.mapapi.search.sug.SuggestionSearchOption;
import com.baidu.mapapi.utils.DistanceUtil;
import com.baidu.mapapi.walknavi.WalkNavigateHelper;
import com.baidu.mapapi.walknavi.adapter.IWEngineInitListener;
import com.baidu.mapapi.walknavi.adapter.IWRoutePlanListener;
import com.baidu.mapapi.walknavi.model.WalkRoutePlanError;
import com.baidu.mapapi.walknavi.params.WalkNaviLaunchParam;
import com.baidu.navisdk.adapter.BaiduNaviManagerFactory;
import com.baidu.navisdk.adapter.IBaiduNaviManager;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import bikenavi_demo.BNaviGuideActivity;
import bikenavi_demo.BNaviMainActivity;
import bikenavi_demo.WNaviGuideActivity;
import wansun.visit.android.R;
import wansun.visit.android.adapter.searchAdapter;
import wansun.visit.android.bean.searchBean;
import wansun.visit.android.utils.ToastUtil;

/**
 * 主页就是百度地图界面
 */
public class MainActivity extends BaseActivity implements OnGetGeoCoderResultListener {
    private final static String TAG = BNaviMainActivity.class.getSimpleName();
MapView mMapView;
    private BaiduMap map;
    public LocationClient mLocationClient = null;
    private MyLocationListener myListener = new MyLocationListener();
    boolean isFirstLocate=true;
    TextView tv_location,tv_info_detail,tv_info_distance;
    EditText et_address;
    Button but_search,but_info_cancle,but_info_submit,but_info_gps;
    private SuggestionSearch suggestionSearch;
    List data;
    searchAdapter adapter;
    ListView lv;
    ImageView iv_search,iv_navigation;
    LatLng pt;   // 精度  纬度
    public BDNotifyListener myLocationListener = new MyNotifyLister();
    DrawerLayout drawerLayout;
    private String mSDCardPath = null;
    private static final String APP_FOLDER_NAME = "BNSDKSimpleDemo";
    private static final int authBaseRequestCode = 1;
    LinearLayout ll_gps;
    Button but_gps_walk,but_gps_car,but_gps_bike;
    BikeNaviLaunchParam bikeParam;    // 起点和终点经纬度
    private LatLng startPt,endPt;
    WalkNaviLaunchParam walkParam;

    private BikeNavigateHelper mNaviHelper;

    private static final String[] authBaseArr = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_FINE_LOCATION
    };
    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        judgePermission();
        lv= (ListView) findViewById(R.id.lv);
        mMapView= (MapView) findViewById(R.id.map);
       map = mMapView.getMap();

   /*     if (initDirs()){
        initNavi();    //初始化百度地图导航
        }*/
        //声明LocationClient类
        mLocationClient = new LocationClient(getApplicationContext());
        //注册监听函数
        mLocationClient.registerLocationListener(myListener);
        //注册监听函数  位置提醒
        mLocationClient.registerNotify(myLocationListener);
        tv_location= (TextView) findViewById(R.id.tv_locatio);
        et_address= (EditText) findViewById(R.id.et_address);
        but_search= (Button) findViewById(R.id.but_search);
        iv_search= (ImageView) findViewById(R.id.iv_search);
        drawerLayout= (DrawerLayout) findViewById(R.id.dl_content);
        iv_navigation= (ImageView) findViewById(R.id.iv_navigation);
        ll_gps= (LinearLayout) findViewById(R.id.ll_gps);
        but_gps_walk= (Button) findViewById(R.id.but_gps_walk);
        but_gps_car= (Button) findViewById(R.id.but_gps_car);
        but_gps_bike= (Button) findViewById(R.id.but_gps_bike);

    }

    private boolean hasBasePhoneAuth() {
        PackageManager pm = this.getPackageManager();
        for (String auth : authBaseArr) {
            if (pm.checkPermission(auth, this.getPackageName()) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    /**
     * 初始化导航
     */
    private void initNavi() {
        // 申请权限
        if (android.os.Build.VERSION.SDK_INT >= 23) {
            if (!hasBasePhoneAuth()) {
                this.requestPermissions(authBaseArr, authBaseRequestCode);
                return;
            }
        }
        BaiduNaviManagerFactory.getBaiduNaviManager().init(this,
                mSDCardPath, APP_FOLDER_NAME, new IBaiduNaviManager.INaviInitListener() {

                    @Override
                    public void onAuthResult(int status, String msg) {
                        String result;
                        if (0 == status) {
                            result = "key校验成功!";
                        } else {
                            result = "key校验失败, " + msg;
                        }

                    }

                    @Override
                    public void initStart() {
                        Log.d("TAG","初始化百度地图开始");
                    }

                    @Override
                    public void initSuccess() {
                        Log.d("TAG","初始化百度地图成功");
                        // 初始化tts
                       // initTTS();
                    }

                    @Override
                    public void initFailed() {
                        Log.d("TAG","初始化百度地图失败");
                    }
                });


    }

    private boolean initDirs() {
        mSDCardPath =getSdcardDir();
        if (mSDCardPath == null) {
            return false;
        }
        File f = new File(mSDCardPath, APP_FOLDER_NAME);
        if (!f.exists()) {
            try {
                f.mkdir();
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }


    private String getSdcardDir() {
        if (Environment.getExternalStorageState().equalsIgnoreCase(Environment.MEDIA_MOUNTED)) {
            return Environment.getExternalStorageDirectory().toString();
        }
        return null;
    }

    @Override
    protected void initEvent() {
        map = mMapView.getMap();
        data=new ArrayList();
        //检索的功能
        suggestionSearch = SuggestionSearch.newInstance();
        suggestionSearch.setOnGetSuggestionResultListener(listener);
        but_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String trim = et_address.getText().toString().trim();
                if (!TextUtils.isEmpty(trim)){
                    suggestionSearch.requestSuggestion(new SuggestionSearchOption()
                            .city("北京")
                            .keyword(trim));
                }else {
                    ToastUtil.showToast(MainActivity.this,"请输入地址");
                }

            }
        });
        //  搜索框返回按钮的点击事件
        iv_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (data.size()>0){
                    lv.setVisibility(View.INVISIBLE);   //隐藏lv 数据
                }
            }
        });

        iv_navigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }

    @Override
    public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {

    }

    @Override
    public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {
        String address = reverseGeoCodeResult.getAddress();
        ReverseGeoCodeResult.AddressComponent addressDetail = reverseGeoCodeResult.getAddressDetail();
        // TODO   地理位置反编码   知道经纬度 得到位置




    }

    public class MyNotifyLister extends BDNotifyListener {
        public void onNotify(BDLocation mlocation, float distance){
            //已到达设置监听位置附近  弹出对话框
            ToastUtil.showToast(MainActivity.this,"到达目标位置请注意");
            Log.d("TAG","到达目标位置请注意"+distance);

        }
    }





    OnGetSuggestionResultListener listener = new OnGetSuggestionResultListener() {
        @Override
        public void onGetSuggestionResult(SuggestionResult suggestionResult) {
            //处理sug检索结果
            List<SuggestionResult.SuggestionInfo> allSuggestions = suggestionResult.getAllSuggestions();
            Iterator<SuggestionResult.SuggestionInfo> iterator = allSuggestions.iterator();
            data.clear();
            while (iterator.hasNext()){
                SuggestionResult.SuggestionInfo next = iterator.next();
                searchBean bean=new searchBean();
                bean.setCity(next.getCity());
                bean.setDistrict(next.getDistrict());
                bean.setPt(next.getPt());
                bean.setKey(next.getKey());
                data.add(bean);


            }
            adapter=new searchAdapter(MainActivity.this,data);
            lv.setVisibility(View.VISIBLE);
            lv.setAdapter(adapter);

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    searchBean bean = (searchBean) data.get(position);
                  pt = bean.getPt();
                    Log.d("TAG","点击"+position);

                    addMark(bean );


                }
            });

        }
    };

    /**
     * 给地图添加标记物Mark  并且显示在手机屏幕的中央  可以拖动
     */
    private void addMark(final searchBean bean) {
        if (pt!=null){
            //定义Maker坐标点   根据精度和纬度
            LatLng point = new LatLng(pt.latitude, pt.longitude);
            //构建Marker图标
            BitmapDescriptor bitmap = BitmapDescriptorFactory
                    .fromResource(R.mipmap.location);
//构建MarkerOption，用于在地图上添加Marker
            OverlayOptions option = new MarkerOptions()
                    .position(point)
                    .title("测试数据")
                    .icon(bitmap);
            //在地图上添加Marker，并显示

            final Marker marker = (Marker) map.addOverlay(option);
            Projection projection = map.getProjection();
            double longitude = pt.longitude;
            double latitude = pt.latitude;
            endPt= marker.getPosition();
            MapStatusUpdate msu = MapStatusUpdateFactory.newLatLng(endPt);
            map.animateMapStatus(msu);
            mMapView.refreshDrawableState();
            lv.setVisibility(View.INVISIBLE);
            Log.d("TAG","定位修改，，，，，");
            //设置位置提醒，四个参数分别是：纬度、经度、半径、坐标类型
            myLocationListener.SetNotifyLocation(latitude,longitude , 30000, mLocationClient.getLocOption().getCoorType());
            LatLng point1=new LatLng(latitude,longitude);
            final double distance = DistanceUtil.getDistance(point1,   ll);

            Log.d("TAG","距离"+distance);

            //点击地图的mark
            map.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(Marker marker) {
                    Log.d("TAG","点击mark");
                    onClickMark(marker,bean,distance);
                    return true;
                }
            });
        }

    }

    /**
     * 点击地图的mark 弹出对话框  进入到崔单的界面
     * @param marker
     * @param bean
     */
    private void onClickMark(Marker marker, searchBean bean,double distance) {
        //获取当前经纬度信息
        final LatLng latLng = marker.getPosition();
        final String[] addr = new String[1];
        //实例化一个地理编码查询对象
        GeoCoder geoCoder = GeoCoder.newInstance();
        //设置反地理编码位置坐标
        ReverseGeoCodeOption option = new ReverseGeoCodeOption();
        option.location(latLng);
        //发起反地理编码请求
        geoCoder.setOnGetGeoCodeResultListener(this);
        geoCoder.reverseGeoCode(option);


        LayoutInflater inflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
      View  InfoConentWindowView = inflater.inflate(R.layout.infowindow_layout, null);
        but_info_cancle= (Button) InfoConentWindowView.findViewById(R.id.but_info_cancle);
        but_info_submit= (Button) InfoConentWindowView.findViewById(R.id.but_info_sbumit);
        tv_info_detail= (TextView) InfoConentWindowView.findViewById(R.id.tv_info_detail);
        tv_info_distance=(TextView) InfoConentWindowView.findViewById(R.id.tv_info_distance);
        but_info_gps= (Button) InfoConentWindowView.findViewById(R.id.but_info_gps);   //点击导航
        double v = distance / 1000f;
        DecimalFormat df = new DecimalFormat("#.00");
        String format = df.format(v);
        tv_info_distance.setText("距离为："+format+"km");
        tv_info_detail.setText(bean.getCity()+bean.getDistrict()+bean.getKey());

        //显示信息窗口
        map.showInfoWindow( new InfoWindow( InfoConentWindowView, latLng, -47));

        but_info_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                map.hideInfoWindow();
                Log.d("TAG","点击取消按钮");
            }
        });
        but_info_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TAG","点击确定按钮");
            }
        });
      //  导航
        but_info_gps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                map.hideInfoWindow();
                butGPS();
            }
        });


    }

    /**
     * 线路导航
     */
    private void butGPS() {
        // 导航分为：1 驾车，2.骑行，3.走路
        ll_gps.setVisibility(View.VISIBLE);


        but_gps_walk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startWalkNavi();
            }
        });
        but_gps_car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        but_gps_bike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Intent intent=new Intent(MainActivity.this, BNaviMainActivity.class);
                startActivity(intent);*/
                startBikeNavi();
                ll_gps.setVisibility(View.INVISIBLE);
            }
        });
    }
    /**
     * 开始骑行导航
     */
    private void startBikeNavi() {
        Log.d(TAG, "startBikeNavi");
        try {
            BikeNavigateHelper.getInstance().initNaviEngine(this, new IBEngineInitListener() {
                @Override
                public void engineInitSuccess() {
                    Log.d(TAG, "BikeNavi engineInitSuccess");
                    routePlanWithBikeParam();
                }

                @Override
                public void engineInitFail() {
                    Log.d(TAG, "BikeNavi engineInitFail");
                }
            });
        } catch (Exception e) {
            Log.d(TAG, "startBikeNavi Exception");
            e.printStackTrace();
        }
    }

    /**
     * 发起骑行导航算路
     */
    private void routePlanWithBikeParam() {
        bikeParam = new BikeNaviLaunchParam().stPt(startPt).endPt(endPt);
        BikeNavigateHelper.getInstance().routePlanWithParams(bikeParam, new IBRoutePlanListener() {
            @Override
            public void onRoutePlanStart() {
                Log.d(TAG, "BikeNavi onRoutePlanStart");
            }

            @Override
            public void onRoutePlanSuccess() {
                Log.d(TAG, "BikeNavi onRoutePlanSuccess");
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, BNaviGuideActivity.class);
                startActivity(intent);
            }

            @Override
            public void onRoutePlanFail(BikeRoutePlanError error) {
                Log.d(TAG, "BikeNavi onRoutePlanFail");
            }

        });
    }
    /**
     * 开始步行导航
     */
    private void startWalkNavi() {
        Log.d(TAG, "startBikeNavi");
        try {
            WalkNavigateHelper.getInstance().initNaviEngine(this, new IWEngineInitListener() {
                @Override
                public void engineInitSuccess() {
                    Log.d(TAG, "WalkNavi engineInitSuccess");
                    routePlanWithWalkParam();
                }

                @Override
                public void engineInitFail() {
                    Log.d(TAG, "WalkNavi engineInitFail");
                }
            });
        } catch (Exception e) {
            Log.d(TAG, "startBikeNavi Exception");
            e.printStackTrace();
        }
    }
    /**
     * 发起步行导航算路
     */

    private void routePlanWithWalkParam() {
        walkParam = new WalkNaviLaunchParam().stPt(startPt).endPt(endPt);
        WalkNavigateHelper.getInstance().routePlanWithParams(walkParam, new IWRoutePlanListener() {
            @Override
            public void onRoutePlanStart() {
                Log.d(TAG, "WalkNavi onRoutePlanStart");
            }

            @Override
            public void onRoutePlanSuccess() {
                Log.d("View", "onRoutePlanSuccess");
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, WNaviGuideActivity.class);
                startActivity(intent);
            }

            @Override
            public void onRoutePlanFail(WalkRoutePlanError error) {
                Log.d(TAG, "WalkNavi onRoutePlanFail");
            }

        });
    }

    @Override
    protected void initData() {

    }


    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时必须调用mMapView. onResume ()
        mMapView.onResume();
     //   mNaviHelper.resume();
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
        mLocationClient.stopIndoorMode();
        mLocationClient.removeNotifyEvent(myLocationListener);
        mNaviHelper.quit();

    }

    LocationClient locationClient;
    private void initLocationOption() {
//定位服务的客户端。宿主程序在客户端声明此类，并调用，目前只支持在主线程中启动
       locationClient = new LocationClient(getApplicationContext());
//声明LocationClient类实例并配置定位参数
        LocationClientOption locationOption = new LocationClientOption();
        MyLocationListener myLocationListener = new MyLocationListener();
//注册监听函数
        locationClient.registerLocationListener(myLocationListener);
//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        locationOption.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
//可选，默认gcj02，设置返回的定位结果坐标系，如果配合百度地图使用，建议设置为bd09ll;
        locationOption.setCoorType("bd09ll");
//可选，默认0，即仅定位一次，设置发起连续定位请求的间隔需要大于等于1000ms才是有效的
        locationOption.setScanSpan(5000);
//可选，设置是否需要地址信息，默认不需要
        locationOption.setIsNeedAddress(true);


//可选，设置是否需要地址描述
        locationOption.setIsNeedLocationDescribe(true);
        locationOption.setAddrType("all");
//可选，设置是否需要设备方向结果
        locationOption.setNeedDeviceDirect(true);
//可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
        locationOption.setLocationNotify(true);
//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        locationOption.setIgnoreKillProcess(true);
//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        locationOption.setIsNeedLocationDescribe(true);
//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        locationOption.setIsNeedLocationPoiList(true);
//可选，默认false，设置是否收集CRASH信息，默认收集
        locationOption.SetIgnoreCacheException(false);
//可选，默认false，设置是否开启Gps定位
        locationOption.setOpenGps(true);
//可选，默认false，设置定位时是否需要海拔信息，默认不需要，除基础定位版本都可用
        locationOption.setIsNeedAltitude(false);
//设置打开自动回调位置模式，该开关打开后，期间只要定位SDK检测到位置变化就会主动回调给开发者，该模式下开发者无需再关心定位间隔是多少，定位SDK本身发现位置变化就会及时回调给开发者
        locationOption.setOpenAutoNotifyMode();
//设置打开自动回调位置模式，该开关打开后，期间只要定位SDK检测到位置变化就会主动回调给开发者
        locationOption.setOpenAutoNotifyMode(3000,1, LocationClientOption.LOC_SENSITIVITY_HIGHT);
        locationClient.setLocOption(locationOption);
        locationClient.start();
        Log.d("TAG","  locationClient");
    }
    /**
     * 实现定位回调
     */
    public class MyLocationListener extends BDAbstractLocationListener{
        @Override
        public void onReceiveLocation(BDLocation location){
            //此处的BDLocation为定位结果信息类，通过它的各种get方法可获取定位相关的全部结果
            //以下只列举部分获取经纬度相关（常用）的结果信息
            //更多结果信息获取说明，请参照类参考中BDLocation类中的说明

            //获取纬度信息
            double latitude = location.getLatitude();
            //获取经度信息
            double longitude = location.getLongitude();
            //获取定位精度，默认值为0.0f
            float radius = location.getRadius();
            //获取经纬度坐标类型，以LocationClientOption中设置过的坐标类型为准
            String coorType = location.getCoorType();
            Address address = location.getAddress();
            String city = location.getCity();
            String province = location.getProvince();
            //获取定位类型、定位错误返回码，具体信息可参照类参考中BDLocation类中的说明
            int errorCode = location.getLocType();
            Log.d("TAG","latitude "+latitude );
            Log.d("TAG","longitude "+longitude);
            Log.d("TAG"," city "+ city);
            Log.d("TAG"," province "+ province);
             navigateTo(location);
          startPt=new LatLng(latitude,longitude);
            //此处的BDLocation为定位结果信息类，通过它的各种get方法可获取定位相关的全部结果
            if (location.getFloor() != null) {
                // 当前支持高精度室内定位
                String buildingID = location.getBuildingID();// 百度内部建筑物ID
                String buildingName = location.getBuildingName();// 百度内部建筑物缩写
                String floor = location.getFloor();// 室内定位的楼层信息，如 f1,f2,b1,b2
                Log.d("TAG"," floor "+floor);
                mLocationClient.startIndoorMode();// 开启室内定位模式（重复调用也没问题），开启后，定位SDK会融合各种定位信息（GPS,WI-FI，蓝牙，传感器等）连续平滑的输出定位结果；
            }



        }
    }

//6.0之后要动态获取权限，重要！！！

    protected void judgePermission() {

//监听授权
        List<String> permissionList=new ArrayList<>();
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.
                permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.
                permission.READ_PHONE_STATE)!= PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.READ_PHONE_STATE);
        }
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.
                permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (!permissionList.isEmpty()){
            String[] permissions=permissionList.toArray(new String[permissionList.size()]);
            ActivityCompat.requestPermissions(MainActivity.this,permissions,1);
        }else {
            initLocationOption();



        }



    }
    /*移动到指定位置*/
    LatLng ll;
    private void  navigateTo(BDLocation location){
        if (isFirstLocate){
           ll = new LatLng(location.getLatitude(),location.getLongitude());
            MapStatusUpdate update = MapStatusUpdateFactory.newLatLng(ll);
            map.animateMapStatus(update);
            update = MapStatusUpdateFactory.zoomTo(19f);
            map.animateMapStatus(update);
	        /*判断baiduMap是已经移动到指定位置*/
            if ( map.getLocationData()!=null)
                if ( map.getLocationData().latitude==location.getLatitude()
                        && map.getLocationData().longitude==location.getLongitude()){
                    isFirstLocate = false;
                }
        }
        map.setMyLocationEnabled(true);

        // 构造定位数据
        MyLocationData locData = new MyLocationData.Builder()
                .accuracy(location.getRadius())
                // 此处设置开发者获取到的方向信息，顺时针0-360
                .direction(100).latitude(location.getLatitude())
                .longitude(location.getLongitude()).build();
                map.setMyLocationData(locData);
      //  map.setMyLocationConfiguration(new MyLocationConfiguration(MyLocationConfiguration.LocationMode.FOLLOWING,true,bitmapDescriptor));

        center2myLoc(location.getLatitude(),location.getLongitude());

        String locationDescribe = location.getLocationDescribe();
        tv_location.setText(location.getProvince()+location.getCity()+locationDescribe);
        Log.d("TAG","位置描述"+locationDescribe);
    }

    /**
     * 地图移动到我的位置,此处可以重新发定位请求，然后定位；
     * 直接拿最近一次经纬度，如果长时间没有定位成功，可能会显示效果不好
     */
    private void center2myLoc(double currentLantitude, double currentLongitude) {
        LatLng ll = new LatLng(currentLantitude, currentLongitude);
        MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(ll);
        mMapView.getMap().animateMapStatus(u);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == authBaseRequestCode) {
            for (int ret : grantResults) {
                if (ret == 0) {
                    continue;
                } else {
                    Toast.makeText(MainActivity.this, "缺少导航基本的权限!", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
            initNavi();
        }
    }
}



