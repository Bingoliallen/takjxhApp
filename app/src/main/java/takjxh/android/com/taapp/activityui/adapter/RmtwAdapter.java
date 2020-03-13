package takjxh.android.com.taapp.activityui.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import takjxh.android.com.commlibrary.adapter.recycler.BaseRecyclerAdapter;
import takjxh.android.com.commlibrary.adapter.recycler.ViewHolder;
import takjxh.android.com.taapp.R;
import takjxh.android.com.taapp.activityui.activity.TwDetailActivity;
import takjxh.android.com.taapp.activityui.bean.QaListBean;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-10-17 16:58
 * @Description:
 **/
public class RmtwAdapter extends BaseRecyclerAdapter<QaListBean.QasBean> {

    public RmtwAdapter(Context context) {
        super(context);
        putItemLayoutId(R.layout.item_recycler_rmtw);

    }


    @Override
    public void onBind(ViewHolder holder, final QaListBean.QasBean item, final int position) {
        holder.setText(R.id.tvtitle, item.getTitle());
        holder.setText(R.id.tv_extra, item.getTitle());
        holder.setText(R.id.tvtype, item.getType());
        holder.setText(R.id.tv_zt, "");
        holder.setText(R.id.tvtotal, ""+item.getCommentNum());

        holder.getView(R.id.mLM).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TwDetailActivity.lunch((Activity)mContext,item.getId());
            }
        });

    }
}