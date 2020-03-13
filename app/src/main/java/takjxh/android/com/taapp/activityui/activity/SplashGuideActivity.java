package takjxh.android.com.taapp.activityui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import cn.bingoogolapple.bgabanner.BGABanner;
import takjxh.android.com.commlibrary.image.ImageWrapper;
import takjxh.android.com.commlibrary.utils.BarUtil;
import takjxh.android.com.commlibrary.view.activity.BaseActivity;
import takjxh.android.com.taapp.QbApplication;
import takjxh.android.com.taapp.R;
import takjxh.android.com.taapp.activityui.MainActivity;
import takjxh.android.com.taapp.activityui.bean.SysParamBean;
import takjxh.android.com.taapp.activityui.presenter.StartUIPresenter;
import takjxh.android.com.taapp.activityui.presenter.impl.IStartUIPresenter;
import takjxh.android.com.taapp.view.CountDownViewT;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-12-12 14:28
 * @Description:
 **/
public class SplashGuideActivity extends BaseActivity <StartUIPresenter> implements IStartUIPresenter.IView{

    @BindView(R.id.banner_guide_content)
    BGABanner mBackgroundBanner;

    @BindView(R.id.bt_next)
    Button mBtn_next;
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
        return R.layout.activity_splash_guide;
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
    }

    /**
     * 设置事件
     */
    @Override
    protected void initEvent() {
        super.initEvent();

    }


    @Override
    protected void initData() {
        super.initData();

    }

    private void initCountDownView(int num) {
        if (cdvTime != null && cdvTime.isShown()) {
            cdvTime.stop();
        }
        cdvTime.setTime(num);
        cdvTime.start();
        cdvTime.setOnLoadingFinishListener(new CountDownViewT.OnLoadingFinishListener() {
            @Override
            public void finish() {
                toMainActivity();
            }
        });
        cdvTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cdvTime.stop();
                toMainActivity();
            }
        });
    }

    private void toMainActivity() {
        MainActivity.startAction(SplashGuideActivity.this);
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.startUI();
        mPresenter.paramlist();
        // 如果开发者的引导页主题是透明的，需要在界面可见时给背景 Banner 设置一个白色背景，避免滑动过程中两个 Banner 都设置透明度后能看到 Launcher
      //  mBackgroundBanner.setBackgroundResource(android.R.color.white);
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

        if(bean==null){
            QbApplication.mBaseApplication.medias.clear();
            QbApplication.mBaseApplication.medias.add("");
        }else if(bean.size()==0){
            QbApplication.mBaseApplication.medias.clear();
            QbApplication.mBaseApplication.medias.add("");
        }else{
            QbApplication.mBaseApplication.medias=bean;
        }


        mBackgroundBanner.setAdapter(new BGABanner.Adapter<ImageView, String>() {
            @Override
            public void fillBannerItem(BGABanner banner, ImageView itemView, String model, int position) {
                if(!TextUtils.isEmpty(model)){
                    Glide.with(SplashGuideActivity.this)
                            .load(model)
                            .placeholder(R.color.background_color)
                            .error(R.color.background_color)
                            .centerCrop()
                            .dontAnimate()
                            .into(itemView);
                }else{
                    ImageWrapper.setImage(itemView, R.mipmap.splash, ImageWrapper.FIT_CENTER);
                }

            }
        });

        mBackgroundBanner.setData(QbApplication.mBaseApplication.medias,null);

        mBackgroundBanner.setEnterSkipViewIdAndDelegate(0, 0, new BGABanner.GuideDelegate() {
            @Override
            public void onClickEnterOrSkip() {
                toMainActivity();
            }
        });
        initCountDownView(25);
    }

    @Override
    public void startFailed() {
        QbApplication.mBaseApplication.medias.clear();
        QbApplication.mBaseApplication.medias.add("");

        mBackgroundBanner.setAdapter(new BGABanner.Adapter<ImageView, String>() {
            @Override
            public void fillBannerItem(BGABanner banner, ImageView itemView, String model, int position) {
                if(!TextUtils.isEmpty(model)){
                    Glide.with(SplashGuideActivity.this)
                            .load(model)
                            .placeholder(R.color.background_color)
                            .error(R.color.background_color)
                            .centerCrop()
                            .dontAnimate()
                            .into(itemView);
                }else{
                    ImageWrapper.setImage(itemView, R.mipmap.splash, ImageWrapper.FIT_CENTER);
                }

            }
        });

        mBackgroundBanner.setData(QbApplication.mBaseApplication.medias,
                Arrays.asList("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""));

        mBackgroundBanner.setEnterSkipViewIdAndDelegate(0, 0, new BGABanner.GuideDelegate() {
            @Override
            public void onClickEnterOrSkip() {
                toMainActivity();
            }
        });
        initCountDownView(8);
    }

    @Override
    public void paramlistSuccess(SysParamBean bean) {
        if(bean==null){
            return;
        }
        if(bean.getParams()==null){
            return;
        }
        if(bean.getParams().getUsertrade()!=null){
            QbApplication.mBaseApplication.usertrade=bean.getParams().getUsertrade();
        }
        if(bean.getParams().getUsertype()!=null){
            QbApplication.mBaseApplication.usertype=bean.getParams().getUsertype();
        }
        if(bean.getParams().getApplyorderstatus()!=null){
            QbApplication.mBaseApplication.applyorderstatus=bean.getParams().getApplyorderstatus();
        }


        if(bean.getParams().getMessagesourcetype()!=null){
            QbApplication.mBaseApplication.messagesourcetype=bean.getParams().getMessagesourcetype();
        }
        if(bean.getParams().getLogchangetype()!=null){
            QbApplication.mBaseApplication.logchangetype=bean.getParams().getLogchangetype();
        }
        if(bean.getParams().getStudytype()!=null){
            QbApplication.mBaseApplication.studytype=bean.getParams().getStudytype();
        }
        if(bean.getParams().getAudittype()!=null){
            QbApplication.mBaseApplication.audittype=bean.getParams().getAudittype();
        }
        if(bean.getParams().getFaqtype()!=null){
            QbApplication.mBaseApplication.faqtype=bean.getParams().getFaqtype();
        }
        if(bean.getParams().getSurveystatus()!=null){
            QbApplication.mBaseApplication.surveystatus=bean.getParams().getSurveystatus();
        }
        if(bean.getParams().getTraintype()!=null){
            QbApplication.mBaseApplication.traintype=bean.getParams().getTraintype();
        }
        if(bean.getParams().getNewstype()!=null){
            QbApplication.mBaseApplication.newstype=bean.getParams().getNewstype();
        }
        if(bean.getParams().getApplystatus()!=null){
            QbApplication.mBaseApplication.applystatus=bean.getParams().getApplystatus();
        }
        if(bean.getParams().getCommtopictype()!=null){
            QbApplication.mBaseApplication.commtopictype=bean.getParams().getCommtopictype();
        }
        if(bean.getParams().getUserstatus()!=null){
            QbApplication.mBaseApplication.userstatus=bean.getParams().getUserstatus();
        }
        if(bean.getParams().getPolicycreteunit()!=null){
            QbApplication.mBaseApplication.policycreteunit=bean.getParams().getPolicycreteunit();
        }
        if(bean.getParams().getAuditstatus()!=null){
            QbApplication.mBaseApplication.auditstatus=bean.getParams().getAuditstatus();
        }

        if (bean.getParams().getUserincome() != null) {
            QbApplication.mBaseApplication.userincome = bean.getParams().getUserincome();

        }
        if (bean.getParams().getUserscale() != null) {
            QbApplication.mBaseApplication.userscale = bean.getParams().getUserscale();

        }
        if (bean.getParams().getUserstation() != null) {
            QbApplication.mBaseApplication.userstation = bean.getParams().getUserstation();

        }
        if (bean.getParams().getApplyordertype() != null) {
            QbApplication.mBaseApplication.applyordertype = bean.getParams().getApplyordertype();

        }


        if (bean.getParams().getExamtype() != null) {
            QbApplication.mBaseApplication.examtype = bean.getParams().getExamtype();

        }
        if (bean.getParams().getQuestiontype() != null) {
            QbApplication.mBaseApplication.questiontype = bean.getParams().getQuestiontype();

        }
        if (bean.getParams().getTitletype() != null) {
            QbApplication.mBaseApplication.titletype = bean.getParams().getTitletype();

        }
    }

    @Override
    public void paramlistFailed() {

    }
}
