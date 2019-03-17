package com.systango.viperboilerplate.domain.entity;

import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Created by Mohit Rajput on 13/3/19.
 */
@Data
@Accessors(chain = true)
public class MovieEntity {
    private String releaseDate;
    private String overview;
    private boolean adult;
    private String backdropPath;
    private List<Integer> genreIds;
    private String originalTitle;
    private String originalLanguage;
    private String posterPath;
    private double popularity;
    private String title;
    private double voteAverage;
    private boolean video;
    private int id;
    private int voteCount;
}
