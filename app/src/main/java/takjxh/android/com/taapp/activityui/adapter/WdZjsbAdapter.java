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
        putItemLayoutId(R.layout.item_recycler_wd_zjsb_list);

    }


    @Override
    public void onBind(ViewHolder holder, final PolicyApplyBean.ApplyInfosBean item, final int position) {
        if (channelid.equals("02")) {
            holder.getView(R.id.mL1).setVisibility(View.VISIBLE);
            holder.getView(R.id.mView1).setVisibility(View.VISIBLE);
            holder.getView(R.id.mL2).setVisibility(View.VISIBLE);
            holder.getView(R.id.mL3).setVisibility(View.GONE);

            holder.getView(R.id.ratingBar1).setVisibility(View.GONE);
            holder.getView(R.id.tv_pj).setVisibility(View.GONE);


            holder.setText(R.id.tv_sj, "申请时间：" + item.getTime());
            holder.setText(R.id.tv_zt, item.getShowStatus());
            holder.setText(R.id.tvtitle, item.getTitle());

            holder.setText(R.id.tv_extra, "");
            holder.setText(R.id.tv_extra1, "");
            holder.getView(R.id.tv_extra1).setVisibility(View.GONE);
            holder.setText(R.id.tv_extra2, "");
            if (!TextUtils.isEmpty(item.getDesc())) {
                String desc = item.getDesc().replace("\r\n", "");
                List<String> list = Arrays.asList(desc.split("<br/>"));
                if (list != null && list.size() > 0) {
                    if (list.size() <= 1) {
                        holder.setText(R.id.tv_extra2, list.get(0));
                    } else {
                        holder.setText(R.id.tv_extra2, list.get(list.size() - 1));
                        String msg = "";
                        for (int i = 0; i < list.size() - 1; i++) {
                            msg = msg + list.get(i) + "\n";
                        }
                        holder.setText(R.id.tv_extra, msg);
                    }
                }
            }

            /* if (item.type == 3) {
            holder.setText(R.id.tv_zt, "求助中");
            holder.getView(R.id.tv_pj).setVisibility(View.VISIBLE);
            } else if (item.type == 4) {
            holder.setText(R.id.tv_zt, "已完成");
            holder.getView(R.id.ratingBar1).setVisibility(View.VISIBLE);
             }
            holder.getView(R.id.tv_pj).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PjActivity.startAction((Activity) mContext);
            }
            });*/

        } else {
            holder.getView(R.id.mL1).setVisibility(View.GONE);
            holder.getView(R.id.mView1).setVisibility(View.GONE);
            holder.getView(R.id.mL2).setVisibility(View.GONE);
            holder.getView(R.id.mL3).setVisibility(View.VISIBLE);

            holder.setText(R.id.tvtitle1, item.getTitle());
            holder.setText(R.id.tv_sj1, item.getTime());

            if ("03".equals(item.getStatus())) {
                holder.setText(R.id.tv_des1, item.getCheckDes());
                holder.getView(R.id.tv_des1).setVisibility(View.VISIBLE);
            } else if ("02".equals(item.getStatus())) {
                holder.setText(R.id.tv_des1, "申请成功后，请打印《申请表》，并进申请详情页面下载打印含水印附件");
                holder.getView(R.id.tv_des1).setVisibility(View.VISIBLE);
            } else {
                holder.getView(R.id.tv_des1).setVisibility(View.INVISIBLE);
            }

            holder.setText(R.id.tv_zt3, item.getShowStatus());


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

        }


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