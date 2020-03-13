package takjxh.android.com.taapp.activityui.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import takjxh.android.com.commlibrary.adapter.recycler.BaseRecyclerAdapter;
import takjxh.android.com.commlibrary.adapter.recycler.ViewHolder;
import takjxh.android.com.taapp.R;
import takjxh.android.com.taapp.activityui.bean.FeedbackListBean;

/**
 * Created by Administrator on 2020/3/1.
 */

public class FeedbackAdapter extends BaseRecyclerAdapter<FeedbackListBean.UserFeedbackVosBean> {

    private String type;

    public FeedbackAdapter(Context context) {
        super(context);
        putItemLayoutId(R.layout.item_recycler_feedback_list);

    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public void onBind(ViewHolder holder, final FeedbackListBean.UserFeedbackVosBean item, final int position) {
        holder.setText(R.id.tvtime1, item.getTime());
        holder.setText(R.id.tvtitle, item.getContent());
        holder.setText(R.id.tvtotal, item.getIsAnswer());
        holder.setText(R.id.tv_extra, item.getAnswerUser());
        holder.setText(R.id.tvtime, item.getAnswer());

    }

}