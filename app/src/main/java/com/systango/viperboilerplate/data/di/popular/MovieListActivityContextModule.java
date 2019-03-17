package com.systango.viperboilerplate.data.di.popular;

import android.content.Context;

import com.systango.viperboilerplate.data.di.ActivityContext;
import com.systango.viperboilerplate.presentation.ui.activity.MovieListActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Mohit Rajput on 14/3/19.
 * TODO: Insert javadoc information here
 */
@Module
public class MovieListActivityContextModule {
    private Context context;
    private MovieListActivity movieListActivity;

    public MovieListActivityContextModule(MovieListActivity movieListActivity) {
        this.movieListActivity = movieListActivity;
        context = movieListActivity;
    }

    @Provides
    @PopularScope
    public MovieListActivity providesMainActivity() {
        return movieListActivity;
    }

    @Provides
    @PopularScope
    @ActivityContext
    public Context provideContext() {
        return context;
    }
}
