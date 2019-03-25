package wansun.visit.android.ui.activity;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Vibrator;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

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
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeOption;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
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
import com.baidu.navisdk.adapter.BNRoutePlanNode;
import com.baidu.navisdk.adapter.BaiduNaviManagerFactory;
import com.baidu.navisdk.adapter.IBNRoutePlanManager;
import com.baidu.navisdk.adapter.IBNTTSManager;
import com.baidu.navisdk.adapter.IBaiduNaviManager;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import baidu.navi.sdkdemo.NormalUtils;
import baidu.navi.sdkdemo.newif.DemoGuideActivity;
import bikenavi_demo.BNaviGuideActivity;
import bikenavi_demo.WNaviGuideActivity;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import wansun.visit.android.R;
import wansun.visit.android.adapter.addressAdapter;
import wansun.visit.android.adapter.geogCodeAdapter;
import wansun.visit.android.adapter.searchAdapter;
import wansun.visit.android.api.apiManager;
import wansun.visit.android.bean.geogCodeBean;
import wansun.visit.android.bean.mapInfoBean;
import wansun.visit.android.bean.saveLocationMessageBean;
import wansun.visit.android.bean.searchBean;
import wansun.visit.android.bean.stateMessageBean;
import wansun.visit.android.bean.visitItemBean;
import wansun.visit.android.global.waifangApplication;
import wansun.visit.android.net.requestBodyUtils;
import wansun.visit.android.utils.NetWorkTesting;
import wansun.visit.android.utils.SharedUtils;
import wansun.visit.android.utils.ToastUtil;
import wansun.visit.android.utils.dialogUtils;
import wansun.visit.android.utils.logUtils;
import wansun.visit.android.utils.netUtils;

/**
 * 主页就是百度地图界面
 */
public class MainActivity extends BaseActivity implements OnGetGeoCoderResultListener, BaiduMap.OnMarkerClickListener {
  private final static String TAG = MainActivity.class.getSimpleName();
    MapView mMapView;
    private BaiduMap map;
    public LocationClient mLocationClient = null;
    private MyLocationListener myListener = new MyLocationListener();
    boolean isFirstLocate=true;
    TextView tv_location,tv_info_detail,tv_info_distance,tv_bottom_current_location,tv_bottom_destination_location,tv_exit;
    EditText et_address;
    Button but_info_cancle,but_info_submit,but_info_gps;
    private SuggestionSearch suggestionSearch;
    List data;
    searchAdapter adapter;
    ListView lv,lv_main;
    ImageView iv_navigation,iv_bottom_go,iv_search_address;
    LatLng pt;   // 精度  纬度
    public BDNotifyListener myLocationListener = new MyNotifyLister();
    DrawerLayout drawerLayout;
    private String mSDCardPath = null;
    private static final String APP_FOLDER_NAME = "BNSDKSimpleDemo";
    private static final int authBaseRequestCode = 1;
    LinearLayout ll_gps,ll_bottom;
    Button but_gps_walk,but_gps_car,but_gps_bike;
    BikeNaviLaunchParam bikeParam;    // 起点和终点经纬度
    private LatLng startPt,endPt;
    WalkNaviLaunchParam walkParam;
    private BikeNavigateHelper mNaviHelper;
    static final String ROUTE_PLAN_NODE = "routePlanNode";
    private BNRoutePlanNode mStartNode = null;
    private boolean hasInitSuccess = false;
    double destinationLongitude;// 目的地
    double destinationLatitude; //目的地
    double curlatitude;
    double curlongitude;
    String curentLcotion;
    final Map<String, LatLng  > key=new HashMap<>();
    Marker marker;  //添加mark
    addressAdapter addAaapter;
    GeoCoder mSearch = null;
    RelativeLayout rl_visit_order,rl_visit_order_record;
    LinearLayout rl_versions;
    List addressData; //从服务器取得的地址信息
    int lv_mainItemPostion;
    String positionGuid ;// 保存定位信息的GUID
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
        lv_main= (ListView) findViewById(R.id.lv_main);
        mMapView= (MapView) findViewById(R.id.map);
        map = mMapView.getMap();
        mMapView.setMapCustomEnable(true);  //开启个性化地图
        mMapView.showZoomControls(false);  //去掉地图放大缩小按钮
        if (initDirs()){
     initNavi();    //初始化百度地图导航
        }
        //声明LocationClient类
        mLocationClient = new LocationClient(getApplicationContext());
        //注册监听函数
        mLocationClient.registerLocationListener(myListener);
        //注册监听函数  位置提醒
        mLocationClient.registerNotify(myLocationListener);
        //初始化点聚合管理类
        tv_location= (TextView) findViewById(R.id.tv_locatio);
        et_address= (EditText) findViewById(R.id.et_address);
        drawerLayout= (DrawerLayout) findViewById(R.id.dl_content);
        iv_navigation= (ImageView) findViewById(R.id.iv_navigation);
        ll_gps= (LinearLayout) findViewById(R.id.ll_gps);
        but_gps_walk= (Button) findViewById(R.id.but_gps_walk);
        but_gps_car= (Button) findViewById(R.id.but_gps_car);
        but_gps_bike= (Button) findViewById(R.id.but_gps_bike);
        tv_bottom_destination_location= (TextView) findViewById(R.id.tv_bottom_destination_location);
        tv_bottom_current_location= (TextView) findViewById(R.id.tv_bottom_current_location);
        ll_bottom= (LinearLayout) findViewById(R.id.ll_bottom);
        iv_bottom_go= (ImageView) findViewById(R.id.iv_bottom_go);
        tv_exit= (TextView) findViewById(R.id.tv_exit);
        iv_search_address= (ImageView) findViewById(R.id.iv_search_address);
        rl_visit_order= (RelativeLayout) findViewById(R.id.rl_visit_order);
        rl_visit_order_record= (RelativeLayout) findViewById(R.id.rl_visit_order_record);
        rl_versions= (LinearLayout) findViewById(R.id.rl_versions);

    }

    /**
     * 其他界面传过来的数据 地址在地图上定位
     * 只有一个地址  在地图上显示一个mark点
     * 并且把mark点拉到屏幕的中央
     */
    private void getIntentData() {
        String address = getIntent().getStringExtra("address");
        logUtils.d("address"+address);
        if (!TextUtils.isEmpty(address)){
            getSearch("北京",address);
        }
    }


