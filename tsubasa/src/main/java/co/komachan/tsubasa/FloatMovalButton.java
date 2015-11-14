package co.komachan.tsubasa;

import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

/**
 * Created by ukiy on 11/14/15.
 */
public class FloatMovalButton extends Button implements View.OnTouchListener {
    private int x;
    private int y;
    private int preX;
    private int preY;
    private int initialX;
    private int initialY;
    private int width;
    private int height;
    public static int MARGIN_W;
    public static int MARGIN_H;
    private WindowManager.LayoutParams windowManegerParams;
    private WindowManager windowManager;
    private Handler handler = new Handler();

    FloatMovalButton(Context c, WindowManager wm, int initX, int initY, int initWidth, int initHeight) {
        super(c);
        windowManager = wm;
        x = initX;
        y = initY;
        width = initWidth;
        height = initHeight;
        windowManegerParams = new WindowManager.LayoutParams(
                width, height, x, y,
                WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |
                    WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL |
                    WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH,
                PixelFormat.TRANSPARENT
        );

        this.setWidth(width);
        this.setHeight(height);
        this.setText("TEST");
        this.setOnTouchListener(this);
    }

    public void moveTo(int x, int y) {
        windowManegerParams.x = x - (width / 2);
        windowManegerParams.y = y - (height / 2);
    }

    public void show() {
        windowManager.addView(this, windowManegerParams);
    }

    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d("TouchEvent", "getAction()" + "ACTION_DOWN");
                preX = (int)event.getRawX();
                preY = (int)event.getRawY();
                initialX = windowManegerParams.x;
                initialY = windowManegerParams.y;
                return false;
            case MotionEvent.ACTION_UP:
                Log.d("TouchEvent", "getAction()" + "ACTION_UP");
                return true;
            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_OUTSIDE:
                Log.d("TouchEvent", "getAction()" + "ACTION_MOVE" + event.getX()+" "+event.getY());
                final int eventX = (int)event.getRawX();
                final int eventY = (int)event.getRawY();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        int direX = eventX - preX;
                        int direY = eventY - preY;
                        //preX = eventX;
                        //preY = eventY;
                        //int moveToX = direX > 0 ? 100 : -100;
                        //int moveToY = direY > 0 ? 100 : -100;
                        windowManegerParams.x =  initialX +  direX;
                        windowManegerParams.y =  initialY +  direY;
                        windowManager.removeView(FloatMovalButton.this);
                        windowManager.addView(FloatMovalButton.this, windowManegerParams);
                    }
                });
                return false;
            case MotionEvent.ACTION_CANCEL:
                Log.d("TouchEvent", "getAction()" + "ACTION_CANCEL");
                Log.d("TouchEvent", "getAction()" + "ACTION_OUTSIDE"+event.getX()+" "+event.getY());
                break;
        }
        return true;
    }

}
