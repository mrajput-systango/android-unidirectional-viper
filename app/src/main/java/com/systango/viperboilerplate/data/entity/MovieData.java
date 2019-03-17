package com.systango.viperboilerplate.data.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;

/**
 * Created by Mohit Rajput on 11/3/19.
 */
@Data
public class MovieData {

    @SerializedName("release_date")
    private String releaseDate;

    @SerializedName("overview")
    private String overview;

    @SerializedName("adult")
    private boolean adult;

    @SerializedName("backdrop_path")
    private String backdropPath;

    @SerializedName("genreIds")
    private List<Integer> genreIds;

    @SerializedName("original_title")
    private String originalTitle;

    @SerializedName("original_language")
    private String originalLanguage;

    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName("popularity")
    private double popularity;

    @SerializedName("title")
    private String title;

    @SerializedName("vote_average")
    private double voteAverage;

    @SerializedName("video")
    private boolean video;

    @SerializedName("id")
    private int id;

    @SerializedName("vote_count")
    private int voteCount;
}
