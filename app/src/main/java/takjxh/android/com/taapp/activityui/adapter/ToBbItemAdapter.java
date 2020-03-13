package takjxh.android.com.taapp.activityui.adapter;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import takjxh.android.com.commlibrary.adapter.recycler.BaseRecyclerAdapter;
import takjxh.android.com.commlibrary.adapter.recycler.ViewHolder;
import takjxh.android.com.commlibrary.image.ImageWrapper;
import takjxh.android.com.taapp.R;
import takjxh.android.com.taapp.activityui.activity.DzrwtxActivity;
import takjxh.android.com.taapp.activityui.activity.DzrwtxActivity1;
import takjxh.android.com.taapp.activityui.bean.TaskListBean;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-20 11:04
 * @Description:
 **/
public class ToBbItemAdapter extends BaseRecyclerAdapter<TaskListBean.ReportTasksBean> {

    public ToBbItemAdapter(Context context) {
        super(context);
        putItemLayoutId(R.layout.item_recycler_tobb_item_list);

    }


    @Override
    public void onBind(ViewHolder holder, final TaskListBean.ReportTasksBean item, final int position) {
        holder.setText(R.id.tvtitle, item.getTitle());
        ImageView iv_icon = (ImageView) holder.getView(R.id.icon);
        if (TextUtils.isEmpty(item.getCover())) {
            iv_icon.setImageResource(R.mipmap.bb1);
        } else {
            ImageWrapper.setImage(iv_icon, item.getCover(), R.mipmap.bb1, ImageWrapper.CENTER_CROP);
        }

        holder.getView(R.id.ml1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DzrwtxActivity1.startAction((Activity) mContext,item.getId(),item.getTitle());
            }
        });

    }
}