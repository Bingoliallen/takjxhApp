package takjxh.android.com.taapp.activityui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.flyco.tablayout.listener.OnTabSelectListener;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yyydjk.library.DropDownMenu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import takjxh.android.com.commlibrary.utils.BarUtil;
import takjxh.android.com.commlibrary.utils.ViewUtil;
import takjxh.android.com.commlibrary.view.activity.BaseActivity;
import takjxh.android.com.taapp.QbApplication;
import takjxh.android.com.taapp.R;
import takjxh.android.com.taapp.activityui.adapter.ZczxAdapter;
import takjxh.android.com.taapp.activityui.bean.PolicysBean;
import takjxh.android.com.taapp.activityui.bean.SysParamBean;
import takjxh.android.com.taapp.activityui.bean.TypesBean;
import takjxh.android.com.taapp.activityui.dropdownmenu.CommonUtil;
import takjxh.android.com.taapp.activityui.dropdownmenu.ConstellationAdapter;
import takjxh.android.com.taapp.activityui.dropdownmenu.DoubleListView;
import takjxh.android.com.taapp.activityui.dropdownmenu.FilterCheckedTextView;
import takjxh.android.com.taapp.activityui.dropdownmenu.FilterType;
import takjxh.android.com.taapp.activityui.dropdownmenu.FilterUrl;
import takjxh.android.com.taapp.activityui.dropdownmenu.GirdDropDownAdapter;
import takjxh.android.com.taapp.activityui.dropdownmenu.ListDropDownAdapter;
import takjxh.android.com.taapp.activityui.dropdownmenu.SimpleTextAdapter;
import takjxh.android.com.taapp.activityui.dropdownmenu.UIUtil;
import takjxh.android.com.taapp.activityui.presenter.ZczxPresenter;
import takjxh.android.com.taapp.activityui.presenter.impl.IZczxPresenter;
import takjxh.android.com.taapp.view.NormalTitleBar;

/**
 * 类名称：政策资讯 政策资讯详情里面也含有评论列表
 *
 * @Author: libaibing
 * @Date: 2019-10-16 9:10
 * @Description:
 **/
public class ZczxActivity extends BaseActivity<ZczxPresenter> implements IZczxPresenter.IView, OnTabSelectListener {

    public static void startAction(Activity activity, String type, String typeId) {
        Intent intent = new Intent(activity, ZczxActivity.class);
        intent.putExtra("type", type);
        intent.putExtra("typeId", typeId);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }


    @BindView(R.id.ntb)
    NormalTitleBar ntb;
    SmartRefreshLayout mRefreshLayout;
    RecyclerView recycler_view;


    private List<PolicysBean.PolicyInfosBean> mList = new ArrayList<>();
    private ZczxAdapter mZczxAdapter;

    private int pageIndex = 1;
    private int pageSize = 20;
    private int total = 0;
    private boolean isLoadMore = false;


    @BindView(R.id.dropDownMenu)
    DropDownMenu mDropDownMenu;
    private String headers[] = {"归属部门", "企业行业"};
    private List<View> popupViews = new ArrayList<>();

    private GirdDropDownAdapter cityAdapter;
    private ListDropDownAdapter ageAdapter;
    private ListDropDownAdapter sexAdapter;
    private ConstellationAdapter constellationAdapter;
    private ArrayList<TypesBean> mTypesBeanList = new ArrayList<>();


    private  List<String> cityslist=new ArrayList<>();
    private List<SysParamBean.ParamsBean.PolicycreteunitBean> policycreteunit = new ArrayList<>();//发布单位/部门
    private List<SysParamBean.ParamsBean.PolicycreteunitBean> policycreteunit2 = new ArrayList<>();//发布单位/部门
    private String createUnit = "";


    private  List<String> ageslist=new ArrayList<>();
    private List<SysParamBean.ParamsBean.UsertradeBean> usertrade=new ArrayList<>(); //行业
    private List<SysParamBean.ParamsBean.UsertradeBean> usertrade2=new ArrayList<>(); //行业
    private String trade = "";


    private String sexs[] = {"不限", "热门", "最新"};

    private String key = "";

    private int constellationPosition = 0;

