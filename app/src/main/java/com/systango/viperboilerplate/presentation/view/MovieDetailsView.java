package com.systango.viperboilerplate.presentation.view;

import com.systango.viperboilerplate.presentation.base.BaseView;
import com.systango.viperboilerplate.presentation.entity.Movie;

/**
 * Created by Mohit Rajput on 17/3/19.
 */
public interface MovieDetailsView extends BaseView {
    void showMovieDetails(Movie movie);
}
