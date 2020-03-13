package takjxh.android.com.taapp.activityui.activity;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import butterknife.BindView;
import takjxh.android.com.commlibrary.presenter.impl.BasePresenter;
import takjxh.android.com.commlibrary.utils.BarUtil;
import takjxh.android.com.commlibrary.view.activity.BaseActivity;
import takjxh.android.com.taapp.R;
import takjxh.android.com.taapp.activityui.bean.TypesBean;
import takjxh.android.com.taapp.activityui.dropdownmenu.ConstellationAdapter;
import takjxh.android.com.taapp.view.NormalTitleBar;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-10-28 15:55
 * @Description:
 **/
public class TabManageActivity  extends BaseActivity implements View.OnClickListener{

    public static void startAction(Activity activity,ArrayList<TypesBean> mList) {
        Intent intent = new Intent(activity, TabManageActivity.class);
        intent.putExtra("list",  mList);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @BindView(R.id.ntb)
    NormalTitleBar ntb;
    @BindView(R.id.constellation)
    GridView constellation;
    @BindView(R.id.tv_btn_edit)
    TextView tv_btn_edit;

    private ArrayList<TypesBean> mList = new ArrayList<>();

    private ConstellationAdapter constellationAdapter;


    /**
     * 返回布局文件
     */
    @Override
    protected int getContentViewId() {
        return R.layout.activity_tab_manage;
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

        mList= (ArrayList<TypesBean>) getIntent().getSerializableExtra("list");

        ntb = findViewById(R.id.ntb);
        ntb.setTitleText("频道管理");
        ntb.setTvLeftVisiable(true);
        ntb.setOnBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        constellationAdapter = new ConstellationAdapter(this, mList);
        constellation.setAdapter(constellationAdapter);

        constellation.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               constellationAdapter.setCheckItem(position);

                if(mList!=null && mList.size()>0){
                    EventBus.getDefault().post(new TypesBean(mList.get(constellationAdapter.getCheckItemPosition()).getCode(),
                            mList.get(constellationAdapter.getCheckItemPosition()).getValue()));
                }

                finish();
            }
        });

        tv_btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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


}