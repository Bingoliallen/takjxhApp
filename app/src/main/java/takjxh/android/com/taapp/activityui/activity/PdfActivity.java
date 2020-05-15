package takjxh.android.com.taapp.activityui.activity;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import com.ycbjie.webviewlib.ProgressWebView;

import butterknife.BindView;
import takjxh.android.com.commlibrary.presenter.impl.BasePresenter;
import takjxh.android.com.commlibrary.utils.BarUtil;
import takjxh.android.com.commlibrary.view.activity.BaseActivity;
import takjxh.android.com.taapp.R;
import takjxh.android.com.taapp.view.NormalTitleBar;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2020-05-14 12:44
 * @Description:
 **/
public class PdfActivity extends BaseActivity implements View.OnClickListener {

    public static void startAction(Activity activity, String url) {
        Intent intent = new Intent(activity, PdfActivity.class);
        intent.putExtra("url", url);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @BindView(R.id.ntb)
    NormalTitleBar ntb;

    @BindView(R.id.web_view)
    ProgressWebView web_view;

    private String url;

    /**
     * 返回布局文件
     */
    @Override
    protected int getContentViewId() {
        return R.layout.activity_pdf;
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
        url = getIntent().getStringExtra("url");

        ntb = findViewById(R.id.ntb);
        ntb.setTitleText("PDF在线预览");
        ntb.setTvLeftVisiable(true);

        ntb.setOnBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        web_view.getWebView().loadUrl(url);
    }

    /**
     * 设置事件
     */
    @Override
    protected void initEvent() {
        super.initEvent();
    }

    /**
     * 初始化数据
     */
    @Override
    protected void initData() {
        super.initData();
    }


    @Override
    public void onClick(View v) {

    }


    @Override
    public void onResume() {
        super.onResume();
        if (web_view != null && web_view.getWebView()!=null) {
            web_view.getWebView().getSettings().setJavaScriptEnabled(true);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (web_view != null && web_view.getWebView()!=null) {
            web_view.getWebView().getSettings().setJavaScriptEnabled(false);
        }
    }
   /* 关于destroy销毁逻辑*/
    @Override
    protected void onDestroy() {
        if (web_view != null && web_view.getWebView()!=null) {
            web_view.getWebView().destroy();
        }
        super.onDestroy();
    }

}
