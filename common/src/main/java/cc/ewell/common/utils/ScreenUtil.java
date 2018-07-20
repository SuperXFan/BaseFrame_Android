package cc.ewell.common.utils;

import android.content.Context;
import android.view.WindowManager;

/**
 * Created by zhaokaiqiang on 15/4/9.
 */
public class ScreenUtil {

	public static int getScreenWidth(Context context) {
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		return wm.getDefaultDisplay().getWidth();
	}

	public static int getScreenHeight(Context context) {
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		return wm.getDefaultDisplay().getHeight();
	}

}
