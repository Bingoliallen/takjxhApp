package takjxh.android.com.taapp.activityui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import takjxh.android.com.commlibrary.utils.BarUtil;
import takjxh.android.com.commlibrary.view.activity.BaseActivity;
import takjxh.android.com.taapp.R;
import takjxh.android.com.taapp.activityui.base.BaseDialog;
import takjxh.android.com.taapp.activityui.bean.KsnrDetailBean;
import takjxh.android.com.taapp.activityui.dialog.MessageDialog;
import takjxh.android.com.taapp.activityui.presenter.KsctckPresenter;
import takjxh.android.com.taapp.activityui.presenter.impl.IKsctckPresenter;
import takjxh.android.com.taapp.view.NormalTitleBar;

/**
 * 类名称：考试错题查看
 *
 * @Author: libaibing
 * @Date: 2019-11-21 14:37
 * @Description:
 **/
public class KsctckActivity extends BaseActivity<KsctckPresenter> implements IKsctckPresenter.IView, View.OnClickListener {

    public static void startAction(Activity activity, String id) {
        Intent intent = new Intent(activity, KsctckActivity.class);
        intent.putExtra("id", id);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @BindView(R.id.kswc)
    TextView kswc;

    @BindView(R.id.ntb)
    NormalTitleBar ntb;

    @BindView(R.id.imageBrowseViewPager)
    ViewPager imageBrowseViewPager;
    @BindView(R.id.mTvImageCount1)
    TextView mTvImageCount1;
    @BindView(R.id.mTvImageCount2)
    TextView mTvImageCount2;


    private String id;

    private ArrayList<KsnrDetailBean.Question1Bean> mList = new ArrayList<>(); //图片列表
    private int index = 0;

    private String msg = "";

    /**
     * 返回布局文件
     */
    @Override
    protected int getContentViewId() {
        return R.layout.activity_ksctck;
    }

    @Override
    protected KsctckPresenter createPresenter() {
        return new KsctckPresenter(this);
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
        ntb.setTitleText("查看考试错题");
        ntb.setTvLeftVisiable(true);
       /* ntb.setRightImagVisibility(true);
        ntb.setRightImagSrc(R.mipmap.nw);*/


        ntb.setOnBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mPresenter.examdetail("", id);

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
    public Context getContext() {
        return this;
    }

    @Override
    public void examdetailSuccess(KsnrDetailBean data) {
        if (data == null) {
            return;
        }
        if (data.question1 == null) {

        }
       /* ntb.setOnRightImagListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //每道题答题的有关提示
                new MessageDialog.Builder(KsnrActivity.this)
                        // 标题可以不用填写
                        .setTitle("退出")
                        // 内容必须要填写
                        .setMessage(msg)
                        // 确定按钮文本
                        .setConfirm("确定")
                        // 设置 null 表示不显示取消按钮
                        .setCancel(null)
                        // 设置点击按钮后不关闭对话框
                        //.setAutoDismiss(false)
                        .setListener(new MessageDialog.OnListener() {

                            @Override
                            public void onConfirm(BaseDialog dialog) {
                                // toast("确定了");

                            }

                            @Override
                            public void onCancel(BaseDialog dialog) {
                                // toast("取消了");
                            }
                        })
                        .show();

            }
        });*/

        mList.clear();
        mList.addAll(data.question1);
        mTvImageCount1.setText(index + 1 + "");
        mTvImageCount2.setText("/" + mList.size());

        imageBrowseViewPager.setAdapter(new ImageBrowseAdapter());
        imageBrowseViewPager.setCurrentItem(index);
        imageBrowseViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                index = position;
                msg = mList.get(position).des;
                mTvImageCount1.setText(index + 1 + "");
                mTvImageCount2.setText("/" + mList.size());
            }
        });


    }

    @Override
    public void examdetailFailed() {

    }


    private class ImageBrowseAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            // View mView = LayoutInflater.from(KsnrActivity.this).inflate(R.layout.layout_ksnr,container, false);
            View mView = View.inflate(KsctckActivity.this, R.layout.layout_ksnr, null);
            LinearLayout mlDX = mView.findViewById(R.id.mlDX);
            LinearLayout mL4 = mView.findViewById(R.id.mL4);

            TextView tvtitle = mView.findViewById(R.id.tvtitle);
            tvtitle.setText((position + 1) + ".在这个入口不能掉头？");
            ImageView iv = mView.findViewById(R.id.iv);
            RadioButton radioButton2 = mView.findViewById(R.id.radioButton2);
            RadioButton radioButton1 = mView.findViewById(R.id.radioButton1);

            container.addView(mView);
            return mView;
        }

        @Override
        //不调用会报错
        public void destroyItem(ViewGroup container, int position, Object object) {

            container.removeView((View) object);
        }
    }


}
