package takjxh.android.com.taapp.activityui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

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
import takjxh.android.com.taapp.activityui.adapter.ZxwjAdapter;
import takjxh.android.com.taapp.activityui.bean.SurveyListBean;
import takjxh.android.com.taapp.activityui.bean.SysParamBean;
import takjxh.android.com.taapp.activityui.bean.TypesBean;
import takjxh.android.com.taapp.activityui.dropdownmenu.ConstellationAdapter;
import takjxh.android.com.taapp.activityui.dropdownmenu.GirdDropDownAdapter;
import takjxh.android.com.taapp.activityui.dropdownmenu.ListDropDownAdapter;
import takjxh.android.com.taapp.activityui.presenter.ZxwjPresenter;
import takjxh.android.com.taapp.activityui.presenter.impl.IZxwjPresenter;
import takjxh.android.com.taapp.view.NormalTitleBar;

/**
 * 类名称：在线问卷
 *
 * @Author: libaibing
 * @Date: 2019-10-15 15:39
 * @Description:
 **/
public class ZxwjActivity extends BaseActivity<ZxwjPresenter> implements IZxwjPresenter.IView, View.OnClickListener {

    public static void startAction(Activity activity) {
        Intent intent = new Intent(activity, ZxwjActivity.class);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @BindView(R.id.ntb)
    NormalTitleBar ntb;
    SmartRefreshLayout mRefreshLayout;
    RecyclerView recycler_view;

    private ZxwjAdapter mZxwjAdapter;
    private List<SurveyListBean.MarketSuveysBean> mList = new ArrayList<>();

    private int pageIndex = 1;
    private int pageSize = 20;
    private int total = 0;
    private boolean isLoadMore = false;

    @BindView(R.id.dropDownMenu)
    DropDownMenu mDropDownMenu;
    private String headers[] = {"状态", "部门", "排序"};
    private List<View> popupViews = new ArrayList<>();

    private GirdDropDownAdapter cityAdapter;
    private ListDropDownAdapter ageAdapter;
    private ListDropDownAdapter sexAdapter;
    private ConstellationAdapter constellationAdapter;
    private ArrayList<TypesBean> mTypesBeanList = new ArrayList<>();

    private List<String> cityslist = new ArrayList<>();
    private List<SysParamBean.ParamsBean.SurveystatusBean> surveystatus = new ArrayList<>();//问卷状态
    private List<SysParamBean.ParamsBean.SurveystatusBean> surveystatus2 = new ArrayList<>();//问卷状态
    private String status = "";


    private List<String> ageslist = new ArrayList<>();
    private List<SysParamBean.ParamsBean.PolicycreteunitBean> policycreteunit = new ArrayList<>();//发布单位/部门
    private List<SysParamBean.ParamsBean.PolicycreteunitBean> policycreteunit2 = new ArrayList<>();//发布单位/部门
    private String createUnit = "";


    private String sexs[] = {"不限", "升序", "降序"};
    private String sexsId[] = {"", "asc", "desc"};
    private String ascOrDesc = "";


    private String orderBy = "createTime";

    private int constellationPosition = 0;

    private ListView cityView;
    private ListView ageView;
    private ListView sexView;
    private GridView constellation;


    /**
     * 返回布局文件
     */
    @Override
    protected int getContentViewId() {
        return R.layout.activity_zxwj;
    }

    @Override
    protected ZxwjPresenter createPresenter() {
        return new ZxwjPresenter(this);
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
        ntb = findViewById(R.id.ntb);
        ntb.setTitleText("在线问卷");
        ntb.setTvLeftVisiable(true);
        ntb.setOnBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        if (QbApplication.mBaseApplication.surveystatus != null) {
            surveystatus.clear();
            surveystatus.add(new SysParamBean.ParamsBean.SurveystatusBean("", "不限"));
            surveystatus.addAll(QbApplication.mBaseApplication.surveystatus);
            surveystatus2.clear();
            surveystatus2.addAll(QbApplication.mBaseApplication.surveystatus);
        }
        cityslist.clear();
        for (SysParamBean.ParamsBean.SurveystatusBean bean : surveystatus) {
            cityslist.add(bean.getValue());
        }


        if (QbApplication.mBaseApplication.policycreteunit != null) {
            policycreteunit.clear();
            policycreteunit.add(new SysParamBean.ParamsBean.PolicycreteunitBean("", "不限"));
            policycreteunit.addAll(QbApplication.mBaseApplication.policycreteunit);
            policycreteunit2.clear();
            policycreteunit2.addAll(QbApplication.mBaseApplication.policycreteunit);
        }
        ageslist.clear();
        for (SysParamBean.ParamsBean.PolicycreteunitBean bean : policycreteunit) {
            ageslist.add(bean.getValue());
        }


        //init city menu
        cityView = new ListView(this);
        cityAdapter = new GirdDropDownAdapter(this, cityslist);
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
        popupViews.add(sexView);
        // popupViews.add(constellationView);

        //add item click event
        cityView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                status = surveystatus.get(position).getCode();
                cityAdapter.setCheckItem(position);
                mDropDownMenu.setTabText(position == 0 ? headers[0] : cityslist.get(position));
                mDropDownMenu.closeMenu();
                refresh();
            }
        });

        ageView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                createUnit = policycreteunit.get(position).getCode();
                ageAdapter.setCheckItem(position);
                mDropDownMenu.setTabText(position == 0 ? headers[1] : ageslist.get(position));
                mDropDownMenu.closeMenu();
                refresh();
            }
        });

        sexView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ascOrDesc = sexsId[position];
                sexAdapter.setCheckItem(position);
                mDropDownMenu.setTabText(position == 0 ? headers[2] : sexs[position]);
                mDropDownMenu.closeMenu();
                refresh();
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
        View contentView = getLayoutInflater().inflate(R.layout.layout_zxwj, null);
        mRefreshLayout = (SmartRefreshLayout) contentView.findViewById(R.id.normal_view1);
        recycler_view = (RecyclerView) contentView.findViewById(R.id.recycler_view);


        recycler_view.setLayoutManager(new LinearLayoutManager(ZxwjActivity.this, LinearLayoutManager.VERTICAL, false));
        recycler_view.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.top = ViewUtil.dp2px(ZxwjActivity.this, 0);
                outRect.bottom = ViewUtil.dp2px(ZxwjActivity.this, 0);
            }
        });
        mZxwjAdapter = new ZxwjAdapter(ZxwjActivity.this);
        recycler_view.setAdapter(mZxwjAdapter);

        mZxwjAdapter.set(mList);

        //init dropdownview
        mDropDownMenu.setDropDownMenu(Arrays.asList(headers), popupViews, contentView);


        setRefresh();


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
    }


    @Override
    public void onResume() {
        super.onResume();
        mRefreshLayout.autoRefresh();
    }


    @Override
    public void onClick(View v) {

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
        Log.e("TAG","-------createUnit-----:"+createUnit);
        Log.e("TAG","-------status-----:"+status);
        Log.e("TAG","-------ascOrDesc-----:"+ascOrDesc);
        mPresenter.surveylist("", createUnit, status, orderBy, ascOrDesc, "" + pageIndex, "" + pageSize);
    }


    /**
     * 更多
     */
    private void loadMore() {

        isLoadMore = true;
        pageIndex++;
        mPresenter.surveylist("", createUnit, status, orderBy, ascOrDesc, "" + pageIndex, "" + pageSize);

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


    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void surveylistSuccess(List<SurveyListBean.MarketSuveysBean> data) {
        if (data == null) {
            return;
        }
        if (!isLoadMore) {
            mList.clear();
            mList.addAll(data);
        } else {
            mList.addAll(data);
        }

        mZxwjAdapter.set(mList);
    }

    @Override
    public void surveylistFailed() {

    }


}
