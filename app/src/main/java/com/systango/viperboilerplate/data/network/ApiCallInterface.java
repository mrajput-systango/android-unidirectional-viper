package com.systango.viperboilerplate.data.network;

import com.systango.viperboilerplate.data.entity.MovieDetailsData;
import com.systango.viperboilerplate.data.network.response.GetMoviesResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

import static com.systango.viperboilerplate.data.network.ApiUrls.GET_MOVIE_DETAILS;
import static com.systango.viperboilerplate.data.network.ApiUrls.GET_POPULAR_MOVIES;

/**
 * Created by Mohit Rajput on 12/2/19.
 * Retrofit API calling interface
 */
public interface ApiCallInterface {
    @GET(GET_POPULAR_MOVIES)
    Observable<GetMoviesResponse> getPopularMovies();

    @GET(GET_MOVIE_DETAILS)
    Observable<MovieDetailsData> getMovieDetails(@Path("id") int movieId);
}
