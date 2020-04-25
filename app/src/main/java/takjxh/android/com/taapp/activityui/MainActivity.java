package takjxh.android.com.taapp.activityui;

import android.Manifest;
import android.app.Activity;
import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.stetho.common.LogUtil;
//import com.mob.pushsdk.MobPush;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import cn.jiguang.api.JCoreInterface;
import cn.jpush.android.api.BasicPushNotificationBuilder;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.callback.GetUserInfoCallback;
import cn.jpush.im.android.api.model.UserInfo;
import cn.jpush.im.api.BasicCallback;
import takjxh.android.com.commlibrary.BaseAppProfile;
import takjxh.android.com.commlibrary.presenter.impl.BasePresenter;
import takjxh.android.com.commlibrary.utils.BarUtil;
import takjxh.android.com.commlibrary.utils.L;
import takjxh.android.com.commlibrary.utils.ShareUtils;
import takjxh.android.com.commlibrary.utils.ThreadUtil;
import takjxh.android.com.commlibrary.view.activity.BaseActivity;
import takjxh.android.com.taapp.QbApplication;
import takjxh.android.com.taapp.R;
import takjxh.android.com.taapp.activityui.activity.LoginActivity;
import takjxh.android.com.taapp.activityui.bean.LoginOut;
import takjxh.android.com.taapp.activityui.chat.takevideo.utils.LogUtils;
import takjxh.android.com.taapp.activityui.fragment.GRFragment;
import takjxh.android.com.taapp.activityui.fragment.QYFragment;
import takjxh.android.com.taapp.activityui.fragment.WDFragment;
import takjxh.android.com.taapp.activityui.fragment.ZFFragment;
import takjxh.android.com.taapp.activityui.fragment.ZYFragment;
import takjxh.android.com.taapp.activityui.presenter.MainPresenter;
import takjxh.android.com.taapp.activityui.presenter.impl.IMainPresenter;
import takjxh.android.com.taapp.base.AppConstant;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-10-14 8:54
 * @Description:
 **/
public class MainActivity extends BaseActivity<MainPresenter> implements IMainPresenter.IView,View.OnClickListener {

    /**
     * 入口
     *
     * @param activity
     */
    public static void startAction(Activity activity) {
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }


    private int currentTabPosition = 2;

    private GRFragment mGRFragment;
    private QYFragment mQYFragment;
    private WDFragment mWDFragment;
    private ZFFragment mZFFragment;
    private ZYFragment mZYFragment;

    private long lastClickTime = 0;
    private LinearLayout all_one, all_two, all_three, all_four;
    private RelativeLayout rlAddDevices;


    private TextView mIv_home_press;
    private TextView mIv_home_normal;
    private TextView mTv_home_normal;
    private TextView mTv_home_press;

    private TextView mIv_fishpond_press;
    private TextView mIv_fishpond_normal;
    private TextView mTv_fishpond_normal;
    private TextView mTv_fishpond_press;

    private TextView mIv_message_normal;
    private TextView mIv_message_press;
    private TextView mTv_message_normal;
    private TextView mTv_message_press;

    private TextView mIv_mine_press;
    private TextView mIv_mine_normal;
    private TextView mTv_mine_normal;
    private TextView mTv_mine_press;

