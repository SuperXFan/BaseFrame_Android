package cc.ewell.baseframe.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import cc.ewell.common.constants.CommonConstant;

/**
 * 轮播图相关的方法
 * 
 * @author fan
 * 
 */
public class BannerUtil {
	
	public static final String DIR_BANNER = "banner";

	private static final String BANNER_VERSION_CODE = "banner_version_code";
	
	public static final String BANNER_PIC_NAME = "banner_pic_name";
	
	public static final String LAST_UPDATE_TIME = "last_update_time";

	/**
	 * 获取当前Banner的版本
	 * @param context
	 * @return
	 */
	public static int getBannerVersion(Context context){
		SharedPreferences sp = context.getSharedPreferences(CommonConstant.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
		return sp.getInt(BANNER_VERSION_CODE, 0);
	}
	
	/**
	 * 存储当前version版本
	 * @param context
	 * @param version
	 */
	public static void setBannerVersion(Context context, int version){
		SharedPreferences sp = context.getSharedPreferences(CommonConstant.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
		Editor edit = sp.edit();
		edit.putInt(BANNER_VERSION_CODE, version);
		edit.apply();
	}
	
	/**
	 * 将文件名都存在Sharedpreference
	 * @param context
	 * @param obj
	 * @param key
	 */
	public static void saveBannerPicName(Context context, Serializable obj, String key) {
		SharedPreferences preferences = context.getSharedPreferences("base64", Context.MODE_PRIVATE);
		// 创建字节输出流
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			// 创建对象输出流，并封装字节流
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			// 将对象写入字节流
			oos.writeObject(obj);
			// 将字节流编码成base64的字符窜
			String oAuth_Base64 = new String(Base64.encode(baos.toByteArray(),Base64.DEFAULT));
			Editor editor = preferences.edit();
			editor.putString(key, oAuth_Base64);
			editor.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取Banner的文件名
	 * @param context
	 * @param key
	 * @return
	 */
	public static <T> T readBannerPicName(Context context, String key) {
		T t = null;
		SharedPreferences preferences = context.getSharedPreferences("base64", Context.MODE_PRIVATE);
		String productBase64 = preferences.getString(key, "");

		// 读取字节
		byte[] base64 = Base64.decode(productBase64.getBytes(),Base64.DEFAULT);

		// 封装到字节流
		ByteArrayInputStream bais = new ByteArrayInputStream(base64);
		try {
			// 再次封装
			ObjectInputStream bis = new ObjectInputStream(bais);
			try {
				// 读取对象
				t = (T) bis.readObject();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return t;
	}
	
	/**
	 * 清除信息
	 * @param context
	 */
	public static void clearNameList(Context context){
		ArrayList<String> list = new ArrayList<String>();
		saveBannerPicName(context, list, BANNER_PIC_NAME);
	}
	
	/**
	 * 获取上一次更新的时间
	 * @param context
	 * @return
	 */
	public static long getLastUpdateTime(Context context){
		SharedPreferences sp = context.getSharedPreferences(CommonConstant.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
		return sp.getLong(LAST_UPDATE_TIME, 0);
		
	}
	
	/**
	 * 获取上一次更新的时间
	 * @param context
	 * @return
	 */
	public static void setLastUpdateTime(Context context, long lastTime){
		SharedPreferences sp = context.getSharedPreferences(CommonConstant.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
		Editor edit = sp.edit();
		edit.putLong(LAST_UPDATE_TIME, lastTime);
		edit.apply();
	}
}
