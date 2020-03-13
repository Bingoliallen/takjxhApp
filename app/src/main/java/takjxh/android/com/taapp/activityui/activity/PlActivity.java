package takjxh.android.com.taapp.activityui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import takjxh.android.com.commlibrary.utils.BarUtil;
import takjxh.android.com.commlibrary.utils.ToastUtil;
import takjxh.android.com.commlibrary.view.activity.BaseActivity;
import takjxh.android.com.taapp.R;
import takjxh.android.com.taapp.activityui.presenter.HdtwPresenter;
import takjxh.android.com.taapp.activityui.presenter.impl.IHdtwPresenter;
import takjxh.android.com.taapp.view.NormalTitleBar;

/**
 * 类名称： 回答交流主题的问题
 *
 * @Author: libaibing
 * @Date: 2019-10-15 15:23
 * @Description:
 **/
public class PlActivity extends BaseActivity<HdtwPresenter> implements IHdtwPresenter.IView, View.OnClickListener {

    public static void startAction(Activity activity, String topicId, String questionId) {
        Intent intent = new Intent(activity, PlActivity.class);
        intent.putExtra("topicId", topicId);
        intent.putExtra("questionId", questionId);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @BindView(R.id.ntb)
    NormalTitleBar ntb;

    @BindView(R.id.clue_content)
    EditText clue_content;

    private String topicId;
    private String questionId;

    /**
     * 返回布局文件
     */
    @Override
    protected int getContentViewId() {
        return R.layout.activity_pl;
    }


    @Override
    protected HdtwPresenter createPresenter() {
        return new HdtwPresenter(this);
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

        ntb = findViewById(R.id.ntb);
        ntb.setTitleText("回答提问");
        ntb.setTvLeftVisiable(true);
        ntb.setRightImagVisibility1(true);
        ntb.setRightImagSrc1(R.mipmap.sc);
        ntb.setOnRightImagListener1(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //发表评论
                questionansweradd();

            }
        });
        ntb.setOnBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
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
    public Context getContext() {
        return this;
    }

    @Override
    public void questionansweraddSuccess(String data) {
        ToastUtil.showToast(this, data);
        finish();
    }

    @Override
    public void questionansweraddFailed() {

    }


    private void questionansweradd() {

        String mclue_content = clue_content.getText().toString().trim();
        if (TextUtils.isEmpty(mclue_content)) {
            ToastUtil.showToast(this, "请输入您的回答提问");
            return;
        }

        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("topicId", topicId);
        queryMap.put("questionId", questionId);
        queryMap.put("content", mclue_content);
        mPresenter.questionansweradd("", queryMap);

    }


}
