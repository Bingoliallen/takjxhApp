package takjxh.android.com.taapp.view.treeview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

import takjxh.android.com.taapp.R;


/**
 * TreeViewAdapter
 *
 * @author carrey
 */
public class TreeViewAdapter extends BaseAdapter {
    /**
     * 元素数据源
     */
    private ArrayList<Element> elementsData;
    /**
     * 树中元素
     */
    private ArrayList<Element> elements;
    /**
     * LayoutInflater
     */
    private LayoutInflater inflater;
    /**
     * item的行首缩进基数
     */
    private int indentionBase;

    public TreeViewAdapter(ArrayList<Element> elements, ArrayList<Element> elementsData, LayoutInflater inflater) {
        this.elements = elements;
        this.elementsData = elementsData;
        this.inflater = inflater;
        indentionBase = 50;
    }

    public ArrayList<Element> getElements() {
        return elements;
    }

    public ArrayList<Element> getElementsData() {
        return elementsData;
    }

    @Override
    public int getCount() {
        return elements.size();
    }

    @Override
    public Object getItem(int position) {
        return elements.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.treeview_item, null);
            holder.disclosureImg = (ImageView) convertView.findViewById(R.id.disclosureImg);
            holder.contentText = (TextView) convertView.findViewById(R.id.contentText);
            holder.cb_select_tree = (CheckBox) convertView.findViewById(R.id.cb_select_tree);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Element element = elements.get(position);
        int level = element.getLevel();
        holder.disclosureImg.setPadding(
                indentionBase * (level + 1),
                holder.disclosureImg.getPaddingTop(),
                holder.disclosureImg.getPaddingRight(),
                holder.disclosureImg.getPaddingBottom());
        holder.contentText.setText(element.getContentText());
        if (element.isHasChildren() && !element.isExpanded()) {
            holder.disclosureImg.setImageResource(R.drawable.close);
            //这里要主动设置一下icon可见，因为convertView有可能是重用了"设置了不可见"的view，下同。
            holder.disclosureImg.setVisibility(View.VISIBLE);
        } else if (element.isHasChildren() && element.isExpanded()) {
            holder.disclosureImg.setImageResource(R.drawable.open);
            holder.disclosureImg.setVisibility(View.VISIBLE);
        } else if (!element.isHasChildren()) {
            holder.disclosureImg.setImageResource(R.drawable.close);
            holder.disclosureImg.setVisibility(View.INVISIBLE);
        }
        holder.cb_select_tree.setChecked(element.isSelected());
        holder.cb_select_tree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    for (Element ele : elements) {
                        ele.setSelected(false);
                    }
                }
                elements.get(position).setSelected(isChecked);
                notifyDataSetChanged();
                if (mTreeViewItemOnCheckedChangeListener != null) {
                    mTreeViewItemOnCheckedChangeListener.onClick(elements.get(position));
                }

            }
        });
        return convertView;
    }

    public interface TreeViewItemOnCheckedChangeListener {
        void onClick(Element ele);
    }

    private TreeViewItemOnCheckedChangeListener mTreeViewItemOnCheckedChangeListener;

    public void setmTreeViewItemOnCheckedChangeListener(TreeViewItemOnCheckedChangeListener mTreeViewItemOnCheckedChangeListener) {
        this.mTreeViewItemOnCheckedChangeListener = mTreeViewItemOnCheckedChangeListener;
    }

    /**
     * 优化Holder
     *
     * @author carrey
     */
    static class ViewHolder {
        ImageView disclosureImg;
        TextView contentText;
        CheckBox cb_select_tree;
    }
}
