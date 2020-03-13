package takjxh.android.com.taapp.activityui.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;

import java.util.Arrays;
import java.util.List;

import takjxh.android.com.commlibrary.adapter.recycler.BaseRecyclerAdapter;
import takjxh.android.com.commlibrary.adapter.recycler.ViewHolder;
import takjxh.android.com.taapp.R;
import takjxh.android.com.taapp.activityui.bean.XxshBean;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-10-18 16:55
 * @Description:
 **/
public class XxshAdapter  extends BaseRecyclerAdapter<XxshBean.AuditsBean> {

    public XxshAdapter(Context context) {
        super(context);
        putItemLayoutId(R.layout.item_recycler_xxsh_list);

    }


    @Override
    public void onBind(ViewHolder holder, final XxshBean.AuditsBean item, final int position) {
        holder.getView(R.id.tv_tg).setVisibility(View.GONE);
        holder.getView(R.id.tv_btg).setVisibility(View.GONE);


        holder.setText(R.id.tv_sj, "注册时间："+item.getTime());
        holder.setText(R.id.tv_zt, item.getShowStatus());

        holder.setText(R.id.tvtitle, item.getTitle());
        holder.setText(R.id.tv_extra, "");
        holder.setText(R.id.tv_extra1, "");
        holder.getView(R.id.tv_extra1).setVisibility(View.GONE);
        holder.setText(R.id.tv_extra2, "");


        if(!TextUtils.isEmpty(item.getDes())){
            String desc=item.getDes().replace("\r\n","");
            List<String> list= Arrays.asList(desc.split("<br/>"));
            if(list!=null &&list.size()>0){
                if(list.size()<=1){
                    holder.setText(R.id.tv_extra2, list.get(0));
                }else  {
                    holder.setText(R.id.tv_extra2, list.get(list.size()-1));
                    String msg="";
                    for(int i=0;i<list.size()-1;i++){
                        msg=msg+list.get(i)+"\n";
                    }
                    holder.setText(R.id.tv_extra, msg);
                }
            }
        }


        if ("01".equals(item.getAuditStatus())) {
            //待审核
            holder.getView(R.id.tv_tg).setVisibility(View.VISIBLE);
            holder.getView(R.id.tv_btg).setVisibility(View.VISIBLE);
        } else if ("02".equals(item.getAuditStatus())) {

        }else if ("03".equals(item.getAuditStatus())) {

        }

       /* ImageView iv_avatar=(ImageView) holder.getView(R.id.iv_avatar);
        if (TextUtils.isEmpty(item.get())) {
            iv_avatar.setImageResource(R.mipmap.g_d);
        } else {
            ImageWrapper.setImage(iv_avatar, item.getDesc(), R.mipmap.g_d, ImageWrapper.CENTER_CROP);
        }*/

        holder.getView(R.id.tv_tg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (listener != null) {
                    listener.onClick(item,  position,  "02");
                }
            }
        });
        holder.getView(R.id.tv_btg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClick(item,  position,  "03");
                }
            }
        });
    }


    public interface OnXxshCommitClickListener {
        void onClick(XxshBean.AuditsBean item, int position, String auditStatus);
    }

    private OnXxshCommitClickListener listener;

    public void setListener(OnXxshCommitClickListener listener) {
        this.listener = listener;
    }
}
