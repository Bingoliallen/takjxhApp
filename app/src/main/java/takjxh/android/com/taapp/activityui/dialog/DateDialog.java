package takjxh.android.com.taapp.activityui.dialog;

import android.support.annotation.StringRes;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.TextView;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import takjxh.android.com.taapp.R;
import takjxh.android.com.taapp.activityui.base.BaseDialog;
import takjxh.android.com.taapp.activityui.base.MyDialogFragment;
import takjxh.android.com.taapp.view.LoopView;


/**
 *    desc   : 日期选择对话框
 */
public final class DateDialog {

    public static final class Builder
            extends MyDialogFragment.Builder<Builder>
            implements LoopView.LoopScrollListener,
            View.OnClickListener {

        private final int mStartYear = 1920;
        private  int mEndYear = 2019;

        private final TextView mTitleView;
        private final TextView mCancelView;
        private final TextView mConfirmView;

        private final LoopView mYearView;
        private final LoopView mMonthView;
        private final LoopView mDayView;

        private OnListener mListener;
        private boolean mAutoDismiss = true;

        @SuppressWarnings("all")
        public Builder(FragmentActivity activity) {
            super(activity);
            setContentView(R.layout.dialog_date);

            mTitleView = findViewById(R.id.tv_date_title);
            mCancelView = findViewById(R.id.tv_date_cancel);
            mConfirmView = findViewById(R.id.tv_date_confirm);

            mYearView = findViewById(R.id.lv_date_year);
            mMonthView = findViewById(R.id.lv_date_month);
            mDayView = findViewById(R.id.lv_date_day);
            Calendar calendar = Calendar.getInstance();
            mEndYear=calendar.get(Calendar.YEAR);
            // 生产年份
            ArrayList<String> yearData = new ArrayList<>(10);
            for (int i = mStartYear; i <= mEndYear; i++) {
                yearData.add(i + " " + getString(R.string.common_year));
            }
            int monthCurNum=calendar.get(Calendar.MONTH) + 1;
            // 生产月份
            ArrayList<String> monthData = new ArrayList<>(monthCurNum);
            for (int i = 1; i <= monthCurNum; i++) {
                monthData.add(i + " " + getString(R.string.common_month));
            }

            mYearView.setData(yearData);
            mMonthView.setData(monthData);

            mYearView.setLoopListener(this);
            mMonthView.setLoopListener(this);

            mCancelView.setOnClickListener(this);
            mConfirmView.setOnClickListener(this);


            setYear(calendar.get(Calendar.YEAR));
            setMonth(calendar.get(Calendar.MONTH) + 1);
            setDay(calendar.get(Calendar.DAY_OF_MONTH));

            // 生产月份
            monthData = new ArrayList<>(monthCurNum);
            for (int i = 1; i <= monthCurNum; i++) {
                monthData.add(i + " " + getString(R.string.common_month));
            }
            mMonthView.setData(monthData);


            int day =calendar.get(Calendar.DAY_OF_MONTH);
            ArrayList<String> dayData = new ArrayList<>(day);
            for (int i = 1; i <= day; i++) {
              dayData.add(i + " " + getString(R.string.common_day));
            }

            mDayView.setData(dayData);

        }

        public Builder setTitle(@StringRes int id) {
            return setTitle(getString(id));
        }
        public Builder setTitle(CharSequence text) {
            mTitleView.setText(text);
            return this;
        }

        public Builder setCancel(@StringRes int id) {
            return setCancel(getString(id));
        }
        public Builder setCancel(CharSequence text) {
            mCancelView.setText(text);
            return this;
        }

        public Builder setConfirm(@StringRes int id) {
            return setConfirm(getString(id));
        }
        public Builder setConfirm(CharSequence text) {
            mConfirmView.setText(text);
            return this;
        }

        public Builder setListener(OnListener listener) {
            mListener = listener;
            return this;
        }

        public Builder setAutoDismiss(boolean dismiss) {
            mAutoDismiss = dismiss;
            return this;
        }

        /**
         * 不选择天数
         */
        public Builder setIgnoreDay() {
            mDayView.setVisibility(View.GONE);
            return this;
        }

        public Builder setDate(long date) {
            if (date > 0) {
                setDate(new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date(date)));
            }
            return this;
        }

