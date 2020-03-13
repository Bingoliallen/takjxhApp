package takjxh.android.com.taapp.activityui.activity;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import butterknife.BindView;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.model.UserInfo;
import takjxh.android.com.commlibrary.BaseAppProfile;
import takjxh.android.com.commlibrary.presenter.impl.BasePresenter;
import takjxh.android.com.commlibrary.utils.BarUtil;
import takjxh.android.com.commlibrary.utils.ShareUtils;
import takjxh.android.com.commlibrary.utils.ToastUtil;
import takjxh.android.com.commlibrary.view.activity.BaseActivity;
import takjxh.android.com.taapp.QbApplication;
import takjxh.android.com.taapp.R;
import takjxh.android.com.taapp.activityui.base.BaseDialog;
import takjxh.android.com.taapp.activityui.bean.LoginOut;
import takjxh.android.com.taapp.activityui.dialog.MessageDialog;
import takjxh.android.com.taapp.utils.CacheDataManager;
import takjxh.android.com.taapp.view.NormalTitleBar;

/**设置
 * Created by Administrator on 2019/10/17.
 */

public class SetingActivity extends BaseActivity implements View.OnClickListener{

    public static void startAction(Activity activity) {
        Intent intent = new Intent(activity, SetingActivity.class);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @BindView(R.id.ntb)
    NormalTitleBar ntb;
    @BindView(R.id.btn_login)
    Button btn_login;
    @BindView(R.id.tv_setting_clear)
    TextView tvSettingClear;
    @BindView(R.id.ll_setting_clear)
    LinearLayout llSettingClear;


    /**
     * 返回布局文件
     */
    @Override
    protected int getContentViewId() {
        return R.layout.activity_seting;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
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
        ntb.setTitleText("设置");
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
        btn_login.setOnClickListener(this);
        llSettingClear.setOnClickListener(this);
    }

    /**
     * 初始化数据
     */
    @Override
    protected void initData() {
        super.initData();
        setText();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.ll_setting_clear:
                CacheDataManager.clearAllCache(this);
                ToastUtil.showToast(this, getString(R.string.clear_success));
                setText();
                break;
            case R.id.btn_login:
                new MessageDialog.Builder(this)
                        // 标题可以不用填写
                        .setTitle("退出")
                        // 内容必须要填写
                        .setMessage("是否退出当前账号？")
                        // 确定按钮文本
                        .setConfirm("确定")
                        // 设置 null 表示不显示取消按钮
                        .setCancel("取消")
                        // 设置点击按钮后不关闭对话框
                        //.setAutoDismiss(false)
                        .setListener(new MessageDialog.OnListener() {

                            @Override
                            public void onConfirm(BaseDialog dialog) {
                               // toast("确定了");
                                loginOut();
                            }

                            @Override
                            public void onCancel(BaseDialog dialog) {
                               // toast("取消了");
                            }
                        })
                        .show();

                break;
        }



    }

    public void loginOut() {
        UserInfo info = JMessageClient.getMyInfo();
        if(null != info){
            JMessageClient.logout();
        }
        QbApplication.mBaseApplication.delConversation = null;
        QbApplication.mBaseApplication.forwardMsg.clear();
        QbApplication.mBaseApplication.alreadyRead.clear();
        QbApplication.mBaseApplication.unRead.clear();
        QbApplication.mBaseApplication.isAtMe.clear();
        QbApplication.mBaseApplication.isAtall.clear();
        QbApplication.mBaseApplication.ids.clear();
        ShareUtils.deleteShare(BaseAppProfile.getApplication(), "jchat_userId");

        ShareUtils.deleteShare(BaseAppProfile.getApplication(), "jchat_userPassword");
        ShareUtils.deleteShare(BaseAppProfile.getApplication(), "jchat_cached_avatar_path");
        ShareUtils.deleteShare(BaseAppProfile.getApplication(), "jchat_isShowCheck");

        ShareUtils.deleteShare(BaseAppProfile.getApplication(), "station");
        ShareUtils.deleteShare(BaseAppProfile.getApplication(), "isTeacher");

        ShareUtils.deleteShare(BaseAppProfile.getApplication(), "userId");
        ShareUtils.deleteShare(BaseAppProfile.getApplication(), "userName");
        ShareUtils.deleteShare(BaseAppProfile.getApplication(), "name");
        ShareUtils.deleteShare(BaseAppProfile.getApplication(), "mobilePhone");
        ShareUtils.deleteShare(BaseAppProfile.getApplication(), "company");
        ShareUtils.deleteShare(BaseAppProfile.getApplication(), "type");
        ShareUtils.deleteShare(BaseAppProfile.getApplication(), "score");
        ShareUtils.deleteShare(BaseAppProfile.getApplication(), "isOpenMsg");
        ShareUtils.deleteShare(BaseAppProfile.getApplication(), "isOpenVoice");
        ShareUtils.deleteShare(BaseAppProfile.getApplication(), "avatar");
        ShareUtils.deleteShare(BaseAppProfile.getApplication(), "token");
        QbApplication.mBaseApplication.userExts=new ArrayList<>();
        EventBus.getDefault().post(new LoginOut());
        finish();
        LoginActivity.startAction(this);
    }


    private void setText() {
        try {
            tvSettingClear.setText(CacheDataManager.getTotalCacheSize(this));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

