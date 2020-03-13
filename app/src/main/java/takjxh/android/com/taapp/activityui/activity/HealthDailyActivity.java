package takjxh.android.com.taapp.activityui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import takjxh.android.com.commlibrary.utils.BarUtil;
import takjxh.android.com.commlibrary.utils.ToastUtil;
import takjxh.android.com.commlibrary.view.activity.BaseActivity;
import takjxh.android.com.taapp.R;
import takjxh.android.com.taapp.activityui.bean.HealthIndexBean;
import takjxh.android.com.taapp.activityui.presenter.HealthDailyPresenter;
import takjxh.android.com.taapp.activityui.presenter.impl.IHealthDailyPresenter;
import takjxh.android.com.taapp.utils.DeviceUtils;
import takjxh.android.com.taapp.view.NormalTitleBar;

/**
 * Created by Administrator on 2020/3/10.
 */

public class HealthDailyActivity extends BaseActivity<HealthDailyPresenter> implements IHealthDailyPresenter.IView, View.OnClickListener {

    public static void startAction(Activity activity) {
        Intent intent = new Intent(activity, HealthDailyActivity.class);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @BindView(R.id.ntb)
    NormalTitleBar ntb;
    @BindView(R.id.btn_login)
    Button btn_login;

    @BindView(R.id.edFR)
    TextView edFR;
    @BindView(R.id.edLXRXM)
    TextView edLXRXM;
    @BindView(R.id.edLXDH)
    TextView edLXDH;


    @BindView(R.id.radioButton1)
    RadioButton radioButton1;
    @BindView(R.id.radioButton2)
    RadioButton radioButton2;

    @BindView(R.id.radioButton21)
    RadioButton radioButton21;
    @BindView(R.id.radioButton22)
    RadioButton radioButton22;

    @BindView(R.id.tvtitle)
    TextView tvtitle;

    @BindView(R.id.edQYSDS)
    EditText edQYSDS;

    private boolean isSubmit;


    private boolean isChecked1;
    private boolean isChecked2;
    private boolean isChecked3;
    private boolean isChecked4;

    /**
     * 返回布局文件
     */
    @Override
    protected int getContentViewId() {
        return R.layout.activity_health_daily;
    }

    @Override
    protected HealthDailyPresenter createPresenter() {
        return new HealthDailyPresenter(this);
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
        ntb.setTitleText("每日健康报告");
        ntb.setTvLeftVisiable(true);
        ntb.setRightTitle("历史数据");
        ntb.setRightTitleVisibility(true);
        ntb.setOnRightTextListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                HealthDailyListActivity.startAction(HealthDailyActivity.this);
            }
        });
        ntb.setOnBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        tvtitle.requestFocus();

    }

    /**
     * 设置事件
     */
    @Override
    protected void initEvent() {
        super.initEvent();
        btn_login.setOnClickListener(this);
        radioButton1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                isChecked1 = b;
            }
        });
        radioButton2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                isChecked2 = b;
            }
        });
        radioButton21.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                isChecked3 = b;
            }
        });
        radioButton22.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                isChecked4 = b;
            }
        });
    }

    /**
     * 初始化数据
     */
    @Override
    protected void initData() {
        super.initData();
        mPresenter.healthindex("");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                healthadd();
                break;
        }
    }

    @Override
    public Context getContext() {
        return this;
    }

    private String deptId;

    @Override
    public void healthindexSuccess(HealthIndexBean data) {
        if (data == null) {
            return;
        }
        deptId = data.deptId;
        isSubmit = data.isSubmit;
        if (isSubmit) {
            btn_login.setText("已提交，重新提交");
            if(data.isFsks==1){
                isChecked1=true;
                radioButton1.setChecked(isChecked1);
            }else{
                isChecked2=true;
                radioButton2.setChecked(isChecked2);
            }

            if(data.isJcbl==1){
                isChecked3=true;
                radioButton21.setChecked(isChecked3);
            }else{
                isChecked4=true;
                radioButton22.setChecked(isChecked4);
            }
            edQYSDS.setText(data.temperature);
            tvtitle.requestFocus();
        }
        edFR.setText(data.deptName);
        edLXRXM.setText(data.name);
        edLXDH.setText(data.phone);


    }

    @Override
    public void healthindexFailed() {

    }

    @Override
    public void healthaddSuccess(String msg) {
        ToastUtil.showToast(this, msg);
        //finish();
    }

    @Override
    public void healthaddFailed() {

    }


    private void healthadd() {
 /*
      {
	"deptId": "1", //机构ID
	"name": "林成民", //当前用户姓名
    "phone": "15080342451", //当前用户手机号
    "isFsks":0, //是否发烧咳嗽,0-没有，1-有
    "isJcbl":0, //是否接触过确诊或疑似病例,0-没有，1-有
    "temperature":36.9 //今天体温

 */
        if (!isChecked1 && !isChecked2) {
            ToastUtil.showToast(this, "请选择当前是否有发烧咳嗽");
            return;
        }
        if (!isChecked3 && !isChecked4) {
            ToastUtil.showToast(this, "请选择是否接触过确诊或疑似病例");
            return;
        }

        String medQYSDS = edQYSDS.getText().toString().trim();
        if (TextUtils.isEmpty(medQYSDS)) {
            ToastUtil.showToast(this, "请输入今日体温");
            return;
        }

        int isFsks = 0;
        if (isChecked1) {
            isFsks = 1;
        }
        int isJcbl = 0;
        if (isChecked3) {
            isJcbl = 1;
        }

        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("deptId", deptId);
        queryMap.put("name", edLXRXM.getText().toString());
        queryMap.put("phone", edLXDH.getText().toString());
        queryMap.put("isFsks", "" + isFsks);
        queryMap.put("isJcbl", "" + isJcbl);
        queryMap.put("temperature", medQYSDS);
        mPresenter.healthadd("", queryMap);

    }


}
