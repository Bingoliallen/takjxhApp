package takjxh.android.com.taapp;

import android.app.Notification;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.arialyy.frame.core.AbsFrame;
import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;
/*import com.mob.MobSDK;
import com.mob.pushsdk.MobPush;
import com.mob.pushsdk.MobPushCustomMessage;
import com.mob.pushsdk.MobPushNotifyMessage;
import com.mob.pushsdk.MobPushReceiver;*/
import com.wanjian.cockroach.Cockroach;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.jpush.android.api.BasicPushNotificationBuilder;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.model.Conversation;
import cn.jpush.im.android.api.model.Message;
import cn.jpush.im.android.api.model.UserInfo;
import okhttp3.OkHttpClient;
import takjxh.android.com.commlibrary.CommonApplication;
import takjxh.android.com.taapp.activityui.bean.LoginUIBean;
import takjxh.android.com.taapp.activityui.bean.SysParamBean;
import takjxh.android.com.taapp.activityui.chat.location.service.LocationService;
import takjxh.android.com.taapp.activityui.chat.utils.StorageUtil;
import takjxh.android.com.taapp.utils.GlideImageLoader;

/**
 * QbApplication
 *
 * @Author: libaibing
 * @Date: 2019-01-07 12:51
 * @Description:
 **/

public class QbApplication extends CommonApplication {

    public List<LoginUIBean.UserExtsBean> userExts=new ArrayList<>();
    public List<String> medias=new ArrayList<>();


    public List<SysParamBean.ParamsBean.MessagesourcetypeBean> messagesourcetype=new ArrayList<>();//消息来源类型
    public List<SysParamBean.ParamsBean.LogchangetypeBean> logchangetype=new ArrayList<>();//积分变更类型
    public List<SysParamBean.ParamsBean.StudytypeBean> studytype=new ArrayList<>();//继续教育学习类型
    public List<SysParamBean.ParamsBean.AudittypeBean> audittype=new ArrayList<>();//信息审核类型
    public List<SysParamBean.ParamsBean.FaqtypeBean> faqtype=new ArrayList<>();//常见问题类型
    public List<SysParamBean.ParamsBean.ApplyorderstatusBean> applyorderstatus=new ArrayList<>();//报名状态
    public List<SysParamBean.ParamsBean.UsertypeBean> usertype=new ArrayList<>();//用户类型
    public List<SysParamBean.ParamsBean.SurveystatusBean> surveystatus=new ArrayList<>();//问卷状态
    public List<SysParamBean.ParamsBean.TraintypeBean> traintype=new ArrayList<>();//继续教育培训类型
    public List<SysParamBean.ParamsBean.NewstypeBean> newstype=new ArrayList<>();//首页新闻类型
    public List<SysParamBean.ParamsBean.ApplystatusBean> applystatus=new ArrayList<>();//政策申报类型
    public List<SysParamBean.ParamsBean.CommtopictypeBean> commtopictype=new ArrayList<>();//继续教育交流类型
    public List<SysParamBean.ParamsBean.UsertradeBean> usertrade=new ArrayList<>();//行业
    public List<SysParamBean.ParamsBean.UserstatusBean> userstatus=new ArrayList<>();//用户状态
    public List<SysParamBean.ParamsBean.PolicycreteunitBean> policycreteunit=new ArrayList<>();//发布单位/部门
    public List<SysParamBean.ParamsBean.AuditstatusBean> auditstatus=new ArrayList<>();//审核状态

    public List<SysParamBean.ParamsBean.UserincomeBean> userincome=new ArrayList<>();//企业营收
    public List<SysParamBean.ParamsBean.UserscaleBean> userscale=new ArrayList<>();//企业规模
    public List<SysParamBean.ParamsBean.UserstationBean> userstation=new ArrayList<>();//职位

    public List<SysParamBean.ParamsBean.AuditstatusBean> applyordertype=new ArrayList<>();


    public List<SysParamBean.ParamsBean.ExamtypeBean> examtype=new ArrayList<>();
    public List<SysParamBean.ParamsBean.ExamtypeBean> questiontype=new ArrayList<>();
    public List<SysParamBean.ParamsBean.ExamtypeBean> titletype=new ArrayList<>();

 //  public UserInfo myInfo;
    public  List<Message> forwardMsg = new ArrayList<>();
    public  List<UserInfo> alreadyRead = new ArrayList<>();
    public  List<UserInfo> unRead = new ArrayList<>();
    public  Conversation delConversation;
    public  Map<Long, Boolean> isAtMe = new HashMap<>();
    public  Map<Long, Boolean> isAtall = new HashMap<>();
    public  List<Message> ids = new ArrayList<>();

    public LocationService locationService;

    public static QbApplication mBaseApplication;

    public static Context getAppContext() {
        return mApplication;
    }


    public static OkHttpClient okHttpClient;

