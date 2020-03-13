package takjxh.android.com.taapp.activityui.adapter;

import android.content.Context;
import android.view.View;

import takjxh.android.com.commlibrary.adapter.recycler.BaseRecyclerAdapter;
import takjxh.android.com.commlibrary.adapter.recycler.ViewHolder;
import takjxh.android.com.taapp.R;
import takjxh.android.com.taapp.activityui.bean.ZjsbtxBean;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-10-16 10:14
 * @Description:
 **/
public class ZjsbtxAdapter  extends BaseRecyclerAdapter<ZjsbtxBean> {

    public ZjsbtxAdapter(Context context) {
        super(context);
        putItemLayoutId(R.layout.item_recycler_zjsbtx_list);

    }


    @Override
    public void onBind(ViewHolder holder, final ZjsbtxBean item, final int position) {

        if(item.type==0){
            holder.setText(R.id.tvtitle,position +1+".企业基本情况");
            holder.getView(R.id.mL1).setVisibility(View.VISIBLE);
            holder.getView(R.id.mL2).setVisibility(View.VISIBLE);
            holder.getView(R.id.mL3).setVisibility(View.VISIBLE);
            holder.getView(R.id.mL4).setVisibility(View.VISIBLE);
            holder.getView(R.id.mL5).setVisibility(View.VISIBLE);
            holder.getView(R.id.mL6).setVisibility(View.VISIBLE);
            holder.getView(R.id.mL7).setVisibility(View.VISIBLE);

            holder.getView(R.id.mLdx).setVisibility(View.GONE);
            holder.getView(R.id.mLrx).setVisibility(View.GONE);
            holder.getView(R.id.mL8).setVisibility(View.GONE);
            holder.getView(R.id.mL9).setVisibility(View.GONE);
            holder.getView(R.id.mL10).setVisibility(View.GONE);
            holder.getView(R.id.mL11).setVisibility(View.GONE);
        }else if(item.type==1){
            holder.setText(R.id.tvtitle,position+1 +".申报内容信息");

            holder.getView(R.id.mL1).setVisibility(View.GONE);
            holder.getView(R.id.mL2).setVisibility(View.GONE);
            holder.getView(R.id.mL3).setVisibility(View.GONE);
            holder.getView(R.id.mL4).setVisibility(View.GONE);
            holder.getView(R.id.mL5).setVisibility(View.GONE);
            holder.getView(R.id.mL6).setVisibility(View.GONE);
            holder.getView(R.id.mL7).setVisibility(View.GONE);

            holder.getView(R.id.mLdx).setVisibility(View.VISIBLE);
            holder.getView(R.id.mLrx).setVisibility(View.GONE);
            holder.getView(R.id.mL8).setVisibility(View.VISIBLE);
            holder.getView(R.id.mL9).setVisibility(View.VISIBLE);
            holder.getView(R.id.mL10).setVisibility(View.VISIBLE);
            holder.getView(R.id.mL11).setVisibility(View.VISIBLE);
        }

       /* holder.getView(R.id.mLM).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });*/

    }
}
