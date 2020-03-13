package takjxh.android.com.taapp.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.TintTypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import takjxh.android.com.taapp.R;
import takjxh.android.com.taapp.utils.DisplayUtil;

/**
 * 统一标题栏
 *
 * @Author: libaibing
 * @Date: 2019-01-07 12:51
 * @Description:
 **/

public class NormalTitleBar extends RelativeLayout {

    private ImageView ivBack, ivRight, image_right1;
    private TextView tvTitle, tvRight;
    private RelativeLayout rlCommonTitle;
    private Context context;

    public NormalTitleBar(Context context) {
        super(context, null);
        this.context = context;
    }

    public NormalTitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        View.inflate(context, R.layout.bar_normal, this);
        ivBack = (ImageView) findViewById(R.id.tv_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvRight = (TextView) findViewById(R.id.tv_right);
        ivRight = (ImageView) findViewById(R.id.image_right);
        image_right1 = (ImageView) findViewById(R.id.image_right1);
        rlCommonTitle = (RelativeLayout) findViewById(R.id.common_title);

        @SuppressLint("RestrictedApi") TintTypedArray a = TintTypedArray.obtainStyledAttributes(context,
                null, android.support.v7.appcompat.R.styleable.ActionBar, android.support.v7.appcompat.R.attr.actionBarStyle, 0);
        @SuppressLint("RestrictedApi") Drawable drawable = a.getDrawable(android.support.v7.appcompat.R.styleable.ActionBar_homeAsUpIndicator);
        drawable.setColorFilter(Color.WHITE, PorterDuff.Mode.MULTIPLY);
        getLiftImge().setImageDrawable(drawable);

        //setHeaderHeight();
    }

    public void setHeaderHeight() {
        rlCommonTitle.setPadding(0, DisplayUtil.getStatusBarHeight(context), 0, 0);
        rlCommonTitle.requestLayout();
    }

    /**
     * 管理返回按钮
     */
    public void setBackVisibility(boolean visible) {
        if (visible) {
            ivBack.setVisibility(View.VISIBLE);
        } else {
            ivBack.setVisibility(View.GONE);
        }
    }

    /**
     * 设置标题栏左侧字符串
     *
     * @param visiable
     */
    public void setTvLeftVisiable(boolean visiable) {
        if (visiable) {
            ivBack.setVisibility(View.VISIBLE);
        } else {
            ivBack.setVisibility(View.GONE);
        }
    }

    /**
     * 设置标题栏左侧字符串
     *
     * @param tvLeftText
     */
    public void setTvLeft(String tvLeftText) {
        //   ivBack.setText(tvLeftText);
    }


    /**
     * 管理标题
     */
    public void setTitleVisibility(boolean visible) {
        if (visible) {
            tvTitle.setVisibility(View.VISIBLE);
        } else {
            tvTitle.setVisibility(View.GONE);
        }
    }

    public TextView getTvTitle() {
        return tvTitle;
    }

    public void setTitleText(String string) {
        tvTitle.setText(string);
    }

    public void setTitleText(int string) {
        tvTitle.setText(string);
    }

    public void setTitleColor(int color) {
        tvTitle.setTextColor(color);
    }

    /**
     * 右图标
     */
    public void setRightImagVisibility(boolean visible) {
        ivRight.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    /**
     * 右图标
     */
    public void setRightImagVisibility1(boolean visible) {
        image_right1.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    public void setRightImagSrc(int id) {
        ivRight.setVisibility(View.VISIBLE);
        ivRight.setImageResource(id);
    }

    public void setRightImagSrc1(int id) {
        image_right1.setVisibility(View.VISIBLE);
        image_right1.setImageResource(id);
    }

    /**
     * 获取右按钮
     *
     * @return
     */
    public View getRightImage() {
        return ivRight;
    }

    /**
     * 获取右按钮
     *
     * @return
     */
    public View getRightImage1() {
        return image_right1;
    }

    public ImageView getLiftImge() {
        return ivBack;
    }

    /**
     * 左图标
     *
     * @param id
     */
    public void setLeftImagSrc(int id) {
        ivBack.setImageDrawable(getResources().getDrawable(id));
    }

    /**
     * 左文字
     *
     * @param str
     */
    public void setLeftTitle(String str) {
        //  ivBack.setText(str);
    }

    /**
     * 右标题
     */
    public void setRightTitleVisibility(boolean visible) {
        tvRight.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    public void setRightTitle(String text) {
        tvRight.setText(text);
    }

    /*
     * 点击事件
     */
    public void setOnBackListener(OnClickListener listener) {
        ivBack.setOnClickListener(listener);
    }

    public void setOnRightImagListener(OnClickListener listener) {
        ivRight.setOnClickListener(listener);
    }

    public void setOnRightImagListener1(OnClickListener listener) {
        image_right1.setOnClickListener(listener);
    }

    public void setOnRightTextListener(OnClickListener listener) {
        tvRight.setOnClickListener(listener);
    }

    /**
     * 标题背景颜色
     *
     * @param color
     */
    public void setBackGroundColor(int color) {
        rlCommonTitle.setBackgroundColor(color);
    }

    public Drawable getBackGroundDrawable() {
        return rlCommonTitle.getBackground();
    }

    /**
     * 标题背景图
     *
     * @param resid
     */
    public void setBackgroundResources(int resid) {
        rlCommonTitle.setBackgroundResource(resid);
    }


}
