package takjxh.android.com.taapp.activityui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import takjxh.android.com.commlibrary.BaseAppProfile;
import takjxh.android.com.commlibrary.utils.BarUtil;
import takjxh.android.com.commlibrary.utils.ShareUtils;
import takjxh.android.com.commlibrary.utils.ViewUtil;
import takjxh.android.com.commlibrary.view.activity.BaseActivity;
import takjxh.android.com.taapp.R;
import takjxh.android.com.taapp.activityui.adapter.XxjfAdapter;
import takjxh.android.com.taapp.activityui.bean.LoginIn;
import takjxh.android.com.taapp.activityui.bean.XxjfBean;
import takjxh.android.com.taapp.activityui.presenter.XxjfPresenter;
import takjxh.android.com.taapp.activityui.presenter.impl.IXxjfPresenter;
import takjxh.android.com.taapp.view.NormalTitleBar;

/**
 * 学习积分
 * Created by Administrator on 2019/10/16.
 */

public class XxjfActivity extends BaseActivity<XxjfPresenter> implements IXxjfPresenter.IView, View.OnClickListener {

    public static void startAction(Activity activity) {
        Intent intent = new Intent(activity, XxjfActivity.class);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }


    @BindView(R.id.tvtotal)
    TextView tvtotal;
    @BindView(R.id.tvtime)
    TextView tvtime;


    @BindView(R.id.tvjfgz)
    TextView tvjfgz;
    @BindView(R.id.ntb)
    NormalTitleBar ntb;
    @BindView(R.id.normal_view1)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;

    private XxjfAdapter mXxjfAdapter;
    private List<XxjfBean.ScoreTasksBean> mList = new ArrayList<>();

    private int pageIndex = 1;
    private int pageSize = 20;
    private int total = 0;
    private boolean isLoadMore = false;

    /**
     * 返回布局文件
     */
    @Override
    protected int getContentViewId() {
        return R.layout.activity_xxjf;
    }

    @Override
    protected XxjfPresenter createPresenter() {
        return new XxjfPresenter(this);
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
        ntb.setTitleText("学习积分");
        ntb.setTvLeftVisiable(true);
        ntb.setOnBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        recycler_view.setLayoutManager(new LinearLayoutManager(XxjfActivity.this, LinearLayoutManager.VERTICAL, false));

        recycler_view.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.top = ViewUtil.dp2px(XxjfActivity.this, 0);
                outRect.bottom = ViewUtil.dp2px(XxjfActivity.this, 0);
            }
        });
        mXxjfAdapter = new XxjfAdapter(XxjfActivity.this);
        recycler_view.setAdapter(mXxjfAdapter);

        mXxjfAdapter.set(mList);
        setRefresh();

        tvjfgz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JfgzActivity.startAction(XxjfActivity.this);
            }
        });
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
    public void onClick(View v) {

    }

    @Override
    public void onResume() {
        super.onResume();
        mRefreshLayout.autoRefresh();
    }

    /**
     * 设置上拉/下拉监听器
     *
     * @Param: []
     * @return: void
     * @Author: libaibing
     * @Date: 2019/1/24
     */
    private void setRefresh() {
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                isLoadMore = false;
                pageIndex = 1;
                /*
                "" + pageIndex;
                "" + pageSize;*/
                mPresenter.myscore("");
                refreshLayout.finishRefresh(1000);
            }
        });
        mRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
              /*  if (total <= mList.size()) {
                    refreshLayout.finishLoadMore(1000);
                    return;
                }*/
                loadMore();
                refreshLayout.finishLoadMore(1000);

            }
        });
    }

    /**
     * 更多
     *
     * @Param: []
     * @return: void
     * @Author: libaibing
     * @Date: 2019/1/24
     */
    private void loadMore() {
        isLoadMore = true;
        pageIndex++;
        /*
         "" + pageIndex;
         "" + pageSize; */
        mPresenter.myscore("");
    }


    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void myscoreSuccess(XxjfBean data) {
        if (data == null) {
            return;
        }
        ShareUtils.putString(BaseAppProfile.getApplication(), "score", data.getScore()+"");
        tvtotal.setText(""+data.getScore());
        tvtime.setText("变更于" + data.getTime());
        EventBus.getDefault().post(new LoginIn());

        if (data.getScoreTasks() != null) {
            if (!isLoadMore) {
                mList.clear();
                mList.addAll(data.getScoreTasks());
            } else {
                mList.addAll(data.getScoreTasks());
            }
            mXxjfAdapter.set(mList);
        }


    }
}