package com.systango.viperboilerplate.data.datastore;

import com.systango.viperboilerplate.domain.entity.MovieEntity;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by Mohit Rajput on 13/3/19.
 * TODO: Insert javadoc information here
 */
public class LocalMovieDataStore implements MoviesDataStore {

    @Inject
    public LocalMovieDataStore() {
    }

    @Override
    public Observable<Optional<MovieEntity>> getMovieById(int movieId) {
        return null;
    }

    @Override
    public Observable<List<MovieEntity>> getPopularMovies() {
        return null;
    }

    @Override
    public Observable<List<MovieEntity>> search(String query) {
        return null;
    }

    public Observable<Boolean> isEmpty() {
        return Observable.just(true);
        //return moviesCache.isEmpty();
    }

    void saveAll(List<MovieEntity> movieEntities) {
        //moviesCache.saveAll(movieEntities);
    }
}
