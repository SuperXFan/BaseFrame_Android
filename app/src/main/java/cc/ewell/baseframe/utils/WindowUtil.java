package cc.ewell.baseframe.utils;

import android.app.Activity;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by fan on 2017/5/5.
 */

public class WindowUtil {
    /**
     * 设置window的透明度
     *
     * @param alpha
     */
    public static void setWindowAlpha(Activity activity, float alpha) {
        Window window = activity.getWindow();
        WindowManager.LayoutParams windowLayoutParams = window.getAttributes(); // 获取对话框当前的参数值
        windowLayoutParams.alpha = alpha;// 设置透明度
        window.setAttributes(windowLayoutParams);
    }
}
