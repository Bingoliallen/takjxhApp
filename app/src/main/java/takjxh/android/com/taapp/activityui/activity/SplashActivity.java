package takjxh.android.com.taapp.activityui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import butterknife.BindView;
import takjxh.android.com.commlibrary.presenter.impl.BasePresenter;
import takjxh.android.com.commlibrary.utils.BarUtil;
import takjxh.android.com.commlibrary.view.activity.BaseActivity;
import takjxh.android.com.taapp.R;
import takjxh.android.com.taapp.activityui.MainActivity;
import takjxh.android.com.taapp.view.CountDownViewT;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-10-15 12:36
 * @Description:
 **/
public class SplashActivity extends BaseActivity {
   /* @BindView(R.id.bt_next)
    Button mBtn_next;*/
    @BindView(R.id.cdv_time)
   CountDownViewT cdvTime;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        //将window的背景图设置为空
        getWindow().setBackgroundDrawable(null);
        super.onCreate(savedInstanceState);
    }

    /**
     * 返回布局文件
     */
    @Override
    protected int getContentViewId() {
        return R.layout.activity_splash;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void beforeSetContentView() {
        super.beforeSetContentView();
        BarUtil.fullScreen(this);
        BarUtil.hideActionBar(this);
    }


    /**
     * 初始化控件
     */
    @Override
    protected void initView() {
        super.initView();
    }

    /**
     * 设置事件
     */
    @Override
    protected void initEvent() {
        super.initEvent();
        cdvTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cdvTime.stop();
                MainActivity.startAction(SplashActivity.this);
                finish();
            }
        });
    }

    /**
     * 初始化数据
     */
    @Override
    protected void initData() {
        super.initData();
        initCountDownView();
    }

    private void initCountDownView() {
        cdvTime.setTime(5);
        cdvTime.start();
        cdvTime.setOnLoadingFinishListener(new CountDownViewT.OnLoadingFinishListener() {
            @Override
            public void finish() {
                toMainActivity();
            }
        });
    }

    private void toMainActivity() {
        MainActivity.startAction(SplashActivity.this);
        finish();
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(cdvTime!=null && cdvTime.isShown()){
            cdvTime.stop();
        }
    }


    /**
     * 屏蔽返回键
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                return true;
            default:
                break;
        }
        return super.onKeyDown(keyCode, event);
    }



}
