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
import takjxh.android.com.taapp.activityui.presenter.TwDyPresenter;
import takjxh.android.com.taapp.activityui.presenter.impl.ITwDyPresenter;
import takjxh.android.com.taapp.view.NormalTitleBar;

/**
 * 类名称：回答继续教育答疑
 *
 * @Author: libaibing
 * @Date: 2019-11-19 9:42
 * @Description:
 **/
public class TwDyActivity extends BaseActivity<TwDyPresenter> implements ITwDyPresenter.IView, View.OnClickListener {

    public static void startAction(Activity activity, String qaId) {
        Intent intent = new Intent(activity, TwDyActivity.class);
        intent.putExtra("qaId", qaId);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @BindView(R.id.ntb)
    NormalTitleBar ntb;

    @BindView(R.id.clue_content)
    EditText clue_content;

    private String qaId;

    /**
     * 返回布局文件
     */
    @Override
    protected int getContentViewId() {
        return R.layout.activity_twdy;
    }


    @Override
    protected TwDyPresenter createPresenter() {
        return new TwDyPresenter(this);
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
        qaId = getIntent().getStringExtra("qaId");

        ntb = findViewById(R.id.ntb);
        ntb.setTitleText("继续教育答疑");
        ntb.setTvLeftVisiable(true);
        ntb.setRightImagVisibility1(true);
        ntb.setRightImagSrc1(R.mipmap.sc);
        ntb.setOnRightImagListener1(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //发表评论
                qaansweradd();

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
    public void qaansweraddSuccess(String data) {
        ToastUtil.showToast(this, data);
        finish();
    }

    @Override
    public void qaansweraddFailed() {

    }


    private void qaansweradd() {

        String mclue_content = clue_content.getText().toString().trim();
        if (TextUtils.isEmpty(mclue_content)) {
            ToastUtil.showToast(this, "请输入您的回答提问");
            return;
        }

        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("qaId", qaId);
        queryMap.put("content", mclue_content);
        mPresenter.qaansweradd("", queryMap);

    }


}
