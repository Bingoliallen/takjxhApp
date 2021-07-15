package takjxh.android.com.taapp.view;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.arialyy.annotations.Download;
import com.arialyy.aria.core.Aria;
import com.arialyy.aria.core.download.DownloadEntity;
import com.arialyy.aria.core.task.DownloadTask;
import com.arialyy.aria.orm.DbEntity;
import com.arialyy.aria.util.ALog;
import com.arialyy.aria.util.CommonUtil;
import com.flyco.roundview.RoundTextView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import takjxh.android.com.commlibrary.utils.DateTimeUtil;
import takjxh.android.com.commlibrary.utils.ToastUtil;
import takjxh.android.com.taapp.QbApplication;
import takjxh.android.com.taapp.R;
import takjxh.android.com.taapp.utils.OpenFileUtil;

/**
 * Date: 2018/7/9
 * Author: libaibing
 * Email：libb@android.com.cn
 * Des：
 */

public class DownloadDialog1 extends AbsDialog1 implements View.OnClickListener{
    HorizontalProgressBarWithNumber mPb;
    RoundTextView rtv_1;
    RoundTextView mStart;
    RoundTextView mStop;
    RoundTextView mCancel;
    RoundTextView mLook;
    TextView mSize;
    TextView mSpeed;
    LinearLayout handle_bar;
    //    @Bind(R.id.title) TextView title;
    private Context context;
    private String url;
    private String filename;
    private String mFilePath;
    private long mTaskId = -1;

  /* private static final String DOWNLOAD_URL =
            "http://192.168.150.144:8080/mmall/upload/20180709122909.apk";*/

    public DownloadDialog1(Context context, String url, String filename) {
        super(context);
        this.context = context;
        this.url = url;
        this.filename = filename;
        init();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.dialog_download_;
    }


    private void init() {
        mPb = (HorizontalProgressBarWithNumber) findViewById(R.id.progressBar);
        rtv_1 = (RoundTextView) findViewById(R.id.rtv_1);
        mStart = (RoundTextView) findViewById(R.id.start);
        mStop = (RoundTextView) findViewById(R.id.stop);
        mCancel = (RoundTextView) findViewById(R.id.cancel12);
        mLook = (RoundTextView) findViewById(R.id.btnLook);
        mSize = (TextView) findViewById(R.id.size);
        mSpeed = (TextView) findViewById(R.id.speed);
        handle_bar = (LinearLayout) findViewById(R.id.handle_bar);
        mStart.setOnClickListener(this);
        mStop.setOnClickListener(this);
        mCancel.setOnClickListener(this);
        mLook.setOnClickListener(this);


        Aria.download(this).register();
        setBtState(true);
        Log.e("TAG", "----------url-----------:" + url);
        //获取当前时间
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateTimeUtil.DF_YYYYMMDDHHMMSS);// HH:mm:ss
        Date date = new Date(System.currentTimeMillis());
        String time = simpleDateFormat.format(date);

        ToastUtil.showToast(QbApplication.mBaseApplication.getApplicationContext(),"当前文件下载地址是："+filename);
        String fName = filename;
        String end = "*/*";
        String start = "";
        //获取后缀名前的分隔符"."在fName中的位置。
        int dotIndex = fName.lastIndexOf(".");
        if (dotIndex < 0) {
            end = "*/*";
        } else {
              /* 获取文件的后缀名 */
            start = fName.substring(0, dotIndex);
            end = fName.substring(dotIndex, fName.length()).toLowerCase();
            if (end == "") {
                end = "*/*";
            }
        }

        try {
            String filename1 = "";
            if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                filename1= context.getFilesDir().toString();

            } else {
                //存储到手机中，或提示
                // Toast.makeText(this, "无卡,保存到手机" + context.getFilesDir().toString() + "/" + filename, Toast.LENGTH_SHORT).show();

                filename1=Environment.getExternalStorageDirectory().getPath();
            }

            mFilePath =filename1 + "/MyFile/UploadFilesZT/" + start + "_" + end;//
            File file = new File(filename1 + "/MyFile/UploadFilesXM");
            if (!file.exists()) {
                if (!file.mkdirs()) {
                    ALog.d(TAG, "创建失败，请检查路径和是否配置文件权限！");

                    ToastUtil.showToast(QbApplication.mBaseApplication.getApplicationContext(),"创建失败，请检查路径和是否配置文件权限！");
                }

            }
            File file1 = new File( mFilePath);
            if (!file1.exists()) {

            } else{
                if (file1.isFile()){
                    deleteFile(mFilePath);
                }else{
                    deleteDirectory(mFilePath);
                }

            }
       /* if (DbEntity.checkDataExist(DownloadEntity.class, "downloadPath=?", mFilePath)) {

        }*/
            DbEntity.deleteData(DownloadEntity.class, "downloadPath=?", mFilePath);
            Aria.download(this).removeAllTask(true);
            // title.setText("正在下载...");
            setCancelable(true);
            setCanceledOnTouchOutside(false);
            mSpeed.setVisibility(View.VISIBLE);
            handle_bar.setVisibility(View.VISIBLE);
            mLook.setVisibility(View.GONE);
            rtv_1.setVisibility(View.GONE);
            mStart.setVisibility(View.VISIBLE);

