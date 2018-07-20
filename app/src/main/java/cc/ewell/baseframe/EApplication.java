package cc.ewell.baseframe;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.support.multidex.MultiDex;


/**
 * Created by fan on 2016/8/9.
 */
public class EApplication extends Application{

    private ApplicationComponent mApplicationComponent;
    
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initInjector();
    }


    /**
     * 连接器初始化
     */
    private void initInjector() {
        mApplicationComponent = DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this)).build();
        mApplicationComponent.inject(this);
    }


    /**
     * 获取Component
     *
     * @return
     */
    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }


    /**
     * 判断当前应用是否处于Debug模式
     *
     * @return
     */
    public boolean isApplicationDebuggable() {
        try {
            ApplicationInfo info = getApplicationInfo();
            return (info.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
        } catch (Exception e) {

        }
        return false;
    }


}
