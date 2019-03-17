package com.systango.viperboilerplate.data.di.popular;

import com.systango.viperboilerplate.data.di.component.MainComponent;
import com.systango.viperboilerplate.presentation.ui.activity.MovieListActivity;

import dagger.Component;

/**
 * Created by Mohit Rajput on 13/3/19.
 * TODO: Insert javadoc information here
 */
@PopularScope
@Component(modules = {MovieListActivityMvpModule.class}, dependencies = MainComponent.class)
public interface PopularSubComponent {
    void inject(MovieListActivity movieListActivity);
}
