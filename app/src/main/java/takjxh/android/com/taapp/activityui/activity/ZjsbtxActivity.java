package takjxh.android.com.taapp.activityui.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.internal.entity.CaptureStrategy;
import com.zhihu.matisse.listener.OnCheckedListener;
import com.zhihu.matisse.listener.OnSelectedListener;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import takjxh.android.com.commlibrary.utils.BarUtil;
import takjxh.android.com.commlibrary.utils.ToastUtil;
import takjxh.android.com.commlibrary.utils.ViewUtil;
import takjxh.android.com.commlibrary.view.activity.BaseActivity;
import takjxh.android.com.taapp.R;
import takjxh.android.com.taapp.activityui.adapter.ZjsblxAdapter;
import takjxh.android.com.taapp.activityui.adapter.ZjsblxAdapter2;
import takjxh.android.com.taapp.activityui.adapter.ZjsbtxAdapter;
import takjxh.android.com.taapp.activityui.base.BaseDialog;
import takjxh.android.com.taapp.activityui.bean.ApplyTypeBean;
import takjxh.android.com.taapp.activityui.bean.PolicyApplyDetailBean;
import takjxh.android.com.taapp.activityui.bean.PolicyapplyaddList;
import takjxh.android.com.taapp.activityui.bean.UploadFileBean;
import takjxh.android.com.taapp.activityui.bean.ZjsbtxBean;
import takjxh.android.com.taapp.activityui.dialog.DateAndTimeDialog;
import takjxh.android.com.taapp.activityui.dialog.DateDialog;
import takjxh.android.com.taapp.activityui.lfilepickerlibrary.LFilePickerT;
import takjxh.android.com.taapp.activityui.presenter.ZjsbtxPresenter;
import takjxh.android.com.taapp.activityui.presenter.impl.IZjsbtxPresenter;
import takjxh.android.com.taapp.adapter.AddAttachmentAdapter;
import takjxh.android.com.taapp.dialog.CustomDialog;
import takjxh.android.com.taapp.utils.DisplayUtil;
import takjxh.android.com.taapp.utils.Regex_Tyshyxdm;
import takjxh.android.com.taapp.utils.RxRegTool;
import takjxh.android.com.taapp.view.CustomGridView;
import takjxh.android.com.taapp.view.CustomSpinner;
import takjxh.android.com.taapp.view.NormalTitleBar;
import takjxh.android.com.taapp.view.mulitmenuselect.Children;

/**
 * 类名称：政策申报填写
 *
 * @Author: libaibing
 * @Date: 2019-10-16 10:13
 * @Description:
 **/
public class ZjsbtxActivity extends BaseActivity<ZjsbtxPresenter> implements IZjsbtxPresenter.IView, View.OnClickListener, ZjsblxAdapter.OnApplyTypeClickListener {

