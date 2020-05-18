package takjxh.android.com.taapp.activityui.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.leon.lfilepickerlibrary.LFilePicker;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.internal.entity.CaptureStrategy;
import com.zhihu.matisse.listener.OnCheckedListener;
import com.zhihu.matisse.listener.OnSelectedListener;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;
import takjxh.android.com.commlibrary.net.HttpConfig;
import takjxh.android.com.commlibrary.utils.BarUtil;
import takjxh.android.com.commlibrary.utils.ToastUtil;
import takjxh.android.com.commlibrary.utils.ViewUtil;
import takjxh.android.com.commlibrary.view.activity.BaseActivity;
import takjxh.android.com.taapp.R;
import takjxh.android.com.taapp.activityui.adapter.AddAttachmentAdapter1;
import takjxh.android.com.taapp.activityui.adapter.ZjsblxMultiItemQuickAdapter;
import takjxh.android.com.taapp.activityui.adapter.ZjsblxNewAdapter;
import takjxh.android.com.taapp.activityui.base.BaseDialog;
import takjxh.android.com.taapp.activityui.bean.ApplyTypeBean;
import takjxh.android.com.taapp.activityui.bean.PolicyApplyDetailBean;
import takjxh.android.com.taapp.activityui.bean.Policyapplyadd;
import takjxh.android.com.taapp.activityui.bean.PolicyapplyaddBean;
import takjxh.android.com.taapp.activityui.bean.PolicyapplyaddList;
import takjxh.android.com.taapp.activityui.bean.PredictAmount;
import takjxh.android.com.taapp.activityui.bean.UploadFileBean;
import takjxh.android.com.taapp.activityui.chat.takevideo.utils.LogUtils;
import takjxh.android.com.taapp.activityui.dialog.MessageDialog;
import takjxh.android.com.taapp.activityui.lfilepickerlibrary.LFilePickerT;
import takjxh.android.com.taapp.activityui.presenter.ZjsbtxPresenter;
import takjxh.android.com.taapp.activityui.presenter.impl.IZjsbtxPresenter;
import takjxh.android.com.taapp.dialog.CustomDialog;
import takjxh.android.com.taapp.utils.DisplayUtil;
import takjxh.android.com.taapp.view.BootStepView;
import takjxh.android.com.taapp.view.CustomSpinner;
import takjxh.android.com.taapp.view.NormalTitleBar;
import takjxh.android.com.taapp.view.mulitmenuselect.Children;
import takjxh.android.com.taapp.view.mulitmenuselect.ChildrenUtil;
import takjxh.android.com.taapp.view.mulitmenuselect.MultiDialogActivity;

/**
 * 类名称：政策申报填写-步骤模式
 *
 * @Author: libaibing
 * @Date: 2020-05-07 11:35
 * @Description:
 **/
