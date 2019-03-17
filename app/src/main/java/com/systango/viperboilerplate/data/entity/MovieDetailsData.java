package com.systango.viperboilerplate.data.entity;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

/**
 * Created by Mohit Rajput on 18/3/19.
 */
@Data
public class MovieDetailsData {
    @SerializedName("vote_count")
    private int voteCount;

    @SerializedName("vote_average")
    private double voteAverage;

    @SerializedName("video")
    private boolean video;

    @SerializedName("title")
    private String title;

    @SerializedName("tagline")
    private String tagline;

    @SerializedName("status")
    private String status;

    @SerializedName("runtime")
    private int runtime;

    @SerializedName("revenue")
    private int revenue;

    @SerializedName("release_date")
    private String releaseDate;

    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName("popularity")
    private double popularity;

    @SerializedName("overview")
    private String overview;

    @SerializedName("original_title")
    private String originalTitle;

    @SerializedName("original_language")
    private String originalLanguage;

    @SerializedName("imdb_id")
    private String imdbId;

    @SerializedName("id")
    private int id;

    @SerializedName("homepage")
    private String homepage;

    @SerializedName("budget")
    private int budget;

    @SerializedName("backdrop_path")
    private String backdropPath;

    @SerializedName("adult")
    private boolean adult;
}
