package takjxh.android.com.taapp.activityui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import takjxh.android.com.commlibrary.utils.BarUtil;
import takjxh.android.com.commlibrary.utils.ToastUtil;
import takjxh.android.com.commlibrary.utils.ViewUtil;
import takjxh.android.com.commlibrary.view.activity.BaseActivity;
import takjxh.android.com.taapp.R;
import takjxh.android.com.taapp.activityui.adapter.KtrwtxAdapter;
import takjxh.android.com.taapp.activityui.bean.IssueAnswerAddBean;
import takjxh.android.com.taapp.activityui.bean.IssueDetailBean;
import takjxh.android.com.taapp.activityui.presenter.KtrwtxPresenter;
import takjxh.android.com.taapp.activityui.presenter.impl.IKtrwtxPresenter;
import takjxh.android.com.taapp.view.NormalTitleBar;

/**
 * 课题任务填写-课题调研
 * Created by Administrator on 2019/10/15.
 */

public class KtrwtxActivity extends BaseActivity<KtrwtxPresenter> implements IKtrwtxPresenter.IView, View.OnClickListener, KtrwtxAdapter.OnKtrwtxClickListener {

    public static void startAction(Activity activity, String id, String title) {
        Intent intent = new Intent(activity, KtrwtxActivity.class);
        intent.putExtra("id", id);
        intent.putExtra("title", title);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @BindView(R.id.ntb)
    NormalTitleBar ntb;
    @BindView(R.id.normal_view1)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;
    @BindView(R.id.btn_login)
    Button btn_login;
    @BindView(R.id.tvmsg)
    TextView tvmsg;

    private KtrwtxAdapter mKtrwtxAdapter;

    private List<IssueDetailBean.Question1Bean> mList0 = new ArrayList<>();
    private List<IssueDetailBean.Question1Bean> mList1 = new ArrayList<>();
    private List<IssueDetailBean.Question1Bean> mList2 = new ArrayList<>();

    private Map<String, List<IssueDetailBean.Question1Bean>> question2 = new HashMap<>();
    private Map<String, List<IssueDetailBean.Question1Bean>> question3 = new HashMap<>();


    private int pageIndex = 1;
    private int pageSize = 20;
    private int total = 0;
    private boolean isLoadMore = false;

    private String id;
    private String title;
    /**
     * 返回布局文件
     */
    @Override
    protected int getContentViewId() {
        return R.layout.activity_dzrwtx;
    }

    @Override
    protected KtrwtxPresenter createPresenter() {
        return new KtrwtxPresenter(this);
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
        title = getIntent().getStringExtra("title");

        ntb = findViewById(R.id.ntb);
        ntb.setTitleText(title);
        ntb.setTvLeftVisiable(true);
        ntb.setOnBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        recycler_view.setLayoutManager(new LinearLayoutManager(KtrwtxActivity.this, LinearLayoutManager.VERTICAL, false));

        recycler_view.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.top = ViewUtil.dp2px(KtrwtxActivity.this, 0);
                outRect.bottom = ViewUtil.dp2px(KtrwtxActivity.this, 0);
            }
        });
        mKtrwtxAdapter = new KtrwtxAdapter(KtrwtxActivity.this);
        mKtrwtxAdapter.setmClickListener(this);
        recycler_view.setAdapter(mKtrwtxAdapter);

        mKtrwtxAdapter.set(mList0);

        setRefresh();

    }

    /**
     * 设置事件
     */
    @Override
    protected void initEvent() {
        super.initEvent();
        btn_login.setOnClickListener(this);
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
        switch (v.getId()) {
            case R.id.btn_login:
                //提交
                issueansweradd();

                break;

        }
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
                mPresenter.issuedetail("", id);

                refreshLayout.finishRefresh(1000);
            }
        });
        mRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {

               // loadMore();
                refreshLayout.finishLoadMore(100);

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
    public void issuedetailSuccess(IssueDetailBean data) {
        if (data == null) {
            return;
        }
        ntb.setTitleText("" + data.title);
        tvmsg.setText("" + data.des);


        if (data.question1 == null || data.question1.size()==0) {
            ToastUtil.showToast(this,"暂无数据");
            return;
        }

        tvmsg.setVisibility(View.VISIBLE);
        btn_login.setVisibility(View.VISIBLE);

        mList1.clear();
        mList1.addAll(data.question1);

        mList0.clear();
        mList0.addAll(data.question1);
        mKtrwtxAdapter.set(mList0);

        if (data.question2 == null) {
            return;
        }
        question2.clear();
        question2.putAll(data.question2);
        if (data.question3 == null) {
            return;
        }
        question3.clear();
        question3.putAll(data.question3);
    }

    @Override
    public void issuedetailFailed() {

    }

    @Override
    public void issueansweraddSuccess(String data) {
        ToastUtil.showToast(this, data);
        finish();
    }

    @Override
    public void issueansweraddFailed() {

    }

    @Override
    public void onClick(String questionId, int position, int cj, boolean isShowNext, String type) {
        if (cj == 1) {
            List<IssueDetailBean.Question1Bean> question2List = question2.get(questionId);
            mList0.clear();
            mList0.addAll(mKtrwtxAdapter.getData());
            if (isShowNext) {
                if (question2List != null && question2List.size() > 0) {
                    for (int j = 0; j < question2List.size(); j++) {
                        question2List.get(j).cj = 2;
                        if (question2List.get(j).getItems() != null) {
                            for (int a = 0; a < question2List.get(j).getItems().size(); a++) {
                                question2List.get(j).getItems().get(a).isCheck = false;
                            }
                        }
                    }
                    mList2.clear();
                    mList2.addAll(mKtrwtxAdapter.getData());


                    List<IssueDetailBean.Question1Bean> mList00 = mKtrwtxAdapter.getData().subList(0, position + 1);
                    List<IssueDetailBean.Question1Bean> mList01 = mKtrwtxAdapter.getData().subList(position + 1, mKtrwtxAdapter.getData().size());
                    List<IssueDetailBean.Question1Bean> mList = new ArrayList<>();
                    mList.clear();
                    mList.addAll(mList00);
                    mList.addAll(question2List);
                    mList.addAll(mList01);

                    Handler handler = new Handler();
                    final Runnable r = new Runnable() {
                        @Override
                        public void run() {
                            mKtrwtxAdapter.set(mList);
                        }
                    };
                    handler.post(r);
                }

            } else {
                Handler handler = new Handler();
                final Runnable r = new Runnable() {
                    @Override
                    public void run() {
                        if (mList0.size() > mList2.size()) {
                            mKtrwtxAdapter.set(mList2);
                        } else {
                            mKtrwtxAdapter.set(mList0);
                        }

                    }
                };
                handler.post(r);
            }
        } else if (cj == 2) {
            List<IssueDetailBean.Question1Bean> question3List = question3.get(questionId);
            mList1.clear();
            mList1.addAll(mKtrwtxAdapter.getData());
            if (isShowNext) {
                if (question3List != null && question3List.size() > 0) {
                    for (int j = 0; j < question3List.size(); j++) {
                        question3List.get(j).cj = 3;
                        if (question3List.get(j).getItems() != null) {
                            for (int a = 0; a < question3List.get(j).getItems().size(); a++) {
                                question3List.get(j).getItems().get(a).isCheck = false;
                            }
                        }
                    }
                    mList0.clear();
                    mList0.addAll(mKtrwtxAdapter.getData());


                    List<IssueDetailBean.Question1Bean> mList00 = mKtrwtxAdapter.getData().subList(0, position + 1);
                    List<IssueDetailBean.Question1Bean> mList01 = mKtrwtxAdapter.getData().subList(position + 1, mKtrwtxAdapter.getData().size());

                    List<IssueDetailBean.Question1Bean> mList = new ArrayList<>();
                    mList.clear();
                    mList.addAll(mList00);
                    mList.addAll(question3List);
                    mList.addAll(mList01);

                    Handler handler = new Handler();
                    final Runnable r = new Runnable() {
                        @Override
                        public void run() {
                            mKtrwtxAdapter.set(mList);
                        }
                    };
                    handler.post(r);
                }


            } else {
                Handler handler = new Handler();
                final Runnable r = new Runnable() {
                    @Override
                    public void run() {
                        if (mList1.size() > mList0.size()) {
                            mKtrwtxAdapter.set(mList0);
                        } else {
                            mKtrwtxAdapter.set(mList1);
                        }

                    }
                };
                handler.post(r);
            }
        }
    }

    private void issueansweradd(){
        List<IssueAnswerAddBean> answerList = new ArrayList<>();
        List<IssueDetailBean.Question1Bean> mListC = mKtrwtxAdapter.getData();

        for (int i = 0; i < mListC.size(); i++) {
            if ("01".equals(mListC.get(i).getType())) {
                if (mListC.get(i).getItems() != null && mListC.get(i).getItems().size() > 0) {
                    for (int j = 0; j < mListC.get(i).getItems().size(); j++) {
                        if (mListC.get(i).getItems().get(j).isCheck) {
                            answerList.add(new IssueAnswerAddBean(mListC.get(i).getId(),
                                    mListC.get(i).getItems().get(j).getId(),
                                    mListC.get(i).getItems().get(j).inputContent));
                            continue;
                        }

                    }
                }
            } else if ("02".equals(mListC.get(i).getType())) {
                if (mListC.get(i).getItems() != null && mListC.get(i).getItems().size() > 0) {
                    for (int j = 0; j < mListC.get(i).getItems().size(); j++) {
                        if (mListC.get(i).getItems().get(j).isCheck) {
                            answerList.add(new IssueAnswerAddBean(mListC.get(i).getId(),
                                    mListC.get(i).getItems().get(j).getId(),
                                    mListC.get(i).getItems().get(j).inputContent));
                        }

                    }
                }
            } else if ("03".equals(mListC.get(i).getType())) {
                for (int j = 0; j < mListC.get(i).getItems().size(); j++) {
                    answerList.add(new IssueAnswerAddBean(mListC.get(i).getId(),
                            mListC.get(i).getItems().get(j).getId(),
                            mListC.get(i).inputContent));
                }
            } else if ("04".equals(mListC.get(i).getType())) {
                for (int j = 0; j < mListC.get(i).getItems().size(); j++) {
                    answerList.add(new IssueAnswerAddBean(mListC.get(i).getId(),
                            mListC.get(i).getItems().get(j).getId(),
                            mListC.get(i).inputContent));
                }
            }
        }
        if(answerList.size()< mListC.size()){
            ToastUtil.showToast(this,"请您填写完课题任务再提交，谢谢！");
            return;
        }


        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("taskId", id);
        queryMap.put("answerList", answerList);
        /*for(AnswerAddBean bean:answerList){
            Log.d("TAG","--getQuestionId-:"+bean.getQuestionId()+"--getItemId-:"+bean.getItemId()+"--getInputContent-:"+bean.getInputContent());
        }*/
        mPresenter.issueansweradd("", queryMap);
    }


}