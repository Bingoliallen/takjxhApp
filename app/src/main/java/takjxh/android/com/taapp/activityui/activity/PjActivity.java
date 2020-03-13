package takjxh.android.com.taapp.activityui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import takjxh.android.com.commlibrary.utils.BarUtil;
import takjxh.android.com.commlibrary.utils.ToastUtil;
import takjxh.android.com.commlibrary.view.activity.BaseActivity;
import takjxh.android.com.taapp.R;
import takjxh.android.com.taapp.activityui.bean.PolicyApplyEvaluateDetailBean;
import takjxh.android.com.taapp.activityui.presenter.PjPresenter;
import takjxh.android.com.taapp.activityui.presenter.impl.IPjPresenter;
import takjxh.android.com.taapp.view.NormalTitleBar;

/**
 * 类名称：评价-给企业打分
 *
 * @Author: libaibing
 * @Date: 2019-10-16 10:49
 * @Description:
 **/
public class PjActivity extends BaseActivity<PjPresenter> implements IPjPresenter.IView, View.OnClickListener {

    public static void startAction(Activity activity, String id) {
        Intent intent = new Intent(activity, PjActivity.class);
        intent.putExtra("id", id);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @BindView(R.id.ntb)
    NormalTitleBar ntb;
    @BindView(R.id.ratingBar1)
    RatingBar ratingBar1;


    @BindView(R.id.tvtitle)
    TextView tvtitle;
    @BindView(R.id.tvtime)
    TextView tvtime;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.clue_content)
    EditText clue_content;

    @BindView(R.id.btn_login)
    Button btn_login;

    private String id;
    private boolean isDF = false;
    private float ratingDF = 0;
    private String helpId;

    /**
     * 返回布局文件
     */
    @Override
    protected int getContentViewId() {
        return R.layout.activity_pj;
    }


    @Override
    protected PjPresenter createPresenter() {
        return new PjPresenter(this);
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
        ntb.setTitleText("第三方服务公司评价");
        ntb.setTvLeftVisiable(true);

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
        ratingBar1.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {

            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                isDF = true;
                ratingDF = rating;
            }
        });

        btn_login.setOnClickListener(this);
    }

    /**
     * 初始化数据
     */
    @Override
    protected void initData() {
        super.initData();
        mPresenter.policyapplyevaluatedetail("", id);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login:
                applyadddevaluate();
                break;
        }
    }


    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void policyapplyevaluatedetailSuccess(PolicyApplyEvaluateDetailBean.DetailVoBean data) {
        if (data == null) {
            return;
        }
        helpId = data.getHelpId();
        tvtitle.setText("给" + data.getOrganName() + "的表现打分");
        tvtime.setText(data.des);
        tvName.setText(data.getLinkman());
        btn_login.setVisibility(View.VISIBLE);
    }

    @Override
    public void policyapplyevaluatedetailFailed() {

    }

    @Override
    public void applyadddevaluateSuccess(String data) {
        ToastUtil.showToast(this, data);
        finish();
    }

    @Override
    public void applyadddevaluateFailed() {

    }


    private void applyadddevaluate() {
        if (!isDF) {
            ToastUtil.showToast(this, "请给第三方服务公司打分");
            return;
        }
        String content = clue_content.getText().toString().trim();
        if (TextUtils.isEmpty(content)) {
            ToastUtil.showToast(this, "请输入您对第三方服务公司的评价");
            return;
        }

        Map<String, Object> searchRequest = new HashMap<>();
        searchRequest.put("helpId", helpId);
        searchRequest.put("evaluateStar", ratingDF);
        searchRequest.put("evaluateDes", content);
        mPresenter.applyadddevaluate("", searchRequest);
    }


}