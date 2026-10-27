package com.shaifan.signals;

import android.app.*;
import android.os.*;
import android.content.*;
import android.net.Uri;
import android.provider.Settings;
import android.graphics.Color;
import android.view.*;
import android.widget.*;

public class MainActivity extends Activity {
    LinearLayout root;
    TextView text(String s, int size) {
        TextView t = new TextView(this); t.setText(s); t.setTextSize(size);
        t.setTextColor(Color.WHITE); t.setPadding(16,16,16,16); return t;
    }
    public void onCreate(Bundle b) {
        super.onCreate(b);
        root = new LinearLayout(this); root.setOrientation(1); root.setPadding(22,28,22,22);
        root.setBackgroundColor(Color.rgb(16,24,39));
        root.addView(text("◉  SHAIFAN SIGNALS",26));
        root.addView(text("Chart Analysis • Urdu Voice (next stage)",16));
        Button overlay = new Button(this); overlay.setText("ENABLE FLOATING BUTTON");
        root.addView(overlay);
        overlay.setOnClickListener(v -> {
            if (!Settings.canDrawOverlays(this)) {
                startActivity(new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:" + getPackageName())));
            } else startService(new Intent(this, FloatingService.class));
        });
        Button info = new Button(this); info.setText("MARKET DATA STATUS");
        root.addView(info);
        info.setOnClickListener(v -> new AlertDialog.Builder(this)
            .setMessage("Market feed is not connected yet. No live trade signal or accuracy claim is available.")
            .setPositiveButton("OK",null).show());
        setContentView(root);
    }
    protected void onResume() {
        super.onResume();
        if (root != null && Settings.canDrawOverlays(this)) {
            // Permission is ready; user can enable the floating control.
        }
    }
}
