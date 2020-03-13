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
import takjxh.android.com.taapp.activityui.adapter.TbbbAdapter1;
import takjxh.android.com.taapp.activityui.bean.TaskAnswerAddBean;
import takjxh.android.com.taapp.activityui.bean.TaskDetailBean1;
import takjxh.android.com.taapp.activityui.presenter.DzrwtxPresenter1;
import takjxh.android.com.taapp.activityui.presenter.impl.IDzrwtxPresenter1;
import takjxh.android.com.taapp.view.NormalTitleBar;

/**
 * Created by Administrator on 2019/12/29.
 */

public class DzrwtxActivity1 extends BaseActivity<DzrwtxPresenter1> implements IDzrwtxPresenter1.IView, View.OnClickListener{

    public static void startAction(Activity activity, String id, String title) {
        Intent intent = new Intent(activity, DzrwtxActivity1.class);
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


    private TbbbAdapter1 mTbbbAdapter;
    private List<TaskDetailBean1.Question1Bean> mList = new ArrayList<>();



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
    protected DzrwtxPresenter1 createPresenter() {
        return new DzrwtxPresenter1(this);
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

        recycler_view.setLayoutManager(new LinearLayoutManager(DzrwtxActivity1.this, LinearLayoutManager.VERTICAL, false));
        recycler_view.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.top = ViewUtil.dp2px(DzrwtxActivity1.this, 0);
                outRect.bottom = ViewUtil.dp2px(DzrwtxActivity1.this, 0);
            }
        });
        mTbbbAdapter = new TbbbAdapter1(DzrwtxActivity1.this);
        recycler_view.setAdapter(mTbbbAdapter);

        mTbbbAdapter.set(mList);

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
    public void taskdetailSuccess(TaskDetailBean1 data) {
        if (data == null) {
            return;
        }

        tvmsg.setText("" + "请核对填报报表时间，提交完后不可修改");


        if (data.itemTitles == null || data.itemTitles.size()==0) {
            ToastUtil.showToast(this,"暂无数据");
            return;
        }

        if(data.itemTitles.size()==1){
            mTbbbAdapter.setShow(false);
        }else {
            mTbbbAdapter.setShow(true);
        }



        tvmsg.setVisibility(View.VISIBLE);
        btn_login.setVisibility(View.VISIBLE);

        mList.clear();
        mList.addAll(data.itemTitles);

        mTbbbAdapter.set(mList);

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
        List<TaskDetailBean1.Question1Bean> mListC = mTbbbAdapter.getData();
        int num=0;
        for (int i = 0; i < mListC.size(); i++) {
            for (int j = 0; j < mListC.get(i).getItems().size(); j++) {
                num++;
                if(!TextUtils.isEmpty(mListC.get(i).getItems().get(j).inputContent)){
                    answerList.add(new TaskAnswerAddBean(mListC.get(i).getId(),
                            mListC.get(i).getItems().get(j).getId(),
                            mListC.get(i).getItems().get(j).inputContent));
                }

            }
        }
        if(answerList.size()<num){
            ToastUtil.showToast(this,"请您填写完报表再提交，谢谢！");
            return;
        }


        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("taskId", id);
        queryMap.put("answerList", answerList);
        for(TaskAnswerAddBean bean:answerList){
            Log.d("TAG","--getQuestionId-:"+bean.titleId+"--getItemId-:"+bean.itemId+"--getInputContent-:"+bean.inputContent);
        }
        mPresenter.taskansweradd("", queryMap);
    }

}