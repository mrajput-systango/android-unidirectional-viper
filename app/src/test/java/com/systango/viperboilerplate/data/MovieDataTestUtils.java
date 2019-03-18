package com.systango.viperboilerplate.data;

import com.systango.viperboilerplate.data.entity.MovieData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mohit Rajput on 18/3/19.
 * Utility to test movie data
 */
public class MovieDataTestUtils {
    public MovieData getTestMovieData(int id) {
        MovieData movieData = new MovieData();
        movieData.setId(id);
        movieData.setTitle("Movie" + id);
        movieData.setOriginalTitle("Movie" + id);
        movieData.setBackdropPath("");
        movieData.setOriginalLanguage("English");
        movieData.setReleaseDate("24/10/2018");
        return movieData;
    }

    public List<MovieData> generateMovieDataList(int totalCount) {
        List<MovieData> movieDataList = new ArrayList<>();
        for (int i = 0; i < totalCount; i++) {
            movieDataList.add(getTestMovieData(i + 1));
        }
        return movieDataList;
    }
}
