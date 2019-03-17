package com.systango.viperboilerplate.presentation.presenter;

import com.systango.viperboilerplate.domain.entity.MovieEntity;
import com.systango.viperboilerplate.presentation.base.BasePresenter;
import com.systango.viperboilerplate.presentation.mapper.MovieEntityMovieMapper;
import com.systango.viperboilerplate.presentation.presenter.base.MovieDetailsPresenter;
import com.systango.viperboilerplate.presentation.view.MovieDetailsView;

import java.util.Optional;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Mohit Rajput on 18/3/19.
 */
public class MovieDetailsPresenterImpl extends BasePresenter implements MovieDetailsPresenter {
    public MovieDetailsView view;
    private MovieEntityMovieMapper movieEntityMovieMapper = new MovieEntityMovieMapper();

    @Override
    public void presentMovie(Observable<Optional<MovieEntity>> movieDetails) {
        addDisposable(movieDetails.map(results -> movieEntityMovieMapper.mapFrom(results.get()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        view::showMovieDetails,
                        throwable -> view.showError(throwable.getMessage())));
    }
}
