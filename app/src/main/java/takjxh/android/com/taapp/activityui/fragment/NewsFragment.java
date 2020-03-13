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
import com.yc.cn.ycbannerlib.banner.BannerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import takjxh.android.com.commlibrary.utils.ViewUtil;
import takjxh.android.com.commlibrary.view.fragment.BaseFragment;
import takjxh.android.com.taapp.R;
import takjxh.android.com.taapp.activityui.adapter.BaseBannerPagerAdapter;
import takjxh.android.com.taapp.activityui.adapter.NewsAdapter;
import takjxh.android.com.taapp.activityui.bean.BannnersBean;
import takjxh.android.com.taapp.activityui.bean.NewsBean;
import takjxh.android.com.taapp.activityui.bean.NewsRef;
import takjxh.android.com.taapp.activityui.presenter.NewsPresenter;
import takjxh.android.com.taapp.activityui.presenter.impl.INewsPresenter;
import takjxh.android.com.taapp.utils.DisplayUtil;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-10-14 14:32
 * @Description:
 **/
public class NewsFragment extends BaseFragment<NewsPresenter> implements INewsPresenter.IView {

    @BindView(R.id.empty_view)
    View mEmptyView;
    @BindView(R.id.normal_view1)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.recycler_view)
    EmptyRecyclerView recycler_view;

    @BindView(R.id.banner)
    BannerView banner;

    private String channelId;
    private NewsAdapter mNewsAdapter;
    private List<NewsBean.NewsListBean> mList = new ArrayList<>();

    private int pageIndex = 1;
    private int pageSize = 20;
    private int total = 0;
    private boolean isLoadMore = false;
    private List<BannnersBean.BannersBean> mdata = new ArrayList<>();
    private BaseBannerPagerAdapter mBaseBannerPagerAdapter;

    public static NewsFragment newInstance(String channelId) {
        NewsFragment fragment = new NewsFragment();
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
        return R.layout.fragment_news;
    }

    @Override
    protected NewsPresenter createPresenter() {
        return new NewsPresenter(this);
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

        initBanner();

        recycler_view.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recycler_view.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.top = ViewUtil.dp2px(getActivity(), 0);
                outRect.bottom = ViewUtil.dp2px(getActivity(), 0);
            }
        });
        mNewsAdapter = new NewsAdapter(getActivity());
        recycler_view.setAdapter(mNewsAdapter);
        recycler_view.setmEmptyView(mEmptyView);


        mNewsAdapter.set(mList);
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
        mRefreshLayout.autoRefresh();

    }

    @Override
    public void bannnerslistSuccess(List<BannnersBean.BannersBean> data) {
        if (data == null) {
            return;
        }
        mdata.clear();
        mdata.addAll(data);
        mBaseBannerPagerAdapter.notifyDataSetChanged();

    }

    @Override
    public void newslistSuccess(List<NewsBean.NewsListBean> data) {
        if (data == null) {
            return;
        }
        if (!isLoadMore) {
            mList.clear();
            mList.addAll(data);
        } else {
            mList.addAll(data);
        }

        mNewsAdapter.set(mList);

    }


    /**
     * 初始化轮播图
     */
    private void initBanner() {
        if (banner != null) {
            mBaseBannerPagerAdapter = new BaseBannerPagerAdapter(getActivity(), mdata);
            banner.setHintGravity(1);
            banner.setAnimationDuration(1000);
            banner.setPlayDelay(2000);
            banner.setHintPadding(0, 0, 0, DisplayUtil.dip2px(10));
            banner.setAdapter(mBaseBannerPagerAdapter);
        }
    }


    @Override
    public void onPause() {
        super.onPause();
        if (banner != null) {
            banner.pause();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (banner != null) {
            banner.resume();
        }

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
                mPresenter.bannnerslist();

                isLoadMore = false;
                pageIndex = 1;
                mPresenter.newslist(channelId, "" + pageIndex, "" + pageSize);

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
        mPresenter.newslist(channelId, "" + pageIndex, "" + pageSize);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        //取消注册 , 防止Activity内存泄漏
        EventBus.getDefault().unregister(this);
    }

    //消息推送通知收到事件
    @Subscribe
    public void onEvent(NewsRef note) {
        if (note != null) {
            if(channelId.equals(note.channelId)){
                mRefreshLayout.autoRefresh();
            }

        }
    }


}
