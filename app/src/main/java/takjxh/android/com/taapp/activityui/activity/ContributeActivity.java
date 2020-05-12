package takjxh.android.com.taapp.activityui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import android.text.TextUtils;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import takjxh.android.com.commlibrary.utils.BarUtil;
import takjxh.android.com.commlibrary.utils.ToastUtil;
import takjxh.android.com.commlibrary.view.activity.BaseActivity;
import takjxh.android.com.taapp.R;
import takjxh.android.com.taapp.activityui.chat.takevideo.utils.LogUtils;
import takjxh.android.com.taapp.activityui.presenter.ContributePresenter;
import takjxh.android.com.taapp.activityui.presenter.impl.IContributePresenter;

import takjxh.android.com.taapp.view.NormalTitleBar;
import takjxh.android.com.taapp.view.mulitmenuselect.Contribute;
import takjxh.android.com.taapp.view.mulitmenuselect.ContributeDataManager;
import takjxh.android.com.taapp.view.mulitmenuselect.ContributeDialog;
import takjxh.android.com.taapp.view.mulitmenuselect.ContributeUtil;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2020-05-10 20:50
 * @Description:
 **/
public class ContributeActivity extends BaseActivity<ContributePresenter> implements IContributePresenter.IView, View.OnClickListener {

    public static void startAction(Activity activity) {
        Intent intent = new Intent(activity, ContributeActivity.class);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }


    @BindView(R.id.ntb)
    NormalTitleBar ntb;
    @BindView(R.id.clue_content)
    EditText clue_content;
    @BindView(R.id.tv_czjg)
    TextView tv_czjg;
    @BindView(R.id.clue_gsmc)
    EditText clue_gsmc;

    @BindView(R.id.btn_clue_commit2)
    Button btn_clue_commit2;



   /* private List<String> mList2 = new ArrayList<>();*/

    private List<Contribute> list1 = new ArrayList<>();

    private List<Contribute> treeItemBeanList=new ArrayList<>();


    private String typeid;


    /**
     * 返回布局文件
     */
    @Override
    protected int getContentViewId() {
        return R.layout.activity_contribute;
    }

    @Override
    protected ContributePresenter createPresenter() {
        return new ContributePresenter(this);
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
        ntb.setTitleText("会员投稿");
        ntb.setTvLeftVisiable(true);
        ntb.setOnBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        btn_clue_commit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contributedone();

            }
        });
        tv_czjg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContributeDialog dialog=new ContributeDialog(ContributeActivity.this,treeItemBeanList);
                dialog.setonItemClickListener(new ContributeDialog.DictItemClickListener() {
                    @Override
                    public void onDictItemClick(Contribute dictUnit) {
                        if (dictUnit!=null) {
                            tv_czjg.setText(dictUnit.getValue());
                            typeid = dictUnit.getCode();
                        }
                    }
                });
                dialog.show();

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
        mPresenter.appindexcontribute("");
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void appindexcontributeSuccess(List<Contribute> data) {
        if (data == null) {
            return;
        }
        treeItemBeanList.clear();
        treeItemBeanList.addAll(data);

        for (int i = 0; i < treeItemBeanList.size(); i++) {
            treeItemBeanList.get(i).setParentId(ContributeDataManager.START_ID);
            if ( treeItemBeanList.get(i).getSecondList() != null) {
                String b = treeItemBeanList.get(i).getCode();
                for (int j = 0; j <  treeItemBeanList.get(i).getSecondList().size(); j++) {
                    treeItemBeanList.get(i).getSecondList().get(j).setParentId(b);
                }
            }
        }

        String jsonString=new Gson().toJson(treeItemBeanList);
        LogUtils.e("--------treeItemBeanList-------------:"+jsonString);


        list1.clear();
        list1= ContributeUtil.getSelList(treeItemBeanList);

        /*String[] dictionary = new String[list1.size()];
        for(int i=0;i<list1.size();i++){
            dictionary[i]=list1.get(i).getValue();
        }

        //利用适配器
        ArrayAdapter<String> adapter_actv = new ArrayAdapter<String>(
                this,android.R.layout.simple_dropdown_item_1line,dictionary);
        tv_sshy.setAdapter(adapter_actv);*/

    }

    private void changeData() {
        String a = "0";
        for (int i = 0; i < treeItemBeanList.size(); i++) {
            a = "0";
            Contribute data=setItemParentId(treeItemBeanList.get(i));
            data.setParentId(a);
            treeItemBeanList.set(i,data);

        }
    }

    private Contribute setItemParentId(Contribute data) {
        if (data.getSecondList() != null) {
            for (int j = 0; j < data.getSecondList().size(); j++) {
                String a = data.getCode();
                data.getSecondList().get(j).setParentId(a);
                data = setItemParentId(data.getSecondList().get(j));
            }
        }
        return data;
    }


    @Override
    public void appindexcontributeFailed() {

    }

    @Override
    public void contributedoneSuccess(String data) {
        ToastUtil.showToast(this, data);
        finish();
    }

    @Override
    public void contributedoneFailed() {

    }


    private void contributedone() {
        String mclue_content = clue_content.getText().toString().trim();
        if (TextUtils.isEmpty(mclue_content)) {
            ToastUtil.showToast(this, "请输入投稿标题");
            return;
        }
        if (TextUtils.isEmpty(typeid)) {
            ToastUtil.showToast(this, "请选择投稿类型");
            return;
        }


        String mclue_gsmc = clue_gsmc.getText().toString().trim();
        if (TextUtils.isEmpty(mclue_gsmc)) {
            ToastUtil.showToast(this, "请输入投稿内容");
            return;
        }


        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("title", mclue_content);
        queryMap.put("type", typeid);
        queryMap.put("content", mclue_gsmc);

        mPresenter.contributedone("", queryMap);

    }


}
