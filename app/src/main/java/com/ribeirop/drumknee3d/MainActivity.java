package com.ribeirop.drumknee3d;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowInsetsController;

public class MainActivity extends Activity implements SurfaceHolder.Callback {

    private SurfaceView surfaceView;
    private SurfaceHolder surfaceHolder;
    private NativeRenderer renderer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
            WindowInsetsController insetsController = getWindow().getInsetsController();
            if (insetsController != null) {
                insetsController.hide(WindowInsets.TYPE_STATUS_BARS | WindowInsets.TYPE_NAVIGATION_BARS);
            }
        }

        surfaceView = new SurfaceView(this);
        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(this);
        setContentView(surfaceView);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        renderer = new NativeRenderer(holder.getSurface());
        renderer.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        if (renderer != null) {
            renderer.onResize(width, height);
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        if (renderer != null) {
            renderer.stop();
            renderer = null;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (renderer != null) {
            renderer.onTouchEvent(event.getX(), event.getY(), event.getActionMasked());
        }
        return true;
    }

    @Override
    protected void onDestroy() {
        if (renderer != null) {
            renderer.stop();
            renderer = null;
        }
        super.onDestroy();
    }
}