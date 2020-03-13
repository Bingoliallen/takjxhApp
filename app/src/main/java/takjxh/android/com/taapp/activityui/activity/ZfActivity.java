package takjxh.android.com.taapp.activityui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.tsy.sdk.pay.alipay.Alipay;
import com.tsy.sdk.pay.weixin.WXPay;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import cn.iwgang.countdownview.CountdownView;
import takjxh.android.com.commlibrary.utils.BarUtil;
import takjxh.android.com.commlibrary.utils.ToastUtil;
import takjxh.android.com.commlibrary.view.activity.BaseActivity;
import takjxh.android.com.taapp.R;
import takjxh.android.com.taapp.activityui.bean.OrderQueryBean;
import takjxh.android.com.taapp.activityui.presenter.OrderZfPresenter;
import takjxh.android.com.taapp.activityui.presenter.impl.IOrderZfPresenter;
import takjxh.android.com.taapp.view.NormalTitleBar;

/**
 * 类名称：发起支付
 *
 * @Author: libaibing
 * @Date: 2019-10-18 15:23
 * @Description:
 **/
public class ZfActivity extends BaseActivity<OrderZfPresenter> implements IOrderZfPresenter.IView, View.OnClickListener {

    public static void startAction(Activity activity, String orderId, String effectSecond,String price) {
        Intent intent = new Intent(activity, ZfActivity.class);
        intent.putExtra("orderId", orderId);
        intent.putExtra("effectSecond", effectSecond);
        intent.putExtra("price", price);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @BindView(R.id.ntb)
    NormalTitleBar ntb;
    @BindView(R.id.cv_countdownViewTest3)
    CountdownView mCvCountdownViewTest3;

    @BindView(R.id.btn_clue_commit)
    Button btn_clue_commit;

    @BindView(R.id.radioButton1)
    RadioButton radioButton1;
    @BindView(R.id.radioButton2)
    RadioButton radioButton2;


    @BindView(R.id.tvtotal)
    TextView tvtotal;

    private String orderId, effectSecond;
    private String applyStatus;
    private boolean isPay = false;
    String price;
    /**
     * 返回布局文件
     */
    @Override
    protected int getContentViewId() {
        return R.layout.activity_zf;
    }

    @Override
    protected OrderZfPresenter createPresenter() {
        return new OrderZfPresenter(this);
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
        orderId = getIntent().getStringExtra("orderId");
        effectSecond = getIntent().getStringExtra("effectSecond");
        price = getIntent().getStringExtra("price");
        ntb = findViewById(R.id.ntb);
        ntb.setTitleText("支付");
        ntb.setTvLeftVisiable(true);
        ntb.setOnBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tvtotal.setText("¥ "+price);

        if (!TextUtils.isEmpty(effectSecond)) {
            long time3 = Long.valueOf(effectSecond) * 1000;
            mCvCountdownViewTest3.start(time3);
        } else {
            isPay = false;
            mPresenter.orderquery("", orderId);
        }

    }

    /**
     * 设置事件
     */
    @Override
    protected void initEvent() {
        super.initEvent();
        mCvCountdownViewTest3.setOnCountdownEndListener(new CountdownView.OnCountdownEndListener() {
            @Override
            public void onEnd(CountdownView cv) {
                ToastUtil.showToast(ZfActivity.this, "订单支付超时！");
                btn_clue_commit.setEnabled(false);
                btn_clue_commit.setBackgroundResource(R.drawable.login_btn_shape_n);
            }
        });
        radioButton1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    radioButton2.setChecked(false);
                }


            }
        });
        radioButton2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    radioButton1.setChecked(false);
                }

            }
        });
        btn_clue_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isPay = true;
                mPresenter.orderquery("", orderId);
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
    public Context getContext() {
        return this;
    }

    @Override
    public void orderquerySuccess(OrderQueryBean data) {
        if (data == null) {
            ToastUtil.showToast(ZfActivity.this, "订单详情数据异常！");
            if (!isPay) {
                btn_clue_commit.setEnabled(false);
                btn_clue_commit.setBackgroundResource(R.drawable.login_btn_shape_n);
            } else {
                btn_clue_commit.setEnabled(true);
                btn_clue_commit.setBackgroundResource(R.drawable.selector_login_btn);
            }

            return;
        }

        effectSecond = "" + data.getEffectSecond();
        applyStatus = data.getApplyStatus();
        if (!isPay) {
            long time3 = Long.valueOf(data.getEffectSecond()) * 1000;
            mCvCountdownViewTest3.start(time3);
            if ("01".equals(data.getApplyStatus())) {
                //待支付状态
                btn_clue_commit.setEnabled(true);
                btn_clue_commit.setBackgroundResource(R.drawable.selector_login_btn);
            } else if ("02".equals(data.getApplyStatus())) {
                ToastUtil.showToast(ZfActivity.this, "订单报名成功！");
                btn_clue_commit.setEnabled(false);
                btn_clue_commit.setBackgroundResource(R.drawable.login_btn_shape_n);
            } else if ("03".equals(data.getApplyStatus())) {
                ToastUtil.showToast(ZfActivity.this, "订单支付失败！");
                btn_clue_commit.setEnabled(false);
                btn_clue_commit.setBackgroundResource(R.drawable.login_btn_shape_n);
            }
        } else {
            if ("01".equals(data.getApplyStatus())) {
                //去调起第三方支付
                /*if (radioButton1.isChecked()) {
                    doAlipay("");
                } else if (radioButton2.isChecked()) {
                    doWXPay("");
                }*/
                ToastUtil.showToast(ZfActivity.this, "支付成功");
                finish();
            } else {

            }
        }


    }

    @Override
    public void orderqueryFailed() {
        if (!isPay) {
            btn_clue_commit.setEnabled(false);
            btn_clue_commit.setBackgroundResource(R.drawable.login_btn_shape_n);
        }

    }

    @Override
    public void orderdoneSuccess(String data) {
        ToastUtil.showToast(ZfActivity.this, data);
        finish();
    }

    @Override
    public void orderdoneFailed() {

    }


    /**
     * 微信支付
     *
     * @param pay_param 支付服务生成的支付参数
     */
    private void doWXPay(String pay_param) {
        String wx_appid = "wxXXXXXXX";     //替换为自己的appid
        WXPay.init(getApplicationContext(), wx_appid);      //要在支付前调用
        WXPay.getInstance().doPay(pay_param, new WXPay.WXPayResultCallBack() {
            @Override
            public void onSuccess() {
                Toast.makeText(getApplication(), "支付成功", Toast.LENGTH_SHORT).show();
                Map<String, String> queryMap = new HashMap<>();
                queryMap.put("orderId", orderId);
                mPresenter.orderdone("", queryMap);
            }

            @Override
            public void onError(int error_code) {
                switch (error_code) {
                    case WXPay.NO_OR_LOW_WX:
                        Toast.makeText(getApplication(), "未安装微信或微信版本过低", Toast.LENGTH_SHORT).show();
                        break;

                    case WXPay.ERROR_PAY_PARAM:
                        Toast.makeText(getApplication(), "参数错误", Toast.LENGTH_SHORT).show();
                        break;

                    case WXPay.ERROR_PAY:
                        Toast.makeText(getApplication(), "支付失败", Toast.LENGTH_SHORT).show();
                        break;
                }
            }

            @Override
            public void onCancel() {
                Toast.makeText(getApplication(), "支付取消", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 支付宝支付
     *
     * @param pay_param 支付服务生成的支付参数
     */
    private void doAlipay(String pay_param) {
        new Alipay(this, pay_param, new Alipay.AlipayResultCallBack() {
            @Override
            public void onSuccess() {
                Toast.makeText(getApplication(), "支付成功", Toast.LENGTH_SHORT).show();
                Map<String, String> queryMap = new HashMap<>();
                queryMap.put("orderId", orderId);
                mPresenter.orderdone("", queryMap);
            }

            @Override
            public void onDealing() {
                Toast.makeText(getApplication(), "支付处理中...", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(int error_code) {
                switch (error_code) {
                    case Alipay.ERROR_RESULT:
                        Toast.makeText(getApplication(), "支付失败:支付结果解析错误", Toast.LENGTH_SHORT).show();
                        break;

                    case Alipay.ERROR_NETWORK:
                        Toast.makeText(getApplication(), "支付失败:网络连接错误", Toast.LENGTH_SHORT).show();
                        break;

                    case Alipay.ERROR_PAY:
                        Toast.makeText(getApplication(), "支付错误:支付码支付失败", Toast.LENGTH_SHORT).show();
                        break;

                    default:
                        Toast.makeText(getApplication(), "支付错误", Toast.LENGTH_SHORT).show();
                        break;
                }

            }

            @Override
            public void onCancel() {
                Toast.makeText(getApplication(), "支付取消", Toast.LENGTH_SHORT).show();
            }
        }).doPay();
    }


}
