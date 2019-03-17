package com.systango.viperboilerplate.data.datastore;

import com.systango.viperboilerplate.ViperApplication;
import com.systango.viperboilerplate.data.mapper.MovieDataEntityMapper;
import com.systango.viperboilerplate.data.network.ApiCallInterface;
import com.systango.viperboilerplate.domain.entity.MovieEntity;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by Mohit Rajput on 13/3/19.
 * Makes REST API calls to fetch movies from remote database
 */
public class RemoteMovieDataStore implements MoviesDataStore {
    @Inject
    ApiCallInterface apiCallInterface;
    private MovieDataEntityMapper movieDataEntityMapper = new MovieDataEntityMapper();

    public RemoteMovieDataStore() {
        ViperApplication.getApp().getMainComponent().inject(this);
    }

    @Override
    public Observable<Optional<MovieEntity>> getMovieById(int movieId) {
        return null;
    }

    @Override
    public Observable<List<MovieEntity>> getPopularMovies() {
        return apiCallInterface.getPopularMovies().map(results -> movieDataEntityMapper.mapFrom(results.getMovies()));
    }

    @Override
    public Observable<List<MovieEntity>> search(String query) {
        return null;
    }
}
