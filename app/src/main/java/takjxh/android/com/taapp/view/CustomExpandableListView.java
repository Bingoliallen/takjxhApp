package takjxh.android.com.taapp.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ExpandableListView;

/***
 * 名称定义
 *
 * @Author: libaibing
 * @Date: 2019/1/30
 */

public class CustomExpandableListView extends ExpandableListView {

    public CustomExpandableListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }

    /**
     * 重写该方法，达到使GridView适应ScrollView的效果
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
