package takjxh.android.com.taapp.activityui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;
import takjxh.android.com.commlibrary.image.ImageWrapper;
import takjxh.android.com.commlibrary.utils.BarUtil;
import takjxh.android.com.commlibrary.utils.ToastUtil;
import takjxh.android.com.commlibrary.view.activity.BaseActivity;
import takjxh.android.com.taapp.R;
import takjxh.android.com.taapp.activityui.bean.CommDetailBean;
import takjxh.android.com.taapp.activityui.bean.CommQuestionBean;
import takjxh.android.com.taapp.activityui.bean.JlztDetailType;
import takjxh.android.com.taapp.activityui.fragment.JlztDetailFragment;
import takjxh.android.com.taapp.activityui.presenter.JlztDetaiPresenter;
import takjxh.android.com.taapp.activityui.presenter.impl.IJlztDetaiPresenter;
import takjxh.android.com.taapp.utils.DisplayUtil;
import takjxh.android.com.taapp.view.NormalTitleBar;

/**
 * 类名称：交流主题详情
 *
 * @Author: libaibing
 * @Date: 2019-10-18 10:17
 * @Description:
 **/
public class JlztDetailActivity extends BaseActivity<JlztDetaiPresenter> implements IJlztDetaiPresenter.IView, OnTabSelectListener {

    public static void startAction(Activity activity, String id) {
        Intent intent = new Intent(activity, JlztDetailActivity.class);
        intent.putExtra("id", id);
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

    @BindView(R.id.iv_avatar)
    CircleImageView iv_avatar;

    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvcompany)
    TextView tvcompany;
    @BindView(R.id.tvtitle)
    TextView tvtitle;
    @BindView(R.id.tv_gz)
    TextView tv_gz;
    @BindView(R.id.tvContent)
    TextView tvContent;


    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private List<JlztDetailType> items = new ArrayList<>();
    private MyPagerAdapter mAdapter;

    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    private int pageIndex = 1;
    private int pageIndex1 = 1;
    private int pageSize = 20;
    private int total = 0;
    private boolean isLoadMore = false;

    private JlztDetailFragment fragmentOne;
    private JlztDetailFragment fragmentTwo;

    private String id = "";
    private String orderBy = "createTime";
    private String ascOrDesc ="desc";


    /**
     * 返回布局文件
     */
    @Override
    protected int getContentViewId() {
        return R.layout.activity_jlzt_detail_refresh;
    }

    @Override
    protected JlztDetaiPresenter createPresenter() {
        return new JlztDetaiPresenter(this);
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
        id = getIntent().getStringExtra("id");
        ntb = findViewById(R.id.ntb);
        ntb.setTitleText("交流主题");
        ntb.setTvLeftVisiable(true);
        ntb.setOnBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        items.clear();
        items.add(new JlztDetailType("createTime", "热门"));
        items.add(new JlztDetailType("commentNum", "最新"));

        for (JlztDetailType title : items) {
            if ("createTime".equals(title.id)) {
                fragmentOne = new JlztDetailFragment(vp);
                Bundle bundle = new Bundle();
                bundle.putString("channelid", title.id);
                bundle.putString("topicId", id);
                fragmentOne.setArguments(bundle);
                mFragments.add(fragmentOne);
            } else if ("commentNum".equals(title.id)) {
                fragmentTwo = new JlztDetailFragment(vp);
                Bundle bundle = new Bundle();
                bundle.putString("channelid", title.id);
                bundle.putString("topicId", id);
                fragmentTwo.setArguments(bundle);
                mFragments.add(fragmentTwo);
            }


        }
        mAdapter = new MyPagerAdapter(getSupportFragmentManager(), mFragments, items);
        vp.setAdapter(mAdapter);
        vp.setOffscreenPageLimit(2);
        vp.setCurrentItem(0);
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //   vp.resetHeight(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        //   vp.resetHeight(0);
        tabLayout_3.setOnTabSelectListener(JlztDetailActivity.this);
        tabLayout_3.setTabWidth((DisplayUtil.px2dip(DisplayUtil.getScreenWidth(JlztDetailActivity.this))) / items.size());
        tabLayout_3.setViewPager(vp);

        setRefresh();
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
    protected void onResume() {
        super.onResume();
        mRefreshLayout.autoRefresh();

    }

    @Override
    public void onTabSelect(int position) {
        orderBy = items.get(position).id;
       // ToastUtil.showToast(this, "------orderBy---:" + orderBy);

        //  vp.resetHeight(position);
        //  vp.setCurrentItem(position);
    }

    @Override
    public void onTabReselect(int position) {

    }

    @Override
    public Context getContext() {
        return this;
    }
    private String questionNum;
    private String answerNum;
    @Override
    public void commdetailSuccess(CommDetailBean.DetailBean data) {
        if (data == null) {
            return;
        }

        ImageWrapper.setImage(iv_avatar, data.getUserCover(), R.mipmap.head_man_offline, ImageWrapper.CENTER_CROP);

        tvName.setText(data.getName());
        tvcompany.setText(data.getCompany());
        tvtitle.setText(data.getTitle());
        tvContent.setText(data.getDes());
        questionNum=data.getQuestionNum();
        answerNum=data.getAnswerNum();
       /* tv_gz;*/

    }

    @Override
    public void commdetailFailed() {

    }

    @Override
    public void commquestionlistSuccess(List<CommQuestionBean.CommQuestionsBean> data, String mmorderBy) {
        if(data ==null){
            return;
        }
        if ("createTime".equals(mmorderBy)) {
            if (!isLoadMore) {
                fragmentOne.onRefresh(questionNum,answerNum,data);
            } else {
                fragmentOne.loadMore(data);
            }

        } else if ("commentNum".equals(mmorderBy)) {
            if (!isLoadMore) {
                fragmentTwo.onRefresh(questionNum,answerNum,data);
            } else {
                fragmentTwo.loadMore(data);
            }

        }


    }

    @Override
    public void commquestionlistFailed() {

    }


    /**
     * 设置上拉/下拉监听器
     */
    private void setRefresh() {
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                mPresenter.commdetail("", id);
                isLoadMore = false;
                pageIndex = 1;
                pageIndex1 = 1;
                mPresenter.commquestionlist("", id, "createTime", ascOrDesc,"" + pageIndex, "" + pageSize);
                mPresenter.commquestionlist("", id, "commentNum", ascOrDesc,"" + pageIndex1, "" + pageSize);
                refreshLayout.finishRefresh(1000);
            }
        });
        mRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                loadMore();
                refreshLayout.finishLoadMore(1000);

            }
        });
    }


    /**
     * 更多
     */
    private void loadMore() {
        isLoadMore = true;
        if("createTime".equals(orderBy)){
            pageIndex++;
            mPresenter.commquestionlist("", id, orderBy, ascOrDesc,"" + pageIndex, "" + pageSize);
        }else if("commentNum".equals(orderBy)){
            pageIndex1++;
            mPresenter.commquestionlist("", id, orderBy, ascOrDesc,"" + pageIndex1, "" + pageSize);
        }

    }


    public class MyPagerAdapter extends FragmentPagerAdapter {

        private ArrayList<Fragment> mFragments = new ArrayList<>();
        private List<JlztDetailType> items = new ArrayList<>();

        public MyPagerAdapter(FragmentManager fm, ArrayList<Fragment> mFragments, List<JlztDetailType> items) {
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
