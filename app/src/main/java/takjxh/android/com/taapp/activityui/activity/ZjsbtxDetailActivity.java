package takjxh.android.com.taapp.activityui.activity;

import android.Manifest;
import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import takjxh.android.com.commlibrary.utils.BarUtil;
import takjxh.android.com.commlibrary.utils.ToastUtil;
import takjxh.android.com.commlibrary.utils.ViewUtil;
import takjxh.android.com.commlibrary.view.activity.BaseActivity;
import takjxh.android.com.taapp.R;
import takjxh.android.com.taapp.activity.ImageBrowseActivity;
import takjxh.android.com.taapp.activityui.adapter.ZjsbtxDetailAdapter;
import takjxh.android.com.taapp.activityui.bean.ApplyTypeBean;
import takjxh.android.com.taapp.activityui.bean.PolicyApplyDetailBean;
import takjxh.android.com.taapp.activityui.bean.UploadFileBean;
import takjxh.android.com.taapp.activityui.presenter.ZjsbtxDetailPresenter;
import takjxh.android.com.taapp.activityui.presenter.impl.IZjsbtxDetailPresenter;
import takjxh.android.com.taapp.dialog.CustomDialog;
import takjxh.android.com.taapp.utils.DisplayUtil;
import takjxh.android.com.taapp.view.NormalTitleBar;

/**
 * 类名称：政策申报详情
 *
 * @Author: libaibing
 * @Date: 2019-10-29 11:40
 * @Description:
 **/
public class ZjsbtxDetailActivity extends BaseActivity<ZjsbtxDetailPresenter> implements IZjsbtxDetailPresenter.IView, View.OnClickListener, ZjsbtxDetailAdapter.OnZjsbtxDetailClickListener {

