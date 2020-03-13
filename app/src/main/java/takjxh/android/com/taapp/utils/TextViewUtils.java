package takjxh.android.com.taapp.utils;

import android.widget.TextView;

public class TextViewUtils {
    public static String NULL = "暂无";

    public static void setText(TextView... textView) {
        if (textView == null) {
            return;
        }
        for (TextView view : textView) {
            setText(view);
        }
    }

    public static void setText(TextView textView) {
        if (textView == null) {
            return;
        }
        textView.setText(textView.getText().toString().replaceAll("null", "暂无"));
    }
}
