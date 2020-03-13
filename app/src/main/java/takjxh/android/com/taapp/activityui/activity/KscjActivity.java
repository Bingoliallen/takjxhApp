package takjxh.android.com.taapp.activityui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import takjxh.android.com.commlibrary.utils.BarUtil;
import takjxh.android.com.commlibrary.view.activity.BaseActivity;
import takjxh.android.com.taapp.R;
import takjxh.android.com.taapp.activityui.bean.ExamAddBean;
import takjxh.android.com.taapp.activityui.bean.KsnrDetailBean;
import takjxh.android.com.taapp.activityui.presenter.KsctckPresenter;
import takjxh.android.com.taapp.activityui.presenter.impl.IKsctckPresenter;
import takjxh.android.com.taapp.view.NormalTitleBar;

/**
 * 类名称：考试成绩
 *
 * @Author: libaibing
 * @Date: 2019-10-17 13:25
 * @Description:
 **/
public class KscjActivity extends BaseActivity <KsctckPresenter> implements IKsctckPresenter.IView, View.OnClickListener {

    public static void startAction(Activity activity, String id, String code,ExamAddBean data) {
        Intent intent = new Intent(activity, KscjActivity.class);
        intent.putExtra("id", id);
        intent.putExtra("code", code);
        intent.putExtra("data",data);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @BindView(R.id.ntb)
    NormalTitleBar ntb;

    @BindView(R.id.mlck)
    LinearLayout mlck;
    @BindView(R.id.mlcxks)
    LinearLayout mlcxks;

    @BindView(R.id.tvtime)
    TextView tvtime;
    @BindView(R.id.tvpm1)
    TextView tvpm1;
    @BindView(R.id.tvtotal)
    TextView tvtotal;
    @BindView(R.id.tvmsg)
    TextView tvmsg;
    @BindView(R.id.tvtype)
    TextView tvtype;
    @BindView(R.id.tvcontent)
    TextView tvcontent;
    @BindView(R.id.tv_zt)
    TextView tv_zt;

    @BindView(R.id.ivpm1)
    ImageView ivpm1;
    @BindView(R.id.tvpm0)
    TextView tvpm0;

    private String id;
    private String code;
    ExamAddBean data;

    /**
     * 返回布局文件
     */
    @Override
    protected int getContentViewId() {
        return R.layout.activity_kscj;
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
        code = getIntent().getStringExtra("code");
        data = (ExamAddBean) getIntent().getSerializableExtra("data");

        ntb = findViewById(R.id.ntb);
        ntb.setTitleText("考试成绩");
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
        mlck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                KsctckActivity.startAction(KscjActivity.this,id);

            }
        });
        mlcxks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                KsnrActivity.startAction(KscjActivity.this,code);
                finish();
            }
        });
        ivpm1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JfpmActivity.startAction(KscjActivity.this);
            }
        });
        tvpm0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JfpmActivity.startAction(KscjActivity.this);
            }
        });
        tvpm1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JfpmActivity.startAction(KscjActivity.this);
            }
        });
    }

    /**
     * 初始化数据
     */
    @Override
    protected void initData() {
        super.initData();
     //   mPresenter.examdetail("", id);


        tvtime.setText(""+data.getShowTotalTime());
        tvpm1.setText(""+data.getRank());
        tvtotal.setText(""+data.getScore());
        tvmsg.setText("棒棒滴~");
        List<String> list= Arrays.asList(data.getDes().split("<br/>"));
        for(int i=0;i<list.size();i++){
            if(i==0){
                tvtype.setText(list.get(i));
            }else  if(i==1){
                tvcontent.setText(list.get(i));
            }
        }
        tv_zt.setText("做错3题，未做60题");
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

        if(data ==null){
            return;
        }



    }

    @Override
    public void examdetailFailed() {

    }
}