    public static void startAction(Activity activity) {
        Intent intent = new Intent(activity, ZjsbtxActivity.class);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    /*@BindView(R.id.normal_view1)
    SmartRefreshLayout mRefreshLayout;*/
    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;
    private ZjsbtxAdapter mZjsbtxAdapter;
    private List<ZjsbtxBean> mList = new ArrayList<>();
    private int pageIndex = 1;
    private int pageSize = 20;
    private int total = 0;
    private boolean isLoadMore = false;

    @BindView(R.id.recycler_view1)
    RecyclerView recycler_view1;
    @BindView(R.id.recycler_view2)
    RecyclerView recycler_view2;
    private ZjsblxAdapter mZjsblxAdapter1;
    private ZjsblxAdapter2 mZjsblxAdapter2;
    private List<ApplyTypeBean.ApplyTypesBean> mList1 = new ArrayList<>();
    private List<ApplyTypeBean.ApplyTypesBean> mList2 = new ArrayList<>();

    @BindView(R.id.btn_login)
    Button btn_login;
    @BindView(R.id.ntb)
    NormalTitleBar ntb;


    @BindView(R.id.sp_register0)
    CustomSpinner sp_register0;
    @BindView(R.id.tv_GSMC)
    EditText tv_GSMC;
    @BindView(R.id.edXYDM)
    EditText edXYDM;
    @BindView(R.id.edZCSJ)
    TextView edZCSJ;
    String mZCSJ;
    @BindView(R.id.edFR)
    EditText edFR;
    @BindView(R.id.edLXRXM)
    EditText edLXRXM;
    @BindView(R.id.edLXDH)
    EditText edLXDH;
    @BindView(R.id.edSCFJ)
    TextView edSCFJ;
    @BindView(R.id.noScrollgridview)
    CustomGridView mGridView;

    @BindView(R.id.mLdx)
    LinearLayout mLdx;
    @BindView(R.id.mLrx)
    LinearLayout mLrx;
    @BindView(R.id.edQYSDS)
    EditText edQYSDS;
    @BindView(R.id.edGRSDS)
    EditText edGRSDS;
    @BindView(R.id.edGSYNS)
    EditText edGSYNS;
    @BindView(R.id.edSQJLJE)
    EditText edSQJLJE;


    private Button btn_delete;
    private Button btn_dialogDelete_cancel;
    private CustomDialog dialogDelete;
    private AddAttachmentAdapter mAdapter;
    /**
     * 当前选择附件
     */
    private int positionSelected = 0;
    private int MAX_ATTACHMENT_COUNT = 8;
    private int REQUESTCODE_FROM_ACTIVITY = 1000;
    private ArrayList<UploadFileBean> urls = new ArrayList<>();
    private String applyType;

    /**
     * 返回布局文件
     */
    @Override
    protected int getContentViewId() {
        return R.layout.activity_zjsbtx;
    }

    @Override
    protected ZjsbtxPresenter createPresenter() {
        return new ZjsbtxPresenter(this);
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
        ntb = findViewById(R.id.ntb);
        ntb.setTitleText("政策申报");
        ntb.setTvLeftVisiable(true);

        ntb.setRightImagVisibility(true);
        ntb.setRightImagSrc(R.mipmap.qz);
        ntb.setOnRightImagListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(applyType)) {
                    ToastUtil.showToast(ZjsbtxActivity.this, "请选择申报的扶持项目");
                    return;
                }
                QzjgActivity.startAction(ZjsbtxActivity.this, applyType);
            }
        });
        ntb.setOnBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        recycler_view.setLayoutManager(new LinearLayoutManager(ZjsbtxActivity.this, LinearLayoutManager.VERTICAL, false));
        recycler_view.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.top = ViewUtil.dp2px(ZjsbtxActivity.this, 0);
                outRect.bottom = ViewUtil.dp2px(ZjsbtxActivity.this, 0);
            }
        });
        mZjsbtxAdapter = new ZjsbtxAdapter(ZjsbtxActivity.this);
        recycler_view.setAdapter(mZjsbtxAdapter);
       /* mList.add(new ZjsbtxBean(0));
        mList.add(new ZjsbtxBean(1));*/
        mZjsbtxAdapter.set(mList);


        recycler_view1.setLayoutManager(new LinearLayoutManager(ZjsbtxActivity.this, LinearLayoutManager.VERTICAL, false));
        recycler_view1.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.top = ViewUtil.dp2px(ZjsbtxActivity.this, 0);
                outRect.bottom = ViewUtil.dp2px(ZjsbtxActivity.this, 0);
            }
        });
        mZjsblxAdapter1 = new ZjsblxAdapter(ZjsbtxActivity.this);
        mZjsblxAdapter1.setmClickListener(this);
        recycler_view1.setAdapter(mZjsblxAdapter1);
        mZjsblxAdapter1.set(mList1);


        recycler_view2.setLayoutManager(new LinearLayoutManager(ZjsbtxActivity.this, LinearLayoutManager.VERTICAL, false));
        recycler_view2.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.top = ViewUtil.dp2px(ZjsbtxActivity.this, 0);
                outRect.bottom = ViewUtil.dp2px(ZjsbtxActivity.this, 0);
            }
        });
        mZjsblxAdapter2 = new ZjsblxAdapter2(ZjsbtxActivity.this);
        recycler_view2.setAdapter(mZjsblxAdapter2);
        mZjsblxAdapter2.set(mList2);


        dialogDelete = new CustomDialog(this, DisplayUtil.getScreenWidth(this),   WindowManager.LayoutParams.WRAP_CONTENT, R.layout.dialog_attachment, R.style.Theme_dialog, Gravity.BOTTOM, R.style.anim_base_dialog_slide_at_bottom);
        dialogDelete.setCancelable(false);
        btn_delete = (Button) dialogDelete.findViewById(R.id.btn_delete);
        btn_delete.setOnClickListener(this);
        btn_dialogDelete_cancel = (Button) dialogDelete.findViewById(R.id.btn_dialogDelete_cancel);
        btn_dialogDelete_cancel.setOnClickListener(this);
        mAdapter = new AddAttachmentAdapter(this, urls);
        mGridView.setAdapter(mAdapter);
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                positionSelected = i;
                dialogDelete.show();

            }
        });


        setRefresh();

    }

    /**
     * 设置事件
     */
    @Override
    protected void initEvent() {
        super.initEvent();
        btn_login.setOnClickListener(this);
        edZCSJ.setOnClickListener(this);
        edSCFJ.setOnClickListener(this);


    }

    /**
     * 初始化数据
     */
    @Override
    protected void initData() {
        super.initData();
        mPresenter.policyapplytypelist("");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_delete:
                toDelete();
                break;
            case R.id.btn_dialogDelete_cancel:
                dialogDelete.dismiss();
                break;

            case R.id.edSCFJ:
                setPhoto();
                break;
            case R.id.edZCSJ:
                // 日期选择对话框
                new DateDialog.Builder(this)
                        .setTitle("请选择日期")
                        // 确定按钮文本
                        .setConfirm("确定")
                        // 设置 null 表示不显示取消按钮
                        .setCancel("取消")
                        // 设置日期
                        //.setDate("2018-12-31")
                        //.setDate("20181231")
                        //.setDate(1546263036137)
                        // 设置年份
                        //.setYear(2018)
                        // 设置月份
                        //.setMonth(2)
                        // 设置天数
                        //.setDay(20)
                        // 不选择天数
                        //.setIgnoreDay()
                        .setListener(new DateDialog.OnListener() {
                            @Override
                            public void onSelected(BaseDialog dialog, int year, int month, int day) {

                                String mmonth="";
                                if(month<10){
                                    mmonth="0"+month;
                                }else{
                                    mmonth=""+month;
                                }
                                String mday="";
                                if(day<10){
                                    mday="0"+day;
                                }else{
                                    mday=""+day;
                                }

                                /*String mhour="";
                                if(hour<10){
                                    mhour="0"+hour;
                                }else{
                                    mhour=""+hour;
                                }

                                String mminute="";
                                if(minute<10){
                                    mminute="0"+minute;
                                }else{
                                    mminute=""+minute;
                                }

                                String msecond="";
                                if(second<10){
                                    msecond="0"+second;
                                }else{
                                    msecond=""+second;
                                }*/
                                edZCSJ.setText(year + getString(R.string.common_year) + mmonth + getString(R.string.common_month) + mday + getString(R.string.common_day)
                                        );//+" "+mhour+":"+mminute+":"+msecond
                                mZCSJ=year +"" + mmonth + "" + mday ;//+ "" +""+mhour+""+mminute+""+msecond


                                // 如果不指定时分秒则默认为现在的时间
                                Calendar calendar = Calendar.getInstance();
                                calendar.set(Calendar.YEAR, year);
                                // 月份从零开始，所以需要减 1
                                calendar.set(Calendar.MONTH, month - 1);
                                calendar.set(Calendar.DAY_OF_MONTH, day);
                                //     toast("时间戳：" + calendar.getTimeInMillis());
                                //toast(new SimpleDateFormat("yyyy年MM月dd日 kk:mm:ss").format(calendar.getTime()));
                            }

                            @Override
                            public void onCancel(BaseDialog dialog) {
                                // toast("取消了");
                            }
                        })
                        .show();
                break;
            case R.id.btn_login:
                applyadddone();
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        //mRefreshLayout.autoRefresh();

    }

    /**
     * 设置上拉/下拉监听器
     */
    private void setRefresh() {
       /* mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                isLoadMore = false;
                pageIndex = 1;

               "" + pageIndex;
              "" + pageSize;
                mPresenter.s("", searchRequest);

                refreshLayout.finishRefresh(1000);
            }
        });
        mRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {

                loadMore();
                refreshLayout.finishLoadMore(1000);

            }
        });*/
    }

    /**
     * 更多
     */
    private void loadMore() {

        isLoadMore = true;
        pageIndex++;
           /*
           "" + pageIndex;
            "" + pageSize;
            mPresenter.s("", searchRequest);*/

    }


    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void tradetreelisttSuccess(List<Children> bean) {

    }

    @Override
    public void tradetreelisttFailed() {

    }

    @Override
    public void policyapplycheckApplySuccess(String data) {

    }

    @Override
    public void policyapplycheckApplyFailed(String data) {

    }

    @Override
    public void policyapplydetail1Success(PolicyapplyaddList data) {

    }

    @Override
    public void policyapplydetail1Failed() {

    }

    @Override
    public void policyapplyupdateSuccess(PolicyapplyaddList data) {

    }

    @Override
    public void policyapplyupdateFailed() {

    }

    @Override
    public void policyapplyaddSuccess(PolicyapplyaddList data) {

    }

    @Override
    public void policyapplyaddFailed() {

    }

    @Override
    public void policyapplydetailSuccess(PolicyApplyDetailBean.ApplyInfoBean data) {

    }

    @Override
    public void policyapplydetailFailed() {

    }

    @Override
    public void policyapplytypelistSuccess(List<ApplyTypeBean.ApplyTypesBean> data) {
        if (data == null) {
            return;
        }
        mList1.clear();
        mList1.addAll(data);

        mList2.clear();
        mList2.addAll(data);

        mZjsblxAdapter1.set(mList1);
        mZjsblxAdapter2.set(mList2);
    }

    @Override
    public void policyapplytypelistFailed() {

    }

    @Override
    public void applyadddtemponeSuccess(String data) {

    }

    @Override
    public void applyadddtemponeFailed(String data) {

    }

    @Override
    public void getPredictAmountSuccess(String data) {

    }

    @Override
    public void getPredictAmountFailed(String data) {

    }

    @Override
    public void applyadddoneSuccess(String data) {
        ToastUtil.showToast(this, data);
        //  ZjsbtxDetailActivity.startAction(this, "");
        finish();
    }

    @Override
    public void applyadddoneFailed(String data) {

    }

    @Override
    public void applyupdatedoneSuccess(String data) {

    }

    @Override
    public void applyupdatedoneFailed(String data) {

    }

    @Override
    public void uploadSuccess(UploadFileBean data) {
         if (data == null) {
            return;
        }
        urls.add(data);
        mAdapter.notifyDataSetChanged();
    }


    //定义请求码常量
    private static final int REQUEST_CODE_CHOOSE = 23;

    private void setPhoto() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            //申请WRITE_EXTERNAL_STORAGE权限
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    345);
        } else {
            Matisse.from(ZjsbtxActivity.this)
                    .choose(MimeType.ofImage(), false)
                    .countable(true)
                    .capture(true)
                    .captureStrategy(new CaptureStrategy(true, "com.zhihu.matisse.sample.fileprovider", "test"))
                    .maxSelectable(8)
                    //    .addFilter(new GifSizeFilter(320, 320, 5 * Filter.K * Filter.K))
                    .gridExpectedSize(getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                    .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                    .thumbnailScale(0.85f)
                    .theme(R.style.Matisse_Zhihus)//主题
//                                            .imageEngine(new GlideEngine())  // for glide-V3
                    //            .imageEngine(new Glide4Engine())    // for glide-V4
                    .setOnSelectedListener(new OnSelectedListener() {
                        @Override
                        public void onSelected(@NonNull List<Uri> uriList, @NonNull List<String> pathList) {
                            // DO SOMETHING IMMEDIATELY HERE
                            Log.e("onSelected", "onSelected: pathList=" + pathList);

                        }
                    })
                    .originalEnable(true)
                    .maxOriginalSize(50)
                    .autoHideToolbarOnSingleTap(true)
                    .setOnCheckedListener(new OnCheckedListener() {
                        @Override
                        public void onCheck(boolean isChecked) {
                            // DO SOMETHING IMMEDIATELY HERE
                            Log.e("isChecked", "onCheck: isChecked=" + isChecked);
                        }
                    })
                    .forResult(REQUEST_CODE_CHOOSE);
        }


    }




    /***
     * 选择附件
     *
     */
    /*private void uploadFile() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            //申请WRITE_EXTERNAL_STORAGE权限
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    345);
        } else {

            new LFilePickerT()
                    .withActivity(ZjsbtxActivity.this)
                    .withRequestCode(REQUESTCODE_FROM_ACTIVITY)
                    .withTitle(getString(R.string.select_wj))
                    .withBackgroundColor("#FF44AA")
                    .withFileFilter(new String[]{".txt", ".doc", ".docx", ".xsl", ".xls", ".xlsx", ".pdf", ".rar", ".zip", ".png", ".jpg", ".jpeg"})
                    .withIsGreater(false)
                    .withFileSize(50 * 1024 * 1024)
                    .withMaxNum(MAX_ATTACHMENT_COUNT)
                    .start();
        }

    }*/


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        doNext(requestCode, grantResults);
    }

    private void doNext(int requestCode, int[] grantResults) {
        if (requestCode == 345) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Matisse.from(ZjsbtxActivity.this)
                        .choose(MimeType.ofImage(), false)
                        .countable(true)
                        .capture(true)
                        .captureStrategy(new CaptureStrategy(true, "com.zhihu.matisse.sample.fileprovider", "test"))
                        .maxSelectable(8)
                        //    .addFilter(new GifSizeFilter(320, 320, 5 * Filter.K * Filter.K))
                        .gridExpectedSize(getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                        .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                        .thumbnailScale(0.85f)
                        .theme(R.style.Matisse_Zhihu)//主题
//                                            .imageEngine(new GlideEngine())  // for glide-V3
                        //            .imageEngine(new Glide4Engine())    // for glide-V4
                        .setOnSelectedListener(new OnSelectedListener() {
                            @Override
                            public void onSelected(@NonNull List<Uri> uriList, @NonNull List<String> pathList) {
                                // DO SOMETHING IMMEDIATELY HERE
                                Log.e("onSelected", "onSelected: pathList=" + pathList);

                            }
                        })
                        .originalEnable(true)
                        .maxOriginalSize(50)
                        .autoHideToolbarOnSingleTap(true)
                        .setOnCheckedListener(new OnCheckedListener() {
                            @Override
                            public void onCheck(boolean isChecked) {
                                // DO SOMETHING IMMEDIATELY HERE
                                Log.e("isChecked", "onCheck: isChecked=" + isChecked);
                            }
                        })
                        .forResult(REQUEST_CODE_CHOOSE);
            } else {
                // Permission Denied
            }
        }
    }

    List<String> mListTP = new ArrayList<>();

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            //  mAdapter.setData(Matisse.obtainResult(data), Matisse.obtainPathResult(data));
            Log.e("OnActivityResult ", String.valueOf(Matisse.obtainOriginalState(data)));
            List<Uri> mUri = Matisse.obtainResult(data);

            mListTP = Matisse.obtainPathResult(data);
            if (mListTP != null && mListTP.size() > 0) {
                mPresenter.upload("", mListTP.get(0));
            } else {
                ToastUtil.showToast(this, getString(R.string.select_msg_f));
            }
        }
    }

