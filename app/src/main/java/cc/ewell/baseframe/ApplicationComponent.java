package cc.ewell.baseframe;

import android.content.Context;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by fan on 2016/8/9.
 *
 * 用于连接ApplicationModule的连接器
 */

@Singleton
@Component(modules = { ApplicationModule.class})
public interface ApplicationComponent {

    // ApplicationComponent会被其他Component依赖, Context实例会被用到, 所以这里提供出去
    Context getContext();

    EventBus getEventBus();

    // 注入, 参数为注入的地方
    void inject(EApplication mApplication);

}
