package cc.ewell.baseframe.ui.login;

import cc.ewell.baseframe.ApplicationComponent;
import cc.ewell.baseframe.injector.PerActivity;
import dagger.Component;

/**
 * Created by Mr.Dong on 2016/12/27.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = LoginModule.class)
public interface LoginComponent {
    void inject(LoginActivity loginActivity);
}
