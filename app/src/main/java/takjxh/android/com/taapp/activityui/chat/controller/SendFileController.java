package takjxh.android.com.taapp.activityui.chat.controller;


import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.ref.WeakReference;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.content.FileContent;
import cn.jpush.im.android.api.content.ImageContent;
import cn.jpush.im.android.api.enums.ConversationType;
import cn.jpush.im.android.api.exceptions.JMFileSizeExceedException;
import cn.jpush.im.android.api.model.Conversation;
import cn.jpush.im.android.api.model.Message;

import takjxh.android.com.taapp.R;
import takjxh.android.com.taapp.activityui.chat.activity.SendFileActivity;
import takjxh.android.com.taapp.activityui.chat.activity.fragment.AudioFragment;
import takjxh.android.com.taapp.activityui.chat.activity.fragment.DocumentFragment;
import takjxh.android.com.taapp.activityui.chat.activity.fragment.ImageFragment;
import takjxh.android.com.taapp.activityui.chat.activity.fragment.OtherFragment;
import takjxh.android.com.taapp.activityui.chat.activity.fragment.VideoFragment;
import takjxh.android.com.taapp.activityui.chat.adapter.ViewPagerAdapter;

import takjxh.android.com.taapp.activityui.chat.entity.FileType;
import takjxh.android.com.taapp.activityui.chat.entity.UpdateSelectedStateListener;
import takjxh.android.com.taapp.activityui.chat.utils.BitmapLoader;
import takjxh.android.com.taapp.activityui.chat.view.SendFileView;