        public Builder setDate(String date) {
            // 20190519
            if (date.matches("\\d{8}")) {
                setYear(date.substring(0, 4));
                setMonth(date.substring(4, 6));
                setDay(date.substring(6, 8));
            // 2019-05-19
            } else if (date.matches("\\d{4}-\\d{2}-\\d{2}")) {
                setYear(date.substring(0, 4));
                setMonth(date.substring(5, 7));
                setDay(date.substring(8, 10));
            }
            return this;
        }

        public Builder setYear(String year) {
            return setYear(Integer.valueOf(year));
        }

        public Builder setYear(int year) {
            int index = year - mStartYear;
            if (index < 0) {
                index = 0;
            } else if (index > mYearView.getSize() - 1) {
                index = mYearView.getSize() - 1;
            }
            mYearView.setInitPosition(index);
            return this;
        }

        public Builder setMonth(String month) {
            return setMonth(Integer.valueOf(month));
        }

        public Builder setMonth(int month) {
            int index = month - 1;
            if (index < 0) {
                index = 0;
            } else if (index > mMonthView.getSize() - 1) {
                index = mMonthView.getSize() - 1;
            }
            mMonthView.setInitPosition(index);
            return this;
        }

        public Builder setDay(String day) {
            return setDay(Integer.valueOf(day));
        }

        public Builder setDay(int day) {
            int index = day - 1;
            if (index < 0) {
                index = 0;
            } else if (index > mDayView.getSize() - 1) {
                index = mDayView.getSize() - 1;
            }
            mDayView.setInitPosition(index);
            return this;
        }

        @Override
        public void onItemSelect(LoopView loopView, int position) {
            // 获取这个月最多有多少天
            Calendar calendar = Calendar.getInstance(Locale.CHINA);

            Calendar calendar1 = Calendar.getInstance();
            int year=calendar1.get(Calendar.YEAR);
            int monthCurNum=calendar1.get(Calendar.MONTH) ;
            if (loopView == mYearView) {
                /*if(year==mStartYear + mYearView.getSelectedItem()){
                    calendar.set(mStartYear + mYearView.getSelectedItem(), monthCurNum, 1);
                }else{
                }*/
                calendar.set(mStartYear + mYearView.getSelectedItem(), mMonthView.getSelectedItem(), 1);



            } else if (loopView == mMonthView) {
                calendar.set(mStartYear + mYearView.getSelectedItem(), mMonthView.getSelectedItem(), 1);
            }


            if(year==mStartYear + mYearView.getSelectedItem()){
                monthCurNum=calendar1.get(Calendar.MONTH) + 1;
            }else{
                monthCurNum=12;
            }
            // 生产月份
            ArrayList<String> monthData = new ArrayList<>(monthCurNum);
            for (int i = 1; i <= monthCurNum; i++) {
                monthData.add(i + " " + getString(R.string.common_month));
            }
            mMonthView.setData(monthData);



            int day = calendar.getActualMaximum(Calendar.DATE);
            if(year==mStartYear + mYearView.getSelectedItem()&&mMonthView.getSelectedItem()==calendar1.get(Calendar.MONTH) ){
                day =calendar1.get(Calendar.DAY_OF_MONTH);
            }
            ArrayList<String> dayData = new ArrayList<>(day);
            for (int i = 1; i <= day; i++) {
                dayData.add(i + " " + getString(R.string.common_day));
            }

            mDayView.setData(dayData);
        }

        /**
         * {@link View.OnClickListener}
         */

        @Override
        public void onClick(View v) {
            if (mAutoDismiss) {
                dismiss();
            }

            if (mListener != null) {
                if (v == mConfirmView) {
                    mListener.onSelected(getDialog(), mStartYear + mYearView.getSelectedItem(), mMonthView.getSelectedItem() + 1, mDayView.getSelectedItem() + 1);
                } else if (v == mCancelView) {
                    mListener.onCancel(getDialog());
                }
            }
        }
    }

    public interface OnListener {

        /**
         * 选择完日期后回调
         *
         * @param year              年
         * @param month             月
         * @param day               日
         */
        void onSelected(BaseDialog dialog, int year, int month, int day);

        /**
         * 点击取消时回调
         */
        void onCancel(BaseDialog dialog);
    }
}