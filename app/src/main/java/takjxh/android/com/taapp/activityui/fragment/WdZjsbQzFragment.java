package takjxh.android.com.taapp.activityui.fragment;

import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.leon.lfilepickerlibrary.widget.EmptyRecyclerView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import takjxh.android.com.commlibrary.utils.ViewUtil;
import takjxh.android.com.commlibrary.view.fragment.BaseFragment;
import takjxh.android.com.taapp.R;
import takjxh.android.com.taapp.activityui.adapter.WdZjsbQzAdapter;
import takjxh.android.com.taapp.activityui.bean.PolicyApplyHelpBean;
import takjxh.android.com.taapp.activityui.presenter.WdZjsbQzPresenter;
import takjxh.android.com.taapp.activityui.presenter.impl.IWdZjsbQzPresenter;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-14 10:19
 * @Description:
 **/
public class WdZjsbQzFragment extends BaseFragment<WdZjsbQzPresenter> implements IWdZjsbQzPresenter.IView, View.OnClickListener {

    @BindView(R.id.normal_view1)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.empty_view)
    View mEmptyView;
    @BindView(R.id.recycler_view)
    EmptyRecyclerView recycler_view;

    private List<PolicyApplyHelpBean.HelpListBean> mList = new ArrayList<>();
    private WdZjsbQzAdapter mWdZjsbQzAdapter;

    private int pageIndex = 1;
    private int pageSize = 20;
    private int total = 0;
    private boolean isLoadMore = false;


    @Override
    public void onClick(View v) {

    }


    @Override
    protected int getContentViewId() {
        return R.layout.fragment_wd_zjsb;
    }

    @Override
    protected WdZjsbQzPresenter createPresenter() {
        return new WdZjsbQzPresenter(this);
    }


    @Override
    protected void initView() {
        super.initView();

        recycler_view.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        recycler_view.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.top = ViewUtil.dp2px(mContext, 0);
                outRect.bottom = ViewUtil.dp2px(mContext, 0);
            }
        });
        mWdZjsbQzAdapter = new WdZjsbQzAdapter(mContext);
        recycler_view.setAdapter(mWdZjsbQzAdapter);
        recycler_view.setmEmptyView(mEmptyView);
        mWdZjsbQzAdapter.set(mList);
        mEmptyView.setVisibility(View.GONE);

        setRefresh();

    }

    @Override
    protected void initEvent() {
        super.initEvent();
    }

    @Override
    protected void initData() {
        super.initData();

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
                mPresenter.policyapplyhelplist("", "" + pageIndex, "" + pageSize);
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
        mPresenter.policyapplyhelplist("", "" + pageIndex, "" + pageSize);
    }


    @Override
    public void policyapplyhelplistSuccess(List<PolicyApplyHelpBean.HelpListBean> data) {
        if (data == null) {
            return;
        }
        if (!isLoadMore) {
            mList.clear();
            mList.addAll(data);
        } else {
            mList.addAll(data);
        }

        mWdZjsbQzAdapter.set(mList);
    }

    @Override
    public void policyapplyhelplistFailed() {

    }
}
