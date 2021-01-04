package com.lunlun.fenhow1219;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

public class BasicNotification extends AppCompatActivity{
    private final String TAG = BasicNotification.class.getSimpleName();
    private NotificationManager notifcationManager;
    private Context context;
    private int nid;
    private NotificationManager notifManager;

    public void createNotification(String aMessage) {
        final int NOTIFY_ID = 0; // ID of notification
        String id = getString(R.string.default_notification_channel_id); // default_channel_id
        String title = getString(R.string.default_notification_channel_title); // Default Channel
        Intent intent;
        PendingIntent pendingIntent;
        NotificationCompat.Builder builder;
        if (notifManager == null) {
            notifManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = notifManager.getNotificationChannel(id);
            if (mChannel == null) {
                mChannel = new NotificationChannel(id, title, importance);
                mChannel.enableVibration(true);
                mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
                notifManager.createNotificationChannel(mChannel);
            }
            builder = new NotificationCompat.Builder(this, id);
            intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
            builder.setContentTitle(aMessage)  // required
                    .setSmallIcon(android.R.drawable.ic_popup_reminder) // required
                    .setContentText(this.getString(R.string.app_name))  // required
                    .setDefaults(Notification.DEFAULT_ALL)
                    .setAutoCancel(true)
                    .setContentIntent(pendingIntent)
                    .setTicker(aMessage)
                    .setVibrate(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
        } else {
            builder = new NotificationCompat.Builder(this);
            intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
            builder.setContentTitle(aMessage)                           // required
                    .setSmallIcon(android.R.drawable.ic_popup_reminder) // required
                    .setContentText(this.getString(R.string.app_name))  // required
                    .setDefaults(Notification.DEFAULT_ALL)
                    .setAutoCancel(true)
                    .setContentIntent(pendingIntent)
                    .setTicker(aMessage)
                    .setVibrate(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400})
                    .setPriority(Notification.PRIORITY_HIGH);
        }
        Notification notification = builder.build();
        notifManager.notify(NOTIFY_ID, notification);
    }

//    public BasicNotification(){
////        context=this;
//        notifcationManager=(NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
//    }
//
////    @Override
//    public void createNotification(Context context, int nid) {
//        this.context = context;
//        this.nid = nid;
////                訊息id
////        2. 建立 - 通知服務建情器
//        Notification.Builder builder = new Notification.Builder(context);
////       3, 建立按下訊息嵌板後所要轉跳的Intent
//        Intent intent = new Intent(context, SystemManagementActivity.class);
////       設定Intent標誌参數
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
//                Intent.FLAG_ACTIVITY_SINGLE_TOP |
//                Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        int requestcode = 1;
//        PendingIntent pendingIntent = PendingIntent.getActivity(context, requestcode, intent, PendingIntent.FLAG_UPDATE_CURRENT);
////       4.設定震動須率
//        long[] vibratepattern = {100, 400, 500, 400};
////      Respurces 輔 bitmap
////        Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.cgh_tree);
////      5. 定義 Notifcation.Builder建構器
//        builder.setSmallIcon(R.drawable.cgh_tree)// 通知服務 icon
////                .setLargeIcon(bmp)
//                .setContentTitle("權限請求")//標題
//                .setContentText(""+nid)
//                .setContentInfo("2013/2 上路") // 信息
//                .setTicker("停看聽!") // Ticker 標題
//                .setLights(0xFFFFFFFF, 1090, 1009)//LED
//                .setVibrate(vibratepattern)//震動
////                .setContentIntent(pendingIntent)// 設定 Intent 服務
//                .setAutoCancel(true);// true:按下訊息嵌板後會自動消失
////        在鎖屏上掲露完整訊息:Notifcation.VISIBILITY_PUBLIC
////        基本的資訊與通知的圖示:Notifcation.VISIBILITY_PRIVATE(預設)
////        在鎖屏上揭露訊息:Notifcation.VISIBILITY_SECRET
//
//        builder.setVisibility(Notification.VISIBILITY_PRIVATE);
////      抬頭顯示儀
////        if (checkBox1.isChecked()) {
////            builder.setPriority(Notification.PRIORITY_HIGH);
////        }
//        Notification notifcation = builder.build(); // 建立 Notifcation
//        notifcationManager.notify(nid, notifcation); //發怖 Notifcation
//    }
}

