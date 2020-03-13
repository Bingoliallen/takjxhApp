package takjxh.android.com.taapp.activityui.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.yc.cn.ycbannerlib.banner.adapter.AbsStaticPagerAdapter;

import java.util.List;

import takjxh.android.com.commlibrary.image.ImageWrapper;
import takjxh.android.com.commlibrary.net.HttpConfig;
import takjxh.android.com.taapp.R;
import takjxh.android.com.taapp.activityui.WebViewActivity;
import takjxh.android.com.taapp.activityui.activity.BannersDetailActivity;
import takjxh.android.com.taapp.activityui.bean.BannnersBean;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-10-14 10:32
 * @Description:
 **/
public class BaseBannerPagerAdapter extends AbsStaticPagerAdapter {

    private Context ctx;
    private List<BannnersBean.BannersBean> list;

    public BaseBannerPagerAdapter(Context ctx, List<BannnersBean.BannersBean> list) {
        this.ctx = ctx;
        this.list = list;
    }


    @Override
    public View getView(ViewGroup container, final int position) {
        ImageView imageView = new ImageView(ctx);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        //加载图片
        if (list != null) {
            ImageWrapper.setImage(imageView, list.get(position).getCover(), R.drawable.pic_defalt, ImageWrapper.FIT_CENTER);
        } else {
            ImageWrapper.setImage(imageView, R.drawable.pic_defalt);
        }
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ("01".equals(list.get(position).getType())) {
                    BannersDetailActivity.lunch((Activity) ctx, list.get(position).getId(),  list.get(position).getCover(), list.get(position).getTitle());
                } else if ("02".equals(list.get(position).getType())) {
                    WebViewActivity.lunch((Activity) ctx, list.get(position).getUrl(), list.get(position).getTitle());
                }
            }
        });
        return imageView;
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

}