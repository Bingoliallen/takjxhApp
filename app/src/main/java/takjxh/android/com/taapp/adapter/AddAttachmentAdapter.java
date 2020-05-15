package takjxh.android.com.taapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import takjxh.android.com.taapp.R;
import takjxh.android.com.taapp.activityui.bean.UploadFileBean;

/**
 * 附件-适配器
 *
 * @Author: libaibing
 * @Date: 2019-01-18 12:54
 * @Description:
 **/

public class AddAttachmentAdapter extends BaseAdapter {

    private Context context;
    private List<UploadFileBean> list = new ArrayList<UploadFileBean>();
    private LayoutInflater layoutInflater;

    public AddAttachmentAdapter(Context context, List<UploadFileBean> list) {
        this.context = context;
        this.list = list;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        if (position >= getCount()) {
            return null;
        }
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.adapter_grid_item_attachment, null);
            viewHolder.textView = (TextView) convertView.findViewById(R.id.tv_name);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        String name = list.get(position).getFileName();
        viewHolder.textView.setText(name);

        return convertView;
    }

    class ViewHolder {
        private TextView textView;
    }
}
