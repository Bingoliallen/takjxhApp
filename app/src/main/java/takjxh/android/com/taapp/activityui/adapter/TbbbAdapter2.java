package takjxh.android.com.taapp.activityui.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.LinearLayout;

import takjxh.android.com.commlibrary.adapter.recycler.BaseRecyclerAdapter;
import takjxh.android.com.commlibrary.adapter.recycler.ViewHolder;
import takjxh.android.com.taapp.R;
import takjxh.android.com.taapp.activityui.bean.TaskDetailBean1;

/**
 * Created by Administrator on 2019/12/29.
 */

public class TbbbAdapter2 extends BaseRecyclerAdapter<TaskDetailBean1.Question1Bean.ItemsBeanXX> {


    public TbbbAdapter2(Context context) {
        super(context);
        putItemLayoutId(R.layout.item_recycler_tbbb_list2);

    }


    @Override
    public void onBind(ViewHolder holder, final TaskDetailBean1.Question1Bean.ItemsBeanXX item, final int position) {
        holder.setText(R.id.tvtitle, item.getDes()+"");
        LinearLayout mL4 = (LinearLayout) holder.getView(R.id.mL4);
        EditText mEditText = (EditText) holder.getView(R.id.edFR);
        if (mEditText.getTag() != null && mEditText.getTag() instanceof TextWatcher) {
            mEditText.removeTextChangedListener((TextWatcher) mEditText.getTag());
        }
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                getData().get(position).inputContent = s.toString();
                if(mClickListener!=null){
                    mClickListener.onClick(item.getId(),position,s.toString());
                }
            }
        };
        mEditText.addTextChangedListener(textWatcher);
        mEditText.setTag(textWatcher);
        mEditText.setText(getData().get(position).inputContent );
    }

    public interface OnTaskDetailClickListener {
        void onClick(String id, int position, String inputContent);

    }

    private OnTaskDetailClickListener mClickListener;

    public void setmClickListener(OnTaskDetailClickListener mClickListener) {
        this.mClickListener = mClickListener;
    }


}