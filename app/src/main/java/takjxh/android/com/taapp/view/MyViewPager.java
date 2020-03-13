package takjxh.android.com.taapp.view;


import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

import com.yc.cn.ycbannerlib.banner.BannerView;


/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-26 8:45
 * @Description:
 **/
public class MyViewPager extends ViewPager {
    public MyViewPager(Context context) {
        super(context);
    }

    public MyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected boolean canScroll(View v, boolean checkV, int dx, int x, int y) {
        if (v != this && v instanceof BannerView) {
            return true;
        }
        return super.canScroll(v, checkV, dx, x, y);
    }
}