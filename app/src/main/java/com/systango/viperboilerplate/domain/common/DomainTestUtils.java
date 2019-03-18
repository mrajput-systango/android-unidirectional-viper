package com.systango.viperboilerplate.domain.common;

import com.systango.viperboilerplate.domain.entity.MovieEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mohit Rajput on 15/3/19.
 * Utility to test movie list domain layer
 */
public class DomainTestUtils {
    private MovieEntity getTestMovieEntity(int id) {
        MovieEntity movieEntity = new MovieEntity();
        movieEntity.setId(id);
        movieEntity.setTitle("Movie" + id);
        movieEntity.setOriginalTitle("Movie" + id);
        movieEntity.setBackdropPath("");
        movieEntity.setOriginalLanguage("English");
        movieEntity.setReleaseDate("24/10/2018");
        return movieEntity;
    }

    public List<MovieEntity> generateMovieEntityList(int totalCount) {
        List<MovieEntity> movieEntityList = new ArrayList<>();
        for (int i = 0; i < totalCount; i++) {
            movieEntityList.add(getTestMovieEntity(i + 1));
        }
        return movieEntityList;
    }
}
