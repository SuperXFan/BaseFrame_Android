package cc.ewell.common.base;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ViewAnimator;

import cc.ewell.common.ApplicationComponent;
import cc.ewell.common.BaseApp;

/**
 * Created by fan on 2016/8/9.
 * <p>
 * Activity的基类
 */
public abstract class BaseActivity extends AppCompatActivity {

    ViewAnimator viewAnimator;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        initTheme();

        super.onCreate(savedInstanceState);

        setContentView(initContentView());

        getIntentData(getIntent());

        setTranslucentStatus(isApplyStatusBarTranslucency());
//        setStatusBarColor(isApplyStatusBarColor());
        setStatusBarColor();

        initInjector();

        initData();
        initView();
        viewAnimator = initViewAnimator();

        start();
    }

    /**
     * 开始业务逻辑处理, 比如:从服务器获取数据
     */
    protected void start(){

    }

    /**
     * 主题初始化
     */
    private void initTheme() {
        // 目前是空实现, 如有需要, 直接在这里写, 便可统一Theme
    }

    ///////////////////////////////////////////////////////////////////////////
    // Abstract Method In Activity
    // 下面是子类必须实现的方法
    ///////////////////////////////////////////////////////////////////////////

    /**
     * 界面初始化, 这里只需要返回R.id.xx即可
     *
     * @return
     */
    protected abstract int initContentView();

    /**
     * 从Intent中获取数据
     */
    protected abstract void getIntentData(Intent intent);

    /**
     * Dagger2的连接器初始化
     */
    protected abstract void initInjector();

    /**
     * 数据初始化
     */
    protected abstract void initData();

    /**
     * 控件初始化
     */
    protected abstract void initView();


    /**
     * 由子类提供包含有 Empty/Error/ProgressBar 的 ViewAnimator
     * 如果子类想要个性化Empty/Error/ProgressBar, 在xml中定制ViewAnimator, 然后将对象返回即可
     * tip:定制的xml布局, 必须遵循第一个子布局为none, 第二个子布局为loading, 第三个子布局为empty, 第四个为error
     */
    protected abstract ViewAnimator initViewAnimator();

    /**
     * 状态栏是否设置成半透明
     */
    protected abstract boolean isApplyStatusBarTranslucency();

    /**
     * 设置状态栏为半透明
     *
     * @param on true 半透明
     *           false 不设置
     */
    protected void setTranslucentStatus(boolean on) {
        if (on && Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            Window win = getWindow();
//            WindowManager.LayoutParams winParams = win.getAttributes();
//            final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
//            if (on) {
//                winParams.flags |= bits;
//            } else {
//                winParams.flags &= ~bits;
//            }
//            win.setAttributes(winParams);
        }
    }

//    /**
//     * 是否需要重新设置状态栏的颜色
//     * @return true false
//     */
//    protected abstract boolean isApplyStatusBarColor();
//
//    /**
//     * 设置状态栏颜色
//     */
//    protected void setStatusBarColor(boolean on) {
//        if (on) {
//            // 修改状态栏颜色
//            StatusBarUtil.setColor(this, ResourceUtil.getThemeColor(this), 0);
//        }
//    }

    protected void setStatusBarColor() {
        // 修改状态栏颜色
//        StatusBarUtil.setColor(this, ResourceUtil.getThemeColor(this), 0);
    }

    ///////////////////////////////////////////////////////////////////////////
    // Common Operation
    // 下面是为子类提供一些公用的操作, 子类里面就不需要再写了
    ///////////////////////////////////////////////////////////////////////////

    /**
     * 隐藏progressBar/ErrorView/EmptyView
     */
    protected void showNone() {
        if (viewAnimator != null)
            if (viewAnimator.getChildCount() > 0)
                if (!viewAnimator.getChildAt(0).isShown())
                    viewAnimator.setDisplayedChild(0);
    }

    /**
     * 为子类提供展示progressBar的通用方法
     */
    protected void showProgressBar() {
        if (viewAnimator != null)
            if (viewAnimator.getChildCount() > 1)
                if (!viewAnimator.getChildAt(1).isShown())
                    viewAnimator.setDisplayedChild(1);
    }

    /**
     * 为子类提供展示EmptyView的通用方法
     */
    protected void showEmptyView() {
        if (viewAnimator != null)
            if (viewAnimator.getChildCount() > 2)
                if (!viewAnimator.getChildAt(2).isShown())
                    viewAnimator.setDisplayedChild(2);
    }

    /**
     * 为子类提供展示ErrorView的通用方法
     */
    protected void showErrorView() {
        if (viewAnimator != null)
            if (viewAnimator.getChildCount() > 3)
                if (!viewAnimator.getChildAt(3).isShown())
                    viewAnimator.setDisplayedChild(3);
    }

    /**
     * 向Activity中添加Fragment
     *
     * @param fragmentManager
     * @param fragment
     * @param frameId
     */
    public void replaceFragment(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, int frameId) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(frameId, fragment);
        transaction.commit();
    }


    public void addFragment(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, int frameId){
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(frameId, fragment);
        transaction.commit();
    }

    public void showFragment(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment){
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.show(fragment);
        transaction.commit();
    }

    public void hideFragment(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment){
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.hide(fragment);
        transaction.commit();
    }


    /**
     * 重新加载
     * 就是把自己当前结束掉, 然后再跳转到自己
     * 这里的Intent, 是从其他页面跳转时候传递过来的.
     * 比如从AActivity跳转到MainActivity, 但是MainActiviy加载失败
     * 这里先把MainActivity finish掉, 然后再次将从AActivity传来的Intent传递至MainActivity
     */
    public void reload() {
        Intent intent = getIntent();
        overridePendingTransition(0, 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();
        overridePendingTransition(0, 0);
        startActivity(intent);
    }

    /**
     * 对外提供全局Component
     *
     * @return
     */
    public ApplicationComponent getApplicationComponent() {
        return ((BaseApp) getApplication()).getApplicationComponent();
    }

    ///////////////////////////////////////////////////////////////////////////
    // 下面三个方法实现点击键盘外部, 自动隐藏键盘
    ///////////////////////////////////////////////////////////////////////////
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideInput(v, ev)) {
                hideSoftInput(v.getWindowToken());
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    private boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0], top = l[1], bottom = top + v.getHeight(), right = left
                    + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    private void hideSoftInput(IBinder token) {
        if (token != null) {
            InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            im.hideSoftInputFromWindow(token,
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
