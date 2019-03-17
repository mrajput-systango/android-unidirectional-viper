package com.systango.viperboilerplate.presentation.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.systango.viperboilerplate.R;
import com.systango.viperboilerplate.common.constants.Constants;
import com.systango.viperboilerplate.data.entity.MovieData;
import com.systango.viperboilerplate.data.utils.GlideUtils;
import com.systango.viperboilerplate.presentation.base.BaseActivity;
import com.systango.viperboilerplate.presentation.entity.Movie;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieDetailsActivity extends BaseActivity {

    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvDescription)
    TextView tvDescription;
    @BindView(R.id.ivPoster)
    ImageView ivPoster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        ButterKnife.bind(this);
        populateMovieDetails();
    }

    private void populateMovieDetails() {
        Movie movie = Parcels.unwrap(getIntent().getParcelableExtra(Constants.IntentExtras.MOVIE));
        if (!TextUtils.isEmpty(movie.getPosterPath())) {
            GlideUtils.loadImage(this, movie.getPosterPath(), ivPoster, 0);
        }
        if (!TextUtils.isEmpty(movie.getTitle())) {
            tvTitle.setText(movie.getTitle());
        }
        if (!TextUtils.isEmpty(movie.getOverview())) {
            tvDescription.setText(movie.getOverview());
        }
    }
}
