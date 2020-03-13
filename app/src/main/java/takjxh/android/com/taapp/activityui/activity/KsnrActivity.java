package takjxh.android.com.taapp.activityui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.yczbj.ycvideoplayerlib.constant.ConstantKeys;
import org.yczbj.ycvideoplayerlib.controller.VideoPlayerController;
import org.yczbj.ycvideoplayerlib.inter.listener.OnPlayerTypeListener;
import org.yczbj.ycvideoplayerlib.inter.listener.OnVideoBackListener;
import org.yczbj.ycvideoplayerlib.manager.VideoPlayerManager;
import org.yczbj.ycvideoplayerlib.player.VideoPlayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import takjxh.android.com.commlibrary.image.ImageWrapper;
import takjxh.android.com.commlibrary.utils.BarUtil;
import takjxh.android.com.commlibrary.utils.L;
import takjxh.android.com.commlibrary.utils.ToastUtil;
import takjxh.android.com.commlibrary.view.activity.BaseActivity;
import takjxh.android.com.taapp.QbApplication;
import takjxh.android.com.taapp.R;
import takjxh.android.com.taapp.activityui.base.BaseDialog;
import takjxh.android.com.taapp.activityui.bean.ExamAddBean;
import takjxh.android.com.taapp.activityui.bean.ExamAnswerAddBean;
import takjxh.android.com.taapp.activityui.bean.KsnrBean;
import takjxh.android.com.taapp.activityui.bean.SysParamBean;
import takjxh.android.com.taapp.activityui.dialog.MessageDialog;
import takjxh.android.com.taapp.activityui.presenter.KsnrPresenter;
import takjxh.android.com.taapp.activityui.presenter.impl.IKsnrPresenter;
import takjxh.android.com.taapp.view.NormalTitleBar;

/**
 * 类名称：考试内容
 *
 * @Author: libaibing
 * @Date: 2019-10-17 11:01
 * @Description:
 **/
public class KsnrActivity extends BaseActivity<KsnrPresenter> implements IKsnrPresenter.IView, View.OnClickListener {

    public static void startAction(Activity activity, String code) {
        Intent intent = new Intent(activity, KsnrActivity.class);
        intent.putExtra("code", code);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @BindView(R.id.kswc)
    TextView kswc;

    @BindView(R.id.ntb)
    NormalTitleBar ntb;

    @BindView(R.id.imageBrowseViewPager)
    ViewPager imageBrowseViewPager;
    @BindView(R.id.mTvImageCount1)
    TextView mTvImageCount1;
    @BindView(R.id.mTvImageCount2)
    TextView mTvImageCount2;


    @BindView(R.id.ml1)
    LinearLayout ml1;
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.tv2)
    TextView tv2;

    private String code;

    private ArrayList<KsnrBean.ExamQuestionsBean> mList = new ArrayList<>(); //图片列表
    private int index = 0;

    private String msg = "";

    private int totalTime = 0;//耗时多少秒
    private String examId;

    private Handler mHandler = new Handler();

    private class MyRunnable implements Runnable {
        @Override
        public void run() {
            //  mTvTime5.setText(getTime());
            totalTime++;
            mHandler.postDelayed(this, 1000);
        }
    }

    private MyRunnable mRunnable;

    private VideoPlayerController controller;

    /**
     * 返回布局文件
     */
    @Override
    protected int getContentViewId() {
        return R.layout.activity_ksnr;
    }

