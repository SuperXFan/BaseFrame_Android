package cc.ewell.baseframe.business.splash;

import android.content.Context;
import android.support.annotation.NonNull;


import javax.inject.Inject;

import cc.ewell.baseframe.utils.AccountUtil;

/**
 * Created by SuperFan on 2017/4/13.
 */
public class SplashPresenter implements SplashContract.Presenter {

    //UI
    private Context context;
    private SplashContract.View mView;

    @Inject
    public SplashPresenter(Context context){
        this.context = context;
    }

    @Override
    public void attachView(@NonNull SplashContract.View view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
    }


    /**
     * 自动登录
     */
    @Override
    public void autoLogin() {
        if(context == null || mView == null){
            return;
        }
        if(!AccountUtil.isUserLogin(context)) {//去登录
            mView.resultFromAutoLogin(false);
        }else{
            mView.resultFromAutoLogin(true);
        }
    }
}
