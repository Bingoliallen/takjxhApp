package takjxh.android.com.taapp.activityui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import takjxh.android.com.commlibrary.utils.BarUtil;
import takjxh.android.com.commlibrary.utils.ToastUtil;
import takjxh.android.com.commlibrary.view.activity.BaseActivity;
import takjxh.android.com.taapp.R;
import takjxh.android.com.taapp.activityui.presenter.ForgetPwdPresenter;
import takjxh.android.com.taapp.activityui.presenter.impl.IForgetPwdPresenter;
import takjxh.android.com.taapp.utils.RxRegTool;
import takjxh.android.com.taapp.view.NormalTitleBar;

/**
 * 类名称：忘记密码
 *
 * @Author: libaibing
 * @Date: 2019-10-15 10:21
 * @Description:
 **/
public class ForgetPwdActivity extends BaseActivity<ForgetPwdPresenter> implements IForgetPwdPresenter.IView, View.OnClickListener {

    public static void startAction(Activity activity) {
        Intent intent = new Intent(activity, ForgetPwdActivity.class);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @BindView(R.id.ntb)
    NormalTitleBar ntb;


    @BindView(R.id.ml1)
    LinearLayout ml1;
    @BindView(R.id.edAccount)
    EditText edAccount;
    @BindView(R.id.edPassword)
    EditText edPassword;
    @BindView(R.id.tvGetCode)
    TextView tvGetCode;
    @BindView(R.id.btn_login)
    Button btn_login;

    @BindView(R.id.ml2)
    LinearLayout ml2;
    @BindView(R.id.edAccount1)
    EditText edAccount1;
    @BindView(R.id.edPassword1)
    EditText edPassword1;
    @BindView(R.id.btn_login1)
    Button btn_login1;

    String medSJ1 = "";


    /**
     * 返回布局文件
     */
    @Override
    protected int getContentViewId() {
        return R.layout.activity_forget_pwd;
    }

    @Override
    protected ForgetPwdPresenter createPresenter() {
        return new ForgetPwdPresenter(this);
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
        ntb = findViewById(R.id.ntb);
        ntb.setTitleText("忘记密码");
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

        tvGetCode.setOnClickListener(this);
        btn_login.setOnClickListener(this);
        btn_login1.setOnClickListener(this);
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
            case R.id.tvGetCode:
                //短信验证
                getCode();
                break;
            case R.id.btn_login:
                //短信验证
                String medSJ = edPassword.getText().toString().trim();
                if (TextUtils.isEmpty(medSJ)) {
                    ToastUtil.showToast(this, "请输入手机验证码");
                    return;
                }
                medSJ1 = edPassword.getText().toString().trim();
                ml1.setVisibility(View.GONE);
                ml2.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_login1:
                //提交
                updatepwd();
                break;
        }
    }


    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void updatepwdSuccess(String msg) {
        ToastUtil.showToast(this, msg);
        finish();
    }

    @Override
    public void updatepwdFailed() {
        edAccount.setText("");
        edPassword.setText("");
        edAccount1.setText("");
        edPassword1.setText("");

        medSJ1 = "";
        ml1.setVisibility(View.VISIBLE);
        ml2.setVisibility(View.GONE);
    }

    @Override
    public void getCodeSuccess() {
        medSJ1 = "";
        edPassword.setText("");
        ToastUtil.showToast(this, "已发送手机验证码！");
    }

    @Override
    public void getCodeFailed() {

    }

    private void getCode() {
        String mAccount = edAccount.getText().toString().trim();

        if (TextUtils.isEmpty(mAccount)) {
            ToastUtil.showToast(this, "请输入11位注册手机号码");
            return;
        }
        if (!RxRegTool.isMobile(mAccount)) {
            ToastUtil.showToast(this, "请输入正确的手机号码");
            return;
        }


        mPresenter.getCode(mAccount);
    }


    private void updatepwd() {
        String medPassword1 = edAccount1.getText().toString().trim();
        if (TextUtils.isEmpty(medPassword1)) {
            ToastUtil.showToast(this, "请输入6-16位的密码");
            return;
        }
        String medPassword2 = edPassword1.getText().toString().trim();
        if (TextUtils.isEmpty(medPassword2)) {
            ToastUtil.showToast(this, "请再次输入您的6-16位密码");
            return;
        }
        if (!medPassword2.equals(medPassword1)) {
            ToastUtil.showToast(this, "两次输入的密码不一致");
            return;
        }

        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("code", medSJ1);
        queryMap.put("oldPwd", medPassword1);
        queryMap.put("newPwd", medPassword2);

        mPresenter.updatepwd("", queryMap);

    }

}
