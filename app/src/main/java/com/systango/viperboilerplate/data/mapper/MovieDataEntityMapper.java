package com.systango.viperboilerplate.data.mapper;

import com.systango.viperboilerplate.common.Mapper;
import com.systango.viperboilerplate.data.entity.MovieData;
import com.systango.viperboilerplate.domain.entity.MovieEntity;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Mohit Rajput on 13/3/19.
 * MovieData to MovieEntity DTO mapper
 */
public class MovieDataEntityMapper extends Mapper<MovieData, MovieEntity> {

    @Override
    public MovieEntity mapFrom(MovieData from) {
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

    public List<MovieEntity> mapFrom(List<MovieData> from) {
        return from.stream().map(this::mapFrom).collect(Collectors.toList());
    }
}