public class SendFileController implements View.OnClickListener, ViewPager.OnPageChangeListener,
        UpdateSelectedStateListener {
    public static final String TARGET_ID = "targetId";
    public static final String TARGET_APP_KEY = "targetAppKey";
    public static final String CONV_TYPE = "conversationType"; //value使用 ConversationType
    public static final String MSG_LIST_JSON = "msg_list_json";
    public static final int RESULT_CODE_SEND_FILE = 27;


    private DocumentFragment mDocumentFragment;
    private VideoFragment mVideoFragment;
    private ImageFragment mImgFragment;
    private AudioFragment mAudioFragment;
    private OtherFragment mOtherFragment;
    private SendFileActivity mContext;
    private SendFileView mSFView;
    // 选中的文件路径集合
    private HashMap<FileType, ArrayList<String>> mFileMap = new HashMap<FileType, ArrayList<String>>();
    private long mTotalSize;
    private ProgressDialog mDialog;
    private Conversation mConv;
    private AtomicInteger mIndex = new AtomicInteger(0);
    private int mSize;
    private final static int SEND_FILE = 0x4001;
    private MyHandler myHandler = new MyHandler(this);
    private Message[] mMsgs;


    public SendFileController(SendFileActivity context, SendFileView view) {
        this.mContext = context;
        this.mSFView = view;

        String targetId = mContext.getIntent().getStringExtra(TARGET_ID);
        String targetAppKey = mContext.getIntent().getStringExtra(TARGET_APP_KEY);
        ConversationType convType = (ConversationType) mContext.getIntent().getSerializableExtra(CONV_TYPE);
        switch (convType) {
            case single:
                mConv = JMessageClient.getSingleConversation(targetId, targetAppKey);
                break;
            case group:
                mConv = JMessageClient.getGroupConversation(Long.valueOf(targetId));
                break;
            case chatroom:
                mConv = JMessageClient.getChatRoomConversation(Long.valueOf(targetId));

        }


        List<Fragment> fragments = new ArrayList<Fragment>();
        // init Fragment
        mDocumentFragment = new DocumentFragment();
        mVideoFragment = new VideoFragment();
        mImgFragment = new ImageFragment();
        Bundle args=new Bundle();
        args.putString(TARGET_ID,targetId);
        args.putString(TARGET_APP_KEY,targetAppKey);
        args.putSerializable(CONV_TYPE,convType);
        mImgFragment.setArguments(args);
        mAudioFragment = new AudioFragment();
        mOtherFragment = new OtherFragment();
        fragments.add(mDocumentFragment);
        fragments.add(mVideoFragment);
        fragments.add(mImgFragment);
        fragments.add(mAudioFragment);
        fragments.add(mOtherFragment);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(mContext.getSupportFragmentManger(),
                fragments);
        mSFView.setViewPagerAdapter(viewPagerAdapter);
        mDocumentFragment.setController(this);
        mVideoFragment.setController(this);
        mImgFragment.setController(this);
        mAudioFragment.setController(this);
        mOtherFragment.setController(this);


    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {
        mSFView.setCurrentItem(i);
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.actionbar_file_btn:
                mSFView.setCurrentItem(0);
                break;
            case R.id.actionbar_video_btn:
                mSFView.setCurrentItem(1);
                break;
            case R.id.actionbar_album_btn:
                mSFView.setCurrentItem(2);
                break;
            case R.id.actionbar_audio_btn:
                mSFView.setCurrentItem(3);
                break;
            case R.id.actionbar_other_btn:
                mSFView.setCurrentItem(4);
                break;
            case R.id.return_btn:
                mContext.finish();
                break;
            case R.id.send_file_btn:
                if (mSize == 0) {
                    break;
                }
                mDialog = new ProgressDialog(mContext);
                mDialog.setCanceledOnTouchOutside(false);
                mDialog.setCancelable(false);
                mDialog.setMessage(mContext.getString(R.string.sending_hint));
                mDialog.show();
                Iterator<Map.Entry<FileType, ArrayList<String>>> iterator = mFileMap.entrySet().iterator();
                mMsgs = new Message[mSize];
                while (iterator.hasNext()) {
                    final Map.Entry<FileType, ArrayList<String>> entry = iterator.next();
                    ArrayList<String> list = entry.getValue();
                    switch (entry.getKey()) {
                        case image:
                            Bitmap bitmap;
                            for (final String path : list) {
                                if (BitmapLoader.verifyPictureSize(path)) {
                                    File file = new File(path);
                                    ImageContent.createImageContentAsync(file, new ImageContent.CreateImageContentCallback() {
                                        @Override
                                        public void gotResult(int status, String desc, ImageContent imageContent) {
                                            if (status == 0) {
                                                Message msg = mConv.createSendMessage(imageContent);
                                                mMsgs[mIndex.get()] = msg;
                                            } else {
                                                mMsgs[mIndex.get()] = null;
                                            }
                                            mIndex.incrementAndGet();
                                            if (mIndex.get() >= mSize) {
                                                myHandler.sendEmptyMessage(SEND_FILE);
                                            }
                                        }
                                    });
                                } else {
                                    bitmap = BitmapLoader.getBitmapFromFile(path, 720, 1280);
                                    ImageContent.createImageContentAsync(bitmap, new ImageContent.CreateImageContentCallback() {
                                        @Override
                                        public void gotResult(int status, String desc, ImageContent imageContent) {
                                            if (status == 0) {
                                                Message msg = mConv.createSendMessage(imageContent);
                                                mMsgs[mIndex.get()] = msg;
                                            } else {
                                                mMsgs[mIndex.get()] = null;
                                            }
                                            mIndex.incrementAndGet();
                                            if (mIndex.get() >= mSize) {
                                                myHandler.sendEmptyMessage(SEND_FILE);
                                            }
                                        }
                                    });
                                }
                            }
                            break;
                        default:
                            for (String path : list) {
                                File file = new File(path);
                                int index = path.lastIndexOf('/');
                                String fileName;
                                if (index > 0) {
                                    fileName = path.substring(index + 1);
                                    try {
                                        String substring = path.substring(path.lastIndexOf(".") + 1, path.length());
                                        FileContent content = new FileContent(file, fileName);
                                        content.setStringExtra("fileType", substring);
//                                        content.setStringExtra("fileType", entry.getKey().toString());
                                        content.setNumberExtra("fileSize", file.length());
                                        Message msg = mConv.createSendMessage(content);
                                        if (mIndex.get() < mSize) {
                                            mMsgs[mIndex.get()] = msg;
                                            mIndex.incrementAndGet();
                                            if (mIndex.get() >= mSize) {
                                                myHandler.sendEmptyMessage(SEND_FILE);
                                            }
                                        }
                                    } catch (FileNotFoundException e) {
                                        mDialog.dismiss();
                                        Toast.makeText(mContext, mContext.getString(R.string.jmui_file_not_found_toast),
                                                Toast.LENGTH_SHORT).show();
                                        mIndex.incrementAndGet();
                                        e.printStackTrace();
                                    } catch (JMFileSizeExceedException e) {
                                        mDialog.dismiss();
                                        Toast.makeText(mContext, mContext.getString(R.string.file_size_over_limit_hint),
                                                Toast.LENGTH_SHORT).show();
                                        e.printStackTrace();
                                    }
                                }

                            }
                    }
                }
                break;
        }
    }

    @Override
    public void onSelected(String path, long fileSize, FileType type) {
        ++mSize;
        if (mFileMap.containsKey(type)) {
            mFileMap.get(type).add(path);
        } else {
            ArrayList<String> list = new ArrayList<>();
            list.add(path);
            mFileMap.put(type, list);
        }
        mTotalSize += fileSize;
        NumberFormat ddf1 = NumberFormat.getNumberInstance();
        //保留小数点后两位
        ddf1.setMaximumFractionDigits(2);
        String sizeDisplay;
        if (mTotalSize > 1048576.0) {
            double result = mTotalSize / 1048576.0;
            sizeDisplay = ddf1.format(result) + "M";
        } else if (mTotalSize > 1024) {
            double result = mTotalSize / 1024;
            sizeDisplay = ddf1.format(result) + "K";

        } else {
            sizeDisplay = ddf1.format(mTotalSize) + "B";
        }
        mSFView.updateSelectedState(mSize, sizeDisplay);
    }

    @Override
    public void onUnselected(String path, long fileSize, FileType type) {
        if (mTotalSize > 0) {
            --mSize;
            mFileMap.get(type).remove(path);
            if (mFileMap.get(type).size() == 0) {
                mFileMap.remove(type);
            }
            mTotalSize -= fileSize;
            NumberFormat ddf1 = NumberFormat.getNumberInstance();
            //保留小数点后两位
            ddf1.setMaximumFractionDigits(2);
            String sizeDisplay;
            if (mTotalSize > 1048576.0) {
                double result = mTotalSize / 1048576.0;
                sizeDisplay = ddf1.format(result) + "M";
            } else if (mTotalSize > 1024) {
                double result = mTotalSize / 1024;
                sizeDisplay = ddf1.format(result) + "K";

            } else {
                sizeDisplay = ddf1.format(mTotalSize) + "B";
            }
            mSFView.updateSelectedState(mFileMap.values().size(), sizeDisplay);
        }
    }

    public int getPathListSize() {
        return mSize;
    }

    public long getTotalSize() {
        return mTotalSize;
    }


    private static class MyHandler extends Handler {
        private final WeakReference<SendFileController> mController;

        public MyHandler(SendFileController controller) {
            mController = new WeakReference<>(controller);
        }

        @Override
        public void handleMessage(android.os.Message msg) {
            super.handleMessage(msg);
            SendFileController controller = mController.get();
            if (controller != null) {
                switch (msg.what) {
                    case SEND_FILE:
                        Intent intent = new Intent();
                        intent.putExtra(MSG_LIST_JSON, Message.collectionToJson(Arrays.asList(controller.mMsgs)));
                        controller.mContext.setResult(RESULT_CODE_SEND_FILE, intent);
                        if (controller.mDialog != null) {
                            controller.mDialog.dismiss();
                        }
                        controller.mContext.finish();
                        break;
                }
            }
        }
    }
}

