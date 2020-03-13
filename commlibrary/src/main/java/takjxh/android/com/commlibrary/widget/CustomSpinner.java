package takjxh.android.com.commlibrary.widget;


import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


@SuppressLint("AppCompatCustomView")
public class CustomSpinner extends Spinner {
    private int mSelection = -1;

    public CustomSpinner(Context context) {
        super(context);
        init();
    }


    public CustomSpinner(Context context, int mode) {
        super(context, mode);
        init();
    }

    public CustomSpinner(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomSpinner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public CustomSpinner(Context context, AttributeSet attrs, int defStyleAttr, int mode) {
        super(context, attrs, defStyleAttr, mode);
        init();
    }



    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void init() {
        Class<?> myClass = Spinner.class;
        try {
            Class<?>[] params = new Class[1];
            params[0] = OnItemClickListener.class;
            Method m = myClass.getDeclaredMethod("setOnItemClickListenerInt", params);
            m.setAccessible(true);
            m.invoke(this, new OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Class<?> myClass = AdapterView.class;
                    try {
                        Field field = myClass.getDeclaredField("mOldSelectedPosition");
                        field.setAccessible(true);
                        field.setInt(CustomSpinner.this, AdapterView.INVALID_POSITION);
                    } catch (NoSuchFieldException | IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setSelection(int position, boolean animate) {

        mSelection = position;
        if (mSelection != -1) {
            super.setSelection(position, animate);
        }

    }

    @Override
    public int getSelectedItemPosition() {
        return mSelection;
    }

    @Override
    public void setSelection(int position) {

        mSelection = position;
        if (mSelection != -1) {
            super.setSelection(position);
        }

    }

}
