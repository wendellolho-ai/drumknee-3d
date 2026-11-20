package com.ribeirop.drumknee3d;

import android.content.Context;

public class ContextHelper {
    private final Context context;

    public ContextHelper(Context context) {
        this.context = context.getApplicationContext();
    }

    public Context get() {
        return context;
    }
}