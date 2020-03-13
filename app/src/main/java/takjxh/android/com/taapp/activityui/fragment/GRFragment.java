package takjxh.android.com.taapp.activityui.fragment;

import android.content.res.TypedArray;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yc.cn.ycbannerlib.banner.BannerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import takjxh.android.com.commlibrary.BaseAppProfile;
import takjxh.android.com.commlibrary.utils.ShareUtils;
import takjxh.android.com.commlibrary.view.fragment.BaseFragment;
import takjxh.android.com.taapp.R;
import takjxh.android.com.taapp.activityui.activity.JlztActivity;
import takjxh.android.com.taapp.activityui.activity.KjllxxActivity;
import takjxh.android.com.taapp.activityui.activity.LlksActivity;
import takjxh.android.com.taapp.activityui.activity.WdZxbbActivity;
import takjxh.android.com.taapp.activityui.activity.XsfdpxActivity;
import takjxh.android.com.taapp.activityui.activity.XsywdyActivity;
import takjxh.android.com.taapp.activityui.activity.XxzzActivity;
import takjxh.android.com.taapp.activityui.adapter.BaseBannerPagerAdapter;
import takjxh.android.com.taapp.activityui.bean.BannnersBean;
import takjxh.android.com.taapp.activityui.bean.LoginIn;
import takjxh.android.com.taapp.activityui.bean.LoginOut;
import takjxh.android.com.taapp.activityui.bean.ScoreRefsh;
import takjxh.android.com.taapp.activityui.presenter.GRPresenter;
import takjxh.android.com.taapp.activityui.presenter.impl.IGRPresenter;
import takjxh.android.com.taapp.utils.DisplayUtil;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-10-14 9:23
 * @Description:
 **/
public class GRFragment extends BaseFragment<GRPresenter> implements IGRPresenter.IView, View.OnClickListener {
    @BindView(R.id.banner)
    BannerView banner;
    @BindView(R.id.ml1)
    LinearLayout ml1;
    @BindView(R.id.ml2)
    LinearLayout ml2;
    @BindView(R.id.ml3)
    LinearLayout ml3;
    @BindView(R.id.ml4)
    LinearLayout ml4;
    @BindView(R.id.ml5)
    LinearLayout ml5;
    @BindView(R.id.ml6)
    LinearLayout ml6;
    @BindView(R.id.tv_right2)
    TextView tv_right2;

    @BindView(R.id.tv_right1)
    TextView tv_right1;


    private List<BannnersBean.BannersBean> mdata = new ArrayList<>();
    private BaseBannerPagerAdapter mBaseBannerPagerAdapter;

    /**
     * 返回布局文件
     */
    @Override
    protected int getContentViewId() {
        return R.layout.fragment_gr;
    }

    @Override
    protected GRPresenter createPresenter() {
        return new GRPresenter(this);
    }

    /**
     * 初始化控件
     */
    @Override
    protected void initView() {
        super.initView();
        //注册
        EventBus.getDefault().register(this);
        initBanner();
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        ml1.setOnClickListener(this);
        ml2.setOnClickListener(this);
        ml3.setOnClickListener(this);
        ml4.setOnClickListener(this);
        ml5.setOnClickListener(this);
        ml6.setOnClickListener(this);
        tv_right2.setOnClickListener(this);

    }

    /**
     * 初始化数据
     */
    @Override
    protected void initData() {
        super.initData();
        mPresenter.bannnerslist();
        String score = ShareUtils.getString(BaseAppProfile.getApplication(), "score", "");
        if(!TextUtils.isEmpty(score)){
            tv_right1.setText("积分 " + score + "分");
        }else{
            tv_right1.setText("积分 " + 0 + "分");
        }


    }

    /**
     * 初始化轮播图
     */
    private void initBanner() {
        if (banner != null) {
            mBaseBannerPagerAdapter = new BaseBannerPagerAdapter(getActivity(), mdata);
            banner.setHintGravity(1);
            banner.setAnimationDuration(1000);
            banner.setPlayDelay(2000);
            banner.setHintPadding(0, 0, 0, DisplayUtil.dip2px(10));
            banner.setAdapter(mBaseBannerPagerAdapter);
        }
    }

    public List<Object> getBannerData() {
        List<Object> lists = new ArrayList<>();
        TypedArray bannerImage = getActivity().getResources().obtainTypedArray(R.array.banner_image);
        for (int i = 0; i < 8; i++) {
            int image = bannerImage.getResourceId(i, R.drawable.bg_small_autumn_tree_min);
            lists.add(image);
        }
        bannerImage.recycle();
        return lists;
    }


    @Override
    public void onPause() {
        super.onPause();
        if (banner != null) {
            banner.pause();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (banner != null) {
            banner.resume();
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_right2:
                XxzzActivity.startAction(getActivity());
                break;
            case R.id.ml1:
                KjllxxActivity.startAction(getActivity());
                break;
            case R.id.ml2:
                LlksActivity.startAction(getActivity());
                break;
            case R.id.ml3:
                XsfdpxActivity.startAction(getActivity());
                break;
            case R.id.ml4:
                XsywdyActivity.startAction(getActivity());
                break;
            case R.id.ml5:
                JlztActivity.startAction(getActivity());
                break;
            case R.id.ml6:
                WdZxbbActivity.startAction(getActivity());
                break;
        }
    }


    @Override
    public void bannnerslistSuccess(List<BannnersBean.BannersBean> data) {
        if (data == null) {
            return;
        }
        mdata.clear();
        mdata.addAll(data);
        mBaseBannerPagerAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //取消注册 , 防止Activity内存泄漏
        EventBus.getDefault().unregister(this);
    }

    //消息推送通知收到事件
    @Subscribe
    public void onEvent(LoginOut note) {
        if (note != null) {
            String score = ShareUtils.getString(BaseAppProfile.getApplication(), "score", "");
            if(!TextUtils.isEmpty(score)){
                tv_right1.setText("积分 " + score + "分");
            }else{
                tv_right1.setText("积分 " + 0 + "分");
            }
        }
    }


    //消息推送通知收到事件
    @Subscribe
    public void onEvent(LoginIn note) {
        if (note != null) {
            String score = ShareUtils.getString(BaseAppProfile.getApplication(), "score", "");
            if(!TextUtils.isEmpty(score)){
                tv_right1.setText("积分 " + score + "分");
            }else{
                tv_right1.setText("积分 " + 0 + "分");
            }
        }
    }

    //消息推送通知收到事件
    @Subscribe
    public void onEvent(ScoreRefsh note) {
        if (note != null) {
            String score = ShareUtils.getString(BaseAppProfile.getApplication(), "score", "");
            if(!TextUtils.isEmpty(score)){
                tv_right1.setText("积分 " + score + "分");
            }else{
                tv_right1.setText("积分 " + 0 + "分");
            }
        }
    }

}