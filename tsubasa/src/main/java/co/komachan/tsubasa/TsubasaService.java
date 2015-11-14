package co.komachan.tsubasa;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

/**
 * Created by ukiy on 11/13/15.
 */
public class TsubasaService extends Service {
    WindowManager wm;
    SideSwipeView sideSwipeView;
    Button clickBtn;
    WindowManager.LayoutParams params;
    android.os.Handler handler = new android.os.Handler();
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        LayoutInflater li = LayoutInflater.from(this);
        wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        sideSwipeView = new SideSwipeView(this, wm);
        final FloatMovalButton fmbtn = new FloatMovalButton(this, wm, 0, 0, 400, 300);
        sideSwipeView.show();
        sideSwipeView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_MOVE:
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                sideSwipeView.hide();
                                fmbtn.show();
                            }
                        });
                }
                return false;
            }
        });
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("MyFirstService", "destroyed");
        //wm.removeView(view);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
