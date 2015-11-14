package co.komachan.tsubasa;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by ukiy on 11/14/15.
 */
public class SideSwipeView extends View implements View.OnTouchListener {

    public static int SWIPE_TOUCH_WIDTH = 40;
    public static int SWIPE_TOUCH_HEIGHT = WindowManager.LayoutParams.MATCH_PARENT;
    public WindowManager.LayoutParams ssvParams;
    private Handler handler = new Handler();
    private WindowManager windowManager;

    public SideSwipeView(Context c, WindowManager wm) {
        super(c);
        windowManager = wm;
        ssvParams = new WindowManager.LayoutParams(
                SideSwipeView.SWIPE_TOUCH_WIDTH,SideSwipeView.SWIPE_TOUCH_HEIGHT,0,0,
                WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |
                        WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL |
                        WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH,
                PixelFormat.TRANSPARENT
        );
        ssvParams.gravity = Gravity.LEFT | Gravity.TOP;
        this.setBackgroundColor(Color.RED);
    }

    public void show() {
        windowManager.addView(this, ssvParams);
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Log.d("MyFirstService", "Click" + event.getX() + " " + event.getY());
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d("TouchEvent", "getAction()" + "ACTION_DOWN");
                break;
            case MotionEvent.ACTION_UP:
                Log.d("TouchEvent", "getAction()" + "ACTION_UP");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d("TouchEvent", "getAction()" + "ACTION_MOVE");
                /*
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        wm.removeView(view);
                        wm.addView(clickBtn, params);
                        clickBtn.setVisibility(View.VISIBLE);
                    }
                });
                */
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.d("TouchEvent", "getAction()" + "ACTION_CANCEL");
                break;
            case MotionEvent.ACTION_OUTSIDE:
                Log.d("TouchEvent", "getAction()" + "ACTION_OUTSIDE");
                break;
        }
        return false;
    }
}
