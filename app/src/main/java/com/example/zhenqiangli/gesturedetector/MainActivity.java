package com.example.zhenqiangli.gesturedetector;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import com.example.zhenqiangli.gesturedetector.customize.GestureTextView;
import com.example.zhenqiangli.gesturedetector.customize.GestureTextView.Gesture;

public class MainActivity extends AppCompatActivity {
  private static final String TAG = "MainActivity";
  GestureTextView v;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    v = (GestureTextView) findViewById(R.id.gesture_text_view);
    v.registerGesture(new MyGesture());
  }

  private class MyGesture implements Gesture {
    public void onClick(MotionEvent e) {
      Log.d(TAG, "onClick: ");
    }

    public void onDoubleClick(MotionEvent e) {
      Log.d(TAG, "onDoubleClick: ");
    }

    public void onLongPress(MotionEvent e) {
      Log.d(TAG, "onLongPress: ");
    }

    public void onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
      Log.d(TAG, "onScroll: ");
    }

    public  void onLongPressSelect(MotionEvent e) {
      Log.d(TAG, "onLongPressSelect: ");
    }
  }

  @Override
  protected void onDestroy() {
    v.unregisterGesture();
    super.onDestroy();
  }
}
