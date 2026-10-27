package com.shaifan.signals;

import android.app.*;
import android.os.*;
import android.content.*;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.view.*;
import android.widget.*;

public class FloatingService extends Service {
    WindowManager wm; View bubble;
    public IBinder onBind(Intent i) { return null; }
    public void onCreate() {
        super.onCreate();
        wm = (WindowManager)getSystemService(WINDOW_SERVICE);
        TextView t = new TextView(this);
        t.setText("S"); t.setTextColor(Color.WHITE); t.setTextSize(22);
        t.setGravity(Gravity.CENTER); t.setBackgroundColor(Color.rgb(20,184,166));
        int type = Build.VERSION.SDK_INT >= 26 ? WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY : WindowManager.LayoutParams.TYPE_PHONE;
        WindowManager.LayoutParams p = new WindowManager.LayoutParams(60,60,type,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, PixelFormat.TRANSLUCENT);
        p.gravity = Gravity.TOP | Gravity.RIGHT; p.x=20; p.y=250;
        t.setOnClickListener(v -> new AlertDialog.Builder(new android.view.ContextThemeWrapper(this, android.R.style.Theme_Material_Light_Dialog_Alert))
            .setTitle("SHAIFAN SIGNALS")
            .setMessage("Floating control is active. Live market analysis is not connected yet.")
            .setPositiveButton("Close", (d,w)->{}).create().getWindow());
        bubble=t; wm.addView(bubble,p);
    }
    public void onDestroy() {
        if (wm != null && bubble != null) try { wm.removeView(bubble); } catch(Exception ignored) {}
        super.onDestroy();
    }
}
