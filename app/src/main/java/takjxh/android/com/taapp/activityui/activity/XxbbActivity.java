package takjxh.android.com.taapp.activityui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
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
import takjxh.android.com.taapp.activityui.adapter.XxbbAdapter;
import takjxh.android.com.taapp.activityui.bean.LoginIn;
import takjxh.android.com.taapp.activityui.bean.ScoresBean;
import takjxh.android.com.taapp.activityui.presenter.XxbbPresenter;
import takjxh.android.com.taapp.activityui.presenter.impl.IXxbbPresenter;
import takjxh.android.com.taapp.view.NormalTitleBar;

/**
 * 学习报表
 * Created by Administrator on 2019/10/16.
 */

public class XxbbActivity extends BaseActivity<XxbbPresenter> implements IXxbbPresenter.IView, View.OnClickListener {

    public static void startAction(Activity activity) {
        Intent intent = new Intent(activity, XxbbActivity.class);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @BindView(R.id.ivpm0)
    ImageView ivpm0;

    @BindView(R.id.tvtotal)
    TextView tvtotal;
    @BindView(R.id.tvpm1)
    TextView tvpm1;
    @BindView(R.id.tvpm2)
    TextView tvpm2;

    @BindView(R.id.ntb)
    NormalTitleBar ntb;
    @BindView(R.id.normal_view1)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;

    private XxbbAdapter mXxbbAdapter;
    private List<ScoresBean.UserScoresBean> mList = new ArrayList<>();

    private int pageIndex = 1;
    private int pageSize = 20;
    private int total = 0;
    private boolean isLoadMore = false;

    /**
     * 返回布局文件
     */
    @Override
    protected int getContentViewId() {
        return R.layout.activity_xxbb;
    }

    @Override
    protected XxbbPresenter createPresenter() {
        return new XxbbPresenter(this);
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
        ntb.setTitleText("学习报表");
        ntb.setTvLeftVisiable(true);
        ntb.setOnBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        recycler_view.setLayoutManager(new LinearLayoutManager(XxbbActivity.this, LinearLayoutManager.VERTICAL, false));

        recycler_view.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.top = ViewUtil.dp2px(XxbbActivity.this, 0);
                outRect.bottom = ViewUtil.dp2px(XxbbActivity.this, 0);
            }
        });
        mXxbbAdapter = new XxbbAdapter(XxbbActivity.this);
        recycler_view.setAdapter(mXxbbAdapter);

        mXxbbAdapter.set(mList);
        setRefresh();

        ivpm0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JfpmActivity.startAction(XxbbActivity.this);
            }
        });
        tvpm1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JfpmActivity.startAction(XxbbActivity.this);
            }
        });
        tvpm2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JfpmActivity.startAction(XxbbActivity.this);
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
                mPresenter.scoreslist("", "" + pageIndex, "" + pageSize);
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
        mPresenter.scoreslist("", "" + pageIndex, "" + pageSize);

    }


    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void scoreslistSuccess(ScoresBean data) {
        if (data == null) {
            return;
        }
        ShareUtils.putString(BaseAppProfile.getApplication(), "score", data.getScore()+"");

        tvtotal.setText(data.getScore()+"分");
        tvpm2.setText(data.getRank()+"名");

        EventBus.getDefault().post(new LoginIn());

        if (data.getUserScores() != null) {
            if (!isLoadMore) {
                mList.clear();
                mList.addAll(data.getUserScores());
            } else {
                mList.addAll(data.getUserScores());
            }

            mXxbbAdapter.set(mList);
        }

    }

}