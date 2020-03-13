package takjxh.android.com.taapp.activityui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

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
import takjxh.android.com.taapp.activityui.adapter.WdZxbbAdapter;
import takjxh.android.com.taapp.activityui.bean.WdZxbbBean;
import takjxh.android.com.taapp.activityui.presenter.WdZxbbPresenter;
import takjxh.android.com.taapp.activityui.presenter.impl.IWdZxbbPresenter;
import takjxh.android.com.taapp.view.NormalTitleBar;

/**
 * 类名称：我的在线报名
 *
 * @Author: libaibing
 * @Date: 2019-10-18 14:17
 * @Description:
 **/
public class WdZxbbActivity extends BaseActivity<WdZxbbPresenter> implements IWdZxbbPresenter.IView, View.OnClickListener {

    public static void startAction(Activity activity) {
        Intent intent = new Intent(activity, WdZxbbActivity.class);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @BindView(R.id.ntb)
    NormalTitleBar ntb;
    @BindView(R.id.normal_view1)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;
    @BindView(R.id.edSearch)
    EditText edSearch;


    private WdZxbbAdapter mWdZxbbAdapter;
    private List<WdZxbbBean.ApplyOrdersBean> mList = new ArrayList<>();

    private int pageIndex = 1;
    private int pageSize = 20;
    private int total = 0;
    private boolean isLoadMore = false;
    private String key = "";

    /**
     * 返回布局文件
     */
    @Override
    protected int getContentViewId() {
        return R.layout.activity_wd_zxbb;
    }

    @Override
    protected WdZxbbPresenter createPresenter() {
        return new WdZxbbPresenter(this);
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
        ntb.setTitleText("我的在线报名");
        ntb.setTvLeftVisiable(true);
        ntb.setRightImagVisibility1(true);
        ntb.setRightImagSrc1(R.mipmap.xz);
        ntb.setOnRightImagListener1(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ZxbbActivity.startAction(WdZxbbActivity.this);
            }
        });

        ntb.setOnBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        recycler_view.setLayoutManager(new LinearLayoutManager(WdZxbbActivity.this, LinearLayoutManager.VERTICAL, false));
        recycler_view.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.top = ViewUtil.dp2px(WdZxbbActivity.this, 0);
                outRect.bottom = ViewUtil.dp2px(WdZxbbActivity.this, 0);
            }
        });
        mWdZxbbAdapter = new WdZxbbAdapter(WdZxbbActivity.this);
        recycler_view.setAdapter(mWdZxbbAdapter);

        mWdZxbbAdapter.set(mList);
        key = edSearch.getText().toString().trim();

        ntb.getTvTitle().setFocusable(true);
        ntb.getTvTitle().setFocusableInTouchMode(true);
        ntb.getTvTitle().requestFocus();

        setRefresh();


    }

    /**
     * 设置事件
     */
    @Override
    protected void initEvent() {
        super.initEvent();

        edSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    //关闭软键盘
                    hideSoftKeyboard(v);
                    isLoadMore = false;
                    pageIndex = 1;
                    key = edSearch.getText().toString().trim();
                    mPresenter.applyslist("", key, "" + pageIndex, "" + pageSize);
                    return true;
                }
                return false;
            }
        });
    }

    /**
     * 初始化数据
     */
    @Override
    protected void initData() {
        super.initData();
    }


    @Override
    public void onResume() {
        super.onResume();
        mRefreshLayout.autoRefresh();
    }


    @Override
    public void onClick(View v) {

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
                key = edSearch.getText().toString().trim();
                mPresenter.applyslist("", key, "" + pageIndex, "" + pageSize);
                refreshLayout.finishRefresh(1000);
            }
        });
        mRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
               /* if (total <= mList.size()) {
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
        mPresenter.applyslist("", key, "" + pageIndex, "" + pageSize);
    }


    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void applyslistSuccess(List<WdZxbbBean.ApplyOrdersBean> data) {
        if (data == null) {
            return;
        }

        if (!isLoadMore) {
            mList.clear();
            mList.addAll(data);
        } else {
            mList.addAll(data);
        }

        mWdZxbbAdapter.set(mList);
    }


    /**
     * 隐藏软键盘
     *
     * @param v
     */
    public static void hideSoftKeyboard(View v) {
        InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }



}
