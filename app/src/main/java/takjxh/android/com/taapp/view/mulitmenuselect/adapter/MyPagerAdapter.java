package takjxh.android.com.taapp.view.mulitmenuselect.adapter;

import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;


import java.util.List;

/**
 * Created by yuliang.zhao on 2015/3/18.
 */
public class MyPagerAdapter extends PagerAdapter {
    private List<View> viewList;

    public MyPagerAdapter(List<View> viewList) {
        this.viewList = viewList;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        if (viewList != null) {
            return viewList.size();
        }
        return 0;
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        // TODO Auto-generated method stub
        return (arg0 == arg1);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        // TODO Auto-generated method stub
        ((MyViewPager) container).addView(viewList.get(position));

        return viewList.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // TODO Auto-generated method stub
       ((MyViewPager) container).removeView((View) object);
        //  container.removeView((View) object);
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public float getPageWidth(int position) {
        Log.d("d","pagewidth = "+super.getPageWidth(position));
        return 0.5f;
    }
}