/*
    private void doNext(int requestCode, int[] grantResults) {
        if (requestCode == 345) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission Granted
                new LFilePickerT()
                        .withActivity(ZjsbtxActivity.this)
                        .withRequestCode(REQUESTCODE_FROM_ACTIVITY)
                        .withTitle(getString(R.string.select_wj))
                        .withBackgroundColor("#FF44AA")
                        .withFileFilter(new String[]{".txt", ".doc", ".docx", ".xsl", ".xls", ".xlsx", ".pdf", ".rar", ".zip", ".png", ".jpg", ".jpeg"})
                        .withIsGreater(false)
                        .withFileSize(50 * 1024 * 1024)
                        .withMaxNum(MAX_ATTACHMENT_COUNT)
                        .start();
            } else {
                // Permission Denied
            }
        }
    }
*/


    /***
     * 选择附件回调
     *
     * @Param: [requestCode, resultCode, data]
     * @return: void
     * @Author: libaibing
     * @Date: 2019/1/23
     */
   /* @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == 10 && resultCode == Activity.RESULT_OK) {
            Bundle bundle = data.getExtras();
            String str = bundle.getString("content");
            Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
            return;
        }

        if (resultCode == RESULT_OK) {
            if (requestCode == REQUESTCODE_FROM_ACTIVITY) {

                List<String> list = data.getStringArrayListExtra("paths");
                for (int i = 0; i < list.size(); i++) {

                    final String filePath = list.get(i);
                    if (filePath == null) {
                        return;
                    }


                    File file = new File(filePath);
                    if (file.isDirectory()) {
                        Log.d("filepath: ", filePath + " is directory");
                        return;
                    } else {
                         mPresenter.upload("", filePath);

                    }
                }
            }
        }

    }
*/

    /**
     * 点击附件，弹出对话框，删除事件
     */
    private void toDelete() {
        urls.remove(positionSelected);
        mAdapter.notifyDataSetChanged();
        dialogDelete.dismiss();

    }


    private void applyadddone() {

        String medGSMC = tv_GSMC.getText().toString().trim();
        if (TextUtils.isEmpty(medGSMC)) {
            ToastUtil.showToast(this, "请输入公司名称");
            return;
        }
        String medXYDM = edXYDM.getText().toString().trim();
        if (TextUtils.isEmpty(medXYDM)) {
            ToastUtil.showToast(this, "请输入统一社会信用代码");
            return;
        }

        if (!Regex_Tyshyxdm.isLicense18(medXYDM)) {
            ToastUtil.showToast(this, "统一社会信用代码格式不正确");
            return;
        }

        String medDWMC = edZCSJ.getText().toString().trim();
        if (TextUtils.isEmpty(medDWMC)) {
            ToastUtil.showToast(this, "请选择注册时间");
            return;
        }


        String medFR = edFR.getText().toString().trim();
        if (TextUtils.isEmpty(medFR)) {
            ToastUtil.showToast(this, "请输入法人");
            return;
        }

        String medLXR = edLXRXM.getText().toString().trim();
        if (TextUtils.isEmpty(medLXR)) {
            ToastUtil.showToast(this, "请输入联系人姓名");
            return;
        }


        String medLXDH = edLXDH.getText().toString().trim();
        if (TextUtils.isEmpty(medLXDH)) {
            ToastUtil.showToast(this, "请输入联系电话");
            return;
        }
        if (!RxRegTool.isMobile(medLXDH)) {
            ToastUtil.showToast(this, "请输入正确的联系电话");
            return;
        }

        if (TextUtils.isEmpty(applyType)) {
            ToastUtil.showToast(this, "请选择申报的扶持项目");
            return;
        }
        String medQYSDS = edQYSDS.getText().toString().trim();
        if (TextUtils.isEmpty(medQYSDS)) {
            ToastUtil.showToast(this, "请输入企业所得税");
            return;
        }
        String medGRSDS = edGRSDS.getText().toString().trim();
        if (TextUtils.isEmpty(medGRSDS)) {
            ToastUtil.showToast(this, "请输入个人所得税");
            return;
        }

        String medGSYNS = edGSYNS.getText().toString().trim();
        if (TextUtils.isEmpty(medGSYNS)) {
            ToastUtil.showToast(this, "请输入公司应纳税");
            return;
        }
        String medSQJLJE = edSQJLJE.getText().toString().trim();
        if (TextUtils.isEmpty(medSQJLJE)) {
            ToastUtil.showToast(this, "请输入申请奖励金额");
            return;
        }

        String files="";
        for(int i=0;i<urls.size();i++){
            if(i==urls.size()-1){
                files=files+urls.get(i).getFilePath()+"";
            }else{
                files=files+urls.get(i).getFilePath()+",";
            }

        }
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("entName", medGSMC);
        queryMap.put("orgCode", medXYDM);
        queryMap.put("regTime", mZCSJ);
        queryMap.put("lagalPerson", medFR);
        queryMap.put("linkman", medLXR);
        queryMap.put("linkmanPhone", medLXDH);
        queryMap.put("files",files);//附件，多个用逗号隔开

        queryMap.put("applyType", applyType);
        queryMap.put("entIncomeTax", medQYSDS);
        queryMap.put("persIncomeTax", medGRSDS);
        queryMap.put("entIncome", medGSYNS);
        queryMap.put("applyAmount", medSQJLJE);

        mPresenter.applyadddone("", queryMap);

    }


    @Override
    public void onClick(ApplyTypeBean.ApplyTypesBean item, int position) {
        applyType = item.getId();

    }
}
