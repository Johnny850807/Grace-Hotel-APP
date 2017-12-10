package com.ood.clean.waterball.gracehotel;


import android.app.Application;
import android.content.Context;
import android.content.res.Resources;

public class MyApplication extends Application{
    private static Context context;
    private static Resources resources;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        resources = getResources();
    }

    public static Context getDefaultContext() {
        return context;
    }

    public static Resources getMyResources(){
        return resources;
    }
}
