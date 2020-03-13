package takjxh.android.com.taapp.activityui.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;

import de.hdodenhof.circleimageview.CircleImageView;
import takjxh.android.com.commlibrary.adapter.recycler.BaseRecyclerAdapter;
import takjxh.android.com.commlibrary.adapter.recycler.ViewHolder;
import takjxh.android.com.commlibrary.image.ImageWrapper;
import takjxh.android.com.taapp.R;
import takjxh.android.com.taapp.activityui.bean.QaAnswerListBean;

/**
 * 类名称：回答列表
 *
 * @Author: libaibing
 * @Date: 2019-10-15 15:17
 * @Description:
 **/
public class PlAdapter extends BaseRecyclerAdapter<QaAnswerListBean.QaAnswersBean> {
    private boolean isAccept;

    public PlAdapter(Context context) {
        super(context);
        putItemLayoutId(R.layout.item_recycler_pl_list);

    }

    public void setAccept(boolean accept) {
        isAccept = accept;
    }

    @Override
    public void onBind(ViewHolder holder, final QaAnswerListBean.QaAnswersBean item, final int position) {

        holder.setText(R.id.tvName, item.getName());
        holder.setText(R.id.tv_extra, item.getTime());
        holder.setText(R.id.tv_extra1, item.getContent());
        if(isAccept){
            holder.getView(R.id.tv_extra3).setVisibility(View.VISIBLE);
        }else{
            holder.getView(R.id.tv_extra3).setVisibility(View.GONE);
        }
        if (item.isIsAccept()) {
            holder.setText(R.id.tv_extra3, "已采纳");
        } else {
            holder.setText(R.id.tv_extra3, "采纳");
            holder.getView(R.id.tv_extra3).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mClickListener!=null){
                        mClickListener.onClick(item,position);
                    }
                }
            });

        }


        CircleImageView iv_icon = (CircleImageView) holder.getView(R.id.iv_avatar);
        if (TextUtils.isEmpty(item.getCover())) {
            iv_icon.setImageResource(R.mipmap.head_man_offline);
        } else {
            ImageWrapper.setImage(iv_icon, item.getCover(), R.mipmap.head_man_offline, ImageWrapper.CENTER_CROP);
        }


    }

    public interface OnPlClickListener {
        void onClick(QaAnswerListBean.QaAnswersBean item, int position);

    }

    private OnPlClickListener mClickListener;

    public void setmClickListener(OnPlClickListener mClickListener) {
        this.mClickListener = mClickListener;
    }



}
