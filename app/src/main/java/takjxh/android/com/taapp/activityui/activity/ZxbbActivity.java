package takjxh.android.com.taapp.activityui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import takjxh.android.com.commlibrary.utils.BarUtil;
import takjxh.android.com.commlibrary.utils.ToastUtil;
import takjxh.android.com.commlibrary.view.activity.BaseActivity;
import takjxh.android.com.taapp.QbApplication;
import takjxh.android.com.taapp.R;
import takjxh.android.com.taapp.activityui.bean.OrderGenerateBean;
import takjxh.android.com.taapp.activityui.bean.SysParamBean;
import takjxh.android.com.taapp.activityui.bean.ZxbbBean;
import takjxh.android.com.taapp.activityui.presenter.ZxbbPresenter;
import takjxh.android.com.taapp.activityui.presenter.impl.IZxbbPresenter;
import takjxh.android.com.taapp.utils.RxRegTool;
import takjxh.android.com.taapp.view.CustomSpinner;
import takjxh.android.com.taapp.view.NormalTitleBar;

/**
 * 类名称：在线报名
 *
 * @Author: libaibing
 * @Date: 2019-10-18 14:34
 * @Description:
 **/
public class ZxbbActivity extends BaseActivity<ZxbbPresenter> implements IZxbbPresenter.IView, View.OnClickListener {

