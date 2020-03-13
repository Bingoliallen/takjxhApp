package takjxh.android.com.taapp.activityui.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import takjxh.android.com.commlibrary.adapter.recycler.BaseRecyclerAdapter;
import takjxh.android.com.commlibrary.adapter.recycler.ViewHolder;
import takjxh.android.com.taapp.R;
import takjxh.android.com.taapp.activityui.activity.KjllxxActivity;
import takjxh.android.com.taapp.activityui.activity.LlksActivity;
import takjxh.android.com.taapp.activityui.activity.XsfdpxActivity;
import takjxh.android.com.taapp.activityui.bean.XxjfBean;

/**
 * Created by Administrator on 2019/10/16.
 */

public class XxjfAdapter extends BaseRecyclerAdapter<XxjfBean.ScoreTasksBean> {

    public XxjfAdapter(Context context) {
        super(context);
        putItemLayoutId(R.layout.item_recycler_xxjf_list);

    }

    @Override
    public void onBind(ViewHolder holder, final XxjfBean.ScoreTasksBean item, final int position) {
        if ("01".equals(item.getIsComplete())) {
            holder.getView(R.id.tvtodo).setVisibility(View.GONE);
            holder.setText(R.id.tvtitle, item.getTitle() + "（完成）");
        } else if ("00".equals(item.getIsComplete())) {
            holder.getView(R.id.tvtodo).setVisibility(View.VISIBLE);
            holder.setText(R.id.tvtitle, item.getTitle() + "（未完成）");
        } else {
            holder.getView(R.id.tvtodo).setVisibility(View.GONE);
            holder.setText(R.id.tvtitle, item.getTitle());
        }

        holder.setText(R.id.tv_extra, item.getTime());
        holder.setText(R.id.tvjf, item.getDesc());

        holder.getView(R.id.tvtodo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //01-线上辅导培训,02-继续教育考试
                if ("01".equals(item.getType())) {
                    XsfdpxActivity.startAction((Activity) mContext);
                }else if ("02".equals(item.getType())) {
                    LlksActivity.startAction((Activity) mContext);
                }else if ("03".equals(item.getType())) {
                    KjllxxActivity.startAction((Activity) mContext);
                }
            }
        });

    }
}