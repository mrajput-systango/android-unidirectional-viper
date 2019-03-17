package com.systango.viperboilerplate.data.network;


import static com.systango.viperboilerplate.BuildConfig.SERVER_BASE_URL;

/**
 * Created by Mohit Rajput on 11/3/19.
 * Contains all REST API URLs
 * BASE_URL comes from Build Config which is different in each environment.
 */

public interface ApiUrls {
    //Server APIs
    String GET_POPULAR_MOVIES = SERVER_BASE_URL + "movie/popular";
    String GET_MOVIE_DETAILS = SERVER_BASE_URL + "movie/{id}?append_to_response=videos,reviews";
}
