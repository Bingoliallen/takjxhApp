package takjxh.android.com.taapp.activityui.fragment;

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
import takjxh.android.com.commlibrary.utils.ViewUtil;
import takjxh.android.com.commlibrary.view.fragment.BaseFragment;
import takjxh.android.com.taapp.R;
import takjxh.android.com.taapp.activityui.adapter.WdZjsbAdapter;
import takjxh.android.com.taapp.activityui.bean.PolicyApplyBean;
import takjxh.android.com.taapp.activityui.presenter.WdZjsbPresenter;
import takjxh.android.com.taapp.activityui.presenter.impl.IWdZjsbPresenter;

/**
 * 类名称：我的资金申报列表
 *
 * @Author: libaibing
 * @Date: 2019-10-16 11:03
 * @Description:
 **/
public class WdZjsbFragment extends BaseFragment<WdZjsbPresenter> implements IWdZjsbPresenter.IView, View.OnClickListener {

    @BindView(R.id.normal_view1)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;

    private List<PolicyApplyBean.ApplyInfosBean> mList = new ArrayList<>();
    private WdZjsbAdapter mWdZjsbAdapter;

    private int pageIndex = 1;
    private int pageSize = 20;
    private int total = 0;
    private boolean isLoadMore = false;

    private String channelid;

    @Override
    public void onClick(View v) {

    }


    @Override
    protected int getContentViewId() {
        return R.layout.fragment_wd_zjsb;
    }

    @Override
    protected WdZjsbPresenter createPresenter() {
        return new WdZjsbPresenter(this);
    }


    @Override
    protected void initView() {
        super.initView();
        channelid = getArguments().getString("channelid");

        recycler_view.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        recycler_view.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.top = ViewUtil.dp2px(mContext, 0);
                outRect.bottom = ViewUtil.dp2px(mContext, 0);
            }
        });
        mWdZjsbAdapter = new WdZjsbAdapter(mContext);
        recycler_view.setAdapter(mWdZjsbAdapter);
        mWdZjsbAdapter.set(mList);

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
                mPresenter.policyapplylist("", channelid, "" + pageIndex, "" + pageSize);
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
        mPresenter.policyapplylist("", channelid, "" + pageIndex, "" + pageSize);
    }


    @Override
    public void policyapplylistSuccess(List<PolicyApplyBean.ApplyInfosBean> data) {
        if (data == null) {
            return;
        }
        if (!isLoadMore) {
            mList.clear();
            mList.addAll(data);
        } else {
            mList.addAll(data);
        }

        mWdZjsbAdapter.set(mList);
    }

    @Override
    public void policyapplylistFailed() {

    }
}
