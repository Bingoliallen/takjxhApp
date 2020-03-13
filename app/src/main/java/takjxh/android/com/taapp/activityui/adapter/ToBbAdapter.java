package takjxh.android.com.taapp.activityui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import takjxh.android.com.commlibrary.adapter.recycler.BaseRecyclerAdapter;
import takjxh.android.com.commlibrary.adapter.recycler.ViewHolder;
import takjxh.android.com.commlibrary.adapter.recycler.divider.GridSpacingItemDecoration;
import takjxh.android.com.commlibrary.adapter.recycler.layout_manager.CustomGridLayoutManager;
import takjxh.android.com.commlibrary.utils.ViewUtil;
import takjxh.android.com.taapp.R;
import takjxh.android.com.taapp.activityui.bean.TaskListBean;
import takjxh.android.com.taapp.activityui.bean.ToBbBean;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-20 11:00
 * @Description:
 **/
public class ToBbAdapter extends BaseRecyclerAdapter<ToBbBean> {

    public ToBbAdapter(Context context) {
        super(context);
        putItemLayoutId(R.layout.item_recycler_tobb_list);

    }


    @Override
    public void onBind(ViewHolder holder, final ToBbBean item, final int position) {

        holder.setText(R.id.tvtitle, (position + 1) + "." + item.value);

        RecyclerView recycler_view = (RecyclerView) holder.getView(R.id.recycler_view);
        ToBbItemAdapter mToBbItemAdapter = new ToBbItemAdapter(mContext);
        CustomGridLayoutManager layoutManagerTime = new CustomGridLayoutManager(mContext, 3);
        layoutManagerTime.setScrollEnabled(true);
        recycler_view.setLayoutManager(layoutManagerTime);
        recycler_view.addItemDecoration(
                new GridSpacingItemDecoration(3, ViewUtil.dp2px(mContext, 0), ViewUtil.dp2px(mContext, 0),
                        true));
        recycler_view.setAdapter(mToBbItemAdapter);
        List<TaskListBean.ReportTasksBean> reportTasks = new ArrayList<>();
        reportTasks.clear();
        if (item.reportTasks != null) {
            reportTasks.addAll(item.reportTasks);
        }
        mToBbItemAdapter.set(reportTasks);
        recycler_view.setTag(item.value);
       /* holder.getView(R.id.mLM).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });*/

    }
}