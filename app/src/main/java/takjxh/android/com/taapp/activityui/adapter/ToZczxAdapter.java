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
import takjxh.android.com.taapp.activityui.activity.ZczxActivity;
import takjxh.android.com.taapp.activityui.bean.PolicyIndexBean;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-12 13:21
 * @Description:
 **/
public class ToZczxAdapter extends BaseRecyclerAdapter<PolicyIndexBean.PolicyTypesBean> {

    public ToZczxAdapter(Context context) {
        super(context);
        putItemLayoutId(R.layout.item_recycler_to_zczx);

    }


    @Override
    public void onBind(ViewHolder holder, final PolicyIndexBean.PolicyTypesBean item, final int position) {

        if (position % 6 == 0) {
            holder.getView(R.id.ml1).setBackgroundResource(R.color.zc1);
            // ((ImageView)holder.getView(R.id.icon)).setImageResource(R.mipmap.jzj);
        } else if (position % 6 == 1) {
            holder.getView(R.id.ml1).setBackgroundResource(R.color.zc2);
            //((ImageView)holder.getView(R.id.icon)).setImageResource(R.mipmap.jcb);
        } else if (position % 6 == 2) {
            holder.getView(R.id.ml1).setBackgroundResource(R.color.zc3);
            // ((ImageView)holder.getView(R.id.icon)).setImageResource(R.mipmap.jsp);
        } else if (position % 6 == 3) {
            holder.getView(R.id.ml1).setBackgroundResource(R.color.zc4);
            //  ((ImageView)holder.getView(R.id.icon)).setImageResource(R.mipmap.hzy);
        } else if (position % 6 == 4) {
            holder.getView(R.id.ml1).setBackgroundResource(R.drawable.login_btn_shape_pressed);
            //  ((ImageView)holder.getView(R.id.icon)).setImageResource(R.mipmap.nw);
        } else if (position % 6 == 5) {
            holder.getView(R.id.ml1).setBackgroundResource(R.drawable.main_tiime_shape2);
            // ((ImageView)holder.getView(R.id.icon)).setImageResource(R.mipmap.zy);
        }
        holder.setText(R.id.tvName, item.getName());

        ImageView iv_icon = (ImageView) holder.getView(R.id.icon);
        if (TextUtils.isEmpty(item.getIco())) {
            iv_icon.setImageResource(R.mipmap.nw);
        } else {
            ImageWrapper.setImage(iv_icon, item.getIco(), R.mipmap.nw, ImageWrapper.CENTER_CROP);
        }
        holder.getView(R.id.ml1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ZczxActivity.startAction((Activity) mContext, "0", item.getId());

            }
        });

    }
}
