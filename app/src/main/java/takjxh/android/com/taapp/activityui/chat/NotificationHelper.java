package takjxh.android.com.taapp.activityui.chat;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

import java.text.SimpleDateFormat;
import java.util.Date;

import takjxh.android.com.taapp.R;
import takjxh.android.com.taapp.activityui.activity.XxzzActivity;

/**
 * Created by Administrator on 2020/3/11.
 */

public class NotificationHelper {

    private static final String CHANNEL_ID="channel_id";   //通道渠道id
    public static final String  CHANEL_NAME="chanel_name"; //通道渠道名称
    private static SimpleDateFormat sdf=new SimpleDateFormat("HH:mm:ss");


    @TargetApi(Build.VERSION_CODES.O)
    public static  void  showMessageNotification(Context context,NotificationManager notificationManager,
                                                 String title, String message,String mchannel){
        NotificationChannel channel = null;
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            //创建 通知通道  channelid和channelname是必须的（自己命名就好）
            channel = new NotificationChannel(CHANNEL_ID, CHANEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            channel.enableLights(true);//是否在桌面icon右上角展示小红点
            channel.setLightColor(Color.GREEN);//小红点颜色
            channel.setShowBadge(false); //是否在久按桌面图标时显示此渠道的通知
        }
        Notification notification;
        //获取Notification实例   获取Notification实例有很多方法处理    在此我只展示通用的方法（虽然这种方式是属于api16以上，但是已经可以了，毕竟16以下的Android机很少了，如果非要全面兼容可以用）
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            //向上兼容 用Notification.Builder构造notification对象

            notification = new Notification.Builder(context,CHANNEL_ID)
                    .setContentTitle(title)
                    .setContentText(message)
                    .setWhen(System.currentTimeMillis())
                    .setSmallIcon(R.drawable.jpush_notification_icon)
                    .setColor(Color.parseColor("#FEDA26"))
                    .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher))
                   // .setTicker("巴士门1")
                    .build();
        }else {
            //向下兼容 用NotificationCompat.Builder构造notification对象
            notification = new NotificationCompat.Builder(context)
                    .setContentTitle(title)
                    .setContentText(message)
                    .setWhen(System.currentTimeMillis())
                    .setSmallIcon(R.drawable.jpush_notification_icon)
                    .setColor(Color.parseColor("#FEDA26"))
                    .setLargeIcon(BitmapFactory.decodeResource(context.getResources(),R.mipmap.ic_launcher))
                   // .setTicker("巴士门1")
                    .build();

        }

        Intent intent=new Intent(context,XxzzActivity.class);
        intent.putExtra("message",":"+sdf.format(new Date()));
        //用当前时间充当通知的id，这里是为了区分不同的通知，如果是同一个id，前者就会被后者覆盖
        int requestId=(int) new Date().getTime();
        //第一个参数连接上下文的context
        // 第二个参数是对PendingIntent的描述，请求值不同Intent就不同
        // 第三个参数是一个Intent对象，包含跳转目标
        // 第四个参数有4种状态
        PendingIntent pendingIntent= PendingIntent.getActivity(context,requestId,intent,PendingIntent.FLAG_UPDATE_CURRENT);
//        builder.setContentIntent(pendingIntent);

        notification.contentIntent=pendingIntent;
        //发送通知
        int  notifiId=1;
        //创建一个通知管理器
       if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            notificationManager.createNotificationChannel(channel);
        }
        notificationManager.notify(notifiId,notification);
    }


}
