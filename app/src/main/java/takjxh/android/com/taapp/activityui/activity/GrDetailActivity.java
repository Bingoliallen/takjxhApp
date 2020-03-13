package takjxh.android.com.taapp.activityui.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.internal.entity.CaptureStrategy;
import com.zhihu.matisse.listener.OnCheckedListener;
import com.zhihu.matisse.listener.OnSelectedListener;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.model.UserInfo;
import cn.jpush.im.api.BasicCallback;
import de.hdodenhof.circleimageview.CircleImageView;
import takjxh.android.com.commlibrary.BaseAppProfile;
import takjxh.android.com.commlibrary.image.ImageWrapper;
import takjxh.android.com.commlibrary.net.HttpConfig;
import takjxh.android.com.commlibrary.utils.BarUtil;
import takjxh.android.com.commlibrary.utils.ShareUtils;
import takjxh.android.com.commlibrary.utils.ThreadUtil;
import takjxh.android.com.commlibrary.utils.ToastUtil;
import takjxh.android.com.commlibrary.view.activity.BaseActivity;
import takjxh.android.com.taapp.QbApplication;
import takjxh.android.com.taapp.R;
import takjxh.android.com.taapp.activityui.base.BaseDialog;
import takjxh.android.com.taapp.activityui.bean.EditHeadImgRef;
import takjxh.android.com.taapp.activityui.bean.LoginOut;
import takjxh.android.com.taapp.activityui.bean.SysParamBean;
import takjxh.android.com.taapp.activityui.bean.UploadFileBean;
import takjxh.android.com.taapp.activityui.dialog.MenuIosDialog;
import takjxh.android.com.taapp.activityui.dialog.MessageDialog;
import takjxh.android.com.taapp.activityui.presenter.GrDetailPresenter;
import takjxh.android.com.taapp.activityui.presenter.impl.IGrDetailPresenter;
import takjxh.android.com.taapp.view.NormalTitleBar;

/**
 * 类名称：个人信息中心
 *
 * @Author: libaibing
 * @Date: 2019-10-25 9:19
 * @Description:
 **/
public class GrDetailActivity extends BaseActivity<GrDetailPresenter> implements IGrDetailPresenter.IView, View.OnClickListener {
    //定义请求码常量
    private static final int REQUEST_CODE_CHOOSE = 23;
    private static final int REQUEST_CODE_Name = 24;
    private static final int REQUEST_CODE_Company = 25;


    public static void startAction(Activity activity) {
        Intent intent = new Intent(activity, GrDetailActivity.class);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @BindView(R.id.ntb)
    NormalTitleBar ntb;
    @BindView(R.id.iv_avatar)
    CircleImageView iv_avatar;


    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvphone)
    TextView tvphone;
    @BindView(R.id.tvtype)
    TextView tvtype;
    @BindView(R.id.tvgsmc)
    TextView tvgsmc;
    @BindView(R.id.tvgszw)
    TextView tvgszw;
    String mtvgszw;

    @BindView(R.id.yhm)
    LinearLayout yhm;
    @BindView(R.id.gsmc)
    LinearLayout gsmc;

    @BindView(R.id.gszw)
    LinearLayout gszw;

    @BindView(R.id.qt)
    LinearLayout qt;
    @BindView(R.id.mlqt)
    LinearLayout mlqt;


    @BindView(R.id.btn_login1)
    Button btn_login1;
    @BindView(R.id.btn_login)
    Button btn_login;

    private List<SysParamBean.ParamsBean.UserstationBean> userstation=new ArrayList<>();
    private  boolean isChange=false;

    /**
     * 返回布局文件
     */
    @Override
    protected int getContentViewId() {
        return R.layout.activity_gr_detail;
    }

