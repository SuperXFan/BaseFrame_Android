package cc.ewell.baseframe.utils;

import android.content.Context;
import android.content.SharedPreferences;

import cc.ewell.common.constants.CommonConstant;

/**
 * Created by SuperFan on 2016/9/13.
 */
public class ServerUtil {

    public static final String SERVER_HOST_PREFERENCE_KEY = "host";//服务的host存取key
    public static final String PICTURE_SERVER_BASE_URL_KEY = "pic_base_url";//图片服务的根地址的存取key

    /**
     * 将服务host存在SharedPreference中
     */
    public static void setServerHost(Context context, String serverHost) {
        SharedPreferences sp = context.getSharedPreferences(CommonConstant.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        sp.edit().putString(SERVER_HOST_PREFERENCE_KEY, serverHost).apply();
    }

    /**
     * 从SharedPreference中获取服务host
     *
     * @param context
     * @return 如果没有, 则返回""
     */
    public static String getServerHost(Context context) {
        SharedPreferences sp = context.getSharedPreferences(CommonConstant.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return sp.getString(SERVER_HOST_PREFERENCE_KEY, "");
    }

    /**
     * 将图片服务baseUrl存在SharedPreference中
     */
    public static void setPicServerBaseUrl(Context context, String picBaseServerUrl) {
        SharedPreferences sp = context.getSharedPreferences(CommonConstant.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        sp.edit().putString(PICTURE_SERVER_BASE_URL_KEY, picBaseServerUrl).apply();
    }

    /**
     * 从SharedPreference中获取图片服务baseUrl
     *
     * @param context
     * @return 如果没有, 则返回""
     */
    public static String getPicServerBaseUrl(Context context) {
        SharedPreferences sp = context.getSharedPreferences(CommonConstant.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return sp.getString(PICTURE_SERVER_BASE_URL_KEY, "");
    }
}
