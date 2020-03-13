package takjxh.android.com.taapp.activityui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.facebook.stetho.common.LogUtil;

import java.util.ArrayList;
import java.util.List;

import takjxh.android.com.commlibrary.BaseAppProfile;
import takjxh.android.com.commlibrary.image.ImageWrapper;
import takjxh.android.com.commlibrary.utils.BarUtil;
import takjxh.android.com.commlibrary.utils.ShareUtils;
import takjxh.android.com.commlibrary.view.activity.BaseActivity;
import takjxh.android.com.taapp.QbApplication;
import takjxh.android.com.taapp.R;
import takjxh.android.com.taapp.activityui.bean.SysParamBean;
import takjxh.android.com.taapp.activityui.presenter.StartUIPresenter;
import takjxh.android.com.taapp.activityui.presenter.impl.IStartUIPresenter;
import takjxh.android.com.taapp.guide.DepthPageTransformer;
import takjxh.android.com.taapp.guide.ViewPagerAdatper;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-10-14 8:53
 * @Description:
 **/
public class GuideActivity extends BaseActivity<StartUIPresenter> implements IStartUIPresenter.IView  {


    private ViewPager mIn_vp;
    private LinearLayout mIn_ll;
    private List<View> mViewList;
    private ImageView mLight_dots;
    private int mDistance;
    /* private ImageView mOne_dot;
     private ImageView mTwo_dot;
     private ImageView mThree_dot;*/
    private Button mBtn_next;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        //将window的背景图设置为空
        getWindow().setBackgroundDrawable(null);
        super.onCreate(savedInstanceState);
    }


    @Override
    protected int getContentViewId() {
        return R.layout.activity_guide;
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


    @Override
    protected void initView() {
        super.initView();
        initView1();

    }

    @Override
    protected void initData() {
        super.initData();
        //ShareUtils.putString(BaseAppProfile.getApplication(), "token", "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOiJlMTFjODJkNmY3MDcxMWU5YjM5ZmIyZTY2OTVjOTFjMyJ9.v-tzK7cDgQD0n-aB-r0XN_7mO0Fy0plFluawi3TC7MM");
        mPresenter.startUI();
        mPresenter.paramlist();
    }

    @Override
    protected void initEvent() {
        super.initEvent();
    }

    private void moveDots() {
        mLight_dots.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                //获得两个圆点之间的距离
                if(QbApplication.mBaseApplication.medias.size()>1){
                    mDistance = mIn_ll.getChildAt(1).getLeft() - mIn_ll.getChildAt(0).getLeft();
                }

                mLight_dots.getViewTreeObserver()
                        .removeGlobalOnLayoutListener(this);
            }
        });
        mIn_vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //页面滚动时小白点移动的距离，并通过setLayoutParams(params)不断更新其位置
                float leftMargin = mDistance * (position + positionOffset);
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mLight_dots.getLayoutParams();
                params.leftMargin = (int) leftMargin;
                mLight_dots.setLayoutParams(params);
                if (position == QbApplication.mBaseApplication.medias.size() - 1) {
                    mBtn_next.setVisibility(View.VISIBLE);
                }
                if (position != QbApplication.mBaseApplication.medias.size() - 1 && mBtn_next.getVisibility() == View.VISIBLE) {
                    mBtn_next.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageSelected(int position) {
                //页面跳转时，设置小圆点的margin
                float leftMargin = mDistance * position;
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mLight_dots.getLayoutParams();
                params.leftMargin = (int) leftMargin;
                mLight_dots.setLayoutParams(params);
                if (position == QbApplication.mBaseApplication.medias.size() - 1) {
                    mBtn_next.setVisibility(View.VISIBLE);
                }
                if (position != QbApplication.mBaseApplication.medias.size() - 1 && mBtn_next.getVisibility() == View.VISIBLE) {
                    mBtn_next.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /* 随后在LinearLayout中动态添加3个小黑点，小白点默认覆盖在第一个小黑点的上面。
     在Activity中的添加小黑点，代码如下：*/
    private void addDots() {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, 0, 40, 0);


        for (int i = 0; i < QbApplication.mBaseApplication.medias.size(); i++) {
            ImageView mOne_dot = new ImageView(this);

            mIn_ll.addView(mOne_dot, layoutParams);
            mOne_dot.setImageResource(R.drawable.light_dot);

            setClickListener(mOne_dot, i);
        }
    /* ImageView  mOne_dot = new ImageView(this);
        mOne_dot.setImageResource(R.drawable.gray_dot);




        mTwo_dot = new ImageView(this);
        mTwo_dot.setImageResource(R.drawable.gray_dot);
        mIn_ll.addView(mTwo_dot, layoutParams);

        mThree_dot = new ImageView(this);
        mThree_dot.setImageResource(R.drawable.gray_dot);
        mIn_ll.addView(mThree_dot, layoutParams);*/

        mBtn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.startAction(GuideActivity.this);
                finish();
            }
        });
    }

    private void setClickListener(ImageView mOne_dot, final int i) {
        mOne_dot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mIn_vp.setCurrentItem(i);
            }
        });
       /* mTwo_dot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mIn_vp.setCurrentItem(1);
            }
        });
        mThree_dot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mIn_vp.setCurrentItem(2);
            }
        });
*/


    }


    private void initData1() {
        mViewList = new ArrayList<View>();
        LayoutInflater lf = LayoutInflater.from(GuideActivity.this);
        for (int i = 0; i < QbApplication.mBaseApplication.medias.size(); i++) {
            View view1 = lf.inflate(R.layout.fragment_guide, null);
            ImageView ivImgPg = view1.findViewById(R.id.ivImgPg);
            ivImgPg.setScaleType(ImageView.ScaleType.FIT_XY);
            if(TextUtils.isEmpty(QbApplication.mBaseApplication.medias.get(i).toString())){
                ImageWrapper.setImage(ivImgPg, R.mipmap.splash, ImageWrapper.FIT_CENTER);
            }else{
                ImageWrapper.setImage(ivImgPg,QbApplication.mBaseApplication.medias.get(i).toString() , R.color.white, ImageWrapper.FIT_CENTER);
            }

            mViewList.add(view1);
        }

       /* View view2 = lf.inflate(R.layout.fragment_guide, null);
        View view3 = lf.inflate(R.layout.fragment_guide, null);

        mViewList.add(view2);
        mViewList.add(view3);*/
    }

    private void initView1() {
        mIn_vp = (ViewPager) findViewById(R.id.in_viewpager);
        mIn_ll = (LinearLayout) findViewById(R.id.in_ll);
        mLight_dots = (ImageView) findViewById(R.id.iv_light_dots);
        mBtn_next = (Button) findViewById(R.id.bt_next);
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

            return false;
        } else if (keyCode == KeyEvent.KEYCODE_HOME) {

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
            QbApplication.mBaseApplication.medias.add("");
        }else if(bean.size()==0){
            QbApplication.mBaseApplication.medias.add("");
        }else{
            QbApplication.mBaseApplication.medias=bean;
        }

        initData1();
        mIn_vp.setAdapter(new ViewPagerAdatper(mViewList));
        addDots();
        moveDots();
        mIn_vp.setPageTransformer(true, new DepthPageTransformer());
        if (QbApplication.mBaseApplication.medias.size() <=  1) {
            mBtn_next.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void startFailed() {
        QbApplication.mBaseApplication.medias.add("");
        initData1();
        mIn_vp.setAdapter(new ViewPagerAdatper(mViewList));
        addDots();
        moveDots();
        mIn_vp.setPageTransformer(true, new DepthPageTransformer());
        if (QbApplication.mBaseApplication.medias.size() <=  1) {
            mBtn_next.setVisibility(View.VISIBLE);
        }
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