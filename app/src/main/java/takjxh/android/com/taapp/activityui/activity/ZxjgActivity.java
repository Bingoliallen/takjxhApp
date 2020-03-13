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
import takjxh.android.com.taapp.activityui.adapter.ZxjgAdapter;
import takjxh.android.com.taapp.activityui.bean.OrgansBean;
import takjxh.android.com.taapp.activityui.presenter.ZxjgPresenter;
import takjxh.android.com.taapp.activityui.presenter.impl.IZxjgPresenter;
import takjxh.android.com.taapp.view.NormalTitleBar;

/**
 * 类名称：第三方机构咨询列表
 *
 * @Author: libaibing
 * @Date: 2019-10-24 14:35
 * @Description:
 **/
public class ZxjgActivity extends BaseActivity<ZxjgPresenter> implements IZxjgPresenter.IView, View.OnClickListener {

    public static void startAction(Activity activity) {
        Intent intent = new Intent(activity, ZxjgActivity.class);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @BindView(R.id.edSearch)
    EditText edSearch;
    @BindView(R.id.ntb)
    NormalTitleBar ntb;
    @BindView(R.id.normal_view1)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;

    private ZxjgAdapter mZxjgAdapter;
    private List<OrgansBean.OrganListBean> mList = new ArrayList<>();

    private int pageIndex = 1;
    private int pageSize = 20;
    private int total = 0;
    private boolean isLoadMore = false;

    private String key="";

    /**
     * 返回布局文件
     */
    @Override
    protected int getContentViewId() {
        return R.layout.activity_zxjg;
    }

    @Override
    protected ZxjgPresenter createPresenter() {
        return new ZxjgPresenter(this);
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
        ntb.setTitleText("第三方机构咨询");
        ntb.setTvLeftVisiable(true);
        ntb.setOnBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        recycler_view.setLayoutManager(new LinearLayoutManager(ZxjgActivity.this, LinearLayoutManager.VERTICAL, false));
        recycler_view.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.top = ViewUtil.dp2px(ZxjgActivity.this, 0);
                outRect.bottom = ViewUtil.dp2px(ZxjgActivity.this, 0);
            }
        });
        mZxjgAdapter = new ZxjgAdapter(ZxjgActivity.this);
        recycler_view.setAdapter(mZxjgAdapter);

        mZxjgAdapter.set(mList);

        ntb.getTvTitle().setFocusable(true);
        ntb.getTvTitle().setFocusableInTouchMode(true);
        ntb.getTvTitle().requestFocus();

        setRefresh();


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

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
                    mPresenter.organslist("", key, "" + pageIndex, "" + pageSize);
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
     */
    private void setRefresh() {
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                isLoadMore = false;
                pageIndex = 1;
                key = edSearch.getText().toString().trim();
                mPresenter.organslist("",key ,"" + pageIndex,"" + pageSize);
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
        mPresenter.organslist("",key ,"" + pageIndex,"" + pageSize);

    }


    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void organslistSuccess(List<OrgansBean.OrganListBean> data) {
        if (data == null) {
            return;
        }
        if (!isLoadMore) {
            mList.clear();
            mList.addAll(data);
        } else {
            mList.addAll(data);
        }

        mZxjgAdapter.set(mList);
    }

    @Override
    public void organslistFailed() {

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

