package com.systango.viperboilerplate.data.mapper;

import com.systango.viperboilerplate.common.Mapper;
import com.systango.viperboilerplate.data.entity.MovieDetailsData;
import com.systango.viperboilerplate.domain.entity.MovieEntity;

/**
 * Created by Mohit Rajput on 17/3/19.
 */
public class MovieDetailsDataEntityMapper extends Mapper<MovieDetailsData, MovieEntity> {

    @Override
    public MovieEntity mapFrom(MovieDetailsData from) {
        MovieEntity movieEntity = new MovieEntity();
        movieEntity.setId(from.getId())
                .setVoteCount(from.getVoteCount())
                .setVoteAverage(from.getVoteAverage())
                .setPopularity(from.getPopularity())
                .setAdult(from.isAdult())
                .setTitle(from.getTitle())
                .setPosterPath(from.getPosterPath())
                .setOriginalLanguage(from.getOriginalLanguage())
                .setBackdropPath(from.getBackdropPath())
                .setOriginalTitle(from.getOriginalTitle())
                .setReleaseDate(from.getReleaseDate())
                .setOverview(from.getOverview());
        return movieEntity;
    }
}
