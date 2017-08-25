package cc.ewell.baseframe.ui.splash;

import android.content.Intent;
import android.os.Handler;
import android.widget.Toast;
import android.widget.ViewAnimator;


import javax.inject.Inject;

import butterknife.ButterKnife;
import cc.ewell.common.utils.ToastUtil;
import cc.ewell.baseframe.R;
import cc.ewell.baseframe.ui.base.BaseActivity;
import cc.ewell.baseframe.ui.login.LoginActivity;
import cc.ewell.baseframe.utils.AccountUtil;

/**
 * Created by SuperFan on 2017/4/13.
 */
public class SplashActivity extends BaseActivity implements SplashContract.View{

    //Presenter
    @Inject
    SplashPresenter splashPresenter;

    //datas
    private final long DELAY_TIME = 2000;//ms

    @Override
    protected int initContentView() {
        return R.layout.activity_splash;
    }

    @Override
    protected void getIntentData(Intent intent) {

    }

    @Override
    protected void initInjector() {
        DaggerSplashComponent.builder().applicationComponent(getApplicationComponent()).build().inject(this);
    }

    @Override
    protected void initData() {


    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        splashPresenter.attachView(this);
    }

    @Override
    protected ViewAnimator initViewAnimator() {
        return null;
    }

    @Override
    protected boolean isApplyStatusBarTranslucency() {
        return false;
    }

    @Override
    public void showLoadingView() {
        showProgressBar();
    }

    @Override
    public void hideLoadingView() {
        showNone();
    }

    @Override
    public void showToast(String text) {
        ToastUtil.showToast(this,text, Toast.LENGTH_SHORT);
    }

    @Override
    protected void onDestroy() {
        if(splashPresenter != null){
            splashPresenter.detachView();
        }
        ButterKnife.unbind(this);
        super.onDestroy();

    }


    @Override
    protected void start() {
        showLoadingView();

        if(!AccountUtil.isUserLogin(this)) {//去登录
            resultFromAutoLogin(false);
        }else{
            resultFromAutoLogin(true);
        }
    }

    /**
     * 自动登录后的结果
     * @param isCanAutoLogin
     */
    @Override
    public void resultFromAutoLogin(final boolean isCanAutoLogin) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                jumpTo(isCanAutoLogin);
            }
        },DELAY_TIME);

    }

    /**
     * 跳转
     */
    private void jumpTo(boolean isCanAutoLogin){
        if(!isCanAutoLogin){//去登录
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }else{//自动登录
        }
    }

}
