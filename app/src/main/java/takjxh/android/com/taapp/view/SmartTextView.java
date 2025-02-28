package takjxh.android.com.taapp.view;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;

/**
 *    desc   : 智能显示的 TextView
 */
public final class SmartTextView extends AppCompatTextView implements TextWatcher {

    public SmartTextView(Context context) {
        super(context);
        initialize();
    }

    public SmartTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }

    public SmartTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize();
    }

    private void initialize() {
        addTextChangedListener(this);
        // 触发一次监听
        afterTextChanged(null);
    }

    /**
     * {@link TextWatcher}
     */

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {}

    @Override
    public void afterTextChanged(Editable s) {
        // 判断当前有没有设置文本达到自动隐藏和显示的效果
        if ("".equals(getText().toString())) {
            if (getVisibility() != GONE) {
                setVisibility(GONE);
            }
        } else {
            if (getVisibility() != VISIBLE) {
                setVisibility(VISIBLE);
            }
        }
    }
}