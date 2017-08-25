package cc.ewell.common.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

import cc.ewell.common.utils.DensityUtil;

/**
 * 自定义ScrollView
 * 
 *
 */
public class MyScrollView extends ScrollView {

	private onScrollViewBottomListener onBottomListener;
	private boolean isNotifyOnBottom = false;
	
	private Context context;

	/***
	 * 构造方法
	 * 
	 * @param context
	 * @param attrs
	 */
	public MyScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;

	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {

		boolean flag = super.onInterceptTouchEvent(ev);
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			isNotifyOnBottom = false;
			break;
		case MotionEvent.ACTION_UP:
			break;
		case MotionEvent.ACTION_MOVE:
			break;
		}
		return flag;
	}

	/***
	 * 滑动到底部
	 * 
	 * @author lei
	 * 
	 */
	public interface onScrollViewBottomListener {

		/** 必须达到一定程度才执行 **/
		void onBottom();
	}
	

	public void setOnBottomListener(onScrollViewBottomListener onBottomListener) {
		this.onBottomListener = onBottomListener;
	}

	@Override
	protected void onScrollChanged(int x, int y, int oldx, int oldy) {

		super.onScrollChanged(x, y, oldx, oldy);
		float newY = DensityUtil.px2dp(context, y);
		float oLdY = DensityUtil.px2dp(context, oldy);

		if (y + getHeight() >= computeVerticalScrollRange() && newY > oLdY && !isNotifyOnBottom) {
			isNotifyOnBottom = true;
			if (onBottomListener != null) {
				onBottomListener.onBottom();
			}
		}

	}
}
