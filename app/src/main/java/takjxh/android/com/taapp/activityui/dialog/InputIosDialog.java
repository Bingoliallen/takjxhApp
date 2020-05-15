package takjxh.android.com.taapp.activityui.dialog;

import android.support.annotation.StringRes;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import takjxh.android.com.taapp.R;
import takjxh.android.com.taapp.activityui.base.BaseDialog;
import takjxh.android.com.taapp.activityui.base.MyDialogFragment;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2020-05-12 21:41
 * @Description:
 **/
public class InputIosDialog {

    public static final class Builder
            extends MyDialogFragment.Builder<Builder>
            implements
            View.OnClickListener {

        private final ImageView mCloseView;
        private final TextView mTitleView;
        private final TextView mCancelView;
        private final TextView mConfirmView;

        private EditText tv_GSMC;
        private View mView3;
        private AutoCompleteTextView tv_sshy;


        private OnListener mListener;
        private boolean mAutoDismiss = true;

        private String key = "";
        private String sshyID = "";

        @SuppressWarnings("all")
        public Builder(FragmentActivity activity) {
            super(activity);
            setContentView(R.layout.dialog_input_ios);

            tv_GSMC = findViewById(R.id.tv_GSMC);
            mView3 = findViewById(R.id.mView3);
            tv_sshy = findViewById(R.id.tv_sshy);

            mTitleView = findViewById(R.id.tv_date_title);
            mCancelView = findViewById(R.id.tv_date_cancel);
            mConfirmView = findViewById(R.id.tv_date_confirm);
            mCloseView = findViewById(R.id.iv_pay_close);

            mView3.setOnClickListener(this);

            mCloseView.setOnClickListener(this);
            mCancelView.setOnClickListener(this);
            mConfirmView.setOnClickListener(this);


        }

        public Builder setTitleBt(CharSequence text) {
            tv_GSMC.setText(text);
            tv_GSMC.setSelection(tv_GSMC.getText().length());
            return this;
        }
        public Builder setSshyID(CharSequence text,String sshyid) {
            tv_sshy.setText(text);
            sshyID=sshyid;
            return this;
        }
        public Builder setSshyID(String sshyid) {
            sshyID=sshyid;
            return this;
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


        @Override
        public void onClick(View v) {


            if (mListener != null) {
                if (v == mConfirmView) {
                    if (mAutoDismiss) {
                        dismiss();
                    }
                    mListener.onSelected(getDialog(),tv_GSMC.getText().toString().trim(),sshyID,tv_sshy.getText().toString().trim());
                } else if (v == mCancelView) {
                    if (mAutoDismiss) {
                        dismiss();
                    }
                    mListener.onCancel(getDialog());
                } else if (v == mCloseView) {
                    if (mAutoDismiss) {
                        dismiss();
                    }
                    mListener.onCancel(getDialog());
                } else if (v == mView3) {
                    mListener.onSel(tv_sshy);
                }
            }
        }
    }

    public interface OnListener {

        /**
         * 选择完后回调
         *
         * @param string
         */
        void onSelected(BaseDialog dialog,String string,String sshyID,String sshyName);

        /**
         * 点击取消时回调
         */
        void onCancel(BaseDialog dialog);

        void onSel(AutoCompleteTextView tv_sshy);
    }


}
