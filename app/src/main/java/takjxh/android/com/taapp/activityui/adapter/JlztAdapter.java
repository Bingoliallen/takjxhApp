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
import takjxh.android.com.taapp.activityui.activity.JlztDetailActivity;
import takjxh.android.com.taapp.activityui.bean.CommListBean;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-10-18 10:06
 * @Description:
 **/
public class JlztAdapter extends BaseRecyclerAdapter<CommListBean.CommTopicsBean> {

    public JlztAdapter(Context context) {
        super(context);
        putItemLayoutId(R.layout.item_recycler_jlzt);

    }


    @Override
    public void onBind(ViewHolder holder, final CommListBean.CommTopicsBean item, final int position) {
        holder.setText(R.id.tvtype, "#"+item.getType()+"#");
        holder.setText(R.id.tvtitle, item.getTitle());
        holder.setText(R.id.tv_extra, item.getCommentNum()+"条评论 - "+item.getViewNum()+"条阅读量");



        ImageView iv_icon = (ImageView) holder.getView(R.id.iv5);
        ImageView ivKS5 = (ImageView) holder.getView(R.id.ivKS5);


        if(TextUtils.isEmpty(item.getVideo())){
            ivKS5.setVisibility(View.INVISIBLE);
        }else{
            ivKS5.setVisibility(View.VISIBLE);
        }


        if (TextUtils.isEmpty(item.getCover())) {
            iv_icon.setImageResource(R.drawable.pic_defalt);
        } else {
            ImageWrapper.setImage(iv_icon,  item.getCover(), R.drawable.pic_defalt, ImageWrapper.CENTER_CROP);
        }
        holder.getView(R.id.mLM).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               JlztDetailActivity.startAction((Activity) mContext,item.getId());
            }
        });

    }
}
