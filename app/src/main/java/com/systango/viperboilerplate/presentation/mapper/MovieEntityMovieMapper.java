package com.systango.viperboilerplate.presentation.mapper;

import com.systango.viperboilerplate.common.Mapper;
import com.systango.viperboilerplate.domain.entity.MovieEntity;
import com.systango.viperboilerplate.presentation.entity.Movie;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Mohit Rajput on 13/3/19.
 */
public class MovieEntityMovieMapper implements Mapper<MovieEntity, Movie> {
    private final static String POSTER_BASE_URL = "https://image.tmdb.org/t/p/w342";
    private final static String BACKDROP_BASE_URL = "https://image.tmdb.org/t/p/w780";
    private final static String YOU_TUBE_BASE_URL = "https://www.youtube.com/watch?v=";

    @Override
    public Movie mapFrom(MovieEntity from) {
        Movie movie = new Movie();
        movie.setId(from.getId())
                .setVoteCount(from.getVoteCount())
                .setVoteAverage(from.getVoteAverage())
                .setPopularity(from.getPopularity())
                .setAdult(from.isAdult())
                .setTitle(from.getTitle())
                .setPosterPath(POSTER_BASE_URL + from.getPosterPath())
                .setOriginalLanguage(from.getOriginalLanguage())
                .setBackdropPath(from.getBackdropPath())
                .setOriginalTitle(from.getOriginalTitle())
                .setReleaseDate(from.getReleaseDate())
                .setOverview(from.getOverview());
        return movie;
    }

    public List<Movie> mapFrom(List<MovieEntity> from) {
        return from.stream().map(this::mapFrom).collect(Collectors.toList());
    }
}
