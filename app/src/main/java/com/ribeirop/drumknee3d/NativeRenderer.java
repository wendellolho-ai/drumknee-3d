package com.ribeirop.drumknee3d;


public class NativeRenderer implements Runnable {
    private final Object nativeHandle;
    private final android.view.Surface surface;
    private Thread thread;
    private volatile boolean running = false;
    private int width = 0, height = 0;

    public NativeRenderer(android.view.Surface surface) {
        this.surface = surface;
        this.nativeHandle = new Long(nativeCreate(surface));
    }

    public void start() {
        running = true;
        thread = new Thread(this, "NativeRenderer");
        thread.start();
    }

    public void stop() {
        running = false;
        if (thread != null) {
            try { thread.join(2000); } catch (InterruptedException e) {}
            thread = null;
        }
        nativeDestroy();
    }

    public void onResize(int w, int h) {
        this.width = w;
        this.height = h;
        nativeResize(w, h);
    }

    public void onTouchEvent(float x, float y, int action) {
        nativeTouch(x, y, action);
    }

    @Override
    public void run() {
        while (running) {
            nativeDraw();
            try { Thread.sleep(16); } catch (InterruptedException e) {}
        }
    }

    private static native long nativeCreate(android.view.Surface surface);
    private static native void nativeResize(int w, int h);
    private static native void nativeDraw();
    private static native void nativeTouch(float x, float y, int action);
    private static native void nativeDestroy();
}