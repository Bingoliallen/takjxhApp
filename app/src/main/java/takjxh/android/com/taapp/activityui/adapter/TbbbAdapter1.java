package takjxh.android.com.taapp.activityui.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import takjxh.android.com.commlibrary.adapter.recycler.BaseRecyclerAdapter;
import takjxh.android.com.commlibrary.adapter.recycler.ViewHolder;
import takjxh.android.com.commlibrary.utils.ViewUtil;
import takjxh.android.com.taapp.R;
import takjxh.android.com.taapp.activityui.bean.TaskDetailBean1;

/**
 * Created by Administrator on 2019/12/29.
 */

public class TbbbAdapter1 extends BaseRecyclerAdapter<TaskDetailBean1.Question1Bean> {

    private boolean isShow=true;
    public TbbbAdapter1(Context context) {
        super(context);
        putItemLayoutId(R.layout.item_recycler_tbbb_list1);

    }

    public void setShow(boolean show) {
        isShow = show;
    }

    @Override
    public void onBind(ViewHolder holder, final TaskDetailBean1.Question1Bean item, final int position) {
        holder.setText(R.id.tvtitle, (position+1)+"."+item.getTitle());
        LinearLayout mlTop = (LinearLayout) holder.getView(R.id.mlTop);
        LinearLayout mlOpen = (LinearLayout) holder.getView(R.id.mlOpen);
        View mlView = (View) holder.getView(R.id.mlView);



        mlTop.setTag(item.getId());
        mlOpen.setTag(item.getTitle());
        if(!item.isOpen){
            mlOpen.setVisibility(View.GONE);
        }else{
            mlOpen.setVisibility(View.VISIBLE);
        }
        if(isShow){
            mlTop.setVisibility(View.VISIBLE);
            mlView.setVisibility(View.VISIBLE);
        }else{
            mlTop.setVisibility(View.GONE);
            mlView.setVisibility(View.GONE);
        }
        mlTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!item.isOpen) {
                    item.isOpen = true;
                } else {
                    item.isOpen = false;
                }

                Handler handler = new Handler();
                final Runnable r = new Runnable() {
                    @Override
                    public void run() {
                        notifyDataSetChanged();// 刷新视图
                    }
                };
                handler.post(r);
            }
        });



        List<TaskDetailBean1.Question1Bean.ItemsBeanXX> mList = new ArrayList<>();
        RecyclerView recycler_view = (RecyclerView) holder.getView(R.id.recycler_view);
        recycler_view.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        recycler_view.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.top = ViewUtil.dp2px((Activity)mContext, 0);
                outRect.bottom = ViewUtil.dp2px((Activity)mContext, 0);
            }
        });
        TbbbAdapter2  mTbbbAdapter = new TbbbAdapter2(mContext);
        mTbbbAdapter.setmClickListener(new TbbbAdapter2.OnTaskDetailClickListener() {
            @Override
            public void onClick(String id, int pos, String inputContent) {
                if(getData().get(position).getItems()!=null){
                    getData().get(position).getItems().get(pos).inputContent = inputContent;
                }

            }
        });
        recycler_view.setAdapter(mTbbbAdapter);

        mList.clear();
        if(item.getItems()!=null){
            mList.addAll(item.getItems());
        }

        mTbbbAdapter.set(mList);

    }




}