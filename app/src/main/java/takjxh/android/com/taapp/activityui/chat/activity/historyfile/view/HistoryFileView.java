package takjxh.android.com.taapp.activityui.chat.activity.historyfile.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.TintTypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import takjxh.android.com.commlibrary.BaseAppProfile;
import takjxh.android.com.commlibrary.utils.ShareUtils;
import takjxh.android.com.taapp.R;
import takjxh.android.com.taapp.activityui.chat.view.ScrollControlViewPager;

/**
 * Created by ${chenyn} on 2017/8/24.
 */

public class HistoryFileView extends RelativeLayout {
    private ScrollControlViewPager mViewContainer;
    private ImageButton mReturnBtn;
    private Button[] mBtnArray;
    private int[] mBtnIdArray;
    private ImageView[] mIVArray;
    private int[] mIVIdArray;
    private Context mContext;
    private Button mDeleteBtn;
    private TextView mSelectSize;
    private RelativeLayout mDeleteFileRl;
    private TextView mTv_choose;

    public HistoryFileView(Context context) {
        super(context);
        this.mContext = context;
    }

    public HistoryFileView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
    }

    public HistoryFileView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
    }

    public void initModule() {
        mReturnBtn = (ImageButton) findViewById(R.id.return_btn);
        mViewContainer = (ScrollControlViewPager) findViewById(R.id.viewpager);
        mDeleteFileRl = (RelativeLayout) findViewById(R.id.delete_file_rl);
        mTv_choose = (TextView) findViewById(R.id.tv_choose);

        @SuppressLint("RestrictedApi") TintTypedArray a = TintTypedArray.obtainStyledAttributes(mContext,
                null, android.support.v7.appcompat.R.styleable.ActionBar, android.support.v7.appcompat.R.attr.actionBarStyle, 0);
        @SuppressLint("RestrictedApi") Drawable drawable = a.getDrawable(android.support.v7.appcompat.R.styleable.ActionBar_homeAsUpIndicator);
        if(drawable!=null){

            drawable.setColorFilter(Color.WHITE, PorterDuff.Mode.MULTIPLY);
            mReturnBtn.setImageDrawable(drawable);
        }

        mBtnIdArray = new int[] {R.id.actionbar_album_btn, R.id.actionbar_file_btn,
                R.id.actionbar_video_btn, R.id.actionbar_audio_btn, R.id.actionbar_other_btn};
        mIVIdArray = new int[] {R.id.slipping_1, R.id.slipping_2, R.id.slipping_3,
                R.id.slipping_4, R.id.slipping_5};
        mBtnArray = new Button[mBtnIdArray.length];
        mIVArray = new ImageView[mBtnIdArray.length];
        for (int i = 0; i < mBtnIdArray.length; i++) {
            mBtnArray[i] = (Button) findViewById(mBtnIdArray[i]);
            mIVArray[i] = (ImageView) findViewById(mIVIdArray[i]);
        }
        mIVArray[0].setVisibility(VISIBLE);
        mBtnArray[0].setTextColor(getResources().getColor(R.color.send_file_action_bar_selected));
        mDeleteBtn = (Button) findViewById(R.id.delete_file_btn);
        mSelectSize = (TextView) findViewById(R.id.size_desc);
    }

    @Override
    public void setOnClickListener(View.OnClickListener listener) {
        mReturnBtn.setOnClickListener(listener);
        mDeleteBtn.setOnClickListener(listener);
        mTv_choose.setOnClickListener(listener);
        for (int i = 0; i < mBtnIdArray.length; i++) {
            mBtnArray[i].setOnClickListener(listener);
        }
    }

    public void setOnPageChangeListener(ViewPager.OnPageChangeListener onPageChangeListener) {
        mViewContainer.addOnPageChangeListener(onPageChangeListener);
    }

    public void setViewPagerAdapter(FragmentPagerAdapter adapter) {
        mViewContainer.setAdapter(adapter);
    }

    public void setCurrentItem(int index) {
        mViewContainer.setCurrentItem(index);
        for (int i = 0; i < mBtnIdArray.length; i++) {
            if (i == index) {
                mIVArray[i].setVisibility(VISIBLE);
                mBtnArray[i].setTextColor(getResources().getColor(R.color.send_file_action_bar_selected));
            } else {
                mIVArray[i].setVisibility(INVISIBLE);
                mBtnArray[i].setTextColor(getResources().getColor(R.color.send_file_action_bar));
            }
        }
    }


    public void setScroll(boolean isScroll) {
        mViewContainer.setScroll(isScroll);
    }

    public void updateSelectedState(int selectedNum) {
        String sendStr;
        if (selectedNum != 0) {
            sendStr = mContext.getString(R.string.already_select) + "(" + selectedNum + ")";
        } else {
            sendStr = mContext.getString(R.string.already_select);
        }
        mSelectSize.setText(sendStr);
    }

    public void setDeleteRl() {
        if (mTv_choose.getText().equals("选择")) {
           // SharePreferenceManager.setShowCheck(true);
            ShareUtils.putBoolean(BaseAppProfile.getApplication(), "jchat_isShowCheck", true);

            mTv_choose.setText("取消");
            mDeleteFileRl.setVisibility(VISIBLE);
        }else {
           // SharePreferenceManager.setShowCheck(false);
            ShareUtils.putBoolean(BaseAppProfile.getApplication(), "jchat_isShowCheck", false);

            mTv_choose.setText("选择");
            mDeleteFileRl.setVisibility(GONE);
        }
    }
}
