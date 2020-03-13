package takjxh.android.com.taapp.activityui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
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

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import takjxh.android.com.commlibrary.utils.BarUtil;
import takjxh.android.com.commlibrary.utils.DateTimeUtil;
import takjxh.android.com.commlibrary.utils.L;
import takjxh.android.com.commlibrary.utils.ToastUtil;
import takjxh.android.com.commlibrary.view.activity.BaseActivity;
import takjxh.android.com.taapp.R;
import takjxh.android.com.taapp.activityui.bean.StudysDetailBean;
import takjxh.android.com.taapp.activityui.bean.TrainsDetailBean;
import takjxh.android.com.taapp.activityui.presenter.XxspPresenter;
import takjxh.android.com.taapp.activityui.presenter.impl.IXxspPresenter;
import takjxh.android.com.taapp.utils.DisplayUtil;
import takjxh.android.com.taapp.view.NormalTitleBar;

/**
 * 类名称：学习视频详情
 *
 * @Author: libaibing
 * @Date: 2019-10-17 9:46
 * @Description:
 **/
public class XxspActivity extends BaseActivity<XxspPresenter> implements IXxspPresenter.IView {

    public static void lunch(Activity activity, String id, String url, String title, String type) {
        if (activity != null) {
            Intent intent = new Intent(activity, XxspActivity.class);
            intent.putExtra("id", id);
            intent.putExtra("url", url);
            intent.putExtra("title", title);
            intent.putExtra("type", type);
            activity.startActivity(intent);
        }
    }

    /* @BindView(R.id.btn_login)
   Button btn_login;
 @BindView(R.id.recycler_view)
   RecyclerView recycler_view;*/
    @BindView(R.id.video_player)
    VideoPlayer videoPlayer;
    private VideoPlayerController controller;


    @BindView(R.id.tvtitle)
    TextView tvtitle;

    @BindView(R.id.tvtime)
    TextView tvtime;
    @BindView(R.id.ntb)
    NormalTitleBar ntb;
    private ScrollWebView mWebView;
    private ProgressBar pb;


    private String type;
    private String id;
    private String url;
    private String name;
    private int typeWZ=0;
 /*   private PlAdapter mPlAdapter;
    private List<PlsBean> mList = new ArrayList<>();*/

    private String videoTitle = "";


    private int totalTime = 0;//耗时多少秒
    private boolean isOpenHttp=true;
    public final int MSG_DONE_SUCCESS = 1001;

    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_DONE_SUCCESS:
                    if(isOpenHttp){
                        if ("1".equals(type)) {
                            isOpenHttp=false;
                            Map<String, Object> searchRequest=new HashMap<>();
                            searchRequest.put("type",typeWZ);
                            mPresenter.studyreaddone("", searchRequest);
                        } else if ("2".equals(type)) {
                            isOpenHttp=false;
                            Map<String, Object> searchRequest=new HashMap<>();
                            searchRequest.put("type",typeWZ);
                            mPresenter.trainreaddone("", searchRequest);
                        }
                    }




