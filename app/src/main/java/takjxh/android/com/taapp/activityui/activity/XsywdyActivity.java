package takjxh.android.com.taapp.activityui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import takjxh.android.com.commlibrary.adapter.recycler.divider.GridSpacingItemDecoration;
import takjxh.android.com.commlibrary.adapter.recycler.layout_manager.CustomGridLayoutManager;
import takjxh.android.com.commlibrary.image.ImageWrapper;
import takjxh.android.com.commlibrary.utils.BarUtil;
import takjxh.android.com.commlibrary.utils.ViewUtil;
import takjxh.android.com.commlibrary.view.activity.BaseActivity;
import takjxh.android.com.taapp.R;
import takjxh.android.com.taapp.activityui.adapter.BaseViewPagerRollAdapter;
import takjxh.android.com.taapp.activityui.adapter.CjwtAdapter;
import takjxh.android.com.taapp.activityui.adapter.RmtwAdapter;
import takjxh.android.com.taapp.activityui.adapter.ZjsAdapter;
import takjxh.android.com.taapp.activityui.bean.QaLatestBean;
import takjxh.android.com.taapp.activityui.bean.QaListBean;
import takjxh.android.com.taapp.activityui.bean.QaQauserListBean;
import takjxh.android.com.taapp.activityui.bean.QafaqListBean;
import takjxh.android.com.taapp.activityui.presenter.XsywdyPresenter;
import takjxh.android.com.taapp.activityui.presenter.impl.IXsywdyPresenter;
import takjxh.android.com.taapp.view.NormalTitleBar;

/**
 * 类名称：线上业务答疑
 *
 * @Author: libaibing
 * @Date: 2019-10-17 15:28
 * @Description:
 **/
public class XsywdyActivity extends BaseActivity<XsywdyPresenter> implements IXsywdyPresenter.IView, View.OnClickListener {

    public static void startAction(Activity activity) {
        Intent intent = new Intent(activity, XsywdyActivity.class);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @BindView(R.id.tvtw)
    TextView tvtw;
    @BindView(R.id.normal_view1)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.ntb)
    NormalTitleBar ntb;
    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;
  /*  @BindView(R.id.recycler_view2)
    RecyclerView recycler_view2;*/
    @BindView(R.id.recycler_view3)
    RecyclerView recycler_view3;

    private ZjsAdapter mZjsAdapter;
   // private CjwtAdapter mCjwtAdapter;
    private RmtwAdapter mRmtwAdapter;
    private List<QaQauserListBean.QaUsersBean> mList1 = new ArrayList<>();
    private List<QafaqListBean.FaqsBean> mList2 = new ArrayList<>();
    private List<QaListBean.QasBean> mList3 = new ArrayList<>();

    @BindView(R.id.vp_pager)
    ViewPager mVpPager;
    @BindView(R.id.ll_points)
    LinearLayout mLlPoints;

    @BindView(R.id.vp_pager1)
    ViewPager mVpPager1;
    @BindView(R.id.ll_points1)
    LinearLayout mLlPoints1;

    private int pageIndex = 1;
    private int pageSize = 20;
    private int pageSize1 = 100;
    private int total = 0;
    private boolean isLoadMore = false;

   private List<QaLatestBean.DetailBean> listData  = new ArrayList<>();



    /**
     * 返回布局文件
     */
    @Override
    protected int getContentViewId() {
        return R.layout.activity_xsywdy;
    }

