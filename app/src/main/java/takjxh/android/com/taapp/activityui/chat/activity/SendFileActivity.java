package takjxh.android.com.taapp.activityui.chat.activity;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.TintTypedArray;


import takjxh.android.com.taapp.R;
import takjxh.android.com.taapp.activityui.chat.controller.SendFileController;
import takjxh.android.com.taapp.activityui.chat.view.SendFileView;


public class SendFileActivity extends FragmentActivity {

    private SendFileView mView;
    private SendFileController mController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_file);

        @SuppressLint("RestrictedApi") TintTypedArray a = TintTypedArray.obtainStyledAttributes(this,
                null, android.support.v7.appcompat.R.styleable.ActionBar, android.support.v7.appcompat.R.attr.actionBarStyle, 0);
        @SuppressLint("RestrictedApi") Drawable drawable = a.getDrawable(android.support.v7.appcompat.R.styleable.ActionBar_homeAsUpIndicator);
        if(drawable!=null){
            drawable.setColorFilter(Color.WHITE, PorterDuff.Mode.MULTIPLY);

        }

        mView = (SendFileView) findViewById(R.id.send_file_view);
        mView.initModule();
        if(drawable!=null){
            mView.setmReturnBtn(drawable);
        }
        mController = new SendFileController(this, mView);
        mView.setOnClickListener(mController);
        mView.setOnPageChangeListener(mController);

        //设置文件选择界面viewpager能左右滑动..在 会话 通讯录 我  主界面是不能左右滑动的
        mView.setScroll(true);
    }

    public FragmentManager getSupportFragmentManger() {
        // TODO Auto-generated method stub
        return getSupportFragmentManager();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mView.setScroll(false);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 10203) {
            if (resultCode == 13) {
                if(data != null){
                    setResult(27, new Intent(data));
                    finish();
                }
            }
        }
    }
}
