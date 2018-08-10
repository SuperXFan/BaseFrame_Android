package cc.ewell.login.business.login;

import cc.ewell.common.ApplicationComponent;
import cc.ewell.login.injector.PerActivity;
import dagger.Component;

/**
 * Created by Mr.Dong on 2016/12/27.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = LoginModule.class)
public interface LoginComponent {
    void inject(LoginActivity loginActivity);
}
