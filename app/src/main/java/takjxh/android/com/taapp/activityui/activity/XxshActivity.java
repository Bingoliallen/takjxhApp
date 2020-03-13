package takjxh.android.com.taapp.activityui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import takjxh.android.com.commlibrary.presenter.impl.BasePresenter;
import takjxh.android.com.commlibrary.utils.BarUtil;
import takjxh.android.com.commlibrary.view.activity.BaseActivity;
import takjxh.android.com.taapp.R;
import takjxh.android.com.taapp.activityui.bean.XxshType;
import takjxh.android.com.taapp.activityui.fragment.XxshFragment;
import takjxh.android.com.taapp.utils.DisplayUtil;
import takjxh.android.com.taapp.view.NormalTitleBar;

/**信息审核
 * Created by Administrator on 2019/10/17.
 */

public class XxshActivity extends BaseActivity implements OnTabSelectListener {
    public static void startAction(Activity activity) {
        Intent intent = new Intent(activity, XxshActivity.class);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    /**
     * 字体加粗,大写
     */
    @BindView(R.id.tl_3)
    SlidingTabLayout tabLayout_3;
    @BindView(R.id.vp)
    ViewPager vp;
    @BindView(R.id.ntb)
    NormalTitleBar ntb;

    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private List<XxshType> items = new ArrayList<>();
    private MyPagerAdapter mAdapter;


    @Override
    protected int getContentViewId() {
        return R.layout.activity_xxsh;
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

    @Override
    protected void initView() {
        super.initView();
        ntb.setTvLeftVisiable(true);
        ntb.setTitleText("信息审核");
        ntb.setOnBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        items.clear();
        items.add(new XxshType("","所有"));
        items.add(new XxshType("01","待审核"));
        items.add(new XxshType("02","已审核"));
        items.add(new XxshType("03","审核不通过"));
        for (XxshType title : items) {
            XxshFragment fragmentOne = new XxshFragment();
            //fragment保存参数，传入一个Bundle对象
            Bundle bundle = new Bundle();
            bundle.putString("channelid", title.id);
            fragmentOne.setArguments(bundle);
            mFragments.add(fragmentOne);

        }
        mAdapter = new MyPagerAdapter(getSupportFragmentManager(), mFragments, items);
        vp.setAdapter(mAdapter);
        tabLayout_3.setOnTabSelectListener(XxshActivity.this);
        tabLayout_3.setTabWidth(DisplayUtil.px2dip(DisplayUtil.getScreenWidth(XxshActivity.this) / 4));
        tabLayout_3.setViewPager(vp);

    }

    @Override
    protected void initData() {
        super.initData();

    }



    @Override
    public void onTabSelect(int position) {

    }

    @Override
    public void onTabReselect(int position) {

    }

    public class MyPagerAdapter extends FragmentPagerAdapter {

        private ArrayList<Fragment> mFragments = new ArrayList<>();
        private List<XxshType> items = new ArrayList<>();

        public MyPagerAdapter(FragmentManager fm, ArrayList<Fragment> mFragments, List<XxshType> items) {
            super(fm);
            this.mFragments = mFragments;
            this.items = items;
        }

        public MyPagerAdapter(FragmentManager fm, ArrayList<Fragment> mFragments) {
            super(fm);
            this.mFragments = mFragments;
        }


        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return items.get(position).mc;
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }


}