            DownloadEntity entity = Aria.download(this).getFirstDownloadEntity(url);
            if (entity != null) {
                mTaskId = entity.getId();
                mSize.setText(CommonUtil.formatFileSize(entity.getFileSize()));
                int p = (int) (entity.getCurrentProgress() * 100 / entity.getFileSize());
                mPb.setProgress(p);
                int state = entity.getState();
                setBtState(true);
                // setBtState(state != DownloadEntity.STATE_RUNNING);
                if (p == 100) {
                    mSpeed.setVisibility(View.GONE);
                    handle_bar.setVisibility(View.GONE);
                    mLook.setVisibility(View.VISIBLE);
                    // title.setText("已下载");
                }

            } else {
                mSpeed.setVisibility(View.VISIBLE);
                handle_bar.setVisibility(View.VISIBLE);
                mLook.setVisibility(View.GONE);
                //  title.setText("正在下载...");
                setBtState(true);
            }


        } catch (Exception e) {
            return;
        }

    }




    public void setCancel() {

        Aria.download(this).load(mTaskId).cancel();
        dismiss();

    }

    public void setLook() {
        try {

            File file = new File(mFilePath);
            OpenFileUtil.openFile1(context, file);

            dismiss();
        } catch (Exception e) {
            e.printStackTrace();
            //没有安装第三方的软件会提示
            ToastUtil.showToast(QbApplication.mBaseApplication.getApplicationContext(),"没有找到打开该文件的应用程序");
            dismiss();
        }


    }


    @Download.onTaskPre
    public void onTaskPre(DownloadTask task) {
        mSize.setText(CommonUtil.formatFileSize(task.getFileSize()));
        setBtState(true);
    }

    @Download.onTaskStop
    public void onTaskStop(DownloadTask task) {
        setBtState(true);
        mSpeed.setText(task.getConvertSpeed());
    }

    @Download.onTaskCancel
    public void onTaskCancel(DownloadTask task) {
        setBtState(true);
        mPb.setProgress(0);
        mSpeed.setText(task.getConvertSpeed());
        mSpeed.setVisibility(View.VISIBLE);
        handle_bar.setVisibility(View.VISIBLE);
        mLook.setVisibility(View.GONE);
        //title.setText("正在下载...");
    }

    @Download.onTaskRunning
    public void onTaskRunning(DownloadTask task) {
        if (task.getKey().equals(url)) {
            mPb.setProgress(task.getPercent());
            mSpeed.setText(task.getConvertSpeed());
        }
    }

    @Download.onTaskComplete
    public void onTaskComplete(DownloadTask task) {
        if (task.getKey().equals(url)) {
            setBtState(true);
            mPb.setProgress(100);
            mSpeed.setVisibility(View.GONE);
            handle_bar.setVisibility(View.GONE);
            mLook.setVisibility(View.VISIBLE);
            // title.setText("已下载");
        }

    }

    @Download.onTaskFail
    public void onTaskFail(DownloadTask task) {
        ToastUtil.showToast(QbApplication.mBaseApplication.getApplicationContext(),"下载失败");
    }


    @Override
    protected void dataCallback(int result, Object obj) {

    }

    private void setBtState(boolean startEnable) {
        mStart.setEnabled(startEnable);
        //   mCancel.setEnabled(!startEnable);
        //   mStop.setEnabled(!startEnable);
    }


    /**
     * 删除单个文件
     *
     * @param fileName 要删除的文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public static boolean deleteFile(String fileName) {
        File file = new File(fileName);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                System.out.println("删除单个文件" + fileName + "成功！");
                return true;
            } else {
                System.out.println("删除单个文件" + fileName + "失败！");
                return false;
            }
        } else {
            System.out.println("删除单个文件失败：" + fileName + "不存在！");
            return false;
        }
    }

    /**
     * 删除目录及目录下的文件
     *
     * @param dir 要删除的目录的文件路径
     * @return 目录删除成功返回true，否则返回false
     */
    public static boolean deleteDirectory(String dir) {
        // 如果dir不以文件分隔符结尾，自动添加文件分隔符
        if (!dir.endsWith(File.separator)) {
            dir = dir + File.separator;
        }
        File dirFile = new File(dir);
        // 如果dir对应的文件不存在，或者不是一个目录，则退出
        if ((!dirFile.exists()) || (!dirFile.isDirectory())) {
            System.out.println("删除目录失败：" + dir + "不存在！");
            return false;
        }
        boolean flag = true;
        // 删除文件夹中的所有文件包括子目录
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            // 删除子文件
            if (files[i].isFile()) {
                flag = deleteFile(files[i].getAbsolutePath());
                if (!flag) {
                    break;
                }
            }
            // 删除子目录
            else if (files[i].isDirectory()) {
                flag =deleteDirectory(files[i]
                        .getAbsolutePath());
                if (!flag) {
                    break;
                }
            }
        }
        if (!flag) {
            System.out.println("删除目录失败！");
            return false;
        }
        // 删除当前目录
        if (dirFile.delete()) {
            System.out.println("删除目录" + dir + "成功！");
            return true;
        } else {
            return false;
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start:
                if (mTaskId == -1) {
                    mTaskId = Aria.download(this)
                            .load(url)
                            .setFilePath(mFilePath)
                            .create();
                }

                rtv_1.setVisibility(View.VISIBLE);
                mStart.setVisibility(View.GONE);
                break;
            case R.id.stop:
                Aria.download(this).load(mTaskId).stop();
                break;
            case R.id.cancel12:
                setCancel();
                break;

            case R.id.btnLook:
                setLook();
                break;

        }
    }
}

