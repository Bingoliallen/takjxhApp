package takjxh.android.com.taapp.activityui.chat.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;
import android.widget.LinearLayout;

import takjxh.android.com.taapp.R;
import takjxh.android.com.taapp.activityui.chat.activity.historyfile.adapter.ImageFileAdapter;
import takjxh.android.com.taapp.activityui.chat.activity.historyfile.grideviewheader.StickyGridHeadersGridView;
import takjxh.android.com.taapp.activityui.chat.adapter.ImageAdapter;


public class SendImageView extends LinearLayout {

    private GridView mImageGV;
    private StickyGridHeadersGridView mHistroyImage;

    public SendImageView(Context context) {
        super(context);
    }

    public SendImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void initModule() {
        mImageGV = (GridView) findViewById(R.id.album_grid_view);
    }

    public GridView initFileViewModule() {
        mHistroyImage = (StickyGridHeadersGridView) findViewById(R.id.asset_grid);
        return mHistroyImage;
    }

    public void setAdapter(ImageAdapter adapter) {
        mImageGV.setAdapter(adapter);
    }

    public void setFileAdapter(ImageFileAdapter adapter) {
        mHistroyImage.setAdapter(adapter);
    }


}
