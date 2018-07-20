package cc.ewell.common.utils;

import android.content.Context;
import android.content.SharedPreferences;

import cc.ewell.common.constants.CommonConstant;

/**
 * Created by Mr.Dong on 2016/12/28.
 */
public class CommonAccountUtil {

    private static final String USER_ID = "user_code";
//    private static final String TOKEN = "token";//token

    public static String getUserId(Context context){
        SharedPreferences sp = context.getSharedPreferences(CommonConstant.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return sp.getString(USER_ID,"");
    }

//    public static String getToken(Context context){
//        SharedPreferences sp = context.getSharedPreferences(CommonConstant.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
//        return sp.getString(TOKEN,"");
//    }


}
