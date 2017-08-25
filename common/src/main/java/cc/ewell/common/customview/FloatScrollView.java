package cc.ewell.common.customview;


import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.ScrollView;

public class FloatScrollView extends ScrollView {
    private OnScrollListener onScrollListener;
    private OnBottomListener onBottomListener;
    private boolean isNotifyOnBottom = true;

    private float preX;
    private float preY;
    private float touchSlop;
    private boolean isViewPagerDragged;

    public FloatScrollView(Context context) {
        this(context, null);
    }

    public FloatScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        touchSlop= ViewConfiguration.get(context).getScaledTouchSlop();
    }

    public FloatScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }



    /**
     * 在onInterceptTouchEvent()方法里，
     * 如果水平移动距离大于竖直移动距离，ScrollView不拦截这个事件
     * @param e
     * @return
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        float currentX=e.getX();
        float currentY=e.getY();
        switch (e.getAction()){
            case MotionEvent.ACTION_DOWN:

                isNotifyOnBottom = false;

                preX=currentX;
                preY=currentY;
                isViewPagerDragged=false;
                break;
            case MotionEvent.ACTION_MOVE:
                if(isViewPagerDragged){
                    return false;
                }
                float dx=Math.abs(preX-currentX);
                float dy=Math.abs(preY-currentY);

                if(dx>dy && dx>touchSlop){
                    isViewPagerDragged=true;
                    return false;
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                isViewPagerDragged=false;
                break;
        }
        return super.onInterceptTouchEvent(e);
    }


    @Override
    protected void onScrollChanged(int x, int y, int oldx, int oldy) {
        super.onScrollChanged(x, y, oldx, oldy);
        if(onScrollListener != null){
            onScrollListener.onScroll(y);
        }
        if (y + getHeight() >= computeVerticalScrollRange() && y > oldy && !isNotifyOnBottom) {
            isNotifyOnBottom = true;
            if (onBottomListener != null) {
                onBottomListener.onBottom();
            }
        }
    }

    /**
     * 设置滚动接口
     * @param onScrollListener
     */
    public void setOnScrollListener(OnScrollListener onScrollListener) {
        this.onScrollListener = onScrollListener;
    }
    /**
     *
     * 滚动的回调接口
     *
     * @author xiaanming
     *
     */
    public interface OnScrollListener{
        /**
         * 回调方法， 返回MyScrollView滑动的Y方向距离
         * @param scrollY
         * 				、
         */
        public void onScroll(int scrollY);
    }


    /**
     * 设置滚动到底部接口
     * @param onBottomListener
     */
    public void setOnBottomListener(OnBottomListener onBottomListener) {
        this.onBottomListener = onBottomListener;
    }

    /**
     *
     * 滚动到底部的回调接口
     *
     * @author xiaanming
     *
     */
    public interface OnBottomListener{
        /**
         * 回调方法， 滾動地底部了
         * @param
         * 				、
         */
        public void onBottom();
    }
}
