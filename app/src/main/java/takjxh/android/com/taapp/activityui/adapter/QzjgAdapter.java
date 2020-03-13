package takjxh.android.com.taapp.activityui.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import takjxh.android.com.commlibrary.adapter.recycler.BaseRecyclerAdapter;
import takjxh.android.com.commlibrary.adapter.recycler.ViewHolder;
import takjxh.android.com.commlibrary.image.ImageWrapper;
import takjxh.android.com.taapp.R;
import takjxh.android.com.taapp.activityui.bean.PolicyApplyOrgansBean;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-10-24 13:51
 * @Description:
 **/
public class QzjgAdapter extends BaseRecyclerAdapter<PolicyApplyOrgansBean.OrganListBean> {

    public QzjgAdapter(Context context) {
        super(context);
        putItemLayoutId(R.layout.item_recycler_qzjg_list);

    }


    @Override
    public void onBind(ViewHolder holder, final PolicyApplyOrgansBean.OrganListBean item, final int position) {
      /* 以江管理咨询，是一家由全球合伙人联合所有和运营的有限合伙企业，由来自中国、美国、英国等地的团队为客户提供咨询服务。
        电话：021-5629 7756
        E-mail: hrd@yijiangglobal.com
        地址：上海市长宁区虹桥路2535号永融国际中心8楼*/

        holder.setText(R.id.tv_sj, item.getName());
        holder.setText(R.id.tv_zt, item.getWorkTime());
        String msg= item.getDes()+"\n"
                +"联系人："+item.getLinkman()+"\n"
                +"电话："+item.getLinkmanPhone()+"\n"
                +"E-mail:"+item.getEmail()+"\n"
                +"地址："+item.getAddress()+"\n";
        holder.setText(R.id.tvcontent, msg);


        ImageView iv_icon = (ImageView) holder.getView(R.id.iv_avatar);
        if (TextUtils.isEmpty(item.getCover())) {
            iv_icon.setImageResource(R.drawable.pic_defalt);
        } else {
            ImageWrapper.setImage(iv_icon, item.getCover(), R.drawable.pic_defalt, ImageWrapper.CENTER_CROP);

        }


        holder.getView(R.id.tv_pj).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //请求求助接口即可
                if (listener != null) {
                    listener.onClick(item,position);
                }

            }
        });

    }

    public interface OnCommitClickListener {
        void onClick(PolicyApplyOrgansBean.OrganListBean item, int position);
    }

    private OnCommitClickListener listener;

    public void setOnClickListener(OnCommitClickListener listener) {
        this.listener = listener;
    }

}

