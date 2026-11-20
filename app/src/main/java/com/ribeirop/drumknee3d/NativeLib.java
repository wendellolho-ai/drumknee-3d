package com.ribeirop.drumknee3d;

public class NativeLib {
    static {
        System.loadLibrary("drumknee3d");
    }

    public static native void init();
    public static native void setContext(Object contextHelper);
    public static native void loadAsset(String path);
    public static native void playNote(int note, int velocity);
    public static native void stopNote(int note);
    public static native void setKit(int kitIndex);
}