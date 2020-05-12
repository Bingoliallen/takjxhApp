package takjxh.android.com.taapp.activityui.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import takjxh.android.com.commlibrary.adapter.recycler.BaseRecyclerAdapter;
import takjxh.android.com.commlibrary.adapter.recycler.ViewHolder;
import takjxh.android.com.taapp.R;
import takjxh.android.com.taapp.activityui.bean.Policyapplyadd;
import takjxh.android.com.taapp.activityui.bean.PolicyapplyaddBean;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2020-05-07 15:21
 * @Description:
 **/
public class ZjsblxNewAdapter extends BaseRecyclerAdapter<Policyapplyadd> {
    private ZjsblxMultiItemQuickAdapter.OnDataChangeListener mOnDataChangeListener;
    private ZjsblxMultiItemQuickAdapter.OnWJSCClickListener mOnWJSCClickListener;
    private ZjsblxMultiItemQuickAdapter.OnWJSCItemClickListener mWJSCItemClickListener;

    public ZjsblxNewAdapter(Context context) {
        super(context);
        putItemLayoutId(R.layout.item_recycler_zjsblx_list_new1);

    }

    public void setOnDataChangeListener(ZjsblxMultiItemQuickAdapter.OnDataChangeListener mClickListener) {
        this.mOnDataChangeListener = mClickListener;
    }


    public void setOnWJSCClickListener(ZjsblxMultiItemQuickAdapter.OnWJSCClickListener mClickListener) {
        this.mOnWJSCClickListener = mClickListener;
    }

    public void setOnWJSCItemClickListener(ZjsblxMultiItemQuickAdapter.OnWJSCItemClickListener mClickListener) {
        this.mWJSCItemClickListener = mClickListener;
    }

    @Override
    public void onBind(ViewHolder holder, final Policyapplyadd item, final int position) {

        holder.setText(R.id.tvtitle, (position+2)+"."+item.key);
        RecyclerView mRecy = (RecyclerView) holder.getView(R.id.recycler_view);
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        mRecy.setLayoutManager(manager);
        List<PolicyapplyaddBean> list = new ArrayList<>();
        list.clear();
        if (item.value != null) {
            list.addAll(item.value);
        }
        ZjsblxMultiItemQuickAdapter apader = new ZjsblxMultiItemQuickAdapter((FragmentActivity)mContext,list,position);
        apader.setOnDataChangeListener(mOnDataChangeListener);
        apader.setOnWJSCClickListener(mOnWJSCClickListener);
        apader.setOnWJSCItemClickListener(mWJSCItemClickListener);
/*      apader.openLoadAnimation(BaseQuickAdapter.SLIDEIN_RIGHT);//自带的动画*/
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

    @Override
    public List<Policyapplyadd> getData() {
        return super.getData();
    }
}
