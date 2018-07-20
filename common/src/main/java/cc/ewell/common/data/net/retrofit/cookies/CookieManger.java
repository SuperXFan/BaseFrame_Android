package cc.ewell.common.data.net.retrofit.cookies;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.List;

import cc.ewell.common.utils.LogUtil;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

/**
 * Created by SuperFan on 2016/8/31.
 */
public class CookieManger implements CookieJar {
    private static Context mContext;

    private static PersistentCookieStore cookieStore;

    public CookieManger(Context context) {
        mContext = context;
        if (cookieStore == null ) {
            cookieStore = new PersistentCookieStore(mContext);
        }

    }

    @Override
    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
        if (cookies != null && cookies.size() > 0) {
            LogUtil.d("下存：：：："+cookies.toString());
            for (Cookie item : cookies) {
                cookieStore.add(url, item);
            }
        }
    }

    @Override
    public List<Cookie> loadForRequest(HttpUrl url) {
        List<Cookie> cookies =cookieStore.get(url);
        LogUtil.d("上传：：：："+cookies.toString());
        // 存cookie
        String cookie = cookies.toString();
        SharedPreferences sp = mContext.getSharedPreferences("cookie",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("cookie", cookie.substring(1, cookie.length() - 1));
        editor.apply();
        return cookies;
    }
}
