package takjxh.android.com.taapp.activityui.activity;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import takjxh.android.com.commlibrary.presenter.impl.BasePresenter;
import takjxh.android.com.commlibrary.utils.BarUtil;
import takjxh.android.com.commlibrary.view.activity.BaseActivity;
import takjxh.android.com.taapp.R;
import takjxh.android.com.taapp.view.NormalTitleBar;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-10-15 9:15
 * @Description:
 **/
public class ToRegisterActivity extends BaseActivity implements View.OnClickListener{

    public static void startAction(Activity activity) {
        Intent intent = new Intent(activity, ToRegisterActivity.class);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @BindView(R.id.ntb)
    NormalTitleBar ntb;
    @BindView(R.id.btn_login1)
    Button btn_login1;
    @BindView(R.id.btn_login2)
    Button btn_login2;
    @BindView(R.id.btn_login3)
    Button btn_login3;
    @BindView(R.id.btn_login4)
    Button btn_login4;


    /**
     * 返回布局文件
     */
    @Override
    protected int getContentViewId() {
        return R.layout.activity_to_register;
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
        ntb.setTitleText("注册");
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
        btn_login1.setOnClickListener(this);
        btn_login2.setOnClickListener(this);
        btn_login3.setOnClickListener(this);
        btn_login4.setOnClickListener(this);
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
        switch (v.getId()){
            case R.id.btn_login1:
                RegisterGLActivity.startAction(ToRegisterActivity.this);
                finish();
                break;
            case R.id.btn_login2:
                RegisterZFActivity.startAction(ToRegisterActivity.this);
                finish();
                break;
            case R.id.btn_login3:
                RegisterQYActivity.startAction(ToRegisterActivity.this);
                finish();
                break;
            case R.id.btn_login4:
                RegisterGRActivity.startAction(ToRegisterActivity.this);
                finish();
                break;
        }
    }


}
