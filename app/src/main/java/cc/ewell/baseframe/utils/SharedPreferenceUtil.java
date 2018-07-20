package cc.ewell.baseframe.utils;

import android.content.Context;
import android.content.SharedPreferences;

import cc.ewell.baseframe.constants.Constant;

/**
 * Created by fan on 2017/4/1.
 */

public class SharedPreferenceUtil {

    // 需要更新OpinionList
    public static final String NEED_UPDATE_OPINION_LIST = "need_update_opinion_list";

    /**
     * 设置是否需要更新OpinionList
     */
    public static void setNeedUpdateOpinionList(Context context, boolean need) {
        SharedPreferences sp = context.getSharedPreferences(Constant.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        sp.edit().putBoolean(NEED_UPDATE_OPINION_LIST, need).apply();
    }

    /**
     * 获取是否获取OpinionList
     */
    public static boolean getNeedUpdateOpinionList(Context context) {
        SharedPreferences sp = context.getSharedPreferences(Constant.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return sp.getBoolean(NEED_UPDATE_OPINION_LIST, true);
    }
}
