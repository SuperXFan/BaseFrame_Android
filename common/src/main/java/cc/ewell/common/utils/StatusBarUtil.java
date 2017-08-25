package cc.ewell.common.utils;

import android.content.Context;

/**
 * Created by SuperFan on 2017/1/12.
 */
public class StatusBarUtil {

    /**
     * 获取状态栏高度——方法1
     * */
    public static int getStatusBarHeight(Context context){
        int statusBarHeight1 = -1;
        //获取status_bar_height资源的ID
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            //根据资源ID获取响应的尺寸值
            statusBarHeight1 = context.getResources().getDimensionPixelSize(resourceId);
        }
        return  statusBarHeight1;
    }

}
