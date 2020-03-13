package takjxh.android.com.taapp.activityui.chat.utils;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.io.IOException;

import takjxh.android.com.taapp.activityui.chat.keyboard.utils.imageloader.ImageBase;
import takjxh.android.com.taapp.activityui.chat.keyboard.utils.imageloader.ImageLoader;


public class ImageLoadUtils extends ImageLoader {

    public ImageLoadUtils(Context context) {
        super(context);
    }

    public static void displayImageFromFile(String imageUri, ImageView imageView,int resourceId) throws IOException {
        String filePath = Scheme.FILE.crop(imageUri);
        Glide.with(imageView.getContext())
                .load(filePath)
                .asBitmap()
                .error(resourceId)
                .into(imageView);
    }


    @Override
    protected void displayImageFromFile(String imageUri, ImageView imageView) throws IOException {
        String filePath = Scheme.FILE.crop(imageUri);
        Glide.with(imageView.getContext())
                .load(filePath)
                .asBitmap()
                .into(imageView);
    }

    @Override
    protected void displayImageFromAssets(String imageUri, ImageView imageView) throws IOException {
        String uri = Scheme.cropScheme(imageUri);
        ImageBase.Scheme.ofUri(imageUri).crop(imageUri);
        Glide.with(imageView.getContext())
                .load(Uri.parse("file:///android_asset/" + uri))
                .into(imageView);
    }
}
