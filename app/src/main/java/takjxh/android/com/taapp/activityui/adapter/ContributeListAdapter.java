package takjxh.android.com.taapp.activityui.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import takjxh.android.com.commlibrary.adapter.recycler.BaseRecyclerAdapter;
import takjxh.android.com.commlibrary.adapter.recycler.ViewHolder;
import takjxh.android.com.taapp.R;
import takjxh.android.com.taapp.activityui.activity.ContributeDetialActivity;
import takjxh.android.com.taapp.activityui.bean.ContributeDetial;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2020-05-10 22:45
 * @Description:
 **/
public class ContributeListAdapter  extends BaseRecyclerAdapter<ContributeDetial> {


    public ContributeListAdapter(Context context) {
        super(context);
        putItemLayoutId(R.layout.item_recycler_contribute_list);

    }


    @Override
    public void onBind(ViewHolder holder, final ContributeDetial item, final int position) {
        holder.setText(R.id.tvtitle1, item.getTitle());
        holder.setText(R.id.tv_sj1, item.getTime());
        holder.setText(R.id.tv_zt3, item.getStatus());


        holder.getView(R.id.mLM).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContributeDetialActivity.startAction((Activity) mContext,item.getId());
            }
        });


    }




}