package com.systango.viperboilerplate.data.di.module;

import android.content.Context;

import com.systango.viperboilerplate.data.di.ApplicationContext;
import com.systango.viperboilerplate.data.di.ApplicationScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Mohit Rajput on 12/2/19.
 */
@Module
public class AppModule {
    private Context context;

    public AppModule(Context context) {
        this.context = context;
    }

    @Provides
    @ApplicationScope
    @ApplicationContext
    Context getContext() {
        return context;
    }
}