    @Override
    protected XsywdyPresenter createPresenter() {
        return new XsywdyPresenter(this);
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
        ntb.setTitleText("答疑频道");
        ntb.setTvLeftVisiable(true);
        ntb.setOnBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        recycler_view.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false));
        mZjsAdapter = new ZjsAdapter(this);
        recycler_view.setAdapter(mZjsAdapter);

        mZjsAdapter.set(mList1);


       /* mCjwtAdapter = new CjwtAdapter(this);
        CustomGridLayoutManager layoutManagerTime = new CustomGridLayoutManager(XsywdyActivity.this, 2);
        layoutManagerTime.setScrollEnabled(true);
        recycler_view2.setLayoutManager(layoutManagerTime);
        recycler_view2.addItemDecoration(
                new GridSpacingItemDecoration(2, ViewUtil.dp2px(XsywdyActivity.this, 0), ViewUtil.dp2px(XsywdyActivity.this, 0),
                        true));
        recycler_view2.setAdapter(mCjwtAdapter);

        mCjwtAdapter.set(mList2);*/

        recycler_view3.setLayoutManager(new LinearLayoutManager(XsywdyActivity.this, LinearLayoutManager.VERTICAL, false));
        recycler_view3.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.top = ViewUtil.dp2px(XsywdyActivity.this, 0);
                outRect.bottom = ViewUtil.dp2px(XsywdyActivity.this, 0);
            }
        });
        mRmtwAdapter = new RmtwAdapter(XsywdyActivity.this);
        recycler_view3.setAdapter(mRmtwAdapter);

        mRmtwAdapter.set(mList3);

        tvtw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ZxywActivity.startAction(XsywdyActivity.this);
            }
        });


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
    public void onClick(View v) {

    }


    private void iniVpData() {
        mLlPoints.removeAllViews();
        //每页显示的最大的数量
        final int mPageSize = 1;
        //总的页数向上取整
      //  final int totalPage = (int) Math.ceil(listData.size() * 1.0 / mPageSize);
        List<View> viewPagerList = new ArrayList<>();
        for (int i = 0; i < listData.size(); i++) {
            String mid=listData.get(i).getId();

            //每个页面都是inflate出一个新实例
            final View mView =  View.inflate(XsywdyActivity.this,R.layout.layout_head_banner, null);
            TextView tvtime =  mView.findViewById(R.id.tvtime);
            TextView tvtype =  mView.findViewById(R.id.tvtype);
            TextView tvtitle =  mView.findViewById(R.id.tvtitle);
            tvtime.setText(listData.get(i).getTime());
            tvtype.setText("");//+listData.get(i).getType(
            tvtitle.setText(listData.get(i).getTitle());

            ImageView iv =  mView.findViewById(R.id.iv);
            iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
            //加载图片
            if(!TextUtils.isEmpty(listData.get(i).getCover())){
                ImageWrapper.setImage(iv, listData.get(i).getCover(), R.drawable.pic_defalt,ImageWrapper.FIT_CENTER);
            }else {
                ImageWrapper.setImage(iv, R.drawable.pic_defalt);
            }

            iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    TwDetailActivity.lunch(XsywdyActivity.this,mid);
                }
            });
            //每一个GridView作为一个View对象添加到ViewPager集合中
            viewPagerList.add(mView);
        }
        //设置ViewPager适配器
        mVpPager.setAdapter(new BaseViewPagerRollAdapter(viewPagerList));

        //添加小圆点
        final ImageView[] ivPoints = new ImageView[listData.size()];
        for (int i = 0; i < listData.size(); i++) {
            //循坏加入点点图片组
            ivPoints[i] = new ImageView(XsywdyActivity.this);
            if (i == 0) {
                ivPoints[i].setImageResource(R.drawable.ic_page_focuese);
            } else {
                ivPoints[i].setImageResource(R.drawable.ic_page_unfocused);
            }
            ivPoints[i].setPadding(8, 8, 8, 8);
            mLlPoints.addView(ivPoints[i]);
        }
        mVpPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < listData.size(); i++) {
                    if (i == position) {
                        ivPoints[i].setImageResource(R.drawable.ic_page_focuese);
                    } else {
                        ivPoints[i].setImageResource(R.drawable.ic_page_unfocused);
                    }
                }
            }
        });
    }


    private void iniVpData1() {
        mLlPoints1.removeAllViews();

        //每页显示的最大的数量
        final int mPageSize1 = 4;
        //总的页数向上取整
        final int totalPage1 = (int) Math.ceil(mList2.size() * 1.0 / mPageSize1);
        List<View> viewPagerList1 = new ArrayList<>();
        for (int i = 0; i < totalPage1; i++) {
            List<QafaqListBean.FaqsBean> mList20 = new ArrayList<>();
          /*  List<QafaqListBean.FaqsBean> mList00 = new ArrayList<>();
            mList20.addAll(mList2);*/
            int start=(i+0)*4;
            int end=(i+1)*4;
            if(end<=mList2.size()){
                mList20 =  mList2.subList(start, end );
            }else {
                mList20 =  mList2.subList(start, mList2.size());
            }
            //每个页面都是inflate出一个新实例
            final RecyclerView recycler_view2 = (RecyclerView) View.inflate(XsywdyActivity.this,
                    R.layout.item_vp_grid_view, null);

            CjwtAdapter mCjwtAdapter = new CjwtAdapter(XsywdyActivity.this);
            CustomGridLayoutManager layoutManagerTime = new CustomGridLayoutManager(XsywdyActivity.this, 2);
            layoutManagerTime.setScrollEnabled(true);
            recycler_view2.setLayoutManager(layoutManagerTime);
            recycler_view2.addItemDecoration(
                    new GridSpacingItemDecoration(2, ViewUtil.dp2px(XsywdyActivity.this, 0), ViewUtil.dp2px(XsywdyActivity.this, 0),
                            true));
            recycler_view2.setAdapter(mCjwtAdapter);
            mCjwtAdapter.set(mList20);

            //每一个GridView作为一个View对象添加到ViewPager集合中
            viewPagerList1.add(recycler_view2);
        }
        //设置ViewPager适配器
        mVpPager1.setAdapter(new BaseViewPagerRollAdapter(viewPagerList1));

        //添加小圆点
        final ImageView[] ivPoints = new ImageView[totalPage1];
        for (int i = 0; i < totalPage1; i++) {
            //循坏加入点点图片组
            ivPoints[i] = new ImageView(XsywdyActivity.this);
            if (i == 0) {
                ivPoints[i].setImageResource(R.drawable.ic_page_focuese);
            } else {
                ivPoints[i].setImageResource(R.drawable.ic_page_unfocused);
            }
            ivPoints[i].setPadding(8, 8, 8, 8);
            mLlPoints1.addView(ivPoints[i]);
        }
        mVpPager1.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < totalPage1; i++) {
                    if (i == position) {
                        ivPoints[i].setImageResource(R.drawable.ic_page_focuese);
                    } else {
                        ivPoints[i].setImageResource(R.drawable.ic_page_unfocused);
                    }
                }
            }
        });
    }





    @Override
    public void onPause() {
        super.onPause();

    }

    @Override
    public void onResume() {
        super.onResume();
        mRefreshLayout.autoRefresh();

    }


    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void qafaqlistSuccess(QafaqListBean data) {
        if (data == null) {
            return;
        }
        total=data.faqSum;
        if(data.faqs !=null){
            mList2.clear();
            mList2.addAll(data.faqs);
        }

        iniVpData1();

        if(mList2.size()<total){
            mPresenter.qafaqlist("",""+1,""+total);
        }



    }

    @Override
    public void qafaqlistFailed() {

    }

    @Override
    public void qalistSuccess(List<QaListBean.QasBean> data) {
        if (data == null) {
            return;
        }
        if (!isLoadMore) {
            mList3.clear();
            mList3.addAll(data);
        } else {
            mList3.addAll(data);
        }

        mRmtwAdapter.set(mList3);
    }

    @Override
    public void qalistFailed() {

    }

    @Override
    public void qaqauserlistSuccess(List<QaQauserListBean.QaUsersBean> data) {

        if (data == null) {
            return;
        }
        if (!isLoadMore) {
            mList1.clear();
            mList1.addAll(data);
        } else {
            mList1.addAll(data);
        }

        mZjsAdapter.set(mList1);

    }

    @Override
    public void qaqauserlistFailed() {

    }

    @Override
    public void qalatestSuccess(QaLatestBean.DetailBean data) {
        if (data == null) {
            return;
        }
        listData.clear();
        listData.add(data);

        iniVpData();
    }

    @Override
    public void qalatestFailed() {

    }


    /**
     * 设置上拉/下拉监听器
     *
     */
    private void setRefresh() {
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                mPresenter.qafaqlist("",""+1,""+10);
                mPresenter.qalatest("");//最新答疑列表
                isLoadMore = false;
                pageIndex = 1;
                mPresenter.qalist("", "" + pageIndex,"" + pageSize);//热门列表
                mPresenter.qaqauserlist("", "" + pageIndex,"" + pageSize1);//专家列表
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
     *
     */
    private void loadMore() {

        isLoadMore = true;
        pageIndex++;
        mPresenter.qalist("", "" + pageIndex,"" + pageSize);
        mPresenter.qaqauserlist("", "" + pageIndex,"" + pageSize1);
    }







}