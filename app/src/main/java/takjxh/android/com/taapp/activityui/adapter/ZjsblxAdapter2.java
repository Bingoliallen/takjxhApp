package takjxh.android.com.taapp.activityui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import takjxh.android.com.commlibrary.adapter.recycler.BaseRecyclerAdapter;
import takjxh.android.com.commlibrary.adapter.recycler.ViewHolder;
import takjxh.android.com.taapp.R;
import takjxh.android.com.taapp.activityui.bean.ApplyTypeBean;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-15 11:17
 * @Description:
 **/
public class ZjsblxAdapter2 extends BaseRecyclerAdapter<ApplyTypeBean.ApplyTypesBean> {


    private Map<Integer, Boolean> maps = new HashMap<>();

    public ZjsblxAdapter2(Context context) {
        super(context);
        putItemLayoutId(R.layout.item_recycler_zjsblx_list);

    }

    private void initMap(List<ApplyTypeBean.ApplyTypesBean> data) {
        maps.clear();
        for (int i = 0; i < data.size(); i++) {
            //每一次进入列表页面  都是未选中状态
            maps.put(i, false);
        }
    }

    /**
     * 增删改查
     *
     * @param data
     */
    @Override
    public void set(List<ApplyTypeBean.ApplyTypesBean> data) {
        initMap(data);
        super.set(data);
    }


    @Override
    public void onBind(ViewHolder holder, final ApplyTypeBean.ApplyTypesBean item, final int position) {
        RadioButton radioButton1 = (RadioButton) holder.getView(R.id.radioButton1);
        CheckBox cb_1 = (CheckBox) holder.getView(R.id.cb_1);

        radioButton1.setVisibility(View.GONE);
        cb_1.setVisibility(View.VISIBLE);
        cb_1.setText(item.getName());
        cb_1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                maps.put(position, isChecked);
            }
        });
        if (maps.get(position) == null) {
            maps.put(position, false);
        }
        //没有设置tag之前会有item重复选框出现，设置tag之后，此问题解决
        cb_1.setChecked(maps.get(position));


    }

    //获取最终的map存储数据
    public Map<Integer, Boolean> getMap() {
        return maps;
    }
}
