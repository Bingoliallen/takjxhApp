package takjxh.android.com.taapp.activityui.fragment;

import android.annotation.SuppressLint;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import takjxh.android.com.commlibrary.utils.ToastUtil;
import takjxh.android.com.commlibrary.utils.ViewUtil;
import takjxh.android.com.commlibrary.view.fragment.BaseFragment;
import takjxh.android.com.taapp.R;
import takjxh.android.com.taapp.activityui.activity.ZxtwActivity;
import takjxh.android.com.taapp.activityui.adapter.JlztDetailAdapter;
import takjxh.android.com.taapp.activityui.bean.CommQuestionBean;
import takjxh.android.com.taapp.activityui.presenter.HdtwPresenter;
import takjxh.android.com.taapp.activityui.presenter.impl.IHdtwPresenter;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-10-18 11:08
 * @Description:
 **/
@SuppressLint("ValidFragment")
public class JlztDetailFragment extends BaseFragment<HdtwPresenter> implements IHdtwPresenter.IView, JlztDetailAdapter.OnJlztTextSendListener {


    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;
    ViewPager viewPager;
    View rootView;


    @BindView(R.id.tvTWFH)
    TextView tvTWFH;
    @BindView(R.id.btn_login)
    Button btn_login;


    private List<CommQuestionBean.CommQuestionsBean> mList = new ArrayList<>();
    private JlztDetailAdapter mJlztDetailAdapter;


    private String channelid;
    private String topicId;

    public JlztDetailFragment(ViewPager viewPager) {
        this.viewPager = viewPager;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = super.onCreateView(inflater, container, savedInstanceState);
        // viewPager.setObjectForPosition(rootView,page);//0代表tab的位置 0,1,2,3
        return rootView;

    }

    /**
     * 返回布局文件
     */
    @Override
    protected int getContentViewId() {
        return R.layout.fragment_jlzt_detail;
    }

    @Override
    protected HdtwPresenter createPresenter() {
        return new HdtwPresenter(this);
    }

    @Override
    protected void initView() {
        super.initView();
        channelid = getArguments().getString("channelid");
        topicId = getArguments().getString("topicId");

        recycler_view.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        recycler_view.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.top = ViewUtil.dp2px(mContext, 0);
                outRect.bottom = ViewUtil.dp2px(mContext, 0);
            }
        });
        mJlztDetailAdapter = new JlztDetailAdapter(mContext);
        mJlztDetailAdapter.setTopicId(topicId);
        mJlztDetailAdapter.setmOnTextSendListener(this);
        recycler_view.setAdapter(mJlztDetailAdapter);

        mJlztDetailAdapter.set(mList);


    }

    @Override
    protected void initEvent() {
        super.initEvent();
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ZxtwActivity.startAction(getActivity(), topicId);
            }
        });
    }

    @Override
    protected void initData() {
        super.initData();


    }

    @Override
    public void onResume() {
        super.onResume();

    }

    public void onRefresh(String questionNum,String answerNum,List<CommQuestionBean.CommQuestionsBean> data) {
        tvTWFH.setText("共"+questionNum+"个提问，"+answerNum+"个回复");
        if (data == null) {
            return;
        }
        mList.clear();
        mList.addAll(data);
        mJlztDetailAdapter.set(mList);
    }

    public void loadMore(List<CommQuestionBean.CommQuestionsBean> data) {
        if (data == null) {
            return;
        }
        mList.addAll(data);
        mJlztDetailAdapter.set(mList);
    }

    @Override
    public void onTextSend(String msg, CommQuestionBean.CommQuestionsBean item, int position) {
        questionansweradd(msg, item.getId());
    }


    @Override
    public void questionansweraddSuccess(String data) {
        ToastUtil.showToast(getActivity(), data);

    }

    @Override
    public void questionansweraddFailed() {

    }

    private void questionansweradd(String msg, String questionId) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("topicId", topicId);
        queryMap.put("questionId", questionId);
        queryMap.put("content", msg);
        mPresenter.questionansweradd("", queryMap);

    }


}
