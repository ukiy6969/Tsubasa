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
                width, height, x - (width/2), y - (height/2),
                WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |
                    WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL |
                    WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH,
                PixelFormat.TRANSPARENT
        );
    }

    public void moveTo(int x, int y) {
        windowManegerParams.x = x - (width / 2);
        windowManegerParams.y = y - (height / 2);
    }

    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d("TouchEvent", "getAction()" + "ACTION_DOWN");
                break;
            case MotionEvent.ACTION_UP:
                Log.d("TouchEvent", "getAction()" + "ACTION_UP");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d("TouchEvent", "getAction()" + "ACTION_MOVE" + event.getX()+" "+event.getY());
                final int eventX = (int)event.getX();
                final int eventY = (int)event.getY();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        //clickBtn.setX(event.getX());
                        //clickBtn.setY(event.getY());
                        windowManegerParams.x += eventX > (windowManegerParams.x - 400/2) ? 10 : -10;
                        windowManegerParams.y += eventY > (windowManegerParams.y - 300/2) ?10 : -10;
                        windowManager.removeView(FloatMovalButton.this);
                        /*
                        windowManager.addView(FloatMovalButton.this, new WindowManager.LayoutParams(
                               400,300,(int)event.getX(),(int)event.getY(),
                                WindowManager.LayoutParams.TYPE_PHONE,
                                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |
                                        WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL |
                                        WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH,
                                PixelFormat.TRANSPARENT
                        ));
                        */
                    }
                });

                break;
            case MotionEvent.ACTION_CANCEL:
                Log.d("TouchEvent", "getAction()" + "ACTION_CANCEL");
                break;
            case MotionEvent.ACTION_OUTSIDE:
                Log.d("TouchEvent", "getAction()" + "ACTION_OUTSIDE"+event.getX()+" "+event.getY());
                break;
        }
        return false;
    }

}
