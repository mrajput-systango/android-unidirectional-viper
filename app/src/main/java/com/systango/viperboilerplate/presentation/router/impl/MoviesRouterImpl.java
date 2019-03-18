package com.systango.viperboilerplate.presentation.router.impl;

import android.app.Activity;
import android.content.Intent;

import com.systango.viperboilerplate.common.constants.Constants;
import com.systango.viperboilerplate.presentation.router.MoviesRouter;
import com.systango.viperboilerplate.presentation.ui.activity.MovieDetailsActivity;

/**
 * Created by Mohit Rajput on 12/3/19.
 */
public class MoviesRouterImpl implements MoviesRouter {

    private Activity activity;

    public MoviesRouterImpl(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void goToMovieDetails(int movieId) {
        Intent intent = new Intent(activity, MovieDetailsActivity.class);
        intent.putExtra(Constants.IntentExtras.MOVIE_ID, movieId);
        activity.startActivity(intent);
    }
}
