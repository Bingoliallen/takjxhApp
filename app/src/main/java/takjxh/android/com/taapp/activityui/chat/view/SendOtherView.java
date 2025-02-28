package takjxh.android.com.taapp.activityui.chat.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import takjxh.android.com.taapp.R;
import takjxh.android.com.taapp.activityui.chat.adapter.OtherAdapter;


public class SendOtherView extends LinearLayout {

    private ListView mListView;

    public SendOtherView(Context context) {
        super(context);
    }

    public SendOtherView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void initModule() {
        mListView = (ListView) findViewById(R.id.other_list_view);
    }

    public void setAdapter(OtherAdapter adapter) {
        mListView.setAdapter(adapter);
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener listener) {
        mListView.setOnItemClickListener(listener);
    }
}