    @Override
    public void onCreate() {
        super.onCreate();
        mBaseApplication = this;

        StorageUtil.init(this, null);
        locationService = new LocationService(getApplicationContext());

        AbsFrame.init(this);
        EventBus.builder().addIndex(new MyEventBusIndex()).installDefaultEventBus();
        //  一般使用默认初始化配置足够使用
        Stetho.initializeWithDefaults(this);
        initOkHttp();


        // 在使用 SDK 各组间之前初始化 context 信息，传入 ApplicationContext
        // 默认本地个性化地图初始化方法
        SDKInitializer.initialize(this);
        //自4.3.0起，百度地图SDK所有接口均支持百度坐标和国测局坐标，用此方法设置您使用的坐标类型.
        //包括BD09LL和GCJ02两种坐标，默认是BD09LL坐标。
        SDKInitializer.setCoordType(CoordType.BD09LL);



        Cockroach.install(new Cockroach.ExceptionHandler() {

            // handlerException内部建议手动try{  你的异常处理逻辑  }catch(Throwable e){ } ，以防handlerException内部再次抛出异常，导致循环调用handlerException

            @Override
            public void handlerException(final Thread thread, final Throwable throwable) {
                //开发时使用Cockroach可能不容易发现bug，所以建议开发阶段在handlerException中用Toast谈个提示框，
                //由于handlerException可能运行在非ui线程中，Toast又需要在主线程，所以new了一个new Handler(Looper.getMainLooper())，
                //所以千万不要在下面的run方法中执行耗时操作，因为run已经运行在了ui线程中。
                //new Handler(Looper.getMainLooper())只是为了能弹出个toast，并无其他用途
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            //建议使用下面方式在控制台打印异常，这样就可以在Error级别看到红色log
                            Log.e("AndroidRuntime", "--->CockroachException:" + thread + "<---", throwable);
                            //       Toast.makeText(BaseApplication.this, "Exception Happend\n" + thread + "\n" + throwable.toString(), Toast.LENGTH_SHORT).show();
//                        throw new RuntimeException("..."+(i++));
                        } catch (Throwable e) {

                        }
                    }
                });
            }
        });

        /*MobSDK.init(this);

        receiver=new MobPushReceiver() {
            @Override
            public void onCustomMessageReceive(Context context, MobPushCustomMessage message) {
                //接收自定义消息
                //接收自定义消息(透传)
                System.out.println("libbMobPush onCustomMessageReceive:" + message.toString());
            }
            @Override
            public void onNotifyMessageReceive(Context context, MobPushNotifyMessage message) {
                //接收通知消息
                System.out.println("libbMobPush onNotifyMessageReceive:" + message.toString());
            }
            @Override
            public void onNotifyMessageOpenedReceive(Context context, MobPushNotifyMessage message) {
                //接收通知消息被点击事件
                System.out.println("libbMobPush onNotifyMessageOpenedReceive:" + message.toString());
            }
            @Override
            public void onTagsCallback(Context context, String[] tags, int operation, int errorCode) {
                //接收tags的增改删查操作
                System.out.println("libbMobPush onTagsCallback:" + operation + "  " + errorCode);
            }
            @Override
            public void onAliasCallback(Context context, String alias, int operation, int errorCode) {
                //接收alias的增改删查操作
                System.out.println("libbMobPush onAliasCallback:" + alias + "  " + operation + "  " + errorCode);
            }
        };
        MobPush.addPushReceiver(receiver);*/


        JMessageClient.setDebugMode(true);
        JMessageClient.init(this,true );

        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);

        BasicPushNotificationBuilder builder = new BasicPushNotificationBuilder(this);
        builder.statusBarDrawable = R.drawable.jpush_notification_icon;
        builder.notificationFlags = Notification.FLAG_AUTO_CANCEL
                | Notification.FLAG_SHOW_LIGHTS;  //设置为自动消失和呼吸灯闪烁
        builder.notificationDefaults = Notification.DEFAULT_SOUND
                | Notification.DEFAULT_VIBRATE
                | Notification.DEFAULT_LIGHTS;  // 设置为铃声、震动、呼吸灯闪烁都要
        JPushInterface.setPushNotificationBuilder(1, builder);

    }

    private void initOkHttp() {
        okHttpClient = new OkHttpClient()
                .newBuilder()
                .addNetworkInterceptor(new StethoInterceptor()) // 这里添加一个拦截器即可
                .build();
    }

   /* private void initImagePicker() {
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new GlideImageLoader());   //设置图片加载器
        imagePicker.setShowCamera(true);                      //显示拍照按钮
        imagePicker.setCrop(true);                           //允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(true);                   //是否按矩形区域保存
        imagePicker.setSelectLimit(maxImgCount);              //选中数量限制
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
        imagePicker.setFocusWidth(800);                       //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(800);                      //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(1000);                         //保存文件的宽度。单位像素
        imagePicker.setOutPutY(1000);                         //保存文件的高度。单位像素
    }*/


    /*private MobPushReceiver receiver;

    public void removePushReceiver() {
        if (receiver != null) {
            MobPush.removePushReceiver(receiver);
        }

    }*/




    @Override
    public void onTerminate() {



        super.onTerminate();
    }


}
