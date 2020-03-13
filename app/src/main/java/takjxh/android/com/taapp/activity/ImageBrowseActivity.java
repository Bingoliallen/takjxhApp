package takjxh.android.com.taapp.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bm.library.PhotoView;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

import takjxh.android.com.taapp.R;


public class ImageBrowseActivity extends AppCompatActivity {

    private ViewPager imageBrowseViewPager;
    private ArrayList<String> imageList =new ArrayList<>(); //图片列表
    private TextView mTvImageCount;
    private int index=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_browse);
        index  = getIntent().getIntExtra("index", 0);
        imageList = getIntent().getStringArrayListExtra("imageList");
        mTvImageCount=(TextView)findViewById(R.id.mTvImageCount);
        mTvImageCount.setText(index+1 + "/" + imageList.size());
        imageBrowseViewPager=(ViewPager)findViewById(R.id.imageBrowseViewPager);
        imageBrowseViewPager.setAdapter( new ImageBrowseAdapter());
        imageBrowseViewPager.setCurrentItem(index);
        imageBrowseViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                index = position;
                mTvImageCount.setText(index + 1 + "/" + imageList.size());
            }
        });
    }




    private  class ImageBrowseAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return imageList.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
          final   PhotoView image = new PhotoView(ImageBrowseActivity.this);
            // 开启图片缩放功能

            image.enable();
            // 设置缩放类型
            image.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            // 设置最大缩放倍数
            image.setMaxScale(2.5f);
            // 加载图片
            Glide.with(ImageBrowseActivity.this)
                    .load(imageList.get(position))
                    .placeholder(R.mipmap.loading)
                    .error(R.mipmap.icon_failed)
                    .into(image);
            // 单击图片，返回
            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    image.disenable();
                    finish();
                }
            });

            container.addView(image);
            return image;
        }

        @Override
        //不调用会报错
        public void destroyItem(ViewGroup container, int position, Object object) {

            container.removeView((View) object);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_HOME) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
