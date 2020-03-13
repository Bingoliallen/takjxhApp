package takjxh.android.com.taapp.activityui.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.internal.entity.CaptureStrategy;
import com.zhihu.matisse.listener.OnCheckedListener;
import com.zhihu.matisse.listener.OnSelectedListener;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.api.BasicCallback;
import takjxh.android.com.commlibrary.BaseAppProfile;
import takjxh.android.com.commlibrary.utils.BarUtil;
import takjxh.android.com.commlibrary.utils.ShareUtils;
import takjxh.android.com.commlibrary.utils.ToastUtil;
import takjxh.android.com.commlibrary.view.activity.BaseActivity;
import takjxh.android.com.taapp.QbApplication;
import takjxh.android.com.taapp.R;
import takjxh.android.com.taapp.activityui.MainActivity;
import takjxh.android.com.taapp.activityui.base.BaseDialog;
import takjxh.android.com.taapp.activityui.bean.RegisterSuc;
import takjxh.android.com.taapp.activityui.bean.RegisterSuccess;
import takjxh.android.com.taapp.activityui.bean.SysParamBean;
import takjxh.android.com.taapp.activityui.bean.UploadFileBean;
import takjxh.android.com.taapp.activityui.dialog.AddressDialog;
import takjxh.android.com.taapp.activityui.dialog.DateAndTimeDialog;
import takjxh.android.com.taapp.activityui.dialog.DateDialog;
import takjxh.android.com.taapp.activityui.dialog.MenuIosDialog;
import takjxh.android.com.taapp.activityui.lfilepickerlibrary.LFilePickerT;
import takjxh.android.com.taapp.activityui.presenter.RegisterGLPresenter;
import takjxh.android.com.taapp.activityui.presenter.impl.IRegisterGLPresenter;
import takjxh.android.com.taapp.utils.CodeUtils;
import takjxh.android.com.taapp.utils.DeviceUtils;
import takjxh.android.com.taapp.utils.RxRegTool;
import takjxh.android.com.taapp.view.CustomSpinner;
import takjxh.android.com.taapp.view.NormalTitleBar;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-10-28 9:33
 * @Description:
 **/
public class RegisterActivity extends BaseActivity<RegisterGLPresenter> implements IRegisterGLPresenter.IView, View.OnClickListener {

    public static void startAction(Activity activity) {
        Intent intent = new Intent(activity, RegisterActivity.class);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @BindView(R.id.ntb)
    NormalTitleBar ntb;
    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.imgsx)
    ImageView imgsx;
    @BindView(R.id.btn_login)
    Button btn_login;

    @BindView(R.id.edAccount)
    EditText edAccount;
    @BindView(R.id.edXM)
    EditText edXM;
    @BindView(R.id.edGSMC)
    EditText edGSMC;
    @BindView(R.id.sp_register0)
    CustomSpinner sp_register0;
    @BindView(R.id.tv_zclx)
    TextView tv_zclx;
    private String zclxID = "";


    @BindView(R.id.mlZF)
    LinearLayout mlZF;
    @BindView(R.id.edDWMC)
    EditText edDWMC;
    @BindView(R.id.edDWDM)
    EditText edDWDM;
    @BindView(R.id.edDWLXR)
    EditText edDWLXR;
    @BindView(R.id.edDWLXDH)
    EditText edDWLXDH;


    @BindView(R.id.mlQYandDSF1)
    LinearLayout mlQYandDSF1;
    @BindView(R.id.edQYMC)
    EditText edQYMC;
    @BindView(R.id.edDM)
    EditText edDM;
    @BindView(R.id.sp_register3)
    CustomSpinner sp_register3;
    @BindView(R.id.tv_sshy)
    TextView tv_sshy;
    private String sshyID = "";


    @BindView(R.id.mlQY)
    LinearLayout mlQY;
    @BindView(R.id.tvZCSJ)
    TextView tvZCSJ;
    String mZCSJ;
    @BindView(R.id.edQYGM)
    TextView edQYGM;
    @BindView(R.id.edQYYS)
    TextView edQYYS;
    String medQYYS;

    @BindView(R.id.tv_name)
    TextView tv_name;
    private String filePath;
    @BindView(R.id.delSCFJ)
    TextView delSCFJ;
    @BindView(R.id.edSCFJ)
    TextView edSCFJ;

    @BindView(R.id.edZCDZ)
    TextView edZCDZ;


    @BindView(R.id.mlQYandDSF2)
    LinearLayout mlQYandDSF2;
    @BindView(R.id.edFR)
    EditText edFR;
    @BindView(R.id.edLXR)
    EditText edLXR;
    @BindView(R.id.edLXDH)
    EditText edLXDH;


    @BindView(R.id.mlDSF_JGYZM)
    LinearLayout mlDSF_JGYZM;
    @BindView(R.id.edJGYZM)
    EditText edJGYZM;


