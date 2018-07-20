package cc.ewell.baseframe.utils;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;

/**
 * Created by fan on 2016/8/17.\
 * 
 * 动画工具类
 */
public class AnimatorUtil {

    /**
     * 展开view
     * @param v view
     * @param height 展开后view的高度
     */
    public static void animateOpen(View v, int height) {
        v.setVisibility(View.VISIBLE);
        ValueAnimator animator = createDropAnimator(v, 0, height);
        animator.setDuration(100).start();
    }

    /**
     * 以中心为圆点, 旋转45度并且展开
     * @param view
     */
    public static void rotateAnimationOpen(View view) {
        RotateAnimation animation = new RotateAnimation(0, 45,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        animation.setFillAfter(true);
        animation.setDuration(100);
        view.startAnimation(animation);
    }

    /**
     * 以中心为圆点旋转45度并且合起
     * @param view
     */
    public static void rotateAnimationClose(View view) {
        RotateAnimation animation = new RotateAnimation(45, 0,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        animation.setFillAfter(true);
        animation.setDuration(100);
        view.startAnimation(animation);
    }

    /**
     * 合起view
     * @param view
     */
    public static void animateClose(final View view) {
        int origHeight = view.getHeight();
        ValueAnimator animator = createDropAnimator(view, origHeight, 0);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                view.setVisibility(View.GONE);
            }

        });
        animator.setDuration(100).start();
    }

    public static ValueAnimator createDropAnimator(final View v, int start, int end) {
        ValueAnimator animator = ValueAnimator.ofInt(start, end);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener () {
            @Override
            public void onAnimationUpdate(ValueAnimator arg0) {
                int value = (int) arg0.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = v.getLayoutParams();
                layoutParams.height = value;
                v.setLayoutParams(layoutParams);
                v.setAlpha(value * 0.01f);
            }
        });
        return animator;
    }
}
