package com.systango.viperboilerplate.presentation.router.impl;

import android.app.Activity;
import android.content.Intent;

import com.systango.viperboilerplate.common.constants.Constants;
import com.systango.viperboilerplate.presentation.entity.Movie;
import com.systango.viperboilerplate.presentation.router.AbstractRouter;
import com.systango.viperboilerplate.presentation.router.MoviesRouter;
import com.systango.viperboilerplate.presentation.ui.activity.MovieDetailsActivity;

import org.parceler.Parcels;

/**
 * Created by Mohit Rajput on 12/3/19.
 * TODO: Insert javadoc information here
 */
public class MoviesRouterImpl extends AbstractRouter implements MoviesRouter {

    public MoviesRouterImpl(Activity activity) {
        super(activity);
    }

    @Override
    public void goToMovieDetails(Movie movie) {
        Intent intent = new Intent(getActivity(), MovieDetailsActivity.class);
        intent.putExtra(Constants.IntentExtras.MOVIE, Parcels.wrap(movie));
        getActivity().startActivity(intent);
    }
}
