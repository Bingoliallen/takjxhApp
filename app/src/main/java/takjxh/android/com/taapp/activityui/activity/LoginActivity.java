package takjxh.android.com.taapp.activityui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.model.UserInfo;
import cn.jpush.im.api.BasicCallback;
import takjxh.android.com.commlibrary.BaseAppProfile;
import takjxh.android.com.commlibrary.utils.BarUtil;
import takjxh.android.com.commlibrary.utils.ShareUtils;
import takjxh.android.com.commlibrary.utils.ToastUtil;
import takjxh.android.com.commlibrary.view.activity.BaseActivity;
import takjxh.android.com.taapp.QbApplication;
import takjxh.android.com.taapp.R;
import takjxh.android.com.taapp.activityui.MainActivity;
import takjxh.android.com.taapp.activityui.bean.LoginIn;
import takjxh.android.com.taapp.activityui.bean.RegisterSuc;
import takjxh.android.com.taapp.activityui.bean.RegisterSuccess;
import takjxh.android.com.taapp.activityui.presenter.LoginUIPresenter;
import takjxh.android.com.taapp.activityui.presenter.impl.ILoginUIPresenter;
import takjxh.android.com.taapp.utils.RxRegTool;
import takjxh.android.com.taapp.view.NormalTitleBar;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-10-15 9:14
 * @Description:
 **/
public class LoginActivity extends BaseActivity<LoginUIPresenter> implements ILoginUIPresenter.IView ,View.OnClickListener{

    public static void startAction(Activity activity) {
        Intent intent = new Intent(activity, LoginActivity.class);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @BindView(R.id.ntb)
    NormalTitleBar ntb;
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.tv2)
    TextView tv2;
    @BindView(R.id.btn_login)
    Button btn_login;

    @BindView(R.id.edAccount)
    EditText edAccount;
    @BindView(R.id.edPassword)
    EditText edPassword;


    /**
     * 返回布局文件
     */
    @Override
    protected int getContentViewId() {
        return R.layout.activity_login_ui;
    }

    @Override
    protected LoginUIPresenter createPresenter() {
        return new LoginUIPresenter(this);
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
        //注册
        EventBus.getDefault().register(this);
        ntb = findViewById(R.id.ntb);
        ntb.setTitleText("账号登录");
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
        tv1.setOnClickListener( this);
        tv2.setOnClickListener( this);
        btn_login.setOnClickListener( this);
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
       switch (v.getId()){
           case R.id.btn_login:
               login();

               break;
           case R.id.tv1:
               RegisterActivity.startAction(LoginActivity.this);

               break;
           case R.id.tv2:
               ForgetPwdActivity.startAction(LoginActivity.this);
               break;
       }
    }


    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void loginSuccess() {
        String mAccount = edAccount.getText().toString().trim();
        ShareUtils.putString(BaseAppProfile.getApplication(), "jchat_userId", mAccount);

        String mPassword =  ShareUtils.getString(BaseAppProfile.getApplication(), "jchat_userPassword", "");
        JMessageClient.login(mAccount, mPassword, new BasicCallback() {
            @Override
            public void gotResult(int responseCode, String responseMessage) {

                if (responseCode == 0) {
                    ShareUtils.putString(BaseAppProfile.getApplication(), "jchat_userPassword", mPassword);
                    UserInfo myInfo = JMessageClient.getMyInfo();
                    File avatarFile = myInfo.getAvatarFile();
                    //登陆成功,如果用户有头像就把头像存起来,没有就设置null
                    if (avatarFile != null) {
                        ShareUtils.putString(BaseAppProfile.getApplication(), "jchat_cached_avatar_path", avatarFile.getAbsolutePath());
                    } else {
                        ShareUtils.putString(BaseAppProfile.getApplication(), "jchat_cached_avatar_path", null);
                    }
                    String name = ShareUtils.getString(BaseAppProfile.getApplication(), "name", "");
                    myInfo.setNickname(name);


                    JMessageClient.updateMyInfo(UserInfo.Field.nickname, myInfo, new BasicCallback() {
                        @Override
                        public void gotResult(int responseCode, String responseMessage) {
                            if (responseCode == 0) {

                            } else {

                            }
                        }
                    });

                    EventBus.getDefault().post(new LoginIn());
                    MainActivity.startAction(LoginActivity.this);
                    finish();
                } else {
                    ToastUtil.showToast(LoginActivity.this, "极光IM:" + responseMessage);
                    EventBus.getDefault().post(new LoginIn());
                    MainActivity.startAction(LoginActivity.this);
                    finish();
                }
            }
        });

    }

    @Override
    public void loginFailed() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //取消注册 , 防止Activity内存泄漏
        EventBus.getDefault().unregister(this);
    }

    //消息推送通知收到事件
    @Subscribe
    public void onEvent(RegisterSuccess note) {
        if (note != null ) {
            EventBus.getDefault().post(new LoginIn());
            finish();
        }
    }


    //消息推送通知收到事件
    @Subscribe
    public void onEvent(RegisterSuc note) {
        if (note != null ) {
            String userId = ShareUtils.getString(BaseAppProfile.getApplication(), "jchat_userId", "");
            String mPassword = ShareUtils.getString(BaseAppProfile.getApplication(), "jchat_userPassword", "");
            JMessageClient.login(userId, mPassword, new BasicCallback() {
                @Override
                public void gotResult(int responseCode, String responseMessage) {

                    if (responseCode == 0) {

                        UserInfo myInfo = JMessageClient.getMyInfo();
                        File avatarFile = myInfo.getAvatarFile();
                        //登陆成功,如果用户有头像就把头像存起来,没有就设置null
                        if (avatarFile != null) {
                            ShareUtils.putString(BaseAppProfile.getApplication(), "jchat_cached_avatar_path", avatarFile.getAbsolutePath());
                        } else {
                            ShareUtils.putString(BaseAppProfile.getApplication(), "jchat_cached_avatar_path", null);
                        }
                        String name = ShareUtils.getString(BaseAppProfile.getApplication(), "name", "");
                        myInfo.setNickname(name);

                        JMessageClient.updateMyInfo(UserInfo.Field.nickname, myInfo, new BasicCallback() {
                            @Override
                            public void gotResult(int responseCode, String responseMessage) {
                                if (responseCode == 0) {

                                } else {

                                }
                            }
                        });

                        EventBus.getDefault().post(new LoginIn());
                        finish();
                    } else {
                        EventBus.getDefault().post(new LoginIn());
                        finish();
                    }
                }
            });





        }
    }



    private void login() {
        String mAccount = edAccount.getText().toString().trim();
        String mPassword = edPassword.getText().toString().trim();
        if (TextUtils.isEmpty(mAccount)) {
            ToastUtil.showToast(this, "请输入手机号码");
            return;
        }
        if (!RxRegTool.isMobile(mAccount)) {
            ToastUtil.showToast(this, "请输入正确的手机号码");
            return;
        }

        if (TextUtils.isEmpty(mPassword)) {
            ToastUtil.showToast(this, "请输入密码");
            return;
        }
        Map<String,String> queryMap=new HashMap<>();
        queryMap.put("username", mAccount);
        queryMap.put("password", mPassword);
        mPresenter.loginUI(queryMap);

    }









}
