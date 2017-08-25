package cc.ewell.baseframe.ui.login;

import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewAnimator;


import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cc.ewell.common.customview.ClearEditText;
import cc.ewell.common.utils.ToastUtil;
import cc.ewell.baseframe.R;
import cc.ewell.baseframe.ui.base.BaseActivity;

/**
 * 登录页面
 */
public class LoginActivity extends BaseActivity implements LoginContract.View {

    @Bind(R.id.state_layout)
    ViewAnimator viewAnimator;
    @Bind(R.id.user_name_edit)
    ClearEditText userNameEdit;
    @Bind(R.id.user_password_edit)
    ClearEditText userPasswordEdit;
    @Bind(R.id.login_button)
    Button loginButton;
    @Bind(R.id.forget_password_view)
    TextView forgetPasswordView;

    @Inject
    LoginPresenter loginPresenter;

    @Override
    protected int initContentView() {
        return R.layout.activity_login;
    }

    @Override
    protected void getIntentData(Intent intent) {

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
        ButterKnife.bind(this);
        loginPresenter.attachView(this);

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

    @OnClick({R.id.login_button,R.id.forget_password_view})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.login_button:
                loginPresenter.toLogin(userNameEdit.getText() != null ? userNameEdit.getText().toString() : ""
                        , userPasswordEdit.getText() != null ? userPasswordEdit.getText().toString() : "");
                break;
            case R.id.forget_password_view: // 忘记密码
                startToForgetPasswordActivity();
                break;
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
        ButterKnife.unbind(this);
        loginPresenter.detachView();
    }
}
