package takjxh.android.com.taapp.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v7.widget.AppCompatCheckBox;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import takjxh.android.com.commlibrary.BaseAppProfile;
import takjxh.android.com.commlibrary.utils.BarUtil;
import takjxh.android.com.commlibrary.utils.PackageUtils;
import takjxh.android.com.commlibrary.utils.ShareUtils;
import takjxh.android.com.commlibrary.utils.ToastUtil;
import takjxh.android.com.commlibrary.view.activity.BaseActivity;
import takjxh.android.com.taapp.R;
import takjxh.android.com.taapp.bean.CenterBean;
import takjxh.android.com.taapp.bean.UserInfoBean;
import takjxh.android.com.taapp.dialog.SweetAlertDialog;
import takjxh.android.com.taapp.presenter.MinePresenter;
import takjxh.android.com.taapp.presenter.impl.IMinePresenter;
import takjxh.android.com.taapp.utils.CacheDataManager;
import takjxh.android.com.taapp.view.NormalTitleBar;

/**
 * 设置页
 *
 * @Author: libaibing
 * @Date: 2019-01-23 12:54
 * @Description:
 **/

public class SettingActivity extends BaseActivity<MinePresenter> implements IMinePresenter.IView, CompoundButton.OnCheckedChangeListener, View.OnClickListener {

    /**
     * 入口
     *
     * @param activity
     */
    public static void startAction(Activity activity) {
        Intent intent = new Intent(activity, SettingActivity.class);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }


    @BindView(R.id.ntb)
    NormalTitleBar ntb;

    @BindView(R.id.cb_setting_cache)
    AppCompatCheckBox cbSettingCache;
    @BindView(R.id.cb_setting_image)
    AppCompatCheckBox cbSettingImage;

    @BindView(R.id.tv_setting_clear)
    TextView tvSettingClear;
    @BindView(R.id.ll_setting_clear)
    LinearLayout llSettingClear;
    @BindView(R.id.tv_setting_update)
    TextView tvSettingUpdate;
    @BindView(R.id.ll_setting_update)
    LinearLayout llSettingUpdate;
    @BindView(R.id.tvVersionName)
    TextView tvVersionName;


    @BindView(R.id.btn_login)
    Button btn_login;

    private String versionName;


    @Override
    protected int getContentViewId() {
        return R.layout.activity_setting;
    }

    @Override
    protected MinePresenter createPresenter() {
        return new MinePresenter(this);
    }

    @Override
    protected void beforeSetContentView() {
        super.beforeSetContentView();
        BarUtil.hideActionBar(this);

    }


    @Override
    protected void initView() {
        super.initView();
        ntb = findViewById(R.id.ntb);
        ntb.setTitleText(getString(R.string.setting));
        ntb.setTvLeftVisiable(true);
        ntb.setOnBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    @Override
    protected void initEvent() {
        super.initEvent();
        llSettingClear.setOnClickListener(this);
        llSettingUpdate.setOnClickListener(this);
        cbSettingCache.setOnCheckedChangeListener(this);
        cbSettingImage.setOnCheckedChangeListener(this);

        btn_login.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        super.initData();
        tvVersionName.setText(getString(R.string.cur_version) + " v" + PackageUtils.getVersionName(this));
        setText();
        try {
            PackageManager pm = getPackageManager();
            PackageInfo pi = pm.getPackageInfo(getPackageName(), PackageManager.GET_ACTIVITIES);
            versionName = pi.versionName;
            tvSettingUpdate.setText(getString(R.string.cur_version_name) + String.format("：v%s", versionName));
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        boolean settingcache = ShareUtils.getBoolean(BaseAppProfile.getApplication(), "settingcache", false);
        boolean settingimage = ShareUtils.getBoolean(BaseAppProfile.getApplication(), "settingimage", false);
        cbSettingCache.setChecked(settingcache);
        cbSettingImage.setChecked(settingimage);
    }

    private void setText() {
        try {
            tvSettingClear.setText(CacheDataManager.getTotalCacheSize(this));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        switch (compoundButton.getId()) {
            case R.id.cb_setting_image:
                ShareUtils.putBoolean(BaseAppProfile.getApplication(), "settingimage", b);

                break;
            case R.id.cb_setting_cache:
                ShareUtils.putBoolean(BaseAppProfile.getApplication(), "settingcache", b);

                break;
            default:
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_setting_clear:
                CacheDataManager.clearAllCache(this);
                ToastUtil.showToast(this, getString(R.string.clear_success));
                setText();
                break;
            case R.id.ll_setting_update:

                break;


            case R.id.btn_login:
                new SweetAlertDialog.Builder(this)
                        .setTitle(getString(R.string.ts))
                        .setMessage(getString(R.string.out_msg))
                        .setCancelable(true)
                        .setPositiveButton(getString(R.string.ok), new SweetAlertDialog.OnDialogClickListener() {
                            @Override
                            public void onClick(Dialog dialog, int which) {
                                mPresenter.loginOut("");

                            }
                        }).show();

                break;

            default:
                break;
        }
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void getUserInfoSuccess(UserInfoBean data) {

    }

    @Override
    public void getCenterSuccess(CenterBean data) {

    }

    @Override
    public void loginOutSuccess() {
        ToastUtil.showToast(this, getString(R.string.out_success));

        ShareUtils.deleteShare(BaseAppProfile.getApplication(), "token");
        finish();
       // LoginActivity.clearLaunch(this);
    }

    @Override
    public void loginOutError() {

    }
}
