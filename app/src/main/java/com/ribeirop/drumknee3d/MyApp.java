package com.ribeirop.drumknee3d;

import android.app.Application;

public class MyApp extends Application {
    public static ContextHelper contextHelper;

    @Override
    public void onCreate() {
        super.onCreate();
        contextHelper = new ContextHelper(this);
        NativeLib.init();
    }
}