package takjxh.android.com.taapp.activityui.activity;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.util.List;

import butterknife.BindView;
import takjxh.android.com.commlibrary.utils.BarUtil;
import takjxh.android.com.commlibrary.view.activity.BaseActivity;
import takjxh.android.com.taapp.R;
import takjxh.android.com.taapp.activityui.MainActivity;
import takjxh.android.com.taapp.activityui.bean.SysParamBean;
import takjxh.android.com.taapp.activityui.presenter.StartUIPresenter;
import takjxh.android.com.taapp.activityui.presenter.impl.IStartUIPresenter;
import takjxh.android.com.taapp.view.CountDownViewT;
import takjxh.android.com.taapp.view.CustomVideoView;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-10-29 16:30
 * @Description:
 **/
public class SplashVideoActivity extends BaseActivity<StartUIPresenter> implements IStartUIPresenter.IView {
    @BindView(R.id.cdv_time)
    CountDownViewT cdvTime;
    @BindView(R.id.videoView)
    CustomVideoView vv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        //将window的背景图设置为空
        getWindow().setBackgroundDrawable(null);
        // 全屏显示
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
    }

    /**
     * 返回布局文件
     */
    @Override
    protected int getContentViewId() {
        return R.layout.activity_splash_video;
    }

    @Override
    protected StartUIPresenter createPresenter() {
        return new StartUIPresenter(this);
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
        /*主要代码起始位置*/

        final String uri = "android.resource://" + getPackageName() + "/" ;//+ R.raw.video2
        vv.setVideoURI(Uri.parse(uri));
        vv.start();
        vv.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {


            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
                mp.setLooping(false);
            }
        });


        vv.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {


            @Override
            public void onCompletion(MediaPlayer mp) {
                MainActivity.startAction(SplashVideoActivity.this);
                finish();
            }
        });
        /*主要代码结束位置*/

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
                MainActivity.startAction(SplashVideoActivity.this);
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
        mPresenter.startUI();
        mPresenter.paramlist();
    }

    private void initCountDownView() {
        cdvTime.setTime(6);
        cdvTime.start();
        cdvTime.setOnLoadingFinishListener(new CountDownViewT.OnLoadingFinishListener() {
            @Override
            public void finish() {
                toMainActivity();
            }
        });
    }

    private void toMainActivity() {
        MainActivity.startAction(SplashVideoActivity.this);
        finish();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (cdvTime != null && cdvTime.isShown()) {
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


    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void startSuccess(List<String> bean) {

    }

    @Override
    public void startFailed() {

    }

    @Override
    public void paramlistSuccess(SysParamBean bean) {

    }

    @Override
    public void paramlistFailed() {

    }
}
