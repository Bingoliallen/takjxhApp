package takjxh.android.com.taapp.activityui.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import takjxh.android.com.commlibrary.adapter.recycler.BaseRecyclerAdapter;
import takjxh.android.com.commlibrary.adapter.recycler.ViewHolder;
import takjxh.android.com.taapp.R;
import takjxh.android.com.taapp.activityui.bean.ApplyTypeBean;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-13 16:32
 * @Description:
 **/
public class ZjsblxAdapter extends BaseRecyclerAdapter<ApplyTypeBean.ApplyTypesBean> {

    private int index = -1;//标记当前选择的选项
    private String applyType;
    public ZjsblxAdapter(Context context) {
        super(context);
        putItemLayoutId(R.layout.item_recycler_zjsblx_list);

    }

    public void setApplyType(String applyType) {
        this.applyType = applyType;
    }

    @Override
    public void onBind(ViewHolder holder, final ApplyTypeBean.ApplyTypesBean item, final int position) {

        RadioButton radioButton1 = (RadioButton) holder.getView(R.id.radioButton1);
        CheckBox cb_1 = (CheckBox) holder.getView(R.id.cb_1);
        radioButton1.setVisibility(View.VISIBLE);
        cb_1.setVisibility(View.GONE);
        radioButton1.setText(item.getName());
        if(!TextUtils.isEmpty(applyType) && applyType.equals(item.getId())){
            index = position;
        }
        if (index == position) {
            radioButton1.setChecked(true);
        } else {
            radioButton1.setChecked(false);
        }

        radioButton1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    applyType="";
                    index = position;
                    notifyDataSetChanged();

                    if (mClickListener != null) {
                        mClickListener.onClick(item, position);
                    }
                }
            }
        });


        holder.getView(R.id.mLM).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    public interface OnApplyTypeClickListener {
        void onClick(ApplyTypeBean.ApplyTypesBean item, int position);

    }

    private OnApplyTypeClickListener mClickListener;

    public void setmClickListener(OnApplyTypeClickListener mClickListener) {
        this.mClickListener = mClickListener;
    }
}

