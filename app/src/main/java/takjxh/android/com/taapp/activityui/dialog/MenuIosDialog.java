package takjxh.android.com.taapp.activityui.dialog;

import android.support.annotation.StringRes;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import takjxh.android.com.taapp.R;
import takjxh.android.com.taapp.activityui.base.BaseDialog;
import takjxh.android.com.taapp.activityui.base.MyDialogFragment;
import takjxh.android.com.taapp.view.LoopView;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-01 11:31
 * @Description:
 **/
public class MenuIosDialog {
    public static final class Builder
            extends MyDialogFragment.Builder<Builder>
            implements LoopView.LoopScrollListener,
            View.OnClickListener {

        private final ImageView mCloseView;
        private final TextView mTitleView;
        private final TextView mCancelView;
        private final TextView mConfirmView;

        private final LoopView mYearView;

        private OnListener mListener;
        private boolean mAutoDismiss = true;
      //  private ArrayList<String> yearData = new ArrayList<>();// mYearView.setData(yearData);

        @SuppressWarnings("all")
        public Builder(FragmentActivity activity) {
            super(activity);
            setContentView(R.layout.dialog_menu_ios);

            mTitleView = findViewById(R.id.tv_date_title);
            mCancelView = findViewById(R.id.tv_date_cancel);
            mConfirmView = findViewById(R.id.tv_date_confirm);
            mCloseView = findViewById(R.id.iv_pay_close);
            mYearView = findViewById(R.id.lv_date_year);


            mYearView.setLoopListener(this);

            mCloseView.setOnClickListener(this);
            mCancelView.setOnClickListener(this);
            mConfirmView.setOnClickListener(this);


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

        public Builder setYear(String year) {
            return setYear(Integer.valueOf(year));
        }

        public Builder setYear(int year) {
            int index = year;
            if (index < 0) {
                index = 0;
            } else if (index > mYearView.getSize() - 1) {
                index = mYearView.getSize() - 1;
            }
            mYearView.setInitPosition(index);
            return this;
        }

        public Builder setData(List<String> data) {
            List<String> index = data;
            if (index == null) {
                index = new ArrayList<>();
            }
            mYearView.setData(index);
            return this;
        }


        @Override
        public void onItemSelect(LoopView loopView, int position) {

            if (loopView == mYearView) {

            }


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
                    mListener.onSelected(getDialog(), mYearView.getSelectedItem(), mYearView.getData().get(mYearView.getSelectedItem()));
                } else if (v == mCancelView) {
                    mListener.onCancel(getDialog());
                } else if (v == mCloseView) {
                    mListener.onCancel(getDialog());
                }
            }
        }
    }

    public interface OnListener {

        /**
         * 选择完日期后回调
         *
         * @param position
         * @param string
         */
        void onSelected(BaseDialog dialog, int position, String string);

        /**
         * 点击取消时回调
         */
        void onCancel(BaseDialog dialog);
    }
}
