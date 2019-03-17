package com.systango.viperboilerplate.presentation.ui.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.systango.viperboilerplate.R;
import com.systango.viperboilerplate.common.configurator.MovieDetailsConfigurator;
import com.systango.viperboilerplate.common.constants.Constants;
import com.systango.viperboilerplate.data.utils.GlideUtils;
import com.systango.viperboilerplate.domain.interactor.GetMoviesDetailsInteractor;
import com.systango.viperboilerplate.presentation.base.BaseActivity;
import com.systango.viperboilerplate.presentation.entity.Movie;
import com.systango.viperboilerplate.presentation.view.MovieDetailsView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieDetailsActivity extends BaseActivity implements MovieDetailsView {

    public GetMoviesDetailsInteractor interactor;
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
        setup();
        fetchMovieDetails();
    }

    private void setup() {
        MovieDetailsConfigurator.shared.configure(this);
    }

    private void fetchMovieDetails() {
        int movieId = getIntent().getIntExtra(Constants.IntentExtras.MOVIE_ID, 0);
        showProgress();
        interactor.fetchPopularMovies(movieId);
    }

    @Override
    public void showMovieDetails(Movie movie) {
        hideProgress();
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

    @Override
    public void showProgress() {
        showProgress(getString(R.string.loading_details));
    }

    @Override
    public void hideProgress() {
        dismissProgress();
    }

    @Override
    public void showError(String errorMessage) {
        hideProgress();
    }
}
