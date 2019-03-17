package com.systango.viperboilerplate.domain.interactor;

import com.systango.viperboilerplate.data.repository.MovieRepository;
import com.systango.viperboilerplate.domain.entity.MovieEntity;
import com.systango.viperboilerplate.presentation.presenter.MoviesPresenter;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Mohit Rajput on 12/3/19.
 * Interactor for getting popular movies list
 */
public class GetPopularMoviesInteractor {

    private final MovieRepository movieRepository;
    public MoviesPresenter presenter;

    public GetPopularMoviesInteractor(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public void fetchPopularMovies() {
        Observable<List<MovieEntity>> popularMovies = movieRepository.getPopularMovies();
        presenter.presenterMovies(popularMovies);
    }
}
