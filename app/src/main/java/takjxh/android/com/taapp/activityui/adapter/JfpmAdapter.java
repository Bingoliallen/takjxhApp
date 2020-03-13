package takjxh.android.com.taapp.activityui.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;
import takjxh.android.com.commlibrary.adapter.recycler.BaseRecyclerAdapter;
import takjxh.android.com.commlibrary.adapter.recycler.ViewHolder;
import takjxh.android.com.commlibrary.image.ImageWrapper;
import takjxh.android.com.taapp.R;
import takjxh.android.com.taapp.activityui.bean.JfpmBean;

/**
 * Created by Administrator on 2019/10/16.
 */

public class JfpmAdapter extends BaseRecyclerAdapter<JfpmBean.UserScoresBean> {

    public JfpmAdapter(Context context) {
        super(context);
        putItemLayoutId(R.layout.item_recycler_jfpm_list);

    }

    @Override
    public void onBind(ViewHolder holder, final JfpmBean.UserScoresBean item, final int position) {
        TextView tv_num = (TextView) holder.getView(R.id.tv_num);
        if (position == 0) {
            tv_num.setTextColor(mContext.getResources().getColor(R.color.red));
        } else if (position == 1) {
            tv_num.setTextColor(mContext.getResources().getColor(R.color.deeppink));
        } else if (position == 2) {
            tv_num.setTextColor(mContext.getResources().getColor(R.color.violet));
        } else if (position == 3) {
            tv_num.setTextColor(mContext.getResources().getColor(R.color.indianred));
        } else if (position == 4) {
            tv_num.setTextColor(mContext.getResources().getColor(R.color.greenyellow));
        } else if (position == 5) {
            tv_num.setTextColor(mContext.getResources().getColor(R.color.palegreen));
        } else if (position == 6) {
            tv_num.setTextColor(mContext.getResources().getColor(R.color.gold));
        } else if (position == 7) {
            tv_num.setTextColor(mContext.getResources().getColor(R.color.orange));
        } else if (position == 8) {
            tv_num.setTextColor(mContext.getResources().getColor(R.color.lightsalmon));
        } else if (position == 9) {
            tv_num.setTextColor(mContext.getResources().getColor(R.color.dodgerblue));
        }else{
            tv_num.setTextColor(mContext.getResources().getColor(R.color.paleturquoise));
        }
        tv_num.setText("" + (position + 1));
        holder.setText(R.id.tv_name, item.getName());
        holder.setText(R.id.tv_extra, "总积分" + item.getNewScore());

        CircleImageView iv_icon = (CircleImageView) holder.getView(R.id.iv_avatar);
        if (TextUtils.isEmpty(item.getCover())) {
            iv_icon.setImageResource(R.mipmap.head_man_offline);
        } else {
            ImageWrapper.setImage(iv_icon, item.getCover(), R.mipmap.head_man_offline, ImageWrapper.CENTER_CROP);
        }

       /* holder.getView(R.id.mLM).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });*/

    }
}