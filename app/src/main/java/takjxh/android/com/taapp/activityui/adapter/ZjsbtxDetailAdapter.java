package takjxh.android.com.taapp.activityui.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import takjxh.android.com.commlibrary.adapter.recycler.BaseRecyclerAdapter;
import takjxh.android.com.commlibrary.adapter.recycler.ViewHolder;
import takjxh.android.com.commlibrary.adapter.recycler.divider.LinearLayoutColorDivider;
import takjxh.android.com.taapp.R;
import takjxh.android.com.taapp.activityui.bean.PolicyApplyDetailBean;
import takjxh.android.com.taapp.activityui.bean.UploadFileBean;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-10-29 11:45
 * @Description:
 **/
public class ZjsbtxDetailAdapter extends BaseRecyclerAdapter<PolicyApplyDetailBean.ApplyInfoBean> {

    public ZjsbtxDetailAdapter(Context context) {
        super(context);
        putItemLayoutId(R.layout.item_recycler_zjsbtx_detail_list);

    }


    @Override
    public void onBind(ViewHolder holder, final PolicyApplyDetailBean.ApplyInfoBean item, final int position) {

        holder.getView(R.id.mL1).setVisibility(View.VISIBLE);
        holder.getView(R.id.mL2).setVisibility(View.VISIBLE);
        holder.getView(R.id.mL3).setVisibility(View.VISIBLE);
        holder.getView(R.id.mL4).setVisibility(View.VISIBLE);
        holder.getView(R.id.mL5).setVisibility(View.VISIBLE);
        holder.getView(R.id.mL6).setVisibility(View.VISIBLE);
        holder.getView(R.id.mL7).setVisibility(View.VISIBLE);

     /*   holder.getView(R.id.mLdx).setVisibility(View.GONE);
        holder.getView(R.id.mL8).setVisibility(View.GONE);
        holder.getView(R.id.mL9).setVisibility(View.GONE);
        holder.getView(R.id.mL10).setVisibility(View.GONE);
        holder.getView(R.id.mL11).setVisibility(View.GONE);



        holder.getView(R.id.mL1).setVisibility(View.GONE);
        holder.getView(R.id.mL2).setVisibility(View.GONE);
        holder.getView(R.id.mL3).setVisibility(View.GONE);
        holder.getView(R.id.mL4).setVisibility(View.GONE);
        holder.getView(R.id.mL5).setVisibility(View.GONE);
        holder.getView(R.id.mL6).setVisibility(View.GONE);
        holder.getView(R.id.mL7).setVisibility(View.GONE);*/

        holder.getView(R.id.mLdx).setVisibility(View.VISIBLE);
        holder.getView(R.id.mL8).setVisibility(View.VISIBLE);
        holder.getView(R.id.mL9).setVisibility(View.VISIBLE);
        holder.getView(R.id.mL10).setVisibility(View.VISIBLE);
        holder.getView(R.id.mL11).setVisibility(View.VISIBLE);
        holder.getView(R.id.mL12).setVisibility(View.VISIBLE);
        holder.getView(R.id.mL13).setVisibility(View.VISIBLE);


        holder.setText(R.id.tvtitle, item.getTitle());

        holder.setText(R.id.edGSMC, item.getEntName());
        holder.setText(R.id.edXYDM, item.getOrgCode());
        holder.setText(R.id.edZCSJ, item.getRegTime());
        holder.setText(R.id.edFR, item.getLagalPerson());
        holder.setText(R.id.edLXRXM, item.getLinkman());
        holder.setText(R.id.edLXDH, item.getLinkmanPhone());

        RecyclerView rv_RecyclerView = (RecyclerView) holder.getView(R.id.rv_RecyclerView);
        if (!TextUtils.isEmpty(item.files)) {
            List<String> list= Arrays.asList(item.files.split(","));
            List<String> listNames= Arrays.asList(item.fileNames.split(","));
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
                holder.getView(R.id.edSCFJ).setVisibility(View.GONE);
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
                holder.getView(R.id.edSCFJ).setVisibility(View.VISIBLE);
                rv_RecyclerView.setVisibility(View.GONE);
            }
        }else{
            holder.getView(R.id.edSCFJ).setVisibility(View.VISIBLE);
            rv_RecyclerView.setVisibility(View.GONE);
        }
       /* */

        holder.setText(R.id.edFCXM, item.getApplyType());
        holder.setText(R.id.edQYSDS, item.getShowEntIncomeTax());
        holder.setText(R.id.edGRSDS, item.getShowPersIncomeTax());
        holder.setText(R.id.edGSYNS, item.getShowEntIncome());
        holder.setText(R.id.edSQJLJE, item.getShowApplyAmount());

        holder.setText(R.id.tv_zt, item.getStatus());
        holder.setText(R.id.tvtime, item.getTime());

       /* if(item.type==0){
            holder.setText(R.id.tvtitle,position +1+".企业基本情况");
        }else if(item.type==1){
            holder.setText(R.id.tvtitle,position+1 +".申报内容信息");


        }*/

       /* holder.getView(R.id.mLM).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });*/

    }



    public interface OnZjsbtxDetailClickListener {
        void onClick(UploadFileBean item,ArrayList<UploadFileBean> urls );

    }

    private OnZjsbtxDetailClickListener mClickListener;

    public void setmClickListener(OnZjsbtxDetailClickListener mClickListener) {
        this.mClickListener = mClickListener;
    }


}