    @Override
    protected KsnrPresenter createPresenter() {
        return new KsnrPresenter(this);
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
        code = getIntent().getStringExtra("code");


        ntb = findViewById(R.id.ntb);
        ntb.setTitleText("考试内容");
        ntb.setTvLeftVisiable(true);


        ntb.setOnBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    /**
     * 设置事件
     */
    @Override
    protected void initEvent() {
        super.initEvent();
    }

    /**
     * 初始化数据
     */
    @Override
    protected void initData() {
        super.initData();
        mPresenter.examlist("", code, "1", "20");
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void examlistSuccess(KsnrBean data) {
        if (data == null || data.examQuestions == null) {
            return;
        }
        examId = data.examId;
        mList.clear();
        mList.addAll(data.examQuestions);

        if (mList.size() > 0) {
            msg = mList.get(0).getKnowledgePoint();
            ntb.setRightImagVisibility(true);
            ntb.setRightImagSrc(R.mipmap.nw);

            ntb.setOnRightImagListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //每道题答题的有关提示
                    new MessageDialog.Builder(KsnrActivity.this)
                            // 标题可以不用填写
                            .setTitle("知识点")
                            // 内容必须要填写
                            .setMessage(msg)
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

                                }

                                @Override
                                public void onCancel(BaseDialog dialog) {
                                    // toast("取消了");
                                }
                            })
                            .show();

                }
            });


            mTvImageCount1.setText(index + 1 + "");
            mTvImageCount2.setText("/" + mList.size());

            imageBrowseViewPager.setAdapter(new ImageBrowseAdapter());
            imageBrowseViewPager.setCurrentItem(index);
            imageBrowseViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
                @Override
                public void onPageSelected(int position) {
                    super.onPageSelected(position);
                    index = position;
                    msg = mList.get(position).getKnowledgePoint();
                    mTvImageCount1.setText(index + 1 + "");
                    mTvImageCount2.setText("/" + mList.size());
                }
            });

            kswc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    examadd();

                }
            });

            if (mRunnable == null) {
                mRunnable = new MyRunnable();
                mHandler.postDelayed(mRunnable, 0);
            }
        }


    }

    @Override
    public void examlistFailed() {

    }

    @Override
    public void examaddSuccess(ExamAddBean data) {
        if (data == null) {
            mHandler.removeCallbacks(mRunnable);
            mRunnable = null;
            return;
        }
        KscjActivity.startAction(KsnrActivity.this, data.getExamId(), code, data);
        finish();
    }

    @Override
    public void examaddFailed() {

    }


    private class ImageBrowseAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            View mView = View.inflate(KsnrActivity.this, R.layout.layout_ksnr, null);

            LinearLayout mlDX = mView.findViewById(R.id.mlDX);
            LinearLayout mL2 = mView.findViewById(R.id.mL2);
            LinearLayout mL3 = mView.findViewById(R.id.mL3);

            TextView tvtitle = mView.findViewById(R.id.tvtitle);
            TextView tvtype = mView.findViewById(R.id.tvtype);

            ImageView iv = mView.findViewById(R.id.iv);
            VideoPlayer video_player = mView.findViewById(R.id.video_player);
            if (!TextUtils.isEmpty(mList.get(position).getImg())) {
                iv.setVisibility(View.VISIBLE);
                ImageWrapper.setImage(iv, mList.get(position).getImg(), R.drawable.pic_defalt, ImageWrapper.CENTER_CROP);

            } else {
                iv.setVisibility(View.GONE);
            }
            if (!TextUtils.isEmpty(mList.get(position).getVideo())) {
                video_player.setVisibility(View.VISIBLE);
                setVideoPlayer(video_player, mList.get(position).getVideo());
            } else {
                video_player.setVisibility(View.GONE);
            }

            tvtitle.setText("                " + (position + 1) + "." + mList.get(position).getTitle());

            String type=mList.get(position).getType();
            String code="";
            if(QbApplication.mBaseApplication.examtype!=null &&QbApplication.mBaseApplication.examtype.size()>0){
                 for(SysParamBean.ParamsBean.ExamtypeBean bean :QbApplication.mBaseApplication.examtype){
                     if("输入框".equals(bean.getValue()) && bean.getCode().equals(type)){
                         code="03";
                     }else if("单选框".equals(bean.getValue()) && bean.getCode().equals(type) ){
                         code="01";
                     }else if("复选框".equals(bean.getValue()) && bean.getCode().equals(type) ){
                         code="02";
                     }
                 }
            }


            if ("01".equals(code)) {
                tvtype.setText("单选题");
                mlDX.setVisibility(View.VISIBLE);

                mL2.setVisibility(View.GONE);
                mL3.setVisibility(View.GONE);


                LinearLayout mlradioButton1 = mView.findViewById(R.id.mlradioButton1);
                mlradioButton1.setVisibility(View.GONE);
                LinearLayout mlradioButton2 = mView.findViewById(R.id.mlradioButton2);
                mlradioButton2.setVisibility(View.GONE);
                LinearLayout mlradioButton3 = mView.findViewById(R.id.mlradioButton3);
                mlradioButton3.setVisibility(View.GONE);
                LinearLayout mlradioButton4 = mView.findViewById(R.id.mlradioButton4);
                mlradioButton4.setVisibility(View.GONE);
                LinearLayout mlradioButton5 = mView.findViewById(R.id.mlradioButton5);
                mlradioButton5.setVisibility(View.GONE);
                LinearLayout mlradioButton6 = mView.findViewById(R.id.mlradioButton6);
                mlradioButton6.setVisibility(View.GONE);
                LinearLayout mlradioButton7 = mView.findViewById(R.id.mlradioButton7);
                mlradioButton7.setVisibility(View.GONE);
                LinearLayout mlradioButton8 = mView.findViewById(R.id.mlradioButton8);
                mlradioButton8.setVisibility(View.GONE);
                LinearLayout mlradioButton9 = mView.findViewById(R.id.mlradioButton9);
                mlradioButton9.setVisibility(View.GONE);
                LinearLayout mlradioButton10 = mView.findViewById(R.id.mlradioButton10);
                mlradioButton10.setVisibility(View.GONE);
                LinearLayout mlradioButton11 = mView.findViewById(R.id.mlradioButton11);
                mlradioButton11.setVisibility(View.GONE);
                LinearLayout mlradioButton12 = mView.findViewById(R.id.mlradioButton12);
                mlradioButton1.setVisibility(View.GONE);
                LinearLayout mlradioButton13 = mView.findViewById(R.id.mlradioButton13);
                mlradioButton13.setVisibility(View.GONE);
                LinearLayout mlradioButton14 = mView.findViewById(R.id.mlradioButton14);
                mlradioButton14.setVisibility(View.GONE);
                LinearLayout mlradioButton15 = mView.findViewById(R.id.mlradioButton15);
                mlradioButton15.setVisibility(View.GONE);

                TextView radioButton1 = mView.findViewById(R.id.radioButton1);
                TextView radioButton2 = mView.findViewById(R.id.radioButton2);
                TextView radioButton3 = mView.findViewById(R.id.radioButton3);
                TextView radioButton4 = mView.findViewById(R.id.radioButton4);
                TextView radioButton5 = mView.findViewById(R.id.radioButton5);
                TextView radioButton6 = mView.findViewById(R.id.radioButton6);
                TextView radioButton7 = mView.findViewById(R.id.radioButton7);
                TextView radioButton8 = mView.findViewById(R.id.radioButton8);
                TextView radioButton9 = mView.findViewById(R.id.radioButton9);
                TextView radioButton10 = mView.findViewById(R.id.radioButton10);
                TextView radioButton11 = mView.findViewById(R.id.radioButton11);
                TextView radioButton12 = mView.findViewById(R.id.radioButton12);
                TextView radioButton13 = mView.findViewById(R.id.radioButton13);
                TextView radioButton14 = mView.findViewById(R.id.radioButton14);
                TextView radioButton15 = mView.findViewById(R.id.radioButton15);

                TextView tv_type1 = mView.findViewById(R.id.tv_type1);
                TextView tv_type2 = mView.findViewById(R.id.tv_type2);
                TextView tv_type3 = mView.findViewById(R.id.tv_type3);
                TextView tv_type4 = mView.findViewById(R.id.tv_type4);
                TextView tv_type5 = mView.findViewById(R.id.tv_type5);
                TextView tv_type6 = mView.findViewById(R.id.tv_type6);
                TextView tv_type7 = mView.findViewById(R.id.tv_type7);
                TextView tv_type8 = mView.findViewById(R.id.tv_type8);
                TextView tv_type9 = mView.findViewById(R.id.tv_type9);
                TextView tv_type10 = mView.findViewById(R.id.tv_type10);
                TextView tv_type11 = mView.findViewById(R.id.tv_type11);
                TextView tv_type12 = mView.findViewById(R.id.tv_type12);
                TextView tv_type13 = mView.findViewById(R.id.tv_type13);
                TextView tv_type14 = mView.findViewById(R.id.tv_type14);
                TextView tv_type15 = mView.findViewById(R.id.tv_type15);


                if (mList.get(position).getItems() != null) {
                    for (int a = 0; a < mList.get(position).getItems().size(); a++) {
                        int num = a;
                        if (a == 0) {
                            radioButton1.setSelected(mList.get(position).getItems().get(a).isCheck);
                            tv_type1.setSelected(mList.get(position).getItems().get(a).isCheck);

                            radioButton1.setText(mList.get(position).getItems().get(0).getDes());
                            radioButton1.setTag(mList.get(position).getItems().get(0).getId());
                            mlradioButton1.setVisibility(View.VISIBLE);
                            mlradioButton1.setOnClickListener(new View.OnClickListener() {

                                @Override
                                public void onClick(View v) {
                                    radioButtonL(mList.get(position), position, num, ImageBrowseAdapter.this);
                                }
                            });

                        } else if (a == 1) {
                            radioButton2.setSelected(mList.get(position).getItems().get(a).isCheck);
                            tv_type2.setSelected(mList.get(position).getItems().get(a).isCheck);

                            radioButton2.setText(mList.get(position).getItems().get(1).getDes());
                            radioButton2.setTag(mList.get(position).getItems().get(1).getId());
                            mlradioButton2.setVisibility(View.VISIBLE);
                            mlradioButton2.setOnClickListener(new View.OnClickListener() {

                                @Override
                                public void onClick(View v) {
                                    radioButtonL(mList.get(position), position, num, ImageBrowseAdapter.this);
                                }
                            });

                        } else if (a == 2) {
                            radioButton3.setSelected(mList.get(position).getItems().get(a).isCheck);
                            tv_type3.setSelected(mList.get(position).getItems().get(a).isCheck);

                            radioButton3.setText(mList.get(position).getItems().get(2).getDes());
                            radioButton3.setTag(mList.get(position).getItems().get(2).getId());
                            mlradioButton3.setVisibility(View.VISIBLE);
                            mlradioButton3.setOnClickListener(new View.OnClickListener() {

                                @Override
                                public void onClick(View v) {
                                    radioButtonL(mList.get(position), position, num, ImageBrowseAdapter.this);
                                }
                            });


                        } else if (a == 3) {
                            radioButton4.setSelected(mList.get(position).getItems().get(a).isCheck);
                            tv_type4.setSelected(mList.get(position).getItems().get(a).isCheck);

                            radioButton4.setText(mList.get(position).getItems().get(3).getDes());
                            radioButton4.setTag(mList.get(position).getItems().get(3).getId());
                            mlradioButton4.setVisibility(View.VISIBLE);
                            mlradioButton4.setOnClickListener(new View.OnClickListener() {

                                @Override
                                public void onClick(View v) {
                                    radioButtonL(mList.get(position), position, num, ImageBrowseAdapter.this);
                                }
                            });

                        } else if (a == 4) {
                            radioButton5.setSelected(mList.get(position).getItems().get(a).isCheck);
                            tv_type5.setSelected(mList.get(position).getItems().get(a).isCheck);

                            radioButton5.setText(mList.get(position).getItems().get(4).getDes());
                            radioButton5.setTag(mList.get(position).getItems().get(4).getId());
                            mlradioButton5.setVisibility(View.VISIBLE);
                            mlradioButton5.setOnClickListener(new View.OnClickListener() {

                                @Override
                                public void onClick(View v) {
                                    radioButtonL(mList.get(position), position, num, ImageBrowseAdapter.this);
                                }
                            });

                        } else if (a == 5) {
                            radioButton6.setSelected(mList.get(position).getItems().get(a).isCheck);
                            tv_type6.setSelected(mList.get(position).getItems().get(a).isCheck);

                            radioButton6.setText(mList.get(position).getItems().get(5).getDes());
                            radioButton6.setTag(mList.get(position).getItems().get(5).getId());
                            mlradioButton6.setVisibility(View.VISIBLE);
                            mlradioButton6.setOnClickListener(new View.OnClickListener() {

                                @Override
                                public void onClick(View v) {
                                    radioButtonL(mList.get(position), position, num, ImageBrowseAdapter.this);
                                }
                            });

                        } else if (a == 6) {
                            radioButton7.setSelected(mList.get(position).getItems().get(a).isCheck);
                            tv_type7.setSelected(mList.get(position).getItems().get(a).isCheck);

                            radioButton7.setText(mList.get(position).getItems().get(6).getDes());
                            radioButton7.setTag(mList.get(position).getItems().get(6).getId());
                            mlradioButton7.setVisibility(View.VISIBLE);
                            mlradioButton7.setOnClickListener(new View.OnClickListener() {

                                @Override
                                public void onClick(View v) {
                                    radioButtonL(mList.get(position), position, num, ImageBrowseAdapter.this);
                                }
                            });

                        } else if (a == 7) {
                            radioButton8.setSelected(mList.get(position).getItems().get(a).isCheck);
                            tv_type8.setSelected(mList.get(position).getItems().get(a).isCheck);

                            radioButton8.setText(mList.get(position).getItems().get(7).getDes());
                            radioButton8.setTag(mList.get(position).getItems().get(7).getId());
                            mlradioButton8.setVisibility(View.VISIBLE);
                            mlradioButton8.setOnClickListener(new View.OnClickListener() {

                                @Override
                                public void onClick(View v) {
                                    radioButtonL(mList.get(position), position, num, ImageBrowseAdapter.this);
                                }
                            });
                        } else if (a == 8) {
                            radioButton9.setSelected(mList.get(position).getItems().get(a).isCheck);
                            tv_type9.setSelected(mList.get(position).getItems().get(a).isCheck);

                            radioButton9.setText(mList.get(position).getItems().get(8).getDes());
                            radioButton9.setTag(mList.get(position).getItems().get(8).getId());
                            mlradioButton9.setVisibility(View.VISIBLE);
                            mlradioButton9.setOnClickListener(new View.OnClickListener() {

                                @Override
                                public void onClick(View v) {
                                    radioButtonL(mList.get(position), position, num, ImageBrowseAdapter.this);
                                }
                            });

                        } else if (a == 9) {
                            radioButton10.setSelected(mList.get(position).getItems().get(a).isCheck);
                            tv_type10.setSelected(mList.get(position).getItems().get(a).isCheck);

                            radioButton10.setText(mList.get(position).getItems().get(9).getDes());
                            radioButton10.setTag(mList.get(position).getItems().get(9).getId());
                            mlradioButton10.setVisibility(View.VISIBLE);
                            mlradioButton10.setOnClickListener(new View.OnClickListener() {

                                @Override
                                public void onClick(View v) {
                                    radioButtonL(mList.get(position), position, num, ImageBrowseAdapter.this);
                                }
                            });
                        } else if (a == 10) {
                            radioButton11.setSelected(mList.get(position).getItems().get(a).isCheck);
                            tv_type11.setSelected(mList.get(position).getItems().get(a).isCheck);

                            radioButton11.setText(mList.get(position).getItems().get(10).getDes());
                            radioButton11.setTag(mList.get(position).getItems().get(10).getId());
                            mlradioButton11.setVisibility(View.VISIBLE);
                            mlradioButton11.setOnClickListener(new View.OnClickListener() {

                                @Override
                                public void onClick(View v) {
                                    radioButtonL(mList.get(position), position, num, ImageBrowseAdapter.this);
                                }
                            });

                        } else if (a == 11) {
                            radioButton12.setSelected(mList.get(position).getItems().get(a).isCheck);
                            tv_type12.setSelected(mList.get(position).getItems().get(a).isCheck);

                            radioButton12.setText(mList.get(position).getItems().get(11).getDes());
                            radioButton12.setTag(mList.get(position).getItems().get(11).getId());
                            mlradioButton12.setVisibility(View.VISIBLE);
                            mlradioButton12.setOnClickListener(new View.OnClickListener() {

                                @Override
                                public void onClick(View v) {
                                    radioButtonL(mList.get(position), position, num, ImageBrowseAdapter.this);
                                }
                            });

                        } else if (a == 12) {
                            radioButton13.setSelected(mList.get(position).getItems().get(a).isCheck);
                            tv_type13.setSelected(mList.get(position).getItems().get(a).isCheck);

                            radioButton13.setText(mList.get(position).getItems().get(12).getDes());
                            radioButton13.setTag(mList.get(position).getItems().get(12).getId());
                            mlradioButton13.setVisibility(View.VISIBLE);
                            mlradioButton13.setOnClickListener(new View.OnClickListener() {

                                @Override
                                public void onClick(View v) {
                                    radioButtonL(mList.get(position), position, num, ImageBrowseAdapter.this);
                                }
                            });

                        } else if (a == 13) {
                            radioButton14.setSelected(mList.get(position).getItems().get(a).isCheck);
                            tv_type14.setSelected(mList.get(position).getItems().get(a).isCheck);

                            radioButton14.setText(mList.get(position).getItems().get(13).getDes());
                            radioButton14.setTag(mList.get(position).getItems().get(13).getId());
                            mlradioButton14.setVisibility(View.VISIBLE);
                            mlradioButton14.setOnClickListener(new View.OnClickListener() {

                                @Override
                                public void onClick(View v) {
                                    radioButtonL(mList.get(position), position, num, ImageBrowseAdapter.this);
                                }
                            });
                        } else if (a == 14) {
                            radioButton15.setSelected(mList.get(position).getItems().get(a).isCheck);
                            tv_type15.setSelected(mList.get(position).getItems().get(a).isCheck);

                            radioButton15.setText(mList.get(position).getItems().get(14).getDes());
                            radioButton15.setTag(mList.get(position).getItems().get(14).getId());
                            mlradioButton15.setVisibility(View.VISIBLE);
                            mlradioButton15.setOnClickListener(new View.OnClickListener() {

                                @Override
                                public void onClick(View v) {
                                    radioButtonL(mList.get(position), position, num, ImageBrowseAdapter.this);
                                }
                            });
                        }
                    }
                }

            } else if ("02".equals(code)) {
                tvtype.setText("多选题");
                mlDX.setVisibility(View.VISIBLE);
                mL2.setVisibility(View.GONE);
                mL3.setVisibility(View.GONE);


                TextView cb_1 = mView.findViewById(R.id.radioButton1);
                TextView cb_2 = mView.findViewById(R.id.radioButton2);
                TextView cb_3 = mView.findViewById(R.id.radioButton3);
                TextView cb_4 = mView.findViewById(R.id.radioButton4);
                TextView cb_5 = mView.findViewById(R.id.radioButton5);
                TextView cb_6 = mView.findViewById(R.id.radioButton6);
                TextView cb_7 = mView.findViewById(R.id.radioButton7);
                TextView cb_8 = mView.findViewById(R.id.radioButton8);
                TextView cb_9 = mView.findViewById(R.id.radioButton9);
                TextView cb_10 = mView.findViewById(R.id.radioButton10);
                TextView cb_11 = mView.findViewById(R.id.radioButton11);
                TextView cb_12 = mView.findViewById(R.id.radioButton12);
                TextView cb_13 = mView.findViewById(R.id.radioButton13);
                TextView cb_14 = mView.findViewById(R.id.radioButton14);
                TextView cb_15 = mView.findViewById(R.id.radioButton15);

                LinearLayout mlradioButton1 = mView.findViewById(R.id.mlradioButton1);
                mlradioButton1.setVisibility(View.GONE);
                LinearLayout mlradioButton2 = mView.findViewById(R.id.mlradioButton2);
                mlradioButton2.setVisibility(View.GONE);
                LinearLayout mlradioButton3 = mView.findViewById(R.id.mlradioButton3);
                mlradioButton3.setVisibility(View.GONE);
                LinearLayout mlradioButton4 = mView.findViewById(R.id.mlradioButton4);
                mlradioButton4.setVisibility(View.GONE);
                LinearLayout mlradioButton5 = mView.findViewById(R.id.mlradioButton5);
                mlradioButton5.setVisibility(View.GONE);
                LinearLayout mlradioButton6 = mView.findViewById(R.id.mlradioButton6);
                mlradioButton6.setVisibility(View.GONE);
                LinearLayout mlradioButton7 = mView.findViewById(R.id.mlradioButton7);
                mlradioButton7.setVisibility(View.GONE);
                LinearLayout mlradioButton8 = mView.findViewById(R.id.mlradioButton8);
                mlradioButton8.setVisibility(View.GONE);
                LinearLayout mlradioButton9 = mView.findViewById(R.id.mlradioButton9);
                mlradioButton9.setVisibility(View.GONE);
                LinearLayout mlradioButton10 = mView.findViewById(R.id.mlradioButton10);
                mlradioButton10.setVisibility(View.GONE);
                LinearLayout mlradioButton11 = mView.findViewById(R.id.mlradioButton11);
                mlradioButton11.setVisibility(View.GONE);
                LinearLayout mlradioButton12 = mView.findViewById(R.id.mlradioButton12);
                mlradioButton1.setVisibility(View.GONE);
                LinearLayout mlradioButton13 = mView.findViewById(R.id.mlradioButton13);
                mlradioButton13.setVisibility(View.GONE);
                LinearLayout mlradioButton14 = mView.findViewById(R.id.mlradioButton14);
                mlradioButton14.setVisibility(View.GONE);
                LinearLayout mlradioButton15 = mView.findViewById(R.id.mlradioButton15);
                mlradioButton15.setVisibility(View.GONE);


                TextView tv_type1 = mView.findViewById(R.id.tv_type1);
                TextView tv_type2 = mView.findViewById(R.id.tv_type2);
                TextView tv_type3 = mView.findViewById(R.id.tv_type3);
                TextView tv_type4 = mView.findViewById(R.id.tv_type4);
                TextView tv_type5 = mView.findViewById(R.id.tv_type5);
                TextView tv_type6 = mView.findViewById(R.id.tv_type6);
                TextView tv_type7 = mView.findViewById(R.id.tv_type7);
                TextView tv_type8 = mView.findViewById(R.id.tv_type8);
                TextView tv_type9 = mView.findViewById(R.id.tv_type9);
                TextView tv_type10 = mView.findViewById(R.id.tv_type10);
                TextView tv_type11 = mView.findViewById(R.id.tv_type11);
                TextView tv_type12 = mView.findViewById(R.id.tv_type12);
                TextView tv_type13 = mView.findViewById(R.id.tv_type13);
                TextView tv_type14 = mView.findViewById(R.id.tv_type14);
                TextView tv_type15 = mView.findViewById(R.id.tv_type15);

                if (mList.get(position).getItems() != null) {
                    for (int a = 0; a < mList.get(position).getItems().size(); a++) {
                        int num = a;
                        if (a == 0) {
                            cb_1.setSelected(mList.get(position).getItems().get(a).isCheck);
                            tv_type1.setSelected(mList.get(position).getItems().get(a).isCheck);


                            cb_1.setText(mList.get(position).getItems().get(0).getDes());
                            cb_1.setTag(mList.get(position).getItems().get(0).getId());
                            mlradioButton1.setVisibility(View.VISIBLE);

                            mlradioButton1.setOnClickListener(new View.OnClickListener() {

                                @Override
                                public void onClick(View v) {

                                    boolean isChecked = !mList.get(position).getItems().get(num).isCheck;
                                    checkBoxL(mList.get(position), position, num, isChecked, ImageBrowseAdapter.this);
                                }
                            });
                        } else if (a == 1) {
                            cb_2.setSelected(mList.get(position).getItems().get(a).isCheck);
                            tv_type2.setSelected(mList.get(position).getItems().get(a).isCheck);

                            cb_2.setText(mList.get(position).getItems().get(1).getDes());
                            cb_2.setTag(mList.get(position).getItems().get(1).getId());
                            mlradioButton2.setVisibility(View.VISIBLE);

                            mlradioButton2.setOnClickListener(new View.OnClickListener() {

                                @Override
                                public void onClick(View v) {
                                    boolean isChecked = !mList.get(position).getItems().get(num).isCheck;
                                    checkBoxL(mList.get(position), position, num, isChecked, ImageBrowseAdapter.this);
                                }
                            });
                        } else if (a == 2) {
                            cb_3.setSelected(mList.get(position).getItems().get(a).isCheck);
                            tv_type3.setSelected(mList.get(position).getItems().get(a).isCheck);


                            cb_3.setText(mList.get(position).getItems().get(2).getDes());
                            cb_3.setTag(mList.get(position).getItems().get(2).getId());
                            mlradioButton3.setVisibility(View.VISIBLE);
                            mlradioButton3.setOnClickListener(new View.OnClickListener() {

                                @Override
                                public void onClick(View v) {

                                    boolean isChecked = !mList.get(position).getItems().get(num).isCheck;
                                    checkBoxL(mList.get(position), position, num, isChecked, ImageBrowseAdapter.this);
                                }
                            });

                        } else if (a == 3) {
                            cb_4.setSelected(mList.get(position).getItems().get(a).isCheck);
                            tv_type4.setSelected(mList.get(position).getItems().get(a).isCheck);


                            cb_4.setText(mList.get(position).getItems().get(3).getDes());
                            cb_4.setTag(mList.get(position).getItems().get(3).getId());
                            mlradioButton4.setVisibility(View.VISIBLE);
                            mlradioButton4.setOnClickListener(new View.OnClickListener() {

                                @Override
                                public void onClick(View v) {

                                    boolean isChecked = !mList.get(position).getItems().get(num).isCheck;
                                    checkBoxL(mList.get(position), position, num, isChecked, ImageBrowseAdapter.this);
                                }
                            });

                        } else if (a == 4) {
                            cb_5.setSelected(mList.get(position).getItems().get(a).isCheck);
                            tv_type5.setSelected(mList.get(position).getItems().get(a).isCheck);


                            cb_5.setText(mList.get(position).getItems().get(4).getDes());
                            cb_5.setTag(mList.get(position).getItems().get(4).getId());

                            mlradioButton5.setVisibility(View.VISIBLE);
                            mlradioButton5.setOnClickListener(new View.OnClickListener() {

                                @Override
                                public void onClick(View v) {

                                    boolean isChecked = !mList.get(position).getItems().get(num).isCheck;
                                    checkBoxL(mList.get(position), position, num, isChecked, ImageBrowseAdapter.this);
                                }
                            });

                        } else if (a == 5) {
                            cb_6.setSelected(mList.get(position).getItems().get(a).isCheck);
                            tv_type6.setSelected(mList.get(position).getItems().get(a).isCheck);

                            cb_6.setText(mList.get(position).getItems().get(5).getDes());
                            cb_6.setTag(mList.get(position).getItems().get(5).getId());

                            mlradioButton6.setVisibility(View.VISIBLE);
                            mlradioButton6.setOnClickListener(new View.OnClickListener() {

                                @Override
                                public void onClick(View v) {

                                    boolean isChecked = !mList.get(position).getItems().get(num).isCheck;
                                    checkBoxL(mList.get(position), position, num, isChecked, ImageBrowseAdapter.this);
                                }
                            });

                        } else if (a == 6) {
                            cb_7.setSelected(mList.get(position).getItems().get(a).isCheck);
                            tv_type7.setSelected(mList.get(position).getItems().get(a).isCheck);

                            cb_7.setText(mList.get(position).getItems().get(6).getDes());
                            cb_7.setTag(mList.get(position).getItems().get(6).getId());

                            mlradioButton7.setVisibility(View.VISIBLE);
                            mlradioButton7.setOnClickListener(new View.OnClickListener() {

                                @Override
                                public void onClick(View v) {

                                    boolean isChecked = !mList.get(position).getItems().get(num).isCheck;
                                    checkBoxL(mList.get(position), position, num, isChecked, ImageBrowseAdapter.this);
                                }
                            });
                        } else if (a == 7) {
                            cb_8.setSelected(mList.get(position).getItems().get(a).isCheck);
                            tv_type8.setSelected(mList.get(position).getItems().get(a).isCheck);

                            cb_8.setText(mList.get(position).getItems().get(7).getDes());
                            cb_8.setTag(mList.get(position).getItems().get(7).getId());
                            mlradioButton8.setVisibility(View.VISIBLE);
                            mlradioButton8.setOnClickListener(new View.OnClickListener() {

                                @Override
                                public void onClick(View v) {

                                    boolean isChecked = !mList.get(position).getItems().get(num).isCheck;
                                    checkBoxL(mList.get(position), position, num, isChecked, ImageBrowseAdapter.this);
                                }
                            });

                        } else if (a == 8) {
                            cb_9.setSelected(mList.get(position).getItems().get(a).isCheck);
                            tv_type9.setSelected(mList.get(position).getItems().get(a).isCheck);

                            cb_9.setText(mList.get(position).getItems().get(8).getDes());
                            cb_9.setTag(mList.get(position).getItems().get(8).getId());
                            mlradioButton9.setVisibility(View.VISIBLE);
                            mlradioButton9.setOnClickListener(new View.OnClickListener() {

                                @Override
                                public void onClick(View v) {

                                    boolean isChecked = !mList.get(position).getItems().get(num).isCheck;
                                    checkBoxL(mList.get(position), position, num, isChecked, ImageBrowseAdapter.this);
                                }
                            });
                        } else if (a == 9) {
                            cb_10.setSelected(mList.get(position).getItems().get(a).isCheck);
                            tv_type10.setSelected(mList.get(position).getItems().get(a).isCheck);

                            cb_10.setText(mList.get(position).getItems().get(9).getDes());
                            cb_10.setTag(mList.get(position).getItems().get(9).getId());
                            mlradioButton10.setVisibility(View.VISIBLE);
                            mlradioButton10.setOnClickListener(new View.OnClickListener() {

                                @Override
                                public void onClick(View v) {

                                    boolean isChecked = !mList.get(position).getItems().get(num).isCheck;
                                    checkBoxL(mList.get(position), position, num, isChecked, ImageBrowseAdapter.this);
                                }
                            });
                        } else if (a == 10) {
                            cb_11.setSelected(mList.get(position).getItems().get(a).isCheck);
                            tv_type11.setSelected(mList.get(position).getItems().get(a).isCheck);

                            cb_11.setText(mList.get(position).getItems().get(10).getDes());
                            cb_11.setTag(mList.get(position).getItems().get(10).getId());
                            mlradioButton11.setVisibility(View.VISIBLE);
                            mlradioButton11.setOnClickListener(new View.OnClickListener() {

                                @Override
                                public void onClick(View v) {

                                    boolean isChecked = !mList.get(position).getItems().get(num).isCheck;

                                    checkBoxL(mList.get(position), position, num, isChecked, ImageBrowseAdapter.this);
                                }
                            });

                        } else if (a == 11) {
                            cb_12.setSelected(mList.get(position).getItems().get(a).isCheck);
                            tv_type12.setSelected(mList.get(position).getItems().get(a).isCheck);

                            cb_12.setText(mList.get(position).getItems().get(11).getDes());
                            cb_12.setTag(mList.get(position).getItems().get(11).getId());
                            mlradioButton12.setVisibility(View.VISIBLE);
                            mlradioButton12.setOnClickListener(new View.OnClickListener() {

                                @Override
                                public void onClick(View v) {

                                    boolean isChecked = !mList.get(position).getItems().get(num).isCheck;
                                    checkBoxL(mList.get(position), position, num, isChecked, ImageBrowseAdapter.this);
                                }
                            });

                        } else if (a == 12) {
                            cb_13.setSelected(mList.get(position).getItems().get(a).isCheck);
                            tv_type13.setSelected(mList.get(position).getItems().get(a).isCheck);

                            cb_13.setText(mList.get(position).getItems().get(12).getDes());
                            cb_13.setTag(mList.get(position).getItems().get(12).getId());
                            mlradioButton13.setVisibility(View.VISIBLE);
                            mlradioButton13.setOnClickListener(new View.OnClickListener() {

                                @Override
                                public void onClick(View v) {

                                    boolean isChecked = !mList.get(position).getItems().get(num).isCheck;
                                    checkBoxL(mList.get(position), position, num, isChecked, ImageBrowseAdapter.this);
                                }
                            });

                        } else if (a == 13) {
                            cb_14.setSelected(mList.get(position).getItems().get(a).isCheck);
                            tv_type14.setSelected(mList.get(position).getItems().get(a).isCheck);

                            cb_14.setText(mList.get(position).getItems().get(13).getDes());
                            cb_14.setTag(mList.get(position).getItems().get(13).getId());
                            mlradioButton14.setVisibility(View.VISIBLE);

                            mlradioButton14.setOnClickListener(new View.OnClickListener() {

                                @Override
                                public void onClick(View v) {

                                    boolean isChecked = !mList.get(position).getItems().get(num).isCheck;

                                    checkBoxL(mList.get(position), position, num, isChecked, ImageBrowseAdapter.this);
                                }
                            });
                        } else if (a == 14) {
                            cb_15.setSelected(mList.get(position).getItems().get(a).isCheck);
                            tv_type15.setSelected(mList.get(position).getItems().get(a).isCheck);

                            cb_15.setText(mList.get(position).getItems().get(14).getDes());
                            cb_15.setTag(mList.get(position).getItems().get(14).getId());
                            mlradioButton15.setVisibility(View.VISIBLE);
                            mlradioButton15.setOnClickListener(new View.OnClickListener() {

                                @Override
                                public void onClick(View v) {

                                    boolean isChecked = !mList.get(position).getItems().get(num).isCheck;
                                    checkBoxL(mList.get(position), position, num, isChecked, ImageBrowseAdapter.this);
                                }
                            });
                        }

                    }
                }
            } else if ("03".equals(code)) {
                tvtype.setText("填空题");
                mlDX.setVisibility(View.GONE);
                mL2.setVisibility(View.VISIBLE);
                mL3.setVisibility(View.GONE);
                EditText mEditText = (EditText) mView.findViewById(R.id.ed4);

                if (mEditText.getTag() != null && mEditText.getTag() instanceof TextWatcher) {
                    mEditText.removeTextChangedListener((TextWatcher) mEditText.getTag());
                }
                TextWatcher textWatcher = new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        mList.get(position).inputContent = s.toString();

                    }
                };
                mEditText.addTextChangedListener(textWatcher);
                mEditText.setTag(textWatcher);
                mEditText.setText(mList.get(position).inputContent);
            } else if ("04".equals(code)) {
                tvtype.setText("问答题");
                mlDX.setVisibility(View.GONE);
                mL2.setVisibility(View.GONE);
                mL3.setVisibility(View.VISIBLE);
                EditText mEditText = (EditText) mView.findViewById(R.id.clue_content);

                if (mEditText.getTag() != null && mEditText.getTag() instanceof TextWatcher) {
                    mEditText.removeTextChangedListener((TextWatcher) mEditText.getTag());
                }
                TextWatcher textWatcher = new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        mList.get(position).inputContent = s.toString();

                    }
                };
                mEditText.addTextChangedListener(textWatcher);
                mEditText.setTag(textWatcher);
                mEditText.setText(mList.get(position).inputContent);
            }


            container.addView(mView);
            return mView;
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }

        @Override
        //不调用会报错
        public void destroyItem(ViewGroup container, int position, Object object) {

            container.removeView((View) object);
        }
    }

    private void examadd() {

        List<ExamAnswerAddBean> answerList = new ArrayList<>();
        int num = 0;
        for (int i = 0; i < mList.size(); i++) {
            if ("01".equals(mList.get(i).getType())) {
                if (mList.get(i).getItems() != null && mList.get(i).getItems().size() > 0) {
                    for (int j = 0; j < mList.get(i).getItems().size(); j++) {
                        if (mList.get(i).getItems().get(j).isCheck) {
                            num++;
                            break;
                        }
                    }
                }
            } else if ("02".equals(mList.get(i).getType())) {
                if (mList.get(i).getItems() != null && mList.get(i).getItems().size() > 0) {
                    for (int j = 0; j < mList.get(i).getItems().size(); j++) {
                        if (mList.get(i).getItems().get(j).isCheck) {
                            num++;
                            break;
                        }
                    }
                }
            } else if ("03".equals(mList.get(i).getType())) {
                if (!TextUtils.isEmpty(mList.get(i).inputContent)) {
                    num++;
                }
            } else if ("04".equals(mList.get(i).getType())) {
                if (!TextUtils.isEmpty(mList.get(i).inputContent)) {
                    num++;
                }
            }
        }

        if (num < mList.size()) {

            new MessageDialog.Builder(KsnrActivity.this)
                    // 标题可以不用填写
                    .setTitle("温馨提示")
                    // 内容必须要填写
                    .setMessage("您还没有完成考试题目，确定提交试卷吗？")
                    // 确定按钮文本
                    .setConfirm("确定")
                    // 设置 null 表示不显示取消按钮
                    .setCancel("取消")
                    // 设置点击按钮后不关闭对话框
                    //.setAutoDismiss(false)
                    .setListener(new MessageDialog.OnListener() {

                        @Override
                        public void onConfirm(BaseDialog dialog) {
                            // toast("确定了");
                            for (int i = 0; i < mList.size(); i++) {
                                if ("01".equals(mList.get(i).getType())) {
                                    if (mList.get(i).getItems() != null && mList.get(i).getItems().size() > 0) {
                                        for (int j = 0; j < mList.get(i).getItems().size(); j++) {
                                            if (mList.get(i).getItems().get(j).isCheck) {
                                                answerList.add(new ExamAnswerAddBean(mList.get(i).getId(),
                                                        mList.get(i).getItems().get(j).getId(),
                                                        mList.get(i).getItems().get(j).inputContent));

                                            }

                                        }
                                    }
                                } else if ("02".equals(mList.get(i).getType())) {
                                    if (mList.get(i).getItems() != null && mList.get(i).getItems().size() > 0) {
                                        for (int j = 0; j < mList.get(i).getItems().size(); j++) {
                                            if (mList.get(i).getItems().get(j).isCheck) {
                                                answerList.add(new ExamAnswerAddBean(mList.get(i).getId(),
                                                        mList.get(i).getItems().get(j).getId(),
                                                        mList.get(i).getItems().get(j).inputContent));
                                            }

                                        }
                                    }
                                } else if ("03".equals(mList.get(i).getType())) {
                                    if (!TextUtils.isEmpty(mList.get(i).inputContent)) {
                                        for (int j = 0; j < mList.get(i).getItems().size(); j++) {
                                            answerList.add(new ExamAnswerAddBean(mList.get(i).getId(),
                                                    mList.get(i).getItems().get(j).getId(),
                                                    mList.get(i).inputContent));
                                        }
                                    }

                                } else if ("04".equals(mList.get(i).getType())) {
                                    if (!TextUtils.isEmpty(mList.get(i).inputContent)) {
                                        for (int j = 0; j < mList.get(i).getItems().size(); j++) {
                                            answerList.add(new ExamAnswerAddBean(mList.get(i).getId(),
                                                    mList.get(i).getItems().get(j).getId(),
                                                    mList.get(i).inputContent));
                                        }
                                    }

                                }
                            }

                            Map<String, Object> queryMap = new HashMap<>();
                            queryMap.put("totalTime", totalTime);
                            Log.d("TAG", "--totalTime--:" + totalTime);
                            queryMap.put("answerList", answerList);
                            for (ExamAnswerAddBean bean : answerList) {
                                Log.d("TAG", "--getQuestionId--:" + bean.getQuestionId() + "--getItemId-:" + bean.getItemId() + "--getInputContent-:" + bean.getInputContent());
                            }

                            mPresenter.examadd("", queryMap);
                        }

                        @Override
                        public void onCancel(BaseDialog dialog) {
                            // toast("取消了");
                        }
                    })
                    .show();

           /* ToastUtil.showToast(this, "请您完成全部的考试题目再提交试卷，谢谢！");
            return;*/
        } else {
            for (int i = 0; i < mList.size(); i++) {
                if ("01".equals(mList.get(i).getType())) {
                    if (mList.get(i).getItems() != null && mList.get(i).getItems().size() > 0) {
                        for (int j = 0; j < mList.get(i).getItems().size(); j++) {
                            if (mList.get(i).getItems().get(j).isCheck) {
                                answerList.add(new ExamAnswerAddBean(mList.get(i).getId(),
                                        mList.get(i).getItems().get(j).getId(),
                                        mList.get(i).getItems().get(j).inputContent));

                            }

                        }
                    }
                } else if ("02".equals(mList.get(i).getType())) {
                    if (mList.get(i).getItems() != null && mList.get(i).getItems().size() > 0) {
                        for (int j = 0; j < mList.get(i).getItems().size(); j++) {
                            if (mList.get(i).getItems().get(j).isCheck) {
                                answerList.add(new ExamAnswerAddBean(mList.get(i).getId(),
                                        mList.get(i).getItems().get(j).getId(),
                                        mList.get(i).getItems().get(j).inputContent));
                            }

                        }
                    }
                } else if ("03".equals(mList.get(i).getType())) {
                    if (!TextUtils.isEmpty(mList.get(i).inputContent)) {
                        for (int j = 0; j < mList.get(i).getItems().size(); j++) {
                            answerList.add(new ExamAnswerAddBean(mList.get(i).getId(),
                                    mList.get(i).getItems().get(j).getId(),
                                    mList.get(i).inputContent));
                        }
                    }

                } else if ("04".equals(mList.get(i).getType())) {
                    if (!TextUtils.isEmpty(mList.get(i).inputContent)) {
                        for (int j = 0; j < mList.get(i).getItems().size(); j++) {
                            answerList.add(new ExamAnswerAddBean(mList.get(i).getId(),
                                    mList.get(i).getItems().get(j).getId(),
                                    mList.get(i).inputContent));
                        }
                    }

                }
            }

            Map<String, Object> queryMap = new HashMap<>();

            queryMap.put("examId", examId);
            queryMap.put("totalTime", totalTime);
            Log.d("TAG", "--totalTime--:" + totalTime);
            queryMap.put("answerList", answerList);
            for (ExamAnswerAddBean bean : answerList) {
                Log.d("TAG", "--getQuestionId--:" + bean.getQuestionId() + "--getItemId-:" + bean.getItemId() + "--getInputContent-:" + bean.getInputContent());
            }

            mPresenter.examadd("", queryMap);
        }


    }

    private void radioButtonL(final KsnrBean.ExamQuestionsBean item, final int position, final int pos, final ImageBrowseAdapter mImageBrowseAdapter) {


        boolean isSel = false;
        for (int a = 0; a < item.getItems().size(); a++) {
            if (item.getItems().get(a).isCheck) {
                isSel = true;
            }
        }
        for (int a = 0; a < item.getItems().size(); a++) {
            item.getItems().get(a).isCheck = false;

        }
        if (item.getItems() != null && item.getItems().size() >= (pos + 1)) {
            Log.e("TAG", "-------radioButtonL---pos--" + pos);
            item.getItems().get(pos).isCheck = true;


            if (!isSel) {
                Handler handler = new Handler();
                final Runnable r = new Runnable() {
                    @Override
                    public void run() {
                        mImageBrowseAdapter.notifyDataSetChanged();
                    }
                };
                handler.post(r);
            } else {
                Handler handler = new Handler();
                final Runnable r = new Runnable() {
                    @Override
                    public void run() {
                        mImageBrowseAdapter.notifyDataSetChanged();
                    }
                };
                handler.post(r);
            }

            int dd = 0;
            int dc = 0;
            for (int i = 0; i < mList.size(); i++) {
                if ("01".equals(mList.get(i).getType())) {
                    for (int a = 0; a < mList.get(i).getItems().size(); a++) {
                        if (mList.get(i).getItems().get(a).isCheck == true) {
                            if (mList.get(i).getItems().get(a).isIsRight() == true) {
                                dd++;
                            } else if (mList.get(i).getItems().get(a).isIsRight() == false) {
                                dc++;
                            }
                        }
                    }
                } else if ("02".equals(mList.get(i).getType())) {
                    String num = "";
                    for (int b = 0; b < mList.get(i).getItems().size(); b++) {
                        if (mList.get(i).getItems().get(b).isIsRight() == true) {
                            num = num + b + "";
                        }
                    }
                    String da = "";
                    for (int a = 0; a < mList.get(i).getItems().size(); a++) {
                        if (mList.get(i).getItems().get(a).isCheck == true) {
                            da = da + a + "";
                        }
                    }
                    if (da.equals(num)) {
                        dd++;
                    } else {
                        dc++;
                    }
                } else if ("03".equals(mList.get(i).getType())) {
                    if (!TextUtils.isEmpty(mList.get(i).inputContent)) {
                        int num = 0;
                        for (int a = 0; a < mList.get(i).getItems().size(); a++) {
                            if (mList.get(i).inputContent.equals(mList.get(i).getItems().get(a).getRightAnser())) {
                                num++;
                            }

                        }
                        if (num > 0) {
                            dd++;
                        } else {
                            dc++;
                        }
                    }


                } else if ("04".equals(mList.get(i).getType())) {
                    if (!TextUtils.isEmpty(mList.get(i).inputContent)) {
                        int num = 0;
                        for (int a = 0; a < mList.get(i).getItems().size(); a++) {
                            if (mList.get(i).inputContent.equals(mList.get(i).getItems().get(a).getRightAnser())) {
                                num++;
                            }

                        }
                        if (num > 0) {
                            dd++;
                        } else {
                            dc++;
                        }
                    }
                }
            }

            tv1.setText("" + dd);
            tv2.setText("" + dc);
        }

    }

    private void checkBoxL(final KsnrBean.ExamQuestionsBean item, final int position, final int pos, final boolean isChecked, final ImageBrowseAdapter mImageBrowseAdapter) {
        if (item.getItems() != null && item.getItems().size() >= (pos + 1)) {
            boolean isSel = false;
            for (int a = 0; a < item.getItems().size(); a++) {
                if (item.getItems().get(a).isCheck) {
                    isSel = true;
                }

            }

            item.getItems().get(pos).isCheck = isChecked;

            if (!isSel) {
                Handler handler = new Handler();
                final Runnable r = new Runnable() {
                    @Override
                    public void run() {
                        mImageBrowseAdapter.notifyDataSetChanged();
                    }
                };
                handler.post(r);
            } else {
                Handler handler = new Handler();
                final Runnable r = new Runnable() {
                    @Override
                    public void run() {

                        mImageBrowseAdapter.notifyDataSetChanged();
                    }
                };
                handler.post(r);
            }


            int dd = 0;
            int dc = 0;
            for (int i = 0; i < mList.size(); i++) {
                if ("01".equals(mList.get(i).getType())) {
                    for (int a = 0; a < mList.get(i).getItems().size(); a++) {
                        if (mList.get(i).getItems().get(a).isCheck == true) {
                            if (mList.get(i).getItems().get(a).isIsRight() == true) {
                                dd++;
                            } else if (mList.get(i).getItems().get(a).isIsRight() == false) {
                                dc++;
                            }
                        }
                    }
                } else if ("02".equals(mList.get(i).getType())) {
                    String num = "";
                    for (int b = 0; b < mList.get(i).getItems().size(); b++) {
                        if (mList.get(i).getItems().get(b).isIsRight() == true) {
                            num = num + b + "";
                        }
                    }
                    String da = "";
                    for (int a = 0; a < mList.get(i).getItems().size(); a++) {
                        if (mList.get(i).getItems().get(a).isCheck == true) {
                            da = da + a + "";
                        }
                    }
                    if (!TextUtils.isEmpty(da)) {
                        if (da.equals(num)) {
                            dd++;
                        } else {
                            dc++;
                        }
                    }

                } else if ("03".equals(mList.get(i).getType())) {
                    if (!TextUtils.isEmpty(mList.get(i).inputContent)) {
                        int num = 0;
                        for (int a = 0; a < mList.get(i).getItems().size(); a++) {
                            if (mList.get(i).inputContent.equals(mList.get(i).getItems().get(a).getRightAnser())) {
                                num++;
                            }

                        }
                        if (num > 0) {
                            dd++;
                        } else {
                            dc++;
                        }
                    }


                } else if ("04".equals(mList.get(i).getType())) {
                    if (!TextUtils.isEmpty(mList.get(i).inputContent)) {
                        int num = 0;
                        for (int a = 0; a < mList.get(i).getItems().size(); a++) {
                            if (mList.get(i).inputContent.equals(mList.get(i).getItems().get(a).getRightAnser())) {
                                num++;
                            }

                        }
                        if (num > 0) {
                            dd++;
                        } else {
                            dc++;
                        }
                    }
                }
            }

            tv1.setText("" + dd);
            tv2.setText("" + dc);


        }
    }


    @Override
    protected void onStop() {
        super.onStop();

        VideoPlayerManager.instance().suspendVideoPlayer();
        L.d("KsnrActivity", "KsnrActivity----" + "onStop");
    }


    @Override
    public void onBackPressed() {
        if (VideoPlayerManager.instance().onBackPressed()) {
            return;
        } else {
            VideoPlayerManager.instance().releaseVideoPlayer();
            L.d("KsnrActivity", "KsnrActivity----" + "退出activity");
        }
        super.onBackPressed();
        L.d("KsnrActivity", "KsnrActivity----" + "onBackPressed");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        VideoPlayerManager.instance().resumeVideoPlayer();
        L.d("KsnrActivity", "KsnrActivity----" + "onRestart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        L.d("KsnrActivity", "KsnrActivity----" + "onPause");
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        VideoPlayerManager.instance().releaseVideoPlayer();
    }

    private void setVideoPlayer(VideoPlayer videoPlayer, String urls) {
        if (videoPlayer == null || urls == null) {
            return;
        }
        L.d("KsnrActivity", "视频链接" + urls);
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
                L.d("KsnrActivity", "setOnPlayerTypeListener" + "onFullScreen");
            }

            /**
             * 切换到小窗口播放监听
             */
            @Override
            public void onTinyWindow() {
                L.d("KsnrActivity", "setOnPlayerTypeListener" + "onTinyWindow");
            }

            /**
             * 切换到正常播放监听
             */
            @Override
            public void onNormal() {
                L.d("KsnrActivity", "setOnPlayerTypeListener" + "onNormal");
            }
        });
        //设置视频控制器
        videoPlayer.setController(controller);
        //是否从上一次的位置继续播放
        videoPlayer.continueFromLastPosition(true);
        //设置播放速度
        videoPlayer.setSpeed(1.0f);

        int maxVolume = videoPlayer.getMaxVolume();
        L.d("KsnrActivity", "视频播放器" + maxVolume);
    }

}
