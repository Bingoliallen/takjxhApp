package takjxh.android.com.taapp.activityui.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import takjxh.android.com.commlibrary.BaseAppProfile;
import takjxh.android.com.commlibrary.utils.ShareUtils;
import takjxh.android.com.commlibrary.view.fragment.BaseFragment;
import takjxh.android.com.taapp.R;
import takjxh.android.com.taapp.activityui.activity.LoginActivity;
import takjxh.android.com.taapp.activityui.activity.TabManageActivity;
import takjxh.android.com.taapp.activityui.activity.XxzzActivity;
import takjxh.android.com.taapp.activityui.adapter.BasePagerAdapter;
import takjxh.android.com.taapp.activityui.bean.LoginIn;
import takjxh.android.com.taapp.activityui.bean.LoginOut;
import takjxh.android.com.taapp.activityui.bean.NewsRef;
import takjxh.android.com.taapp.activityui.bean.NewstypeBean;
import takjxh.android.com.taapp.activityui.bean.ScoreRefsh;
import takjxh.android.com.taapp.activityui.bean.TypesBean;
import takjxh.android.com.taapp.activityui.presenter.ZYPresenter;
import takjxh.android.com.taapp.activityui.presenter.impl.IZYPresenter;
import takjxh.android.com.taapp.view.MyViewPager;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-10-14 9:37
 * @Description:
 **/
public class ZYFragment extends BaseFragment<ZYPresenter> implements IZYPresenter.IView {

    private FrameLayout llTitleMenu;
    private TextView toolbarTitle;


    @BindView(R.id.toMore)
    ImageView toMore;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.vp_content)
    MyViewPager vpContent;
    @BindView(R.id.tv_right2)
    TextView tv_right2;
    private ArrayList<TypesBean> mList = new ArrayList<>();

    @BindView(R.id.tv_right1)
    TextView tv_right1;


    /**
     * 返回布局文件
     */
    @Override
    protected int getContentViewId() {
        return R.layout.fragment_zy;
    }

    @Override
    protected ZYPresenter createPresenter() {
        return new ZYPresenter(this);
    }

    /**
     * 初始化控件
     */
    @Override
    protected void initView() {
        super.initView();
        //注册
        EventBus.getDefault().register(this);
    }

    /**
     * 设置事件
     */
    @Override
    protected void initEvent() {
        super.initEvent();
        tv_right2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String  token = ShareUtils.getString(BaseAppProfile.getApplication(), "token", "");
                if(TextUtils.isEmpty(token)){
                    LoginActivity.startAction(getActivity());
                    return;
                }

                XxzzActivity.startAction(getActivity());
            }
        });
        toMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TabManageActivity.startAction(getActivity(), mList);
            }
        });
    }

    /**
     * 初始化数据
     */
    @Override
    protected void initData() {
        super.initData();

        mPresenter.newstypelist();
        String score = ShareUtils.getString(BaseAppProfile.getApplication(), "score", "");
        if(!TextUtils.isEmpty(score)){
            tv_right1.setText("积分 " + score + "分");
        }else{
            tv_right1.setText("积分 " + 0 + "分");
        }
    }

    @Override
    public void newstypelistSuccess(List<NewstypeBean.NewsTypesBean> data) {
        if (data == null) {
            return;
        }
        mList.clear();

        List<Fragment> fragmentList = new ArrayList<>();

        ArrayList<String> title = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            mList.add(new TypesBean(data.get(i).getCode(), data.get(i).getValue()));
            Fragment fragment = NewsFragment.newInstance(data.get(i).getCode());
            fragmentList.add(fragment);
            title.add(data.get(i).getValue());
        }
        FragmentManager manager = getFragmentManager();
        BasePagerAdapter adapter = new BasePagerAdapter(manager, fragmentList, title);
        vpContent.setAdapter(adapter);
        tabLayout.setupWithViewPager(vpContent);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        vpContent.setOffscreenPageLimit(data.size());
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                EventBus.getDefault().post(new NewsRef(mList.get(tab.getPosition()).getCode()));

            }


            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }


            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //取消注册 , 防止Activity内存泄漏
        EventBus.getDefault().unregister(this);
    }


    //消息推送通知收到事件
    @Subscribe
    public void onEvent(TypesBean note) {
        if (note != null) {
            for (int i = 0; i < mList.size(); i++) {
                if (note.getCode().equals(mList.get(i).getCode())
                        && note.getValue().equals(mList.get(i).getValue())) {
                    vpContent.setCurrentItem(i);
                    EventBus.getDefault().post(new NewsRef(mList.get(i).getCode()));
                    break;
                }
            }

        }
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
