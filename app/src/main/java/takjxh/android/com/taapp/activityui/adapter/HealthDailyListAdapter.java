package takjxh.android.com.taapp.activityui.adapter;

import android.content.Context;

import takjxh.android.com.commlibrary.adapter.recycler.BaseRecyclerAdapter;
import takjxh.android.com.commlibrary.adapter.recycler.ViewHolder;
import takjxh.android.com.taapp.R;
import takjxh.android.com.taapp.activityui.bean.HealthListBean;

/**
 * Created by Administrator on 2020/3/10.
 */

public class HealthDailyListAdapter  extends BaseRecyclerAdapter<HealthListBean.HealthListVosBean> {


    public HealthDailyListAdapter(Context context) {
        super(context);
        putItemLayoutId(R.layout.item_recycler_healthdaily_list);

    }


    @Override
    public void onBind(ViewHolder holder, final HealthListBean.HealthListVosBean item, final int position) {
        holder.setText(R.id.tvtitle, item.getTime());
        holder.setText(R.id.tvtitle2, item.getTemperature());
        holder.setText(R.id.tvtime1, item.getIsFsks());
        holder.setText(R.id.tvtotal, item.getIsJcbl());

    }

}