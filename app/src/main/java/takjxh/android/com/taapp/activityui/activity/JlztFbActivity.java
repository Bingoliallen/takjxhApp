package takjxh.android.com.taapp.activityui.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.internal.entity.CaptureStrategy;
import com.zhihu.matisse.listener.OnCheckedListener;
import com.zhihu.matisse.listener.OnSelectedListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import takjxh.android.com.commlibrary.image.ImageWrapper;
import takjxh.android.com.commlibrary.net.HttpConfig;
import takjxh.android.com.commlibrary.presenter.impl.BasePresenter;
import takjxh.android.com.commlibrary.utils.BarUtil;
import takjxh.android.com.commlibrary.utils.ShareUtils;
import takjxh.android.com.commlibrary.utils.ToastUtil;
import takjxh.android.com.commlibrary.view.activity.BaseActivity;
import takjxh.android.com.taapp.QbApplication;
import takjxh.android.com.taapp.R;
import takjxh.android.com.taapp.activityui.bean.SysParamBean;
import takjxh.android.com.taapp.activityui.bean.UploadFileBean;
import takjxh.android.com.taapp.activityui.presenter.JlztFbPresenter;
import takjxh.android.com.taapp.activityui.presenter.impl.IJlztFbPresenter;
import takjxh.android.com.taapp.dialog.CustomDialog;
import takjxh.android.com.taapp.utils.DisplayUtil;
import takjxh.android.com.taapp.view.CustomSpinner;
import takjxh.android.com.taapp.view.NormalTitleBar;

/**
 * 类名称：交流主题发布
 *
 * @Author: libaibing
 * @Date: 2019-10-18 13:20
 * @Description:
 **/
public class JlztFbActivity extends BaseActivity<JlztFbPresenter> implements IJlztFbPresenter.IView, View.OnClickListener {

    public static void startAction(Activity activity) {
        Intent intent = new Intent(activity, JlztFbActivity.class);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @BindView(R.id.ntb)
    NormalTitleBar ntb;


    @BindView(R.id.clue_title)
    EditText clue_title;
    @BindView(R.id.clue_content)
    EditText clue_content;
    @BindView(R.id.icon)
    ImageView icon;
    @BindView(R.id.ivsc)
    ImageView ivsc;
    private String imgPath = "";


    @BindView(R.id.sp_register0)
    CustomSpinner sp_register0;
    @BindView(R.id.tv_type)
    TextView tv_type;
    private String type = "";
    private MyArrayAdapter adapterResult0;
    private List<SysParamBean.ParamsBean.CommtopictypeBean> commtopictype = new ArrayList<>();//继续教育交流类型

    private Button btn_delete;
    private Button btn_dialogDelete_cancel;
    private CustomDialog dialogDelete;


    //定义请求码常量
    private static final int REQUEST_CODE_CHOOSE = 23;

    /**
     * 返回布局文件
     */
    @Override
    protected int getContentViewId() {
        return R.layout.activity_jlzt_fb;
    }

    @Override
    protected JlztFbPresenter createPresenter() {
        return new JlztFbPresenter(this);
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
        ntb.setTitleText("创建交流主题");
        ntb.setTvLeftVisiable(true);
        ntb.setRightImagVisibility1(true);
        ntb.setRightImagSrc1(R.mipmap.sc);
        ntb.setOnRightImagListener1(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commadd();
            }
        });
        ntb.setOnBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        if (QbApplication.mBaseApplication.commtopictype != null) {
            commtopictype.clear();
            commtopictype.addAll(QbApplication.mBaseApplication.commtopictype);
        }


