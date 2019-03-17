package com.systango.viperboilerplate;

import android.app.Application;
import android.content.Context;

import com.systango.viperboilerplate.data.di.component.DaggerMainComponent;
import com.systango.viperboilerplate.data.di.component.MainComponent;
import com.systango.viperboilerplate.data.di.module.AppModule;
import com.systango.viperboilerplate.data.di.module.NetworkModule;

/**
 * Created by Mohit Rajput on 12/3/19.
 */
public class ViperApplication extends Application {
    private static ViperApplication viperApplication;
    private MainComponent mainComponent;

    public static ViperApplication getApp() {
        return viperApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        viperApplication = this;
        initDependencies();
    }

    private void initDependencies() {
        mainComponent = DaggerMainComponent.builder().
                appModule(new AppModule(this))
                .networkModule(new NetworkModule(getString(R.string.api_key)))
                .build();
        mainComponent.inject(this);
    }

    public MainComponent getMainComponent() {
        return mainComponent;
    }

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
    }
}
