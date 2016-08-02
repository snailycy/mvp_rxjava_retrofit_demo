package com.ycy.demo.app;

import android.app.Application;

public class DemoApplication extends Application {
    private AppProfile appProfile;

    @Override
    public void onCreate() {
        super.onCreate();

        appProfile = AppProfile.getAppProfile(getApplicationContext());

        appProfile.init();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        if (appProfile != null) {
            appProfile.onTerminate();
        }
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        if (appProfile != null) {
            appProfile.onLowMemory();
        }
    }
}
