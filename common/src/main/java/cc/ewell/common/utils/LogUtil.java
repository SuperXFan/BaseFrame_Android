package cc.ewell.common.utils;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

/**
 * Created by fan on 2016/9/23.
 * <p>
 * 对Logger的进一步封装
 */
public class LogUtil {

    // 是否打印LOG, 发布时候将DEBUG改成false
    private static boolean DEBUG = true;

    public static boolean isDebug(){
        return  DEBUG;
    }

    public static void init(){
        Logger.addLogAdapter(new AndroidLogAdapter());
    }

    public static void i(String message, Object... args) {
        if (DEBUG) {
            Logger.i(message, args);
        }
    }

    public static void d(String message, Object... args) {
        if (DEBUG) {
            Logger.d(message, args);
        }
    }

    public static void e(String message, Object... args) {
        if (DEBUG) {
            Logger.e(message, args);
        }
    }

    public static void v(String message, Object... args) {
        if (DEBUG) {
            Logger.v(message, args);
        }
    }

    public static void w(String message, Object... args) {
        if (DEBUG) {
            Logger.w(message, args);
        }
    }

    public static void xml(String xml) {
        if (DEBUG) {
            Logger.xml(xml);
        }
    }

    public static void json(String json) {
        if (DEBUG) {
            Logger.json(json);
        }
    }

}
