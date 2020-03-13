package takjxh.android.com.taapp.activityui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import takjxh.android.com.commlibrary.utils.BarUtil;
import takjxh.android.com.commlibrary.utils.ToastUtil;
import takjxh.android.com.commlibrary.view.activity.BaseActivity;
import takjxh.android.com.taapp.R;
import takjxh.android.com.taapp.activityui.MainActivity;
import takjxh.android.com.taapp.activityui.bean.SysParamBean;
import takjxh.android.com.taapp.activityui.bean.UploadFileBean;
import takjxh.android.com.taapp.activityui.presenter.RegisterGLPresenter;
import takjxh.android.com.taapp.activityui.presenter.impl.IRegisterGLPresenter;
import takjxh.android.com.taapp.utils.CodeUtils;
import takjxh.android.com.taapp.view.NormalTitleBar;

/**
 * 类名称：管理员注册
 *
 * @Author: libaibing
 * @Date: 2019-10-15 9:50
 * @Description:
 **/
public class RegisterGLActivity extends BaseActivity<RegisterGLPresenter> implements IRegisterGLPresenter.IView, View.OnClickListener {

    public static void startAction(Activity activity) {
        Intent intent = new Intent(activity, RegisterGLActivity.class);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @BindView(R.id.ntb)
    NormalTitleBar ntb;
    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.imgsx)
    ImageView imgsx;
    @BindView(R.id.btn_login)
    Button btn_login;

    @BindView(R.id.edAccount)
    EditText edAccount;
    @BindView(R.id.edTP)
    EditText edTP;
    @BindView(R.id.edSJ)
    EditText edSJ;
    @BindView(R.id.edPassword)
    EditText edPassword;


    @BindView(R.id.tvcode)
    TextView tvcode;

    /**
     * 返回布局文件
     */
    @Override
    protected int getContentViewId() {
        return R.layout.activity_register_gl;
    }

    @Override
    protected RegisterGLPresenter createPresenter() {
        return new RegisterGLPresenter(this);
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
        ntb.setTitleText("管理员注册");
        ntb.setTvLeftVisiable(true);
        ntb.setOnBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        img.setImageBitmap(CodeUtils.getInstance().createBitmap()); //我们在控件初始化时设置随机生成图片验证码
    }

    /**
     * 设置事件
     */
    @Override
    protected void initEvent() {
        super.initEvent();
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                img.setImageBitmap(CodeUtils.getInstance().createBitmap()); //随机生成图片验证码
            }
        });
        imgsx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /*  String code = CodeUtils.getInstance().getCode();//获取图片验证码上的内容*/
                img.setImageBitmap(CodeUtils.getInstance().createBitmap()); //随机生成图片验证码
            }
        });


        btn_login.setOnClickListener(this);
        tvcode.setOnClickListener(this);

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
            case R.id.tvcode:
                getCode();
                break;
            case R.id.btn_login:
                register();
                break;

        }
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void registerSuccess(String msg) {
        MainActivity.startAction(RegisterGLActivity.this);
        finish();
    }

    @Override
    public void registerFailed() {

    }

    @Override
    public void getCodeSuccess() {
        ToastUtil.showToast(this, "获取验证码成功");
    }

    @Override
    public void getCodeFailed() {

    }

    @Override
    public void paramlistSuccess(SysParamBean bean) {

    }

    @Override
    public void paramlistFailed() {

    }

    @Override
    public void uploadSuccess(UploadFileBean data) {

    }

    private void getCode() {
        String mAccount = edAccount.getText().toString().trim();

        if (TextUtils.isEmpty(mAccount)) {
            ToastUtil.showToast(this, "请输入手机号码");
            return;
        }
        mPresenter.getCode(mAccount);
    }


    private void register() {
        String mAccount = edAccount.getText().toString().trim();
        if (TextUtils.isEmpty(mAccount)) {
            ToastUtil.showToast(this, "请输入手机号码");
            return;
        }
        String medTP = edTP.getText().toString().trim();
        if (TextUtils.isEmpty(medTP)) {
            ToastUtil.showToast(this, "请输入图片验证码");
            return;
        }
        String medSJ = edSJ.getText().toString().trim();
        if (TextUtils.isEmpty(medSJ)) {
            ToastUtil.showToast(this, "请输入手机验证码");
            return;
        }
        String medPassword = edPassword.getText().toString().trim();
        if (TextUtils.isEmpty(medPassword)) {
            ToastUtil.showToast(this, "请输入6-16位的密码");
            return;
        }
        String code = CodeUtils.getInstance().getCode();//获取图片验证码上的内容
        if (!code.equals(medTP)) {
            ToastUtil.showToast(this, "图片验证码不正确");
            return;
        }

       // mPresenter.register(medSJ, mAccount, medPassword, PackageUtils.getVersionName(this));

    }


}
