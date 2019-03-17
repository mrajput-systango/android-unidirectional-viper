package com.systango.viperboilerplate.data.repository;

import com.systango.viperboilerplate.data.datastore.LocalMovieDataStore;
import com.systango.viperboilerplate.data.datastore.RemoteMovieDataStore;
import com.systango.viperboilerplate.domain.entity.MovieEntity;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by Mohit Rajput on 11/3/19.
 * MovieData list data provider
 */
public class MovieRepository {
    private RemoteMovieDataStore remoteMovieDataStore;

    private LocalMovieDataStore localMovieDataStore;

    @Inject
    public MovieRepository(RemoteMovieDataStore remoteMovieDataStore, LocalMovieDataStore localMovieDataStore) {
        this.remoteMovieDataStore = remoteMovieDataStore;
        this.localMovieDataStore = localMovieDataStore;
    }

    public Observable<List<MovieEntity>> getPopularMovies() {
        return remoteMovieDataStore.getPopularMovies();
    }

    public Observable<Optional<MovieEntity>> getMovieDetails(int movieId) {
        return remoteMovieDataStore.getMovieById(movieId);
    }
}
