package cc.ewell.login.business.login;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import cc.ewell.common.constants.CommonServerConstant;
import cc.ewell.common.data.net.retrofit.service.RxBaseApi;
import cc.ewell.common.data.net.retrofit.subscriber.HttpResultSubscriber;
import cc.ewell.login.constants.ServerConstant;
import cc.ewell.login.model.LoginInfo;
import rx.Subscription;

/**
 * LoginActivity对应的Presenter
 */
public class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.View mLoginView;
    private Context mContext;
    private Subscription subscription; // 登录
    private String userModel;

    private final int MIN_USERNAME_LENGHT = 1;//用户名最小长度
    private final int MIN_PASSWARD_LENGHT = 4;//密码最小长度

    @Inject
    public LoginPresenter(Context context) {
        this.mContext = context;
    }

    @Override
    public void attachView(@NonNull LoginContract.View view) {
        this.mLoginView = view;
    }

    @Override
    public void detachView() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
        mLoginView = null;
    }


    /**
     * step1:登录
     */
    @Override
    public void toLogin(String username, String password) {

//        //判断参数
//        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(username.trim())) {
//            if (mLoginView != null) {
//                mLoginView.showToast(mContext.getResources().getString(R.string.account_can_not_be_empty));
//            }
//            return;
//        }
//        if (!TextUtils.isEmpty(username) && username.length() < MIN_USERNAME_LENGHT) {
//            if (mLoginView != null) {
//                mLoginView.showToast(mContext.getResources().getString(R.string.account_format_incorrect));
//            }
//            return;
//        }
//        if (TextUtils.isEmpty(password) || TextUtils.isEmpty(password.trim())) {
//            if (mLoginView != null) {
//                mLoginView.showToast(mContext.getResources().getString(R.string.password_can_not_be_empty));
//            }
//            return;
//        }
//        if (!TextUtils.isEmpty(password) && password.length() < MIN_PASSWARD_LENGHT) {
//            if (mLoginView != null) {
//                mLoginView.showToast(mContext.getResources().getString(R.string.password_format_incorrect));
//            }
//            return;
//        }
//        String cid = AccountUtil.getGetuiCid(mContext);
//        if (TextUtils.isEmpty(cid)) {
//            AccountUtil.setIsSetCid(mContext, false);
//            cid = "";
//        } else {
//            AccountUtil.setIsSetCid(mContext, true);
//        }


        // 登录按钮disable
        if (mLoginView != null) {
            mLoginView.setLoginButton(false);
        }

        Map<String, String> param = new HashMap<>();
//        param.put(ServerConstant.USER_NAME_KEY, username);
//        param.put(ServerConstant.USER_PWD_KEY, password);
//        param.put(ServerConstant.CID_KEY, "1235423542354");
//        param.put(ServerConstant.TIMEOUT_KEY, ServerConstant.TIME_OUT_VALUE + "");
        param.put("method","hidoctor.hospital.list");
        param.put("format","json");
        param.put("v","1.0");

        subscription = RxBaseApi.getDefault(mContext, "http://gm.api.hidoctor.cc", null)
                .executePost(mContext, new LoginResultSubscriber(), LoginInfo.class, "/router/rest", param);

    }

    /**
     * 访问登录服务后的回调
     */
    private class LoginResultSubscriber extends HttpResultSubscriber<LoginInfo> {

        @Override
        public void onStart() {
            if (mLoginView != null) {
                mLoginView.showLoadingView();
            }
        }

        @Override
        public void onFailure(String errMessage) {
            if (mLoginView != null) {
                mLoginView.setLoginButton(true);
                mLoginView.hideLoadingView();

                if (!TextUtils.isEmpty(errMessage)) {
                    mLoginView.showToast(errMessage);
                } else {
//                    mLoginView.showToast(mContext.getResources().getString(R.string.server_error));
                }
            }
        }

        @Override
        public void onCookieInvalid(String errMessage) {
            if (mLoginView != null) {
                mLoginView.setLoginButton(true);
                mLoginView.showToast(errMessage);
                mLoginView.hideLoadingView();
            }
        }

        @Override
        public void onSuccess(LoginInfo resultData) {


        }
    }
}
