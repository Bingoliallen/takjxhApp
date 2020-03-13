package takjxh.android.com.taapp.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.youth.banner.loader.ImageLoader;

import takjxh.android.com.commlibrary.utils.L;
import takjxh.android.com.taapp.R;


/**
 * 名称定义
 *
 * @Author: libaibing
 * @Date: 2019-01-17 12:51
 * @Description:
 **/

public class GlideImageLoader extends ImageLoader {

    @Override
    public void displayImage(Context context, Object o, ImageView imageView) {
        L.d("GlideImageLoader", "----------displayImage-------");
        Glide.with(context).load(o).error(R.drawable.pic_defalt).fitCenter().into(imageView);
    }
}