    @Override
    protected GrDetailPresenter createPresenter() {
        return new GrDetailPresenter(this);
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
        ntb.setTitleText("个人资料");
        ntb.setTvLeftVisiable(true);

        ntb.setRightTitle("保存");
        ntb.setRightTitleVisibility(true);
        ntb.setOnRightTextListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                updateinfo();
            }
        });
        ntb.setOnBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isChange){
                    new MessageDialog.Builder(GrDetailActivity.this)
                            // 标题可以不用填写
                            .setTitle("温馨提示")
                            // 内容必须要填写
                            .setMessage("确定不保存修改的用户信息吗？")
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
                                    finish();
                                }

                                @Override
                                public void onCancel(BaseDialog dialog) {
                                    // toast("取消了");
                                }
                            })
                            .show();
                }else{
                    finish();
                }

            }
        });

    }

    /**
     * 设置事件
     */
    @Override
    protected void initEvent() {
        super.initEvent();
        iv_avatar.setOnClickListener(this);
        btn_login.setOnClickListener(this);
        btn_login1.setOnClickListener(this);

        yhm.setOnClickListener(this);
        gsmc.setOnClickListener(this);

        qt.setOnClickListener(this);

        gszw.setOnClickListener(this);
    }

    /**
     * 初始化数据
     */
    @Override
    protected void initData() {
        super.initData();

        String avatar = ShareUtils.getString(BaseAppProfile.getApplication(), "avatar", "");
        if (!TextUtils.isEmpty(avatar)) {
            filePath=avatar;
            ImageWrapper.setCircleImage(iv_avatar, HttpConfig.HOST + avatar, R.mipmap.head_man_offline, ImageWrapper.CENTER_CROP);
        }
        String userName = ShareUtils.getString(BaseAppProfile.getApplication(), "name", "");
        String mobilePhone = ShareUtils.getString(BaseAppProfile.getApplication(), "mobilePhone", "");
        String type = ShareUtils.getString(BaseAppProfile.getApplication(), "type", "");
        String company = ShareUtils.getString(BaseAppProfile.getApplication(), "company", "");
        tvName.setText(userName);
        tvphone.setText("+86 " + mobilePhone);
        mlqt.setVisibility(View.GONE);
        if ("01".equals(type)||"政府会员".equals(type)) {
            tvtype.setText("政府会员");
            mlqt.setVisibility(View.VISIBLE);
        } else if ("02".equals(type)||"企业会员".equals(type)) {
            tvtype.setText("企业会员");
            mlqt.setVisibility(View.VISIBLE);
        } else if ("03".equals(type)||"第三方服务机构".equals(type)) {
            tvtype.setText("第三方服务机构");
            mlqt.setVisibility(View.VISIBLE);
        } else if ("04".equals(type)||"系统管理员".equals(type)) {
            tvtype.setText("系统管理员");
        } else if ("05".equals(type)||"00".equals(type)||"普通会员".equals(type)) {
            tvtype.setText("普通会员");
        } else {
            tvtype.setText(type);
        }

        tvgsmc.setText(company);

        if (QbApplication.mBaseApplication.userstation != null && QbApplication.mBaseApplication.userstation.size() > 0) {
            userstation.clear();
            userstation.addAll(QbApplication.mBaseApplication.userstation);
        }

        String station = ShareUtils.getString(BaseAppProfile.getApplication(), "station", "");
        tvgszw.setText(station);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.qt:
                UserExtsListActivity.startAction(this);
                break;
            case R.id.gszw:
                List<String> data1 = new ArrayList<>();
                for (int i = 0; i < userstation.size(); i++) {
                    data1.add(userstation.get(i).getValue());
                }
                new MenuIosDialog.Builder(GrDetailActivity.this)
                        .setTitle("选择企业职位")
                        // 确定按钮文本
                        .setConfirm("确定")
                        // 设置 null 表示不显示取消按钮
                        .setCancel(null)
                        .setData(data1)
                        .setYear(0)
                        .setListener(new MenuIosDialog.OnListener() {
                            @Override
                            public void onSelected(BaseDialog dialog, int pos, String msg) {
                                tvgszw.setText(msg);
                                mtvgszw=userstation.get(pos).getCode();
                                isChange=true;

                            }

                            @Override
                            public void onCancel(BaseDialog dialog) {
                                // toast("取消了");
                            }
                        })
                        .show();
                break;
            case R.id.yhm:
                GrDetailInputActivity.startAction(this, tvName.getText().toString().trim(),"用户名", REQUEST_CODE_Name);
                break;
            case R.id.gsmc:
                GrDetailInputActivity.startAction(this,  tvgsmc.getText().toString().trim(),"公司名称", REQUEST_CODE_Company);
                break;
            case R.id.iv_avatar:
                setPhoto();
                break;
            case R.id.btn_login1:
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
                                loginOut1();
                            }

                            @Override
                            public void onCancel(BaseDialog dialog) {
                                // toast("取消了");
                            }
                        })
                        .show();
                break;
            case R.id.btn_login:
                new MessageDialog.Builder(this)
                        // 标题可以不用填写
                        .setTitle("注销")
                        // 内容必须要填写
                        .setMessage("是否注销当前用户的帐号？")
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
                                setCancel();
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

    private void updateinfo(){

        Map<String, Object> searchRequest=new HashMap<>();
        searchRequest.put("name",tvName.getText().toString().trim());
        searchRequest.put("company",tvgsmc.getText().toString().trim());
        searchRequest.put("cover",filePath);
        searchRequest.put("station",mtvgszw);
        mPresenter.updateinfo("", searchRequest);
    }



    private void setCancel() {
        mPresenter.usercancel("");
    }


    public void loginOut1() {
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


    public void loginOut() {
        UserInfo info = JMessageClient.getMyInfo();
        if (null != info) {
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
        QbApplication.mBaseApplication.userExts = new ArrayList<>();
        EventBus.getDefault().post(new LoginOut());
        finish();
        LoginActivity.startAction(this);
    }


    private void setPhoto() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            //申请WRITE_EXTERNAL_STORAGE权限
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    345);
        } else {
            Matisse.from(GrDetailActivity.this)
                    .choose(MimeType.ofImage(), false)
                    .countable(true)
                    .capture(true)
                    .captureStrategy(new CaptureStrategy(true, "com.zhihu.matisse.sample.fileprovider", "test"))
                    .maxSelectable(1)
                    //    .addFilter(new GifSizeFilter(320, 320, 5 * Filter.K * Filter.K))
                    .gridExpectedSize(getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                    .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                    .thumbnailScale(0.85f)
                    .theme(R.style.Matisse_Zhihus)//主题
//                                            .imageEngine(new GlideEngine())  // for glide-V3
                    //            .imageEngine(new Glide4Engine())    // for glide-V4
                    .setOnSelectedListener(new OnSelectedListener() {
                        @Override
                        public void onSelected(@NonNull List<Uri> uriList, @NonNull List<String> pathList) {
                            // DO SOMETHING IMMEDIATELY HERE
                            Log.e("onSelected", "onSelected: pathList=" + pathList);

                        }
                    })
                    .originalEnable(true)
                    .maxOriginalSize(50)
                    .autoHideToolbarOnSingleTap(true)
                    .setOnCheckedListener(new OnCheckedListener() {
                        @Override
                        public void onCheck(boolean isChecked) {
                            // DO SOMETHING IMMEDIATELY HERE
                            Log.e("isChecked", "onCheck: isChecked=" + isChecked);
                        }
                    })
                    .forResult(REQUEST_CODE_CHOOSE);
        }


    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        doNext(requestCode, grantResults);
    }

    private void doNext(int requestCode, int[] grantResults) {
        if (requestCode == 345) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Matisse.from(GrDetailActivity.this)
                        .choose(MimeType.ofImage(), false)
                        .countable(true)
                        .capture(true)
                        .captureStrategy(new CaptureStrategy(true, "com.zhihu.matisse.sample.fileprovider", "test"))
                        .maxSelectable(1)
                        //    .addFilter(new GifSizeFilter(320, 320, 5 * Filter.K * Filter.K))
                        .gridExpectedSize(getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                        .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                        .thumbnailScale(0.85f)
                        .theme(R.style.Matisse_Zhihu)//主题
//                                            .imageEngine(new GlideEngine())  // for glide-V3
                        //            .imageEngine(new Glide4Engine())    // for glide-V4
                        .setOnSelectedListener(new OnSelectedListener() {
                            @Override
                            public void onSelected(@NonNull List<Uri> uriList, @NonNull List<String> pathList) {
                                // DO SOMETHING IMMEDIATELY HERE
                                Log.e("onSelected", "onSelected: pathList=" + pathList);

                            }
                        })
                        .originalEnable(true)
                        .maxOriginalSize(50)
                        .autoHideToolbarOnSingleTap(true)
                        .setOnCheckedListener(new OnCheckedListener() {
                            @Override
                            public void onCheck(boolean isChecked) {
                                // DO SOMETHING IMMEDIATELY HERE
                                Log.e("isChecked", "onCheck: isChecked=" + isChecked);
                            }
                        })
                        .forResult(REQUEST_CODE_CHOOSE);
            } else {
                // Permission Denied
            }
        }
    }

    List<String> mList = new ArrayList<>();

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            //  mAdapter.setData(Matisse.obtainResult(data), Matisse.obtainPathResult(data));
            Log.e("OnActivityResult ", String.valueOf(Matisse.obtainOriginalState(data)));
            List<Uri> mUri = Matisse.obtainResult(data);

            mList = Matisse.obtainPathResult(data);
            if (mList != null && mList.size() > 0) {
                mPresenter.editHeadImg("", mList.get(0));
            } else {
                ToastUtil.showToast(this, getString(R.string.select_msg_f));
            }
        } else if (requestCode == REQUEST_CODE_Name && resultCode == RESULT_OK) {
            if (data != null) {
                String input = data.getStringExtra("input");
                tvName.setText(input);
                isChange=true;
            }

        } else if (requestCode == REQUEST_CODE_Company && resultCode == RESULT_OK) {
            if (data != null) {
                String input = data.getStringExtra("input");
                tvgsmc.setText(input);
                isChange=true;
            }
        }
    }


    @Override
    public Context getContext() {
        return this;
    }
    private String filePath;
    @Override
    public void editHeadImgSuccess(UploadFileBean data) {
        if (data == null) {
            return;
        }

       /* if (mList != null && mList.size() > 0) {
            Log.e("TAG", "-----editHeadImgSuccess-------：" + mList.get(0));
            ShareUtils.putString(BaseAppProfile.getApplication(), "jchat_cached_avatar_path", mList.get(0));
            updateMyInfo();
        }*/

        if (!TextUtils.isEmpty(data.getFilePath())) {
            isChange=true;
            filePath=data.getFilePath();
            String mDA = data.getFilePath().replace(HttpConfig.HOST, "");
            ImageWrapper.setImage(iv_avatar, HttpConfig.HOST + mDA, R.mipmap.head_man_offline, ImageWrapper.CENTER_CROP);
           /* ShareUtils.putString(this, "avatar", mDA);
            EventBus.getDefault().post(new EditHeadImgRef());*/
        }

    }

    @Override
    public void editHeadImgError() {
        ToastUtil.showToast(this, getString(R.string.up_tx_f));
    }

    @Override
    public void usercancelSuccess(String data) {
        ToastUtil.showToast(this, data);
        loginOut();
    }

    @Override
    public void usercancelError() {

    }

    @Override
    public void updateinfoSuccess(String data) {


        ShareUtils.putString(BaseAppProfile.getApplication(), "station", tvgszw.getText().toString().trim());
        ShareUtils.putString(BaseAppProfile.getApplication(), "name", tvName.getText().toString().trim());
        ShareUtils.putString(BaseAppProfile.getApplication(), "company", tvgsmc.getText().toString().trim());

        if (mList != null && mList.size() > 0) {
            Log.e("TAG", "-----editHeadImgSuccess-------：" + mList.get(0));
            ShareUtils.putString(BaseAppProfile.getApplication(), "jchat_cached_avatar_path", mList.get(0));
            updateMyInfo();
        }

        if (!TextUtils.isEmpty(filePath)) {
            String mDA = filePath.replace(HttpConfig.HOST, "");
           /* ImageWrapper.setImage(iv_avatar, HttpConfig.HOST + mDA, R.mipmap.head_man_offline, ImageWrapper.CENTER_CROP);*/
            ShareUtils.putString(this, "avatar", mDA);
            EventBus.getDefault().post(new EditHeadImgRef());
        }

        ToastUtil.showToast(this, data);
        finish();
    }

    @Override
    public void updateinfoError() {

    }

    private void updateMyInfo() {

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


    /**
     * 监听返回键
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if(isChange){
                new MessageDialog.Builder(this)
                        // 标题可以不用填写
                        .setTitle("温馨提示")
                        // 内容必须要填写
                        .setMessage("确定不保存修改的用户信息吗？")
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
                                finish();
                            }

                            @Override
                            public void onCancel(BaseDialog dialog) {
                                // toast("取消了");
                            }
                        })
                        .show();
            }else{
                finish();
            }
            return false;
        } else if (keyCode == KeyEvent.KEYCODE_HOME) {

        }
        return super.onKeyDown(keyCode, event);
    }


}
