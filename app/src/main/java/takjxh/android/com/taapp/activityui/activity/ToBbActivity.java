package takjxh.android.com.taapp.activityui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import takjxh.android.com.commlibrary.presenter.impl.BasePresenter;
import takjxh.android.com.commlibrary.utils.BarUtil;
import takjxh.android.com.commlibrary.utils.ViewUtil;
import takjxh.android.com.commlibrary.view.activity.BaseActivity;
import takjxh.android.com.taapp.R;
import takjxh.android.com.taapp.activityui.adapter.ToBbAdapter;
import takjxh.android.com.taapp.activityui.bean.TaskListBean;
import takjxh.android.com.taapp.activityui.bean.TaskTypeListBean;
import takjxh.android.com.taapp.activityui.bean.ToBbBean;
import takjxh.android.com.taapp.activityui.presenter.ToBbPresenter;
import takjxh.android.com.taapp.activityui.presenter.impl.IToBbPresenter;
import takjxh.android.com.taapp.view.NormalTitleBar;

/**
 * 类名称：报表填报入口
 *
 * @Author: libaibing
 * @Date: 2019-10-15 16:59
 * @Description:
 **/
public class ToBbActivity extends BaseActivity<ToBbPresenter> implements IToBbPresenter.IView, View.OnClickListener {

    public static void startAction(Activity activity) {
        Intent intent = new Intent(activity, ToBbActivity.class);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @BindView(R.id.ntb)
    NormalTitleBar ntb;

    @BindView(R.id.recycler_view2)
    RecyclerView recycler_view;
    private ToBbAdapter mToBbAdapter;
    private List<ToBbBean> mList = new ArrayList<>();

    @BindView(R.id.normal_view1)
    SmartRefreshLayout mRefreshLayout;


    private int pageIndex = 1;
    private int pageSize = 100;
    private int total = 0;
    private boolean isLoadMore = false;

    @BindView(R.id.ml1)
    LinearLayout ml1;
    @BindView(R.id.ml2)
    LinearLayout ml2;
    @BindView(R.id.ml3)
    LinearLayout ml3;
    @BindView(R.id.ml4)
    LinearLayout ml4;
    @BindView(R.id.ml5)
    LinearLayout ml5;

    @BindView(R.id.ml6)
    LinearLayout ml6;
    @BindView(R.id.ml7)
    LinearLayout ml7;
    @BindView(R.id.ml8)
    LinearLayout ml8;
    @BindView(R.id.ml9)
    LinearLayout ml9;
    @BindView(R.id.ml10)
    LinearLayout ml10;
    @BindView(R.id.ml11)
    LinearLayout ml11;

    /**
     * 返回布局文件
     */
    @Override
    protected int getContentViewId() {
        return R.layout.activity_to_bb;
    }

    @Override
    protected ToBbPresenter createPresenter() {
        return new ToBbPresenter(this);
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
        ntb.setTitleText("报表填报");
        ntb.setTvLeftVisiable(true);
        ntb.setOnBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        recycler_view.setLayoutManager(new LinearLayoutManager(ToBbActivity.this, LinearLayoutManager.VERTICAL, false));
        recycler_view.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.top = ViewUtil.dp2px(ToBbActivity.this, 0);
                outRect.bottom = ViewUtil.dp2px(ToBbActivity.this, 0);
            }
        });
        mToBbAdapter = new ToBbAdapter(ToBbActivity.this);
        recycler_view.setAdapter(mToBbAdapter);
        setRefresh();
    }

    /**
     * 设置事件
     */
    @Override
    protected void initEvent() {
        super.initEvent();
        ml1.setOnClickListener(this);
        ml2.setOnClickListener(this);
        ml3.setOnClickListener(this);
        ml4.setOnClickListener(this);
        ml5.setOnClickListener(this);

        ml6.setOnClickListener(this);
        ml7.setOnClickListener(this);
        ml8.setOnClickListener(this);
        ml9.setOnClickListener(this);
        ml10.setOnClickListener(this);
        ml11.setOnClickListener(this);

    }

    /**
     * 初始化数据
     */
    @Override
    protected void initData() {
        super.initData();
        mPresenter.tasktypelist("");
    }

    @Override
    public void onClick(View v) {
       /* switch (v.getId()) {
            case R.id.ml1:
                DzrwtxActivity.startAction(this);
                break;
            case R.id.ml2:
                DzrwtxActivity.startAction(this);
                break;
            case R.id.ml3:
                DzrwtxActivity.startAction(this);
                break;
            case R.id.ml4:
                DzrwtxActivity.startAction(this);
                break;
            case R.id.ml5:
                KtrwListActivity.startAction(this);
                break;
            case R.id.ml6:
                DzrwtxActivity.startAction(this);
                break;
            case R.id.ml7:
                DzrwtxActivity.startAction(this);
                break;
            case R.id.ml8:
                DzrwtxActivity.startAction(this);
                break;
            case R.id.ml9:
                DzrwtxActivity.startAction(this);
                break;
            case R.id.ml10:
                DzrwtxActivity.startAction(this);
                break;
            case R.id.ml11:
                DzrwtxActivity.startAction(this);
                break;
        }*/
    }


    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void tasktypelistSuccess(List<TaskTypeListBean.CommTypesBean> data) {
        if (data == null) {
            return;
        }

        mList.clear();
        for (TaskTypeListBean.CommTypesBean bean : data) {
            if (bean != null) {
                mList.add(new ToBbBean(bean.getCode(), bean.getValue()));
            }
        }
        mToBbAdapter.set(mList);
        mRefreshLayout.autoRefresh();
    }

    @Override
    public void tasktypelistFailed() {

    }

    @Override
    public void tasklistSuccess(List<TaskListBean.ReportTasksBean> data, String type) {
        if (data == null) {
            return;
        }
        if (!isLoadMore) {
            for (int i = 0; i < mList.size(); i++) {
                if (mList.get(i) != null && type.equals(mList.get(i).code)) {
                    mList.get(i).reportTasks.clear();
                    mList.get(i).reportTasks.addAll(data);
                    break;
                }
            }
        } else {
            for (int i = 0; i < mList.size(); i++) {
                if (mList.get(i) != null && type.equals(mList.get(i).code)) {
                    mList.get(i).reportTasks.addAll(data);
                    break;
                }
            }
        }
        mToBbAdapter.set(mList);
    }

    @Override
    public void tasklistFailed() {

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
        for (ToBbBean bean : mList) {
            if (bean != null) {
                mPresenter.tasklist("", bean.code, "" + pageIndex, "" + pageSize);
            }
        }

    }

    /**
     * 更多
     */
    private void loadMore() {
        isLoadMore = true;
        pageIndex++;
        for (ToBbBean bean : mList) {
            if (bean != null) {
                mPresenter.tasklist("", bean.code, "" + pageIndex, "" + pageSize);
            }
        }
    }


}