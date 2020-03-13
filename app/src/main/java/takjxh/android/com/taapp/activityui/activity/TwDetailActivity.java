package takjxh.android.com.taapp.activityui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
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
import takjxh.android.com.commlibrary.BaseAppProfile;
import takjxh.android.com.commlibrary.utils.BarUtil;
import takjxh.android.com.commlibrary.utils.ShareUtils;
import takjxh.android.com.commlibrary.utils.ToastUtil;
import takjxh.android.com.commlibrary.utils.ViewUtil;
import takjxh.android.com.commlibrary.view.activity.BaseActivity;
import takjxh.android.com.taapp.R;
import takjxh.android.com.taapp.activityui.adapter.PlAdapter;
import takjxh.android.com.taapp.activityui.bean.QaAnswerListBean;
import takjxh.android.com.taapp.activityui.bean.QaDetailBean;
import takjxh.android.com.taapp.activityui.presenter.TwDetailPresenter;
import takjxh.android.com.taapp.activityui.presenter.impl.ITwDetailPresenter;
import takjxh.android.com.taapp.view.NormalTitleBar;

/**
 * 类名称：提问详情
 *
 * @Author: libaibing
 * @Date: 2019-10-18 9:12
 * @Description:
 **/
public class TwDetailActivity extends BaseActivity<TwDetailPresenter> implements ITwDetailPresenter.IView ,PlAdapter.OnPlClickListener {

    public static void lunch(Activity activity, String id) {
        if (activity != null) {
            Intent intent = new Intent(activity, TwDetailActivity.class);
            intent.putExtra("id", id);
            activity.startActivity(intent);
        }
    }

    @BindView(R.id.ntb)
    NormalTitleBar ntb;
    @BindView(R.id.btn_login)
    Button btn_login;

    @BindView(R.id.tvtitle)
    TextView tvtitle;
    @BindView(R.id.tvtime)
    TextView tvtime;
    @BindView(R.id.webView)
    TextView webView;
    @BindView(R.id.tvtype)
    TextView tvtype;
    @BindView(R.id.tv_zt)
    TextView tv_zt;
    @BindView(R.id.tvtotal)
    TextView tvtotal;


    @BindView(R.id.normal_view1)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;

    private PlAdapter mPlAdapter;
    private List<QaAnswerListBean.QaAnswersBean> mList = new ArrayList<>();


    private int pageIndex = 1;
    private int pageSize = 20;
    private int total = 0;
    private boolean isLoadMore = false;

    private String id;

    /**
     * 返回布局文件
     */
    @Override
    protected int getContentViewId() {
        return R.layout.activity_tw_detail;
    }

    @Override
    protected TwDetailPresenter createPresenter() {
        return new TwDetailPresenter(this);
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
        id = getIntent().getStringExtra("id");

        ntb = findViewById(R.id.ntb);
        ntb.setTitleText("提问详情");
        ntb.setTvLeftVisiable(true);
        ntb.setOnBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        recycler_view.setLayoutManager(new LinearLayoutManager(TwDetailActivity.this, LinearLayoutManager.VERTICAL, false));
        recycler_view.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.top = ViewUtil.dp2px(TwDetailActivity.this, 0);
                outRect.bottom = ViewUtil.dp2px(TwDetailActivity.this, 0);
            }
        });
        mPlAdapter = new PlAdapter(TwDetailActivity.this);
        mPlAdapter.setmClickListener(this);
        recycler_view.setAdapter(mPlAdapter);

        mPlAdapter.set(mList);


        setRefresh();

        boolean isTeacher = ShareUtils.getBoolean(BaseAppProfile.getApplication(),"isTeacher" ,false);
        if(isTeacher){
            btn_login.setVisibility(View.VISIBLE);
        }


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
                TwDyActivity.startAction(TwDetailActivity.this, id);
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
    public void qadetailSuccess(QaDetailBean.DetailBean data) {
        if (data == null) {
            return;
        }
        tvtitle.setText(data.getTitle());
        tvtime.setText("作者：" + data.getName() + "  " + data.getType());
        webView.setText(data.getDes());

        tvtype.setText("发布于" + data.getTime());
        tv_zt.setText("");
        tvtotal.setText("" + data.getCommentNum());

        if("true".equals(data.getIsHaveAccept())){
            mPlAdapter.setAccept(false);
        }else{
            String userId = ShareUtils.getString(BaseAppProfile.getApplication(), "userId", "");
            if (!TextUtils.isEmpty(userId)) {
                if(userId.equals(data.getUserId())){
                    mPlAdapter.setAccept(true);
                }else{
                    mPlAdapter.setAccept(false);
                }
            }else{
                mPlAdapter.setAccept(false);
            }
        }


    }

    @Override
    public void qadetailFailed() {

    }

    @Override
    public void qaanswerlistSuccess(QaAnswerListBean data) {
        if (data == null || data.qaAnswers == null) {
            return;
        }
        if (!isLoadMore) {
            mList.clear();
            mList.addAll(data.qaAnswers);
        } else {
            mList.addAll(data.qaAnswers);
        }
       // mPlAdapter.setAccept(data.isAccept);
        mPlAdapter.set(mList);
    }

    @Override
    public void qaanswerlistFailed() {

    }

    @Override
    public void answeracceptSuccess(String data) {
        ToastUtil.showToast(this,data);


        mPresenter.qadetail("", id);
        isLoadMore = false;
        pageIndex = 1;
        mPresenter.qaanswerlist("", id, "" + pageIndex, "" + pageSize);

    }

    @Override
    public void answeracceptFailed() {

    }


    /**
     * 设置上拉/下拉监听器
     */
    private void setRefresh() {
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                mPresenter.qadetail("", id);
                isLoadMore = false;
                pageIndex = 1;
                mPresenter.qaanswerlist("", id, "" + pageIndex, "" + pageSize);

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
        mPresenter.qaanswerlist("", id, "" + pageIndex, "" + pageSize);
    }


    @Override
    public void onClick(QaAnswerListBean.QaAnswersBean item, int position) {
        Map<String, Object> searchRequest=new HashMap<>();
        searchRequest.put("answerId",item.getId());
        mPresenter.answeraccept("",searchRequest);
    }
}

