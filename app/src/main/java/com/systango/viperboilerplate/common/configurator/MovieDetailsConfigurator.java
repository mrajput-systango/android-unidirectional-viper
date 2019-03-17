package com.systango.viperboilerplate.common.configurator;

import com.systango.viperboilerplate.data.datastore.LocalMovieDataStore;
import com.systango.viperboilerplate.data.datastore.RemoteMovieDataStore;
import com.systango.viperboilerplate.data.repository.MovieRepository;
import com.systango.viperboilerplate.domain.interactor.GetMoviesDetailsInteractor;
import com.systango.viperboilerplate.presentation.presenter.MovieDetailsPresenterImpl;
import com.systango.viperboilerplate.presentation.presenter.base.MovieDetailsPresenter;
import com.systango.viperboilerplate.presentation.ui.activity.MovieDetailsActivity;

/**
 * Created by Mohit Rajput on 17/3/19.
 * Configure each layer of VIPER for movie details module
 */
public class MovieDetailsConfigurator implements BaseConfigurator<MovieDetailsActivity> {
    public static MovieDetailsConfigurator shared = new MovieDetailsConfigurator();

    @Override
    public void configure(MovieDetailsActivity view) {
        GetMoviesDetailsInteractor interactor = new GetMoviesDetailsInteractor(new MovieRepository(new RemoteMovieDataStore(), new LocalMovieDataStore()));
        MovieDetailsPresenter presenter = new MovieDetailsPresenterImpl();
        ((MovieDetailsPresenterImpl) presenter).view = view;
        interactor.presenter = presenter;
        view.interactor = interactor;
    }
}
