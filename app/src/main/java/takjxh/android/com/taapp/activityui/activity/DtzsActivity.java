package takjxh.android.com.taapp.activityui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import takjxh.android.com.taapp.activityui.chat.takevideo.utils.LogUtils;
import takjxh.android.com.taapp.activityui.dialog.InputIosDialog;
import takjxh.android.com.taapp.activityui.dialog.MenuIosDialog;
import takjxh.android.com.taapp.activityui.popupwindow.utils.FitPopupUtil;
import takjxh.android.com.taapp.activityui.presenter.DtzsPresenter;
import takjxh.android.com.taapp.activityui.presenter.impl.IDtzsPresenter;
import takjxh.android.com.taapp.view.mulitmenuselect.Children;
import takjxh.android.com.taapp.view.mulitmenuselect.ChildrenUtil;
import takjxh.android.com.taapp.view.mulitmenuselect.MultiDialogActivity;

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
    private ArrayList<Children> treeItemBeanList = new ArrayList<>();

    //定义请求码常量
    private static final int REQUEST_CODE_Company = 25;

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
                    resetOverlay(data, new LatLng(Double.valueOf(data.getLat()), Double.valueOf(data.getLng())));
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

    public void initOverlay(CompanysBean.CompanyBean item, LatLng llA) {

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
        LinearLayout ml = new LinearLayout(getApplicationContext());
        ml.setOrientation(LinearLayout.VERTICAL);
        /*LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.gravity = Gravity.CENTER_VERTICAL| Gravity.LEFT;*/
        ml.setGravity(Gravity.CENTER | Gravity.LEFT);

        TextView button = new TextView(getApplicationContext());
        button.setBackgroundResource(R.color.dtzs);
        //button.setText(item.getName() +"\n"+"注册时间："+item.getRegTime()+"\n"+item.getRegAddr() +"\n"+"营业收入："+item.getIncome()+"\n"+"从业人数："+item.getScale()   );
        button.setText(item.getName());
        button.setTextSize(17);
        button.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        button.setTextColor(Color.WHITE);
        button.setPadding(15, 15, 15, 0);
        ml.addView(button);

        button = new TextView(getApplicationContext());
        button.setBackgroundResource(R.color.dtzs);
        button.setText("注册地址：" + item.getRegAddr());
        button.setTextSize(12);
        button.setTextColor(Color.WHITE);
        button.setPadding(15, 15, 15, 0);
        ml.addView(button);


        button = new TextView(getApplicationContext());
        button.setBackgroundResource(R.color.dtzs);
        button.setText("注册时间：" + item.getRegTime());
        button.setTextSize(12);
        button.setTextColor(Color.WHITE);
        button.setPadding(15, 15, 15, 0);
        ml.addView(button);


        button = new TextView(getApplicationContext());
        button.setBackgroundResource(R.color.dtzs);
        button.setText("营业收入：" + item.getIncome());
        button.setTextSize(12);
        button.setTextColor(Color.WHITE);
        button.setPadding(15, 15, 15, 0);
        ml.addView(button);


        button = new TextView(getApplicationContext());
        button.setBackgroundResource(R.color.dtzs);
        button.setText("从业人数：" + item.getScale());
        button.setTextSize(12);
        button.setTextColor(Color.WHITE);
        button.setPadding(15, 15, 15, 15);
        ml.addView(button);


        InfoWindow.OnInfoWindowClickListener listener = null;
        listener = new InfoWindow.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick() {

                mBaiduMap.hideInfoWindow();
            }
        };
        LatLng lll = mMarkerA.getPosition();
        mInfoWindow = new InfoWindow(BitmapDescriptorFactory.fromView(ml), lll, -47, listener);
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
    public void resetOverlay(CompanysBean.CompanyBean address, LatLng llA) {
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

    InputIosDialog.Builder mInputIosDialog;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_definition:

                mInputIosDialog = new InputIosDialog.Builder(DtzsActivity.this);
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
                            public void onSel(TextView tv_sshy) {
                                /*String[] dictionary = new String[list1.size()];
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
                                });*/

                                MultiDialogActivity.startAction(DtzsActivity.this, "选择所属行业", treeItemBeanList, REQUEST_CODE_Company);
                               /* ThirdDialog2 dialog = new ThirdDialog2(DtzsActivity.this, treeItemBeanList);
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
                                dialog.show();*/
                            }
                        })
                        .show();


                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_Company && resultCode == RESULT_OK) {
            if (data != null) {
                Children dictUnit = data.getParcelableExtra("dictUnit");
                if (dictUnit != null) {
                    // tv_sshy.setText(dictUnit.getName());
                    if (mInputIosDialog != null) {
                        // mInputIosDialog.setSshyID(dictUnit.getName(),dictUnit.getId());

                    }

                    mInputIosDialog = new InputIosDialog.Builder(DtzsActivity.this);
                    mInputIosDialog
                            .setTitleBt(key)
                            .setSshyID(dictUnit.getName(), dictUnit.getId())
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
                                public void onSel(TextView tv_sshy) {
                                    /*list1.clear();
                                    list1 = ChildrenUtil.getSelList(treeItemBeanList);
                                    LogUtils.e("----onSel----size-------------:" + list1.size());
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

                                    tv_sshy.setOnKeyListener(new View.OnKeyListener()
                                    {
                                        // 添加软键盘事件（让软键盘有一个搜索的图标）
                                        @Override
                                        public boolean onKey(View v, int keyCode, KeyEvent event)
                                        {
                                            // TODO Auto-generated method stub
                                            if (keyCode == KeyEvent.KEYCODE_ENTER)
                                            {
                                                if (event.getAction() == KeyEvent.ACTION_UP)
                                                {
                                                    InputMethodManager imm = (InputMethodManager) v
                                                            .getContext().getSystemService(
                                                                    Context.INPUT_METHOD_SERVICE);
                                                    if (imm.isActive())
                                                    {
                                                        imm.hideSoftInputFromWindow(v
                                                                .getApplicationWindowToken(), 0);
                                                    }
                                                    return true;
                                                }

                                            }
                                            return false;
                                        }
                                    });
                                    tv_sshy.setOnClickListener(new View.OnClickListener() {

                                        @Override
                                        public void onClick(View v)
                                        {
                                            tv_sshy.showDropDown();
                                        }
                                    });*/


                                    MultiDialogActivity.startAction(DtzsActivity.this, "选择所属行业", treeItemBeanList, REQUEST_CODE_Company);
                                /*ThirdDialog2 dialog = new ThirdDialog2(DtzsActivity.this, treeItemBeanList);
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
                                dialog.show();*/
                                }
                            })
                            .show();
                }
            }
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
                initOverlay(mList.get(0), new LatLng(Double.valueOf(mList.get(0).getLat()), Double.valueOf(mList.get(0).getLng())));
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
