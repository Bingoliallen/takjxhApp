package takjxh.android.com.taapp.activityui.adapter;

import android.content.Context;

import takjxh.android.com.commlibrary.adapter.recycler.BaseRecyclerAdapter;
import takjxh.android.com.commlibrary.adapter.recycler.ViewHolder;
import takjxh.android.com.taapp.R;
import takjxh.android.com.taapp.activityui.bean.ScoresBean;

/**
 * Created by Administrator on 2019/10/16.
 */

public class XxbbAdapter extends BaseRecyclerAdapter<ScoresBean.UserScoresBean> {

    public XxbbAdapter(Context context) {
        super(context);
        putItemLayoutId(R.layout.item_recycler_xxbb_list);

    }

    @Override
    public void onBind(ViewHolder holder, final ScoresBean.UserScoresBean item, final int position) {
        holder.setText(R.id.tvtime, item.getTime()+"  "+item.getShowDesc());
        if(item.getChangeScore()>0){
            holder.setText(R.id.tv_extra, "+"+item.getChangeScore()+"积分");
        }else{
            holder.setText(R.id.tv_extra, item.getChangeScore()+"积分");
        }


       /* holder.getView(R.id.mLM).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });*/

    }
}