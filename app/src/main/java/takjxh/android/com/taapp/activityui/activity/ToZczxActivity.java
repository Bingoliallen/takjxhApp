package takjxh.android.com.taapp.activityui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

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
import takjxh.android.com.taapp.activityui.adapter.ToZczxAdapter;
import takjxh.android.com.taapp.activityui.bean.PolicyIndexBean;
import takjxh.android.com.taapp.activityui.presenter.ToZczxPresenter;
import takjxh.android.com.taapp.activityui.presenter.impl.IToZczxPresenter;
import takjxh.android.com.taapp.view.NormalTitleBar;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-10-23 11:41
 * @Description:
 **/
public class ToZczxActivity extends BaseActivity<ToZczxPresenter> implements IToZczxPresenter.IView, View.OnClickListener {

    public static void startAction(Activity activity) {
        Intent intent = new Intent(activity, ToZczxActivity.class);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @BindView(R.id.ntb)
    NormalTitleBar ntb;
    @BindView(R.id.iv)
    ImageView iv;


    @BindView(R.id.tvtotal)
    TextView tvtotal;
    @BindView(R.id.tvtime)
    TextView tvtime;

   /* @BindView(R.id.ml1)
    LinearLayout ml1;
    @BindView(R.id.ml2)
    LinearLayout ml2;
    @BindView(R.id.ml3)
    LinearLayout ml3;
    @BindView(R.id.ml4)
    LinearLayout ml4;*/


    @BindView(R.id.mLabm)
    LinearLayout mLabm;
    @BindView(R.id.mLgjz)
    LinearLayout mLgjz;
    @BindView(R.id.mLwzc)
    LinearLayout mLwzc;

    private ToZczxAdapter mToZczxAdapter;
    private List<PolicyIndexBean.PolicyTypesBean> mList = new ArrayList<>();

    @BindView(R.id.recycler_view2)
    RecyclerView recycler_view2;

    /**
     * 返回布局文件
     */
    @Override
    protected int getContentViewId() {
        return R.layout.activity_to_zczx;
    }

    @Override
    protected ToZczxPresenter createPresenter() {
        return new ToZczxPresenter(this);
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
        ntb.setTitleText("政策资讯");
        ntb.setTvLeftVisiable(true);
        ntb.setOnBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        mToZczxAdapter = new ToZczxAdapter(this);
        CustomGridLayoutManager layoutManagerTime = new CustomGridLayoutManager(ToZczxActivity.this, 4);
        layoutManagerTime.setScrollEnabled(true);
        recycler_view2.setLayoutManager(layoutManagerTime);
        recycler_view2.addItemDecoration(
                new GridSpacingItemDecoration(4, ViewUtil.dp2px(ToZczxActivity.this, 0), ViewUtil.dp2px(ToZczxActivity.this, 0),
                        true));
        recycler_view2.setAdapter(mToZczxAdapter);

        mToZczxAdapter.set(mList);



    }

    /**
     * 设置事件
     */
    @Override
    protected void initEvent() {
        super.initEvent();
      //  iv.setOnClickListener(this);

     /*   ml1.setOnClickListener(this);
        ml2.setOnClickListener(this);
        ml3.setOnClickListener(this);
        ml4.setOnClickListener(this);*/


        mLabm.setOnClickListener(this);
        mLgjz.setOnClickListener(this);
        mLwzc.setOnClickListener(this);

    }

    /**
     * 初始化数据
     */
    @Override
    protected void initData() {
        super.initData();
        mPresenter.policyindex("");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
           /* case R.id.ml1:
                ZczxActivity.startAction(ToZczxActivity.this,"0");
                break;
            case R.id.ml2:
                ZczxActivity.startAction(ToZczxActivity.this,"0");
                break;
            case R.id.ml3:
                ZczxActivity.startAction(ToZczxActivity.this,"0");
                break;
            case R.id.ml4:
                ZczxActivity.startAction(ToZczxActivity.this,"0");
                break;*/
            case R.id.mLabm:
                ZczxActivity.startAction(ToZczxActivity.this,"0","");
                break;
            case R.id.mLgjz:
                ZczxActivity.startAction(ToZczxActivity.this,"1","");
                break;
            case R.id.mLwzc:
                ZxjgActivity.startAction(ToZczxActivity.this);
                break;
        }
    }


    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void policyindexSuccess(PolicyIndexBean data) {
       if(data ==null){
           return;
       }
        if(data.policyTypes !=null){
            mList.clear();
            mList.addAll(data.policyTypes);
            mToZczxAdapter.set(mList);
        }
        tvtotal.setText(""+data.policyNum);
        tvtime.setText(""+data.policingNum);

        if (TextUtils.isEmpty(data.cover)) {
            iv.setImageResource(R.drawable.pic_defalt);
        } else {
            ImageWrapper.setImage(iv,  data.cover, R.drawable.pic_defalt, ImageWrapper.CENTER_CROP);
        }


    }

    @Override
    public void policyindexFailed() {

    }
}