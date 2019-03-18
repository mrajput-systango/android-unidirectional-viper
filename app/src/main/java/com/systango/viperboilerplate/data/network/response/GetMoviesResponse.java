package com.systango.viperboilerplate.data.network.response;

import com.google.gson.annotations.SerializedName;
import com.systango.viperboilerplate.data.entity.MovieData;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Mohit Rajput on 11/2/19.
 * Movies list response
 */
@Getter
@Setter
public class GetMoviesResponse {

    @SerializedName("page")
    private int page;

    @SerializedName("total_results")
    private int totalResults;

    @SerializedName("total_pages")
    private int totalPages;

    @SerializedName("results")
    private List<MovieData> movies;
}
