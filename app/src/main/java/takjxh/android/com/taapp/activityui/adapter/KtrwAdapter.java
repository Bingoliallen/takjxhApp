package takjxh.android.com.taapp.activityui.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import takjxh.android.com.commlibrary.adapter.recycler.BaseRecyclerAdapter;
import takjxh.android.com.commlibrary.adapter.recycler.ViewHolder;
import takjxh.android.com.taapp.R;
import takjxh.android.com.taapp.activityui.activity.KtrwfkActivity;
import takjxh.android.com.taapp.activityui.activity.KtrwtxActivity;
import takjxh.android.com.taapp.activityui.bean.IssueListBean;


public class KtrwAdapter extends BaseRecyclerAdapter<IssueListBean.UserIssueTasksBean> {

    private String type;

    public KtrwAdapter(Context context) {
        super(context);
        putItemLayoutId(R.layout.item_recycler_ktrw_list);

    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public void onBind(ViewHolder holder, final IssueListBean.UserIssueTasksBean item, final int position) {
        holder.setText(R.id.tvtitle, item.getTitle());
        holder.setText(R.id.tv_extra, item.getDes());
        holder.setText(R.id.tvtotal, item.getCreateUnit());
        holder.setText(R.id.tvtime, item.getTime());
        if ("01".equals(type)) {
            holder.setText(R.id.tv_pj, "报名");
            holder.getView(R.id.tv_pj).setVisibility(View.VISIBLE);
            holder.getView(R.id.tv_zt).setVisibility(View.GONE);
            holder.getView(R.id.tv_pj).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                  /*  ZxbbActivity.startAction((Activity) mContext);*/
                }
            });

        } else if ("00".equals(type)) {
            if ("00".equals(item.getStatus())) {
                holder.setText(R.id.tv_zt, "已报名");
                holder.getView(R.id.tv_zt).setVisibility(View.VISIBLE);
                holder.getView(R.id.tv_pj).setVisibility(View.GONE);
            } else if ("02".equals(item.getStatus())) {
                holder.setText(R.id.tv_pj, "待反馈");
                holder.getView(R.id.tv_pj).setVisibility(View.VISIBLE);
                holder.getView(R.id.tv_zt).setVisibility(View.GONE);
                holder.getView(R.id.tv_pj).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        KtrwfkActivity.startAction((Activity) mContext,item.getId());
                    }
                });
            } else if ("03".equals(item.getStatus())) {
                holder.setText(R.id.tv_zt, "已反馈");
                holder.getView(R.id.tv_zt).setVisibility(View.VISIBLE);
                holder.getView(R.id.tv_pj).setVisibility(View.GONE);
            }


        }
        holder.getView(R.id.mLM).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KtrwtxActivity.startAction((Activity) mContext, item.getId(), item.getTitle());
            }
        });

    }
}