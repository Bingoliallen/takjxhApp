package takjxh.android.com.taapp.view.mulitmenuselect.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import takjxh.android.com.taapp.R;
import takjxh.android.com.taapp.view.mulitmenuselect.Contribute;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2020-05-11 8:37
 * @Description:
 **/
public class ContributeDialogAdapter extends BaseAdapter {

    private Context mContext;
    private List<Contribute> mData;
    private int selectedPos = -1;
    private int mSelectedBackgroundResource;
    private int mNormalBackgroundResource;
    private boolean hasDivider = true;


    public void setNormalBackgroundResource(int normalBackgroundResource) {
        this.mNormalBackgroundResource = normalBackgroundResource;
    }

    public void setHasDivider(boolean hasDivider) {
        this.hasDivider = hasDivider;
    }

    public ContributeDialogAdapter(Context context, List<Contribute> data) {
        this.mContext = context;
        mData = data;
    }

    public void setSelectedBackground(int res) {
        mSelectedBackgroundResource = res;
    }


    public void setSelectedItem(int position) {
        selectedPos = position;
        notifyDataSetChanged();
    }


    public void setData(List<Contribute> data) {
        mData = data;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (mData==null) {
            return 0;
        }
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        if (mData==null) {
            return null;
        }
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.layout_dict_item, parent, false);

        }

        LinearLayout itemLayout= ViewHolder.get(convertView, R.id.dict_item_ly);

        TextView nameText = ViewHolder.get(convertView, R.id.dict_item_textview);

        TextView dividerTextView = ViewHolder.get(convertView, R.id.dict_item_divider);

        final Contribute dictUnit = mData.get(position);

        nameText.setText(dictUnit.getValue());


        convertView.setSelected(selectedPos == position);
        nameText.setSelected(selectedPos == position);

        nameText.setTextColor(selectedPos == position ? 0xFF00B4C9 : 0xFF333333);

        if (mNormalBackgroundResource == 0) {
            mNormalBackgroundResource = R.color.white;
        }

        if (mSelectedBackgroundResource != 0) {
            itemLayout.setBackgroundResource(selectedPos == position ? mSelectedBackgroundResource : mNormalBackgroundResource);
        }

        dividerTextView.setVisibility(hasDivider ? View.VISIBLE : View.INVISIBLE);

        return convertView;
    }



}