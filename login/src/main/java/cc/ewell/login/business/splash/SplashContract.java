package cc.ewell.login.business.splash;

import cc.ewell.common.base.BasePresenter;
import cc.ewell.common.base.BaseView;

/**
 * Created by SuperFan on 2017/4/13.
 */
public interface SplashContract {
    interface View extends BaseView{
        void resultFromAutoLogin(boolean isCanAutoLogin);
    }
    interface Presenter extends BasePresenter<View>{
        void autoLogin();
    }
}
