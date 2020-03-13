package takjxh.android.com.taapp.activityui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
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
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.LatLngBounds;
import com.baidu.mapapi.utils.DistanceUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;
import takjxh.android.com.commlibrary.image.ImageWrapper;
import takjxh.android.com.commlibrary.utils.BarUtil;
import takjxh.android.com.commlibrary.view.activity.BaseActivity;
import takjxh.android.com.taapp.R;
import takjxh.android.com.taapp.activityui.bean.PolicyApplyHelpDetailBean;
import takjxh.android.com.taapp.activityui.presenter.ZjsbQzDetailPresenter;
import takjxh.android.com.taapp.activityui.presenter.impl.IZjsbQzDetailPresenter;
import takjxh.android.com.taapp.view.NormalTitleBar;

/**
 * 类名称：某某的求助详情
 *
 * @Author: libaibing
 * @Date: 2019-10-16 13:47
 * @Description:
 **/
public class ZjsbQzDetailActivity extends BaseActivity<ZjsbQzDetailPresenter> implements IZjsbQzDetailPresenter.IView, View.OnClickListener {

    public static void startAction(Activity activity, String id) {
        Intent intent = new Intent(activity, ZjsbQzDetailActivity.class);
        intent.putExtra("id", id);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @BindView(R.id.icon)
    ImageView icon;
    @BindView(R.id.tvtitle)
    TextView tvtitle;

    @BindView(R.id.iv_avatar)
    CircleImageView iv_avatar;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tv_extra)
    TextView tv_extra;
    @BindView(R.id.tv_extra1)
    TextView tv_extra1;
    @BindView(R.id.tv_extra2)
    TextView tv_extra2;


    @BindView(R.id.ntb)
    NormalTitleBar ntb;
    @BindView(R.id.bmapView)
    MapView mMapView;

    BaiduMap mBaiduMap;
    private Marker mMarkerA;
    BitmapDescriptor bdA = BitmapDescriptorFactory.fromResource(R.drawable.arrow2);
    private InfoWindow mInfoWindow;
    private LatLng cur;
    private LatLng llALatLng = new LatLng(0.0, 0.0);
    private double dis = 0;

    // 定位相关
    LocationClient mLocClient;
    public MyLocationListenner myListener = new MyLocationListenner();
    private MyLocationConfiguration.LocationMode mCurrentMode;
    BitmapDescriptor mCurrentMarker;
    TextView button;

    private String id;


    /**
     * 返回布局文件
     */
    @Override
    protected int getContentViewId() {
        return R.layout.activity_zjsbqz_detail;
    }

    @Override
    protected ZjsbQzDetailPresenter createPresenter() {
        return new ZjsbQzDetailPresenter(this);
    }

    @Override
    protected void beforeSetContentView() {
        super.beforeSetContentView();

        BarUtil.hideActionBar(this);
    }

