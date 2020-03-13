package takjxh.android.com.taapp.activityui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import takjxh.android.com.commlibrary.utils.BarUtil;
import takjxh.android.com.commlibrary.view.activity.BaseActivity;
import takjxh.android.com.taapp.R;
import takjxh.android.com.taapp.activityui.bean.OrderDetailBean;
import takjxh.android.com.taapp.activityui.presenter.OrderDetailPresenter;
import takjxh.android.com.taapp.activityui.presenter.impl.IOrderDetailPresenter;
import takjxh.android.com.taapp.view.NormalTitleBar;

/**
 * 类名称：报名订单详情
 *
 * @Author: libaibing
 * @Date: 2019-11-08 15:32
 * @Description:
 **/
public class OrderDetailActivity extends BaseActivity<OrderDetailPresenter> implements IOrderDetailPresenter.IView, View.OnClickListener {

    public static void startAction(Activity activity, String id) {
        Intent intent = new Intent(activity, OrderDetailActivity.class);
        intent.putExtra("id", id);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @BindView(R.id.ntb)
    NormalTitleBar ntb;


    @BindView(R.id.edGSMC)
    TextView edGSMC;
    @BindView(R.id.edXYDM)
    TextView edXYDM;
    @BindView(R.id.edZCSJ)
    TextView edZCSJ;
    @BindView(R.id.edFR)
    TextView edFR;
    @BindView(R.id.edLXRXM)
    TextView edLXRXM;
    @BindView(R.id.edLXDH)
    TextView edLXDH;
    @BindView(R.id.edSCFJ)
    TextView edSCFJ;


    @BindView(R.id.btn_clue_commit)
    Button btn_clue_commit;


    String id;

    /**
     * 返回布局文件
     */
    @Override
    protected int getContentViewId() {
        return R.layout.activity_order_detail;
    }

    @Override
    protected OrderDetailPresenter createPresenter() {
        return new OrderDetailPresenter(this);
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
        ntb.setTitleText("在线报名详情");
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
        btn_clue_commit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ZfActivity.startAction(OrderDetailActivity.this,id,"",mPrice);
            }
        });

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
        mPresenter.orderdetail("", id);
    }


    @Override
    public Context getContext() {
        return this;
    }

    private String mPrice;
    @Override
    public void orderdetailSuccess(OrderDetailBean.OrderBean data) {
        if (data == null) {
            return;
        }
        mPrice=""+data.getPrice();

        edGSMC.setText(data.getTitle());
        edXYDM.setText(data.getName());
        edZCSJ.setText(data.getMobilePhone());
        edFR.setText(data.getShowPrice());
        edLXRXM.setText(data.getDes());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            edLXRXM.setText(Html.fromHtml(data.getDes(),Html.FROM_HTML_MODE_COMPACT));
        }else {
            edLXRXM.setText(Html.fromHtml(data.getDes()));
        }

        edLXDH.setText(data.getTime());
        edSCFJ.setText(data.getApplyStatus());
        if("待支付".equals(data.getApplyStatus())){
            btn_clue_commit.setVisibility(View.VISIBLE);
        }
    }

}
