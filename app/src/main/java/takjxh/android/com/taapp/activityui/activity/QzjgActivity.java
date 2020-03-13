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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import takjxh.android.com.commlibrary.utils.BarUtil;
import takjxh.android.com.commlibrary.utils.ToastUtil;
import takjxh.android.com.commlibrary.utils.ViewUtil;
import takjxh.android.com.commlibrary.view.activity.BaseActivity;
import takjxh.android.com.taapp.R;
import takjxh.android.com.taapp.activityui.adapter.QzjgAdapter;
import takjxh.android.com.taapp.activityui.bean.PolicyApplyOrgansBean;
import takjxh.android.com.taapp.activityui.presenter.QzjgPresenter;
import takjxh.android.com.taapp.activityui.presenter.impl.IQzjgPresenter;
import takjxh.android.com.taapp.view.NormalTitleBar;

/**
 * 类名称：求助机构列表
 *
 * @Author: libaibing
 * @Date: 2019-10-24 13:48
 * @Description:
 **/
public class QzjgActivity extends BaseActivity<QzjgPresenter> implements IQzjgPresenter.IView, View.OnClickListener, QzjgAdapter.OnCommitClickListener {

    public static void startAction(Activity activity, String applyType) {
        Intent intent = new Intent(activity, QzjgActivity.class);
        intent.putExtra("applyType", applyType);
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

    private QzjgAdapter mQzjgAdapter;
    private List<PolicyApplyOrgansBean.OrganListBean> mList = new ArrayList<>();

    private int pageIndex = 1;
    private int pageSize = 20;
    private int total = 0;
    private boolean isLoadMore = false;

    private String key = "";
    private String applyType;

    /**
     * 返回布局文件
     */
    @Override
    protected int getContentViewId() {
        return R.layout.activity_qzjg;
    }

    @Override
    protected QzjgPresenter createPresenter() {
        return new QzjgPresenter(this);
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
        applyType = getIntent().getStringExtra("applyType");
        ntb = findViewById(R.id.ntb);
        ntb.setTitleText("求助机构列表");
        ntb.setTvLeftVisiable(true);
        ntb.setOnBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        recycler_view.setLayoutManager(new LinearLayoutManager(QzjgActivity.this, LinearLayoutManager.VERTICAL, false));
        recycler_view.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.top = ViewUtil.dp2px(QzjgActivity.this, 0);
                outRect.bottom = ViewUtil.dp2px(QzjgActivity.this, 0);
            }
        });
        mQzjgAdapter = new QzjgAdapter(QzjgActivity.this);
        mQzjgAdapter.setOnClickListener(this);
        recycler_view.setAdapter(mQzjgAdapter);

        mQzjgAdapter.set(mList);

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
                    mPresenter.policyapplyorganslist("", key, "" + pageIndex, "" + pageSize);
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
                mPresenter.policyapplyorganslist("", key, "" + pageIndex, "" + pageSize);

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
        mPresenter.policyapplyorganslist("", key, "" + pageIndex, "" + pageSize);

    }


    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void policyapplyorganslistSuccess(List<PolicyApplyOrgansBean.OrganListBean> data) {
        if (data == null) {
            return;
        }
        if (!isLoadMore) {
            mList.clear();
            mList.addAll(data);
        } else {
            mList.addAll(data);
        }

        mQzjgAdapter.set(mList);
    }

    @Override
    public void policyapplyorganslistFailed() {

    }

    @Override
    public void applyadddhelpSuccess(String data) {
        ToastUtil.showToast(this, data);
    }

    @Override
    public void applyadddhelpFailed() {

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


    @Override
    public void onClick(PolicyApplyOrgansBean.OrganListBean item, int position) {
        Map<String, String> searchRequest = new HashMap<>();
        searchRequest.put("organId", item.getId());
        searchRequest.put("applyType", applyType);
        mPresenter.applyadddhelp("", searchRequest);
    }
}
