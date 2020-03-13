package takjxh.android.com.taapp.activityui.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import takjxh.android.com.commlibrary.adapter.recycler.BaseRecyclerAdapter;
import takjxh.android.com.commlibrary.adapter.recycler.ViewHolder;
import takjxh.android.com.commlibrary.image.ImageWrapper;
import takjxh.android.com.taapp.R;
import takjxh.android.com.taapp.activityui.bean.CompanysBean;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-10-16 16:15
 * @Description:
 **/
public class DtdsAdapter extends BaseRecyclerAdapter<CompanysBean.CompanyBean> {

    public DtdsAdapter(Context context) {
        super(context);
        putItemLayoutId(R.layout.item_recycler_dtzs_list);

    }

    @Override
    public void onBind(ViewHolder holder, final CompanysBean.CompanyBean item, final int position) {
        if(item.isSelect){
            ((TextView) holder.getView(R.id.tvtitle)).setTextColor(mContext.getResources().getColor(R.color.dtzs));
            ((TextView) holder.getView(R.id.tv_extra)).setTextColor(mContext.getResources().getColor(R.color.dtzs));
            ((TextView) holder.getView(R.id.tvtime)).setTextColor(mContext.getResources().getColor(R.color.dtzs));
        }else{
            ((TextView) holder.getView(R.id.tvtitle)).setTextColor(mContext.getResources().getColor(R.color.text_color));
            ((TextView) holder.getView(R.id.tv_extra)).setTextColor(mContext.getResources().getColor(R.color.text_color));
            ((TextView) holder.getView(R.id.tvtime)).setTextColor(mContext.getResources().getColor(R.color.text_color));

        }
        holder.setText(R.id.tvtitle,"厦门 "+item.getArea());
        holder.setText(R.id.tv_extra,item.getName());
        holder.setText(R.id.tvtime,item.getAddress());

        ImageView iv_icon = (ImageView) holder.getView(R.id.icon);
        if (TextUtils.isEmpty(item.getCover())) {
            iv_icon.setImageResource(R.drawable.pic_defalt);
        } else {
            ImageWrapper.setImage(iv_icon,  item.getCover(), R.drawable.pic_defalt, ImageWrapper.CENTER_CROP);
        }

       /* holder.getView(R.id.mLM).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });*/

    }
}