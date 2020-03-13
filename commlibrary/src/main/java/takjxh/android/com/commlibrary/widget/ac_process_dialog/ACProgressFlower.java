package takjxh.android.com.commlibrary.widget.ac_process_dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;


import java.util.TimerTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import takjxh.android.com.commlibrary.R;


public class ACProgressFlower extends ACProgress {

  private Builder mBuilder;
  private FlowerView mFlowerView;

  private int mSpinCount = 0;
//  private Timer mTimer;

  private  ScheduledExecutorService scheduledExecutorService = null;

  private ACProgressFlower(Builder builder) {
    super(builder.mContext, builder.mTheme);
    mBuilder = builder;
    setOnDismissListener(new DialogInterface.OnDismissListener() {
      @Override
      public void onDismiss(DialogInterface dialog) {
        /*if (mTimer != null) {
          mTimer.cancel();
          mTimer = null;
        }*/
        shutDownNowScheduledExecutor();
        mSpinCount = 0;
        mFlowerView = null;
      }
    });
  }
  @Override
  public void show() {
    if (mFlowerView == null) {
      int size = (int) (getMinimumSideOfScreen(mBuilder.mContext) * mBuilder.mSizeRatio);
      mFlowerView = new FlowerView(mBuilder.mContext, size, mBuilder.mBackgroundColor,
          mBuilder.mBackgroundAlpha, mBuilder.mBackgroundCornerRadius,
          mBuilder.mPetalThickness, mBuilder.mPetalCount, mBuilder.mPetalAlpha,
          mBuilder.mBorderPadding, mBuilder.mCenterPadding,
          mBuilder.mThemeColor, mBuilder.mFadeColor,
          mBuilder.mText, mBuilder.mTextSize, mBuilder.mTextColor, mBuilder.mTextAlpha,
          mBuilder.mTextMarginTop, mBuilder.mTextExpandWidth);
    }
    // 设置花瓣控件为显示的内容
    super.setContentView(mFlowerView);
    super.show();

    // 计时任务来旋转花搬
    long delay = (long) (1000 / mBuilder.mSpeed);
 //   mTimer = new Timer();

    getScheduledExecutorService().scheduleAtFixedRate(new TimerTask() {
      @Override
      public void run() {
        int result = mSpinCount % mBuilder.mPetalCount;
        if (mBuilder.mDirection == ACProgressConstant.DIRECT_CLOCKWISE) {
          mFlowerView.updateFocusIndex(result);
        } else {
          mFlowerView.updateFocusIndex(mBuilder.mPetalCount - 1 - result);
        }
        if (result == 0) {
          mSpinCount = 1;
        } else {
          mSpinCount++;
        }
      }
    }, delay, delay, TimeUnit.MILLISECONDS);
  /*  mTimer.scheduleAtFixedRate(new TimerTask() {
      @Override
      public void run() {
        int result = mSpinCount % mBuilder.mPetalCount;
        if (mBuilder.mDirection == ACProgressConstant.DIRECT_CLOCKWISE) {
          mFlowerView.updateFocusIndex(result);
        } else {
          mFlowerView.updateFocusIndex(mBuilder.mPetalCount - 1 - result);
        }
        if (result == 0) {
          mSpinCount = 1;
        } else {
          mSpinCount++;
        }
      }
    }, delay, delay);*/
  }



  private static final int SCHE_THREAD_SIZE = 5;

  public ScheduledExecutorService getScheduledExecutorService() {

    if (!isScheduledServiceEnable()) {
      scheduledExecutorService = new ScheduledThreadPoolExecutor(SCHE_THREAD_SIZE);
    }

    return scheduledExecutorService;
  }

  public void shutDownScheduledExecutor() {
    if (!isScheduledServiceEnable()) {
      //     先前提交的任务将会被工作线程执行，新的线程将会被拒绝。这个方法
      //     不会等待提交的任务执行完，我们可以用awaitTermination来等待任务执行完。
      getScheduledExecutorService().shutdown();
    }
  }

