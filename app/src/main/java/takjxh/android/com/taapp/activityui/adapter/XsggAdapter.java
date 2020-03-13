package takjxh.android.com.taapp.activityui.adapter;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import de.hdodenhof.circleimageview.CircleImageView;
import takjxh.android.com.commlibrary.adapter.recycler.BaseRecyclerAdapter;
import takjxh.android.com.commlibrary.adapter.recycler.ViewHolder;
import takjxh.android.com.commlibrary.image.ImageWrapper;
import takjxh.android.com.taapp.R;
import takjxh.android.com.taapp.activityui.activity.XsggDetialActivity;
import takjxh.android.com.taapp.activityui.bean.AdsBean;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-10-16 15:45
 * @Description:
 **/
public class XsggAdapter extends BaseRecyclerAdapter<AdsBean.AdListBean> {

    public XsggAdapter(Context context) {
        super(context);
        putItemLayoutId(R.layout.item_recycler_xsgg_list);

    }


    @Override
    public void onBind(ViewHolder holder, final AdsBean.AdListBean item, final int position) {
        holder.setText(R.id.tvtitle, item.getTitle());
        holder.setText(R.id.tv_extra, item.getViewNum()+"人浏览过");
        holder.setText(R.id.tv_name, item.getCreateUser());

        ImageView iv_icon = (ImageView) holder.getView(R.id.icon);
        if (TextUtils.isEmpty(item.getCover())) {
            iv_icon.setImageResource(R.drawable.pic_defalt);
        } else {
            ImageWrapper.setImage(iv_icon,  item.getCover(), R.drawable.pic_defalt, ImageWrapper.CENTER_CROP);
        }

        CircleImageView iv_avatar = (CircleImageView) holder.getView(R.id.iv_avatar);
        if (TextUtils.isEmpty(item.getUserCover())) {
            iv_avatar.setImageResource(R.mipmap.head_man_offline);
        } else {
            ImageWrapper.setImage(iv_avatar,  item.getUserCover(), R.mipmap.head_man_offline, ImageWrapper.CENTER_CROP);
        }


        holder.getView(R.id.mLM).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                XsggDetialActivity.lunch((Activity) mContext, item.getId(), item.getTitle());
            }
        });

    }
}