public void getSearch(String city,String address){
    mSearch = GeoCoder.newInstance();
    mSearch.setOnGetGeoCodeResultListener(GeoListener);
    mSearch.geocode(new GeoCodeOption()
            .city(city)
            .address(address));
}



    /**
     *    修改地图样式
     */
    @Override
    protected void initLise() {
        setMapCustomFile(this,"custom_map_config.json");
    }

    /**
     * 将个性化文件写入本地后调用MapView.setCustomMapStylePath加载
     * @param context
     * @param fileName assets目录下自定义样式文件的文件名
     */
    private void setMapCustomFile(Context context, String fileName) {
        InputStream inputStream = null;
        FileOutputStream fileOutputStream = null;
        String moduleName = null;
        try {
            inputStream = context.getAssets().open("customConfigDir/" + fileName);
            byte[] b = new byte[inputStream.available()];
            inputStream.read(b);
            moduleName = context.getFilesDir().getAbsolutePath();
            File file = new File(moduleName + "/" + fileName);
            if (file.exists()) file.delete();
            file.createNewFile();
            fileOutputStream = new FileOutputStream(file);
            //将自定义样式文件写入本地
            fileOutputStream.write(b);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //设置自定义样式文件
        MapView.setCustomMapStylePath(moduleName + "/" + fileName);
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
                        Log.d("TAG","onAuthResult"+ msg+status);
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
                        hasInitSuccess = true;
                        initTTS();
                    }
                    @Override
                    public void initFailed() {
                        Log.d("TAG","初始化百度地图失败");
                    }
                });
    }
    private void initTTS() {
        // 使用内置TTS
        BaiduNaviManagerFactory.getTTSManager().initTTS(getApplicationContext(),
                getSdcardDir(), APP_FOLDER_NAME, NormalUtils.getTTSAppID());
        // 注册同步内置tts状态回调
        BaiduNaviManagerFactory.getTTSManager().setOnTTSStateChangedListener(
                new IBNTTSManager.IOnTTSPlayStateChangedListener() {
                    @Override
                    public void onPlayStart() {
                        Log.e("BNSDKDemo", "ttsCallback.onPlayStart");
                    }
                    @Override
                    public void onPlayEnd(String speechId) {
                        Log.e("BNSDKDemo", "ttsCallback.onPlayEnd");
                    }
                    @Override
                    public void onPlayError(int code, String message) {
                        Log.e("BNSDKDemo", "ttsCallback.onPlayError"+message);
                    }
                }
        );
        // 注册内置tts 异步状态消息
        BaiduNaviManagerFactory.getTTSManager().setOnTTSStateChangedHandler(
                new Handler(Looper.getMainLooper()) {
                    @Override
                    public void handleMessage(Message msg) {
                        Log.e("TAG", "ttsHandler.msg.what=" + msg.what+",,,,,"+msg.toString());
                    }
                }
        );
    }
    private void routeplanToNavi(final int coType) {
        if (!hasInitSuccess) {
            Toast.makeText(MainActivity.this, "还未初始化!", Toast.LENGTH_SHORT).show();
        }
        BNRoutePlanNode sNode=null;
        BNRoutePlanNode eNode=null;
        switch (coType) {
            case BNRoutePlanNode.CoordinateType.BD09LL: {
                String s = tv_bottom_destination_location.getText().toString();
                Log.e("TAG", "导航" + s);
                sNode = new BNRoutePlanNode(curlongitude, curlatitude, locationDescribe, null, coType);
                eNode = new BNRoutePlanNode(destinationLongitude, destinationLatitude, s,null, coType);
                break;
            }
            default:
                break;
        }
        mStartNode = sNode;
        List<BNRoutePlanNode> list = new ArrayList<BNRoutePlanNode>();
        list.add(sNode);
        list.add(eNode);

        BaiduNaviManagerFactory.getRoutePlanManager().routeplanToNavi(
                list,
                IBNRoutePlanManager.RoutePlanPreference.ROUTE_PLAN_PREFERENCE_DEFAULT,
                null,
                new Handler(Looper.getMainLooper()) {
                    @Override
                    public void handleMessage(Message msg) {
                        switch (msg.what) {
                            case IBNRoutePlanManager.MSG_NAVI_ROUTE_PLAN_START:
                                Toast.makeText(MainActivity.this, "算路开始", Toast.LENGTH_SHORT)
                                        .show();
                                break;
                            case IBNRoutePlanManager.MSG_NAVI_ROUTE_PLAN_SUCCESS:
                                Toast.makeText(MainActivity.this, "算路成功", Toast.LENGTH_SHORT)
                                        .show();
                                break;
                            case IBNRoutePlanManager.MSG_NAVI_ROUTE_PLAN_FAILED:
                                Toast.makeText(MainActivity.this, "算路失败"+msg, Toast.LENGTH_SHORT)
                                        .show();
                                break;
                            case IBNRoutePlanManager.MSG_NAVI_ROUTE_PLAN_TO_NAVI:
                                Toast.makeText(MainActivity.this, "算路成功准备进入导航", Toast.LENGTH_SHORT)
                                        .show();
                                Intent intent = new Intent(MainActivity.this, DemoGuideActivity.class);
                                Bundle bundle = new Bundle();
                                bundle.putSerializable(ROUTE_PLAN_NODE, mStartNode);
                                intent.putExtras(bundle);
                                startActivity(intent);
                                break;
                            default:
                                // nothing
                                break;
                        }
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
    boolean lv_flag=false;
    boolean self_flag=false;
    boolean click_flag=false;
    boolean click_address_item_flag=false;
    @Override
    protected void initEvent() {
        map = mMapView.getMap();
        data=new ArrayList();
        //检索的功能
        suggestionSearch = SuggestionSearch.newInstance();
        suggestionSearch.setOnGetSuggestionResultListener(listener);
        mSearch = GeoCoder.newInstance();
        mSearch.setOnGetGeoCodeResultListener(GeoListener);


        /**
         * 点击弹出侧滑菜单栏
         */
        iv_navigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(Gravity.LEFT);   //打开左边的菜单栏
            }
        });
/*       map.setOnMapClickListener(new BaiduMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                map.hideInfoWindow();   //点击地图前先要隐藏掉弹窗
                    delete();
                //定义Maker坐标点
                Log.d("TAG","添加mark"+"latitude"+latLng.latitude+"longitude"+latLng.longitude+"在地图上点击");
                LatLng point = new LatLng(latLng.latitude, latLng.longitude);
                key.clear();
                key.put("point",point);
             //构建Marker图标
                BitmapDescriptor bitmap = BitmapDescriptorFactory
                        .fromResource(R.mipmap.end);
             //构建MarkerOption，用于在地图上添加Marker
                OverlayOptions option = new MarkerOptions()
                        .position(point)
                        .icon(bitmap);
               //在地图上添加Marker，并显示
                marker = (Marker) map.addOverlay(option);
                self_flag=true;   //点击地图产生的mark
            }
            @Override
            public boolean onMapPoiClick(MapPoi mapPoi) {
                return false;
            }
        });*/

        map.setOnMarkerClickListener(this);//mark点击监听
        tv_bottom_destination_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogBottom(v);
                logUtils.d("点击底部");
            }
        });
        //退出登陆
        tv_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exitAPP();
            }
        });
        iv_search_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logUtils.d("测试地址");
                if (!click_flag){
                    iv_search_address.setImageResource(R.mipmap.upone);

                    click_flag=true;
                    lv_main.setVisibility(View.VISIBLE);
                    addAaapter=new addressAdapter(MainActivity.this,addressData);
                    lv_main.setAdapter(addAaapter);

                }else {
                    iv_search_address.setImageResource(R.mipmap.pull);
                    logUtils.d("点击收起");
                    click_flag=false;
                    lv_main.setVisibility(View.GONE);
                }

            }
        });
        // 地址的item点击事件  再地图上标记mark点  判断点已经存在 就不再添加mark  而是把点拉到手机屏幕的中心
        lv_main.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                lv_mainItemPostion=position;
                logUtils.d("点击了item"+position);
                click_address_item_flag=true;
                lv_main.setVisibility(View.GONE);   //点击item 后 lv_main隐藏
                iv_search_address.setImageResource(R.mipmap.pull);
                String address = (String) addressData.get(position);
                et_address.setText(address);
                if (!TextUtils.isEmpty(address)){
                    getSearch("北京",address);
                }


            }
        });
        //外访单
        rl_visit_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,VisitOrderActivity.class);
                intent.putExtra("visitData", (Serializable) visitData);
                startActivity(intent);
                overridePendingTransition(R.anim.in_from_right,R.anim.out_to_left);
             //   drawerLayout.closeDrawer(Gravity.LEFT);
            }
        });
        //历史外访单
        rl_visit_order_record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,VistRecordActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.in_from_right,R.anim.out_to_left);
            }
        });
        // 版本升级
        rl_versions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,VersionsActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.in_from_right,R.anim.out_to_left);
            }
        });
    }

    /**
     * 退出App
     */
    private void exitAPP() {
        View view = getLayoutInflater().inflate(R.layout.custom_diaglog_layut_exit_app, null);
        final TextView tv = (TextView) view.findViewById(R.id.tv);
        TextView tv_cancle= (TextView) view.findViewById(R.id.add_cancle);
        tv.setText(R.string.app_exit);
        tv.setTextSize(16);
        tv.setGravity(Gravity.CENTER);
        TextView tv_submit= (TextView) view.findViewById(R.id.add_submit);
        WindowManager manager=getWindowManager();
        final dialogUtils utils=new dialogUtils(MainActivity.this,manager,view );
        utils.getDialog();
        tv_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                utils.cancleDialog();

            }
        });
        tv_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                utils.cancleDialog();
                waifangApplication.getInstence().removeALLActivity_();// 清掉全部应用的activity
                overridePendingTransition(R.anim.in_from_left,R.anim.out_to_right);  //退出动画
            }
        });
    }

    //创建OverlayOptions的集合  批量添加mark点
    List<OverlayOptions> options = new ArrayList<OverlayOptions>();
    boolean service_flag=false;  //服务器下发点的标记
    LatLng servicePoint;
    int currrentCount=0;//记录循环的次数  ，也就是服务器地址数量
    OnGetGeoCoderResultListener GeoListener = new OnGetGeoCoderResultListener() {
        @Override
        public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {
            if (null != geoCodeResult && null != geoCodeResult.getLocation()) {
                if (geoCodeResult == null || geoCodeResult.error != SearchResult.ERRORNO.NO_ERROR) {
                    //没有检索到结果
                    return;
                } else {
                    service_flag=true;
                    double latitude = geoCodeResult.getLocation().latitude;
                    double longitude = geoCodeResult.getLocation().longitude;
                    LatLng latLng=new LatLng(latitude,longitude );
                    logUtils.d("地理反编码地址"+"latitude"+latitude+"longitude"+longitude);
                    servicePoint = new LatLng(latitude, longitude);
                    //创建OverlayOptions属性
                     //  lv_mainItemPostion
                    mapInfoBean mapInfoBean=null;
                    if (!click_address_item_flag){

                        mapInfoBean = mapInfoData.get(currrentCount);
                        currrentCount++;
                    }else {
                        mapInfoBean = mapInfoData.get(lv_mainItemPostion);
                    }
                        BitmapDescriptor bitmap = BitmapDescriptorFactory.fromResource(R.mipmap.man);
                        OverlayOptions option1 =  new MarkerOptions()
                                .position(servicePoint)
                                .icon(bitmap);
                        marker = (Marker) map.addOverlay(option1);
                        Bundle bundle=new Bundle();
                        bundle.putSerializable("info", mapInfoBean);
                        marker.setExtraInfo(bundle);
                    MapStatus mMapStatus = new MapStatus.Builder()
                            .target(latLng)
                            .zoom(15)
                            .build();
                    //定义MapStatusUpdate对象，以便描述地图状态将要发生的变化
                    MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
//改变地图状态
                    map.animateMapStatus(mMapStatusUpdate);

                }
            }
        }

        @Override
        public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {

        }
    };
    /**
     *  底部弹窗
     * @param v
     */
    private Dialog dialog;
    private void dialogBottom(View v) {
        map.hideInfoWindow();
        dialog = new Dialog(this, R.style.ActionSheetDialogStyle);
        View inflate = LayoutInflater.from(this).inflate(R.layout.botom_dialog_layout, null);
       final EditText et_address_botom = (EditText) inflate.findViewById(R.id.et_address);
       Button    but_search = (Button) inflate.findViewById(R.id.but_search);
        lv = (ListView) inflate.findViewById(R.id.lv);
        dialog.setContentView(inflate);
        Window dialogWindow = dialog.getWindow();
        dialogWindow.setGravity(Gravity.TOP);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        lp.height = (int) (wm.getDefaultDisplay().getHeight() * 0.6);
        lp.width=(int) (wm.getDefaultDisplay().getWidth() * 1);
        dialogWindow.setAttributes(lp);
        dialog.show();//显示对话框
        showKeyboard(et_address_botom);
        but_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lv_main.setVisibility(View.GONE);
                logUtils.d("点击了搜索");
                String trim = et_address_botom.getText().toString().trim();   //可以优化 自动搜索
                if (!TextUtils.isEmpty(trim)) {
                    suggestionSearch.requestSuggestion(new SuggestionSearchOption()
                            .city("深圳市")
                            .keyword(trim));
                } else {
                    ToastUtil.showToast(MainActivity.this, "请输入地址");
                }
            }
        });

    }
    private void showKeyboard(final EditText et_address_botom){

        Timer timer = new Timer();
        timer.schedule(new TimerTask()   {
            public void run() {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.showSoftInput(et_address_botom, 0);
      }
      }, 200);
    }
    /**
     * mark点击时间
     * @param marker
     */

    LatLng position;
    private void onClickMarkSelef(Marker marker) {
        position = marker.getPosition();
        double longitude = position.longitude;
        double latitude = position.latitude;
        LatLng point1=new LatLng(latitude,longitude);
        final double distance = DistanceUtil.getDistance(point1,   ll);
        Log.d("TAG","添加mark+distance...point1 "+point1 );
        Log.d("TAG","添加mark+distance...servicePoint"+servicePoint);
        String id = marker.getId();
        Log.d("TAG","添加mark+distance...id"+id);
        String title = marker.getTitle();
        Log.d("TAG","title"+title);
/*
        if (self_flag){  //点击地图的点
    *//*        self_flag=false;
            Log.d("TAG","客户自定义mark");
            mSearch = GeoCoder.newInstance();
            mSearch.setOnGetGeoCodeResultListener(this);
            mSearch.reverseGeoCode(new ReverseGeoCodeOption()
                    .location(position).radius(500));   //500米搜索*//*
        }else {

        }*/

            onClickMark(marker);   //服务器的地址点击






    }

    /**
     * 删除原来的mark点
     */
    private void delete() {
        if (!key.isEmpty()){
            marker.remove();
        }
    }
    geogCodeBean goeBean;  //地理反编码
    List  geoData = new ArrayList<>();
    ListView lvbottom;
    @Override
    public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {

    }

    @Override
    public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {
        String address = reverseGeoCodeResult.getAddress();
        List<PoiInfo> poiList = reverseGeoCodeResult.getPoiList();
        if (poiList.size()>0&&poiList!=null){
            Iterator<PoiInfo> iterator = poiList.iterator();
            geoData.clear();
            while (iterator.hasNext()){
                goeBean=new geogCodeBean();
                PoiInfo next = iterator.next();
                Log.e("TAG","next"+next);
                   goeBean.setAddress(next.getAddress());
                goeBean.setCity(next.getCity());
                goeBean.setLocation(next.getLocation());
                goeBean.setName(next.getName());
                geoData.add(goeBean);
        }

        ReverseGeoCodeResult.AddressComponent addressDetail = reverseGeoCodeResult.getAddressDetail();
        Log.e("TAG","onGetReverseGeoCodeResult+address "+address+"addressDetail"+addressDetail );
        // TODO   地理位置反编码   知道经纬度 得到位置
        final geogCodeAdapter adapter=new geogCodeAdapter(MainActivity.this,geoData);

        LayoutInflater inflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
        View  InfoConentWindowView = inflater.inflate(R.layout.infowindow_geogcode_layout, null);


        lvbottom= (ListView) InfoConentWindowView.findViewById(R.id.lv_geog);
        lvbottom.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        //显示信息窗口
        map.showInfoWindow( new InfoWindow( InfoConentWindowView,   position, -47));
          //  final geogCodeBean codeBean=new geogCodeBean();
        lvbottom.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("TAG","点击item");
                ll_gps.setVisibility(View.VISIBLE);
                TextView textView = (TextView)view.findViewById(R.id.tv_search_item);
                String str = (String) textView.getText();
                if (!TextUtils.isEmpty(str)){
                    ll_bottom.setVisibility(View.VISIBLE);
                }
                geogCodeBean codeBean = (geogCodeBean) geoData.get(position);
                LatLng location = codeBean.getLocation();
                endPt=location ;
                double latitude = location.latitude;
                double longitude = location.longitude;
                Log.e("TAG","点击item"+"latitude"+latitude+"longitude"+longitude);
                tv_bottom_destination_location.setText(str);
                butGPS();

            }
        });
        }else {
            ToastUtil.showToast(MainActivity.this, "请重试一下");
        }
    }

    /**
     * 地图mark的点击事件
     * @param marker
     * @return
     */
    @Override
    public boolean onMarkerClick(Marker marker) {
        LatLng latLng = marker.getPosition();
        destinationLongitude = latLng.longitude;
        destinationLatitude = latLng.latitude;
        //TODO 判断条件
   /*     if (bean!=null){
            onClickMark(marker,bean,distance);   // 后台数据的mar
        }else {*/
            onClickMarkSelef(marker);  //客户点击地图的mark
       // }
         logUtils.d("点击屏幕的mark");
        return false;
    }

    public class MyNotifyLister extends BDNotifyListener {
        public void onNotify(BDLocation mlocation, float distance){
            //已到达设置监听位置附近  弹出对话框
            ToastUtil.showToast(MainActivity.this,"到达目标位置请注意");
            Log.e("TAG","到达目标位置请注意"+distance);
            Vibrator vibrator = (Vibrator)MainActivity.this.getSystemService(MainActivity.this.VIBRATOR_SERVICE);
            vibrator.vibrate(2000);   //手机震动提示
            String s = "你已经到达"+mlocation.getProvince() + mlocation.getCity() + mlocation.getLocationDescribe();
            Log.e("TAG","到达目标位置请注意"+s);
            waifangApplication.getmSpeechSynthesizer().speak(s);
        }
    }
