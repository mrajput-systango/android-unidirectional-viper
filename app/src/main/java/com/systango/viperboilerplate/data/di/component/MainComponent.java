package com.systango.viperboilerplate.data.di.component;

import android.content.Context;

import com.systango.viperboilerplate.ViperApplication;
import com.systango.viperboilerplate.data.datastore.RemoteMovieDataStore;
import com.systango.viperboilerplate.data.di.ApplicationContext;
import com.systango.viperboilerplate.data.di.ApplicationScope;
import com.systango.viperboilerplate.data.di.module.AppModule;
import com.systango.viperboilerplate.data.di.module.NetworkModule;

import dagger.Component;

/**
 * Created by Mohit Rajput on 13/3/19.
 * Main component of dagger to inject must have dependencies i.e. context and API calling interface
 */

@ApplicationScope
@Component(modules = {AppModule.class, NetworkModule.class})
public interface MainComponent {
    @ApplicationContext
    Context getContext();

    void inject(ViperApplication viperApplication);

    void inject(RemoteMovieDataStore remoteMovieDataStore);
}