  public void shutDownNowScheduledExecutor() {
    if (!isScheduledServiceEnable()) {
      getScheduledExecutorService().shutdownNow();
    }
  }

  private boolean isScheduledServiceEnable() {
    return !(scheduledExecutorService == null
            || scheduledExecutorService.isShutdown()
            || scheduledExecutorService.isTerminated());
  }



  public static class Builder {

    private Context mContext;

    private int mTheme = R.style.ACPLDialog;

    // 大小比例
    private float mSizeRatio = 0.20f;
    // 边界 padding
    private float mBorderPadding = 0.50f;
    // 中心 padding
    private float mCenterPadding = 0.30f;

 /*   private int mBackgroundColor = Color.DKGRAY;
    private int mThemeColor = Color.WHITE;
    private int mFadeColor = Color.parseColor("#FF44AA");*/;
    private int mBackgroundColor = Color.BLACK;
    private int mThemeColor = Color.WHITE;
    private int mFadeColor = Color.DKGRAY;



    // 花瓣数量
    private int mPetalCount = 12;
    // 花瓣厚度
    private int mPetalThickness = 8;
    // 花瓣透明的
    private float mPetalAlpha = 0.8f;

    // 圆角角度
    private float mBackgroundCornerRadius = 30f;
    // 背景透明度
    private float mBackgroundAlpha = 0.5f;

    // 旋转方向
    private int mDirection = ACProgressConstant.DIRECT_CLOCKWISE;
    // 旋转速度
    private float mSpeed = 12f;

    // 文字
    private String mText = null;
    private int mTextColor = Color.WHITE;
    private float mTextAlpha = 0.5f;
    private float mTextSize = 40f;
    private int mTextMarginTop = 0;
    // 添加文字后是否修正为矩形
    private boolean mTextExpandWidth = true;

    public Builder(Context context) {
      mContext = context;
    }

    public Builder(Context context, int theme) {
      mContext = context;
      mTheme = theme;
    }

    public Builder sizeRatio(float ratio) {
      mSizeRatio = ratio;
      return this;
    }

    public Builder borderPadding(float padding) {
      mBorderPadding = padding;
      return this;
    }

    public Builder centerPadding(float padding) {
      mCenterPadding = padding;
      return this;
    }

    public Builder bgColor(int bgColor) {
      mBackgroundColor = bgColor;
      return this;
    }

    public Builder themeColor(int themeColor) {
      mThemeColor = themeColor;
      return this;
    }

    public Builder fadeColor(int fadeColor) {
      mFadeColor = fadeColor;
      return this;
    }

    public Builder petalCount(int petalCount) {
      mPetalCount = petalCount;
      return this;
    }

    public Builder petalThickness(int thickness) {
      mPetalThickness = thickness;
      return this;
    }

    public Builder petalAlpha(float alpha) {
      mPetalAlpha = alpha;
      return this;
    }

    public Builder bgCornerRadius(float cornerRadius) {
      mBackgroundCornerRadius = cornerRadius;
      return this;
    }

    public Builder bgAlpha(float alpha) {
      mBackgroundAlpha = alpha;
      return this;
    }

    public Builder direction(int direction) {
      mDirection = direction;
      return this;
    }

    public Builder speed(float speed) {
      mSpeed = speed;
      return this;
    }

    public Builder text(String text) {
      mText = text;
      return this;
    }

    public Builder textSize(int textSize) {
      mTextSize = textSize;
      return this;
    }

    public Builder textColor(int textColor) {
      mTextColor = textColor;
      return this;
    }

    public Builder textAlpha(float textAlpha) {
      mTextAlpha = textAlpha;
      return this;
    }

    public Builder textMarginTop(int textMarginTop) {
      mTextMarginTop = textMarginTop;
      return this;
    }

    public Builder isTextExpandWidth(boolean isTextExpandWidth) {
      mTextExpandWidth = isTextExpandWidth;
      return this;
    }

    public ACProgressFlower build() {
      return new ACProgressFlower(this);
    }
  }
}