package takjxh.android.com.taapp.activityui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import takjxh.android.com.commlibrary.utils.BarUtil;
import takjxh.android.com.commlibrary.utils.ViewUtil;
import takjxh.android.com.commlibrary.view.activity.BaseActivity;
import takjxh.android.com.taapp.R;
import takjxh.android.com.taapp.activityui.adapter.XxzxAdapter;
import takjxh.android.com.taapp.activityui.bean.MessagesBean;
import takjxh.android.com.taapp.activityui.presenter.XxzzPresenter;
import takjxh.android.com.taapp.activityui.presenter.impl.IXxzzPresenter;
import takjxh.android.com.taapp.view.NormalTitleBar;

/**
 * 消息中心
 * Created by Administrator on 2019/10/17.
 */

public class XxzzActivity extends BaseActivity<XxzzPresenter> implements IXxzzPresenter.IView, View.OnClickListener {

    public static void startAction(Activity activity) {
        Intent intent = new Intent(activity, XxzzActivity.class);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @BindView(R.id.ntb)
    NormalTitleBar ntb;
    @BindView(R.id.normal_view1)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;

    private XxzxAdapter mXxzxAdapter;
    private List<MessagesBean.SysMessagesBean> mList = new ArrayList<>();

    private int pageIndex = 1;
    private int pageSize = 20;
    private int total = 0;
    private boolean isLoadMore = false;

    /**
     * 返回布局文件
     */
    @Override
    protected int getContentViewId() {
        return R.layout.activity_xxzz;
    }

    @Override
    protected XxzzPresenter createPresenter() {
        return new XxzzPresenter(this);
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
        ntb.setTitleText("消息中心");
        ntb.setTvLeftVisiable(true);
        ntb.setOnBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        recycler_view.setLayoutManager(new LinearLayoutManager(XxzzActivity.this, LinearLayoutManager.VERTICAL, false));

        recycler_view.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.top = ViewUtil.dp2px(XxzzActivity.this, 0);
                outRect.bottom = ViewUtil.dp2px(XxzzActivity.this, 0);
            }
        });
        mXxzxAdapter = new XxzxAdapter(XxzzActivity.this);
        recycler_view.setAdapter(mXxzxAdapter);

        mXxzxAdapter.set(mList);
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
    public void onClick(View v) {

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
                isLoadMore = false;
                pageIndex = 1;
                mPresenter.messagelist("", "" + pageIndex, "" + pageSize);
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
     */
    private void loadMore() {

        isLoadMore = true;
        pageIndex++;
        mPresenter.messagelist("", "" + pageIndex, "" + pageSize);

    }


    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void messagelistSuccess(List<MessagesBean.SysMessagesBean> data) {
        if (data == null) {
            return;
        }
        if (!isLoadMore) {
            mList.clear();
            mList.addAll(data);
        } else {
            mList.addAll(data);
        }

        mXxzxAdapter.set(mList);
    }

    @Override
    public void messagelistFailed() {

    }
}