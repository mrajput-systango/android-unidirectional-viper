package com.systango.viperboilerplate.presentation.presenter;

import com.systango.viperboilerplate.domain.entity.MovieEntity;
import com.systango.viperboilerplate.presentation.base.BasePresenter;
import com.systango.viperboilerplate.presentation.mapper.MovieEntityMovieMapper;
import com.systango.viperboilerplate.presentation.view.MoviesView;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Mohit Rajput on 13/3/19.
 * TODO: Insert javadoc information here
 */
public class MoviesPresenterImpl extends BasePresenter implements MoviesPresenter {
    public MoviesView view;
    private MovieEntityMovieMapper movieEntityMovieMapper = new MovieEntityMovieMapper();

    @Override
    public void presenterMovies(Observable<List<MovieEntity>> popularMovies) {
        addDisposable(popularMovies.map(results -> movieEntityMovieMapper.mapFrom(results))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        view::showMovieList,
                        throwable -> view.showError(throwable.getMessage())));
    }
}
