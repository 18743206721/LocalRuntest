package com.xingguang.localrun.view;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;

/**
 * Created by ${liuyu} on 2017/9/19.
 * 自定义scrollview
 */

public class TiceScrollview extends ScrollView {


    public TiceScrollview(Context context) {
        super(context);
        init();
    }

    public TiceScrollview(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TiceScrollview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private onScrollChangedListener mListener;


    private GestureDetector mGestureDetector;

    private void init() {
        mGestureDetector = new GestureDetector(getContext(),
                new YScrollDetector());
        setFadingEdgeLength(0);
    }

    private class YScrollDetector extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2,
                                float distanceX, float distanceY) {

            if (Math.abs(distanceY) >= Math.abs(distanceX)) {
                return true;
            }
            return false;
        }
    }

    private int downX;
    private int downY;
    private int mTouchSlop;
    View v1;
    View v2;

//    private ScrollViewListener scrollViewListener = null;
//
//    public void setScrollViewListener(ScrollViewListener scrollViewListener) {
//        this.scrollViewListener = scrollViewListener;
//    }

    private boolean allowChildViewScroll = true;

    public void setAllowChildViewScroll(boolean allowChildViewScroll) {
        this.allowChildViewScroll = allowChildViewScroll;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (!allowChildViewScroll) {
            return true;
        }
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                downX = (int) ev.getRawX();
                downY = (int) ev.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                int moveY = (int) ev.getRawY();
                if (Math.abs(moveY - downY) > mTouchSlop) {
                    return true;
                }
        }
        return super.onInterceptTouchEvent(ev)
                && mGestureDetector.onTouchEvent(ev);
    }

    @Override
    protected int computeScrollDeltaToGetChildRectOnScreen(Rect rect) {
        return 0;
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);

        if (mListener != null) {
            mListener.onScrollChanged(t);
        }

    }


    public void addOnScrollChangedListener(onScrollChangedListener listener) {
        mListener = listener;
    }

    public interface onScrollChangedListener {
        void onScrollChanged(int t);
    }

//    @Override
//    public boolean canPullDown() {
//        if (getScrollY() == 0)
//            return true;
//        else
//            return false;
//    }
//
//    @Override
//    public boolean canPullUp() {
//        if (getScrollY() >= (getChildAt(0).getHeight() - getMeasuredHeight()))
//            return true;
//        else
//            return false;
//    }


}




