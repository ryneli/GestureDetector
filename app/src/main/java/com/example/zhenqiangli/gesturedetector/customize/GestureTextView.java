package com.example.zhenqiangli.gesturedetector.customize;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

/**
 * Read following link for using gesture detector
 * https://developer.android.com/training/gestures/detector.html
 */

public class GestureTextView extends AppCompatTextView {
  private static final String TAG = "GestureTextView";
  private static final int ACTION_START = 0;
  private static final int ACTION_LONG_PRESS = 1;

  private GestureDetectorCompat gestureDetector;
  private Gesture gesture;
  private int latestAction;

  public GestureTextView(Context context) {
    this(context, null);
  }

  public GestureTextView(Context context,
      @Nullable AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public GestureTextView(Context context,
      @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    final GestureListener gestureListener = new GestureListener();
    gestureDetector = new GestureDetectorCompat(context, gestureListener);

    // https://stackoverflow.com/questions/11065403/gesturedetector-doesnt-work-with-my-view
    setOnTouchListener((v, e) -> onTouchEventWrapper(e));
  }

  private boolean onTouchEventWrapper(MotionEvent e) {
    if (latestAction == ACTION_LONG_PRESS) {
      gesture.onLongPressSelect(e);
    }
    return gestureDetector.onTouchEvent(e);
  }

  public interface Gesture {
    void onClick(MotionEvent e);
    void onDoubleClick(MotionEvent e);
    void onLongPress(MotionEvent e);
    void onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY);
    void onLongPressSelect(MotionEvent e);
  }

  public void registerGesture(Gesture gesture) {
    this.gesture = gesture;
  }

  public void unregisterGesture() {
    this.gesture = null;
  }

  private static void L(String tag, String fmt, Object... args) {
    Log.d(tag, String.format(fmt, args));
  }

  private class GestureListener extends GestureDetector.SimpleOnGestureListener {

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
      L(TAG, "onSingleTapUp");
      return super.onSingleTapUp(e);
    }

    @Override
    public void onLongPress(MotionEvent e) {
      L(TAG, "onLongPress");
      super.onLongPress(e);
      gesture.onLongPress(e);
      latestAction = ACTION_LONG_PRESS;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
      L(TAG, "onScroll");
      gesture.onScroll(e1, e2, distanceX, distanceY);
      return super.onScroll(e1, e2, distanceX, distanceY);
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
      L(TAG, "onFling");
      return super.onFling(e1, e2, velocityX, velocityY);
    }

    @Override
    public void onShowPress(MotionEvent e) {
      L(TAG, "onShowPress");
      super.onShowPress(e);
    }

    @Override
    public boolean onDown(MotionEvent e) {
      L(TAG, "onDown");
      super.onDown(e);
      latestAction = ACTION_START;
      return true;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
      L(TAG, "onDoubleTap");
      gesture.onDoubleClick(e);
      return super.onDoubleTap(e);
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
      L(TAG, "onDoubleTapEvent");
      return super.onDoubleTapEvent(e);
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
      L(TAG, "onSingleTapConfirmed");
      gesture.onClick(e);
      return super.onSingleTapConfirmed(e);
    }

    @Override
    public boolean onContextClick(MotionEvent e) {
      L(TAG, "onContextClick");
      return super.onContextClick(e);
    }
  }
}
