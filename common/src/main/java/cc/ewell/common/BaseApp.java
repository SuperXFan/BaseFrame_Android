package cc.ewell.common;

import android.app.Application;

import cc.ewell.common.utils.BaseUtil;
import cc.ewell.common.utils.LogUtil;

public abstract class BaseApp extends Application {

    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        BaseUtil.init(this);//初始话BaseUtil 会有app实例和相关生命周期的使用
        LogUtil.init();//初始化Log
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
     * Application 初始化
     */
    public abstract void initModuleApp(Application application);

    /**
     * 所有 Application 初始化后的自定义操作
     */
    public abstract void initModuleData(Application application);
}
