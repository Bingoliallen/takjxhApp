package takjxh.android.com.taapp.listener;

import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import takjxh.android.com.taapp.QbApplication;

/**
 * 输入框长度监听
 *
 * @Author: libaibing
 * @Date: 2019-01-18 12:54
 * @Description:
 **/

public class TVEditTextChangeListener implements TextWatcher {
    private int maxLen = 0;
    private EditText clueTitle;

    public TVEditTextChangeListener(int maxLen, EditText clueTitle) {
        this.maxLen = maxLen;
        this.clueTitle = clueTitle;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//            L.i("MyEditTextChangeListener"+"beforeTextChanged---"+s.toString());
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
//            L.i("MyEditTextChangeListener" + "onTextChanged---" + s.toString());
        if (s.toString().length() > maxLen) {
            Toast.makeText(QbApplication.getAppContext(), "标题" + "字数不得超过100", Toast.LENGTH_SHORT).show();
        }

        Editable editable = clueTitle.getText();
        int len = editable.length();

        if (len > maxLen) {
            int selEndIndex = Selection.getSelectionEnd(editable);
            String str = editable.toString();
            //截取新字符串
            String newStr = str.substring(0, maxLen);
            clueTitle.setText(newStr);
            editable = clueTitle.getText();

            //新字符串的长度
            int newLen = editable.length();
            //旧光标位置超过字符串长度
            if (selEndIndex > newLen) {
                selEndIndex = editable.length();
            }
            //设置新光标所在的位置
            Selection.setSelection(editable, selEndIndex);

        }
    }

    @Override
    public void afterTextChanged(Editable s) {
//            L.i("MyEditTextChangeListener"+"afterTextChanged---");
    }
}
