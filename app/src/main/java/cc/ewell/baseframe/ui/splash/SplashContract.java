package cc.ewell.baseframe.ui.splash;

import cc.ewell.baseframe.ui.base.BasePresenter;
import cc.ewell.baseframe.ui.base.BaseView;

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
