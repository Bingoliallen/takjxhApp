package takjxh.android.com.taapp.activityui.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import takjxh.android.com.commlibrary.adapter.recycler.BaseRecyclerAdapter;
import takjxh.android.com.commlibrary.adapter.recycler.ViewHolder;
import takjxh.android.com.commlibrary.adapter.recycler.divider.LinearLayoutColorDivider;
import takjxh.android.com.taapp.R;
import takjxh.android.com.taapp.activityui.bean.Policyapplyadd;
import takjxh.android.com.taapp.activityui.bean.PolicyapplyaddBean;
import takjxh.android.com.taapp.activityui.bean.UploadFileBean;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2020-05-11 11:12
 * @Description:
 **/
public class ZjsbtxDetailAdapter1  extends BaseRecyclerAdapter<Policyapplyadd> {

    private ZjsbtxDetailMultiItemQuickAdapter.OnZjsbtxDetailClickListener mClickListener;

    public void setmClickListener(ZjsbtxDetailMultiItemQuickAdapter.OnZjsbtxDetailClickListener mClickListener) {
        this.mClickListener = mClickListener;
    }


    public ZjsbtxDetailAdapter1(Context context) {
        super(context);
        putItemLayoutId(R.layout.item_recycler_zjsbtx_detail_list1);

    }


    @Override
    public void onBind(ViewHolder holder, final Policyapplyadd item, final int position) {


        holder.setText(R.id.tvtitle1, (position+2)+"."+item.key);

        RecyclerView mRecy = (RecyclerView) holder.getView(R.id.recycler_view);
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        mRecy.setLayoutManager(manager);
        List<PolicyapplyaddBean> list = new ArrayList<>();
        list.clear();
        if (item.value != null) {
            list.addAll(item.value);
        }
        ZjsbtxDetailMultiItemQuickAdapter apader = new ZjsbtxDetailMultiItemQuickAdapter((FragmentActivity)mContext,list,position);
        apader.setmClickListener(mClickListener);
        mRecy.setAdapter(apader);


        apader.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                /*if (view.getId()==R.id.mWo){

                }else {

                }*/

            }
        });


    }



}
