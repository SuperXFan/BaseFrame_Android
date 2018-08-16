package cc.ewell.login.business.splash;

import android.Manifest;
import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewAnimator;

import com.alibaba.android.arouter.launcher.ARouter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cc.ewell.common.base.BaseActivity;
import cc.ewell.common.utils.SpanUtils;
import cc.ewell.common.utils.ToastUtil;
import cc.ewell.common.utils.permission.DialogHelper;
import cc.ewell.common.utils.permission.PermissionConstants;
import cc.ewell.common.utils.permission.PermissionUtils;
import cc.ewell.login.R;
import cc.ewell.login.R2;

/**
 * Created by SuperFan on 2017/4/13.
 */
public class SplashActivity extends BaseActivity implements SplashContract.View {

    @BindView(R2.id.splash_logo_view)
    ImageView splashLogoView;
    @BindView(R2.id.gologin)
    TextView gologin;
    @BindView(R2.id.permission)
    TextView permission;
    private Unbinder unbinder;

    //Presenter
    @Inject
    SplashPresenter splashPresenter;

    //datas
    private final long DELAY_TIME = 2000;//ms

    @Override
    protected int initContentView() {
        return R.layout.login_activity_splash;
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

    @OnClick({R2.id.splash_logo_view, R2.id.gologin, R2.id.permission})
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.splash_logo_view) {
            showToast("gaga");

        } else if (i == R.id.gologin) {
            jumpTo(false);
        } else if(i == R.id.permission){
            requestPermission();
        }
    }

    private void requestPermission(){
        PermissionUtils.permission(PermissionConstants.CALENDAR)
                .rationale(new PermissionUtils.OnRationaleListener() {//如果用户自己关闭了权限
                    @Override
                    public void rationale(final ShouldRequest shouldRequest) {
                        DialogHelper.showRationaleDialog(shouldRequest);//提示去开启
                    }
                })
                .callback(new PermissionUtils.FullCallback() {//获取权限后的回调
                    @Override
                    public void onGranted(List<String> permissionsGranted) {//成功获取
                        updateAboutPermission();
                    }

                    @Override
                    public void onDenied(List<String> permissionsDeniedForever,
                                         List<String> permissionsDenied) {//拒绝权限
                        if (!permissionsDeniedForever.isEmpty()) {
                            DialogHelper.showOpenAppSettingDialog();//提示去开启
                        }
                    }
                })
//                .theme(new PermissionUtils.ThemeCallback() {
//                    @Override
//                    public void onActivityCreate(Activity activity) {
//                        ScreenUtils.setFullScreen(activity);
//                    }
//                })
                .request();
    }


    @Override
    protected void onResume() {
        super.onResume();
        updateAboutPermission();
    }
    private void updateAboutPermission() {
        permission.setText(new SpanUtils()
                .append("点击动态获取权限：").setBold()
                .appendLine("READ_CALENDAR: " + PermissionUtils.isGranted(Manifest.permission.READ_CALENDAR))
                .create());
    }
}
