package com.systango.viperboilerplate.presentation.router.impl;

import android.app.Activity;
import android.content.Intent;

import com.systango.viperboilerplate.common.constants.Constants;
import com.systango.viperboilerplate.presentation.router.AbstractRouter;
import com.systango.viperboilerplate.presentation.router.MoviesRouter;
import com.systango.viperboilerplate.presentation.ui.activity.MovieDetailsActivity;

/**
 * Created by Mohit Rajput on 12/3/19.
 */
public class MoviesRouterImpl extends AbstractRouter implements MoviesRouter {

    public MoviesRouterImpl(Activity activity) {
        super(activity);
    }

    @Override
    public void goToMovieDetails(int movieId) {
        Intent intent = new Intent(getActivity(), MovieDetailsActivity.class);
        intent.putExtra(Constants.IntentExtras.MOVIE_ID, movieId);
        getActivity().startActivity(intent);
    }
}
