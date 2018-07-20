package cc.ewell.baseframe.utils;

import android.app.NotificationManager;
import android.content.Context;

/**
 * Created by fan on 2017/4/24.
 */

public class NotificationUtil {

    /**
     * 清除所有的Notification
     */
    public static void cancelAllNotification(Context context){
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancelAll();
    }
}