        adapterResult0 = new MyArrayAdapter(this, commtopictype);
        adapterResult0.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_register0.setAdapter(adapterResult0);
        sp_register0.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int arg2, long l) {
                String mBean = commtopictype.get(arg2).getValue();
                tv_type.setText(mBean);
                type = commtopictype.get(arg2).getCode();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }


        });
        sp_register0.setSelection(-1, true);

        dialogDelete = new CustomDialog(this, DisplayUtil.getScreenWidth(this),   WindowManager.LayoutParams.WRAP_CONTENT, R.layout.dialog_attachment, R.style.Theme_dialog, Gravity.BOTTOM, R.style.anim_base_dialog_slide_at_bottom);
        dialogDelete.setCancelable(false);
        btn_delete = (Button) dialogDelete.findViewById(R.id.btn_delete);
        btn_delete.setOnClickListener(this);
        btn_dialogDelete_cancel = (Button) dialogDelete.findViewById(R.id.btn_dialogDelete_cancel);
        btn_dialogDelete_cancel.setOnClickListener(this);
    }

    /**
     * 设置事件
     */
    @Override
    protected void initEvent() {
        super.initEvent();
        icon.setOnClickListener(this);
        ivsc.setOnClickListener(this);

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
            case R.id.btn_delete:
                toDelete();
                break;
            case R.id.btn_dialogDelete_cancel:
                dialogDelete.dismiss();
                break;
            case R.id.icon:
                dialogDelete.show();
                break;
            case R.id.ivsc:
                setPhoto();
                break;
        }
    }


    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void commaddSuccess(String data) {
        ToastUtil.showToast(this, data);
        finish();
    }

    @Override
    public void commaddFailed() {

    }

    @Override
    public void editHeadImgSuccess(UploadFileBean data) {
        if (data == null) {
            return;
        }
        if(!TextUtils.isEmpty(data.getFilePath())){
            imgPath= data.getFilePath();
            ImageWrapper.setImage(icon, HttpConfig.HOST+data.getFilePath(), R.drawable.pic_defalt, ImageWrapper.CENTER_CROP);
            icon.setVisibility(View.VISIBLE);
        }


    }

    @Override
    public void editHeadImgError() {
        ToastUtil.showToast(this, "图片上传失败");
    }

    /**
     * 点击附件，弹出对话框，删除事件
     */
    private void toDelete() {
        imgPath="";
        icon.setImageResource(R.color.background_color);
        icon.setVisibility(View.GONE);
        dialogDelete.dismiss();

    }


    private void setPhoto() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            //申请WRITE_EXTERNAL_STORAGE权限
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    345);
        } else {
            Matisse.from(JlztFbActivity.this)
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


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        doNext(requestCode, grantResults);
    }

    private void doNext(int requestCode, int[] grantResults) {
        if (requestCode == 345) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Matisse.from(JlztFbActivity.this)
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


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            //  mAdapter.setData(Matisse.obtainResult(data), Matisse.obtainPathResult(data));
            Log.e("OnActivityResult ", String.valueOf(Matisse.obtainOriginalState(data)));
            List<Uri> mUri = Matisse.obtainResult(data);
            List<String> mList = Matisse.obtainPathResult(data);
            if (mList != null && mList.size() > 0) {
                mPresenter.editHeadImg("", mList.get(0));
            } else {
                ToastUtil.showToast(this, getString(R.string.select_msg_f));
            }
        }
    }


    private void commadd() {
        if (TextUtils.isEmpty(type)) {
            ToastUtil.showToast(this, "请选择交流的主题类型");
            return;
        }
        String mclue_title = clue_title.getText().toString().trim();
        if (TextUtils.isEmpty(mclue_title)) {
            ToastUtil.showToast(this, "请输入交流的主题标题");
            return;
        }
        String mclue_content = clue_content.getText().toString().trim();
        if (TextUtils.isEmpty(mclue_content)) {
            ToastUtil.showToast(this, "请输入交流的主题内容描述");
            return;
        }

      /*  if (TextUtils.isEmpty(imgPath)) {
            ToastUtil.showToast(this, "请上传主题图片");
            return;
        }*/

        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("title", mclue_title);
        queryMap.put("type", type);
        queryMap.put("cover", imgPath);
        queryMap.put("des", mclue_content);
        mPresenter.commadd("", queryMap);

    }


    public class MyArrayAdapter extends ArrayAdapter<SysParamBean.ParamsBean.CommtopictypeBean> {

        private List<SysParamBean.ParamsBean.CommtopictypeBean> mList;

        public MyArrayAdapter(Context context, List<SysParamBean.ParamsBean.CommtopictypeBean> objects) {
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

            String mBean = mList.get(position).getValue();
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
            String mBean = mList.get(position).getValue();
            text.setText(mBean);
            return convertView;
        }

    }


}

