package cc.ewell.baseframe;

import android.content.Context;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


/**
 * Created by fan on 2016/8/9.
 */

@Module
public class ApplicationModule {

    private Context mContext;

    /**
     * ApplicationModule的构造
     *
     * @param context
     */
    public ApplicationModule(Context context) {
        this.mContext = context;
    }

    /**
     * 对外提供context实例
     *
     * @return
     */
    @Provides
    @Singleton
    Context provideApplicationContext() {
        return mContext.getApplicationContext();
    }

    /**
     * 对外提供EventBus实例
     *
     * @return
     */
    @Provides
    @Singleton
    EventBus provideEventBus() {
        return EventBus.getDefault();
    }

}
