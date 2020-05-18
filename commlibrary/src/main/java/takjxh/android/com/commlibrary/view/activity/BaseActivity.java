package takjxh.android.com.commlibrary.view.activity;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;


import butterknife.ButterKnife;
import takjxh.android.com.commlibrary.BaseApplication;
import takjxh.android.com.commlibrary.presenter.impl.BasePresenter;
import takjxh.android.com.commlibrary.utils.AppUtil;
import takjxh.android.com.commlibrary.utils.DensityUtil;
import takjxh.android.com.commlibrary.utils.PermissionUtil;
import takjxh.android.com.commlibrary.utils.SoftKeyboardUtil;
import takjxh.android.com.commlibrary.utils.ToastUtil;
import takjxh.android.com.commlibrary.utils.wrapper.IntentWrapper;
import takjxh.android.com.commlibrary.widget.TitleBarView;

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity {

  public TitleBarView titleBar;
  protected String TAG = this.getClass().getSimpleName();
  protected FragmentManager mFragmentManager;
  protected T mPresenter;
  protected View mContentView;
  protected PermissionUtil mPermissionUtil;
  public Bundle savedInstanceState;
  //protected static void launch(Activity activity, Intent intent) {
  //  // 取消动画
  //  //intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
  //  activity.startActivity(intent);
  //  //activity.overridePendingTransition(R.anim.common_activity_slide_from_right, R.anim.common_activity_slide_to_left);
  //}
  public static boolean isForeground = false;
  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.savedInstanceState=savedInstanceState;
    if ((getIntent().getFlags() & Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) != 0) {
      finish();
      return;
    }

    beforeSetContentView();
    setContentView(getContentViewId());
    ButterKnife.bind(this);

    if (getApplication() instanceof BaseApplication) {
      BaseApplication application = (BaseApplication) getApplication();
      application.addActivity(this);
    }
    mFragmentManager = getSupportFragmentManager();
    mPresenter = createPresenter();

    registerMessageReceiver();
    initDialog();
    initView();
    initEvent();
    initData();

  }

  @Override
  protected void onResume() {

    super.onResume();
    DensityUtil.setDensity(this);
    if (!AppUtil.isNetworkAvailable(this)) {
      // 提示是否前往设置网络链接
      mNetDialog.show();
      ToastUtil.showToast(this,"网络似乎有点不好");
    }
    //if (BuildConfig.IS_DEBUG && Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
    //  if (!Settings.canDrawOverlays(this)) {
    //    com.example.common.utils.init.T.showShort("测试模式需要打开悬浮窗。");
    //    Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
    //    intent.setData(Uri.parse("package:" + getPackageName()));
    //    startActivityForResult(intent, 0);
    //  }
    //}
    if (mPermissionUtil != null) {
      if (mPermissionUtil.needCheck) {
        mPermissionUtil.checkPermissions();
      }
    }
  }

  @Override
  protected void onPause() {

    super.onPause();
  }


  @Override
  protected void onDestroy() {
   // LocalBroadcastManager.getInstance(this).unregisterReceiver(mMessageReceiver);
    if(mMessageReceiver!=null){
      unregisterReceiver(mMessageReceiver);
    }

    if (getApplication() instanceof BaseApplication) {
      BaseApplication application = (BaseApplication) getApplication();
      application.removeActivity(this);
    }
    if (mPresenter != null) {
      mPresenter.onDestroy();
      mPresenter = null;
    }
    super.onDestroy();
  }

  protected void beforeSetContentView() {
    DensityUtil.setDensity(this);
  }

  /**
   * 返回布局文件
   */
  protected abstract int getContentViewId();

  protected abstract T createPresenter();

  /**
   * 初始化控件
   */
  protected void initView() {
    mContentView = findViewById(android.R.id.content);
   titleBar = findViewById(takjxh.android.com.commlibrary.R.id.title_bar);
    changeWidgetStyle();
  }

  /**
   * 设置事件
   */
  protected void initEvent() {
    if (mContentView != null) {
      mContentView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          SoftKeyboardUtil.hideSoftKeyboard(BaseActivity.this);
        }
      });
    }
  }

  /**
   * 初始化数据
   */
  protected void initData() {
  }

  /**
   * 更新控件样式
   */
  protected void changeWidgetStyle() {
  }

  @Override
  public void finish() {
    super.finish();
    //overridePendingTransition(R.anim.common_activity_slide_from_left, R.anim.common_activity_slide_to_right);
  }
  @Override
  public void onRequestPermissionsResult(int requestCode, String[] permissions,
      int[] paramArrayOfInt) {
    if (requestCode == PermissionUtil.PERMISSION_REQUEST_CODE) {
      if (mPermissionUtil != null) {
        if (!mPermissionUtil.verifyPermissions(paramArrayOfInt)) {
          mPermissionUtil.showDialog();
          mPermissionUtil.needCheck = false;
        }
      }
    }
  }

  // protected void showFinishDialog() {
  //     if (mFinishDialog != null && !mFinishDialog.isShowing())
  //         mFinishDialog.show();
  // }

  // @Override
  // public boolean onKeyDown(int keyCode, KeyEvent event) {
  //     if (keyCode == KeyEvent.KEYCODE_BACK) {//返回按钮事件
  //         finish();
  //     }
  //     return super.onKeyDown(keyCode, event);
  // }

  /**
   * 之前遇到一个情况，onBackPressed 无效
   */
  // @Override
  // public void onBackPressed() {
  //     finish();
  // }

  /**
   * 私有初始化对话框控件
   */
  private AlertDialog mNetDialog;
  private AlertDialog mMesDialog;
  //private AlertDialog mFinishDialog;
  private void initDialog() {
    mNetDialog = new AlertDialog.Builder(this)
        .setTitle("提示")
        .setMessage("无网络连接，是否前往设置。")
        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialog, int which) {
            IntentWrapper.startSystemSettings(BaseActivity.this);
          }
        })
        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialog, int which) {
            finish();
          }
        })
        .setCancelable(false)
        .create();
    mNetDialog.setCanceledOnTouchOutside(false);

    //    mFinishDialog = AlertDialogWrapper.create(this, "提示", "确认要退出程序吗？",
    //            new DialogInterface.OnClickListener() {
    //                @Override
    //                public void onClick(DialogInterface dialog, int which) {
    //                    finish();
    //                }
    //            }, null,
    //            true, false);
  }

  /**
   * 如果第一个打开不是 SplashActivity，那么使用这个可以主题去除白屏
   */
  // <style name="AppTheme.Launcher" parent="AppTheme">
  //     <!--关闭启动窗口-->
  //     <item name="android:windowDisablePreview">true</item>
  // </style>




  //for receive customer msg from jpush server
  private MessageReceiver mMessageReceiver;
  public static final String MESSAGE_RECEIVED_ACTION = "com.example.jpushdemo.MESSAGE_RECEIVED_ACTION";
  public static final String KEY_TITLE = "title";
  public static final String KEY_MESSAGE = "message";
  public static final String KEY_EXTRAS = "extras";

  public void registerMessageReceiver() {
    mMessageReceiver = new MessageReceiver();
    IntentFilter filter = new IntentFilter();
    filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
    filter.addAction(MESSAGE_RECEIVED_ACTION);
   // LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver, filter);
    registerReceiver(mMessageReceiver, filter);
  }

  public class MessageReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
      try {
        if (MESSAGE_RECEIVED_ACTION.equals(intent.getAction())) {
          String messge = intent.getStringExtra(KEY_MESSAGE);
          String extras = intent.getStringExtra(KEY_EXTRAS);
          StringBuilder showMsg = new StringBuilder();
          showMsg.append( messge + "\n");
          if (!isEmpty(extras)) {
            showMsg.append("附加信息 : " + extras + "\n");
          }
          setCostomMsg(showMsg.toString());
        }
      } catch (Exception e){
      }
    }
  }

  public static boolean isEmpty(String s) {
    if (null == s)
      return true;
    if (s.length() == 0)
      return true;
    if (s.trim().length() == 0)
      return true;
    return false;
  }

  private void setCostomMsg(String msg){
    Log.e("lbb","setCostomMsg:"+msg);
    mMesDialog = new AlertDialog.Builder(this)
            .setTitle("消息推送提醒")
            .setMessage(msg)
            .setPositiveButton("知道了", new DialogInterface.OnClickListener() {
              @Override
              public void onClick(DialogInterface dialog, int which) {

              }
            })
            /*.setNegativeButton("取消", new DialogInterface.OnClickListener() {
              @Override
              public void onClick(DialogInterface dialog, int which) {

              }
            })*/
            .setCancelable(true)
            .create();
    mMesDialog.setCanceledOnTouchOutside(true);
    mMesDialog.show();
  }


}