    /**
     * 初始化控件
     */
    @Override
    protected void initView() {
        super.initView();
        id = getIntent().getStringExtra("id");

        ntb = findViewById(R.id.ntb);
        ntb.setTitleText("求助详情");
        ntb.setTvLeftVisiable(true);
        ntb.setOnBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mBaiduMap = mMapView.getMap();


        mCurrentMode = MyLocationConfiguration.LocationMode.NORMAL;
        mBaiduMap.setMyLocationConfiguration(new MyLocationConfiguration(
                mCurrentMode, true, mCurrentMarker));

        MapStatus.Builder builder1 = new MapStatus.Builder();
        builder1.overlook(0);
        mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder1.build()));


        mBaiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(final Marker marker) {


                if (marker == mMarkerA) {

                    mBaiduMap.showInfoWindow(mInfoWindow);
                }

                return true;
            }
        });


        // 开启定位图层
        mBaiduMap.setMyLocationEnabled(true);
        // 定位初始化
        mLocClient = new LocationClient(this);
        mLocClient.registerLocationListener(myListener);
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(1000);
        mLocClient.setLocOption(option);
        mLocClient.start();
    }

    /**
     * 设置事件
     */
    @Override
    protected void initEvent() {
        super.initEvent();
    }

    /**
     * 初始化数据
     */
    @Override
    protected void initData() {
        super.initData();
        mPresenter.policyapplyhelpdetail("", id);
    }

    boolean isFirstLoc = false; // 是否首次定位
    private int mCurrentDirection = 0;
    private double mCurrentLat = 0.0;
    private double mCurrentLon = 0.0;
    private float mCurrentAccracy;
    private MyLocationData locData;

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void policyapplyhelpdetailSuccess(PolicyApplyHelpDetailBean.HelpBean data) {
        if (data == null) {
            return;
        }
        llALatLng = new LatLng(Double.valueOf(data.getLat()), Double.valueOf(data.getLng()));

        initOverlay();

        MapStatus.Builder builder = new MapStatus.Builder();
        float zoom = 17f; // 地图缩放级别
        builder.target(llALatLng).zoom(zoom);
        mMapView.getMap().setMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
        MapView.setMapCustomEnable(false);
        isFirstLoc = true;

        tvtitle.setText(data.getTip());
        if (TextUtils.isEmpty(data.getCover())) {
            iv_avatar.setImageResource(R.mipmap.head_man_offline);
        } else {
            ImageWrapper.setImage(iv_avatar, data.getCover(), R.mipmap.head_man_offline, ImageWrapper.CENTER_CROP);

        }
        tvName.setText("求助人：" + data.getLinkman());
        tv_extra.setText("联系电话：" + data.getLinkmanPhne());
        tv_extra1.setText("求助内容：" + data.getDesc());
        tv_extra2.setText("求助时间：" + data.getTime());


    }

    @Override
    public void policyapplyhelpdetailFailed() {

    }

    /**
     * 定位SDK监听函数
     */
    public class MyLocationListenner extends BDAbstractLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            // map view 销毁后不在处理新接收的位置
            if (location == null || mMapView == null) {
                return;
            }
            mCurrentLat = location.getLatitude();
            mCurrentLon = location.getLongitude();
            mCurrentAccracy = location.getRadius();
            locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(mCurrentDirection).latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();
            mBaiduMap.setMyLocationData(locData);
            if (isFirstLoc) {
                isFirstLoc = false;
                LatLng ll = new LatLng(location.getLatitude(),
                        location.getLongitude());
                MapStatus.Builder builder = new MapStatus.Builder();
                builder.target(ll).zoom(17.0f);
                mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
                cur = new LatLng(mCurrentLat, mCurrentLon);
                //计算p1、p2两点之间的直线距离，单位：米
                dis = DistanceUtil.getDistance(cur, llALatLng);

                button = new TextView(getApplicationContext());
                button.setBackgroundResource(R.drawable.popup);
                InfoWindow.OnInfoWindowClickListener listener = null;
                if (dis < 100) {
                    button.setText("距您位置" + dis + "米");
                } else {
                    BigDecimal b = new BigDecimal(dis / 1000).setScale(2, BigDecimal.ROUND_HALF_UP);
                    button.setText("距您位置" + b.doubleValue() + "公里");
                }
                button.setTextColor(Color.BLACK);
                button.setGravity(Gravity.CENTER);
                button.setPadding(15, 5, 15, 5);
                listener = new InfoWindow.OnInfoWindowClickListener() {
                    @Override
                    public void onInfoWindowClick() {

                        mBaiduMap.hideInfoWindow();
                    }
                };
                LatLng lll = mMarkerA.getPosition();
                mInfoWindow = new InfoWindow(BitmapDescriptorFactory.fromView(button), lll, -47, listener);
                mBaiduMap.showInfoWindow(mInfoWindow);


                final List<LatLng> points = new ArrayList<>();
                points.add(llALatLng);
                points.add(ll);
                //将所有点的经纬度放在一个集合中。
                LatLngBounds.Builder builder1 = new LatLngBounds.Builder();
                for (LatLng p : points) {
                    builder1 = builder1.include(p);
                }

                LatLngBounds latlngBounds = builder1.build();
                MapStatusUpdate us = MapStatusUpdateFactory.newLatLngBounds(builder1.build());
                MapStatusUpdateFactory.newLatLngBounds(latlngBounds, mMapView.getWidth(), mMapView.getHeight());
                mBaiduMap.animateMapStatus(us);

                mBaiduMap.setMapStatus(us);
                MapStatusUpdate msu = MapStatusUpdateFactory.zoomBy(-0.8f);
                mBaiduMap.setMapStatus(msu);


            }
        }

        public void onReceivePoi(BDLocation poiLocation) {
        }
    }


    public void initOverlay() {
        // add marker overlay

        MarkerOptions ooA = new MarkerOptions().position(llALatLng).icon(bdA).zIndex(9).draggable(true);
        mMarkerA = (Marker) (mBaiduMap.addOverlay(ooA));
        mBaiduMap.setOnMarkerDragListener(new BaiduMap.OnMarkerDragListener() {
            @Override
            public void onMarkerDrag(Marker marker) {

            }

            @Override
            public void onMarkerDragEnd(Marker marker) {
                Toast.makeText(
                        ZjsbQzDetailActivity.this,
                        "拖拽结束，新位置：" + marker.getPosition().latitude + ", " + marker.getPosition().longitude,
                        Toast.LENGTH_LONG).show();
            }

            @Override
            public void onMarkerDragStart(Marker marker) {
            }
        });
    }


    @Override
    public void onClick(View v) {

    }

    @Override
    protected void onPause() {
        super.onPause();
        // activity 暂停时同时暂停地图控件
        mMapView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // activity 恢复时同时恢复地图控件
        mMapView.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 销毁地图前线关闭个性化地图，否则会出现资源无法释放
        MapView.setMapCustomEnable(false);
        // activity 销毁时同时销毁地图控件
        mMapView.onDestroy();
        // 回收 bitmap 资源
        bdA.recycle();

    }

}