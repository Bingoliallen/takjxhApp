package takjxh.android.com.taapp.activityui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
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
import takjxh.android.com.taapp.activityui.adapter.LlksAdapter;
import takjxh.android.com.taapp.activityui.bean.ExamIndexBean;
import takjxh.android.com.taapp.activityui.presenter.LlksPresenter;
import takjxh.android.com.taapp.activityui.presenter.impl.ILlksPresenter;
import takjxh.android.com.taapp.view.NormalTitleBar;

/**
 * 类名称：会计理论考试
 *
 * @Author: libaibing
 * @Date: 2019-10-17 10:26
 * @Description:
 **/
public class LlksActivity extends BaseActivity<LlksPresenter> implements ILlksPresenter.IView, View.OnClickListener {

    public static void startAction(Activity activity) {
        Intent intent = new Intent(activity, LlksActivity.class);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @BindView(R.id.ntb)
    NormalTitleBar ntb;
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.tv2)
    TextView tv2;

    @BindView(R.id.normal_view1)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;

    private LlksAdapter mLlksAdapter;
    private List<ExamIndexBean.TypesBean> mList = new ArrayList<>();

    private int pageIndex = 1;
    private int pageSize = 20;
    private int total = 0;
    private boolean isLoadMore = false;

    /**
     * 返回布局文件
     */
    @Override
    protected int getContentViewId() {
        return R.layout.activity_llks;
    }

    @Override
    protected LlksPresenter createPresenter() {
        return new LlksPresenter(this);
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
        ntb.setTitleText("理论考试");
        ntb.setTvLeftVisiable(true);
        ntb.setOnBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        recycler_view.setLayoutManager(new LinearLayoutManager(LlksActivity.this, LinearLayoutManager.VERTICAL, false));

        recycler_view.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.top = ViewUtil.dp2px(LlksActivity.this, 0);
                outRect.bottom = ViewUtil.dp2px(LlksActivity.this, 0);
            }
        });
        mLlksAdapter = new LlksAdapter(LlksActivity.this);
        recycler_view.setAdapter(mLlksAdapter);

        mLlksAdapter.set(mList);
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

                mPresenter.examindex("");

                refreshLayout.finishRefresh(1000);
            }
        });
        mRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {

                //loadMore();
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

    }


    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void examindexSuccess(ExamIndexBean data) {
        if (data == null) {
            return;
        }
        tv1.setText("" + data.getScore());
        tv2.setText("" + data.getExamNum());

        if (data.getTypes() == null) {
            return;
        }
        mList.clear();
        mList.addAll(data.getTypes());
        mLlksAdapter.set(mList);

    }

    @Override
    public void examindexFailed() {

    }
}