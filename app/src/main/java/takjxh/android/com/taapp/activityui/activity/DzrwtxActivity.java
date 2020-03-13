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
import takjxh.android.com.taapp.activityui.adapter.TbbbAdapter;
import takjxh.android.com.taapp.activityui.bean.TaskAnswerAddBean;
import takjxh.android.com.taapp.activityui.bean.TaskDetailBean;
import takjxh.android.com.taapp.activityui.presenter.DzrwtxPresenter;
import takjxh.android.com.taapp.activityui.presenter.impl.IDzrwtxPresenter;
import takjxh.android.com.taapp.view.NormalTitleBar;

/**
 * 定制任务填写-报表填写
 * Created by Administrator on 2019/10/15.
 */

public class DzrwtxActivity extends BaseActivity<DzrwtxPresenter> implements IDzrwtxPresenter.IView, View.OnClickListener,TbbbAdapter.OnTaskDetailClickListener {

    public static void startAction(Activity activity, String id, String title) {
        Intent intent = new Intent(activity, DzrwtxActivity.class);
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


    private TbbbAdapter mTbbbAdapter;
    private List<TaskDetailBean.Question1Bean> mList0 = new ArrayList<>();
    private List<TaskDetailBean.Question1Bean> mList1 = new ArrayList<>();
    private List<TaskDetailBean.Question1Bean> mList2 = new ArrayList<>();


    private Map<String, List<TaskDetailBean.Question1Bean>> question2 = new HashMap<>();
    private Map<String, List<TaskDetailBean.Question1Bean>> question3 = new HashMap<>();



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
    protected DzrwtxPresenter createPresenter() {
        return new DzrwtxPresenter(this);
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

        recycler_view.setLayoutManager(new LinearLayoutManager(DzrwtxActivity.this, LinearLayoutManager.VERTICAL, false));
        recycler_view.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.top = ViewUtil.dp2px(DzrwtxActivity.this, 0);
                outRect.bottom = ViewUtil.dp2px(DzrwtxActivity.this, 0);
            }
        });
        mTbbbAdapter = new TbbbAdapter(DzrwtxActivity.this);
        mTbbbAdapter.setmClickListener(this);
        recycler_view.setAdapter(mTbbbAdapter);

        mTbbbAdapter.set(mList0);

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
        mRefreshLayout.autoRefresh();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                //提交
                taskansweradd();

                break;

        }
    }

    @Override
    public void onResume() {
        super.onResume();

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

                mPresenter.taskdetail("", id);

                refreshLayout.finishRefresh(1000);
            }
        });
        mRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {

                //  loadMore();
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
    public void taskdetailSuccess(TaskDetailBean data) {
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
        mTbbbAdapter.set(mList0);

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
    public void taskdetailFailed() {

    }

    @Override
    public void taskansweraddSuccess(String data) {
        ToastUtil.showToast(this, data);
        finish();
    }

    @Override
    public void taskansweraddFailed() {

    }




    private void taskansweradd(){
        List<TaskAnswerAddBean> answerList = new ArrayList<>();
        List<TaskDetailBean.Question1Bean> mListC = mTbbbAdapter.getData();

        for (int i = 0; i < mListC.size(); i++) {
            if ("01".equals(mListC.get(i).getType())) {
                if (mListC.get(i).getItems() != null && mListC.get(i).getItems().size() > 0) {
                    for (int j = 0; j < mListC.get(i).getItems().size(); j++) {
                        if (mListC.get(i).getItems().get(j).isCheck) {
                            answerList.add(new TaskAnswerAddBean(mListC.get(i).getId(),
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
                            answerList.add(new TaskAnswerAddBean(mListC.get(i).getId(),
                                    mListC.get(i).getItems().get(j).getId(),
                                    mListC.get(i).getItems().get(j).inputContent));
                        }

                    }
                }
            } else if ("03".equals(mListC.get(i).getType())) {
                for (int j = 0; j < mListC.get(i).getItems().size(); j++) {
                    answerList.add(new TaskAnswerAddBean(mListC.get(i).getId(),
                            mListC.get(i).getItems().get(j).getId(),
                            mListC.get(i).inputContent));
                }
            } else if ("04".equals(mListC.get(i).getType())) {
                for (int j = 0; j < mListC.get(i).getItems().size(); j++) {
                    answerList.add(new TaskAnswerAddBean(mListC.get(i).getId(),
                            mListC.get(i).getItems().get(j).getId(),
                            mListC.get(i).inputContent));
                }
            }
        }
        if(answerList.size()<mListC.size()){
            ToastUtil.showToast(this,"请您填写完报表再提交，谢谢！");
            return;
        }


        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("answerList", answerList);
        /*for(AnswerAddBean bean:answerList){
            Log.d("TAG","--getQuestionId-:"+bean.getQuestionId()+"--getItemId-:"+bean.getItemId()+"--getInputContent-:"+bean.getInputContent());
        }*/
        mPresenter.taskansweradd("", queryMap);
    }

    @Override
    public void onClick(String questionId, int position, int cj, boolean isShowNext, String type) {
        if (cj == 1) {
            List<TaskDetailBean.Question1Bean> question2List = question2.get(questionId);
            mList0.clear();
            mList0.addAll(mTbbbAdapter.getData());
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
                    mList2.addAll(mTbbbAdapter.getData());


                    List<TaskDetailBean.Question1Bean> mList00 = mTbbbAdapter.getData().subList(0, position + 1);
                    List<TaskDetailBean.Question1Bean> mList01 = mTbbbAdapter.getData().subList(position + 1, mTbbbAdapter.getData().size());
                    List<TaskDetailBean.Question1Bean> mList = new ArrayList<>();
                    mList.clear();
                    mList.addAll(mList00);
                    mList.addAll(question2List);
                    mList.addAll(mList01);

                    Handler handler = new Handler();
                    final Runnable r = new Runnable() {
                        @Override
                        public void run() {
                            mTbbbAdapter.set(mList);
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
                            mTbbbAdapter.set(mList2);
                        } else {
                            mTbbbAdapter.set(mList0);
                        }

                    }
                };
                handler.post(r);
            }
        } else if (cj == 2) {
            List<TaskDetailBean.Question1Bean> question3List = question3.get(questionId);
            mList1.clear();
            mList1.addAll(mTbbbAdapter.getData());
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
                    mList0.addAll(mTbbbAdapter.getData());


                    List<TaskDetailBean.Question1Bean> mList00 = mTbbbAdapter.getData().subList(0, position + 1);
                    List<TaskDetailBean.Question1Bean> mList01 = mTbbbAdapter.getData().subList(position + 1, mTbbbAdapter.getData().size());

                    List<TaskDetailBean.Question1Bean> mList = new ArrayList<>();
                    mList.clear();
                    mList.addAll(mList00);
                    mList.addAll(question3List);
                    mList.addAll(mList01);

                    Handler handler = new Handler();
                    final Runnable r = new Runnable() {
                        @Override
                        public void run() {
                            mTbbbAdapter.set(mList);
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
                            mTbbbAdapter.set(mList0);
                        } else {
                            mTbbbAdapter.set(mList1);
                        }

                    }
                };
                handler.post(r);
            }
        }
    }
}