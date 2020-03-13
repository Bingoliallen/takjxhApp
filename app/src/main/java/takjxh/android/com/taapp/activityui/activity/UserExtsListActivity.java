package takjxh.android.com.taapp.activityui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.leon.lfilepickerlibrary.widget.EmptyRecyclerView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import takjxh.android.com.commlibrary.utils.BarUtil;
import takjxh.android.com.commlibrary.utils.ToastUtil;
import takjxh.android.com.commlibrary.utils.ViewUtil;
import takjxh.android.com.commlibrary.view.activity.BaseActivity;
import takjxh.android.com.taapp.R;
import takjxh.android.com.taapp.activityui.adapter.UserExtsListAdapter;
import takjxh.android.com.taapp.activityui.base.UserExtsListBean;
import takjxh.android.com.taapp.activityui.presenter.UserExtsListPresenter;
import takjxh.android.com.taapp.activityui.presenter.impl.IUserExtsListPresenter;
import takjxh.android.com.taapp.view.NormalTitleBar;

/**
 * Created by libb on 2020/3/7.
 */

public class UserExtsListActivity extends BaseActivity<UserExtsListPresenter> implements
        IUserExtsListPresenter.IView,View.OnClickListener,UserExtsListAdapter.OnUserExtClickListener {

    public static void startAction(Activity activity) {
        Intent intent = new Intent(activity, UserExtsListActivity.class);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @BindView(R.id.ntb)
    NormalTitleBar ntb;

    @BindView(R.id.empty_view)
    View mEmptyView;
    @BindView(R.id.normal_view1)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.recycler_view)
    EmptyRecyclerView recycler_view;

    UserExtsListAdapter mUserExtsListAdapter;

    private List<UserExtsListBean.UserExtListVosBean> mList = new ArrayList<>();

    private int pageIndex = 1;
    private int pageSize = 20;
    private int total = 0;
    private boolean isLoadMore = false;



    /**
     * 返回布局文件
     */
    @Override
    protected int getContentViewId() {
        return R.layout.activity_user_exts_list;
    }

    @Override
    protected UserExtsListPresenter createPresenter() {
        return new UserExtsListPresenter(this);
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
        ntb.setTitleText("个人扩展信息");
        ntb.setTvLeftVisiable(true);
        ntb.setRightTitle("新增");
        ntb.setRightTitleVisibility(true);
        ntb.setOnRightTextListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                UserExtsAddAcitivty.startAction(UserExtsListActivity.this);
            }
        });
        ntb.setOnBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        recycler_view.setLayoutManager(new LinearLayoutManager(UserExtsListActivity.this, LinearLayoutManager.VERTICAL, false));
        recycler_view.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.top = ViewUtil.dp2px(UserExtsListActivity.this, 0);
                outRect.bottom = ViewUtil.dp2px(UserExtsListActivity.this, 0);
            }
        });
        mUserExtsListAdapter = new UserExtsListAdapter(UserExtsListActivity.this);
        mUserExtsListAdapter.setmClickListener(this);
        recycler_view.setAdapter(mUserExtsListAdapter);
        recycler_view.setmEmptyView(mEmptyView);
        mUserExtsListAdapter.set(mList);
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
    public void onClick(View v) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        mRefreshLayout.autoRefresh();
    }

    @Override
    public Context getContext() {
        return this;
    }


    @Override
    public void userExtsListSuccess(List<UserExtsListBean.UserExtListVosBean> data) {
        if (data == null) {
            return;
        }
        if (!isLoadMore) {
            mList.clear();
            mList.addAll(data);
        } else {
            mList.addAll(data);
        }

        mUserExtsListAdapter.set(mList);
    }

    @Override
    public void userExtsListFailed() {

    }

    @Override
    public void userextdefaultSuccess(String data) {
        ToastUtil.showToast(this,data);
        refresh();
    }

    @Override
    public void userextdefaultFailed() {

    }

    @Override
    public void userextdelSuccess(String data) {
        ToastUtil.showToast(this,data);
        refresh();
    }

    @Override
    public void userextdelFailed() {

    }

    private void refresh() {
        isLoadMore = false;
        pageIndex = 1;
        mPresenter.userExtsList("",  "" + pageIndex, "" + pageSize);
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

    /**
     * 更多
     */
    private void loadMore() {

        isLoadMore = true;
        pageIndex++;
        mPresenter.userExtsList("", "" + pageIndex, "" + pageSize);

    }


    @Override
    public void setClick(String mId, int position) {
        mPresenter.userextdefault("",mId);
    }

    @Override
    public void delClick(String mId, int position) {
        mPresenter.userextdel("",mId);
    }
}