    private ListView cityView;
    private ListView ageView;
    private ListView sexView;
    private GridView constellation;
    private LinearLayout mlSearch;
    private EditText edSearch;

    private String type;
    private String typeId;

    /**
     * 返回布局文件
     */
    @Override
    protected int getContentViewId() {
        return R.layout.activity_zczx;
    }

    @Override
    protected ZczxPresenter createPresenter() {
        return new ZczxPresenter(this);
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
        type = getIntent().getStringExtra("type");
        typeId = getIntent().getStringExtra("typeId");
        if(QbApplication.mBaseApplication.usertrade!=null ){
            usertrade.clear();
            usertrade.add(new SysParamBean.ParamsBean.UsertradeBean("","不限"));
            usertrade.addAll(QbApplication.mBaseApplication.usertrade);
            usertrade2.clear();
            usertrade2.addAll(QbApplication.mBaseApplication.usertrade);
        }
        ageslist.clear();
        for(SysParamBean.ParamsBean.UsertradeBean bean:usertrade){
            ageslist.add(bean.getValue());
        }



        if (QbApplication.mBaseApplication.policycreteunit != null) {
            policycreteunit.clear();
            policycreteunit.add(new SysParamBean.ParamsBean.PolicycreteunitBean("", "不限"));
            policycreteunit.addAll(QbApplication.mBaseApplication.policycreteunit);
            policycreteunit2.clear();
            policycreteunit2.addAll(QbApplication.mBaseApplication.policycreteunit);
        }
        cityslist.clear();
        for (SysParamBean.ParamsBean.PolicycreteunitBean bean : policycreteunit) {
            cityslist.add(bean.getValue());
        }



        ntb = findViewById(R.id.ntb);
        ntb.setTitleText("政策资讯");
        ntb.setTvLeftVisiable(true);
        ntb.setOnBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        //init city menu
        cityView = new ListView(this);
        cityAdapter = new GirdDropDownAdapter(this,cityslist);
        cityView.setDividerHeight(0);
        cityView.setAdapter(cityAdapter);

        //init age menu
        ageView = new ListView(this);
        ageView.setDividerHeight(0);
        ageAdapter = new ListDropDownAdapter(this, ageslist);
        ageView.setAdapter(ageAdapter);

        //init sex menu
        sexView = new ListView(this);
        sexView.setDividerHeight(0);
        sexAdapter = new ListDropDownAdapter(this, Arrays.asList(sexs));
        sexView.setAdapter(sexAdapter);

        //init constellation
        final View constellationView = getLayoutInflater().inflate(R.layout.custom_layout, null);

        constellation = constellationView.findViewById(R.id.constellation);
        constellationAdapter = new ConstellationAdapter(this, mTypesBeanList);
        constellation.setAdapter(constellationAdapter);
        TextView ok = constellationView.findViewById(R.id.ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDropDownMenu.setTabText(constellationPosition == 0 ? headers[3] : mTypesBeanList.get(constellationPosition).getValue());
                mDropDownMenu.closeMenu();
            }
        });

        //init popupViews
        popupViews.add(cityView);
        popupViews.add(ageView);
      //  popupViews.add(createDoubleListView());
        // popupViews.add(constellationView);

        //add item click event
        cityView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                createUnit = policycreteunit.get(position).getCode();
                cityAdapter.setCheckItem(position);
                mDropDownMenu.setTabText(position == 0 ? headers[0] : cityslist.get(position));
                mDropDownMenu.closeMenu();
                refresh();
            }
        });

        ageView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                trade = usertrade.get(position).getCode();
                ageAdapter.setCheckItem(position);
                mDropDownMenu.setTabText(position == 0 ? headers[1] : ageslist.get(position));
                mDropDownMenu.closeMenu();
                refresh();
            }
        });

        sexView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                sexAdapter.setCheckItem(position);
                mDropDownMenu.setTabText(position == 0 ? headers[2] : sexs[position]);
                mDropDownMenu.closeMenu();
            }
        });

        constellation.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                constellationAdapter.setCheckItem(position);
                constellationPosition = position;
                mDropDownMenu.setTabText(constellationPosition == 0 ? headers[3] : mTypesBeanList.get(constellationPosition).getValue());
                mDropDownMenu.closeMenu();
            }
        });

        //init context view
        View contentView = getLayoutInflater().inflate(R.layout.fragment_zczx, null);
        mRefreshLayout = (SmartRefreshLayout) contentView.findViewById(R.id.normal_view1);
        recycler_view = (RecyclerView) contentView.findViewById(R.id.recycler_view);

        mlSearch = (LinearLayout) contentView.findViewById(R.id.mlSearch);
        edSearch = (EditText) contentView.findViewById(R.id.edSearch);
        if (type.equals("1")) {
            mlSearch.setVisibility(View.VISIBLE);
        } else {
            mlSearch.setVisibility(View.GONE);
        }

        recycler_view.setLayoutManager(new LinearLayoutManager(ZczxActivity.this, LinearLayoutManager.VERTICAL, false));
        recycler_view.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.top = ViewUtil.dp2px(ZczxActivity.this, 0);
                outRect.bottom = ViewUtil.dp2px(ZczxActivity.this, 0);
            }
        });
        mZczxAdapter = new ZczxAdapter(ZczxActivity.this);
        recycler_view.setAdapter(mZczxAdapter);

        mZczxAdapter.set(mList);


        //init dropdownview
        mDropDownMenu.setDropDownMenu(Arrays.asList(headers), popupViews, contentView);

        ntb.getTvTitle().setFocusable(true);
        ntb.getTvTitle().setFocusableInTouchMode(true);
        ntb.getTvTitle().requestFocus();
        setRefresh();



    }

    /**
     * 设置事件
     */
    @Override
    protected void initEvent() {
        super.initEvent();

        edSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    //关闭软键盘
                    hideSoftKeyboard(v);

                    if (type.equals("1")) {
                        if(TextUtils.isEmpty(edSearch.getText().toString().trim())){
                            return true;
                        }
                        isLoadMore = false;
                        pageIndex = 1;
                        key = edSearch.getText().toString().trim();
                        mPresenter.policyslist("",typeId, key, createUnit, trade, "" + pageIndex, "" + pageSize);
                    }


                    return true;
                }
                return false;
            }
        });

    }

    /**
     * 初始化数据
     */
    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    public void onTabSelect(int position) {

    }

    @Override
    public void onTabReselect(int position) {

    }


    @Override
    public void onResume() {
        super.onResume();
        mRefreshLayout.autoRefresh();
    }

    /**
     * 设置上拉/下拉监听器
     */
    private void setRefresh() {
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                refresh();
                refreshLayout.finishRefresh(1000);
            }
        });
        mRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {

                loadMore();
                refreshLayout.finishLoadMore(1000);

            }
        });
    }
    private void refresh() {
        isLoadMore = false;
        pageIndex = 1;
        if (type.equals("1")) {
            key = edSearch.getText().toString().trim();
        } else {
            key = "";
        }
        mPresenter.policyslist("",typeId, key, createUnit, trade, "" + pageIndex, "" + pageSize);
    }

    /**
     * 更多
     */
    private void loadMore() {
        isLoadMore = true;
        pageIndex++;
        mPresenter.policyslist("",typeId, key, createUnit, trade, "" + pageIndex, "" + pageSize);
    }


    @Override
    public void onBackPressed() {
        //退出activity前关闭菜单
        if (mDropDownMenu.isShowing()) {
            mDropDownMenu.closeMenu();
        } else {
            super.onBackPressed();
        }
    }



    /**
     * 隐藏软键盘
     *
     * @param v
     */
    public static void hideSoftKeyboard(View v) {
        InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }


    private View createDoubleListView() {
        DoubleListView<FilterType, String> comTypeDoubleListView = new DoubleListView<FilterType, String>(ZczxActivity.this)
                .leftAdapter(new SimpleTextAdapter<FilterType>(null, ZczxActivity.this) {
                    @Override
                    public String provideText(FilterType filterType) {
                        return filterType.desc;
                    }

                    @Override
                    protected void initCheckedTextView(FilterCheckedTextView checkedTextView) {
                        checkedTextView.setPadding(UIUtil.dp(ZczxActivity.this, 44), UIUtil.dp(ZczxActivity.this, 15), 0, UIUtil.dp(ZczxActivity.this, 15));
                    }
                })
                .rightAdapter(new SimpleTextAdapter<String>(null, ZczxActivity.this) {
                    @Override
                    public String provideText(String s) {
                        return s;
                    }

                    @Override
                    protected void initCheckedTextView(FilterCheckedTextView checkedTextView) {
                        checkedTextView.setPadding(UIUtil.dp(ZczxActivity.this, 30), UIUtil.dp(ZczxActivity.this, 15), 0, UIUtil.dp(ZczxActivity.this, 15));
                        checkedTextView.setBackgroundResource(android.R.color.white);
                    }
                })
                .onLeftItemClickListener(new DoubleListView.OnLeftItemClickListener<FilterType, String>() {
                    @Override
                    public List<String> provideRightList(FilterType item, int position) {
                        List<String> child = item.child;
                        if (CommonUtil.isEmpty(child)) {
                            FilterUrl.instance().doubleListLeft = item.desc;
                            FilterUrl.instance().doubleListRight = "";

                            FilterUrl.instance().positionLeft = position;
                            if ("不限".equals(item.desc)) {
                                FilterUrl.instance().positionTitle = "更多";
                            } else {
                                FilterUrl.instance().positionTitle = item.desc;
                            }


                            onFilterDone();
                        }

                        return child;
                    }
                })
                .onRightItemClickListener(new DoubleListView.OnRightItemClickListener<FilterType, String>() {
                    @Override
                    public void onRightItemClick(FilterType item, String string, int position) {
                        FilterUrl.instance().doubleListLeft = item.desc;
                        FilterUrl.instance().doubleListRight = string;

                        FilterUrl.instance().positionRight =position;
                        FilterUrl.instance().positionTitle = string;

                        onFilterDone();
                    }
                });


        List<FilterType> list = new ArrayList<>();
        //第0项"", ""
        FilterType filterType = new FilterType();
        filterType.desc = "不限";
        list.add(filterType);


        //第一项"", ""
        filterType = new FilterType();
        filterType.desc = "归属部门";
        List<String> childList = new ArrayList<>();

        for(SysParamBean.ParamsBean.PolicycreteunitBean bean:policycreteunit2){
            childList.add(bean.getValue());
        }

        filterType.child = childList;
        list.add(filterType);

        //第二项
        filterType = new FilterType();
        filterType.desc = "企业行业";
        childList = new ArrayList<>();
        for(SysParamBean.ParamsBean.UsertradeBean bean:usertrade2){
            childList.add(bean.getValue());
        }
        filterType.child = childList;
        list.add(filterType);

        /*//第三项
        filterType = new FilterType();
        filterType.desc = "政策类型";
        childList = new ArrayList<>();
        for (int i = 0; i < 5; ++i) {
            childList.add("政策类型" + i);
        }
        filterType.child = childList;
        list.add(filterType);*/

        //初始化选中.
        comTypeDoubleListView.setLeftList(list, 1);
        comTypeDoubleListView.setRightList(list.get(1).child, -1);
        comTypeDoubleListView.getLeftListView().setBackgroundColor(ZczxActivity.this.getResources().getColor(R.color.b_c_fafafa));

        return comTypeDoubleListView;
    }


    private void onFilterDone() {
       /* if (onFilterDoneListener != null) {
            onFilterDoneListener.onFilterDone(0, "", "");
        }*/
        if( FilterUrl.instance().positionLeft==0){

        }else if( FilterUrl.instance().positionLeft==1){
           createUnit=policycreteunit2.get( FilterUrl.instance().positionRight).getCode();
           refresh();
       }else  if( FilterUrl.instance().positionLeft==2){
           trade= usertrade2.get( FilterUrl.instance().positionRight).getCode();
           refresh();
       }
        mDropDownMenu.setTabText(FilterUrl.instance().positionTitle);
        mDropDownMenu.closeMenu();
    }


    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void policyslistSuccess(List<PolicysBean.PolicyInfosBean> data) {
        if (data == null) {
            return;
        }

        if (!isLoadMore) {
            mList.clear();
            mList.addAll(data);
        } else {
            mList.addAll(data);
        }

        mZczxAdapter.set(mList);
    }

    @Override
    public void policyslistFailed() {

    }
}
