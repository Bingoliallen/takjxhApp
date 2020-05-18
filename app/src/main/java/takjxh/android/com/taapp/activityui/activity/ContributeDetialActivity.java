package takjxh.android.com.taapp.activityui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import takjxh.android.com.commlibrary.utils.BarUtil;
import takjxh.android.com.commlibrary.view.activity.BaseActivity;
import takjxh.android.com.taapp.R;
import takjxh.android.com.taapp.activityui.bean.ContributeDetial;
import takjxh.android.com.taapp.activityui.presenter.ContributeDetialPresenter;
import takjxh.android.com.taapp.activityui.presenter.impl.IContributeDetialPresenter;
import takjxh.android.com.taapp.view.NormalTitleBar;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2020-05-10 22:57
 * @Description:
 **/
public class ContributeDetialActivity  extends BaseActivity<ContributeDetialPresenter> implements IContributeDetialPresenter.IView, View.OnClickListener {

    public static void startAction(Activity activity, String id) {
        Intent intent = new Intent(activity, ContributeDetialActivity.class);
        intent.putExtra("id", id);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @BindView(R.id.ntb)
    NormalTitleBar ntb;


    @BindView(R.id.edGSMC)
    TextView edGSMC;
/*    @BindView(R.id.edXYDM)
    TextView edXYDM;*/

    @BindView(R.id.edLXRXM)
    TextView edLXRXM;
    @BindView(R.id.edLXDH)
    TextView edLXDH;




    String id;

    /**
     * 返回布局文件
     */
    @Override
    protected int getContentViewId() {
        return R.layout.activity_contribute_detial;
    }

    @Override
    protected ContributeDetialPresenter createPresenter() {
        return new ContributeDetialPresenter(this);
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
        ntb.setTitleText("投稿详情");
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
        mPresenter.contributedetail("", id);
    }


    @Override
    public Context getContext() {
        return this;
    }


    @Override
    public void contributedetailSuccess(ContributeDetial data) {
        if (data == null) {
            return;
        }

        edGSMC.setText(data.getTitle());
       /* edXYDM.setText(data.getType());*/

        edLXRXM.setText(data.getContent());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            edLXRXM.setText(Html.fromHtml(data.getContent(),Html.FROM_HTML_MODE_COMPACT));
        }else {
            edLXRXM.setText(Html.fromHtml(data.getContent()));
        }

        edLXDH.setText("作者："+data.getCreateUser()+"  发布时间："+data.getTime());


    }

    @Override
    public void contributedetailFailed() {

    }

}
