package cc.ewell.baseframe.ui.base;

import android.support.annotation.NonNull;

/**
 * Created by fan on 2016/8/9.
 * MVP中Presenter的基类
 */
public interface BasePresenter <T extends BaseView>{

    void attachView(@NonNull T view);// 与View进行关联

    void detachView();// 解除关联
}
