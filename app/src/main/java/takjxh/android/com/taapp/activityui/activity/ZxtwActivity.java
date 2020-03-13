package takjxh.android.com.taapp.activityui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import takjxh.android.com.commlibrary.presenter.impl.BasePresenter;
import takjxh.android.com.commlibrary.utils.BarUtil;
import takjxh.android.com.commlibrary.utils.ToastUtil;
import takjxh.android.com.commlibrary.view.activity.BaseActivity;
import takjxh.android.com.taapp.R;
import takjxh.android.com.taapp.activityui.bean.CommDetailBean;
import takjxh.android.com.taapp.activityui.presenter.ZxtwPresenter;
import takjxh.android.com.taapp.activityui.presenter.impl.IZxtwPresenter;
import takjxh.android.com.taapp.view.NormalTitleBar;

/**
 * 类名称：撰写提问
 *
 * @Author: libaibing
 * @Date: 2019-10-18 9:01
 * @Description:
 **/
public class ZxtwActivity extends BaseActivity <ZxtwPresenter> implements IZxtwPresenter.IView, View.OnClickListener {

    public static void startAction(Activity activity,String topicId) {
        Intent intent = new Intent(activity, ZxtwActivity.class);
        intent.putExtra("topicId",topicId);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @BindView(R.id.ntb)
    NormalTitleBar ntb;

    @BindView(R.id.tvtitle)
    TextView tvtitle;
    @BindView(R.id.clue_content)
    EditText clue_content;

    private String topicId;
    /**
     * 返回布局文件
     */
    @Override
    protected int getContentViewId() {
        return R.layout.activity_zxtw;
    }


    @Override
    protected ZxtwPresenter createPresenter() {
        return new ZxtwPresenter(this);
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



        ntb = findViewById(R.id.ntb);
        ntb.setTitleText("撰写提问");
        ntb.setTvLeftVisiable(true);
        ntb.setRightImagVisibility1(true);
        ntb.setRightImagSrc1(R.mipmap.sc);
        ntb.setOnRightImagListener1(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //发布
                questionadd();

            }
        });
        ntb.setOnBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mPresenter.commdetail("",topicId);
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
    public void commdetailSuccess(CommDetailBean.DetailBean data) {
        if (data == null) {
            return;
        }
        tvtitle.setText("关于"+data.getTitle()+"的咨询");
    }

    @Override
    public void commdetailFailed() {

    }

    @Override
    public void questionaddSuccess(String data) {
        ToastUtil.showToast(this, data);
        finish();
    }

    @Override
    public void questionaddFailed() {

    }

    private void questionadd(){

        String mclue_content = clue_content.getText().toString().trim();
        if (TextUtils.isEmpty(mclue_content)) {
            ToastUtil.showToast(this, "请输入您的问题");
            return;
        }

        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("topicId", topicId);
        queryMap.put("content", mclue_content);
        mPresenter.questionadd("", queryMap);
    }
}
