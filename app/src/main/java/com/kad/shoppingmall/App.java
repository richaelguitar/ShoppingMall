package com.kad.shoppingmall;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

public class App extends Application {

    private static App app;

    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
        app = this;
    }




    public static App getInstance(){
        return app;
    }
}
