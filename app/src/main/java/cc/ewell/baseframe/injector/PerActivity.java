package cc.ewell.baseframe.injector;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by Mr.Dong on 2016/12/27.
 */
@Scope
@Retention(RUNTIME)
public @interface PerActivity {
}
