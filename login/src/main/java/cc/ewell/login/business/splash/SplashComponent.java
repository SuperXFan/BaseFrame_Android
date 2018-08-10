package cc.ewell.login.business.splash;

import cc.ewell.common.ApplicationComponent;
import cc.ewell.login.injector.PerActivity;
import dagger.Component;

/**
 * Created by SuperFan on 2017/4/13.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class)
public interface SplashComponent {
    void inject(SplashActivity splashActivity);
}
