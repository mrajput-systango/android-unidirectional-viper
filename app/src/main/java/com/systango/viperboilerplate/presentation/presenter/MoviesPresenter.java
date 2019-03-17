package com.systango.viperboilerplate.presentation.presenter;

import com.systango.viperboilerplate.domain.entity.MovieEntity;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Mohit Rajput on 13/3/19.
 */
public interface MoviesPresenter {
    void presenterMovies(Observable<List<MovieEntity>> popularMovies);
}
