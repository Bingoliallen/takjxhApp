package takjxh.android.com.taapp.activityui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import takjxh.android.com.taapp.R;
import takjxh.android.com.taapp.activityui.bean.UploadFileBean;


/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-27 15:09
 * @Description:
 **/
public class QuickAdapter  extends BaseQuickAdapter<UploadFileBean, BaseViewHolder> {
    public QuickAdapter() {
        super(R.layout.adapter_grid_item_collaborative);
    }


    @Override
    protected void convert(BaseViewHolder viewHolder, UploadFileBean item) {
        viewHolder.setText(R.id.tv_name, item.getFileName());
    }
}
