package takjxh.android.com.taapp.activityui.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;

import de.hdodenhof.circleimageview.CircleImageView;
import takjxh.android.com.commlibrary.adapter.recycler.BaseRecyclerAdapter;
import takjxh.android.com.commlibrary.adapter.recycler.ViewHolder;
import takjxh.android.com.commlibrary.image.ImageWrapper;
import takjxh.android.com.taapp.R;
import takjxh.android.com.taapp.activityui.bean.QuestionAnswerListBean;

/**
 * 类名称：回答列表
 *
 * @Author: libaibing
 * @Date: 2019-10-28 14:47
 * @Description:
 **/
public class HdAdapter extends BaseRecyclerAdapter<QuestionAnswerListBean.CommAnswersBean> {

    public HdAdapter(Context context) {
        super(context);
        putItemLayoutId(R.layout.item_recycler_hd_list);

    }


    @Override
    public void onBind(ViewHolder holder, final QuestionAnswerListBean.CommAnswersBean item, final int position) {
        holder.getView(R.id.tv_extra3).setVisibility(View.GONE);

        holder.setText(R.id.tvName, item.getName());
        holder.setText(R.id.tv_extra, item.getTime());
        holder.setText(R.id.tv_extra1, item.getContent());


        CircleImageView iv_icon = (CircleImageView) holder.getView(R.id.iv_avatar);
        if (TextUtils.isEmpty(item.getUserCover())) {
            iv_icon.setImageResource(R.mipmap.head_man_offline);
        } else {
            ImageWrapper.setImage(iv_icon,  item.getUserCover(), R.mipmap.head_man_offline, ImageWrapper.CENTER_CROP);
        }

       /* holder.getView(R.id.mLM).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });*/

    }
}
