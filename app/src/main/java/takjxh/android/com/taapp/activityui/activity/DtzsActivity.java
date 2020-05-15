package takjxh.android.com.taapp.activityui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


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
import com.baidu.mapapi.model.LatLng;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import takjxh.android.com.commlibrary.adapter.recycler.BaseRecyclerAdapter;
import takjxh.android.com.commlibrary.utils.BarUtil;
import takjxh.android.com.commlibrary.view.activity.BaseActivity;
import takjxh.android.com.taapp.R;
import takjxh.android.com.taapp.activityui.adapter.DtdsAdapter;
import takjxh.android.com.taapp.activityui.base.BaseDialog;
import takjxh.android.com.taapp.activityui.bean.CompanyTypesBean;
import takjxh.android.com.taapp.activityui.bean.CompanysBean;
import takjxh.android.com.taapp.activityui.dialog.InputIosDialog;
import takjxh.android.com.taapp.activityui.dialog.MenuIosDialog;
import takjxh.android.com.taapp.activityui.popupwindow.utils.FitPopupUtil;
import takjxh.android.com.taapp.activityui.presenter.DtzsPresenter;
import takjxh.android.com.taapp.activityui.presenter.impl.IDtzsPresenter;
import takjxh.android.com.taapp.view.mulitmenuselect.Children;
import takjxh.android.com.taapp.view.mulitmenuselect.ChildrenUtil;
import takjxh.android.com.taapp.view.mulitmenuselect.ThirdDialog2;

/**
 * 类名称：地图展示
 *
 * @Author: libaibing
 * @Date: 2019-10-16 15:54
 * @Description:
 **/
public class DtzsActivity extends BaseActivity<DtzsPresenter> implements IDtzsPresenter.IView, View.OnClickListener {

    public static void startAction(Activity activity) {
        Intent intent = new Intent(activity, DtzsActivity.class);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @BindView(R.id.refreshLayout)
    RefreshLayout mRefreshLayout;
    @BindView(R.id.bmapView)
    MapView mMapView;


    @BindView(R.id.tv_definition)
    TextView tv_definition;

    @BindView(R.id.iv)
    ImageView iv;
    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;

    private DtdsAdapter mDtdsAdapter;
    private List<CompanysBean.CompanyBean> mList = new ArrayList<>();
    private List<CompanyTypesBean.CompanyTypeBean> mData = new ArrayList<>();
    BaiduMap mBaiduMap;

    private int pos = 0;
    /**
     * 初始化全局 bitmap 信息，不用时及时 recycle
     */
    BitmapDescriptor bdA = BitmapDescriptorFactory.fromResource(R.drawable.arrow1);

    private String key = "";
    private String typeId = "";
    private String typeName = "";


    private int pageIndex = 1;
    private int pageSize = 20;
    private int total = 0;
    private boolean isLoadMore = false;

    private InfoWindow mInfoWindow;

    private int checkItemPosition = 0;


    private List<Children> list1 = new ArrayList<>();
    private List<Children> treeItemBeanList = new ArrayList<>();


    /**
     * 返回布局文件
     */
    @Override
    protected int getContentViewId() {
        return R.layout.activity_dtzs;
    }

    @Override
    protected DtzsPresenter createPresenter() {
        return new DtzsPresenter(this);
    }

    @Override
    protected void beforeSetContentView() {
        super.beforeSetContentView();
        BarUtil.fullScreen(this);
        BarUtil.hideActionBar(this);
    }

    /**
     * 初始化控件
     */
    @Override
    protected void initView() {
        super.initView();

        recycler_view.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false));
        mDtdsAdapter = new DtdsAdapter(this);
        mDtdsAdapter.setOnRecyclerItemClickListener(new BaseRecyclerAdapter.OnRecyclerItemClickListener<CompanysBean.CompanyBean>() {
            @Override
            public void onRecyclerItemClicked(BaseRecyclerAdapter adapter, View view, CompanysBean.CompanyBean data, int position) {
                if (pos != position) {
                    pos = position;
                    clearOverlay();
                    resetOverlay(data.getName(), new LatLng(Double.valueOf(data.getLat()), Double.valueOf(data.getLng())));
                    for (CompanysBean.CompanyBean bean : mList) {
                        bean.isSelect = false;
                    }
                    mList.get(position).isSelect = true;
                    for (CompanysBean.CompanyBean bean : mList) {
                        mDtdsAdapter.update(bean);
                    }
                }

            }
        });
        recycler_view.setAdapter(mDtdsAdapter);


