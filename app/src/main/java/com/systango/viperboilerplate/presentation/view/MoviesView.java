package com.systango.viperboilerplate.presentation.view;

import com.systango.viperboilerplate.presentation.base.BaseView;
import com.systango.viperboilerplate.presentation.entity.Movie;

import java.util.List;

/**
 * Created by Mohit Rajput on 12/3/19.
 */
public interface MoviesView extends BaseView {
    void showMovieList(List<Movie> movies);
}
