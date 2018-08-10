package cc.ewell.common.base;

/**
 * Created by fan on 2016/8/9.
 * MVP中View的基类
 */
public interface BaseView {

    void showLoadingView();// 展示加载的ProgressBar

    void hideLoadingView();// 隐藏正在加载中的ProgressBar

    void showToast(String text);// 弹出Toast
}
