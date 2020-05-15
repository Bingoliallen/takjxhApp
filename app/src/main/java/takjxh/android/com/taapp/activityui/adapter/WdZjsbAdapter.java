package takjxh.android.com.taapp.activityui.adapter;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import takjxh.android.com.commlibrary.adapter.recycler.BaseRecyclerAdapter;
import takjxh.android.com.commlibrary.adapter.recycler.ViewHolder;
import takjxh.android.com.taapp.R;
import takjxh.android.com.taapp.activityui.activity.ZjsbtxDetailActivity;
import takjxh.android.com.taapp.activityui.activity.ZjsbtxDetailActivity1;
import takjxh.android.com.taapp.activityui.activity.ZjsbtxUpdateActivity1;
import takjxh.android.com.taapp.activityui.bean.PolicyApplyBean;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-10-16 11:12
 * @Description:
 **/
public class WdZjsbAdapter extends BaseRecyclerAdapter<PolicyApplyBean.ApplyInfosBean> {

    String channelid;

    public WdZjsbAdapter(Context context, String channelid) {
        super(context);
        this.channelid = channelid;
        putItemLayoutId(R.layout.item_recycler_wd_zjsb_list1);

    }


    @Override
    public void onBind(ViewHolder holder, final PolicyApplyBean.ApplyInfosBean item, final int position) {

        holder.getView(R.id.ratingBar1).setVisibility(View.GONE);
        holder.getView(R.id.tv_qxg0).setVisibility(View.GONE);
        holder.getView(R.id.tv_qxg2).setVisibility(View.GONE);
        holder.getView(R.id.tv_qxg4).setVisibility(View.GONE);

        holder.setText(R.id.tv_sj, "申请时间：" + item.getTime());
        holder.setText(R.id.tv_zt, item.getShowStatus());
        holder.setText(R.id.tvtitle, item.getTitle());

        if ("03".equals(item.getStatus())) {
            holder.setText(R.id.tv_extra1, item.getCheckDes());
            holder.getView(R.id.tv_extra1).setVisibility(View.VISIBLE);
        } else if ("02".equals(item.getStatus())) {
            holder.setText(R.id.tv_extra1, "申请成功后，请打印《申请表》，并进申请详情页面下载打印含水印附件");
            holder.getView(R.id.tv_extra1).setVisibility(View.VISIBLE);
        } else {
            holder.setText(R.id.tv_extra1, "申请时间：" );
            holder.getView(R.id.tv_extra1).setVisibility(View.INVISIBLE);
        }



        if ("02".equals(item.getStatus()) && item.isOpenDown()) {
            holder.getView(R.id.tv_qxg0).setVisibility(View.VISIBLE);
            holder.getView(R.id.tv_qxg2).setVisibility(View.VISIBLE);
        } else {
            holder.getView(R.id.tv_qxg0).setVisibility(View.GONE);
            holder.getView(R.id.tv_qxg2).setVisibility(View.GONE);
        }

        if ("00".equals(item.getStatus())) {
            holder.getView(R.id.tv_qxg4).setVisibility(View.VISIBLE);
        } else {
            holder.getView(R.id.tv_qxg4).setVisibility(View.GONE);
        }

        holder.getView(R.id.tv_qxg0).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mClickListener!=null){
                    mClickListener.onClick1(item.getId());
                }

            }
        });

        holder.getView(R.id.tv_qxg2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mClickListener!=null){
                    mClickListener.onClick2(item.getId());
                }
            }
        });

        holder.getView(R.id.tv_qxg4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ZjsbtxUpdateActivity1.startAction((Activity) mContext,item.getId());
            }
        });



        holder.getView(R.id.mLM).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ZjsbtxDetailActivity1.startAction((Activity) mContext, item.getId());
            }
        });


    }

    public interface OnWdZjsbClickListener {
        void onClick1(String applyId);
        void onClick2(String applyId);
    }

    private OnWdZjsbClickListener mClickListener;

    public void setmClickListener(OnWdZjsbClickListener mClickListener) {
        this.mClickListener = mClickListener;
    }


}