package cc.ewell.baseframe.ui.splash;

import cc.ewell.baseframe.ApplicationComponent;
import cc.ewell.baseframe.injector.PerActivity;
import dagger.Component;

/**
 * Created by SuperFan on 2017/4/13.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class)
public interface SplashComponent {
    void inject(SplashActivity splashActivity);
}
