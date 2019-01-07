package me.zhouzhuo810.magpietv.utils;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import me.zhouzhuo810.magpietv.act.CopyUrlActivity;
import me.zhouzhuo810.magpietv.cons.Cons;


public class NoticeUtil {
    private NoticeUtil() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }
    
    public static final int NOTICE_NORMAL = 0x11;
    public static final int NOTICE_PROGRESS = 0x12;
    public static int NOTICE_URL = 0;
    public static final String DEFAULT_CHANNEL_ID = "default";
    
    /**
     * 显示普通的通知
     *
     * @param title      标题
     * @param content    内容
     * @param autoCancel 是否自动取消
     * @param onGoing    是否常驻
     * @param smallIcon  小图标
     * @param voice      是否开启声音
     * @param vibrate    是否振动
     * @param channelId  分类id
     * @param intent     点击的intent
     */
    public static void showNormalNotice(String title,
                                        String content, boolean autoCancel,
                                        boolean onGoing, int smallIcon,
                                        boolean voice, boolean vibrate,
                                        String channelId,
                                        Intent intent) {
        NotificationCompat.Builder b = new NotificationCompat.Builder(BaseUtil.getApp(), channelId == null ? DEFAULT_CHANNEL_ID : channelId);
        b.setContentTitle(title)
            .setContentText(content)
            .setWhen(System.currentTimeMillis())
            .setAutoCancel(autoCancel)
            .setOngoing(onGoing)
            .setSmallIcon(smallIcon)
            .setDefaults(voice ? Notification.DEFAULT_SOUND : Notification.DEFAULT_ALL)
            .setVibrate(vibrate ? new long[]{200, 200} : null);
        if (intent != null) {
            b.setContentIntent(PendingIntent.getActivity(BaseUtil.getApp(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT));
        }
        NotificationManagerCompat nm = NotificationManagerCompat.from(BaseUtil.getApp());
        nm.notify(NOTICE_NORMAL, b.build());
    }
    
    /**
     * 显示普通的自定义声音的通知
     *
     * @param title      标题
     * @param content    内容
     * @param autoCancel 是否自动取消
     * @param onGoing    是否常驻
     * @param smallIcon  小图标
     * @param voice      是否开启声音
     * @param voiceId    声音资源id , R.raw.xxx
     * @param vibrate    是否振动
     * @param channelId  分类id
     * @param intent     点击的intent
     */
    public static void showNormalNoticeWithCustomVoice(String title,
                                                       String content, boolean autoCancel,
                                                       boolean onGoing, int smallIcon,
                                                       boolean voice, int voiceId, boolean vibrate, String channelId,
                                                       Intent intent) {
        NotificationCompat.Builder b = new NotificationCompat.Builder(BaseUtil.getApp(), channelId == null ? DEFAULT_CHANNEL_ID : channelId);
        b.setContentTitle(title)
            .setContentText(content)
            .setWhen(System.currentTimeMillis())
            .setAutoCancel(autoCancel)
            .setOngoing(onGoing)
            .setSmallIcon(smallIcon)
            .setVibrate(vibrate ? new long[]{200, 200} : null);
        if (voice) {
            b.setSound(Uri.parse("android.resource://" + BaseUtil.getPackageInfo(BaseUtil.getApp()).packageName + "/" + voiceId));
        }
        if (intent != null) {
            PendingIntent pi = PendingIntent.getActivity(BaseUtil.getApp(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            b.setContentIntent(pi);
        }
        Notification notification = b.build();
        NotificationManagerCompat nm = NotificationManagerCompat.from(BaseUtil.getApp());
        nm.notify(NOTICE_NORMAL, notification);
    }
    
    /**
     * 隐藏普通的通知
     */
    public static void hideNormalNotice(Context context) {
        NotificationManagerCompat nm = NotificationManagerCompat.from(context);
        nm.cancel(NOTICE_NORMAL);
    }
    
    /**
     * @param title      标题
     * @param progress   进度
     * @param smallIcon  小图标
     * @param autoCancel 自动取消
     * @param onGoing    是否常驻
     * @param channelId  分类id
     * @param intent     点击的intent
     */
    public static void showProgressNotice(String title, int progress, int smallIcon, boolean autoCancel,
                                          boolean onGoing, String channelId, Intent intent) {
        
        NotificationCompat.Builder b = new NotificationCompat.Builder(BaseUtil.getApp(), channelId == null ? DEFAULT_CHANNEL_ID : channelId);
        b.setContentTitle(title)
            .setWhen(System.currentTimeMillis())
            .setAutoCancel(autoCancel)
            .setProgress(100, progress, false)
            .setOngoing(onGoing)
            .setSmallIcon(smallIcon)
            .setVibrate(null)
            .setSound(null);
        if (intent != null) {
            PendingIntent pi = PendingIntent.getActivity(BaseUtil.getApp(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            b.setContentIntent(pi);
        }
        NotificationManagerCompat nm = NotificationManagerCompat.from(BaseUtil.getApp());
        nm.notify(NOTICE_PROGRESS, b.build());
    }
    
    /**
     * 关闭进度通知栏
     */
    public static void hideProgressNotice() {
        NotificationManagerCompat nm = NotificationManagerCompat.from(BaseUtil.getApp());
        nm.cancel(NOTICE_PROGRESS);
    }
    
    
    /**
     * 显示复制URL调试通知
     *
     * @param context    上下文
     * @param title      标题
     * @param content    内容
     * @param autoCancel 是否自动取消
     * @param onGoing    是否常驻
     * @param smallIcon  小图标
     * @param vibrate    是否震动
     * @param channelId  通知渠道id
     */
    public static void showNormalNoticeWithCopyAction(Context context, String title,
                                                      String content, boolean autoCancel,
                                                      boolean onGoing, int smallIcon, boolean vibrate, String channelId) {
        NotificationCompat.Builder b = new NotificationCompat.Builder(context, channelId == null ? DEFAULT_CHANNEL_ID : channelId);
        b.setContentTitle(title)
            .setContentText(content)
            .setWhen(System.currentTimeMillis())
            .setAutoCancel(autoCancel)
            .setOngoing(onGoing)
            .setSmallIcon(smallIcon)
            .setVibrate(vibrate ? new long[]{200, 200} : null);
        Intent intent = new Intent(context, CopyUrlActivity.class);
        intent.putExtra(Cons.NOTICE_URL, content);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pi = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        b.setContentIntent(pi);
        Notification notification = b.build();
        NotificationManagerCompat nm = NotificationManagerCompat.from(context);
        final int id = (NOTICE_URL++) % 4 + 1;
        nm.notify(id, notification);
    }
    
    
}
