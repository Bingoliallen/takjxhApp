package takjxh.android.com.taapp.activityui.activity;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;


import butterknife.BindView;
import takjxh.android.com.commlibrary.presenter.impl.BasePresenter;
import takjxh.android.com.commlibrary.utils.BarUtil;
import takjxh.android.com.commlibrary.utils.ToastUtil;
import takjxh.android.com.commlibrary.view.activity.BaseActivity;
import takjxh.android.com.taapp.R;
import takjxh.android.com.taapp.view.NormalTitleBar;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-12-24 11:11
 * @Description:
 **/
public class GrDetailInputActivity extends BaseActivity {

    public static void startAction(Activity activity, String content, String title, int requestCode) {
        Intent intent = new Intent(activity, GrDetailInputActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("content", content);
        activity.startActivityForResult(intent, requestCode);
        activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }


    @BindView(R.id.ntb)
    NormalTitleBar ntb;

    @BindView(R.id.clue_content)
    EditText clue_content;

    private String title;
    private String content;

    /**
     * 返回布局文件
     */
    @Override
    protected int getContentViewId() {
        return R.layout.activity_grdetail_input;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void beforeSetContentView() {
        super.beforeSetContentView();
        BarUtil.hideActionBar(this);
    }


    /**
     * 初始化控件
     */
    @Override
    protected void initView() {
        super.initView();
        content = getIntent().getStringExtra("content");
        title = getIntent().getStringExtra("title");
        ntb = findViewById(R.id.ntb);
        ntb.setTitleText(title);
        ntb.setTvLeftVisiable(true);
        ntb.setRightTitle("保存");
        ntb.setRightTitleVisibility(true);
        ntb.setOnRightTextListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String input = clue_content.getText().toString().trim();
                if (TextUtils.isEmpty(input)) {
                    ToastUtil.showToast(GrDetailInputActivity.this, "请输入" + title);
                    return;
                }
                Intent intent = new Intent();
                intent.putExtra("input", input);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        ntb.setOnBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        clue_content.setText(content);
        clue_content.setSelection(clue_content.getText().length());

    }


}
