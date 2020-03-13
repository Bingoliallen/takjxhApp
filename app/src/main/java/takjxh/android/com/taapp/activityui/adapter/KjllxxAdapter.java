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
import takjxh.android.com.commlibrary.utils.DateTimeUtil;
import takjxh.android.com.taapp.R;
import takjxh.android.com.taapp.activityui.activity.XxspActivity;
import takjxh.android.com.taapp.activityui.bean.StudysBean;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-10-16 17:01
 * @Description:
 **/
public class KjllxxAdapter  extends BaseRecyclerAdapter<StudysBean.StudyListBean> {

    public KjllxxAdapter(Context context) {
        super(context);
        putItemLayoutId(R.layout.item_recycler_kjllxx);

    }


    @Override
    public void onBind(ViewHolder holder, final StudysBean.StudyListBean item, final int position) {
        holder.setText(R.id.tvtitle, item.getTitle());
        if(TextUtils.isEmpty(item.getTime())){
            holder.setText(R.id.tv_extra, "作者："+item.getCreateUser()+" - "+ item.getTime());

        }else{
            holder.setText(R.id.tv_extra, "作者："+item.getCreateUser()+" - "+ DateTimeUtil.formatFriendly(DateTimeUtil.parseDate(item.getTime(), DateTimeUtil.DF_YYYY_MM_DD_HH_MM_SS)));
        }

        ImageView iv_icon = (ImageView) holder.getView(R.id.iv1);

        ImageView ivKS = (ImageView) holder.getView(R.id.ivKS);
        ImageView ivKS5 = (ImageView) holder.getView(R.id.ivKS5);


        if(TextUtils.isEmpty(item.getVideo())){
            ivKS.setVisibility(View.INVISIBLE);
            ivKS5.setVisibility(View.INVISIBLE);
        }else{
            ivKS.setVisibility(View.VISIBLE);
            ivKS5.setVisibility(View.VISIBLE);
        }

        holder.getView(R.id.mR1).setVisibility(View.VISIBLE);
        if (TextUtils.isEmpty(item.getCover())) {
            iv_icon.setImageResource(R.drawable.pic_defalt);
        } else {
            List<String> list= Arrays.asList(item.getCover().split(","));
            if(list.size()>0){
                if(list.size()==1){
                    ImageWrapper.setImage(iv_icon,  item.getCover(), R.drawable.pic_defalt, ImageWrapper.CENTER_CROP);
                }else {
                    holder.getView(R.id.mR1).setVisibility(View.GONE);
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
                XxspActivity.lunch((Activity) mContext, item.getId(), item.getVideo(),item.getTitle(),"1");
            }
        });

    }
}