        // mList.add(new CompanyTypesBean.CompanyTypeBean(new LatLng(24.65163477927172, 118.17398405848407)));
        mDtdsAdapter.set(mList);

        mBaiduMap = mMapView.getMap();

        setRefresh();

    }

    Marker mMarkerA;

    public void initOverlay(String address, LatLng llA) {

        MapStatus.Builder builder = new MapStatus.Builder();
        float zoom = 14f; // 地图缩放级别
        builder.target(llA).zoom(zoom);
        mMapView.getMap().setMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
        MapView.setMapCustomEnable(false);

        MapStatusUpdate msu = MapStatusUpdateFactory.zoomTo(14.0f);
        mBaiduMap.setMapStatus(msu);


        // add marker overlay
        MarkerOptions ooA = new MarkerOptions().position(llA).icon(bdA).zIndex(9).draggable(true);
        mMarkerA = (Marker) (mBaiduMap.addOverlay(ooA));

        mBaiduMap.setOnMarkerDragListener(new BaiduMap.OnMarkerDragListener() {
            @Override
            public void onMarkerDrag(Marker marker) {

            }

            @Override
            public void onMarkerDragEnd(Marker marker) {
                Toast.makeText(
                        DtzsActivity.this,
                        "拖拽结束，新位置：" + marker.getPosition().latitude + ", " + marker.getPosition().longitude,
                        Toast.LENGTH_LONG).show();
            }

            @Override
            public void onMarkerDragStart(Marker marker) {
            }
        });

        TextView button = new TextView(getApplicationContext());
        button.setBackgroundResource(R.color.dtzs);
        InfoWindow.OnInfoWindowClickListener listener = null;
        button.setText(address);
        button.setTextColor(Color.WHITE);
        button.setGravity(Gravity.CENTER);
        button.setPadding(25, 35, 25, 35);
        listener = new InfoWindow.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick() {

                mBaiduMap.hideInfoWindow();
            }
        };
        LatLng lll = mMarkerA.getPosition();
        mInfoWindow = new InfoWindow(BitmapDescriptorFactory.fromView(button), lll, -47, listener);
        mBaiduMap.showInfoWindow(mInfoWindow);

        mBaiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(final Marker marker) {


                if (marker == mMarkerA) {

                    mBaiduMap.showInfoWindow(mInfoWindow);
                }

                return true;
            }
        });

    }

    /**
     * 清除所有Overlay
     */
    public void clearOverlay() {
        mBaiduMap.clear();
        mMarkerA = null;

    }


    /**
     * 重新添加Overlay
     */
    public void resetOverlay(String address, LatLng llA) {
        clearOverlay();
        initOverlay(address, llA);
    }

    /**
     * 设置事件
     */
    @Override
    protected void initEvent() {
        super.initEvent();
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tv_definition.setOnClickListener(this);
    }

    /**
     * 初始化数据
     */
    @Override
    protected void initData() {
        super.initData();
        mPresenter.tradetreelistt();
        // mPresenter.companytypelist("");
        mPresenter.companyslist("", "", typeId, "" + pageIndex, "" + pageSize);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_definition:

                InputIosDialog.Builder mInputIosDialog = new InputIosDialog.Builder(DtzsActivity.this);

                mInputIosDialog
                        .setTitleBt(key)
                        .setSshyID(typeName, typeId)
                        .setTitle("筛选条件")
                        // 确定按钮文本
                        .setConfirm("确定")
                        // 设置 null 表示不显示取消按钮
                        .setCancel(null)
                        .setListener(new InputIosDialog.OnListener() {
                            @Override
                            public void onSelected(BaseDialog dialog, String msg, String sshyID, String sshyName) {
                                typeName = sshyName;
                                typeId = sshyID;
                                key = msg;

                                isLoadMore = false;
                                pageIndex = 1;
                                mPresenter.companyslist("", key, typeId, "" + pageIndex, "" + pageSize);
                                dialog.dismiss();
                            }

                            @Override
                            public void onCancel(BaseDialog dialog) {
                                // toast("取消了");
                            }

                            @Override
                            public void onSel(AutoCompleteTextView tv_sshy) {
                                String[] dictionary = new String[list1.size()];
                                for (int i = 0; i < list1.size(); i++) {
                                    dictionary[i] = list1.get(i).getName();
                                }
                                //利用适配器
                                ArrayAdapter<String> adapter_actv = new ArrayAdapter<String>(
                                        DtzsActivity.this, android.R.layout.simple_dropdown_item_1line, dictionary);
                                tv_sshy.setAdapter(adapter_actv);

                                tv_sshy.setThreshold(1);
                                tv_sshy.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                                    @Override
                                    public void onFocusChange(View v, boolean hasFocus) {
                                        if (hasFocus) {//获取焦点时
                                            tv_sshy.showDropDown();
                                        }
                                    }
                                });
                                tv_sshy.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                        // sshyID = list1.get(position).getId();
                                        mInputIosDialog.setSshyID(list1.get(position).getId());
                                    }
                                });


                                ThirdDialog2 dialog = new ThirdDialog2(DtzsActivity.this, treeItemBeanList);
                                dialog.setonItemClickListener(new ThirdDialog2.DictItemClickListener() {
                                    @Override
                                    public void onDictItemClick(Children dictUnit) {
                                        if (dictUnit != null) {
                                            tv_sshy.setText(dictUnit.getName());
                                            //sshyID = dictUnit.getId();
                                            mInputIosDialog.setSshyID(dictUnit.getId());
                                        }
                                    }
                                });
                                dialog.show();
                            }
                        })
                        .show();


                break;
        }
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


