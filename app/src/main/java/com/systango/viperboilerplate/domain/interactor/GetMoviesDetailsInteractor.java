package com.systango.viperboilerplate.domain.interactor;

import com.systango.viperboilerplate.data.repository.MovieRepository;
import com.systango.viperboilerplate.domain.entity.MovieEntity;
import com.systango.viperboilerplate.presentation.presenter.base.MovieDetailsPresenter;

import java.util.Optional;

import io.reactivex.Observable;

/**
 * Created by Mohit Rajput on 12/3/19.
 * Interactor for getting popular movies list
 */
public class GetMoviesDetailsInteractor {

    private final MovieRepository movieRepository;
    public MovieDetailsPresenter presenter;

    public GetMoviesDetailsInteractor(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public void fetchPopularMovies(int movieId) {
        Observable<Optional<MovieEntity>> movieDetails = movieRepository.getMovieDetails(movieId);
        presenter.presentMovie(movieDetails);
    }
}