public class ZjsbtxActivity1 extends BaseActivity<ZjsbtxPresenter> implements IZjsbtxPresenter.IView, View.OnClickListener,
        ZjsblxMultiItemQuickAdapter.OnWJSCClickListener, ZjsblxMultiItemQuickAdapter.OnWJSCItemClickListener, ZjsblxMultiItemQuickAdapter.OnDataChangeListener {

    public static void startAction(Activity activity) {
        Intent intent = new Intent(activity, ZjsbtxActivity1.class);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }


    @BindView(R.id.ntb)
    NormalTitleBar ntb;

    @BindView(R.id.scrollView)
    NestedScrollView scrollView;


    @BindView(R.id.mlStep1)
    LinearLayout mlStep1;
    @BindView(R.id.stepView)
    BootStepView stepView;

    @BindView(R.id.tv_czjg)
    TextView tv_czjg;
    @BindView(R.id.sp_register1)
    CustomSpinner sp_register1;
    private MyArrayAdapter adapterResult1;
    private List<ApplyTypeBean.ApplyTypesBean> mList = new ArrayList<>();
    @BindView(R.id.tv_ms)
    TextView tv_ms;
    @BindView(R.id.view_main)
    WebView webView;
    @BindView(R.id.btn_login1)
    Button btn_login1;


    @BindView(R.id.mlStep2)
    LinearLayout mlStep2;
    @BindView(R.id.recycler_view)
    RecyclerView recycler_view1;
    private ZjsblxNewAdapter mZjsblxNewAdapter;
    private List<Policyapplyadd> mData = new ArrayList<>();
    @BindView(R.id.btn_login2)
    Button btn_login2;
    @BindView(R.id.btn_login3)
    Button btn_login3;
    @BindView(R.id.tv_sshy)
    TextView tv_sshy;
    private String sshyID = "";
   // private List<Children> list1 = new ArrayList<>();
    private ArrayList<Children> treeItemBeanList = new ArrayList<>();
    @BindView(R.id.mView3)
    View mView3;
    @BindView(R.id.tvAmount)
    TextView tvAmount;


    @BindView(R.id.mlStep3)
    LinearLayout mlStep3;
    @BindView(R.id.tvcontent)
    TextView tvcontent;
    @BindView(R.id.btn_login4)
    Button btn_login4;


    private Button btn_delete;
    private Button btn_dialogDelete_cancel;
    private CustomDialog dialogDelete;


    private Button btn_downloadSel;
    private Button btn_deleteSel;
    private Button btn_dialogDelete_cancelSel;
    private CustomDialog dialogSel;

    /**
     * 当前选择附件
     */
    private int positionSelected = 0;

    private AddAttachmentAdapter1 mAdapter;
    private ArrayList<UploadFileBean> urls;


    private String applyType;

    //定义请求码常量
    private static final int REQUEST_CODE_Company = 25;

    /**
     * 返回布局文件
     */
    @Override
    protected int getContentViewId() {
        return R.layout.activity_zjsbtx1;
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
                    ToastUtil.showToast(ZjsbtxActivity1.this, "请选择申报类型");
                    return;
                }
                QzjgActivity.startAction(ZjsbtxActivity1.this, applyType);
            }
        });
        ntb.setOnBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        scrollView.setVisibility(View.GONE);
        mlStep2.setVisibility(View.GONE);
        mlStep1.setVisibility(View.VISIBLE);
        mlStep3.setVisibility(View.GONE);
        stepView.setTwoColor(false);
        stepView.setThreeColor(false);

        adapterResult1 = new MyArrayAdapter(this, mList);
        adapterResult1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_register1.setAdapter(adapterResult1);
        sp_register1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int arg2, long l) {
                String mBean = mList.get(arg2).getName();
                tv_czjg.setText(mBean);
                applyType = mList.get(arg2).getId();

                String des = mList.get(arg2).getDes();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    tv_ms.setText(Html.fromHtml(des, Html.FROM_HTML_MODE_COMPACT));
                } else {
                    tv_ms.setText(Html.fromHtml(des));
                }
                setWebView(webView,des);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }


        });
        sp_register1.setSelection(0, true);
        if(mList.size()>0){
            int arg2=0;
            String mBean = mList.get(arg2).getName();
            tv_czjg.setText(mBean);
            applyType = mList.get(arg2).getId();

            String des = mList.get(arg2).getDes();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                tv_ms.setText(Html.fromHtml(des, Html.FROM_HTML_MODE_COMPACT));
            } else {
                tv_ms.setText(Html.fromHtml(des));
            }
            setWebView(webView,des);
        }



        recycler_view1.setLayoutManager(new LinearLayoutManager(ZjsbtxActivity1.this, LinearLayoutManager.VERTICAL, false));
        recycler_view1.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.top = ViewUtil.dp2px(ZjsbtxActivity1.this, 0);
                outRect.bottom = ViewUtil.dp2px(ZjsbtxActivity1.this, 0);
            }
        });
        mZjsblxNewAdapter = new ZjsblxNewAdapter(ZjsbtxActivity1.this);
        mZjsblxNewAdapter.setOnDataChangeListener(this);
        mZjsblxNewAdapter.setOnWJSCItemClickListener(this);
        mZjsblxNewAdapter.setOnWJSCClickListener(this);
        recycler_view1.setAdapter(mZjsblxNewAdapter);
        mZjsblxNewAdapter.set(mData);


       /* tv_sshy.setThreshold(1);
        tv_sshy.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {//获取焦点时
                    tv_sshy.showDropDown();
                }
            }
        });
        tv_sshy.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                sshyID = list1.get(position).getId();
            }
        });*/
        mView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MultiDialogActivity.startAction(ZjsbtxActivity1.this, "选择所属行业", treeItemBeanList, REQUEST_CODE_Company);

                /*ThirdDialog2 dialog = new ThirdDialog2(ZjsbtxActivity1.this, treeItemBeanList);
                dialog.setonItemClickListener(new ThirdDialog2.DictItemClickListener() {
                    @Override
                    public void onDictItemClick(Children dictUnit) {
                        if (dictUnit != null) {
                            tv_sshy.setText(dictUnit.getName());
                            sshyID = dictUnit.getId();
                        }
                    }
                });
                dialog.show();*/
            }
        });


        dialogDelete = new CustomDialog(this, DisplayUtil.getScreenWidth(this), WindowManager.LayoutParams.WRAP_CONTENT, R.layout.dialog_attachment, R.style.Theme_dialog, Gravity.BOTTOM, R.style.anim_base_dialog_slide_at_bottom);
        dialogDelete.setCancelable(false);
        btn_delete = (Button) dialogDelete.findViewById(R.id.btn_delete);
        btn_delete.setOnClickListener(this);
        btn_dialogDelete_cancel = (Button) dialogDelete.findViewById(R.id.btn_dialogDelete_cancel);
        btn_dialogDelete_cancel.setOnClickListener(this);


        dialogSel = new CustomDialog(this, DisplayUtil.getScreenWidth(this), WindowManager.LayoutParams.WRAP_CONTENT, R.layout.dialog_attachment, R.style.Theme_dialog, Gravity.BOTTOM, R.style.anim_base_dialog_slide_at_bottom);
        dialogSel.setCancelable(false);
        btn_downloadSel = (Button) dialogSel.findViewById(R.id.btn_download);
        btn_downloadSel.setVisibility(View.VISIBLE);
        btn_downloadSel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                uploadFile();
                dialogSel.dismiss();
            }
        });
        btn_downloadSel.setText("上传pdf文件（单个文件大小不超过3M）");
        btn_deleteSel = (Button) dialogSel.findViewById(R.id.btn_delete);
        btn_deleteSel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                setPhoto();
                dialogSel.dismiss();
            }
        });
        btn_deleteSel.setText("上传图片（支持png/jpg,且单个文件大小不超过3M）");
        btn_dialogDelete_cancelSel = (Button) dialogSel.findViewById(R.id.btn_dialogDelete_cancel);
        btn_dialogDelete_cancelSel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialogSel.dismiss();
            }
        });

    }

    /**
     * 设置事件
     */
    @Override
    protected void initEvent() {
        super.initEvent();
        btn_login1.setOnClickListener(this);
        btn_login2.setOnClickListener(this);
        btn_login3.setOnClickListener(this);
        btn_login4.setOnClickListener(this);
    }

    /**
     * 初始化数据
     */
    @Override
    protected void initData() {
        super.initData();
        mPresenter.policyapplytypelist("");
        mPresenter.tradetreelistt();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login4:
                finish();
                break;
            case R.id.btn_delete:
                toDelete();
                break;
            case R.id.btn_dialogDelete_cancel:
                dialogDelete.dismiss();
                break;
            case R.id.btn_login1:
                if (TextUtils.isEmpty(applyType)) {
                    ToastUtil.showToast(ZjsbtxActivity1.this, "请选择申报类型");
                    return;
                }
                mPresenter.policyapplycheckApply("", applyType);

                break;
            case R.id.btn_login2:
                applyadddone();
                break;
            case R.id.btn_login3:
                applyadddtempone();
                break;
        }
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void tradetreelisttSuccess(List<Children> bean) {
        if (bean == null) {
            return;
        }
        treeItemBeanList.clear();
        treeItemBeanList.addAll(bean);

        /*list1.clear();
        list1 = ChildrenUtil.getSelList(treeItemBeanList);
        String[] dictionary = new String[list1.size()];
        for (int i = 0; i < list1.size(); i++) {
            dictionary[i] = list1.get(i).getName();
        }

        //利用适配器
        ArrayAdapter<String> adapter_actv = new ArrayAdapter<String>(
                this, android.R.layout.simple_dropdown_item_1line, dictionary);
        tv_sshy.setAdapter(adapter_actv);*/

    }

    @Override
    public void tradetreelisttFailed() {

    }

    @Override
    public void policyapplycheckApplySuccess(String data) {
        scrollView.setVisibility(View.VISIBLE);
        mlStep1.setVisibility(View.GONE);
        mlStep2.setVisibility(View.VISIBLE);
        mlStep3.setVisibility(View.GONE);
        mPresenter.policyapplyadd("", applyType);
        stepView.setTwoColor(true);
        stepView.setThreeColor(false);
    }

    @Override
    public void policyapplycheckApplyFailed(String data) {
        new MessageDialog.Builder(ZjsbtxActivity1.this)
                // 标题可以不用填写
                .setTitle("提示")
                // 内容必须要填写
                .setMessage(data)
                // 确定按钮文本
                .setConfirm("确定")
                // 设置 null 表示不显示取消按钮
                .setCancel(null)
                // 设置点击按钮后不关闭对话框
                //.setAutoDismiss(false)
                .setListener(new MessageDialog.OnListener() {

                    @Override
                    public void onConfirm(BaseDialog dialog) {
                        // toast("确定了");
                        finish();
                    }

                    @Override
                    public void onCancel(BaseDialog dialog) {
                        // toast("取消了");
                    }
                })
                .show();
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
        if (data == null) {
            mData.clear();
            mZjsblxNewAdapter.set(mData);
            return;
        }


        if (data.itemMap == null) {
            mData.clear();
            mZjsblxNewAdapter.set(mData);
            return;
        }
        mData.clear();
        Set<String> keys = data.itemMap.keySet();
        Iterator<String> iterator = keys.iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            List<PolicyapplyaddBean> arrayList = (List<PolicyapplyaddBean>) data.itemMap.get(key);
            mData.add(new Policyapplyadd(key, arrayList));
        }
        mZjsblxNewAdapter.set(mData);

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
        mList.clear();
        mList.addAll(data);
        adapterResult1.notifyDataSetChanged();
        if(mList.size()>0){
            int arg2=0;
            String mBean = mList.get(arg2).getName();
            tv_czjg.setText(mBean);
            applyType = mList.get(arg2).getId();

            String des = mList.get(arg2).getDes();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                tv_ms.setText(Html.fromHtml(des, Html.FROM_HTML_MODE_COMPACT));
            } else {
                tv_ms.setText(Html.fromHtml(des));
            }
            setWebView(webView,des);
        }
    }

    @Override
    public void policyapplytypelistFailed() {

    }

    @Override
    public void applyadddtemponeSuccess(String data) {
        // ToastUtil.showToast(this, data);
        tvcontent.setText(data);
        stepView.setThreeColor(true);
        scrollView.setVisibility(View.VISIBLE);
        mlStep1.setVisibility(View.GONE);
        mlStep3.setVisibility(View.VISIBLE);
        mlStep2.setVisibility(View.GONE);
    }

    @Override
    public void applyadddtemponeFailed(String data) {
        new MessageDialog.Builder(ZjsbtxActivity1.this)
                // 标题可以不用填写
                .setTitle("提示")
                // 内容必须要填写
                .setMessage(data)
                // 确定按钮文本
                .setConfirm("确定")
                // 设置 null 表示不显示取消按钮
                .setCancel(null)
                // 设置点击按钮后不关闭对话框
                //.setAutoDismiss(false)
                .setListener(new MessageDialog.OnListener() {

                    @Override
                    public void onConfirm(BaseDialog dialog) {
                        // toast("确定了");
                        finish();
                    }

                    @Override
                    public void onCancel(BaseDialog dialog) {
                        // toast("取消了");
                    }
                })
                .show();
    }

    private String predictAmount = "0";

    @Override
    public void getPredictAmountSuccess(String data) {
        LogUtils.e("------------getPredictAmountSuccess-------:" + data);
        predictAmount = data;
        tvAmount.setText("参考可扶持金额取整为" + data + "元，实际按审核为准。");
    }

    @Override
    public void getPredictAmountFailed(String data) {

    }

    @Override
    public void applyadddoneSuccess(String data) {
        //ToastUtil.showToast(this, data);
        tvcontent.setText(data);
        stepView.setThreeColor(true);
        scrollView.setVisibility(View.VISIBLE);
        mlStep1.setVisibility(View.GONE);
        mlStep3.setVisibility(View.VISIBLE);
        mlStep2.setVisibility(View.GONE);

    }

    @Override
    public void applyadddoneFailed(String data) {
        new MessageDialog.Builder(ZjsbtxActivity1.this)
                // 标题可以不用填写
                .setTitle("提示")
                // 内容必须要填写
                .setMessage(data)
                // 确定按钮文本
                .setConfirm("确定")
                // 设置 null 表示不显示取消按钮
                .setCancel(null)
                // 设置点击按钮后不关闭对话框
                //.setAutoDismiss(false)
                .setListener(new MessageDialog.OnListener() {

                    @Override
                    public void onConfirm(BaseDialog dialog) {
                        // toast("确定了");
                        finish();
                    }

                    @Override
                    public void onCancel(BaseDialog dialog) {
                        // toast("取消了");
                    }
                })
                .show();
    }

    @Override
    public void applyupdatedoneSuccess(String data) {
        ToastUtil.showToast(this, data);
    }

    @Override
    public void applyupdatedoneFailed(String data) {

    }

    @Override
    public void uploadSuccess(UploadFileBean data) {
        if (data == null) {
            return;
        }
        if (urls != null) {
            urls.clear();
            urls.add(data);
        }
        if (positionNum != -1) {
            String files = "";
            for (int i = 0; i < urls.size(); i++) {
                if (i == urls.size() - 1) {
                    files = files + urls.get(i).getFilePath() + "";
                } else {
                    files = files + urls.get(i).getFilePath() + ",";
                }

            }
            if (position != -1) {
                mData.get(positionNum).value.get(position).setValue(files);
            }

        }

        if (mAdapter != null) {
            mAdapter.notifyDataSetChanged();

        }

    }

    /* PolicyapplyaddBean item;
     List<PolicyapplyaddBean> list;*/
    private int positionNum = -1;
    private int position = -1;

    @Override
    public void onClick(AddAttachmentAdapter1 mAdapter, int positionNum, int position, ArrayList<UploadFileBean> urls) {
        positionSelected = 0;
        this.mAdapter = null;
        this.urls = null;
        this.positionNum = -1;
        this.position = -1;
        this.positionNum = positionNum;
        this.position = position;
        this.mAdapter = mAdapter;
        this.urls = urls;
        dialogSel.show();
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
            Matisse.from(ZjsbtxActivity1.this)
                    .choose(MimeType.ofImage(), false)
                    .countable(true)
                    .capture(true)
                    .captureStrategy(new CaptureStrategy(true, "com.zhihu.matisse.sample.fileprovider", "test"))
                    .maxSelectable(1)
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
                    .maxOriginalSize(3)
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


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        doNext(requestCode, grantResults);
    }

    private void doNext(int requestCode, int[] grantResults) {
        if (requestCode == 345) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Matisse.from(ZjsbtxActivity1.this)
                        .choose(MimeType.ofImage(), false)
                        .countable(true)
                        .capture(true)
                        .captureStrategy(new CaptureStrategy(true, "com.zhihu.matisse.sample.fileprovider", "test"))
                        .maxSelectable(1)
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
                        .maxOriginalSize(3)
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
        } else if (requestCode == 344) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission Granted
                new LFilePickerT()
                        .withActivity(ZjsbtxActivity1.this)
                        .withRequestCode(REQUESTCODE_FROM_ACTIVITY)
                        .withTitle(getString(R.string.select_wj))
                        .withFileFilter(new String[]{".pdf"})
                        .withIsGreater(false)
                        .withFileSize(3 * 1024 * 1024)
                        .withMaxNum(MAX_ATTACHMENT_COUNT)
                        .start();
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
        } else if (requestCode == REQUESTCODE_FROM_ACTIVITY && resultCode == RESULT_OK) {
            List<String> list = data.getStringArrayListExtra("paths");
            if(list!=null && list.size()>0){
                final String filePath = list.get(0);
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
            }else{
                ToastUtil.showToast(this, "文件选择失败");
            }

        }else  if (requestCode == REQUEST_CODE_Company && resultCode == RESULT_OK) {
            if (data != null) {
                Children dictUnit = data.getParcelableExtra("dictUnit");
                if (dictUnit != null) {
                    tv_sshy.setText(dictUnit.getName());
                    sshyID = dictUnit.getId();
                }
            }
        }
    }

    /**
     * 点击附件，弹出对话框，删除事件
     */
    private void toDelete() {
        if (urls != null && positionSelected < urls.size()) {
            urls.remove(positionSelected);
        }

        if (positionNum != -1) {
            if (position != -1) {
                mData.get(positionNum).value.get(position).setValue("");
            }

        }

        if (mAdapter != null) {
            mAdapter.notifyDataSetChanged();
        }

        dialogDelete.dismiss();

    }


    @Override
    public void onClick(int position) {
        positionSelected = position;
        dialogDelete.show();
    }

    @Override
    public void onClick(int positionNum, int position, String putKey) {

        mData.get(positionNum).value.get(position).setValue(putKey);
        if (!TextUtils.isEmpty(mData.get(positionNum).value.get(position).getSyscode())) {
            getPredictAmount();
        }
    }


    public class MyArrayAdapter extends ArrayAdapter<ApplyTypeBean.ApplyTypesBean> {

        private List<ApplyTypeBean.ApplyTypesBean> mList;

        public MyArrayAdapter(Context context, List<ApplyTypeBean.ApplyTypesBean> objects) {
            super(context, android.R.layout.simple_list_item_1, android.R.id.text1, objects);
            mList = objects;
        }

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_1, null);
            }
            TextView tvName = (TextView) convertView.findViewById(android.R.id.text1);

            String mBean = mList.get(position).getName();
            tvName.setText(mBean);

            return convertView;
        }

        @Override
        public View getDropDownView(int position, View convertView,
                                    ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(
                        android.R.layout.simple_list_item_1, null);
            }
            TextView text = (TextView) convertView
                    .findViewById(android.R.id.text1);
            String mBean = mList.get(position).getName();
            text.setText(mBean);
            return convertView;
        }

    }

    private void applyadddone() {

        if (TextUtils.isEmpty(sshyID)) {
            ToastUtil.showToast(this, "请选择企业所属行业");
            return;
        }

        boolean isAll = true;
        for (Policyapplyadd bean : mData) {
            if (bean != null && bean.value != null && bean.value.size() > 0) {
                for (PolicyapplyaddBean item : bean.value) {
                    if (item != null) {
                        if (item.isRuleReqest() && TextUtils.isEmpty(item.getValue())) {
                            ToastUtil.showToast(this, item.getName() + "为必填");
                            isAll = false;
                            break;
                        }
                    }
                }

            }
        }

        if (!isAll) {
            return;
        }


        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("typeId", applyType);
        queryMap.put("trade", sshyID);
        queryMap.put("predictAmount", predictAmount);

        Map<String, Object> applyData = new HashMap<>();
        for (Policyapplyadd bean : mData) {
            if (bean != null && bean.value != null && bean.value.size() > 0) {
                for (PolicyapplyaddBean item : bean.value) {
                    if (item != null) {
                        if ("07".equals(item.getType())) {
                            applyData.put("item-" + item.getId(), item.getValue());
                        } else {
                            applyData.put("item-" + item.getId(), item.getValue());
                        }
                    }
                }

            }
        }
        String jsonString1 = new Gson().toJson(applyData);
        queryMap.put("applyData", jsonString1);
        String jsonString = new Gson().toJson(queryMap);
        LogUtils.e("--------jsonString-------------:" + jsonString);
        mPresenter.applyadddone("", queryMap);
    }


    private void applyadddtempone() {

        if (TextUtils.isEmpty(sshyID)) {
            ToastUtil.showToast(this, "请选择企业所属行业");
            return;
        }

        boolean isAll = true;
        for (Policyapplyadd bean : mData) {
            if (bean != null && bean.value != null && bean.value.size() > 0) {
                for (PolicyapplyaddBean item : bean.value) {
                    if (item != null) {
                        if (item.isRuleReqest() && TextUtils.isEmpty(item.getValue())) {
                            ToastUtil.showToast(this, item.getName() + "为必填");
                            isAll = false;
                            break;
                        }
                    }
                }

            }
        }

        if (!isAll) {
            return;
        }


        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("typeId", applyType);
        queryMap.put("trade", sshyID);
        queryMap.put("predictAmount", predictAmount);

        Map<String, Object> applyData = new HashMap<>();
        for (Policyapplyadd bean : mData) {
            if (bean != null && bean.value != null && bean.value.size() > 0) {
                for (PolicyapplyaddBean item : bean.value) {
                    if (item != null) {
                        if ("07".equals(item.getType())) {
                            applyData.put("item-" + item.getId(), item.getValue());
                        } else {
                            applyData.put("item-" + item.getId(), item.getValue());
                        }

                    }
                }

            }
        }
        String jsonString1 = new Gson().toJson(applyData);
        queryMap.put("applyData", jsonString1);


        String jsonString = new Gson().toJson(queryMap);
        LogUtils.e("--------jsonString-------------:" + jsonString);
        mPresenter.applyadddtempone("", queryMap);
    }


    private void getPredictAmount() {
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("typeId", applyType);
        queryMap.put("trade", sshyID);

        List<PredictAmount> applyData = new ArrayList<>();
        for (Policyapplyadd bean : mData) {
            if (bean != null && bean.value != null && bean.value.size() > 0) {
                for (PolicyapplyaddBean item : bean.value) {
                    if (item != null && !TextUtils.isEmpty(item.getSyscode())) {
                        applyData.add(new PredictAmount(item.getSyscode(), item.getValue()));

                    }
                }

            }
        }
        String jsonString1 = new Gson().toJson(applyData);
        queryMap.put("applyData", jsonString1);

        Map<String, Object> subApplyData = new HashMap<>();
        for (Policyapplyadd bean : mData) {
            if (bean != null && bean.value != null && bean.value.size() > 0) {
                for (PolicyapplyaddBean item : bean.value) {
                    if (item != null) {
                        if ("07".equals(item.getType())) {
                            subApplyData.put("item-" + item.getId(), item.getValue());
                        } else {
                            subApplyData.put("item-" + item.getId(), item.getValue());
                        }

                    }
                }

            }
        }
        String jsonString2 = new Gson().toJson(subApplyData);
        queryMap.put("subApplyData", jsonString2);

        String jsonString = new Gson().toJson(queryMap);
        LogUtils.e("----getPredictAmount----jsonString-------------:" + jsonString);

        mPresenter.getPredictAmount("", queryMap);
    }


    private int MAX_ATTACHMENT_COUNT = 1;
    private boolean fromContentActivity = false;
    private int REQUESTCODE_FROM_ACTIVITY = 1000;

    /***
     * 选择附件
     */
    private void uploadFile() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            //申请WRITE_EXTERNAL_STORAGE权限
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    344);
        } else {
            fromContentActivity = true;
            new LFilePickerT()
                    .withActivity(ZjsbtxActivity1.this)
                    .withRequestCode(REQUESTCODE_FROM_ACTIVITY)
                    .withTitle(getString(R.string.select_wj))
                    .withFileFilter(new String[]{".pdf"})
                    .withIsGreater(false)
                    .withFileSize(5 * 1024 * 1024)
                    .withMaxNum(MAX_ATTACHMENT_COUNT)
                    .start();
        }

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
            pElement.attr("max-width", String.valueOf(DisplayUtil.getScreenWidth(ZjsbtxActivity1.this) + "px"))
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
    protected void onDestroy() {
        if (webView != null) {
            webView.clearHistory();
            ViewGroup parent = (ViewGroup) webView.getParent();
            if (parent != null) {
                parent.removeView(webView);
            }
            webView.destroy();
            webView = null;
        }
        super.onDestroy();
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

}
