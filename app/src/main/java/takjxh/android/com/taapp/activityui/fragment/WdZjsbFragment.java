package takjxh.android.com.taapp.activityui.fragment;

import android.Manifest;
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
import android.view.View;

import com.leon.lfilepickerlibrary.widget.EmptyRecyclerView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import takjxh.android.com.commlibrary.utils.ToastUtil;
import takjxh.android.com.commlibrary.utils.ViewUtil;
import takjxh.android.com.commlibrary.view.fragment.BaseFragment;
import takjxh.android.com.taapp.R;
import takjxh.android.com.taapp.activity.ImageBrowseActivity;
import takjxh.android.com.taapp.activityui.adapter.WdZjsbAdapter;
import takjxh.android.com.taapp.activityui.bean.DownFileByApplyId;
import takjxh.android.com.taapp.activityui.bean.PolicyApplyBean;
import takjxh.android.com.taapp.activityui.presenter.WdZjsbPresenter;
import takjxh.android.com.taapp.activityui.presenter.impl.IWdZjsbPresenter;

/**
 * 类名称：我的资金申报列表
 *
 * @Author: libaibing
 * @Date: 2019-10-16 11:03
 * @Description:
 **/
public class WdZjsbFragment extends BaseFragment<WdZjsbPresenter> implements IWdZjsbPresenter.IView, View.OnClickListener,WdZjsbAdapter.OnWdZjsbClickListener {

    @BindView(R.id.normal_view1)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.empty_view)
    View mEmptyView;
    @BindView(R.id.recycler_view)
    EmptyRecyclerView recycler_view;

    private List<PolicyApplyBean.ApplyInfosBean> mList = new ArrayList<>();
    private WdZjsbAdapter mWdZjsbAdapter;

    private int pageIndex = 1;
    private int pageSize = 20;
    private int total = 0;
    private boolean isLoadMore = false;

    private String channelid;

    @Override
    public void onClick(View v) {

    }


    @Override
    protected int getContentViewId() {
        return R.layout.fragment_wd_zjsb;
    }

    @Override
    protected WdZjsbPresenter createPresenter() {
        return new WdZjsbPresenter(this);
    }


    @Override
    protected void initView() {
        super.initView();
        channelid = getArguments().getString("channelid");

        recycler_view.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        recycler_view.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.top = ViewUtil.dp2px(mContext, 0);
                outRect.bottom = ViewUtil.dp2px(mContext, 0);
            }
        });
        mWdZjsbAdapter = new WdZjsbAdapter(mContext,channelid);
        mWdZjsbAdapter.setmClickListener(this);
        recycler_view.setAdapter(mWdZjsbAdapter);
        recycler_view.setmEmptyView(mEmptyView);
        mWdZjsbAdapter.set(mList);
        mEmptyView.setVisibility(View.GONE);

        setRefresh();

    }

    @Override
    protected void initEvent() {
        super.initEvent();
    }

    @Override
    protected void initData() {
        super.initData();

    }


    @Override
    public void onResume() {
        super.onResume();
        mRefreshLayout.autoRefresh();
    }

    /**
     * 设置上拉/下拉监听器
     */
    private void setRefresh() {
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                isLoadMore = false;
                pageIndex = 1;
                mPresenter.policyapplylist("", channelid, "" + pageIndex, "" + pageSize);
                refreshLayout.finishRefresh(1000);
            }
        });
        mRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {

                loadMore();
                refreshLayout.finishLoadMore(1000);

            }
        });
    }

    /**
     * 更多
     */
    private void loadMore() {
        isLoadMore = true;
        pageIndex++;
        mPresenter.policyapplylist("", channelid, "" + pageIndex, "" + pageSize);
    }


    @Override
    public void policyapplylistSuccess(List<PolicyApplyBean.ApplyInfosBean> data) {
        if (data == null) {
            return;
        }
        if (!isLoadMore) {
            mList.clear();
            mList.addAll(data);
        } else {
            mList.addAll(data);
        }

        mWdZjsbAdapter.set(mList);
    }

    @Override
    public void policyapplylistFailed() {

    }

    @Override
    public void downFileByApplyIdSuccess(DownFileByApplyId data) {
        startDownload(data.url,data.filename);
    }

    @Override
    public void downFileByApplyIdFailed(String data) {

    }

    @Override
    public void downSJByApplyIdSuccess(DownFileByApplyId data) {
        startDownload(data.url,data.filename);
    }

    @Override
    public void downSJByApplyIdFailed(String data) {

    }

    @Override
    public void onClick1(String applyId) {
        if(!TextUtils.isEmpty(applyId)){
          mPresenter.downFileByApplyId("",applyId);
        }
    }

    @Override
    public void onClick2(String applyId) {
        if(!TextUtils.isEmpty(applyId)){
            mPresenter.downSJByApplyId("",applyId);
        }
    }
    private int requestPermissionsCode = 345;
    private String murl;
    private String mname;

    private void startDownload(String url,String name){
        murl=url;
        mname=name;
        if (ContextCompat.checkSelfPermission(mContext, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(mContext,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            //申请WRITE_EXTERNAL_STORAGE权限
            ActivityCompat.requestPermissions(mContext, new String[]{
                            Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    requestPermissionsCode);
        } else {

            toDownload( url, name);

        }
    }

    private void toDownload(String url,String name){
        DownloadManager downloadManager = (DownloadManager) mContext.getSystemService(Context.DOWNLOAD_SERVICE);
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        //下载时，下载完成后显示通知
        //创建目录
        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).mkdir();
        request
                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        //下载的路径，第一个参数是文件夹名称，第二个参数是下载的文件名
        request.setDestinationInExternalPublicDir(getString(R.string.qbapp_name),
                String.format("%s-%s-%s", "政策申报文件下载", "-", name));
        request.setVisibleInDownloadsUi(true);
        downloadManager.enqueue(request);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        doNext(requestCode, grantResults);
    }

    private void doNext(int requestCode, int[] grantResults) {
        if (requestCode == requestPermissionsCode) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                toDownload( murl, mname);


            } else {

                ToastUtil.showToast(mContext, "没有文件读写权限,无法下载");

            }
        }
    }






}