/*
    private void initPopup(View anchorView) {
        FitPopupUtil  fitPopupUtil = new FitPopupUtil(this,list,checkItemPosition);
        fitPopupUtil.setOnClickListener(new FitPopupUtil.OnCommitClickListener() {
            @Override
            public void onClick(String reason, int position) {
                checkItemPosition=position;
                type = mData.get(position).getCode();
                tv_definition.setText(reason);

                isLoadMore = false;
                pageIndex = 1;
                mPresenter.companyslist("", type, "" + pageIndex, "" + pageSize);
            }
        });
        fitPopupUtil.showPopup(anchorView);
    }
*/


    @Override
    public Context getContext() {
        return this;
    }

    private List<String> list = new ArrayList<>();

    @Override
    public void tradetreelisttSuccess(List<Children> bean) {
        if (bean == null) {
            return;
        }
        treeItemBeanList.clear();
        treeItemBeanList.addAll(bean);

        list1.clear();
        list1 = ChildrenUtil.getSelList(treeItemBeanList);


    }

    @Override
    public void tradetreelisttFailed() {

    }

    @Override
    public void companytypelistSuccess(List<CompanyTypesBean.CompanyTypeBean> data) {
       /* if (data == null) {
            return;
        }
        mData.clear();
        list.clear();
        mData.add(new CompanyTypesBean.CompanyTypeBean("", "不限"));
        for (CompanyTypesBean.CompanyTypeBean bean : data) {
            mData.add(bean);
        }

        for (CompanyTypesBean.CompanyTypeBean bean : mData) {
            list.add(bean.getValue());
        }*/

    }

    @Override
    public void companytypelistFailed() {
        /*mData.clear();
        list.clear();
        mData.add(new CompanyTypesBean.CompanyTypeBean("", "不限"));
        for (CompanyTypesBean.CompanyTypeBean bean : mData) {
            list.add(bean.getValue());
        }*/
    }

    @Override
    public void companyslistSuccess(List<CompanysBean.CompanyBean> data) {
        if (data == null) {
            return;
        }
        if (!isLoadMore) {
            mList.clear();
            mList.addAll(data);
            if (mList.size() > 0) {
                mList.get(0).isSelect = true;
                pos = 0;
                initOverlay(mList.get(0).getName(), new LatLng(Double.valueOf(mList.get(0).getLat()), Double.valueOf(mList.get(0).getLng())));
            }
        } else {
            mList.addAll(data);
        }

        mDtdsAdapter.set(mList);


    }

    @Override
    public void companyslistFailed() {

    }


    /**
     * 设置上拉/下拉监听器
     */
    private void setRefresh() {

        mRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onRefresh(@NonNull final RefreshLayout refreshLayout) {
                refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        isLoadMore = false;
                        pageIndex = 1;

                        mPresenter.companyslist("", key, typeId, "" + pageIndex, "" + pageSize);
                        refreshLayout.finishRefresh();
                    }
                }, 1000);
            }

            @Override
            public void onLoadMore(@NonNull final RefreshLayout refreshLayout) {
                recycler_view.stopScroll();
                recycler_view.stopNestedScroll();
                refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadMore();
                        refreshLayout.finishLoadMore();
                    }
                }, 1000);
            }
        });


    }


    /**
     * 更多
     */
    private void loadMore() {
        isLoadMore = true;
        pageIndex++;
        mPresenter.companyslist("", key, typeId, "" + pageIndex, "" + pageSize);
    }


}
