package takjxh.android.com.taapp.activityui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import takjxh.android.com.commlibrary.utils.BarUtil;
import takjxh.android.com.commlibrary.utils.ToastUtil;
import takjxh.android.com.commlibrary.view.activity.BaseActivity;
import takjxh.android.com.taapp.R;
import takjxh.android.com.taapp.activityui.bean.QaTypeListBean;
import takjxh.android.com.taapp.activityui.presenter.ZxywPresenter;
import takjxh.android.com.taapp.activityui.presenter.impl.IZxywPresenter;
import takjxh.android.com.taapp.view.CustomSpinner;
import takjxh.android.com.taapp.view.NormalTitleBar;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-19 8:39
 * @Description:
 **/
public class ZxywActivity extends BaseActivity<ZxywPresenter> implements IZxywPresenter.IView, View.OnClickListener {

    public static void startAction(Activity activity) {
        Intent intent = new Intent(activity, ZxywActivity.class);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @BindView(R.id.ntb)
    NormalTitleBar ntb;

    @BindView(R.id.sp_register0)
    CustomSpinner sp_register0;
    @BindView(R.id.tv_type)
    TextView tv_type;
    private String type = "";


    @BindView(R.id.clue_title)
    EditText clue_title;
    @BindView(R.id.clue_content)
    EditText clue_content;


    private MyArrayAdapter adapterResult0;
    private List<QaTypeListBean.CommTypesBean> commTypes = new ArrayList<>();//继续教育交流类型

    /**
     * 返回布局文件
     */
    @Override
    protected int getContentViewId() {
        return R.layout.activity_zxyw;
    }


    @Override
    protected ZxywPresenter createPresenter() {
        return new ZxywPresenter(this);
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
        ntb.setTitleText("撰写提问");
        ntb.setTvLeftVisiable(true);
        ntb.setRightImagVisibility1(true);
        ntb.setRightImagSrc1(R.mipmap.sc);
        ntb.setOnRightImagListener1(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //发布
                commquestionadd();

            }
        });
        ntb.setOnBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        adapterResult0 = new MyArrayAdapter(this, commTypes);
        adapterResult0.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_register0.setAdapter(adapterResult0);
        sp_register0.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int arg2, long l) {
                String mBean = commTypes.get(arg2).getValue();
                tv_type.setText(mBean);
                type = commTypes.get(arg2).getCode();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }


        });
        sp_register0.setSelection(-1, true);





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
        mPresenter.qatypelist("");
    }


    @Override
    public void onClick(View v) {

    }


    @Override
    public Context getContext() {
        return this;
    }



    @Override
    public void qatypelistSuccess(List<QaTypeListBean.CommTypesBean> data) {
         if(data ==null){
             return;
         }
        commTypes.clear();
        commTypes.addAll(data);

        adapterResult0.notifyDataSetChanged();
    }

    @Override
    public void qatypelistFailed() {

    }

    @Override
    public void commquestionaddSuccess(String data) {
        ToastUtil.showToast(this, data);
        finish();
    }

    @Override
    public void commquestionaddFailed() {

    }


    private void commquestionadd(){
        if (TextUtils.isEmpty(type)) {
            ToastUtil.showToast(this, "请选择问题的答疑类型");
            return;
        }
        String mclue_title = clue_title.getText().toString().trim();
        if (TextUtils.isEmpty(mclue_title)) {
            ToastUtil.showToast(this, "请输入问题的标题");
            return;
        }
        String mclue_content = clue_content.getText().toString().trim();
        if (TextUtils.isEmpty(mclue_content)) {
            ToastUtil.showToast(this, "请输入问题描述");
            return;
        }

        Map<String, String> queryMap = new HashMap<>();


        queryMap.put("type", type);
        queryMap.put("title", mclue_title);
        queryMap.put("des", mclue_content);
        mPresenter.commquestionadd("", queryMap);
    }



    public class MyArrayAdapter extends ArrayAdapter<QaTypeListBean.CommTypesBean> {

        private List<QaTypeListBean.CommTypesBean> mList;

        public MyArrayAdapter(Context context, List<QaTypeListBean.CommTypesBean> objects) {
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
