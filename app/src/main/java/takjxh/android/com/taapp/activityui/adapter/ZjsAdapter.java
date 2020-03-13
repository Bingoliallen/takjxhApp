package takjxh.android.com.taapp.activityui.adapter;

import android.content.Context;
import android.text.TextUtils;

import de.hdodenhof.circleimageview.CircleImageView;
import takjxh.android.com.commlibrary.adapter.recycler.BaseRecyclerAdapter;
import takjxh.android.com.commlibrary.adapter.recycler.ViewHolder;
import takjxh.android.com.commlibrary.image.ImageWrapper;
import takjxh.android.com.taapp.R;
import takjxh.android.com.taapp.activityui.bean.QaQauserListBean;

/**
 * 类名称：专家列表
 *
 * @Author: libaibing
 * @Date: 2019-10-17 15:48
 * @Description:
 **/
public class ZjsAdapter extends BaseRecyclerAdapter<QaQauserListBean.QaUsersBean> {

    public ZjsAdapter(Context context) {
        super(context);
        putItemLayoutId(R.layout.item_recycler_zjs);

    }


    @Override
    public void onBind(ViewHolder holder, final QaQauserListBean.QaUsersBean item, final int position) {

        holder.setText(R.id.tvName, item.getName());
        holder.setText(R.id.tv_extra, item.getDes());

        CircleImageView iv_icon = (CircleImageView) holder.getView(R.id.iv_avatar);
        if (TextUtils.isEmpty(item.getCover())) {
            iv_icon.setImageResource(R.mipmap.head_man_offline);
        } else {
            ImageWrapper.setImage(iv_icon, item.getCover(), R.mipmap.head_man_offline, ImageWrapper.CENTER_CROP);
        }


      /*  holder.getView(R.id.mLM).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });*/

    }
}
