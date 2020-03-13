package takjxh.android.com.taapp.activityui.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import takjxh.android.com.commlibrary.adapter.recycler.BaseRecyclerAdapter;
import takjxh.android.com.commlibrary.adapter.recycler.ViewHolder;
import takjxh.android.com.taapp.R;
import takjxh.android.com.taapp.activityui.activity.OrderDetailActivity;
import takjxh.android.com.taapp.activityui.bean.WdZxbbBean;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-10-18 14:22
 * @Description:
 **/
public class WdZxbbAdapter extends BaseRecyclerAdapter<WdZxbbBean.ApplyOrdersBean> {

    public WdZxbbAdapter(Context context) {
        super(context);
        putItemLayoutId(R.layout.item_recycler_wd_zxbb_list);

    }


    @Override
    public void onBind(ViewHolder holder, final WdZxbbBean.ApplyOrdersBean item, final int position) {

        holder.setText(R.id.tvtitle, item.getTitle());
        holder.setText(R.id.tv_extra, item.getShowPrice());
        holder.setText(R.id.tv_extra2, item.getApplyStatus());
        holder.getView(R.id.mLM).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OrderDetailActivity.startAction((Activity) mContext, item.getId());
            }
        });

    }
}

