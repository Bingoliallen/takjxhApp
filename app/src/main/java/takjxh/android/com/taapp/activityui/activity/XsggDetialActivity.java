package takjxh.android.com.taapp.activityui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.ns.yc.ycutilslib.webView.ScrollWebView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import butterknife.BindView;
import takjxh.android.com.commlibrary.utils.BarUtil;
import takjxh.android.com.commlibrary.view.activity.BaseActivity;
import takjxh.android.com.taapp.R;
import takjxh.android.com.taapp.activityui.bean.AdsDetailBean;
import takjxh.android.com.taapp.activityui.presenter.XsggDetialPresenter;
import takjxh.android.com.taapp.activityui.presenter.impl.IXsggDetialPresenter;
import takjxh.android.com.taapp.utils.DisplayUtil;
import takjxh.android.com.taapp.view.NormalTitleBar;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-05 14:01
 * @Description:
 **/
public class XsggDetialActivity extends BaseActivity<XsggDetialPresenter> implements IXsggDetialPresenter.IView {

    public static void lunch(Activity activity, String id, String title) {
        if (activity != null) {
            Intent intent = new Intent(activity, XsggDetialActivity.class);
            intent.putExtra("id", id);
            intent.putExtra("title", title);
            activity.startActivity(intent);
        }
    }

    @BindView(R.id.tvtitle)
    TextView tvtitle;
    @BindView(R.id.ntb)
    NormalTitleBar ntb;

    private ScrollWebView mWebView;
    private String id;
    private String name;


    /**
     * 返回布局文件
     */
    @Override
    protected int getContentViewId() {
        return R.layout.activity_xsgg_detial;
    }

    @Override
    protected XsggDetialPresenter createPresenter() {
        return new XsggDetialPresenter(this);
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
        Intent intent = getIntent();
        if (intent != null) {
            id = intent.getStringExtra("id");
            name = intent.getStringExtra("title");
        } else {
            name = "";
        }
        tvtitle.setText(name);
        ntb.setTitleText(name);
        ntb.setTvLeftVisiable(true);
        ntb.setOnBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mWebView = findViewById(R.id.webView);

    }

    /**
     * 设置事件
     */
    @Override
    protected void initEvent() {
        super.initEvent();

    }


    @Override
    protected void initData() {
        super.initData();
        mPresenter.adsdetail("", id);

    }


    @Override
    protected void onResume() {
        super.onResume();
        if (mWebView != null) {
            mWebView.getSettings().setJavaScriptEnabled(true);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mWebView != null) {
            mWebView.getSettings().setJavaScriptEnabled(false);
        }

    }

    @Override
    protected void onPause() {
        super.onPause();

    }


    @Override
    protected void onDestroy() {
        if (mWebView != null) {
            mWebView.clearHistory();
            ViewGroup parent = (ViewGroup) mWebView.getParent();
            if (parent != null) {
                parent.removeView(mWebView);
            }
            mWebView.destroy();
            mWebView = null;
        }
        super.onDestroy();
    }


    @Override
    public Context getContext() {
        return this;
    }


    /**
     * 设置img标签下的width为手机屏幕宽度，height自适应
     *
     * @param data html字符串
     * @return 更新宽高属性后的html字符串
     */
    private String getNewData(String data) {
        Document document = Jsoup.parse(data);

        Elements pElements = document.select("p:has(img)");
        for (Element pElement : pElements) {
            pElement.attr("style", "text-align:center");
            pElement.attr("max-width", String.valueOf(DisplayUtil.getScreenWidth(XsggDetialActivity.this) + "px"))
                    .attr("height", "auto");
        }
        Elements imgElements = document.select("img");
        for (Element imgElement : imgElements) {
            //重新设置宽高
            imgElement.attr("max-width", "100%")
                    .attr("height", "auto");
            imgElement.attr("style", "max-width:100%;height:auto");
        }
        Log.i("newData:", document.toString());
        return document.toString();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
            mWebView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    private void setWebView(WebView webview_showpage, String content) {
        webview_showpage.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);//把html中的内容放大webview等宽的一列中
        webview_showpage.getSettings().setJavaScriptEnabled(true);//支持js
        webview_showpage.getSettings().setBuiltInZoomControls(true);// 显示放大缩小
        webview_showpage.getSettings().setSupportZoom(false); //支持缩放，默认为true。是下面那个的前提。

        webview_showpage.getSettings().setDisplayZoomControls(false);
        webview_showpage.setWebChromeClient(new WebChromeClient());
        webview_showpage.setWebViewClient(new WebViewClient());
        webview_showpage.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY); //取消滚动条白边效果
        webview_showpage.getSettings().setDefaultTextEncodingName("UTF-8");
        webview_showpage.getSettings().setBlockNetworkImage(false);

        webview_showpage.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

                //这个是一定要加上那个的,配合scrollView和WebView的height=wrap_content属性使用
                int w = View.MeasureSpec.makeMeasureSpec(0,
                        View.MeasureSpec.UNSPECIFIED);
                int h = View.MeasureSpec.makeMeasureSpec(0,
                        View.MeasureSpec.UNSPECIFIED);
                //重新测量
                webview_showpage.measure(w, h);


            }
        });

        /*WebSettings webSettings = webview_showpage.getSettings();
        //如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
        webSettings.setJavaScriptEnabled(true);
        // 若加载的 html 里有JS 在执行动画等操作，会造成资源浪费（CPU、电量）
        // 在 onStop 和 onResume 里分别把 setJavaScriptEnabled() 给设置成 false 和 true 即可
        //支持插件
        //  webSettings.setPluginsEnabled(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);

        //设置自适应屏幕，两者合用
        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小

        //缩放操作
        webSettings.setSupportZoom(false); //支持缩放，默认为true。是下面那个的前提。
        webSettings.setBuiltInZoomControls(true); //设置内置的缩放控件。若为false，则该WebView不可缩放
        webSettings.setDisplayZoomControls(false); //隐藏原生的缩放控件

        //其他细节操作
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); //关闭webview中缓存
        webSettings.setAllowFileAccess(true); //设置可以访问文件
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口*/

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webview_showpage.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);  //注意安卓5.0以上的权限
        }
        webview_showpage.loadDataWithBaseURL(null, getNewData(content), "text/html", "UTF-8", null);
    }


    @Override
    public void adsdetailSuccess(AdsDetailBean.AdBean data) {
        if (data == null) {
            return;
        }
        tvtitle.setText(data.getTitle());

        setWebView(mWebView, data.getContent());
    }
}
