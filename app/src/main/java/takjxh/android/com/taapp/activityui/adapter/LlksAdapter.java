package takjxh.android.com.taapp.activityui.adapter;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;

import takjxh.android.com.commlibrary.adapter.recycler.BaseRecyclerAdapter;
import takjxh.android.com.commlibrary.adapter.recycler.ViewHolder;
import takjxh.android.com.taapp.R;
import takjxh.android.com.taapp.activityui.activity.KsnrActivity;
import takjxh.android.com.taapp.activityui.bean.ExamIndexBean;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-10-17 10:37
 * @Description:
 **/
public class LlksAdapter extends BaseRecyclerAdapter<ExamIndexBean.TypesBean> {

    public LlksAdapter(Context context) {
        super(context);
        putItemLayoutId(R.layout.item_recycler_llks);

    }

    @Override
    public void onBind(ViewHolder holder, final ExamIndexBean.TypesBean item, final int position) {

        holder.setText(R.id.tvtitle, item.getValue());
        holder.setText(R.id.tvName, item.getDes());
        if(!TextUtils.isEmpty(item.getValue())){
            holder.setText(R.id.tv_zt, item.getValue().substring(0,1));
        }


        holder.getView(R.id.mLM).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KsnrActivity.startAction((Activity) mContext,item.getCode());
            }
        });

    }
}