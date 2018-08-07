package cc.ewell.baseframe.business.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewAnimator;

import com.alibaba.android.arouter.launcher.ARouter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cc.ewell.baseframe.R;
import cc.ewell.baseframe.utils.AccountUtil;
import cc.ewell.common.base.BaseActivity;
import cc.ewell.common.utils.ToastUtil;

/**
 * Created by SuperFan on 2017/4/13.
 */
public class SplashActivity extends BaseActivity implements SplashContract.View {

    @BindView(R.id.splash_logo_view)
    ImageView splashLogoView;
    @BindView(R.id.company_english_name_view)
    TextView companyEnglishNameView;
    @BindView(R.id.company_chinese_name_view)
    TextView companyChineseNameView;
    private Unbinder unbinder;

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
        unbinder = ButterKnife.bind(this);
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
        ToastUtil.showToast(this, text, Toast.LENGTH_SHORT);
    }

    @Override
    protected void onDestroy() {
        if (splashPresenter != null) {
            splashPresenter.detachView();
        }
        if (unbinder != null) {
            unbinder.unbind();
        }
        super.onDestroy();

    }


    @Override
    protected void start() {
        showLoadingView();

//        if (!AccountUtil.isUserLogin(this)) {//去登录
//            resultFromAutoLogin(false);
//        } else {
//            resultFromAutoLogin(true);
//        }
    }

    /**
     * 自动登录后的结果
     *
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
        }, DELAY_TIME);

    }

    /**
     * 跳转
     */
    private void jumpTo(boolean isCanAutoLogin) {
        if (!isCanAutoLogin) {//去登录
//            Intent intent = new Intent(this, LoginActivity.class);
//            startActivity(intent);
            ARouter.getInstance().build("/login/login").withString("lala","youyou").navigation();
//            finish();
        } else {//自动登录
        }
    }

    @OnClick({R.id.splash_logo_view, R.id.company_english_name_view, R.id.company_chinese_name_view})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.splash_logo_view:
                showToast("gaga");
                break;
            case R.id.company_english_name_view:
            case R.id.company_chinese_name_view:
                jumpTo(false);
                break;
        }
    }
}
