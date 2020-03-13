package takjxh.android.com.taapp.activityui.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import com.mcxtzhang.swipemenulib.SwipeMenuLayout;

import takjxh.android.com.commlibrary.adapter.recycler.BaseRecyclerAdapter;
import takjxh.android.com.commlibrary.adapter.recycler.ViewHolder;
import takjxh.android.com.taapp.R;
import takjxh.android.com.taapp.activityui.activity.UserExtsEditAcitivty;
import takjxh.android.com.taapp.activityui.base.UserExtsListBean;

/**
 * Created by Administrator on 2020/3/7.
 */

public class UserExtsListAdapter extends BaseRecyclerAdapter<UserExtsListBean.UserExtListVosBean> {


    public UserExtsListAdapter(Context context) {
        super(context);
        putItemLayoutId(R.layout.item_recycler_userext_list);

    }


    @Override
    public void onBind(ViewHolder holder, final UserExtsListBean.UserExtListVosBean item, final int position) {
        holder.setText(R.id.title, item.getUnitName());
        holder.setText(R.id.tvtitle1, item.getShowType());

        if (item.getIsDefaultType()) {
            holder.getView(R.id.tvtitle2).setVisibility(View.VISIBLE);
        } else {
            holder.getView(R.id.tvtitle2).setVisibility(View.GONE);
        }
        holder.setText(R.id.tv_zt, item.getShowStatus());
        holder.setText(R.id.tv_extra, "单位代码：" + item.getUnitCode());
        holder.setText(R.id.tvtime1, item.getLinkmanPhone());

        holder.getView(R.id.iv_avatar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  UserExtsEditAcitivty.startAction((Activity) mContext,
                          item.getId(), item.getType());
            }
        });
        holder.getView(R.id.setButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((SwipeMenuLayout) holder.itemView).quickClose();
                if (mClickListener != null) {
                    mClickListener.setClick(item.getId(), position);
                }
            }
        });
        holder.getView(R.id.delButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((SwipeMenuLayout) holder.itemView).quickClose();
                if (mClickListener != null) {
                    mClickListener.delClick(item.getId(), position);
                }
            }
        });


    }

    public interface OnUserExtClickListener {
        void setClick(String mId, int position);

        void delClick(String mId, int position);
    }

    private OnUserExtClickListener mClickListener;

    public void setmClickListener(OnUserExtClickListener mClickListener) {
        this.mClickListener = mClickListener;
    }


}