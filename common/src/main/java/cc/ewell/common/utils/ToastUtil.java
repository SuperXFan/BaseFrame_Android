package cc.ewell.common.utils;

/**
 * Toast工具类
 */

import android.content.Context;
import android.widget.Toast;

public class ToastUtil {
	
	private static Toast toast;
	/**
	 * 
	 * @param context 上下文
	 * @param showInfo 内容
	 * @param showType Toast 显示时间长短类型  如：Toast.LENGTH_SHORT
	 */
	public static void showToast(Context context, String showInfo, int showType){
		if (context == null){
			return;
		}
		if(toast != null){
			toast.cancel();
		}
		toast = Toast.makeText(context, showInfo, showType);
		toast.show();
	}
	/**
	 * 
	 * @param
	 */
	public static void cancelToast(){
		if(toast != null){
			toast.cancel();
		}
	}
}