    public static void startAction(Activity activity, String id) {
        Intent intent = new Intent(activity, ZjsbtxDetailActivity.class);
        intent.putExtra("id", id);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @BindView(R.id.btn_login)
    Button btn_login;
    @BindView(R.id.ntb)
    NormalTitleBar ntb;
    /*@BindView(R.id.normal_view1)
    SmartRefreshLayout mRefreshLayout;*/
    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;

    private ZjsbtxDetailAdapter mZjsbtxDetailAdapter;
    private List<PolicyApplyDetailBean.ApplyInfoBean> mList = new ArrayList<>();

    private int pageIndex = 1;
    private int pageSize = 20;
    private int total = 0;
    private boolean isLoadMore = false;

    private String id;
    String applyType = "";
    private List<ApplyTypeBean.ApplyTypesBean> mList1 = new ArrayList<>();


    private Button btn_download;
    private Button btn_dialogDelete_cancel;
    private CustomDialog dialogDelete;

    private int requestPermissionsCode = 345;

    private UploadFileBean updata;
    private ArrayList<UploadFileBean> upurls = new ArrayList<>();

    /**
     * 返回布局文件
     */
    @Override
    protected int getContentViewId() {
        return R.layout.activity_zjsbtx_detail;
    }

    @Override
    protected ZjsbtxDetailPresenter createPresenter() {
        return new ZjsbtxDetailPresenter(this);
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
        id = getIntent().getStringExtra("id");

        ntb = findViewById(R.id.ntb);
        ntb.setTitleText("政策申报详情");
        ntb.setTvLeftVisiable(true);
        ntb.setOnBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        dialogDelete = new CustomDialog(this, DisplayUtil.getScreenWidth(this), WindowManager.LayoutParams.WRAP_CONTENT, R.layout.dialog_attachment, R.style.Theme_dialog, Gravity.BOTTOM, R.style.pop_anim_style);
        dialogDelete.setCancelable(false);
        btn_download = (Button) dialogDelete.findViewById(R.id.btn_download);
        btn_download.setVisibility(View.VISIBLE);
        btn_download.setOnClickListener(this);
        dialogDelete.findViewById(R.id.btn_delete).setVisibility(View.GONE);

        btn_dialogDelete_cancel = (Button) dialogDelete.findViewById(R.id.btn_dialogDelete_cancel);
        btn_dialogDelete_cancel.setOnClickListener(this);


        recycler_view.setLayoutManager(new LinearLayoutManager(ZjsbtxDetailActivity.this, LinearLayoutManager.VERTICAL, false));
        recycler_view.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.top = ViewUtil.dp2px(ZjsbtxDetailActivity.this, 0);
                outRect.bottom = ViewUtil.dp2px(ZjsbtxDetailActivity.this, 0);
            }
        });
        mZjsbtxDetailAdapter = new ZjsbtxDetailAdapter(ZjsbtxDetailActivity.this);
        mZjsbtxDetailAdapter.setmClickListener(this);
        recycler_view.setAdapter(mZjsbtxDetailAdapter);

        mZjsbtxDetailAdapter.set(mList);

        setRefresh();

    }

    /**
     * 设置事件
     */
    @Override
    protected void initEvent() {
        super.initEvent();
        btn_login.setOnClickListener(this);
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
        switch (v.getId()) {

            case R.id.btn_login:
                ZjsbtxUpdateActivity.startAction(this,id);
                break;

            case R.id.btn_dialogDelete_cancel:
                dialogDelete.dismiss();
                break;
            case R.id.btn_download:
                if (updata == null) {
                    return;
                }
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

                    //申请WRITE_EXTERNAL_STORAGE权限
                    ActivityCompat.requestPermissions(this, new String[]{
                                    Manifest.permission.READ_EXTERNAL_STORAGE,
                                    Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            requestPermissionsCode);
                } else {
                    if (updata.getFileName().contains(".png")
                            || updata.getFileName().contains(".jpg")
                            || updata.getFileName().contains(".jpeg")) {
                        String url = updata.getFilePath();
                        ArrayList<String> imageList = new ArrayList<>();
                        int index = 0;
                        for (UploadFileBean mPath : upurls) {
                            if (mPath.getFileName().contains(".png") || mPath.getFileName().contains(".jpg") || mPath.getFileName().contains(".jpeg")) {
                                int pos = upurls.indexOf(mPath);
                                imageList.add(upurls.get(pos).getFilePath());
                            }

                        }
                        for (String mUrl : imageList) {
                            int pos = imageList.indexOf(mUrl);
                            if (url.equals(mUrl)) {
                                index = pos;
                            }
                        }
                        Intent intents = new Intent(ZjsbtxDetailActivity.this, ImageBrowseActivity.class);
                        intents.putStringArrayListExtra("imageList", imageList);
                        intents.putExtra("index", index);
                        startActivity(intents);
                    } else {

                        DownloadManager downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
                        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(updata.getFilePath()));
                        //下载时，下载完成后显示通知
                        //创建目录
                        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).mkdir();
                        request
                                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                        //下载的路径，第一个参数是文件夹名称，第二个参数是下载的文件名
                        request.setDestinationInExternalPublicDir(getString(R.string.qbapp_name),
                                String.format("%s-%s-%s", "政策申报附件", "-", updata.getFileName()));
                        request.setVisibleInDownloadsUi(true);
                        downloadManager.enqueue(request);


                    }

                    dialogDelete.dismiss();

                }
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.policyapplytypelist("");
        //mRefreshLayout.autoRefresh();
    }

    /**
     * 设置上拉/下拉监听器
     */
    private void setRefresh() {

    }

    /**
     * 更多
     */
    private void loadMore() {
        isLoadMore = true;
        pageIndex++;
    }


    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void policyapplytypelistSuccess(List<ApplyTypeBean.ApplyTypesBean> data) {
        if (data == null) {
            return;
        }
        mList1.clear();
        mList1.addAll(data);
        mPresenter.policyapplydetail("", id);
    }

    @Override
    public void policyapplytypelistFailed() {
        mPresenter.policyapplydetail("", id);
    }

    @Override
    public void policyapplydetailSuccess(PolicyApplyDetailBean.ApplyInfoBean data) {
        if (data == null) {
            mList.clear();
            mList.add(new PolicyApplyDetailBean.ApplyInfoBean());
            mZjsbtxDetailAdapter.set(mList);
            return;
        }
        if(!TextUtils.isEmpty(data.getStatus()) &&
                (data.getStatus().contains("不通过")||data.getStatus().contains("失败"))){
            btn_login.setVisibility(View.VISIBLE);
        }else{
            btn_login.setVisibility(View.INVISIBLE);
        }

        String applyTypeName = data.getApplyType();
        mList.clear();
        mList.add(data);
        mZjsbtxDetailAdapter.set(mList);


        if (!TextUtils.isEmpty(applyTypeName)) {
            for (ApplyTypeBean.ApplyTypesBean bean : mList1) {
                if (bean != null) {
                    if (applyTypeName.equals(bean.getName()) || applyTypeName.equals(bean.getDes())) {
                        applyType = bean.getId();
                        break;
                    }
                }
            }

            if (!TextUtils.isEmpty(applyType)) {
                ntb.setRightImagVisibility(true);
                ntb.setRightImagSrc(R.mipmap.qz);
                ntb.setOnRightImagListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        QzjgActivity.startAction(ZjsbtxDetailActivity.this, applyType);
                    }
                });
            }
        }


    }

    @Override
    public void policyapplydetailFailed() {
        mList.clear();
        mList.add(new PolicyApplyDetailBean.ApplyInfoBean());
        mZjsbtxDetailAdapter.set(mList);
    }

    @Override
    public void onClick(UploadFileBean item, ArrayList<UploadFileBean> urls) {

        if (item != null) {
            if (urls != null) {
                upurls.clear();
                upurls.addAll(urls);

                updata = item;
                if (item.getFileName().contains(".png")
                        || item.getFileName().contains(".jpg")
                        || item.getFileName().contains(".jpeg")) {
                    btn_download.setText("查看");
                } else {
                    btn_download.setText("下载");
                }
                dialogDelete.show();

            }

        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        doNext(requestCode, grantResults);
    }

    private void doNext(int requestCode, int[] grantResults) {
        if (requestCode == requestPermissionsCode) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                dialogDelete.dismiss();
                if (updata.getFileName().contains(".png")
                        || updata.getFileName().contains(".jpg")
                        || updata.getFileName().contains(".jpeg")) {
                    String url = updata.getFilePath();
                    ArrayList<String> imageList = new ArrayList<>();
                    int index = 0;
                    for (UploadFileBean mPath : upurls) {
                        if (mPath.getFileName().contains(".png") || mPath.getFileName().contains(".jpg") || mPath.getFileName().contains(".jpeg")) {
                            int pos = upurls.indexOf(mPath);
                            imageList.add(upurls.get(pos).getFilePath());
                        }

                    }
                    for (String mUrl : imageList) {
                        int pos = imageList.indexOf(mUrl);
                        if (url.equals(mUrl)) {
                            index = pos;
                        }
                    }
                    Intent intents = new Intent(ZjsbtxDetailActivity.this, ImageBrowseActivity.class);
                    intents.putStringArrayListExtra("imageList", imageList);
                    intents.putExtra("index", index);
                    startActivity(intents);
                } else {

                    DownloadManager downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
                    DownloadManager.Request request = new DownloadManager.Request(Uri.parse(updata.getFilePath()));
                    //下载时，下载完成后显示通知
                    //创建目录
                    Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).mkdir();
                    request
                            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                    //下载的路径，第一个参数是文件夹名称，第二个参数是下载的文件名
                    request.setDestinationInExternalPublicDir(getString(R.string.qbapp_name),
                            String.format("%s-%s-%s", "政策申报附件", "-", updata.getFileName()));
                    request.setVisibleInDownloadsUi(true);
                    downloadManager.enqueue(request);
                }


            } else {

                dialogDelete.dismiss();
                ToastUtil.showToast(ZjsbtxDetailActivity.this, "没有文件读写权限,无法下载");

            }
        }
    }


}
