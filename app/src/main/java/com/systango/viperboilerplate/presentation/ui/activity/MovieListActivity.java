package com.systango.viperboilerplate.presentation.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.systango.viperboilerplate.R;
import com.systango.viperboilerplate.common.configurator.MovieConfigurator;
import com.systango.viperboilerplate.domain.interactor.GetPopularMoviesInteractor;
import com.systango.viperboilerplate.presentation.base.BaseActivity;
import com.systango.viperboilerplate.presentation.common.ToastUtils;
import com.systango.viperboilerplate.presentation.entity.Movie;
import com.systango.viperboilerplate.presentation.listener.OnMovieItemClickedListener;
import com.systango.viperboilerplate.presentation.router.MoviesRouter;
import com.systango.viperboilerplate.presentation.ui.adapter.MovieAdapter;
import com.systango.viperboilerplate.presentation.view.MoviesView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieListActivity extends BaseActivity implements MoviesView, OnMovieItemClickedListener {

    public MoviesRouter router;
    public GetPopularMoviesInteractor interactor;
    @BindView(R.id.rvMovies)
    RecyclerView rvMovies;
    private MovieAdapter movieAdapter;
    private List<Movie> movies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);
        ButterKnife.bind(this);
        setup();
        initRecyclerView();
        loadMovies();
    }

    private void setup() {
        MovieConfigurator.shared.configure(this);
    }

    private void initRecyclerView() {
        movies = new ArrayList<>();
        movieAdapter = new MovieAdapter(this, movies);
        movieAdapter.setOnMovieItemClickedListener(this);
        rvMovies.setAdapter(movieAdapter);
        rvMovies.setLayoutManager(new LinearLayoutManager(context));
    }

    private void loadMovies() {
        showProgress();
        interactor.fetchPopularMovies();
    }

    @Override
    public void showMovieList(List<Movie> movies) {
        refreshMovies(movies);
    }

    @Override
    public void showProgress() {
        showProgress(getString(R.string.loading_movies));
    }

    @Override
    public void hideProgress() {
        dismissProgress();
    }

    @Override
    public void showError(String errorMessage) {
        hideProgress();
        ToastUtils.showLongToast(context, errorMessage);
    }

    @Override
    public void onMovieItemClicked(Movie movie) {
        router.goToMovieDetails(movie);
    }

    private void refreshMovies(List<Movie> movies) {
        hideProgress();
        this.movies.clear();
        this.movies.addAll(movies);
        movieAdapter.notifyDataSetChanged();
    }
}