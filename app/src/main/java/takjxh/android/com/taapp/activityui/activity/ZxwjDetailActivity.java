package takjxh.android.com.taapp.activityui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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
import takjxh.android.com.taapp.activityui.adapter.ZxwjDetailAdapter;
import takjxh.android.com.taapp.activityui.bean.AnswerAddBean;
import takjxh.android.com.taapp.activityui.bean.SurveyDetailBean;
import takjxh.android.com.taapp.activityui.presenter.ZxwjDetailPresenter;
import takjxh.android.com.taapp.activityui.presenter.impl.IZxwjDetailPresenter;
import takjxh.android.com.taapp.view.NormalTitleBar;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-10-15 15:57
 * @Description:
 **/
public class ZxwjDetailActivity extends BaseActivity<ZxwjDetailPresenter> implements IZxwjDetailPresenter.IView, View.OnClickListener, ZxwjDetailAdapter.OnZxwjDetailClickListener {

    public static void startAction(Activity activity, String id) {
        Intent intent = new Intent(activity, ZxwjDetailActivity.class);
        intent.putExtra("id", id);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @BindView(R.id.ntb)
    NormalTitleBar ntb;

    @BindView(R.id.tvtitle)
    TextView tvtitle;
    @BindView(R.id.tvmsg)
    TextView tvmsg;


    @BindView(R.id.btn_login)
    Button btn_login;
    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;

    private ZxwjDetailAdapter mZxwjDetailAdapter;
    private List<SurveyDetailBean.Question1Bean> mList0 = new ArrayList<>();
    private List<SurveyDetailBean.Question1Bean> mList1 = new ArrayList<>();
    private List<SurveyDetailBean.Question1Bean> mList2 = new ArrayList<>();


    private Map<String, List<SurveyDetailBean.Question1Bean>> question2 = new HashMap<>();
    private Map<String, List<SurveyDetailBean.Question1Bean>> question3 = new HashMap<>();

    private String id;

    /**
     * 返回布局文件
     */
    @Override
    protected int getContentViewId() {
        return R.layout.activity_zxwj_detail;
    }

    @Override
    protected ZxwjDetailPresenter createPresenter() {
        return new ZxwjDetailPresenter(this);
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
        ntb.setTitleText("调查问卷");
        ntb.setTvLeftVisiable(true);
        ntb.setOnBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        recycler_view.setLayoutManager(new LinearLayoutManager(ZxwjDetailActivity.this, LinearLayoutManager.VERTICAL, false));


        recycler_view.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.top = ViewUtil.dp2px(ZxwjDetailActivity.this, 0);
                outRect.bottom = ViewUtil.dp2px(ZxwjDetailActivity.this, 0);
            }
        });
        mZxwjDetailAdapter = new ZxwjDetailAdapter(ZxwjDetailActivity.this);
        mZxwjDetailAdapter.setmClickListener(this);
        recycler_view.setAdapter(mZxwjDetailAdapter);

        mZxwjDetailAdapter.set(mList0);


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
        mPresenter.surveydetail("", id);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                //提交
                surveyansweradd();
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();

    }


    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void surveydetailSuccess(SurveyDetailBean data) {
        if (data == null) {
            return;
        }
        tvtitle.setText("" + data.title);
        tvmsg.setText("" + data.des);


        if (data.question1 == null|| data.question1.size()==0) {
            ToastUtil.showToast(this,"暂无问卷调查数据");
            return;
        }

        btn_login.setVisibility(View.VISIBLE);

        mList1.clear();
        mList1.addAll(data.question1);

        mList0.clear();
        mList0.addAll(data.question1);


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

        for (int i = 0; i < mList1.size(); i++) {
            if (!TextUtils.isEmpty(mList1.get(i).getId())) {
                List<SurveyDetailBean.Question1Bean> question2List = question2.get(mList1.get(i).getId());
                if (question2List != null && question2List.size() > 0) {
                    for (int j = 0; j < question2List.size(); j++) {
                        question2List.get(j).cj = 2;
                        List<SurveyDetailBean.Question1Bean> question3List = question3.get(question2List.get(j).getId());
                        if (question3List != null && question3List.size() > 0) {
                            for (int a = 0; a < question3List.size(); a++) {
                                question3List.get(a).cj = 3;
                            }
                            question2List.get(j).question.clear();
                            question2List.get(j).question.addAll(question3List);
                        }

                    }
                    // mList0.get(i).question.clear();
                    // mList0.get(i).question.addAll(question2List);
                }
            }

        }

        mZxwjDetailAdapter.set(mList0);
    }

    @Override
    public void surveydetailFailed() {

    }

    @Override
    public void surveyansweraddSuccess(String data) {
        ToastUtil.showToast(this, data);
        finish();
    }

    @Override
    public void surveyansweraddFailed() {

    }


    private void surveyansweradd() {
        List<AnswerAddBean> answerList = new ArrayList<>();
        List<SurveyDetailBean.Question1Bean> mListC = mZxwjDetailAdapter.getData();
        for (int i = 0; i < mListC.size(); i++) {
            if ("01".equals(mListC.get(i).getType())) {
                if (mListC.get(i).getItems() != null && mListC.get(i).getItems().size() > 0) {
                    for (int j = 0; j < mListC.get(i).getItems().size(); j++) {
                        if (mListC.get(i).getItems().get(j).isCheck) {
                            answerList.add(new AnswerAddBean(mListC.get(i).getId(),
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
                            answerList.add(new AnswerAddBean(mListC.get(i).getId(),
                                    mListC.get(i).getItems().get(j).getId(),
                                    mListC.get(i).getItems().get(j).inputContent));
                        }

                    }
                }
            } else if ("03".equals(mListC.get(i).getType())) {
                if(mListC.get(i).getItems()!=null && mListC.get(i).getItems().size()>0){
                    for (int j = 0; j < mListC.get(i).getItems().size(); j++) {
                        answerList.add(new AnswerAddBean(mListC.get(i).getId(),
                                mListC.get(i).getItems().get(j).getId(),
                                mListC.get(i).inputContent));
                    }
                }else{
                    /*answerList.add(new AnswerAddBean(mListC.get(i).getId(),
                            mListC.get(i).getId(),
                            mListC.get(i).inputContent));*/
                }

            } else if ("04".equals(mListC.get(i).getType())) {
                if(mListC.get(i).getItems()!=null && mListC.get(i).getItems().size()>0){
                    for (int j = 0; j < mListC.get(i).getItems().size(); j++) {
                        answerList.add(new AnswerAddBean(mListC.get(i).getId(),
                                mListC.get(i).getItems().get(j).getId(),
                                mListC.get(i).inputContent));
                    }
                }else{
                    /*answerList.add(new AnswerAddBean(mListC.get(i).getId(),
                            mListC.get(i).getId(),
                            mListC.get(i).inputContent));*/
                }

            }
        }
        /*if (answerList.size() < mListC.size()) {
            ToastUtil.showToast(this, "请您填写完问卷调查再提交，谢谢！");
            return;
        }*/
        Map<String, Object> queryMap = new HashMap<>();

        queryMap.put("surveyId", id);
        queryMap.put("answerList", answerList);
        for (AnswerAddBean bean : answerList) {
            Log.d("TAG", "--getQuestionId-:" + bean.getQuestionId() + "--getItemId-:" + bean.getItemId() + "--getInputContent-:" + bean.getInputContent());
        }
        mPresenter.surveyansweradd("", queryMap);


    }

    @Override
    public void onClick(String questionId, int position, int cj, boolean isShowNext, String type) {
        tvtitle.setFocusable(true);
        tvtitle.setFocusableInTouchMode(true);
        tvtitle.requestFocus();
        if (cj == 1) {
            List<SurveyDetailBean.Question1Bean> question2List = question2.get(questionId);
            mList0.clear();
            mList0.addAll(mZxwjDetailAdapter.getData());
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
                    mList2.addAll(mZxwjDetailAdapter.getData());


                    List<SurveyDetailBean.Question1Bean> mList00 = mZxwjDetailAdapter.getData().subList(0, position + 1);
                    List<SurveyDetailBean.Question1Bean> mList01 = mZxwjDetailAdapter.getData().subList(position + 1, mZxwjDetailAdapter.getData().size());
                    List<SurveyDetailBean.Question1Bean> mList = new ArrayList<>();
                    mList.clear();
                    mList.addAll(mList00);
                    mList.addAll(question2List);
                    mList.addAll(mList01);

                    Handler handler = new Handler();
                    final Runnable r = new Runnable() {
                        @Override
                        public void run() {
                            mZxwjDetailAdapter.set(mList);
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
                            mZxwjDetailAdapter.set(mList2);
                        } else {
                            mZxwjDetailAdapter.set(mList0);
                        }

                    }
                };
                handler.post(r);
            }
        } else if (cj == 2) {
            List<SurveyDetailBean.Question1Bean> question3List = question3.get(questionId);
            mList1.clear();
            mList1.addAll(mZxwjDetailAdapter.getData());
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
                    mList0.addAll(mZxwjDetailAdapter.getData());


                    List<SurveyDetailBean.Question1Bean> mList00 = mZxwjDetailAdapter.getData().subList(0, position + 1);
                    List<SurveyDetailBean.Question1Bean> mList01 = mZxwjDetailAdapter.getData().subList(position + 1, mZxwjDetailAdapter.getData().size());

                    List<SurveyDetailBean.Question1Bean> mList = new ArrayList<>();
                    mList.clear();
                    mList.addAll(mList00);
                    mList.addAll(question3List);
                    mList.addAll(mList01);

                    Handler handler = new Handler();
                    final Runnable r = new Runnable() {
                        @Override
                        public void run() {
                            mZxwjDetailAdapter.set(mList);
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
                            mZxwjDetailAdapter.set(mList0);
                        } else {
                            mZxwjDetailAdapter.set(mList1);
                        }

                    }
                };
                handler.post(r);
            }
        }
    }


}