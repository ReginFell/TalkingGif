package ua.regin.gif.application;

import com.squareup.leakcanary.LeakCanary;

public class Application extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();
        LeakCanary.install(this);
    }
}
