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
import takjxh.android.com.taapp.activityui.presenter.KtrwfkPresenter;
import takjxh.android.com.taapp.activityui.presenter.impl.IKtrwfkPresenter;
import takjxh.android.com.taapp.view.NormalTitleBar;

/**
 * 类名称：课题任务反馈
 *
 * @Author: libaibing
 * @Date: 2019-10-23 11:15
 * @Description:
 **/
public class KtrwfkActivity extends BaseActivity<KtrwfkPresenter> implements IKtrwfkPresenter.IView, View.OnClickListener {

    public static void startAction(Activity activity, String taskId) {
        Intent intent = new Intent(activity, KtrwfkActivity.class);
        intent.putExtra("taskId", taskId);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @BindView(R.id.ntb)
    NormalTitleBar ntb;
    @BindView(R.id.clue_content)
    EditText clue_content;


    private String taskId;

    /**
     * 返回布局文件
     */
    @Override
    protected int getContentViewId() {
        return R.layout.activity_ktrwfk;
    }


    @Override
    protected KtrwfkPresenter createPresenter() {
        return new KtrwfkPresenter(this);
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

        taskId = getIntent().getStringExtra("taskId");

        ntb = findViewById(R.id.ntb);
        ntb.setTitleText("任务心得反馈");
        ntb.setTvLeftVisiable(true);
        ntb.setRightImagVisibility1(true);
        ntb.setRightImagSrc1(R.mipmap.sc);
        ntb.setOnRightImagListener1(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //发表评论
                issuefeedbackadd();
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
    public void issuefeedbackaddSuccess(String data) {
        ToastUtil.showToast(this, data);
        finish();
    }

    @Override
    public void issuefeedbackaddFailed() {

    }


    private void issuefeedbackadd() {
        String mclue_content = clue_content.getText().toString().trim();
        if (TextUtils.isEmpty(mclue_content)) {
            ToastUtil.showToast(this, "请输入您的反馈！");
            return;
        }
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("taskId", taskId);
        queryMap.put("content", mclue_content);
        mPresenter.issuefeedbackadd("", queryMap);
    }
}
