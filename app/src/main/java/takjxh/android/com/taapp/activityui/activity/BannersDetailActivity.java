package takjxh.android.com.taapp.activityui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Build;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.ns.yc.ycutilslib.webView.ScrollWebView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import takjxh.android.com.commlibrary.utils.BarUtil;
import takjxh.android.com.commlibrary.utils.DateTimeUtil;
import takjxh.android.com.commlibrary.utils.ViewUtil;
import takjxh.android.com.commlibrary.view.activity.BaseActivity;
import takjxh.android.com.taapp.R;
import takjxh.android.com.taapp.activityui.adapter.PlAdapter;
import takjxh.android.com.taapp.activityui.bean.BannnerDetailBean;
import takjxh.android.com.taapp.activityui.bean.QaAnswerListBean;
import takjxh.android.com.taapp.activityui.presenter.BannersDetailPresenter;
import takjxh.android.com.taapp.activityui.presenter.impl.IBannersDetailPresenter;
import takjxh.android.com.taapp.utils.DisplayUtil;
import takjxh.android.com.taapp.view.NormalTitleBar;

import static android.view.View.GONE;

/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-11-04 16:21
 * @Description:
 **/
public class BannersDetailActivity extends BaseActivity<BannersDetailPresenter> implements IBannersDetailPresenter.IView{

    public static void lunch(Activity activity, String id, String img, String title) {
        if (activity != null) {
            Intent intent = new Intent(activity, BannersDetailActivity.class);
            intent.putExtra("id", id);
            intent.putExtra("img", img);
            intent.putExtra("title", title);
            activity.startActivity(intent);
        }
    }

    @BindView(R.id.btn_login)
    Button btn_login;
    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;
    @BindView(R.id.tvtitle)
    TextView tvtitle;
    @BindView(R.id.tvtime)
    TextView tvtime;


    @BindView(R.id.ntb)
    NormalTitleBar ntb;

    @BindView(R.id.webView)
    ScrollWebView mWebView;
    @BindView(R.id.pb)
    ProgressBar pb;
    @BindView(R.id.icon)
    ImageView icon;



    private String img;
    private String id;
    private String name;
    private PlAdapter mPlAdapter;
    private List<QaAnswerListBean.QaAnswersBean> mList = new ArrayList<>();

    /**
     * 返回布局文件
     */
    @Override
    protected int getContentViewId() {
        return R.layout.activity_newsdetail_ui;
    }

    @Override
    protected BannersDetailPresenter createPresenter() {
        return new BannersDetailPresenter(this);
    }

    @Override
    protected void beforeSetContentView() {
        super.beforeSetContentView();

        BarUtil.hideActionBar(this);
    }

    public static void load(Activity activity, String url, ImageView iv) {    //使用Glide加载圆形ImageView(如头像)时，不要使用占位图
        if (!activity.isDestroyed()) {
            Glide.with(activity).load(url).crossFade().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(iv);
        }
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
            img = intent.getStringExtra("img");
            name = intent.getStringExtra("title");
        } else {
            id = "";
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


        pb.setVisibility(GONE);
        load(BannersDetailActivity.this, img, icon);

        recycler_view.setLayoutManager(new LinearLayoutManager(BannersDetailActivity.this, LinearLayoutManager.VERTICAL, false));
        recycler_view.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.top = ViewUtil.dp2px(BannersDetailActivity.this, 0);
                outRect.bottom = ViewUtil.dp2px(BannersDetailActivity.this, 0);
            }
        });
        mPlAdapter = new PlAdapter(BannersDetailActivity.this);
        recycler_view.setAdapter(mPlAdapter);

        mPlAdapter.set(mList);

    }

    /**
     * 设置事件
     */
    @Override
    protected void initEvent() {
        super.initEvent();
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /*  PlActivity.startAction(BannersDetailActivity.this);*/
            }
        });
    }

    /**
     * 初始化数据
     */
    @Override
    protected void initData() {
        super.initData();
        mPresenter.bannnerdetail(id);
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
            pElement.attr("max-width", String.valueOf(DisplayUtil.getScreenWidth(BannersDetailActivity.this) + "px"))
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
    public void bannnerdetailSuccess(BannnerDetailBean.DetailBean data) {
        if (data == null) {
            return;
        }
        tvtitle.setText(data.getTitle());
        if(!TextUtils.isEmpty(data.getTime())){
            String ds= DateTimeUtil.formatDateTime(DateTimeUtil.parseDate(data.getTime(), DateTimeUtil.DF_YYYY_MM_DD_HH_MM_SS), DateTimeUtil.DF_YYYY_MM_DD_HH_MM_SS1);
            tvtime.setText("作者："+data.getCreateUser()+"    " +"发布时间："+ds);
        }else{
            tvtime.setText("作者："+data.getCreateUser()+"    " +"发布时间：  ");
        }
        setWebView(mWebView, data.getContent());
    }


    @Override
    public Context getContext() {
        return this;
    }
}

