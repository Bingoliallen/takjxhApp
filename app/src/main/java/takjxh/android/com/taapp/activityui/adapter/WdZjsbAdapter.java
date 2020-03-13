package takjxh.android.com.taapp.activityui.adapter;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;

import java.util.Arrays;
import java.util.List;

import takjxh.android.com.commlibrary.adapter.recycler.BaseRecyclerAdapter;
import takjxh.android.com.commlibrary.adapter.recycler.ViewHolder;
import takjxh.android.com.taapp.R;
import takjxh.android.com.taapp.activityui.activity.ZjsbtxDetailActivity;
import takjxh.android.com.taapp.activityui.bean.PolicyApplyBean;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-10-16 11:12
 * @Description:
 **/
public class WdZjsbAdapter extends BaseRecyclerAdapter<PolicyApplyBean.ApplyInfosBean> {

    public WdZjsbAdapter(Context context) {
        super(context);
        putItemLayoutId(R.layout.item_recycler_wd_zjsb_list);

    }


    @Override
    public void onBind(ViewHolder holder, final PolicyApplyBean.ApplyInfosBean item, final int position) {
        holder.getView(R.id.ratingBar1).setVisibility(View.GONE);
        holder.getView(R.id.tv_pj).setVisibility(View.GONE);


        holder.setText(R.id.tv_sj, "申请时间："+item.getTime());
        holder.setText(R.id.tv_zt, item.getStatus());
        holder.setText(R.id.tvtitle, item.getTitle());

        holder.setText(R.id.tv_extra, "");
        holder.setText(R.id.tv_extra1, "");
        holder.getView(R.id.tv_extra1).setVisibility(View.GONE);
        holder.setText(R.id.tv_extra2, "");
        if(!TextUtils.isEmpty(item.getDesc())){
            String desc=item.getDesc().replace("\r\n","");
            List<String> list= Arrays.asList(desc.split("<br/>"));
           if(list!=null &&list.size()>0){
               if(list.size()<=1){
                   holder.setText(R.id.tv_extra2, list.get(0));
               }else  {
                   holder.setText(R.id.tv_extra2, list.get(list.size()-1));
                   String msg="";
                   for(int i=0;i<list.size()-1;i++){
                       msg=msg+list.get(i)+"\n";
                   }
                   holder.setText(R.id.tv_extra, msg);
               }
           }
        }

       /* if (item.type == 3) {
            holder.setText(R.id.tv_zt, "求助中");
            holder.getView(R.id.tv_pj).setVisibility(View.VISIBLE);
        } else if (item.type == 4) {
            holder.setText(R.id.tv_zt, "已完成");
            holder.getView(R.id.ratingBar1).setVisibility(View.VISIBLE);
        }
        holder.getView(R.id.tv_pj).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PjActivity.startAction((Activity) mContext);
            }
        });*/

        holder.getView(R.id.mLM).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ZjsbtxDetailActivity.startAction((Activity) mContext,item.getId());
            }
        });



    }
}