                    break;
            }

        }
    };

    private class MyRunnable implements Runnable {
        @Override
        public void run() {
            //  mTvTime5.setText(getTime());
            totalTime++;
            if(typeWZ==1){
                if (totalTime > 2 * 60 ) {

                    Message msg = new Message();
                    msg.what = MSG_DONE_SUCCESS;
                    mHandler.sendMessage(msg);

                }
            }else if(typeWZ==2){
                if (totalTime > 3 * 60 ) {

                    Message msg = new Message();
                    msg.what = MSG_DONE_SUCCESS;
                    mHandler.sendMessage(msg);

                }
            }

            mHandler.postDelayed(this, 1000);
        }
    }

    private MyRunnable mRunnable = null;


    /**
     * 返回布局文件
     */
    @Override
    protected int getContentViewId() {
        return R.layout.activity_xxsp;
    }

    @Override
    protected XxspPresenter createPresenter() {
        return new XxspPresenter(this);
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
            type = intent.getStringExtra("type");
            id = intent.getStringExtra("id");
            url = intent.getStringExtra("url");
            name = intent.getStringExtra("title");
        } else {
            url = "";
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
        pb = findViewById(R.id.pb);
        pb.setVisibility(View.GONE);

       /* recycler_view.setLayoutManager(new LinearLayoutManager(NewsDetailActivity.this, LinearLayoutManager.VERTICAL, false));
        recycler_view.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.top = ViewUtil.dp2px(NewsDetailActivity.this, 0);
                outRect.bottom = ViewUtil.dp2px(NewsDetailActivity.this, 0);
            }
        });
        mPlAdapter = new PlAdapter(NewsDetailActivity.this);
        recycler_view.setAdapter(mPlAdapter);
        mList.add(new PlsBean());
        mList.add(new PlsBean());
        mList.add(new PlsBean());
        mList.add(new PlsBean());
        mList.add(new PlsBean());
        mList.add(new PlsBean());
        mList.add(new PlsBean());
        mPlAdapter.set(mList);*/

        if (!TextUtils.isEmpty(url)) {
            typeWZ=2;
            videoPlayer.setVisibility(View.VISIBLE);
            setVideoPlayer(url);
        } else {
            typeWZ=1;
            videoPlayer.setVisibility(View.GONE);
        }


    }

    /**
     * 设置事件
     */
    @Override
    protected void initEvent() {
        super.initEvent();
        /*btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlActivity.startAction(NewsDetailActivity.this);
            }
        });*/
    }

    /**
     * 初始化数据
     */
    @Override
    protected void initData() {
        super.initData();
        //  mWebView.loadUrl(url);
        if ("1".equals(type)) {
            mPresenter.studysdetail("", id);
        } else if ("2".equals(type)) {
            mPresenter.trainsdetail("", id);
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
        L.d("XxspActivity", "XxspActivity----" + "onStop");
    }


    @Override
    public void onBackPressed() {
        if (VideoPlayerManager.instance().onBackPressed()) {
            return;
        } else {
            VideoPlayerManager.instance().releaseVideoPlayer();
            L.d("XxspActivity", "XxspActivity----" + "退出activity");
        }
        super.onBackPressed();
        L.d("XxspActivity", "XxspActivity----" + "onBackPressed");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        VideoPlayerManager.instance().resumeVideoPlayer();
        L.d("XxspActivity", "XxspActivity----" + "onRestart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        L.d("XxspActivity", "XxspActivity----" + "onPause");
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
        if (mRunnable != null) {
            mHandler.removeCallbacks(mRunnable);
            mRunnable = null;
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
            pElement.attr("max-width", String.valueOf(DisplayUtil.getScreenWidth(XxspActivity.this) + "px"))
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
    public void studysdetailSuccess(StudysDetailBean.StudyBean data) {
        if (data == null) {
            return;
        }
        tvtitle.setText(data.getTitle());
        if (!TextUtils.isEmpty(data.getTime())) {
            String ds = DateTimeUtil.formatDateTime(DateTimeUtil.parseDate(data.getTime(), DateTimeUtil.DF_YYYY_MM_DD_HH_MM_SS), DateTimeUtil.DF_YYYY_MM_DD_HH_MM_SS1);
            tvtime.setText("作者：" + data.getCreateUser() + "    " + "发布时间：" + ds);
        } else {
            tvtime.setText("作者：" + data.getCreateUser() + "    " + "发布时间：  ");
        }
        setWebView(mWebView, data.getContent());
        if (TextUtils.isEmpty(url)) {
            if(!TextUtils.isEmpty(data.getVideo())){
                typeWZ=2;
                videoPlayer.setVisibility(View.VISIBLE);
                setVideoPlayer(data.getVideo());
            }else {
                typeWZ=1;
                videoPlayer.setVisibility(View.GONE);
            }

        }

        totalTime = 0;
        if (mRunnable != null) {
            mHandler.removeCallbacks(mRunnable);
            mRunnable = null;
        }
        if (mRunnable == null) {
            mRunnable = new MyRunnable();
            mHandler.postDelayed(mRunnable, 1000);
        }
    }

    @Override
    public void trainsdetailSuccess(TrainsDetailBean.TrainBean data) {
        if (data == null) {
            return;
        }
        tvtitle.setText(data.getTitle());
        if (!TextUtils.isEmpty(data.getTime())) {
            String ds = DateTimeUtil.formatDateTime(DateTimeUtil.parseDate(data.getTime(), DateTimeUtil.DF_YYYY_MM_DD_HH_MM_SS), DateTimeUtil.DF_YYYY_MM_DD_HH_MM_SS1);
            tvtime.setText("作者：" + data.getCreateUser() + "    " + "发布时间：" + ds);
        } else {
            tvtime.setText("作者：" + data.getCreateUser() + "    " + "发布时间：  ");
        }
        setWebView(mWebView, data.getContent());
        if (TextUtils.isEmpty(url)) {
            if (!TextUtils.isEmpty(data.getVideo())) {
                typeWZ=2;
                videoPlayer.setVisibility(View.VISIBLE);
                setVideoPlayer(data.getVideo());
            } else {
                typeWZ=1;
                videoPlayer.setVisibility(View.GONE);
            }
        }

        totalTime = 0;
        if (mRunnable != null) {
            mHandler.removeCallbacks(mRunnable);
            mRunnable = null;
        }
        if (mRunnable == null) {
            mRunnable = new MyRunnable();
            mHandler.postDelayed(mRunnable, 1000);
        }

    }

    @Override
    public void studyreaddoneSuccess(String data) {
        ToastUtil.showToast(this,data);
        isOpenHttp=false;
    }

    @Override
    public void trainreaddoneSuccess(String data) {
        ToastUtil.showToast(this,data);
        isOpenHttp=false;
    }

    @Override
    public void studyreaddoneFailed() {
        isOpenHttp=true;
    }

    @Override
    public void trainreaddoneFailed() {
        isOpenHttp=true;
    }

    /*public class WebChromeClient extends android.webkit.WebChromeClient {
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
        L.d("XxspActivity", "视频链接" + urls);
        //设置播放类型
        videoPlayer.setPlayerType(ConstantKeys.IjkPlayerType.TYPE_IJK);
        //设置视频地址和请求头部
        videoPlayer.setUp(urls, null);
        //创建视频控制器
        controller = new VideoPlayerController(this);
        controller.setTitle(videoTitle);
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
                L.d("XxspActivity", "setOnPlayerTypeListener" + "onFullScreen");
            }

            /**
             * 切换到小窗口播放监听
             */
            @Override
            public void onTinyWindow() {
                L.d("XxspActivity", "setOnPlayerTypeListener" + "onTinyWindow");
            }

            /**
             * 切换到正常播放监听
             */
            @Override
            public void onNormal() {
                L.d("XxspActivity", "setOnPlayerTypeListener" + "onNormal");
            }
        });
        //设置视频控制器
        videoPlayer.setController(controller);
        //是否从上一次的位置继续播放
        videoPlayer.continueFromLastPosition(true);
        //设置播放速度
        videoPlayer.setSpeed(1.0f);

        int maxVolume = videoPlayer.getMaxVolume();
        L.d("XxspActivity", "视频播放器" + maxVolume);
    }


}
