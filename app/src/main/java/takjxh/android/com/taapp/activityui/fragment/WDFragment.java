package takjxh.android.com.taapp.activityui.fragment;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;
import takjxh.android.com.commlibrary.BaseAppProfile;
import takjxh.android.com.commlibrary.image.ImageWrapper;
import takjxh.android.com.commlibrary.net.HttpConfig;
import takjxh.android.com.commlibrary.presenter.impl.BasePresenter;
import takjxh.android.com.commlibrary.utils.ShareUtils;
import takjxh.android.com.commlibrary.view.fragment.BaseFragment;
import takjxh.android.com.taapp.R;
import takjxh.android.com.taapp.activityui.activity.ContributeListActivity;
import takjxh.android.com.taapp.activityui.activity.GrDetailActivity;
import takjxh.android.com.taapp.activityui.activity.JyfkActivity;
import takjxh.android.com.taapp.activityui.activity.SetingActivity;
import takjxh.android.com.taapp.activityui.activity.XxbbActivity;
import takjxh.android.com.taapp.activityui.activity.XxjfActivity;
import takjxh.android.com.taapp.activityui.activity.XxshActivity;
import takjxh.android.com.taapp.activityui.bean.EditHeadImgRef;
import takjxh.android.com.taapp.activityui.bean.LoginIn;
import takjxh.android.com.taapp.activityui.bean.LoginOut;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-10-14 9:23
 * @Description:
 **/
public class WDFragment extends BaseFragment  implements View.OnClickListener{


    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvcompany)
    TextView tvcompany;

    @BindView(R.id.iv_avatar)
    CircleImageView iv_avatar;
    @BindView(R.id.togrxx)
    LinearLayout togrxx;

    @BindView(R.id.xxjf)
    LinearLayout xxjf;
    @BindView(R.id.jyfk)
    LinearLayout jyfk;

    @BindView(R.id.xxbb)
    LinearLayout xxbb;

    @BindView(R.id.xtsz)
    LinearLayout xtsz;

    @BindView(R.id.jfsc)
    LinearLayout jfsc;

    @BindView(R.id.xxsh)
    LinearLayout xxsh;

    @BindView(R.id.imgRight)
    ImageView imgRight;


    @BindView(R.id.hytg)
    LinearLayout hytg;

    /**
     * 返回布局文件
     */
    @Override
    protected int getContentViewId() {
        return R.layout.fragment_wd;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initView() {
        super.initView();
        //注册
        EventBus.getDefault().register(this);
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        hytg.setOnClickListener(this);
        iv_avatar.setOnClickListener(this);
        togrxx.setOnClickListener(this);
        xxjf.setOnClickListener(this);
        jyfk.setOnClickListener(this);
        xxbb.setOnClickListener(this);
        xtsz.setOnClickListener(this);
        jfsc.setOnClickListener(this);
        xxsh.setOnClickListener(this);
        imgRight.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        super.initData();

        String avatar = ShareUtils.getString(BaseAppProfile.getApplication(), "avatar", "");
        if (!TextUtils.isEmpty(avatar)) {
            ImageWrapper.setCircleImage(iv_avatar, HttpConfig.HOST+avatar, R.mipmap.head_man_offline, ImageWrapper.CENTER_CROP);
        }else{
            ImageWrapper.setImage(iv_avatar, R.mipmap.head_man_offline, ImageWrapper.CENTER_CROP);
        }
        String userName = ShareUtils.getString(BaseAppProfile.getApplication(), "name", "");
        String company = ShareUtils.getString(BaseAppProfile.getApplication(), "company", "");

        tvName.setText(userName);
        tvcompany.setText(company);

        String type = ShareUtils.getString(BaseAppProfile.getApplication(), "type", "");
        if ("04".equals(type)||"系统管理员".equals(type)){
            //系统管理员
            xxsh.setVisibility(View.VISIBLE);
        }else {
            xxsh.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgRight:
                GrDetailActivity.startAction(getActivity());
                break;
            case R.id.iv_avatar:
                GrDetailActivity.startAction(getActivity());
                break;
            case R.id.togrxx:
                GrDetailActivity.startAction(getActivity());
                break;

            case R.id.xxjf:
                XxjfActivity.startAction(getActivity());
                break;
            case R.id.jyfk:
                JyfkActivity.startAction(getActivity());
                break;
            case R.id.xxbb:
                XxbbActivity.startAction(getActivity());
                break;
            case R.id.xtsz:
                SetingActivity.startAction(getActivity());
                break;
            case R.id.jfsc:

                break;
            case R.id.xxsh:
                XxshActivity.startAction(getActivity());
                break;
            case R.id.hytg:
                ContributeListActivity.startAction(getActivity());
                break;


        }
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
            String avatar = ShareUtils.getString(BaseAppProfile.getApplication(), "avatar", "");
            if (!TextUtils.isEmpty(avatar)) {
                ImageWrapper.setCircleImage(iv_avatar, HttpConfig.HOST+avatar, R.mipmap.head_man_offline, ImageWrapper.CENTER_CROP);
            }else{
                ImageWrapper.setImage(iv_avatar, R.mipmap.head_man_offline, ImageWrapper.CENTER_CROP);
            }
            String userName = ShareUtils.getString(BaseAppProfile.getApplication(), "name", "");
            String company = ShareUtils.getString(BaseAppProfile.getApplication(), "company", "");

            tvName.setText(userName);
            tvcompany.setText(company);
        }
    }

    //消息推送通知收到事件
    @Subscribe
    public void onEvent(LoginIn note) {
        if (note != null) {
            String avatar = ShareUtils.getString(BaseAppProfile.getApplication(), "avatar", "");
            if (!TextUtils.isEmpty(avatar)) {
                ImageWrapper.setCircleImage(iv_avatar, HttpConfig.HOST+avatar, R.mipmap.head_man_offline, ImageWrapper.CENTER_CROP);
            }else{
                ImageWrapper.setImage(iv_avatar, R.mipmap.head_man_offline, ImageWrapper.CENTER_CROP);
            }
            String userName = ShareUtils.getString(BaseAppProfile.getApplication(), "name", "");
            String company = ShareUtils.getString(BaseAppProfile.getApplication(), "company", "");

            tvName.setText(userName);
            tvcompany.setText(company);
            String type = ShareUtils.getString(BaseAppProfile.getApplication(), "type", "");
            if ("04".equals(type)||"系统管理员".equals(type)){
                //系统管理员
                xxsh.setVisibility(View.VISIBLE);
            }else {
                xxsh.setVisibility(View.GONE);
            }
        }
    }

    //消息推送通知收到事件
    @Subscribe
    public void onEvent(EditHeadImgRef note) {
        if (note != null) {
            String avatar = ShareUtils.getString(BaseAppProfile.getApplication(), "avatar", "");
            if (!TextUtils.isEmpty(avatar)) {
                ImageWrapper.setCircleImage(iv_avatar, HttpConfig.HOST+avatar, R.mipmap.head_man_offline, ImageWrapper.CENTER_CROP);
            }else{
                ImageWrapper.setImage(iv_avatar, R.mipmap.head_man_offline, ImageWrapper.CENTER_CROP);
            }

        }
    }

}