    public static void startAction(Activity activity) {
        Intent intent = new Intent(activity, ZxbbActivity.class);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @BindView(R.id.ml4)
    LinearLayout ml4;
    @BindView(R.id.ml1)
    LinearLayout ml1;
    @BindView(R.id.tv_ms)
    TextView tv_ms;
    @BindView(R.id.tv_jg)
    TextView tv_jg;
    @BindView(R.id.tv_st)
    TextView tv_st;
    @BindView(R.id.tv_et)
    TextView tv_et;
    @BindView(R.id.tv_fj)
    TextView tv_fj;


    @BindView(R.id.ml2)
    LinearLayout ml2;
    @BindView(R.id.clue_content)
    EditText clue_content;
    @BindView(R.id.clue_tel)
    EditText clue_tel;
    @BindView(R.id.clue_gsmc)
    EditText clue_gsmc;
    @BindView(R.id.sp_register2)
    CustomSpinner sp_register2;
    @BindView(R.id.tv_sffp)
    TextView tv_sffp;
    int mtv_sffp = -1;

    @BindView(R.id.ml3)
    LinearLayout ml3;
    @BindView(R.id.sp_register3)
    CustomSpinner sp_register3;
    @BindView(R.id.tv_kplx)
    TextView tv_kplx;
    String mtv_kplx;
    @BindView(R.id.clue_zzjgdm)
    EditText clue_zzjgdm;
    @BindView(R.id.clue_kpmc)
    EditText clue_kpmc;
    @BindView(R.id.btn_clue_commit2)
    Button btn_clue_commit2;

    @BindView(R.id.ntb)
    NormalTitleBar ntb;
    @BindView(R.id.tv_czjg)
    TextView tv_czjg;
    @BindView(R.id.btn_clue_commit)
    Button btn_clue_commit;


    @BindView(R.id.sp_register1)
    CustomSpinner sp_register1;
    private MyArrayAdapter adapterResult1;
    private List<ZxbbBean.ApplyItemsBean> mList = new ArrayList<>();
    private String id;
    private double price;
    private String showPrice;
    private String des;


    private List<String> mList2 = new ArrayList<>();
    private List<SysParamBean.ParamsBean.AuditstatusBean> mList3 = new ArrayList<>();

    private MyArrayAdapter2 adapterResult2;
    private MyArrayAdapter3 adapterResult3;

    /**
     * 返回布局文件
     */
    @Override
    protected int getContentViewId() {
        return R.layout.activity_zxbb;
    }

    @Override
    protected ZxbbPresenter createPresenter() {
        return new ZxbbPresenter(this);
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
        ntb.setTitleText("在线报名");
        ntb.setTvLeftVisiable(true);
        ntb.setOnBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        adapterResult1 = new MyArrayAdapter(this, mList);
        adapterResult1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_register1.setAdapter(adapterResult1);
        sp_register1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int arg2, long l) {
                String mBean = mList.get(arg2).getTitle();
                tv_czjg.setText(mBean);
                id = mList.get(arg2).getId();
                price = mList.get(arg2).getPrice();
                showPrice = mList.get(arg2).getShowPrice();
                des = mList.get(arg2).getDes();


                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    tv_ms.setText(Html.fromHtml(des,Html.FROM_HTML_MODE_COMPACT));
                }else {
                    tv_ms.setText(Html.fromHtml(des));
                }
                tv_jg.setText(""+showPrice);
                tv_st.setText(""+mList.get(arg2).getStartTime());
                tv_et.setText(""+mList.get(arg2).getEndTime());
                tv_fj.setText(""+mList.get(arg2).getFileName());

                ml1.setVisibility(View.VISIBLE);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }


        });
        sp_register1.setSelection(0, true);
        if(mList.size()>0){
            int arg2=0;
            String mBean = mList.get(arg2).getTitle();
            tv_czjg.setText(mBean);
            id = mList.get(arg2).getId();
            price = mList.get(arg2).getPrice();
            showPrice = mList.get(arg2).getShowPrice();
            des = mList.get(arg2).getDes();


            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                tv_ms.setText(Html.fromHtml(des,Html.FROM_HTML_MODE_COMPACT));
            }else {
                tv_ms.setText(Html.fromHtml(des));
            }
            tv_jg.setText(""+showPrice);
            tv_st.setText(""+mList.get(arg2).getStartTime());
            tv_et.setText(""+mList.get(arg2).getEndTime());
            tv_fj.setText(""+mList.get(arg2).getFileName());
            ml1.setVisibility(View.VISIBLE);
        }


        mList2.clear();
        mList2.add("是");
        mList2.add("否");
        adapterResult2 = new MyArrayAdapter2(this, mList2);
        adapterResult2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_register2.setAdapter(adapterResult2);
        sp_register2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int arg2, long l) {
                String mBean = mList2.get(arg2);
                tv_sffp.setText(mBean);
                if (mBean.equals("是")) {
                    mtv_sffp = 1;
                    ml3.setVisibility(View.VISIBLE);
                } else {
                    mtv_sffp = 0;
                    ml3.setVisibility(View.GONE);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }


        });
        sp_register2.setSelection(-1, true);

        mList3.addAll(QbApplication.mBaseApplication.applyordertype);
        adapterResult3 = new MyArrayAdapter3(this, mList3);
        adapterResult3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_register3.setAdapter(adapterResult3);
        sp_register3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int arg2, long l) {
                String mBean = mList3.get(arg2).getValue();
                tv_kplx.setText(mBean);
                mtv_kplx = mList3.get(arg2).getCode();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }


        });
        sp_register3.setSelection(-1, true);


        btn_clue_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ml4.setVisibility(View.GONE);
                ml1.setVisibility(View.GONE);
                ml2.setVisibility(View.VISIBLE);
                ml3.setVisibility(View.GONE);
            }
        });


        btn_clue_commit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ordergenerate();

            }
        });
        ml4.setVisibility(View.VISIBLE);
        ml1.setVisibility(View.VISIBLE);
        ml2.setVisibility(View.GONE);
        ml3.setVisibility(View.GONE);
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
        mPresenter.itemlist("");
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void itemlistSuccess(List<ZxbbBean.ApplyItemsBean> data) {
        if (data == null) {
            return;
        }

        mList.clear();
        mList.addAll(data);
        adapterResult1.notifyDataSetChanged();

    }

    @Override
    public void ordergenerateSuccess(OrderGenerateBean data) {

        if (data == null) {
            ToastUtil.showToast(this, "报名订单数据返回异常");
            return;
        }
        ZfActivity.startAction(ZxbbActivity.this, data.getOrderId(), data.getEffectSecond(), "" + price);
        finish();
    }

    @Override
    public void ordergenerateFailed() {

    }


    public class MyArrayAdapter extends ArrayAdapter<ZxbbBean.ApplyItemsBean> {

        private List<ZxbbBean.ApplyItemsBean> mList;

        public MyArrayAdapter(Context context, List<ZxbbBean.ApplyItemsBean> objects) {
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

            String mBean = mList.get(position).getTitle();
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
            String mBean = mList.get(position).getTitle();
            text.setText(mBean);
            return convertView;
        }

    }


    public class MyArrayAdapter2 extends ArrayAdapter<String> {

        private List<String> mList;

        public MyArrayAdapter2(Context context, List<String> objects) {
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

            String mBean = mList.get(position);
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
            String mBean = mList.get(position);
            text.setText(mBean);
            return convertView;
        }

    }

    public class MyArrayAdapter3 extends ArrayAdapter<SysParamBean.ParamsBean.AuditstatusBean> {

        private List<SysParamBean.ParamsBean.AuditstatusBean> mList;

        public MyArrayAdapter3(Context context, List<SysParamBean.ParamsBean.AuditstatusBean> objects) {
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


    private void ordergenerate() {
        if (TextUtils.isEmpty(id)) {
            ToastUtil.showToast(this, "请选择报名项目");
            return;
        }
        String mclue_content = clue_content.getText().toString().trim();
        if (TextUtils.isEmpty(mclue_content)) {
            ToastUtil.showToast(this, "请输入您的姓名");
            return;
        }
        String mclue_tel = clue_tel.getText().toString().trim();
        if (TextUtils.isEmpty(mclue_tel)) {
            ToastUtil.showToast(this, "请输入您的手机号码");
            return;
        }
        if (!RxRegTool.isMobile(mclue_tel)) {
            ToastUtil.showToast(this, "请输入正确的手机号码");
            return;
        }

        String mclue_gsmc=clue_gsmc.getText().toString().trim();
        if (TextUtils.isEmpty(mclue_gsmc)) {
            ToastUtil.showToast(this, "请输入公司名称");
            return;
        }

        if (mtv_sffp==-1) {
            ToastUtil.showToast(this, "请选择是否需要发票");
            return;
        }
        if(mtv_sffp==1){
            if (TextUtils.isEmpty(mtv_kplx)) {
                ToastUtil.showToast(this, "请选择开票类型");
                return;
            }


            String mclue_zzjgdm=clue_zzjgdm.getText().toString().trim();
            if (TextUtils.isEmpty(mclue_zzjgdm)) {
                ToastUtil.showToast(this, "请输入组织机构代码");
                return;
            }

            String mclue_kpmc=clue_kpmc.getText().toString().trim();
            if (TextUtils.isEmpty(mclue_kpmc)) {
                ToastUtil.showToast(this, "请输入开票名称");
                return;
            }
        }

        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("mobilePhone", mclue_tel);
        queryMap.put("name", mclue_content);
        queryMap.put("applyItemId", id);

        queryMap.put("company", mclue_gsmc);

        queryMap.put("isOpenTicket", mtv_sffp);
        if(mtv_sffp==1){
            queryMap.put("ticketType", mtv_kplx);
            queryMap.put("ticketOrgCode", clue_zzjgdm.getText().toString().trim());
            queryMap.put("ticketName", clue_kpmc.getText().toString().trim());
        }

        mPresenter.ordergenerate("", queryMap);

    }


}

