package cc.ewell.login.business.login;


import cc.ewell.common.base.BasePresenter;
import cc.ewell.common.base.BaseView;

/**
 * Created by Mr.Dong on 2016/12/27.
 *  * 登录页面的契约类
 */
public class LoginContract {
    interface View extends BaseView {
        // 这里是P层通知View层更新界面的一些回调接口
        void startToHomeActivity();
        void setLoginButton(boolean isEnable);

    }

    interface Presenter extends BasePresenter<View> {
        // 这里是View层调用P层的接口方法

        void toLogin(String username, String password);

    }

}