List dataAddress=new ArrayList();
    OnGetSuggestionResultListener listener = new OnGetSuggestionResultListener() {
        @Override
        public void onGetSuggestionResult(SuggestionResult suggestionResult) {
            //处理sug检索结果
            List<SuggestionResult.SuggestionInfo> allSuggestions = suggestionResult.getAllSuggestions();
            Iterator<SuggestionResult.SuggestionInfo> iterator = allSuggestions.iterator();
            dataAddress.clear();
            while (iterator.hasNext()){
                SuggestionResult.SuggestionInfo next = iterator.next();
                searchBean bean=new searchBean();
                bean.setCity(next.getCity());
                bean.setDistrict(next.getDistrict());
                bean.setPt(next.getPt());
                bean.setKey(next.getKey());
                dataAddress.add(bean);

            }
           adapter=new searchAdapter(MainActivity.this,dataAddress);
     /*       if (lv_flag){
                lv_flag=false;
                lv_main.setVisibility(View.VISIBLE);
                lv_main.setAdapter(adapter);
                lv_main.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        lv_main.setVisibility(View.INVISIBLE);
                        bean = (searchBean) dataAddress.get(position);
                        pt = bean.getPt();
                        addMark(bean );
                        if (dialog!=null){
                            dialog.hide();
                        }

                    }
                });
            }else {*/
            lv.setVisibility(View.VISIBLE);
            lv.setAdapter(adapter);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    lv.setVisibility(View.INVISIBLE);
                    lv_flag=true;
                     bean = (searchBean) dataAddress.get(position);
                    TextView text = (TextView) lv.getChildAt(position).findViewById(R.id.tv_search_item);
                    pt = bean.getPt();
                    Log.d("TAG","点击底部lv"+position+":tv"+text.getText().toString());
                    tv_bottom_destination_location.setText(text.getText().toString());
                    addMark(bean );
                    butGPS();
                    LatLng pt = bean.getPt();
                    destinationLongitude = pt.longitude;
                    destinationLatitude = pt.latitude;
                    if (dialog!=null){
                        dialog.hide();
                    }

                }
            });
           // }
        }
    };

    /**
     * 给地图添加标记物Mark  并且显示在手机屏幕的中央  可以拖动
     */
 double distance;
    searchBean bean;

    private void addMark(final searchBean bean) {
        if (pt!=null){
            //定义Maker坐标点   根据精度和纬度
            LatLng point = new LatLng(pt.latitude, pt.longitude);
            //构建Marker图标
            BitmapDescriptor bitmap=null;
            if (click_address_item_flag){   //服务器下发的地址
                click_address_item_flag=false;
                bitmap = BitmapDescriptorFactory
                        .fromResource(R.mipmap.man);
                logUtils.d("走了man");
            }
                if (lv_flag){
                    logUtils.d("走了location");
                    lv_flag=false;
                    bitmap = BitmapDescriptorFactory
                            .fromResource(R.mipmap.location);  //自定义的点

                    //构建MarkerOption，用于在地图上添加Marker

                }


            OverlayOptions option = new MarkerOptions()
                    .position(point)
                    .title("测试数据")
                    .icon(bitmap);
            //在地图上添加Marker，并显示
            Marker marker = (Marker) map.addOverlay(option);
            double longitude = pt.longitude;
            double latitude = pt.latitude;
            endPt= marker.getPosition();
            MapStatusUpdate msu = MapStatusUpdateFactory.newLatLng(endPt);
            map.animateMapStatus(msu);
            mMapView.refreshDrawableState();

            Log.d("TAG","定位修改，，，，，"+"longitude"+longitude+"latitude"+latitude);
            //设置位置提醒，四个参数分别是：纬度、经度、半径、坐标类型
            myLocationListener.SetNotifyLocation(latitude,longitude , 1000, mLocationClient.getLocOption().getCoorType());
            LatLng point1=new LatLng(latitude,longitude);
            mLocationClient.start();


        }
    }

    /**
     * 点击地图的mark 弹出对话框  进入到崔单的界面
     * @param marker
     *
     */

    String caseCodeNumber;
    String batchNumber;
    TextView tv_address;
    private void onClickMark(Marker marker) {
        Bundle bundle = marker.getExtraInfo();
        if (bundle !=null) {
       final mapInfoBean  bean = (mapInfoBean) bundle.getSerializable("info");
            logUtils.d("债务人名字" + bean.getDebtor());
            //获取当前经纬度信息
            endPt= marker.getPosition();
            destinationLongitude = endPt.longitude;
            destinationLatitude = endPt.latitude;
            logUtils.d("点击信息" + endPt.latitude + ":" + endPt.longitude);
            LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
            View InfoConentWindowView = inflater.inflate(R.layout.infowindow_layout, null);
            InfoConentWindowView.setMinimumWidth(900);
            TextView tv_name = (TextView) InfoConentWindowView.findViewById(R.id.tv_name);
            tv_name.setText("债务人姓名：" + bean.getDebtor());
            TextView tv_custome_name = (TextView) InfoConentWindowView.findViewById(R.id.tv_custome_name);
            tv_custome_name.setText("欠款机构：" + bean.getCustomer());
        tv_address = (TextView) InfoConentWindowView.findViewById(R.id.home_address);
            tv_address.setText("地址：" + bean.getAddressDeail());
            but_info_cancle = (Button) InfoConentWindowView.findViewById(R.id.but_info_cancle);
            but_info_submit = (Button) InfoConentWindowView.findViewById(R.id.but_info_sbumit);
            tv_info_detail = (TextView) InfoConentWindowView.findViewById(R.id.tv_info_detail);
            tv_info_distance = (TextView) InfoConentWindowView.findViewById(R.id.tv_info_distance);
            but_info_gps = (Button) InfoConentWindowView.findViewById(R.id.but_info_gps);   //点击导航
            TextView but_modify= (TextView) InfoConentWindowView.findViewById(R.id.but_modify);
          //  distance = DistanceUtil.getDistance(startPt,   endPt);
           // Log.d("TAG","距离"+distance);
         //   double v = distance / 1000f;
         //   DecimalFormat df = new DecimalFormat("#.00");
         //   String format = df.format(v);
        //    tv_info_distance.setText("距离为：" + format + "km");
            //  tv_info_detail.setText(bean.getCity()+bean.getDistrict()+bean.getKey());
            caseCodeNumber = bean.getCaseCodeNumber();
            batchNumber = bean.getBatchNumber();
            //显示信息窗口
            map.showInfoWindow(new InfoWindow(InfoConentWindowView, endPt, -47));

            but_info_cancle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    map.hideInfoWindow();
                    Log.d("TAG", "点击取消按钮");
                }
            });
            ///    final String finalCaseCode = caseCode;
            but_info_submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("TAG", "点击确定按钮");  //跳转到外访界面

                    if (!TextUtils.isEmpty(caseCodeNumber) && !TextUtils.isEmpty(batchNumber)) {
                        Intent intent = new Intent(MainActivity.this, OutBoundActivity.class);
                        intent.putExtra("caseCode", caseCodeNumber);
                        intent.putExtra("visitGuid", batchNumber);
                        startActivity(intent);
                        overridePendingTransition(R.anim.in_from_right,R.anim.out_to_left);
                        map.hideInfoWindow();  //点击导航就隐藏弹窗
                    } else {
                        ToastUtil.showToast(MainActivity.this, "案件编号为空");
                    }


                }
            });
            //  导航
            but_info_gps.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    map.hideInfoWindow();
                    butGPS();
                    tv_bottom_destination_location.setText(bean.getAddressDeail());
                    logUtils.d("点击导航");
                }
            });
            //地址修改
            but_modify.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //TODO
                    modifyAddress();

                }
            });
        }
    }

    /**
     * 修改地址
     */
    private void modifyAddress() {
        View view = getLayoutInflater().inflate(R.layout.custom_diaglog_layut_exit_app, null);
        final TextView tv = (TextView) view.findViewById(R.id.tv);
        TextView tv_cancle= (TextView) view.findViewById(R.id.add_cancle);
       final EditText et_modify= (EditText ) view.findViewById(R.id.et_modify);
        et_modify.setVisibility(View.VISIBLE);
        tv.setText(R.string.modify_address);
        tv.setTextSize(16);
        tv.setGravity(Gravity.CENTER);
        TextView tv_submit= (TextView) view.findViewById(R.id.add_submit);
        WindowManager manager=getWindowManager();
        final dialogUtils utils=new dialogUtils(MainActivity.this,manager,view );
        utils.getDialog();
        tv_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                utils.cancleDialog();

            }
        });
        tv_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(et_modify.getText().toString().trim())){
                    utils.cancleDialog();
                    NetWorkTesting net=new NetWorkTesting(MainActivity.this);
                    if (net.isNetWorkAvailable()) {
                        Retrofit retrofit = netUtils.getRetrofit();
                        apiManager manager= retrofit.create(apiManager.class);
                        logUtils.d("修改地址"+caseCodeNumber+":"+batchNumber);
                        final RequestBody requestBody = requestBodyUtils.modifyAddress( caseCodeNumber,batchNumber,et_modify.getText().toString().trim());
                        Call<String> call = manager.modifyAddressToService(requestBody);

                        call.enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Call<String> call, Response<String> response) {
                                String body = response.body();
                                logUtils.d("修改地址"+body);
                                if (!TextUtils.isEmpty(body)){
                                    Gson gson=new Gson();
                                    stateMessageBean data = gson.fromJson(body, new TypeToken<stateMessageBean>() {}.getType());
                                    String statusID = data.getStatusID();
                                    if (statusID.equals("200")){
                                        tv_address.setText(et_modify.getText().toString().trim());
                                        tv_bottom_destination_location.setText(et_modify.getText().toString().trim());
                                        butGPS();
                                        ToastUtil.showToast(MainActivity.this,data.getData());
                                    }else {
                                        ToastUtil.showToast(MainActivity.this,data.getData());
                                    }

                                }


                            }

                            @Override
                            public void onFailure(Call<String> call, Throwable t) {

                            }
                        });
                      //  but_upload_batch.setText("文件上传中...");
                      //  but_upload_batch.setClickable(false);  //上传中不让点击 防止重复加载
                    }else {
                        ToastUtil.showToast(MainActivity.this,R.string.network_unavailing);
                    }

                }else {
                    ToastUtil.showToast(MainActivity.this,"请输入修改地址");
                }

            }
        });
    }

    /**
     * 线路导航
     */
    private void butGPS() {
        // 导航分为：1 驾车，2.骑行，3.走路
        //点击导航按钮 就保存当前的定位信息
        saveLocationMessage();
        ll_bottom.setVisibility(View.VISIBLE);
        ll_gps.setVisibility(View.VISIBLE);
        but_gps_walk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TAG"," but_gps_walk");
                map.hideInfoWindow();  //点击导航就隐藏弹窗
                ll_bottom.setVisibility(View.GONE);
                tv_bottom_destination_location.setText("");
                startWalkNavi();

            }
        });
        but_gps_car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                map.hideInfoWindow();  //点击导航就隐藏弹窗
                if (BaiduNaviManagerFactory.getBaiduNaviManager().isInited()) {
                    routeplanToNavi(BNRoutePlanNode.CoordinateType.BD09LL);
                    ll_bottom.setVisibility(View.GONE);
                    tv_bottom_destination_location.setText("");
                }
            }
        });
        but_gps_bike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                map.hideInfoWindow();  //点击导航就隐藏弹窗
                ll_bottom.setVisibility(View.GONE);
                tv_bottom_destination_location.setText("");
                startBikeNavi();
            }
        });

        uploadLocationMessageToService();
    }

    /**
     * 保存定位信息
     */
    private void saveLocationMessage() {
        // caseCodeNumber,batchNumber
        TelephonyManager telephonyManager=(TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
        String imei=telephonyManager.getDeviceId();
     //  tv_bottom_current_location,tv_bottom_destination_location
        Map<String,Object> map=new HashMap<>();
        map.put("caseCode",caseCodeNumber);
        map.put("visitGuid",batchNumber);
        map.put("visitorId",SharedUtils.getString("id"));
        map.put("deviceNumber",imei);
        map.put("status","0");  //导航状态(0:正在导航 1:结束导航)
        map.put("vehicle","");
        map.put("startTime",System.currentTimeMillis());
        map.put("endTime","");
        map.put("timeOfUse","");
        map.put("startAddress",tv_bottom_current_location.getText().toString().trim());
        map.put("endAddress",tv_bottom_destination_location.getText().toString().trim());
        map.put("longitude",startPt.longitude);
        map.put("latitude",startPt.latitude);
        map.put("positioningTime",System.currentTimeMillis());
        Retrofit retrofit = netUtils.getRetrofit();
        apiManager manager= retrofit.create(apiManager.class);
        final RequestBody requestBody = requestBodyUtils.saveLocationMessage(map);
        Call<String> call = manager.saveLocationMessage(requestBody);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String body = response.body();
                logUtils.d("保存定位信息"+body);
                if (!TextUtils.isEmpty(body)) {
                    Gson gson = new Gson();
                    saveLocationMessageBean data = gson.fromJson(body, new TypeToken<saveLocationMessageBean>() {
                    }.getType());
                    String statusID = data.getStatusID();
                    if (statusID.equals("200")){
                        saveLocationMessageBean.DataBean data1 = data.getData();
                       positionGuid = data1.getPositionGuid();
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

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
                Log.d(TAG, "BikeNavi onRoutePlanFail"+error.toString());
                ToastUtil.showToast(MainActivity.this,"距离太远支持骑行");
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
                Log.d(TAG, "WalkNavi onRoutePlanFail"+error.toString());
                ToastUtil.showToast(MainActivity.this,"距离太远支持步行");
            }

        });
    }

    /**
     * 加载数据
     */
    List<visitItemBean.DataBean> visitData;
    List <visitItemBean.DataBean> applyData;
    List <mapInfoBean>mapInfoData; //地图展示的数据
    @Override
    protected void initData() {
        applyData=new ArrayList();
        visitData=new ArrayList<>();
        addressData=new ArrayList();
        mapInfoData=new ArrayList<>();
        String userName = SharedUtils.getString("account");
        logUtils.d("userName"+userName);
        Retrofit retrofit = netUtils.getRetrofit();
        apiManager manager= retrofit.create(apiManager.class);
        RequestBody requestBody = requestBodyUtils.visitItemToService(userName,true);
        Call<String> call = manager.visitListFormeService(requestBody);
        applyData.clear();
        visitData.clear();
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String body = response.body();
                logUtils.d("body"+body);
                if (!TextUtils.isEmpty(body)){
                    Gson gson=new Gson();
                    visitItemBean bean = gson.fromJson(body, new TypeToken<visitItemBean>() {}.getType());
                    String statusID = bean.getStatusID();
                    if (statusID.equals("200")){
                        List<visitItemBean.DataBean> data = bean.getData();
                        Iterator<visitItemBean.DataBean> iterator = data.iterator();
                        while (iterator.hasNext()){
                            visitItemBean.DataBean next = iterator.next();
                            visitData.add(next);
                            String name = next.getName();
                            logUtils.d("债务人名字："+name);
                            applyData.add(next);
                            String address = next.getAddress();

                         // TODO地址遍历 加载再地图上  放在子线程里面去做  防止数据太多 阻塞主线程
                  if (!TextUtils.isEmpty(address)){
                      if (!addressData.contains(address)){  //过滤掉相同的地址信息
                          addressData.add(address);
                          mSearch.geocode(new GeoCodeOption()
                                  .city("")
                                  .address(address));
                          logUtils.d("地址反编码"+":"+address);
                          mapInfoData.add(new mapInfoBean(name,next.getVisitStatusText(),next.getCustomerName(),next.getAddress(),next.getCaseCode(),next.getVisitGuid()));
                      }
                            }
                        }
                    }
                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });


    }



    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时必须调用mMapView. onResume ()
        mMapView.onResume();
     //   mNaviHelper.resume();
        Timer timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                getIntentData();  //可以延迟几秒执行 等待地图加载完成 在加载传过来的地址信息
                logUtils.d("onResume()走了");
            }
        },2000);

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

        mLocationClient.stopIndoorMode();
        mLocationClient.removeNotifyEvent(myLocationListener);
        mMapView.setMapCustomEnable(false);  //开启个性化地图
      //  mNaviHelper.quit();
        if (mSearch!=null){
            mSearch.destroy();
        }
        map.clear();
        if (marker!=null){
            marker.remove();
        }
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
        locationOption.setScanSpan(2000);

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
       // Log.d("TAG","  locationClient");
    }
    /**
     * 实现定位回调
     */
    public class MyLocationListener extends BDAbstractLocationListener{
        @Override
        public void onReceiveLocation(BDLocation location){
            curlatitude = location.getLatitude();
           curlongitude = location.getLongitude();
             navigateTo(location);
          startPt=new LatLng(curlatitude,curlongitude);
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
    String locationDescribe;
    private void  navigateTo(BDLocation location){
        if (isFirstLocate){
            isFirstLocate = false;
           ll = new LatLng(location.getLatitude(),location.getLongitude());

            // 设置地图缩放比例：17级100米
            MapStatusUpdate ms = MapStatusUpdateFactory.zoomTo(15);
            map.setMapStatus(ms);
	        /*判断baiduMap是已经移动到指定位置*/
    /*        if ( map.getLocationData()!=null)
                if ( map.getLocationData().latitude==location.getLatitude()
                        && map.getLocationData().longitude==location.getLongitude()){

                }*/
            map.setMyLocationEnabled(true);
            center2myLoc(location.getLatitude(),location.getLongitude());
            Log.d("TAG","第一次走了");
        }

        // 构造定位数据
        MyLocationData locData = new MyLocationData.Builder()
           //   .accuracy(location.getRadius())
               .accuracy(0)   //去掉光圈
                // 此处设置开发者获取到的方向信息，顺时针0-360
                .direction(0).latitude(location.getLatitude())
                .longitude(location.getLongitude()).build();
        map.setMyLocationData(locData);
        map.setMyLocationConfigeration(new MyLocationConfiguration(MyLocationConfiguration.LocationMode.NORMAL,true,null));
        locationDescribe = location.getLocationDescribe();
        tv_location.setText(location.getProvince()+location.getCity()+locationDescribe);
        curentLcotion=location.getProvince()+location.getCity()+locationDescribe;
        tv_bottom_current_location.setText("当前位置："+curentLcotion);
       Log.d("TAG","位置描述+curentLcotion"+curentLcotion);

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

    public  void  uploadLocationMessageToService(){
        Timer timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
        Map<String,Object> map=new HashMap<>();
         map.put("caseCode",caseCodeNumber);
         map.put("status","0");  //  上传定位点信息，状态 0："进行中" 1："已完成" 2："暂停中"
          map.put("visitGuid",batchNumber);
        map.put("positionGuid",positionGuid);
        map.put("longitude",ll.longitude);
        map.put("latitude",ll.latitude);
        map.put("positioningTime",System.currentTimeMillis());
        Retrofit retrofit = netUtils.getRetrofit();
        apiManager manager= retrofit.create(apiManager.class);
        final RequestBody requestBody = requestBodyUtils.uploadLocationMessage(map);
        Call<String> call = manager.uploadLocationMessage(requestBody);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String body = response.body();
                logUtils.d("定位数据上传"+body);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
            }
        },2000,30000);
    }

}



