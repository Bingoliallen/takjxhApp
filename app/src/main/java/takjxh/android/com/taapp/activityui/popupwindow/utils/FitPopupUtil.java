package takjxh.android.com.taapp.activityui.popupwindow.utils;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import takjxh.android.com.taapp.R;
import takjxh.android.com.taapp.activityui.dropdownmenu.GirdDropDownAdapter;


public class FitPopupUtil implements View.OnClickListener {

    private View contentView;

    private Activity context;

    private ListView reason1;
    private GirdDropDownAdapter cityAdapter;

    private List<String> list = new ArrayList<>();
    private FitPopupWindow mPopupWindow;

    private OnCommitClickListener listener;

    public FitPopupUtil(Activity context,List<String> list1,int checkItemPosition) {

        this.context = context;
        this.list = list1;
        LayoutInflater inflater = LayoutInflater.from(context);
        contentView = inflater.inflate(R.layout.layout_popupwindow, null);
        reason1 = (ListView) contentView.findViewById(R.id.title_list);
        cityAdapter = new GirdDropDownAdapter(context, list);
        reason1.setDividerHeight(0);
        reason1.setAdapter(cityAdapter);
        reason1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int index,
                                    long arg3) {
                // 点击子类项后，弹窗消失
                mPopupWindow.dismiss();
                if (listener != null) {
                    listener.onClick(list.get(index),index);
                }
            }
        });
        cityAdapter.setCheckItem(checkItemPosition);
    }

    public void setOnClickListener(OnCommitClickListener listener) {
        this.listener = listener;
    }

    /**
     * 弹出自适应位置的popupwindow
     *
     * @param anchorView 目标view
     */
    public View showPopup(View anchorView) {
        if (mPopupWindow == null) {
            mPopupWindow = new FitPopupWindow(context,
                    ScreenUtils.getScreenWidth(context) - DensityUtils.dp2px(20),
                    DensityUtils.dp2px(150)
            );
        }

        mPopupWindow.setView(contentView, anchorView);
        mPopupWindow.show();
        return contentView;
    }


    @Override
    public void onClick(View v) {

    }


    public void setList(List<String> list1) {
        if(list1!=null){
            this.list.clear();
            this.list.addAll(list1);
        }else {
            this.list.clear();
        }
        cityAdapter.notifyDataSetChanged();
    }

    public interface OnCommitClickListener {
        void onClick(String item, int position);
    }

}
