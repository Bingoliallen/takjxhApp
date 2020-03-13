package takjxh.android.com.taapp.activityui.fragment;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.leon.lfilepickerlibrary.widget.EmptyRecyclerView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import takjxh.android.com.commlibrary.utils.ViewUtil;
import takjxh.android.com.commlibrary.view.fragment.BaseFragment;
import takjxh.android.com.taapp.R;
import takjxh.android.com.taapp.activityui.adapter.JlztAdapter;
import takjxh.android.com.taapp.activityui.bean.CommListBean;
import takjxh.android.com.taapp.activityui.bean.CommTypeListBean;
import takjxh.android.com.taapp.activityui.bean.JlztRef;
import takjxh.android.com.taapp.activityui.presenter.JlztPresenter;
import takjxh.android.com.taapp.activityui.presenter.impl.IJlztPresenter;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-10-18 9:57
 * @Description:
 **/
public class JlztFragment extends BaseFragment<JlztPresenter> implements IJlztPresenter.IView {

    @BindView(R.id.empty_view)
    View mEmptyView;
    @BindView(R.id.normal_view1)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.recycler_view)
    EmptyRecyclerView recycler_view;


    private String channelId;
    private JlztAdapter mJlztAdapter;
    private List<CommListBean.CommTopicsBean> mList = new ArrayList<>();

    private int pageIndex = 1;
    private int pageSize = 20;
    private int total = 0;
    private boolean isLoadMore = false;


    public static JlztFragment newInstance(String channelId) {
        JlztFragment fragment = new JlztFragment();
        Bundle args = new Bundle();
        args.putString("channelId", channelId);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * 返回布局文件
     */
    @Override
    protected int getContentViewId() {
        return R.layout.fragment_jlzt;
    }

    @Override
    protected JlztPresenter createPresenter() {
        return new JlztPresenter(this);
    }

    /**
     * 初始化控件
     */
    @Override
    protected void initView() {
        super.initView();
        //注册
        EventBus.getDefault().register(this);

        if (getArguments() != null) {
            channelId = getArguments().getString("channelId");
        }
        recycler_view.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recycler_view.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.top = ViewUtil.dp2px(getActivity(), 0);
                outRect.bottom = ViewUtil.dp2px(getActivity(), 0);
            }
        });
        mJlztAdapter = new JlztAdapter(getActivity());
        recycler_view.setAdapter(mJlztAdapter);
        recycler_view.setmEmptyView(mEmptyView);
        mJlztAdapter.set(mList);
        mEmptyView.setVisibility(View.GONE);
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
    public void onPause() {
        super.onPause();

    }

    @Override
    public void onResume() {
        super.onResume();

        mRefreshLayout.autoRefresh();
    }


    /**
     * 设置上拉/下拉监听器
     *
     */
    private void setRefresh() {
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                isLoadMore = false;
                pageIndex = 1;
                mPresenter.commlist("", channelId,"" + pageIndex,"" + pageSize);

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
     */
    private void loadMore() {

        isLoadMore = true;
        pageIndex++;
        mPresenter.commlist("", channelId,"" + pageIndex,"" + pageSize);
    }


    @Override
    public void commtypelistSuccess(List<CommTypeListBean.CommTypesBean> data) {

    }

    @Override
    public void commtypelistFailed() {

    }

    @Override
    public void commlistSuccess(List<CommListBean.CommTopicsBean> data) {
        if (data == null) {
            return;
        }
        if (!isLoadMore) {
            mList.clear();
            mList.addAll(data);
        } else {
            mList.addAll(data);
        }

        mJlztAdapter.set(mList);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //取消注册 , 防止Activity内存泄漏
        EventBus.getDefault().unregister(this);
    }

    //消息推送通知收到事件
    @Subscribe
    public void onEvent(JlztRef note) {
        if (note != null) {
            if(channelId.equals(note.channelId)){
                mRefreshLayout.autoRefresh();
            }

        }
    }



}

