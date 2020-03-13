package takjxh.android.com.taapp.activityui.adapter;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import de.hdodenhof.circleimageview.CircleImageView;
import takjxh.android.com.commlibrary.adapter.recycler.BaseRecyclerAdapter;
import takjxh.android.com.commlibrary.adapter.recycler.ViewHolder;
import takjxh.android.com.commlibrary.image.ImageWrapper;
import takjxh.android.com.taapp.R;
import takjxh.android.com.taapp.activityui.activity.ZxwjDetailActivity;
import takjxh.android.com.taapp.activityui.bean.SurveyListBean;

/**
 * 类名称：在线问卷
 *
 * @Author: libaibing
 * @Date: 2019-10-15 15:43
 * @Description:
 **/
public class ZxwjAdapter extends BaseRecyclerAdapter<SurveyListBean.MarketSuveysBean> {

    public ZxwjAdapter(Context context) {
        super(context);
        putItemLayoutId(R.layout.item_recycler_zxwj_list);

    }


    @Override
    public void onBind(ViewHolder holder, final SurveyListBean.MarketSuveysBean item, final int position) {

        holder.setText(R.id.tvtitle, item.getCreateUser());
        holder.setText(R.id.tvtime, item.getCreateUnit());
        holder.setText(R.id.tv_extra, item.getTitle());
        holder.setText(R.id.tv_extra1, "最新参与： " + item.getLastestTime());
        holder.setText(R.id.tv_extra2, item.getJoinNum());

        CircleImageView iv_icon = (CircleImageView) holder.getView(R.id.iv_avatar);
        if (TextUtils.isEmpty(item.getUserCover())) {
            iv_icon.setImageResource(R.mipmap.head_man_offline);
        } else {
            ImageWrapper.setImage(iv_icon, item.getUserCover(), R.mipmap.head_man_offline, ImageWrapper.CENTER_CROP);
        }
        holder.getView(R.id.mLM).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  Log.e("TAG","----ID------:"+ item.getId());
                ZxwjDetailActivity.startAction((Activity) mContext, item.getId());
            }
        });

    }
}
