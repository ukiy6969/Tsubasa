package co.komachan.tsubasa;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by ukiy on 11/13/15.
 */
public class MyFirstService extends Service {
    WindowManager wm;
    View view;
    android.os.Handler handler = new android.os.Handler();
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        int res = super.onStartCommand(intent, flags, startId);
        LayoutInflater li = LayoutInflater.from(this);
        WindowManager.LayoutParams lparams = new WindowManager.LayoutParams(
                0,0,10,WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |
                    WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL |
                    WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH,
                PixelFormat.TRANSPARENT
        );
        wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        view = li.inflate(R.layout.overlay, null);
        view.setOnTouchListener(new View.OnTouchListener() {
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
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        Log.d("TouchEvent", "getAction()" + "ACTION_CANCEL");
                        break;
                    case MotionEvent.ACTION_OUTSIDE:
                        Log.d("TouchEvent", "getAction()" + "ACTION_OUTSIDE");
                        break;
                }
            /*
            handler.post(() -> {
                TextView tv =(TextView) view.findViewById(R.id.textView1);
                tv.setText("Click");
                wm.removeView(view);
            });
            */
                return false;
            });
        /*
        Button clickBtn = (Button) view.findViewById(R.id.clickBtn);
        clickBtn.setOnTouchListener((v, motion) -> {
            Log.d("MyFirstService", "Click"+motion.getX()+" "+motion.getY());
            switch (motion.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    Log.d("TouchEvent", "getAction()" + "ACTION_DOWN");
                    break;
                case MotionEvent.ACTION_UP:
                    Log.d("TouchEvent", "getAction()" + "ACTION_UP");
                    break;
                case MotionEvent.ACTION_MOVE:
                    Log.d("TouchEvent", "getAction()" + "ACTION_MOVE");
                    break;
                case MotionEvent.ACTION_CANCEL:
                    Log.d("TouchEvent", "getAction()" + "ACTION_CANCEL");
                    break;
                case MotionEvent.ACTION_OUTSIDE:
                    Log.d("TouchEvent", "getAction()" + "ACTION_OUTSIDE");
                    break;
            }
            handler.post(() -> {
                TextView tv =(TextView) view.findViewById(R.id.textView1);
                tv.setText("Click");
                wm.removeView(view);
            });
            return false;
        });
        */
        wm.addView(view, lparams);
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("MyFirstService", "destroyed");
        wm.removeView(view);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
