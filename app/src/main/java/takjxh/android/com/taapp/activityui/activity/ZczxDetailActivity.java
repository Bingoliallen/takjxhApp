package takjxh.android.com.taapp.activityui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
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
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ns.yc.ycutilslib.webView.ScrollWebView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.yczbj.ycvideoplayerlib.constant.ConstantKeys;
import org.yczbj.ycvideoplayerlib.controller.VideoPlayerController;
import org.yczbj.ycvideoplayerlib.inter.listener.OnPlayerTypeListener;
import org.yczbj.ycvideoplayerlib.inter.listener.OnVideoBackListener;
import org.yczbj.ycvideoplayerlib.manager.VideoPlayerManager;
import org.yczbj.ycvideoplayerlib.player.VideoPlayer;

import butterknife.BindView;
import takjxh.android.com.commlibrary.utils.BarUtil;
import takjxh.android.com.commlibrary.utils.L;
import takjxh.android.com.commlibrary.view.activity.BaseActivity;
import takjxh.android.com.taapp.R;
import takjxh.android.com.taapp.activityui.bean.PolicyDetailBean;
import takjxh.android.com.taapp.activityui.presenter.ZczxDetailPresenter;
import takjxh.android.com.taapp.activityui.presenter.impl.IZczxDetailPresenter;
import takjxh.android.com.taapp.utils.DisplayUtil;
import takjxh.android.com.taapp.view.NormalTitleBar;


/**
 * 类名称：
 *
 * @Author: libaibing
 * @Date: 2019-10-24 13:06
 * @Description:
 **/
public class ZczxDetailActivity extends BaseActivity<ZczxDetailPresenter> implements IZczxDetailPresenter.IView {

    public static void startAction(Activity activity, String id, String title) {
        Intent intent = new Intent(activity, ZczxDetailActivity.class);
        intent.putExtra("id", id);
        intent.putExtra("title", title);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
    @BindView(R.id.video_player)
    VideoPlayer videoPlayer;
    private VideoPlayerController controller;


    @BindView(R.id.btn_login)
    Button btn_login;
    @BindView(R.id.btn_login1)
    Button btn_login1;

    @BindView(R.id.ntb)
    NormalTitleBar ntb;
    @BindView(R.id.webView)
    ScrollWebView mWebView;
    @BindView(R.id.pb)
    ProgressBar pb;


    @BindView(R.id.tvtitle)
    TextView tvtitle;

    private String id;
    private String title;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_zczx_detail;
    }

    @Override
    protected ZczxDetailPresenter createPresenter() {
        return new ZczxDetailPresenter(this);
    }

    @Override
    protected void beforeSetContentView() {
        super.beforeSetContentView();
        BarUtil.hideActionBar(this);

    }

    @Override
    protected void initView() {
        super.initView();
        id = getIntent().getStringExtra("id");
        title = getIntent().getStringExtra("title");

        ntb = findViewById(R.id.ntb);
        ntb.setTitleText(title);
        ntb.setTvLeftVisiable(true);
        ntb.setOnBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvtitle.setText(title);
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ZjsbtxActivity1.startAction(ZczxDetailActivity.this);
            }
        });
        btn_login1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ZxjgActivity.startAction(ZczxDetailActivity.this);
            }
        });

       // webView.setWebChromeClient(new WebChromeClient());
    }

    @Override
    protected void initData() {
        super.initData();
        loadUrl();
        mPresenter.policydetail("",id);
    }

    public void loadUrl() {
        if (mWebView != null) {
         //   webView.loadUrl(url);
        }
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
        VideoPlayerManager.instance().suspendVideoPlayer();
        L.d("ZczxDetailActivity", "ZczxDetailActivity----" + "onStop");
    }


    @Override
    public void onBackPressed() {
        if (VideoPlayerManager.instance().onBackPressed()) {
            return;
        } else {
            VideoPlayerManager.instance().releaseVideoPlayer();
            L.d("ZczxDetailActivity", "ZczxDetailActivity----" + "退出activity");
        }
        super.onBackPressed();
        L.d("ZczxDetailActivity", "ZczxDetailActivity----" + "onBackPressed");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        VideoPlayerManager.instance().resumeVideoPlayer();
        L.d("ZczxDetailActivity", "ZczxDetailActivity----" + "onRestart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        L.d("ZczxDetailActivity", "ZczxDetailActivity----" + "onPause");
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
        VideoPlayerManager.instance().releaseVideoPlayer();
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
            pElement.attr("max-width", String.valueOf(DisplayUtil.getScreenWidth(ZczxDetailActivity.this) + "px"))
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



   /* public class WebChromeClient extends android.webkit.WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            if (newProgress == 100) {
                pb.setVisibility(GONE);
            } else {
                if (pb.getVisibility() == GONE) {
                    pb.setVisibility(VISIBLE);
                }
                pb.setProgress(newProgress);
            }
            super.onProgressChanged(view, newProgress);
        }
    }*/

    private void setVideoPlayer(String urls) {
        if (videoPlayer == null || urls == null) {
            return;
        }
        L.d("ZczxDetailActivity", "视频链接" + urls);
        //设置播放类型
        videoPlayer.setPlayerType(ConstantKeys.IjkPlayerType.TYPE_IJK);
        //设置视频地址和请求头部
        videoPlayer.setUp(urls, null);
        //创建视频控制器
        controller = new VideoPlayerController(this);
        controller.setTitle("");
        controller.setLoadingType(ConstantKeys.Loading.LOADING_RING);
        controller.imageView().setBackgroundResource(R.color.mode_bg);
        controller.setOnVideoBackListener(new OnVideoBackListener() {
            @Override
            public void onBackClick() {
                onBackPressed();
            }
        });
        controller.setOnPlayerTypeListener(new OnPlayerTypeListener() {
            /**
             * 切换到全屏播放监听
             */
            @Override
            public void onFullScreen() {
                L.d("ZczxDetailActivity", "setOnPlayerTypeListener" + "onFullScreen");
            }

            /**
             * 切换到小窗口播放监听
             */
            @Override
            public void onTinyWindow() {
                L.d("ZczxDetailActivity", "setOnPlayerTypeListener" + "onTinyWindow");
            }

            /**
             * 切换到正常播放监听
             */
            @Override
            public void onNormal() {
                L.d("ZczxDetailActivity", "setOnPlayerTypeListener" + "onNormal");
            }
        });
        //设置视频控制器
        videoPlayer.setController(controller);
        //是否从上一次的位置继续播放
        videoPlayer.continueFromLastPosition(true);
        //设置播放速度
        videoPlayer.setSpeed(1.0f);

        int maxVolume = videoPlayer.getMaxVolume();
        L.d("ZczxDetailActivity", "视频播放器" + maxVolume);
    }


    @Override
    public void policydetailSuccess(PolicyDetailBean.PolicyBean data) {
        if (data == null) {
            return;
        }
        ntb.setTitleText(title);
        tvtitle.setText(data.getTitle());

        setWebView(mWebView, data.getContent());
        if(!TextUtils.isEmpty(data.getVideo())){
            videoPlayer.setVisibility(View.VISIBLE);
            setVideoPlayer(data.getVideo());
        }else {
            videoPlayer.setVisibility(View.GONE);
        }
    }

    @Override
    public void policydetailFailed() {

    }
}
