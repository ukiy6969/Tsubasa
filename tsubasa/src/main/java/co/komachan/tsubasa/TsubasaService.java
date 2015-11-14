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
        sideSwipeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fmbtn.show();
                sideSwipeView.hide();
            }
        });
        /*
        view.setBackgroundColor(Color.RED);
        clickBtn = (Button) li.inflate(R.layout.float_moval_button, null);
        clickBtn.setVisibility(View.INVISIBLE);
        clickBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wm.removeView(clickBtn);
            }
        });

        params = new WindowManager.LayoutParams(
                400, 300,
                WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |
                    WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL |
                    WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH,
                PixelFormat.RGB_565
        );
        view.setOnTouchListener(new View.OnTouchListener() {

            /*
            handler.post(() -> {
                TextView tv =(TextView) view.findViewById(R.id.textView1);
                tv.setText("Click");
                wm.removeView(view);
            });
            */
        /*
        });
        wm.addView(view, lparams);
        */
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
