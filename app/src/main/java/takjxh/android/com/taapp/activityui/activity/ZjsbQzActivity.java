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
import takjxh.android.com.taapp.activityui.adapter.ZjsbQzAdapter;
import takjxh.android.com.taapp.activityui.bean.PolicyApplyHelpBean2;
import takjxh.android.com.taapp.activityui.presenter.ZjsbQzPresenter;
import takjxh.android.com.taapp.activityui.presenter.impl.IZjsbQzPresenter;
import takjxh.android.com.taapp.view.NormalTitleBar;

/**
 * 类名称：资金申报求助
 *
 * @Author: libaibing
 * @Date: 2019-10-16 13:27
 * @Description:
 **/
public class ZjsbQzActivity  extends BaseActivity<ZjsbQzPresenter> implements IZjsbQzPresenter.IView {
    public static void startAction(Activity activity) {
        Intent intent = new Intent(activity, ZjsbQzActivity.class);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @BindView(R.id.ntb)
    NormalTitleBar ntb;
    @BindView(R.id.normal_view1)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;

    private List<PolicyApplyHelpBean2.HelpListBean> mList = new ArrayList<>();
    private ZjsbQzAdapter mZjsbQzAdapter;

    private int pageIndex = 1;
    private int pageSize = 20;
    private int total = 0;
    private boolean isLoadMore = false;


    /**
     * 返回布局文件
     */
    @Override
    protected int getContentViewId() {
        return R.layout.activity_zjsbqz;
    }

    @Override
    protected ZjsbQzPresenter createPresenter() {
        return new ZjsbQzPresenter(this);
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

        ntb.setTvLeftVisiable(true);
        ntb.setTitleText("政策申报求助");

        ntb.setOnBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        recycler_view.setLayoutManager(new LinearLayoutManager(ZjsbQzActivity.this, LinearLayoutManager.VERTICAL, false));
        recycler_view.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.top = ViewUtil.dp2px(ZjsbQzActivity.this, 0);
                outRect.bottom = ViewUtil.dp2px(ZjsbQzActivity.this, 0);
            }
        });
        mZjsbQzAdapter = new ZjsbQzAdapter(ZjsbQzActivity.this);
        recycler_view.setAdapter(mZjsbQzAdapter);

        mZjsbQzAdapter.set(mList);
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
    protected void onResume() {
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
                mPresenter.policyapplyhelplist2("", "" + pageIndex,"" + pageSize);
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
        mPresenter.policyapplyhelplist2("", "" + pageIndex,"" + pageSize);
    }


    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void policyapplyhelplist2Success(List<PolicyApplyHelpBean2.HelpListBean> data) {
        if (data == null) {
            return;
        }
        if (!isLoadMore) {
            mList.clear();
            mList.addAll(data);
        } else {
            mList.addAll(data);
        }

        mZjsbQzAdapter.set(mList);
    }

    @Override
    public void policyapplyhelplist2Failed() {

    }
}
