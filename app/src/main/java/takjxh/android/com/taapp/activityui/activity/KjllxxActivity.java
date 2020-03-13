package takjxh.android.com.taapp.activityui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import takjxh.android.com.commlibrary.utils.BarUtil;
import takjxh.android.com.commlibrary.view.activity.BaseActivity;
import takjxh.android.com.taapp.R;
import takjxh.android.com.taapp.activityui.adapter.BasePagerAdapter;
import takjxh.android.com.taapp.activityui.bean.KjllxxRef;
import takjxh.android.com.taapp.activityui.bean.StudyTypeBean;
import takjxh.android.com.taapp.activityui.bean.StudysBean;
import takjxh.android.com.taapp.activityui.bean.TypesBean;
import takjxh.android.com.taapp.activityui.fragment.KjllxxFragment;
import takjxh.android.com.taapp.activityui.presenter.KjllxxPresenter;
import takjxh.android.com.taapp.activityui.presenter.impl.IKjllxxPresenter;
import takjxh.android.com.taapp.view.NormalTitleBar;

/**
 * 类名称：会计理论学习
 *
 * @Author: libaibing
 * @Date: 2019-10-16 16:49
 * @Description:
 **/
public class KjllxxActivity extends BaseActivity<KjllxxPresenter> implements IKjllxxPresenter.IView, View.OnClickListener {

    public static void startAction(Activity activity) {
        Intent intent = new Intent(activity, KjllxxActivity.class);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
    @BindView(R.id.ntb)
    NormalTitleBar ntb;
    @BindView(R.id.toMore)
    ImageView toMore;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.vp_content)
    ViewPager vpContent;
    private ArrayList<TypesBean> mList = new ArrayList<>();

    /**
     * 返回布局文件
     */
    @Override
    protected int getContentViewId() {
        return R.layout.activity_kjllxx;
    }

    @Override
    protected KjllxxPresenter createPresenter() {
        return new KjllxxPresenter(this);
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
        //注册
        EventBus.getDefault().register(this);
        ntb = findViewById(R.id.ntb);
        ntb.setTitleText("继续教育学习");
        ntb.setTvLeftVisiable(true);
        ntb.setOnBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /**
     * 设置事件
     */
    @Override
    protected void initEvent() {
        super.initEvent();
        toMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TabManageActivity.startAction(KjllxxActivity.this, mList);
            }
        });
    }

    /**
     * 初始化数据
     */
    @Override
    protected void initData() {
        super.initData();
        mPresenter.studytypelist("");


    }

    @Override
    public void onClick(View v) {

    }


    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void studytypelistSuccess(List<StudyTypeBean.StudyTypesBean> data) {
        if (data == null) {
            return;
        }
        mList.clear();

        List<Fragment> fragmentList = new ArrayList<>();
        ArrayList<String> title = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            mList.add(new TypesBean(data.get(i).getCode(), data.get(i).getValue()));
            Fragment fragment = KjllxxFragment.newInstance(data.get(i).getCode());
            fragmentList.add(fragment);
            title.add(data.get(i).getValue());
        }
        FragmentManager manager = getSupportFragmentManager();
        BasePagerAdapter adapter = new BasePagerAdapter(manager, fragmentList, title);
        vpContent.setAdapter(adapter);
        tabLayout.setupWithViewPager(vpContent);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        vpContent.setOffscreenPageLimit(data.size());
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                EventBus.getDefault().post(new KjllxxRef(mList.get(tab.getPosition()).getCode()));

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
    public void studyslistSuccess(List<StudysBean.StudyListBean> data) {

    }


    @Override
    protected void onDestroy() {
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
                        &&note.getValue().equals(mList.get(i).getValue())) {
                    vpContent.setCurrentItem(i);
                    break;
                }
            }

        }
    }


}