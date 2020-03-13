package takjxh.android.com.taapp.activityui.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import takjxh.android.com.commlibrary.presenter.impl.BasePresenter;
import takjxh.android.com.commlibrary.utils.BarUtil;
import takjxh.android.com.commlibrary.view.activity.BaseActivity;
import takjxh.android.com.taapp.R;
import takjxh.android.com.taapp.activityui.adapter.BasePagerAdapter;
import takjxh.android.com.taapp.activityui.bean.KtrwListRef;
import takjxh.android.com.taapp.activityui.bean.TypesBean;
import takjxh.android.com.taapp.activityui.fragment.KtrwListFragment;
import takjxh.android.com.taapp.view.NormalTitleBar;

/**
 * 课题任务频道
 * Created by Administrator on 2019/10/15.
 */

public class KtrwListActivity extends BaseActivity implements View.OnClickListener {

    public static void startAction(Activity activity) {
        Intent intent = new Intent(activity, KtrwListActivity.class);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @BindView(R.id.ntb)
    NormalTitleBar ntb;
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
        return R.layout.activity_ktrw_list;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
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
        ntb.setTitleText("课题任务频道");
        ntb.setTvLeftVisiable(true);
        ntb.setOnBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mList.clear();
        ArrayList<String> mTitleList = new ArrayList<>();
        mTitleList.add("待报名任务");//01-待报名，00-已报名
        mTitleList.add("已报名任务");
        List<Fragment> fragmentList = new ArrayList<>();
        ArrayList<String> title = new ArrayList<>();
        for (int i = 0; i < mTitleList.size(); i++) {
            String channelId = "01";
            if (i == 0) {
                channelId = "01";
            } else if (i == 1) {
                channelId = "00";
            }
            mList.add(new TypesBean(channelId, mTitleList.get(i)));
            Fragment fragment = KtrwListFragment.newInstance(channelId);
            fragmentList.add(fragment);
            title.add(mTitleList.get(i));
        }
        FragmentManager manager = getSupportFragmentManager();
        BasePagerAdapter adapter = new BasePagerAdapter(manager, fragmentList, title);
        vpContent.setAdapter(adapter);
        tabLayout.setupWithViewPager(vpContent);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        vpContent.setOffscreenPageLimit(mTitleList.size());
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                EventBus.getDefault().post(new KtrwListRef(mList.get(tab.getPosition()).getCode()));

            }


            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }


            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    /**
     * 设置事件
     */
    @Override
    protected void initEvent() {
        super.initEvent();
    }

    /**
     * 初始化数据
     */
    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onResume() {
        super.onResume();

    }


}