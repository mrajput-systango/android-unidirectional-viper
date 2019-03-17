package com.systango.viperboilerplate.presentation.presenter.base;

import com.systango.viperboilerplate.domain.entity.MovieEntity;

import java.util.Optional;

import io.reactivex.Observable;

/**
 * Created by Mohit Rajput on 17/3/19.
 */
public interface MovieDetailsPresenter {
    void presentMovie(Observable<Optional<MovieEntity>> movieDetails);
}
