package takjxh.android.com.taapp.activityui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

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
import takjxh.android.com.taapp.activityui.adapter.HdAdapter;
import takjxh.android.com.taapp.activityui.bean.QuestionAnswerListBean;
import takjxh.android.com.taapp.activityui.presenter.JlztHdPresenter;
import takjxh.android.com.taapp.activityui.presenter.impl.IJlztHdPresenter;
import takjxh.android.com.taapp.view.NormalTitleBar;

/**
 * 类名称：交流主题详情-提问的回答列表
 *
 * @Author: libaibing
 * @Date: 2019-10-28 14:28
 * @Description:
 **/
public class JlztHdListActivity extends BaseActivity<JlztHdPresenter> implements IJlztHdPresenter.IView {

    public static void lunch(Activity activity,String topicId,String questionId) {
        if (activity != null) {
            Intent intent = new Intent(activity, JlztHdListActivity.class);
            intent.putExtra("topicId", topicId);
            intent.putExtra("questionId", questionId);
            activity.startActivity(intent);
            activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        }
    }

    @BindView(R.id.ntb)
    NormalTitleBar ntb;


    @BindView(R.id.normal_view1)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;


    @BindView(R.id.btn_login)
    Button btn_login;

    private String name;
    private HdAdapter mHdAdapter;
    private List<QuestionAnswerListBean.CommAnswersBean> mList = new ArrayList<>();

    private String topicId;
    private String questionId;

    private int pageIndex = 1;
    private int pageSize = 20;
    private int total = 0;
    private boolean isLoadMore = false;


    /**
     * 返回布局文件
     */
    @Override
    protected int getContentViewId() {
        return R.layout.activity_jlzt_hd_list;
    }

    @Override
    protected JlztHdPresenter createPresenter() {
        return new JlztHdPresenter(this);
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
        topicId=getIntent().getStringExtra("topicId");
        questionId=getIntent().getStringExtra("questionId");

        name = "评论列表";
        ntb = findViewById(R.id.ntb);
        ntb.setTitleText(name);
        ntb.setTvLeftVisiable(true);
        ntb.setOnBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        recycler_view.setLayoutManager(new LinearLayoutManager(JlztHdListActivity.this, LinearLayoutManager.VERTICAL, false));
        recycler_view.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.top = ViewUtil.dp2px(JlztHdListActivity.this, 0);
                outRect.bottom = ViewUtil.dp2px(JlztHdListActivity.this, 0);
            }
        });
        mHdAdapter = new HdAdapter(JlztHdListActivity.this);
        recycler_view.setAdapter(mHdAdapter);

        mHdAdapter.set(mList);

        setRefresh();

    }

    /**
     * 设置事件
     */
    @Override
    protected void initEvent() {
        super.initEvent();
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlActivity.startAction(JlztHdListActivity.this,topicId,questionId);
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
    protected void onResume() {
        super.onResume();
        mRefreshLayout.autoRefresh();
    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void questionanswerlistSuccess(List<QuestionAnswerListBean.CommAnswersBean> data) {
        if (data == null) {
            return;
        }
        if (!isLoadMore) {
            mList.clear();
            mList.addAll(data);
        } else {
            mList.addAll(data);
        }

        mHdAdapter.set(mList);
    }

    @Override
    public void questionanswerlistFailed() {

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
                mPresenter.questionanswerlist("", questionId,"" + pageIndex,"" + pageSize);

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
        mPresenter.questionanswerlist("", questionId,"" + pageIndex,"" + pageSize);
    }


}


