package com.systango.viperboilerplate.presentation.entity;

import org.parceler.Parcel;

import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Created by Mohit Rajput on 12/3/19.
 * Model class of Movie.
 */
@Data
@Accessors(chain = true)
@Parcel(Parcel.Serialization.BEAN)
public class Movie {
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
