package takjxh.android.com.taapp.activityui.base;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import butterknife.ButterKnife;

/**
 *    desc   : 项目中的 Dialog 基类
 */
public final class MyDialogFragment {

    public static class Builder<B extends MyDialogFragment.Builder>
            extends BaseDialogFragment.Builder<B> {

        public Builder(FragmentActivity activity) {
            super(activity);
        }

        @Override
        public B setContentView(@NonNull View view) {
            // 使用 ButterKnife 注解
            ButterKnife.bind(this, view);
            return super.setContentView(view);
        }

        /**
         * 显示吐司
         */
        public void toast(CharSequence text) {
           // Toast.show(text);
        }

        public void toast(@StringRes int id) {
          //  ToastUtils.show(id);
        }

        public void toast(Object object) {
           // ToastUtils.show(object);
        }
    }
}