    private TextView tvtvNull;
    private TextView tvtvNull1;
    private LinearLayout tvNull;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main_ui;
    }

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter(this);
    }

    @Override
    protected void beforeSetContentView() {
        super.beforeSetContentView();
        BarUtil.hideActionBar(this);
    }


    @Override
    protected void initView() {
        super.initView();
        //注册
        EventBus.getDefault().register(this);



        String  mRegistrationID=JPushInterface.getRegistrationID(this);

       // MobPush.setNotifyIcon(R.mipmap.ic_launcher);
       // MobPush.setShowBadge(true); //默认是关闭的，设置true为打开显示角标，反之则为关闭显示角标

        all_one = (LinearLayout) findViewById(R.id.all_one);
        all_one.setOnClickListener(this);
        all_two = (LinearLayout) findViewById(R.id.all_two);
        all_two.setOnClickListener(this);
        all_three = (LinearLayout) findViewById(R.id.all_three);
        all_three.setOnClickListener(this);
        all_four = (LinearLayout) findViewById(R.id.all_four);
        rlAddDevices = (RelativeLayout) findViewById(R.id.rlAddDevices);
        all_four.setOnClickListener(this);


        mIv_home_press = (TextView) findViewById(R.id.iv_home_press);
        mIv_home_normal = (TextView) findViewById(R.id.iv_home_normal);
        mTv_home_normal = (TextView) findViewById(R.id.tv_home_normal);
        mTv_home_press = (TextView) findViewById(R.id.tv_home_press);


        mIv_fishpond_press = (TextView) findViewById(R.id.iv_fishpond_press);
        mIv_fishpond_normal = (TextView) findViewById(R.id.iv_fishpond_normal);
        mTv_fishpond_normal = (TextView) findViewById(R.id.tv_fishpond_normal);
        mTv_fishpond_press = (TextView) findViewById(R.id.tv_fishpond_press);


        mIv_message_normal = (TextView) findViewById(R.id.iv_message_normal);
        mIv_message_press = (TextView) findViewById(R.id.iv_message_press);
        mTv_message_normal = (TextView) findViewById(R.id.tv_message_normal);
        mTv_message_press = (TextView) findViewById(R.id.tv_message_press);


        mIv_mine_press = (TextView) findViewById(R.id.iv_mine_press);
        mIv_mine_normal = (TextView) findViewById(R.id.iv_mine_normal);
        mTv_mine_normal = (TextView) findViewById(R.id.tv_mine_normal);
        mTv_mine_press = (TextView) findViewById(R.id.tv_mine_press);

        tvNull = (LinearLayout) findViewById(R.id.tvNull);
        tvNull.setOnClickListener(this);
        tvtvNull = (TextView) findViewById(R.id.tvtvNull);
        tvtvNull1 = (TextView) findViewById(R.id.tvtvNull1);
        //默认选中第一个
        setTransparency();
        tvNull.setBackgroundResource(R.drawable.login_ed_shape_p);
        tvtvNull.setVisibility(View.GONE);
        tvtvNull1.setVisibility(View.VISIBLE);


        String token = ShareUtils.getString(BaseAppProfile.getApplication(), "token", "");
        if (!TextUtils.isEmpty(token)) {

            Map<String, Object> queryMap = new HashMap<>();
            queryMap.put("registrationId",mRegistrationID);
            mPresenter.registrationid(queryMap);

            UserInfo info = JMessageClient.getMyInfo();
            if (null != info) {
                String name = ShareUtils.getString(BaseAppProfile.getApplication(), "name", "");
                info.setNickname(name);

                updateMyInfo(info);
            } else {
                String userId = ShareUtils.getString(BaseAppProfile.getApplication(), "jchat_userId", "");
                String mPassword = ShareUtils.getString(BaseAppProfile.getApplication(), "jchat_userPassword", "");
                if(!TextUtils.isEmpty(mPassword)){
                    JMessageClient.login(userId, mPassword, new BasicCallback() {
                        @Override
                        public void gotResult(int responseCode, String responseMessage) {
                             Log.e("TAG","-------responseCode------:"+responseCode);
                            if (responseCode == 0) {
                                UserInfo minfo = JMessageClient.getMyInfo();
                                String name = ShareUtils.getString(BaseAppProfile.getApplication(), "name", "");
                                minfo.setNickname(name);

                                updateMyInfo(minfo);
                            }else if (responseCode == 801003)  {
                                
                            }
                            else if (responseCode == 800005 ||responseCode == 800006 ||responseCode == 800004 ||responseCode == 810005)  {
                                JMessageClient.register(userId, mPassword, new BasicCallback() {
                                    @Override
                                    public void gotResult(int i, String s) {
                                        if (i == 0) {

                                            JMessageClient.login(userId, mPassword, new BasicCallback() {
                                                @Override
                                                public void gotResult(int responseCode, String responseMessage) {

                                                    if (responseCode == 0) {
                                                        UserInfo myInfo = JMessageClient.getMyInfo();
                                                        String name = ShareUtils.getString(BaseAppProfile.getApplication(), "name", "");
                                                        myInfo.setNickname(name);
                                                        updateMyInfo(myInfo);

                                                    } else {

                                                    }
                                                }
                                            });

                                        } else {

                                        }
                                    }
                                });
                            }
                        }
                    });

                }else {
                    ShareUtils.deleteShare(BaseAppProfile.getApplication(), "token");
                }


               /* JMessageClient.getUserInfo(userId, new GetUserInfoCallback() {
                    @Override
                    public void gotResult(int responseCode, String responseMessage, UserInfo minfo) {
                        if (responseCode == 0) {
                            if (minfo != null) {

                            }
                        }
                    }
                });*/
            }
        } else {

        }

    }

    private void updateMyInfo(UserInfo info) {
        JMessageClient.updateMyInfo(UserInfo.Field.nickname, info, new BasicCallback() {
            @Override
            public void gotResult(int responseCode, String responseMessage) {
                if (responseCode == 0) {

                } else {

                }
            }
        });

        //更新头像
        final String avatarPath = ShareUtils.getString(BaseAppProfile.getApplication(), "jchat_cached_avatar_path", null);
        if (avatarPath != null) {
            ThreadUtil.runInThread(new Runnable() {
                @Override
                public void run() {
                    try {
                        JMessageClient.updateUserAvatar(new File(avatarPath), new BasicCallback() {
                            @Override
                            public void gotResult(int responseCode, String responseMessage) {
                                if (responseCode == 0) {
                                    ShareUtils.putString(BaseAppProfile.getApplication(), "jchat_cached_avatar_path", avatarPath);
                                }
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } else {
            ShareUtils.putString(BaseAppProfile.getApplication(), "jchat_cached_avatar_path", null);
        }

    }


    /* 检查使用权限 */
    protected void checkPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            try {
                PackageManager pm = getPackageManager();
                PackageInfo pi = pm.getPackageInfo(getPackageName(), PackageManager.GET_PERMISSIONS);
                ArrayList<String> list = new ArrayList<String>();
                for (String p : pi.requestedPermissions) {
                    if (checkSelfPermission(p) != PackageManager.PERMISSION_GRANTED) {
                        list.add(p);
                    }
                }
                if (list.size() > 0) {
                    String[] permissions = list.toArray(new String[list.size()]);
                    if (permissions != null) {
                        requestPermissions(permissions, 1);
                    }
                }
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        try {
            if (permissions != null && permissions.length > 0) {
                StringBuilder sb = null;
                String permission;
                for (int i = 0; i < permissions.length; i++) {
                    if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                        continue;
                    }
                    if (sb == null) {
                        sb = new StringBuilder();
                    }
                    permission = permissions[i];
                }
                if (sb != null) {
                    //toast 提示用户已经禁用了必要的权限，然后去权限中心打开即可
                    //   Toast.makeText(getApplication(), R.string.toast_permission_deny, Toast.LENGTH_SHORT).show();
                }
            }
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    @Override
    protected void initEvent() {
        super.initEvent();
    }

    @Override
    protected void initData() {
        super.initData();
        //初始化frament
        initFragment(savedInstanceState);
        requestPermission();
        checkPermissions();
    }

    @Override
    protected void onPause() {
        isForeground = false;
        JCoreInterface.onPause(this);
        super.onPause();
    }

    @Override
    protected void onResume() {
        isForeground = true;
        JCoreInterface.onResume(this);
        super.onResume();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //取消注册 , 防止Activity内存泄漏
        EventBus.getDefault().unregister(this);
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //奔溃前保存位置
        L.d("", "onSaveInstanceState进来了1");
        LogUtil.e("onSaveInstanceState进来了2");
        outState.putInt(AppConstant.HOME_CURRENT_TAB_POSITION, currentTabPosition);
    }


    /**
     * 初始化碎片
     */
    private void initFragment(Bundle savedInstanceState) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        int currentTabPosition = 2;
        if (savedInstanceState != null) {

            mZFFragment = (ZFFragment) getSupportFragmentManager().findFragmentByTag("mZFFragment");
            mQYFragment = (QYFragment) getSupportFragmentManager().findFragmentByTag("mQYFragment");
            mZYFragment = (ZYFragment) getSupportFragmentManager().findFragmentByTag("mZYFragment");
            mGRFragment = (GRFragment) getSupportFragmentManager().findFragmentByTag("mGRFragment");
            mWDFragment = (WDFragment) getSupportFragmentManager().findFragmentByTag("mWDFragment");

            currentTabPosition = savedInstanceState.getInt(AppConstant.HOME_CURRENT_TAB_POSITION);
        } else {

            mZFFragment = new ZFFragment();
            mQYFragment = new QYFragment();
            mZYFragment = new ZYFragment();
            mGRFragment = new GRFragment();
            mWDFragment = new WDFragment();

            transaction.add(R.id.fl_body, mZFFragment, "mZFFragment");
            transaction.add(R.id.fl_body, mQYFragment, "mQYFragment");
            transaction.add(R.id.fl_body, mZYFragment, "mZYFragment");
            transaction.add(R.id.fl_body, mGRFragment, "mGRFragment");
            transaction.add(R.id.fl_body, mWDFragment, "mWDFragment");


        }
        transaction.commit();
        SwitchTo(currentTabPosition);

    }

    /**
     * 切换
     */
    public void SwitchTo(int position) {
        LogUtil.d("主页菜单position" + position);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        switch (position) {

            case 0:
                if (mQYFragment != null) {
                    transaction.hide(mQYFragment);
                }
                if (mZYFragment != null) {
                    transaction.hide(mZYFragment);
                }
                if (mGRFragment != null) {
                    transaction.hide(mGRFragment);
                }
                if (mWDFragment != null) {
                    transaction.hide(mWDFragment);
                }
                transaction.show(mZFFragment);
                transaction.commitAllowingStateLoss();
                break;

            case 1:
                if (mZFFragment != null) {
                    transaction.hide(mZFFragment);
                }
                if (mZYFragment != null) {
                    transaction.hide(mZYFragment);
                }
                if (mGRFragment != null) {
                    transaction.hide(mGRFragment);
                }
                if (mWDFragment != null) {
                    transaction.hide(mWDFragment);
                }
                transaction.show(mQYFragment);
                transaction.commitAllowingStateLoss();
                break;

            case 2:
                if (mZFFragment != null) {
                    transaction.hide(mZFFragment);
                }
                if (mQYFragment != null) {
                    transaction.hide(mQYFragment);
                }
                if (mGRFragment != null) {
                    transaction.hide(mGRFragment);
                }
                if (mWDFragment != null) {
                    transaction.hide(mWDFragment);
                }
                transaction.show(mZYFragment);
                transaction.commitAllowingStateLoss();
                break;
            case 3:
                if (mZFFragment != null) {
                    transaction.hide(mZFFragment);
                }
                if (mQYFragment != null) {
                    transaction.hide(mQYFragment);
                }
                if (mZYFragment != null) {
                    transaction.hide(mZYFragment);
                }
                if (mWDFragment != null) {
                    transaction.hide(mWDFragment);
                }
                transaction.show(mGRFragment);
                transaction.commitAllowingStateLoss();
                break;
            case 4:
                if (mZFFragment != null) {
                    transaction.hide(mZFFragment);
                }
                if (mQYFragment != null) {
                    transaction.hide(mQYFragment);
                }
                if (mZYFragment != null) {
                    transaction.hide(mZYFragment);
                }
                if (mGRFragment != null) {
                    transaction.hide(mGRFragment);
                }
                transaction.show(mWDFragment);
                transaction.commitAllowingStateLoss();
                break;
            default:
                break;
        }

    }


    /**
     * 监听返回键
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        LogUtil.e("---------onKeyDown----" + keyCode);
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - lastClickTime) > 2000) {
                Toast.makeText(this, getString(R.string.out_account), Toast.LENGTH_SHORT).show();
                lastClickTime = System.currentTimeMillis();
            } else {
               // QbApplication.mBaseApplication.removePushReceiver();
                finish();
            }
            return false;
        } else if (keyCode == KeyEvent.KEYCODE_HOME) {

        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    public void onClick(View v) {
        String token = ShareUtils.getString(BaseAppProfile.getApplication(), "token", "");
        if (TextUtils.isEmpty(token)) {
            LoginActivity.startAction(MainActivity.this);
            return;
        }
        setTransparency();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (v.getId()) {
            case R.id.all_one:
                currentTabPosition = 0;
                // SwitchTo(0);

                mIv_home_normal.setVisibility(View.GONE);
                mIv_home_press.setVisibility(View.VISIBLE);
                mTv_home_normal.setVisibility(View.GONE);
                mTv_home_press.setVisibility(View.VISIBLE);
                if (mQYFragment != null) {
                    transaction.hide(mQYFragment);
                }
                if (mZYFragment != null) {
                    transaction.hide(mZYFragment);
                }
                if (mGRFragment != null) {
                    transaction.hide(mGRFragment);
                }
                if (mWDFragment != null) {
                    transaction.hide(mWDFragment);
                }
                transaction.show(mZFFragment);
                transaction.commitAllowingStateLoss();
                break;

            case R.id.all_two:
                currentTabPosition = 1;
                //  SwitchTo(1);

                mIv_fishpond_normal.setVisibility(View.GONE);
                mIv_fishpond_press.setVisibility(View.VISIBLE);
                mTv_fishpond_normal.setVisibility(View.GONE);
                mTv_fishpond_press.setVisibility(View.VISIBLE);
                if (mZFFragment != null) {
                    transaction.hide(mZFFragment);
                }
                if (mZYFragment != null) {
                    transaction.hide(mZYFragment);
                }
                if (mGRFragment != null) {
                    transaction.hide(mGRFragment);
                }
                if (mWDFragment != null) {
                    transaction.hide(mWDFragment);
                }
                transaction.show(mQYFragment);
                transaction.commitAllowingStateLoss();
                break;

            case R.id.all_three:
                currentTabPosition = 3;
                // SwitchTo(3);

                mIv_message_normal.setVisibility(View.GONE);
                mIv_message_press.setVisibility(View.VISIBLE);
                mTv_message_normal.setVisibility(View.GONE);
                mTv_message_press.setVisibility(View.VISIBLE);
                if (mZFFragment != null) {
                    transaction.hide(mZFFragment);
                }
                if (mQYFragment != null) {
                    transaction.hide(mQYFragment);
                }
                if (mZYFragment != null) {
                    transaction.hide(mZYFragment);
                }
                if (mWDFragment != null) {
                    transaction.hide(mWDFragment);
                }
                transaction.show(mGRFragment);
                transaction.commitAllowingStateLoss();
                break;

            case R.id.all_four:
                currentTabPosition = 4;
                //  SwitchTo(4);

                mIv_mine_normal.setVisibility(View.GONE);
                mIv_mine_press.setVisibility(View.VISIBLE);
                mTv_mine_normal.setVisibility(View.GONE);
                mTv_mine_press.setVisibility(View.VISIBLE);
                if (mZFFragment != null) {
                    transaction.hide(mZFFragment);
                }
                if (mQYFragment != null) {
                    transaction.hide(mQYFragment);
                }
                if (mZYFragment != null) {
                    transaction.hide(mZYFragment);
                }
                if (mGRFragment != null) {
                    transaction.hide(mGRFragment);
                }
                transaction.show(mWDFragment);
                transaction.commitAllowingStateLoss();
                break;

            case R.id.tvNull:
                currentTabPosition = 2;
                // SwitchTo(2);
                tvNull.setBackgroundResource(R.drawable.login_ed_shape_p);
                tvtvNull.setVisibility(View.GONE);
                tvtvNull1.setVisibility(View.VISIBLE);

                if (mZFFragment != null) {
                    transaction.hide(mZFFragment);
                }
                if (mQYFragment != null) {
                    transaction.hide(mQYFragment);
                }
                if (mGRFragment != null) {
                    transaction.hide(mGRFragment);
                }
                if (mWDFragment != null) {
                    transaction.hide(mWDFragment);
                }
                transaction.show(mZYFragment);
                transaction.commitAllowingStateLoss();
                break;

        }
    }

    /**
     * 把press图片、文字全部隐藏(设置透明度)
     */
    private void setTransparency() {
        mIv_home_normal.setVisibility(View.VISIBLE);
        mIv_home_press.setVisibility(View.GONE);
        mTv_home_normal.setVisibility(View.VISIBLE);
        mTv_home_press.setVisibility(View.GONE);

        mIv_fishpond_normal.setVisibility(View.VISIBLE);
        mIv_fishpond_press.setVisibility(View.GONE);
        mTv_fishpond_normal.setVisibility(View.VISIBLE);
        mTv_fishpond_press.setVisibility(View.GONE);

        mIv_message_normal.setVisibility(View.VISIBLE);
        mIv_message_press.setVisibility(View.GONE);
        mTv_message_normal.setVisibility(View.VISIBLE);
        mTv_message_press.setVisibility(View.GONE);


        mIv_mine_normal.setVisibility(View.VISIBLE);
        mIv_mine_press.setVisibility(View.GONE);
        mTv_mine_normal.setVisibility(View.VISIBLE);
        mTv_mine_press.setVisibility(View.GONE);

        tvNull.setBackgroundResource(R.drawable.login_ed_shape);
        tvtvNull.setVisibility(View.VISIBLE);
        tvtvNull1.setVisibility(View.GONE);

    }


    private boolean isPermissionRequested;

    /**
     * Android6.0之后需要动态申请权限
     */
    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= 23 && !isPermissionRequested) {

            isPermissionRequested = true;

            ArrayList<String> permissionsList = new ArrayList<>();

            String[] permissions = {
                    Manifest.permission.ACCESS_NETWORK_STATE,
                    Manifest.permission.INTERNET,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.CAMERA,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.ACCESS_WIFI_STATE,
                    Manifest.permission.RECORD_AUDIO
            };

            for (String perm : permissions) {
                if (PackageManager.PERMISSION_GRANTED != checkSelfPermission(perm)) {
                    permissionsList.add(perm);
                    // 进入到这里代表没有权限.
                }
            }

            if (!permissionsList.isEmpty()) {
                requestPermissions(permissionsList.toArray(new String[permissionsList.size()]), 0);
            }
        }
    }


    //消息推送通知收到事件
    @Subscribe
    public void onEvent(LoginOut note) {
        if (note != null) {
            currentTabPosition = 2;

            //默认选中第一个
            setTransparency();
            tvNull.setBackgroundResource(R.drawable.login_ed_shape_p);
            tvtvNull.setVisibility(View.GONE);
            tvtvNull1.setVisibility(View.VISIBLE);

            SwitchTo(currentTabPosition);

        }
    }


    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void registrationidSuccess(String msg) {
        LogUtils.e("----registrationId--:",msg);
    }

    @Override
    public void registrationidFailed() {

    }
}
