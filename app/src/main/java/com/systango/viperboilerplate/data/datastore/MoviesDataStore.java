package com.systango.viperboilerplate.data.datastore;

import com.systango.viperboilerplate.domain.entity.MovieEntity;

import java.util.List;
import java.util.Optional;

import io.reactivex.Observable;

/**
 * Created by Mohit Rajput on 13/3/19.
 * TODO: Insert javadoc information here
 */
public interface MoviesDataStore {
    Observable<Optional<MovieEntity>> getMovieById(int movieId);

    Observable<List<MovieEntity>> getPopularMovies();

    Observable<List<MovieEntity>> search(String query);
}
