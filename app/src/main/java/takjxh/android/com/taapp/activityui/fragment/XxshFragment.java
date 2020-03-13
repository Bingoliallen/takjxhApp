package takjxh.android.com.taapp.activityui.fragment;

import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import takjxh.android.com.commlibrary.utils.ToastUtil;
import takjxh.android.com.commlibrary.utils.ViewUtil;
import takjxh.android.com.commlibrary.view.fragment.BaseFragment;
import takjxh.android.com.taapp.R;
import takjxh.android.com.taapp.activityui.adapter.XxshAdapter;
import takjxh.android.com.taapp.activityui.bean.XxshBean;
import takjxh.android.com.taapp.activityui.presenter.XxshPresenter;
import takjxh.android.com.taapp.activityui.presenter.impl.IXxshPresenter;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-10-18 16:53
 * @Description:
 **/
public class XxshFragment extends BaseFragment<XxshPresenter> implements IXxshPresenter.IView,  View.OnClickListener,XxshAdapter.OnXxshCommitClickListener {

    @BindView(R.id.normal_view1)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;

    private List<XxshBean.AuditsBean> mList = new ArrayList<>();
    private XxshAdapter mXxshAdapter;

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
        return R.layout.fragment_xxsh;
    }

    @Override
    protected XxshPresenter createPresenter() {
        return new XxshPresenter(this);
    }


    @Override
    protected void initView() {
        super.initView();
        //注册
        EventBus.getDefault().register(this);

        if( getArguments()!=null){
            channelid = getArguments().getString("channelid");
        }

        recycler_view.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        recycler_view.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.top = ViewUtil.dp2px(mContext, 0);
                outRect.bottom = ViewUtil.dp2px(mContext, 0);
            }
        });
        mXxshAdapter = new XxshAdapter(mContext);
        mXxshAdapter.setListener(this);
        recycler_view.setAdapter(mXxshAdapter);

        mXxshAdapter.set(mList);
        setRefresh();

    }

    @Override
    protected void initEvent() {
        super.initEvent();
    }

    @Override
    protected void initData() {
        super.initData();

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
                mPresenter.auditlist("",channelid,"" + pageIndex, "" + pageSize);
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
        mPresenter.auditlist("",channelid,"" + pageIndex, "" + pageSize);
    }


    @Override
    public void auditlistSuccess(List<XxshBean.AuditsBean> data) {
        if (data == null) {
            return;
        }

        if (!isLoadMore) {
            mList.clear();
            mList.addAll(data);
        } else {
            mList.addAll(data);
        }

        mXxshAdapter.set(mList);
    }

    @Override
    public void updateauditSuccess(String data) {
        ToastUtil.showToast(getActivity(),data);
        EventBus.getDefault().post(new XxshBean.AuditsBean());

    }

    @Override
    public void updateauditFailed() {

    }

    @Override
    public void onClick(XxshBean.AuditsBean item, int position, String auditStatus) {

        Map<String, String> searchRequest=new HashMap<>();
        searchRequest.put("auditId",item.getId());
        searchRequest.put("auditStatus",auditStatus);
        mPresenter.updateaudit("",searchRequest);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //取消注册 , 防止Activity内存泄漏
        EventBus.getDefault().unregister(this);
    }

    //消息推送通知收到事件
    @Subscribe
    public void onEvent(XxshBean.AuditsBean note) {
        if (note != null) {
            isLoadMore = false;
            pageIndex = 1;
            mPresenter.auditlist("",channelid,"" + pageIndex, "" + pageSize);
        }
    }



}

