package takjxh.android.com.taapp.activityui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.leon.lfilepickerlibrary.widget.EmptyRecyclerView;
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
import takjxh.android.com.taapp.activityui.adapter.FeedbackAdapter;
import takjxh.android.com.taapp.activityui.bean.FeedbackListBean;
import takjxh.android.com.taapp.activityui.presenter.JyfkPresenter;
import takjxh.android.com.taapp.activityui.presenter.impl.IJyfkPresenter;
import takjxh.android.com.taapp.view.NormalTitleBar;

/** 建议反馈
 * Created by Administrator on 2019/10/16.
 */

public class JyfkActivity  extends BaseActivity<JyfkPresenter> implements IJyfkPresenter.IView,View.OnClickListener{

    public static void startAction(Activity activity) {
        Intent intent = new Intent(activity, JyfkActivity.class);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @BindView(R.id.ntb)
    NormalTitleBar ntb;

    @BindView(R.id.clue_content)
    EditText clue_content;


    @BindView(R.id.empty_view)
    View mEmptyView;
    @BindView(R.id.normal_view1)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.recycler_view)
    EmptyRecyclerView recycler_view;

    FeedbackAdapter mFeedbackAdapter;


    private List<FeedbackListBean.UserFeedbackVosBean> mList = new ArrayList<>();

    private int pageIndex = 1;
    private int pageSize = 20;
    private int total = 0;
    private boolean isLoadMore = false;

    /**
     * 返回布局文件
     */
    @Override
    protected int getContentViewId() {
        return R.layout.activity_jyfk;
    }

    @Override
    protected JyfkPresenter createPresenter() {
        return new JyfkPresenter(this);
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
        ntb.setTitleText("建议反馈");
        ntb.setTvLeftVisiable(true);
        ntb.setRightImagVisibility1(true);
        ntb.setRightImagSrc1(R.mipmap.sc);
        ntb.setOnRightImagListener1(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                feedback();
            }
        });
        ntb.setOnBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        recycler_view.setLayoutManager(new LinearLayoutManager(JyfkActivity.this, LinearLayoutManager.VERTICAL, false));
        recycler_view.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.top = ViewUtil.dp2px(JyfkActivity.this, 0);
                outRect.bottom = ViewUtil.dp2px(JyfkActivity.this, 0);
            }
        });
        mFeedbackAdapter = new FeedbackAdapter(JyfkActivity.this);

        recycler_view.setAdapter(mFeedbackAdapter);
        recycler_view.setmEmptyView(mEmptyView);
        mFeedbackAdapter.set(mList);
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
    public void feedbackSuccess(String msg) {
        ToastUtil.showToast(this, msg);
        finish();
    }

    @Override
    public void feedbackFailed() {

    }

    @Override
    public void feedbacklistSuccess(List<FeedbackListBean.UserFeedbackVosBean> data) {
        if (data == null) {
            return;
        }
        if (!isLoadMore) {
            mList.clear();
            mList.addAll(data);
        } else {
            mList.addAll(data);
        }

        mFeedbackAdapter.set(mList);
    }

    @Override
    public void feedbacklistFailed() {

    }

    private void feedback(){
        String content = clue_content.getText().toString().trim();
        if (TextUtils.isEmpty(content)) {
            ToastUtil.showToast(this, "请输入您的宝贵建议或反馈");
            return;
        }
        Map<String,String> queryMap=new HashMap<>();
        queryMap.put("content", content);
        mPresenter.feedback("",queryMap);
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
                mPresenter.feedbacklist("",  "" + pageIndex, "" + pageSize);

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
        mPresenter.feedbacklist("", "" + pageIndex, "" + pageSize);

    }


}
