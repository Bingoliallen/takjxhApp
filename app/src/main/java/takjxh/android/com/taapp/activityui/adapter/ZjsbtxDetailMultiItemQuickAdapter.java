package takjxh.android.com.taapp.activityui.adapter;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.text.TextUtils;

import android.view.View;


import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import takjxh.android.com.commlibrary.adapter.recycler.divider.LinearLayoutColorDivider;
import takjxh.android.com.taapp.R;
import takjxh.android.com.taapp.activityui.bean.PolicyapplyaddBean;
import takjxh.android.com.taapp.activityui.bean.UploadFileBean;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2020-05-11 11:14
 * @Description:
 **/
public class ZjsbtxDetailMultiItemQuickAdapter  extends BaseMultiItemQuickAdapter<PolicyapplyaddBean, BaseViewHolder> {

    FragmentActivity activity;
    int positionNum;

    public ZjsbtxDetailMultiItemQuickAdapter(FragmentActivity activity, List<PolicyapplyaddBean> data, int position) {
        super(data);
        this.activity = activity;
        this.positionNum=position;
        addItemType(1, R.layout.item_recycler_zjsbtx_detail_list11);
        addItemType(2, R.layout.item_recycler_zjsbtx_detail_list11);
        addItemType(3, R.layout.item_recycler_zjsbtx_detail_list11);
        addItemType(4, R.layout.item_recycler_zjsbtx_detail_list11);
        addItemType(6, R.layout.item_recycler_zjsbtx_detail_list11);
        addItemType(7, R.layout.item_recycler_zjsbtx_detail_list11);

        addItemType(5, R.layout.item_recycler_zjsbtx_detail_list112);


    }

    @Override
    protected void convert(BaseViewHolder helper, PolicyapplyaddBean item) {

        switch (helper.getItemViewType()) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 6:
            case 7:
                if (item.isRuleReqest()) {
                    helper.getView(R.id.imgbt).setVisibility(View.VISIBLE);
                } else {
                    helper.getView(R.id.imgbt).setVisibility(View.GONE);
                }
                helper.setText(R.id.tvName, item.getName());
                helper.setText(R.id.edGSMC, item.getValue());
                break;

            case 5:
                if (item.isRuleReqest()) {
                    helper.getView(R.id.imgbt).setVisibility(View.VISIBLE);
                } else {
                    helper.getView(R.id.imgbt).setVisibility(View.GONE);
                }
                helper.setText(R.id.tvName, item.getName());

                RecyclerView rv_RecyclerView = (RecyclerView) helper.getView(R.id.rv_RecyclerView);
                if (!TextUtils.isEmpty(item.getValue())) {
                    List<String> list= Arrays.asList(item.getValue().split(","));
                    List<String> listNames= Arrays.asList(item.getValue().split(","));
                    if (list!=null && list.size()>0) {
                        ArrayList<UploadFileBean> urls = new ArrayList<>();
                        urls.clear();
                        for(int a=0;a<list.size();a++){
                            if(a<listNames.size()){
                                urls.add(new UploadFileBean(listNames.get(a),list.get(a)));
                            }else{
                                urls.add(new UploadFileBean(list.get(a),list.get(a)));
                            }
                        }
                        helper.getView(R.id.edSCFJ).setVisibility(View.GONE);
                        rv_RecyclerView.setVisibility(View.VISIBLE);

                        rv_RecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
                        rv_RecyclerView.addItemDecoration(new LinearLayoutColorDivider(mContext.getResources(), R.color.white, R.dimen.dp_4, RecyclerView.VERTICAL));
                        rv_RecyclerView.setHasFixedSize(true);
                        QuickAdapter  mAdapter = new QuickAdapter();
                        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                if(mClickListener!=null){
                                    mClickListener.onClick(urls.get(position),urls);
                                }
                            }
                        });
                        mAdapter.replaceData(urls);
                        rv_RecyclerView.setAdapter(mAdapter);


                    }else{
                        helper.getView(R.id.edSCFJ).setVisibility(View.VISIBLE);
                        rv_RecyclerView.setVisibility(View.GONE);
                    }
                }else{
                    helper.getView(R.id.edSCFJ).setVisibility(View.VISIBLE);
                    rv_RecyclerView.setVisibility(View.GONE);
                }

                break;


        }
    }

    public interface OnZjsbtxDetailClickListener {
        void onClick(UploadFileBean item,ArrayList<UploadFileBean> urls );

    }

    private OnZjsbtxDetailClickListener mClickListener;

    public void setmClickListener(OnZjsbtxDetailClickListener mClickListener) {
        this.mClickListener = mClickListener;
    }


}

