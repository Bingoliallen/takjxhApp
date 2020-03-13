package takjxh.android.com.taapp.activityui.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import takjxh.android.com.commlibrary.adapter.recycler.BaseRecyclerAdapter;
import takjxh.android.com.commlibrary.adapter.recycler.ViewHolder;
import takjxh.android.com.commlibrary.image.ImageWrapper;
import takjxh.android.com.taapp.R;
import takjxh.android.com.taapp.activityui.bean.MessagesBean;

/**
 * Created by Administrator on 2019/10/17.
 */

public class XxzxAdapter extends BaseRecyclerAdapter<MessagesBean.SysMessagesBean> {

    public XxzxAdapter(Context context) {
        super(context);
        putItemLayoutId(R.layout.item_recycler_xxzx_list);

    }

    @Override
    public void onBind(ViewHolder holder, final MessagesBean.SysMessagesBean item, final int position) {


        ImageView iv_icon = (ImageView) holder.getView(R.id.iv_avatar);
        if (TextUtils.isEmpty(item.getCover())) {
            iv_icon.setImageResource(R.drawable.pic_defalt);
        } else {
            ImageWrapper.setImage(iv_icon, item.getCover(), R.drawable.pic_defalt, ImageWrapper.CENTER_CROP);
        }

        holder.setText(R.id.tvtitle, item.getShowSourceType());
        holder.setText(R.id.tv_extra, item.getTime());
        holder.setText(R.id.tv_extra1, item.getDes());
        holder.getView(R.id.mLM).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

    }
}