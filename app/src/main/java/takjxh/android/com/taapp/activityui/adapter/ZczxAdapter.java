package takjxh.android.com.taapp.activityui.adapter;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import java.util.Arrays;
import java.util.List;

import takjxh.android.com.commlibrary.adapter.recycler.BaseRecyclerAdapter;
import takjxh.android.com.commlibrary.adapter.recycler.ViewHolder;
import takjxh.android.com.commlibrary.image.ImageWrapper;
import takjxh.android.com.commlibrary.utils.ToastUtil;
import takjxh.android.com.taapp.R;
import takjxh.android.com.taapp.activityui.activity.ZczxDetailActivity;
import takjxh.android.com.taapp.activityui.bean.PolicysBean;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-10-16 9:26
 * @Description:
 **/
public class ZczxAdapter  extends BaseRecyclerAdapter<PolicysBean.PolicyInfosBean> {

    public ZczxAdapter(Context context) {
        super(context);
        putItemLayoutId(R.layout.item_recycler_zczx_list);

    }

    @Override
    public void onBind(ViewHolder holder, final PolicysBean.PolicyInfosBean item, final int position) {

       /* if(item.type==0){
            holder.getView(R.id.iv1).setVisibility(View.VISIBLE);
            holder.getView(R.id.mL2).setVisibility(View.GONE);
            holder.getView(R.id.iv5).setVisibility(View.GONE);
        }else if(item.type==1){
            holder.getView(R.id.iv1).setVisibility(View.GONE);
            holder.getView(R.id.mL2).setVisibility(View.GONE);
            holder.getView(R.id.iv5).setVisibility(View.VISIBLE);
        }else if(item.type==2){
            holder.getView(R.id.iv1).setVisibility(View.GONE);
            holder.getView(R.id.mL2).setVisibility(View.VISIBLE);
            holder.getView(R.id.iv5).setVisibility(View.GONE);
        }else if(item.type==3){
            holder.getView(R.id.iv1).setVisibility(View.GONE);
            holder.getView(R.id.mL2).setVisibility(View.GONE);
            holder.getView(R.id.iv5).setVisibility(View.GONE);
        }else if(item.type==4){
            holder.getView(R.id.iv1).setVisibility(View.GONE);
            holder.getView(R.id.mL2).setVisibility(View.VISIBLE);
            holder.getView(R.id.iv5).setVisibility(View.GONE);

            holder.getView(R.id.iv2).setVisibility(View.VISIBLE);
            holder.getView(R.id.iv3).setVisibility(View.VISIBLE);
            holder.getView(R.id.iv4).setVisibility(View.GONE);
        }*/
        holder.setText(R.id.tvtitle,item.getTitle());
        holder.setText(R.id.tv_zt,item.getApplyStatus());
        holder.setText(R.id.tv_extra,item.getApplyDay());
        holder.setText(R.id.tv_extra1,"");
        holder.setText(R.id.tv_extra2,"");
        ImageView iv_icon = (ImageView) holder.getView(R.id.iv5);
        iv_icon.setVisibility(View.VISIBLE);
        if (TextUtils.isEmpty(item.getCover())) {
            iv_icon.setImageResource(R.drawable.pic_defalt);
        } else {
            List<String> list= Arrays.asList(item.getCover().split(","));
            if(list.size()>0){
                if(list.size()==1){
                    ImageWrapper.setImage(iv_icon,  item.getCover(), R.drawable.pic_defalt, ImageWrapper.CENTER_CROP);
                }else{
                    iv_icon.setVisibility(View.GONE);
                    holder.getView(R.id.mL2).setVisibility(View.VISIBLE);
                    ImageView iv2 = (ImageView) holder.getView(R.id.iv2);
                    ImageView iv3 = (ImageView) holder.getView(R.id.iv3);
                    ImageView iv4 = (ImageView) holder.getView(R.id.iv4);
                    if(list.size()==2){
                        iv2.setVisibility(View.VISIBLE);
                        iv3.setVisibility(View.VISIBLE);
                        iv4.setVisibility(View.INVISIBLE);
                        ImageWrapper.setImage(iv2, list.get(0).toString(), R.drawable.pic_defalt, ImageWrapper.CENTER_CROP);
                        ImageWrapper.setImage(iv3, list.get(1).toString(), R.drawable.pic_defalt, ImageWrapper.CENTER_CROP);
                    }else  if(list.size()==3){
                        iv2.setVisibility(View.VISIBLE);
                        iv3.setVisibility(View.VISIBLE);
                        iv4.setVisibility(View.VISIBLE);
                        ImageWrapper.setImage(iv2, list.get(0).toString(), R.drawable.pic_defalt, ImageWrapper.CENTER_CROP);
                        ImageWrapper.setImage(iv3, list.get(1).toString(), R.drawable.pic_defalt, ImageWrapper.CENTER_CROP);
                        ImageWrapper.setImage(iv4, list.get(2).toString(), R.drawable.pic_defalt, ImageWrapper.CENTER_CROP);
                    }
                }
            }else {
                iv_icon.setImageResource(R.drawable.pic_defalt);
            }

        }

        holder.getView(R.id.mLM).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if("00".equals(item.getStatus())){
                    ToastUtil.showToast(mContext,"申请未开始");
                }else if("02".equals(item.getStatus())){
                    ToastUtil.showToast(mContext,"申请已结束");
                }else{
                    ZczxDetailActivity.startAction((Activity) mContext, item.getId(), item.getTitle());
                }

            }
        });




    }
}