    @BindView(R.id.edTP)
    EditText edTP;
    @BindView(R.id.edSJ)
    EditText edSJ;
    @BindView(R.id.edPassword)
    EditText edPassword;
    @BindView(R.id.tvcode)
    TextView tvcode;

    //定义请求码常量
    private static final int REQUEST_CODE_CHOOSE = 23;

    private MyArrayAdapter1 adapterResult3;
    private MyArrayAdapter adapterResult0;
    private List<SysParamBean.ParamsBean.UsertypeBean> usertype = new ArrayList<>();
    private List<SysParamBean.ParamsBean.UsertradeBean> usertrade = new ArrayList<>();

    //企业营收
    private List<SysParamBean.ParamsBean.UserincomeBean> userincome=new ArrayList<>();
    //企业规模
    private List<SysParamBean.ParamsBean.UserscaleBean> userscale=new ArrayList<>();

    private String medQYGM;

    /**
     * 返回布局文件
     */
    @Override
    protected int getContentViewId() {
        return R.layout.activity_register_ui;
    }

    @Override
    protected RegisterGLPresenter createPresenter() {
        return new RegisterGLPresenter(this);
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
        ntb.setTitleText("会员注册");
        ntb.setTvLeftVisiable(true);
        ntb.setOnBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        img.setImageBitmap(CodeUtils.getInstance().createBitmap()); //我们在控件初始化时设置随机生成图片验证码

        mlZF.setVisibility(View.GONE);
        mlQYandDSF1.setVisibility(View.GONE);
        mlQY.setVisibility(View.GONE);
        mlQYandDSF2.setVisibility(View.GONE);
        mlDSF_JGYZM.setVisibility(View.GONE);


        adapterResult0 = new MyArrayAdapter(this, usertype);
        adapterResult0.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_register0.setAdapter(adapterResult0);
        sp_register0.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int arg2, long l) {
                String mBean = usertype.get(arg2).getValue();
                tv_zclx.setText(mBean);
                zclxID = usertype.get(arg2).getCode();
                if ("01".equals(zclxID)) {
                    mlZF.setVisibility(View.VISIBLE);
                    mlQYandDSF1.setVisibility(View.GONE);
                    mlQY.setVisibility(View.GONE);
                    mlQYandDSF2.setVisibility(View.GONE);
                    mlDSF_JGYZM.setVisibility(View.GONE);
                } else if ("02".equals(zclxID)) {
                    mlZF.setVisibility(View.GONE);
                    mlQYandDSF1.setVisibility(View.VISIBLE);
                    mlQY.setVisibility(View.VISIBLE);
                    mlQYandDSF2.setVisibility(View.VISIBLE);
                    mlDSF_JGYZM.setVisibility(View.GONE);
                } else if ("03".equals(zclxID)) {
                    mlZF.setVisibility(View.GONE);
                    mlQYandDSF1.setVisibility(View.VISIBLE);
                    mlQY.setVisibility(View.GONE);
                    mlQYandDSF2.setVisibility(View.VISIBLE);
                    mlDSF_JGYZM.setVisibility(View.VISIBLE);
                } else {
                    mlZF.setVisibility(View.GONE);
                    mlQYandDSF1.setVisibility(View.GONE);
                    mlQY.setVisibility(View.GONE);
                    mlQYandDSF2.setVisibility(View.GONE);
                    mlDSF_JGYZM.setVisibility(View.GONE);

                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }


        });
        sp_register0.setSelection(-1, true);


        adapterResult3 = new MyArrayAdapter1(this, usertrade);
        adapterResult3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_register3.setAdapter(adapterResult3);
        sp_register3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int arg2, long l) {
                String mBean = usertrade.get(arg2).getValue();
                tv_sshy.setText(mBean);
                sshyID = usertrade.get(arg2).getCode();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }


        });
        sp_register3.setSelection(-1, true);

         tv_name.setVisibility(View.GONE);
         delSCFJ.setVisibility(View.GONE);
         edSCFJ.setVisibility(View.VISIBLE);
    }

    /**
     * 设置事件
     */
    @Override
    protected void initEvent() {
        super.initEvent();

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /*  String code = CodeUtils.getInstance().getCode();//获取图片验证码上的内容*/
                img.setImageBitmap(CodeUtils.getInstance().createBitmap()); //随机生成图片验证码
            }
        });
        imgsx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                img.setImageBitmap(CodeUtils.getInstance().createBitmap()); //随机生成图片验证码
            }
        });

        btn_login.setOnClickListener(this);
        tvcode.setOnClickListener(this);

        edQYGM.setOnClickListener(this);
        edQYYS.setOnClickListener(this);
        edSCFJ.setOnClickListener(this);
        delSCFJ.setOnClickListener(this);

        edZCDZ.setOnClickListener(this);

        tvZCSJ.setOnClickListener(this);

      /*  if (android.os.Build.VERSION.SDK_INT <= 10) {
            edQYGM.setInputType(InputType.TYPE_NULL);
        } else {
            Class<EditText> cls = EditText.class;
            Method method;

            try {
                method = cls.getMethod("setShowSoftInputOnFocus", boolean.class);
                method.setAccessible(true);
                method.invoke(edQYGM, false);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }*/


    }

    /**
     * 初始化数据
     */
    @Override
    protected void initData() {
        super.initData();

        if (QbApplication.mBaseApplication.usertrade != null && QbApplication.mBaseApplication.usertrade.size() > 0) {
            usertrade.clear();
            usertrade.addAll(QbApplication.mBaseApplication.usertrade);
            adapterResult3.notifyDataSetChanged();
        } else {
            mPresenter.paramlist();
        }

        if (QbApplication.mBaseApplication.usertype != null && QbApplication.mBaseApplication.usertype.size() > 0) {
            usertype.clear();
            usertype.addAll(QbApplication.mBaseApplication.usertype);
            adapterResult0.notifyDataSetChanged();
        } else {
            mPresenter.paramlist();
        }

       if(QbApplication.mBaseApplication.userincome!=null &&QbApplication.mBaseApplication.userincome.size()>0){
           userincome.clear();
           userincome.addAll(QbApplication.mBaseApplication.userincome);
        }else{
            mPresenter.paramlist();
        }



        if(QbApplication.mBaseApplication.userscale!=null &&QbApplication.mBaseApplication.userscale.size()>0){
            userscale.clear();
            userscale.addAll(QbApplication.mBaseApplication.userscale);
        }else{
            mPresenter.paramlist();
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.delSCFJ:
                filePath="";
                tv_name.setText("");
                tv_name.setVisibility(View.GONE);
                delSCFJ.setVisibility(View.GONE);
                edSCFJ.setVisibility(View.VISIBLE);
                break;
            case R.id.edSCFJ:
                setPhoto();
                break;
            case R.id.edQYYS:
                List<String> data2 = new ArrayList<>();
                for (int i = 0; i < userincome.size(); i++) {
                    data2.add(userincome.get(i).getValue());
                }
                new MenuIosDialog.Builder(RegisterActivity.this)
                        .setTitle("选择企业营收")
                        // 确定按钮文本
                        .setConfirm("确定")
                        // 设置 null 表示不显示取消按钮
                        .setCancel(null)
                        .setData(data2)
                        .setYear(0)
                        .setListener(new MenuIosDialog.OnListener() {
                            @Override
                            public void onSelected(BaseDialog dialog, int pos, String msg) {
                                edQYYS.setText(msg);
                                medQYYS=userincome.get(pos).getCode();

                            }

                            @Override
                            public void onCancel(BaseDialog dialog) {
                                // toast("取消了");
                            }
                        })
                        .show();

                break;

            case R.id.edQYGM:
                List<String> data1 = new ArrayList<>();
                for (int i = 0; i < userscale.size(); i++) {
                    data1.add(userscale.get(i).getValue());
                }
                new MenuIosDialog.Builder(RegisterActivity.this)
                        .setTitle("选择企业规模")
                        // 确定按钮文本
                        .setConfirm("确定")
                        // 设置 null 表示不显示取消按钮
                        .setCancel(null)
                        .setData(data1)
                        .setYear(0)
                        .setListener(new MenuIosDialog.OnListener() {
                            @Override
                            public void onSelected(BaseDialog dialog, int pos, String msg) {
                                edQYGM.setText(msg);
                                medQYGM=userscale.get(pos).getCode();

                            }

                            @Override
                            public void onCancel(BaseDialog dialog) {
                                // toast("取消了");
                            }
                        })
                        .show();

                break;
            case R.id.tvZCSJ:
                // 时间选择对话框
              /*  new TimeDialog.Builder(this)
                        .setTitle("请选择时间")
                        // 确定按钮文本
                        .setConfirm("确定")
                        // 设置 null 表示不显示取消按钮
                        .setCancel("取消")
                        // 设置时间
                        //.setTime("23:59:59")
                        //.setTime("235959")
                        // 设置小时
                        //.setHour(23)
                        // 设置分钟
                        //.setMinute(59)
                        // 设置秒数
                        //.setSecond(59)
                        // 不选择秒数
                        //.setIgnoreSecond()
                        .setListener(new TimeDialog.OnListener() {

                            @Override
                            public void onSelected(BaseDialog dialog, int hour, int minute, int second) {
                                toast(hour + getString(R.string.common_hour) + minute + getString(R.string.common_minute) + second + getString(R.string.common_second));

                                // 如果不指定年月日则默认为今天的日期
                                Calendar calendar = Calendar.getInstance();
                                calendar.set(Calendar.HOUR_OF_DAY, hour);
                                calendar.set(Calendar.MINUTE, minute);
                                calendar.set(Calendar.SECOND, second);
                               // toast("时间戳：" + calendar.getTimeInMillis());
                                //toast(new SimpleDateFormat("yyyy年MM月dd日 kk:mm:ss").format(calendar.getTime()));
                            }

                            @Override
                            public void onCancel(BaseDialog dialog) {
                               // toast("取消了");
                            }
                        })
                        .show();*/


                // 日期选择对话框
                new DateAndTimeDialog.Builder(RegisterActivity.this)
                        .setTitle("请选择日期")
                        // 确定按钮文本
                        .setConfirm("确定")
                        // 设置 null 表示不显示取消按钮
                        .setCancel("取消")
                        // 设置日期
                        //.setDate("2018-12-31")
                        //.setDate("20181231")
                        //.setDate(1546263036137)
                        // 设置年份
                        //.setYear(2018)
                        // 设置月份
                        //.setMonth(2)
                        // 设置天数
                        //.setDay(20)
                        // 不选择天数
                        //.setIgnoreDay()
                        .setListener(new DateAndTimeDialog.OnListener() {
                            @Override
                            public void onSelected(BaseDialog dialog, int year, int month, int day, int hour, int minute, int second) {

                                String mmonth = "";
                                if (month < 10) {
                                    mmonth = "0" + month;
                                } else {
                                    mmonth = "" + month;
                                }
                                String mday = "";
                                if (day < 10) {
                                    mday = "0" + day;
                                } else {
                                    mday = "" + day;
                                }

                                String mhour = "";
                                if (hour < 10) {
                                    mhour = "0" + hour;
                                } else {
                                    mhour = "" + hour;
                                }

                                String mminute = "";
                                if (minute < 10) {
                                    mminute = "0" + minute;
                                } else {
                                    mminute = "" + minute;
                                }

                                String msecond = "";
                                if (second < 10) {
                                    msecond = "0" + second;
                                } else {
                                    msecond = "" + second;
                                }
                                tvZCSJ.setText(year + getString(R.string.common_year) + mmonth + getString(R.string.common_month) + mday + getString(R.string.common_day)
                                        + " " + mhour + ":" + mminute + ":" + msecond);
                                mZCSJ = year + "" + mmonth + "" + mday + "" + "" + mhour + "" + mminute + "" + msecond;
                                // 如果不指定时分秒则默认为现在的时间
                                Calendar calendar = Calendar.getInstance();
                                calendar.set(Calendar.YEAR, year);
                                // 月份从零开始，所以需要减 1
                                calendar.set(Calendar.MONTH, month - 1);
                                calendar.set(Calendar.DAY_OF_MONTH, day);
                                //     toast("时间戳：" + calendar.getTimeInMillis());
                                //toast(new SimpleDateFormat("yyyy年MM月dd日 kk:mm:ss").format(calendar.getTime()));
                            }

                            @Override
                            public void onCancel(BaseDialog dialog) {
                                // toast("取消了");
                            }
                        })
                        .show();
                break;
            case R.id.edZCDZ:
                // 选择地区对话框
                new AddressDialog.Builder(RegisterActivity.this)
                        .setTitle("请选择地区")
                        // 设置默认省份
                        // .setProvince("福建省")
                        // 设置默认城市（必须要先设置默认省份）
                        //  .setCity("厦门市")
                        // 不选择县级区域
                        //.setIgnoreArea()
                        .setListener(new AddressDialog.OnListener() {

                            @Override
                            public void onSelected(BaseDialog dialog, String province, String city, String area) {
                                // toast(province + city + area);
                                edZCDZ.setText(province + city + area);
                            }

                            @Override
                            public void onCancel(BaseDialog dialog) {
                                //   toast("取消了");
                            }
                        })
                        .show();


                break;

            case R.id.tvcode:
                getCode();
                break;
            case R.id.btn_login:
                register();
                break;

        }
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void registerSuccess(String msg) {
        String medPassword = ShareUtils.getString(BaseAppProfile.getApplication(), "jchat_userPassword", "");
        String mAccount = edAccount.getText().toString().trim();
        ShareUtils.putString(BaseAppProfile.getApplication(), "jchat_userId", mAccount);


        EventBus.getDefault().post(new RegisterSuc());
        ToastUtil.showToast(RegisterActivity.this, msg);
        MainActivity.startAction(RegisterActivity.this);
        finish();


        /*JMessageClient.register(userId, medPassword, new BasicCallback() {
            @Override
            public void gotResult(int i, String s) {
                if (i == 0) {
                    EventBus.getDefault().post(new RegisterSuc());
                    ToastUtil.showToast(RegisterActivity.this, msg);
                    MainActivity.startAction(RegisterActivity.this);
                    finish();

                } else {
                     EventBus.getDefault().post(new RegisterSuccess());
                   // ToastUtil.showToast(RegisterActivity.this, msg+"   但极光IM即时通讯的注册失败");
                    MainActivity.startAction(RegisterActivity.this);
                    finish();
                }
            }
        });*/



    }

    @Override
    public void registerFailed() {

    }

    @Override
    public void getCodeSuccess() {
        ToastUtil.showToast(this, "已发送手机验证码！");
    }

    @Override
    public void getCodeFailed() {

    }

    @Override
    public void paramlistSuccess(SysParamBean bean) {
        if (bean == null) {
            return;
        }
        if (bean.getParams() == null) {
            return;
        }
        if (bean.getParams().getUsertrade() != null) {
            QbApplication.mBaseApplication.usertrade = bean.getParams().getUsertrade();
            usertrade.clear();
            usertrade.addAll(QbApplication.mBaseApplication.usertrade);
            adapterResult3.notifyDataSetChanged();
        }
        if (bean.getParams().getUsertype() != null) {
            QbApplication.mBaseApplication.usertype = bean.getParams().getUsertype();
            usertype.clear();
            usertype.addAll(QbApplication.mBaseApplication.usertype);
            adapterResult0.notifyDataSetChanged();
        }
        if (bean.getParams().getApplyorderstatus() != null) {
            QbApplication.mBaseApplication.applyorderstatus = bean.getParams().getApplyorderstatus();
        }

        if (bean.getParams().getMessagesourcetype() != null) {
            QbApplication.mBaseApplication.messagesourcetype = bean.getParams().getMessagesourcetype();
        }
        if (bean.getParams().getLogchangetype() != null) {
            QbApplication.mBaseApplication.logchangetype = bean.getParams().getLogchangetype();
        }
        if (bean.getParams().getStudytype() != null) {
            QbApplication.mBaseApplication.studytype = bean.getParams().getStudytype();
        }
        if (bean.getParams().getAudittype() != null) {
            QbApplication.mBaseApplication.audittype = bean.getParams().getAudittype();
        }
        if (bean.getParams().getFaqtype() != null) {
            QbApplication.mBaseApplication.faqtype = bean.getParams().getFaqtype();
        }
        if (bean.getParams().getSurveystatus() != null) {
            QbApplication.mBaseApplication.surveystatus = bean.getParams().getSurveystatus();
        }
        if (bean.getParams().getTraintype() != null) {
            QbApplication.mBaseApplication.traintype = bean.getParams().getTraintype();
        }
        if (bean.getParams().getNewstype() != null) {
            QbApplication.mBaseApplication.newstype = bean.getParams().getNewstype();
        }
        if (bean.getParams().getApplystatus() != null) {
            QbApplication.mBaseApplication.applystatus = bean.getParams().getApplystatus();
        }
        if (bean.getParams().getCommtopictype() != null) {
            QbApplication.mBaseApplication.commtopictype = bean.getParams().getCommtopictype();
        }
        if (bean.getParams().getUserstatus() != null) {
            QbApplication.mBaseApplication.userstatus = bean.getParams().getUserstatus();
        }
        if (bean.getParams().getPolicycreteunit() != null) {
            QbApplication.mBaseApplication.policycreteunit = bean.getParams().getPolicycreteunit();
        }
        if (bean.getParams().getAuditstatus() != null) {
            QbApplication.mBaseApplication.auditstatus = bean.getParams().getAuditstatus();
        }

        if (bean.getParams().getUserincome() != null) {
            QbApplication.mBaseApplication.userincome = bean.getParams().getUserincome();
            userincome.clear();
            userincome.addAll(QbApplication.mBaseApplication.userincome);
        }
        if (bean.getParams().getUserscale() != null) {
            QbApplication.mBaseApplication.userscale = bean.getParams().getUserscale();
            userscale.clear();
            userscale.addAll(QbApplication.mBaseApplication.userscale);
        }

        if (bean.getParams().getUserstation() != null) {
            QbApplication.mBaseApplication.userstation = bean.getParams().getUserstation();

        }
        if (bean.getParams().getApplyordertype() != null) {
            QbApplication.mBaseApplication.applyordertype = bean.getParams().getApplyordertype();

        }


        if (bean.getParams().getExamtype() != null) {
            QbApplication.mBaseApplication.examtype = bean.getParams().getExamtype();

        }
        if (bean.getParams().getQuestiontype() != null) {
            QbApplication.mBaseApplication.questiontype = bean.getParams().getQuestiontype();

        }
        if (bean.getParams().getTitletype() != null) {
            QbApplication.mBaseApplication.titletype = bean.getParams().getTitletype();

        }
    }

    @Override
    public void paramlistFailed() {

    }

    @Override
    public void uploadSuccess(UploadFileBean data) {
        if (data == null) {
            return;
        }
        filePath=data.getFilePath();
        tv_name.setText(data.getFileName());
        tv_name.setVisibility(View.VISIBLE);
        delSCFJ.setVisibility(View.VISIBLE);
        edSCFJ.setVisibility(View.GONE);
    }


    private void setPhoto() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            //申请WRITE_EXTERNAL_STORAGE权限
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    345);
        } else {
            Matisse.from(RegisterActivity.this)
                    .choose(MimeType.ofImage(), false)
                    .countable(true)
                    .capture(true)
                    .captureStrategy(new CaptureStrategy(true, "com.zhihu.matisse.sample.fileprovider", "test"))
                    .maxSelectable(1)
                    //    .addFilter(new GifSizeFilter(320, 320, 5 * Filter.K * Filter.K))
                    .gridExpectedSize(getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                    .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                    .thumbnailScale(0.85f)
                    .theme(R.style.Matisse_Zhihus)//主题
//                                            .imageEngine(new GlideEngine())  // for glide-V3
                    //            .imageEngine(new Glide4Engine())    // for glide-V4
                    .setOnSelectedListener(new OnSelectedListener() {
                        @Override
                        public void onSelected(@NonNull List<Uri> uriList, @NonNull List<String> pathList) {
                            // DO SOMETHING IMMEDIATELY HERE
                            Log.e("onSelected", "onSelected: pathList=" + pathList);

                        }
                    })
                    .originalEnable(true)
                    .maxOriginalSize(50)
                    .autoHideToolbarOnSingleTap(true)
                    .setOnCheckedListener(new OnCheckedListener() {
                        @Override
                        public void onCheck(boolean isChecked) {
                            // DO SOMETHING IMMEDIATELY HERE
                            Log.e("isChecked", "onCheck: isChecked=" + isChecked);
                        }
                    })
                    .forResult(REQUEST_CODE_CHOOSE);
        }


    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        doNext(requestCode, grantResults);
    }

    private void doNext(int requestCode, int[] grantResults) {
        if (requestCode == 345) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Matisse.from(RegisterActivity.this)
                        .choose(MimeType.ofImage(), false)
                        .countable(true)
                        .capture(true)
                        .captureStrategy(new CaptureStrategy(true, "com.zhihu.matisse.sample.fileprovider", "test"))
                        .maxSelectable(1)
                        //    .addFilter(new GifSizeFilter(320, 320, 5 * Filter.K * Filter.K))
                        .gridExpectedSize(getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                        .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                        .thumbnailScale(0.85f)
                        .theme(R.style.Matisse_Zhihu)//主题
//                                            .imageEngine(new GlideEngine())  // for glide-V3
                        //            .imageEngine(new Glide4Engine())    // for glide-V4
                        .setOnSelectedListener(new OnSelectedListener() {
                            @Override
                            public void onSelected(@NonNull List<Uri> uriList, @NonNull List<String> pathList) {
                                // DO SOMETHING IMMEDIATELY HERE
                                Log.e("onSelected", "onSelected: pathList=" + pathList);

                            }
                        })
                        .originalEnable(true)
                        .maxOriginalSize(50)
                        .autoHideToolbarOnSingleTap(true)
                        .setOnCheckedListener(new OnCheckedListener() {
                            @Override
                            public void onCheck(boolean isChecked) {
                                // DO SOMETHING IMMEDIATELY HERE
                                Log.e("isChecked", "onCheck: isChecked=" + isChecked);
                            }
                        })
                        .forResult(REQUEST_CODE_CHOOSE);
            } else {
                // Permission Denied
            }
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            //  mAdapter.setData(Matisse.obtainResult(data), Matisse.obtainPathResult(data));
            Log.e("OnActivityResult ", String.valueOf(Matisse.obtainOriginalState(data)));
            List<Uri> mUri = Matisse.obtainResult(data);
            List<String> mList = Matisse.obtainPathResult(data);
            if (mList != null && mList.size() > 0) {
                mPresenter.upload("", mList.get(0));
            } else {
                ToastUtil.showToast(this, getString(R.string.select_msg_f));
            }
        }
    }





    private void getCode() {
        String mAccount = edAccount.getText().toString().trim();

        if (TextUtils.isEmpty(mAccount)) {
            ToastUtil.showToast(this, "请输入手机号码");
            return;
        }
        if (!RxRegTool.isMobile(mAccount)) {
            ToastUtil.showToast(this, "请输入正确的手机号码");
            return;
        }


        mPresenter.getCode(mAccount);
    }

    private void register() {
        String mAccount = edAccount.getText().toString().trim();
        if (TextUtils.isEmpty(mAccount)) {
            ToastUtil.showToast(this, "请输入手机号码");
            return;
        }
        if (!RxRegTool.isMobile(mAccount)) {
            ToastUtil.showToast(this, "请输入正确的手机号码");
            return;
        }

        String medXM = edXM.getText().toString().trim();
        if (TextUtils.isEmpty(medXM)) {
            ToastUtil.showToast(this, "请输入姓名");
            return;
        }
        String medGSMC = edGSMC.getText().toString().trim();
        if (TextUtils.isEmpty(medGSMC)) {
            ToastUtil.showToast(this, "请输入公司名称");
            return;
        }

        if (TextUtils.isEmpty(zclxID)) {
            ToastUtil.showToast(this, "请选择用户类型");
            return;
        }

        if ("01".equals(zclxID)) {
            String medDWMC = edDWMC.getText().toString().trim();
            if (TextUtils.isEmpty(medDWMC)) {
                ToastUtil.showToast(this, "请输入单位名称");
                return;
            }
            String medDWDM = edDWDM.getText().toString().trim();
            if (TextUtils.isEmpty(medDWDM)) {
                ToastUtil.showToast(this, "请输入单位代码");
                return;
            }
            String medDWLXR = edDWLXR.getText().toString().trim();
            if (TextUtils.isEmpty(medDWLXR)) {
                ToastUtil.showToast(this, "请输入单位联系人");
                return;
            }
            String medDWLXDH = edDWLXDH.getText().toString().trim();
            if (TextUtils.isEmpty(medDWLXDH)) {
                ToastUtil.showToast(this, "请输入单位联系电话");
                return;
            }
            if (!RxRegTool.isMobile(medDWLXDH)) {
                ToastUtil.showToast(this, "请输入正确的单位联系电话");
                return;
            }

        } else if ("02".equals(zclxID)) {
            String medQYMC = edQYMC.getText().toString().trim();
            if (TextUtils.isEmpty(medQYMC)) {
                ToastUtil.showToast(this, "请输入企业名称");
                return;
            }
            String medDM = edDM.getText().toString().trim();
            if (TextUtils.isEmpty(medDM)) {
                ToastUtil.showToast(this, "请输入企业组织机构代码");
                return;
            }
            if (TextUtils.isEmpty(sshyID)) {
                ToastUtil.showToast(this, "请选择所属行业");
                return;
            }

            String mtvZCSJ = tvZCSJ.getText().toString().trim();
            if (TextUtils.isEmpty(mtvZCSJ)) {
                ToastUtil.showToast(this, "请输入注册时间");
                return;
            }

            /*String medQYGM = edQYGM.getText().toString().trim();*/

            if (TextUtils.isEmpty(medQYGM)) {
                ToastUtil.showToast(this, "请选择企业规模");
                return;
            }


            if (TextUtils.isEmpty(medQYYS)) {
                ToastUtil.showToast(this, "请选择企业营收");
                return;
            }


            String medZCDZ = edZCDZ.getText().toString().trim();
            if (TextUtils.isEmpty(medZCDZ)) {
                ToastUtil.showToast(this, "请输入注册地址");
                return;
            }


            String medFR = edFR.getText().toString().trim();
            if (TextUtils.isEmpty(medFR)) {
                ToastUtil.showToast(this, "请输入法人");
                return;
            }

            String medLXR = edLXR.getText().toString().trim();
            if (TextUtils.isEmpty(medLXR)) {
                ToastUtil.showToast(this, "请输入联系人");
                return;
            }


            String medLXDH = edLXDH.getText().toString().trim();
            if (TextUtils.isEmpty(medLXDH)) {
                ToastUtil.showToast(this, "请输入联系电话");
                return;
            }
            if (!RxRegTool.isMobile(medLXDH)) {
                ToastUtil.showToast(this, "请输入正确的联系电话");
                return;
            }

        } else if ("03".equals(zclxID)) {

            String medQYMC = edQYMC.getText().toString().trim();
            if (TextUtils.isEmpty(medQYMC)) {
                ToastUtil.showToast(this, "请输入企业名称");
                return;
            }
            String medDM = edDM.getText().toString().trim();
            if (TextUtils.isEmpty(medDM)) {
                ToastUtil.showToast(this, "请输入企业组织机构代码");
                return;
            }
            if (TextUtils.isEmpty(sshyID)) {
                ToastUtil.showToast(this, "请选择所属行业");
                return;
            }


            String medFR = edFR.getText().toString().trim();
            if (TextUtils.isEmpty(medFR)) {
                ToastUtil.showToast(this, "请输入法人");
                return;
            }

            String medLXR = edLXR.getText().toString().trim();
            if (TextUtils.isEmpty(medLXR)) {
                ToastUtil.showToast(this, "请输入联系人");
                return;
            }


            String medLXDH = edLXDH.getText().toString().trim();
            if (TextUtils.isEmpty(medLXDH)) {
                ToastUtil.showToast(this, "请输入联系电话");
                return;
            }
            if (!RxRegTool.isMobile(medLXDH)) {
                ToastUtil.showToast(this, "请输入正确的联系电话");
                return;
            }

            String medJGYZM = edJGYZM.getText().toString().trim();
            if (TextUtils.isEmpty(medJGYZM)) {
                ToastUtil.showToast(this, "请输入机构认证验证码");
                return;
            }

        } else {


        }


        String medTP = edTP.getText().toString().trim();
        if (TextUtils.isEmpty(medTP)) {
            ToastUtil.showToast(this, "请输入图片验证码");
            return;
        }
        String medSJ = edSJ.getText().toString().trim();
        if (TextUtils.isEmpty(medSJ)) {
            //2020-03-06 暂时去隐藏
            //ToastUtil.showToast(this, "请输入手机验证码");
           // return;
        }
        String medPassword = edPassword.getText().toString().trim();
        if (TextUtils.isEmpty(medPassword)) {
            ToastUtil.showToast(this, "请输入6-16位的密码");
            return;
        }
        String code = CodeUtils.getInstance().getCode();//获取图片验证码上的内容
        if (!code.equals(medTP)) {
            ToastUtil.showToast(this, "图片验证码不正确");
            return;
        }


        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("code", medSJ);
        queryMap.put("username", mAccount);
        queryMap.put("password", medPassword);
        queryMap.put("name", medXM);
        queryMap.put("mobilePhone", mAccount);
        queryMap.put("company", medGSMC);
        queryMap.put("type", zclxID);
        queryMap.put("mac", DeviceUtils.getMacAddress());
        if ("01".equals(zclxID)) {
            String medDWMC = edDWMC.getText().toString().trim();
            String medDWDM = edDWDM.getText().toString().trim();
            String medDWLXR = edDWLXR.getText().toString().trim();
            String medDWLXDH = edDWLXDH.getText().toString().trim();

            queryMap.put("unitName", medDWMC);
            queryMap.put("unitCode", medDWDM);
            queryMap.put("unitLinkman", medDWLXR);
            queryMap.put("linkmanPhone", medDWLXDH);
        } else if ("02".equals(zclxID)) {
            String medQYMC = edQYMC.getText().toString().trim();
            String medDM = edDM.getText().toString().trim();
            String mtvZCSJ = tvZCSJ.getText().toString().trim();

          /*  String medQYGM = edQYGM.getText().toString().trim();
            if (TextUtils.isEmpty(medQYGM)) {
                ToastUtil.showToast(this, "请选择企业规模");
                return;
            }*/

            String medZCDZ = edZCDZ.getText().toString().trim();
            String medFR = edFR.getText().toString().trim();
            String medLXR = edLXR.getText().toString().trim();
            String medLXDH = edLXDH.getText().toString().trim();

            queryMap.put("companyName", medQYMC);
            queryMap.put("orgCode", medDM);
            queryMap.put("trade", sshyID);
            queryMap.put("regTime", mZCSJ);
            queryMap.put("regAddr", medZCDZ);
            queryMap.put("lagalPerson", medFR);
            queryMap.put("unitLinkman", medLXR);
            queryMap.put("linkmanPhone", medLXDH);

            queryMap.put("scale", medQYGM);
            queryMap.put("income", medQYYS);
            if(!TextUtils.isEmpty(filePath)){
                queryMap.put("orgFile", filePath);
            }


        } else if ("03".equals(zclxID)) {
            String medQYMC = edQYMC.getText().toString().trim();
            String medDM = edDM.getText().toString().trim();
            String medFR = edFR.getText().toString().trim();
            String medLXR = edLXR.getText().toString().trim();
            String medLXDH = edLXDH.getText().toString().trim();
            String medJGYZM = edJGYZM.getText().toString().trim();
            queryMap.put("companyName", medQYMC);
            queryMap.put("orgCode", medDM);
            queryMap.put("trade", sshyID);
            queryMap.put("lagalPerson", medFR);
            queryMap.put("unitLinkman", medLXR);
            queryMap.put("linkmanPhone", medLXDH);
            queryMap.put("orgAuthCode", medJGYZM);

            if(!TextUtils.isEmpty(filePath)){
                queryMap.put("orgFile", filePath);
            }
        } else {

        }

        mPresenter.register(queryMap);

    }


    public class MyArrayAdapter extends ArrayAdapter<SysParamBean.ParamsBean.UsertypeBean> {

        private List<SysParamBean.ParamsBean.UsertypeBean> mList;

        public MyArrayAdapter(Context context, List<SysParamBean.ParamsBean.UsertypeBean> objects) {
            super(context, android.R.layout.simple_list_item_1, android.R.id.text1, objects);
            mList = objects;
        }

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_1, null);
            }
            TextView tvName = (TextView) convertView.findViewById(android.R.id.text1);

            String mBean = mList.get(position).getValue();
            tvName.setText(mBean);

            return convertView;
        }

        @Override
        public View getDropDownView(int position, View convertView,
                                    ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(
                        android.R.layout.simple_list_item_1, null);
            }
            TextView text = (TextView) convertView
                    .findViewById(android.R.id.text1);
            String mBean = mList.get(position).getValue();
            text.setText(mBean);
            return convertView;
        }

    }


    public class MyArrayAdapter1 extends ArrayAdapter<SysParamBean.ParamsBean.UsertradeBean> {

        private List<SysParamBean.ParamsBean.UsertradeBean> mList;

        public MyArrayAdapter1(Context context, List<SysParamBean.ParamsBean.UsertradeBean> objects) {
            super(context, android.R.layout.simple_list_item_1, android.R.id.text1, objects);
            mList = objects;
        }

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_1, null);
            }
            TextView tvName = (TextView) convertView.findViewById(android.R.id.text1);

            String mBean = mList.get(position).getValue();
            tvName.setText(mBean);

            return convertView;
        }

        @Override
        public View getDropDownView(int position, View convertView,
                                    ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(
                        android.R.layout.simple_list_item_1, null);
            }
            TextView text = (TextView) convertView
                    .findViewById(android.R.id.text1);
            String mBean = mList.get(position).getValue();
            text.setText(mBean);
            return convertView;
        }

    }

}
