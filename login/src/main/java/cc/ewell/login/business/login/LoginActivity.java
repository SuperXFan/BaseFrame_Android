package cc.ewell.login.business.login;

import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewAnimator;


import com.alibaba.android.arouter.facade.annotation.Route;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cc.ewell.common.customview.ClearEditText;
import cc.ewell.common.utils.ToastUtil;
import cc.ewell.common.base.BaseActivity;
import cc.ewell.login.R;
import cc.ewell.login.R2;

/**
 * 登录页面
 */
@Route(path = "/login/login")
public class LoginActivity extends BaseActivity implements LoginContract.View {

    @BindView(R2.id.state_layout)
    ViewAnimator viewAnimator;
    @BindView(R2.id.user_name_edit)
    ClearEditText userNameEdit;
    @BindView(R2.id.user_password_edit)
    ClearEditText userPasswordEdit;
    @BindView(R2.id.login_button)
    Button loginButton;
    @BindView(R2.id.forget_password_view)
    TextView forgetPasswordView;

    private Unbinder unbinder;

    @Inject
    LoginPresenter loginPresenter;

    private String lala = "";

    @Override
    protected int initContentView() {
        return R.layout.login_activity_login;
    }

    @Override
    protected void getIntentData(Intent intent) {
        if(intent != null){
            lala = intent.getStringExtra("lala");
        }
    }

    @Override
    protected void initInjector() {
        DaggerLoginComponent.builder().applicationComponent(getApplicationComponent())
                .loginModule(new LoginModule()).build().inject(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        unbinder = ButterKnife.bind(this);
        loginPresenter.attachView(this);

        userNameEdit.setText(lala);

        userNameEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if (s != null) {
                    String userName = s.toString();
                    String password = userPasswordEdit.getText().toString();
                    if (!TextUtils.isEmpty(userName) && !TextUtils.isEmpty(password)) {
                        setLoginButton(true);
                    } else {
                        setLoginButton(false);
                    }
                }
            }
        });
        
        userPasswordEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if (s != null) {
                    String password = s.toString();
                    String userName = userNameEdit.getText().toString();
                    if (!TextUtils.isEmpty(userName) && !TextUtils.isEmpty(password)) {
                        setLoginButton(true);
                    } else {
                        setLoginButton(false);
                    }
                }
            }
        });
        
    }

    @Override
    protected ViewAnimator initViewAnimator() {
        return viewAnimator;
    }

    @Override
    protected boolean isApplyStatusBarTranslucency() {
        return true;
    }

    /**
     * 跳转到Home页面
     */
    @Override
    public void startToHomeActivity() {

    }

    /**
     * 设置LoginButton enable/disable
     */
    @Override
    public void setLoginButton(boolean isEnable) {
        loginButton.setEnabled(isEnable);
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

    @OnClick({R2.id.login_button,R2.id.forget_password_view})
    public void onClick(View view){
        int i = view.getId();
        if (i == R.id.login_button) {
            loginPresenter.toLogin(userNameEdit.getText() != null ? userNameEdit.getText().toString() : ""
                    , userPasswordEdit.getText() != null ? userPasswordEdit.getText().toString() : "");

        } else if (i == R.id.forget_password_view) {
            startToForgetPasswordActivity();

        }
    }

    /**
     * 跳转到忘记密码页面
     */
    private void startToForgetPasswordActivity(){


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(unbinder != null){
            unbinder.unbind();
        }
        loginPresenter.detachView();
    }
}
