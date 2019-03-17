package com.systango.viperboilerplate.common.configurator;

import com.systango.viperboilerplate.data.datastore.LocalMovieDataStore;
import com.systango.viperboilerplate.data.datastore.RemoteMovieDataStore;
import com.systango.viperboilerplate.data.repository.MovieRepository;
import com.systango.viperboilerplate.domain.interactor.GetPopularMoviesInteractor;
import com.systango.viperboilerplate.presentation.presenter.MoviesPresenter;
import com.systango.viperboilerplate.presentation.presenter.MoviesPresenterImpl;
import com.systango.viperboilerplate.presentation.router.MoviesRouter;
import com.systango.viperboilerplate.presentation.router.impl.MoviesRouterImpl;
import com.systango.viperboilerplate.presentation.ui.activity.MovieListActivity;

/**
 * Created by Mohit Rajput on 17/3/19.
 * Configure each layer of VIPER for movies module
 */
public class MovieConfigurator implements BaseConfigurator<MovieListActivity> {
    public static MovieConfigurator shared = new MovieConfigurator();

    @Override
    public void configure(MovieListActivity view) {
        GetPopularMoviesInteractor interactor = new GetPopularMoviesInteractor(new MovieRepository(new RemoteMovieDataStore(), new LocalMovieDataStore()));
        MoviesRouter router = new MoviesRouterImpl(view);
        MoviesPresenter presenter = new MoviesPresenterImpl();
        ((MoviesPresenterImpl) presenter).view = view;
        interactor.presenter = presenter;
        view.interactor = interactor;
        view.router = router;
    }
}
